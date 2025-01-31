package com.creaturelove.storeservice.service;

import com.creaturelove.storeservice.db.entity.*;
import com.creaturelove.storeservice.db.repository.CartRepository;
import com.creaturelove.storeservice.db.repository.ProductRepository;
import com.creaturelove.storeservice.externalService.FactoryClient;
import com.creaturelove.storeservice.model.dto.CartProductDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final FactoryClient factoryClient;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, OrderService orderService, FactoryClient factoryClient) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.factoryClient = factoryClient;
        this.orderService = orderService;
    }

    public List<CartProductDto> getCart(Long userId) {
        // get cart product list by userId
        Set<CartProductEntity> cartProductList = cartRepository.findById(userId).get().getCartProducts();

        // map cart product entity to cart product dto
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        for(CartProductEntity cpe : cartProductList) {
            ProductEntity productEntity = factoryClient.getProductById(cpe.getProductId());
            CartProductDto cpd = new CartProductDto(
              productEntity.id(),
              productEntity.name(),
              productEntity.description(),
              productEntity.price(),
              cpe.getQuantity()
            );
            cartProductDtoList.add(cpd);
        }

        // return cart product dto list
        return cartProductDtoList;
    }

    public void addProductToCart(Long userId, String productId) {
        // get cart entity by userId
        Optional<CartEntity> cart = cartRepository.findByUserId(userId);

        // get cart product list
        Set<CartProductEntity> cartProductList = cart.get().getCartProducts();

        // find the product in the cart
        Optional<CartProductEntity> existingProduct = cartProductList.stream()
                .filter(cp -> cp.getProductId().equals(productId))
                .findFirst();

        // product exists, add quantity
        if (existingProduct.isPresent()) {
            CartProductEntity cpe = existingProduct.get();
            cpe.setQuantity(cpe.getQuantity() + 1);
        }

        // product doesn't exist, create one
        else {
            CartProductEntity newCartProduct = new CartProductEntity();
            newCartProduct.setCart(cart.get());
            newCartProduct.setProductId(productId);
            newCartProduct.setQuantity(1);
            cart.get().getCartProducts().add(newCartProduct);
        }

        // get product price in mongodb, update cart total price
        ProductEntity product = factoryClient.getProductById(productId);
        cartRepository.updateTotalPrice(cart.get().getId(), cart.get().getTotalPrice().add(product.price()));

        // save updated cart
        cartRepository.save(cart.get());
    }

    public void deleteProductFromCart(Long userId, String productId) {
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<CartProductEntity> existingProduct = cart.getCartProducts().stream()
                .filter(cp -> cp.getProductId().equals(productId))
                .findFirst();

        // product exists, delete quantity
        if (existingProduct.isPresent()) {
            CartProductEntity cpe = existingProduct.get();
            cpe.setQuantity(cpe.getQuantity() - 1);
            if(cpe.getQuantity() == 0) {
                cart.getCartProducts().remove(cpe);
            }
            cartRepository.updateTotalPrice(cart.getId(), cart.getTotalPrice().subtract(factoryClient.getProductById(productId).price()));
        }
        // product doesn't exist, throw exception
        else {
            throw new RuntimeException("Product not found");
        }
    }

    public void clearCart(Long userId) {
        // load cart
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        // clear cart product list
        cart.getCartProducts().clear();

        // update total_price
        cart.setTotalPrice(BigDecimal.ZERO);

        // 保存购物车，自动级联删除所有 CartProduct
        cartRepository.save(cart);
    }

    public void checkout(Long userId) {
        // get cart products by userId
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
        Set<CartProductEntity> cartProducts = cart.getCartProducts();

        // if cart is empty, throw exception
        if(cartProducts.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot checkout.");
        }

        // map cart products list to order items list
        List<OrderItemEntity> orderItems = cartProducts.stream().map(cartProduct -> {
            OrderItemEntity item = new OrderItemEntity();
            item.setProductId(cartProduct.getProductId());
            String productName = factoryClient.getProductById(cartProduct.getProductId()).name();
            BigDecimal productPrice = factoryClient.getProductById(cartProduct.getProductId()).price();
            item.setProductName(productName);
            item.setProductPrice(productPrice);
            item.setQuantity(cartProduct.getQuantity());
            return item;
        }).toList();

        OrderEntity order = orderService.createOrder(userId, orderItems, cart.getTotalPrice());

        clearCart(userId);
    }


}

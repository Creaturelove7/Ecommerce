package com.creaturelove.storeservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

public class PaymentService {
    public static void main(String[] args) throws StripeException {
        Stripe.apiKey = "sk_test_51PAFCDDYqweIfH16EbdhO57AGT9ulfXSN0H4gtnKk1Uiyvaz7v1lWdX5JxNtcctuvL9zHaUVPMrVfK6fl5eJ1fil00hx6JCt9p";


        ProductCreateParams productParams =
                ProductCreateParams.builder()
                        .setName("Starter Subscription")
                        .setDescription("$12/Month subscription")
                        .build();
        Product product = Product.create(productParams);
        System.out.println("Success! Here is your starter subscription product id: " + product.getId());

        PriceCreateParams params =
                PriceCreateParams
                        .builder()
                        .setProduct(product.getId())
                        .setCurrency("usd")
                        .setUnitAmount(1200L)
                        .setRecurring(
                                PriceCreateParams.Recurring
                                        .builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build())
                        .build();
        Price price = Price.create(params);
        System.out.println("Success! Here is your starter subscription price id: " + price.getId());
    }
}
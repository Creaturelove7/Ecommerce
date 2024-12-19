db.createCollection("products");

db.products.insertMany([
    {
        product_id: UUID(),
        name: "Smart Light Bulb",
        description: "IoT-enabled light bulb",
        price: 19.99,
        category: "Lighting",
        features: ["Wi-Fi", "Energy Efficient"],
        created_at: new Date()
    },
    {
        product_id: UUID(),
        name: "Smart Thermostat",
        description: "IoT-enabled thermostat",
        price: 149.99,
        category: "Climate Control",
        features: ["Wi-Fi", "Mobile App"],
        created_at: new Date()
    }
]);
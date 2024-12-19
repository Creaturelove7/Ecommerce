package com.creaturelove.ecommercebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.0.12")
class ECommerceBackendApplicationTests {

	@Autowired
	MongoTemplate mongoTemplate;



	@Test
	void contextLoads() {
		Person person = new Person("david", 2);
		mongoTemplate.insert(person);



	}
}

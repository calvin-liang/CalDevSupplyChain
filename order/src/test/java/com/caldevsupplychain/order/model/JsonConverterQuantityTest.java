package com.caldevsupplychain.order.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;


@Slf4j
@JsonTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsonConverterQuantityTest.class)
public class JsonConverterQuantityTest {

	@Autowired
	ObjectMapper objectMapper;

	String strQuantity = "{\"XS\":1,\"S\":2,\"M\":3,\"L\":4}";

	@Test
	public void testJsonToQuantityEntity() throws IOException {
		Quantity quantity = objectMapper.readValue(strQuantity, Quantity.class);
		assertEquals(strQuantity, objectMapper.writeValueAsString(quantity));
	}

	@Test
	public void testQuantityEntityToJson() throws IOException {
		Quantity quantity = new Quantity();
		quantity.setXS(1);
		quantity.setS(2);
		quantity.setM(3);
		quantity.setL(4);
		String convertedJsonQuantity = objectMapper.writeValueAsString(quantity);
		assertEquals(strQuantity, convertedJsonQuantity);
	}

}

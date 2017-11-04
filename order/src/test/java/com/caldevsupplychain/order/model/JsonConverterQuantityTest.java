package com.caldevsupplychain.order.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


@Slf4j
@JsonTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsonConverterQuantityTest.class)
public class JsonConverterQuantityTest {

	@Autowired
	ObjectMapper objectMapper;

	String strQuantity = "{\"XS\":99.00,\"S\":99.99,\"M\":55.55,\"L\":70.00}";

	@Test
	public void testJsonToQuantityEntity() throws IOException {
		Quantity quantity = objectMapper.readValue(strQuantity, Quantity.class);
		assertEquals(strQuantity, objectMapper.writeValueAsString(quantity));
	}

	@Test
	public void testQuantityEntityToJson() throws IOException {
		Quantity quantity = new Quantity();
		quantity.setXS(new BigDecimal(99.00));
		quantity.setS(new BigDecimal(99.99));
		quantity.setM(new BigDecimal(55.55));
		quantity.setL(new BigDecimal(70.00));
		String convertedJsonQuantity = objectMapper.writeValueAsString(quantity);
		assertEquals(strQuantity, convertedJsonQuantity);
	}
}

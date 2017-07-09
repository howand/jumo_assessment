package com.jumoassess.aggregator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.jumoassess.aggregator.CsvFileMapperBean;

public class CsvFileMapperBeanTest {
	
	@Test
	public void setAndGetMSISDNSetsAndGetsTheCorrectMemberVariable() {
		final String MSISDN_TEST = "MSISDNTest1";
		
		CsvFileMapperBean bean = new CsvFileMapperBean();
		
		bean.setMsisdn(MSISDN_TEST);
		
		assertEquals(MSISDN_TEST, bean.getMSISDN());
	}

	@Test
	public void setAndGetNetworkSetsAndGetsTheCorrectMemberVariable() {
		final String NETWORK_TEST = "NetworkTest1";
		
		CsvFileMapperBean bean = new CsvFileMapperBean();
		
		bean.setNetwork(NETWORK_TEST);
		
		assertEquals(NETWORK_TEST, bean.getNetwork());
	}
	
	@Test
	public void setAndGetProductSetsAndGetsTheCorrectMemberVariable() {
		final String PRODUCT_TEST = "ProductTest1";
		
		CsvFileMapperBean bean = new CsvFileMapperBean();
		
		bean.setProduct(PRODUCT_TEST);
		
		assertEquals(PRODUCT_TEST, bean.getProduct());
	}
	
	@Test
	public void setAndGetAmountSetsAndGetsTheCorrectMemberVariable() {
		final BigDecimal AMOUNT_TEST = new BigDecimal("1");
		
		CsvFileMapperBean bean = new CsvFileMapperBean();
		
		bean.setAmount(AMOUNT_TEST);
		
		assertEquals(AMOUNT_TEST, bean.getAmount());
	}
	
	@Test
	public void setAndGetDateSetsAndGetsTheCorrectMemberVariable() {
		final String DATE_TEST = "01-Jan-2016";
		
		CsvFileMapperBean bean = new CsvFileMapperBean();
		
		bean.setDate(DATE_TEST);
		
		assertEquals(DATE_TEST, bean.getDate());
	}
}

package com.example.demo;

import exchangerate.CurrencyConverter;
import exchangerate.ExchangeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoApplicationTests {
	List<ExchangeRate> exchangeRates;
	CurrencyConverter currencyConverter;
	String Dollar;
	String Banana;
	String Apple;

	@BeforeEach
	void init(){
		Dollar = "Dollar";
		Banana = "Banana";
		Apple = "Apple";
		exchangeRates = new ArrayList<>();
		exchangeRates.add(new ExchangeRate(Dollar, Apple ,  BigDecimal.valueOf(5)));
		exchangeRates.add(new ExchangeRate(Dollar, Banana, BigDecimal.valueOf(10)));
		exchangeRates.add(new ExchangeRate(Banana, Apple, BigDecimal.valueOf(0.50)));
		exchangeRates.add(new ExchangeRate(Banana, Dollar, BigDecimal.valueOf(0.10)));
		exchangeRates.add(new ExchangeRate(Apple, Dollar, BigDecimal.valueOf(0.20)));
		exchangeRates.add(new ExchangeRate(Apple, Banana, BigDecimal.valueOf(2)));
		currencyConverter = new CurrencyConverter(exchangeRates);
	}

	@Test
	void contextLoads() {
		assertThat(BigDecimal.valueOf(100)).isEqualByComparingTo(currencyConverter.convert(Dollar,Apple, BigDecimal.valueOf(20)));
		assertThat(BigDecimal.valueOf(200)).isEqualByComparingTo(currencyConverter.convert(Dollar,Banana, BigDecimal.valueOf(20)));

		assertThat(BigDecimal.valueOf(1)).isEqualByComparingTo(currencyConverter.convert(Apple,Dollar, BigDecimal.valueOf(5)));
		assertThat(BigDecimal.valueOf(10)).isEqualByComparingTo(currencyConverter.convert(Apple,Banana, BigDecimal.valueOf(5)));

		assertThat(BigDecimal.valueOf(0.5)).isEqualByComparingTo(currencyConverter.convert(Banana,Dollar, BigDecimal.valueOf(5)));
		assertThat(BigDecimal.valueOf(2.5)).isEqualByComparingTo(currencyConverter.convert(Banana,Apple, BigDecimal.valueOf(5)));

		assertThat(BigDecimal.valueOf(0)).isEqualByComparingTo(currencyConverter.convert(Banana,Dollar, BigDecimal.valueOf(0)));
		assertThat(BigDecimal.valueOf(0)).isEqualByComparingTo(currencyConverter.convert(Banana,Apple, BigDecimal.valueOf(-5)));
		assertThat(BigDecimal.valueOf(0)).isEqualByComparingTo(currencyConverter.convert("Bananaaa",Apple, BigDecimal.valueOf(5)));
	}
}

package com.blackun.chapter8;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class MainTest {
	private static final Logger logger = LogManager.getLogger(MainTest.class);

	@Test
	public void test1(){
		List<String> friends = new ArrayList<>();
		friends.add("Raphael");
		friends.add("Olivia");
		friends.add("Thibaut");

		logger.info("friends = {}", friends);
	}

	@Test
	public void test2(){
		List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");

		logger.info("friends = {}", friends);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test3(){
		List<String> friends = Arrays.asList("Raphael", "Olivia");
		// 이거는 괜찮다.
		friends.set(0, "Richard");
		// 이거는 Exception 발생, friends 가 고정 크기의 리스트이기 때문에
		friends.add("Thibaut");

		logger.info("friends = {}", friends);
	}

	@Test
	public void test4(){
		Set<String> friends = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));

		logger.info("friends = {}", friends);
	}

	@Test
	public void test5(){
		Set<String> friends = Stream.of("Raphael", "Olivia", "Thibaut")
									.collect(Collectors.toSet());

		logger.info("friends = {}", friends);
	}
}


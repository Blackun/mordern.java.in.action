package com.blackun.chapter8;

import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Java9Test {
	private static final Logger logger = LogManager.getLogger(Java9Test.class);

	@Test
	public void test1(){
		List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
		logger.info("friends : {}", friends);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test2(){
		List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
		friends.add("Chih-Chun");
		logger.info("friends : {}", friends);
	}

	@Test
	public void test3(){
		Set<String> friends = Set.of("Raphael", "Olivia", "Thibaut");
		logger.info("friends : {}", friends);
	}
}

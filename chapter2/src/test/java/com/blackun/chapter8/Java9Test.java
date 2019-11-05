package com.blackun.chapter8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static java.util.Map.entry;

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

	@Test
	public void test_두_개의_맵_합침(){
		Map<String, String> family = Map.ofEntries(entry("Teo", "Star wars"), entry("Cristina", "James Bond"));
		Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"));
		Map<String, String> everyone = new HashMap<>(family);
		everyone.putAll(friends);
		logger.info("everyone : {}", everyone);
	}

	@Test
	public void test_두_개의_맵_합침_Ver2(){
		Map<String, String> family = Map.ofEntries(entry("Teo", "Star wars"), entry("Cristina", "James Bond"));
		Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));
		Map<String, String> everyone = new HashMap<>(family);
		everyone.putAll(friends);
		// Cristina 가 friends 에 설정된 value로 업데이트 된다.
		logger.info("everyone : {}", everyone);
	}


	@Test
	public void test_두_개의_맵_합침_Ver3(){
		Map<String, String> family = Map.ofEntries(entry("Teo", "Star wars"), entry("Cristina", "James Bond"));
		Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));
		Map<String, String> everyone = new HashMap<>(family);
		// 중복된 키가 있으면 두 값을 연결
		friends.forEach((k,v) -> everyone.merge(k, v, (movie1, movie2)->movie1 + " & " + movie2));
		logger.info("everyone : {}", everyone);
	}

	@Test
	public void test_merge를_이용한_초기화_검사(){
		Map<String, Long> moviesToCount = new HashMap<>();
		String movieName = "JamesBond";
		Long count = moviesToCount.get(movieName);
		if(count == null){
			moviesToCount.put(movieName, 1L);
		} else {
			moviesToCount.put(movieName, count + 1);
		}

		logger.info("moviesToCount : {}", moviesToCount);
	}

	@Test
	public void test_merge를_이용한_초기화_검사_Ver2(){
		Map<String, Long> moviesToCount = new HashMap<>();
		String movieName = "JamesBond";
		moviesToCount.merge(movieName, 1L, (key, count)->count + 1L);

		// 위와 동일한 결과를 표시한다.
		logger.info("moviesToCount : {}", moviesToCount);
	}

	@Test
	public void test_퀴즈_8_2(){
		Map<String, Integer> movies = new HashMap<>();
		movies.put("JamesBond", 20);
		movies.put("Matrix", 15);
		movies.put("Harry Potter", 5);
		Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();

		while(iterator.hasNext()){
			Map.Entry<String, Integer> entry = iterator.next();
			if(entry.getValue() < 10){
				iterator.remove();
			}
		}
		// Harry Potter 이 제거된다.
		logger.info("movies : {}", movies);
	}

	@Test
	public void test_퀴즈_8_2_Ver2(){
		Map<String, Integer> movies = new HashMap<>();
		movies.put("JamesBond", 20);
		movies.put("Matrix", 15);
		movies.put("Harry Potter", 5);

		// removeIf를 사용하면서 코드가 간소화 되었다.
		movies.entrySet().removeIf(entry->entry.getValue()<10);

		// Harry Potter 이 제거된다.
		logger.info("movies : {}", movies);
	}
}

package com.blackun.chapter5;

import java.util.stream.Stream;
import org.junit.Test;

public class StreamTest {

	@Test
	public void test01(){
		// 5.8.5 함수로 무한 스트림만들기
		// iterate 메서드
		Stream.iterate(0, n->n+2)
				.limit(10)
				.forEach(System.out::println);
	}

	@Test
	public void test02(){
		// 퀴즈 5-4 : 피보나치수열 집합
		Stream.iterate(new int[]{0, 1}, n->new int[]{n[1], n[0]+n[1]})
				.limit(20)
				.map(t->t[0])   // 배열의 첫번째 항목만 가져오면 된다.
				.forEach(System.out::println);
	}

	@Test
	public void test03(){
		System.out.println(System.currentTimeMillis());
	}
}

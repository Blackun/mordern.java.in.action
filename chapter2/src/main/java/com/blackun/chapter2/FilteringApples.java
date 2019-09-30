package com.blackun.chapter2;

import com.blackun.chapter2.filter.AppleGreenColorPredicate;
import com.blackun.chapter2.filter.AppleHeavyWeightPredicate;
import com.blackun.chapter2.filter.ApplePredicate;
import com.blackun.chapter2.filter.Predicate;
import com.blackun.chapter2.formatter.AppleFancyFormatter;
import com.blackun.chapter2.formatter.AppleFormatter;
import com.blackun.chapter2.formatter.AppleSimpleFormatter;
import com.blackun.chapter2.model.Apple;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

import static com.blackun.chapter2.model.Color.GREEN;
import static com.blackun.chapter2.model.Color.RED;

public class FilteringApples {
	public static void main(String... args){
		List<Apple> inventory = Arrays.asList(new Apple(80, GREEN),
											new Apple(155, GREEN),
											new Apple(120, RED));
		List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());

		// 1단계 : 추상적 조건으로 필터링
		// List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

		// 2단계 : 익명 클래스로 필터링
		/*
		List<Apple> greenApples = filterApples(inventory, new ApplePredicate() {
			@Override public boolean test(Apple apple) {
				return GREEN.equals(apple.getColor());
			}
		});
		*/

		// 3단계 : 람다 표현식으로 필터링
		// List<Apple> greenApples = filterApples(inventory, (apple)->GREEN.equals(apple.getColor()));

		// 4단계 : 리스트 형식으로 추상화
		List<Apple> greenApples = filter(inventory, (apple)->GREEN.equals(apple.getColor()));

		System.out.println("===== origin");
		prettyPrintApple(inventory, new AppleSimpleFormatter());
		System.out.println("===== heavyApples");
		prettyPrintApple(heavyApples, new AppleSimpleFormatter());
		System.out.println("===== greenApples");
		prettyPrintApple(greenApples, new AppleFancyFormatter());

		// 정렬 : Comparator 로 정렬
		inventory.sort(Comparator.comparing(Apple::getWeight));

		System.out.println("===== sort");
		prettyPrintApple(inventory, new AppleSimpleFormatter());
	}

	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for(T e: list){
			if(p.test(e)){
				result.add(e);
			}
		}
		return result;
	}

	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
		for(Apple apple: inventory){
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}
}

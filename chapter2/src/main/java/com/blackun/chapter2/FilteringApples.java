package com.blackun.chapter2;

import com.blackun.chapter2.filter.AppleGreenColorPredicate;
import com.blackun.chapter2.filter.AppleHeavyWeightPredicate;
import com.blackun.chapter2.filter.ApplePredicate;
import com.blackun.chapter2.formatter.AppleFancyFormatter;
import com.blackun.chapter2.formatter.AppleFormatter;
import com.blackun.chapter2.formatter.AppleSimpleFormatter;
import com.blackun.chapter2.model.Apple;
import java.util.ArrayList;
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
		List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

		System.out.println("===== heavyApples");
		prettyPrintApple(heavyApples, new AppleSimpleFormatter());
		System.out.println("===== greenApples");
		prettyPrintApple(greenApples, new AppleFancyFormatter());
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

	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
		for(Apple apple: inventory){
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}
}

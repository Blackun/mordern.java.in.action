package com.blackun.chapter2.formatter;

import com.blackun.chapter2.model.Apple;

public class AppleFancyFormatter implements AppleFormatter {
	public String accept(Apple apple){
		String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
		return "A " + characteristic + " " + apple.getColor() + " apple";
	}
}

package com.blackun.chapter2.formatter;

import com.blackun.chapter2.model.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
	public String accept(Apple apple){
		return "An apple of " + apple.getWeight() + "g";

	}
}

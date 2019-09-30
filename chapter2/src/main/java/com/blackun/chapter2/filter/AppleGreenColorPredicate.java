package com.blackun.chapter2.filter;

import com.blackun.chapter2.model.Apple;

import static com.blackun.chapter2.model.Color.GREEN;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override public boolean test(Apple apple) {
		return GREEN.equals(apple.getColor());
	}
}

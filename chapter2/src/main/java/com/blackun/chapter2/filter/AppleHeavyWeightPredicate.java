package com.blackun.chapter2.filter;

import com.blackun.chapter2.model.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
	@Override public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}

package com.matt.business.test.util;

public class Test {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		builder.append("1234567890");
		StringBuilder str = builder.replace(5, 8, "");
		System.out.println(str);
	}
}

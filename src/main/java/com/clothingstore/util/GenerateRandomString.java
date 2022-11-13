package com.clothingstore.util;

import java.util.Random;

public class GenerateRandomString {
	static public String randomCode(int n) {
		final String alpha = "qwertyuioplkjhgfdsazxcvbnm";
		final String alphaUpperCase = alpha.toUpperCase();
		final String number = "0123456789";
		final String alphaAndUpperCase = alpha+alphaUpperCase;
		final String all = alpha + alphaUpperCase + number;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int numberRandom = randomNumber(0, all.length() - 1);
			char ch = all.charAt(numberRandom);
			sb.append(ch);
		}
		return sb.toString();
	}

	private static int randomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) + min;
	}
}

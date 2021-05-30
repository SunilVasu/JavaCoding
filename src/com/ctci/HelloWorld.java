package com.ctci;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("HelloWorld!!!");

		testStreams();

		int[] numArr = new int[1];
		numArr[0] = 0;
		change(numArr);
		System.out.println("numArr == " + numArr[0]);

		List numList = new ArrayList<>(1);
		numList.add(1);
		changeList(numList);
		System.out.println("numList == " + numList);

	}

	private static void change(int[] numArr) {
		numArr[0] = 1;
	}

	private static void changeList(List numList) {
		numList.add(2);
	}

	private static void testStreams() {
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);

		arr.forEach(x -> System.out.println(x));

	}

}

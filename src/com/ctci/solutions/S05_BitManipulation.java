package com.ctci.solutions;

public class S05_BitManipulation {

	public static void main(String[] args) {
		System.out.println("###Bit Manipulation###");

		// #5.1 Insertion: insert N into M
		System.out.println("#5.1 Insertion");
		insertion();

		// #5.2 Binary to String
		System.out.println("\n#5.2 Binary to String");
		printBinary();

		// #5.3 Flip Bit to Win
		System.out.println("\n#5.3 Flip Bit to Win");
		longestSeqOfOnes();

		// #5.4 Next Number
		int n = 1775;
		System.out.println("\n#5.4 Next Number \ninput n =  " + Integer.toBinaryString(n));
		getNext(n);
		getPrev(n);

		// #5.6 Conversion
		System.out.println("\n#5.6 Conversion");
		conversion();

		// #5.7 Pairwise Swap
		System.out.println("\n#5.7 Pairwise Swap");
		pairwiseSwap();

		// #5.8 Draw Line
		System.out.println("\n#5.8 Draw Line");
		drawLine();
	}

	// #5.1 Insertion: insert N into M
	public static void insertion() {
		byte m = 0b1011101;
		byte n = 0b101;
		int i = 2, j = 4;
		System.out.println("m = " + Integer.toBinaryString(m) + "; n = " + Integer.toBinaryString(n));
		// step 1: create mask
		byte left = (byte) (~0 << (j + 1));
		System.out.println("left = " + Integer.toBinaryString(left));
		byte right = (byte) ((1 << i) - 1);
		System.out.println("right = " + Integer.toBinaryString(right));
		byte mask = (byte) (left ^ right);
		System.out.println("mask => " + Integer.toBinaryString(mask));
		m = (byte) (m & mask);
		System.out.println("m => " + Integer.toBinaryString(m));
		// step 2: arrange n
		n = (byte) (n << i);
		System.out.println("n => " + Integer.toBinaryString(n));
		// step 3: merge m & n
		byte ans = (byte) (m | n);
		System.out.println("insertions = " + Integer.toBinaryString(~0));

	}

	// #5.2 Binary to String
	public static void printBinary() {
		double num = 0.25;

		StringBuilder sb = new StringBuilder();
		sb.append(".");
		while (num > 0) {
			if (sb.length() >= 32) {
				System.out.println("Error");
				return;
			}
			double r = num * 2;
			if (r >= 1) {
				sb.append(1);
				num = r - 1;
			} else {
				sb.append(0);
				num = r;
			}
		}
		System.out.println("Binary => " + sb.toString());
	}

	// #5.3 Flip Bit to Win
	public static void longestSeqOfOnes() {
		int a = 1775;
		System.out.println("a = " + Integer.toBinaryString(a));
		if (~a == 0) {
			System.out.println("Longest Seq of 1s = " + (Integer.BYTES * 8));
			return;
		}

		int currLen = 0;
		int prevLen = 0;
		int maxLen = 1;
		while (a != 0) {
			if ((a & 1) == 1) {
				currLen++;
			} else if ((a & 1) == 0) {
				prevLen = (a & 2) == 0 ? 0 : currLen;
				currLen = 0;
			}
			// System.out.println(Integer.toBinaryString(a) + " - " + currLen +
			// " - " + prevLen);
			maxLen = Math.max(maxLen, prevLen + currLen + 1);
			a >>>= 1;
		}
		System.out.println("maxLen = " + maxLen);
	}

	// #5.4 Next Number
	public static void getNext(int n) {
		int c = n;
		int c0 = 0;
		int c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		if (c0 + c1 == 31 || c0 + c1 == 0) {
			System.out.println("ERROR getNext: n=11..1100..00, then there is no bigger num with same no. 1s");
			return;
		}

		int p = c0 + c1; // position of rightmost non-trailing 0
		n |= (1 << p); // flip rightmost non-trailing 0
		n &= ~((1 << p) - 1); // clear all bits to the right of p
		n |= (1 << (c1 - 1)) - 1; // insert (c1-1) ones on the right;

		System.out.println("Next Num = " + Integer.toBinaryString(n));
	}

	public static void getPrev(int n) {
		int temp = n;
		int c0 = 0;
		int c1 = 0;
		while ((temp & 1) == 1) {
			c1++;
			temp >>= 1;
		}
		if (temp == 0) {
			System.out.println("ERROR: getPrev");
			return;
		}
		while (((temp & 1) == 0) && (temp != 0)) {
			c0++;
			temp >>= 1;
		}
		int p = c0 + c1; // position of rightmost non-trailing one
		n &= ((~0) << (p + 1)); // clears from bit p onwards

		int mask = (1 << (c1 + 1)) - 1; // Seq of (c1+1) ones
		n |= mask << (c0 - 1);

		System.out.println("Prev Num = " + Integer.toBinaryString(n));
	}

	// #5.5 Debugger
	// n&(n-1) = 0 :: checks if n is a power of 2 or if n=0

	// #5.6: Conversion
	public static void conversion() {
		int a = 29;// 11101
		int b = 15;// 01111
		int count = 0;
		// XOR represent a bit difference between A&B
		// after XOR, shift right and count 1s
		for (int c = a ^ b; c != 0; c = c >> 1) {
			count += c & 1;
		}
		System.out.println("Conversion Needed = " + count);
		count = 0;
		// c&(c-1) :: flips least significant bit & count how long to reach 0
		for (int c = a ^ b; c != 0; c = c & (c - 1)) {
			count++;
		}
		System.out.println("Conversion Needed = " + count);

	}

	// 5.7 Pairwise Swap
	public static void pairwiseSwap() {
		byte x = 0b101101;
		// find odd bits 0101 & shift right
		// find even bit 1010 & shift left
		int a = (x & 0b101010) >>> 1;
		int b = (x & 0b010101) << 1;
		System.out.println("a = " + Integer.toBinaryString(a) + " : b= " + Integer.toBinaryString(b));
		System.out.println("a | b = " + Integer.toBinaryString(a | b));
		int ans = ((x & 0xaa) >>> 1) | ((x & 0x55) << 1);
		System.out.println("Input = " + Integer.toBinaryString(x) + "\nPairwise Swap = " + Integer.toBinaryString(ans));
	}

	// 5.8 Draw Line: drawn horizontal line from (x1, y) to (x2, y)
	public static void drawLine() {
		byte[] screen = new byte[] { 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0 };
		int width = 0;
		int x1 = 1, x2 = 5;
		int y = 1;

		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) {
			first_full_byte++;
		}

		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) {
			last_full_byte++;
		}

		// Set full byte
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}

		// create mask for start & end of line
		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

		// set start & end of line
		if ((x1 / 8) == (x2 / 8)) {
			// x1 & x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			}
		}
		for (byte n : screen)
			System.out.print(n + " ");
	}
}

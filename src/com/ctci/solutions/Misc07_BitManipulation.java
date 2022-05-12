package com.ctci.solutions;

public class Misc07_BitManipulation {

	public static void main(String[] args) {
		facts();
		operations();
	}

	// #XOR
	// 0 0 = 0
	// 0 1 = 1
	// 1 0 = 1
	// 1 1 = 0

	public static void facts() {
		byte a = 0b100101;
		byte ones = 0b111111;
		byte zeros = 0b000000;

		System.out.println("#1 Basic:: a = " + a + "   Binary = " + Integer.toBinaryString(a));
		System.out.println("a >> 1 = " + (a >> 1) + " : " + Integer.toBinaryString(a >> 1));
		System.out.println();
		System.out.println("a ^ 0s = " + (a ^ zeros) + " : " + Integer.toBinaryString(a ^ zeros));
		System.out.println("a ^ 1s = " + (a ^ ones) + " : " + Integer.toBinaryString(a ^ ones));
		System.out.println("a ^ a = " + (a ^ a) + " : " + Integer.toBinaryString(a ^ a));
		System.out.println();
		System.out.println("a & 0s = " + (a & zeros) + " : " + Integer.toBinaryString(a & zeros));
		System.out.println("a & 1s = " + (a & ones) + " : " + Integer.toBinaryString(a & ones));
		System.out.println("a & a = " + (a & a) + " : " + Integer.toBinaryString(a & a));
		System.out.println();
		System.out.println("a | 0s = " + (a | zeros) + " : " + Integer.toBinaryString(a | zeros));
		System.out.println("a | 1s = " + (a | ones) + " : " + Integer.toBinaryString(a | ones));
		System.out.println("a | a = " + (a | a) + " : " + Integer.toBinaryString(a | a));
		System.out.println();

		byte b = -10;
		byte c = 10;
		System.out.println("Basic:: b = " + b + "   Binary = " + Integer.toBinaryString(b));
		System.out.println("Basic:: c = " + c + "   Binary = " + Integer.toBinaryString(c));
		// Arithmetic shift: 0 is added to the MSB
		System.out.println("b >> 1 = " + (b >> 1) + "   Binary = \t\t" + Integer.toBinaryString(b >> 1));
		// Logical shift: 1 is added to the MSB(unsigned shift) sign not
		// consider; negative always becomes positive
		System.out.println("b >>> 1 = " + (b >>> 1) + "   Binary = " + Integer.toBinaryString(b >>> 1));
		System.out.println("c >>> 1= " + (c >>> 1) + "   Binary = " + Integer.toBinaryString(c >>> 1));

		System.out.println("1000 - 1 = " + (0b1000 - 0b1) + "   Binary = " + Integer.toBinaryString(0b1000 - 0b1));
		System.out.println(
				"1000 - 1 = " + (0b1000 - (0b1 << 2)) + " Bin = " + Integer.toBinaryString(0b1000 - (0b1 << 2)));
	}

	public static void operations() {
		byte a = 0b1101;
		System.out.println("\n#2 Operations:: a = " + a + "   Binary = " + Integer.toBinaryString(a));

		System.out.println("getBit = " + getBit(a, 3));
		System.out.println("setBit = " + (byte) setBit(a, 1));
		System.out.println("clearBit = " + (byte) clearBit(a, 0));
		System.out.println("clearBit (MSB to I)= " + (byte) clearBit_MSBthroughI(a, 2));
		System.out.println("clearBit (I to 0)= " + (byte) clearBit_Ithrough0(a, 2));
		System.out.println("updateBit = " + (byte) updateBit(a, 1, true));
	}

	public static boolean getBit(byte num, int i) {
		return (num & (1 << i)) != 0;
	}

	public static int setBit(byte num, int i) {
		return num | (1 << i);
	}

	public static int clearBit(byte num, int i) {
		int mask = ~(1 << i);
		return num & mask;
	}

	// mask = 00011
	public static int clearBit_MSBthroughI(byte num, int i) {
		int mask = (1 << i) - 1;
		System.out.println("mask = " + Integer.toBinaryString(mask));
		return num & mask;
	}

	// mask = 11000
	public static int clearBit_Ithrough0(byte num, int i) {
		int mask = ~0 << (i + 1);
		// mask = -1 << (i + 1);
		System.out.println("mask = " + Integer.toBinaryString(mask));
		return num & mask;
	}

	public static int updateBit(byte num, int i, boolean bitIs1) {
		int value = bitIs1 ? 1 : 0;
		int mask = ~(1 << i);
		return (num & mask) | (value << i);
	}

}

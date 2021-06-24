package com.ctci.solutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class S3_StackQueue {
	public static void main(String[] args) {
		System.out.println("###Stacks and Queues###\nInsert Order 1-2-3");
		// Stack -> pop, push, peek, isEmpty
		// Queue -> add, remove, peek, isEmpty
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("stack.peek():" + stack.peek());
		System.out.println("stack.pop():" + stack.pop());
		printStack(stack, "stack");
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println("queue.peek():" + queue.peek());
		System.out.println("queue.remove():" + queue.remove());
		printQueue(queue, "queue");

		// 3.1 three in one
		multiStack();

		// 3.2 Stack min
		stackMin();

		// 3.3 Stack of plates: Implement DS SetOfStacks composed of multiple
		// stacks; New stack created when capacity is reached

		// 3.4 Queue via Stacks - MISC 1 & 2

		// 3.5 Sort Stack
		sortStack();
		// 3.6 Animal Shelter
		animalShelter();
		// MISC 1: Implement Queue using Stack
		System.out.println("\n#MISC 1: Implement Queue using Stack");
		MyQueue q = new MyQueue();
		q.add(1);
		q.add(2);
		q.add(3);
		System.out.println("q.remove():" + q.remove());
		System.out.println("q.peek():" + q.peek());
		System.out.println("q.isEmpty():" + q.isEmpty());

		// MISC 2 Implement Stack using Queue
		System.out.println("\n#MISC 2: Implement Stack using Queue");
		MyStack s = new MyStack();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println("s.pop():" + s.pop());
		System.out.println("s.peek():" + s.peek());
		System.out.println("s.isEmpty():" + s.isEmpty());

	}

	// 3.1 three in one
	public static void multiStack() {
		FixedMultiStack stack = new FixedMultiStack(3);
		System.out.println("\n#3.1 Three in One Stack");
		try {
			stack.push(0, 11);
			stack.push(1, 22);
			stack.push(2, 33);
			System.out.println("stack0.pop() : " + stack.pop(0));
			System.out.println("stack1.pop() : " + stack.pop(1));
			System.out.println("stack2.peek() : " + stack.peek(2));
			System.out.println("stack2.pop() : " + stack.pop(2));
			// System.out.println("stack3.pop() : " + stack.pop(2));
			stack.push(0, 44);
			stack.push(0, 55);
			stack.push(0, 66);
			// stack.push(0, 77);
			System.out.println("stack0.peek() : " + stack.peek(0));
		} catch (Exception e) {
			System.out.println("Exception Occurred : " + e);
		}

	}

	// 3.2 Stack min
	public static void stackMin() {
		System.out.println("\n#3.2 Stack min");
		StackWithMin stack = new StackWithMin();
		System.out.println("stack.min(): " + stack.min());
		stack.push(5);
		System.out.println("stack.min(): " + stack.min());
		stack.push(1);
		System.out.println("stack.min(): " + stack.min());

		StackWithMin2 stack2 = new StackWithMin2();
		System.out.println("stack2.min(): " + stack2.min());
		stack2.push(5);
		System.out.println("stack2.min(): " + stack2.min());
		stack2.push(1);
		System.out.println("stack2.min(): " + stack2.min());
		stack2.pop();
		System.out.println("stack2.min(): " + stack2.min());
	}

	// 3.5 Sort Stack
	public static void sortStack() {
		System.out.println("\n#3.5 Sort Stack");
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> r = new Stack<>();

		stack.push(9);
		stack.push(1);
		stack.push(5);

		while (!stack.isEmpty()) {
			int tmp = stack.pop();
			while (!r.isEmpty() && r.peek() > tmp) {
				stack.push(r.pop());
			}
			r.push(tmp);
		}

		while (!r.isEmpty()) {
			stack.push(r.pop());
		}

		printStack(stack, "Sorted Stack");

	}

	// 3.6 Animal Shelter
	public static void animalShelter() {
		System.out.println("\n#3.6 Animal Shelter");
		AnimalQueue q = new AnimalQueue();
		Dog d1 = new Dog("d1");
		Dog d2 = new Dog("d2");
		Cat c1 = new Cat("c1");

		q.enqueue(d1);
		q.enqueue(d2);
		q.enqueue(c1);

		System.out.println("dequeu Any : " + q.dequeueAny().name);
		System.out.println("dequeu Cat : " + q.dequeueCats().name);
		System.out.println("dequeu Dog : " + q.dequeueDogs().name);
	}

	// META
	public static void printStack(Stack stack, String msg) {
		System.out.print(msg + ": ");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
			if (!stack.isEmpty()) {
				System.out.print("-");
			}
		}
		System.out.print("\n");
	}

	public static void printQueue(Queue queue, String msg) {
		System.out.print(msg + ": ");
		while (!queue.isEmpty()) {
			System.out.print(queue.poll());
			if (!queue.isEmpty()) {
				System.out.print("-");
			}
		}
		System.out.print("\n");
	}
}

// 3.1 three in one: One stack used as 3 stacks. Each stack of fixed capacity.
class FixedMultiStack {
	private int numberOfStacks = 3;
	private int stackCapacity;
	private int[] values;
	private int[] sizes;

	public FixedMultiStack(int stackSize) {
		stackCapacity = stackSize;
		values = new int[stackSize * numberOfStacks];
		sizes = new int[numberOfStacks];
	}

	public void push(int stackNum, int value) throws Exception {
		if (isFull(stackNum)) {
			throw new Exception("Stack is Full. Failed to Push");
		}
		sizes[stackNum]++;
		values[indexOfTop(stackNum)] = value;
	}

	public int pop(int stackNum) throws Exception {
		if (isEmpty(stackNum)) {
			throw new Exception("Stack is Empty. Failed to Pop");
		}
		int topIndex = indexOfTop(stackNum);
		int value = values[topIndex];
		values[topIndex] = 0;
		sizes[stackNum]--;
		return value;
	}

	public int peek(int stackNum) throws Exception {
		if (isEmpty(stackNum)) {
			throw new Exception("Stack is Empty.");
		}
		return values[indexOfTop(stackNum)];
	}

	public boolean isEmpty(int stackNum) {
		return sizes[stackNum] == 0;
	}

	public boolean isFull(int stackNum) {
		return sizes[stackNum] == stackCapacity;
	}

	public int indexOfTop(int stackNum) {
		int offset = stackNum * numberOfStacks;
		int size = sizes[stackNum];
		return offset + size - 1;
	}

}

// 3.2 Stack with Min
class StackWithMin extends Stack<NodeWithMin> {
	public void push(int value) {
		int newMin = Math.min(value, min());
		super.push(new NodeWithMin(value, newMin));
	}

	public int min() {
		if (this.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return peek().min;
		}
	}
}

class NodeWithMin {
	int value;
	int min;

	public NodeWithMin(int value, int min) {
		this.value = value;
		this.min = min;
	}
}

// 3.2 [2] can do better by using a stack which keep track of min
class StackWithMin2 extends Stack<Integer> {
	Stack<Integer> s2;

	public StackWithMin2() {
		s2 = new Stack<Integer>();
	}

	public void push(int value) {
		if (value <= min()) {
			s2.push(value);
		}
		super.push(value);
	}

	@Override
	public Integer pop() {
		int value = super.pop();
		if (value == min()) {
			s2.pop();
		}
		return value;
	}

	public int min() {
		if (s2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}
}

// 3.6 Animal Shelter
abstract class Animal {
	private int order;
	protected String name;

	public Animal(String n) {
		name = n;
	}

	public void setOrder(int ord) {
		order = ord;
	}

	public int getOrder() {
		return order;
	}

	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}

class AnimalQueue {
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;

	public void enqueue(Animal a) {
		a.setOrder(order);
		order++;

		if (a instanceof Dog)
			dogs.addLast((Dog) a);
		else
			cats.addLast((Cat) a);
	}

	public Animal dequeueAny() {
		if (dogs.size() == 0) {
			return dequeueCats();
		} else if (cats.size() == 0) {
			return dequeueDogs();
		}

		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isOlderThan(cat)) {
			return dequeueDogs();
		} else {
			return dequeueCats();
		}
	}

	public Dog dequeueDogs() {
		return dogs.poll();
	}

	public Cat dequeueCats() {
		return cats.poll();
	}
}

class Dog extends Animal {
	public Dog(String n) {
		super(n);
	}
}

class Cat extends Animal {
	public Cat(String n) {
		super(n);
	}
}

// MISC 1: Implement Queue using Stack
class MyQueue {
	Stack<Integer> input = new Stack<>();
	Stack<Integer> output = new Stack<>();

	public MyQueue() {

	}

	public void add(int x) {
		input.add(x);
	}

	public int remove() {
		peek();
		return output.pop();
	}

	public int peek() {
		if (output.isEmpty()) {
			while (!input.isEmpty()) {
				output.push(input.pop());
			}
		}
		return output.peek();
	}

	public boolean isEmpty() {
		return input.isEmpty() && output.isEmpty();
	}
}

// MISC 2: Implement Stack using Queue
class MyStack {
	Queue<Integer> queue = new LinkedList<>();

	public MyStack() {

	}

	public void push(int x) {
		queue.add(x);
		for (int i = 1; i < queue.size(); i++) {
			queue.add(queue.remove());
		}
	}

	public int pop() {
		return queue.remove();
	}

	public int peek() {
		return queue.peek();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}

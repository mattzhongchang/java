package algs4.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import algs4.programming.StdIn;

public class Bag<Item> implements Iterable<Item>{

	private Node<Item> first;
	private int n;
	
	private static class Node<Item>
	{
		private Item item;
		private Node<Item> next;
		
	}
	
	public Bag()
	{
		this.first = null;
		this.n = 0;
	}
	
	public boolean isEmpty()
	{
		return this.first == null;
	}

	public int size()
	{
		return this.n;
	}
	
	public void add(Item item)
	{
		Node<Item> oldFirst = this.first;
		this.first = new Node<Item>();
		this.first.item = item;
		this.first.next = oldFirst;
		n++;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item>
	{
		private Node<Item> current;

		public ListIterator(Node<Item> first)
		{
			this.current = first;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	
	public static void main(String[] args)
	{
		Bag<String> bag = new Bag<String>();
		while (!StdIn.isEmpty())
		{
			String item = StdIn.readString();
			bag.add(item);
		}
		
		System.out.println("size of bag = " + bag.size());
		for (String s : bag)
		{
			System.out.println(s);
		}
	}
}

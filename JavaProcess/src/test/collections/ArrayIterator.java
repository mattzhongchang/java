package test.collections;

import java.util.Iterator;

/**
 * 解密Iterator接口，显示如何编写数组对象的简单接口
 * @author Administrator
 *
 */
public class ArrayIterator implements Iterator{
	
	protected Object[] data;
	
	protected int index = 0;
	
	public ArrayIterator(Object[] data)
	{
		setData(data);
	}

	public void setData(Object[] data) {
		this.data = data;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		return (index < data.length);
	}

	@Override
	public Object next() {
		if (hasNext())
		{
			return data[index++];
		}
		throw new IndexOutOfBoundsException("only " + data.length + " elements.");
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This demo does not implements the remove method.");

	}
}

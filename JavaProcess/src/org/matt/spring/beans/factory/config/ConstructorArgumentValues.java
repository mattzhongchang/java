package org.matt.spring.beans.factory.config;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConstructorArgumentValues {
	
	private Map<Integer, ValueHolder> indexedArgumentValues = new LinkedHashMap<Integer, ValueHolder>();
	
	private List<ValueHolder> genericArgumentValues = new LinkedList<ValueHolder>();
	

	public ConstructorArgumentValues() {
		super();
	}


	public static class ValueHolder {
		
		private Object value;
		
		private String type;
		
		private String name;
		
		private Object source;
		
		private boolean converted = false;
		
		private Object convertedValue;

		public ValueHolder(Object value) {
			super();
			this.value = value;
		}

		public ValueHolder(Object value, String type) {
			super();
			this.value = value;
			this.type = type;
		}

		public ValueHolder(Object value, String type, String name) {
			super();
			this.value = value;
			this.type = type;
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getSource() {
			return source;
		}

		public void setSource(Object source) {
			this.source = source;
		}

		public synchronized Object getConvertedValue() {
			return convertedValue;
		}

		public synchronized void setConvertedValue(Object convertedValue) {
			this.convertedValue = convertedValue;
		}

		public synchronized boolean isConverted() {
			return converted;
		}

		
		
	}
}

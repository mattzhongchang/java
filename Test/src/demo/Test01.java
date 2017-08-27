package demo;

import java.util.Vector;

public class Test01 {

	
	    static Vector<Integer> vector = new Vector<Integer>();
	    public static void main(String[] args) throws InterruptedException {
	        while(true) {
	            for(int i=0;i<10;i++)
	            {
	            	vector.add(i);
	            }
	            Thread thread1 = new Thread(){
	                public void run() {
	                    synchronized (Test01.class) {   //进行额外的同步
	                        for(int i=0;i<vector.size();i++)
	                        {
	                        	Integer in = vector.remove(i);
	                        	System.out.println(in + " " + vector.size());
	                        }
	                    }
	                };
	            };
	            Thread thread2 = new Thread(){
	                public void run() {
	                    synchronized (Test01.class) {
	                        for(int i=0;i<vector.size();i++)
	                        {
	                        	Integer inte = vector.get(i);
	                        	System.out.println("get " + inte + " " + vector.size());
	                        	
	                        }
	                    }
	                };
	            };
	            thread1.start();
	            thread2.start();
	            while(Thread.activeCount()>10)   {
	                 
	            }
	            break;
	        }
	    }
	
}

import java.util.*; 


public class OSThreading { 
	public static void main(String []args) { 
		Double average = 0.0; 
		Integer min = 0, max = 0;

		LinkedList<Integer> list = new LinkedList(); 
		Scanner input = new Scanner(System.in); 
		
		System.out.println("\n\n*******************************\n"+
								"Enter The list to exit enter 0"); 
		
		int x = input.nextInt(); 

		while (x != 0) { 
			list.add(x); 
			x = input.nextInt(); 
		}

		AverageThread avgThread = new AverageThread(list); 
		avgThread.start(); 

		MaxThread maxThread = new MaxThread(list); 
		maxThread.start(); 

		MinThread minThread = new MinThread(list); 
		minThread.start(); 

		if(!avgThread.isAlive())
			average = avgThread.getAvgValue();  

		if(!maxThread.isAlive())
			max = maxThread.getMaxValue();  

		if(!minThread.isAlive())
			min = minThread.getMinValue();  

		System.out.println("Average:\t" + average); 
		System.out.println("Max:\t\t" + max); 
		System.out.println("Min:\t\t" + min);
		System.out.println("*******************************\n\n");  
	}
}

class MaxThread implements Runnable { 
	private LinkedList<Integer> list; 
	private Thread t; 
	private Integer max; 

	public MaxThread(LinkedList<Integer> l) { 
		list = l; 
		max = 0;
	}

	public int getMaxValue() {return max;}

	public void run() { 
		max = list.get(0); 
		for(Integer x : list) {
			if(max < x) max = x;   
		}
	}

	public void start() { 
		if(t == null) { 
			try { 
			t = new Thread(this); 
			t.start();
			t.join();
			}catch(Exception e){}  
		}
	}

	public boolean isAlive() {return t.isAlive();}
}


class MinThread implements Runnable { 
	private LinkedList<Integer> list; 
	private Thread t; 
	private Integer min; 

	public MinThread(LinkedList<Integer> l) { 
		list = l; 
		min = 0;
	}

	public int getMinValue() {return min;}

	public void run() { 
		min = list.get(0); 
		for(Integer x : list) {
			if(min > x) min = x;   
		}
	}

	public void start() { 
		if(t == null) { 
			try { 
			t = new Thread(this); 
			t.start();
			t.join();
			}catch(Exception e){}  
		}
	}

	public boolean isAlive() {return t.isAlive();}
}

class AverageThread implements Runnable { 
	private LinkedList<Integer> list; 
	private Thread t; 
	private Double avg; 
	public AverageThread(LinkedList<Integer> l) { 
		list = l; 
		avg = 0.0;
	}

	public double getAvgValue() {return avg;}

	public void run() { 
		for(Integer x : list) { 
			avg += x; 
		}
		avg /= list.size();
	}

	public void start() { 
		if(t == null) { 
			try { 
			t = new Thread(this); 
			t.start();
			t.join();
			}catch(Exception e){}  
		}
	}

	public boolean isAlive() {return t.isAlive();}
}
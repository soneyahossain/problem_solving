import java.util.LinkedList;
import java.util.Queue;



class producer extends Thread {

	public void run() {
		Long i=(long) 0;
		while (true) {

	
			if( Main.product.size()>=11)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			else 
			{
				Main.product.add(i++);
				System.out.println("producer producing:"+i);
				notify();

			}

		}
		
	}

}

class consumer extends Thread {

	public void run() {
		while (true) {
			
			if( Main.product.size()==0)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			else
			{
				
				long i = Main.product.remove();
				System.out.println("consumer consuming:"+i);
				notify();
			}
			
			
			
			System.out.println("consumer running");
		}
	}
}

public class Main {
	
	public static Queue<Long> product = new LinkedList();

	public static void main(String args[]) {

		producer p = new producer();
		p.start();

		consumer c = new consumer();
		c.start();

	}

}

import java.util.Scanner;
public class Main {
	private int  function3n(int i , int j)
	{
		if (j<i){int temp =j;j=i;i=temp;	}
		int max_length =0;
		for(int m=i; m<=j; m++ )
		{
			int length =1;
			int number=m;
			while(number!=1)
			{
			  if(number%2==0) number=number/2;
			  else number= (number*3)+1; 
			  length++;
			}	
			if(length >max_length) max_length=length;	
		}
		return max_length;					
	}
	 public static void main(String[] args)
	 {
		 Main myObject = new Main();
		 Scanner reader = new Scanner(System.in);
		 while(reader.hasNextInt()){
			 int n1 =reader.nextInt(); 	 
			 int n2 =reader.nextInt(); 	 
			 String output=n1+" "+n2+" "+myObject.function3n(n1,n2); 
			 System.out.println(output);
		 }
		reader.close();   
	 }
}

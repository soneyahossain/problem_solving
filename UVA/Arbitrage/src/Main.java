import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	int noOfCountry;
	
	
	
	

	
	
	
	
	public static void main(String[] args) throws IOException {

		
		
		
		
		
	    System.out.println(returnString());
	    
	    
	    
	      
	     
		
		
		
		
		
		
		
		
		
		Main myObject = new Main();
		Scanner reader = new Scanner(System.in);
		
		while (reader.hasNextDouble()) {
			
			
			
			myObject.noOfCountry = (int)reader.nextDouble();
			double conversionrate[][]= new double[myObject.noOfCountry][myObject.noOfCountry];
			
			for (int i=0;i<myObject.noOfCountry;i++)
			{
				for( int j=0;j<myObject.noOfCountry;j++)
				{
					if(i==j) conversionrate[i][j]= 0.0;
					else conversionrate[i][j]=reader.nextDouble();
				}				
			}
			
			
			//myObject.printOutputArray(conversionrate);
			myObject.processArbitrage(conversionrate);
			//System.out.println();
		
		}
		
		reader.close();
	}

	
	void processArbitrage(double conversionrate[][]) {
		
		
		//initialize distance array
		//best[i][j][s] = 0, for all i,j,s
		//best[i][j][1] = input for the program
		//best[i][i][1] = 1, for all i
		//path[i][j][1] = i, for all i, j
		
		double distanceArry[][][] = new double[noOfCountry][noOfCountry][noOfCountry];
		int parent[][][] = new int[noOfCountry][noOfCountry][noOfCountry];
		
		for (int i = 0; i < noOfCountry; i++) {
			for (int j = 0; j < noOfCountry; j++) {
				for (int s = 0; s < noOfCountry; s++) {

					if (s == 0) {
						
						parent[i][j][s] = i;
						if (i == j)
							distanceArry[i][j][s] = 1;
						distanceArry[i][j][s] = conversionrate[i][j];  //weight of the edge 
						parent[i][j][s] =i;
						
					}					
					else 
					{
						distanceArry[i][j][s] =0;
						parent[i][j][s] = i;					
					}
				}
			}
		}
		
		
		
		//(distanceArry,parent);
		
		
		//start computing floyd warshal algorithm
		//At the end, find the smallest s (and any i) such that a[i][i][s] > 1.01
		
		for (int s = 1; s < noOfCountry; s++){	
				for (int k = 0; k < noOfCountry; k++){		     
        			for (int i = 0; i < noOfCountry; i++) {	
        				for (int j = 0; j < noOfCountry; j++) {	
        					 double temp = distanceArry[i][k][s-1]*distanceArry[k][j][0];
        		               if(temp > distanceArry[i][j][s]){
        		            	   distanceArry[i][j][s] = temp;
        		            	   parent[i][j][s] = k; //since par[k][j][0] = k
        		               } 
        				}
        			}
				}
		}	
		printOutputArray(distanceArry,parent);	
	}
	
	
	
	void printOutputArray(double conversionrate[][][],int parent[][][]) {
		
	
		
		int smallS= Integer.MAX_VALUE;
		
		int ij=-1;
		for (int i = 0; i < noOfCountry; i++) {
			
			for (int j = 0; j < noOfCountry; j++) {
				
				for (int s = 0; s < noOfCountry; s++){	
					
					if( i==j){
						
					    
					    if(conversionrate[i][j][s]>1.01 )	
					    {
					    	
					    	if (s< smallS) 
					    	{
					    		smallS=s;
					    		ij=i;
					    	
		
					    	}
					    }      
					      
					}
			}
			
		}
		}
		
		
		//now print the arbitrage
		//System.out.println();
		if(ij==-1 )
		{
			System.out.print("no arbitrage sequence exists");
			//System.out.println();
		}
	
		else
		{
			
		ArrayList<String> output = new ArrayList<String>();
		output.add(String.valueOf(ij));
	   
		int start= ij;
		int i=0;
		while(true)
	    {
			if(i==smallS) break;
		
			output.add(String.valueOf(parent[ij][ij][smallS])); 
		    ij= parent[ij][ij][smallS];
		    i++;
		}	
		output.add(String.valueOf(start));
		for(int size = output.size()-1; size>=0; size-- )
		{
			System.out.print(Integer.valueOf(output.get(size))+1);
			if(size!=0) System.out.print(" ");
		}
		
		}
		
		System.out.println();
	}
}

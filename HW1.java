//Mauricio Vinagre
//CSE 417 Winter 2017
//Implementation of Stable Matching Algorithm

import java.util.*;

public class HW1 {
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int number = console.nextInt();
		console.nextLine();
		
		Queue<Integer> freeMenQueue = new LinkedList<Integer>();
		int[] wife = new int[number];
		int[] husband = new int[number];
		
		int[] count = new int[number]; // amount of proposals made by man count[m]
		
		//"for each man, mantain a list of women, ordered by preference"
		int[][] boysPreferences = new int[number][number];
		for (int i = 0; i < number; i++) {// "i" represents the man, however
			   							  // it is starting at ZERO here, not ONE. 
										  // Consider.
			freeMenQueue.add(i); 
			String line = console.nextLine();
			Scanner lineScanner = new Scanner(line);
			for (int j = 0; j < number; j++) {
				boysPreferences[i][j] = lineScanner.nextInt();
			}
		}
		
		//creation of list of women preferences, as well as 
		//inverse list
		int[][] inverse = new int[number][number];
		int[][] womensPreferences = new int[number][number];
		for (int i = 0; i < number; i++) {
			String line = console.nextLine();
			Scanner lineScanner = new Scanner(line);
			for (int j = 0; j < number; j++) {
				int nextNumber = lineScanner.nextInt();
				womensPreferences[i][j] = nextNumber;
				inverse[i][nextNumber-1] = j; 
			}
		}
		
		while(!freeMenQueue.isEmpty()) {
			int currentMan = freeMenQueue.remove();
			int next = count[currentMan];
			int nextGirl = boysPreferences[currentMan][next];
			
			if (husband[nextGirl-1] == 0) { //girl he is about to propose hasn't
											//been matched yet
				count[currentMan]++;
				husband[nextGirl-1] = currentMan + 1;
				wife[currentMan] = nextGirl;
			} else { //woman he is about to propose is matched to someone else
				
				int womanCurrentPartner = husband[nextGirl - 1];
				
				//next check: does woman like her current partner better?
				if (inverse[nextGirl-1][womanCurrentPartner - 1] <
						inverse[nextGirl-1][currentMan]) {
					count[currentMan]++;
					freeMenQueue.add(currentMan);
					
				} else { //woman likes the new guy better
					int currentHusbandToBeDumped = husband[nextGirl-1];
					wife[currentHusbandToBeDumped-1] = 0;
					freeMenQueue.add(currentHusbandToBeDumped-1);
					//now update with new husband
					husband[nextGirl-1] = currentMan+1;
					wife[currentMan] = nextGirl;
					count[currentMan]++;
				}
				
			}
			
		}
		
		for(int i = 0; i < number; i++) {
			System.out.print(wife[i]+ " ");
		}
		
		//debug stuff-----
		
		/*System.out.println();
		System.out.println();
		for (int i = 0; i < number;i++) {
			for (int j = 0; j < number; j++) {
				System.out.print(womensPreferences[i][j] + " ");
			}
			System.out.println();
		}*/
		
		//debug stuff------
		
	}

}

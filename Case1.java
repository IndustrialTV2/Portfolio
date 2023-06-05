import java.util.Scanner;

public class Case1 {

	public static void main(String[] args) {
		
		int input;
		int[] setArray;
		int[] totalsArray = new int[10];
		int setIndex = 0;
		int totalsIndex = 0;
		int totalNumber;
		Scanner scanner = new Scanner(System.in);
		
		
		//Reads the values of each sets and saves them in an array
		while(scanner.hasNext()) {
			
			//Stops input when -1 is input
			input = scanner.nextInt();
			if(input == -1) {
				break;
			}
			
			//Sets the array of Sets to a size suitable to the number of sets
			setArray = new int[input * 2];
			
			//Fills array of inputed values
			while(setIndex < setArray.length) {
				input = scanner.nextInt();
				
				setArray[setIndex] = input;
				setIndex++;
			}
			
			setIndex = 0;
			
			//Calculates the first set to avoid special cases
			totalNumber = setArray[1] * setArray[0];
			
			//Calculates remaining sets via loop
			for(int i = 3; i < setArray.length; i += 2) {
				totalNumber += ( (setArray[i] - setArray[i - 2] ) * setArray[i - 1] );
			}
			
			//The total miles are saved to a second array
			totalsArray[totalsIndex] = totalNumber;
			totalsIndex++;
		}
		
		scanner.close();
		
		//Prints all totals stored in the array of totals
		for(int i = 0; i < totalsArray.length; i++) {
			if(totalsArray[i] != 0) {
				System.out.println(totalsArray[i] + " miles");
			}
		}
		
		
	}
}

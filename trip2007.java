package trip2007;
import java.util.Scanner;

public class trip2007 {
	
	private class cases{
		int size;
		int amount;
		cases next;
		cases previous;

		public cases(int size) {
			this.size = size;
			amount = 1;
		}
	}

	cases head;
	cases end;
	cases maxAmount;
	cases secondMaxAmount;
	
	public static void main(String[] args) {
	}

	/* String findMInimalCases(String)
	 * Input: must have number of cases followed by the sizes of each case and ending with a 0
	 * 			ex. 	3
	 * 					2 1 1
	 * 					0
	 * 
	 * 
	 * Output: starts with the number of pairs followed by a listing of each pair
	 * Constraints: case size must be between 1 and 1000000 inclusive
	 * 				amount of cases must be between 0 and 10000 inclusive
	 * 
	 */
	public String findMinimalCases(String input) {
		int numCases;
		Scanner scanner = new Scanner(input);
		
		if(scanner.hasNextInt() == false)
			return "Invalid format";
		numCases = scanner.nextInt();
		if(numCases < 1 || numCases > 10000)
			return "Invalid number of cases";
		
		for(int i = 0; i < numCases; i++) {
			int caseSize;
			if(scanner.hasNextInt() == false)
				return "Not enough cases";
			caseSize = scanner.nextInt();
			if(caseSize < 1 || caseSize > 100000)
				return "Invalid case size";
			if(i == 0) {
				head = new cases(caseSize);
				end = head;
			}
			else { 
				cases current = head;
				while(true) {
					if(current.size == caseSize) {
						current.amount++;
						break;
					}
					if(current.size < caseSize) {
						cases newCase = new cases(caseSize);
						newCase.previous = current.previous;
						newCase.next = current;
						current.previous = newCase;
						head = newCase;
						break;
					}
					if(current.next == null) {
						current.next = new cases(caseSize);
						end = current.next;
						break;
					}
				}//end of while loop
			}
		}
		
		if(scanner.hasNextInt() == false)
			return "Invalid Format";
		if(scanner.nextInt() != 0)
			return "Invalid Format";
		
		int numberOfPieces = 0;
		String casePairing = "";
		
		while(head != null) {
         	findMaxAmount();	
			if(maxAmount == null && secondMaxAmount == null) {
				return numberOfPieces + casePairing;
			}
			if(secondMaxAmount == null) {
				numberOfPieces += maxAmount.amount;
				for(int i = 0; i < maxAmount.amount;i++)
					casePairing +="\n"+maxAmount.size;
				return numberOfPieces + casePairing;
			}
			
			while(maxAmount.amount > 0 && secondMaxAmount.amount > 0) {
				numberOfPieces++;
				casePairing += "\n" + maxAmount.size + " " + secondMaxAmount.size;
				maxAmount.amount--;
				secondMaxAmount.amount--;
				
			}

			if(head != null && maxAmount.amount == 0)
				trimCase(maxAmount);
			if(head != null && secondMaxAmount.amount == 0)
				trimCase(secondMaxAmount);
		}
		return numberOfPieces+casePairing;
	}//end of findMinimal Cases
	
	/*findMaxAmount looks through the linked list and fills in the maxAmount and secondMaxAmount 
	 * 				with references to the node that has the highest or second highest amount
	 * 
	 * Output: None, modifies maxAmount and secondMaxAmount;
	 * 		   maxAmount will never be the head of the linked list except when the head is the only node in the list
	 * 		   secondMaxAmount can be the head of the linked list and will be null if the linked list only has 1 node
	 */
	public void findMaxAmount() {
		maxAmount = head;
		secondMaxAmount = null;
		if(head.next == null)
			return;
		cases current = head.next.next;
		maxAmount = head.next;
		secondMaxAmount = head;
		while(current != null) {
			if(current.amount > maxAmount.amount) {
				maxAmount = current;
				current = current.next;
				continue;
			}
			
			if(current.amount > secondMaxAmount.amount) 
				secondMaxAmount = current;
			
			current = current.next;
		}
	}
	
	/*trimCase(cases)
	* Input:reference to node in linked list
	* Output: None, removes node from list 
	*/
	public void trimCase(cases trimmedCase) {
		if(trimmedCase.previous == null) {
			head = trimmedCase.next;
			if(head == null)
				return;
			if(head.previous != null)
				head.previous = null;
		}
		else {
			trimmedCase.previous.next = trimmedCase.next;
			if(trimmedCase.next != null)
				trimmedCase.next.previous = trimmedCase.previous;
		}
	
	}
}

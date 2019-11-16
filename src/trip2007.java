package trip2007;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class trip2007 {
	
	private class Bags{
		int size;
		int amount;
		Bags next;
		Bags previous;

		public Bags(int size) {
			this.size = size; 
			amount = 1;
		}
	}

	Bags head;
	Bags end;
	Bags maxAmount;
	Bags secondMaxAmount;
	static File inputFile;
	
	public static void main(String[] args) {
		String output = "";
		String fileName = "src/input.txt";
		if(args.length > 0)
			fileName = args[0];
		inputFile = new File(fileName);
		trip2007 trp = new trip2007();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			trp.writeResult("File not found");
		}
		
		int ticker = 0;
		String input = "";
		while(scanner.hasNextLine()) {
			if(scanner.hasNext() == false)
				break;
			if(ticker == -1) {
				scanner.nextLine();
				ticker++;
				continue;
			}
			input += scanner.nextLine() + "\n";
			ticker ++;
			if(ticker >= 3) {
				output += trp.findMinimalBags(input) + "\n\n";
				ticker = -1; 
				input = "";
			}
		}
		if(!(ticker == 0 || ticker == -1)) {
			output += "Invalid format\n";
		}
		trp.writeResult(output);
	}

	/* String findMInimalBags(String)
	 * Input: must have number of bags followed by the sizes of each bag and ending with a 0
	 * 			ex. 	3
	 * 					2 1 1
	 * 					0
	 * 
	 * 
	 * Output: starts with the number of pairs followed by a listing of each pair
	 * Constraints: bag size must be between 1 and 1000000 inclusive
	 * 				amount of bags must be between 0 and 10000 inclusive
	 * 
	 */
	public String findMinimalBags(String input) {
		int numBags;
		Scanner scannerNewLine = new Scanner(input);
		
		//1st input line
		Scanner scanner = new Scanner(scannerNewLine.nextLine());
		if(scanner.hasNextInt() == false)
			return "Invalid format";
		numBags = scanner.nextInt();
		if(numBags < 1 || numBags > 10000)
			return "Invalid number of bags";
		if(scanner.hasNext()) 
			return "Invalid format";
		
		//2nd input Line;
		scanner = new Scanner(scannerNewLine.nextLine());
		for(int i = 0; i < numBags; i++) {
			int bagSize;
			if(scanner.hasNextInt() == false)
				return "Not enough bags";
			bagSize = scanner.nextInt();
			if(bagSize < 1 || bagSize > 1000000)
				return "Invalid bag size";
			if(i == 0) {
				head = new Bags(bagSize);
				end = head;
			}
			else { 
				Bags current = head;
				while(true) {
					if(current.size == bagSize) {
						current.amount++;
						break;
					}
					if(current.size < bagSize) {
						Bags newBag = new Bags(bagSize);
						newBag.previous = current.previous;
						newBag.next = current;
						if(current == head) {
							head = newBag;
							current.previous = head;
							break;
						}
						current.previous.next = newBag;
						current.previous = newBag;
						break;
					}
					if(current.next == null) {
						current.next = new Bags(bagSize);
						end = current.next;
						end.previous = current;
						break;
					}
					current = current.next;
				}//end of while loop
			}
		}
		if(scanner.hasNext())
			return "Invalid Format";
		
		//3rd input line
		scanner = new Scanner(scannerNewLine.nextLine());
		if(scanner.hasNextInt() == false)
			return "Invalid Format";
		if(scanner.nextInt() != 0)
			return "Invalid Format";
		if(scanner.hasNextInt()) 
			return "Invalid Format";
		
		int numberOfPieces = 0;
		String bagPairing = "";
		
		while(head != null) {
         	findMaxAmount();	
			if(maxAmount == null && secondMaxAmount == null) {
				return numberOfPieces + bagPairing;
			}
			if(secondMaxAmount == null) {
				numberOfPieces += maxAmount.amount;
				for(int i = 0; i < maxAmount.amount;i++)
					bagPairing +="\n"+maxAmount.size;
				return numberOfPieces + bagPairing;
			}
			
			if(maxAmount.amount > 0 && secondMaxAmount.amount > 0) {
				numberOfPieces++;
				bagPairing += "\n" + maxAmount.size + " " + secondMaxAmount.size;
				maxAmount.amount--;
				secondMaxAmount.amount--;
				
			}

			if(head != null && maxAmount.amount == 0)
				trimBags(maxAmount);
			if(head != null && secondMaxAmount.amount == 0)
				trimBags(secondMaxAmount);
		}
		return numberOfPieces+bagPairing;
	}//end of findMinimalBags
	
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
		Bags current = head.next.next;
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
	
	/*trimBags(Bag)
	* Input:reference to node in linked list
	* Output: None, removes node from list 
	*/
	private void trimBags(Bags trimmedBag) {
		if(trimmedBag.previous == null) {
			head = trimmedBag.next;
			if(head == null)
				return;
			if(head.previous != null)
				head.previous = null;
		}
		else {
			trimmedBag.previous.next = trimmedBag.next;
			if(trimmedBag.next != null)
				trimmedBag.next.previous = trimmedBag.previous;
		}
	
	}
	
	private void writeResult(String output) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/trip2007/output.txt")));
			writer.write(output);
			writer.close();
		}
		catch(IOException e){
			System.out.println("Write to output file failed.");
		}
		
	}
}

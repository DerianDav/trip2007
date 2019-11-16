package trip2007;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;

public class Tests {

	trip2007 t207 = new trip2007();
	
	@Test
	public void testMinimalBags_WrongFormatting() {
		assertEquals(t207.findMinimalBags("\n\n"), "Invalid format");
		assertEquals(t207.findMinimalBags("gfdggadsxcawerta\n\n"), "Invalid format");
	}
	
	@Test
	public void testMinimalBags_Edges() {
		assertEquals(t207.findMinimalBags("-1\n\n"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("0\n\n"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("-9999999"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("10001 \n 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 4 4 4 4 4 4 4  4 \n 0"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("3434321"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("-1 \n0 \n0"),"Invalid number of bags");
		assertEquals(t207.findMinimalBags("1 \n0 \n0"),"Invalid bag size");
		assertEquals(t207.findMinimalBags("1 \n-1 \n0"),"Invalid bag size");
		assertEquals(t207.findMinimalBags("1 \n1000001 \n0"),"Invalid bag size");
	}
	
	@Test
	public void testMinimalBags_SimpleInput1() {
		assertEquals(t207.findMinimalBags("1\n2\n0"),"1\n2");
	}
	
	@Test
	public void testMinimalBags_SimpleInput2() {
		assertEquals(t207.findMinimalBags("1\n2\n3"),"Invalid Format");
	}

	@Test
	public void testMinimalBags_SimpleInput3() {
		assertEquals(t207.findMinimalBags("1\n2\n1"),"Invalid Format");
	}
	@Test
	public void testMinimalBags_SimpleInput4() {
		assertEquals(t207.findMinimalBags("1\n2\n-1"),"Invalid Format");
	}
	
	@Test
	public void testMinimalBags_SimpleInput5() {
		assertEquals(t207.findMinimalBags("2\n1\n0"),"Not enough bags");
	}
	 
	@Test  
	public void testMinimalBags_Input() {
		Scanner scanner = new Scanner(t207.findMinimalBags("6\n1 1 2 2 2 3\n0"));
		assertTrue(scanner.nextInt() == 3);
	}
	
	@Test  
	public void testMinimalBags_Input2() {
		Scanner scanner = new Scanner(t207.findMinimalBags(""
				+ "30\n" + 
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 999999\n" + 
				"0" + 
				""));
		assertTrue(scanner.nextInt() == 15);
	}
	
	@Test  
	public void testMinimalBags_Input3() {
		Scanner scanner = new Scanner(t207.findMinimalBags(""
				+ "30\n" + 
				"1 2 2 4 5 6 7 7 9 10 11 11 13 14 15 16 16 18 20 20 21 22 22 22 22 22 27 28 29 30\n" + 
				"0" + 
				""));
		assertTrue(scanner.nextInt() == 15);
	}
	
	@Test  
	public void testMinimalBags_Input4() {
                     		String output = t207.findMinimalBags("30\n" + 
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 \n" + 
				"0" + 
				"");
		Scanner scanner = new Scanner(output);
		assertTrue(scanner.nextInt() == 16);
	}
	

	@Test  
	public void testMinimalBags_Input5() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += "1 ";
		input+= "\n ";
		assertEquals(t207.findMinimalBags(input),"Invalid Format");
	}
	
	@Test  
	public void testMinimalBags_Input6() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += "1 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 10000);
	}
	
	@Test  
	public void testMinimalBags_Input7() {
		String input = "10000\n";
		for(int i = 0; i < 5000; i++)
			input += "1 ";
		for(int i = 0; i < 5000; i++)
			input += "2 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 5000);
	}
	
	@Test  
	public void testMinimalBags_Input8() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += i+1 + " ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 5000);
	}
	 

	@Test  
	public void testMinimalBags_Input9() {
		String input = "10000\n";
		for(int i = 0; i < 9999; i++)
			input += i+1 + " ";
		input += "\n0";
		assertEquals(t207.findMinimalBags(input),"Not enough bags");
	}

	@Test  
	public void testMinimalBags_Input10() {
		String input = "9\n";
		for(int i = 0; i < 3; i++)
			input += "21 ";
		for(int i = 0; i < 3; i++)
			input += "15 ";
		for(int i = 0; i < 3; i++)
			input += "72 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 5); // last Bag size is 0 since its missing 1 Bag
	}
	
	@Test  
	public void testMinimalBags_Input11() {
		String input = "9000\n";
		for(int i = 0; i < 3000; i++)
			input += "21 ";
		for(int i = 0; i < 3000; i++)
			input += "15 ";
		for(int i = 0; i < 3000; i++)
			input += "72 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 4500); // last Bag size is 0 since its missing 1 Bag
	}
	
	@Test  
	public void testMinimalBags_NonOrderedInput() {
		String input = "33\n" + 
				"41 2 3 4 15 6 7 8 9 10 11 12 13 14 145 16 17 18 19 20 2761 22 23 24 25 26 27 28 2459 30 31 32 33\n" + 
				"0\n";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 17);
	}
	
	@Test  
	public void testMinimalBags_NonOrderedInput2() {
		String input = "33\n" + 
				"41 2545 3 41 15 6 7 8 9 10 41 12 13 14 145 16 17 23215 19 200000 2761 22 23 24 25 26 2722 28 2459 30 31 32 33\n" + 
				"0\n";
		Scanner scanner = new Scanner(t207.findMinimalBags(input));
		assertTrue(scanner.nextInt() == 17);
	}
}
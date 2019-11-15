package trip2007;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;

public class Tests {

	trip2007 t207 = new trip2007();
	
	@Test
	public void testMinimalCases_WrongFormatting() {
		assertEquals(t207.findMinimalCases(""), "Invalid format");
		assertEquals(t207.findMinimalCases("gfdggadsxcawerta"), "Invalid format");
	}
	
	@Test
	public void testMinimalCases_Edges() {
		assertEquals(t207.findMinimalCases("-1"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("0"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("-9999999"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("10001 \n 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 4 4 4 4 4 4 4  4 \n 0"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("3434321"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("-1 \n0 \n0"),"Invalid number of cases");
		assertEquals(t207.findMinimalCases("1 \n0 \n0"),"Invalid case size");
		assertEquals(t207.findMinimalCases("1 \n-1 \n0"),"Invalid case size");
		assertEquals(t207.findMinimalCases("1 \n1000001 \n0"),"Invalid case size");
	}
	
	@Test
	public void testMinimalCases_SimpleInput1() {
		assertEquals(t207.findMinimalCases("1\n2\n0"),"1\n2");
	}
	
	@Test
	public void testMinimalCases_SimpleInput2() {
		assertEquals(t207.findMinimalCases("1\n2"),"Invalid Format");
	}

	@Test
	public void testMinimalCases_SimpleInput3() {
		assertEquals(t207.findMinimalCases("1\n2\n1"),"Invalid Format");
	}
	@Test
	public void testMinimalCases_SimpleInput4() {
		assertEquals(t207.findMinimalCases("1\n2\n-1"),"Invalid Format");
	}
	 
	@Test  
	public void testMinimalCases_Input() {
		Scanner scanner = new Scanner(t207.findMinimalCases("6\n1 1 2 2 2 3\n0"));
		assertTrue(scanner.nextInt() == 3);
	}
	
	@Test  
	public void testMinimalCases_Input2() {
		Scanner scanner = new Scanner(t207.findMinimalCases(""
				+ "30\n" + 
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30\n" + 
				"0" + 
				""));
		assertTrue(scanner.nextInt() == 15);
	}
	
	@Test  
	public void testMinimalCases_Input3() {
		Scanner scanner = new Scanner(t207.findMinimalCases(""
				+ "30\n" + 
				"1 2 2 4 5 6 7 7 9 10 11 11 13 14 15 16 16 18 20 20 21 22 22 22 22 22 27 28 29 30\n" + 
				"0" + 
				""));
		assertTrue(scanner.nextInt() == 15);
	}
	
	@Test  
	public void testMinimalCases_Input4() {
                     		String output = t207.findMinimalCases("30\n" + 
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 \n" + 
				"0" + 
				"");
		Scanner scanner = new Scanner(output);
		assertTrue(scanner.nextInt() == 16);
	}
	

	@Test  
	public void testMinimalCases_Input5() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += "1 ";
		assertEquals(t207.findMinimalCases(input),"Invalid Format");
	}
	
	@Test  
	public void testMinimalCases_Input6() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += "1 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalCases(input));
		assertTrue(scanner.nextInt() == 10000);
	}
	
	@Test  
	public void testMinimalCases_Input7() {
		String input = "10000\n";
		for(int i = 0; i < 5000; i++)
			input += "1 ";
		for(int i = 0; i < 5000; i++)
			input += "2 ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalCases(input));
		assertTrue(scanner.nextInt() == 5000);
	}
	
	@Test  
	public void testMinimalCases_Input8() {
		String input = "10000\n";
		for(int i = 0; i < 10000; i++)
			input += i+1 + " ";
		input += "\n0";
		Scanner scanner = new Scanner(t207.findMinimalCases(input));
		assertTrue(scanner.nextInt() == 5000);
	}
	

	@Test  
	public void testMinimalCases_Input9() {
		String input = "10000\n";
		for(int i = 0; i < 9999; i++)
			input += i+1 + " ";
		input += "\n0";
		assertEquals(t207.findMinimalCases(input),"Invalid case size");
	}
	
}

import java.util.*;
import java.io.*;

public class RandomNumberTester {

	static int ones = 0;
	static int twos = 0;
	static int threes = 0;
	static int fours = 0;
	static int fives = 0;

	public static void main(String[] args) throws IOException{
		Scanner scanner = new Scanner(new File("numbers.txt"));

		while(scanner.hasNextInt()){
			switch(scanner.nextInt()){
				case(1) : ones++;
						  break;
				case(2) : twos++;
						  break;
				case(3) : threes++;
						  break;
				case(4) : fours++;
						  break;
				case(5) : fives++;
						  break;
			}
		}

		getFrequency();
		printSequence();
		checkchisquare();
		System.out.println("\n");
		getRun();
		System.out.println("\n");
		getSubBlockFrequency();
	}

	public static void getFrequency(){
		System.out.println("frequency of ones:   " + ones + " / 50   Expected: 10");
		System.out.println("frequency of twos:   " + twos + " / 50   Expected: 10");
		System.out.println("frequency of threes: " + threes + " / 50   Expected: 10");
		System.out.println("frequency of fours:  " + fours + " / 50   Expected: 10");
		System.out.println("frequency of fives:  " + fives + " / 50   Expected: 10");
	}

	public static void getRun() throws IOException{
		Scanner scanner = new Scanner(new File("numbers.txt"));
		int run = scanner.nextInt();
		int longestrun = 0;
		int temp = 1;
		int run2;
		while(scanner.hasNextInt()){
			run2 = scanner.nextInt();
			if (run2 == run){
				temp++;
			} 
			else {
				if(temp > longestrun){
					longestrun = temp;
				}
				run = run2;
				temp = 1;
			}

		}
		if (temp > longestrun) longestrun = temp; 

		System.out.println("The longest run is:" + longestrun);
	}

	public static void getSubBlockFrequency() throws IOException{
		Scanner scanner = new Scanner(new File("numbers.txt"));

		for (int i = 1; i <= 5; i ++){
			ones = 0;
			twos = 0;
			threes = 0;
			fours = 0;
			fives = 0;

			for(int j = 0; j < 10; j++){
				switch(scanner.nextInt()){
				case(1) : ones++;
						  break;
				case(2) : twos++;
						  break;
				case(3) : threes++;
						  break;
				case(4) : fours++;
						  break;
				case(5) : fives++;
						  break;
				}
				
			}
			System.out.println("frequency of ones in sublock["+i+"]:   " + ones + " / 10   Expected: 2");
				System.out.println("frequency of twos in sublock["+i+"]:   " + twos + " / 10   Expected: 2");
				System.out.println("frequency of threes in sublock["+i+"]: " + threes + " / 10   Expected: 2");
				System.out.println("frequency of fours in sublock["+i+"]:  " + fours + " / 10   Expected: 2");
				System.out.println("frequency of fives in sublock["+i+"]:  " + fives + " / 10   Expected: 2");
				System.out.println("\n");
		}
	}

	public static void checkchisquare(){
		double v;
		v = (Math.pow((ones-10),2)/10) + (Math.pow((twos-10),2)/10) + (Math.pow((threes-10),2)/10) + (Math.pow((fours-10),2)/10) + (Math.pow((fives-10),2)/10);
		System.out.println("" + v);
	}

	public static void printSequence()throws IOException{
		Scanner scanner = new Scanner(new File("numbers.txt"));

		while(scanner.hasNextInt()){
			System.out.print(scanner.nextInt() + " ");
		}
		System.out.println("");
	}
}
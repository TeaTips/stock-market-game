/*
 * Stock Market Game v0.2
 * 
 * Protected under a Creative Commons Attribution 3.0 Unported License,
 * Tom Penney 2012
 * 
 */

package stock.b2kan.com;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	public static Random rand		= new Random();
	public static Scanner inputScan	= new Scanner(System.in);
	public static int input, day;
	public static double price1, price2, price3, price4;
	public static double version	= 0.2;
	public static double money		= 100.0;
	public static int[] inventory1	= {0, 0, 0, 0};
	public static double[] inventory2	= {0.0, 0.0, 0.0, 0.0};
	
	public static void main(String[] args) {
		init();
		menu();
	}
	
	public static void menu() {
		System.out.println("\n\nStock Market Game v"+version+"0.1\n" +
							"You have $"+money+" in the bank.\n\n" +
							"1. Stock Prices.\n" +
							"2. Buy Stocks.\n" +
							"3. Sell Stocks.\n" +
							"4. My Shares.\n" +
							"5. Next Day.\n" +
							"6. Exit.");
		if(inputScan.hasNextInt() == true) {
			input	= inputScan.nextInt();
		} else {
			inputScan.next();
		}
		
		switch(input) {
			case 1: stockPrices(); menu();
			case 2: buyStockMenu(); menu();
			case 3: sellStock(); menu();
			case 4: myShares(); menu();
			case 5: nextDay(); stockPrices(); menu();
			case 6: System.exit(0);
			case 7: next100(); menu();
			default: System.out.println("Please enter a valid choice."); sleep(500); menu();
		}
	}
	
	private static void init() {
		// Make all the prices a random number so we have something to from.
		price1	= rand.nextInt(10);
		price2	= rand.nextInt(10);
		price3	= rand.nextInt(10);
		price4	= rand.nextInt(10);
		day		= 1;
	}

	private static void stockPrices() {
		System.out.println("\nToday's Share Prices" +
						"\nDerp Inc	$"+price1+
						"\nHerp Industries	$"+price2+
						"\nFoo Co		$"+price3+
						"\nBar Pty Ltd	$"+price4);
	}

	private static void buyStockMenu() {
		int amount	= 0;
		double currentPrice = 0;
		System.out.println("\n\nWhich company would you like to buy shares in?\n" +
							"1. Derp Inc.\n" +
							"2. Herp Industries.\n" +
							"3. Foo Co.\n" +
							"4. Bar Pty Ltd.\n" +
							"5. Back.");
		input	= inputScan.nextInt();
		System.out.println("How many?");
		amount	= inputScan.nextInt();
		
		if(input == 5) {
			menu();
		}
		
		if(input > 0 && input < 5) {
			if(enoughFunds(input, amount) == true) {
				switch(input) {
					case 1: currentPrice = price1;
					case 2: currentPrice = price2;
					case 3: currentPrice = price3;
					case 4: currentPrice = price4;
				}
				inventory1[input-1]	= inventory1[input-1] + amount;
				inventory2[input-1]	= currentPrice;
				System.out.println("Stocks purchased!");
			} else {
				System.out.println("You don't have enough capital to buy these shares.");
			}
			
		}
	}
	
	public static boolean enoughFunds(int companyID, int quantity) {
		if(companyID == 1) {
			if(money >= (price1 * quantity)) {
				return true;
			} else {
				return false;
			}
		}
		
		if(companyID == 2) {
			if(money >= (price2 * quantity)) {
				return true;
			} else {
				return false;
			}
		}
		
		if(companyID == 3) {
			if(money >= (price3 * quantity)) {
				return true;
			} else {
				return false;
			}
		}
		
		if(companyID == 4) {
			if(money >= (price4 * quantity)) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}

	private static void sellStock() {
		int amount	= 0;
		double oldPrice	= 0.0;
		double currentPrice = 0.0;
		System.out.println("\n\nWhich company would you like to sell shares from?\n" +
							"1. Derp Inc.		("+inventory1[0]+")\n" +
							"2. Herp Industries.	("+inventory1[1]+")\n" +
							"3. Foo Co.		("+inventory1[2]+")\n" +
							"4. Bar Pty Ltd.		("+inventory1[3]+")" +
							"5. Back.");
		input	= inputScan.nextInt();
		System.out.println("How many?");
		amount	= inputScan.nextInt();
		
		if(input == 5) {
			menu();
		}
		
		if(amount > inventory1[input-1]) {
			System.out.println("You only have "+inventory1[input-1]+" shares in that company.");
			sleep(500);
			sellStock();
		} else {
			oldPrice	= inventory2[input-1];
			switch(input) {
				case 1: currentPrice	= price1;
				case 2: currentPrice	= price2;
				case 3: currentPrice	= price3;
				case 4: currentPrice	= price4;
			}
			
			inventory1[input-1]	= inventory1[input-1] - amount;
			money	= money + (amount * currentPrice);
			
			System.out.println("Shares sold! Profit made: $"+((currentPrice - oldPrice)*amount));
		}
	}
	
	public static void myShares() {
		System.out.println("Derp Inc	"+inventory1[0]+" shares at $"+inventory2[0]+" each.\n" +
							"Herp Industries	"+inventory1[1]+" shares $"+inventory2[1]+" each.\n" +
							"Foo Co		"+inventory1[2]+" shares $"+inventory2[2]+" each.\n" +
							"Bar Pty Ltd	"+ inventory1[3]+" shares $"+inventory2[3]+" each.\n");
	}

	private static void nextDay() {
		price1	= fluctuate(price1);
		price2	= fluctuate(price2);
		price3	= fluctuate(price3);
		price4	= fluctuate(price4);
	}

	public static double fluctuate(double fluctuated) {
		int chance	= rand.nextInt(3);
		double random	= rand.nextDouble();
		// 0 = Stable(ish), 1 = Positive shift, 2 = Negative shift.
		switch(chance) {
			case 0: fluctuated	= fluctuated + (random * 3) - 3;
			case 1: fluctuated	= fluctuated + (random * 10);
			case 2: fluctuated	= fluctuated - (random * 5);
		}
		
		// If things turned out really bad for the company and their share price is bad, make sure the price can't go below $0
		if(fluctuated < 0.0) {
			fluctuated	= 0.0;
		}
		
		return (Math.round(fluctuated * 100.0) / 100.0);
	}
	
	public static void next100() {
		int run	= 0;
		while(run <= 100) {
			price1	= fluctuate(price1);
			System.out.println(price1);
			run++;
		}
		run	= 0;
		System.out.println("\n\n\n");
		while(run <= 100) {
			price2	= fluctuate(price2);
			System.out.println(price2);
			run++;
		}
		run	= 0;
		System.out.println("\n\n\n");
		while(run <= 100) {
			price3	= fluctuate(price3);
			System.out.println(price3);
			run++;
		}
		run	= 0;
		System.out.println("\n\n\n");
		while(run <= 100) {
			price4	= fluctuate(price4);
			System.out.println(price4);
			run++;
		}
		System.out.println(Math.round(rand.nextDouble()*100.0)/100.0);
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
 * Stock Market Game v0.1
 * 
 * Protected under a Creative Commons Attribution 3.0 Unported License,
 * Tom Penney 2012
 * 
 */

package stock.b2kan.com;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static Random rand		= new Random();
	public static Scanner inputScan	= new Scanner(System.in);
	public static int input			= 0;
	public static int day			= 0;
	public static int price1, price2, price3, price4;
	public static double version	= 0.1;
	
	public static void main(String[] args) {
		init();
		menu();
	}
	
	public static void menu() {
		System.out.println("\n\nStock Market Game v"+version+"0.1\n\n" +
							"1. Stock Prices.\n" +
							"2. Buy Stocks.\n" +
							"3. Sell Stocks.\n" +
							"4. Next Day.\n" +
							"5. Exit.");
		input	= inputScan.nextInt();
		
		switch(input) {
			case 1: stockPrices(); menu();
			case 2: buyStock(); menu();
			case 3: sellStock(); menu();
			case 4: nextDay(); menu();
			case 5: System.exit(0);
			default: System.out.println("Please enter a valid choice."); menu();
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
		System.out.println("Derp Inc	$"+price1+
						"\nHerp Industries	$"+price2+
						"\nFoo Co		$"+price3+
						"\nBar Pty Ltd	$"+price4);
		// Sleep for a second so the menu doesn't show up too quickly
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void buyStock() {
		
	}

	private static void sellStock() {
		
	}

	private static void nextDay() {
		price1	= fluctuate(price1);
		price2	= fluctuate(price2);
		price3	= fluctuate(price3);
		price4	= fluctuate(price4);
	}

	public static int fluctuate(int fluctuated) {
		int chance		= rand.nextInt(3);
		
		// 0 = Stable(ish), 1 = Positive shift, 2 = Negative shift.
		switch(chance) {
			case 0: fluctuated	= fluctuated + (rand.nextInt(6) - 3);
			case 1: fluctuated	= fluctuated + rand.nextInt(10);
			case 2: fluctuated	= fluctuated - rand.nextInt(5);
		}
		
		// If things turned out really bad for the company and their share price is bad, make sure the price can't go below $0
		if(fluctuated < 0) {
			fluctuated	= 0;
		}
		
		return fluctuated;
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
		
	}
}

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
	public static Scanner inputScan2= new Scanner(System.in);
	public static int input, day, daysLeft = 0, count = 0, gfcCount = 0;
	public static String version	= "0.5";
	public static int[] inventory1	= {0, 0, 0, 0, 0, 0};
	public static double[] inventory2	= {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	public static boolean gfc		= false;
	public static int rumor			= -1;
	public static String thisRumor, oldRumor, rumorResult = "", news = "";
	public static String[] rumors	= {"Derp Inc have hired a new CEO, share prices are expected to drop.", "Charity commends Herp Industries for their most recent work, share prices set to rise.", "Foo Co has been rumored to be carelessly dumping toxic waste into a nearby river, prices set to drop.", "Bar Pty Ltd released smear campaign against Foo Co, Foo Co prices expected to fall.", "Experts predict Foo Co share prices are going to rise.", "There are rumors that Bar Pty Ltd is bankrupt and will pull out of the stock exchange. Share prices are expected to drop severely."};
	public static String[] listed	= {"Derp Inc", "Herp Industries", "Foo Corp", "Bar Pty Ltd", "Nanohard", "Applesoft"};
	public static double[] exchange	= {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	public static String[] traits	= {"STABLE", "UNSTABLE", "STABLE", "UNSTABLE", "STABLE", "UNSTABLE"};
	public static boolean barBankrupt= false;
	
	public static void main(String[] args) {
		init();
		//if(args.length == 1) {
			//if(args[0].equals("showgui")) {
				GUI.showGUI();
			//}
		//}
		menu();
	}
	
	public static void menu() {
		System.out.println("\n\nStock Market Game v"+version+"\n" +
							"You have $"+Money.money+" in the bank.");
		if(Money.borrowed == true) { System.out.println("You are $"+Money.debt+" in debt"); }
		Game.showNews();
		System.out.println("\n1. Stock Prices.\n" +
							"2. Buy Stocks.\n" +
							"3. Sell Stocks.\n" +
							"4. My Shares.\n" +
							"5. Next Day.\n" +
							"6. Loan Shark.\n" +
							"7. Exit.");
		if(inputScan.hasNextInt() == true) {
			input	= inputScan.nextInt();
		} else {
			inputScan.next();
		}
		
		switch(input) {
			case 1: stockPrices(); menu();
			case 2: Money.buyStock(); menu();
			case 3: Money.sellStock(); menu();
			case 4: myShares(); menu();
			case 5: nextDay(); stockPrices(); menu();
			case 6: Money.loanShark(); menu();
			case 7: System.exit(0);
			case 8: next100(); menu();
			case 9: while(count <= 100) { Game.rollForRumor(); count++; } count = 0; menu();
			case 100: Money.money = Money.money + 500; menu();
			default: System.out.println("Please enter a valid choice."); Game.sleep(500); menu();
		}
	}
	
	private static void init() {
		// Make all the prices a random number so we have something to from.
		for(int i=0; i < 6; i++) {
			exchange[i]	= rand.nextInt(40) + 1;
			if(i == 5 || i == 6) {
				exchange[i]	= rand.nextInt(180) + 20;
			}
		}
		day		= 1;
	}

	private static void stockPrices() {
		for(int i=0; i < listed.length; i++) {
			System.out.println(listed[i]+"	"+"$"+exchange[i]);
		}
	}
	
	public static void myShares() {
		int count	= 0;
		System.out.println("\nMy shares:");
		for(int i=0; i < inventory1.length; i++) {
			if(inventory1[i] != 0) {
				System.out.println(listed[i]+"	"+inventory1[i]+" shares at $"+inventory2[i]+" each.");
				count++;
			}
		}
		if(count == 0) {
			System.out.println("You do not have any shares yet.");
		}
	}

	public static void nextDay() {
		for(int i=0; i < exchange.length; i++) {
			exchange[i]	= Money.fluctuate(exchange[i], traits[i]);
		}
		
		// If there is a rumor
		if(rumor != -1) {
			// Chance if the rumor was true or false
			if(rand.nextInt(3) != 1) { // If the rumor is true
				if(gfc == false) {
					switch(rumor) {
						case 0: exchange[0]	= exchange[0] - (exchange[0] * .3); exchange[0]	= Math.round(exchange[0] * 100.0) / 100.0;
						case 1: exchange[1]	= exchange[1] + (exchange[1] * .2); exchange[1]	= Math.round(exchange[1] * 100.0) / 100.0;
						case 2: exchange[2]	= exchange[2] - (exchange[2] * .4); exchange[2]	= Math.round(exchange[2] * 100.0) / 100.0;
						case 3: exchange[2]	= exchange[2] - (exchange[2] * .6); exchange[2]	= Math.round(exchange[2] * 100.0) / 100.0;
					}
					thisRumor	= "The rumor was true!";
				}
				rumor		= -1; // Set to -2 because the rumor "it was true" doesn't deserve a postive number
			} else { // If the rumor is false
				thisRumor	= "The rumor was false!";
				rumor		= -1; // Set to -2 for the same reason as above ^
			}
		} else {
			if(thisRumor == "") {
				Game.rollForGfc();
				if(gfc == false) {
					Game.rollForRumor();
					Game.rollForBuyBack();
				} else {
					gfcCount	= 3;
				}
			} else {
				rumor = -1;
				thisRumor	= "";
				rumorResult	= "";
			}
		}
		
		if(daysLeft != 0) {
			daysLeft--;
			Money.debt	= Money.debt + (Money.debt * .05);
		}
		
		if(daysLeft == 1) {
			news	= "Logan will be stopping by tomorrow, make sure you have his money!";
		}
		if(Money.borrowed == true && daysLeft == 0) {
			if(Money.money < Money.debt) {
				news	= "You don't have enough money to pay back Logan. You have one more day to pay him back.";
				Money.lastChance	= true;
			} else {
				Money.lastChance	= false;
				Money.borrowed	= false;
				Money.money		= Money.money - Money.debt;
			}
			if(Money.lastChance == true) {
				if(Money.money < Money.debt) {
					Game.gameOver(1);
				}
			}
		}
		
		if(barBankrupt == true) {
			exchange[3]	= 0.0;
		}
		
		if(gfc == true) {
			gfcCount--;
		}
		
		if(gfc == true && gfcCount == 0) {
			gfc	= false;
		}
	}
	
	public static void next100() {
		int run	= 0;
		for(int i=0; i<exchange.length; i++) {
			System.out.println("\n");
			while(run <= 100) {
				exchange[i]	= Money.fluctuate(exchange[i], traits[i]);
				System.out.println(exchange[i]);
				run++;
			} run	= 0;
		}
	}
}

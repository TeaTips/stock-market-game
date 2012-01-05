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
	public static int input, day, daysLeft = 0, count = 0;
	public static double price1, price2, price3, price4;
	public static String version	= "0.3.1";
	public static double money		= 100.0;
	public static double debt		= 0.0;
	public static boolean borrowed = false, lastChance = false;
	public static int[] inventory1	= {0, 0, 0, 0};
	public static double[] inventory2	= {0.0, 0.0, 0.0, 0.0};
	public static boolean gfc		= false;
	public static int rumor			= -1;
	public static String yesno, thisRumor, oldRumor, rumorResult = "";
	public static String[] rumors	= {"Derp Inc have hired a new CEO, share prices are expected to drop.", "Charity commends Herp Industries for their most recent work, share prices set to rise.", "Foo Co has been rumored to be carelessly dumping toxic waste into a nearby river, prices set to drop.", "Bar Pty Ltd released smear campaign against Foo Co, Foo Co prices expected to fall."};
	
	public static void main(String[] args) {
		init();
		menu();
	}
	
	public static void menu() {
		System.out.println("\n\nStock Market Game v"+version+"\n" +
							"You have $"+money+" in the bank.");
		if(borrowed == true) { System.out.println("You are $"+debt+" in debt"); }
		if(rumor != -1 || thisRumor == "The rumor was true!" || thisRumor == "The rumor was false!") {
			System.out.println("\nLatest News: "+thisRumor);
			thisRumor	= "";
		}
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
			case 2: buyStockMenu(); menu();
			case 3: sellStock(); menu();
			case 4: myShares(); menu();
			case 5: nextDay(); stockPrices(); menu();
			case 6: loanShark(); menu();
			case 7: System.exit(0);
			case 8: next100(); menu();
			case 9: while(count <= 100) { rollForRumor(); count++; } count = 0; menu();
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
		
		// If there is a rumor
		if(rumor != -1) {
			// Chance if the rumor was true or false
			if(rand.nextInt(3) != 1) { // If the rumor is true
				if(gfc == false) {
					switch(rumor) {
						case 0: price1	= price1 - (price1 * .3); price1	= Math.round(price1 * 100.0) / 100.0;
						case 1: price2	= price2 + (price2 * .2); price2	= Math.round(price2 * 100.0) / 100.0;
						case 2: price3	= price3 - (price3 * .4); price3	= Math.round(price3 * 100.0) / 100.0;
						case 3: price3	= price3 - (price3 * .6); price3	= Math.round(price3 * 100.0) / 100.0;
					}
				}
				thisRumor	= "The rumor was true!";
				rumor		= -1; // Set to -2 because the rumor "it was true" doesn't deserve a postive number
			} else { // If the rumor is false
				thisRumor	= "The rumor was false!";
				rumor		= -1; // Set to -2 for the same reason as above ^
			}
		} else {
			if(thisRumor == "") {
				thisRumor	= "";
				rollForRumor();
				rollForGfc();
			} else {
				rumor = -1;
				thisRumor	= "";
				rumorResult	= "";
			}
		}
		
		if(daysLeft != 0) {
			daysLeft--;
			debt	= debt + (debt * .05);
		}
		
		if(daysLeft == 1) {
			System.out.println("Logan will be stopping by tomorrow, make sure you have his money!");
		}
		if(borrowed == true && daysLeft == 0) {
			if(money < debt) {
				System.out.println("You don't have enough money to pay back Logan. You have one more day to pay him back.");
				lastChance	= true;
			} else {
				lastChance	= false;
				borrowed	= false;
				money		= money - debt;
			}
			if(lastChance == true) {
				if(money < debt) {
					gameOver(1);
				}
			}
		}
	}
	
	public static void loanShark() {
		System.out.println("\n\nLoan Shark\nHow much would you like to borrow from Logan the loan shark? (intrest rate of 5% per day)\n");
		System.out.print("$");
		input	= inputScan.nextInt();
		System.out.println("Are you sure you wish to borrow $"+input+"? Intrest will be $"+(input * .05)+" per day. (y/n)");
		yesno	= inputScan2.next();
		
		if(yesno.equals("y") || yesno.equals("Y") || yesno.equals("yes")) {
			debt		= input;
			money		= money + input;
			borrowed	= true;
			System.out.println("You have been granted the money. Logan will be visiting in 5 days to get his money back.");
			daysLeft	= 5;
		} else {
			System.out.println("Logan walked.");
		}
	}

	public static double fluctuate(double fluctuation) {
		int chance	= rand.nextInt(3);
		double random	= rand.nextDouble();
		// 0 = Stable(ish), 1 = Positive shift, 2 = Negative shift.
		switch(chance) {
			case 0: fluctuation	= fluctuation + (random * 3) - 3;
			case 1: fluctuation	= fluctuation + (random * 10);
			case 2: fluctuation	= fluctuation - (random * 5);
		}
		
		// If we're going through a financial crisis
		if(gfc == true) {
			fluctuation	= fluctuation - (fluctuation * .8);
		}
		
		// If things turned out really bad for the company and their share price is bad, make sure the price can't go below $0
		if(fluctuation < 0.0) {
			fluctuation	= 0.0;
		}
		
		return (Math.round(fluctuation * 100.0) / 100.0);
	}
	
	public static void rollForRumor() {
		if(rand.nextInt(4) == 1) {
			boolean loop	= true;
			int random	= rand.nextInt(4);
			while(loop) {
				if(rumors[random] == oldRumor) {
					random	= rand.nextInt(4);
				} else {
					thisRumor	= rumors[random];
					rumor	= random;
					loop	= false;
				}
			}
			
			oldRumor	= thisRumor;
			System.out.println("Latest News: "+thisRumor+"\n");
		}
		
	}
	
	public static void rollForGfc() {
		if(rand.nextInt(100) == 1) {
			gfc			= true;
			rumor		= -2;
			thisRumor	= "A global financial crisis is predicted! Expect share prices to severely drop!";
		}
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
	
	public static void gameOver(int reason) {
		System.out.println("\n\n\n\n\n\n\n\n\n" +
							"	GAME OVER\n");
		if(reason == 1) { // Reason 1 means they lost because they couldn't pay back Logan
			System.out.println("You failed to pay back Logan.");
		} else {
			System.out.println("Final score: $"+money);
		}
		System.out.println("\n\n");
	}
}

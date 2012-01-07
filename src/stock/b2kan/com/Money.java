package stock.b2kan.com;

import java.util.Scanner;

public class Money {
	private static String yesno	= "";
	public static double money		= 100.0;
	public static double debt		= 0.0;
	public static boolean borrowed	= false, lastChance = false;
	private static int input		= 0;
	private static Scanner inputScan= new Scanner(System.in);
	
	public static void loanShark() {
		System.out.println("\n\nLoan Shark\nHow much would you like to borrow from Logan the loan shark? (intrest rate of 5% per day)\n");
		System.out.print("$");
		input	= inputScan.nextInt();
		System.out.println("Are you sure you wish to borrow $"+input+"? Intrest will be $"+(input * .05)+" per day. (y/n)");
		yesno	= inputScan.next();
		
		if(yesno.equals("y") || yesno.equals("Y") || yesno.equals("yes")) {
			debt		= input;
			money		= money + input;
			borrowed	= true;
			Main.news		= "You have been granted the money. Logan will be visiting in 5 days to get his money back.";
			Main.daysLeft	= 5;
		} else {
			System.out.println("Logan walked.");
		}
	}

	public static double fluctuate(double fluctuation, String trait) {
		int chance	= Main.rand.nextInt(3);
		double random	= Main.rand.nextDouble();
		int[] change	= {0, 0, 0};
		
		if(trait.equals("STABLE")) {
			change[0] = 2;
			change[1] = 5;
			change[2] = 1;
		} else {
			change[0] = 3;
			change[1] = 7;
			change[2] = 3;
		}
		// 0 = Stable(ish), 1 = Positive shift, 2 = Negative shift.
		switch(chance) {
			case 0: fluctuation	= fluctuation + (random * change[0]) - change[0];
			case 1: fluctuation	= fluctuation + (random * change[1]);
			case 2: fluctuation	= fluctuation - (random * change[2]);
		}
		
		// If we're going through a financial crisis
		if(Main.gfc == true) {
			fluctuation	= fluctuation - (fluctuation * .8);
		}
		
		// If things turned out really bad for the company and their share price is bad, make sure the price can't go below $0
		if(fluctuation < 0.0) {
			fluctuation	= 0.0;
		}
		
		return (Math.round(fluctuation * 100.0) / 100.0);
	}
	
	public static boolean enoughFunds(int companyID, int quantity) {
		if(Money.money >= (Main.exchange[companyID-1] * quantity)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void buyStock() {
		int amount	= 0;
		double currentPrice = 0;
		System.out.println("\n\nWhich company would you like to buy shares in?\n" +
							"1. Derp Inc.\n" +
							"2. Herp Industries.\n" +
							"3. Foo Co.\n" +
							"4. Bar Pty Ltd.\n" +
							"5. Nanohard\n" +
							"6. Applesoft\n" +
							"7. Back.");
		input	= inputScan.nextInt();
		if(input == 7) {
			Main.menu();
		}
		System.out.println("How many?");
		amount	= inputScan.nextInt();
		
		if(input > 0 && input < 7) {
			if(Money.enoughFunds(input, amount) == true) {
				currentPrice	= Main.exchange[input-1];
				if(currentPrice == 0.0) {
					System.out.println("You can't buy stocks at $0.00");
					Game.sleep(500);
				} else {
					Main.inventory1[input-1]	= Main.inventory1[input-1] + amount;
					Main.inventory2[input-1]	= currentPrice;
					Money.money					= Money.money - (currentPrice * amount);
					System.out.println("Stocks purchased!");
				}
			} else {
				System.out.println("You don't have enough capital to buy these shares.");
			}
		}
	}
	
	public static void sellStock() {
		int amount	= 0;
		double oldPrice	= 0.0;
		double currentPrice = 0.0;
		System.out.println("\n\nWhich company would you like to sell shares from?\n" +
							"1. Derp Inc.		("+Main.inventory1[0]+")\n" +
							"2. Herp Industries.	("+Main.inventory1[1]+")\n" +
							"3. Foo Co.		("+Main.inventory1[2]+")\n" +
							"4. Bar Pty Ltd.		("+Main.inventory1[3]+")\n" +
							"5. Nanohard		("+Main.inventory1[4]+")\n" +
							"6. Applesoft		("+Main.inventory1[5]+")\n" +
							"7. Back.");
		input	= inputScan.nextInt();
		if(input == 7) {
			Main.menu();
		}
		System.out.println("How many?");
		amount	= inputScan.nextInt();
		
		if(amount > Main.inventory1[input-1]) {
			System.out.println("You only have "+Main.inventory1[input-1]+" shares in that company.");
			Game.sleep(500);
			sellStock();
		} else {
			oldPrice		= Main.inventory2[input-1];
			currentPrice	= Main.exchange[input-1];
			Main.inventory1[input-1]	= Main.inventory1[input-1] - amount;
			Money.money	= Money.money + (amount * currentPrice);
			
			System.out.println("Shares sold! Profit made: $"+((currentPrice - oldPrice)*amount));
		}
	}
}

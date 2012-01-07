package stock.b2kan.com;

import java.util.Scanner;

public class Game {
	//private static int input		= 0;
	private static Scanner inputScan= new Scanner(System.in);
	
	public static void rollForRumor() {
		if(Main.rand.nextInt(6) == 1) {
			boolean loop	= true;
			int random	= Main.rand.nextInt(6);
			while(loop) {
				if(Main.rumors[random] == Main.oldRumor) {
					random	= Main.rand.nextInt(6);
				} else {
					if(random == 5) {
						Main.barBankrupt	= true;
					}
					Main.thisRumor	= Main.rumors[random];
					Main.rumor	= random;
					loop	= false;
				}
			}
			Main.oldRumor	= Main.thisRumor;
		}
		
	}
	
	public static void rollForGfc() {
		if(Main.rand.nextInt(100) == 1) {
			Main.gfc		= true;
			Main.rumor		= -2;
			Main.thisRumor	= "A global financial crisis is predicted! Expect share prices to severely drop!";
		}
	}
	
	public static void rollForBuyBack() {
		if(Main.rand.nextInt(100) == 1) {
			int random			= Main.rand.nextInt(6);
			double currentPrice	= Main.exchange[random];
			if(Main.inventory1[random] != 0) {
				System.out.println(Main.listed[random]+" are offering to buy back all of their shares at $"+(currentPrice + (currentPrice * .2))+", do you accept their offer? (y/n)");
				String yesno	= inputScan.next();
				
				if(yesno.equals("y") || yesno.equals("Y") || yesno.equals("yes")) {
					Main.inventory1[random]	= 0;
					Money.money	= Money.money + (Main.inventory1[random] * (currentPrice * .2));
				}
			}
		}
	}
	
	public static void showNews() {
		if(Main.rumor != -1 || Main.thisRumor == "The rumor was true!" || Main.thisRumor == "The rumor was false!") {
			System.out.println("\nLatest News: "+Main.thisRumor);
		}
		if(Main.news != "") {
			System.out.println("\n"+Main.news);
			Main.news	= "";
		}
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
			System.out.println("Final score: $"+Money.money);
		}
		System.out.println("\n\n");
	}
}

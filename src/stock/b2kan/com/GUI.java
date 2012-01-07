package stock.b2kan.com;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private static JButton buyDerp, buyHerp, buyFoo, buyBar, buyNano, buyApple, nextDay;
	private static JLabel companyName, priceDerp, priceHerp, priceFoo, priceBar, priceNano, priceApple;
	public static GridBagConstraints con	= new GridBagConstraints();
	public static JFrame gui	= new JFrame("GUI tests");
	public static GUI content	= new GUI(gui);
	
	public static void showGUI() {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.getContentPane();
		
		gui.pack();
		gui.setVisible(true);
	}
	
	public GUI(Container pane) {
		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		con.fill				= GridBagConstraints.HORIZONTAL;
		con.gridx	= 0;
		con.gridy	= 0;
		con.insets	= new Insets(10,10,10,10);
		JPanel buyShares	= new JPanel();
		buyShares.setLayout(new GridBagLayout());
		pane.add(buyShares, con);
		
		companyName		= new JLabel("Derp Inc"); 
		buyDerp			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 0;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyDerp, con);

		companyName		= new JLabel("Herp Industries"); 
		buyHerp			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 1;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyHerp, con);
		
		companyName		= new JLabel("Foo Corp"); 
		buyFoo			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 2;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyFoo, con);
		
		companyName		= new JLabel("Bar Pty Ltd"); 
		buyBar			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 3;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyBar, con);
		
		companyName		= new JLabel("Nanohard"); 
		buyNano			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 4;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyNano, con);
		
		companyName		= new JLabel("Applesoft"); 
		buyApple		= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 5;
		buyShares.add(companyName, con);
		con.gridx	= 1;
		buyShares.add(buyApple, con);
		
		con.gridx	= 0;
		
		showPrices(pane, false);
		con.gridy	= Main.listed.length;
		nextDay		= new JButton("Next Day");
		buyShares.add(nextDay, con);
		
		HandlerClass handler	= new HandlerClass();
		buyDerp.addActionListener(handler);
		buyHerp.addActionListener(handler);
		buyFoo.addActionListener(handler);
		buyBar.addActionListener(handler);
		buyNano.addActionListener(handler);
		buyApple.addActionListener(handler);
		nextDay.addActionListener(handler);
	}
	
	public static void showPrices(Container pane, boolean update) {
		con.gridx	= 1;
		con.gridy	= 0;
		con.weighty	= 0.0;
		JPanel sharePrice	= new JPanel();
		sharePrice.setLayout(new GridBagLayout());
		pane.add(sharePrice, con);
		if(update == false) {
			con.gridx	= 0;
			con.gridy	= 0;
			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			String thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceDerp	= new JLabel("$"+thisPrice);
			sharePrice.add(priceDerp, con);
			
			con.gridx	= 0;
			con.gridy++;
			

			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceHerp	= new JLabel("$"+thisPrice);
			sharePrice.add(priceHerp, con);
			
			con.gridx	= 0;
			con.gridy++;
			

			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceFoo	= new JLabel("$"+thisPrice);
			sharePrice.add(priceFoo, con);
			
			con.gridx	= 0;
			con.gridy++;
			

			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceBar	= new JLabel("$"+thisPrice);
			sharePrice.add(priceBar, con);
			
			con.gridx	= 0;
			con.gridy++;
			

			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceNano	= new JLabel("$"+thisPrice);
			sharePrice.add(priceNano, con);
			
			con.gridx	= 0;
			con.gridy++;
			

			companyName	= new JLabel(Main.listed[con.gridy]);
			sharePrice.add(companyName, con);
			
			con.gridx	= 1;
			thisPrice	= Double.toString(Main.exchange[con.gridy]);
			priceApple	= new JLabel("$"+thisPrice);
			sharePrice.add(priceApple, con);
			
			con.gridx	= 0;
			con.gridy++;
		} else {
			int count	= 0;
			String thisPrice	= Double.toString(Main.exchange[count]);
			priceDerp.setText("$"+thisPrice);
			count++;
			thisPrice	= Double.toString(Main.exchange[count]);
			priceHerp.setText("$"+thisPrice);
			count++;
			thisPrice	= Double.toString(Main.exchange[count]);
			priceFoo.setText("$"+thisPrice);
			count++;
			thisPrice	= Double.toString(Main.exchange[count]);
			priceBar.setText("$"+thisPrice);
			count++;
			thisPrice	= Double.toString(Main.exchange[count]);
			priceNano.setText("$"+thisPrice);
			count++;
			thisPrice	= Double.toString(Main.exchange[count]);
			priceApple.setText("$"+thisPrice);
			pane.repaint();
		}
		//sharePrice.add(nextDay, con);
	}
	
	private class HandlerClass implements ActionListener {
		int amount;
		JOptionPane yesNo;
		Object obj;
		Object[] options	= new String[] {"Yes", "No"};
		JDialog dialog;
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == nextDay) {
				Main.nextDay();
				showPrices(gui, true);
				Game.showNews();
			}
			if(event.getSource() == buyDerp || event.getSource() == buyHerp || event.getSource() == buyFoo || event.getSource() == buyBar || event.getSource() == buyNano || event.getSource() == buyApple) {
				String StAmount	= JOptionPane.showInputDialog("How many shares would you like to buy?");
				amount		= Integer.parseInt(StAmount);
			}
			
			if(event.getSource() == buyDerp) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in Derp Inc for $"+Main.exchange[0]+" each? (Total $"+(amount*Main.exchange[0])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
			
			if(event.getSource() == buyHerp) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in Herp Industries for $"+Main.exchange[1]+" each? (Total $"+(amount*Main.exchange[1])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
			
			if(event.getSource() == buyFoo) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in FooCorp for $"+Main.exchange[2]+" each? (Total $"+(amount*Main.exchange[2])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
			
			if(event.getSource() == buyBar) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in Bar Pty Ltd for $"+Main.exchange[3]+" each? (Total $"+(amount*Main.exchange[3])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
			
			if(event.getSource() == buyNano) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in Nanohard for $"+Main.exchange[4]+" each? (Total $"+(amount*Main.exchange[4])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
			
			if(event.getSource() == buyApple) {
				yesNo	= new JOptionPane("Buy "+amount+" shares in Applesoft for $"+Main.exchange[5]+" each? (Total $"+(amount*Main.exchange[5])+")");
				yesNo.setOptions(options);
				dialog = yesNo.createDialog(new JFrame(), "Dialog");
				dialog.setVisible(true);
				obj	= yesNo.getValue();
				System.out.println("User chose: "+obj);
			}
		}
	}
	
}

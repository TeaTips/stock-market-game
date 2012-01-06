package stock.b2kan.com;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private static JButton buyDerp;
	private static JButton buyHerp;
	private static JButton buyFoo;
	private static JButton buyBar;
	private static JButton buyNano;
	private static JButton buyApple;
	private static JLabel companyName;
	
	public static void showGUI() {
		JFrame gui	= new JFrame("GUI tests");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI content	= new GUI(gui);
		content.getContentPane();
		
		gui.pack();
		gui.setVisible(true);
	}
	
	public GUI(Container pane) {
		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints con	= new GridBagConstraints();
		con.fill				= GridBagConstraints.HORIZONTAL;
		
		JPanel shares	= new JPanel();
		shares.setLayout(new GridBagLayout());
		pane.add(shares, con);
		
		companyName		= new JLabel("Derp Inc"); 
		buyDerp			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 0;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyDerp, con);

		companyName		= new JLabel("Herp Industries"); 
		buyHerp			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 1;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyHerp, con);
		
		companyName		= new JLabel("Foo Corp"); 
		buyFoo			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 2;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyFoo, con);
		
		companyName		= new JLabel("Bar Pty Ltd"); 
		buyBar			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 3;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyBar, con);
		
		companyName		= new JLabel("Nanohard"); 
		buyNano			= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 4;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyNano, con);
		
		companyName		= new JLabel("Applesoft"); 
		buyApple		= new JButton("Buy shares");
		con.gridx	= 0;
		con.gridy	= 5;
		shares.add(companyName, con);
		con.gridx	= 1;
		shares.add(buyApple, con);
		
		HandlerClass handler	= new HandlerClass();
		buyDerp.addActionListener(handler);
		buyHerp.addActionListener(handler);
		buyFoo.addActionListener(handler);
		buyBar.addActionListener(handler);
		buyNano.addActionListener(handler);
		buyApple.addActionListener(handler);
	}
	
	private class HandlerClass implements ActionListener {
		int amount;
		JOptionPane yesNo;
		Object obj;
		Object[] options	= new String[] {"Yes", "No"};
		JDialog dialog;
		public void actionPerformed(ActionEvent event) {
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

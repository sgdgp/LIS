package libreria;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.*;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPassword;
	private JTextField textFieldUsername;
	private JLabel lblUsertype;
	private JComboBox<String> comboBoxUserType;

	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				splashTimer();
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.pack();
				    frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*spalsh time setter*/
	
	public static void splashTimer(){

		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	/*constructor for the main window */
	
	public MainWindow() {
		setMinimumSize(new Dimension(500, 340));

		setTitle("LIBRERIA v1.0");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Libreria",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 500, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.RED, Color.ORANGE, Color.DARK_GRAY, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setBounds(202, 12, 81, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(49, 131, 103, 25);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(170, 133, 241, 22);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(49, 97, 103, 25);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(170, 99, 241, 22);
		contentPane.add(textFieldUsername);
		
		lblUsertype = new JLabel("Type :");
		lblUsertype.setBounds(49, 173, 103, 25);
		contentPane.add(lblUsertype);
		
		comboBoxUserType = new JComboBox();
		comboBoxUserType.setBounds(170, 173, 241, 24);
		contentPane.add(comboBoxUserType);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setBounds(108, 257, 117, 25);
		contentPane.add(btnProceed);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(270, 257, 117, 25);
		contentPane.add(btnQuit);
		comboBoxUserType.addItem("---Choose Type---");
		comboBoxUserType.addItem("Librarian");
		comboBoxUserType.addItem("Clerk");
		comboBoxUserType.addItem("User");
	}
}

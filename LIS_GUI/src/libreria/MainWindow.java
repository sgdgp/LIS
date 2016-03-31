package libreria;

import java.awt.EventQueue;
import javax.swing.UIManager.*;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField textFieldPassword;
	private JTextField textFieldUsername;
	private JLabel lblUsertype;
	private JComboBox<String> comboBoxUserType;

	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				splashTimer();
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				   System.out.println("Nimbus not available");
				}
				Initialize I = new Initialize();
				boolean flagInit = I.init();
				System.out.println(flagInit);
				if (flagInit)
					JOptionPane.showMessageDialog(null, "Init done");
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
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.RED, Color.ORANGE, Color.DARK_GRAY, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setBounds(202, 12, 81, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(49, 131, 103, 25);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JPasswordField();
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
		
		comboBoxUserType = new JComboBox<String>();
		comboBoxUserType.setBounds(170, 173, 241, 24);
		contentPane.add(comboBoxUserType);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textFieldUsername.getText().trim();
				String password = String.valueOf(textFieldPassword.getPassword()).trim();
				
				String type = comboBoxUserType.getSelectedItem().toString().trim();
				
				boolean checkEmpty = true;//checkEmpty(username,password,type);
				
				if(checkEmpty){
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
					
					Statement stmt = null;
					String query = null;
					switch(type){
					case "Librarian":
						query = "SELECT * FROM librarian";
						//query  = "SELECT password FROM librarian WHERE username="+username;
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while(rs.next()){
							
							if(rs.getString("password").equals(password) && rs.getString("username").equals(username)){
								try {
									LibrarianHomePage frame = new LibrarianHomePage();
									frame.setVisible(true);
									dispose();
									break;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						break;
					case "Clerk" :
						break;
					case "User" :
						break;
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else{
					System.out.println("else part");
				}
			}
		});
		btnProceed.setBounds(108, 257, 117, 25);
		contentPane.add(btnProceed);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Libreria",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
			}
		});
		btnQuit.setBounds(270, 257, 117, 25);
		contentPane.add(btnQuit);
		
		JLabel lblLibreriaLibrary = new JLabel("LIBRERIA ---- LIBRARY INFORMATION SYSTEM");
		lblLibreriaLibrary.setBounds(113, 44, 241, 14);
		contentPane.add(lblLibreriaLibrary);
		comboBoxUserType.addItem("---Choose Type---");
		comboBoxUserType.addItem("Librarian");
		comboBoxUserType.addItem("Clerk");
		comboBoxUserType.addItem("User");
	}
	
	
}

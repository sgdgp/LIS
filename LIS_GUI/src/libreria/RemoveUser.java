package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class RemoveUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JLabel lblEnterUsername;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Removeuser frame = new Removeuser();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RemoveUser() {
		setTitle("Remove user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(161, 72, 239, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton btnRemoveuser = new JButton("Remove user");
		btnRemoveuser.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		btnRemoveuser.setBackground(new Color(102, 51, 153));
		btnRemoveuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					String username = textFieldUsername.getText().trim();
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
					con.createStatement().executeQuery("SET SQL_SAFE_UPDATES=0");
					System.out.println("line executed");
					String sql1 = "Select count(*) from users where username='"+username+"'";
					Statement st = con.createStatement();
					ResultSet r = st.executeQuery(sql1);
					r.next();
					System.out.println(r.getInt(1));
					if(r.getInt(1)!=0){
						String sql = "Delete from users where username='"+username+"'";
						System.out.println("if line executed");
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
					}
					else{
						System.out.println("user not present");
					}
				} catch (SQLException ex) {
					System.out.println("Exception reached");
//					e.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemoveuser.setBounds(127, 127, 162, 33);
		contentPane.add(btnRemoveuser);
		
		lblEnterUsername = new JLabel("Enter username");
		lblEnterUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterUsername.setBounds(10, 75, 118, 14);
		contentPane.add(lblEnterUsername);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		btnBack.setBackground(new Color(102, 51, 153));
		btnBack.setBounds(33, 227, 89, 23);
		contentPane.add(btnBack);
	}
}

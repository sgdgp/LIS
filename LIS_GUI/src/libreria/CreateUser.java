package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldUsername;
	private JTextField textFieldAddress;
	private JTextField textFieldPhone;
	private JPasswordField textFieldPassword;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateUser frame = new CreateUser();
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
	public CreateUser() {
		setTitle("Create New User");
		setBackground(new Color(138, 43, 226));
		setBounds(100, 100, 494, 324);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(139, 0, 139));
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblName.setBounds(78, 56, 91, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(139, 0, 139));
		lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblUsername.setBounds(78, 81, 102, 14);
		contentPane.add(lblUsername);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(139, 0, 139));
		lblAddress.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblAddress.setBounds(78, 106, 101, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(new Color(139, 0, 139));
		lblPhoneNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPhoneNumber.setBounds(78, 131, 116, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(139, 0, 139));
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPassword.setBounds(78, 156, 100, 14);
		contentPane.add(lblPassword);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(173, 216, 230));
		comboBox.setFont(new Font("Verdana", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UG Student", "PG Student", "Research Scholar", "Faculty"}));
		comboBox.setBounds(260, 201, 116, 18);
		contentPane.add(comboBox);
		
		JLabel lblChooseUserType = new JLabel("Choose User Type");
		lblChooseUserType.setForeground(new Color(139, 0, 139));
		lblChooseUserType.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblChooseUserType.setBounds(78, 203, 159, 14);
		contentPane.add(lblChooseUserType);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(203, 54, 209, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(203, 79, 209, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(203, 104, 209, 20);
		contentPane.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(204, 129, 208, 20);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(203, 154, 209, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUsername.getText().trim();
				String password = String.valueOf(textFieldPassword.getPassword()).trim();
				String name = textFieldName.getText().trim();
				String address = textFieldAddress.getText().trim();
				String phone = textFieldPhone.getText().trim();
//				boolean check = checkEntry();
				
				addtoDatabase(username,name,address,phone,password);
				
			}
		});
		btnCreateUser.setBackground(new Color(255, 105, 180));
		btnCreateUser.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnCreateUser.setBounds(305, 251, 124, 23);
		contentPane.add(btnCreateUser);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(255, 105, 180));
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnBack.setBounds(21, 251, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblCreateNewUser = new JLabel("Create New User");
		lblCreateNewUser.setForeground(new Color(0, 0, 205));
		lblCreateNewUser.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblCreateNewUser.setBounds(48, 11, 209, 34);
		contentPane.add(lblCreateNewUser);
	}
	
	public void addtoDatabase(String username,String name,String address,String phone,String password){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
			Statement stmt = con.createStatement();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		
		
	}
}

package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateClerk extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateClerk frame = new CreateClerk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateClerk() {
		setTitle("Create New Clerk");
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
		
		textField = new JTextField();
		textField.setBounds(203, 54, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 79, 209, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(203, 104, 209, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(204, 129, 208, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(203, 154, 209, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnCreateUser = new JButton("Create Clerk");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JLabel lblCreateNewUser = new JLabel("Create New Clerk");
		lblCreateNewUser.setForeground(new Color(0, 0, 205));
		lblCreateNewUser.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblCreateNewUser.setBounds(48, 11, 209, 34);
		contentPane.add(lblCreateNewUser);
	}
}

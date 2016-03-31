package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class LibrarianHomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianHomePage frame = new LibrarianHomePage();
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
	public LibrarianHomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameOfLibrarian = new JLabel("Name of librarian");
		lblNameOfLibrarian.setBounds(103, 12, 155, 15);
		contentPane.add(lblNameOfLibrarian);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(324, 7, 117, 25);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Operations that can be performed");
		lblNewLabel.setBounds(139, 63, 214, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnAddUser = new JButton("Add user");
		btnAddUser.setBackground(new Color(221, 160, 221));
		btnAddUser.setBounds(45, 117, 139, 23);
		contentPane.add(btnAddUser);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setBackground(new Color(221, 160, 221));
		btnRemoveUser.setBounds(278, 117, 149, 23);
		contentPane.add(btnRemoveUser);
		
		JButton btnAddClerk = new JButton("Add clerk");
		btnAddClerk.setBackground(new Color(221, 160, 221));
		btnAddClerk.setBounds(45, 166, 139, 23);
		contentPane.add(btnAddClerk);
		
		JButton btnRemoveClerk = new JButton("Remove clerk");
		btnRemoveClerk.setBackground(new Color(221, 160, 221));
		btnRemoveClerk.setBounds(278, 166, 149, 23);
		contentPane.add(btnRemoveClerk);
		
		JButton btnPrintNotifications = new JButton("Print notifications");
		btnPrintNotifications.setBackground(new Color(221, 160, 221));
		btnPrintNotifications.setBounds(147, 222, 155, 23);
		contentPane.add(btnPrintNotifications);
	}
}

package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class LibraryUser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LibraryUser frame = new LibraryUser();
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
	public LibraryUser() {
		setTitle("Library User Actions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibraryUserActions = new JLabel("Library User Actions");
		lblLibraryUserActions.setForeground(new Color(75, 0, 130));
		lblLibraryUserActions.setBackground(new Color(240, 240, 240));
		lblLibraryUserActions.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblLibraryUserActions.setBounds(105, 0, 288, 25);
		contentPane.add(lblLibraryUserActions);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					SearchBook frame = new SearchBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnIssueBook.setBackground(new Color(46, 139, 87));
		btnIssueBook.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnIssueBook.setBounds(124, 66, 120, 23);
		contentPane.add(btnIssueBook);
		
		JButton btnReserveBook = new JButton("Reserve Book");
		btnReserveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					SearchBook frame = new SearchBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnReserveBook.setBackground(new Color(46, 139, 87));
		btnReserveBook.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnReserveBook.setBounds(124, 101, 120, 23);
		contentPane.add(btnReserveBook);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.setBackground(new Color(46, 139, 87));
		btnReturnBook.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnReturnBook.setBounds(124, 135, 120, 23);
		contentPane.add(btnReturnBook);
		
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					SearchBook frame = new SearchBook();
					frame.setVisible(true);
				} catch (Exception ex) {
//					e.printStackTrace();
				}
			}
		});
		btnSearchBook.setBackground(new Color(60, 179, 113));
		btnSearchBook.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnSearchBook.setBounds(124, 36, 120, 23);
		contentPane.add(btnSearchBook);
		
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.setBackground(new Color(46, 139, 87));
		btnPayFine.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnPayFine.setBounds(124, 169, 120, 23);
		contentPane.add(btnPayFine);
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.setBackground(new Color(46, 139, 87));
		btnNotifications.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnNotifications.setBounds(289, 227, 120, 23);
		contentPane.add(btnNotifications);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(46, 139, 87));
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
	}
}

package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class LibraryClerkScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LibraryClerkScreen frame = new LibraryClerkScreen();
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
	public LibraryClerkScreen() {
		setTitle("Library Clerk Options");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				MainWindow x = new MainWindow();
				x.setVisible(true);
				x.setLocationRelativeTo(null);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setBackground(new Color(144, 238, 144));
		btnAddBook.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					AddBook frame = new AddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAddBook.setBounds(128, 79, 166, 49);
		contentPane.add(btnAddBook);
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setBackground(new Color(144, 238, 144));
		btnDeleteBook.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		btnDeleteBook.setBounds(128, 139, 166, 49);
		contentPane.add(btnDeleteBook);
		
		JLabel lblLibraryClerkOptions = new JLabel("Library Clerk Options");
		lblLibraryClerkOptions.setForeground(UIManager.getColor("ComboBox.selectionBackground"));
		lblLibraryClerkOptions.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 20));
		lblLibraryClerkOptions.setBounds(113, 28, 213, 40);
		contentPane.add(lblLibraryClerkOptions);
		
		JButton btnViewTasks = new JButton("View Tasks");
		btnViewTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClerkNotification frame = new ClerkNotification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnViewTasks.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnViewTasks.setBounds(153, 227, 117, 23);
		contentPane.add(btnViewTasks);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MainWindow frame=new MainWindow();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnLogout.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnLogout.setBounds(335, 11, 89, 23);
		contentPane.add(btnLogout);
	}
}

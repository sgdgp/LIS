package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchBook extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldISBN;
	private JTextField textFieldName;
	private JTextField textFieldAuthor;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SearchBook frame = new SearchBook();
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
	String username;
	public SearchBook(String uname) {

		username = uname;
		setTitle("Search Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldName.getText().trim();
				String author = textFieldAuthor.getText().trim();
				String ISBN = textFieldISBN.getText().trim();
				dispose();
				try {
					BookDetails frame = new BookDetails(username);
					frame.setVisible(true);
					frame.showParams(name,author,ISBN);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnSearch.setBackground(new Color(119, 136, 153));
		btnSearch.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnSearch.setBounds(303, 205, 89, 23);
		contentPane.add(btnSearch);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(171, 73, 231, 20);
		contentPane.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(171, 112, 231, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(171, 159, 231, 20);
		contentPane.add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		JLabel lblIsbnNumber = new JLabel("ISBN Number");
		lblIsbnNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblIsbnNumber.setBounds(30, 76, 123, 14);
		contentPane.add(lblIsbnNumber);
		
		JLabel lblNameOfThe = new JLabel("Name of the Book");
		lblNameOfThe.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNameOfThe.setBounds(30, 115, 131, 14);
		contentPane.add(lblNameOfThe);
		
		JLabel lblAuthorsName = new JLabel("Author's Name");
		lblAuthorsName.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblAuthorsName.setBounds(30, 162, 123, 14);
		contentPane.add(lblAuthorsName);
		
		JLabel lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setForeground(new Color(51, 102, 204));
		lblSearchBook.setFont(new Font("Perpetua Titling MT", Font.BOLD | Font.ITALIC, 18));
		lblSearchBook.setBounds(132, 11, 159, 28);
		contentPane.add(lblSearchBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBackground(new Color(119, 136, 153));
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnBack.setBounds(50, 205, 89, 23);
		contentPane.add(btnBack);
	}
}

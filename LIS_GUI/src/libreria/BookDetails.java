package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class BookDetails extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetails frame = new BookDetails();
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
	public BookDetails() {
		setTitle("Book Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 401);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblName.setBounds(40, 61, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblAuthor.setBounds(40, 87, 46, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblIsbnNumber = new JLabel("ISBN Number");
		lblIsbnNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblIsbnNumber.setBounds(40, 112, 93, 14);
		contentPane.add(lblIsbnNumber);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(243, 58, 222, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(243, 84, 222, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(243, 109, 222, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblBookDetails = new JLabel("Book Details");
		lblBookDetails.setForeground(new Color(72, 61, 139));
		lblBookDetails.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 18));
		lblBookDetails.setBounds(40, 11, 169, 31);
		contentPane.add(lblBookDetails);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(176, 224, 230));
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnBack.setBounds(10, 328, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNumberOfCopies = new JLabel("Number of Copies");
		lblNumberOfCopies.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNumberOfCopies.setBounds(40, 137, 128, 14);
		contentPane.add(lblNumberOfCopies);
		
		JLabel lblNumberOfCopies_1 = new JLabel("Number of Copies issued");
		lblNumberOfCopies_1.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNumberOfCopies_1.setBounds(40, 162, 169, 14);
		contentPane.add(lblNumberOfCopies_1);
		
		JLabel lblNumberOfCopies_2 = new JLabel("Number of Copies reserved");
		lblNumberOfCopies_2.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNumberOfCopies_2.setBounds(40, 187, 159, 14);
		contentPane.add(lblNumberOfCopies_2);
		
		JLabel lblNumberOfCopies_3 = new JLabel("Number of Copies on shelf");
		lblNumberOfCopies_3.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNumberOfCopies_3.setBounds(40, 215, 169, 14);
		contentPane.add(lblNumberOfCopies_3);
		
		JLabel lblRackNumber = new JLabel("Rack Number");
		lblRackNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblRackNumber.setBounds(40, 240, 118, 14);
		contentPane.add(lblRackNumber);
		
		JLabel lblEarliestDueDate = new JLabel("Earliest Due date of an issued book");
		lblEarliestDueDate.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblEarliestDueDate.setBounds(40, 265, 211, 14);
		contentPane.add(lblEarliestDueDate);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.setBackground(new Color(176, 224, 230));
		btnIssueBook.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnIssueBook.setBounds(186, 328, 118, 23);
		contentPane.add(btnIssueBook);
		
		JButton btnReturnBook = new JButton("Reserve Book");
		btnReturnBook.setBackground(new Color(176, 224, 230));
		btnReturnBook.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReturnBook.setBounds(339, 328, 144, 23);
		contentPane.add(btnReturnBook);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(243, 134, 222, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(243, 159, 222, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(243, 184, 222, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(243, 212, 222, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(243, 262, 222, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(243, 237, 222, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
	}

}

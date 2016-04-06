package libreria;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAuthor;
	private JTextField textFieldISBN;
	private JTextField textFieldRack;
	private JTextField textFieldYear;
	private JLabel lblPrice;
	private JLabel lblPublisher;
	private JTextField textFieldPrice;
	private JTextField textFieldPublisher;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddBook frame = new AddBook();
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
	public AddBook() {
		setTitle("Book Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 372);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
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
		
		textFieldName = new JTextField();
		textFieldName.setBounds(150, 59, 222, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(150, 85, 222, 20);
		contentPane.add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(150, 110, 222, 20);
		contentPane.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		JLabel lblBookDetails = new JLabel("Enter Book Details");
		lblBookDetails.setForeground(new Color(72, 61, 139));
		lblBookDetails.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 18));
		lblBookDetails.setBounds(40, 11, 169, 31);
		contentPane.add(lblBookDetails);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBackground(new Color(176, 224, 230));
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnBack.setBounds(10, 299, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblRackNumber = new JLabel("Rack Number");
		lblRackNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblRackNumber.setBounds(40, 137, 118, 14);
		contentPane.add(lblRackNumber);
		
		JButton btnReturnBook = new JButton("Add Book");
		btnReturnBook.setBackground(new Color(176, 224, 230));
		btnReturnBook.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldName.getText().trim();
				String author = textFieldAuthor.getText().trim();
				String publisher = textFieldPublisher.getText().trim();
				String rackno = textFieldRack.getText().trim();
				String ISBN = textFieldISBN.getText().trim();
				String year = textFieldYear.getText().trim();
				String price = textFieldPrice.getText().trim();
				addToDatabase(ISBN,name,author,publisher,year,rackno,price);
				
			}
		});
		btnReturnBook.setBounds(277, 299, 144, 23);
		contentPane.add(btnReturnBook);
		
		textFieldRack = new JTextField();
		textFieldRack.setBounds(150, 135, 222, 20);
		contentPane.add(textFieldRack);
		textFieldRack.setColumns(10);
		
		textFieldYear = new JTextField();
		textFieldYear.setBounds(150, 162, 222, 20);
		contentPane.add(textFieldYear);
		textFieldYear.setColumns(10);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblYear.setBounds(40, 162, 46, 14);
		contentPane.add(lblYear);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPrice.setBounds(40, 192, 78, 14);
		contentPane.add(lblPrice);
		
		lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPublisher.setBounds(40, 217, 78, 14);
		contentPane.add(lblPublisher);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(150, 189, 224, 20);
		contentPane.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		textFieldPublisher = new JTextField();
		textFieldPublisher.setBounds(150, 214, 222, 20);
		contentPane.add(textFieldPublisher);
		textFieldPublisher.setColumns(10);
	}
	@SuppressWarnings("unchecked")
	public void addToDatabase(String ISBN,String name,String author, String publisher,String year,String rackno,String price){
		try {
			System.out.println("Add book reached");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("Select * from books where ISBN='"+ISBN+"'");
			int count=0;
			int oS=0;
			while(r.next()){
				count = r.getInt("countID");
				oS = r.getInt("onShelf");
			}
			
			
			ArrayList<BookInfo> cd = new ArrayList<BookInfo>();
			ArrayList<String> rl = new ArrayList<String>();
			if(count!=0){
				//set up the copy details arraylist
				byte[] buf1 = r.getBytes("copyDetails");
                ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
                cd = (ArrayList<BookInfo>) o1.readObject();
                
                cd.add(new BookInfo(false));
				//set up the reserve list arraylist
				byte[] buf2 = r.getBytes("reserveList");
                ObjectInputStream o2 = new ObjectInputStream(new ByteArrayInputStream(buf2));
                rl = (ArrayList<String>) o2.readObject();
                
//                rl.add("-");
                String sql = "UPDATE books SET reserveList = " + "?"
                        + ", copyDetails = " + "?"
                        + " WHERE ISBN = '" + ISBN + "'";
                PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setObject(2,cd);
				stmt.setObject(1, rl);
				stmt.setInt(7, oS+1);
				stmt.setInt(8, count+1);
				stmt.executeUpdate();
			}
			else{
				System.out.println("else part of add book adtodatabase, count="+count);
				cd.add(new BookInfo(false));
//				rl.add("-");
				String sql = "INSERT INTO books (ISBN,name,author,publisher,yearOfPurchase,rackNo,onShelf,countID,issueStats,price,isReserved,copyDetails,reserveList,delNotif)" +
	                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ISBN);
				stmt.setString(2, name);
				stmt.setString(3, author);
				stmt.setString(4, publisher);
				stmt.setString(5, year);
				stmt.setString(6, rackno);
				stmt.setInt(7, oS+1);
				stmt.setInt(8, count+1);
				stmt.setInt(9, 0);
				stmt.setString(10, price);
				stmt.setBoolean(11, false);
				stmt.setObject(12,cd);
				stmt.setObject(13, rl);
				stmt.setBoolean(14, false);
				stmt.executeUpdate();
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}

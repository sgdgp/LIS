package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;

public class UserOverdueNotification extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public UserOverdueNotification(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 362, 290);
		contentPane.add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"ISBN"});
		table.setModel(model);
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis","root","qwerty");
			ResultSet r = con.createStatement().executeQuery("Select * from users WHERE username = '" + username+"'");
			//String add = "SELECT * FROM users WHERE username = '" + username+"'";
			while(r.next()){
				byte[] buf1 = r.getBytes("booksIssued");
	            ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
	            ArrayList<UserIssueDetails> booksIssued = (ArrayList<UserIssueDetails>) o1.readObject();
	            for(int i=0;i<booksIssued.size();++i){
	            	if(booksIssued.get(i).isNotif()==true && booksIssued.get(i).isOverdue()==true){
	            		model.addRow(new String[]{booksIssued.get(i).getIssuedBook()});
	            	}
	            }
			}
		}
		catch(Exception e){
			
		}
		scrollPane.setViewportView(table);
	}
}

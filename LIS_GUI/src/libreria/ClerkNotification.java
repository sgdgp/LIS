package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.*;
public class ClerkNotification extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClerkNotification frame = new ClerkNotification();
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
	public ClerkNotification() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 338, 259);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
			Statement st = con.createStatement();
			String query = "Select * from books where delNotif=true";
			ResultSet r = st.executeQuery(query);
			String a = "";
			while(r.next()){
				a += "Delete book with ISBN = "+r.getString("ISBN")+"\n";
			}
			textArea.setText(a);
		}catch(Exception e){
			
		}
	
	}
}

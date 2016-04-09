package libreria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class DelBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblDeleteBook;
	private JLabel lblIsbn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DelBook frame = new DelBook();
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
	public DelBook() {
		setEnabled(false);
		setTitle("Dispose Book");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				LastScreen.screen1.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(120, 86, 261, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDisposeBook = new JButton("Dispose Book");
		btnDisposeBook.setIcon(new ImageIcon(DelBook.class.getResource("/libreria/delete.png")));
		btnDisposeBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					String ISBN = textField.getText().trim();
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis?useSSL=false", "root", "qwerty");
					con.createStatement().executeQuery("SET SQL_SAFE_UPDATES=0");
					System.out.println("line executed");
					String sql1 = "Select count(*) from books where ISBN='"+ISBN+"' and delNotif=true";
					Statement st = con.createStatement();
					ResultSet r = st.executeQuery(sql1);
					r.next();
					System.out.println(r.getInt(1));
					if(r.getInt(1)!=0){
						String sql = "Delete from books where ISBN='"+ISBN+"' and delNotif=true";
						System.out.println("if line executed");
						Statement stmt = con.createStatement();
						stmt.executeUpdate(sql);
					}
					else{
						System.out.println("not deleteds");
					}
				} catch (SQLException ex) {
					System.out.println("Exception reached");
//					e.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDisposeBook.setBounds(120, 185, 128, 30);
		contentPane.add(btnDisposeBook);
		
		lblDeleteBook = new JLabel("DISPOSE BOOK");
		lblDeleteBook.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 16));
		lblDeleteBook.setForeground(new Color(0, 100, 0));
		lblDeleteBook.setBounds(137, 30, 165, 22);
		contentPane.add(lblDeleteBook);
		
		lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblIsbn.setForeground(new Color(255, 140, 0));
		lblIsbn.setBounds(48, 88, 46, 14);
		contentPane.add(lblIsbn);
	}
}

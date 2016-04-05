package libreria;
//Displays list of all books in library
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class IssueStat extends JFrame {

	private JPanel contentPane;
	public String[][] data1= new String[100][100];
	
	private String url1 = "jdbc:mysql://localhost:3306/";
    private static String url = "jdbc:mysql://localhost:3306/lis";
    private static final String user = "root";
    private static final String password = "qwerty";
    private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DisplayAccountDetails frame = new DisplayAccountDetails();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public IssueStat() throws SQLException, IOException, ClassNotFoundException {
		setTitle("Issue Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 584, 300);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 548, 239);
		getContentPane().add(scrollPane);
		
		DefaultTableModel t = new DefaultTableModel()
				{
			boolean canEdit[]={false,false,false,true};
				};
		String[] columnNames = {"ISBN Number of  Book","Name of Book", "Times issued in last 5 years", "Dispose"};
		t.setColumnIdentifiers(columnNames);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		 table.setModel(t);
		JPanel panel = new JPanel();
		Statement stmt = null;
        Connection con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
		String add = "SELECT * FROM books ";
        ResultSet rs = stmt.executeQuery(add);
        String name[]=new String[10000];
        String ISBN[]=new String[10000];
        String issue[]=new String[10000];
        int i=0;
        table.getColumn("Dispose").setCellRenderer(new ButtonRenderer());
		table.getColumn("Dispose").setCellEditor(new ButtonEditor(new JCheckBox()));
		
        while (rs.next())
        {
        	ISBN[i]=rs.getString("ISBN");
        	name[i]=rs.getString("name");
        	issue[i]=Integer.toString(rs.getInt("issueStats"));
        	String a[]={ISBN[i],name[i],issue[i],"Dispose"};
        	t.addRow(a);
        }
      
        

	
		}
	
	}



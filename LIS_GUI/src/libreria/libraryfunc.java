
package libreria;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sayan
 */
public class libraryfunc {

    private String url1 = "jdbc:mysql://localhost:3306/";
    private static String url = "jdbc:mysql://localhost:3306/lis";
    private static final String user = "root";
    private static final String password = "qwerty";

    public libraryfunc() {
//        try {
//            Statement stmt = null;
//            Connection con = DriverManager.getConnection(url1, user, password);
//            stmt = con.createStatement();
//            String add = "SELECT * FROM lis";
//            ResultSet rs = stmt.executeQuery(add);
//            rs.next();
//            url = "jdbc:mysql://" + rs.getString("url") + ":3306/lis";
//        } catch (SQLException ex) {
//
//        }
    }

    //private static ArrayList<Book> bookList;
    //private static ArrayList<Member> memberList;
    public void statistics() {
        try {
			Connection con = DriverManager.getConnection(url+"lis", user, password);
			Statement s = con.createStatement();
			String sql = "Select * from books";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				String isbn = rs.getString("ISBN");
				String a = rs.getString("issueStats");
				System.out.println(isbn+"     "+a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public void print() {

    }

    public void checkReserve(String ISBN) throws ParseException {
        try {
            System.out.println("Check Reserve!");
            Statement stmt = null;
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
            ResultSet rs = stmt.executeQuery(add);
            Date date = new Date(0);
            if (rs.next()) {
                boolean isReserved = rs.getBoolean("isReserved");
                int onShelf = rs.getInt("onShelf");
                byte[] buf1 = rs.getBytes("reserveList");
                ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
                ArrayList<Integer> reservedList = (ArrayList<Integer>) o1.readObject();
                if (isReserved) {
                    if (onShelf > 0) {

                        byte[] buf = rs.getBytes("copyDetails");
                        ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
                        ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();
                        Iterator itr = copyDetails.iterator();
                        while (itr.hasNext()) {
                            BookInfo sb = (BookInfo) itr.next();
                            ArrayList<BookCopy> iss = (ArrayList<BookCopy>) sb.getIssuedMembers();
                            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                            if (!iss.get(iss.size() - 1).getReturnDate().equals("")) {
                                Date dd = sd.parse(iss.get(iss.size() - 1).getReturnDate());
                                if (dd.compareTo(date) > 0) {
                                    date = dd;
                                }
                            }
                        }
                    }
                }
                Date date1 = new Date();
                System.out.println(date.toString());
                System.out.println(date1.toString());
                if ((date1.getTime() - date.getTime()) >= 7 * 24 * 60 * 60 * 1000) {
                    reservedList.clear();
                    isReserved = false;
                    add = "UPDATE books SET reserveList = " + "?"
                            + ", isReserved = " + isReserved
                            + " WHERE ISBN = '" + ISBN + "'";
                    PreparedStatement pstmt = con.prepareStatement(add);
                    pstmt.setObject(1, reservedList);
                    pstmt.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(libraryfunc.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(libraryfunc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(libraryfunc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fine(String username ) {
        try {
            System.out.println("fine");
            int flag = 0;
            double fine = 0.0;
            String issueDate = "";
            String returnDate = "";
            Statement stmt = null;
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Success");
            stmt = con.createStatement();
            String add = "SELECT * FROM users WHERE username = '" + username+"'";
            ResultSet rs = stmt.executeQuery(add);
            rs.next();
            int duration = rs.getInt("duration");
            byte[] buf1 = rs.getBytes("booksIssued");
            ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
            ArrayList<UserIssueDetails> booksIssued = (ArrayList<UserIssueDetails>) o1.readObject();
            Iterator itr = booksIssued.iterator();
            System.out.println(username);

            while (itr.hasNext()) {
                UserIssueDetails iss = (UserIssueDetails) itr.next();
                issueDate = iss.getIssueDate();
                returnDate = iss.getReturnDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if (returnDate.equals("")) {
                    returnDate = sdf.format(Calendar.getInstance().getTime());
                }
                Date dIssue = sdf.parse(issueDate);
                Date dReturn = sdf.parse(returnDate);
                //System.out.println(dIssue.toString());
                //System.out.println(dReturn.toString());
                long diff = dReturn.getTime() - dIssue.getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                if (days > duration * 30) {
                    fine = fine + days - duration * 30;
                }

            }
            add = "UPDATE users SET fine = " + fine
                    + " WHERE username = '" +username+"'";
            PreparedStatement pstmt = con.prepareStatement(add);
            pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void informReservedMembers(String ISBN){
        try {
            int flag = 0;
            Statement stmt = null;
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Success");
            stmt = con.createStatement();
            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
            ResultSet rs = stmt.executeQuery(add);
            rs.next();
            boolean isReserved = rs.getBoolean("isReserved");
            int onShelf = rs.getInt("onShelf");
            byte[] buf1 = rs.getBytes("reserveList");
            ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
            ArrayList<Integer> reserveList = (ArrayList<Integer>) o1.readObject();
            System.out.println(reserveList.size());
            System.out.println("Success-info");
            Iterator itr = reserveList.iterator();
            while (itr.hasNext() && (onShelf > 0)) {
                System.out.println("Book Available for member username = " +itr.next() );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void penalty() {

    }

    @SuppressWarnings("unchecked")
	public void issue(String ISBN, String username){
    	
//    	try {
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lis", "root", "qwerty");
//			Statement st = con.createStatement();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	
    	
    	
    	
        try {
            int flag = 0;
            Statement stmt = null;
            Statement stmt1 = null;
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
            ResultSet rs = stmt.executeQuery(add);
            try {
                if (rs.next()) {
                	 boolean isReserved = rs.getBoolean("isReserved");
                     int onShelf = rs.getInt("onShelf");
                    
                     
                    int issueStats = rs.getInt("issueStats"); 
                    System.out.println(issueStats);
                    byte[] buf = rs.getBytes("copyDetails");
                    ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
                    ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();

                    byte[] buf1 = rs.getBytes("reserveList");
                    ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
                    ArrayList<String> reserveList = (ArrayList<String>) o1.readObject();

                    String mem = "SELECT * FROM users WHERE username = '" + username+"'";
                    ResultSet ms = stmt1.executeQuery(mem);
                    if (ms.next()) {

                        double fine = ms.getDouble("fine");
                        int bookLimit = ms.getInt("bookLimit");
                        byte[] buf2 = ms.getBytes("booksIssued");
                        ObjectInputStream o2 = new ObjectInputStream(new ByteArrayInputStream(buf2));
                        ArrayList<UserIssueDetails> booksIssued = (ArrayList<UserIssueDetails>) o2.readObject();

                        //if fine is not paid or max books issued
                        if (fine > 0) {
                            PopUpFrame pop = new PopUpFrame("Can't issue book! Fine due Rs." + fine);
                            pop.setVisible(true);
                            return;
                        }
                        int count = 0;
                        Iterator itr1 = booksIssued.iterator();
                        while (itr1.hasNext()) {
                            UserIssueDetails ism = (UserIssueDetails) itr1.next();
                            if (ism.getReturnDate().equals("")) {
                                count++;
                            }
                        }
                        if (count >= bookLimit) {
                            PopUpFrame pop = new PopUpFrame("Can't issue book! Book limit reached");
                            pop.setVisible(true);
                            return;
                        }

                        //for non-reserved books
                        if (isReserved == false) {
                            if (onShelf > 0) {
                                Iterator itr = copyDetails.iterator();
                                
                                while (itr.hasNext()) {
                                	
                                    BookInfo sb = (BookInfo) itr.next();
                                    if (!sb.isIsIssued()) {
                                        onShelf--;
                                        itr.remove();
                                        sb.setIsIssued(true);
                                        ArrayList<BookCopy> issuedMembers = sb.getIssuedMembers();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        String date = sdf.format(Calendar.getInstance().getTime());
                                        BookCopy temp = new BookCopy(username, date);
                                        issuedMembers.add(temp);
                                        sb.setIssuedMembers(issuedMembers);
                                        PopUpFrame pop = new PopUpFrame("Book Issued!");
                                        issueStats++;
                                        pop.setVisible(true);
                                        UserIssueDetails memtemp = new UserIssueDetails(ISBN, date);
                                        booksIssued.add(memtemp);
                                        copyDetails.add(sb);
                                        
                                        break;
                                    }
                                    
                                }

                            } else {
                                PopUpFrame pop = new PopUpFrame("Book not on shelf!");
                                pop.setVisible(true);
                            }
                        }

                        //if book is reserved
                        boolean member_exists = false;
                        if (isReserved == true) {
                            if (onShelf > 0) {
                                Iterator iter = reserveList.iterator();
                                while (iter.hasNext()) {
                                   String x = (String) iter.next();
                                    if (x.equals(username)) {
                                        member_exists = true;
                                        iter.remove();
                                        break;
                                    }
                                }

                                if (!member_exists) {
                                    PopUpFrame pop = new PopUpFrame("Book reserved! Can't be issued out!");
                                    pop.setVisible(true);
                                    return;
                                }

                                Iterator itr = copyDetails.iterator();

                                while (itr.hasNext()) {
                                    BookInfo sb = (BookInfo) itr.next();
                                    if (!sb.isIsIssued()) {
                                        onShelf--;
                                        itr.remove();
                                        sb.setIsIssued(true);
                                        ArrayList<BookCopy> issuedMembers = sb.getIssuedMembers();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        String date = sdf.format(Calendar.getInstance().getTime());
                                        BookCopy temp = new BookCopy(username, date);
                                        issuedMembers.add(temp);
                                        sb.setIssuedMembers(issuedMembers);
                                        if (reserveList.isEmpty()) {
                                            isReserved = false;
                                        }

                                        PopUpFrame pop = new PopUpFrame("Book Issued!");
                                        pop.setVisible(true);
                                        issueStats++;
                                        UserIssueDetails memtemp = new UserIssueDetails(ISBN, date);
                                        booksIssued.add(memtemp);
                                        copyDetails.add(sb);
                                        break;
                                    }
                                }

                            } else {
                                PopUpFrame pop = new PopUpFrame("Book still not on shelf!");
                                pop.setVisible(true);
                            }
                        }

                        add = "UPDATE books SET reserveList = " + "?"
                        		+ ", issueStats = " + "?"
                                + ", isReserved = " + isReserved
                                + ", copyDetails = " + "?"
                                + ", onShelf = " + onShelf
                                + " WHERE ISBN = '" + ISBN + "'";
                        PreparedStatement pstmt = con.prepareStatement(add);
                        System.out.println(issueStats);
                        pstmt.setInt(2, issueStats);
                        pstmt.setObject(1, reserveList);
                        pstmt.setObject(3, copyDetails);
                        pstmt.executeUpdate();
                        //stmt.executeUpdate(add);

                        mem = "UPDATE users SET booksIssued = " + "?" + " WHERE username = '" + username+"'";
                        PreparedStatement pstmt1 = con.prepareStatement(mem);
                        //pstmt1 = con.prepareStatement(mem);
                        pstmt1.setObject(1, booksIssued);
                        pstmt1.executeUpdate();
                        //stmt.executeUpdate(mem);
                        ms.close();
                    } else {
                        PopUpFrame pop = new PopUpFrame("Inavalid Friend ID!");
                        pop.setVisible(true);
                    }
                    rs.close();
                } else {
                    PopUpFrame pop = new PopUpFrame("ISBN doesn't exist!");
                    pop.setVisible(true);
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(libraryfunc.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (SQLException ex) {
            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void returned(String ISBN, String username) {

        try {
            int ID = 0;
            int flag = 0;
            
            Statement stmt = null;
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Success");
            stmt = con.createStatement();
            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
            ResultSet rs = stmt.executeQuery(add);
            if (rs.next()) {
                boolean isReserved = rs.getBoolean("isReserved");
                int onShelf = rs.getInt("onShelf");
                byte[] buf = rs.getBytes("copyDetails");
                ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
                ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();

                byte[] buf1 = rs.getBytes("reserveList");
                ObjectInputStream o1 = new ObjectInputStream(new ByteArrayInputStream(buf1));
                ArrayList<Integer> reserveList = (ArrayList<Integer>) o1.readObject();
//                Iterator itr1 = copyDetails.iterator();
//                while (itr1.hasNext()) {
//                    BookInfo sb = (BookInfo) itr1.next();
//                    if (sb.getIssuedMembers().size() > 0) {
//                        if (sb.getID() == subID) {
//                            username = sb.getIssuedMembers().get(sb.getIssuedMembers().size() - 1).getIssueMember();
//                        }
//                    }
//                }
//                System.out.println(ID);
                String mem = "SELECT * FROM users WHERE username = '" + username+"'";
                ResultSet ms = stmt.executeQuery(mem);
                if (ms.next()) {
                    double fine = ms.getDouble("fine");
                    int bookLimit = ms.getInt("bookLimit");
                    byte[] buf2 = ms.getBytes("booksIssued");
                    ObjectInputStream o2 = new ObjectInputStream(new ByteArrayInputStream(buf2));
                    ArrayList<UserIssueDetails> booksIssued = (ArrayList<UserIssueDetails>) o2.readObject();

                    Iterator itr = copyDetails.iterator();
                    while (itr.hasNext() && flag == 0) {
                        BookInfo sb = (BookInfo) itr.next();
                        if ( sb.isIsIssued()) {
                            Iterator iter = sb.getIssuedMembers().iterator();
                            while (iter.hasNext()) {
                                BookCopy iss = (BookCopy) iter.next();
                                if (iss.getIssueMember().equals(username) && iss.getReturnDate().equalsIgnoreCase("")) {
                                    iter.remove();
                                    itr.remove();
                                    flag = 1;
                                    sb.setIsIssued(false);
                                    onShelf++;
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    String date = sdf.format(Calendar.getInstance().getTime());
                                    iss.setReturnDate(date);
                                    ArrayList<BookCopy> isbk = sb.getIssuedMembers();
                                    isbk.add(iss);
                                    sb.setIssuedMembers(isbk);
                                    copyDetails.add(sb);

                                    Iterator itrr = booksIssued.iterator();
                                    while (itrr.hasNext()) {
                                        UserIssueDetails memb = (UserIssueDetails) itrr.next();
                                        if (memb.getIssuedBook().equals(ISBN) && memb.getReturnDate().equalsIgnoreCase("")) {
                                            itrr.remove();
                                            memb.setReturnDate(date);
                                            booksIssued.add(memb);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // TODO inform reserved members
                    add = "UPDATE books SET reserveList = " + "?"
                            + ", isReserved = " + isReserved
                            + ", copyDetails = " + "?"
                            + ", onShelf = " + onShelf
                            + " WHERE ISBN = '" + ISBN + "'";
                    PreparedStatement pstmt = con.prepareStatement(add);
                    pstmt.setObject(1, reserveList);
                    pstmt.setObject(2, copyDetails);
                    pstmt.executeUpdate();
                    //stmt.executeUpdate(add);

                    mem = "UPDATE users SET booksIssued = " + "?" + " WHERE username = '" + username+"'";
                    pstmt = con.prepareStatement(mem);
                    pstmt.setObject(1, booksIssued);
                    pstmt.executeUpdate();
                    //stmt.executeUpdate(mem);
                    if (flag == 1) {
                        PopUpFrame pop = new PopUpFrame("Book returned!");
                        pop.setVisible(true);
                        if (isReserved == true) {
                            libraryfunc.informReservedMembers(ISBN);
                        }
                    } else {
                        PopUpFrame pop = new PopUpFrame("Book ID invalid!");
                        pop.setVisible(true);
                    }
                    ms.close();
                } else {
                    PopUpFrame pop = new PopUpFrame("Book ID invalid!");
                    pop.setVisible(true);
                }

                rs.close();
            } else {
                PopUpFrame pop = new PopUpFrame("ISBN invalid!");
                pop.setVisible(true);
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings({ "unchecked", "unused" })
	public void reserve(String ISBN, String username){
        try {
            int flag = 0;
            Statement stmt = null;
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Success");
            stmt = con.createStatement();
            String add0 = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs0 = stmt.executeQuery(add0);
            rs0.next();
            byte[] buf0 = rs0.getBytes("booksIssued");
            ObjectInputStream o0 = new ObjectInputStream(new ByteArrayInputStream(buf0));
            ArrayList<UserIssueDetails> bookList = (ArrayList<UserIssueDetails>) o0.readObject();
            for(int i=0;i<bookList.size();i++){
            	if(bookList.get(i).getIssuedBook().equals(ISBN)){
            		PopUpFrame pop = new PopUpFrame("Book already issued by you!");
                    pop.setVisible(true);
                    return;
            	}
            		
            }
            
            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
            ResultSet rs = stmt.executeQuery(add);
            rs.next();
            boolean isReserved = rs.getBoolean("isReserved");
            isReserved = true;
            System.out.println("is reserved set to true");
            byte[] buf = rs.getBytes("reserveList");
            ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
            ArrayList<String> reserveList = (ArrayList<String>) o.readObject();
//            Iterator itr = reserveList.iterator();
//            while (itr.hasNext()) {
//                String sb = (String) itr.next();
//                if (sb.equals(username)) {
//                    flag = 1;
//                    break;
//                }
//            }
//            if (flag == 0) {
            if(reserveList.contains(username)){
            	PopUpFrame pop = new PopUpFrame("Book already reserved under this username!");
                pop.setVisible(true);
                return;
            }
            
            reserveList.add(username);
//            }
            rs.close();
            
            System.out.println("Resrve list of books table updated");
            add = "UPDATE books SET reserveList = " + "?"
                    + ", isReserved = " + isReserved
                    + " WHERE ISBN = '" + ISBN + "'";
            PreparedStatement pstmt = con.prepareStatement(add);
            pstmt.setObject(1, reserveList);
            pstmt.executeUpdate();
            System.out.println("Table updated");
            //stmt.executeUpdate(add);
            PopUpFrame pop = new PopUpFrame("Book Reserved!");
            pop.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

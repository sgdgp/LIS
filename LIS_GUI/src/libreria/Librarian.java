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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Librarian {
	 private String url1 = "jdbc:mysql://localhost:3306/ip";
	    private static String url = "jdbc:mysql://localhost:3306/library";
	    private static final String user = "root";
	    private static final String password = "qwerty";

	    public Librarian() {
	        try {
	            Statement stmt = null;
	            Connection con = DriverManager.getConnection(url1, user, password);
	            stmt = con.createStatement();
	            String add = "SELECT * FROM ip";
	            ResultSet rs = stmt.executeQuery(add);
	            rs.next();
	            url = "jdbc:mysql://" + rs.getString("url") + ":3306/library";
	        } catch (SQLException ex) {

	        }
	    }

	    // TODO :  Modify records
	    public void create_record(String memberType, int ID, String name, String phoneNo, String address, String passWd) {
	        //mbr = new Member(memberType, ID, name, phoneNo, address);
	        ArrayList<UserIssueDetails> issue_member = new ArrayList<>();
	        int bookLimit = 0;
	        int duration = 0;
	        if (memberType == "underGraduate") {
	            bookLimit = 2;
	            duration = 1;
	        } else if (memberType == "postGraduate") {
	            bookLimit = 4;
	            duration = 1;
	        } else if (memberType == "researchScholars") {
	            bookLimit = 6;
	            duration = 3;
	        } else if (memberType == "faculty") {
	            bookLimit = 10;
	            duration = 6;
	        }
	        try {
	            Statement stmt = null;

	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Success");
	            //stmt = con.createStatement();
	            System.out.println("Hello");
	            String add = "INSERT into members VALUES ('"
	                    + memberType.toString() + "', "
	                    + ID + ", '"
	                    + name + "', '"
	                    + phoneNo + "', '"
	                    + address + "', "
	                    + 0.0 + ", "
	                    + bookLimit + ", "
	                    + duration + ", "
	                    + "?, '"
	                    + passWd + "')";
	            PreparedStatement pstmt = con.prepareStatement(add);
	            pstmt.setObject(1, issue_member);
	            pstmt.executeUpdate();
	            //stmt.executeUpdate(add);
	            PopUpFrame pop = new PopUpFrame("<html>Member record added! Member ID is " + ID
	                    + "<br> Your password is " + passWd);
	            pop.setVisible(true);
	        } catch (SQLException ex) {
	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

	    public void delete_record(int ID) throws IOException, ClassNotFoundException {
	        try {
	            Statement stmt = null;
	            boolean flag = false;
	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Success");

	            stmt = con.createStatement();
	            String add = "SELECT * FROM members WHERE ID = " + ID;
	            ResultSet rs = stmt.executeQuery(add);
	            rs.next();
	            byte[] buf = rs.getBytes("booksIssued");
	            ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));

	            ArrayList<UserIssueDetails> bookIssued = (ArrayList<UserIssueDetails>) o.readObject();
	            Iterator itr = bookIssued.iterator();
	            while (itr.hasNext()) {
	                UserIssueDetails mem = (UserIssueDetails) itr.next();
	                if (mem.getReturnDate().equalsIgnoreCase("")) {
	                    flag = true;
	                }
	            }
	            if (!flag) {
	                add = "DELETE from members WHERE ID = " + ID;
	                stmt.executeUpdate(add);

	                PopUpFrame pop = new PopUpFrame("Member record deleted!");
	                pop.setVisible(true);
	            } else {
	                PopUpFrame pop = new PopUpFrame("Member has issued a book, can't be removed!");
	                pop.setVisible(true);

	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

//	    public void create_book(String ISBN, String name, String publisher, int yearOfPurchase, int rackNo, double price, int copies) {
//	        ArrayList<BookInfo> copyDetails = new ArrayList<>();
//	        ArrayList<Integer> reserveList = new ArrayList<>();
//	        for (int i = 0; i < copies; i++) {
//	            BookInfo newCopy = new BookInfo(i + 1, false);
//	            copyDetails.add(newCopy);
//	        }
//	        try {
//	            //book = new Book(ISBN, name, publisher, yearOfPurchase, rackNo, price);
//	            Statement stmt = null;
//
//	            Connection con = DriverManager.getConnection(url, user, password);
//	            System.out.println("Success");
//	            stmt = con.createStatement();
//	            String add = "INSERT into books VALUES ('"
//	                    + ISBN + "', '"
//	                    + name + "', '"
//	                    + publisher + "', "
//	                    + yearOfPurchase + ", "
//	                    + rackNo + ", "
//	                    + copies + ", "
//	                    + (copies + 1) + ", "
//	                    + price + ", "
//	                    + false + ", "
//	                    + "?" + ", "
//	                    + "?" + ")";
//	            PreparedStatement statement = con.prepareStatement(add);
//	            statement.setObject(1, copyDetails);
//	            statement.setObject(2, reserveList);
//	            statement.executeUpdate();
//	            //stmt.executeUpdate(add);
//	            PopUpFrame pop = new PopUpFrame("Book record successfully created!");
//	            pop.setVisible(true);
//
//	        } catch (SQLException ex) {
//	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//
//	    }

//	    public void delete_book(String ISBN) throws IOException, ClassNotFoundException {
//	        try {
//	            Statement stmt = null;
//
//	            Connection con = DriverManager.getConnection(url, user, password);
//	            System.out.println("Success");
//	            stmt = con.createStatement();
//	            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
//	            ResultSet rs = stmt.executeQuery(add);
//	            rs.next();
//	            int onShelf = rs.getInt("onShelf");
//	            byte[] buf = rs.getBytes("copyDetails");
//	            ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
//
//	            ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();
//	            if (onShelf == copyDetails.size()) {
//	                add = "DELETE from books WHERE ISBN = '" + ISBN + "'";
//	                stmt.executeUpdate(add);
//	                PopUpFrame pop = new PopUpFrame("Book Deleted!");
//	                pop.setVisible(true);
//	            }
//
//	        } catch (SQLException ex) {
//	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//	    }

//	    public void create_BookInfo(String ISBN, int copies) throws IOException, ClassNotFoundException {
//	        try {
//	            Statement stmt = null;
//
//	            Connection con = DriverManager.getConnection(url, user, password);
//	            System.out.println("Success");
//	            stmt = con.createStatement();
//	            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
//	            ResultSet rs = stmt.executeQuery(add);
//	            rs.next();
//	            int onShelf = rs.getInt("onShelf");
//	            boolean isReserved = rs.getBoolean("isReserved");
//	            byte[] buf = rs.getBytes("copyDetails");
//	            ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
//
//	            ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();
//	            System.out.println(onShelf);
//
//	            int countID = rs.getInt("countID");
//	            for (int i = 0; i < copies; i++) {
//	                BookInfo newCopy = new BookInfo(countID + i, false);
//	                copyDetails.add(newCopy);
//	            }
//	            countID = countID + copies;
//	            onShelf = onShelf + copies;
//	            rs.close();
//
//	            if (isReserved) {
//	                libraryfunc ls = new libraryfunc();
//	                ls.informReservedMembers(ISBN);
//	            }
//
//	            add = "UPDATE books SET countID = " + countID
//	                    + ", copyDetails = " + "?"
//	                    + ", onShelf = " + onShelf
//	                    + " WHERE ISBN = '" + ISBN + "'";
//	            PreparedStatement statement = con.prepareStatement(add);
//	            statement.setObject(1, copyDetails);
//	            statement.executeUpdate();
//	            //stmt.executeUpdate(add);
//	            PopUpFrame pop = new PopUpFrame("Books added!");
//	            pop.setVisible(true);
//	        } catch (SQLException ex) {
//	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//	    }

//	    public void delete_BookInfo(String ISBN, int ID) throws IOException, ClassNotFoundException {
//	        try {
//	            boolean flag = false;
//	            boolean flag1 = false;
//	            Statement stmt = null;
//	            Connection con = DriverManager.getConnection(url, user, password);
//	            System.out.println("Success");
//	            stmt = con.createStatement();
//	            String add = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
//	            ResultSet rs = stmt.executeQuery(add);
//	            rs.next();
//	            int onShelf = rs.getInt("onShelf");
//	            byte[] buf = rs.getBytes("copyDetails");
//	            ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(buf));
//
//	            ArrayList<BookInfo> copyDetails = (ArrayList<BookInfo>) o.readObject();
//	            Iterator itr = copyDetails.iterator();
//	            while (itr.hasNext()) {
//	                BookInfo sb = (BookInfo) itr.next();
//	                if (sb.getID() == ID) {
//	                    flag = true;
//	                    if (sb.isIsIssued() == false) {
//	                        onShelf--;
//	                        flag1 = true;
//	                        itr.remove();
//	                        break;
//	                    }
//	                }
//	            }
//	            rs.close();
//	            add = "UPDATE books SET copyDetails = " + "?"
//	                    + ", onShelf = " + onShelf
//	                    + " WHERE ISBN = '" + ISBN + "'";
//	            PreparedStatement statement = con.prepareStatement(add);
//	            statement.setObject(1, copyDetails);
//	            statement.executeUpdate();
//	            //stmt.executeUpdate(add);
//	            if (flag1 == true) {
//	                PopUpFrame pop = new PopUpFrame("Book deleted!");
//	                pop.setVisible(true);
//	            } else if (flag == true && flag1 == false) {
//	                PopUpFrame pop = new PopUpFrame("Book issued out, can't be deleted!");
//	                pop.setVisible(true);
//	            } else {
//	                PopUpFrame pop = new PopUpFrame("Invalid ID!");
//	                pop.setVisible(true);
//	            }
//	        } catch (SQLException ex) {
//	            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//	    }

	    public void modify_book(String ISBN, String name, String publisher, int yearOfPurchase, int rackNo, double price) {
	        try {
	            Statement stmt = null;

	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Success");
	            stmt = con.createStatement();

	            String add = "UPDATE books SET name = '" + name
	                    + "', publisher = '" + publisher
	                    + "', yearOfPurchase = " + yearOfPurchase
	                    + ", rackNo = " + rackNo
	                    + ", price = " + price
	                    + " WHERE ISBN = '" + ISBN + "'";
	            stmt.executeUpdate(add);
	            PopUpFrame pop = new PopUpFrame("Changes saved!");
	            pop.setVisible(true);
	        } catch (Exception e) {

	        }
	    }

	    public void modify_member(int ID, String address, String phNo) {
	        try {
	            Statement stmt = null;

	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Success");
	            stmt = con.createStatement();

	            String add = "UPDATE members SET address = '" + address
	                    + "', phoneNo = '" + phNo
	                    + "' WHERE ID = " + ID;
	            stmt.executeUpdate(add);
	            PopUpFrame pop = new PopUpFrame("Changes saved!");
	            pop.setVisible(true);
	        } catch (Exception e) {

	        }
	    }
}

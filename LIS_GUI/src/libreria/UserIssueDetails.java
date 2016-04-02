
package libreria;

import java.io.Serializable;




 
public class UserIssueDetails implements Serializable{
    private String issuedBook;
    private String issueDate;
    private String returnDate;

    public UserIssueDetails(String issuedBook, String issueDate) {
        this.issuedBook = issuedBook;
        this.issueDate = issueDate;
        this.returnDate = "";
    }
    
    
    public String getIssuedBook() {
        return issuedBook;
    }

    public void setIssuedBook(String issuedBook) {
        this.issuedBook = issuedBook;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
    
}

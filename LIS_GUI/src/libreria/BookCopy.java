package libreria;

import java.io.Serializable;

class BookCopy implements Serializable{
    private String issueMember;
    private String issueDate;
    private String returnDate;

    public BookCopy(String issueMember, String issueDate) {
        this.issueMember = issueMember;
        this.issueDate = issueDate;
        this.returnDate="";
    }

    public String getIssueMember() {
        return issueMember;
    }

    public void setIssueMember(String issueMember) {
        this.issueMember = issueMember;
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

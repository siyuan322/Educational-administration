package model;

public class Book {
    private int bookId;
    private int cid;
    private String bookName;
    private String bookAuthor;
    private String publishing;
    private String imgPath;

    public Book() {
    }

    public Book(int bookId, int cid, String bookName, String bookAuthor, String publishing, String imgPath) {
        this.bookId = bookId;
        this.cid = cid;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publishing = publishing;
        this.imgPath = imgPath;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", cid=" + cid +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishing='" + publishing + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}

// Dorcas Kumbu Buthidi 40224424
// COMP249
// Assignment 4
// Due Date: April 17, 2023 

public class Book {
    private String title;
    private String author;
    private double price;
    private String isbn;
    private String genre;
    private int year;

    /**
     * full constructor
     * 
     * @param title title of record
     * @param author name of author
     * @param price price of record
     * @param isbn isbn of record
     * @param genre genre of record 
     * @param year release year of record 
     */
    public Book(String title, String author, double price, String isbn, String genre, int year){
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

     /**
     * get the title 
     * 
     * @return
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * get the author
     * 
     * @return
     */
    public String getauthor(){
        return this.author;
    }

    /**
     * get the price
     * 
     * @return
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * get the isbn
     * 
     * @return
     */
    public String getISBN(){
        return this.isbn;
    }

    /**
     * get the genre
     * 
     * @return
     */
    public String getGenre(){
        return this.genre;
    }

    /**
     * get the year
     * 
     * @return
     */
    public int getYear(){
        return this.year;
    }

    /**
     * set title 
     * 
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

     /**
     * set author 
     * 
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }

     /**
     * set price
     * 
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
    }

     /**
     * set isbn
     * 
     * @param isbn
     */
    public void setISBN(String isbn){
        this.isbn = isbn;
    }

     /**
     * set genre
     * 
     * @param genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }

     /**
     * set year
     * 
     * @param year
     */
    public void setYear(int year){
        this.year = year;
    }

    public String toString(){
        return this.title + "," + this.author + "," + this.price + "," + this.isbn + "," + this.genre + "," + this.year;
    }

    public boolean equals(Object o){
        if(o==null || !(o instanceof Book)){
            return false;
        } else {
            Book other = (Book) o;
            return title == other.title && author == other.author && price == other.price && isbn == other.isbn && genre == other.genre && year == other.year;
        }
    }
}

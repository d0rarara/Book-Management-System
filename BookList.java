import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookList {
    
    private class Node{
        // two private attributes 
        private Book b;
        private Node next;

        public Node(){
            b = null;
            next = null;
        }

        public Node(Book bData, Node nextNode){
            b = bData;
            next = nextNode;
        }

        public Book getBook(){
            return this.b;
        }

        public Node getNext(){
            return this.next;
        }

        public void setBook(Book b){
            this.b = b;
        }

        public void setNext(Node next){
            this.next = next;
        }
    }

    // one attribute called head 
    // points to the first node of a BookList object
    private Node head; 

    /**
     * default constructor 
     * initializes head to null 
     */
    public BookList(){
        this.head = null;
    }

    /**
     * adds a Node with the passed Book object at the start (head) of the list
     * 
     * @param b
     */
    public void addToStart(Book b){
        // if the list is empty
        if (head == null)
        {
            // create new node whos "next" attribute points to itself
            head = new Node(b, null);

            head.next = head;

            return;
        }

        // link the new node to the head
        Node newNode = new Node(b, head);

        // find the last node 
        Node current = head;
        while (current.next != head) 
        {
            current = current.next;
        } // when this loop finishes, current will point to the last node in the list

        // link the last node of the list to the new node
        current.next = newNode;

        // change head to our new node
        head = newNode;
    }

    /**
     * finds all book records based on given year and stores them in proper yr.txt file
     * 
     * @param yr
     */
    public void storeRecordsByYear(int yr){

        // delete [yr].txt file if it exists
        String filename = Integer.toString(yr) + ".txt";
        File fileObject = new File(filename);
        if (fileObject.exists()){
            fileObject.delete();
        }

        Node current = head;

        try 
        {
            // open text file 
            FileWriter myWriter = new FileWriter(filename);
            while (true) {
                if (current.b.getYear() == yr) 
                {
                    // write current.b to [yr].txt
                    myWriter.write(current.b.toString() + "\n");
                }

                // move our current pointer to the next node in the list
                current = current.next;
                
                // Break the loop when we get back to the head
                if (current.next == head){
                    break;
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public boolean insertBefore(long isbn, Book b)
    {
        Node current = head;
        while (true)
        {
            // check if the next node matches the passed isbn parameter
            if (current.next.b.getISBN() == isbn)
            {
                // insert new node before current.next
                Node newNode = new Node(b, current.next);

                // and after current
                current.next = newNode;

                return true;
            }

            // shift to the next node
            current = current.next;

            // if we get to the end of the list without matching isbn, return false
            if (current.next == head){
                return false;
            }
        }
    }

    //  ----------------------------------------
    // TODO
    // ---------------------------------------

    public boolean insertBetween(long isbn1, long isbn2, Book b)
    {
        return false;
    }

    public void displayContent()
    {
        Node current = head;

        // print out first node
        System.out.println(current.b.toString());
        while (current.next != head) {
            current = current.next;
            System.out.println(current.b.toString());
        }
    }

    //  ----------------------------------------
    // TODO
    // ---------------------------------------
    public boolean delConsecutiveRepeatedRecords()
    {
        return false;
    }
        
    public BookList extractAuthList(String aut)
    {
        return null;
    }

    public boolean swap(long isbn1, long isbn2)
    {
        return false;
    }

    public void commit()
    {

    }
}

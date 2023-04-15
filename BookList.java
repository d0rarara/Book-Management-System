import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookList {
    
    private class Node{
        // two private attributes 
        private Book b;
        private Node next;

        // public Node(){
        //     b = null;
        //     next = null;
        // }

        public Node(Book bData, Node nextNode){
            b = bData;
            next = nextNode;
        }

        // public Book getBook(){
        //     return this.b;
        // }

        // public Node getNext(){
        //     return this.next;
        // }

        // public void setBook(Book b){
        //     this.b = b;
        // }

        // public void setNext(Node next){
        //     this.next = next;
        // }
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
     * @param b book object
     */
    public void addToStart(Book b){
        // if the list is empty
        if (head == null)
        {
            // create new node whose "next" attribute points to itself
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
            // when this loop finishes, current will point to the last node in the list
            current = current.next;
        } 

        // link the last node of the list to the new node
        current.next = newNode;

        // change head to new node
        head = newNode;
    }

    /**
     * finds all book records based on given year and stores them in proper yr.txt file
     * 
     * @param yr year of book record
     */
    public void storeRecordsByYear(int yr){

        // delete [yr].txt file if it exists
        String filename = Integer.toString(yr) + ".txt";
        File fileObject = new File(filename);
        if (fileObject.exists()){
            fileObject.delete();
        }

        Node current = head;

        
        while (true) 
        { 
            // SHOULD NOT CREATE FILE IF YEAR NOT FOUND
            FileWriter myWriter = null;
            try
            {   
                if (current.b.getYear() == yr) 
                {
                    // open text file 
                    myWriter = new FileWriter(filename, true);

                    // write current.b to [yr].txt
                    myWriter.write(current.b.toString() + "\n");
                    myWriter.close();
                }
                    
        
                // move our current pointer to the next node in the list
                current = current.next;
                        
                // Break the loop when we get back to the head
                if (current.next == head){
                    break;
                }
            } 
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    /**
     * method searches list for first occurence of Node holding Book object with ISBN equal
     * to passed isbn value
     * 
     * if found, new node will be inserted in the list (holding Book b) before the aforementioned node
     * 
     * @param isbn isbn value of book record
     * @param b book object
     * @return true if node is found 
     */
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

    /**
     * method searches list for first occurence of first two consecutive nodes holding Book objects with ISBN values
     * equal to passed isbn1 and isbn2
     * 
     * if found, new node will be inserted in the list (holding Book b) in between the aforementioned nodes
     * 
     * @param isbn1 first isbn value of book record
     * @param isbn2 second isbn value of book record
     * @param b book record
     * @return true if nodes are found
     */
    public boolean insertBetween(long isbn1, long isbn2, Book b)
    {
        Node current = head;
        while (true)
        {
            // check if the next two nodes matches the passed isbn parameters
            if (current.b.getISBN() == isbn2 && current.next.b.getISBN() == isbn1)
            {
                // insert new node before current.next
                Node newNode = new Node(b, current.next);

                // and after current.next
                current.next = newNode;

                return true;
            }

            // shift to the next node
            current = current.next;

            // if we get to the end of the list without matching isbn, return false
            if (current == head || current.next == head ){
                return false;
            }
        }
    }

    public void displayContent()
    {
        Node current = head;

        // print out first node
        System.out.println(current.b.toString() + " ==> ");
        while (current.next != head) {
            current = current.next;
            System.out.println(current.b.toString() + " ==> ");
        }
        System.out.println(" ==> head");
    }

    //  ----------------------------------------
    // TODO
    // ---------------------------------------

    /**
     * method finds all consecutive repeated nodes, each having the same Book record and deletes them 
     * 
     * @return false if 
     */
    public boolean delConsecutiveRepeatedRecords()
    {
        Node current = head;

        boolean didDelete = false;

        while(true)
        {
            // check if the current node is equal to the next node
            if(current.b.equals(current.next.b)){
                // SPECIAL CASE: if node to be deleted is the head, shift the head to the following node
                if (current.next == head)
                {
                    head = current.next.next;
                }

                // delete repeated book node from the linked list
                current.next = current.next.next;

                // remember that i deleted a node
                didDelete = true;
            }
            else{
                // only move on to the next node if i didn't delete a consecutive node
                current = current.next;

                // break from the loop when i get back to the head
                if (current == head) {
                    break;
                }
            }
        }
        return didDelete;
    }
        
    public BookList extractAuthList(String aut)
    {
        BookList authList = new BookList();
        Node current = head;

        while(true)
        {
            if (current.b.getAuthor().equals(aut))
            {
                authList.addToStart(current.b);
            }

            current = current.next;

            if (current == head)
            {
                break;
            }
        }

        return authList;
    }

    public boolean swap(long isbn1, long isbn2)
    {
        // find last node
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        // Search for isbn1 (keep track of beforeISBN1Node and current1)
        Node beforeISBN1Node = last, current1 = head;
        
        // loop through list until current1 matches isbn1 or we get back to head
        while (current1.b.getISBN() != isbn1) {
            beforeISBN1Node = current1;
            current1 = current1.next;

            // if i get back to head without a match, return false
            if (current1.next == head) {
                return false;
            }
        }
 
        // loop through list until current2 matches isbn2 or we get back to head
        Node beforeISBN2Node = last, current2 = head;
        while (current2.b.getISBN() != isbn2) {
            beforeISBN2Node = current2;
            current2 = current2.next;

            // if i get back to head without a match, return false
            if (current2.next == head) {
                return false;
            }
        }
 
 
        // swap node positions
        beforeISBN1Node.next = current2;
        beforeISBN2Node.next = current1;
 
        // Swap next pointers
        Node temp = current1.next;
        current1.next = current2.next;
        current2.next = temp;

        // switch the head if one was the head
        if (current1 == head){
            head = current2;
        }
        else if (current2 == head){
            head = current1;
        }

        return true;
    }

    public void commit()
    {

    }
}

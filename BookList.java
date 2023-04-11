public class BookList {
    
    private class Node{
        // two private attributes 
        Book b;
        Book next;
    }

    Node head;
    // one attribute called head 
    // points to the first node of a BookList object

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

    }


}

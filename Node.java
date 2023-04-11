public class Node {
    private Book b;
    private Book next;

    public Node(Book b, Book next){
        this.b = b;
        this.next = next;
    }

    public Book getB(){
        return this.b;
    }

    public Book getNext(){
        return this.next;
    }

    public void setB(Book b){
        this.b = b;
    }

    public void setNext(Book next){
        this.next = next;
    }

    public String toString(){
        return this.b + "," + this.next;
    }

    public boolean equals(Object o){
        if(o==null || !(o instanceof Node)){
            return false;
        } else {
            Node other = (Node) o;
            return b == other.b && next == other.next;
        }
    }
}

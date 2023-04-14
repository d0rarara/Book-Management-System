import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Book getBookFromInput(Scanner sc)
    {
        System.out.println("Enter book title");
        String title = sc.nextLine();

        // etc

        Book b = new Book(title, ...);
    }

    public static void main(String[] args){
        // create arrLst and bkLst
        ArrayList<Book> arrLst = new ArrayList<Book>();
        BookList bkLst = new BookList();

        System.out.println("Opening Books.txt");
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File("Books.txt"));

            while(sc.hasNext())
            {
                String line = sc.nextLine();
                String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                // create book object
                Book obj;
                obj = new Book(splitted[0], splitted[1], Double.parseDouble(splitted[2]), Long.parseLong(splitted[3]), splitted[4], Integer.parseInt(splitted[5]));
                // if year > 2024 add to arrLst
                if(obj.getYear() > 2024)
                {
                    arrLst.add(obj);
                }
                // else add to bkLst
                else
                {
                    bkLst.addToStart(obj);
                }  
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: file not found");
            System.exit(0);
        }

        // Record the contents of arrLst into a file called YearErr.txt

        // only write to file if there are elements in arrLst
        if (!arrLst.isEmpty())
        {
            try {
                File fileObject = new File("YearErr.txt");
                if (fileObject.exists()){
                    fileObject.delete();
                }
                FileWriter myWriter = new FileWriter("YearErr.txt");

                // write all books in arrLst to the file
                for (int i = 0; i < arrLst.size(); i++)
                {
                    myWriter.write(arrLst.get(i).toString() + "\n");
                }

                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        // Display the contents of the bkLst List.
        bkLst.displayContent();

        // bkLst.insertBefore(1557835659, new Book());

        // bkLst.displayContent();

        // display the menu
        System.out.println("1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
        System.out.println("2) Ask me to delete all consecutive repeated records;");
        System.out.println("3) Give me an author name and I will create a new list with the records of this author and display them;");
        System.out.println("4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;");
        System.out.println("5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
        System.out.println("6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
        System.out.println("7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
        System.out.println("8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");

        Scanner inputScanner = new Scanner(System.in); 

        while (true)
        {
            String inp = inputScanner.nextLine();
            if (inp == "1")
            {
                System.out.println("Enter year (4 digits):");
                bkLst.storeRecordsByYear(inputScanner.nextInt());
                inputScanner.nextLine();
            }
            else if (inp == "2")
            {
                bkLst.delConsecutiveRepeatedRecords();
            }
            else if (inp == "3")
            {
                System.out.println("Enter author name:\n> ");
                BookList authList = bkLst.extractAuthList(inputScanner.nextLine());
                authList.displayContent();
            }
            else if (inp == "4")
            {
                bkLst.insertBefore(sc.nextLong(), getBookFromInput(inputScanner));
            }
        }
        
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    
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
                System.out.println("--------------");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        // Display the contents of the bkLst List.
        bkLst.displayContent();
        System.out.println("--------------");

        Scanner inputScanner = new Scanner(System.in); 
        while (true)
        {
            // display the menu
            System.out.println("1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
            System.out.println("2) Ask me to delete all consecutive repeated records;");
            System.out.println("3) Give me an author name and I will create a new list with the records of this author and display them;");
            System.out.println("4) Give me an ISBN number and a Book object, and I will insert a Node with the book before the record with this ISBN;");
            System.out.println("5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
            System.out.println("6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
            System.out.println("7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
            System.out.println("8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
            System.out.println("--------------");
    
            int inp = inputScanner.nextInt();
            inputScanner.nextLine();
            System.out.println("--------------");
            if (inp == 1)
            {
                System.out.println("Enter year (4 digits):");
                int year = inputScanner.nextInt();
                bkLst.storeRecordsByYear(year);
                System.out.println("--------------");
                
            }
            else if (inp == 2)
            {
                if (bkLst.delConsecutiveRepeatedRecords())
                {
                    System.out.println("Deleted some records.");
                }
                else
                {
                    System.out.println("No repeated records were found.");
                }
                bkLst.displayContent();
            }
            else if (inp == 3)
            {
                System.out.println("Enter author name:");
                String author = inputScanner.nextLine();
                BookList authList = bkLst.extractAuthList(author);
                authList.displayContent();
                System.out.println("--------------");
            }
            else if (inp == 4)
            {
                //prompt user to enter isbn and info of the book to be inserted
                System.out.println("We are looking for ISBN 1887664688.");
                Book b1 = new Book("\"The Dark Road\"","Jimin S",25.92,1239009879,"FCN",2019);
                bkLst.insertBefore(1887664688L, b1);
                System.out.println(); 
                bkLst.displayContent();
                System.out.println("--------------");

                
                

            }
            else if(inp == 5)
            {
                System.out.println("Enter 2 isbns to swap:");
                Long isbn1 = inputScanner.nextLong();
                inputScanner.nextLine();
                Long isbn2 = inputScanner.nextLong();
                inputScanner.nextLine();

                System.out.println("We are looking for ISBN" + isbn1+ "and ISBN "+isbn2+" (respectfully).");
                Book b2 = new Book("\"The Gifted\"", "Shawn Daron", 45.22, 1765669879, "DOC", 2023);
                bkLst.insertBetween(isbn1, isbn2,b2);
                System.out.println(); 
                bkLst.displayContent();
                System.out.println("--------------");
            }
            else if(inp == 6)
            {
                System.out.println("Enter 2 isbns to swap:");
                Long isbn1 = inputScanner.nextLong();
                inputScanner.nextLine();
                Long isbn2 = inputScanner.nextLong();
                inputScanner.nextLine();
                bkLst.swap(isbn1, isbn2);

                System.out.println(); 
                bkLst.displayContent();
                System.out.println("--------------");
            }
            else if(inp == 7)
            {
                
            }
            else if(inp == 8)
            {
                System.out.println("I have been silenced. Goodbye.");
                break;
            }
            else{
                System.out.println("Error: invalid input");
            }
            // bkLst.displayContent();
        }
        inputScanner.close();
        
    }
}

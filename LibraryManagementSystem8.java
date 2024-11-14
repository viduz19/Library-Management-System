import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class.
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

// Constructor of the Book class
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

 // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

// Setter method

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public String toString() {
        return "|_Title_|:- " + title + "|_Author_|:- " + author + "|_ISBN_|:- " + isbn + "|_Availability_|:- " + (isAvailable ? "Available" : "Not Available");
    }
}

// Create the FictionBook child class inherite form the Book parent class.
class FictionBook extends Book {
    public FictionBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}

// Create the NonFictionBook child class inherite form the Book parent class.
class NonFictionBook extends Book {
    public NonFictionBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}

//User class
class User {
    private int userId;
    private List<Book> borrowedBooks;

    public User(int userId) {
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

// Laibrary class
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        if (books.isEmpty()) {
            System.out.println("_____||__No books in the library__||_____");
        } 
		else {
            System.out.println("_______||____available books___||_______");
            System.out.println(" ");

            for (Book book : books) {
                if (book.isAvailable()) {
					System.out.println(book);
                }
            }
        }
    }


    public void addUser(int userId) {
        User newUser = new User(userId);
        users.add(newUser);
        System.out.println("*******---User added to the library with ID :-" + newUser.getUserId());
        System.out.println(" ");
        System.out.println(" ");


    }

    public void borrowBook(int userId, String isbn) {
        User user = getUserById(userId);
        if (user != null) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                    user.borrowBook(book);
                    book.setAvailable(false);
                    System.out.println("_____||__Book borrowed successfully!........");
                    return;
                }
            }
            System.out.println("_____||__Book with ISBN " + isbn + " not found or not available in the library............");
        } else {
            System.out.println("_____||__User with ID " + userId + " not found in the library.............");
        }
    }

    public void returnBook(int userId, String isbn) {
        User user = getUserById(userId);
        if (user != null) {
            for (Book book : user.getBorrowedBooks()) {
                if (book.getIsbn().equals(isbn)) {
                    user.returnBook(book);
                    book.setAvailable(true);
                    System.out.println("_____||__Book returned successfully!........");
                    return;
                }
            }
            System.out.println("_____||__Book ISBN : " + isbn + " not found in the user's borrowed list.");
        } else {
            System.out.println("_____||__User ID : " + userId + " not found in the library.");
        }
    }

    private User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}

// LibraryManagementSystem class (Main class)
class LibraryManagementSystem8 {
	
	//Main method 
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);


        FictionBook fictionBook = new FictionBook("Hello Beautiful               " , " Ann Napolitano   " , " 156-8-4-2-45 " );
        NonFictionBook nonFictionBook = new NonFictionBook("How to Catch an Elf  " , " Adam Wallace     " , " 123-8-6-4-60 ");
		
		FictionBook fictionBook1 = new FictionBook("The Old Man and the Sea      " , " Ernest Hemingway " , " 156-8-4-2-45 ");
		NonFictionBook nonFictionBook1 = new NonFictionBook("Lord of the Flies   " , " William Golding  " , " 123-8-6-4-65 ");
		
		
		FictionBook fictionBook2 = new FictionBook("The Lord of the Rings        " , " R.R. Tolkien     " , " 156-8-4-2-45 ");
		NonFictionBook nonFictionBook2 = new NonFictionBook("Lord of the Flies   " , " William Golding  " , " 123-8-6-4-36 ");
		
		
		      library.addBook(fictionBook);
			  library.addBook(fictionBook1);
			  library.addBook(fictionBook2);
              library.addBook(nonFictionBook);
			  library.addBook(nonFictionBook1);
			  library.addBook(nonFictionBook2);


        while (true) {
            System.out.println(" ");
            System.out.println("**********------- Library Management System -------**********");
            System.out.println(" ");
            library.displayAvailableBooks();
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("1. Add a book");
            System.out.println("2. Display available books");
            System.out.println("3. Add a user");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.println(" ");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("***___Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("***___Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("***___Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.print("_____||__Book added successfully!........");
                    System.out.print(" ");

                    Book newBook = new FictionBook(title, author, isbn);
                    library.addBook(newBook);
                    break;
                case 2:
                    library.displayAvailableBooks();
                    break;
                case 3:
                    System.out.print("***___Enter user ID: ");
                    int userId = scanner.nextInt();
                    library.addUser(userId);
                    break;
                case 4:
                    System.out.print("***___Enter your user ID: ");
                    int borrowUserId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("***___Enter the ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowUserId, borrowIsbn);
                    break;
                case 5:
                    System.out.print("***___Enter your user ID: ");
                    int returnUserId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("***___Enter the ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnUserId, returnIsbn);
                    break;
                case 6:
                    System.out.println("****-----You are the Exiting the Library Management System. Goodbye!..................");
                    System.exit(0);
                    break;
                default:
                    System.out.println("****-----Invalid choice.........Please enter a valid option-----****");

            }
        }
    }
}



import java.io.*;
import java.util.*;

class Book {
    int bookId;
    String title;
    String author;
    String category;
    boolean isIssued;

    Book(int bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    void displayBookDetails() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Category: " + category + ", Issued: " + (isIssued ? "Yes" : "No"));
    }

    void markAsIssued() {
        isIssued = true;
    }

    void markAsReturned() {
        isIssued = false;
    }
}

class Member {
    int memberId;
    String name;
    String email;
    List<Integer> issuedBooks = new ArrayList<>();

    Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    void displayMemberDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + name + ", Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }

    void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }
}

public class LibrarySystem {
    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        Book book = new Book(id, title, author, category);
        books.put(id, book);
        System.out.println("Book added successfully!");
        saveBooksToFile();
    }

    void addMember() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Member Email: ");
        String email = sc.nextLine();

        Member member = new Member(id, name, email);
        members.put(id, member);
        System.out.println("Member added successfully!");
        saveMembersToFile();
    }

    void issueBook() {
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        if (!members.containsKey(memberId) || !books.containsKey(bookId)) {
            System.out.println("Invalid Member or Book ID!");
            return;
        }

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book.isIssued) {
            System.out.println("Book already issued!");
        } else {
            book.markAsIssued();
            member.addIssuedBook(bookId);
            System.out.println("Book issued successfully!");
            saveBooksToFile();
            saveMembersToFile();
        }
    }

    void returnBook() {
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        if (!members.containsKey(memberId) || !books.containsKey(bookId)) {
            System.out.println("Invalid Member or Book ID!");
            return;
        }

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book.isIssued) {
            book.markAsReturned();
            member.returnIssuedBook(bookId);
            System.out.println("Book returned successfully!");
            saveBooksToFile();
            saveMembersToFile();
        } else {
            System.out.println("Book was not issued!");
        }
    }

    void searchBooks() {
        sc.nextLine();
        System.out.print("Enter keyword to search (title/author/category): ");
        String key = sc.nextLine().toLowerCase();
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key) ||
                b.author.toLowerCase().contains(key) ||
                b.category.toLowerCase().contains(key)) {
                b.displayBookDetails();
            }
        }
    }

    void sortBooks() {
        List<Book> bookList = new ArrayList<>(books.values());
        bookList.sort(Comparator.comparing(b -> b.title.toLowerCase()));
        System.out.println("Books sorted by title:");
        for (Book b : bookList) {
            b.displayBookDetails();
        }
    }

    void saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book b : books.values()) {
                bw.write(b.bookId + "," + b.title + "," + b.author + "," + b.category + "," + b.isIssued);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books!");
        }
    }

    void saveMembersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("members.txt"))) {
            for (Member m : members.values()) {
                bw.write(m.memberId + "," + m.name + "," + m.email + "," + m.issuedBooks.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving members!");
        }
    }

    public static void main(String[] args) {
        LibrarySystem lib = new LibrarySystem();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to City Library Digital Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: lib.addBook(); break;
                case 2: lib.addMember(); break;
                case 3: lib.issueBook(); break;
                case 4: lib.returnBook(); break;
                case 5: lib.searchBooks(); break;
                case 6: lib.sortBooks(); break;
                case 7: System.out.println("Thank you for using Library System!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}

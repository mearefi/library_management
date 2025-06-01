package management;

import library.Book;
import library.Member;
import datastructures.maps.CustomHashMap;
import datastructures.interfaces.Map;


public class BookManager {
    private Map<String, Book> books;
    private MemberManager memberManager;

    public BookManager(MemberManager memberManager) {
        this.books = new CustomHashMap<>();
        this.memberManager = memberManager;
    }

    public void addBook(Book book) {
        if (book != null && book.getIsbn() != null) {
            books.put(book.getIsbn(), book);
        }
    }

    public Book getBookByIsbn(String isbn) {
        if (isbn != null) {
            return books.get(isbn);
        }
        return null;
    }

    public boolean isBookAvailable(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book != null && book.isAvailable();
    }

    public void setBookAvailability(String isbn, boolean available) {
        Book book = getBookByIsbn(isbn);
        if (book != null) {
            book.setAvailable(available);
        }
    }

    public void addToWaitlist(String isbn, String memberId) {
        Book book = getBookByIsbn(isbn);
        Member member = memberManager.getMember(memberId);
        if (book != null && member != null) {
            book.addToWaitlist(member);
            System.out.println(member.getName() + " added to waitlist for book: " + book.getTitle());
        } else {
            if (book == null) System.out.println("Error: Book not found for waitlist. ISBN: " + isbn);
            if (member == null) System.out.println("Error: Member not found for waitlist. ID: " + memberId);
        }
    }

    public Member getNextFromWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book != null) {
            return book.getNextInWaitlist();
        }
        return null;
    }

    public boolean hasWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book != null && book.hasWaitlist();
    }
}
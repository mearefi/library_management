package management;

import library.Book;
import library.Member;
import library.Transaction;
import datastructures.lists.CustomArrayList;
import datastructures.interfaces.List;

import java.util.Date;

public class TransactionManager {
    private List<Transaction> transactionLog;

    public TransactionManager() {
        this.transactionLog = new CustomArrayList<>();
    }

    public Transaction addTransaction(String bookTitle, String memberName, String type) {
        Transaction transaction = new Transaction(
                "TXN-" + new Date().getTime() + "-" + transactionLog.size(),
                bookTitle,
                memberName,
                type
        );
        transactionLog.add(transaction);
        System.out.println("Transaction recorded: " + type + " - Book: " + bookTitle + " by " + memberName);
        return transaction;
    }

    public Transaction getTransactionByIndex(int index) {
        if (index >= 0 && index < transactionLog.size()) {
            return transactionLog.get(index);
        }
        System.out.println("Error: Transaction index " + index + " out of bounds.");
        return null;
    }
}
package library;

import datastructures.stacks.CustomStack;
import datastructures.interfaces.Queue;


public class Member {
    private String memberId;
    private String name;
    private Queue<Transaction> transactionHistory;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.transactionHistory = new CustomStack<>();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionHistory.add(transaction);
        }
    }

    public Transaction getLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            return transactionHistory.peek();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
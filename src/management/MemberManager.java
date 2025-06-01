package management;

import datastructures.maps.CustomHashMap;
import library.Member;
import library.Transaction;

public class MemberManager {
    private CustomHashMap<String, Member> members;

    public MemberManager() {
        this.members = new CustomHashMap<>();
    }

    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member is null");
        }
        members.put(member.getMemberId(), member);
    }

    public Member getMember(String memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Memberid is null");
        }
        return members.get(memberId);
    }

    public void recordTransaction(String memberId, Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is null");
        }
        Member member = getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Can not find member:" + memberId);
        }
        member.addTransaction(transaction);
    }

    public Transaction getLastTransaction(String memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Memberid is null");
        }
        Member member = getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Can not find member: " + memberId);
        }
        return member.getLastTransaction();
    }
}

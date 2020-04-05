package oopDesignPattern.ch02.ex02;

import java.util.Date;

public class Member {
    private Date expiryDate;
    private boolean male;
    
    public boolean isExpired() {
        return expiryDate != null && expiryDate.getDate() < System.currentTimeMillis();
    }
}

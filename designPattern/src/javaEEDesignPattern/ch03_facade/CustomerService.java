package javaEEDesignPattern.ch03_facade;

import javax.ejb.Stateless;

@Stateless
public class CustomerService {
    public long getCustomer(int sessionID) {
        // 로그인 한 고객 ID를 조회
        return 100005L;
    }
    public boolean checkId(long x) {
        //고객 ID가 바른지 체크
        return true;
    }
}



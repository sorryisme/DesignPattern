package javaEEDesignPattern.ch05_di.after;

public class UserService {
    private UserDataRepository udr;
    
    UserService(UserDataRepository udr){
        this.udr = udr;
        //객체를 new 인스턴스형태로 생성하지 않고 팩토리를 통해 주입받는 형태로 리팩토링(before와 비교할 것)
    }
    
    public void persistUser(User user) {
        udr.save(user);
    }
}

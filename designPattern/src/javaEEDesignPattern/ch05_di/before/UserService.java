package javaEEDesignPattern.ch05_di.before;

public class UserService {
    private UserDataRepository udr;
    
    UserService(){
        this.udr = new UserDataRepositoryImpl();
    }
    
    public void persistUser(User user) {
        udr.save(user);
    }
}

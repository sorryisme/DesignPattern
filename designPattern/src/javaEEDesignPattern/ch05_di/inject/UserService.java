package javaEEDesignPattern.ch05_di.inject;

import javax.inject.Inject;
import javax.inject.Named;

public class UserService {
    
    @Inject @Named
    private UserDataRepository udr;
    
    
    public void persistUser(User user) {
        udr.save(user);
    }
}

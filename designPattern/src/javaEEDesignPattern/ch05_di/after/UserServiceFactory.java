package javaEEDesignPattern.ch05_di.after;

public class UserServiceFactory {
    public UserService getInstace() {
        return new UserService(new UserDataRepositoryImpl());
    }
}

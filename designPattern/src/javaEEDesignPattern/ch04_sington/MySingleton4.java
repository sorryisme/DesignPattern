package javaEEDesignPattern.ch04_sington;

public class MySingleton4 {
    
    private volatile MySingleton4 instance = null;

    private MySingleton4() {}
    
    public MySingleton4 getInstance() {
        if(instance == null) {
            synchronized(MySingleton4.class) {
                if(instance == null){
                    instance =new MySingleton4();
                }
            }
        } //if
        return instance;
    }
}

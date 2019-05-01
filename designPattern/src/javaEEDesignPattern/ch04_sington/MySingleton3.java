package javaEEDesignPattern.ch04_sington;

public class MySingleton3 {
    private static MySingleton3 instance = null;
    //클래스 로딩과 동시에 인스턴스 생성
    // 정적멤버/블록은 로딩시 실행
    
    static {
        instance = new MySingleton3();
    }
    private MySingleton3() {}
    
    public static synchronized MySingleton3 getInstance() {
        return instance;
    }
}

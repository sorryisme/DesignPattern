package javaEEDesignPattern.ch04_sington;

public class MySingleton2 {
    private static MySingleton2 instance = new MySingleton2();
    //클래스 로딩과 동시에 인스턴스 생성
    // 정적멤버/블록은 로딩시 실행
    private MySingleton2() {}
    
    public static synchronized MySingleton2 getInstance() {
        return instance;
    }
}

package javaEEDesignPattern.ch04_sington;
//리플렉션 API를 통해서 접근자 수정을 막기 위해 enum을 사용한다

public enum MySingletonEnum {
    INSTANCE;
    public void doSomethingInteresting() {}
}

package oopDesignPattern.ch02;

public class AuthenticationHandler {
    
    public void handleRequest(String inputId, String inputPassword) {
        Authenticator auth = new Authenticator();
        if(auth.authenticate(inputId, inputPassword)) {
            /// 아이디 비밀번호 일치시 처리
        } else {
            
        }
    }

}

#	템플릿 메소드 패턴

- 프로그램을 구현하다보면 완전히 동일한 절차를 가진 코드를 작성할 수 있음
- 실행 과정/단계는 동일하지만 일부 구현이 다른 경우 사용
  - 실행 과정을 구현한 상위 클래스
  - 실행 과정의 일부 단계를 구현한 하위 클래스
- 1번 예제(실행과정이 동일한 코드)

```java
public class DbAuthenticator {
	public Auth authenticae(String id, String pwd){
		// 사용자 정보로 인증 확인
		User user = userDao.selectById(id);
		boolan auth = user.equalPassword(pwd);
		// 인증 실패 시 익셉션	
		if(!auth) {
			throw createException();
		}
    // 인증 성공 시 인증정보 제공
		return new Auth(id, user.getName());
	}
}
```

- 2번 예제(실행과정이 동일한 코드)

```java
public class LdapAuthenticator {
	public Auth authenticate(String id, String pwd){
		//사용자 정보로 인증 확인
		boolean lauth = ldapClient.authenticate(id, pwd);
    //인증 실패 시 익셉션 발생
    if(! auth) throw createException();
    //인증 성공 시 인증 정보 제공
    LdapContext ctax = ldapClient.find(id);
    return new Auth(id, ctx.getAttribute("name"));
	}
}
```



### To-BE

- 템플릿 메서드를 제공하는 Authenticator

  ```java
  public abstract Authenticate(String id, String pwd){
  		if(!doAuthenticate(id, pwd))
  			throw createException();
  		return createAuth(id);
  }
  
  protected abstract doAuthenticate(String id, String pwd);
  
  protected abstract Auth createAuth(String id);
  ```

  - 동일한 실행 과정은 구현하고 차이나니는 부분은 별도의 추상 메서드로 분리

- 2번 예제 TO-BE

  ```java
  public class LdapAuthenticator extends Authenticator {
  	
  	@Override
  	protected boolean doAuthenticate(String id, String pw){
  		return ladpClient.authenticate(id, pw);
  	}
  	
    @Override
    protected Auth createAuth(String id) {
      LdapContext ctx = ladapClient.find(id);
      return new Auth(id, ctx.getAttribute("name"));
    }
  }
  ```

  - 전체 실행과정은 Authenticator의 authenticate 메서드에서 제공
  - 각 타입에서 코드가 중복되는 것을 방지

### 상위클래스가 흐름 제어 주체

- 템플릿 메서드 템플릿 특징은 하위 클래스가 아닌 상위 클래스에서 흐름제어
- 상위타입 템플릿 메서드가 모든 흐름을 제어하고 하위타입의 메서드는 템플릿 메서드에서 호출
- 상위클래스에서 실행시점이 제어되고 기본 구현을 제공하면서 하위 클래에서 알맞게 확장하는 메서드를 훅 메서드라고 부른다



## 템플릿 메서드와 전략 패턴 조합	

- 템플릿과 전략 패턴을 같이 사용하면 상속이 아닌 조립의 방식으로 활용

  - 스프링 프레임워크의 Template으로 끝나는 클래스

- 예시코드 : TransactionTemplate의 execut

  ```java
  public <T> T execute(TransactionCallback<T> action) throws TransactionException{
    //중략
    TranStaction status = this.transactionManager.getTransaction(this);
    T result;
    try{
      result = action.doInTransaction(status);
    } catch (RuntimeException ex) {
      	rollbackOnException(status, ex);
      	throw ex;
    }
    ...// 기타 다른 익셉션 처리 코드
      this.transactionManager.commit(status);
    	return result;
  }
  ```

  - Execute 메서드는 트랜잭션의 시작/커밋/롤백 등의 실행 흐름을 제공하는 템플릿 메서드

    - 앞서 템플릿 메서드가 하위타입에서 재정의할 메서드를 호출한다면
    - TransactionTemplate의 execute() 메서드는 파라미터로 전달 받은 action의 메서드를 호출

    ```java
    transactionTemplate.execute(new TransactionCallback<String>(){
    	public String doInTransaction(TransactionStatus status) {
    		//트랜잭션 안에서 실행될 코드
    	}
    });
    ```

    - 조립 위임 방식에서는 확장 기능을 제공하려면 구현이 다소 복잡해짐
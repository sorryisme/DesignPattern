## Ch01. 들어가기



### 2. 수정하기 좋은 구조를 가진 코드

- 메뉴가 선택되면 해당 화면을 보여준다

- 버튼 1을 클릭하면 선택된 메뉴화면에서 알맞은 처리를 한다.

  

---------

- 위의 동작은 메뉴가 추가되더라도 동일하게 동작한다. 
- "화면을 보여주고 버튼 1을 클릭하면 화면에 반영한다."
- 이를 인터페이스로 구현하여 처리한다.

```java
public interface ScreenUI {
    public void show();
    public void handleButton1Click();
}
```



```java
public class Application implements OnClickListener{
    private Menu menu1 = new Menu("menu1");
    private Menu menu2 = new Menu("menu2");
    private Button button1 = new Button("button1");
    
    //private String currentMenu = null;
    private ScreenUI currentScreen = null;
    
    public Application() {
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        button1.setOnClickListener(this);
    }
    
    public void clicked(Component eventSource) {
        if(eventSource.getId().equals("menu1")) {
//            changeUItoMenu1();
            currentScreen = new Menu1ScreenUI();
            currentScreen.show();
            
        } else if (eventSource.getId().equals("menu2")) {
//            changeUItoMenu2();
            currentScreen = new Menu2ScreenUI();
            currentScreen.show();
            
        } else if(eventSource.getId().equals("button1")) {
            if(currentScreen == null){
                return;
            }
            
            
//            if(currentMenu.equals("menu1")) {
//                processButton1WhenMenu1();
//            } else if (currentMenu.equals("menu2")) {
//                processButton1WhenMenu2();
//            }
// 		    eventSource.getId()로 생성된 객체의 button이 클릭된다.
            currentScreen.handleButton1Click();
            
        }
    }
}
```

#### 리팩토링

- 메뉴 클릭 처리 코드와 버튼 클릭 처리 코드를 분리

  ```java
      private OnClickListener menuListener = new OnClickListener() {
          public void clicked(Component eventSource) {
              String sourceId = eventSource.getId();
              if(sourceId.equals("menu1")) {
                currentScreen = new Menu1ScreenUI();  
              } else if (sourceId.equals("menu2")) {
                currentScreen = new Menu1ScreenUI();
              } 
              currentScreen.show();
          }
      };
      
      private OnClickListener buttonListener = new OnClickListener() {
          public void clicked(Component eventSource) {
              if(currentScreen == null){
                  return;
              }
              
              String sourceId = eventSource.getId();
              if(sourceId.equals("button1")) {
                  currentScreen.handleButton1Click();
              }
          }
      };
  ```

  - 새로운 메뉴가 추가 되어도 버튼 처리 코드가 영향을 받지 않음
  - 한 메뉴 관련 코드가 한 개의 클래스로 모여서 코드 분석/수정이 용이
  - 서로 다른 메뉴에 대한 처리코드가 섞여 있지 않아 수정용이



## Ch2. 객체지향

- 객체 지향을 잘하려면 절차지향 프로그램을 지양해야한다.



### 절차지향 

- 소프트웨어 구현 : 데이터와 데이터를 조작하는 코드를 작성
- 프로시저 : 데이터를 조작하는 방식으로 코드를 작성 
- 절차 지향 : 프로시저로 프로그램을 구성
- 단점
  - 프로그램 규모가 커졌을 때 함께 수정해야하는 프로시저 증가
  - 같은 데이터를 프로시저들이 서로 다른 의미로 사용하는 경우가 발생



### 객체지향

- 데이터 및 데이터와 관련된 프로시저를 객체라는 단위로 묶는다.
- 객체는 자신만의 데이터와 프로시저를 가진다
- 각 객체들은 서로 연결되어 다른 객체가 제공하는 기능을 사용할 수 있다.
- 복잡한 구조를 가지지만 수정에 좀 더 유연하다



### 객체

- 객체의 핵심
  - 기능을 제공해야한다.
  - 제공하는 기능을 오퍼레이션이라고 한다.
- 인터페이스와 클래스
  - 시그니처 : 기능 식별 이름, 파라미터 및 파라미터 타입, 기능 실행 값
- 객체가 제공하는 모든 오퍼레이션 집합을 인터페이스
  - 인터페이스는 객체를 사용하기 위한 일종의 명세나 규칙



### 메세지

- 오퍼레이션을 실행해달라는 요청을 전달
- 요청한 기능을 실행한 뒤 응답을 전달
- 이 때 오퍼레이션 실행을 요청하는 것을 메세지를 보낸다라고 표현



### 객체의 책임과 크기

- 객체마다 자신만의 책임이 있다는 의미를 갖는다
- 과연 객체가 갖는 책임은 어떻게 결정될까?
  - 객체 지향 설계의 출발점
- 객체마다 기능을 할당하는 것
- 객체가 갖는 책임의 크기는 작아져야하며 객체는 단 한개만의 책임을 가져야한다
  - 단일 책임의 원칙 (SRP : Single Responsibility Principle)



### 의존

- 한 객체가 다른 객체를 생성하거나 다른 객체의 메소드를 호출할 때 객체에 의존한다고 표현
- 파라미터로 받으면 메소두 구현 과정에서 파라미터 객체를 사용할 가능성이 있어 의존한다고 표현
- 의존할 수록 의존타입이 변경이 발생될 때 같이 변경될 가능성이 높다
- 의존으로 인한 문제가 발생되지 않도록 의존역전의 원칙 ( DIP : Dependency inversion principle)를 지키는 것을 추천한다

```java
 public void handleRequest(String inputId, String inputPassword) {
        Authenticator auth = new Authenticator();
        if(auth.authenticate(inputId, inputPassword)) {
            /// 아이디 비밀번호 일치시 처리
        } else {
            
        }
    }
```

- 만약 AuthenticationHandler 클래스 변경 요구가 발생하면 Authenticator 클래스에 변화가 발생한다
  - 내가 변경되면 나에게 의존하고 있는 코드에 영향을 준다
  - 나의 요구가 변경되면 내가 의존하고 있는 타입에 영향을 준다



## 캡슐화

- 객체지향의 장점은 구현의 변경이 다른 곳 변경에 가하지 않는데 있다.
- 캡슐화를 통해서 한 곳의 변화가 다른 곳에 미치는 영향을 최소화 한다
- 캡슐화 : 내부적으로 기능을 어떻게 구현하는지 감추고 내부 구현 변경에 유연함을 준다

```
if (Memeber.getExpiryDate()) != null && member.getExpiryDate().getDate() < System.currentTimeMillis()) { 
	//만료가 되었을 때 처리
} 
```

- 만료여부를 확인하는 소스 다음과 같은 추가 요구사항이 발생

  - 여성 회원인 경우 만료기간이 지나도 30일 간 서비스를 사용할 수 있음

- 관련 코드를 모두 수정해야하며 유지보수가하기가 힘들고 추후 나중에 추가 요구사항이 발생할 수 있음

- 이런 문제는 데이터를 중심으로 프로그래밍을 했기 때문이다

- 캡슐화를 통해 다음과 같이 처리하면 된다.

  ```java
  public class Member {
      private Date expiryDate;
      private boolean male;
      
      public boolean isExpired() {
          return expiryDate != null && expiryDate.getDate() < System.currentTimeMillis();
      }
  }
  
  ```

  - 이와 같이 처리하면 isExpired를 사용한 모든 메서드는 변경없이 사용할 수 있다.



### 캡슐화를 위한 두 개의 규칙

- Tell, Don't Ask  
  - 데이터를 물어보지 않고 기능을 실행해 달라고 말하는 규칙
  - 데이터를 달라고 하지 않고 기능을 실행을 요청하면 된다
    - 예시 : isExpired()
- 데미테르의 법칙
  - 한 번의 메서드 호출로만 변경하자
    - 메서드에서 생성한 객체의 메서드만 호출
    - 파라미터로 받은 객체의 메서드만 호출
    - 필드로 참조하는 객체의 메서드만 호출



## 객체지향의 설계과정

- 제공해야할 기능을 찾고 세분화 하고 그 기능을 알맞은 객체에 할당
  - 기능을 구현하는데 필요한 데이터를 객체 추가
    - 객체에 데이터를 먼저 추가하고 데이터를 이용하는 기능을 넣을 수 있다.
  - 기능은 최대한 캡슐화
- 객체 간에 어떻게 메세지를 주고 받을 지 결정
- 과정1과 과정 2을 지속적으로 반복



## 다향성과 추상 타입



### 다형성과 상속

- 다형성 : 한 객체가 여러타입을 가질 수 있다
- 구현상속 : 보통 상위 클래스에 정의된 기능을 재사용하기 위한 목적으로 사용



### 추상타입과 유연함

- 추상화 : 데이터나 프로세스 등을 의미가 비슷한 개념이나 표현으로 정의하는 과정
- 추상화된 타입은 오퍼레이션의 시그니처만 정의할 뿐 실제구현을 제공하지는 못한다
- 인터페이스에서 정의한 기능을 구현하는 클래스를 '콘크리트 클래스'라고 호칭한다



### 추상 타입을 이용한 구현 교체의 유연함

- 콘크리트 클래스를 직접 사용해도 되는데 왜 추상 타입을 사용할까?
- 공통적인 기능을 추상화 함으로써 교체의 유연함을 발생시킬 수 있다.



### 변화되는 부분을 추상화하기

- 변화되는 부분을 추상화하자



### 인터페이스에 대고 프로그래밍하기

- 변화가능성이 높은 경우에 한해서만 사용하자



## 재사용 : 상속보단 조립

- 상속의 문제점 
  - 상위 클래스 변경의 어려움
    - 상위클래스 코드를 변경하면 의존 하는 코드에 영향을 준다
  - 클래스의 불필요한 증가
    - 필요한 기능의 조합이 증가할 수록 클래스의 개수 또한 증가하게 된다
  - 상속의 오용
    - 여러 개의 메서드를 상속받기 때문에 개발자들이 잘못된 메서드 사용을 발생시킬 수 있다.
    - 상속은 IS-A 관계에가 성립할 때만 사용할 수 있다.

### 조립을 이용한 재사용

- 런타임 구조가 복잡해지지만 런타임 단계에서 객체를 바꿀 수 있다.
- 구현하기 어렵지만 변경의 유연성에서 오는 장점이 크다



### 위임

- 나의 할 일을 다른 객체에게 넘긴다는 의미
- 조립방식을 통한 위임 구현



### 상속 사용 시기

- 기능의 확장의 개념에서 상속을 적용
- 명확하게 IS-A 관계 성립





## 설계원칙 : SOLID

- 단일 책임 원칙 : SRP
- 개방-폐쇄의 원칙 : OCP
- 리스코프 치환 원칙 : LSP
- 인터페이스 분리 원칙 : ISP
- 의존 역전 원칙 : DIP



###	단일책임의 원칙

- 클래스는 단 한 개의 책임을 가져야한다

```java
public class DataViewer {

	public void display() {
		String data = loadHtml();
		updateGui(data);
	}
	
	public String loadHtml() {
		String url = "";
		HttpClient client = new HttpClient();
		client.connect(url);
		return client.getResponse();
	}
	
	private void updateGui(String data){
		GuiData guiModel = parseDataToGuiData(data);
		tableUI.changeData(guiModel);
	}
	
	private GuiData parseDataToGuiData(String data) {
		return new GuiData();
	}
}
```

- 현재 소스 상에서는 String을 GuiData로 변환
- 만약 데이터가 String에서 byte배열로 변경되는 상황이면 다음과 같은 연쇄작용이 발생한다
  - 읽어온 데이터 구조의 변화
  - 파라미터 타입 변화
  - GuiData 생성하는 코드 변화
- 해결방안
  - 데이터 읽기와 데이터를 화면에 보여주는 책임을 두 개의 클래스로 분리
  - String 같은 저수준이 아닌 알맞게 추상화 된 타입을 사용
- 단일 책임의 원칙을 어길 시 재사용을 어렵게 한다
  - 필요하지 않는 패키지까지 필요하게 된다
  - 만약 분리할 경우 필요한 클래스와 패키지만 사용 가능하다



### 책임이란 변화에 대한 것

- 단일 책임을 지키지 않았을 때 한 책임의 구현 변경에 의해 다른 책임과 관련된 코드가 변경될 가능성이 높다
- 메서드를 실행하는 주체가 누구인지 확인하여 단일 책임의 원칙을 확인



## 개방 폐쇄 원칙(Open-closed principle)

- 확장에는 열려있어야하고 변경에는 닫혀 있어야 한다
  - 기능을 변경하거나 확장할 수 있으면서
  - 그 기능을 사용하는 코드는 수정하지 않는다
- 추상화하여 개방 폐쇄 원칙을 구현할 수 있다.



```java
public class ResponseSender {

	private Data data;
	
	public ResponseSender(Data data) {
		this.data = data;
	}
	
	
	public Data getData() {
		return data;
	}
	
	public void Send() {
		sendHeader();
		sendBody();
	}
	
	protected void sendHeader() {
		
	}
	protected void sendBody(){
		
	}
}

```

- 만약 여기에 추가적으로 압추하여 전달하는 기능을 추가하고 싶다면?
  - 클래스를 상속받아 오버라이딩하면 된다.
    - 기능을 변경하거나 확장에 문제가 없으며
    - 기존에 사용하는 클래스에 영향을 주지 않는다.



### 개방 폐쇄의 원칙이 깨질 때 주요 증상

- 다운 캐스팅을 한다
  - instanceof 같은 타입 확인 연산자가 사용된다면 개방 폐쇄의 원칙이 지켜지지 않을 가능성이 높다
- 비슷한 if-else 블록이 존재한다



### 개방 폐쇄의 원칙은 유연함에 대한 것

- 기존 기능을 확장하기 위해 기존 코드를 수정해줘한다면 새로운 기능을 추가하기 점점 힘들어 진다
- 변화가 예상되는 것을 추상화하여 변경의 유연함을 얻도록 해준다



## 리스코프 치환 원칙

- 상위 타입의 객체를 하위 타입의 객체로 치환해도 사용하는 프로그램은 정상적으로 동작해야한다

  ```java
  public void someMethod(SuperClass sc){
  	sc.someMethod();
  }
  
  => someMethod(new SubClass());
  ```

  - 다형성에 기반한 개방 폐쇄 원칙이 지켜지지 않기 때문에 매우 중요하다



- 직사각형-정사각형 문제
  - 상속 관계인 것 처럼 보여도 실제 구현에서는 별개의 타입으로 지정
- 상위 타입에서 지정한 리턴의 값 범위에 해당하지 않는 값을 리턴하는 것
  - 상위 타입 범위를 잘 체크해서 리턴을 처리해야한다

### 리스코프 치환 원칙은 계약과 확장

- 기능의 명세 또는 계약에 대한 내용 

- 직사각형-정사각형 문제에서 다음과 같은 계약을 제공한다

  - 높이 값을 파라미터로 전달받은 값으로 변경한다
  - 폭 값은 변경되지 않는다.

- 기능 계약 관련해서 위반은 다음과 같다

  - 명시된 명세에서 벗어난 값을 리턴한다
  - 명시된 명세에서 벗어난 익셉션을 발생시킨다
  - 명시된 명세에서 벗어난 기능을 수행한다

- 리스코프 치환 원칙을 어긴 예시

```java
public class Coupon {
	public int calculateDiscountAmount(Item item){
		return item.getPrice() * discountRate;
	}
}
```

- 무조건 할인해주지 않는 정책이 추가되어 Item을 상속받는 SpecialItem 클래스 추가

```java
public class Coupon {
	public int calculateDiscountAmount(Item item){
		if (item instanceof SpecialItem){ //LSP 위반
			return 0;
		}
		return item.getPrice() * discountRate;
	}
}
```

- instanceof를 사용하는 것은 하위타입이 상위 타입을 대체할 수 없다는 것을 의미

- 변화되는 기능을 상위 타입에 추가

  ```javascript
  public class Item {
  	//상속 받아 오버라이딩 
  	public boolean isDicountAvailable (){
  		return true;
  	}
  }
  ```




## 인터페이스 분리 원칙

- 인터페이스는 인터페이스를 사용하는 클라이언트를 기준으로 분리해야한다.

  
# 전략패턴

- 예시코드

  - 매장 가격 할인 정책

    - 첫손님 할인
    - 저녁 덜 신선한 과일 할인

    ```java
    public class Calculator {
    	
    	public int calculate(boolean firstGuest, List<Item> items) {
    		int sum = 0;
    		for (Item item : items) {
    			if(firstGuest){
    				sum += (int) (item.getPrice() * 0.9);
    			} else if(! item.isFresh()) {
    				sum += (item.getPrice() * 0.8);
    			} else {
    				sum += item.getPrice();
    			}
    		}
    		
    		return sum;
    	}
    
    }
    ```

- 문제점

  - 서로 다른 계산 정책들이 한 코드에 섞여 있음
  - 정책이 추가될수록 코드 분석이 어려움
  - 가격 정책이 추가될수록 if 블록이 추가 됨

- 해결방안

  - 가격 할인 정책을 별도로 분리
  - 인터페이스로 할인금액 계산을 추상화
  - 가격 할인 알고리즘을 추상화하고 있는 DiscountStrategy를 전략이라고 부름
  - 가격 계산 기능 자체의 책임을 갖고 있는 Calculator를 콘텍스트라고 부른다.

- TO-BE

```java
public class Calculator {
	
	private DiscountStrategy discountStrategy;
	
	public Calculator(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}
	
	public int calculate(boolean firstGuest, List<Item> items) {
		int sum = 0;
		for (Item item : items) {
			sum += discountStrategy.getDiscountPrice(item);
		}
		
		return sum;
	}

}
```

```java
public class FirstGuestDiscountStrategy implements DiscountStrategy {
	@Override
	public int getDiscountPrice(Item item) {
		return (int)(item.getPrice() * 0.9);
	}
}
```



- 전략 객체는 콘텍스트를 사용하는 클라이언트에서 직접 생성

  ```java
  public class UIController {
  
  	private DiscountStrategy strategy;
  	
  	public void onFirstGuestButtonClick() {
  		strategy = new FirstGuestDiscountStrategy();
  	}
  	
  	public void onCalculatorButtonClick(List<Item> items) {
  		Calculator cal = new Calculator(strategy);
  		int price = cal.calculate(items);
  	}
  	
  }
  ```

  

- 다음과 같이 버튼을 구현되었다고 가정한다면

  - 콘텍스트를 사용하는 클라이언트가 상세구현에 대한 의존 발생
  - 클라이언트와 전략의 콘크리트 클래스가 쌍으로 이루어짐
    - 코드가 생성될 때 같이 생성되고 
    - 코드가 삭제될 때 같이 삭제되기 때문에 코드 응집도를 높여줌

- 이점

  - 콘텍스트 코드(계산 코드) 변경없이 새로운 전략을 추가할 수 있다

- 사용예제

  - 동일한 기능을 제공하지만 성능의 장단점에 따라 알고리즘을 선택해야하는 경우 전략 패턴을 사용
  - XML을 파싱하여 객체 생성하는 기능의 경우 DomUnmarshaller 또는 StaxUnmarshaller 콘크리트 클래스를 선택한다

  




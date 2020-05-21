# State 패턴

- 단일 상품을판매하는 자판기

| 동작        | 조건                 | 실행                | 결과                                                 |
| ----------- | -------------------- | ------------------- | ---------------------------------------------------- |
| 동전을 넣음 | 동전 없음이면        | 금액을 증가         | 제품 선택 가능                                       |
| 동전을 넣음 | 제품을 선택 가능이면 | 금액을 증가         | 제품 선택 가능                                       |
| 제품 선택   | 동전 없음이면        | 아무 동작하지않음   | 동전 없음 유지                                       |
| 제품 선택   | 제품 선택 가능이면   | 제품 주고 잔액 감소 | 잔액 있으면 제품 선택 가능<br />잔액없으면 동전 없음 |



-  코드 예시

  ```java
  public static enum State { NOCOIN, SELECTABLE}
  	private State state = State.NOCOIN;
  	
  	public void insertCoin (int coin) {
  		switch(state) {
  			case NOCOIN:
  				increaseCoin(coin);
  				state = State.SELECTABLE;
  				break;
  				
  			case SELECTABLE:
  				increaseCoin(coin);
  		}
  	}
  	
  	public void select(int productId) {
  		switch(state) {
  			case NOCOIN :
  				//아무것도하지않음
  				break;
  			case SELECTABLE :
  				provideProduct(productId);
  				decreaseCoin();
  				if(hasNoCoin())
  					state = State.NOCOIN;
  		
  		}
  	}
  ```

  - 추가 요구 사항
    - 자판기에 제품이 없는 경우 동전을 넣으면 바로 동전을 되돌려준다.


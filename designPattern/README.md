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












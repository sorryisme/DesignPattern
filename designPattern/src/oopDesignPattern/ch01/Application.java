package oopDesignPattern.ch01;


public class Application {
    private Menu menu1 = new Menu("menu1");
    private Menu menu2 = new Menu("menu2");
    private Button button1 = new Button("button1");
    
    //private String currentMenu = null;
    private ScreenUI currentScreen = null;
    
    public Application() {
        menu1.setOnClickListener(menuListener);
        menu2.setOnClickListener(menuListener);
        button1.setOnClickListener(buttonListener);
    }
    
    
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
            
    
    
//    public void clicked(Component eventSource) {
//        if(eventSource.getId().equals("menu1")) {
//            changeUItoMenu1();
//            currentScreen = new Menu1ScreenUI();
//            currentScreen.show();
            
//        } else if (eventSource.getId().equals("menu2")) {
//            changeUItoMenu2();
//            currentScreen = new Menu2ScreenUI();
//            currentScreen.show();
            
//        } else if(eventSource.getId().equals("button1")) {
//            if(currentScreen == null){
//                return;
//            }
//            if(currentMenu.equals("menu1")) {
//                processButton1WhenMenu1();
//            } else if (currentMenu.equals("menu2")) {
//                processButton1WhenMenu2();
//            }
//            currentScreen.handleButton1Click();
//        }
//    }
}

class Menu1ScreenUI implements ScreenUI {
    @Override
    public void show() {
    }

    @Override
    public void handleButton1Click() {
    }
}

class Menu2ScreenUI implements ScreenUI {
    @Override
    public void show() {
    }

    @Override
    public void handleButton1Click() {
    }
}
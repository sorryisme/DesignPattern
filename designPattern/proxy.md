## 프록시 패턴



- 제품 목록을 보여주는 GUI 프로그램은 일부화면에 보여주고 스크롤 할 때 나머지 목록을 화면에 표시할 수 있다.
- 이미지를 로딩할 때 대기시간이 길어지게 되는데 이를 해결하기 위한 클래스가 DynamicLoadingImage
  - 그러나 5개 이상 동적으,로 로딩하도록 구현해야할 때 클래스를 구분하는 조건문을 갖게됨
- 프록시 패턴은 실체 하는 객체 대신하여 프록시를 통해 객체의 생성이나 접근을 제어하게한다
- Image 인터페이스는 ㅇ이미지를 표현 , ReaImage 클래스가 콘크리트 클래스
- 



### 예시코드

```java
public class ProxyImage implements image {
	private String path;
	private RealImage image;
	
	public ProxyImage(String path){
		this.path = path;
	}
	
	public void draw(){
		if(image = null){
			image = new RealImage(path); // 최초 접근 시 객체생성
		}
        image.draw()); // Real Image 객체에 위임
	}
}
```

- List UI 클래스는 Image 타입을 사용하기 때문에 실제 타입이 RealImage인지 Proxy Image인지 모름

```java
 public class ListUI {
 	private List<Image> images;
 	public ListUI(List<Image> images){
 		this.images = images;
 	}
 }

public void onScroll(in start, int end){
    for(int i = start; i <= end; i++){
        Image image = images.get(i);
        image.draw();
    }
    
}
```

- Proxy 이미지 처럼 필요한 순간에 실제 객체를 생성해주는 프록시를 가상 프록시라고 부른다
  - 가상 프록시 
  - 보호 프록시 : 실제 객체에 대한 접근 제어
  - 원격 프록시 : 다른 프로세스에 존재하는 객체에 접근할 때 사용되는 프록시

### 프록시 패턴 적용시 고려

- 실체 객체를 누가 생성
- 접근 제어를 위한 목적으로 사용되는 보호 프록시는 객체가 생성될 때 실제 객체 전달
  - 실제 객체의 타입을 알 필요 없이 추상타입 사용
- 위임 기반 프록시 패턴은 데코레이션과 매우 유사
  - 차이 
    - 프록시 패턴 : 실제 객체에 대한 접근 제어
    - 데코레이션 : 기존 객체의 기능 확장의 의도
package javaEEDesignPattern.ch04_sington;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
@Startup // 시작과 동시에 싱글턴 생성, 이 애노테이션이 없다면 접근 시에 싱글톤을 생성한다
@DependsOn({"MyLogginBean","MyInitializationBean"})//싱글톤 시동 순서를 정한다
@Singleton
public class CacheSingletoneBean {
    
    private Map<Integer, String> myCache;
    
    @PostConstruct
    public void start() {
        Logger.getLogger("MyGlobalLogger").info("시작합니다");
        myCache = new HashMap<Integer, String>();
    }
    
    public void addUser(Integer id, String name) {
        myCache.put(id, name);
    }
    
    public String getName(Integer id) {
        return myCache.get(id);
    }
}

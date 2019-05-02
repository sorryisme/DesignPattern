package javaEEDesignPattern.ch04_sington;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
@Startup // 시작과 동시에 싱글턴 생성, 이 애노테이션이 없다면 접근 시에 싱글톤을 생성한다
@DependsOn("MyLogginBean")//싱글톤 시동 순서를 정한다
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)//동시성 관리
@Singleton
@AccessTimeout(value=120000) // (기본 밀리초) 타임아웃 설정
public class CacheSingletoneBean2 {
    
    private Map<Integer, String> myCache;
    
    @PostConstruct
    public void start() {
        Logger.getLogger("MyGlobalLogger").info("시작합니다");
        myCache = new HashMap<Integer, String>();
    }
    
    @AccessTimeout(value=30, unit=TimeUnit.SECONDS)
    @Lock(LockType.WRITE)
    public void addUser(Integer id, String name) {
        myCache.put(id, name);
    }
    
    @Lock(LockType.READ)
    public String getName(Integer id) {
        return myCache.get(id);
    }
}

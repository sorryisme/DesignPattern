package javaEEDesignPattern.ch03_facade;
/**
 * 세탁기
 * 기능 : 찌뜬 때, 조물조물
 * 잡다기능 : 물온도,탈수시간,표백제 투입여부
 * 퍼사드가 내부적으로 메서드를 호출해 처리
 * */
public class WashingMachine {
    
    public void heavliySolied() {
        /*
        setWaterTemperature(100);
        setWashCycleDuration(90);
        setSpinCycleDuration(10);
        addDetergent();
        addBleach();
        addFabricSofftener();
        heatWater();
        startWash();
         */
    }
    
    public void LightlySolied() {
        /*
         * setWaterTemperature(40);
         * setWashCycleDuration(20);
         * setSpinCycleDuration(10);
         * addDetergent();
         * heatWater();
         * startWash();
         * */
    }
}
// 퍼사드는 다음과 같이 사용한다
// new WashingMachine().lightlySolied();
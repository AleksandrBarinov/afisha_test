package util;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import static steps.BaseSteps.driver;

public class RunListener {
    public EventFiringWebDriver getEventDriver() {
        return eventDriver;
    }

    private EventFiringWebDriver eventDriver;
    private WebEventListener eventListener;

    public void runListener(){
        eventDriver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        eventDriver.register(eventListener);
    }


}

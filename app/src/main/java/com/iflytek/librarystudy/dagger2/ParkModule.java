package com.iflytek.librarystudy.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-05-29 17:09
 */
@Module
public class ParkModule {
    private String driver;
    private int seats;

    public ParkModule(String driver, int seats) {
        this.driver = driver;
        this.seats = seats;
    }

    @Provides
    public Bus providerBus() {
        return new Bus();
    }

    @Sign
    @SignLocal
    @Provides
    public Bus providerBusHasDriver() {
        return new Bus(driver, seats);
    }
}

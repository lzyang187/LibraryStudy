package com.iflytek.librarystudy.dagger2.thirdlib;

import com.iflytek.librarystudy.dagger2.thirdlib.bean.Human;
import com.iflytek.librarystudy.dagger2.thirdlib.bean.Soul;
import com.iflytek.librarystudy.dagger2.thirdlib.bean.Woman;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-10-21 11:35
 */
@Module
public class DaggerThirdLibActivityModule {
    @Provides
    Human humanProvides() {
        return new Human();
    }

    private int money;

    public DaggerThirdLibActivityModule(int money) {
        this.money = money;
    }

    @Provides
    Soul provideSoul() {
        Soul soul = new Soul();
        soul.money = money;
        return soul;
    }

    @Provides
    Woman provideWoman(Soul soul) {
        return new Woman(soul);
    }

}

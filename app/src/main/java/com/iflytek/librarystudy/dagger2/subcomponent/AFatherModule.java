package com.iflytek.librarystudy.dagger2.subcomponent;

import com.iflytek.librarystudy.dagger2.dependencies.Father;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-05-30 15:18
 */
@Module(subcomponents = AChildComponent.class)
public class AFatherModule {
    @Provides
    public Father provideFather() {
        return new Father();
    }
}

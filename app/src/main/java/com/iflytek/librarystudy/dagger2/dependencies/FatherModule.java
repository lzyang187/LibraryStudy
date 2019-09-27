package com.iflytek.librarystudy.dagger2.dependencies;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-05-30 14:48
 */
@Module
public class FatherModule {
    @Provides
    public Father provideFather() {
        return new Father();
    }
}

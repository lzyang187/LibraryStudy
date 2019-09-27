package com.iflytek.librarystudy.dagger2.dependencies;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-05-30 14:50
 */
@Module
public class ChildModule {

    @Provides
    public Child provideChild(Father father) {
        return new Child(father);
    }
}

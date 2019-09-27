package com.iflytek.librarystudy.dagger2.subcomponent;

import com.iflytek.librarystudy.dagger2.dependencies.Child;
import com.iflytek.librarystudy.dagger2.dependencies.Father;

import dagger.Module;
import dagger.Provides;

/**
 * @author: cyli8
 * @date: 2019-05-30 15:19
 */
@Module
public class AChildModule {
    @Provides
    public Child providerChild(Father father) {
        return new Child(father);
    }
}

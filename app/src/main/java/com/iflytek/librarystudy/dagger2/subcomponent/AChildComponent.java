package com.iflytek.librarystudy.dagger2.subcomponent;

import com.iflytek.librarystudy.dagger2.dependencies.ChildActivity;

import dagger.Subcomponent;

/**
 * @author: cyli8
 * @date: 2019-05-30 15:20
 */

@Subcomponent(modules = AChildModule.class)
public interface AChildComponent {
    void inject(ChildActivity activity);

    @Subcomponent.Builder
    interface Builder {
        AChildComponent build();
    }
}

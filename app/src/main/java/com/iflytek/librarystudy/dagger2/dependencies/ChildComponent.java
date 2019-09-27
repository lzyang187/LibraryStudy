package com.iflytek.librarystudy.dagger2.dependencies;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-05-30 14:52
 */
@Component(modules = ChildModule.class, dependencies = FatherComponent.class)
public interface ChildComponent {
    void inject(ChildActivity activity);
}

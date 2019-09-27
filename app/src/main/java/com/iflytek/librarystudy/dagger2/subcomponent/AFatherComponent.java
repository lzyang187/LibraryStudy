package com.iflytek.librarystudy.dagger2.subcomponent;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-05-30 15:24
 */
@Component(modules = AFatherModule.class)
public interface AFatherComponent {
    AChildComponent.Builder inject();
}

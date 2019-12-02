package com.iflytek.librarystudy.dagger2.thirdlib;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-10-21 11:36
 */
@Component(modules = DaggerThirdLibActivityModule.class)
public interface DaggerThirdLibComponent {
    void injectTo(DaggerThirdLibActivity activity);
}

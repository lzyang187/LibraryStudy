package com.iflytek.librarystudy.dagger2;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-10-21 11:22
 */
@Component
public interface Dagger2ActivityComponent {
    void injectTo(Dagger2Activity activity);
}

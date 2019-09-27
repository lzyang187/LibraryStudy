package com.iflytek.librarystudy.dagger2;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-05-29 15:42
 */
@SignLocal
@Component(modules = ParkModule.class)
public interface ParkComponent {
    void inject(ParkActivity activity);
}

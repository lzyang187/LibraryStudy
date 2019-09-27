package com.iflytek.librarystudy.dagger2.dependencies;

import dagger.Component;

/**
 * @author: cyli8
 * @date: 2019-05-30 14:49
 */
@Component(modules = FatherModule.class)
public interface FatherComponent {
    Father inject();
}

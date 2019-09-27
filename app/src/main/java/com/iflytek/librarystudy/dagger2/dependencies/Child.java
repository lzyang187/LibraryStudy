package com.iflytek.librarystudy.dagger2.dependencies;

/**
 * @author: cyli8
 * @date: 2019-05-30 14:50
 */
public class Child {
    private Father father;

    public Child(Father father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "Child不为空，Father为空吗？" + (father == null) + "  " + super.toString();
    }

}

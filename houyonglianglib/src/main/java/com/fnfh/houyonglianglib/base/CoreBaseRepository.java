package com.fnfh.houyonglianglib.base;


import com.fnfh.houyonglianglib.data.repository.Repository;

/**
 * Created by hpw on 16/11/1.
 */

public class CoreBaseRepository {
    @Override
    public Object clone() {
        Repository stu = null;
        try {
            stu = (Repository) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}

package com.fnfh.houyonglianglib.data.repository;

import android.database.Observable;


import com.fnfh.houyonglianglib.base.CoreBaseRepository;

import java.util.Map;

/**
 * Created by hpw on 16/11/1.
 */
public abstract class Repository<T> extends CoreBaseRepository {
    public T data;

    public Map<String, String> param;

    public abstract Observable<Data<T>> getPageAt(int page);
}

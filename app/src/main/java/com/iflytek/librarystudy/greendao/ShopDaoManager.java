package com.iflytek.librarystudy.greendao;

import com.iflytek.librarystudy.MyApplication;
import com.lzy.greendao.DaoSession;
import com.lzy.greendao.ShopDao;

import java.util.List;

/**
 * @author: cyli8
 * @date: 2019-09-26 17:37
 */
public class ShopDaoManager {

    private ShopDaoManager() {
        DaoSession daoSession = MyApplication.getDaoSession();
        if (daoSession != null) {
            mShopDao = daoSession.getShopDao();
        }
    }

    private static ShopDaoManager mInstance;
    private ShopDao mShopDao;

    public ShopDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (ShopDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new ShopDaoManager();
                }
            }
        }
        return mInstance;
    }

    public void insertOrReplace(Shop shop) {
        if (shop != null) {
            mShopDao.insertOrReplace(shop);
        }
    }

    public void delete(Shop shop) {
        if (shop != null) {
            mShopDao.delete(shop);
        }
    }

    public void update(Shop shop) {
        if (shop != null) {
            mShopDao.update(shop);
        }
    }

    public List<Shop> queryByType(int type) {
        return mShopDao.queryBuilder()
                .where(ShopDao.Properties.Type.eq(type))
                .orderDesc(ShopDao.Properties.CreateTime)
                .list();
    }

    public List<Shop> loadAll() {
        return mShopDao.loadAll();
    }

}

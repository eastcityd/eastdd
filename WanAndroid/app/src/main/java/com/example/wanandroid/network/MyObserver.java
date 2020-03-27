package com.example.wanandroid.network;

import android.content.Context;

import com.example.wanandroid.bean.ResultBean;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public abstract class MyObserver<T> implements Observer<T> {

    public abstract void succeed(T t);

    public abstract void failed(ResultBean resultBean);

    public abstract void error(Throwable e);

    private WeakReference<Context> weakReference;

    public MyObserver(Context context) {
        weakReference = new WeakReference<Context>(context);
    }

    private OnResultBeanCallBack onResultBeanCallBack;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    public MyObserver setOnResultBeanCallBack(OnResultBeanCallBack onResultBeanCallBack) {
        this.onResultBeanCallBack = onResultBeanCallBack;
        return this;
    }

    @Override
    public void onNext(T t) {
        if (null != t) {
            if (t instanceof ResultBean) {
                if (onResultBeanCallBack != null) {
                    onResultBeanCallBack.onReceive((ResultBean) t);
                }
                failed((ResultBean) t);
            } else {
                succeed(t);
            }
        } else {
            failed(new ResultBean("获取数据失败",0));
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        error(e);
    }

    public interface OnResultBeanCallBack {

        void onReceive(ResultBean resultBean);

    }
}

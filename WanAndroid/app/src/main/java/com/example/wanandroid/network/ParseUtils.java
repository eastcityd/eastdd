package com.example.wanandroid.network;

import android.content.Context;

import com.example.wanandroid.bean.ResultBean;
import com.example.wanandroid.utils.JSONUtil;
import com.example.wanandroid.utils.log;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import org.json.JSONObject;

public class ParseUtils<T> {

    private TypeAdapter<T> typeAdapter;

    public ParseUtils(TypeAdapter typeAdapter) {
        this.typeAdapter = typeAdapter;
    }

    private Context context;

    public T parseResult(String resultJson) {
        log.e("接口返回：" + resultJson);

        try {
            if (null != resultJson) {
                String _val = resultJson;

                JSONObject _json = new JSONObject(_val);
                ResultBean _baseBean = new ResultBean();
                _baseBean.code = _json.optInt("errorCode");
                _baseBean.msg = _json.optString("errorMsg");
                _baseBean.data = _json.optString("data");

                switch (_baseBean.code) {
                    // 执行成功
                    case 10000:
                        if (!JSONUtil.isEmpty(_baseBean.data)) {
                            T resultBean = typeAdapter.fromJson(_baseBean.data);
                            if (resultBean instanceof ResultBean) {
                                ((ResultBean) resultBean).code = _baseBean.code;
                                ((ResultBean) resultBean).msg = _baseBean.msg;
                                ((ResultBean) resultBean).data = _baseBean.data;
                            }
                            if (null != resultBean) {
                                return resultBean;
                            }
                        } else {
                            T resultBean = (T) _baseBean;
                            return resultBean;
                        }
                        break;
                    case 400: // 执行失败
                    case 500: // 内部异常
                    case 404: // 无此接口
//                        MyLog.i("tag", "接口返回异常信息：" + _baseBean.msg);
//                        showToastMsg( _baseBean.msg);
                        break;
                    case 1000: // 访问拒绝，用户未登录
                    case 1001: // 该当前账户已在其他地方登录,请重新登录
//                        MyLog.i("tag", "接口登录异常信息：" + _baseBean.msg);
//                        showToastMsg( _baseBean.msg);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

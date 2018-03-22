package com.rehome.huizhouxdj.base;

import android.content.Context;
import android.widget.Toast;

import com.rehome.huizhouxdj.weight.LoadDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ruihong on 2017/12/22.
 */

public abstract class BaseCallBack<T> implements Callback<T> {

    private Context context;

    private LoadDialog dialog;

    public BaseCallBack(Context context) {
        this.context = context;
        dialog = new LoadDialog(context, false, "正在加载中...");
        dialog.show();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        dialog.dismiss();
        onSuccess(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dialog.dismiss();
        Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        onError(call, t);
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onError(Call<T> call, Throwable t);
}

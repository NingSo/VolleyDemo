package com.ningso.volleydemo.toolbox;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ningso.volleydemo.Utils.ILog;
import com.ningso.volleydemo.Utils.VolleyErrorHelper;

/**
 * Created by NingSo on 15/6/10.
 */
public class MyErrorLisener implements Response.ErrorListener {
    private Context mContext;

    public MyErrorLisener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        VolleyErrorHelper.getMessage(error, mContext);
        ILog.e(error.getMessage());
    }
}

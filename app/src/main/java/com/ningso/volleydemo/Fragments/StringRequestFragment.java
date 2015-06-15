package com.ningso.volleydemo.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.ningso.volleydemo.R;
import com.ningso.volleydemo.Utils.StringUtil;
import com.ningso.volleydemo.app.Constact;
import com.ningso.volleydemo.app.VolleyApp;
import com.ningso.volleydemo.toolbox.MyErrorLisener;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by NingPingPing on 2015/6/10.
 */
public class StringRequestFragment extends BaseFragment {
    @InjectView(R.id.input_url)
    EditText inputUrl;
    @InjectView(R.id.send_http)
    Button sendHttp;
    @InjectView(R.id.show_content)
    TextView showContent;
    @InjectView(R.id.sendurl)
    TextView sendurl;
    @InjectView(R.id.send_http_withparams)
    Button sendHttpWithparams;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_stringobject, container, false);
        }
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.send_http)
    public void setButtonClick() {
        String url = StringUtil.preUrl(inputUrl.getText().toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showContent.setText(response);
            }
        }, new MyErrorLisener(getActivity()));
        sendurl.setText(url);
        VolleyApp.getInstance().addToRequestQueue(stringRequest);
    }

    @OnClick(R.id.send_http_withparams)
    public void setSendHttpWithParams() {
        String url = Constact.CDN_API_PREFIX + "/message/apps";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showContent.setText(response);
            }
        }, new MyErrorLisener(getActivity())) {
            //设置参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //设置需要post的参数
                Map<String, String> mapParams = new HashMap<String, String>();
                mapParams.put("channel", "tifen");
                mapParams.put("pkg", "com.yeuxue.tifenapp");
                return mapParams;
            }

            //设置请求头
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }

        };
        sendurl.setText(url + "?channel=tifen&pkg=com.yuexue.tifenapp");
        VolleyApp.getInstance().addToRequestQueue(stringRequest);
    }

    /**
     * 有的服务器不支持上面的传参格式
     */
    public void setSendWithParams() {
        String url = "http://www.imooc.com/api/teacher";
        //正常的http://www.imooc.com/api/teacher?type=4&num=10
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("type", "4");
        builder.appendQueryParameter("num", "10");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, builder.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showContent.setText(response);
            }
        }, new MyErrorLisener(getActivity()));
        sendurl.setText("http://www.imooc.com/api/teacher?type=4&num=10");
        VolleyApp.getInstance().addToRequestQueue(stringRequest);
    }

}

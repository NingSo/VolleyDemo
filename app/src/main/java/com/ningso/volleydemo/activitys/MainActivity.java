package com.ningso.volleydemo.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ningso.volleydemo.R;
import com.ningso.volleydemo.Utils.ILog;
import com.ningso.volleydemo.app.VolleyApp;
import com.ningso.volleydemo.toolbox.BitmapCache;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by NingPingPing on 2015/6/9.
 */
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.test)
    TextView test;
    @InjectView(R.id.networkimage)
    Button networkimage;
    @InjectView(R.id.json_request)
    Button jsonRequest;
    @InjectView(R.id.string_request)
    Button stringRequest;
    @InjectView(R.id.gson_response)
    Button gsonResponse;
    @InjectView(R.id.xml_response)
    Button xmlResponse;
    @InjectView(R.id.multipart_request)
    Button multipartRequest;
    @InjectView(R.id.ssl_connection)
    Button sslConnection;
    @InjectView(R.id.net_image)
    ImageView netImage;

    private String apiUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.inject(this);
        getStringRequest();
    }

    private void getStringRequest() {
        apiUrl = "http://www.imooc.com/api/teacher?";
        //正常的http://www.imooc.com/api/teacher?type=4&num=10
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ILog.d(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ILog.e(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "4");
                map.put("num", "10");
                return map;
            }
        };
        VolleyApp.getInstance().addToRequestQueue(stringRequest);
    }

    @OnClick(R.id.json_request)
    public void getJsonRequest() {
        apiUrl = "http://www.imooc.com/api/teacher";
        Uri.Builder builder = Uri.parse(apiUrl).buildUpon();
        builder.appendQueryParameter("type", "4");
        builder.appendQueryParameter("num", "10");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ILog.d(response.toString());
                test.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ILog.e(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "4");
                map.put("num", "10");
                return map;
            }
        };
        VolleyApp.getInstance().addToRequestQueue(jsonObjectRequest);
    }

//    @OnClick(R.id.networkimage)
//    public void getImageRequest() {
//        String imageUrl = "http://img.mukewang.com/552640c300018a9606000338.jpg";
//        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                netImage.setImageBitmap(response);
//            }
//        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                ILog.e(error.getMessage());
//            }
//        });
//        VolleyApp.getInstance().addToRequestQueue(imageRequest);
//    }

    @OnClick(R.id.networkimage)
    public void getImageLoadingRequest() {
        String imageUrl = "http://img.mukewang.com/552640c300018a9606000338.jpg";
        ImageLoader imageLoader = new ImageLoader(VolleyApp.getInstance().getRequestQueue(), new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(netImage, R.mipmap.tifen_logo, R.mipmap.tifen_icon);
        imageLoader.get(imageUrl, listener);
    }

}

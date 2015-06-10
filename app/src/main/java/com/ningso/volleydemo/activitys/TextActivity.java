package com.ningso.volleydemo.activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.ningso.volleydemo.R;
import com.ningso.volleydemo.Utils.ILog;
import com.ningso.volleydemo.app.Constact;
import com.ningso.volleydemo.app.VolleyApp;
import com.ningso.volleydemo.entity.RecApp;
import com.ningso.volleydemo.toolbox.BitmapCache;
import com.ningso.volleydemo.toolbox.GsonRequest;
import com.ningso.volleydemo.toolbox.MyErrorLisener;
import com.ningso.volleydemo.toolbox.NormalPostRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by NingSo on 15/6/10.
 */
public class TextActivity extends AppCompatActivity {

    @InjectView(R.id.iv_image)
    ImageView ivImage;
    @InjectView(R.id.iv_network)
    NetworkImageView ivNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
    }

    public void StringRequest() {
        StringRequest stringRequest = new StringRequest("http://www.ningdev.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ILog.i(response.toString());
            }
        }, new MyErrorLisener(this)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        VolleyApp.getInstance().addToRequestQueue(stringRequest);
    }

    public void ImageRequest() {
        String imageUrl = "http://img.mukewang.com/552640c300018a9606000338.jpg";
        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //TOdo get a bitmap
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.ARGB_8888, new MyErrorLisener(this));
        VolleyApp.getInstance().addToRequestQueue(imageRequest);
    }

    public void ImageLoadRequest() {
        String imageUrl = "http://img.mukewang.com/552640c300018a9606000338.jpg";
        ImageLoader imageLoader = new ImageLoader(VolleyApp.getInstance().getRequestQueue(), new BitmapCache());
        ImageLoader.ImageListener listener =
                ImageLoader.getImageListener(ivImage, R.mipmap.tifen_logo, R.mipmap.tifen_icon);
        imageLoader.get(imageUrl, listener);
    }

    public void NetWorkImageRequest() {

    }

    public void JsonObjectRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.imooc.com/api/teacher?type=4&num=10", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                response.toString();

            }
        }, new MyErrorLisener(this));
        VolleyApp.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void GsonRequest() {
        GsonRequest<RecApp> gsonRequest = new GsonRequest<RecApp>(Request.Method.GET, Constact.CDN_API_PREFIX, null, null, new Response.Listener<RecApp>() {
            @Override
            public void onResponse(RecApp response) {

            }
        }, new MyErrorLisener(this));
        VolleyApp.getInstance().addToRequestQueue(gsonRequest);

    }

    public void XmlRequest() {

    }

    public void NormalPostRequest() {
        HashMap<String, String> mMap = new HashMap<>();
        mMap.put("pkg", "");
        mMap.put("channel", "");
        NormalPostRequest normalPostRequest = new NormalPostRequest(Request.Method.GET, Constact.CDN_API_PREFIX, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ILog.i(response.toString());
            }
        }, new MyErrorLisener(this), mMap);
        VolleyApp.getInstance().addToRequestQueue(normalPostRequest);
    }


}

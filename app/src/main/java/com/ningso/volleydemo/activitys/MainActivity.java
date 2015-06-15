package com.ningso.volleydemo.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ningso.volleydemo.Fragments.ImageLoadFragment;
import com.ningso.volleydemo.Fragments.ImageRequestFragment;
import com.ningso.volleydemo.Fragments.JsonObjectFragment;
import com.ningso.volleydemo.Fragments.NetWorkImageFragment;
import com.ningso.volleydemo.Fragments.StringRequestFragment;
import com.ningso.volleydemo.R;
import com.ningso.volleydemo.Utils.ILog;
import com.ningso.volleydemo.app.VolleyApp;

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


    @InjectView(R.id.bt_stringrequest)
    Button btStringrequest;
    @InjectView(R.id.bt_imageruquest)
    Button btImageruquest;
    @InjectView(R.id.bt_imageload)
    Button btImageload;
    @InjectView(R.id.bt_networkimage)
    Button btNetworkimage;
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
    private String apiUrl;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.bt_stringrequest, R.id.bt_imageruquest, R.id.bt_imageload, R.id.bt_networkimage, R.id.json_request,})
    public void setVolleySelfObjectClick(View view) {
        int vId = view.getId();
        String tag = null;
        Fragment fragment = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (vId) {
            case R.id.bt_stringrequest:
                tag = StringRequestFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new StringRequestFragment();
                }
                break;
            case R.id.bt_imageruquest:
                tag = ImageRequestFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new ImageRequestFragment();
                }
                break;
            case R.id.bt_imageload:
                tag = ImageLoadFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new ImageLoadFragment();
                }
                break;
            case R.id.bt_networkimage:
                tag = NetWorkImageFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new NetWorkImageFragment();
                }
                break;
            case R.id.json_request:
                tag = JsonObjectFragment.class.getSimpleName();
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new JsonObjectFragment();
                }
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, tag).commit();
    }




    @OnClick(R.id.json_request)
    public void getJsonRequest() {
        apiUrl = "http://www.imooc.com/api/teacher";
        Uri.Builder builder = Uri.parse(apiUrl).buildUpon();
        builder.appendQueryParameter("type", "4");
        builder.appendQueryParameter("num", "10");

        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("type", "4");
        mapParams.put("num", "10");
        JSONObject jsonObject = new JSONObject(mapParams);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(apiUrl, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ILog.d(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ILog.e(error.getMessage() + error.getCause());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "4");
                map.put("num", "10");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
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

//    @OnClick(R.id.networkimage)
//    public void getImageLoadingRequest() {
////        String imageUrl = "http://img.mukewang.com/552640c300018a9606000338.jpg";
////        ImageLoader imageLoader = new ImageLoader(VolleyApp.getInstance().getRequestQueue(), new BitmapCache());
////        ImageLoader.ImageListener listener = ImageLoader.getImageListener(netImage, R.mipmap.tifen_logo, R.mipmap.tifen_icon);
////        imageLoader.get(imageUrl, listener);
//    }

}

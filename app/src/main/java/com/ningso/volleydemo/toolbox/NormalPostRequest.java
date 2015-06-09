package com.ningso.volleydemo.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by NingSo on 15/6/10.
 */
public class NormalPostRequest extends Request<JSONObject> {
    /**
     * Default charset for JSON request.
     */
    protected static final String PROTOCOL_CHARSET = "UTF-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private Map<String, String> mMap;
    private Listener<JSONObject> mListener;

    public NormalPostRequest(String url, Listener<JSONObject> mListener, Response.ErrorListener errorListener, Map<String, String> mMap) {
        this(Method.GET, url, mListener, errorListener, mMap);
    }

    public NormalPostRequest(int method, String url, Listener<JSONObject> mListener, Response.ErrorListener errorListener, Map<String, String> mMap) {
        super(method, url, errorListener);
        this.mListener = mListener;
        this.mMap = mMap;
    }

    //mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }
}

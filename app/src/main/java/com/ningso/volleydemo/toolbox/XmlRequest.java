package com.ningso.volleydemo.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by NingSo on 15/6/9.
 */
public class XmlRequest extends Request<XmlPullParser> {
    private final Response.Listener<XmlPullParser> mListener;

    /**
     * Creates a new request with the given method.
     *
     * @param method        the request {@link Method} to use
     * @param url           URL to fetch the XmlPullParser at
     * @param mListener     Listener to receive the XmlPullParser response
     * @param errorListener Error listener, or null to ignore errors
     */
    public XmlRequest(int method, String url, Response.Listener<XmlPullParser> mListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = mListener;
    }

    /**
     * Creates a new GET request.
     *
     * @param url           URL to fetch the XmlPullParser at
     * @param mListener     Listener to receive the XmlPullParser response
     * @param errorListener Error listener, or null to ignore errors
     */
    public XmlRequest(String url, Response.Listener<XmlPullParser> mListener, Response.ErrorListener errorListener) {
        this(Method.GET, url, mListener, errorListener);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }
}

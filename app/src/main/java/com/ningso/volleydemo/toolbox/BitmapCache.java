package com.ningso.volleydemo.toolbox;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Created by NingPingPing on 2015/6/9.
 */
public class BitmapCache implements ImageCache {

    private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
//        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
//        // LruCache通过构造函数传入缓存值，以KB为单位。
//        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        // 使用最大可用内存值的1/8作为缓存的大小。
//        int cacheSize = maxMemory / 8;

        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }

}

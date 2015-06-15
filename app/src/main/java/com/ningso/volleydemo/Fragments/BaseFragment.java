package com.ningso.volleydemo.Fragments;

import android.support.v4.app.Fragment;

import com.ningso.volleydemo.app.VolleyApp;

import butterknife.ButterKnife;

/**
 * Created by NingPingPing on 2015/6/15.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        ButterKnife.reset(this);
        VolleyApp.getInstance().cancelPendingRequests(this);
        super.onDestroy();
    }
}

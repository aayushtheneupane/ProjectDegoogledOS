package com.android.settings.network;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

public class MobileDataEnabledListener extends ContentObserver {
    private Client mClient;
    private Context mContext;
    private int mSubId = -1;

    public interface Client {
        void onMobileDataEnabledChange();
    }

    public MobileDataEnabledListener(Context context, Client client) {
        super(new Handler());
        this.mContext = context;
        this.mClient = client;
    }

    public void start(int i) {
        Uri uri;
        this.mSubId = i;
        if (this.mSubId == -1) {
            uri = Settings.Global.getUriFor("mobile_data");
        } else {
            uri = Settings.Global.getUriFor("mobile_data" + this.mSubId);
        }
        this.mContext.getContentResolver().registerContentObserver(uri, true, this);
    }

    public int getSubId() {
        return this.mSubId;
    }

    public MobileDataEnabledListener stop() {
        this.mContext.getContentResolver().unregisterContentObserver(this);
        return this;
    }

    public void onChange(boolean z) {
        this.mClient.onMobileDataEnabledChange();
    }
}

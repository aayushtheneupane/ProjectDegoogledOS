package com.android.settings.accounts;

import android.accounts.Account;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.settings.homepage.SettingsHomepageActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.net.URISyntaxException;
import java.util.List;

public class AvatarViewMixin implements LifecycleObserver {
    static final Intent INTENT_GET_ACCOUNT_DATA = new Intent("android.content.action.SETTINGS_ACCOUNT_DATA");
    private String mAccountName;
    private final ActivityManager mActivityManager = ((ActivityManager) this.mContext.getSystemService(ActivityManager.class));
    private final MutableLiveData<Bitmap> mAvatarImage;
    private final ImageView mAvatarView;
    private final Context mContext;

    public AvatarViewMixin(SettingsHomepageActivity settingsHomepageActivity, ImageView imageView) {
        this.mContext = settingsHomepageActivity.getApplicationContext();
        this.mAvatarView = imageView;
        this.mAvatarView.setOnClickListener(new View.OnClickListener(settingsHomepageActivity) {
            private final /* synthetic */ SettingsHomepageActivity f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                AvatarViewMixin.this.lambda$new$0$AvatarViewMixin(this.f$1, view);
            }
        });
        this.mAvatarImage = new MutableLiveData<>();
        this.mAvatarImage.observe(settingsHomepageActivity, new Observer(imageView) {
            private final /* synthetic */ ImageView f$0;

            {
                this.f$0 = r1;
            }

            public final void onChanged(Object obj) {
                this.f$0.setImageBitmap((Bitmap) obj);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AvatarViewMixin(SettingsHomepageActivity settingsHomepageActivity, View view) {
        try {
            Intent parseUri = Intent.parseUri(this.mContext.getResources().getString(C1715R.string.config_account_intent_uri), 1);
            if (!TextUtils.isEmpty(this.mAccountName)) {
                parseUri.putExtra("extra.accountName", this.mAccountName);
            }
            if (this.mContext.getPackageManager().queryIntentActivities(parseUri, 1048576).isEmpty()) {
                Log.w("AvatarViewMixin", "Cannot find any matching action VIEW_ACCOUNT intent.");
                return;
            }
            FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(0, 1643, 1502, (String) null, Integer.MIN_VALUE);
            settingsHomepageActivity.startActivity(parseUri);
        } catch (URISyntaxException e) {
            Log.w("AvatarViewMixin", "Error parsing avatar mixin intent, skipping", e);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_avatar_in_homepage)) {
            Log.d("AvatarViewMixin", "Feature disabled by config. Skipping");
        } else if (this.mActivityManager.isLowRamDevice()) {
            Log.d("AvatarViewMixin", "Feature disabled on low ram device. Skipping");
        } else if (hasAccount()) {
            loadAccount();
        } else {
            this.mAvatarView.setImageResource(C1715R.C1717drawable.ic_account_circle_24dp);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasAccount() {
        Account[] accounts = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider().getAccounts(this.mContext);
        return accounts != null && accounts.length > 0;
    }

    private void loadAccount() {
        String queryProviderAuthority = queryProviderAuthority();
        if (!TextUtils.isEmpty(queryProviderAuthority)) {
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(queryProviderAuthority) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AvatarViewMixin.this.lambda$loadAccount$2$AvatarViewMixin(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$loadAccount$2$AvatarViewMixin(String str) {
        Bundle call = this.mContext.getContentResolver().call(new Uri.Builder().scheme("content").authority(str).build(), "getAccountAvatar", (String) null, (Bundle) null);
        this.mAccountName = call.getString("account_name", "");
        this.mAvatarImage.postValue((Bitmap) call.getParcelable("account_avatar"));
    }

    /* access modifiers changed from: package-private */
    public String queryProviderAuthority() {
        List<ResolveInfo> queryIntentContentProviders = this.mContext.getPackageManager().queryIntentContentProviders(INTENT_GET_ACCOUNT_DATA, 1048576);
        if (queryIntentContentProviders.size() == 1) {
            return queryIntentContentProviders.get(0).providerInfo.authority;
        }
        Log.w("AvatarViewMixin", "The size of the provider is " + queryIntentContentProviders.size());
        return null;
    }
}

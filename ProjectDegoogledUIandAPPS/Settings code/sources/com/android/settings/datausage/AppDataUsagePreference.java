package com.android.settings.datausage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.AppItem;
import com.android.settingslib.net.UidDetail;
import com.android.settingslib.net.UidDetailProvider;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.apppreference.AppPreference;
import com.havoc.config.center.C1715R;

public class AppDataUsagePreference extends AppPreference {
    private UidDetail mDetail;
    private final AppItem mItem;
    private final int mPercent;

    public AppDataUsagePreference(Context context, AppItem appItem, int i, UidDetailProvider uidDetailProvider) {
        super(context);
        this.mItem = appItem;
        this.mPercent = i;
        if (!appItem.restricted || appItem.total > 0) {
            setSummary(DataUsageUtils.formatDataUsage(context, appItem.total));
        } else {
            setSummary((int) C1715R.string.data_usage_app_restricted);
        }
        this.mDetail = uidDetailProvider.getUidDetail(appItem.key, false);
        if (this.mDetail != null) {
            lambda$new$0$AppDataUsagePreference();
        } else {
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(uidDetailProvider) {
                private final /* synthetic */ UidDetailProvider f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AppDataUsagePreference.this.lambda$new$1$AppDataUsagePreference(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$1$AppDataUsagePreference(UidDetailProvider uidDetailProvider) {
        this.mDetail = uidDetailProvider.getUidDetail(this.mItem.key, true);
        ThreadUtils.postOnMainThread(new Runnable() {
            public final void run() {
                AppDataUsagePreference.this.lambda$new$0$AppDataUsagePreference();
            }
        });
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.findViewById(16908301);
        AppItem appItem = this.mItem;
        if (!appItem.restricted || appItem.total > 0) {
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(8);
        }
        progressBar.setProgress(this.mPercent);
    }

    /* access modifiers changed from: private */
    /* renamed from: setAppInfo */
    public void lambda$new$0$AppDataUsagePreference() {
        UidDetail uidDetail = this.mDetail;
        if (uidDetail != null) {
            setIcon(uidDetail.icon);
            setTitle(this.mDetail.label);
            return;
        }
        setIcon((Drawable) null);
        setTitle((CharSequence) null);
    }

    public AppItem getItem() {
        return this.mItem;
    }
}

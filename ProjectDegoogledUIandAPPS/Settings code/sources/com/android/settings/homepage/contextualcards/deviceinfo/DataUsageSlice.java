package com.android.settings.homepage.contextualcards.deviceinfo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.TextAppearanceSpan;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.SubSettings;
import com.android.settings.datausage.DataUsageSummary;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.net.DataUsageUtils;
import com.havoc.config.center.C1715R;
import java.util.concurrent.TimeUnit;

public class DataUsageSlice implements CustomSliceable {
    private static final long MILLIS_IN_A_DAY = TimeUnit.DAYS.toMillis(1);
    private final Context mContext;

    public void onNotifyChange(Intent intent) {
    }

    public DataUsageSlice(Context context) {
        this.mContext = context;
    }

    public Uri getUri() {
        return CustomSliceRegistry.DATA_USAGE_SLICE_URI;
    }

    public Slice getSlice() {
        DataUsageController.DataUsageInfo dataUsageInfo;
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_settings_data_usage);
        String string = this.mContext.getString(C1715R.string.data_usage_summary_title);
        boolean z = false;
        SliceAction createDeeplink = SliceAction.createDeeplink(getPrimaryAction(), createWithResource, 0, string);
        DataUsageController dataUsageController = new DataUsageController(this.mContext);
        SubscriptionManager subscriptionManager = (SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service");
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (Settings.System.getInt(this.mContext.getContentResolver(), "data_usage_period", 1) == 0) {
            z = true;
        }
        if (defaultDataSubscriptionId == -1) {
            dataUsageInfo = dataUsageController.getDataUsageInfo();
        } else if (z) {
            dataUsageInfo = dataUsageController.getDailyDataUsageInfo(DataUsageUtils.getMobileTemplate(this.mContext, defaultDataSubscriptionId));
        } else {
            dataUsageInfo = dataUsageController.getDataUsageInfo(DataUsageUtils.getMobileTemplate(this.mContext, defaultDataSubscriptionId));
        }
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.DATA_USAGE_SLICE_URI, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder();
        headerBuilder.setTitle(string);
        listBuilder.setHeader(headerBuilder);
        if (com.android.settings.datausage.DataUsageUtils.hasSim(this.mContext)) {
            ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
            rowBuilder.setTitle(getDataUsageText(dataUsageInfo));
            rowBuilder.setSubtitle(getCycleTime(dataUsageInfo));
            rowBuilder.setPrimaryAction(createDeeplink);
            listBuilder.addRow(rowBuilder);
        } else {
            ListBuilder.RowBuilder rowBuilder2 = new ListBuilder.RowBuilder();
            rowBuilder2.setTitle(this.mContext.getText(C1715R.string.no_sim_card));
            rowBuilder2.setPrimaryAction(createDeeplink);
            listBuilder.addRow(rowBuilder2);
        }
        return listBuilder.build();
    }

    public Intent getIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, DataUsageSummary.class.getName(), "", this.mContext.getText(C1715R.string.data_usage_wifi_title).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(CustomSliceRegistry.DATA_USAGE_SLICE_URI);
    }

    private PendingIntent getPrimaryAction() {
        return PendingIntent.getActivity(this.mContext, 0, getIntent(), 0);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public CharSequence getDataUsageText(DataUsageController.DataUsageInfo dataUsageInfo) {
        Formatter.BytesResult formatBytes = Formatter.formatBytes(this.mContext.getResources(), dataUsageInfo.usageLevel, 10);
        SpannableString spannableString = new SpannableString(formatBytes.value);
        spannableString.setSpan(new TextAppearanceSpan(this.mContext, 16973890), 0, spannableString.length(), 33);
        return TextUtils.expandTemplate(this.mContext.getText(C1715R.string.data_used_formatted), new CharSequence[]{spannableString, formatBytes.units});
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public CharSequence getCycleTime(DataUsageController.DataUsageInfo dataUsageInfo) {
        long currentTimeMillis = dataUsageInfo.cycleEnd - System.currentTimeMillis();
        if (currentTimeMillis <= 0) {
            return this.mContext.getString(C1715R.string.billing_cycle_none_left);
        }
        int i = (int) (currentTimeMillis / MILLIS_IN_A_DAY);
        if (i < 1) {
            return this.mContext.getString(C1715R.string.billing_cycle_less_than_one_day_left);
        }
        return this.mContext.getResources().getQuantityString(C1715R.plurals.billing_cycle_days_left, i, new Object[]{Integer.valueOf(i)});
    }
}

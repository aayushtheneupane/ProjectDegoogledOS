package com.android.settings.homepage.contextualcards.deviceinfo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.SubSettings;
import com.android.settings.deviceinfo.HardwareInfoPreferenceController;
import com.android.settings.deviceinfo.aboutphone.MyDeviceInfoFragment;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.DeviceInfoUtils;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import java.util.List;

public class DeviceInfoSlice implements CustomSliceable {
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class));

    public void onNotifyChange(Intent intent) {
    }

    public DeviceInfoSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_info_outline_24dp);
        String string = this.mContext.getString(C1715R.string.device_info_label);
        SliceAction createDeeplink = SliceAction.createDeeplink(getPrimaryAction(), createWithResource, 0, string);
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.DEVICE_INFO_SLICE_URI, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder();
        headerBuilder.setTitle(string);
        listBuilder.setHeader(headerBuilder);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(getPhoneNumber());
        rowBuilder.setSubtitle(getDeviceModel());
        rowBuilder.setPrimaryAction(createDeeplink);
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.DEVICE_INFO_SLICE_URI;
    }

    public Intent getIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, MyDeviceInfoFragment.class.getName(), "", this.mContext.getText(C1715R.string.device_info_label).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(CustomSliceRegistry.DEVICE_INFO_SLICE_URI);
    }

    private PendingIntent getPrimaryAction() {
        return PendingIntent.getActivity(this.mContext, 0, getIntent(), 0);
    }

    /* access modifiers changed from: package-private */
    public CharSequence getPhoneNumber() {
        SubscriptionInfo firstSubscriptionInfo = getFirstSubscriptionInfo();
        if (firstSubscriptionInfo == null) {
            return this.mContext.getString(C1715R.string.device_info_default);
        }
        String formattedPhoneNumber = DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, firstSubscriptionInfo);
        if (TextUtils.isEmpty(formattedPhoneNumber)) {
            return this.mContext.getString(C1715R.string.device_info_default);
        }
        return BidiFormatter.getInstance().unicodeWrap(formattedPhoneNumber, TextDirectionHeuristics.LTR);
    }

    private CharSequence getDeviceModel() {
        return HardwareInfoPreferenceController.getDeviceModel();
    }

    /* access modifiers changed from: package-private */
    public SubscriptionInfo getFirstSubscriptionInfo() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) {
            return null;
        }
        return (SubscriptionInfo) activeSubscriptionInfoList.get(0);
    }
}

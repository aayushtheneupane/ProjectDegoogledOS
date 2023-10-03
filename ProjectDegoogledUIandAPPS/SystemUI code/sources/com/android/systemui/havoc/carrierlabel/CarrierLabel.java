package com.android.systemui.havoc.carrierlabel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;

public class CarrierLabel extends TextView implements DarkIconDispatcher.DarkReceiver {
    /* access modifiers changed from: private */
    public static boolean isCN;
    private boolean mAttached;
    private Context mContext;
    private final BroadcastReceiver mIntentReceiver;

    public CarrierLabel(Context context) {
        this(context, (AttributeSet) null);
    }

    public CarrierLabel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarrierLabel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIntentReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.provider.Telephony.SPN_STRINGS_UPDATED".equals(action) || "android.intent.action.CUSTOM_CARRIER_LABEL".equals(action)) {
                    CarrierLabel.this.updateNetworkName(intent.getBooleanExtra("showSpn", true), intent.getStringExtra("spn"), intent.getBooleanExtra("showPlmn", false), intent.getStringExtra("plmn"));
                    boolean unused = CarrierLabel.isCN = Utils.isChineseLanguage();
                }
            }
        };
        this.mContext = context;
        updateNetworkName(true, (String) null, false, (String) null);
        if (Utils.hasNotch(this.mContext)) {
            Settings.System.putInt(this.mContext.getContentResolver(), "carrier_label_location", 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.provider.Telephony.SPN_STRINGS_UPDATED");
            intentFilter.addAction("android.intent.action.CUSTOM_CARRIER_LABEL");
            this.mContext.registerReceiver(this.mIntentReceiver, intentFilter, (String) null, getHandler());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
        if (this.mAttached) {
            this.mContext.unregisterReceiver(this.mIntentReceiver);
            this.mAttached = false;
        }
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        setTextColor(DarkIconDispatcher.getTint(rect, this, i));
    }

    /* access modifiers changed from: package-private */
    public void updateNetworkName(boolean z, String str, boolean z2, String str2) {
        boolean z3 = true;
        boolean z4 = z2 && !TextUtils.isEmpty(str2);
        if (!z || TextUtils.isEmpty(str)) {
            z3 = false;
        }
        if (!z3) {
            str = z4 ? str2 : "";
        }
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "custom_carrier_label", -2);
        if (!TextUtils.isEmpty(stringForUser)) {
            setText(stringForUser);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = getOperatorName();
        }
        setText(str);
    }

    private String getOperatorName() {
        String str;
        getContext().getString(C1784R$string.quick_settings_wifi_no_network);
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService("phone");
        if (isCN) {
            String networkOperator = telephonyManager.getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator)) {
                networkOperator = telephonyManager.getSimOperator();
            }
            str = new SpnOverride().getSpn(networkOperator);
        } else {
            str = telephonyManager.getNetworkOperatorName();
        }
        return TextUtils.isEmpty(str) ? telephonyManager.getSimOperatorName() : str;
    }
}

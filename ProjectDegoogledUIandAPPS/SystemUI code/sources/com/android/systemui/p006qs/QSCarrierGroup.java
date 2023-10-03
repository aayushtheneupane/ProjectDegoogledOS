package com.android.systemui.p006qs;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.keyguard.CarrierTextController;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.NetworkController;

/* renamed from: com.android.systemui.qs.QSCarrierGroup */
public class QSCarrierGroup extends LinearLayout implements CarrierTextController.CarrierTextCallback, NetworkController.SignalCallback, View.OnClickListener {
    private ActivityStarter mActivityStarter;
    private View[] mCarrierDividers;
    private QSCarrier[] mCarrierGroups;
    private CarrierTextController mCarrierTextController;
    private final CellSignalState[] mInfos;
    private boolean mListening;
    private final NetworkController mNetworkController;
    private TextView mNoSimTextView;

    public QSCarrierGroup(Context context, AttributeSet attributeSet, NetworkController networkController, ActivityStarter activityStarter) {
        super(context, attributeSet);
        this.mCarrierDividers = new View[2];
        this.mCarrierGroups = new QSCarrier[3];
        this.mInfos = new CellSignalState[3];
        this.mNetworkController = networkController;
        this.mActivityStarter = activityStarter;
    }

    public QSCarrierGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (NetworkController) Dependency.get(NetworkController.class), (ActivityStarter) Dependency.get(ActivityStarter.class));
    }

    public void onClick(View view) {
        if (view.isVisibleToUser()) {
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.WIRELESS_SETTINGS"), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mCarrierGroups[0] = (QSCarrier) findViewById(C1777R$id.carrier1);
        this.mCarrierGroups[1] = (QSCarrier) findViewById(C1777R$id.carrier2);
        this.mCarrierGroups[2] = (QSCarrier) findViewById(C1777R$id.carrier3);
        this.mCarrierDividers[0] = findViewById(C1777R$id.qs_carrier_divider1);
        this.mCarrierDividers[1] = findViewById(C1777R$id.qs_carrier_divider2);
        this.mNoSimTextView = (TextView) findViewById(C1777R$id.no_carrier_text);
        for (int i = 0; i < 3; i++) {
            this.mInfos[i] = new CellSignalState();
            this.mCarrierGroups[i].setOnClickListener(this);
        }
        this.mNoSimTextView.setOnClickListener(this);
        this.mCarrierTextController = new CarrierTextController(this.mContext, this.mContext.getString(17040268), false, false);
        setImportantForAccessibility(1);
    }

    public void setListening(boolean z) {
        if (z != this.mListening) {
            this.mListening = z;
            updateListeners();
        }
    }

    public void onDetachedFromWindow() {
        setListening(false);
        super.onDetachedFromWindow();
    }

    private void updateListeners() {
        if (this.mListening) {
            if (this.mNetworkController.hasVoiceCallingFeature()) {
                this.mNetworkController.addCallback(this);
            }
            this.mCarrierTextController.setListening(this);
            return;
        }
        this.mNetworkController.removeCallback(this);
        this.mCarrierTextController.setListening((CarrierTextController.CarrierTextCallback) null);
    }

    private void handleUpdateState() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            this.mCarrierGroups[i2].updateState(this.mInfos[i2]);
        }
        View view = this.mCarrierDividers[0];
        CellSignalState[] cellSignalStateArr = this.mInfos;
        view.setVisibility((!cellSignalStateArr[0].visible || !cellSignalStateArr[1].visible) ? 8 : 0);
        View view2 = this.mCarrierDividers[1];
        CellSignalState[] cellSignalStateArr2 = this.mInfos;
        if (!cellSignalStateArr2[1].visible || !cellSignalStateArr2[2].visible) {
            CellSignalState[] cellSignalStateArr3 = this.mInfos;
            if (!cellSignalStateArr3[0].visible || !cellSignalStateArr3[2].visible) {
                i = 8;
            }
        }
        view2.setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public int getSlotIndex(int i) {
        return SubscriptionManager.getSlotIndex(i);
    }

    public void updateCarrierInfo(CarrierTextController.CarrierTextCallbackInfo carrierTextCallbackInfo) {
        this.mNoSimTextView.setVisibility(8);
        if (carrierTextCallbackInfo.airplaneMode || !carrierTextCallbackInfo.anySimReady) {
            for (int i = 0; i < 3; i++) {
                this.mInfos[i].visible = false;
                this.mCarrierGroups[i].setCarrierText("");
                this.mCarrierGroups[i].setVisibility(8);
            }
            this.mNoSimTextView.setText(carrierTextCallbackInfo.carrierText);
            this.mNoSimTextView.setVisibility(0);
        } else {
            boolean[] zArr = new boolean[3];
            if (carrierTextCallbackInfo.listOfCarriers.length == carrierTextCallbackInfo.subscriptionIds.length) {
                int i2 = 0;
                while (i2 < 3 && i2 < carrierTextCallbackInfo.listOfCarriers.length) {
                    int slotIndex = getSlotIndex(carrierTextCallbackInfo.subscriptionIds[i2]);
                    if (slotIndex >= 3) {
                        Log.w("QSCarrierGroup", "updateInfoCarrier - slot: " + slotIndex);
                    } else if (slotIndex == -1) {
                        Log.e("QSCarrierGroup", "Invalid SIM slot index for subscription: " + carrierTextCallbackInfo.subscriptionIds[i2]);
                    } else {
                        this.mInfos[slotIndex].visible = true;
                        zArr[slotIndex] = true;
                        this.mCarrierGroups[slotIndex].setCarrierText(carrierTextCallbackInfo.listOfCarriers[i2].toString().trim());
                        this.mCarrierGroups[slotIndex].setVisibility(0);
                    }
                    i2++;
                }
                for (int i3 = 0; i3 < 3; i3++) {
                    if (!zArr[i3]) {
                        this.mInfos[i3].visible = false;
                        this.mCarrierGroups[i3].setVisibility(8);
                    }
                }
            } else {
                Log.e("QSCarrierGroup", "Carrier information arrays not of same length");
            }
        }
        handleUpdateState();
    }

    public void setMobileDataIndicators(NetworkController.IconState iconState, NetworkController.IconState iconState2, int i, int i2, boolean z, boolean z2, int i3, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, boolean z3, int i4, boolean z4) {
        int slotIndex = getSlotIndex(i4);
        if (slotIndex >= 3) {
            Log.w("QSCarrierGroup", "setMobileDataIndicators - slot: " + slotIndex);
        } else if (slotIndex == -1) {
            Log.e("QSCarrierGroup", "Invalid SIM slot index for subscription: " + i4);
        } else {
            CellSignalState[] cellSignalStateArr = this.mInfos;
            cellSignalStateArr[slotIndex].visible = iconState.visible;
            cellSignalStateArr[slotIndex].mobileSignalIconId = iconState.icon;
            cellSignalStateArr[slotIndex].contentDescription = iconState.contentDescription;
            cellSignalStateArr[slotIndex].typeContentDescription = charSequence.toString();
            this.mInfos[slotIndex].roaming = z4;
            handleUpdateState();
        }
    }

    public void setNoSims(boolean z, boolean z2) {
        if (z) {
            for (int i = 0; i < 3; i++) {
                this.mInfos[i].visible = false;
            }
        }
        handleUpdateState();
    }

    /* renamed from: com.android.systemui.qs.QSCarrierGroup$CellSignalState */
    static final class CellSignalState {
        String contentDescription;
        int mobileSignalIconId;
        boolean roaming;
        String typeContentDescription;
        boolean visible;

        CellSignalState() {
        }
    }
}

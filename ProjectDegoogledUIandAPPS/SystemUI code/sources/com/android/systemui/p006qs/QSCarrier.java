package com.android.systemui.p006qs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.DualToneHandler;
import com.android.systemui.p006qs.QSCarrierGroup;

/* renamed from: com.android.systemui.qs.QSCarrier */
public class QSCarrier extends LinearLayout {
    private TextView mCarrierText;
    private float mColorForegroundIntensity;
    private ColorStateList mColorForegroundStateList;
    private DualToneHandler mDualToneHandler;
    private View mMobileGroup;
    private ImageView mMobileRoaming;
    private ImageView mMobileSignal;

    public QSCarrier(Context context) {
        super(context);
    }

    public QSCarrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QSCarrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public QSCarrier(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDualToneHandler = new DualToneHandler(getContext());
        this.mMobileGroup = findViewById(C1777R$id.mobile_combo);
        this.mMobileSignal = (ImageView) findViewById(C1777R$id.mobile_signal);
        this.mMobileRoaming = (ImageView) findViewById(C1777R$id.mobile_roaming);
        this.mCarrierText = (TextView) findViewById(C1777R$id.qs_carrier_text);
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(this.mContext, 16842800);
        this.mColorForegroundStateList = ColorStateList.valueOf(colorAttrDefaultColor);
        this.mColorForegroundIntensity = QuickStatusBarHeader.getColorIntensity(colorAttrDefaultColor);
    }

    public void updateState(QSCarrierGroup.CellSignalState cellSignalState) {
        int i = 0;
        this.mMobileGroup.setVisibility(cellSignalState.visible ? 0 : 8);
        if (cellSignalState.visible) {
            ImageView imageView = this.mMobileRoaming;
            if (!cellSignalState.roaming) {
                i = 8;
            }
            imageView.setVisibility(i);
            ColorStateList valueOf = ColorStateList.valueOf(this.mDualToneHandler.getSingleColor(this.mColorForegroundIntensity));
            this.mMobileRoaming.setImageTintList(valueOf);
            this.mMobileSignal.setImageDrawable(new SignalDrawable(this.mContext));
            this.mMobileSignal.setImageTintList(valueOf);
            this.mMobileSignal.setImageLevel(cellSignalState.mobileSignalIconId);
            StringBuilder sb = new StringBuilder();
            String str = cellSignalState.contentDescription;
            if (str != null) {
                sb.append(str);
                sb.append(", ");
            }
            if (cellSignalState.roaming) {
                sb.append(this.mContext.getString(C1784R$string.data_connection_roaming));
                sb.append(", ");
            }
            if (hasValidTypeContentDescription(cellSignalState.typeContentDescription)) {
                sb.append(cellSignalState.typeContentDescription);
            }
            this.mMobileSignal.setContentDescription(sb);
        }
    }

    private boolean hasValidTypeContentDescription(String str) {
        return TextUtils.equals(str, this.mContext.getString(C1784R$string.data_connection_no_internet)) || TextUtils.equals(str, this.mContext.getString(C1784R$string.cell_data_off_content_description)) || TextUtils.equals(str, this.mContext.getString(C1784R$string.not_default_data_content_description));
    }

    public void setCarrierText(CharSequence charSequence) {
        this.mCarrierText.setText(charSequence);
    }
}

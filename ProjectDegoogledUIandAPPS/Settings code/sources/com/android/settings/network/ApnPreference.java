package com.android.settings.network;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class ApnPreference extends Preference implements CompoundButton.OnCheckedChangeListener {
    private static CompoundButton mCurrentChecked;
    private static String mSelectedKey;
    private boolean mHideDetails;
    private boolean mProtectFromCheckedChange;
    private boolean mSelectable;
    private int mSubId;

    public ApnPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSubId = -1;
        this.mProtectFromCheckedChange = false;
        this.mSelectable = true;
        this.mHideDetails = false;
    }

    public ApnPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1715R.attr.apnPreferenceStyle);
    }

    public ApnPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.apn_radiobutton);
        if (findViewById != null && (findViewById instanceof RadioButton)) {
            RadioButton radioButton = (RadioButton) findViewById;
            if (this.mSelectable) {
                radioButton.setOnCheckedChangeListener(this);
                boolean equals = getKey().equals(mSelectedKey);
                if (equals) {
                    mCurrentChecked = radioButton;
                    mSelectedKey = getKey();
                }
                this.mProtectFromCheckedChange = true;
                radioButton.setChecked(equals);
                this.mProtectFromCheckedChange = false;
                radioButton.setVisibility(0);
                return;
            }
            radioButton.setVisibility(8);
        }
    }

    public void setChecked() {
        mSelectedKey = getKey();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Log.i("ApnPreference", "ID: " + getKey() + " :" + z);
        if (!this.mProtectFromCheckedChange) {
            if (z) {
                CompoundButton compoundButton2 = mCurrentChecked;
                if (compoundButton2 != null) {
                    compoundButton2.setChecked(false);
                }
                mCurrentChecked = compoundButton;
                mSelectedKey = getKey();
                callChangeListener(mSelectedKey);
                return;
            }
            mCurrentChecked = null;
            mSelectedKey = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        super.onClick();
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (this.mHideDetails) {
            Toast.makeText(context, context.getString(C1715R.string.cannot_change_apn_toast), 1).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.EDIT", ContentUris.withAppendedId(Telephony.Carriers.CONTENT_URI, (long) Integer.parseInt(getKey())));
        intent.putExtra("sub_id", this.mSubId);
        context.startActivity(intent);
    }

    public void setSelectable(boolean z) {
        this.mSelectable = z;
    }

    public void setSubId(int i) {
        this.mSubId = i;
    }

    public void setHideDetails() {
        this.mHideDetails = true;
    }
}

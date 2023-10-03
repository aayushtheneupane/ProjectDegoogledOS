package com.android.settings.datausage;

import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.net.NetworkTemplate;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.Utils;
import com.android.settings.datausage.TemplatePreference;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.havoc.config.center.C1715R;
import java.util.List;

public class CellDataPreference extends CustomDialogPreferenceCompat implements TemplatePreference {
    public boolean mChecked;
    private final DataStateListener mDataStateListener = new DataStateListener() {
        public void onChange(boolean z) {
            CellDataPreference.this.updateChecked();
        }
    };
    public boolean mMultiSimDialog;
    final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() {
        public void onSubscriptionsChanged() {
            CellDataPreference.this.updateEnabled();
        }
    };
    public int mSubId = -1;
    SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public CellDataPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, C1715R.attr.switchPreferenceStyle, 16843629));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        CellDataState cellDataState = (CellDataState) parcelable;
        super.onRestoreInstanceState(cellDataState.getSuperState());
        this.mTelephonyManager = TelephonyManager.from(getContext());
        this.mChecked = cellDataState.mChecked;
        this.mMultiSimDialog = cellDataState.mMultiSimDialog;
        if (this.mSubId == -1) {
            this.mSubId = cellDataState.mSubId;
            setKey(getKey() + this.mSubId);
        }
        notifyChanged();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        CellDataState cellDataState = new CellDataState(super.onSaveInstanceState());
        cellDataState.mChecked = this.mChecked;
        cellDataState.mMultiSimDialog = this.mMultiSimDialog;
        cellDataState.mSubId = this.mSubId;
        return cellDataState;
    }

    public void onAttached() {
        super.onAttached();
        this.mDataStateListener.setListener(true, this.mSubId, getContext());
        SubscriptionManager subscriptionManager = this.mSubscriptionManager;
        if (subscriptionManager != null) {
            subscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        }
    }

    public void onDetached() {
        this.mDataStateListener.setListener(false, this.mSubId, getContext());
        SubscriptionManager subscriptionManager = this.mSubscriptionManager;
        if (subscriptionManager != null) {
            subscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        }
        super.onDetached();
    }

    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
        if (i != -1) {
            this.mSubscriptionManager = SubscriptionManager.from(getContext());
            this.mTelephonyManager = TelephonyManager.from(getContext());
            this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
            if (this.mSubId == -1) {
                this.mSubId = i;
                setKey(getKey() + i);
            }
            updateEnabled();
            updateChecked();
            return;
        }
        throw new IllegalArgumentException("CellDataPreference needs a SubscriptionInfo");
    }

    /* access modifiers changed from: private */
    public void updateChecked() {
        setChecked(this.mTelephonyManager.getDataEnabled(this.mSubId));
    }

    /* access modifiers changed from: private */
    public void updateEnabled() {
        setEnabled(this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId) != null);
    }

    /* access modifiers changed from: protected */
    public void performClick(View view) {
        Context context = getContext();
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 178, !this.mChecked);
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (this.mChecked) {
            if (!Utils.showSimCardTile(getContext()) || !(defaultDataSubscriptionInfo == null || activeSubscriptionInfo == null || activeSubscriptionInfo.getSubscriptionId() != defaultDataSubscriptionInfo.getSubscriptionId())) {
                setMobileDataEnabled(false);
                if (defaultDataSubscriptionInfo != null && activeSubscriptionInfo != null && activeSubscriptionInfo.getSubscriptionId() == defaultDataSubscriptionInfo.getSubscriptionId()) {
                    disableDataForOtherSubscriptions(this.mSubId);
                    return;
                }
                return;
            }
            this.mMultiSimDialog = false;
            super.performClick(view);
        } else if (Utils.showSimCardTile(getContext())) {
            this.mMultiSimDialog = true;
            if (defaultDataSubscriptionInfo == null || activeSubscriptionInfo == null || activeSubscriptionInfo.getSubscriptionId() != defaultDataSubscriptionInfo.getSubscriptionId()) {
                super.performClick(view);
                return;
            }
            setMobileDataEnabled(true);
            disableDataForOtherSubscriptions(this.mSubId);
        } else {
            setMobileDataEnabled(true);
        }
    }

    private void setMobileDataEnabled(boolean z) {
        this.mTelephonyManager.setDataEnabled(this.mSubId, z);
        setChecked(z);
    }

    private void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            notifyChanged();
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908352);
        findViewById.setClickable(false);
        ((Checkable) findViewById).setChecked(this.mChecked);
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        if (this.mMultiSimDialog) {
            showMultiSimDialog(builder, onClickListener);
        } else {
            showDisableDialog(builder, onClickListener);
        }
    }

    private void showDisableDialog(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        builder.setTitle((CharSequence) null);
        builder.setMessage((int) C1715R.string.data_usage_disable_mobile);
        builder.setPositiveButton(17039370, onClickListener);
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
    }

    private void showMultiSimDialog(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        String str;
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            str = getContext().getResources().getString(C1715R.string.sim_selection_required_pref);
        } else {
            str = defaultDataSubscriptionInfo.getDisplayName().toString();
        }
        builder.setTitle((int) C1715R.string.sim_change_data_title);
        Context context = getContext();
        Object[] objArr = new Object[2];
        objArr[0] = String.valueOf(activeSubscriptionInfo != null ? activeSubscriptionInfo.getDisplayName() : null);
        objArr[1] = str;
        builder.setMessage((CharSequence) context.getString(C1715R.string.sim_change_data_message, objArr));
        builder.setPositiveButton((int) C1715R.string.okay, onClickListener);
        builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
    }

    private void disableDataForOtherSubscriptions(int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSubscriptionId() != i) {
                    this.mTelephonyManager.setDataEnabled(subscriptionInfo.getSubscriptionId(), false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            if (this.mMultiSimDialog) {
                this.mSubscriptionManager.setDefaultDataSubId(this.mSubId);
                setMobileDataEnabled(true);
                disableDataForOtherSubscriptions(this.mSubId);
                return;
            }
            setMobileDataEnabled(false);
        }
    }

    public static abstract class DataStateListener extends ContentObserver {
        public DataStateListener() {
            super(new Handler(Looper.getMainLooper()));
        }

        public void setListener(boolean z, int i, Context context) {
            if (z) {
                Uri uriFor = Settings.Global.getUriFor("mobile_data");
                if (TelephonyManager.getDefault().getSimCount() != 1) {
                    uriFor = Settings.Global.getUriFor("mobile_data" + i);
                }
                context.getContentResolver().registerContentObserver(uriFor, false, this);
                return;
            }
            context.getContentResolver().unregisterContentObserver(this);
        }
    }

    public static class CellDataState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<CellDataState> CREATOR = new Parcelable.Creator<CellDataState>() {
            public CellDataState createFromParcel(Parcel parcel) {
                return new CellDataState(parcel);
            }

            public CellDataState[] newArray(int i) {
                return new CellDataState[i];
            }
        };
        public boolean mChecked;
        public boolean mMultiSimDialog;
        public int mSubId;

        public CellDataState(Parcelable parcelable) {
            super(parcelable);
        }

        public CellDataState(Parcel parcel) {
            super(parcel);
            boolean z = true;
            this.mChecked = parcel.readByte() != 0;
            this.mMultiSimDialog = parcel.readByte() == 0 ? false : z;
            this.mSubId = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.mChecked ? (byte) 1 : 0);
            parcel.writeByte(this.mMultiSimDialog ? (byte) 1 : 0);
            parcel.writeInt(this.mSubId);
        }
    }
}

package com.android.voicemail.impl;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.VoicemailContract;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;

public class VoicemailStatus$Editor {
    private final Context context;
    private final PhoneAccountHandle phoneAccountHandle;
    private ContentValues values = new ContentValues();

    /* synthetic */ VoicemailStatus$Editor(Context context2, PhoneAccountHandle phoneAccountHandle2, VoicemailStatus$1 voicemailStatus$1) {
        this.context = context2;
        this.phoneAccountHandle = phoneAccountHandle2;
        if (this.phoneAccountHandle == null) {
            VvmLog.m46w("VvmStatus", "VoicemailStatus.Editor created with null phone account, status will not be written");
        }
    }

    public boolean apply() {
        PhoneAccountHandle phoneAccountHandle2 = this.phoneAccountHandle;
        if (phoneAccountHandle2 == null) {
            return false;
        }
        this.values.put("phone_account_component_name", phoneAccountHandle2.getComponentName().flattenToString());
        this.values.put("phone_account_id", this.phoneAccountHandle.getId());
        try {
            StrictModeUtils.bypass(new Supplier(this.context.getContentResolver(), VoicemailContract.Status.buildSourceUri(this.context.getPackageName())) {
                private final /* synthetic */ ContentResolver f$1;
                private final /* synthetic */ Uri f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object get() {
                    return VoicemailStatus$Editor.this.lambda$apply$0$VoicemailStatus$Editor(this.f$1, this.f$2);
                }
            });
            this.values.clear();
            return true;
        } catch (IllegalArgumentException e) {
            VvmLog.m44e("VvmStatus", "apply :: failed to insert content resolver ", e);
            this.values.clear();
            return false;
        }
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    public ContentValues getValues() {
        return this.values;
    }

    public /* synthetic */ Uri lambda$apply$0$VoicemailStatus$Editor(ContentResolver contentResolver, Uri uri) {
        return contentResolver.insert(uri, this.values);
    }

    public VoicemailStatus$Editor setConfigurationState(int i) {
        this.values.put("configuration_state", Integer.valueOf(i));
        return this;
    }

    public VoicemailStatus$Editor setDataChannelState(int i) {
        this.values.put("data_channel_state", Integer.valueOf(i));
        return this;
    }

    public VoicemailStatus$Editor setNotificationChannelState(int i) {
        this.values.put("notification_channel_state", Integer.valueOf(i));
        return this;
    }

    public VoicemailStatus$Editor setQuota(int i, int i2) {
        if (i == -1 && i2 == -1) {
            return this;
        }
        this.values.put("quota_occupied", Integer.valueOf(i));
        this.values.put("quota_total", Integer.valueOf(i2));
        return this;
    }

    public VoicemailStatus$Editor setType(String str) {
        this.values.put("source_type", str);
        return this;
    }
}

package com.android.settings.notification;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.preference.Preference;
import com.android.settings.RingtonePreference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.ThreadUtils;

public abstract class RingtonePreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    public abstract int getRingtoneType();

    public boolean handlePreferenceTreeClick(Preference preference) {
        return false;
    }

    public boolean isAvailable() {
        return true;
    }

    public RingtonePreferenceControllerBase(Context context) {
        super(context);
    }

    public void updateState(Preference preference) {
        ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(preference) {
            private final /* synthetic */ Preference f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                RingtonePreferenceControllerBase.this.lambda$updateState$0$RingtonePreferenceControllerBase(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: updateSummary */
    public void lambda$updateState$0$RingtonePreferenceControllerBase(Preference preference) {
        String str;
        Uri actualDefaultRingtoneUriForPhoneAccountHandle = RingtoneManager.getActualDefaultRingtoneUriForPhoneAccountHandle(this.mContext, getRingtoneType(), ((RingtonePreference) preference).getPhoneAccountHandle());
        if (actualDefaultRingtoneUriForPhoneAccountHandle == null) {
            str = this.mContext.getString(17041021);
        } else {
            str = Ringtone.getTitle(this.mContext, actualDefaultRingtoneUriForPhoneAccountHandle, false, true);
        }
        if (str != null) {
            ThreadUtils.postOnMainThread(new Runnable(str) {
                private final /* synthetic */ CharSequence f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Preference.this.setSummary(this.f$1);
                }
            });
        }
    }
}

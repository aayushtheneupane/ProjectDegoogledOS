package com.android.settings.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.notification.NotificationAssistantPicker;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.widget.CandidateInfo;

public class NotificationAssistantPreferenceController extends BasePreferenceController {
    protected NotificationBackend mNotificationBackend = new NotificationBackend();
    private PackageManager mPackageManager = this.mContext.getPackageManager();

    public int getAvailabilityStatus() {
        return 0;
    }

    public NotificationAssistantPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        CandidateInfo candidateNone = new NotificationAssistantPicker.CandidateNone(this.mContext);
        ComponentName allowedNotificationAssistant = this.mNotificationBackend.getAllowedNotificationAssistant();
        if (allowedNotificationAssistant != null) {
            candidateNone = createCandidateInfo(allowedNotificationAssistant);
        }
        return candidateNone.loadLabel();
    }

    /* access modifiers changed from: protected */
    public CandidateInfo createCandidateInfo(ComponentName componentName) {
        return new DefaultAppInfo(this.mContext, this.mPackageManager, UserHandle.myUserId(), componentName);
    }
}

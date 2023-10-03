package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import java.util.List;

public class AssistantCapabilityPreferenceController extends TogglePreferenceController {
    static final String PRIORITIZER_KEY = "asst_capability_prioritizer";
    static final String SMART_KEY = "asst_capabilities_actions_replies";
    private NotificationBackend mBackend = new NotificationBackend();

    public AssistantCapabilityPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            updateState(findPreference);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBackend(NotificationBackend notificationBackend) {
        this.mBackend = notificationBackend;
    }

    public boolean isChecked() {
        List<String> assistantAdjustments = this.mBackend.getAssistantAdjustments(this.mContext.getPackageName());
        if (PRIORITIZER_KEY.equals(getPreferenceKey())) {
            return assistantAdjustments.contains("key_importance");
        }
        if (!SMART_KEY.equals(getPreferenceKey()) || !assistantAdjustments.contains("key_contextual_actions") || !assistantAdjustments.contains("key_text_replies")) {
            return false;
        }
        return true;
    }

    public boolean setChecked(boolean z) {
        if (PRIORITIZER_KEY.equals(getPreferenceKey())) {
            this.mBackend.allowAssistantAdjustment("key_importance", z);
            return true;
        } else if (!SMART_KEY.equals(getPreferenceKey())) {
            return true;
        } else {
            this.mBackend.allowAssistantAdjustment("key_contextual_actions", z);
            this.mBackend.allowAssistantAdjustment("key_text_replies", z);
            return true;
        }
    }

    public int getAvailabilityStatus() {
        return this.mBackend.getAllowedNotificationAssistant() != null ? 0 : 5;
    }
}

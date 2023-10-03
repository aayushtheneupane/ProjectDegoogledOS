package com.android.dialer.spam.stub;

import com.android.dialer.spam.SpamSettings;

public class SpamSettingsStub implements SpamSettings {
    public boolean isDialogEnabledForSpamNotification() {
        return false;
    }

    public boolean isDialogReportSpamCheckedByDefault() {
        return false;
    }

    public boolean isSpamBlockingEnabled() {
        return false;
    }

    public boolean isSpamEnabled() {
        return false;
    }

    public boolean isSpamNotificationEnabled() {
        return false;
    }

    public void modifySpamBlockingSetting(boolean z, SpamSettings.ModifySettingListener modifySettingListener) {
        modifySettingListener.onComplete(false);
    }
}

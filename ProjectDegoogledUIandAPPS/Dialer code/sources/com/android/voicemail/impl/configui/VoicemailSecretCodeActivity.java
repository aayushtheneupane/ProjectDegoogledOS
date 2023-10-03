package com.android.voicemail.impl.configui;

import android.content.Intent;
import android.preference.PreferenceActivity;
import java.util.List;

public class VoicemailSecretCodeActivity extends PreferenceActivity {
    private PreferenceActivity.Header syncHeader;

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return true;
    }

    public void onBuildHeaders(List<PreferenceActivity.Header> list) {
        super.onBuildHeaders(list);
        this.syncHeader = new PreferenceActivity.Header();
        PreferenceActivity.Header header = this.syncHeader;
        header.title = "Sync";
        list.add(header);
        PreferenceActivity.Header header2 = new PreferenceActivity.Header();
        header2.fragment = ConfigOverrideFragment.class.getName();
        header2.title = "VVM config override";
        list.add(header2);
    }

    public void onHeaderClick(PreferenceActivity.Header header, int i) {
        if (header == this.syncHeader) {
            Intent intent = new Intent("android.provider.action.SYNC_VOICEMAIL");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
            return;
        }
        super.onHeaderClick(header, i);
    }
}

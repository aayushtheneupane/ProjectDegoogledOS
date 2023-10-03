package com.android.dialer.voicemailstatus;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import com.android.dialer.app.filterednumber.BlockedNumbersFragment;
import com.android.dialer.database.CallLogQueryHandler;

public class VisualVoicemailEnabledChecker implements CallLogQueryHandler.Listener {
    private CallLogQueryHandler callLogQueryHandler;
    private Callback callback;
    private Context context;
    private boolean hasActiveVoicemailProvider = this.prefs.getBoolean("has_active_voicemail_provider", false);
    private SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);

    public interface Callback {
    }

    public VisualVoicemailEnabledChecker(Context context2, Callback callback2) {
        this.context = context2;
        this.callback = callback2;
    }

    public void asyncUpdate() {
        Context context2 = this.context;
        this.callLogQueryHandler = new CallLogQueryHandler(context2, context2.getContentResolver(), this, -1);
        this.callLogQueryHandler.fetchVoicemailStatus();
    }

    public boolean isVisualVoicemailEnabled() {
        return this.hasActiveVoicemailProvider;
    }

    public boolean onCallsFetched(Cursor cursor) {
        return false;
    }

    public void onMissedCallsUnreadCountFetched(Cursor cursor) {
    }

    public void onVoicemailStatusFetched(Cursor cursor) {
        boolean z = VoicemailStatusHelper.getNumberActivityVoicemailSources(cursor) > 0;
        if (z != this.hasActiveVoicemailProvider) {
            this.hasActiveVoicemailProvider = z;
            this.prefs.edit().putBoolean("has_active_voicemail_provider", this.hasActiveVoicemailProvider).apply();
            Callback callback2 = this.callback;
            if (callback2 != null) {
                ((BlockedNumbersFragment) callback2).onVisualVoicemailEnabledStatusChanged(this.hasActiveVoicemailProvider);
            }
        }
    }

    public void onVoicemailUnreadCountFetched(Cursor cursor) {
    }
}

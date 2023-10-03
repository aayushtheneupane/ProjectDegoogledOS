package com.android.dialer.app.settings;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.RingtonePreference;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.Toast;
import com.android.dialer.R;

public class DefaultRingtonePreference extends RingtonePreference {
    public DefaultRingtonePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onPrepareRingtonePickerIntent(Intent intent) {
        super.onPrepareRingtonePickerIntent(intent);
        intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
    }

    /* access modifiers changed from: protected */
    public Uri onRestoreRingtone() {
        return RingtoneManager.getActualDefaultRingtoneUri(getContext(), getRingtoneType());
    }

    /* access modifiers changed from: protected */
    public void onSaveRingtone(Uri uri) {
        if (!Settings.System.canWrite(getContext())) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_cannot_write_system_settings), 0).show();
        } else {
            RingtoneManager.setActualDefaultRingtoneUri(getContext(), getRingtoneType(), uri);
        }
    }
}

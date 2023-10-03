package com.android.incallui.answer.impl.hint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.storage.StorageComponent;
import java.util.Random;

public class PawSecretCodeListener extends BroadcastReceiver {
    static final String CONFIG_PAW_SECRET_CODE = "paw_secret_code";

    public static void selectPawType(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putBoolean("paw_enabled_with_secret_code", true).putInt("paw_type", new Random().nextBoolean() ? 1 : 2).apply();
    }

    public void onReceive(Context context, Intent intent) {
        String string;
        String host = intent.getData().getHost();
        if (!TextUtils.isEmpty(host) && (string = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getString(CONFIG_PAW_SECRET_CODE, "729")) != null && TextUtils.equals(string, host)) {
            SharedPreferences unencryptedSharedPrefs = StorageComponent.get(context).unencryptedSharedPrefs();
            if (unencryptedSharedPrefs.getBoolean("paw_enabled_with_secret_code", false)) {
                unencryptedSharedPrefs.edit().putBoolean("paw_enabled_with_secret_code", false).apply();
                Toast.makeText(context, R.string.event_deactivated, 0).show();
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.EVENT_ANSWER_HINT_DEACTIVATED);
                LogUtil.m9i("PawSecretCodeListener.onReceive", "PawAnswerHint disabled", new Object[0]);
                return;
            }
            selectPawType(unencryptedSharedPrefs);
            Toast.makeText(context, R.string.event_activated, 0).show();
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.EVENT_ANSWER_HINT_ACTIVATED);
            LogUtil.m9i("PawSecretCodeListener.onReceive", "PawAnswerHint enabled", new Object[0]);
        }
    }
}

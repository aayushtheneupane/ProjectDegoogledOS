package com.android.dialer.spam.promo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.p000v4.app.FragmentManager;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.spam.SpamSettings;
import com.android.dialer.spam.promo.SpamBlockingPromoDialogFragment;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.android.dialer.storage.StorageComponent;

public class SpamBlockingPromoHelper {
    private final Context context;
    private final SpamSettings spamSettings;

    public SpamBlockingPromoHelper(Context context2, SpamSettings spamSettings2) {
        this.context = context2;
        this.spamSettings = spamSettings2;
    }

    private void updateLastShowSpamTimestamp() {
        StorageComponent.get(this.context).unencryptedSharedPrefs().edit().putLong("spam_blocking_promo_last_show_millis", System.currentTimeMillis()).apply();
    }

    public boolean shouldShowAfterCallSpamBlockingPromo() {
        shouldShowSpamBlockingPromo();
        return false;
    }

    public boolean shouldShowSpamBlockingPromo() {
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("enable_spam_blocking_promo", false)) {
            ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        }
        return false;
    }

    public void showModifySettingOnCompleteToast(boolean z) {
        String str;
        if (z) {
            str = this.context.getString(R.string.spam_blocking_settings_enable_complete_text);
        } else {
            str = this.context.getString(R.string.spam_blocking_settings_enable_error_text);
        }
        Toast.makeText(this.context, str, 1).show();
    }

    public void showSpamBlockingPromoDialog(FragmentManager fragmentManager, SpamBlockingPromoDialogFragment.OnEnableListener onEnableListener, DialogInterface.OnDismissListener onDismissListener) {
        updateLastShowSpamTimestamp();
        SpamBlockingPromoDialogFragment spamBlockingPromoDialogFragment = new SpamBlockingPromoDialogFragment();
        spamBlockingPromoDialogFragment.positiveListener = onEnableListener;
        spamBlockingPromoDialogFragment.dismissListener = onDismissListener;
        spamBlockingPromoDialogFragment.show(fragmentManager, "SpamBlockingPromoDialog");
    }
}

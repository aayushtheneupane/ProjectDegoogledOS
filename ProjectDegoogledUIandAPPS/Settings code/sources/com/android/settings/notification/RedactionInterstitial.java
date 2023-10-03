package com.android.settings.notification;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.settings.RestrictedRadioButton;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.SetupRedactionInterstitial;
import com.android.settings.SetupWizardUtils;
import com.android.settings.Utils;
import com.android.settings.notification.RedactionInterstitial;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import com.havoc.config.center.C1715R;

public class RedactionInterstitial extends SettingsActivity {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", RedactionInterstitialFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return RedactionInterstitialFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
    }

    public static Intent createStartIntent(Context context, int i) {
        return new Intent(context, RedactionInterstitial.class).putExtra(":settings:show_fragment_title_resid", UserManager.get(context).isManagedProfile(i) ? C1715R.string.lock_screen_notifications_interstitial_title_profile : C1715R.string.lock_screen_notifications_interstitial_title).putExtra("android.intent.extra.USER_ID", i);
    }

    public static class RedactionInterstitialFragment extends SettingsPreferenceFragment implements RadioGroup.OnCheckedChangeListener {
        private RadioGroup mRadioGroup;
        private RestrictedRadioButton mRedactSensitiveButton;
        private RestrictedRadioButton mShowAllButton;
        private int mUserId;

        public int getMetricsCategory() {
            return 74;
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(C1715R.layout.redaction_interstitial, viewGroup, false);
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mRadioGroup = (RadioGroup) view.findViewById(C1715R.C1718id.radio_group);
            this.mShowAllButton = (RestrictedRadioButton) view.findViewById(C1715R.C1718id.show_all);
            this.mRedactSensitiveButton = (RestrictedRadioButton) view.findViewById(C1715R.C1718id.redact_sensitive);
            this.mRadioGroup.setOnCheckedChangeListener(this);
            this.mUserId = Utils.getUserIdFromBundle(getContext(), getActivity().getIntent().getExtras());
            if (UserManager.get(getContext()).isManagedProfile(this.mUserId)) {
                ((TextView) view.findViewById(C1715R.C1718id.sud_layout_description)).setText(C1715R.string.lock_screen_notifications_interstitial_message_profile);
                this.mShowAllButton.setText(C1715R.string.lock_screen_notifications_summary_show_profile);
                this.mRedactSensitiveButton.setText(C1715R.string.lock_screen_notifications_summary_hide_profile);
                ((RadioButton) view.findViewById(C1715R.C1718id.hide_all)).setVisibility(8);
            }
            FooterButton.Builder builder = new FooterButton.Builder(getContext());
            builder.setText(C1715R.string.app_notifications_dialog_done);
            builder.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    RedactionInterstitial.RedactionInterstitialFragment.this.onDoneButtonClicked(view);
                }
            });
            builder.setButtonType(5);
            builder.setTheme(2131952050);
            ((FooterBarMixin) ((GlifLayout) view.findViewById(C1715R.C1718id.setup_wizard_layout)).getMixin(FooterBarMixin.class)).setPrimaryButton(builder.build());
        }

        /* access modifiers changed from: private */
        public void onDoneButtonClicked(View view) {
            SetupRedactionInterstitial.setEnabled(getContext(), false);
            RedactionInterstitial redactionInterstitial = (RedactionInterstitial) getActivity();
            if (redactionInterstitial != null) {
                redactionInterstitial.setResult(-1, (Intent) null);
                finish();
            }
        }

        public void onResume() {
            super.onResume();
            checkNotificationFeaturesAndSetDisabled(this.mShowAllButton, 12);
            checkNotificationFeaturesAndSetDisabled(this.mRedactSensitiveButton, 4);
            loadFromSettings();
        }

        private void checkNotificationFeaturesAndSetDisabled(RestrictedRadioButton restrictedRadioButton, int i) {
            restrictedRadioButton.setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(getActivity(), i, this.mUserId));
        }

        private void loadFromSettings() {
            boolean z = false;
            boolean z2 = UserManager.get(getContext()).isManagedProfile(this.mUserId) || Settings.Secure.getIntForUser(getContentResolver(), "lock_screen_show_notifications", 0, this.mUserId) != 0;
            if (Settings.Secure.getIntForUser(getContentResolver(), "lock_screen_allow_private_notifications", 1, this.mUserId) != 0) {
                z = true;
            }
            int i = C1715R.C1718id.hide_all;
            if (z2) {
                if (z && !this.mShowAllButton.isDisabledByAdmin()) {
                    i = C1715R.C1718id.show_all;
                } else if (!this.mRedactSensitiveButton.isDisabledByAdmin()) {
                    i = C1715R.C1718id.redact_sensitive;
                }
            }
            this.mRadioGroup.check(i);
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int i2 = 1;
            int i3 = i == C1715R.C1718id.show_all ? 1 : 0;
            if (i == C1715R.C1718id.hide_all) {
                i2 = 0;
            }
            Settings.Secure.putIntForUser(getContentResolver(), "lock_screen_allow_private_notifications", i3, this.mUserId);
            Settings.Secure.putIntForUser(getContentResolver(), "lock_screen_show_notifications", i2, this.mUserId);
        }
    }
}

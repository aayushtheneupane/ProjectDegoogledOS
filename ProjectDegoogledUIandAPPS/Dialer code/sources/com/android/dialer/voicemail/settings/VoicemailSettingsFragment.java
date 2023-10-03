package com.android.dialer.voicemail.settings;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.preference.SwitchPreferenceWithClickableSummary;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.notification.NotificationChannelManager;
import com.android.dialer.spannable.ContentWithLearnMoreSpanner;
import com.android.dialer.telecom.TelecomUtil;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;
import com.google.common.base.Optional;

@TargetApi(26)
public class VoicemailSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, VoicemailClient.ActivationStateListener {
    static final String SUB_ID_EXTRA = "com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId";
    static final String SUB_LABEL_EXTRA = "com.android.phone.settings.SubscriptionInfoHelper.SubscriptionLabel";
    private PreferenceScreen advancedSettingsPreference;
    private Preference changeGreetingPreference;
    private SwitchPreferenceWithClickableSummary donateTranscribedVoicemailPreference;
    private PhoneAccountHandle phoneAccountHandle;
    private SwitchPreferenceWithClickableSummary transcribeVoicemailPreference;
    private SwitchPreference visualVoicemailPreference;
    private SwitchPreference voicemailAutoArchivePreference;
    private Preference voicemailChangePinPreference;
    private VoicemailClient voicemailClient;
    private Preference voicemailNotificationPreference;

    private void removeAllTranscriptionPreferences() {
        getPreferenceScreen().removePreference(this.transcribeVoicemailPreference);
        getPreferenceScreen().removePreference(this.donateTranscribedVoicemailPreference);
    }

    private void updateChangePinPreference() {
        if (!this.voicemailClient.isVoicemailEnabled(getContext(), this.phoneAccountHandle)) {
            this.voicemailChangePinPreference.setSummary(R.string.voicemail_change_pin_preference_summary_disable);
            this.voicemailChangePinPreference.setEnabled(false);
        } else if (!this.voicemailClient.isActivated(getContext(), this.phoneAccountHandle)) {
            this.voicemailChangePinPreference.setSummary(R.string.voicemail_change_pin_preference_summary_not_activated);
            this.voicemailChangePinPreference.setEnabled(false);
        } else {
            this.voicemailChangePinPreference.setSummary((CharSequence) null);
            this.voicemailChangePinPreference.setEnabled(true);
        }
    }

    private void updateTranscriptionDonationPreference() {
        if (!VoicemailComponent.get(getContext()).getVoicemailClient().isVoicemailDonationAvailable(getContext(), this.phoneAccountHandle)) {
            getPreferenceScreen().removePreference(this.donateTranscribedVoicemailPreference);
            return;
        }
        this.donateTranscribedVoicemailPreference.setEnabled(true);
        this.donateTranscribedVoicemailPreference.setChecked(this.voicemailClient.isVoicemailDonationEnabled(getContext(), this.phoneAccountHandle));
        this.donateTranscribedVoicemailPreference.setOnPreferenceChangeListener(this);
        this.donateTranscribedVoicemailPreference.setSummary(new ContentWithLearnMoreSpanner(getContext()).create(getContext().getString(R.string.voicemail_donate_preference_summary_info), getContext().getString(R.string.donation_learn_more_url)));
        getPreferenceScreen().addPreference(this.donateTranscribedVoicemailPreference);
    }

    private void updateTranscriptionPreferences() {
        if (!VoicemailComponent.get(getContext()).getVoicemailClient().isVoicemailTranscriptionAvailable(getContext(), this.phoneAccountHandle)) {
            getPreferenceScreen().removePreference(this.transcribeVoicemailPreference);
            getPreferenceScreen().removePreference(this.donateTranscribedVoicemailPreference);
            return;
        }
        this.transcribeVoicemailPreference.setOnPreferenceChangeListener(this);
        this.transcribeVoicemailPreference.setChecked(this.voicemailClient.isVoicemailTranscriptionEnabled(getContext(), this.phoneAccountHandle));
        this.transcribeVoicemailPreference.setSummary(new ContentWithLearnMoreSpanner(getContext()).create(getContext().getString(R.string.voicemail_transcription_preference_summary_info), getContext().getString(R.string.transcription_learn_more_url)));
        this.transcribeVoicemailPreference.setEnabled(true);
        getPreferenceScreen().addPreference(this.transcribeVoicemailPreference);
        updateTranscriptionDonationPreference();
    }

    /* access modifiers changed from: private */
    public void updateVoicemailEnabled(boolean z) {
        this.voicemailClient.setVoicemailEnabled(getContext(), this.phoneAccountHandle, z);
        this.visualVoicemailPreference.setChecked(z);
        if (z) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_ENABLED_IN_SETTINGS);
        } else {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_DISABLED_IN_SETTINGS);
        }
        updateVoicemailSummaryMessage();
        updateTranscriptionPreferences();
        updateChangePinPreference();
    }

    private void updateVoicemailSummaryMessage() {
        if (!this.voicemailClient.isVoicemailEnabled(getContext(), this.phoneAccountHandle) || this.voicemailClient.isActivated(getContext(), this.phoneAccountHandle)) {
            this.visualVoicemailPreference.setSummary("");
        } else {
            this.visualVoicemailPreference.setSummary(R.string.voicemail_activating_summary_info);
        }
    }

    public void onActivationStateChanged(PhoneAccountHandle phoneAccountHandle2, boolean z) {
        if (this.phoneAccountHandle.equals(phoneAccountHandle2)) {
            updateVoicemailSummaryMessage();
            updateTranscriptionPreferences();
            updateChangePinPreference();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PhoneAccountHandle phoneAccountHandle2 = (PhoneAccountHandle) getArguments().getParcelable("phone_account_handle");
        Assert.isNotNull(phoneAccountHandle2);
        this.phoneAccountHandle = phoneAccountHandle2;
        this.voicemailClient = VoicemailComponent.get(getContext()).getVoicemailClient();
    }

    public void onPause() {
        this.voicemailClient.removeActivationStateListener(this);
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        "onPreferenceChange: \"" + preference + "\" changed to \"" + obj + "\"";
        if (preference.getKey().equals(this.visualVoicemailPreference.getKey())) {
            if (!((Boolean) obj).booleanValue()) {
                LogUtil.m9i("VmSettingsActivity", "showDisableConfirmationDialog", new Object[0]);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.confirm_disable_voicemail_dialog_title);
                builder.setMessage(R.string.confirm_disable_voicemail_dialog_message);
                builder.setPositiveButton(R.string.confirm_disable_voicemail_accept_dialog_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LogUtil.m9i("VmSettingsActivity", "showDisableConfirmationDialog, confirmed", new Object[0]);
                        VoicemailSettingsFragment.this.updateVoicemailEnabled(false);
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton(17039360, new DialogInterface.OnClickListener(this) {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LogUtil.m9i("VmSettingsActivity", "showDisableConfirmationDialog, cancelled", new Object[0]);
                        dialogInterface.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.show();
                return false;
            }
            updateVoicemailEnabled(true);
        } else if (preference.getKey().equals(this.voicemailAutoArchivePreference.getKey())) {
            Boolean bool = (Boolean) obj;
            if (bool.booleanValue()) {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_ARCHIVE_ON_FROM_SETTINGS);
            } else {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_ARCHIVE_OFF_FROM_SETTINGS);
            }
            this.voicemailClient.setVoicemailArchiveEnabled(getContext(), this.phoneAccountHandle, bool.booleanValue());
        } else if (preference.getKey().equals(this.transcribeVoicemailPreference.getKey())) {
            Boolean bool2 = (Boolean) obj;
            if (bool2.booleanValue()) {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_TRANSCRIBE_ON_FROM_SETTINGS);
            } else {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_TRANSCRIBE_OFF_FROM_SETTINGS);
            }
            this.voicemailClient.setVoicemailTranscriptionEnabled(getContext(), this.phoneAccountHandle, bool2.booleanValue());
            updateTranscriptionDonationPreference();
        } else if (preference.getKey().equals(this.donateTranscribedVoicemailPreference.getKey())) {
            Boolean bool3 = (Boolean) obj;
            if (bool3.booleanValue()) {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_TRANSCRIBE_ON_FROM_SETTINGS);
            } else {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_USER_TURNED_TRANSCRIBE_OFF_FROM_SETTINGS);
            }
            this.voicemailClient.setVoicemailDonationEnabled(getContext(), this.phoneAccountHandle, bool3.booleanValue());
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_SETTINGS_VIEWED);
        this.voicemailClient.addActivationStateListener(this);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        addPreferencesFromResource(R.xml.voicemail_settings);
        this.voicemailNotificationPreference = findPreference(getString(R.string.voicemail_notifications_key));
        this.voicemailNotificationPreference.setOrder(1);
        this.changeGreetingPreference = findPreference(getString(R.string.voicemail_change_greeting_key));
        this.changeGreetingPreference.setOrder(8);
        this.advancedSettingsPreference = (PreferenceScreen) findPreference(getString(R.string.voicemail_advanced_settings_key));
        this.advancedSettingsPreference.setOrder(7);
        this.visualVoicemailPreference = (SwitchPreference) findPreference(getString(R.string.voicemail_visual_voicemail_key));
        this.visualVoicemailPreference.setOrder(2);
        this.voicemailAutoArchivePreference = (SwitchPreference) findPreference(getString(R.string.voicemail_visual_voicemail_archive_key));
        this.voicemailAutoArchivePreference.setOrder(6);
        this.transcribeVoicemailPreference = (SwitchPreferenceWithClickableSummary) findPreference(getString(R.string.voicemail_visual_voicemail_transcription_key));
        this.transcribeVoicemailPreference.setOrder(3);
        this.donateTranscribedVoicemailPreference = (SwitchPreferenceWithClickableSummary) findPreference(getString(R.string.voicemail_visual_voicemail_donation_key));
        this.donateTranscribedVoicemailPreference.setOrder(4);
        this.voicemailChangePinPreference = findPreference(getString(R.string.voicemail_change_pin_key));
        this.voicemailChangePinPreference.setOrder(5);
        if (!this.voicemailClient.hasCarrierSupport(getContext(), this.phoneAccountHandle)) {
            PreferenceScreen preferenceScreen2 = getPreferenceScreen();
            preferenceScreen2.removePreference(this.visualVoicemailPreference);
            preferenceScreen2.removePreference(this.voicemailAutoArchivePreference);
            preferenceScreen2.removePreference(this.transcribeVoicemailPreference);
            preferenceScreen2.removePreference(this.donateTranscribedVoicemailPreference);
            preferenceScreen2.removePreference(this.voicemailChangePinPreference);
        } else {
            this.visualVoicemailPreference.setOnPreferenceChangeListener(this);
            this.visualVoicemailPreference.setChecked(this.voicemailClient.isVoicemailEnabled(getContext(), this.phoneAccountHandle));
            updateVoicemailSummaryMessage();
            if (!this.voicemailClient.isVoicemailEnabled(getContext(), this.phoneAccountHandle) || !this.voicemailClient.isActivated(getContext(), this.phoneAccountHandle)) {
                removeAllTranscriptionPreferences();
                getPreferenceScreen().removePreference(this.voicemailAutoArchivePreference);
            } else {
                if (!VoicemailComponent.get(getContext()).getVoicemailClient().isVoicemailArchiveAvailable(getContext())) {
                    getPreferenceScreen().removePreference(this.voicemailAutoArchivePreference);
                } else {
                    this.voicemailAutoArchivePreference.setOnPreferenceChangeListener(this);
                    this.voicemailAutoArchivePreference.setChecked(this.voicemailClient.isVoicemailArchiveEnabled(getContext(), this.phoneAccountHandle));
                }
                updateTranscriptionPreferences();
            }
            Intent intent = new Intent(new Intent(getContext(), VoicemailChangePinActivity.class));
            intent.putExtra("phone_account_handle", this.phoneAccountHandle);
            this.voicemailChangePinPreference.setIntent(intent);
            this.voicemailChangePinPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    ((LoggingBindingsStub) Logger.get(VoicemailSettingsFragment.this.getContext())).logImpression(DialerImpression$Type.VVM_CHANGE_PIN_CLICKED);
                    return false;
                }
            });
            if (VoicemailChangePinActivity.isPinScrambled(getContext(), this.phoneAccountHandle)) {
                this.voicemailChangePinPreference.setTitle(R.string.voicemail_set_pin_preference_title);
            } else {
                this.voicemailChangePinPreference.setTitle(R.string.voicemail_change_pin_preference_title);
            }
            updateChangePinPreference();
        }
        this.voicemailNotificationPreference.setIntent(new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.CHANNEL_ID", NotificationChannelManager.getVoicemailChannelId(getContext(), this.phoneAccountHandle)).putExtra("android.provider.extra.APP_PACKAGE", getContext().getPackageName()));
        this.voicemailNotificationPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ((LoggingBindingsStub) Logger.get(VoicemailSettingsFragment.this.getContext())).logImpression(DialerImpression$Type.VVM_NOTIFICATIONS_SETTING_CLICKED);
                return false;
            }
        });
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(getContext()).getConfigProvider()).getBoolean("voicemail_change_greeting_enabled", false)) {
            getPreferenceScreen().removePreference(this.changeGreetingPreference);
        } else {
            this.changeGreetingPreference.setIntent(new Intent(getContext(), CurrentVoicemailGreetingActivity.class));
        }
        Intent intent2 = new Intent("android.telephony.action.CONFIGURE_VOICEMAIL");
        intent2.putExtra("android.telephony.extra.HIDE_PUBLIC_SETTINGS", true);
        intent2.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", this.phoneAccountHandle);
        Optional<SubscriptionInfo> subscriptionInfo = TelecomUtil.getSubscriptionInfo(getContext(), this.phoneAccountHandle);
        if (subscriptionInfo.isPresent()) {
            intent2.putExtra(SUB_ID_EXTRA, subscriptionInfo.get().getSubscriptionId());
            PhoneAccount phoneAccount = ((TelecomManager) getContext().getSystemService(TelecomManager.class)).getPhoneAccount(this.phoneAccountHandle);
            if (phoneAccount != null) {
                intent2.putExtra(SUB_LABEL_EXTRA, phoneAccount.getLabel());
            }
        }
        this.advancedSettingsPreference.setIntent(intent2);
        this.advancedSettingsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ((LoggingBindingsStub) Logger.get(VoicemailSettingsFragment.this.getContext())).logImpression(DialerImpression$Type.VVM_ADVANCED_SETINGS_CLICKED);
                return false;
            }
        });
    }
}

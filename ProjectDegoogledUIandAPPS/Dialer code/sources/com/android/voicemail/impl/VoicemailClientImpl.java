package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.voicemail.PinChanger;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.configui.VoicemailSecretCodeActivity;
import com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil;
import com.android.voicemail.impl.sync.VvmAccountManager;
import com.android.voicemail.impl.transcribe.TranscriptionBackfillService;
import com.android.voicemail.impl.transcribe.TranscriptionConfigProvider;
import java.util.List;

public class VoicemailClientImpl implements VoicemailClient {
    private static final String[] OMTP_VOICEMAIL_BLACKLIST = {"com.android.phone"};
    private static final String[] OMTP_VOICEMAIL_TYPE = {"vvm_type_omtp", "vvm_type_cvvm", "vvm_type_vvm3"};

    public VoicemailClientImpl() {
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
    }

    public void addActivationStateListener(VoicemailClient.ActivationStateListener activationStateListener) {
        VvmAccountManager.addListener(activationStateListener);
    }

    @TargetApi(26)
    public void appendOmtpVoicemailSelectionClause(Context context, StringBuilder sb, List<String> list) {
        String visualVoicemailPackageName = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getVisualVoicemailPackageName();
        if (sb.length() != 0) {
            sb.append(" AND ");
        }
        sb.append("(");
        sb.append("(");
        sb.append("is_omtp_voicemail");
        sb.append(" != 1");
        sb.append(")");
        sb.append(" OR ");
        sb.append("(");
        sb.append("source_package");
        sb.append(" = ?");
        list.add(visualVoicemailPackageName);
        sb.append(")");
        sb.append(")");
        for (String add : OMTP_VOICEMAIL_BLACKLIST) {
            sb.append("AND (");
            sb.append("source_package");
            sb.append("!= ?)");
            list.add(add);
        }
    }

    @TargetApi(26)
    public void appendOmtpVoicemailStatusSelectionClause(Context context, StringBuilder sb, List<String> list) {
        String visualVoicemailPackageName = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getVisualVoicemailPackageName();
        if (sb.length() != 0) {
            sb.append(" AND ");
        }
        sb.append("(");
        sb.append("(");
        sb.append("source_package");
        sb.append(" = ? ");
        list.add(visualVoicemailPackageName);
        sb.append(")");
        sb.append(" OR NOT (");
        for (int i = 0; i < OMTP_VOICEMAIL_TYPE.length; i++) {
            if (i != 0) {
                sb.append(" OR ");
            }
            sb.append(" (");
            sb.append("source_type");
            sb.append(" IS ?");
            list.add(OMTP_VOICEMAIL_TYPE[i]);
            sb.append(")");
        }
        sb.append(")");
        for (String add : OMTP_VOICEMAIL_BLACKLIST) {
            sb.append("AND (");
            sb.append("source_package");
            sb.append("!= ?");
            list.add(add);
            sb.append(")");
        }
        sb.append(")");
    }

    public PinChanger createPinChanger(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new PinChangerImpl(context, phoneAccountHandle);
    }

    public String getCarrierConfigString(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle);
        if (omtpVvmCarrierConfigHelper.isValid()) {
            return omtpVvmCarrierConfigHelper.getString(str);
        }
        return null;
    }

    public PersistableBundle getConfig(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle).getConfig();
    }

    public boolean hasAcceptedTos(Context context, PhoneAccountHandle phoneAccountHandle) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if ("vvm_type_vvm3".equals(new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle).getVvmType())) {
            if (defaultSharedPreferences.getInt("vvm3_tos_version_accepted", 0) >= 2) {
                return true;
            }
            return false;
        } else if (defaultSharedPreferences.getInt("dialer_tos_version_accepted", 0) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasCarrierSupport(Context context, PhoneAccountHandle phoneAccountHandle) {
        OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle);
        return omtpVvmCarrierConfigHelper.isValid() && !omtpVvmCarrierConfigHelper.isCarrierAppInstalled();
    }

    public boolean isActivated(Context context, PhoneAccountHandle phoneAccountHandle) {
        return VvmAccountManager.isAccountActivated(context, phoneAccountHandle);
    }

    public boolean isVoicemailArchiveAvailable(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("allow_voicemail_archive", false)) {
            return true;
        }
        LogUtil.m9i("VoicemailClientImpl.isVoicemailArchiveAllowed", "feature disabled by config: %s", "allow_voicemail_archive");
        return false;
    }

    public boolean isVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        Assert.isNotNull(phoneAccountHandle);
        return new VisualVoicemailPreferences(context, phoneAccountHandle).getBoolean("archive_is_enabled", false);
    }

    public boolean isVoicemailDonationAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (!isVoicemailTranscriptionAvailable(context, phoneAccountHandle)) {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailDonationAvailable", "transcription not available", new Object[0]);
            return false;
        } else if (!isVoicemailTranscriptionEnabled(context, phoneAccountHandle)) {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailDonationAvailable", "transcription not enabled", new Object[0]);
            return false;
        } else if (new TranscriptionConfigProvider(context).isVoicemailDonationAvailable()) {
            return true;
        } else {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailDonationAvailable", "feature disabled by config", new Object[0]);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isVoicemailDonationEnabled(android.content.Context r5, android.telecom.PhoneAccountHandle r6) {
        /*
            r4 = this;
            boolean r0 = r4.isVoicemailTranscriptionEnabled(r5, r6)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x004e
            boolean r0 = r4.isVoicemailTranscriptionAvailable(r5, r6)
            java.lang.String r3 = "VoicemailClientImpl.isVoicemailDonationAvailable"
            if (r0 != 0) goto L_0x0019
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r0 = "transcription not available"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r4)
        L_0x0017:
            r4 = r2
            goto L_0x003b
        L_0x0019:
            boolean r4 = r4.isVoicemailTranscriptionEnabled(r5, r6)
            if (r4 != 0) goto L_0x0027
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r0 = "transcription not enabled"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r4)
            goto L_0x0017
        L_0x0027:
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r4 = new com.android.voicemail.impl.transcribe.TranscriptionConfigProvider
            r4.<init>(r5)
            boolean r4 = r4.isVoicemailDonationAvailable()
            if (r4 != 0) goto L_0x003a
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r0 = "feature disabled by config"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r4)
            goto L_0x0017
        L_0x003a:
            r4 = r1
        L_0x003b:
            if (r4 == 0) goto L_0x004e
            com.android.dialer.common.Assert.isNotNull(r6)
            com.android.voicemail.impl.VisualVoicemailPreferences r4 = new com.android.voicemail.impl.VisualVoicemailPreferences
            r4.<init>(r5, r6)
            java.lang.String r5 = "donate_voicemails"
            boolean r4 = r4.getBoolean(r5, r2)
            if (r4 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r1 = r2
        L_0x004f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.VoicemailClientImpl.isVoicemailDonationEnabled(android.content.Context, android.telecom.PhoneAccountHandle):boolean");
    }

    public boolean isVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        return VisualVoicemailSettingsUtil.isEnabled(context, phoneAccountHandle);
    }

    public boolean isVoicemailModuleEnabled() {
        return true;
    }

    public boolean isVoicemailTranscriptionAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle == null) {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailTranscriptionAvailable", "phone account handle is null", new Object[0]);
        }
        int i = Build.VERSION.SDK_INT;
        if (!VisualVoicemailSettingsUtil.isEnabled(context, phoneAccountHandle)) {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailTranscriptionAvailable", "visual voicemail is not enabled", new Object[0]);
            return false;
        } else if (!VvmAccountManager.isAccountActivated(context, phoneAccountHandle)) {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailTranscriptionAvailable", "visual voicemail is not activated", new Object[0]);
            return false;
        } else if (new TranscriptionConfigProvider(context).isVoicemailTranscriptionAvailable()) {
            return true;
        } else {
            LogUtil.m9i("VoicemailClientImpl.isVoicemailTranscriptionAvailable", "feature disabled by config", new Object[0]);
            return false;
        }
    }

    public boolean isVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (!isVoicemailTranscriptionAvailable(context, phoneAccountHandle)) {
            return false;
        }
        Assert.isNotNull(phoneAccountHandle);
        if (new VisualVoicemailPreferences(context, phoneAccountHandle).getBoolean("transcribe_voicemails", false)) {
            return true;
        }
        return false;
    }

    public void onBoot(Context context) {
        OmtpService.onBoot(context);
        StatusCheckJobService.schedule(context);
    }

    public void onShutdown(Context context) {
        OmtpService.onShutdown(context);
    }

    public void onTosAccepted(Context context, PhoneAccountHandle phoneAccountHandle) {
        LogUtil.m9i("VoicemailClientImpl.onTosAccepted", "try backfilling voicemail transcriptions", new Object[0]);
        TranscriptionBackfillService.scheduleTask(context, phoneAccountHandle);
    }

    public void removeActivationStateListener(VoicemailClient.ActivationStateListener activationStateListener) {
        VvmAccountManager.removeListener(activationStateListener);
    }

    public void setVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Assert.checkArgument(VoicemailComponent.get(context).getVoicemailClient().isVoicemailArchiveAvailable(context));
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        edit.putBoolean("archive_is_enabled", z);
        edit.apply();
    }

    public void setVoicemailDonationEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        if (z) {
            Assert.checkArgument(isVoicemailTranscriptionAvailable(context, phoneAccountHandle) && isVoicemailTranscriptionEnabled(context, phoneAccountHandle), "should not be able to enable donation without transcription available(value: %b) and enabled (value:%b) for account:%s", Boolean.valueOf(isVoicemailTranscriptionAvailable(context, phoneAccountHandle)), Boolean.valueOf(isVoicemailTranscriptionEnabled(context, phoneAccountHandle)), phoneAccountHandle.toString());
        }
        Assert.checkArgument(VoicemailComponent.get(context).getVoicemailClient().isVoicemailTranscriptionAvailable(context, phoneAccountHandle));
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        edit.putBoolean("donate_voicemails", z);
        edit.apply();
    }

    public void setVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        VisualVoicemailSettingsUtil.setEnabled(context, phoneAccountHandle, z);
    }

    public void setVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Assert.checkArgument(isVoicemailTranscriptionAvailable(context, phoneAccountHandle), "transcription must be available before enabling/disabling it", new Object[0]);
        VisualVoicemailSettingsUtil.setVoicemailTranscriptionEnabled(context, phoneAccountHandle, z);
        if (z) {
            TranscriptionBackfillService.scheduleTask(context, phoneAccountHandle);
        }
    }

    public void showConfigUi(Context context) {
        Intent intent = new Intent(context, VoicemailSecretCodeActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}

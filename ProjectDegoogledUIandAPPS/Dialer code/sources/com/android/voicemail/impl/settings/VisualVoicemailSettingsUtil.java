package com.android.voicemail.impl.settings;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CallLog;
import android.provider.VoicemailContract;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.database.Selection;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VisualVoicemailPreferences;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.sync.VvmAccountManager;

public class VisualVoicemailSettingsUtil {
    public static final String IS_ENABLED_KEY = "is_enabled";

    private static class ClearGoogleTranscribedVoicemailTranscriptionWorker implements DialerExecutor.Worker<Void, Void> {
        private final Context context;

        ClearGoogleTranscribedVoicemailTranscriptionWorker(Context context2) {
            this.context = context2;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Void voidR = (Void) obj;
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("transcription");
            contentValues.put("transcription_state", 0);
            Selection.Builder builder = Selection.builder();
            builder.and(Selection.column("type").mo5867is("=", 4));
            builder.and(Selection.column("source_package").mo5867is("=", this.context.getPackageName()));
            Selection build = builder.build();
            int update = this.context.getContentResolver().update(CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL, contentValues, build.getSelection(), build.getSelectionArgs());
            VvmLog.m45i("VisualVoicemailSettingsUtil.doInBackground", "cleared " + update + " voicemail transcription");
            return null;
        }
    }

    private static class VoicemailDeleteWorker implements DialerExecutor.Worker<Void, Void> {
        private final Context context;

        VoicemailDeleteWorker(Context context2) {
            this.context = context2;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Void voidR = (Void) obj;
            int delete = this.context.getContentResolver().delete(VoicemailContract.Voicemails.buildSourceUri(this.context.getPackageName()), (String) null, (String[]) null);
            VvmLog.m45i("VisualVoicemailSettingsUtil.doInBackground", "deleted " + delete + " voicemails");
            return null;
        }
    }

    public static boolean isArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        Assert.isNotNull(phoneAccountHandle);
        return new VisualVoicemailPreferences(context, phoneAccountHandle).getBoolean("archive_is_enabled", false);
    }

    public static boolean isEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle == null) {
            LogUtil.m9i("VisualVoicemailSettingsUtil.isEnabled", "phone account is null", new Object[0]);
            return false;
        }
        VisualVoicemailPreferences visualVoicemailPreferences = new VisualVoicemailPreferences(context, phoneAccountHandle);
        if (visualVoicemailPreferences.contains(IS_ENABLED_KEY)) {
            return visualVoicemailPreferences.getBoolean(IS_ENABLED_KEY, false);
        }
        return new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle).isEnabledByDefault();
    }

    /* access modifiers changed from: private */
    public static void onFailure(Throwable th) {
        VvmLog.m44e("VisualVoicemailSettingsUtil.onFailure", "delete voicemails", th);
    }

    /* access modifiers changed from: private */
    public static void onSuccess(Void voidR) {
        VvmLog.m45i("VisualVoicemailSettingsUtil.onSuccess", "delete voicemails");
    }

    public static void setEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        VvmLog.m45i("VisualVoicemailSettingsUtil.setEnable", phoneAccountHandle + " enabled:" + z);
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        edit.putBoolean(IS_ENABLED_KEY, z);
        edit.apply();
        OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context, phoneAccountHandle);
        if (z) {
            omtpVvmCarrierConfigHelper.startActivation();
            return;
        }
        VvmAccountManager.removeAccount(context, phoneAccountHandle);
        omtpVvmCarrierConfigHelper.startDeactivation();
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new VoicemailDeleteWorker(context)).onSuccess(C0783xc4190523.INSTANCE).onFailure(C0782x45dccbdb.INSTANCE).build().executeParallel(null);
    }

    public static void setVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Assert.checkArgument(VoicemailComponent.get(context).getVoicemailClient().isVoicemailTranscriptionAvailable(context, phoneAccountHandle));
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        edit.putBoolean("transcribe_voicemails", z);
        edit.apply();
        if (!z) {
            VvmLog.m45i("VisualVoicemailSettingsUtil.setVoicemailTranscriptionEnabled", "clear all Google transcribed voicemail.");
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new ClearGoogleTranscribedVoicemailTranscriptionWorker(context)).onSuccess(C0781x7d427816.INSTANCE).onFailure(C0780x163351e4.INSTANCE).build().executeParallel(null);
        }
    }
}

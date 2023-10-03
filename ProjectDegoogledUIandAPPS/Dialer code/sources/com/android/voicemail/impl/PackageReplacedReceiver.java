package com.android.voicemail.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.voicemail.VoicemailComponent;

public class PackageReplacedReceiver extends BroadcastReceiver {

    private static class ExistingVoicemailCheck implements DialerExecutor.Worker<Void, Void> {
        private static final String[] PROJECTION = {"_id"};
        private final Context context;

        ExistingVoicemailCheck(Context context2) {
            this.context = context2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
            if (r0 != null) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
            r8.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
            throw r9;
         */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doInBackground(java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.Void r9 = (java.lang.Void) r9
                r9 = 0
                java.lang.Object[] r0 = new java.lang.Object[r9]
                java.lang.String r1 = "PackageReplacedReceiver.ExistingVoicemailCheck.doInBackground"
                java.lang.String r2 = ""
                com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
                android.content.Context r0 = r8.context
                java.lang.String r0 = r0.getPackageName()
                android.net.Uri r3 = android.provider.VoicemailContract.Voicemails.buildSourceUri(r0)
                android.content.Context r0 = r8.context
                android.content.ContentResolver r2 = r0.getContentResolver()
                java.lang.String[] r4 = PROJECTION
                r6 = 0
                r7 = 0
                java.lang.String r5 = "type = 4"
                android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)
                if (r0 != 0) goto L_0x0030
                java.lang.String r2 = "failed to check for existing voicemails"
                java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch:{ all -> 0x005e }
                com.android.dialer.common.LogUtil.m8e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x005e }
                goto L_0x0038
            L_0x0030:
                boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x005e }
                if (r2 == 0) goto L_0x0038
                r2 = 1
                goto L_0x0039
            L_0x0038:
                r2 = r9
            L_0x0039:
                if (r0 == 0) goto L_0x003e
                r0.close()
            L_0x003e:
                java.lang.String r0 = "has voicemails: "
                java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline10(r0, r2)
                java.lang.Object[] r9 = new java.lang.Object[r9]
                com.android.dialer.common.LogUtil.m9i(r1, r0, r9)
                android.content.Context r8 = r8.context
                android.content.SharedPreferences r8 = android.preference.PreferenceManager.getDefaultSharedPreferences(r8)
                android.content.SharedPreferences$Editor r8 = r8.edit()
                java.lang.String r9 = "dialer_feature_version_acknowledged"
                android.content.SharedPreferences$Editor r8 = r8.putInt(r9, r2)
                r8.apply()
                r8 = 0
                return r8
            L_0x005e:
                r8 = move-exception
                throw r8     // Catch:{ all -> 0x0060 }
            L_0x0060:
                r9 = move-exception
                if (r0 == 0) goto L_0x006b
                r0.close()     // Catch:{ all -> 0x0067 }
                goto L_0x006b
            L_0x0067:
                r0 = move-exception
                r8.addSuppressed(r0)
            L_0x006b:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.PackageReplacedReceiver.ExistingVoicemailCheck.doInBackground(java.lang.Object):java.lang.Object");
        }
    }

    static /* synthetic */ void lambda$setVoicemailFeatureVersionAsync$0(BroadcastReceiver.PendingResult pendingResult, Void voidR) {
        LogUtil.m9i("PackageReplacedReceiver.setVoicemailFeatureVersionAsync", "success", new Object[0]);
        pendingResult.finish();
    }

    static /* synthetic */ void lambda$setVoicemailFeatureVersionAsync$1(BroadcastReceiver.PendingResult pendingResult, Throwable th) {
        LogUtil.m9i("PackageReplacedReceiver.setVoicemailFeatureVersionAsync", "failure", new Object[0]);
        pendingResult.finish();
    }

    public void onReceive(Context context, Intent intent) {
        VvmLog.m45i("PackageReplacedReceiver.onReceive", "package replaced, starting activation");
        if (!VoicemailComponent.get(context).getVoicemailClient().isVoicemailModuleEnabled()) {
            VvmLog.m43e("PackageReplacedReceiver.onReceive", "module disabled");
            return;
        }
        for (PhoneAccountHandle start : ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            ActivationTask.start(context, start, (Bundle) null);
        }
        if (!PreferenceManager.getDefaultSharedPreferences(context).contains("dialer_feature_version_acknowledged")) {
            LogUtil.enterBlock("PackageReplacedReceiver.setVoicemailFeatureVersionAsync");
            BroadcastReceiver.PendingResult goAsync = goAsync();
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new ExistingVoicemailCheck(context)).onSuccess(new DialerExecutor.SuccessListener(goAsync) {
                private final /* synthetic */ BroadcastReceiver.PendingResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onSuccess(Object obj) {
                    PackageReplacedReceiver.lambda$setVoicemailFeatureVersionAsync$0(this.f$0, (Void) obj);
                }
            }).onFailure(new DialerExecutor.FailureListener(goAsync) {
                private final /* synthetic */ BroadcastReceiver.PendingResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onFailure(Throwable th) {
                    PackageReplacedReceiver.lambda$setVoicemailFeatureVersionAsync$1(this.f$0, th);
                }
            }).build().executeParallel(null);
        }
    }
}

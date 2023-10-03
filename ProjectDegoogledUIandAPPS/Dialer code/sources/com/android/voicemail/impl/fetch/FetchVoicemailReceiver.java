package com.android.voicemail.impl.fetch;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.sync.VvmAccountManager;
import com.android.voicemail.impl.sync.VvmNetworkRequestCallback;
import java.util.concurrent.Executors;

@TargetApi(26)
public class FetchVoicemailReceiver extends BroadcastReceiver {
    static final String[] PROJECTION = {"source_data", "subscription_id", "subscription_component_name"};
    private ContentResolver contentResolver;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public VvmNetworkRequestCallback networkCallback;
    /* access modifiers changed from: private */
    public PhoneAccountHandle phoneAccount;
    /* access modifiers changed from: private */
    public int retryCount = 3;
    /* access modifiers changed from: private */
    public String uid;
    /* access modifiers changed from: private */
    public Uri uri;

    private class fetchVoicemailNetworkRequestCallback extends VvmNetworkRequestCallback {
        public fetchVoicemailNetworkRequestCallback(Context context, PhoneAccountHandle phoneAccountHandle) {
            super(context, phoneAccountHandle, Assert.edit(context, phoneAccountHandle));
        }

        public void onAvailable(Network network) {
            super.onAvailable(network);
            FetchVoicemailReceiver.this.fetchVoicemail(network, getVoicemailStatusEditor());
        }
    }

    static /* synthetic */ int access$110(FetchVoicemailReceiver fetchVoicemailReceiver) {
        int i = fetchVoicemailReceiver.retryCount;
        fetchVoicemailReceiver.retryCount = i - 1;
        return i;
    }

    /* access modifiers changed from: private */
    public void fetchVoicemail(final Network network, final VoicemailStatus$Editor voicemailStatus$Editor) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0089, code lost:
                r3 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
                r1.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
                throw r3;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    java.lang.String r0 = "FetchVoicemailReceiver"
                L_0x0002:
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r1 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x00bd }
                    int r1 = r1.retryCount     // Catch:{ all -> 0x00bd }
                    if (r1 <= 0) goto L_0x00ab
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
                    r1.<init>()     // Catch:{ all -> 0x00bd }
                    java.lang.String r2 = "fetching voicemail, retry count="
                    r1.append(r2)     // Catch:{ all -> 0x00bd }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r2 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x00bd }
                    int r2 = r2.retryCount     // Catch:{ all -> 0x00bd }
                    r1.append(r2)     // Catch:{ all -> 0x00bd }
                    java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00bd }
                    com.android.voicemail.impl.VvmLog.m45i(r0, r1)     // Catch:{ all -> 0x00bd }
                    com.android.voicemail.impl.imap.ImapHelper r1 = new com.android.voicemail.impl.imap.ImapHelper     // Catch:{ InitializingException -> 0x0093 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r2 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ InitializingException -> 0x0093 }
                    android.content.Context r2 = r2.context     // Catch:{ InitializingException -> 0x0093 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r3 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ InitializingException -> 0x0093 }
                    android.telecom.PhoneAccountHandle r3 = r3.phoneAccount     // Catch:{ InitializingException -> 0x0093 }
                    android.net.Network r4 = r3     // Catch:{ InitializingException -> 0x0093 }
                    com.android.voicemail.impl.VoicemailStatus$Editor r5 = r4     // Catch:{ InitializingException -> 0x0093 }
                    r1.<init>(r2, r3, r4, r5)     // Catch:{ InitializingException -> 0x0093 }
                    com.android.voicemail.impl.fetch.VoicemailFetchedCallback r2 = new com.android.voicemail.impl.fetch.VoicemailFetchedCallback     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r3 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    android.content.Context r3 = r3.context     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r4 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    android.net.Uri r4 = r4.uri     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r5 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    android.telecom.PhoneAccountHandle r5 = r5.phoneAccount     // Catch:{ all -> 0x0087 }
                    r2.<init>(r3, r4, r5)     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r3 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    java.lang.String r3 = r3.uid     // Catch:{ all -> 0x0087 }
                    boolean r2 = r1.fetchVoicemailPayload(r2, r3)     // Catch:{ all -> 0x0087 }
                    if (r2 != 0) goto L_0x0072
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r2 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    int r2 = r2.retryCount     // Catch:{ all -> 0x0087 }
                    if (r2 <= 0) goto L_0x0072
                    java.lang.String r2 = "fetch voicemail failed, retrying"
                    com.android.voicemail.impl.VvmLog.m45i(r0, r2)     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r2 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this     // Catch:{ all -> 0x0087 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver.access$110(r2)     // Catch:{ all -> 0x0087 }
                    r1.close()     // Catch:{ InitializingException -> 0x0093 }
                    goto L_0x0002
                L_0x0072:
                    r1.close()     // Catch:{ InitializingException -> 0x0093 }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r0 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r0 = r0.networkCallback
                    if (r0 == 0) goto L_0x0086
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r6 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r6 = r6.networkCallback
                    r6.releaseNetwork()
                L_0x0086:
                    return
                L_0x0087:
                    r2 = move-exception
                    throw r2     // Catch:{ all -> 0x0089 }
                L_0x0089:
                    r3 = move-exception
                    r1.close()     // Catch:{ all -> 0x008e }
                    goto L_0x0092
                L_0x008e:
                    r1 = move-exception
                    r2.addSuppressed(r1)     // Catch:{ InitializingException -> 0x0093 }
                L_0x0092:
                    throw r3     // Catch:{ InitializingException -> 0x0093 }
                L_0x0093:
                    r1 = move-exception
                    java.lang.String r2 = "Can't retrieve Imap credentials "
                    com.android.voicemail.impl.VvmLog.m47w(r0, r2, r1)     // Catch:{ all -> 0x00bd }
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r0 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r0 = r0.networkCallback
                    if (r0 == 0) goto L_0x00aa
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r6 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r6 = r6.networkCallback
                    r6.releaseNetwork()
                L_0x00aa:
                    return
                L_0x00ab:
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r0 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r0 = r0.networkCallback
                    if (r0 == 0) goto L_0x00bc
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r6 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r6 = r6.networkCallback
                    r6.releaseNetwork()
                L_0x00bc:
                    return
                L_0x00bd:
                    r0 = move-exception
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r1 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r1 = r1.networkCallback
                    if (r1 == 0) goto L_0x00cf
                    com.android.voicemail.impl.fetch.FetchVoicemailReceiver r6 = com.android.voicemail.impl.fetch.FetchVoicemailReceiver.this
                    com.android.voicemail.impl.sync.VvmNetworkRequestCallback r6 = r6.networkCallback
                    r6.releaseNetwork()
                L_0x00cf:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.fetch.FetchVoicemailReceiver.C07691.run():void");
            }
        });
    }

    private static PhoneAccountHandle getAccountFromMarshmallowAccount(Context context2, PhoneAccountHandle phoneAccountHandle) {
        int i = Build.VERSION.SDK_INT;
        for (PhoneAccountHandle next : ((TelecomManager) context2.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            String id = next.getId();
            int i2 = 0;
            while (true) {
                if (i2 >= id.length()) {
                    break;
                } else if (!Character.isDigit(id.charAt(i2))) {
                    id = id.substring(0, i2);
                    break;
                } else {
                    i2++;
                }
            }
            if (id.equals(phoneAccountHandle.getId())) {
                return next;
            }
        }
        return null;
    }

    public void onReceive(Context context2, Intent intent) {
        if (VoicemailComponent.get(context2).getVoicemailClient().isVoicemailModuleEnabled() && "android.intent.action.FETCH_VOICEMAIL".equals(intent.getAction())) {
            VvmLog.m45i("FetchVoicemailReceiver", "ACTION_FETCH_VOICEMAIL received");
            this.context = context2;
            this.contentResolver = context2.getContentResolver();
            this.uri = intent.getData();
            if (this.uri == null) {
                VvmLog.m46w("FetchVoicemailReceiver", "android.intent.action.FETCH_VOICEMAIL intent sent with no data");
            } else if (!context2.getPackageName().equals(this.uri.getQueryParameter("source_package"))) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("ACTION_FETCH_VOICEMAIL from foreign pacakge ");
                outline13.append(context2.getPackageName());
                VvmLog.m43e("FetchVoicemailReceiver", outline13.toString());
            } else {
                Cursor query = this.contentResolver.query(this.uri, PROJECTION, (String) null, (String[]) null, (String) null);
                if (query == null) {
                    VvmLog.m45i("FetchVoicemailReceiver", "ACTION_FETCH_VOICEMAIL query returned null");
                    return;
                }
                try {
                    if (query.moveToFirst()) {
                        this.uid = query.getString(0);
                        if (!TextUtils.isEmpty(query.getString(1)) || !TextUtils.isEmpty(((TelephonyManager) context2.getSystemService("phone")).getSimSerialNumber())) {
                            this.phoneAccount = new PhoneAccountHandle(ComponentName.unflattenFromString(query.getString(2)), query.getString(1));
                            if (((TelephonyManager) context2.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(this.phoneAccount) == null) {
                                VvmLog.m43e("FetchVoicemailReceiver", "account no longer valid, cannot retrieve message");
                                query.close();
                                return;
                            }
                            if (!VvmAccountManager.isAccountActivated(context2, this.phoneAccount)) {
                                this.phoneAccount = getAccountFromMarshmallowAccount(context2, this.phoneAccount);
                                if (this.phoneAccount == null) {
                                    VvmLog.m46w("FetchVoicemailReceiver", "Account not registered - cannot retrieve message.");
                                    query.close();
                                    return;
                                }
                                VvmLog.m45i("FetchVoicemailReceiver", "Fetching voicemail with Marshmallow PhoneAccountHandle");
                            }
                            VvmLog.m45i("FetchVoicemailReceiver", "Requesting network to fetch voicemail");
                            this.networkCallback = new fetchVoicemailNetworkRequestCallback(context2, this.phoneAccount);
                            this.networkCallback.requestNetwork();
                        } else {
                            VvmLog.m43e("FetchVoicemailReceiver", "Account null and no default sim found.");
                            return;
                        }
                    }
                    query.close();
                } finally {
                    query.close();
                }
            }
        }
    }
}

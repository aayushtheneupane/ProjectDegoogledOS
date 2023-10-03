package com.android.incallui.spam;

import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorFactory;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import java.util.Random;

public class SpamCallListListener implements CallList.Listener {
    private final Context context;
    private final DialerExecutorFactory dialerExecutorFactory;
    private final Random random;

    private static final class NumberInCallHistoryWorker implements DialerExecutor.Worker<Void, Integer> {
        private final Context appContext;
        private final String countryIso;
        private final String number;

        public NumberInCallHistoryWorker(Context context, String str, String str2) {
            Assert.isNotNull(context);
            this.appContext = context;
            this.number = str;
            this.countryIso = str2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0058 A[SYNTHETIC, Splitter:B:17:0x0058] */
        /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doInBackground(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Void r11 = (java.lang.Void) r11
                java.lang.String r11 = r10.number
                java.lang.String r0 = r10.countryIso
                java.lang.String r0 = android.telephony.PhoneNumberUtils.formatNumberToE164(r11, r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x0013
                java.lang.String r11 = "normalized_number"
                goto L_0x0018
            L_0x0013:
                java.lang.String r0 = "number"
                r9 = r0
                r0 = r11
                r11 = r9
            L_0x0018:
                r1 = 0
                android.content.Context r2 = r10.appContext     // Catch:{ SQLiteException -> 0x0069 }
                android.content.ContentResolver r3 = r2.getContentResolver()     // Catch:{ SQLiteException -> 0x0069 }
                android.content.Context r10 = r10.appContext     // Catch:{ SQLiteException -> 0x0069 }
                android.net.Uri r4 = com.android.dialer.telecom.TelecomUtil.getCallLogUri(r10)     // Catch:{ SQLiteException -> 0x0069 }
                java.lang.String r10 = "_id"
                java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x0069 }
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0069 }
                r10.<init>()     // Catch:{ SQLiteException -> 0x0069 }
                r10.append(r11)     // Catch:{ SQLiteException -> 0x0069 }
                java.lang.String r11 = " = ?"
                r10.append(r11)     // Catch:{ SQLiteException -> 0x0069 }
                java.lang.String r6 = r10.toString()     // Catch:{ SQLiteException -> 0x0069 }
                r10 = 1
                java.lang.String[] r7 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0069 }
                r7[r1] = r0     // Catch:{ SQLiteException -> 0x0069 }
                r8 = 0
                android.database.Cursor r11 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0069 }
                if (r11 == 0) goto L_0x0051
                int r0 = r11.getCount()     // Catch:{ all -> 0x004f }
                if (r0 <= 0) goto L_0x0051
                goto L_0x0052
            L_0x004f:
                r10 = move-exception
                goto L_0x005c
            L_0x0051:
                r10 = 2
            L_0x0052:
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x004f }
                if (r11 == 0) goto L_0x0075
                r11.close()     // Catch:{ SQLiteException -> 0x0069 }
                goto L_0x0075
            L_0x005c:
                throw r10     // Catch:{ all -> 0x005d }
            L_0x005d:
                r0 = move-exception
                if (r11 == 0) goto L_0x0068
                r11.close()     // Catch:{ all -> 0x0064 }
                goto L_0x0068
            L_0x0064:
                r11 = move-exception
                r10.addSuppressed(r11)     // Catch:{ SQLiteException -> 0x0069 }
            L_0x0068:
                throw r0     // Catch:{ SQLiteException -> 0x0069 }
            L_0x0069:
                r10 = move-exception
                java.lang.String r11 = "NumberInCallHistoryWorker.doInBackground"
                java.lang.String r0 = "query call log error"
                com.android.dialer.common.LogUtil.m7e((java.lang.String) r11, (java.lang.String) r0, (java.lang.Throwable) r10)
                java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
            L_0x0075:
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.spam.SpamCallListListener.NumberInCallHistoryWorker.doInBackground(java.lang.Object):java.lang.Object");
        }
    }

    public SpamCallListListener(Context context2, DialerExecutorFactory dialerExecutorFactory2) {
        Random random2 = new Random();
        this.context = context2;
        this.random = random2;
        Assert.isNotNull(dialerExecutorFactory2);
        this.dialerExecutorFactory = dialerExecutorFactory2;
    }

    public void onCallListChange(CallList callList) {
    }

    public void onDisconnect(DialerCall dialerCall) {
        ((SpamSettingsStub) SpamComponent.get(this.context).spamSettings()).isSpamNotificationEnabled();
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
        String number = dialerCall.getNumber();
        if (!TextUtils.isEmpty(number)) {
            if (!PermissionsUtil.hasCallLogReadPermissions(this.context)) {
                LogUtil.m9i("SpamCallListListener.onIncomingCall", "call log permission missing, not checking if number is in call history", new Object[0]);
                return;
            }
            ((DefaultDialerExecutorFactory) this.dialerExecutorFactory).createNonUiTaskBuilder(new NumberInCallHistoryWorker(this.context, number, dialerCall.getCountryIso())).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    DialerCall.this.setCallHistoryStatus(((Integer) obj).intValue());
                }
            }).build().executeParallel(null);
        }
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }
}

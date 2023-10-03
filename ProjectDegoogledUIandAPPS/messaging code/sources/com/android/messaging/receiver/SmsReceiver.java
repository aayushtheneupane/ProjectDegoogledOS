package com.android.messaging.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.NoConfirmationSmsSendService;
import com.android.messaging.datamodel.action.ReceiveSmsMessageAction;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import p026b.p027a.p030b.p031a.C0632a;

public final class SmsReceiver extends BroadcastReceiver {

    /* renamed from: sb */
    private static ArrayList f1455sb;

    /* renamed from: Bm */
    private static void m2317Bm() {
        C1449g.get().getString("bugle_sms_ignore_message_regex", "");
        String[] split = "".split("\n");
        if (split.length != 0) {
            f1455sb = new ArrayList();
            for (int i = 0; i < split.length; i++) {
                try {
                    f1455sb.add(Pattern.compile(split[i]));
                } catch (PatternSyntaxException unused) {
                    StringBuilder Pa = C0632a.m1011Pa("compileIgnoreSmsPatterns: Skipping bad expression: ");
                    Pa.append(split[i]);
                    C1430e.m3622e("MessagingApp", Pa.toString());
                }
            }
        }
    }

    /* renamed from: Cm */
    private static String m2318Cm() {
        return C0967f.get().getApplicationContext().getPackageName() + ":secondaryuser";
    }

    /* renamed from: Na */
    public static void m2319Na() {
        NotificationManagerCompat.from(C0967f.get().getApplicationContext()).cancel(m2318Cm(), 1);
    }

    /* renamed from: a */
    public static void m2321a(Context context, Intent intent) {
        SmsMessage[] messagesFromIntent = getMessagesFromIntent(intent);
        if (messagesFromIntent == null || messagesFromIntent.length < 1) {
            C1430e.m3622e("MessagingApp", "processReceivedSms: null or zero or ignored message");
            return;
        }
        m2320a(context, C1474sa.getDefault().mo8202a(intent, "subscription"), intent.getIntExtra("errorCode", -1), messagesFromIntent);
        C1029y.m2412Ki();
    }

    /* renamed from: b */
    public static void m2322b(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (C1464na.m3757Xj()) {
            z3 = C1464na.m3762ak();
            z2 = false;
            z = true;
        } else {
            z4 = C1474sa.getDefault().mo8230lk();
            z3 = z4;
            z2 = z3;
            z = z2;
        }
        PackageManager packageManager = context.getPackageManager();
        boolean isLoggable = Log.isLoggable("MessagingApp", 2);
        if (z3) {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Enabling SMS message receiving");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, SmsReceiver.class), 1, 1);
        } else {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Disabling SMS message receiving");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, SmsReceiver.class), 2, 1);
        }
        if (z4) {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Enabling MMS message receiving");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, MmsWapPushReceiver.class), 1, 1);
        } else {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Disabling MMS message receiving");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, MmsWapPushReceiver.class), 2, 1);
        }
        if (z2) {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Enabling SMS/MMS broadcast abort");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, AbortSmsReceiver.class), 1, 1);
            packageManager.setComponentEnabledSetting(new ComponentName(context, AbortMmsWapPushReceiver.class), 1, 1);
        } else {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Disabling SMS/MMS broadcast abort");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, AbortSmsReceiver.class), 2, 1);
            packageManager.setComponentEnabledSetting(new ComponentName(context, AbortMmsWapPushReceiver.class), 2, 1);
        }
        if (z) {
            if (isLoggable) {
                C1430e.m3628v("MessagingApp", "Enabling respond via message intent");
            }
            packageManager.setComponentEnabledSetting(new ComponentName(context, NoConfirmationSmsSendService.class), 1, 1);
            return;
        }
        if (isLoggable) {
            C1430e.m3628v("MessagingApp", "Disabling respond via message intent");
        }
        packageManager.setComponentEnabledSetting(new ComponentName(context, NoConfirmationSmsSendService.class), 2, 1);
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        SmsMessage[] messagesFromIntent = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        if (messagesFromIntent != null && messagesFromIntent.length >= 1) {
            try {
                String displayMessageBody = messagesFromIntent[0].getDisplayMessageBody();
                if (displayMessageBody != null) {
                    if (f1455sb == null) {
                        m2317Bm();
                    }
                    Iterator it = f1455sb.iterator();
                    while (it.hasNext()) {
                        if (((Pattern) it.next()).matcher(displayMessageBody).matches()) {
                            return null;
                        }
                    }
                }
                return messagesFromIntent;
            } catch (NullPointerException unused) {
                C1430e.m3622e("MessagingApp", "shouldIgnoreMessage: NPE inside SmsMessage");
            }
        }
        return null;
    }

    public void onReceive(Context context, Intent intent) {
        C1430e.m3628v("MessagingApp", "SmsReceiver.onReceive " + intent);
        if (C1474sa.getDefault().mo8230lk()) {
            String action = intent.getAction();
            if (C1464na.m3762ak() && ("android.provider.Telephony.SMS_RECEIVED".equals(action) || "android.provider.Telephony.MMS_DOWNLOADED".equals(action))) {
                Context applicationContext = C0967f.get().getApplicationContext();
                Resources resources = applicationContext.getResources();
                PendingIntent t = C1040Ea.get().mo6983t(applicationContext);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext, (String) null);
                builder.setContentTitle(resources.getString(R.string.secondary_user_new_message_title)).setTicker(resources.getString(R.string.secondary_user_new_message_ticker)).setSmallIcon(R.drawable.ic_sms_light).setPriority(1).setContentIntent(t);
                NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
                bigTextStyle.bigText(resources.getString(R.string.secondary_user_new_message_title));
                Notification build = bigTextStyle.build();
                NotificationManagerCompat from = NotificationManagerCompat.from(C0967f.get().getApplicationContext());
                build.defaults = 6;
                from.notify(m2318Cm(), 1, build);
            } else if (!C1464na.m3757Xj()) {
                m2321a(context, intent);
            }
        }
    }

    /* renamed from: a */
    public static void m2320a(Context context, int i, int i2, SmsMessage[] smsMessageArr) {
        ContentValues a = C1029y.m2418a(smsMessageArr, i2);
        C1430e.m3628v("MessagingApp", "SmsReceiver.deliverSmsMessages");
        long currentTimeMillis = System.currentTimeMillis();
        SmsMessage smsMessage = smsMessageArr[0];
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2011, 8, 18);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTimeInMillis(currentTimeMillis);
        if (gregorianCalendar2.before(gregorianCalendar)) {
            currentTimeMillis = smsMessage.getTimestampMillis();
        }
        a.put("date", Long.valueOf(Long.valueOf(currentTimeMillis).longValue()));
        a.put("read", 0);
        a.put("seen", 0);
        if (C1464na.m3759Zj()) {
            a.put("sub_id", Integer.valueOf(i));
        }
        if (smsMessageArr[0].getMessageClass() == SmsMessage.MessageClass.CLASS_0 || C1410N.m3545Lj()) {
            C0967f.get().mo6652Od().mo6957a(context, a);
        } else {
            new ReceiveSmsMessageAction(a).start();
        }
    }
}

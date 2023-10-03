package com.android.dialer.blocking;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.R$dimen;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.storage.StorageComponent;
import java.util.concurrent.TimeUnit;

@Deprecated
public class FilteredNumbersUtil {
    public static final String LAST_EMERGENCY_CALL_MS_PREF_KEY = "last_emergency_call_ms";
    static final long RECENT_EMERGENCY_CALL_THRESHOLD_MS = TimeUnit.DAYS.toMillis(2);

    public interface ImportSendToVoicemailContactsListener {
        void onImportComplete();
    }

    public static class PhoneQuery {
        public static final String[] PROJECTION = {"_id", "data4", "data1"};
    }

    public static boolean canBlockNumber(Context context, String str, String str2) {
        String blockableNumber = getBlockableNumber(context, str, str2);
        return !TextUtils.isEmpty(blockableNumber) && !PhoneNumberUtils.isEmergencyNumber(blockableNumber);
    }

    public static String getBlockableNumber(Context context, String str, String str2) {
        return (FilteredNumberCompat.useNewFiltering(context) && TextUtils.isEmpty(str)) ? str2 : str;
    }

    public static long getLastEmergencyCallTimeMillis(Context context) {
        return StorageComponent.get(context).unencryptedSharedPrefs().getLong(LAST_EMERGENCY_CALL_MS_PREF_KEY, 0);
    }

    public static boolean hasRecentEmergencyCall(Context context) {
        long j;
        if (context == null) {
            return false;
        }
        Long valueOf = Long.valueOf(getLastEmergencyCallTimeMillis(context));
        if (valueOf.longValue() == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - valueOf.longValue();
        if (LogUtil.isVerboseEnabled()) {
            j = Settings.System.getLong(context.getContentResolver(), "dialer_emergency_call_threshold_ms", 0);
            if (j <= 0) {
                j = RECENT_EMERGENCY_CALL_THRESHOLD_MS;
            }
        } else {
            j = RECENT_EMERGENCY_CALL_THRESHOLD_MS;
        }
        if (currentTimeMillis < j) {
            return true;
        }
        return false;
    }

    public static void importSendToVoicemailContacts(final Context context, final ImportSendToVoicemailContactsListener importSendToVoicemailContactsListener) {
        ((LoggingBindingsStub) Logger.get(context)).logInteraction(InteractionEvent$Type.IMPORT_SEND_TO_VOICEMAIL);
        final FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler = new FilteredNumberAsyncQueryHandler(context);
        new AsyncTask<Object, Void, Boolean>() {
            /* JADX INFO: finally extract failed */
            public Object doInBackground(Object[] objArr) {
                Cursor query;
                Context context = context;
                if (context == null || (query = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PhoneQuery.PROJECTION, "send_to_voicemail=1", (String[]) null, (String) null)) == null) {
                    return false;
                }
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(1);
                        String string2 = query.getString(2);
                        if (string != null) {
                            filteredNumberAsyncQueryHandler.blockNumber((FilteredNumberAsyncQueryHandler.OnBlockNumberListener) null, string, string2, (String) null);
                        }
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                query.close();
                ContentValues contentValues = new ContentValues();
                contentValues.put("send_to_voicemail", 0);
                context.getContentResolver().update(ContactsContract.Contacts.CONTENT_URI, contentValues, "send_to_voicemail=1", (String[]) null);
                return true;
            }

            public void onPostExecute(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    ImportSendToVoicemailContactsListener importSendToVoicemailContactsListener = importSendToVoicemailContactsListener;
                    if (importSendToVoicemailContactsListener != null) {
                        importSendToVoicemailContactsListener.onImportComplete();
                        return;
                    }
                    return;
                }
                Context context = context;
                if (context != null) {
                    Toast.makeText(context, context.getString(R.string.send_to_voicemail_import_failed), 0).show();
                }
            }
        }.execute(new Object[0]);
    }

    public static void maybeNotifyCallBlockingDisabled(final Context context) {
        if (!FilteredNumberCompat.useNewFiltering(context) && !StorageComponent.get(context).unencryptedSharedPrefs().getBoolean("notified_call_blocking_disabled_by_emergency_call", false)) {
            new FilteredNumberAsyncQueryHandler(context).hasBlockedNumbers(new FilteredNumberAsyncQueryHandler.OnHasBlockedNumbersListener() {
                public void onHasBlockedNumbers(boolean z) {
                    Context context = context;
                    if (context != null && z) {
                        Notification.Builder autoCancel = new Notification.Builder(context).setSmallIcon(R.drawable.quantum_ic_block_white_24).setContentTitle(context.getString(R.string.call_blocking_disabled_notification_title)).setContentText(context.getString(R.string.call_blocking_disabled_notification_text)).setAutoCancel(true);
                        int i = Build.VERSION.SDK_INT;
                        autoCancel.setChannelId("phone_default");
                        Context context2 = context;
                        autoCancel.setContentIntent(PendingIntent.getActivity(context2, 0, FilteredNumberCompat.createManageBlockedNumbersIntent(context2), 134217728));
                        DialerNotificationManager.notify(context, "call_blocking", 10, autoCancel.build());
                        StorageComponent.get(context).unencryptedSharedPrefs().edit().putBoolean("notified_call_blocking_disabled_by_emergency_call", true).apply();
                    }
                }
            });
        }
    }

    public static void recordLastEmergencyCallTime(Context context) {
        if (context != null) {
            StorageComponent.get(context).unencryptedSharedPrefs().edit().putLong(LAST_EMERGENCY_CALL_MS_PREF_KEY, System.currentTimeMillis()).putBoolean("notified_call_blocking_disabled_by_emergency_call", false).apply();
            if (R$dimen.isUserUnlocked(context)) {
                maybeNotifyCallBlockingDisabled(context);
            }
        }
    }
}

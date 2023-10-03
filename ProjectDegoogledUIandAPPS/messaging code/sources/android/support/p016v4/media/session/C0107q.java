package android.support.p016v4.media.session;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.p025a.C0365g;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.mmslib.p039a.C0973c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import java.io.UnsupportedEncodingException;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: android.support.v4.media.session.q */
public class C0107q {
    /* renamed from: Bb */
    public static boolean m121Bb() {
        C1451h Hd = C1451h.m3724Hd();
        Context applicationContext = C0967f.get().getApplicationContext();
        return Hd.getBoolean(applicationContext.getString(R.string.swipe_right_deletes_conversation_key), applicationContext.getResources().getBoolean(R.bool.swipe_right_deletes_conversation_default));
    }

    /* renamed from: a */
    public static boolean m129a(AccessibilityManager accessibilityManager) {
        int i = Build.VERSION.SDK_INT;
        return accessibilityManager.isTouchExplorationEnabled();
    }

    public static boolean areSameOptions(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        if (bundle == null) {
            if (bundle2.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                return true;
            }
            return false;
        } else if (bundle2 == null) {
            if (bundle.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                return true;
            }
            return false;
        } else if (bundle.getInt("android.media.browse.extra.PAGE", -1) == bundle2.getInt("android.media.browse.extra.PAGE", -1) && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m131b(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: c */
    public static byte[] m132c(String str, int i) {
        if (i == 0) {
            return str.getBytes();
        }
        try {
            return str.getBytes(C0973c.m2208va(i));
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    /* renamed from: d */
    public static NotificationChannel m133d(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        return ((NotificationManager) context.getSystemService(NotificationManager.class)).getNotificationChannel(str);
    }

    /* renamed from: f */
    public static boolean m134f(Context context) {
        return ((AccessibilityManager) context.getSystemService("accessibility")).isTouchExplorationEnabled();
    }

    public static boolean isAllWhitespace(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isLayoutRtl(View view) {
        if (C1464na.m3755Vj()) {
            return 1 == view.getLayoutDirection();
        }
        return false;
    }

    public static void propagateIfInstanceOf(Throwable th, Class cls) {
        if (th != null && cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            return contentResolver.query(uri, strArr, str, strArr2, str2);
        } catch (SQLiteException e) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when query", e);
            return null;
        } catch (IllegalArgumentException e2) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when query", e2);
            return null;
        }
    }

    public static String replaceUnicodeDigits(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            int digit = Character.digit(c, 10);
            if (digit != -1) {
                sb.append(digit);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt >= 'A' && charAt <= 'Z') {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (c >= 'A' && c <= 'Z') {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    /* renamed from: b */
    public static void m130b(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(C0107q.class.getClassLoader());
        }
    }

    /* renamed from: a */
    public static void m127a(Context context, String str, String str2, int i, String str3) {
        int i2 = Build.VERSION.SDK_INT;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        if (notificationManager.getNotificationChannel(str) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, i);
            notificationChannel.enableLights(true);
            if (str3 != null) {
                notificationChannel.setGroup(str3);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /* renamed from: a */
    public static int m122a(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            return contentResolver.update(uri, contentValues, str, strArr);
        } catch (SQLiteException e) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when update", e);
            return -1;
        } catch (IllegalArgumentException e2) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when update", e2);
            return -1;
        }
    }

    /* renamed from: a */
    public static void m128a(View view, AccessibilityManager accessibilityManager, CharSequence charSequence) {
        Context applicationContext = view.getContext().getApplicationContext();
        if (accessibilityManager == null) {
            accessibilityManager = (AccessibilityManager) applicationContext.getSystemService("accessibility");
        }
        if (accessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(C1464na.m3754Uj() ? 16384 : 8);
            obtain.getText().add(charSequence);
            obtain.setEnabled(view.isEnabled());
            obtain.setClassName(view.getClass().getName());
            obtain.setPackageName(applicationContext.getPackageName());
            new C0365g(obtain).setSource(view);
            accessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    /* renamed from: a */
    public static void m126a(Context context, String str, int i) {
        int i2 = Build.VERSION.SDK_INT;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        if (notificationManager.getNotificationChannelGroup(str) == null) {
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(str, context.getString(i)));
        }
    }

    /* renamed from: a */
    public static Uri m123a(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
        try {
            return contentResolver.insert(uri, contentValues);
        } catch (SQLiteException e) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when insert", e);
            return null;
        } catch (IllegalArgumentException e2) {
            C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when insert", e2);
            return null;
        }
    }

    /* renamed from: a */
    public static String m124a(Resources resources, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '0':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_zero, sb, " ");
                    break;
                case '1':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_one, sb, " ");
                    break;
                case '2':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_two, sb, " ");
                    break;
                case '3':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_three, sb, " ");
                    break;
                case '4':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_four, sb, " ");
                    break;
                case '5':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_five, sb, " ");
                    break;
                case '6':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_six, sb, " ");
                    break;
                case '7':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_seven, sb, " ");
                    break;
                case '8':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_eight, sb, " ");
                    break;
                case '9':
                    C0632a.m1019a(resources, (int) R.string.content_description_for_number_nine, sb, " ");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return new java.lang.String(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        return new java.lang.String(r1, "iso-8859-1");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0012 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m125a(byte[] r1, int r2) {
        /*
            if (r2 != 0) goto L_0x0008
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            return r2
        L_0x0008:
            java.lang.String r2 = com.android.messaging.mmslib.p039a.C0973c.m2208va(r2)     // Catch:{ UnsupportedEncodingException -> 0x0012 }
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0012 }
            r0.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x0012 }
            return r0
        L_0x0012:
            java.lang.String r2 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x001a }
            java.lang.String r0 = "iso-8859-1"
            r2.<init>(r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x001a }
            return r2
        L_0x001a:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p016v4.media.session.C0107q.m125a(byte[], int):java.lang.String");
    }
}

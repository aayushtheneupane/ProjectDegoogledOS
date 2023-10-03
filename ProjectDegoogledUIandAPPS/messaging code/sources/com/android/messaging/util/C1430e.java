package com.android.messaging.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.p032ex.chips.C0699ra;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.e */
public class C1430e {

    /* renamed from: GJ */
    private static C1425ba f2241GJ;

    /* renamed from: HJ */
    private static volatile boolean f2242HJ;

    /* renamed from: IJ */
    private static Typeface f2243IJ;

    /* renamed from: JJ */
    private static Typeface f2244JJ;

    /* renamed from: Hj */
    public static Typeface m3609Hj() {
        C1424b.m3593oc();
        if (f2243IJ == null) {
            f2243IJ = Typeface.create(Typeface.SANS_SERIF, 1);
        }
        return f2243IJ;
    }

    /* renamed from: Ij */
    public static Typeface m3610Ij() {
        C1424b.m3593oc();
        if (f2244JJ == null) {
            f2244JJ = Typeface.create(Typeface.SANS_SERIF, 0);
        }
        return f2244JJ;
    }

    /* renamed from: a */
    private static synchronized File m3612a(File file, String str, String str2) {
        synchronized (C1414S.class) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            String str3 = simpleDateFormat.format(date) + "_%02d." + str;
            for (int i = 1; i <= 99; i++) {
                File file2 = new File(file, String.format(Locale.US, str3, new Object[]{Integer.valueOf(i)}));
                if (!file2.exists()) {
                    file2.createNewFile();
                    return file2;
                }
            }
            println(6, "MessagingApp", "Too many duplicate file names: " + str3);
            return null;
        }
    }

    /* renamed from: b */
    public static void m3616b(C1449g gVar) {
        gVar.mo8172i(new C1427ca(gVar));
        m3618c(gVar);
    }

    /* renamed from: c */
    public static void m3618c(C1449g gVar) {
        C1425ba baVar;
        gVar.getBoolean("bugle_logsaver", false);
        f2242HJ = false;
        if (f2242HJ && ((baVar = f2241GJ) == null || !baVar.mo8051Oj())) {
            C1449g.get().getBoolean("bugle_persistent_logsaver", false);
            C1449g.get().getInt("bugle_in_memory_logsaver_record_count", 500);
            f2241GJ = new C1423aa(500);
        } else if (!f2242HJ && f2241GJ != null) {
            f2241GJ = null;
        }
    }

    /* renamed from: d */
    public static String m3619d(C0699ra raVar) {
        if (raVar.getContactId() == -1001) {
            return C0967f.get().getApplicationContext().getResources().getString(R.string.contact_list_send_to_text, new Object[]{m3617c(raVar)});
        } else if (!TextUtils.isEmpty(raVar.getDisplayName())) {
            return raVar.getDisplayName();
        } else {
            return m3617c(raVar);
        }
    }

    public static void dump(PrintWriter printWriter) {
        C1425ba baVar = f2241GJ;
        if (baVar != null) {
            baVar.dump(printWriter);
        }
    }

    /* renamed from: e */
    public static boolean m3624e(C0699ra raVar) {
        return raVar.getContactId() == -1001;
    }

    /* renamed from: i */
    public static void m3625i(String str, String str2) {
        println(4, str, str2);
    }

    private static void println(int i, String str, String str2) {
        Log.println(i, str, str2);
        C1425ba baVar = f2241GJ;
        if (baVar != null && i >= 3) {
            baVar.mo8052b(i, str, str2);
        }
    }

    /* renamed from: u */
    public static boolean m3627u(Uri uri) {
        if (!TextUtils.equals(uri.getScheme(), "file")) {
            return false;
        }
        File file = new File(uri.getPath());
        try {
            File canonicalFile = Environment.getDataDirectory().getCanonicalFile();
            for (File canonicalFile2 = file.getCanonicalFile(); canonicalFile2 != null; canonicalFile2 = canonicalFile2.getParentFile()) {
                if (canonicalFile.equals(canonicalFile2)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            m3623e("MessagingApp", "Error while accessing file", e);
            return false;
        }
    }

    /* renamed from: v */
    public static void m3628v(String str, String str2) {
        println(2, str, str2);
    }

    /* renamed from: va */
    public static C0699ra m3629va(String str) {
        return m3615b(str, -1000);
    }

    /* renamed from: w */
    public static void m3630w(String str, String str2) {
        println(5, str, str2);
    }

    /* renamed from: wa */
    public static C0699ra m3632wa(String str) {
        return m3615b(str, -1001);
    }

    public static void wtf(String str, String str2) {
        println(7, str, C0632a.m1025k("wtf\n", str2));
        Log.wtf(str, str2, new Exception());
    }

    /* renamed from: xa */
    public static String m3633xa(String str) {
        if (str == null) {
            return null;
        }
        if (Log.isLoggable("MessagingApp", 3)) {
            return str;
        }
        StringBuilder Pa = C0632a.m1011Pa("Redacted-");
        Pa.append(str.length());
        return Pa.toString();
    }

    /* renamed from: e */
    public static void m3622e(String str, String str2) {
        println(6, str, str2);
    }

    /* renamed from: i */
    public static void m3626i(String str, String str2, Throwable th) {
        println(4, str, str2 + 10 + Log.getStackTraceString(th));
    }

    /* renamed from: w */
    public static void m3631w(String str, String str2, Throwable th) {
        println(5, str, str2);
        println(5, str, Log.getStackTraceString(th));
    }

    /* renamed from: b */
    private static C0699ra m3615b(String str, long j) {
        return C0699ra.m1079a((String) null, 40, str, -1, (String) null, j, (Long) null, j, j == -1001 ? C1426c.f2237FJ : null, true, (String) null);
    }

    /* renamed from: e */
    public static void m3623e(String str, String str2, Throwable th) {
        println(6, str, str2);
        println(6, str, Log.getStackTraceString(th));
    }

    public static void wtf(String str, String str2, Throwable th) {
        println(7, str, "wtf\n" + str2 + 10 + Log.getStackTraceString(th));
        Log.wtf(str, str2, th);
    }

    /* renamed from: d */
    public static void m3620d(String str, String str2) {
        println(3, str, str2);
    }

    /* renamed from: c */
    public static String m3617c(C0699ra raVar) {
        return C1474sa.getDefault().mo8220Ia(raVar.getDestination());
    }

    /* renamed from: d */
    public static void m3621d(String str, String str2, Throwable th) {
        println(3, str, str2 + 10 + Log.getStackTraceString(th));
    }

    /* renamed from: a */
    public static boolean m3614a(Context context, Activity activity) {
        C0947h.get().mo6591Qd();
        C0967f.get().mo6654Qd();
        if (!C1464na.m3758Yj() || !((UserManager) context.getSystemService("user")).hasUserRestriction("no_sms")) {
            return true;
        }
        new AlertDialog.Builder(activity, R.style.BugleThemeDialog).setMessage(R.string.requires_sms_permissions_message).setCancelable(false).setNegativeButton(R.string.requires_sms_permissions_close_button, new C1428d()).show();
        return false;
    }

    /* renamed from: a */
    public static File m3611a(File file, String str) {
        return m3612a(file, MimeTypeMap.getSingleton().getExtensionFromMimeType(str), C0967f.get().getApplicationContext().getString(C1481w.isImageType(str) ? R.string.new_image_file_name_format : R.string.new_file_name_format));
    }

    /* renamed from: a */
    public static void m3613a(int i, String str, String str2) {
        C1425ba baVar = f2241GJ;
        if (baVar != null) {
            baVar.mo8052b(i, str, str2);
        }
    }
}

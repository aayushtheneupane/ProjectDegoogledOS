package com.android.messaging.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.widget.ArrayAdapter;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.google.common.p043io.C1715d;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.N */
public class C1410N {
    /* access modifiers changed from: private */

    /* renamed from: YJ */
    public static boolean f2211YJ;
    /* access modifiers changed from: private */

    /* renamed from: ZJ */
    public static boolean f2212ZJ;

    /* renamed from: _J */
    private static MediaPlayer[] f2213_J;
    private static final Object sLock = new Object();

    /* renamed from: Fa */
    public static byte[] m3544Fa(String str) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(m3551e(str, false)));
            bArr = C1715d.m4648e(bufferedInputStream);
            if (bArr == null || bArr.length < 1) {
                C1430e.m3622e("MessagingApp", "receiveFromDumpFile: empty data");
            }
            bufferedInputStream.close();
        } catch (IOException e) {
            C0632a.m1020a("receiveFromDumpFile: ", (Object) e, "MessagingApp", (Throwable) e);
        } catch (Throwable th) {
            bufferedInputStream.close();
            throw th;
        }
        return bArr;
    }

    /* renamed from: Lj */
    public static boolean m3545Lj() {
        return f2212ZJ;
    }

    /* renamed from: Mj */
    public static File m3546Mj() {
        return Environment.getExternalStorageDirectory();
    }

    /* renamed from: Nj */
    public static boolean m3547Nj() {
        C1449g.get().getBoolean("bugle_debugging", false);
        return false;
    }

    static /* synthetic */ void access$200() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.android.messaging");
        intent.putExtra("android.intent.extra.STREAM", Uri.parse("file:///system/media/audio/ringtones/Andromeda.ogg"));
        intent.setType("image/*");
        C0967f.get().getApplicationContext().startActivity(intent);
    }

    /* renamed from: b */
    public static void m3548b(File file) {
        if (file.exists()) {
            file.setReadable(true, false);
        }
    }

    /* renamed from: c */
    public static void m3549c(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, 17367043);
        arrayAdapter.add(new C1397B("Dump Database"));
        arrayAdapter.add(new C1399C("Log Telephony Data"));
        arrayAdapter.add(new C1400D("Toggle Noise"));
        arrayAdapter.add(new C1401E("Force sync SMS"));
        arrayAdapter.add(new C1402F("Sync SMS"));
        arrayAdapter.add(new C1403G("Load SMS/MMS from dump file", activity));
        arrayAdapter.add(new C1404H("Email SMS/MMS dump file", activity));
        arrayAdapter.add(new C1405I("MMS Config...", activity));
        arrayAdapter.add(new C1406J(f2212ZJ ? "Turn off Class 0 sms test" : "Turn on Class Zero test"));
        arrayAdapter.add(new C1487z("Test sharing a file URI"));
        builder.setAdapter(arrayAdapter, new C1395A(arrayAdapter));
        builder.create().show();
    }

    /* renamed from: e */
    public static File m3551e(String str, boolean z) {
        File file = new File(m3546Mj(), str);
        if (z && file.exists()) {
            file.delete();
        }
        return file;
    }

    /* renamed from: g */
    public static void m3552g(Context context, int i) {
        if (f2211YJ) {
            synchronized (sLock) {
                try {
                    if (f2213_J == null) {
                        f2213_J = new MediaPlayer[2];
                        f2213_J[0] = MediaPlayer.create(context, R.raw.server_request_debug);
                        f2213_J[1] = MediaPlayer.create(context, R.raw.db_op_debug);
                        f2213_J[1].setVolume(1.0f, 1.0f);
                        f2213_J[0].setVolume(0.3f, 0.3f);
                    }
                    if (f2213_J[i] != null) {
                        f2213_J[i].start();
                    }
                } catch (IllegalArgumentException e) {
                    C1430e.m3623e("bugle.util.DebugUtils", "MediaPlayer exception", e);
                } catch (SecurityException e2) {
                    C1430e.m3623e("bugle.util.DebugUtils", "MediaPlayer exception", e2);
                } catch (IllegalStateException e3) {
                    C1430e.m3623e("bugle.util.DebugUtils", "MediaPlayer exception", e3);
                }
            }
        }
    }

    public static StackTraceElement getCaller(int i) {
        if (i >= 0) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= i + 2) {
                for (int i2 = 0; i2 < stackTrace.length - 1; i2++) {
                    if ("getCaller".equals(stackTrace[i2].getMethodName())) {
                        return stackTrace[i2 + i + 1];
                    }
                }
            }
            return null;
        }
        throw new IllegalArgumentException("depth cannot be negative");
    }
}

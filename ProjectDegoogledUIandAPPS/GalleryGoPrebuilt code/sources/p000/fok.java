package p000;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: fok */
/* compiled from: PG */
public final class fok {

    /* renamed from: a */
    private static final Pattern f10150a = Pattern.compile("VmHWM:\\s*(\\d+)\\s*kB");

    /* renamed from: b */
    private static volatile boolean f10151b;

    /* renamed from: c */
    private static Method f10152c;

    private fok() {
    }

    /* renamed from: a */
    public static irc m9317a(int i, int i2, String str, Context context, String str2, boolean z) {
        String str3 = str2;
        fxk.m9836c();
        ife.m12898e((Object) context);
        Debug.MemoryInfo[] processMemoryInfo = fom.m9322a(context).getProcessMemoryInfo(new int[]{i2});
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        fom.m9322a(context).getMemoryInfo(memoryInfo);
        Long l = null;
        if (z && Build.VERSION.SDK_INT >= 29) {
            try {
                String str4 = new String(iab.m12562b(new File("/proc/self/status")).mo8317a(), Charset.defaultCharset());
                if (hpz.m11899a(str4)) {
                    flw.m9203e("PrimesMemoryCapture", "Null or empty proc status", new Object[0]);
                } else {
                    Matcher matcher = f10150a.matcher(str4);
                    try {
                        if (matcher.find()) {
                            l = Long.valueOf(Long.parseLong(matcher.group(1)));
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            } catch (IOException e2) {
                flw.m9200c("PrimesMemoryCapture", "Error reading proc status", e2, new Object[0]);
            }
        }
        iit iit = (iit) irc.f14819g.mo8793g();
        iir g = ira.f14814c.mo8793g();
        Debug.MemoryInfo memoryInfo2 = processMemoryInfo[0];
        iir g2 = iqy.f14785v.mo8793g();
        int i3 = memoryInfo2.dalvikPss;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy = (iqy) g2.f14318b;
        iqy.f14787a |= 1;
        iqy.f14788b = i3;
        int i4 = memoryInfo2.nativePss;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy2 = (iqy) g2.f14318b;
        iqy2.f14787a |= 2;
        iqy2.f14789c = i4;
        int i5 = memoryInfo2.otherPss;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy3 = (iqy) g2.f14318b;
        iqy3.f14787a |= 4;
        iqy3.f14790d = i5;
        int i6 = memoryInfo2.dalvikPrivateDirty;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy4 = (iqy) g2.f14318b;
        iqy4.f14787a |= 8;
        iqy4.f14791e = i6;
        int i7 = memoryInfo2.nativePrivateDirty;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy5 = (iqy) g2.f14318b;
        iqy5.f14787a |= 16;
        iqy5.f14792f = i7;
        int i8 = memoryInfo2.otherPrivateDirty;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy6 = (iqy) g2.f14318b;
        iqy6.f14787a |= 32;
        iqy6.f14793g = i8;
        int totalPss = memoryInfo2.getTotalPss();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy7 = (iqy) g2.f14318b;
        iqy7.f14787a |= 64;
        iqy7.f14794h = totalPss;
        int i9 = Build.VERSION.SDK_INT;
        int totalPrivateClean = memoryInfo2.getTotalPrivateClean();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy8 = (iqy) g2.f14318b;
        iqy8.f14787a |= 128;
        iqy8.f14795i = totalPrivateClean;
        int totalSwappablePss = memoryInfo2.getTotalSwappablePss();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy9 = (iqy) g2.f14318b;
        iqy9.f14787a |= 512;
        iqy9.f14797k = totalSwappablePss;
        int totalSharedDirty = memoryInfo2.getTotalSharedDirty();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy10 = (iqy) g2.f14318b;
        iqy10.f14787a |= 256;
        iqy10.f14796j = totalSharedDirty;
        int i10 = Build.VERSION.SDK_INT;
        int a = m9316a(memoryInfo2);
        if (a != -1) {
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            iqy iqy11 = (iqy) g2.f14318b;
            iqy11.f14787a |= 1024;
            iqy11.f14798l = a;
        }
        if (l != null) {
            long longValue = l.longValue();
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            iqy iqy12 = (iqy) g2.f14318b;
            iqy12.f14787a |= 524288;
            iqy12.f14807u = longValue;
        }
        int i11 = Build.VERSION.SDK_INT;
        try {
            Map<String, String> memoryStats = memoryInfo2.getMemoryStats();
            Integer a2 = m9319a(memoryStats.get("summary.code"));
            if (a2 != null) {
                int intValue = a2.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy13 = (iqy) g2.f14318b;
                iqy13.f14787a |= 4096;
                iqy13.f14800n = intValue;
            }
            Integer a3 = m9319a(memoryStats.get("summary.stack"));
            if (a3 != null) {
                int intValue2 = a3.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy14 = (iqy) g2.f14318b;
                iqy14.f14787a |= 8192;
                iqy14.f14801o = intValue2;
            }
            Integer a4 = m9319a(memoryStats.get("summary.graphics"));
            if (a4 != null) {
                int intValue3 = a4.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy15 = (iqy) g2.f14318b;
                iqy15.f14787a |= 16384;
                iqy15.f14802p = intValue3;
            }
            Integer a5 = m9319a(memoryStats.get("summary.system"));
            if (a5 != null) {
                int intValue4 = a5.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy16 = (iqy) g2.f14318b;
                iqy16.f14787a |= 65536;
                iqy16.f14804r = intValue4;
            }
            Integer a6 = m9319a(memoryStats.get("summary.java-heap"));
            if (a6 != null) {
                int intValue5 = a6.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy17 = (iqy) g2.f14318b;
                iqy17.f14787a |= 2048;
                iqy17.f14799m = intValue5;
            }
            Integer a7 = m9319a(memoryStats.get("summary.private-other"));
            if (a7 != null) {
                int intValue6 = a7.intValue();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iqy iqy18 = (iqy) g2.f14318b;
                iqy18.f14787a |= 32768;
                iqy18.f14803q = intValue6;
            }
        } catch (NumberFormatException e3) {
            flw.m9203e("PrimesMemoryCapture", "failed to collect memory summary stats", new Object[0]);
        }
        int i12 = (int) (memoryInfo.availMem >> 10);
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy19 = (iqy) g2.f14318b;
        iqy19.f14787a |= 131072;
        iqy19.f14805s = i12;
        int i13 = (int) (memoryInfo.totalMem >> 20);
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        iqy iqy20 = (iqy) g2.f14318b;
        iqy20.f14787a |= 262144;
        iqy20.f14806t = i13;
        iqy iqy21 = (iqy) g2.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ira ira = (ira) g.f14318b;
        iqy21.getClass();
        ira.f14817b = iqy21;
        ira.f14816a |= 1;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        irc irc = (irc) iit.f14318b;
        ira ira2 = (ira) g.mo8770g();
        ira2.getClass();
        irc.f14822b = ira2;
        irc.f14821a |= 1;
        iir g3 = iro.f14893c.mo8793g();
        irn a8 = foj.m9311a(str, context);
        if (g3.f14319c) {
            g3.mo8751b();
            g3.f14319c = false;
        }
        iro iro = (iro) g3.f14318b;
        a8.getClass();
        iro.f14896b = a8;
        iro.f14895a |= 1;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        irc irc2 = (irc) iit.f14318b;
        iro iro2 = (iro) g3.mo8770g();
        iro2.getClass();
        irc2.f14823c = iro2;
        irc2.f14821a |= 2;
        iir g4 = iqz.f14808c.mo8793g();
        boolean c = fom.m9324c(context);
        if (g4.f14319c) {
            g4.mo8751b();
            g4.f14319c = false;
        }
        iqz iqz = (iqz) g4.f14318b;
        iqz.f14810a = 1 | iqz.f14810a;
        iqz.f14811b = c;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        irc irc3 = (irc) iit.f14318b;
        iqz iqz2 = (iqz) g4.mo8770g();
        iqz2.getClass();
        irc3.f14825e = iqz2;
        irc3.f14821a |= 8;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        irc irc4 = (irc) iit.f14318b;
        irc4.f14824d = i - 1;
        int i14 = irc4.f14821a | 4;
        irc4.f14821a = i14;
        if (str3 != null) {
            str2.getClass();
            irc4.f14821a = i14 | 16;
            irc4.f14826f = str3;
        }
        return (irc) iit.mo8770g();
    }

    /* renamed from: a */
    public static irc m9318a(int i, Context context, String str, boolean z) {
        return m9317a(i, Process.myPid(), (String) null, context, str, z);
    }

    /* renamed from: a */
    private static int m9316a(Debug.MemoryInfo memoryInfo) {
        Method a = m9320a();
        if (a == null) {
            return -1;
        }
        try {
            return ((Integer) a.invoke(memoryInfo, new Object[]{14})).intValue();
        } catch (Error | Exception e) {
            f10152c = null;
            flw.m9200c("PrimesMemoryCapture", "MemoryInfo.getOtherPss(which) invocation failure", e, new Object[0]);
            return -1;
        }
    }

    /* renamed from: a */
    private static Method m9320a() {
        if (!f10151b) {
            synchronized (fok.class) {
                if (!f10151b) {
                    try {
                        f10152c = Debug.MemoryInfo.class.getDeclaredMethod("getOtherPss", new Class[]{Integer.TYPE});
                    } catch (NoSuchMethodException e) {
                        flw.m9192a("PrimesMemoryCapture", "MemoryInfo.getOtherPss(which) not found", (Throwable) e, new Object[0]);
                    } catch (Error | Exception e2) {
                        flw.m9200c("PrimesMemoryCapture", "MemoryInfo.getOtherPss(which) failure", e2, new Object[0]);
                    }
                    f10151b = true;
                }
            }
        }
        return f10152c;
    }

    /* renamed from: a */
    private static Integer m9319a(String str) {
        if (str != null) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        return null;
    }
}

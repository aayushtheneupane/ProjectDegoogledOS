package p000;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.SystemClock;
import com.google.android.libraries.performance.primes.metriccapture.PackageStatsCapture;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: fkc */
/* compiled from: PG */
final /* synthetic */ class fkc implements Runnable {

    /* renamed from: a */
    private final fkd f9869a;

    public fkc(fkd fkd) {
        this.f9869a = fkd;
    }

    public final void run() {
        hso hso;
        File file;
        fkd fkd = this.f9869a;
        SharedPreferences sharedPreferences = fkd.f9871e;
        long j = fkd.f9870d;
        fxk.m9836c();
        long j2 = sharedPreferences.getLong("primes.packageMetric.lastSendTime", -1);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime < j2) {
            if (!sharedPreferences.edit().remove("primes.packageMetric.lastSendTime").commit()) {
                flw.m9199b("SamplingUtil", "Failure storing timestamp to SharedPreferences", new Object[0]);
            }
            j2 = -1;
        }
        if (j2 == -1 || elapsedRealtime > j2 + j) {
            PackageStats packageStats = PackageStatsCapture.getPackageStats(fkd.f9685a);
            if (packageStats != null) {
                iir g = isc.f14974r.mo8793g();
                ife.m12898e((Object) packageStats);
                iir g2 = iry.f14947k.mo8793g();
                long j3 = packageStats.cacheSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry = (iry) g2.f14318b;
                iry.f14949a |= 1;
                iry.f14950b = j3;
                long j4 = packageStats.codeSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry2 = (iry) g2.f14318b;
                iry2.f14949a |= 2;
                iry2.f14951c = j4;
                long j5 = packageStats.dataSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry3 = (iry) g2.f14318b;
                iry3.f14949a |= 4;
                iry3.f14952d = j5;
                long j6 = packageStats.externalCacheSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry4 = (iry) g2.f14318b;
                iry4.f14949a |= 8;
                iry4.f14953e = j6;
                long j7 = packageStats.externalCodeSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry5 = (iry) g2.f14318b;
                iry5.f14949a |= 16;
                iry5.f14954f = j7;
                long j8 = packageStats.externalDataSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry6 = (iry) g2.f14318b;
                iry6.f14949a |= 32;
                iry6.f14955g = j8;
                long j9 = packageStats.externalMediaSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry7 = (iry) g2.f14318b;
                iry7.f14949a |= 64;
                iry7.f14956h = j9;
                long j10 = packageStats.externalObbSize;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                iry iry8 = (iry) g2.f14318b;
                iry8.f14949a |= 128;
                iry8.f14957i = j10;
                iry iry9 = (iry) g2.mo8770g();
                iir iir = (iir) iry9.mo8790b(5);
                iir.mo8503a((iix) iry9);
                if (fkd.f9872f) {
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    ((iry) iir.f14318b).f14958j = iry.m13615l();
                    Application application = fkd.f9685a;
                    int i = fkd.f9873g;
                    hso hso2 = fkd.f9874h;
                    fxk.m9836c();
                    ArrayList arrayList = new ArrayList();
                    try {
                        file = new File(application.getPackageManager().getApplicationInfo(application.getPackageName(), 0).dataDir);
                    } catch (PackageManager.NameNotFoundException e) {
                        try {
                            flw.m9202d("DirStatsCapture", "Failed to use package manager getting data directory from context instead.", new Object[0]);
                            File filesDir = application.getFilesDir();
                            if (filesDir != null) {
                                file = filesDir.getParentFile();
                            } else {
                                file = null;
                            }
                        } catch (Exception e2) {
                            String valueOf = String.valueOf(e2);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
                            sb.append("Failed to retrieve DirStats: ");
                            sb.append(valueOf);
                            flw.m9202d("DirStatsCapture", sb.toString(), new Object[0]);
                            hso = hso.m12047f();
                        }
                    }
                    if (file != null) {
                        foi foi = new foi(file, arrayList, i, hso2);
                        foi.mo5999a(new foh(foi));
                        hso = hso.m12041a((Collection) arrayList);
                    } else {
                        hso = hso.m12047f();
                    }
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    iry iry10 = (iry) iir.f14318b;
                    iry10.mo9069a();
                    igz.m12986a((Iterable) hso, (List) iry10.f14958j);
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                isc isc = (isc) g.f14318b;
                iry iry11 = (iry) iir.mo8770g();
                iry11.getClass();
                isc.f14985j = iry11;
                isc.f14976a |= 256;
                fkd.mo5728a((isc) g.mo8770g());
                SharedPreferences sharedPreferences2 = fkd.f9871e;
                if (!sharedPreferences2.edit().putLong("primes.packageMetric.lastSendTime", SystemClock.elapsedRealtime()).commit()) {
                    flw.m9199b("PackageMetricService", "Failure storing timestamp persistently", new Object[0]);
                    return;
                }
                return;
            }
            flw.m9202d("PackageMetricService", "PackageStats capture failed.", new Object[0]);
        }
    }
}

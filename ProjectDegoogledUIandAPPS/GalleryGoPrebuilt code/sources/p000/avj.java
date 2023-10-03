package p000;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

/* renamed from: avj */
/* compiled from: PG */
public final class avj implements avc {

    /* renamed from: a */
    private final avv f1760a;

    /* renamed from: b */
    private final File f1761b;

    /* renamed from: c */
    private final long f1762c;

    /* renamed from: d */
    private final avg f1763d = new avg();

    /* renamed from: e */
    private apu f1764e;

    @Deprecated
    public avj(File file, long j) {
        this.f1761b = file;
        this.f1762c = j;
        this.f1760a = new avv();
    }

    /* renamed from: a */
    public final synchronized void mo1667a() {
        try {
            m1759b().mo1469e();
        } catch (IOException e) {
            try {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to clear disk cache or disk cache cleared externally", e);
                }
            } catch (Throwable th) {
                m1760c();
                throw th;
            }
        }
        m1760c();
    }

    /* renamed from: a */
    public final File mo1666a(aqu aqu) {
        try {
            apt a = m1759b().mo1461a(this.f1760a.mo1675a(aqu));
            if (a != null) {
                return a.f1379a[0];
            }
            return null;
        } catch (IOException e) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            return null;
        }
    }

    /* renamed from: b */
    private final synchronized apu m1759b() {
        if (this.f1764e == null) {
            File file = this.f1761b;
            long j = this.f1762c;
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    apu.m1417a(file2, file3, false);
                }
            }
            apu apu = new apu(file, j);
            if (apu.f1381b.exists()) {
                try {
                    apu.mo1462a();
                    apu.m1416a(apu.f1382c);
                    Iterator it = apu.f1386g.values().iterator();
                    while (it.hasNext()) {
                        aps aps = (aps) it.next();
                        if (aps.f1376e != null) {
                            aps.f1376e = null;
                            for (int i = 0; i < apu.f1383d; i = 1) {
                                apu.m1416a(aps.mo1459b());
                                apu.m1416a(aps.mo1460c());
                            }
                            it.remove();
                        } else {
                            for (int i2 = 0; i2 < apu.f1383d; i2 = 1) {
                                apu.f1384e += aps.f1373b[0];
                            }
                        }
                    }
                } catch (IOException e) {
                    PrintStream printStream = System.out;
                    String valueOf = String.valueOf(file);
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36 + String.valueOf(message).length());
                    sb.append("DiskLruCache ");
                    sb.append(valueOf);
                    sb.append(" is corrupt: ");
                    sb.append(message);
                    sb.append(", removing");
                    printStream.println(sb.toString());
                    apu.mo1469e();
                }
                this.f1764e = apu;
            }
            file.mkdirs();
            apu = new apu(file, j);
            apu.mo1465b();
            this.f1764e = apu;
        }
        return this.f1764e;
    }

    /* renamed from: a */
    public final void mo1668a(aqu aqu, avb avb) {
        ave ave;
        avg avg;
        File c;
        String a = this.f1760a.mo1675a(aqu);
        avg avg2 = this.f1763d;
        synchronized (avg2) {
            ave = (ave) avg2.f1756a.get(a);
            if (ave == null) {
                avf avf = avg2.f1757b;
                synchronized (avf.f1755a) {
                    ave = (ave) avf.f1755a.poll();
                }
                if (ave == null) {
                    ave = new ave();
                }
                avg2.f1756a.put(a, ave);
            }
            ave.f1754b++;
        }
        ave.f1753a.lock();
        try {
            apu b = m1759b();
            if (b.mo1461a(a) == null) {
                apr b2 = b.mo1464b(a);
                if (b2 == null) {
                    throw new IllegalStateException(a.length() == 0 ? new String("Had two simultaneous puts for: ") : "Had two simultaneous puts for: ".concat(a));
                }
                try {
                    synchronized (b2.f1371d) {
                        aps aps = b2.f1368a;
                        if (aps.f1376e == b2) {
                            if (!aps.f1375d) {
                                b2.f1369b[0] = true;
                            }
                            c = aps.mo1460c();
                            if (!b2.f1371d.f1380a.exists()) {
                                b2.f1371d.f1380a.mkdirs();
                            }
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                    if (((aso) avb).f1534a.mo1488a(((aso) avb).f1535b, c, ((aso) avb).f1536c)) {
                        b2.f1371d.mo1463a(b2, true);
                        b2.f1370c = true;
                    }
                    b2.mo1457b();
                    avg = this.f1763d;
                    avg.mo1669a(a);
                } catch (Throwable th) {
                    b2.mo1457b();
                    throw th;
                }
            } else {
                avg = this.f1763d;
                avg.mo1669a(a);
            }
        } catch (IOException e) {
            try {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
            } catch (Throwable th2) {
                this.f1763d.mo1669a(a);
                throw th2;
            }
        }
    }

    /* renamed from: c */
    private final synchronized void m1760c() {
        this.f1764e = null;
    }
}

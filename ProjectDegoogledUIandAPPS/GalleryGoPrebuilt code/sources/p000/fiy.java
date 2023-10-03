package p000;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: fiy */
/* compiled from: PG */
final class fiy implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    public final Thread.UncaughtExceptionHandler f9761a;

    /* renamed from: b */
    private final /* synthetic */ fiz f9762b;

    public fiy(fiz fiz, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f9762b = fiz;
        this.f9761a = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        iqx iqx;
        try {
            if (this.f9762b.mo5731b()) {
                fiz fiz = this.f9762b;
                String name = thread.getName();
                iir g = irt.f14911i.mo8793g();
                String a = fjy.m9052a(fiz.f9764e);
                int i = 4;
                if (a != null) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    irt irt = (irt) g.f14318b;
                    a.getClass();
                    irt.f14913a |= 4;
                    irt.f14916d = a;
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                irt irt2 = (irt) g.f14318b;
                int i2 = irt2.f14913a | 1;
                irt2.f14913a = i2;
                irt2.f14914b = true;
                name.getClass();
                irt2.f14913a = i2 | 8;
                irt2.f14917e = name;
                Class<?> cls = th.getClass();
                if (cls == OutOfMemoryError.class) {
                    i = 3;
                } else if (NullPointerException.class.isAssignableFrom(cls)) {
                    i = 2;
                } else if (!RuntimeException.class.isAssignableFrom(cls)) {
                    if (!Error.class.isAssignableFrom(cls)) {
                        i = 1;
                    } else {
                        i = 5;
                    }
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                irt irt3 = (irt) g.f14318b;
                irt3.f14918f = i - 1;
                irt3.f14913a |= 16;
                String name2 = th.getClass().getName();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                irt irt4 = (irt) g.f14318b;
                name2.getClass();
                irt4.f14913a |= 64;
                irt4.f14920h = name2;
                try {
                    StringWriter stringWriter = new StringWriter();
                    ifn.m12934a(th, new PrintWriter(stringWriter));
                    String stringWriter2 = stringWriter.toString();
                    StringBuilder sb = new StringBuilder();
                    Matcher matcher = Pattern.compile("([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+)(?:(\nCaused by: )([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+))?(?:(\nCaused by: )([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+))?").matcher(stringWriter2);
                    if (matcher.find()) {
                        int i3 = 1;
                        while (i3 <= matcher.groupCount() && matcher.group(i3) != null) {
                            sb.append(matcher.group(i3));
                            i3++;
                        }
                    }
                    Long a2 = fje.m9029a(sb.toString());
                    if (a2 != null) {
                        long longValue = a2.longValue();
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        irt irt5 = (irt) g.f14318b;
                        irt5.f14913a |= 32;
                        irt5.f14919g = longValue;
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 38);
                    sb2.append("Failed to generate hashed stack trace.");
                    sb2.append(valueOf);
                    flw.m9202d("CrashMetricService", sb2.toString(), new Object[0]);
                }
                iqx iqx2 = null;
                try {
                    iir g2 = iro.f14893c.mo8793g();
                    irn a3 = foj.m9311a((String) null, fiz.f9685a);
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    iro iro = (iro) g2.f14318b;
                    a3.getClass();
                    iro.f14896b = a3;
                    iro.f14895a |= 1;
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    irt irt6 = (irt) g.f14318b;
                    iro iro2 = (iro) g2.mo8770g();
                    iro2.getClass();
                    irt6.f14915c = iro2;
                    irt6.f14913a |= 2;
                } catch (Exception e2) {
                    flw.m9198b("CrashMetricService", "Failed to get process stats.", e2, new Object[0]);
                }
                irt irt7 = (irt) g.mo8770g();
                iir g3 = isc.f14974r.mo8793g();
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                isc isc = (isc) g3.f14318b;
                irt7.getClass();
                isc.f14983h = irt7;
                isc.f14976a |= 64;
                iqk iqk = this.f9762b.f9765f;
                if (iqk != null) {
                    try {
                        iqx = (iqx) iqk.mo2097a();
                    } catch (Exception e3) {
                        flw.m9198b("CrashMetricService", "Exception while getting crash metric extension!", e3, new Object[0]);
                        iqx = null;
                    }
                    if (!iqx.f14783a.equals(iqx)) {
                        iqx2 = iqx;
                    }
                    if (iqx2 != null) {
                        if (g3.f14319c) {
                            g3.mo8751b();
                            g3.f14319c = false;
                        }
                        isc isc2 = (isc) g3.f14318b;
                        iqx2.getClass();
                        isc2.f14988m = iqx2;
                        isc2.f14976a |= 8192;
                    }
                }
                this.f9762b.mo5848g();
                this.f9762b.mo5728a((isc) g3.mo8770g());
                if (!this.f9762b.f9687c) {
                    fiz fiz2 = this.f9762b;
                    fpa fpa = fiz2.f9766g;
                    fjy fjy = fiz2.f9764e;
                    fpa.mo6012a();
                }
            }
            uncaughtExceptionHandler = this.f9761a;
            if (uncaughtExceptionHandler == null) {
                return;
            }
        } catch (Exception e4) {
            flw.m9198b("CrashMetricService", "Failed to record crash.", e4, new Object[0]);
            uncaughtExceptionHandler = this.f9761a;
            if (uncaughtExceptionHandler == null) {
                return;
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f9761a;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }
}

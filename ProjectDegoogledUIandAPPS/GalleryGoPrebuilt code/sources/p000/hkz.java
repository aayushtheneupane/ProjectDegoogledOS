package p000;

import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* renamed from: hkz */
/* compiled from: PG */
public final class hkz implements hmd, gth {

    /* renamed from: a */
    private final hkf f12952a;

    public hkz(hkf hkf) {
        this.f12952a = hkf;
    }

    /* renamed from: a */
    public final void mo7541a(hmt hmt, SparseArray sparseArray) {
    }

    /* renamed from: a */
    public final void mo7034a() {
        if (!this.f12952a.mo7512a().isEmpty()) {
            int i = 0;
            int i2 = 0;
            for (hmt hmt : this.f12952a.mo7512a().values()) {
                int i3 = i2 + 1;
                StringBuilder sb = new StringBuilder(18);
                sb.append("Trace: ");
                sb.append(i2);
                Log.println(4, "trace_manager", sb.toString());
                if ((hmt.f13057a & 16) != 0) {
                    hkq hkq = hmt.f13063g;
                    if (hkq == null) {
                        hkq = hkq.f12942d;
                    }
                    if ((hkq.f12944a & 2) != 0) {
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[2];
                        objArr[i] = ((hlh) hmt.f13060d.get(i)).f12969b;
                        hko hko = hkq.f12946c;
                        if (hko == null) {
                            hko = hko.f12933d;
                        }
                        objArr[1] = Integer.valueOf(hko.f12937c);
                        Log.println(4, "trace_manager", String.format(locale, "Trace %s timed out before completion. %s spans remaining", objArr));
                    }
                    if ((hkq.f12944a & 1) != 0) {
                        Locale locale2 = Locale.US;
                        Object[] objArr2 = new Object[2];
                        objArr2[i] = ((hlh) hmt.f13060d.get(i)).f12969b;
                        hkp hkp = hkq.f12945b;
                        if (hkp == null) {
                            hkp = hkp.f12938c;
                        }
                        objArr2[1] = Integer.valueOf(hkp.f12941b);
                        Log.println(4, "trace_manager", String.format(locale2, "Trace %s tried to log too many spans. %s spans dropped", objArr2));
                    }
                }
                C0296kt ktVar = new C0296kt();
                ije ije = hmt.f13060d;
                int size = ije.size();
                for (int i4 = 0; i4 < size; i4++) {
                    hlh hlh = (hlh) ije.get(i4);
                    ktVar.mo9231a((long) hlh.f12970c, hlh);
                }
                ArrayList arrayList = new ArrayList();
                for (int i5 = 0; i5 < ktVar.mo9232b(); i5++) {
                    hlh hlh2 = (hlh) ktVar.mo9233b(i5);
                    long j = (long) hlh2.f12970c;
                    long j2 = hlh2.f12973f;
                    StringBuilder sb2 = new StringBuilder(23);
                    sb2.append(j2);
                    sb2.append(" ms");
                    String sb3 = sb2.toString();
                    while (true) {
                        hlh hlh3 = (hlh) ktVar.mo9229a(j);
                        if (hlh3 != null) {
                            j = (long) hlh3.f12971d;
                            String str = hlh3.f12969b;
                            StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(sb3).length());
                            sb4.append(str);
                            sb4.append(" > ");
                            sb4.append(sb3);
                            sb3 = sb4.toString();
                            if (j == -1) {
                                break;
                            }
                        } else {
                            String valueOf = String.valueOf(sb3);
                            sb3 = valueOf.length() == 0 ? new String("Orphaned Root > ") : "Orphaned Root > ".concat(valueOf);
                        }
                    }
                    arrayList.add(String.format(Locale.US, "%06d\t%s", new Object[]{Long.valueOf(hlh2.f12972e), sb3}));
                }
                Collections.sort(arrayList);
                int size2 = arrayList.size();
                for (int i6 = 0; i6 < size2; i6++) {
                    Log.println(4, "trace_manager", (String) arrayList.get(i6));
                }
                i2 = i3;
                i = 0;
            }
        }
    }
}

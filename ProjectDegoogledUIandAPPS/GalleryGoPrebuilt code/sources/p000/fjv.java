package p000;

import android.os.SystemClock;
import android.text.TextUtils;

/* renamed from: fjv */
/* compiled from: PG */
final class fjv {

    /* renamed from: a */
    public final fop f9850a;

    /* renamed from: b */
    public final hqk f9851b;

    /* renamed from: c */
    public final int f9852c;

    /* renamed from: d */
    private final iqk f9853d;

    /* renamed from: e */
    private final hqk f9854e;

    public fjv(iqk iqk, hqk hqk, hqk hqk2, int i, int i2) {
        this.f9853d = (iqk) ife.m12898e((Object) iqk);
        this.f9854e = (hqk) ife.m12898e((Object) hqk);
        this.f9851b = hqk2;
        this.f9852c = i;
        this.f9850a = new fop(i2);
    }

    /* renamed from: a */
    public final void mo5887a(String str, boolean z, isc isc, iqx iqx, String str2) {
        if (isc != null) {
            fjx fjx = (fjx) this.f9854e.mo2652a();
            iir iir = (iir) isc.mo8790b(5);
            iir.mo8503a((iix) isc);
            iir g = irr.f14902g.mo8793g();
            int i = fjx.f9864h;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irr irr = (irr) g.f14318b;
            irr.f14907d = i - 1;
            int i2 = irr.f14904a | 4;
            irr.f14904a = i2;
            String str3 = fjx.f9857a;
            if (str3 != null) {
                str3.getClass();
                irr.f14904a = i2 | 1;
                irr.f14905b = str3;
            }
            long longValue = fjx.f9862f.longValue();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irr irr2 = (irr) g.f14318b;
            int i3 = irr2.f14904a | 8;
            irr2.f14904a = i3;
            irr2.f14908e = longValue;
            String str4 = fjx.f9859c;
            if (str4 != null) {
                str4.getClass();
                i3 |= 2;
                irr2.f14904a = i3;
                irr2.f14906c = str4;
            }
            String str5 = fjx.f9858b;
            if (str5 != null) {
                str5.getClass();
                irr2.f14904a = i3 | 16;
                irr2.f14909f = str5;
            }
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            isc isc2 = (isc) iir.f14318b;
            irr irr3 = (irr) g.mo8770g();
            irr3.getClass();
            isc2.f14981f = irr3;
            isc2.f14976a |= 16;
            iir g2 = iru.f14921d.mo8793g();
            long freeSpace = fjx.f9860d.mo5375a().getFreeSpace() / 1024;
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            iru iru = (iru) g2.f14318b;
            int i4 = iru.f14923a | 1;
            iru.f14923a = i4;
            iru.f14924b = freeSpace;
            long j = fjx.f9861e;
            iru.f14923a = i4 | 2;
            iru.f14925c = j;
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            isc isc3 = (isc) iir.f14318b;
            iru iru2 = (iru) g2.mo8770g();
            iru2.getClass();
            isc3.f14992q = iru2;
            isc3.f14976a |= 4194304;
            hqk hqk = fjx.f9863g;
            String str6 = hqk != null ? ((fjy) hqk.mo2652a()).f9865a : null;
            if (!TextUtils.isEmpty(str6)) {
                irp irp = isc.f14991p;
                if (irp == null) {
                    irp = irp.f14897c;
                }
                iir iir2 = (iir) irp.mo8790b(5);
                iir2.mo8503a((iix) irp);
                if (!((irp) iir2.f14318b).f14900b.isEmpty()) {
                    String str7 = str6 + "::" + ((irp) iir2.f14318b).f14900b;
                    if (iir2.f14319c) {
                        iir2.mo8751b();
                        iir2.f14319c = false;
                    }
                    irp irp2 = (irp) iir2.f14318b;
                    str7.getClass();
                    irp2.f14899a |= 1;
                    irp2.f14900b = str7;
                } else {
                    if (iir2.f14319c) {
                        iir2.mo8751b();
                        iir2.f14319c = false;
                    }
                    irp irp3 = (irp) iir2.f14318b;
                    str6.getClass();
                    irp3.f14899a |= 1;
                    irp3.f14900b = str6;
                }
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                isc isc4 = (isc) iir.f14318b;
                irp irp4 = (irp) iir2.mo8770g();
                irp4.getClass();
                isc4.f14991p = irp4;
                isc4.f14976a |= 1048576;
            }
            isc isc5 = (isc) iir.mo8770g();
            iir iir3 = (iir) isc5.mo8790b(5);
            iir3.mo8503a((iix) isc5);
            if (!z) {
                if (str != null) {
                    if (iir3.f14319c) {
                        iir3.mo8751b();
                        iir3.f14319c = false;
                    }
                    isc isc6 = (isc) iir3.f14318b;
                    str.getClass();
                    isc6.f14976a |= 4;
                    isc6.f14979d = str;
                } else {
                    if (iir3.f14319c) {
                        iir3.mo8751b();
                        iir3.f14319c = false;
                    }
                    isc isc7 = (isc) iir3.f14318b;
                    isc7.f14976a &= -5;
                    isc7.f14979d = isc.f14974r.f14979d;
                }
            } else if (str != null) {
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                isc isc8 = (isc) iir3.f14318b;
                str.getClass();
                isc8.f14976a |= 65536;
                isc8.f14990o = str;
            } else {
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                isc isc9 = (isc) iir3.f14318b;
                isc9.f14976a &= -65537;
                isc9.f14990o = isc.f14974r.f14990o;
            }
            if (iqx != null) {
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                isc isc10 = (isc) iir3.f14318b;
                iqx.getClass();
                isc10.f14988m = iqx;
                isc10.f14976a |= 8192;
            }
            if (str2 != null) {
                iir g3 = irp.f14897c.mo8793g();
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                irp irp5 = (irp) g3.f14318b;
                str2.getClass();
                irp5.f14899a |= 1;
                irp5.f14900b = str2;
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                isc isc11 = (isc) iir3.f14318b;
                irp irp6 = (irp) g3.mo8770g();
                irp6.getClass();
                isc11.f14991p = irp6;
                isc11.f14976a |= 1048576;
            }
            ((foy) this.f9853d.mo2097a()).mo5891a((isc) iir3.mo8770g());
            fop fop = this.f9850a;
            synchronized (fop.f10157a) {
                fop.f10159c++;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - fop.f10160d > 1000) {
                    fop.f10159c = 0;
                    fop.f10160d = elapsedRealtime;
                }
            }
            return;
        }
        String valueOf = String.valueOf(str);
        flw.m9202d("MetricRecorder", valueOf.length() == 0 ? new String("metric is null, skipping recorded metric for event: ") : "metric is null, skipping recorded metric for event: ".concat(valueOf), new Object[0]);
    }
}

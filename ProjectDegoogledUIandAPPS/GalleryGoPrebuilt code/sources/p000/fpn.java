package p000;

import android.content.Context;
import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: fpn */
/* compiled from: PG */
public final class fpn implements foy {

    /* renamed from: a */
    private static final fpm f10202a = new fpj();

    /* renamed from: c */
    private static final fpm f10203c = new fpk();

    /* renamed from: d */
    private static final fpm f10204d = new fpl();

    /* renamed from: e */
    private final Object f10205e;

    /* renamed from: f */
    private final fow f10206f;

    /* renamed from: g */
    private final Context f10207g;

    /* renamed from: h */
    private final ext f10208h;

    /* renamed from: i */
    private final String f10209i;

    /* renamed from: j */
    private final Map f10210j;

    /* renamed from: k */
    private final eyq f10211k;

    public /* synthetic */ fpn(Context context, ext ext, String str, fow fow) {
        this.f10205e = new Object();
        this.f10210j = Collections.synchronizedMap(new HashMap());
        this.f10211k = new fpb();
        this.f10207g = context.getApplicationContext();
        this.f10208h = (ext) ife.m12898e((Object) ext);
        this.f10209i = (String) ife.m12898e((Object) str);
        this.f10206f = (fow) ife.m12898e((Object) fow);
    }

    /* renamed from: b */
    private final exs m9365b(String str) {
        exs exs;
        synchronized (this.f10205e) {
            if (this.f10210j.get(str) == null) {
                this.f10210j.put(str, this.f10208h.mo5383a(this.f10207g, str, (String) null));
            }
            exs = (exs) this.f10210j.get(str);
        }
        return exs;
    }

    /* renamed from: a */
    public static void m9362a(eyr eyr) {
        flw.m9193a("ClearcutTransmitter", "handleResult, success: %b", Boolean.valueOf(eyr.mo5408b()));
        if (!eyr.mo5408b()) {
            flw.m9199b("ClearcutTransmitter", "Clearcut logging failed", new Object[0]);
        }
    }

    public fpn() {
    }

    /* renamed from: a */
    private static void m9363a(fpm fpm, ike ike) {
        if (TextUtils.isEmpty(fpm.mo6018b(ike))) {
            fpm.mo6017a(ike, fje.m9029a(fpm.mo6016a(ike)));
        } else {
            fpm.mo6017a(ike, (Long) null);
        }
        fpm.mo6019c(ike);
    }

    /* renamed from: a */
    public final void mo5891a(isc isc) {
        fna fna = fkl.f9892a;
        iir iir = (iir) isc.mo8790b(5);
        iir.mo8503a((iix) isc);
        m9363a(f10202a, iir);
        isc isc2 = (isc) iir.f14318b;
        if ((isc2.f14976a & 512) != 0) {
            iqo iqo = isc2.f14986k;
            if (iqo == null) {
                iqo = iqo.f14672c;
            }
            if ((iqo.f14674a & 1) != 0) {
                iqo iqo2 = ((isc) iir.f14318b).f14986k;
                if (iqo2 == null) {
                    iqo2 = iqo.f14672c;
                }
                iqn iqn = iqo2.f14675b;
                if (iqn == null) {
                    iqn = iqn.f14660k;
                }
                iir iir2 = (iir) iqn.mo8790b(5);
                iir2.mo8503a((iix) iqn);
                m9363a(f10203c, iir2);
                iqo iqo3 = ((isc) iir.f14318b).f14986k;
                if (iqo3 == null) {
                    iqo3 = iqo.f14672c;
                }
                iir iir3 = (iir) iqo3.mo8790b(5);
                iir3.mo8503a((iix) iqo3);
                if (iir3.f14319c) {
                    iir3.mo8751b();
                    iir3.f14319c = false;
                }
                iqo iqo4 = (iqo) iir3.f14318b;
                iqn iqn2 = (iqn) iir2.mo8770g();
                iqn2.getClass();
                iqo4.f14675b = iqn2;
                iqo4.f14674a |= 1;
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                isc isc3 = (isc) iir.f14318b;
                iqo iqo5 = (iqo) iir3.mo8770g();
                isc isc4 = isc.f14974r;
                iqo5.getClass();
                isc3.f14986k = iqo5;
                isc3.f14976a |= 512;
            }
        }
        isc isc5 = (isc) iir.f14318b;
        if ((isc5.f14976a & 256) != 0) {
            iry iry = isc5.f14985j;
            if (iry == null) {
                iry = iry.f14947k;
            }
            if (iry.f14958j.size() != 0) {
                iry iry2 = ((isc) iir.f14318b).f14985j;
                if (iry2 == null) {
                    iry2 = iry.f14947k;
                }
                iir iir4 = (iir) iry2.mo8790b(5);
                iir4.mo8503a((iix) iry2);
                for (int i = 0; i < ((iry) iir4.f14318b).f14958j.size(); i++) {
                    irx irx = (irx) ((iry) iir4.f14318b).f14958j.get(i);
                    iir iir5 = (iir) irx.mo8790b(5);
                    iir5.mo8503a((iix) irx);
                    if (!TextUtils.isEmpty(((irx) iir5.f14318b).f14944b)) {
                        if (iir5.f14319c) {
                            iir5.mo8751b();
                            iir5.f14319c = false;
                        }
                        irx irx2 = irx.f14941e;
                        ((irx) iir5.f14318b).f14945c = irx.m13614k();
                        for (String a : m9364a(((irx) iir5.f14318b).f14944b)) {
                            long longValue = fje.m9029a(a).longValue();
                            if (iir5.f14319c) {
                                iir5.mo8751b();
                                iir5.f14319c = false;
                            }
                            irx irx3 = (irx) iir5.f14318b;
                            if (!irx3.f14945c.mo8521a()) {
                                irx3.f14945c = iix.m13607a(irx3.f14945c);
                            }
                            irx3.f14945c.mo8805a(longValue);
                        }
                    }
                    if (iir5.f14319c) {
                        iir5.mo8751b();
                        iir5.f14319c = false;
                    }
                    irx irx4 = (irx) iir5.f14318b;
                    irx irx5 = irx.f14941e;
                    irx4.f14943a &= -2;
                    irx4.f14944b = irx.f14941e.f14944b;
                    if (iir4.f14319c) {
                        iir4.mo8751b();
                        iir4.f14319c = false;
                    }
                    iry iry3 = (iry) iir4.f14318b;
                    irx irx6 = (irx) iir5.mo8770g();
                    irx6.getClass();
                    iry3.mo9069a();
                    iry3.f14958j.set(i, irx6);
                }
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                isc isc6 = (isc) iir.f14318b;
                iry iry4 = (iry) iir4.mo8770g();
                isc isc7 = isc.f14974r;
                iry4.getClass();
                isc6.f14985j = iry4;
                isc6.f14976a |= 256;
            }
        }
        isc isc8 = (isc) iir.f14318b;
        if ((isc8.f14976a & 32) != 0) {
            ire ire = isc8.f14982g;
            if (ire == null) {
                ire = ire.f14833b;
            }
            if (ire.f14835a.size() != 0) {
                ire ire2 = ((isc) iir.f14318b).f14982g;
                if (ire2 == null) {
                    ire2 = ire.f14833b;
                }
                iir iir6 = (iir) ire2.mo8790b(5);
                iir6.mo8503a((iix) ire2);
                for (int i2 = 0; i2 < ((ire) iir6.f14318b).f14835a.size(); i2++) {
                    ird ird = (ird) ((ire) iir6.f14318b).f14835a.get(i2);
                    iir iir7 = (iir) ird.mo8790b(5);
                    iir7.mo8503a((iix) ird);
                    if (!TextUtils.isEmpty(((ird) iir7.f14318b).f14831b)) {
                        if (iir7.f14319c) {
                            iir7.mo8751b();
                            iir7.f14319c = false;
                        }
                        ird ird2 = ird.f14828d;
                        ((ird) iir7.f14318b).f14832c = ird.m13614k();
                        String[] a2 = m9364a(((ird) iir7.f14318b).f14831b);
                        int length = a2.length;
                        long[] jArr = new long[length];
                        for (int i3 = 0; i3 < length; i3++) {
                            jArr[i3] = fje.m9029a(a2[i3]).longValue();
                        }
                        for (int i4 = 0; i4 < length; i4++) {
                            long j = jArr[i4];
                            if (iir7.f14319c) {
                                iir7.mo8751b();
                                iir7.f14319c = false;
                            }
                            ird ird3 = (ird) iir7.f14318b;
                            if (!ird3.f14832c.mo8521a()) {
                                ird3.f14832c = iix.m13607a(ird3.f14832c);
                            }
                            ird3.f14832c.mo8805a(j);
                        }
                    }
                    if (iir7.f14319c) {
                        iir7.mo8751b();
                        iir7.f14319c = false;
                    }
                    ird ird4 = (ird) iir7.f14318b;
                    ird ird5 = ird.f14828d;
                    ird4.f14830a &= -262145;
                    ird4.f14831b = ird.f14828d.f14831b;
                    if (iir6.f14319c) {
                        iir6.mo8751b();
                        iir6.f14319c = false;
                    }
                    ire ire3 = (ire) iir6.f14318b;
                    ird ird6 = (ird) iir7.mo8770g();
                    ird6.getClass();
                    if (!ire3.f14835a.mo8521a()) {
                        ire3.f14835a = iix.m13608a(ire3.f14835a);
                    }
                    ire3.f14835a.set(i2, ird6);
                }
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                isc isc9 = (isc) iir.f14318b;
                ire ire4 = (ire) iir6.mo8770g();
                isc isc10 = isc.f14974r;
                ire4.getClass();
                isc9.f14982g = ire4;
                isc9.f14976a |= 32;
            }
        }
        isc isc11 = (isc) iir.f14318b;
        if ((isc11.f14976a & 32768) != 0) {
            iri iri = isc11.f14989n;
            if (iri == null) {
                iri = iri.f14843h;
            }
            if (iri.f14848d.size() != 0) {
                iri iri2 = ((isc) iir.f14318b).f14989n;
                if (iri2 == null) {
                    iri2 = iri.f14843h;
                }
                iir iir8 = (iir) iri2.mo8790b(5);
                iir8.mo8503a((iix) iri2);
                for (int i5 = 0; i5 < ((iri) iir8.f14318b).f14848d.size(); i5++) {
                    irk irk = (irk) ((iri) iir8.f14318b).f14848d.get(i5);
                    iir iir9 = (iir) irk.mo8790b(5);
                    iir9.mo8503a((iix) irk);
                    m9363a(f10204d, iir9);
                    if (iir8.f14319c) {
                        iir8.mo8751b();
                        iir8.f14319c = false;
                    }
                    iri iri3 = (iri) iir8.f14318b;
                    irk irk2 = (irk) iir9.mo8770g();
                    irk2.getClass();
                    iri3.mo9068a();
                    iri3.f14848d.set(i5, irk2);
                }
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                isc isc12 = (isc) iir.f14318b;
                iri iri4 = (iri) iir8.mo8770g();
                isc isc13 = isc.f14974r;
                iri4.getClass();
                isc12.f14989n = iri4;
                isc12.f14976a |= 32768;
            }
        }
        byte[] ag = ((isc) iir.mo8770g()).mo8512ag();
        String str = this.f10209i;
        try {
            this.f10206f.mo6011a();
        } catch (Exception e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 73);
            sb.append("Failed to get Account Name, falling back to Zwieback logging, exception: ");
            sb.append(valueOf);
            flw.m9199b("ClearcutTransmitter", sb.toString(), new Object[0]);
        } catch (Throwable th) {
            exr a3 = m9365b(str).mo5381a(ag);
            a3.mo5379b((String) null);
            a3.mo5377a().mo5404a(this.f10211k);
            throw th;
        }
        exr a4 = m9365b(str).mo5381a(ag);
        a4.mo5379b((String) null);
        a4.mo5377a().mo5404a(this.f10211k);
    }

    /* renamed from: a */
    private static String[] m9364a(String str) {
        return str.replaceFirst("^/+", "").split("/+");
    }
}

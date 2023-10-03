package p000;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: gnc */
/* compiled from: PG */
final /* synthetic */ class gnc implements ice {

    /* renamed from: a */
    private final gne f11665a;

    /* renamed from: b */
    private final gna f11666b;

    /* renamed from: c */
    private final gng f11667c;

    public gnc(gne gne, gna gna, gng gng) {
        this.f11665a = gne;
        this.f11666b = gna;
        this.f11667c = gng;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        gne gne;
        int i;
        String str;
        gne gne2 = this.f11665a;
        gna gna = this.f11666b;
        gng gng = this.f11667c;
        ArrayList arrayList = new ArrayList();
        int a = gna.mo6855a();
        hsj j = hso.m12048j();
        char c = 0;
        for (int i2 = 0; i2 < a; i2++) {
            if (gna.f11660a.contains(gna.m10535a(i2, "created"))) {
                j.mo7908c(gkn.m10445a(i2, gtf.f12011a));
            }
        }
        hvs i3 = j.mo7905a().listIterator();
        while (i3.hasNext()) {
            gkn gkn = (gkn) i3.next();
            String[] strArr = new String[12];
            strArr[c] = "display_name";
            strArr[1] = "account_name";
            strArr[2] = "effective_gaia_id";
            strArr[3] = "gaia_id";
            strArr[4] = "profile_photo_url";
            strArr[5] = "is_managed_account";
            strArr[6] = "display_name";
            strArr[7] = "avatar_url";
            strArr[8] = "gaia_id";
            strArr[9] = "email_gaia_id";
            strArr[10] = "logged_in";
            strArr[11] = "logged_out";
            hvs hvs = i3;
            int i4 = 0;
            while (true) {
                gne = gne2;
                if (i4 >= 12) {
                    break;
                }
                gna.f11661b.add(gna.m10536b(gkn, strArr[i4]));
                i4++;
                gne2 = gne;
                strArr = strArr;
            }
            iir g = gle.f11566i.mo8793g();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            gle gle = (gle) g.f14318b;
            "google".getClass();
            gng gng2 = gng;
            gle.f11568a |= 64;
            gle.f11575h = "google";
            if (!gna.mo6857c(gkn, "is_managed_account")) {
                String a2 = gna.mo6856a(gkn, "account_name");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle2 = (gle) g.f14318b;
                a2.getClass();
                gle2.f11568a |= 4;
                gle2.f11571d = a2;
                int i5 = 2;
                String[] strArr2 = {"display_name", "account_name"};
                int i6 = 0;
                while (true) {
                    if (i6 < i5) {
                        str = gna.mo6856a(gkn, strArr2[i6]);
                        if (str != null) {
                            break;
                        }
                        i6++;
                        i5 = 2;
                    } else {
                        str = null;
                        break;
                    }
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle3 = (gle) g.f14318b;
                str.getClass();
                gle3.f11568a |= 2;
                gle3.f11570c = str;
                String a3 = gna.mo6856a(gkn, "gaia_id");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle4 = (gle) g.f14318b;
                a3.getClass();
                gle4.f11568a |= 1;
                gle4.f11569b = a3;
                String a4 = gna.mo6856a(gkn, "profile_photo_url");
                if (a4 != null) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    gle gle5 = (gle) g.f14318b;
                    a4.getClass();
                    gle5.f11568a |= 8;
                    gle5.f11572e = a4;
                }
            } else {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle6 = (gle) g.f14318b;
                gle6.f11568a |= 16;
                gle6.f11573f = true;
                String a5 = gna.mo6856a(gkn, "account_name");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle7 = (gle) g.f14318b;
                a5.getClass();
                gle7.f11568a |= 4;
                gle7.f11571d = a5;
                String a6 = gna.mo6856a(gkn, "display_name");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle8 = (gle) g.f14318b;
                a6.getClass();
                gle8.f11568a |= 2;
                gle8.f11570c = a6;
                String a7 = gna.mo6856a(gkn, "effective_gaia_id");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle9 = (gle) g.f14318b;
                a7.getClass();
                gle9.f11568a |= 1;
                gle9.f11569b = a7;
                String a8 = gna.mo6856a(gkn, "gaia_id");
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gle gle10 = (gle) g.f14318b;
                a8.getClass();
                gle10.f11568a |= 32;
                gle10.f11574g = a8;
                String a9 = gna.mo6856a(gkn, "avatar_url");
                if (a9 != null) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    gle gle11 = (gle) g.f14318b;
                    a9.getClass();
                    gle11.f11568a |= 8;
                    gle11.f11572e = a9;
                }
            }
            boolean c2 = gna.mo6857c(gkn, "logged_in");
            boolean c3 = gna.mo6857c(gkn, "logged_out");
            iir g2 = gnj.f11683e.mo8793g();
            int a10 = gkn.mo6807a();
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            gnj gnj = (gnj) g2.f14318b;
            gnj.f11685a |= 1;
            gnj.f11686b = a10;
            gle gle12 = (gle) g.mo8770g();
            gle12.getClass();
            gnj.f11687c = gle12;
            gnj.f11685a |= 2;
            if (c2) {
                i = 2;
            } else if (c3) {
                i = 3;
            } else {
                i = 1;
            }
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            gnj gnj2 = (gnj) g2.f14318b;
            gnj2.f11688d = i - 1;
            gnj2.f11685a |= 4;
            arrayList.add(ife.m12820a((Object) (gnj) g2.mo8770g()));
            i3 = hvs;
            gng = gng2;
            gne2 = gne;
            c = 0;
        }
        gng gng3 = gng;
        return ife.m12866b((Iterable) arrayList).mo8443a(hmq.m11749a((Callable) new gnd(gng, gna, arrayList)), (Executor) gne2.f11671a);
    }
}

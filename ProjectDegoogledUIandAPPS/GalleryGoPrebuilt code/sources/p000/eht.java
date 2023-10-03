package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import java.util.concurrent.Callable;

/* renamed from: eht */
/* compiled from: PG */
public final class eht extends eig implements hbf, ioe, hbd {

    /* renamed from: Z */
    private final C0002ab f8306Z = new C0002ab(this);

    /* renamed from: aa */
    private boolean f8307aa;

    /* renamed from: b */
    private ehy f8308b;

    /* renamed from: c */
    private Context f8309c;

    /* renamed from: d */
    private final hkx f8310d = new hkx(this);

    @Deprecated
    public eht() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f8306Z;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f8309c == null) {
            this.f8309c = new hcf((Context) this.f8336a);
        }
        return this.f8309c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4815Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f8336a != null) {
            return mo2628P();
        }
        return null;
    }

    /* renamed from: a */
    public final void mo2665a(int i, int i2, Intent intent) {
        ieh ieh;
        hlq a = this.f8310d.mo7537a();
        try {
            mo7266b(i, i2, intent);
            ehy R = mo2635n();
            if (i == 1) {
                R.mo4817a(i2);
            } else if (i == 2) {
                if (i2 == -1) {
                    if (!egx.f8238g.equals(R.f8327h)) {
                        egw egw = new egw(R.f8327h);
                        R.f8327h = egx.f8238g;
                        ieh = R.f8326g.mo5933a(hmq.m11749a((Callable) new ehv(R, egw)));
                    } else {
                        cwn.m5514b("CropAndSetWallpaperFragmentPeer: No temporary file available.", new Object[0]);
                        ieh = ife.m12820a((Object) 0);
                    }
                    R.f8323d.mo6987a(new grw(ieh), R.f8329j);
                } else {
                    R.mo4817a(0);
                }
            }
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo2631a(Activity activity) {
        hlq c = hnb.m11779c();
        try {
            super.mo2631a(activity);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo1832a(Context context) {
        hlq c = hnb.m11779c();
        try {
            if (!this.f8307aa) {
                super.mo1832a(context);
                if (this.f8308b == null) {
                    this.f8308b = ((ehz) mo2453b()).mo2392N();
                    this.f9583V.mo64a(new hbu(this.f8306Z));
                }
                if (c != null) {
                    c.close();
                    return;
                }
                return;
            }
            throw new IllegalStateException("A Fragment cannot be attached more than once. Instead, create a new Fragment instance.");
        } catch (ClassCastException e) {
            throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo165a(android.os.Bundle r10) {
        /*
            r9 = this;
            java.lang.String r0 = "CREATED_TEMP_FILE_METADATA"
            hlq r1 = p000.hnb.m11779c()
            r9.mo7268c(r10)     // Catch:{ all -> 0x00b4 }
            ehy r2 = r9.mo2635n()     // Catch:{ all -> 0x00b4 }
            grx r3 = r2.f8323d     // Catch:{ all -> 0x00b4 }
            gry r4 = r2.f8329j     // Catch:{ all -> 0x00b4 }
            r3.mo6988a(r4)     // Catch:{ all -> 0x00b4 }
            gvi r3 = r2.f8324e     // Catch:{ all -> 0x00b4 }
            eij r4 = r2.f8322c     // Catch:{ all -> 0x00b4 }
            guy r5 = p000.guy.DONT_CARE     // Catch:{ all -> 0x00b4 }
            gvc r6 = r2.f8328i     // Catch:{ all -> 0x00b4 }
            r3.mo7113a(r4, r5, r6)     // Catch:{ all -> 0x00b4 }
            if (r10 != 0) goto L_0x0022
            goto L_0x0042
        L_0x0022:
            boolean r3 = r10.containsKey(r0)     // Catch:{ all -> 0x00b4 }
            if (r3 == 0) goto L_0x0042
            android.os.Parcelable r10 = r10.getParcelable(r0)     // Catch:{ all -> 0x00b4 }
            imh r10 = (p000.imh) r10     // Catch:{ all -> 0x00b4 }
            java.lang.Object r10 = p000.ife.m12898e((java.lang.Object) r10)     // Catch:{ all -> 0x00b4 }
            imh r10 = (p000.imh) r10     // Catch:{ all -> 0x00b4 }
            egx r0 = p000.egx.f8238g     // Catch:{ all -> 0x00b4 }
            iij r3 = r2.f8325f     // Catch:{ all -> 0x00b4 }
            ikf r10 = r10.mo8995a(r0, r3)     // Catch:{ all -> 0x00b4 }
            egx r10 = (p000.egx) r10     // Catch:{ all -> 0x00b4 }
            r2.f8327h = r10     // Catch:{ all -> 0x00b4 }
            goto L_0x00ae
        L_0x0042:
            ehu r10 = r2.f8320a     // Catch:{ all -> 0x00b4 }
            java.lang.String r10 = r10.f8314b     // Catch:{ all -> 0x00b4 }
            android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ all -> 0x00b4 }
            eht r0 = r2.f8321b     // Catch:{ all -> 0x00b4 }
            android.content.Context r0 = r0.mo2634k()     // Catch:{ all -> 0x00b4 }
            android.app.WallpaperManager r0 = android.app.WallpaperManager.getInstance(r0)     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = "content"
            java.lang.String r4 = r10.getScheme()     // Catch:{ all -> 0x00b4 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00b4 }
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x0063
            goto L_0x0075
        L_0x0063:
            eht r3 = r2.f8321b     // Catch:{ IllegalArgumentException -> 0x006d }
            android.content.Intent r10 = r0.getCropAndSetWallpaperIntent(r10)     // Catch:{ IllegalArgumentException -> 0x006d }
            r3.startActivityForResult(r10, r4)     // Catch:{ IllegalArgumentException -> 0x006d }
            goto L_0x00ae
        L_0x006d:
            r10 = move-exception
            java.lang.String r0 = "CropAndSetWallpaperFragmentPeer: Unable to start activity for CROP_AND_SET_WALLPAPER intent."
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ all -> 0x00b4 }
            p000.cwn.m5511a((java.lang.Throwable) r10, (java.lang.String) r0, (java.lang.Object[]) r3)     // Catch:{ all -> 0x00b4 }
        L_0x0075:
            eij r10 = r2.f8322c     // Catch:{ all -> 0x00b4 }
            gus r0 = r10.f8343b     // Catch:{ all -> 0x00b4 }
            ehb r2 = r10.f8342a     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = ".jpg"
            java.lang.String r6 = "image/jpg"
            char r7 = r3.charAt(r5)     // Catch:{ all -> 0x00b4 }
            r8 = 46
            if (r7 != r8) goto L_0x0088
            goto L_0x008a
        L_0x0088:
            r4 = 0
        L_0x008a:
            p000.ife.m12890c((boolean) r4)     // Catch:{ all -> 0x00b4 }
            android.content.Context r4 = r2.f8260a     // Catch:{ all -> 0x00b4 }
            ieh r4 = r2.mo4809a(r4)     // Catch:{ all -> 0x00b4 }
            egz r5 = new egz     // Catch:{ all -> 0x00b4 }
            r5.<init>(r3, r6)     // Catch:{ all -> 0x00b4 }
            iel r2 = r2.f8261b     // Catch:{ all -> 0x00b4 }
            ieh r2 = p000.gte.m10770a((p000.ieh) r4, (p000.hpr) r5, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x00b4 }
            eii r3 = new eii     // Catch:{ all -> 0x00b4 }
            r3.<init>(r10)     // Catch:{ all -> 0x00b4 }
            iek r10 = r10.f8345d     // Catch:{ all -> 0x00b4 }
            ieh r10 = p000.gte.m10770a((p000.ieh) r2, (p000.hpr) r3, (java.util.concurrent.Executor) r10)     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = "TEMPORARY_FILE_DATA_SOURCE"
            r0.mo7096a((p000.ieh) r10, (java.lang.Object) r2)     // Catch:{ all -> 0x00b4 }
        L_0x00ae:
            if (r1 == 0) goto L_0x00b3
            r1.close()
        L_0x00b3:
            return
        L_0x00b4:
            r10 = move-exception
            if (r1 == 0) goto L_0x00bf
            r1.close()     // Catch:{ all -> 0x00bb }
            goto L_0x00bf
        L_0x00bb:
            r0 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r10, (java.lang.Throwable) r0)
        L_0x00bf:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eht.mo165a(android.os.Bundle):void");
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f8307aa = true;
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final LayoutInflater mo2633b(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            LayoutInflater from = LayoutInflater.from(new hcf(LayoutInflater.from(new fts(mo5627H(), (C0147fh) this, true))));
            if (c != null) {
                c.close();
            }
            return from;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        egx egx = mo2635n().f8327h;
        if (egx != null) {
            bundle.putParcelable("CREATED_TEMP_FILE_METADATA", imi.m14101a((ikf) egx));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final ehy mo2635n() {
        ehy ehy = this.f8308b;
        if (ehy == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f8307aa) {
            return ehy;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}

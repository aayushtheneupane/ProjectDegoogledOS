package p000;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: elu */
/* compiled from: PG */
public abstract class elu extends LifecycleCallback implements DialogInterface.OnCancelListener {

    /* renamed from: a */
    public volatile boolean f8526a;

    /* renamed from: b */
    public final AtomicReference f8527b = new AtomicReference((Object) null);

    /* renamed from: c */
    public final Handler f8528c = new eui(Looper.getMainLooper());

    /* renamed from: d */
    public final ejw f8529d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected elu(enw enw) {
        super(enw);
        ejw ejw = ejw.f8454a;
        this.f8529d = ejw;
    }

    /* renamed from: a */
    private static final int m7745a(elr elr) {
        if (elr == null) {
            return -1;
        }
        return elr.f8520a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo4984a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo4985a(ejq ejq, int i);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo4986b() {
        this.f8527b.set((Object) null);
        mo4984a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3514a(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicReference r0 = r4.f8527b
            java.lang.Object r0 = r0.get()
            elr r0 = (p000.elr) r0
            r1 = 1
            r2 = 0
            if (r5 == r1) goto L_0x002d
            r6 = 2
            if (r5 == r6) goto L_0x0011
        L_0x000f:
            r1 = 0
            goto L_0x0062
        L_0x0011:
            ejw r5 = r4.f8529d
            android.app.Activity r6 = r4.mo3520g()
            int r5 = r5.mo4918b(r6)
            if (r5 != 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r1 = 0
        L_0x001f:
            if (r0 == 0) goto L_0x002c
            ejq r6 = r0.f8521b
            int r6 = r6.f8443b
            r7 = 18
            if (r6 != r7) goto L_0x0061
            if (r5 == r7) goto L_0x002c
            goto L_0x0061
        L_0x002c:
            return
        L_0x002d:
            r5 = -1
            if (r6 == r5) goto L_0x0061
            if (r6 != 0) goto L_0x0060
            if (r0 == 0) goto L_0x005f
            r5 = 13
            if (r7 == 0) goto L_0x003f
            java.lang.String r6 = "<<ResolutionFailureErrorDetail>>"
            int r5 = r7.getIntExtra(r6, r5)
            goto L_0x0041
        L_0x003f:
        L_0x0041:
            elr r6 = new elr
            ejq r7 = new ejq
            r1 = 0
            ejq r3 = r0.f8521b
            java.lang.String r3 = r3.toString()
            r7.<init>(r5, r1, r3)
            int r5 = m7745a((p000.elr) r0)
            r6.<init>(r7, r5)
            java.util.concurrent.atomic.AtomicReference r5 = r4.f8527b
            r5.set(r6)
            r0 = r6
            r1 = 0
            goto L_0x0062
        L_0x005f:
            return
        L_0x0060:
            goto L_0x000f
        L_0x0061:
        L_0x0062:
            if (r1 == 0) goto L_0x0068
            r4.mo4986b()
            return
        L_0x0068:
            if (r0 == 0) goto L_0x0071
            ejq r5 = r0.f8521b
            int r6 = r0.f8520a
            r4.mo4985a(r5, r6)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.elu.mo3514a(int, int, android.content.Intent):void");
    }

    public final void onCancel(DialogInterface dialogInterface) {
        mo4985a(new ejq(13, (PendingIntent) null), m7745a((elr) this.f8527b.get()));
        mo4986b();
    }

    /* renamed from: a */
    public final void mo3515a(Bundle bundle) {
        elr elr;
        if (bundle != null) {
            AtomicReference atomicReference = this.f8527b;
            if (bundle.getBoolean("resolving_error", false)) {
                elr = new elr(new ejq(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                elr = null;
            }
            atomicReference.set(elr);
        }
    }

    /* renamed from: b */
    public final void mo3516b(Bundle bundle) {
        elr elr = (elr) this.f8527b.get();
        if (elr != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", elr.f8520a);
            bundle.putInt("failed_status", elr.f8521b.f8443b);
            bundle.putParcelable("failed_resolution", elr.f8521b.f8444c);
        }
    }
}

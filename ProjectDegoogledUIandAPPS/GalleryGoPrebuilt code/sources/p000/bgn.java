package p000;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;

/* renamed from: bgn */
/* compiled from: PG */
final class bgn extends AsyncTask {

    /* renamed from: a */
    private final WeakReference f2279a;

    /* renamed from: b */
    private final WeakReference f2280b;

    /* renamed from: c */
    private final WeakReference f2281c;

    /* renamed from: d */
    private final Uri f2282d;

    /* renamed from: e */
    private bgs f2283e;

    /* renamed from: f */
    private Exception f2284f;

    public bgn(bgo bgo, Context context, bgq bgq, Uri uri) {
        this.f2279a = new WeakReference(bgo);
        this.f2280b = new WeakReference(context);
        this.f2281c = new WeakReference(bgq);
        this.f2282d = uri;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        try {
            String uri = this.f2282d.toString();
            Context context = (Context) this.f2280b.get();
            bgq bgq = (bgq) this.f2281c.get();
            bgo bgo = (bgo) this.f2279a.get();
            if (context != null) {
                if (bgq != null) {
                    if (bgo != null) {
                        bgs bgs = (bgs) bgq.mo2014a();
                        this.f2283e = bgs;
                        Point a = bgs.mo2017a(context, this.f2282d);
                        return new int[]{a.x, a.y, bgo.m2465a(context, uri)};
                    }
                }
            }
            return null;
        } catch (Exception e) {
            Log.e(bgo.f2287a, "Failed to initialise bitmap decoder", e);
            this.f2284f = e;
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        bgj bgj;
        int[] iArr = (int[]) obj;
        bgo bgo = (bgo) this.f2279a.get();
        if (bgo != null) {
            bgs bgs = this.f2283e;
            if (bgs == null || iArr == null || iArr.length != 3) {
                Exception exc = this.f2284f;
                if (exc != null && (bgj = bgo.f2293D) != null) {
                    bgj.mo1984a(exc);
                    return;
                }
                return;
            }
            bgo.mo2000a(bgs, iArr[0], iArr[1], iArr[2]);
        }
    }
}

package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;

/* renamed from: bgg */
/* compiled from: PG */
final class bgg extends AsyncTask {

    /* renamed from: a */
    private final WeakReference f2259a;

    /* renamed from: b */
    private final WeakReference f2260b;

    /* renamed from: c */
    private final WeakReference f2261c;

    /* renamed from: d */
    private final Uri f2262d;

    /* renamed from: e */
    private final boolean f2263e;

    /* renamed from: f */
    private Bitmap f2264f;

    /* renamed from: g */
    private Exception f2265g;

    public bgg(bgo bgo, Context context, bgq bgq, Uri uri, boolean z) {
        this.f2259a = new WeakReference(bgo);
        this.f2260b = new WeakReference(context);
        this.f2261c = new WeakReference(bgq);
        this.f2262d = uri;
        this.f2263e = z;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        return m2459a();
    }

    /* renamed from: a */
    private final Integer m2459a() {
        try {
            String uri = this.f2262d.toString();
            Context context = (Context) this.f2260b.get();
            bgq bgq = (bgq) this.f2261c.get();
            bgo bgo = (bgo) this.f2259a.get();
            if (context == null || bgq == null || bgo == null) {
                return null;
            }
            this.f2264f = ((bgr) bgq.mo2014a()).mo2015a(context, this.f2262d);
            return Integer.valueOf(bgo.m2465a(context, uri));
        } catch (Exception e) {
            Log.e(bgo.f2287a, "Failed to load bitmap", e);
            this.f2265g = e;
            return null;
        } catch (OutOfMemoryError e2) {
            Log.e(bgo.f2287a, "Failed to load bitmap - OutOfMemoryError", e2);
            this.f2265g = new RuntimeException(e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        bgj bgj;
        Integer num = (Integer) obj;
        bgo bgo = (bgo) this.f2259a.get();
        if (bgo != null) {
            Bitmap bitmap = this.f2264f;
            if (bitmap == null || num == null) {
                Exception exc = this.f2265g;
                if (exc != null && (bgj = bgo.f2293D) != null) {
                    if (this.f2263e) {
                        ((drd) bgj).f7204a.mo4369j();
                    } else {
                        bgj.mo1984a(exc);
                    }
                }
            } else if (this.f2263e) {
                bgo.mo1995a(bitmap);
            } else {
                bgo.mo1996a(bitmap, num.intValue());
            }
        }
    }
}

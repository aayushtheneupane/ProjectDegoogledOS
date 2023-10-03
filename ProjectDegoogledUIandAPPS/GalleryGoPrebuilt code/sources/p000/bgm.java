package p000;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;

/* renamed from: bgm */
/* compiled from: PG */
final class bgm extends AsyncTask {

    /* renamed from: a */
    private final WeakReference f2275a;

    /* renamed from: b */
    private final WeakReference f2276b;

    /* renamed from: c */
    private final WeakReference f2277c;

    /* renamed from: d */
    private Exception f2278d;

    public bgm(bgo bgo, bgs bgs, bgl bgl) {
        this.f2275a = new WeakReference(bgo);
        this.f2276b = new WeakReference(bgs);
        this.f2277c = new WeakReference(bgl);
        bgl.f2271d = true;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        return m2462a();
    }

    /* renamed from: a */
    private final Bitmap m2462a() {
        bgo bgo;
        try {
            bgo = (bgo) this.f2275a.get();
            bgs bgs = (bgs) this.f2276b.get();
            bgl bgl = (bgl) this.f2277c.get();
            if (bgs != null && bgl != null && bgo != null && bgs.mo2018a() && bgl.f2272e) {
                Object[] objArr = {bgl.f2268a, Integer.valueOf(bgl.f2269b)};
                bgo.f2345u.readLock().lock();
                if (bgs.mo2018a()) {
                    Rect rect = bgl.f2268a;
                    Rect rect2 = bgl.f2274g;
                    if (bgo.mo2006d() == 0) {
                        rect2.set(rect);
                    } else if (bgo.mo2006d() == 90) {
                        rect2.set(rect.top, bgo.f2341q - rect.right, rect.bottom, bgo.f2341q - rect.left);
                    } else if (bgo.mo2006d() == 180) {
                        rect2.set(bgo.f2340p - rect.right, bgo.f2341q - rect.bottom, bgo.f2340p - rect.left, bgo.f2341q - rect.top);
                    } else {
                        rect2.set(bgo.f2340p - rect.bottom, rect.left, bgo.f2340p - rect.top, rect.right);
                    }
                    Bitmap a = bgs.mo2016a(bgl.f2274g, bgl.f2269b);
                    bgo.f2345u.readLock().unlock();
                    return a;
                }
                bgl.f2271d = false;
                bgo.f2345u.readLock().unlock();
                return null;
            } else if (bgl == null) {
                return null;
            } else {
                bgl.f2271d = false;
                return null;
            }
        } catch (Exception e) {
            Log.e(bgo.f2287a, "Failed to decode tile", e);
            this.f2278d = e;
            return null;
        } catch (OutOfMemoryError e2) {
            Log.e(bgo.f2287a, "Failed to decode tile - OutOfMemoryError", e2);
            this.f2278d = new RuntimeException(e2);
            return null;
        } catch (Throwable th) {
            bgo.f2345u.readLock().unlock();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        bgj bgj;
        Bitmap bitmap = (Bitmap) obj;
        bgo bgo = (bgo) this.f2275a.get();
        bgl bgl = (bgl) this.f2277c.get();
        if (bgo != null && bgl != null) {
            if (bitmap != null) {
                bgl.f2270c = bitmap;
                bgl.f2271d = false;
                bgo.mo1992a();
            } else if (this.f2278d != null && (bgj = bgo.f2293D) != null) {
                ((drd) bgj).f7204a.mo4369j();
            }
        }
    }
}

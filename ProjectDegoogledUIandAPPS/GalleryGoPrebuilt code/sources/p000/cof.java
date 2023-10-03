package p000;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: cof */
/* compiled from: PG */
public final class cof {
    /* renamed from: a */
    public static Uri m4686a(Uri uri) {
        return m4687a(uri, bit.BADGE);
    }

    /* renamed from: b */
    public static Uri m4690b(Uri uri) {
        return m4687a(uri, bit.EDITOR);
    }

    /* renamed from: a */
    public static Uri m4687a(Uri uri, bit bit) {
        return uri.buildUpon().appendPath(bit.f2477e).build();
    }

    /* renamed from: a */
    public static ieh m4688a(hdt hdt, apj apj, icf icf, Executor executor) {
        hlj a = hnb.m11765a("Load Image");
        try {
            iev f = iev.m12774f();
            bdz b = apj.mo1418a((bee) new cnv(f)).mo1427b();
            f.mo53a((Runnable) new cnu(f, b), (Executor) idh.f13918a);
            ieh a2 = fxk.m9820a(gte.m10771a(a.mo7548a(f), icf, executor), (Closeable) new cnt(hdt, b), executor);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public static byte[] m4689a(Bitmap bitmap, int i) {
        ihw[] ihwArr;
        byte[] bArr;
        int i2;
        ihu l = ihw.m13169l();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, l);
        coe coe = new coe(l.mo8616a());
        try {
            synchronized (l) {
                ArrayList arrayList = l.f14197a;
                ihwArr = (ihw[]) arrayList.toArray(new ihw[arrayList.size()]);
                bArr = l.f14198b;
                i2 = l.f14199c;
            }
            for (ihw a : ihwArr) {
                a.mo8610a((OutputStream) coe);
            }
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i2));
            coe.write(bArr2);
            return coe.mo3304a();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write image", e);
        }
    }
}

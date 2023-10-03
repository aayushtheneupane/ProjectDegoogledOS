package p000;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.apps.photosgo.sharing.compression.ImageCompressionProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: edt */
/* compiled from: PG */
public final /* synthetic */ class edt implements Runnable {

    /* renamed from: a */
    private final ebi f8058a;

    /* renamed from: b */
    private final Uri f8059b;

    /* renamed from: c */
    private final Bitmap f8060c;

    /* renamed from: d */
    private final OutputStream f8061d;

    public edt(ebi ebi, Uri uri, Bitmap bitmap, OutputStream outputStream) {
        this.f8058a = ebi;
        this.f8059b = uri;
        this.f8060c = bitmap;
        this.f8061d = outputStream;
    }

    public final void run() {
        int i;
        InputStream a;
        ebi ebi = this.f8058a;
        Uri uri = this.f8059b;
        Bitmap bitmap = this.f8060c;
        OutputStream outputStream = this.f8061d;
        Bitmap.CompressFormat compressFormat = ImageCompressionProvider.f4923a;
        try {
            a = ebi.mo4664a(uri);
            i = new abz(a).mo152a("Orientation", 0);
            if (a != null) {
                a.close();
            }
        } catch (IOException e) {
            i = 0;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        Matrix matrix = new Matrix();
        switch (i) {
            case RecyclerView.SCROLL_STATE_SETTLING:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
        }
        if (!matrix.isIdentity()) {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        }
        bitmap.compress(ImageCompressionProvider.f4923a, 70, outputStream);
        try {
            outputStream.close();
            return;
        } catch (IOException e2) {
            cwn.m5511a((Throwable) e2, "ImageCompressionProvider: Error closing compressed OutputStream.", new Object[0]);
            return;
        }
        throw th;
    }
}

package p000;

import android.graphics.Bitmap;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: dbn */
/* compiled from: PG */
public final class dbn implements dbo {

    /* renamed from: a */
    private final Bitmap f6203a;

    /* renamed from: b */
    private final exm f6204b;

    public dbn(Bitmap bitmap, exm exm) {
        this.f6203a = bitmap;
        this.f6204b = exm;
    }

    /* renamed from: a */
    public final String mo3203a() {
        return ".jpg";
    }

    /* renamed from: b */
    public final String mo3205b() {
        return "image/jpeg";
    }

    /* renamed from: a */
    public static dbn m5855a(Bitmap bitmap, exm exm) {
        return new dbn(bitmap, exm);
    }

    /* renamed from: e */
    public final String mo3208e() {
        String valueOf = String.valueOf(new SimpleDateFormat("_yyyyMMdd_HHmmss").format(new Date(this.f6204b.mo5370a())));
        return dcm.m5896a(valueOf.length() == 0 ? new String("IMG") : "IMG".concat(valueOf));
    }

    /* renamed from: d */
    public final int mo3207d() {
        return this.f6203a.getHeight();
    }

    /* renamed from: c */
    public final int mo3206c() {
        return this.f6203a.getWidth();
    }

    /* renamed from: a */
    public final void mo3204a(OutputStream outputStream) {
        if (!this.f6203a.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)) {
            throw new IllegalStateException("Bitmap compress/save failure");
        }
    }
}

package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.rastermill.FrameSequence;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.exif.C1435d;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.s */
public abstract class C0879s implements C0883w {
    protected final Context mContext;
    protected final C0880t mDescriptor;
    protected int mOrientation;

    public C0879s(Context context, C0880t tVar) {
        this.mContext = context;
        this.mDescriptor = tVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gh */
    public Bitmap mo6140Gh() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Hh */
    public C0844G mo6160Hh() {
        C0882v ua = C0866f.get().mo6144ua(mo6115Ih());
        if (ua == null || !(ua instanceof C0845H)) {
            return null;
        }
        return ((C0845H) ua).mo6094Cb();
    }

    /* renamed from: Ih */
    public int mo6115Ih() {
        return 1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public abstract InputStream mo6085Jh();

    /* access modifiers changed from: protected */
    /* renamed from: Kh */
    public boolean mo6141Kh() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Lh */
    public boolean mo6086Lh() {
        return C1416U.m3568b(mo6085Jh());
    }

    /* access modifiers changed from: protected */
    /* renamed from: Mh */
    public Bitmap mo6087Mh() {
        C0880t tVar = this.mDescriptor;
        boolean z = tVar.f1129zC == -1 || tVar.f1124AC == -1;
        if (mo6141Kh()) {
            Bitmap Gh = mo6140Gh();
            if (Gh != null && z) {
                this.mDescriptor.mo6083u(Gh.getWidth(), Gh.getHeight());
            }
            return Gh;
        }
        this.mOrientation = C1416U.m3561a(mo6085Jh());
        BitmapFactory.Options a = C0845H.m1524a(false, 0, 0);
        if (z) {
            InputStream Jh = mo6085Jh();
            if (Jh != null) {
                try {
                    a.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(Jh, (Rect) null, a);
                    if (C1435d.m3656Ua(this.mOrientation).f2251lL) {
                        this.mDescriptor.mo6083u(a.outHeight, a.outWidth);
                    } else {
                        this.mDescriptor.mo6083u(a.outWidth, a.outHeight);
                    }
                } finally {
                    Jh.close();
                }
            } else {
                throw new FileNotFoundException();
            }
        } else {
            C0880t tVar2 = this.mDescriptor;
            a.outWidth = tVar2.f1129zC;
            a.outHeight = tVar2.f1124AC;
        }
        C1416U u = C1416U.get();
        C0880t tVar3 = this.mDescriptor;
        a.inSampleSize = u.mo8047a(a, tVar3.desiredWidth, tVar3.f1128yC);
        C1424b.m3592ia(a.inSampleSize > 0);
        InputStream Jh2 = mo6085Jh();
        if (Jh2 != null) {
            try {
                a.inJustDecodeBounds = false;
                C0844G Hh = mo6160Hh();
                if (Hh == null) {
                    return BitmapFactory.decodeStream(Jh2, (Rect) null, a);
                }
                Bitmap a2 = Hh.mo6089a(Jh2, a, ((a.outWidth + a.inSampleSize) - 1) / a.inSampleSize, ((a.outHeight + a.inSampleSize) - 1) / a.inSampleSize);
                Jh2.close();
                return a2;
            } finally {
                Jh2.close();
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    /* renamed from: a */
    public C0846I mo6120a(List list) {
        C1424b.m3584Gj();
        C0881u k = mo6116k(list);
        C0880t tVar = this.mDescriptor;
        if (!tVar.f1125CC || !(k instanceof C0873m)) {
            return k;
        }
        int i = tVar.desiredWidth;
        int i2 = tVar.f1128yC;
        Bitmap bitmap = k.getBitmap();
        Bitmap v = mo6160Hh().mo6093v(i, i2);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        RectF rectF2 = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        C0880t tVar2 = this.mDescriptor;
        int i3 = tVar2.f1126DC;
        C1416U.m3565a(bitmap, new Canvas(v), rectF2, rectF, (Paint) null, i3 != 0, i3, tVar2.f1127EC);
        return new C0873m(getKey(), v, k.getOrientation());
    }

    /* renamed from: fa */
    public C0882v mo6121fa() {
        return C0866f.get().mo6144ua(mo6115Ih());
    }

    public C0880t getDescriptor() {
        return this.mDescriptor;
    }

    public String getKey() {
        return this.mDescriptor.getKey();
    }

    public int getRequestType() {
        return 3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public C0881u mo6116k(List list) {
        C0878r rVar;
        if (this.mDescriptor.isStatic || !mo6086Lh()) {
            Bitmap Mh = mo6087Mh();
            if (Mh != null) {
                return new C0873m(getKey(), Mh, this.mOrientation);
            }
            throw new RuntimeException("failed decoding bitmap");
        }
        String key = getKey();
        InputStream Jh = mo6085Jh();
        try {
            FrameSequence decodeStream = FrameSequence.decodeStream(Jh);
            if (decodeStream == null) {
                rVar = null;
            } else {
                rVar = new C0878r(key, decodeStream);
            }
            if (rVar != null) {
                return rVar;
            }
            throw new RuntimeException("Error decoding gif");
        } finally {
            try {
                Jh.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: getDescriptor  reason: collision with other method in class */
    public C0884x m4704getDescriptor() {
        return this.mDescriptor;
    }
}

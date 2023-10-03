package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1424b;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.g */
public class C0867g extends C0879s {
    private final Bitmap mBitmap;
    private final Canvas mCanvas = new Canvas(this.mBitmap);
    private final Paint mPaint = new Paint(1);

    public C0867g(Context context, C0868h hVar) {
        super(context, hVar);
        C0844G Hh = mo6160Hh();
        C0880t tVar = this.mDescriptor;
        this.mBitmap = Hh.mo6093v(((C0868h) tVar).desiredWidth, ((C0868h) tVar).f1128yC);
    }

    /* renamed from: Ih */
    public int mo6115Ih() {
        return 2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public InputStream mo6085Jh() {
        throw new IllegalStateException("Composite image request doesn't support input stream!");
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public C0881u mo6116k(List list) {
        int i;
        C0880t tVar = this.mDescriptor;
        List list2 = ((C0868h) tVar).mDescriptors;
        C0862b bVar = (C0862b) tVar;
        int size = bVar.mDescriptors.size();
        float f = (float) bVar.desiredWidth;
        float f2 = (float) bVar.f1128yC;
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        RectF[] rectFArr = new RectF[size];
        if (size == 2) {
            float sqrt = (float) ((2.0d - Math.sqrt(2.0d)) * ((double) f));
            rectFArr[0] = new RectF(0.0f, 0.0f, sqrt, sqrt);
            RectF rectF = new RectF(f - sqrt, f2 - sqrt, f, f2);
            i = 1;
            rectFArr[1] = rectF;
        } else if (size != 3) {
            rectFArr[0] = new RectF(0.0f, 0.0f, f3, f4);
            rectFArr[1] = new RectF(f3, 0.0f, f, f4);
            rectFArr[2] = new RectF(0.0f, f4, f3, f2);
            rectFArr[3] = new RectF(f3, f4, f, f2);
            i = 1;
        } else {
            float f5 = f / 4.0f;
            float f6 = f2 / 4.0f;
            float sqrt2 = (f2 - f6) - ((float) (((double) f6) * Math.sqrt(3.0d)));
            rectFArr[0] = new RectF(f5, sqrt2 - f6, 3.0f * f5, sqrt2 + f6);
            rectFArr[1] = new RectF(0.0f, f4, f3, f2);
            rectFArr[2] = new RectF(f3, f4, f, f2);
            i = 1;
        }
        List asList = Arrays.asList(rectFArr);
        C1424b.equals(list2.size(), asList.size());
        C1424b.m3592ia(list2.size() > i);
        for (int i2 = 0; i2 < list2.size(); i2++) {
            C0881u uVar = (C0881u) C0840C.get().mo6082b(((C0880t) list2.get(i2)).mo6084n(this.mContext));
            if (uVar != null) {
                try {
                    RectF rectF2 = (RectF) asList.get(i2);
                    Bitmap bitmap = uVar.getBitmap();
                    RectF rectF3 = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
                    Bitmap v = mo6160Hh().mo6093v(Math.round(rectF2.width()), Math.round(rectF2.height()));
                    RectF rectF4 = new RectF(0.0f, 0.0f, (float) v.getWidth(), (float) v.getHeight());
                    C1416U.m3565a(uVar.getBitmap(), new Canvas(v), rectF3, rectF4, (Paint) null, false, 0, 0);
                    Matrix matrix = new Matrix();
                    matrix.setRectToRect(rectF4, rectF2, Matrix.ScaleToFit.FILL);
                    this.mCanvas.drawBitmap(v, matrix, this.mPaint);
                } finally {
                    uVar.release();
                }
            }
        }
        return new C0873m(getKey(), this.mBitmap, 1);
    }
}

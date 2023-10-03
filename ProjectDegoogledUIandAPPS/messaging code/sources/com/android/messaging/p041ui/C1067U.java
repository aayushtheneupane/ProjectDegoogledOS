package com.android.messaging.p041ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import com.android.messaging.util.exif.C1434c;
import com.android.messaging.util.exif.C1435d;

/* renamed from: com.android.messaging.ui.U */
public class C1067U extends BitmapDrawable {

    /* renamed from: Cc */
    private final C1434c f1677Cc;

    /* renamed from: Dc */
    private final Rect f1678Dc = new Rect();

    /* renamed from: Ec */
    private boolean f1679Ec = true;
    private int mCenterX;
    private int mCenterY;

    private C1067U(int i, Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        this.f1677Cc = C1435d.m3656Ua(i);
    }

    /* renamed from: a */
    public static BitmapDrawable m2646a(int i, Resources resources, Bitmap bitmap) {
        if (i <= 1) {
            return new BitmapDrawable(resources, bitmap);
        }
        return new C1067U(i, resources, bitmap);
    }

    public void draw(Canvas canvas) {
        int i;
        int i2;
        if (this.f1679Ec) {
            int gravity = getGravity();
            if (this.f1677Cc.f2251lL) {
                i = super.getIntrinsicHeight();
            } else {
                i = super.getIntrinsicWidth();
            }
            if (this.f1677Cc.f2251lL) {
                i2 = super.getIntrinsicWidth();
            } else {
                i2 = super.getIntrinsicHeight();
            }
            Gravity.apply(gravity, i, i2, getBounds(), this.f1678Dc);
            this.mCenterX = this.f1678Dc.centerX();
            this.mCenterY = this.f1678Dc.centerY();
            if (this.f1677Cc.f2251lL) {
                Matrix matrix = new Matrix();
                matrix.setRotate((float) this.f1677Cc.rotation, (float) this.mCenterX, (float) this.mCenterY);
                RectF rectF = new RectF(this.f1678Dc);
                matrix.mapRect(rectF);
                this.f1678Dc.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            }
            this.f1679Ec = false;
        }
        canvas.save();
        C1434c cVar = this.f1677Cc;
        canvas.scale((float) cVar.scaleX, (float) cVar.scaleY, (float) this.mCenterX, (float) this.mCenterY);
        canvas.rotate((float) this.f1677Cc.rotation, (float) this.mCenterX, (float) this.mCenterY);
        canvas.drawBitmap(getBitmap(), (Rect) null, this.f1678Dc, getPaint());
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        if (this.f1677Cc.f2251lL) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        if (this.f1677Cc.f2251lL) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f1679Ec = true;
    }
}

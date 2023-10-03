package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1488za;
import java.io.InputStream;

/* renamed from: com.android.messaging.datamodel.b.c */
public class C0863c extends C0848K {

    /* renamed from: uC */
    private static Bitmap f1114uC;

    /* renamed from: vC */
    private static Bitmap f1115vC;
    private TypedArray mColors = this.mContext.getResources().obtainTypedArray(R.array.letter_tile_colors);

    public C0863c(Context context, C0864d dVar) {
        super(context, dVar);
    }

    /* renamed from: hb */
    private int m1574hb(String str) {
        if (TextUtils.isEmpty(str) || !this.mContext.getResources().getBoolean(R.bool.contact_colors)) {
            return this.mContext.getResources().getColor(R.color.primary_color);
        }
        return this.mColors.getColor(Math.abs(str.hashCode()) % this.mColors.length(), this.mContext.getResources().getColor(R.color.primary_color));
    }

    /* renamed from: Ih */
    public int mo6115Ih() {
        return 2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public InputStream mo6085Jh() {
        if (C1488za.m3877z(((C0864d) this.mDescriptor).uri)) {
            return this.mContext.getContentResolver().openInputStream(((C0849L) this.mDescriptor).uri);
        }
        Uri s = C1426c.m3606s(((C0864d) this.mDescriptor).uri);
        C1424b.m3592ia(C1488za.m3877z(s));
        return this.mContext.getContentResolver().openInputStream(s);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0051  */
    /* renamed from: k */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.datamodel.p038b.C0881u mo6116k(java.util.List r12) {
        /*
            r11 = this;
            com.android.messaging.util.C1424b.m3584Gj()
            com.android.messaging.datamodel.b.t r0 = r11.mDescriptor
            com.android.messaging.datamodel.b.d r0 = (com.android.messaging.datamodel.p038b.C0864d) r0
            android.net.Uri r0 = r0.uri
            java.lang.String r0 = com.android.messaging.util.C1426c.m3603p(r0)
            com.android.messaging.datamodel.b.t r1 = r11.mDescriptor
            com.android.messaging.datamodel.b.d r1 = (com.android.messaging.datamodel.p038b.C0864d) r1
            android.net.Uri r1 = r1.uri
            boolean r1 = com.android.messaging.util.C1488za.m3877z(r1)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0026
            java.lang.String r1 = "r"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r0 = r2
            goto L_0x0027
        L_0x0026:
            r0 = r3
        L_0x0027:
            r1 = 0
            if (r0 == 0) goto L_0x0041
            com.android.messaging.datamodel.b.u r12 = super.mo6116k(r12)     // Catch:{ Exception -> 0x0037 }
            android.graphics.Bitmap r4 = r12.getBitmap()     // Catch:{ Exception -> 0x0037 }
            int r12 = r12.mOrientation     // Catch:{ Exception -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r12 = move-exception
            goto L_0x0039
        L_0x0037:
            r12 = move-exception
            r4 = r1
        L_0x0039:
            java.lang.String r5 = "MessagingAppImage"
            java.lang.String r6 = "AvatarRequest: failed to load local avatar resource, switching to fallback rendering"
            com.android.messaging.util.C1430e.m3631w(r5, r6, r12)
            goto L_0x0042
        L_0x0041:
            r4 = r1
        L_0x0042:
            r12 = r3
        L_0x0043:
            com.android.messaging.datamodel.b.t r5 = r11.mDescriptor
            r6 = r5
            com.android.messaging.datamodel.b.d r6 = (com.android.messaging.datamodel.p038b.C0864d) r6
            int r6 = r6.desiredWidth
            r7 = r5
            com.android.messaging.datamodel.b.d r7 = (com.android.messaging.datamodel.p038b.C0864d) r7
            int r7 = r7.f1128yC
            if (r4 != 0) goto L_0x01a1
            r4 = r5
            com.android.messaging.datamodel.b.d r4 = (com.android.messaging.datamodel.p038b.C0864d) r4
            android.net.Uri r4 = r4.uri
            if (r0 == 0) goto L_0x0071
            com.android.messaging.datamodel.b.d r5 = (com.android.messaging.datamodel.p038b.C0864d) r5
            android.net.Uri r0 = r5.uri
            com.android.messaging.util.C1424b.m3594t(r0)
            java.lang.String r4 = "f"
            java.lang.String r0 = r0.getQueryParameter(r4)
            if (r0 != 0) goto L_0x0068
            goto L_0x006c
        L_0x0068:
            android.net.Uri r1 = android.net.Uri.parse(r0)
        L_0x006c:
            r4 = r1
            if (r4 != 0) goto L_0x0071
            android.net.Uri r4 = com.android.messaging.util.C1426c.f2237FJ
        L_0x0071:
            java.lang.String r0 = com.android.messaging.util.C1426c.m3603p(r4)
            java.lang.String r1 = "l"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x00f7
            com.android.messaging.util.C1424b.m3594t(r4)
            java.lang.String r0 = "n"
            java.lang.String r0 = r4.getQueryParameter(r0)
            int r1 = r6 / 2
            float r1 = (float) r1
            int r4 = r7 / 2
            float r4 = (float) r4
            int r5 = java.lang.Math.min(r6, r7)
            com.android.messaging.datamodel.b.G r8 = r11.mo6160Hh()
            com.android.messaging.datamodel.b.t r9 = r11.mDescriptor
            com.android.messaging.datamodel.b.d r9 = (com.android.messaging.datamodel.p038b.C0864d) r9
            android.net.Uri r9 = r9.uri
            java.lang.String r9 = com.android.messaging.util.C1426c.m3605r(r9)
            int r9 = r11.m1574hb(r9)
            android.graphics.Bitmap r6 = r8.mo6088a(r6, r7, r9)
            android.content.Context r7 = r11.mContext
            android.content.res.Resources r7 = r7.getResources()
            android.graphics.Paint r8 = new android.graphics.Paint
            r8.<init>(r3)
            java.lang.String r9 = "sans-serif-medium"
            android.graphics.Typeface r9 = android.graphics.Typeface.create(r9, r2)
            r8.setTypeface(r9)
            r9 = 2131099769(0x7f060079, float:1.78119E38)
            int r9 = r7.getColor(r9)
            r8.setColor(r9)
            r9 = 2131165388(0x7f0700cc, float:1.7944992E38)
            float r7 = r7.getFraction(r9, r3, r3)
            float r5 = (float) r5
            float r7 = r7 * r5
            r8.setTextSize(r7)
            java.lang.String r0 = r0.substring(r2, r3)
            java.lang.String r0 = r0.toUpperCase()
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            r8.getTextBounds(r0, r2, r3, r5)
            android.graphics.Canvas r2 = new android.graphics.Canvas
            r2.<init>(r6)
            int r3 = r5.centerX()
            float r3 = (float) r3
            float r1 = r1 - r3
            int r3 = r5.centerY()
            float r3 = (float) r3
            float r4 = r4 - r3
            r2.drawText(r0, r1, r4, r8)
            r4 = r6
            goto L_0x01a1
        L_0x00f7:
            com.android.messaging.datamodel.b.G r0 = r11.mo6160Hh()
            com.android.messaging.datamodel.b.t r1 = r11.mDescriptor
            com.android.messaging.datamodel.b.d r1 = (com.android.messaging.datamodel.p038b.C0864d) r1
            android.net.Uri r1 = r1.uri
            java.lang.String r1 = com.android.messaging.util.C1426c.m3605r(r1)
            int r1 = r11.m1574hb(r1)
            android.graphics.Bitmap r4 = r0.mo6088a(r6, r7, r1)
            android.graphics.Canvas r0 = new android.graphics.Canvas
            r0.<init>(r4)
            android.graphics.Bitmap r1 = f1114uC
            if (r1 != 0) goto L_0x012b
            android.content.Context r1 = r11.mContext
            android.content.res.Resources r1 = r1.getResources()
            r5 = 2131230931(0x7f0800d3, float:1.8077929E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r5)
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            android.graphics.Bitmap r1 = r1.getBitmap()
            f1114uC = r1
        L_0x012b:
            android.graphics.Bitmap r1 = f1115vC
            if (r1 != 0) goto L_0x0144
            android.content.Context r1 = r11.mContext
            android.content.res.Resources r1 = r1.getResources()
            r5 = 2131230932(0x7f0800d4, float:1.807793E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r5)
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            android.graphics.Bitmap r1 = r1.getBitmap()
            f1115vC = r1
        L_0x0144:
            com.android.messaging.datamodel.b.t r1 = r11.mDescriptor
            com.android.messaging.datamodel.b.d r1 = (com.android.messaging.datamodel.p038b.C0864d) r1
            boolean r1 = r1.f1116GC
            if (r1 == 0) goto L_0x0160
            android.content.Context r1 = r11.mContext
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230933(0x7f0800d5, float:1.8077933E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            android.graphics.Bitmap r1 = r1.getBitmap()
            goto L_0x0178
        L_0x0160:
            android.graphics.Bitmap r1 = f1114uC
            int r1 = r1.getWidth()
            if (r6 > r1) goto L_0x0170
            android.graphics.Bitmap r1 = f1114uC
            int r1 = r1.getHeight()
            if (r7 <= r1) goto L_0x0171
        L_0x0170:
            r2 = r3
        L_0x0171:
            if (r2 == 0) goto L_0x0176
            android.graphics.Bitmap r1 = f1115vC
            goto L_0x0178
        L_0x0176:
            android.graphics.Bitmap r1 = f1114uC
        L_0x0178:
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>(r3)
            android.graphics.Matrix r3 = new android.graphics.Matrix
            r3.<init>()
            android.graphics.RectF r5 = new android.graphics.RectF
            int r8 = r1.getWidth()
            float r8 = (float) r8
            int r9 = r1.getHeight()
            float r9 = (float) r9
            r10 = 0
            r5.<init>(r10, r10, r8, r9)
            android.graphics.RectF r8 = new android.graphics.RectF
            float r6 = (float) r6
            float r7 = (float) r7
            r8.<init>(r10, r10, r6, r7)
            android.graphics.Matrix$ScaleToFit r6 = android.graphics.Matrix.ScaleToFit.FILL
            r3.setRectToRect(r5, r8, r6)
            r0.drawBitmap(r1, r3, r2)
        L_0x01a1:
            com.android.messaging.datamodel.b.m r0 = new com.android.messaging.datamodel.b.m
            java.lang.String r11 = r11.getKey()
            r0.<init>(r11, r4, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0863c.mo6116k(java.util.List):com.android.messaging.datamodel.b.u");
    }
}

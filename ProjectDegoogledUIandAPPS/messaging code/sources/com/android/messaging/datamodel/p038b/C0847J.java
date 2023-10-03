package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.J */
public class C0847J extends C0863c {

    /* renamed from: wC */
    private static Bitmap f1101wC;

    public C0847J(Context context, C0864d dVar) {
        super(context, dVar);
    }

    /* renamed from: Ih */
    public int mo6115Ih() {
        return 2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public C0881u mo6116k(List list) {
        C1424b.m3584Gj();
        if (!"s".equals(C1426c.m3603p(((C0864d) this.mDescriptor).uri))) {
            return super.mo6116k(list);
        }
        C0880t tVar = this.mDescriptor;
        int i = ((C0864d) tVar).desiredWidth;
        int i2 = ((C0864d) tVar).f1128yC;
        String r = C1426c.m3605r(((C0864d) tVar).uri);
        Uri uri = ((C0864d) this.mDescriptor).uri;
        C1424b.m3594t(uri);
        boolean booleanValue = Boolean.valueOf(uri.getQueryParameter("s")).booleanValue();
        Uri uri2 = ((C0864d) this.mDescriptor).uri;
        C1424b.m3594t(uri2);
        int intValue = Integer.valueOf(uri2.getQueryParameter("c")).intValue();
        Uri uri3 = ((C0864d) this.mDescriptor).uri;
        C1424b.m3594t(uri3);
        Boolean.valueOf(uri3.getQueryParameter("g")).booleanValue();
        Resources resources = this.mContext.getResources();
        float f = (float) (i / 2);
        float f2 = (float) (i2 / 2);
        int min = Math.min(i, i2);
        int i3 = booleanValue ? intValue : -1;
        int i4 = booleanValue ? intValue : -1;
        if (booleanValue) {
            intValue = -1;
        }
        Bitmap a = mo6160Hh().mo6088a(i, i2, i3);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(a);
        if (f1101wC == null) {
            f1101wC = ((BitmapDrawable) this.mContext.getResources().getDrawable(R.drawable.ic_sim_card_send)).getBitmap();
        }
        paint.setColorFilter(new PorterDuffColorFilter(intValue, PorterDuff.Mode.SRC_ATOP));
        paint.setAlpha(255);
        Bitmap bitmap = f1101wC;
        canvas.drawBitmap(bitmap, f - ((float) (bitmap.getWidth() / 2)), f2 - ((float) (f1101wC.getHeight() / 2)), paint);
        paint.setColorFilter((ColorFilter) null);
        paint.setAlpha(255);
        if (!TextUtils.isEmpty(r)) {
            paint.setTypeface(Typeface.create("sans-serif", 0));
            paint.setColor(i4);
            paint.setTextSize(resources.getFraction(R.dimen.sim_identifier_to_tile_ratio, 1, 1) * ((float) min));
            String upperCase = r.substring(0, 1).toUpperCase();
            Rect rect = new Rect();
            paint.getTextBounds(upperCase, 0, 1, rect);
            canvas.drawText(upperCase, f - ((float) rect.centerX()), f2 - ((float) rect.centerY()), paint);
        }
        return new C0873m(getKey(), a, 1);
    }
}

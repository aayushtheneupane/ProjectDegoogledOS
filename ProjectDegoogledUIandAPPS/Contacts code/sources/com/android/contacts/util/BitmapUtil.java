package com.android.contacts.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.android.contacts.ContactPhotoManager;

public class BitmapUtil {
    public static int findOptimalSampleSize(int i, int i2) {
        if (i2 < 1 || i < 1) {
            return 1;
        }
        int i3 = 1;
        while (true) {
            i >>= 1;
            if (((float) i) < ((float) i2) * 0.8f) {
                return i3;
            }
            i3 <<= 1;
        }
    }

    private BitmapUtil() {
    }

    public static int getSmallerExtentFromBytes(byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        return Math.min(options.outWidth, options.outHeight);
    }

    public static Bitmap decodeBitmapFromBytes(byte[] bArr, int i) {
        BitmapFactory.Options options;
        if (i <= 1) {
            options = null;
        } else {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            options = options2;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public static Drawable getRotatedDrawable(Resources resources, int i, float f) {
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.rotate(f, (float) (decodeResource.getWidth() / 2), (float) (decodeResource.getHeight() / 2));
        canvas.drawBitmap(decodeResource, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, (Paint) null);
        return new BitmapDrawable(resources, createBitmap);
    }

    public static Bitmap getRoundedBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setAntiAlias(true);
        float f = (float) i;
        float f2 = (float) i2;
        RectF rectF = new RectF(ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, f, f2);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float min = Math.min(((float) width) / f, ((float) height) / f2);
        int i3 = (int) ((f * min) / 2.0f);
        int i4 = (int) ((min * f2) / 2.0f);
        int i5 = width / 2;
        int i6 = height / 2;
        canvas.drawBitmap(bitmap, new Rect(i5 - i3, i6 - i4, i5 + i3, i6 + i4), rectF, paint);
        return createBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int i) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}

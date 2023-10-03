package com.android.settingslib.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.DrawableWrapper;
import android.util.PathParser;

public class AdaptiveOutlineDrawable extends DrawableWrapper {
    private final Bitmap mBitmap;
    private final int mInsetPx;
    final Paint mOutlinePaint = new Paint();
    private Path mPath;

    public AdaptiveOutlineDrawable(Resources resources, Bitmap bitmap) {
        super(new AdaptiveIconShapeDrawable(resources));
        getDrawable().setTint(-1);
        this.mPath = new Path(PathParser.createPathFromPathData(resources.getString(17039785)));
        this.mOutlinePaint.setColor(resources.getColor(R$color.bt_outline_color, (Resources.Theme) null));
        this.mOutlinePaint.setStyle(Paint.Style.STROKE);
        this.mOutlinePaint.setStrokeWidth(resources.getDimension(R$dimen.adaptive_outline_stroke));
        this.mOutlinePaint.setAntiAlias(true);
        this.mInsetPx = resources.getDimensionPixelSize(R$dimen.dashboard_tile_foreground_image_inset);
        this.mBitmap = bitmap;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.scale(((float) (bounds.right - bounds.left)) / 100.0f, ((float) (bounds.bottom - bounds.top)) / 100.0f);
        canvas.drawPath(this.mPath, this.mOutlinePaint);
        canvas.restoreToCount(save);
        Bitmap bitmap = this.mBitmap;
        int i = bounds.left;
        int i2 = this.mInsetPx;
        canvas.drawBitmap(bitmap, (float) (i + i2), (float) (bounds.top + i2), (Paint) null);
    }

    public int getIntrinsicHeight() {
        return this.mBitmap.getHeight() + (this.mInsetPx * 2);
    }

    public int getIntrinsicWidth() {
        return this.mBitmap.getWidth() + (this.mInsetPx * 2);
    }
}

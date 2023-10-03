package p000;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;
import com.google.android.apps.photosgo.R;

/* renamed from: coa */
/* compiled from: PG */
public final class coa {

    /* renamed from: a */
    public final cnz f4763a;

    /* renamed from: b */
    public final int f4764b;

    /* renamed from: c */
    public final int f4765c;

    /* renamed from: d */
    public final int f4766d;

    /* renamed from: e */
    public final int f4767e;

    public coa(Context context, WindowManager windowManager, dgp dgp) {
        cnz cnz;
        this.f4764b = context.getResources().getDimensionPixelSize(R.dimen.fast_grid_thumbnail_size);
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        this.f4765c = Math.min(point.x, point.y);
        this.f4766d = Math.max(point.x, point.y);
        int i = this.f4765c;
        int integer = context.getResources().getInteger(R.integer.photos_photogrid_default_column_count);
        this.f4767e = (int) Math.ceil((double) (((float) (i - (context.getResources().getDimensionPixelSize(R.dimen.photogrid_item_margin) * (integer - 1)))) / ((float) integer)));
        long a = (long) dgp.mo4122a();
        cnz[] values = cnz.values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                cnz = cnz.LOTS;
                break;
            }
            cnz = values[i2];
            if (a < cnz.f4762c + 64) {
                break;
            }
            i2++;
        }
        this.f4763a = cnz;
    }
}

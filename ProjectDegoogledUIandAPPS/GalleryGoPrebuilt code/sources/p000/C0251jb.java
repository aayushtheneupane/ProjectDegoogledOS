package p000;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;

/* renamed from: jb */
/* compiled from: PG */
public final class C0251jb extends C0252jc {
    public C0251jb(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public final void getOutline(Outline outline) {
        mo9123a();
        outline.setRoundRect(this.f15070d, this.f15069c);
    }

    /* renamed from: a */
    public final void mo9121a(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }
}

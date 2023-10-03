package p000;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.ActionBarContainer;

/* renamed from: sg */
/* compiled from: PG */
public final class C0499sg extends Drawable {

    /* renamed from: a */
    private final ActionBarContainer f15867a;

    public C0499sg(ActionBarContainer actionBarContainer) {
        this.f15867a = actionBarContainer;
    }

    public final int getOpacity() {
        return 0;
    }

    public final void setAlpha(int i) {
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public final void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.f15867a;
        if (actionBarContainer.f890d) {
            Drawable drawable = actionBarContainer.f889c;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f887a;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        if (this.f15867a.f888b == null) {
        }
    }

    public final void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.f15867a;
        if (actionBarContainer.f890d) {
            Drawable drawable = actionBarContainer.f889c;
            if (drawable != null) {
                drawable.getOutline(outline);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f887a;
        if (drawable2 != null) {
            drawable2.getOutline(outline);
        }
    }
}

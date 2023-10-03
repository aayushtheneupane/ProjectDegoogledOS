package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;

/* renamed from: androidx.recyclerview.widget.I */
public abstract class C0536I {
    protected final C0558ca mLayoutManager;
    final Rect mTmpRect = new Rect();

    /* renamed from: mr */
    private int f549mr = RtlSpacingHelper.UNDEFINED;

    /* synthetic */ C0536I(C0558ca caVar, C0533G g) {
        this.mLayoutManager = caVar;
    }

    /* renamed from: a */
    public static C0536I m632a(C0558ca caVar, int i) {
        if (i == 0) {
            return new C0533G(caVar);
        }
        if (i == 1) {
            return new C0535H(caVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.f549mr) {
            return 0;
        }
        return getTotalSpace() - this.f549mr;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChildren(int i);

    public void onLayoutComplete() {
        this.f549mr = getTotalSpace();
    }
}

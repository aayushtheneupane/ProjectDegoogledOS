package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/* renamed from: androidx.recyclerview.widget.X */
public abstract class C0550X {
    @Deprecated
    /* renamed from: a */
    public void mo4985a(Canvas canvas, RecyclerView recyclerView) {
    }

    /* renamed from: a */
    public void mo4986a(Canvas canvas, RecyclerView recyclerView, C0582oa oaVar) {
        mo4985a(canvas, recyclerView);
    }

    @Deprecated
    /* renamed from: b */
    public void mo4989b(Canvas canvas, RecyclerView recyclerView) {
    }

    /* renamed from: b */
    public void mo4664b(Canvas canvas, RecyclerView recyclerView, C0582oa oaVar) {
        mo4989b(canvas, recyclerView);
    }

    @Deprecated
    /* renamed from: a */
    public void mo4987a(Rect rect, int i, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }

    /* renamed from: a */
    public void mo4988a(Rect rect, View view, RecyclerView recyclerView, C0582oa oaVar) {
        mo4987a(rect, ((C0560da) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
    }
}

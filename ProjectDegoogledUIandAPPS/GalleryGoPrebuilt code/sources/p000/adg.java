package p000;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: adg */
/* compiled from: PG */
public final class adg extends C0643xp {

    /* renamed from: a */
    public Drawable f209a;

    /* renamed from: b */
    public int f210b;

    /* renamed from: c */
    public boolean f211c = true;

    /* renamed from: d */
    public final /* synthetic */ adk f212d;

    public adg(adk adk) {
        this.f212d = adk;
    }

    /* renamed from: a */
    public final void mo199a(Rect rect, View view, RecyclerView recyclerView, C0664yj yjVar) {
        if (m234a(view, recyclerView)) {
            rect.bottom = this.f210b;
        }
    }

    /* renamed from: a */
    public final void mo198a(Canvas canvas, RecyclerView recyclerView) {
        if (this.f209a != null) {
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (m234a(childAt, recyclerView)) {
                    int y = ((int) childAt.getY()) + childAt.getHeight();
                    this.f209a.setBounds(0, y, width, this.f210b + y);
                    this.f209a.draw(canvas);
                }
            }
        }
    }

    /* renamed from: a */
    private final boolean m234a(View view, RecyclerView recyclerView) {
        C0667ym childViewHolder = recyclerView.getChildViewHolder(view);
        if (!(childViewHolder instanceof ady) || !((ady) childViewHolder).f248q) {
            return false;
        }
        boolean z = this.f211c;
        int indexOfChild = recyclerView.indexOfChild(view);
        if (indexOfChild >= recyclerView.getChildCount() - 1) {
            return z;
        }
        C0667ym childViewHolder2 = recyclerView.getChildViewHolder(recyclerView.getChildAt(indexOfChild + 1));
        if (!(childViewHolder2 instanceof ady) || !((ady) childViewHolder2).f247p) {
            return false;
        }
        return true;
    }
}

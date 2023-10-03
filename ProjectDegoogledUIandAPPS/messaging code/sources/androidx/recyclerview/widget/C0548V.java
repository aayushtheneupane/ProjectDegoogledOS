package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.V */
public class C0548V {
    public int left;
    public int top;

    /* renamed from: i */
    public C0548V mo4983i(C0586qa qaVar) {
        View view = qaVar.itemView;
        this.left = view.getLeft();
        this.top = view.getTop();
        view.getRight();
        view.getBottom();
        return this;
    }
}

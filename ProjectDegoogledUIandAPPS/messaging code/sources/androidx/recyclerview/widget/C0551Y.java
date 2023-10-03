package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.Y */
class C0551Y implements C0528Da {
    final /* synthetic */ C0558ca this$0;

    C0551Y(C0558ca caVar) {
        this.this$0 = caVar;
    }

    /* renamed from: a */
    public int mo4642a(View view) {
        return this.this$0.getDecoratedLeft(view) - ((C0560da) view.getLayoutParams()).leftMargin;
    }

    /* renamed from: b */
    public int mo4643b(View view) {
        return this.this$0.getDecoratedRight(view) + ((C0560da) view.getLayoutParams()).rightMargin;
    }

    /* renamed from: f */
    public int mo4644f() {
        return this.this$0.getPaddingLeft();
    }

    public View getChildAt(int i) {
        return this.this$0.getChildAt(i);
    }

    /* renamed from: w */
    public int mo4646w() {
        return this.this$0.getWidth() - this.this$0.getPaddingRight();
    }
}

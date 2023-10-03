package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.Z */
class C0552Z implements C0528Da {
    final /* synthetic */ C0558ca this$0;

    C0552Z(C0558ca caVar) {
        this.this$0 = caVar;
    }

    /* renamed from: a */
    public int mo4642a(View view) {
        return this.this$0.getDecoratedTop(view) - ((C0560da) view.getLayoutParams()).topMargin;
    }

    /* renamed from: b */
    public int mo4643b(View view) {
        return this.this$0.getDecoratedBottom(view) + ((C0560da) view.getLayoutParams()).bottomMargin;
    }

    /* renamed from: f */
    public int mo4644f() {
        return this.this$0.getPaddingTop();
    }

    public View getChildAt(int i) {
        return this.this$0.getChildAt(i);
    }

    /* renamed from: w */
    public int mo4646w() {
        return this.this$0.getHeight() - this.this$0.getPaddingBottom();
    }
}

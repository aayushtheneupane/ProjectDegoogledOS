package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.p */
class C0583p implements Runnable {
    final /* synthetic */ FastScroller this$0;

    C0583p(FastScroller fastScroller) {
        this.this$0 = fastScroller;
    }

    public void run() {
        this.this$0.hide(500);
    }
}

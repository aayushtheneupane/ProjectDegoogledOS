package androidx.viewpager.widget;

/* renamed from: androidx.viewpager.widget.d */
class C0619d implements Runnable {
    final /* synthetic */ ViewPager this$0;

    C0619d(ViewPager viewPager) {
        this.this$0 = viewPager;
    }

    public void run() {
        this.this$0.setScrollState(0);
        this.this$0.populate();
    }
}

package androidx.viewpager.widget;

import android.database.DataSetObserver;

/* renamed from: androidx.viewpager.widget.m */
class C0628m extends DataSetObserver {
    final /* synthetic */ ViewPager this$0;

    C0628m(ViewPager viewPager) {
        this.this$0 = viewPager;
    }

    public void onChanged() {
        this.this$0.dataSetChanged();
    }

    public void onInvalidated() {
        this.this$0.dataSetChanged();
    }
}

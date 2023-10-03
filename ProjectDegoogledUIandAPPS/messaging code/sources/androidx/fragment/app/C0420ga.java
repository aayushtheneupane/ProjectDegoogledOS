package androidx.fragment.app;

import android.widget.ListView;

/* renamed from: androidx.fragment.app.ga */
class C0420ga implements Runnable {
    final /* synthetic */ ListFragment this$0;

    C0420ga(ListFragment listFragment) {
        this.this$0 = listFragment;
    }

    public void run() {
        ListView listView = this.this$0.mList;
        listView.focusableViewAvailable(listView);
    }
}

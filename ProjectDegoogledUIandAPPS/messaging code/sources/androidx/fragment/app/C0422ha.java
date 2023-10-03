package androidx.fragment.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* renamed from: androidx.fragment.app.ha */
class C0422ha implements AdapterView.OnItemClickListener {
    final /* synthetic */ ListFragment this$0;

    C0422ha(ListFragment listFragment) {
        this.this$0 = listFragment;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.this$0.onListItemClick((ListView) adapterView, view, i, j);
    }
}

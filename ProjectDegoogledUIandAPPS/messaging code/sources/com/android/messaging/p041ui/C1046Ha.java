package com.android.messaging.p041ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

/* renamed from: com.android.messaging.ui.Ha */
class C1046Ha implements ExpandableListView.OnChildClickListener {
    final /* synthetic */ VCardDetailFragment this$0;

    C1046Ha(VCardDetailFragment vCardDetailFragment) {
        this.this$0 = vCardDetailFragment;
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        Intent Mb;
        if ((view instanceof PersonItemView) && (Mb = ((PersonItemView) view).mo7063Mb()) != null) {
            try {
                this.this$0.startActivity(Mb);
                return true;
            } catch (ActivityNotFoundException unused) {
            }
        }
        return false;
    }
}

package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0917ba;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.conversation.Ba */
class C1132Ba extends ArrayAdapter {
    final /* synthetic */ SimSelectorView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1132Ba(SimSelectorView simSelectorView, Context context) {
        super(context, R.layout.sim_selector_item_view, new ArrayList());
        this.this$0 = simSelectorView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SimSelectorItemView simSelectorItemView;
        if (view == null || !(view instanceof SimSelectorItemView)) {
            simSelectorItemView = (SimSelectorItemView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(this.this$0.f1849gg, viewGroup, false);
            simSelectorItemView.mo7442a((C1206ya) this.this$0);
        } else {
            simSelectorItemView = (SimSelectorItemView) view;
        }
        simSelectorItemView.mo7443c((C0917ba) getItem(i));
        return simSelectorItemView;
    }
}

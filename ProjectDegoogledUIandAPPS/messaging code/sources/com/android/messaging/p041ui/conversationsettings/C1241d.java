package com.android.messaging.p041ui.conversationsettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1031A;

/* renamed from: com.android.messaging.ui.conversationsettings.d */
class C1241d extends C1031A {

    /* renamed from: EF */
    private final int f1965EF;

    /* renamed from: FF */
    private final boolean f1966FF;
    final /* synthetic */ PeopleAndOptionsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1241d(PeopleAndOptionsFragment peopleAndOptionsFragment, BaseAdapter baseAdapter, int i, boolean z) {
        super(true, true, baseAdapter);
        this.this$0 = peopleAndOptionsFragment;
        this.f1965EF = i;
        this.f1966FF = z;
    }

    /* renamed from: a */
    public View mo6855a(View view, ViewGroup viewGroup) {
        int i = 0;
        if (view == null || view.getId() != R.id.people_and_options_header) {
            view = LayoutInflater.from(this.this$0.getActivity()).inflate(R.layout.people_and_options_section_header, viewGroup, false);
        }
        View findViewById = view.findViewById(R.id.divider);
        ((TextView) view.findViewById(R.id.header_text)).setText(this.f1965EF);
        if (!this.f1966FF) {
            i = 8;
        }
        findViewById.setVisibility(i);
        return view;
    }
}

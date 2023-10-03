package com.android.messaging.p041ui.debug;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.util.C1474sa;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.debug.a */
class C1248a implements AdapterView.OnItemSelectedListener {

    /* renamed from: PH */
    final /* synthetic */ Integer[] f1974PH;
    final /* synthetic */ DebugMmsConfigFragment this$0;
    final /* synthetic */ ListView val$listView;

    /* renamed from: yp */
    final /* synthetic */ View f1975yp;

    C1248a(DebugMmsConfigFragment debugMmsConfigFragment, ListView listView, Integer[] numArr, View view) {
        this.this$0 = debugMmsConfigFragment;
        this.val$listView = listView;
        this.f1974PH = numArr;
        this.f1975yp = view;
    }

    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ListView listView = this.val$listView;
        DebugMmsConfigFragment debugMmsConfigFragment = this.this$0;
        listView.setAdapter(new C1249b(debugMmsConfigFragment, debugMmsConfigFragment.getActivity(), this.f1974PH[i].intValue()));
        int[] fk = C1474sa.get(this.f1974PH[i].intValue()).mo8207fk();
        StringBuilder Pa = C0632a.m1011Pa("(");
        Pa.append(fk[0]);
        Pa.append("/");
        Pa.append(fk[1]);
        Pa.append(") ");
        Pa.append(this.this$0.getActivity().getString(R.string.debug_sub_id_spinner_text));
        ((TextView) this.f1975yp.findViewById(R.id.sim_title)).setText(Pa.toString());
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}

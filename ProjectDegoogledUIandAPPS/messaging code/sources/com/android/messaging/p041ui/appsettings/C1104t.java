package com.android.messaging.p041ui.appsettings;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0912Y;
import com.android.messaging.datamodel.data.C0915aa;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import java.util.List;

/* renamed from: com.android.messaging.ui.appsettings.t */
public class C1104t extends Fragment implements C0912Y {
    private C1103s mAdapter;
    private final C0783c mBinding = C0784d.m1315q(this);
    private ListView mListView;

    /* renamed from: a */
    public void mo7188a(C0915aa aaVar) {
        this.mBinding.mo5929a(aaVar);
        C1103s sVar = this.mAdapter;
        List xf = aaVar.mo6399xf();
        sVar.clear();
        sVar.addAll(xf);
        sVar.notifyDataSetChanged();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBinding.mo5930b(C0947h.get().mo6597a((Context) getActivity(), (C0912Y) this));
        ((C0915aa) this.mBinding.getData()).mo6395a(getLoaderManager(), this.mBinding);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.settings_fragment, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(16908298);
        this.mAdapter = new C1103s(this, getActivity());
        this.mListView.setAdapter(this.mAdapter);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }
}

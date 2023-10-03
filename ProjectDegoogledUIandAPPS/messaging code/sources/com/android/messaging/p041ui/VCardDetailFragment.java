package com.android.messaging.p041ui;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0908U;
import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.datamodel.data.C0921da;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.VCardDetailFragment */
public class VCardDetailFragment extends Fragment implements C0908U {

    /* renamed from: Ea */
    private Uri f1680Ea;
    /* access modifiers changed from: private */

    /* renamed from: Fa */
    public Uri f1681Fa;
    private C1042Fa mAdapter;
    /* access modifiers changed from: private */
    public final C0783c mBinding = C0784d.m1315q(this);
    /* access modifiers changed from: private */
    public ExpandableListView mListView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.vcard_detail_fragment_menu, menu);
        menu.findItem(R.id.action_add_contact).setVisible(this.mBinding.isBound() && ((C0921da) this.mBinding.getData()).mo6428wf());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        C1424b.m3594t(this.f1680Ea);
        View inflate = layoutInflater.inflate(R.layout.vcard_detail_fragment, viewGroup, false);
        this.mListView = (ExpandableListView) inflate.findViewById(R.id.list);
        this.mListView.addOnLayoutChangeListener(new C1044Ga(this));
        this.mListView.setOnChildClickListener(new C1046Ha(this));
        this.mBinding.mo5930b(C0947h.get().mo6599a((Context) getActivity(), this.f1680Ea));
        ((C0921da) this.mBinding.getData()).mo6379a((C0908U) this);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mBinding.isBound()) {
            this.mBinding.unbind();
        }
        this.mListView.setAdapter((ExpandableListAdapter) null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_add_contact) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mBinding.mo5935yf();
        new C1048Ia(this, ((C0921da) this.mBinding.getData()).mo6427vf()).mo8233b(new Void[0]);
        return true;
    }

    /* renamed from: b */
    public void mo7090b(Uri uri) {
        C1424b.m3592ia(!this.mBinding.isBound());
        this.f1680Ea = uri;
    }

    /* renamed from: a */
    public void mo6377a(C0909V v, Exception exc) {
        this.mBinding.mo5935yf();
        C1486ya.m3848Pa(R.string.failed_loading_vcard);
        getActivity().finish();
    }

    /* renamed from: b */
    public void mo6378b(C0909V v) {
        C1424b.m3592ia(v instanceof C0921da);
        this.mBinding.mo5935yf();
        C0921da daVar = (C0921da) v;
        C1424b.m3592ia(daVar.mo6428wf());
        this.mAdapter = new C1042Fa(getActivity(), daVar.mo6426uf().mo6125Wh());
        this.mListView.setAdapter(this.mAdapter);
        if (this.mAdapter.getGroupCount() == 1) {
            this.mListView.expandGroup(0);
        }
        getActivity().invalidateOptionsMenu();
    }
}

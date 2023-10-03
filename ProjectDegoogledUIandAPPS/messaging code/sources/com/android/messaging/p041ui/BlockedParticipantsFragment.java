package com.android.messaging.p041ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0914a;
import com.android.messaging.datamodel.data.C0916b;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;

/* renamed from: com.android.messaging.ui.BlockedParticipantsFragment */
public class BlockedParticipantsFragment extends Fragment implements C0914a {
    private C1381t mAdapter;
    /* access modifiers changed from: private */
    public final C0783c mBinding = C0784d.m1315q(this);
    private ListView mListView;

    /* renamed from: b */
    public void mo6394b(Cursor cursor) {
        this.mAdapter.swapCursor(cursor);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.blocked_participants_fragment, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(16908298);
        this.mAdapter = new C1381t(this, getActivity(), (Cursor) null);
        this.mListView.setAdapter(this.mAdapter);
        this.mBinding.mo5930b(C0947h.get().mo6598a((Context) getActivity(), (C0914a) this));
        ((C0916b) this.mBinding.getData()).mo6400a(getLoaderManager(), this.mBinding);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }
}

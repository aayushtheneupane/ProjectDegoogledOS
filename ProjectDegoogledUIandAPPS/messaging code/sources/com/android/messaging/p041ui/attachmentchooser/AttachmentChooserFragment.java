package com.android.messaging.p041ui.attachmentchooser;

import android.app.Fragment;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0942y;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1040Ea;
import java.util.List;

/* renamed from: com.android.messaging.ui.attachmentchooser.AttachmentChooserFragment */
public class AttachmentChooserFragment extends Fragment implements C0942y, C1112h {
    /* access modifiers changed from: private */

    /* renamed from: Ga */
    public AttachmentGridView f1757Ga;
    private C1106b mAdapter;
    C0783c mBinding = C0784d.m1315q(this);
    private C1105a mHost;

    /* renamed from: a */
    public void mo6221a(C0889A a) {
    }

    /* access modifiers changed from: package-private */
    public void confirmSelection() {
        if (this.mBinding.isBound()) {
            ((C0889A) this.mBinding.getData()).mo6181b(this.f1757Ga.mo7208Db());
            ((C0889A) this.mBinding.getData()).mo6186e((C0784d) this.mBinding);
            this.mHost.mo7192da();
        }
    }

    /* renamed from: e */
    public void mo7197e(String str) {
        this.mBinding.mo5930b(C0947h.get().mo6587K(str));
        ((C0889A) this.mBinding.getData()).mo6176a((C0942y) this);
        ((C0889A) this.mBinding.getData()).mo6179a(this.mBinding, (MessageData) null, false);
    }

    /* renamed from: f */
    public void mo7198f(int i) {
        ActionBar supportActionBar;
        if ((getActivity() instanceof BugleActionBarActivity) && (supportActionBar = ((BugleActionBarActivity) getActivity()).getSupportActionBar()) != null) {
            supportActionBar.setTitle((CharSequence) getResources().getString(R.string.attachment_chooser_selection, new Object[]{Integer.valueOf(i)}));
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.attachment_chooser_menu, menu);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.attachment_chooser_fragment, viewGroup, false);
        this.f1757Ga = (AttachmentGridView) inflate.findViewById(R.id.grid);
        this.mAdapter = new C1106b(this, getActivity());
        this.f1757Ga.setAdapter(this.mAdapter);
        this.f1757Ga.mo7210a((C1112h) this);
        setHasOptionsMenu(true);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_confirm_selection) {
            return super.onOptionsItemSelected(menuItem);
        }
        confirmSelection();
        return true;
    }

    /* renamed from: r */
    public void mo6223r() {
    }

    /* renamed from: a */
    public void mo7195a(C1105a aVar) {
        this.mHost = aVar;
    }

    /* renamed from: a */
    public void mo6222a(C0889A a, int i) {
        this.mBinding.mo5929a(a);
        if ((i & 1) == 1) {
            C1106b bVar = this.mAdapter;
            List list = a.mo6195if();
            bVar.clear();
            bVar.addAll(list);
            bVar.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void mo7194a(Rect rect, Uri uri) {
        C1040Ea.get().mo6952a(getActivity(), uri, rect, MessagingContentProvider.m1271p(((C0889A) this.mBinding.getData()).mo6170Ue()));
    }
}

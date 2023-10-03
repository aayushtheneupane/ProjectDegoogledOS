package com.android.messaging.p041ui.conversationlist;

import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.util.C1424b;
import java.util.HashSet;
import p000a.p005b.C0015b;

/* renamed from: com.android.messaging.ui.conversationlist.v */
public class C1233v implements ActionMode.Callback {

    /* renamed from: GH */
    private HashSet f1951GH;

    /* renamed from: HH */
    private final C0015b f1952HH = new C0015b();

    /* renamed from: IH */
    private MenuItem f1953IH;

    /* renamed from: JH */
    private MenuItem f1954JH;

    /* renamed from: KH */
    private MenuItem f1955KH;

    /* renamed from: LH */
    private MenuItem f1956LH;

    /* renamed from: MH */
    private boolean f1957MH;
    private C1231t mListener;

    public C1233v(C1231t tVar) {
        this.mListener = tVar;
    }

    /* renamed from: zo */
    private void m3147zo() {
        if (this.f1957MH) {
            boolean z = false;
            if (this.f1952HH.size() == 1) {
                C1232u uVar = (C1232u) this.f1952HH.valueAt(0);
                this.f1955KH.setVisible(!uVar.f1949EH && !(TextUtils.isEmpty(uVar.f1948DH) ^ true));
                String str = uVar.f1947CH;
                this.f1956LH.setVisible(str != null && !this.f1951GH.contains(str));
            } else {
                this.f1956LH.setVisible(false);
                this.f1955KH.setVisible(false);
            }
            boolean z2 = false;
            for (C1232u uVar2 : this.f1952HH.values()) {
                if (uVar2.f1950FH) {
                    z = true;
                } else {
                    z2 = true;
                }
                if (z && z2) {
                    break;
                }
            }
            this.f1953IH.setVisible(z2);
            this.f1954JH.setVisible(z);
        }
    }

    /* renamed from: a */
    public void mo7591a(C0933p pVar, C0934q qVar) {
        C1424b.m3594t(qVar);
        this.f1951GH = pVar.mo6487df();
        String Ue = qVar.mo6505Ue();
        if (this.f1952HH.containsKey(Ue)) {
            this.f1952HH.remove(Ue);
        } else {
            this.f1952HH.put(Ue, new C1232u(qVar));
        }
        if (this.f1952HH.isEmpty()) {
            this.mListener.mo7532E();
        } else {
            m3147zo();
        }
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                this.mListener.mo7532E();
                return true;
            case R.id.action_add_contact /*2131361833*/:
                C1424b.m3592ia(this.f1952HH.size() == 1);
                this.mListener.mo7524b((C1232u) this.f1952HH.valueAt(0));
                return true;
            case R.id.action_archive /*2131361835*/:
                this.mListener.mo7523a(this.f1952HH.values(), true);
                return true;
            case R.id.action_block /*2131361843*/:
                C1424b.m3592ia(this.f1952HH.size() == 1);
                this.mListener.mo7522a((C1232u) this.f1952HH.valueAt(0));
                return true;
            case R.id.action_delete /*2131361851*/:
                this.mListener.mo7525b(this.f1952HH.values());
                return true;
            case R.id.action_unarchive /*2131361874*/:
                this.mListener.mo7523a(this.f1952HH.values(), false);
                return true;
            default:
                return false;
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.conversation_list_fragment_select_menu, menu);
        this.f1953IH = menu.findItem(R.id.action_archive);
        this.f1954JH = menu.findItem(R.id.action_unarchive);
        this.f1955KH = menu.findItem(R.id.action_add_contact);
        this.f1956LH = menu.findItem(R.id.action_block);
        this.f1957MH = true;
        m3147zo();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.mListener = null;
        this.f1952HH.clear();
        this.f1957MH = false;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    /* renamed from: ua */
    public boolean mo7596ua(String str) {
        return this.f1952HH.indexOfKey(str) >= 0;
    }
}

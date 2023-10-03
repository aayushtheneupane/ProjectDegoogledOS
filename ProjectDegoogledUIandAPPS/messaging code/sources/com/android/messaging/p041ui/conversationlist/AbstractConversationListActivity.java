package com.android.messaging.p041ui.conversationlist;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.C1369oa;
import com.android.messaging.p041ui.C1376qa;
import com.android.messaging.p041ui.contact.AddContactsConfirmationDialog;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1486ya;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversationlist.AbstractConversationListActivity */
public abstract class AbstractConversationListActivity extends BugleActionBarActivity implements C1224m, C1231t {

    /* renamed from: Xb */
    protected ConversationListFragment f1895Xb;

    /* renamed from: B */
    public boolean mo7519B() {
        return mo7528db();
    }

    /* renamed from: Z */
    public void mo7520Z() {
        C1040Ea.get().mo6958a((Context) this, (MessageData) null);
    }

    /* renamed from: a */
    public void mo7523a(Iterable iterable, boolean z) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            String str = ((C1232u) it.next()).f1946BH;
            arrayList.add(str);
            if (z) {
                UpdateConversationArchiveStatusAction.m1455T(str);
            } else {
                UpdateConversationArchiveStatusAction.m1456U(str);
            }
        }
        C1486ya.m3853a((Context) this, findViewById(16908298), getResources().getString(z ? R.string.archived_toast_message : R.string.unarchived_toast_message, new Object[]{Integer.valueOf(arrayList.size())}), (Runnable) new C1214c(this, arrayList, z), 0, this.f1895Xb.mo7546b());
        mo7526bb();
    }

    /* renamed from: b */
    public void mo7525b(Collection collection) {
        if (!C1474sa.getDefault().mo8228kk()) {
            C1486ya.m3852a((Context) this, getWindow().getDecorView().getRootView(), getString(R.string.requires_default_sms_app), C1369oa.m3482a(new C1212a(this, this), getString(R.string.requires_default_sms_change_button)), (List) null, (C1376qa) null);
            return;
        }
        new AlertDialog.Builder(this, R.style.BugleThemeDialog).setTitle(getResources().getQuantityString(R.plurals.delete_conversations_confirmation_dialog_title, collection.size())).setPositiveButton(R.string.delete_conversation_confirmation_button, new C1213b(this, collection)).setNegativeButton(R.string.delete_conversation_decline_button, (DialogInterface.OnClickListener) null).show();
    }

    /* access modifiers changed from: protected */
    /* renamed from: bb */
    public void mo7526bb() {
        this.f1895Xb.mo7559wa();
        mo6907G();
        this.f1895Xb.mo7560xa();
    }

    /* renamed from: d */
    public boolean mo7527d(String str) {
        return mo7528db() && ((C1233v) mo6910_a()).mo7596ua(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: db */
    public boolean mo7528db() {
        return mo6910_a() instanceof C1233v;
    }

    /* renamed from: eb */
    public void mo7529eb() {
        C1410N.m3549c(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: fb */
    public void mo7530fb() {
        startActionMode(new C1233v(this));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ConversationListFragment) {
            this.f1895Xb = (ConversationListFragment) fragment;
            this.f1895Xb.mo7545a((C1224m) this);
        }
    }

    public void onBackPressed() {
        if (mo6908K() != null) {
            mo6907G();
        } else {
            super.onBackPressed();
        }
    }

    /* renamed from: a */
    public void mo7522a(C1232u uVar) {
        Resources resources = getResources();
        new AlertDialog.Builder(this, R.style.BugleThemeDialog).setTitle(resources.getString(R.string.block_confirmation_title, new Object[]{uVar.f1947CH})).setMessage(resources.getString(R.string.block_confirmation_message)).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, new C1216e(this, uVar)).create().show();
    }

    /* renamed from: b */
    public void mo7524b(C1232u uVar) {
        String str = uVar.icon;
        new AddContactsConfirmationDialog(this, str != null ? Uri.parse(str) : null, uVar.f1947CH).show();
        mo7526bb();
    }

    /* renamed from: a */
    public void mo7521a(C0933p pVar, C0934q qVar, boolean z, ConversationListItemView conversationListItemView) {
        if (z && !mo7528db()) {
            mo7530fb();
        }
        if (mo7528db()) {
            ((C1233v) mo6910_a()).mo7591a(pVar, qVar);
            this.f1895Xb.mo7560xa();
            return;
        }
        C1040Ea.get().mo6960a((Context) this, qVar.mo6505Ue(), (MessageData) null, (Bundle) null, false);
    }
}

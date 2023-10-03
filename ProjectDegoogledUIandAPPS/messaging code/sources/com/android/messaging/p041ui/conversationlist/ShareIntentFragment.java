package com.android.messaging.p041ui.conversationlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0543P;
import androidx.recyclerview.widget.C0558ca;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0932o;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.ListEmptyView;

/* renamed from: com.android.messaging.ui.conversationlist.ShareIntentFragment */
public class ShareIntentFragment extends DialogFragment implements C0932o, C1234w {

    /* renamed from: J */
    private ListEmptyView f1926J;
    private C1237z mAdapter;
    /* access modifiers changed from: private */
    public boolean mDismissed;
    /* access modifiers changed from: private */
    public C1211C mHost;
    private final C0783c mListBinding = C0784d.m1315q(this);
    private RecyclerView mRecyclerView;

    /* renamed from: b */
    public void mo7574b(C0934q qVar) {
        this.mHost.mo7109a(qVar);
    }

    /* renamed from: e */
    public void mo6484e(boolean z) {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof C1211C) {
            this.mHost = (C1211C) activity;
        }
        this.mListBinding.mo5930b(C0947h.get().mo6603a((Context) activity, (C0932o) this, false));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        View inflate = activity.getLayoutInflater().inflate(R.layout.share_intent_conversation_list_view, (ViewGroup) null);
        this.f1926J = (ListEmptyView) inflate.findViewById(R.id.no_conversations_view);
        this.f1926J.mo7036w(R.drawable.ic_oobe_conv_list);
        C1209A a = new C1209A(this, activity);
        ((C0933p) this.mListBinding.getData()).mo6486a(getLoaderManager(), this.mListBinding);
        this.mAdapter = new C1237z(activity, (Cursor) null, this);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(16908298);
        this.mRecyclerView.mo4822a((C0558ca) a);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.mo4819a((C0543P) this.mAdapter);
        AlertDialog.Builder title = new AlertDialog.Builder(activity, R.style.BugleThemeDialog).setView(inflate).setTitle(R.string.share_intent_activity_label);
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.getBoolean("hide_conv_button_key")) {
            title.setPositiveButton(R.string.share_new_message, new C1210B(this));
        }
        return title.setNegativeButton(R.string.share_cancel, (DialogInterface.OnClickListener) null).create();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mListBinding.unbind();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Activity activity;
        if (!this.mDismissed && (activity = getActivity()) != null) {
            activity.finish();
        }
    }

    /* renamed from: a */
    public void mo6483a(C0933p pVar, Cursor cursor) {
        this.mListBinding.mo5929a(pVar);
        this.mAdapter.swapCursor(cursor);
        if (cursor == null || cursor.getCount() == 0) {
            this.f1926J.mo7037x(R.string.conversation_list_empty_text);
            this.f1926J.setVisibility(0);
            return;
        }
        this.f1926J.setVisibility(8);
    }
}

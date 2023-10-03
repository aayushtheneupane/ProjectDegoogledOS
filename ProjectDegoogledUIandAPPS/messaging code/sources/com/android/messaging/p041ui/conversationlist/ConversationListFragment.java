package com.android.messaging.p041ui.conversationlist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.C0543P;
import androidx.recyclerview.widget.C0558ca;
import androidx.recyclerview.widget.C0564fa;
import androidx.recyclerview.widget.C0566ga;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.R;
import com.android.messaging.annotation.VisibleForAnimation;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0932o;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.C1382ta;
import com.android.messaging.p041ui.ListEmptyView;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversationlist.ConversationListFragment */
public class ConversationListFragment extends Fragment implements C0932o, C1227p {

    /* renamed from: J */
    private ListEmptyView f1896J;

    /* renamed from: Ma */
    private Parcelable f1897Ma;

    /* renamed from: Wa */
    private MenuItem f1898Wa;

    /* renamed from: Xa */
    private boolean f1899Xa;

    /* renamed from: Ya */
    private boolean f1900Ya;

    /* renamed from: Za */
    private boolean f1901Za;
    /* access modifiers changed from: private */

    /* renamed from: _a */
    public ImageView f1902_a;
    private C1219h mAdapter;
    /* access modifiers changed from: private */
    public C1224m mHost;
    final C0783c mListBinding = C0784d.m1315q(this);
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;

    /* access modifiers changed from: private */
    /* renamed from: Am */
    public boolean m3069Am() {
        return ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0;
    }

    /* renamed from: i */
    public static ConversationListFragment m3074i(String str) {
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, true);
        conversationListFragment.setArguments(bundle);
        return conversationListFragment;
    }

    /* renamed from: B */
    public boolean mo7540B() {
        C1224m mVar = this.mHost;
        return mVar != null && mVar.mo7519B();
    }

    /* renamed from: Y */
    public boolean mo7541Y() {
        return this.mHost.mo7533Y();
    }

    /* renamed from: e */
    public void mo6484e(boolean z) {
        this.f1900Ya = z;
        MenuItem menuItem = this.f1898Wa;
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
    }

    @VisibleForAnimation
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f1899Xa = arguments.getBoolean("archived_mode", false);
            this.f1901Za = arguments.getBoolean("forward_message_mode", false);
        }
        this.mListBinding.mo5930b(C0947h.get().mo6603a((Context) activity, (C0932o) this, this.f1899Xa));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((C0933p) this.mListBinding.getData()).mo6486a(getLoaderManager(), this.mListBinding);
        this.mAdapter = new C1219h(getActivity(), (Cursor) null, this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (isAdded()) {
            this.f1898Wa = menu.findItem(R.id.action_show_blocked_contacts);
            MenuItem menuItem = this.f1898Wa;
            if (menuItem != null) {
                menuItem.setVisible(this.f1900Ya);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.conversation_list_fragment, viewGroup, false);
        this.mRecyclerView = (RecyclerView) viewGroup2.findViewById(16908298);
        this.f1896J = (ListEmptyView) viewGroup2.findViewById(R.id.no_conversations_view);
        this.f1896J.mo7036w(R.drawable.ic_oobe_conv_list);
        this.mRecyclerView.mo4822a((C0558ca) new C1220i(this, getActivity()));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.mo4819a((C0543P) this.mAdapter);
        this.mRecyclerView.mo4839c((C0566ga) new C1221j(this));
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.mo4823a((C0564fa) new C1230s(recyclerView));
        if (bundle != null) {
            this.f1897Ma = bundle.getParcelable("conversationListViewState");
        }
        this.f1902_a = (ImageView) viewGroup2.findViewById(R.id.start_new_conversation_button);
        if (this.f1899Xa) {
            this.f1902_a.setVisibility(8);
        } else {
            this.f1902_a.setVisibility(0);
            this.f1902_a.setOnClickListener(new C1222k(this));
        }
        ViewCompat.setTransitionName(this.f1902_a, "bugle:fabicon");
        int i = Build.VERSION.SDK_INT;
        viewGroup2.setTransitionGroup(false);
        setHasOptionsMenu(true);
        return viewGroup2;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mListBinding.unbind();
        this.mHost = null;
    }

    public void onPause() {
        super.onPause();
        this.f1897Ma = this.mRecyclerView.getLayoutManager().onSaveInstanceState();
        ((C0933p) this.mListBinding.getData()).mo6485K(false);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.action_start_new_conversation);
        if (findItem != null) {
            findItem.setVisible(((AccessibilityManager) getActivity().getSystemService("accessibility")).isTouchExplorationEnabled());
        }
        MenuItem findItem2 = menu.findItem(R.id.action_show_archived);
        if (findItem2 != null) {
            findItem2.setVisible(true);
        }
    }

    public void onResume() {
        super.onResume();
        C1424b.m3594t(this.mHost);
        mo7558va();
        mo7560xa();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelable = this.f1897Ma;
        if (parcelable != null) {
            bundle.putParcelable("conversationListViewState", parcelable);
        }
    }

    /* renamed from: va */
    public void mo7558va() {
        if (!this.f1899Xa && !this.f1901Za && m3069Am() && this.mHost.hasWindowFocus()) {
            ((C0933p) this.mListBinding.getData()).mo6485K(true);
        }
    }

    /* renamed from: wa */
    public ViewPropertyAnimator mo7559wa() {
        return this.f1902_a.animate().setInterpolator(C1486ya.f2357aL).setDuration((long) getActivity().getResources().getInteger(R.integer.fab_animation_duration_ms)).translationX(0.0f).withEndAction(new C1223l(this));
    }

    /* renamed from: xa */
    public void mo7560xa() {
        this.mAdapter.notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo7545a(C1224m mVar) {
        C1424b.isNull(this.mHost);
        this.mHost = mVar;
    }

    /* renamed from: b */
    public List mo7546b() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new C1382ta(this.f1902_a));
        return arrayList;
    }

    /* renamed from: d */
    public boolean mo7547d(String str) {
        return this.mHost.mo7527d(str);
    }

    /* renamed from: a */
    public void mo6483a(C0933p pVar, Cursor cursor) {
        int i;
        this.mListBinding.mo5929a(pVar);
        Cursor swapCursor = this.mAdapter.swapCursor(cursor);
        if (cursor == null || cursor.getCount() == 0) {
            if (!((C0933p) this.mListBinding.getData()).mo6491re()) {
                i = R.string.conversation_list_first_sync_text;
            } else {
                i = this.f1899Xa ? R.string.archived_conversation_list_empty_text : R.string.conversation_list_empty_text;
            }
            this.f1896J.mo7037x(i);
            this.f1896J.setVisibility(0);
            this.f1896J.mo7033o(true);
            this.f1896J.mo7035p(true);
        } else {
            this.f1896J.setVisibility(8);
        }
        if (this.f1897Ma != null && cursor != null && swapCursor == null) {
            this.mRecyclerView.getLayoutManager().onRestoreInstanceState(this.f1897Ma);
        }
    }

    /* renamed from: a */
    public void mo7544a(C0934q qVar, boolean z, ConversationListItemView conversationListItemView) {
        this.mHost.mo7521a((C0933p) this.mListBinding.getData(), qVar, z, conversationListItemView);
    }

    /* renamed from: a */
    public void mo7543a(Uri uri, Rect rect, Uri uri2) {
        C1040Ea.get().mo6952a(getActivity(), uri, rect, uri2);
    }

    /* renamed from: a */
    public void mo7542a(Uri uri) {
        C1040Ea.get().mo6972d((Context) getActivity(), uri);
    }
}

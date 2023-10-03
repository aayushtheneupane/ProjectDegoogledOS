package com.android.messaging.p041ui.conversation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.recyclerview.widget.C0543P;
import androidx.recyclerview.widget.C0558ca;
import androidx.recyclerview.widget.C0566ga;
import androidx.recyclerview.widget.C0586qa;
import androidx.recyclerview.widget.C0594ua;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.datamodel.data.C0924g;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.datamodel.data.C0942y;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.C1369oa;
import com.android.messaging.p041ui.C1376qa;
import com.android.messaging.p041ui.contact.AddContactsConfirmationDialog;
import com.android.messaging.p041ui.mediapicker.C1345pa;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1459l;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1486ya;
import java.util.Iterator;
import java.util.List;
import p000a.p010e.p011a.C0045d;

/* renamed from: com.android.messaging.ui.conversation.O */
public class C1146O extends Fragment implements C0924g, C1183n, C1186oa, C1152V, C0942y {
    /* access modifiers changed from: private */

    /* renamed from: Ha */
    public ComposeMessageView f1828Ha;
    /* access modifiers changed from: private */

    /* renamed from: Ia */
    public View f1829Ia;

    /* renamed from: Ja */
    private C1459l f1830Ja;

    /* renamed from: Ka */
    private String f1831Ka;

    /* renamed from: La */
    private MessageData f1832La;

    /* renamed from: Ma */
    private Parcelable f1833Ma;
    /* access modifiers changed from: private */

    /* renamed from: Na */
    public ConversationMessageView f1834Na;
    /* access modifiers changed from: private */

    /* renamed from: Oa */
    public MessagePartData f1835Oa;

    /* renamed from: Pa */
    private final BroadcastReceiver f1836Pa = new C1129A(this);

    /* renamed from: Qa */
    private boolean f1837Qa;

    /* renamed from: Ra */
    private boolean f1838Ra;

    /* renamed from: Sa */
    private C0786f f1839Sa;
    /* access modifiers changed from: private */

    /* renamed from: Ta */
    public int f1840Ta;

    /* renamed from: Ua */
    private final C0566ga f1841Ua = new C1131B(this);

    /* renamed from: Va */
    private final ActionMode.Callback f1842Va = new C1133C(this);
    private C1162ca mAdapter;
    final C0783c mBinding = C0784d.m1315q(this);
    /* access modifiers changed from: private */
    public C1144M mHost;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;

    public C1146O() {
        new Handler();
    }

    private void invalidateOptionsMenu() {
        Activity activity = getActivity();
        if (activity != null && (activity instanceof BugleActionBarActivity)) {
            ((BugleActionBarActivity) activity).supportInvalidateOptionsMenu();
        }
    }

    /* renamed from: ym */
    private boolean m2870ym() {
        C0931n nVar = (C0931n) this.mBinding.getData();
        if (!nVar.mo6465_e()) {
            return false;
        }
        Iterator it = nVar.getParticipants().iterator();
        while (it.hasNext()) {
            if (((ParticipantData) it.next()).mo6328Ah()) {
                C1486ya.m3847Oa(R.string.unknown_sender);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: zm */
    public boolean m2871zm() {
        C0586qa findViewHolderForItemId;
        if (this.mRecyclerView.getChildCount() == 0) {
            return true;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        View childAt = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
        int findLastVisibleItemPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        if (findLastVisibleItemPosition < 0 && (findViewHolderForItemId = this.mRecyclerView.findViewHolderForItemId(this.mRecyclerView.getChildItemId(childAt))) != null) {
            findLastVisibleItemPosition = findViewHolderForItemId.getAdapterPosition();
        }
        if (!(findLastVisibleItemPosition + 1 == this.mRecyclerView.getAdapter().getItemCount()) || childAt.getBottom() > this.mRecyclerView.getHeight()) {
            return false;
        }
        return true;
    }

    /* renamed from: C */
    public SimSelectorView mo7378C() {
        return (SimSelectorView) getView().findViewById(R.id.sim_selector);
    }

    /* renamed from: D */
    public boolean mo7379D() {
        return true;
    }

    /* renamed from: G */
    public void mo7380G() {
        this.mHost.mo7370G();
    }

    /* renamed from: H */
    public int mo6582H() {
        ParticipantData Y = ((C0931n) this.mBinding.getData()).mo6462Y(this.f1828Ha.mo7294Pb());
        if (Y == null) {
            return -1;
        }
        return Y.getSubId();
    }

    /* renamed from: I */
    public void mo7381I() {
        this.mHost.mo7324I();
    }

    /* renamed from: Q */
    public void mo7382Q() {
        this.mHost.mo7324I();
    }

    /* renamed from: T */
    public Uri mo7383T() {
        return null;
    }

    /* renamed from: U */
    public void mo7384U() {
        if (this.f1828Ha != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f1828Ha.getWindowToken(), 0);
        }
        FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
        C1198ua newInstance = C1198ua.newInstance(mo6582H());
        newInstance.setTargetFragment(this, 0);
        newInstance.show(beginTransaction, (String) null);
    }

    /* renamed from: a */
    public int mo7385a() {
        return R.layout.sim_selector_item_view;
    }

    /* renamed from: a */
    public void mo6221a(C0889A a) {
    }

    /* renamed from: c */
    public boolean mo7396c() {
        return false;
    }

    /* renamed from: d */
    public int mo7397d() {
        return -1;
    }

    /* renamed from: d */
    public void mo7398d(boolean z) {
    }

    /* renamed from: f */
    public void mo7400f(boolean z) {
    }

    /* renamed from: i */
    public void mo7404i() {
        m2859a(this.f1831Ka, getActivity());
    }

    public void invalidateActionBar() {
        this.mHost.invalidateActionBar();
    }

    public boolean isBound() {
        return this.mBinding.isBound();
    }

    /* renamed from: la */
    public C1345pa mo7407la() {
        return new C1345pa(getActivity());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mRecyclerView.setVisibility(4);
        this.mBinding.mo5935yf();
        ((C0931n) this.mBinding.getData()).mo6467a(getLoaderManager(), (C0784d) this.mBinding);
        this.f1828Ha.mo7304a(new C1158aa(getActivity(), this, this.f1828Ha, this.mHost, C1464na.m3755Vj() ? getChildFragmentManager() : getFragmentManager(), this.mBinding, this.f1828Ha.mo7295Qb(), bundle));
        this.f1828Ha.mo7301a(C0784d.m1314f(this.mBinding));
        this.mHost.invalidateActionBar();
        this.f1839Sa = C0784d.m1314f(this.f1828Ha.mo7295Qb());
        ((C0889A) this.f1839Sa.getData()).mo6176a((C0942y) this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.f1830Ja == null) {
            this.f1830Ja = new C1459l();
        }
        this.f1830Ja.mo8187a(i, i2, (Runnable) null);
    }

    public boolean onBackPressed() {
        return this.f1828Ha.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mRecyclerView.getItemAnimator().endAnimations();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getResources().getDimensionPixelOffset(R.dimen.conversation_fast_fling_threshold);
        this.mAdapter = new C1162ca(getActivity(), (Cursor) null, this, new C1135D(this), new C1136E(this));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mHost.mo7371K() == null) {
            menuInflater.inflate(R.menu.conversation_menu, menu);
            C0931n nVar = (C0931n) this.mBinding.getData();
            menu.findItem(R.id.action_people_and_options).setEnabled(nVar.mo6465_e());
            ParticipantData Ye = nVar.mo6463Ye();
            boolean z = false;
            menu.findItem(R.id.action_add_contact).setVisible(Ye != null && TextUtils.isEmpty(Ye.mo6342m()));
            boolean We = nVar.mo6460We();
            menu.findItem(R.id.action_archive).setVisible(!We);
            menu.findItem(R.id.action_unarchive).setVisible(We);
            if (C1474sa.getDefault().isVoiceCapable() && nVar.mo6464Ze() != null) {
                z = true;
            }
            menu.findItem(R.id.action_call).setVisible(z);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.conversation_fragment, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(16908298);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(false);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.mo4822a((C0558ca) linearLayoutManager);
        this.mRecyclerView.mo4829a((C0594ua) new C1139H(this));
        this.mRecyclerView.mo4819a((C0543P) this.mAdapter);
        if (bundle != null) {
            this.f1833Ma = bundle.getParcelable("conversationViewState");
        }
        this.f1829Ia = inflate.findViewById(R.id.conversation_compose_divider);
        this.f1840Ta = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
        this.mRecyclerView.mo4824a(this.f1841Ua);
        C1193s.m3033d(this.mRecyclerView, C1486ya.m3860pk() ? 1 : 0);
        this.f1828Ha = (ComposeMessageView) inflate.findViewById(R.id.message_compose_view_container);
        this.f1828Ha.mo7302a(C0947h.get().mo6587K(((C0931n) this.mBinding.getData()).mo6458Ue()), (C1183n) this);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        ComposeMessageView composeMessageView = this.f1828Ha;
        if (composeMessageView != null) {
            composeMessageView.unbind();
        }
        this.mBinding.unbind();
        this.f1831Ka = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Point point;
        switch (menuItem.getItemId()) {
            case R.id.action_add_contact /*2131361833*/:
                ParticipantData Ye = ((C0931n) this.mBinding.getData()).mo6463Ye();
                C1424b.m3594t(Ye);
                String sf = Ye.mo6353sf();
                new AddContactsConfirmationDialog(getActivity(), C1426c.m3601c(Ye), sf).show();
                return true;
            case R.id.action_archive /*2131361835*/:
                ((C0931n) this.mBinding.getData()).mo6474b((C0784d) this.mBinding);
                mo6218b(this.f1831Ka);
                return true;
            case R.id.action_call /*2131361844*/:
                String Ze = ((C0931n) this.mBinding.getData()).mo6464Ze();
                C1424b.m3594t(Ze);
                View findViewById = getActivity().findViewById(R.id.action_call);
                if (findViewById != null) {
                    int[] iArr = new int[2];
                    findViewById.getLocationOnScreen(iArr);
                    point = new Point((findViewById.getWidth() / 2) + iArr[0], (findViewById.getHeight() / 2) + iArr[1]);
                } else {
                    Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
                    point = new Point(defaultDisplay.getWidth() / 2, defaultDisplay.getHeight() / 2);
                }
                C1040Ea.get().mo6959a((Context) getActivity(), Ze, point);
                return true;
            case R.id.action_delete /*2131361851*/:
                if (mo7402g()) {
                    new AlertDialog.Builder(getActivity(), R.style.BugleThemeDialog).setTitle(getResources().getQuantityString(R.plurals.delete_conversations_confirmation_dialog_title, 1)).setPositiveButton(R.string.delete_conversation_confirmation_button, new C1140I(this)).setNegativeButton(R.string.delete_conversation_decline_button, (DialogInterface.OnClickListener) null).show();
                } else {
                    mo7393a(false, (Runnable) null);
                }
                return true;
            case R.id.action_people_and_options /*2131361865*/:
                C1424b.m3592ia(((C0931n) this.mBinding.getData()).mo6465_e());
                C1040Ea.get().mo6953a(getActivity(), this.f1831Ka);
                return true;
            case R.id.action_settings /*2131361868*/:
                break;
            case R.id.action_unarchive /*2131361874*/:
                ((C0931n) this.mBinding.getData()).mo6477c(this.mBinding);
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public void onPause() {
        super.onPause();
        ComposeMessageView composeMessageView = this.f1828Ha;
        if (composeMessageView != null && !this.f1837Qa) {
            composeMessageView.mo7299Ub();
        }
        this.f1837Qa = false;
        ((C0931n) this.mBinding.getData()).mo6479cf();
        this.f1833Ma = this.mRecyclerView.getLayoutManager().onSaveInstanceState();
        C0045d.getInstance(getActivity()).unregisterReceiver(this.f1836Pa);
    }

    public void onResume() {
        super.onResume();
        MessageData messageData = this.f1832La;
        if (messageData == null) {
            this.f1828Ha.mo7319s(this.f1838Ra);
        } else {
            this.f1828Ha.mo7307b(messageData);
            this.f1832La = null;
        }
        this.f1838Ra = false;
        if (this.mHost.mo7332p()) {
            this.f1828Ha.mo7292J();
        }
        mo7424ta();
        this.mAdapter.notifyDataSetChanged();
        C0045d.getInstance(getActivity()).registerReceiver(this.f1836Pa, new IntentFilter("conversation_self_id_change"));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelable = this.f1833Ma;
        if (parcelable != null) {
            bundle.putParcelable("conversationViewState", parcelable);
        }
        this.f1828Ha.mo7311c(bundle);
    }

    /* renamed from: pa */
    public void mo7420pa() {
        if (mo7402g()) {
            getActivity();
            ((C0931n) this.mBinding.getData()).mo6468a(this.mBinding);
            mo6218b(this.f1831Ka);
            return;
        }
        mo7393a(false, (Runnable) null);
    }

    /* renamed from: qa */
    public String mo7421qa() {
        return ((C0931n) this.mBinding.getData()).mo6482qa();
    }

    /* renamed from: r */
    public void mo6223r() {
    }

    /* renamed from: ra */
    public void mo7422ra() {
        this.f1838Ra = true;
    }

    /* renamed from: sa */
    public boolean mo7423sa() {
        return this.f1828Ha.mo7320sa();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ta */
    public void mo7424ta() {
        if (this.mHost.mo7325X()) {
            ((C0931n) this.mBinding.getData()).mo6476bf();
        }
    }

    /* renamed from: u */
    public int mo7425u() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ua */
    public void mo7426ua() {
        this.f1837Qa = true;
    }

    public void updateActionBar(ActionBar actionBar) {
        ComposeMessageView composeMessageView = this.f1828Ha;
        if (composeMessageView == null || !composeMessageView.updateActionBar(actionBar)) {
            int Ea = C1037D.get().mo6936Ea();
            actionBar.setBackgroundDrawable(new ColorDrawable(Ea));
            C1486ya.m3850a(getActivity(), Ea);
            actionBar.setDisplayOptions(16);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(0);
            View customView = actionBar.getCustomView();
            if (customView == null || customView.getId() != R.id.conversation_title_container) {
                customView = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.action_bar_conversation_name, (ViewGroup) null);
                customView.setOnClickListener(new C1207z(this));
                actionBar.setCustomView(customView);
            }
            TextView textView = (TextView) customView.findViewById(R.id.conversation_title);
            String qa = mo7421qa();
            if (!TextUtils.isEmpty(qa)) {
                textView.setText(BidiFormatter.getInstance().unicodeWrap(C1486ya.m3849a(qa, textView.getPaint(), textView.getWidth(), getString(R.string.plus_one), getString(R.string.plus_n)).toString(), TextDirectionHeuristicsCompat.LTR));
                textView.setContentDescription(C0107q.m124a(getResources(), qa));
                getActivity().setTitle(qa);
            } else {
                String string = getString(R.string.app_name);
                textView.setText(string);
                getActivity().setTitle(string);
            }
            if (!this.mHost.mo6909S() || !C1486ya.m3859ok()) {
                actionBar.show();
            } else {
                actionBar.hide();
            }
        }
    }

    /* renamed from: y */
    public void mo7428y() {
        this.f1828Ha.mo7297Sb();
    }

    /* renamed from: z */
    public void mo7429z() {
        C1486ya.m3848Pa(R.string.attachment_load_failed_dialog_message);
    }

    /* renamed from: a */
    static /* synthetic */ void m2858a(C1146O o, boolean z) {
        if (o.mAdapter.getItemCount() > 0) {
            o.m2861b(o.mAdapter.getItemCount() - 1, z);
        }
    }

    /* renamed from: c */
    public void mo6219c(C0931n nVar) {
        this.mBinding.mo5929a(nVar);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo7399f(String str) {
        if (mo7402g()) {
            AlertDialog.Builder negativeButton = new AlertDialog.Builder(getActivity(), R.style.BugleThemeDialog).setTitle(R.string.delete_message_confirmation_dialog_title).setMessage(R.string.delete_message_confirmation_dialog_text).setPositiveButton(R.string.delete_message_confirmation_button, new C1197u(this, str)).setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            if (C1464na.m3755Vj()) {
                negativeButton.setOnDismissListener(new C1199v(this));
            } else {
                negativeButton.setOnCancelListener(new C1201w(this));
            }
            negativeButton.create().show();
            return;
        }
        mo7393a(false, (Runnable) null);
        this.mHost.mo7370G();
    }

    /* renamed from: g */
    public boolean mo7402g() {
        return C1486ya.m3857g();
    }

    /* renamed from: h */
    public void mo7403h(String str) {
        if (!mo7402g()) {
            mo7393a(true, (Runnable) new C1195t(this, str));
        } else if (m2870ym()) {
            ((C0931n) this.mBinding.getData()).mo6478c(this.mBinding, str);
        }
    }

    /* renamed from: b */
    private void m2861b(int i, boolean z) {
        int i2;
        if (z) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
            int findFirstVisibleItemPosition = i - linearLayoutManager.findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition > 15) {
                i2 = Math.max(0, i - 15);
            } else {
                i2 = findFirstVisibleItemPosition < -15 ? Math.min(linearLayoutManager.getItemCount() - 1, i + 15) : -1;
            }
            if (i2 != -1) {
                this.mRecyclerView.scrollToPosition(i2);
            }
            this.mRecyclerView.smoothScrollToPosition(i);
            return;
        }
        this.mRecyclerView.scrollToPosition(i);
    }

    /* renamed from: g */
    public void mo7401g(String str) {
        if (mo7402g()) {
            ((C0931n) this.mBinding.getData()).mo6475b(this.mBinding, str);
        } else {
            mo7393a(false, (Runnable) null);
        }
    }

    /* renamed from: a */
    public void mo7388a(Uri uri, Rect rect, boolean z) {
        Uri uri2;
        String str = this.f1831Ka;
        Activity activity = getActivity();
        if (z) {
            uri2 = MessagingContentProvider.m1271p(str);
        } else {
            uri2 = MessagingContentProvider.m1267l(str);
        }
        C1040Ea.get().mo6952a(activity, uri, rect, uri2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2856a(ConversationMessageView conversationMessageView, MessagePartData messagePartData) {
        this.f1834Na = conversationMessageView;
        if (this.f1834Na == null) {
            this.mAdapter.mo7480A((String) null);
            this.mHost.mo7370G();
            this.f1835Oa = null;
            return;
        }
        this.f1835Oa = messagePartData;
        this.mAdapter.mo7480A(conversationMessageView.getData().getMessageId());
        this.mHost.startActionMode(this.f1842Va);
    }

    /* renamed from: b */
    public void mo6218b(String str) {
        if (TextUtils.equals(str, this.f1831Ka)) {
            this.mHost.mo7328e();
        }
    }

    /* renamed from: b */
    public void mo6217b(C0931n nVar) {
        this.mBinding.mo5929a(nVar);
        if (((C0931n) this.mBinding.getData()).mo6465_e()) {
            this.mAdapter.mo7481e(((C0931n) this.mBinding.getData()).mo6463Ye() != null, true);
            invalidateOptionsMenu();
            this.mHost.invalidateActionBar();
            this.mRecyclerView.setVisibility(0);
            this.mHost.mo7326a(((C0931n) this.mBinding.getData()).mo6461Xe());
        }
    }

    /* renamed from: a */
    public void mo6216a(C0931n nVar, Cursor cursor, C0936s sVar, boolean z) {
        int i;
        Intent intent;
        Intent intent2;
        Cursor cursor2 = cursor;
        this.mBinding.mo5929a(nVar);
        boolean zm = m2871zm();
        int max = Math.max((this.mAdapter.getItemCount() - 1) - ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition(), 0);
        this.mAdapter.mo7481e(!nVar.mo6465_e() || nVar.mo6463Ye() != null, false);
        invalidateOptionsMenu();
        Cursor swapCursor = this.mAdapter.swapCursor(cursor2);
        if (!(cursor2 == null || swapCursor != null || this.f1833Ma == null)) {
            this.mRecyclerView.getLayoutManager().onRestoreInstanceState(this.f1833Ma);
            this.f1841Ua.mo5125f(this.mRecyclerView, 0, 0);
        }
        if (z) {
            m2861b(Math.max((this.mAdapter.getItemCount() - 1) - max, 0), false);
        } else if (sVar != null) {
            if (zm || !sVar.mo6546gg()) {
                boolean z2 = !zm;
                if (this.mAdapter.getItemCount() > 0) {
                    m2861b(this.mAdapter.getItemCount() - 1, z2);
                }
            } else if (((C0931n) this.mBinding.getData()).isFocused()) {
                C1486ya.m3852a((Context) getActivity(), getView().getRootView(), getString(R.string.in_conversation_notify_new_message_text), C1369oa.m3482a(new C1141J(this), getString(R.string.in_conversation_notify_new_message_action)), (List) null, C1376qa.m3512f(this.f1828Ha));
            }
        }
        if (cursor2 != null) {
            this.mHost.mo7329e(cursor.getCount());
            Activity activity = getActivity();
            if (activity == null || (intent2 = activity.getIntent()) == null) {
                i = -1;
            } else {
                i = intent2.getIntExtra("message_position", -1);
            }
            if (i >= 0) {
                if (Log.isLoggable("MessagingApp", 2)) {
                    C1430e.m3628v("MessagingApp", "onConversationMessagesCursorUpdated  scrollToPos: " + i + " cursorCount: " + cursor.getCount());
                }
                m2861b(i, true);
                Activity activity2 = getActivity();
                if (!(activity2 == null || (intent = activity2.getIntent()) == null)) {
                    intent.putExtra("message_position", -1);
                }
            }
        }
        this.mHost.invalidateActionBar();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2862b(ConversationMessageView conversationMessageView) {
        if (conversationMessageView != this.f1834Na) {
            C0936s data = conversationMessageView.getData();
            boolean g = mo7402g();
            if (data.mo6555og()) {
                mo7403h(data.getMessageId());
                m2856a((ConversationMessageView) null, (MessagePartData) null);
            } else if (data.mo6529Eg() && g) {
                m2856a(conversationMessageView, (MessagePartData) null);
            } else if (!data.mo6528Dg() || !g) {
                mo7393a(false, (Runnable) null);
                m2856a((ConversationMessageView) null, (MessagePartData) null);
            } else {
                mo7401g(data.getMessageId());
            }
        } else {
            m2856a((ConversationMessageView) null, (MessagePartData) null);
        }
    }

    /* renamed from: a */
    public void mo6215a(C0931n nVar) {
        this.mBinding.mo5929a(nVar);
        ConversationMessageView conversationMessageView = this.f1834Na;
        if (!(conversationMessageView == null || this.f1835Oa == null)) {
            List _f = conversationMessageView.getData().mo6534_f();
            if (_f.size() == 1) {
                this.f1835Oa = (MessagePartData) _f.get(0);
            } else if (!_f.contains(this.f1835Oa)) {
                m2856a((ConversationMessageView) null, (MessagePartData) null);
            }
        }
        invalidateOptionsMenu();
        this.mHost.mo7330k();
        this.mAdapter.notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo7387a(Context context, String str, MessageData messageData) {
        if (!this.mBinding.isBound()) {
            this.f1831Ka = str;
            this.f1832La = messageData;
            this.mBinding.mo5930b(C0947h.get().mo6602a(context, (C0924g) this, str));
            return;
        }
        C1424b.m3592ia(TextUtils.equals(((C0931n) this.mBinding.getData()).mo6458Ue(), str));
    }

    /* renamed from: a */
    public void mo7389a(MessageData messageData) {
        if (!mo7402g()) {
            mo7393a(true, (Runnable) new C1142K(this, messageData));
        } else if (m2870ym()) {
            messageData.mo6248Sg();
            ((C0931n) this.mBinding.getData()).mo6469a((C0784d) this.mBinding, messageData);
            this.f1828Ha.mo7297Sb();
        } else {
            C1430e.m3630w("MessagingApp", "Message can't be sent: conv participants not loaded");
        }
    }

    /* renamed from: a */
    public void mo7391a(C1144M m) {
        this.mHost = m;
    }

    /* renamed from: a */
    public void mo7393a(boolean z, Runnable runnable) {
        if (this.f1830Ja == null) {
            this.f1830Ja = new C1459l();
        }
        this.f1830Ja.mo8188a(z, runnable, this.f1828Ha, getView().getRootView(), getActivity(), this);
    }

    /* renamed from: a */
    public boolean mo7395a(ConversationMessageView conversationMessageView, MessagePartData messagePartData, Rect rect, boolean z) {
        if (z) {
            m2856a(conversationMessageView, messagePartData);
            return true;
        } else if (conversationMessageView.getData().mo6555og()) {
            m2862b(conversationMessageView);
            return true;
        } else {
            if (messagePartData.mo6304fh()) {
                mo7388a(messagePartData.getContentUri(), rect, false);
            }
            if (messagePartData.mo6314hh()) {
                C1040Ea.get().mo6976f(getActivity(), messagePartData.getContentUri());
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo7392a(boolean z) {
        setHasOptionsMenu(z);
    }

    /* renamed from: a */
    public void mo7390a(C0917ba baVar) {
        this.f1828Ha.mo7303a(baVar);
        this.mHost.mo7324I();
    }

    /* renamed from: a */
    public C0917ba mo7386a(String str, boolean z) {
        return ((C0931n) this.mBinding.getData()).mo6466a(str, z);
    }

    /* renamed from: a */
    public void mo7394a(boolean z, boolean z2) {
        ComposeMessageView composeMessageView = this.f1828Ha;
        String str = this.f1831Ka;
        Activity activity = getActivity();
        AlertDialog.Builder title = new AlertDialog.Builder(activity, R.style.BugleThemeDialog).setTitle(R.string.mms_attachment_limit_reached);
        if (z) {
            if (z2) {
                title.setMessage(R.string.video_attachment_limit_exceeded_when_sending);
            } else {
                title.setMessage(R.string.attachment_limit_reached_dialog_message_when_sending).setNegativeButton(R.string.attachment_limit_reached_send_anyway, new C1203x(composeMessageView));
            }
            title.setPositiveButton(17039370, new C1205y(str, activity));
        } else {
            title.setMessage(R.string.attachment_limit_reached_dialog_message_when_composing).setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        }
        title.show();
    }

    /* renamed from: a */
    public static void m2859a(String str, Activity activity) {
        C1040Ea.get().mo6954a(activity, str, 2);
    }

    /* renamed from: a */
    public void mo6222a(C0889A a, int i) {
        this.f1839Sa.mo5929a(a);
        if (i == 257) {
            this.f1838Ra = true;
        }
    }
}

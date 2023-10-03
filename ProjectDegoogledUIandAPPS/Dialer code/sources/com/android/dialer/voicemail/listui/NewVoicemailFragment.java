package com.android.dialer.voicemail.listui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.content.Loader;
import android.support.p000v4.content.LocalBroadcastManager;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.dialer.R;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.RefreshAnnotatedCallLogReceiver;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.voicemail.listui.error.VoicemailStatus;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.Callable;

public final class NewVoicemailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private EmptyContentView emptyContentView;
    FrameLayout fragmentRootFrameLayout;
    private UiListener<ImmutableList<VoicemailStatus>> queryVoicemailStatusTableListener;
    private RecyclerView recyclerView;
    private RefreshAnnotatedCallLogReceiver refreshAnnotatedCallLogReceiver;

    public NewVoicemailFragment() {
        LogUtil.enterBlock("NewVoicemailFragment.NewVoicemailFragment");
    }

    static /* synthetic */ void lambda$queryAndUpdateVoicemailStatusAlert$0(Throwable th) {
        throw new RuntimeException(th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x009c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009d, code lost:
        if (r0 != null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a7, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ com.google.common.collect.ImmutableList lambda$queryVoicemailStatus$1(android.content.Context r9) throws java.lang.Exception {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.android.voicemail.VoicemailComponent r2 = com.android.voicemail.VoicemailComponent.get(r9)
            com.android.voicemail.VoicemailClient r2 = r2.getVoicemailClient()
            r2.appendOmtpVoicemailStatusSelectionClause(r9, r0, r1)
            com.google.common.collect.ImmutableList$Builder r2 = com.google.common.collect.ImmutableList.builder()
            android.content.ContentResolver r3 = r9.getContentResolver()
            android.net.Uri r4 = android.provider.VoicemailContract.Status.CONTENT_URI
            java.lang.String[] r5 = com.android.dialer.voicemailstatus.VoicemailStatusQuery.getProjection()
            java.lang.String r6 = r0.toString()
            int r0 = r1.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            r7 = r0
            java.lang.String[] r7 = (java.lang.String[]) r7
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)
            r1 = 0
            java.lang.String r3 = "NewVoicemailFragment.queryVoicemailStatus"
            if (r0 != 0) goto L_0x004f
            java.lang.String r9 = "query failed. Null cursor."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x009a }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r3, (java.lang.String) r9, (java.lang.Object[]) r1)     // Catch:{ all -> 0x009a }
            com.google.common.collect.ImmutableList r9 = r2.build()     // Catch:{ all -> 0x009a }
            if (r0 == 0) goto L_0x004e
            r0.close()
        L_0x004e:
            return r9
        L_0x004f:
            java.lang.String r4 = "cursor size:%d "
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x009a }
            int r7 = r0.getCount()     // Catch:{ all -> 0x009a }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x009a }
            r6[r1] = r7     // Catch:{ all -> 0x009a }
            com.android.dialer.common.LogUtil.m9i(r3, r4, r6)     // Catch:{ all -> 0x009a }
        L_0x0061:
            boolean r4 = r0.moveToNext()     // Catch:{ all -> 0x009a }
            if (r4 == 0) goto L_0x007d
            com.android.dialer.voicemail.listui.error.VoicemailStatus r4 = new com.android.dialer.voicemail.listui.error.VoicemailStatus     // Catch:{ all -> 0x009a }
            r4.<init>(r9, r0)     // Catch:{ all -> 0x009a }
            boolean r6 = r4.isActive(r9)     // Catch:{ all -> 0x009a }
            if (r6 == 0) goto L_0x0061
            java.lang.String r6 = "inactive source ignored"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x009a }
            com.android.dialer.common.LogUtil.m9i(r3, r6, r7)     // Catch:{ all -> 0x009a }
            r2.add((java.lang.Object) r4)     // Catch:{ all -> 0x009a }
            goto L_0x0061
        L_0x007d:
            r0.close()
            java.lang.Object[] r9 = new java.lang.Object[r5]
            com.google.common.collect.ImmutableList r0 = r2.build()
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9[r1] = r0
            java.lang.String r0 = "query returned %d results"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r9)
            com.google.common.collect.ImmutableList r9 = r2.build()
            return r9
        L_0x009a:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x009c }
        L_0x009c:
            r1 = move-exception
            if (r0 == 0) goto L_0x00a7
            r0.close()     // Catch:{ all -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x00a7:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.NewVoicemailFragment.lambda$queryVoicemailStatus$1(android.content.Context):com.google.common.collect.ImmutableList");
    }

    private void onFragmentHidden() {
        LogUtil.enterBlock("NewVoicemailFragment.unregisterRefreshAnnotatedCallLogReceiver");
        CallLogComponent.get(getContext()).getRefreshAnnotatedCallLogNotifier().cancel();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.refreshAnnotatedCallLogReceiver);
    }

    private void onFragmentShown() {
        LogUtil.enterBlock("NewVoicemailFragment.registerRefreshAnnotatedCallLogReceiver");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.refreshAnnotatedCallLogReceiver, RefreshAnnotatedCallLogReceiver.getIntentFilter());
        CallLogComponent.get(getContext()).getRefreshAnnotatedCallLogNotifier().notify(true);
    }

    private void showView(View view) {
        int i = 0;
        LogUtil.m9i("NewVoicemailFragment.showView", GeneratedOutlineSupport.outline6("Showing view: ", view), new Object[0]);
        EmptyContentView emptyContentView2 = this.emptyContentView;
        emptyContentView2.setVisibility(view == emptyContentView2 ? 0 : 8);
        RecyclerView recyclerView2 = this.recyclerView;
        if (view != recyclerView2) {
            i = 8;
        }
        recyclerView2.setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void updateVoicemailStatusAlert(ImmutableList<VoicemailStatus> immutableList) {
        ((NewVoicemailAdapter) this.recyclerView.getAdapter()).updateVoicemailAlertWithMostRecentStatus(getContext(), immutableList);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LogUtil.enterBlock("NewVoicemailFragment.onActivityCreated");
        this.refreshAnnotatedCallLogReceiver = new RefreshAnnotatedCallLogReceiver(getContext());
        this.queryVoicemailStatusTableListener = DialerExecutorComponent.get(getContext()).createUiListener(getActivity().getFragmentManager(), "NewVoicemailFragment.queryVoicemailStatusTable");
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        LogUtil.enterBlock("NewVoicemailFragment.onCreateLoader");
        return new VoicemailCursorLoader(getContext());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.enterBlock("NewVoicemailFragment.onCreateView");
        this.fragmentRootFrameLayout = (FrameLayout) layoutInflater.inflate(R.layout.new_voicemail_call_log_fragment, viewGroup, false);
        this.recyclerView = (RecyclerView) this.fragmentRootFrameLayout.findViewById(R.id.new_voicemail_call_log_recycler_view);
        this.emptyContentView = (EmptyContentView) this.fragmentRootFrameLayout.findViewById(R.id.empty_content_view);
        LoaderManager.getInstance(this).restartLoader(0, (Bundle) null, this);
        return this.fragmentRootFrameLayout;
    }

    public void onHiddenChanged(boolean z) {
        LogUtil.m9i("NewVoicemailFragment.onHiddenChanged", "hidden = %s", Boolean.valueOf(z));
        if (z) {
            onFragmentHidden();
        } else {
            onFragmentShown();
        }
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        LogUtil.m9i("NewVoicemailFragment.onLoadFinished", "cursor size is %d", Integer.valueOf(cursor.getCount()));
        if (cursor.getCount() == 0) {
            LogUtil.enterBlock("NewVoicemailFragment.showEmptyVoicemailFragmentView");
            showView(this.emptyContentView);
            this.emptyContentView.setDescription(R.string.empty_voicemail_tab_text);
            this.emptyContentView.setImage(R.drawable.quantum_ic_voicemail_vd_theme_24);
            return;
        }
        showView(this.recyclerView);
        if (this.recyclerView.getAdapter() == null) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            this.recyclerView.setAdapter(new NewVoicemailAdapter(cursor, $$Lambda$RAnRraZd4HLErqzt4JoHEBlfQ.INSTANCE, getActivity().getFragmentManager()));
            return;
        }
        LogUtil.m9i("NewVoicemailFragment.onLoadFinished", "adapter: %s was not null, checking and playing the voicemail if conditions met", this.recyclerView.getAdapter());
        ((NewVoicemailAdapter) this.recyclerView.getAdapter()).updateCursor(cursor);
        ((NewVoicemailAdapter) this.recyclerView.getAdapter()).checkAndPlayVoicemail();
        UiListener<ImmutableList<VoicemailStatus>> uiListener = this.queryVoicemailStatusTableListener;
        Context context = getContext();
        Context context2 = getContext();
        uiListener.listen(context, DialerExecutorComponent.get(context2).backgroundExecutor().submit(new Callable(context2) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Object call() {
                return NewVoicemailFragment.lambda$queryVoicemailStatus$1(this.f$0);
            }
        }), new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                NewVoicemailFragment.this.updateVoicemailStatusAlert((ImmutableList) obj);
            }
        }, $$Lambda$NewVoicemailFragment$tYlavILKq89007P79Eemrc0vIWQ.INSTANCE);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        LogUtil.enterBlock("NewVoicemailFragment.onLoaderReset");
        this.recyclerView.setAdapter((RecyclerView.Adapter) null);
    }

    public void onPause() {
        super.onPause();
        LogUtil.enterBlock("NewVoicemailFragment.onPause");
        onFragmentHidden();
    }

    public void onResume() {
        super.onResume();
        boolean isHidden = isHidden();
        LogUtil.m9i("NewVoicemailFragment.onResume", "isHidden = %s", Boolean.valueOf(isHidden));
        if (!isHidden) {
            onFragmentShown();
        }
    }

    public void onStart() {
        super.onStart();
        LogUtil.enterBlock("NewVoicemailFragment.onStart");
    }
}

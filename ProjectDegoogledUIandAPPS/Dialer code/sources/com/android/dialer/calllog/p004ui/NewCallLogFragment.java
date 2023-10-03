package com.android.dialer.calllog.p004ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.content.Loader;
import android.support.p000v4.content.LocalBroadcastManager;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.RefreshAnnotatedCallLogReceiver;
import com.android.dialer.calllog.database.CallLogDatabaseComponent;
import com.android.dialer.calllog.database.Coalescer;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.common.concurrent.SupportUiListener;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.jank.RecyclerViewJankLogger;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.dialer.calllog.ui.NewCallLogFragment */
public final class NewCallLogFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    static final long MARK_ALL_CALLS_READ_WAIT_MILLIS = TimeUnit.SECONDS.toMillis(3);
    private SupportUiListener<ImmutableList<CoalescedRow>> coalesingAnnotatedCallLogListener;
    private EmptyContentView emptyContentView;
    private RecyclerView recyclerView;
    private RefreshAnnotatedCallLogReceiver refreshAnnotatedCallLogReceiver;
    private final Runnable setShouldMarkCallsReadTrue = new Runnable() {
        public final void run() {
            NewCallLogFragment.this.lambda$new$0$NewCallLogFragment();
        }
    };
    private boolean shouldMarkCallsRead = false;

    /* renamed from: com.android.dialer.calllog.ui.NewCallLogFragment$TurnOnPhonePermissions */
    private class TurnOnPhonePermissions implements EmptyContentView.OnEmptyViewActionButtonClickedListener {
        /* synthetic */ TurnOnPhonePermissions(C04391 r2) {
        }

        public void onEmptyViewActionButtonClicked() {
            if (NewCallLogFragment.this.getContext() == null) {
                LogUtil.m10w("TurnOnPhonePermissions.onEmptyViewActionButtonClicked", "no context", new Object[0]);
                return;
            }
            String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(NewCallLogFragment.this.getContext(), PermissionsUtil.allPhoneGroupPermissionsUsedInDialer);
            if (permissionsCurrentlyDenied.length > 0) {
                LogUtil.m9i("TurnOnPhonePermissions.onEmptyViewActionButtonClicked", "requesting permissions: %s", Arrays.toString(permissionsCurrentlyDenied));
                NewCallLogFragment.this.requestPermissions(permissionsCurrentlyDenied, 1);
            }
        }
    }

    public NewCallLogFragment() {
        LogUtil.enterBlock("NewCallLogFragment.NewCallLogFragment");
    }

    static /* synthetic */ void lambda$onLoadFinished$2(Throwable th) {
        if (!(th instanceof Coalescer.ExpectedCoalescerException)) {
            throw new AssertionError(th);
        }
    }

    private void onFragmentHidden() {
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.setShouldMarkCallsReadTrue);
        LogUtil.enterBlock("NewCallLogFragment.unregisterRefreshAnnotatedCallLogReceiver");
        CallLogComponent.get(getContext()).getRefreshAnnotatedCallLogNotifier().cancel();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.refreshAnnotatedCallLogReceiver);
        if (this.shouldMarkCallsRead) {
            Futures.addCallback(CallLogComponent.get(getContext()).getClearMissedCalls().clearAll(), new DefaultFutureCallback(), MoreExecutors.directExecutor());
        }
    }

    private void onFragmentShown() {
        LoaderManager instance = LoaderManager.getInstance(this);
        if (!PermissionsUtil.hasCallLogReadPermissions(getContext())) {
            this.recyclerView.setVisibility(8);
            this.emptyContentView.setVisibility(0);
            instance.destroyLoader(0);
            return;
        }
        this.recyclerView.setVisibility(0);
        this.emptyContentView.setVisibility(8);
        if (instance.getLoader(0) == null) {
            instance.restartLoader(0, (Bundle) null, this);
        }
        LogUtil.enterBlock("NewCallLogFragment.registerRefreshAnnotatedCallLogReceiver");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.refreshAnnotatedCallLogReceiver, RefreshAnnotatedCallLogReceiver.getIntentFilter());
        CallLogComponent.get(getContext()).getRefreshAnnotatedCallLogNotifier().notify(true);
        if (this.recyclerView.getAdapter() != null) {
            ((NewCallLogAdapter) this.recyclerView.getAdapter()).clearCache();
            this.recyclerView.getAdapter().notifyDataSetChanged();
        }
        this.shouldMarkCallsRead = false;
        DialerExecutorModule.getUiThreadHandler().postDelayed(this.setShouldMarkCallsReadTrue, MARK_ALL_CALLS_READ_WAIT_MILLIS);
    }

    public /* synthetic */ void lambda$new$0$NewCallLogFragment() {
        this.shouldMarkCallsRead = true;
    }

    public /* synthetic */ void lambda$onLoadFinished$1$NewCallLogFragment(ImmutableList immutableList) {
        LogUtil.m9i("NewCallLogFragment.onLoadFinished", "coalescing succeeded", new Object[0]);
        if (this.recyclerView.getAdapter() == null) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FragmentActivity activity = getActivity();
            Assert.isNotNull(activity);
            this.recyclerView.setAdapter(new NewCallLogAdapter(activity, immutableList, $$Lambda$RAnRraZd4HLErqzt4JoHEBlfQ.INSTANCE, ((DaggerAospDialerRootComponent) ((HasRootComponent) getContext().getApplicationContext()).component()).promotionComponent().promotionManager().getHighestPriorityPromotion(1).orElse((Object) null)));
            return;
        }
        ((NewCallLogAdapter) this.recyclerView.getAdapter()).updateRows(immutableList);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LogUtil.enterBlock("NewCallLogFragment.onActivityCreated");
        this.refreshAnnotatedCallLogReceiver = new RefreshAnnotatedCallLogReceiver(getContext());
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        LogUtil.enterBlock("NewCallLogFragment.onCreateLoader");
        Context context = getContext();
        Assert.isNotNull(context);
        return new AnnotatedCallLogCursorLoader(context);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.enterBlock("NewCallLogFragment.onCreateView");
        View inflate = layoutInflater.inflate(R.layout.new_call_log_fragment, viewGroup, false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.new_call_log_recycler_view);
        this.recyclerView.addOnScrollListener(new RecyclerViewJankLogger(MetricsComponent.get(getContext()).metrics(), "NewCallLog.Jank"));
        this.emptyContentView = (EmptyContentView) inflate.findViewById(R.id.new_call_log_empty_content_view);
        this.emptyContentView.setImage(R.drawable.quantum_ic_query_builder_vd_theme_24);
        this.emptyContentView.setDescription(R.string.new_call_log_permission_no_calllog);
        this.emptyContentView.setActionLabel(R.string.permission_single_turn_on);
        this.emptyContentView.setActionClickedListener(new TurnOnPhonePermissions((C04391) null));
        this.coalesingAnnotatedCallLogListener = DialerExecutorComponent.get(getContext()).createUiListener(getChildFragmentManager(), "NewCallLogFragment.coalescingAnnotatedCallLog");
        if (PermissionsUtil.hasCallLogReadPermissions(getContext())) {
            LoaderManager.getInstance(this).restartLoader(0, (Bundle) null, this);
        }
        return inflate;
    }

    public void onHiddenChanged(boolean z) {
        LogUtil.m9i("NewCallLogFragment.onHiddenChanged", "hidden = %s", Boolean.valueOf(z));
        if (z) {
            onFragmentHidden();
        } else {
            onFragmentShown();
        }
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        LogUtil.enterBlock("NewCallLogFragment.onLoadFinished");
        if (cursor == null) {
            LogUtil.m10w("NewCallLogFragment.onLoadFinished", "null cursor", new Object[0]);
            return;
        }
        this.coalesingAnnotatedCallLogListener.listen(getContext(), CallLogDatabaseComponent.get(getContext()).coalescer().coalesce(cursor), new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                NewCallLogFragment.this.lambda$onLoadFinished$1$NewCallLogFragment((ImmutableList) obj);
            }
        }, $$Lambda$NewCallLogFragment$TwUKiv4Sti_JVx8fgjpTrkH3fYU.INSTANCE);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        LogUtil.enterBlock("NewCallLogFragment.onLoaderReset");
        this.recyclerView.setAdapter((RecyclerView.Adapter) null);
    }

    public void onPause() {
        super.onPause();
        LogUtil.enterBlock("NewCallLogFragment.onPause");
        onFragmentHidden();
    }

    public void onResume() {
        super.onResume();
        boolean isHidden = isHidden();
        LogUtil.m9i("NewCallLogFragment.onResume", "isHidden = %s", Boolean.valueOf(isHidden));
        if (!isHidden) {
            onFragmentShown();
        }
    }

    public void onStart() {
        super.onStart();
        LogUtil.enterBlock("NewCallLogFragment.onStart");
    }

    public void onStop() {
        super.onStop();
        if (this.recyclerView.getAdapter() != null) {
            ((NewCallLogAdapter) this.recyclerView.getAdapter()).logMetrics(getContext());
        }
    }
}

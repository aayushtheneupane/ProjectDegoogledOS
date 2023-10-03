package com.android.dialer.precall.impl;

import android.content.Context;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.android.dialer.precall.impl.PreCallCoordinatorImpl;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

public class DuoAction implements PreCallAction {
    private final ListeningExecutorService uiExecutor;

    DuoAction(ListeningExecutorService listeningExecutorService) {
        this.uiExecutor = listeningExecutorService;
    }

    public void onDiscard() {
    }

    public boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder) {
        return callIntentBuilder.isDuoCall();
    }

    public void runWithUi(PreCallCoordinator preCallCoordinator) {
        final PreCallCoordinatorImpl preCallCoordinatorImpl = (PreCallCoordinatorImpl) preCallCoordinator;
        preCallCoordinatorImpl.getActivity();
        if (preCallCoordinatorImpl.getBuilder().isDuoCall()) {
            final String schemeSpecificPart = preCallCoordinatorImpl.getBuilder().getUri().getSchemeSpecificPart();
            ListenableFuture<ImmutableMap<String, Duo.ReachabilityData>> updateReachability = ((DuoStub) DuoComponent.get(preCallCoordinatorImpl.getActivity()).getDuo()).updateReachability(preCallCoordinatorImpl.getActivity(), ImmutableList.m75of(schemeSpecificPart));
            final PreCallCoordinator.PendingAction startPendingAction = preCallCoordinatorImpl.startPendingAction();
            Futures.addCallback(updateReachability, new FutureCallback<ImmutableMap<String, Duo.ReachabilityData>>(this) {
                public void onFailure(Throwable th) {
                    LogUtil.m7e("DuoAction.runWithUi", "reachability query failed", th);
                    ((PreCallCoordinatorImpl) preCallCoordinatorImpl).getBuilder().setIsDuoCall(false);
                    ((PreCallCoordinatorImpl.PendingActionImpl) startPendingAction).finish();
                }

                public void onSuccess(Object obj) {
                    ImmutableMap immutableMap = (ImmutableMap) obj;
                    if (!immutableMap.containsKey(schemeSpecificPart) || !((Duo.ReachabilityData) immutableMap.get(schemeSpecificPart)).videoCallable()) {
                        LogUtil.m10w("DuoAction.runWithUi", GeneratedOutlineSupport.outline12(new StringBuilder(), schemeSpecificPart, " number no longer duo reachable, falling back to carrier video call"), new Object[0]);
                        ((PreCallCoordinatorImpl) preCallCoordinatorImpl).getBuilder().setIsDuoCall(false);
                    }
                    ((PreCallCoordinatorImpl.PendingActionImpl) startPendingAction).finish();
                }
            }, this.uiExecutor);
        }
    }

    public void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder) {
    }
}

package com.android.dialer.precall.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.function.Consumer;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.android.dialer.telecom.TelecomUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Objects;

public class PreCallCoordinatorImpl implements PreCallCoordinator {
    private boolean aborted = false;
    private ImmutableList<PreCallAction> actions;
    private final Activity activity;
    private CallIntentBuilder builder;
    private PreCallAction currentAction;
    private int currentActionIndex = 0;
    /* access modifiers changed from: private */
    public PreCallCoordinator.PendingAction pendingAction;
    private UiListener<Object> uiListener;

    private class PendingActionImpl implements PreCallCoordinator.PendingAction {
        /* synthetic */ PendingActionImpl(C05521 r2) {
        }

        public void finish() {
            Assert.checkArgument(PreCallCoordinatorImpl.this.pendingAction == this);
            PreCallCoordinator.PendingAction unused = PreCallCoordinatorImpl.this.pendingAction = null;
            PreCallCoordinatorImpl.this.onActionFinished();
        }
    }

    PreCallCoordinatorImpl(Activity activity2) {
        Assert.isNotNull(activity2);
        this.activity = activity2;
    }

    /* access modifiers changed from: private */
    public void onActionFinished() {
        LogUtil.enterBlock("PreCallCoordinatorImpl.onActionFinished");
        Assert.isNotNull(this.currentAction);
        this.currentAction = null;
        this.currentActionIndex++;
        if (!this.aborted) {
            runNextAction();
        } else {
            this.activity.finish();
        }
    }

    private void runNextAction() {
        LogUtil.enterBlock("PreCallCoordinatorImpl.runNextAction");
        Assert.checkArgument(this.currentAction == null);
        if (this.currentActionIndex >= this.actions.size()) {
            if (this.builder.isDuoCall()) {
                Optional<Intent> callIntent = ((DuoStub) DuoComponent.get(this.activity).getDuo()).getCallIntent(this.builder.getUri().getSchemeSpecificPart());
                if (callIntent.isPresent()) {
                    this.activity.startActivityForResult(callIntent.get(), 0);
                    this.activity.finish();
                    return;
                }
                LogUtil.m8e("PreCallCoordinatorImpl.placeCall", "duo.getCallIntent() returned absent", new Object[0]);
            }
            TelecomUtil.placeCall(this.activity, this.builder.build());
            this.activity.finish();
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("running ");
        outline13.append(this.actions.get(this.currentActionIndex));
        LogUtil.m9i("PreCallCoordinatorImpl.runNextAction", outline13.toString(), new Object[0]);
        this.currentAction = this.actions.get(this.currentActionIndex);
        this.actions.get(this.currentActionIndex).runWithUi(this);
        if (this.pendingAction == null) {
            onActionFinished();
        }
    }

    public void abortCall() {
        Assert.checkState(this.currentAction != null);
        this.aborted = true;
        ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.PRECALL_CANCELED);
    }

    public Activity getActivity() {
        return this.activity;
    }

    public CallIntentBuilder getBuilder() {
        return this.builder;
    }

    public <OutputT> void listen(ListenableFuture<OutputT> listenableFuture, Consumer<OutputT> consumer, Consumer<Throwable> consumer2) {
        UiListener<Object> uiListener2 = this.uiListener;
        Activity activity2 = this.activity;
        ListenableFuture<O> transform = Futures.transform(listenableFuture, $$Lambda$PreCallCoordinatorImpl$kz22exviI3DbydXHjrCjwWi2S8o.INSTANCE, MoreExecutors.directExecutor());
        $$Lambda$PreCallCoordinatorImpl$HDsAxGDJOk6qQCG9Vzg1RFesxw r1 = new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                Consumer.this.accept(obj);
            }
        };
        Objects.requireNonNull(consumer2);
        uiListener2.listen(activity2, transform, r1, new DialerExecutor.FailureListener() {
            public final void onFailure(Throwable th) {
                Consumer.this.accept(th);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onCreate(Intent intent, Bundle bundle) {
        LogUtil.enterBlock("PreCallCoordinatorImpl.onCreate");
        if (bundle != null) {
            this.currentActionIndex = bundle.getInt("current_action");
            CallIntentBuilder callIntentBuilder = (CallIntentBuilder) bundle.getParcelable(PreCallCoordinator.EXTRA_CALL_INTENT_BUILDER);
            Assert.isNotNull(callIntentBuilder);
            this.builder = callIntentBuilder;
        } else {
            CallIntentBuilder callIntentBuilder2 = (CallIntentBuilder) intent.getParcelableExtra(PreCallCoordinator.EXTRA_CALL_INTENT_BUILDER);
            Assert.isNotNull(callIntentBuilder2);
            this.builder = callIntentBuilder2;
        }
        this.uiListener = DialerExecutorComponent.get(this.activity).createUiListener(this.activity.getFragmentManager(), "PreCallCoordinatorImpl.uiListener");
    }

    /* access modifiers changed from: package-private */
    public void onPause() {
        PreCallAction preCallAction = this.currentAction;
        if (preCallAction != null) {
            preCallAction.onDiscard();
        }
        this.currentAction = null;
        this.pendingAction = null;
    }

    /* access modifiers changed from: package-private */
    public void onRestoreInstanceState(Bundle bundle) {
        this.currentActionIndex = bundle.getInt("current_action");
        this.builder = (CallIntentBuilder) bundle.getParcelable(PreCallCoordinator.EXTRA_CALL_INTENT_BUILDER);
    }

    /* access modifiers changed from: package-private */
    public void onResume() {
        this.actions = ((DaggerAospDialerRootComponent) ((HasRootComponent) this.activity.getApplicationContext()).component()).preCallActionsComponent().createActions();
        runNextAction();
    }

    /* access modifiers changed from: package-private */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("current_action", this.currentActionIndex);
        bundle.putParcelable(PreCallCoordinator.EXTRA_CALL_INTENT_BUILDER, this.builder);
    }

    public PreCallCoordinator.PendingAction startPendingAction() {
        Assert.isMainThread();
        Assert.isNotNull(this.currentAction);
        Assert.checkArgument(this.pendingAction == null);
        this.pendingAction = new PendingActionImpl((C05521) null);
        return this.pendingAction;
    }
}

package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.ImageMessageConsumer;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.MediaNotificationProcessor;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.InflatedSmartReplies;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.util.Assert;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class NotificationContentInflater {
    private final ArrayMap<Integer, RemoteViews> mCachedContentViews = new ArrayMap<>();
    private InflationCallback mCallback;
    private boolean mInflateSynchronously = false;
    private int mInflationFlags = 3;
    private boolean mIsChildInGroup;
    private boolean mIsLowPriority;
    private RemoteViews.OnClickHandler mRemoteViewClickHandler;
    private final ExpandableNotificationRow mRow;
    private boolean mUsesIncreasedHeadsUpHeight;
    private boolean mUsesIncreasedHeight;

    public interface InflationCallback {
        void handleInflationException(StatusBarNotification statusBarNotification, Exception exc);

        void onAsyncInflationFinished(NotificationEntry notificationEntry, int i);
    }

    public NotificationContentInflater(ExpandableNotificationRow expandableNotificationRow) {
        this.mRow = expandableNotificationRow;
    }

    public void setIsLowPriority(boolean z) {
        this.mIsLowPriority = z;
    }

    public void setIsChildInGroup(boolean z) {
        if (z != this.mIsChildInGroup) {
            this.mIsChildInGroup = z;
            if (this.mIsLowPriority) {
                inflateNotificationViews(3);
            }
        }
    }

    public void setUsesIncreasedHeight(boolean z) {
        this.mUsesIncreasedHeight = z;
    }

    public void setUsesIncreasedHeadsUpHeight(boolean z) {
        this.mUsesIncreasedHeadsUpHeight = z;
    }

    public void setRemoteViewClickHandler(RemoteViews.OnClickHandler onClickHandler) {
        this.mRemoteViewClickHandler = onClickHandler;
    }

    public void updateNeedsRedaction(boolean z) {
        if (this.mRow.getEntry() != null && z) {
            inflateNotificationViews(8);
        }
    }

    public void updateInflationFlag(int i, boolean z) {
        if (z) {
            this.mInflationFlags = i | this.mInflationFlags;
        } else if ((i & 3) == 0) {
            this.mInflationFlags = (~i) & this.mInflationFlags;
        }
    }

    @VisibleForTesting
    public void addInflationFlags(int i) {
        this.mInflationFlags = i | this.mInflationFlags;
    }

    public boolean isInflationFlagSet(int i) {
        return (this.mInflationFlags & i) != 0;
    }

    public void inflateNotificationViews() {
        inflateNotificationViews(this.mInflationFlags);
    }

    private void inflateNotificationViews(int i) {
        if (!this.mRow.isRemoved()) {
            int i2 = i & this.mInflationFlags;
            StatusBarNotification statusBarNotification = this.mRow.getEntry().notification;
            this.mRow.getImageResolver().preloadImages(statusBarNotification.getNotification());
            AsyncInflationTask asyncInflationTask = new AsyncInflationTask(statusBarNotification, this.mInflateSynchronously, i2, this.mCachedContentViews, this.mRow, this.mIsLowPriority, this.mIsChildInGroup, this.mUsesIncreasedHeight, this.mUsesIncreasedHeadsUpHeight, this.mCallback, this.mRemoteViewClickHandler);
            if (this.mInflateSynchronously) {
                asyncInflationTask.onPostExecute(asyncInflationTask.doInBackground(new Void[0]));
            } else {
                asyncInflationTask.execute(new Void[0]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public InflationProgress inflateNotificationViews(boolean z, int i, Notification.Builder builder, Context context) {
        InflationProgress createRemoteViews = createRemoteViews(i, builder, this.mIsLowPriority, this.mIsChildInGroup, this.mUsesIncreasedHeight, this.mUsesIncreasedHeadsUpHeight, context);
        inflateSmartReplyViews(createRemoteViews, i, this.mRow.getEntry(), this.mRow.getContext(), context, this.mRow.getHeadsUpManager(), this.mRow.getExistingSmartRepliesAndActions());
        apply(z, createRemoteViews, i, this.mCachedContentViews, this.mRow, this.mRemoteViewClickHandler, (InflationCallback) null);
        return createRemoteViews;
    }

    public void freeNotificationView(int i) {
        if ((this.mInflationFlags & i) == 0) {
            if (i != 4) {
                if (i == 8 && this.mRow.getPublicLayout().isContentViewInactive(0)) {
                    this.mRow.getPublicLayout().setContractedChild((View) null);
                    this.mCachedContentViews.remove(8);
                }
            } else if (this.mRow.getPrivateLayout().isContentViewInactive(2)) {
                this.mRow.getPrivateLayout().setHeadsUpChild((View) null);
                this.mCachedContentViews.remove(4);
                this.mRow.getPrivateLayout().setHeadsUpInflatedSmartReplies((InflatedSmartReplies) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public static InflationProgress inflateSmartReplyViews(InflationProgress inflationProgress, int i, NotificationEntry notificationEntry, Context context, Context context2, HeadsUpManager headsUpManager, InflatedSmartReplies.SmartRepliesAndActions smartRepliesAndActions) {
        SmartReplyConstants smartReplyConstants = (SmartReplyConstants) Dependency.get(SmartReplyConstants.class);
        SmartReplyController smartReplyController = (SmartReplyController) Dependency.get(SmartReplyController.class);
        if (!((i & 2) == 0 || inflationProgress.newExpandedView == null)) {
            InflatedSmartReplies unused = inflationProgress.expandedInflatedSmartReplies = InflatedSmartReplies.inflate(context, context2, notificationEntry, smartReplyConstants, smartReplyController, headsUpManager, smartRepliesAndActions);
        }
        if (!((i & 4) == 0 || inflationProgress.newHeadsUpView == null)) {
            InflatedSmartReplies unused2 = inflationProgress.headsUpInflatedSmartReplies = InflatedSmartReplies.inflate(context, context2, notificationEntry, smartReplyConstants, smartReplyController, headsUpManager, smartRepliesAndActions);
        }
        return inflationProgress;
    }

    /* access modifiers changed from: private */
    public static InflationProgress createRemoteViews(int i, Notification.Builder builder, boolean z, boolean z2, boolean z3, boolean z4, Context context) {
        InflationProgress inflationProgress = new InflationProgress();
        boolean z5 = z && !z2;
        if ((i & 1) != 0) {
            RemoteViews unused = inflationProgress.newContentView = createContentView(builder, z5, z3);
        }
        if ((i & 2) != 0) {
            RemoteViews unused2 = inflationProgress.newExpandedView = createExpandedView(builder, z5);
        }
        if ((i & 4) != 0) {
            RemoteViews unused3 = inflationProgress.newHeadsUpView = builder.createHeadsUpContentView(z4);
        }
        if ((i & 8) != 0) {
            RemoteViews unused4 = inflationProgress.newPublicView = builder.makePublicContentView(z5);
        }
        inflationProgress.packageContext = context;
        CharSequence unused5 = inflationProgress.headsUpStatusBarText = builder.getHeadsUpStatusBarText(false);
        CharSequence unused6 = inflationProgress.headsUpStatusBarTextPublic = builder.getHeadsUpStatusBarText(true);
        return inflationProgress;
    }

    public static CancellationSignal apply(boolean z, InflationProgress inflationProgress, int i, ArrayMap<Integer, RemoteViews> arrayMap, ExpandableNotificationRow expandableNotificationRow, RemoteViews.OnClickHandler onClickHandler, InflationCallback inflationCallback) {
        NotificationContentView notificationContentView;
        NotificationContentView notificationContentView2;
        HashMap hashMap;
        final InflationProgress inflationProgress2 = inflationProgress;
        ArrayMap<Integer, RemoteViews> arrayMap2 = arrayMap;
        NotificationContentView privateLayout = expandableNotificationRow.getPrivateLayout();
        NotificationContentView publicLayout = expandableNotificationRow.getPublicLayout();
        HashMap hashMap2 = new HashMap();
        if ((i & 1) != 0) {
            boolean z2 = !canReapplyRemoteView(inflationProgress.newContentView, arrayMap2.get(1));
            C11801 r8 = new ApplyCallback() {
                public void setResultView(View view) {
                    View unused = InflationProgress.this.inflatedContentView = view;
                }

                public RemoteViews getRemoteView() {
                    return InflationProgress.this.newContentView;
                }
            };
            hashMap = hashMap2;
            notificationContentView2 = publicLayout;
            notificationContentView = privateLayout;
            applyRemoteView(z, inflationProgress, i, 1, arrayMap, expandableNotificationRow, z2, onClickHandler, inflationCallback, privateLayout, privateLayout.getContractedChild(), privateLayout.getVisibleWrapper(0), hashMap, r8);
        } else {
            hashMap = hashMap2;
            notificationContentView2 = publicLayout;
            notificationContentView = privateLayout;
        }
        if ((i & 2) != 0 && inflationProgress.newExpandedView != null) {
            NotificationContentView notificationContentView3 = notificationContentView;
            applyRemoteView(z, inflationProgress, i, 2, arrayMap, expandableNotificationRow, !canReapplyRemoteView(inflationProgress.newExpandedView, arrayMap2.get(2)), onClickHandler, inflationCallback, notificationContentView3, notificationContentView.getExpandedChild(), notificationContentView3.getVisibleWrapper(1), hashMap, new ApplyCallback() {
                public void setResultView(View view) {
                    View unused = InflationProgress.this.inflatedExpandedView = view;
                }

                public RemoteViews getRemoteView() {
                    return InflationProgress.this.newExpandedView;
                }
            });
        }
        if (!((i & 4) == 0 || inflationProgress.newHeadsUpView == null)) {
            NotificationContentView notificationContentView4 = notificationContentView;
            applyRemoteView(z, inflationProgress, i, 4, arrayMap, expandableNotificationRow, !canReapplyRemoteView(inflationProgress.newHeadsUpView, arrayMap2.get(4)), onClickHandler, inflationCallback, notificationContentView4, notificationContentView.getHeadsUpChild(), notificationContentView4.getVisibleWrapper(2), hashMap, new ApplyCallback() {
                public void setResultView(View view) {
                    View unused = InflationProgress.this.inflatedHeadsUpView = view;
                }

                public RemoteViews getRemoteView() {
                    return InflationProgress.this.newHeadsUpView;
                }
            });
        }
        if ((i & 8) != 0) {
            NotificationContentView notificationContentView5 = notificationContentView2;
            applyRemoteView(z, inflationProgress, i, 8, arrayMap, expandableNotificationRow, !canReapplyRemoteView(inflationProgress.newPublicView, arrayMap2.get(8)), onClickHandler, inflationCallback, notificationContentView5, notificationContentView2.getContractedChild(), notificationContentView5.getVisibleWrapper(0), hashMap, new ApplyCallback() {
                public void setResultView(View view) {
                    View unused = InflationProgress.this.inflatedPublicView = view;
                }

                public RemoteViews getRemoteView() {
                    return InflationProgress.this.newPublicView;
                }
            });
        }
        finishIfDone(inflationProgress, i, arrayMap, hashMap, inflationCallback, expandableNotificationRow);
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener(hashMap) {
            private final /* synthetic */ HashMap f$0;

            {
                this.f$0 = r1;
            }

            public final void onCancel() {
                this.f$0.values().forEach($$Lambda$POlPJz26zF5Nt5Z2kVGSqFxN8Co.INSTANCE);
            }
        });
        return cancellationSignal;
    }

    @VisibleForTesting
    static void applyRemoteView(boolean z, InflationProgress inflationProgress, int i, int i2, ArrayMap<Integer, RemoteViews> arrayMap, ExpandableNotificationRow expandableNotificationRow, boolean z2, RemoteViews.OnClickHandler onClickHandler, InflationCallback inflationCallback, NotificationContentView notificationContentView, View view, NotificationViewWrapper notificationViewWrapper, HashMap<Integer, CancellationSignal> hashMap, ApplyCallback applyCallback) {
        CancellationSignal cancellationSignal;
        InflationProgress inflationProgress2 = inflationProgress;
        RemoteViews.OnClickHandler onClickHandler2 = onClickHandler;
        HashMap<Integer, CancellationSignal> hashMap2 = hashMap;
        final RemoteViews remoteView = applyCallback.getRemoteView();
        if (!z) {
            InflationCallback inflationCallback2 = inflationCallback;
            NotificationContentView notificationContentView2 = notificationContentView;
            View view2 = view;
            final ApplyCallback applyCallback2 = applyCallback;
            final ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
            final boolean z3 = z2;
            final NotificationViewWrapper notificationViewWrapper2 = notificationViewWrapper;
            final HashMap<Integer, CancellationSignal> hashMap3 = hashMap;
            final int i3 = i2;
            final InflationProgress inflationProgress3 = inflationProgress;
            final int i4 = i;
            final ArrayMap<Integer, RemoteViews> arrayMap2 = arrayMap;
            final InflationCallback inflationCallback3 = inflationCallback;
            final View view3 = view;
            RemoteViews remoteViews = remoteView;
            final NotificationContentView notificationContentView3 = notificationContentView;
            final RemoteViews.OnClickHandler onClickHandler3 = onClickHandler;
            C11845 r1 = new RemoteViews.OnViewAppliedListener() {
                public void onViewInflated(View view) {
                    if (view instanceof ImageMessageConsumer) {
                        ((ImageMessageConsumer) view).setImageResolver(ExpandableNotificationRow.this.getImageResolver());
                    }
                }

                public void onViewApplied(View view) {
                    if (z3) {
                        view.setIsRootNamespace(true);
                        applyCallback2.setResultView(view);
                    } else {
                        NotificationViewWrapper notificationViewWrapper = notificationViewWrapper2;
                        if (notificationViewWrapper != null) {
                            notificationViewWrapper.onReinflated();
                        }
                    }
                    hashMap3.remove(Integer.valueOf(i3));
                    boolean unused = NotificationContentInflater.finishIfDone(inflationProgress3, i4, arrayMap2, hashMap3, inflationCallback3, ExpandableNotificationRow.this);
                }

                public void onError(Exception exc) {
                    try {
                        View view = view3;
                        if (z3) {
                            view = remoteView.apply(inflationProgress3.packageContext, notificationContentView3, onClickHandler3);
                        } else {
                            remoteView.reapply(inflationProgress3.packageContext, view3, onClickHandler3);
                        }
                        Log.wtf("NotifContentInflater", "Async Inflation failed but normal inflation finished normally.", exc);
                        onViewApplied(view);
                    } catch (Exception unused) {
                        hashMap3.remove(Integer.valueOf(i3));
                        NotificationContentInflater.handleInflationError(hashMap3, exc, ExpandableNotificationRow.this.getStatusBarNotification(), inflationCallback3);
                    }
                }
            };
            if (z2) {
                cancellationSignal = remoteViews.applyAsync(inflationProgress2.packageContext, notificationContentView, (Executor) null, r1, onClickHandler);
            } else {
                cancellationSignal = remoteViews.reapplyAsync(inflationProgress2.packageContext, view, (Executor) null, r1, onClickHandler);
            }
            hashMap.put(Integer.valueOf(i2), cancellationSignal);
        } else if (z2) {
            try {
                View apply = remoteView.apply(inflationProgress2.packageContext, notificationContentView, onClickHandler2);
                apply.setIsRootNamespace(true);
                applyCallback.setResultView(apply);
            } catch (Exception e) {
                handleInflationError(hashMap2, e, expandableNotificationRow.getStatusBarNotification(), inflationCallback);
                hashMap2.put(Integer.valueOf(i2), new CancellationSignal());
            }
        } else {
            remoteView.reapply(inflationProgress2.packageContext, view, onClickHandler2);
            notificationViewWrapper.onReinflated();
        }
    }

    /* access modifiers changed from: private */
    public static void handleInflationError(HashMap<Integer, CancellationSignal> hashMap, Exception exc, StatusBarNotification statusBarNotification, InflationCallback inflationCallback) {
        Assert.isMainThread();
        hashMap.values().forEach($$Lambda$POlPJz26zF5Nt5Z2kVGSqFxN8Co.INSTANCE);
        if (inflationCallback != null) {
            inflationCallback.handleInflationException(statusBarNotification, exc);
        }
    }

    /* access modifiers changed from: private */
    public static boolean finishIfDone(InflationProgress inflationProgress, int i, ArrayMap<Integer, RemoteViews> arrayMap, HashMap<Integer, CancellationSignal> hashMap, InflationCallback inflationCallback, ExpandableNotificationRow expandableNotificationRow) {
        Assert.isMainThread();
        NotificationEntry entry = expandableNotificationRow.getEntry();
        NotificationContentView privateLayout = expandableNotificationRow.getPrivateLayout();
        NotificationContentView publicLayout = expandableNotificationRow.getPublicLayout();
        boolean z = false;
        if (!hashMap.isEmpty()) {
            return false;
        }
        if ((i & 1) != 0) {
            if (inflationProgress.inflatedContentView != null) {
                privateLayout.setContractedChild(inflationProgress.inflatedContentView);
                arrayMap.put(1, inflationProgress.newContentView);
            } else if (arrayMap.get(1) != null) {
                arrayMap.put(1, inflationProgress.newContentView);
            }
        }
        if ((i & 2) != 0) {
            if (inflationProgress.inflatedExpandedView != null) {
                privateLayout.setExpandedChild(inflationProgress.inflatedExpandedView);
                arrayMap.put(2, inflationProgress.newExpandedView);
            } else if (inflationProgress.newExpandedView == null) {
                privateLayout.setExpandedChild((View) null);
                arrayMap.put(2, (Object) null);
            } else if (arrayMap.get(2) != null) {
                arrayMap.put(2, inflationProgress.newExpandedView);
            }
            if (inflationProgress.newExpandedView != null) {
                privateLayout.setExpandedInflatedSmartReplies(inflationProgress.expandedInflatedSmartReplies);
            } else {
                privateLayout.setExpandedInflatedSmartReplies((InflatedSmartReplies) null);
            }
            if (inflationProgress.newExpandedView != null) {
                z = true;
            }
            expandableNotificationRow.setExpandable(z);
        }
        if ((i & 4) != 0) {
            if (inflationProgress.inflatedHeadsUpView != null) {
                privateLayout.setHeadsUpChild(inflationProgress.inflatedHeadsUpView);
                arrayMap.put(4, inflationProgress.newHeadsUpView);
            } else if (inflationProgress.newHeadsUpView == null) {
                privateLayout.setHeadsUpChild((View) null);
                arrayMap.put(4, (Object) null);
            } else if (arrayMap.get(4) != null) {
                arrayMap.put(4, inflationProgress.newHeadsUpView);
            }
            if (inflationProgress.newHeadsUpView != null) {
                privateLayout.setHeadsUpInflatedSmartReplies(inflationProgress.headsUpInflatedSmartReplies);
            } else {
                privateLayout.setHeadsUpInflatedSmartReplies((InflatedSmartReplies) null);
            }
        }
        if ((i & 8) != 0) {
            if (inflationProgress.inflatedPublicView != null) {
                publicLayout.setContractedChild(inflationProgress.inflatedPublicView);
                arrayMap.put(8, inflationProgress.newPublicView);
            } else if (arrayMap.get(8) != null) {
                arrayMap.put(8, inflationProgress.newPublicView);
            }
        }
        entry.headsUpStatusBarText = inflationProgress.headsUpStatusBarText;
        entry.headsUpStatusBarTextPublic = inflationProgress.headsUpStatusBarTextPublic;
        if (inflationCallback != null) {
            inflationCallback.onAsyncInflationFinished(expandableNotificationRow.getEntry(), i);
        }
        return true;
    }

    private static RemoteViews createExpandedView(Notification.Builder builder, boolean z) {
        RemoteViews createBigContentView = builder.createBigContentView();
        if (createBigContentView != null) {
            return createBigContentView;
        }
        if (!z) {
            return null;
        }
        RemoteViews createContentView = builder.createContentView();
        Notification.Builder.makeHeaderExpanded(createContentView);
        return createContentView;
    }

    private static RemoteViews createContentView(Notification.Builder builder, boolean z, boolean z2) {
        if (z) {
            return builder.makeLowPriorityContentView(false);
        }
        return builder.createContentView(z2);
    }

    @VisibleForTesting
    static boolean canReapplyRemoteView(RemoteViews remoteViews, RemoteViews remoteViews2) {
        if (remoteViews == null && remoteViews2 == null) {
            return true;
        }
        if (remoteViews == null || remoteViews2 == null || remoteViews2.getPackage() == null || remoteViews.getPackage() == null || !remoteViews.getPackage().equals(remoteViews2.getPackage()) || remoteViews.getLayoutId() != remoteViews2.getLayoutId() || remoteViews2.hasFlags(1)) {
            return false;
        }
        return true;
    }

    public void setInflationCallback(InflationCallback inflationCallback) {
        this.mCallback = inflationCallback;
    }

    public void clearCachesAndReInflate() {
        this.mCachedContentViews.clear();
        inflateNotificationViews();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setInflateSynchronously(boolean z) {
        this.mInflateSynchronously = z;
    }

    public static class AsyncInflationTask extends AsyncTask<Void, Void, InflationProgress> implements InflationCallback, InflationTask {
        private final ArrayMap<Integer, RemoteViews> mCachedContentViews;
        private final InflationCallback mCallback;
        private CancellationSignal mCancellationSignal;
        private final Context mContext;
        private Exception mError;
        private final boolean mInflateSynchronously;
        private final boolean mIsChildInGroup;
        private final boolean mIsLowPriority;
        private int mReInflateFlags;
        private RemoteViews.OnClickHandler mRemoteViewClickHandler;
        private ExpandableNotificationRow mRow;
        private final StatusBarNotification mSbn;
        private final boolean mUsesIncreasedHeadsUpHeight;
        private final boolean mUsesIncreasedHeight;

        private AsyncInflationTask(StatusBarNotification statusBarNotification, boolean z, int i, ArrayMap<Integer, RemoteViews> arrayMap, ExpandableNotificationRow expandableNotificationRow, boolean z2, boolean z3, boolean z4, boolean z5, InflationCallback inflationCallback, RemoteViews.OnClickHandler onClickHandler) {
            this.mRow = expandableNotificationRow;
            this.mSbn = statusBarNotification;
            this.mInflateSynchronously = z;
            this.mReInflateFlags = i;
            this.mCachedContentViews = arrayMap;
            this.mContext = this.mRow.getContext();
            this.mIsLowPriority = z2;
            this.mIsChildInGroup = z3;
            this.mUsesIncreasedHeight = z4;
            this.mUsesIncreasedHeadsUpHeight = z5;
            this.mRemoteViewClickHandler = onClickHandler;
            this.mCallback = inflationCallback;
            expandableNotificationRow.getEntry().setInflationTask(this);
        }

        @VisibleForTesting
        public int getReInflateFlags() {
            return this.mReInflateFlags;
        }

        /* access modifiers changed from: protected */
        public InflationProgress doInBackground(Void... voidArr) {
            try {
                Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(this.mContext, this.mSbn.getNotification());
                Context packageContext = this.mSbn.getPackageContext(this.mContext);
                Notification notification = this.mSbn.getNotification();
                if (notification.isMediaNotification()) {
                    new MediaNotificationProcessor(this.mContext, packageContext).processNotification(notification, recoverBuilder);
                }
                InflationProgress access$1500 = NotificationContentInflater.createRemoteViews(this.mReInflateFlags, recoverBuilder, this.mIsLowPriority, this.mIsChildInGroup, this.mUsesIncreasedHeight, this.mUsesIncreasedHeadsUpHeight, packageContext);
                InflationProgress unused = NotificationContentInflater.inflateSmartReplyViews(access$1500, this.mReInflateFlags, this.mRow.getEntry(), this.mRow.getContext(), packageContext, this.mRow.getHeadsUpManager(), this.mRow.getExistingSmartRepliesAndActions());
                return access$1500;
            } catch (Exception e) {
                this.mError = e;
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(InflationProgress inflationProgress) {
            Exception exc = this.mError;
            if (exc == null) {
                this.mCancellationSignal = NotificationContentInflater.apply(this.mInflateSynchronously, inflationProgress, this.mReInflateFlags, this.mCachedContentViews, this.mRow, this.mRemoteViewClickHandler, this);
                return;
            }
            handleError(exc);
        }

        private void handleError(Exception exc) {
            this.mRow.getEntry().onInflationTaskFinished();
            StatusBarNotification statusBarNotification = this.mRow.getStatusBarNotification();
            Log.e(StatusBar.TAG, "couldn't inflate view for notification " + (statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId())), exc);
            this.mCallback.handleInflationException(statusBarNotification, new InflationException("Couldn't inflate contentViews" + exc));
        }

        public void abort() {
            cancel(true);
            CancellationSignal cancellationSignal = this.mCancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
        }

        public void supersedeTask(InflationTask inflationTask) {
            if (inflationTask instanceof AsyncInflationTask) {
                this.mReInflateFlags = ((AsyncInflationTask) inflationTask).mReInflateFlags | this.mReInflateFlags;
            }
        }

        public void handleInflationException(StatusBarNotification statusBarNotification, Exception exc) {
            handleError(exc);
        }

        public void onAsyncInflationFinished(NotificationEntry notificationEntry, int i) {
            this.mRow.getEntry().onInflationTaskFinished();
            this.mRow.onNotificationUpdated();
            this.mCallback.onAsyncInflationFinished(this.mRow.getEntry(), i);
            this.mRow.getImageResolver().purgeCache();
        }
    }

    @VisibleForTesting
    static class InflationProgress {
        /* access modifiers changed from: private */
        public InflatedSmartReplies expandedInflatedSmartReplies;
        /* access modifiers changed from: private */
        public InflatedSmartReplies headsUpInflatedSmartReplies;
        /* access modifiers changed from: private */
        public CharSequence headsUpStatusBarText;
        /* access modifiers changed from: private */
        public CharSequence headsUpStatusBarTextPublic;
        /* access modifiers changed from: private */
        public View inflatedContentView;
        /* access modifiers changed from: private */
        public View inflatedExpandedView;
        /* access modifiers changed from: private */
        public View inflatedHeadsUpView;
        /* access modifiers changed from: private */
        public View inflatedPublicView;
        /* access modifiers changed from: private */
        public RemoteViews newContentView;
        /* access modifiers changed from: private */
        public RemoteViews newExpandedView;
        /* access modifiers changed from: private */
        public RemoteViews newHeadsUpView;
        /* access modifiers changed from: private */
        public RemoteViews newPublicView;
        @VisibleForTesting
        Context packageContext;

        InflationProgress() {
        }
    }

    @VisibleForTesting
    static abstract class ApplyCallback {
        public abstract RemoteViews getRemoteView();

        public abstract void setResultView(View view);

        ApplyCallback() {
        }
    }
}

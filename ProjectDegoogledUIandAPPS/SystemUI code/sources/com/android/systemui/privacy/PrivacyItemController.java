package com.android.systemui.privacy;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dumpable;
import com.android.systemui.appops.AppOpItem;
import com.android.systemui.appops.AppOpsController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItemController.kt */
public final class PrivacyItemController implements Dumpable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int[] OPS = {26, 27, 0, 1};
    /* access modifiers changed from: private */
    public static final List<String> intents = CollectionsKt__CollectionsKt.listOf("android.intent.action.USER_FOREGROUND", "android.intent.action.MANAGED_PROFILE_ADDED", "android.intent.action.MANAGED_PROFILE_REMOVED");
    private final AppOpsController appOpsController;
    private final Handler bgHandler;
    /* access modifiers changed from: private */
    public final List<WeakReference<Callback>> callbacks;

    /* renamed from: cb */
    private final PrivacyItemController$cb$1 f53cb;
    private final Context context;
    /* access modifiers changed from: private */
    public List<Integer> currentUserIds = CollectionsKt__CollectionsKt.emptyList();
    private final DeviceConfig.OnPropertyChangedListener devicePropertyChangedListener;
    /* access modifiers changed from: private */
    public boolean indicatorsAvailable;
    private boolean listening;
    /* access modifiers changed from: private */
    public final C0869H messageHandler;
    /* access modifiers changed from: private */
    public final Runnable notifyChanges;
    private List<PrivacyItem> privacyList = CollectionsKt__CollectionsKt.emptyList();
    private final PrivacyApplication systemApp;
    /* access modifiers changed from: private */
    public final Handler uiHandler;
    private final Runnable updateListAndNotifyChanges;
    private final UserManager userManager = ((UserManager) this.context.getSystemService(UserManager.class));
    private Receiver userSwitcherReceiver;

    /* compiled from: PrivacyItemController.kt */
    public interface Callback {
        void privacyChanged(List<PrivacyItem> list);
    }

    @VisibleForTesting
    public static /* synthetic */ void devicePropertyChangedListener$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void privacyList$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void userSwitcherReceiver$annotations() {
    }

    public PrivacyItemController(Context context2, AppOpsController appOpsController2, Handler handler, Handler handler2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(appOpsController2, "appOpsController");
        Intrinsics.checkParameterIsNotNull(handler, "uiHandler");
        Intrinsics.checkParameterIsNotNull(handler2, "bgHandler");
        this.context = context2;
        this.appOpsController = appOpsController2;
        this.uiHandler = handler;
        this.bgHandler = handler2;
        String string = this.context.getString(C1784R$string.device_services);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.device_services)");
        this.systemApp = new PrivacyApplication(string, 1000, this.context);
        this.callbacks = new ArrayList();
        WeakReference weakReference = new WeakReference(this);
        Looper looper = this.uiHandler.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "uiHandler.looper");
        this.messageHandler = new C0869H(weakReference, looper);
        this.notifyChanges = new PrivacyItemController$notifyChanges$1(this);
        this.updateListAndNotifyChanges = new PrivacyItemController$updateListAndNotifyChanges$1(this);
        this.indicatorsAvailable = Settings.System.getIntForUser(this.context.getContentResolver(), "permissions_hub_enabled", 1, -2) != 1 ? false : true;
        this.devicePropertyChangedListener = new PrivacyItemController$devicePropertyChangedListener$1(this);
        this.f53cb = new PrivacyItemController$cb$1(this);
        this.userSwitcherReceiver = new Receiver();
        DeviceConfig.addOnPropertyChangedListener("privacy", this.context.getMainExecutor(), this.devicePropertyChangedListener);
    }

    @VisibleForTesting
    /* compiled from: PrivacyItemController.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<String> getIntents() {
            return PrivacyItemController.intents;
        }
    }

    public final synchronized List<PrivacyItem> getPrivacyList$name() {
        return CollectionsKt___CollectionsKt.toList(this.privacyList);
    }

    private final void unregisterReceiver() {
        this.context.unregisterReceiver(this.userSwitcherReceiver);
    }

    private final void registerReceiver() {
        Context context2 = this.context;
        Receiver receiver = this.userSwitcherReceiver;
        UserHandle userHandle = UserHandle.ALL;
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : intents) {
            intentFilter.addAction(addAction);
        }
        context2.registerReceiverAsUser(receiver, userHandle, intentFilter, (String) null, (Handler) null);
    }

    /* access modifiers changed from: private */
    public final void update(boolean z) {
        if (z) {
            List<UserInfo> profiles = this.userManager.getProfiles(ActivityManager.getCurrentUser());
            Intrinsics.checkExpressionValueIsNotNull(profiles, "userManager.getProfiles(currentUser)");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(profiles, 10));
            for (UserInfo userInfo : profiles) {
                arrayList.add(Integer.valueOf(userInfo.id));
            }
            this.currentUserIds = arrayList;
        }
        this.bgHandler.post(this.updateListAndNotifyChanges);
    }

    /* access modifiers changed from: private */
    public final void setListeningState() {
        boolean z = (!this.callbacks.isEmpty()) & this.indicatorsAvailable;
        if (this.listening != z) {
            this.listening = z;
            if (this.listening) {
                this.appOpsController.addCallback(OPS, this.f53cb);
                registerReceiver();
                update(true);
                return;
            }
            this.appOpsController.removeCallback(OPS, this.f53cb);
            unregisterReceiver();
            update(false);
        }
    }

    /* access modifiers changed from: private */
    public final void addCallback(WeakReference<Callback> weakReference) {
        this.callbacks.add(weakReference);
        if ((!this.callbacks.isEmpty()) && !this.listening) {
            this.messageHandler.removeMessages(2);
            this.messageHandler.sendEmptyMessage(2);
        } else if (this.listening) {
            this.uiHandler.post(new NotifyChangesToCallback((Callback) weakReference.get(), getPrivacyList$name()));
        }
    }

    /* access modifiers changed from: private */
    public final void removeCallback(WeakReference<Callback> weakReference) {
        this.callbacks.removeIf(new PrivacyItemController$removeCallback$1(weakReference));
        if (this.callbacks.isEmpty()) {
            this.messageHandler.removeMessages(2);
            this.messageHandler.sendEmptyMessage(2);
        }
    }

    public final void addCallback(Callback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.messageHandler.obtainMessage(0, callback).sendToTarget();
    }

    public final void removeCallback(Callback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.messageHandler.obtainMessage(1, callback).sendToTarget();
    }

    /* access modifiers changed from: private */
    public final void updatePrivacyList() {
        if (!this.listening) {
            this.privacyList = CollectionsKt__CollectionsKt.emptyList();
            return;
        }
        List<Integer> list = this.currentUserIds;
        ArrayList<AppOpItem> arrayList = new ArrayList<>();
        for (Number intValue : list) {
            boolean unused = CollectionsKt__MutableCollectionsKt.addAll(arrayList, this.appOpsController.getActiveAppOpsForUser(intValue.intValue()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (AppOpItem appOpItem : arrayList) {
            Intrinsics.checkExpressionValueIsNotNull(appOpItem, "it");
            PrivacyItem privacyItem = toPrivacyItem(appOpItem);
            if (privacyItem != null) {
                arrayList2.add(privacyItem);
            }
        }
        this.privacyList = CollectionsKt___CollectionsKt.distinct(arrayList2);
    }

    private final PrivacyItem toPrivacyItem(AppOpItem appOpItem) {
        PrivacyType privacyType;
        int code = appOpItem.getCode();
        if (code == 0) {
            privacyType = PrivacyType.TYPE_LOCATION;
        } else if (code == 1) {
            privacyType = PrivacyType.TYPE_LOCATION;
        } else if (code == 26) {
            privacyType = PrivacyType.TYPE_CAMERA;
        } else if (code != 27) {
            return null;
        } else {
            privacyType = PrivacyType.TYPE_MICROPHONE;
        }
        if (appOpItem.getUid() == 1000) {
            return new PrivacyItem(privacyType, this.systemApp);
        }
        String packageName = appOpItem.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "appOpItem.packageName");
        return new PrivacyItem(privacyType, new PrivacyApplication(packageName, appOpItem.getUid(), this.context));
    }

    /* compiled from: PrivacyItemController.kt */
    public final class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (CollectionsKt___CollectionsKt.contains(PrivacyItemController.Companion.getIntents(), intent != null ? intent.getAction() : null)) {
                PrivacyItemController.this.update(true);
            }
        }
    }

    /* compiled from: PrivacyItemController.kt */
    private static final class NotifyChangesToCallback implements Runnable {
        private final Callback callback;
        private final List<PrivacyItem> list;

        public NotifyChangesToCallback(Callback callback2, List<PrivacyItem> list2) {
            Intrinsics.checkParameterIsNotNull(list2, "list");
            this.callback = callback2;
            this.list = list2;
        }

        public void run() {
            Callback callback2 = this.callback;
            if (callback2 != null) {
                callback2.privacyChanged(this.list);
            }
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (printWriter != null) {
            printWriter.println("PrivacyItemController state:");
        }
        if (printWriter != null) {
            printWriter.println("  Listening: " + this.listening);
        }
        if (printWriter != null) {
            printWriter.println("  Current user ids: " + this.currentUserIds);
        }
        if (printWriter != null) {
            printWriter.println("  Privacy Items:");
        }
        for (PrivacyItem privacyItem : getPrivacyList$name()) {
            if (printWriter != null) {
                printWriter.print("    ");
            }
            if (printWriter != null) {
                printWriter.println(privacyItem.toString());
            }
        }
        if (printWriter != null) {
            printWriter.println("  Callbacks:");
        }
        for (WeakReference weakReference : this.callbacks) {
            Callback callback = (Callback) weakReference.get();
            if (callback != null) {
                if (printWriter != null) {
                    printWriter.print("    ");
                }
                if (printWriter != null) {
                    printWriter.println(callback.toString());
                }
            }
        }
    }

    /* renamed from: com.android.systemui.privacy.PrivacyItemController$H */
    /* compiled from: PrivacyItemController.kt */
    private static final class C0869H extends Handler {
        private final WeakReference<PrivacyItemController> outerClass;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0869H(WeakReference<PrivacyItemController> weakReference, Looper looper) {
            super(looper);
            Intrinsics.checkParameterIsNotNull(weakReference, "outerClass");
            Intrinsics.checkParameterIsNotNull(looper, "looper");
            this.outerClass = weakReference;
        }

        public void handleMessage(Message message) {
            PrivacyItemController privacyItemController;
            PrivacyItemController privacyItemController2;
            PrivacyItemController privacyItemController3;
            Intrinsics.checkParameterIsNotNull(message, "msg");
            super.handleMessage(message);
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 && (privacyItemController3 = (PrivacyItemController) this.outerClass.get()) != null) {
                        privacyItemController3.setListeningState();
                    }
                } else if ((message.obj instanceof Callback) && (privacyItemController2 = (PrivacyItemController) this.outerClass.get()) != null) {
                    Object obj = message.obj;
                    if (obj != null) {
                        privacyItemController2.removeCallback((WeakReference<Callback>) new WeakReference((Callback) obj));
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.android.systemui.privacy.PrivacyItemController.Callback");
                }
            } else if ((message.obj instanceof Callback) && (privacyItemController = (PrivacyItemController) this.outerClass.get()) != null) {
                Object obj2 = message.obj;
                if (obj2 != null) {
                    privacyItemController.addCallback((WeakReference<Callback>) new WeakReference((Callback) obj2));
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.android.systemui.privacy.PrivacyItemController.Callback");
            }
        }
    }
}

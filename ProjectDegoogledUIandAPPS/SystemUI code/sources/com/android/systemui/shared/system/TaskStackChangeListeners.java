package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import com.android.systemui.shared.recents.model.ThumbnailData;
import java.util.ArrayList;
import java.util.List;

public class TaskStackChangeListeners extends TaskStackListener {
    private static final String TAG = "TaskStackChangeListeners";
    private final Handler mHandler;
    private boolean mRegistered;
    /* access modifiers changed from: private */
    public final List<TaskStackChangeListener> mTaskStackListeners = new ArrayList();
    private final List<TaskStackChangeListener> mTmpListeners = new ArrayList();

    public TaskStackChangeListeners(Looper looper) {
        this.mHandler = new C1034H(looper);
    }

    public void addListener(IActivityManager iActivityManager, TaskStackChangeListener taskStackChangeListener) {
        this.mTaskStackListeners.add(taskStackChangeListener);
        if (!this.mRegistered) {
            try {
                ActivityTaskManager.getService().registerTaskStackListener(this);
                this.mRegistered = true;
            } catch (Exception e) {
                Log.w(TAG, "Failed to call registerTaskStackListener", e);
            }
        }
    }

    public void removeListener(TaskStackChangeListener taskStackChangeListener) {
        this.mTaskStackListeners.remove(taskStackChangeListener);
        if (this.mTaskStackListeners.isEmpty() && this.mRegistered) {
            try {
                ActivityTaskManager.getService().unregisterTaskStackListener(this);
                this.mRegistered = false;
            } catch (Exception e) {
                Log.w(TAG, "Failed to call unregisterTaskStackListener", e);
            }
        }
    }

    public void onTaskStackChanged() throws RemoteException {
        synchronized (this.mTaskStackListeners) {
            this.mTmpListeners.clear();
            this.mTmpListeners.addAll(this.mTaskStackListeners);
        }
        for (int size = this.mTmpListeners.size() - 1; size >= 0; size--) {
            this.mTmpListeners.get(size).onTaskStackChangedBackground();
        }
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(1);
    }

    public void onActivityPinned(String str, int i, int i2, int i3) throws RemoteException {
        this.mHandler.removeMessages(3);
        this.mHandler.obtainMessage(3, new PinnedActivityInfo(str, i, i2, i3)).sendToTarget();
    }

    public void onActivityUnpinned() throws RemoteException {
        this.mHandler.removeMessages(10);
        this.mHandler.sendEmptyMessage(10);
    }

    public void onPinnedActivityRestartAttempt(boolean z) throws RemoteException {
        this.mHandler.removeMessages(4);
        this.mHandler.obtainMessage(4, z ? 1 : 0, 0).sendToTarget();
    }

    public void onPinnedStackAnimationStarted() throws RemoteException {
        this.mHandler.removeMessages(9);
        this.mHandler.sendEmptyMessage(9);
    }

    public void onPinnedStackAnimationEnded() throws RemoteException {
        this.mHandler.removeMessages(5);
        this.mHandler.sendEmptyMessage(5);
    }

    public void onActivityForcedResizable(String str, int i, int i2) throws RemoteException {
        this.mHandler.obtainMessage(6, i, i2, str).sendToTarget();
    }

    public void onActivityDismissingDockedStack() throws RemoteException {
        this.mHandler.sendEmptyMessage(7);
    }

    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo runningTaskInfo, int i) throws RemoteException {
        this.mHandler.obtainMessage(11, i, 0, runningTaskInfo).sendToTarget();
    }

    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo runningTaskInfo, int i) throws RemoteException {
        this.mHandler.obtainMessage(16, i, 0, runningTaskInfo).sendToTarget();
    }

    public void onTaskProfileLocked(int i, int i2) throws RemoteException {
        this.mHandler.obtainMessage(8, i, i2).sendToTarget();
    }

    public void onTaskSnapshotChanged(int i, ActivityManager.TaskSnapshot taskSnapshot) throws RemoteException {
        this.mHandler.obtainMessage(2, i, 0, taskSnapshot).sendToTarget();
    }

    public void onTaskCreated(int i, ComponentName componentName) throws RemoteException {
        this.mHandler.obtainMessage(12, i, 0, componentName).sendToTarget();
    }

    public void onTaskRemoved(int i) throws RemoteException {
        this.mHandler.obtainMessage(13, i, 0).sendToTarget();
    }

    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
        this.mHandler.obtainMessage(14, runningTaskInfo).sendToTarget();
    }

    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
        this.mHandler.obtainMessage(18, runningTaskInfo).sendToTarget();
    }

    public void onActivityRequestedOrientationChanged(int i, int i2) throws RemoteException {
        this.mHandler.obtainMessage(15, i, i2).sendToTarget();
    }

    public void onSizeCompatModeActivityChanged(int i, IBinder iBinder) throws RemoteException {
        this.mHandler.obtainMessage(17, i, 0, iBinder).sendToTarget();
    }

    public void onSingleTaskDisplayDrawn(int i) throws RemoteException {
        this.mHandler.obtainMessage(19, i, 0).sendToTarget();
    }

    public void onSingleTaskDisplayEmpty(int i) throws RemoteException {
        this.mHandler.obtainMessage(20, i, 0).sendToTarget();
    }

    public void onTaskDisplayChanged(int i, int i2) throws RemoteException {
        this.mHandler.obtainMessage(21, i, i2).sendToTarget();
    }

    /* renamed from: com.android.systemui.shared.system.TaskStackChangeListeners$H */
    private final class C1034H extends Handler {
        public C1034H(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            synchronized (TaskStackChangeListeners.this.mTaskStackListeners) {
                switch (message.what) {
                    case 1:
                        Trace.beginSection("onTaskStackChanged");
                        for (int size = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size >= 0; size--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size)).onTaskStackChanged();
                        }
                        Trace.endSection();
                        break;
                    case 2:
                        Trace.beginSection("onTaskSnapshotChanged");
                        for (int size2 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size2 >= 0; size2--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size2)).onTaskSnapshotChanged(message.arg1, new ThumbnailData((ActivityManager.TaskSnapshot) message.obj));
                        }
                        Trace.endSection();
                        break;
                    case 3:
                        PinnedActivityInfo pinnedActivityInfo = (PinnedActivityInfo) message.obj;
                        for (int size3 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size3 >= 0; size3--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size3)).onActivityPinned(pinnedActivityInfo.mPackageName, pinnedActivityInfo.mUserId, pinnedActivityInfo.mTaskId, pinnedActivityInfo.mStackId);
                        }
                        break;
                    case 4:
                        for (int size4 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size4 >= 0; size4--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size4)).onPinnedActivityRestartAttempt(message.arg1 != 0);
                        }
                        break;
                    case 5:
                        for (int size5 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size5 >= 0; size5--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size5)).onPinnedStackAnimationEnded();
                        }
                        break;
                    case 6:
                        for (int size6 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size6 >= 0; size6--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size6)).onActivityForcedResizable((String) message.obj, message.arg1, message.arg2);
                        }
                        break;
                    case 7:
                        for (int size7 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size7 >= 0; size7--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size7)).onActivityDismissingDockedStack();
                        }
                        break;
                    case 8:
                        for (int size8 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size8 >= 0; size8--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size8)).onTaskProfileLocked(message.arg1, message.arg2);
                        }
                        break;
                    case 9:
                        for (int size9 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size9 >= 0; size9--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size9)).onPinnedStackAnimationStarted();
                        }
                        break;
                    case 10:
                        for (int size10 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size10 >= 0; size10--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size10)).onActivityUnpinned();
                        }
                        break;
                    case 11:
                        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) message.obj;
                        for (int size11 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size11 >= 0; size11--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size11)).onActivityLaunchOnSecondaryDisplayFailed(runningTaskInfo);
                        }
                        break;
                    case 12:
                        for (int size12 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size12 >= 0; size12--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size12)).onTaskCreated(message.arg1, (ComponentName) message.obj);
                        }
                        break;
                    case 13:
                        for (int size13 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size13 >= 0; size13--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size13)).onTaskRemoved(message.arg1);
                        }
                        break;
                    case 14:
                        ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) message.obj;
                        for (int size14 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size14 >= 0; size14--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size14)).onTaskMovedToFront(runningTaskInfo2);
                        }
                        break;
                    case 15:
                        for (int size15 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size15 >= 0; size15--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size15)).onActivityRequestedOrientationChanged(message.arg1, message.arg2);
                        }
                        break;
                    case 16:
                        ActivityManager.RunningTaskInfo runningTaskInfo3 = (ActivityManager.RunningTaskInfo) message.obj;
                        for (int size16 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size16 >= 0; size16--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size16)).onActivityLaunchOnSecondaryDisplayRerouted(runningTaskInfo3);
                        }
                        break;
                    case 17:
                        for (int size17 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size17 >= 0; size17--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size17)).onSizeCompatModeActivityChanged(message.arg1, (IBinder) message.obj);
                        }
                        break;
                    case 18:
                        for (int size18 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size18 >= 0; size18--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size18)).onBackPressedOnTaskRoot((ActivityManager.RunningTaskInfo) message.obj);
                        }
                        break;
                    case 19:
                        for (int size19 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size19 >= 0; size19--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size19)).onSingleTaskDisplayDrawn(message.arg1);
                        }
                        break;
                    case 20:
                        for (int size20 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size20 >= 0; size20--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size20)).onSingleTaskDisplayEmpty(message.arg1);
                        }
                        break;
                    case 21:
                        for (int size21 = TaskStackChangeListeners.this.mTaskStackListeners.size() - 1; size21 >= 0; size21--) {
                            ((TaskStackChangeListener) TaskStackChangeListeners.this.mTaskStackListeners.get(size21)).onTaskDisplayChanged(message.arg1, message.arg2);
                        }
                        break;
                }
            }
        }
    }

    private static class PinnedActivityInfo {
        final String mPackageName;
        final int mStackId;
        final int mTaskId;
        final int mUserId;

        PinnedActivityInfo(String str, int i, int i2, int i3) {
            this.mPackageName = str;
            this.mUserId = i;
            this.mTaskId = i2;
            this.mStackId = i3;
        }
    }
}

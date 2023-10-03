package com.android.incallui.legacyblocking;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.CallLog;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.AsyncTaskExecutor;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.legacyblocking.DeleteBlockedCallTask;
import java.util.Objects;

public class BlockedNumberContentObserver extends ContentObserver implements DeleteBlockedCallTask.Listener {
    private final AsyncTaskExecutor asyncTaskExecutor = AsyncTaskExecutors.createThreadPoolExecutor();
    private final Context context;
    private final Handler handler;
    private final String number;
    private final long timeAddedMillis;
    private final Runnable timeoutRunnable = new Runnable() {
        public void run() {
            BlockedNumberContentObserver.this.unregister();
        }
    };

    public BlockedNumberContentObserver(Context context2, Handler handler2, String str, long j) {
        super(handler2);
        this.context = ((Context) Objects.requireNonNull(context2, "context")).getApplicationContext();
        this.handler = (Handler) Objects.requireNonNull(handler2);
        this.number = str;
        this.timeAddedMillis = j;
    }

    /* access modifiers changed from: private */
    public void unregister() {
        LogUtil.m9i("BlockedNumberContentObserver.unregister", (String) null, new Object[0]);
        this.handler.removeCallbacks(this.timeoutRunnable);
        this.context.getContentResolver().unregisterContentObserver(this);
    }

    public void onChange(boolean z) {
        LogUtil.m9i("BlockedNumberContentObserver.onChange", "attempting to remove call log entry from blocked number", new Object[0]);
        this.asyncTaskExecutor.submit("DeleteBlockedCallTask", new DeleteBlockedCallTask(this.context, this, this.number, this.timeAddedMillis), new Void[0]);
    }

    public void onDeleteBlockedCallTaskComplete(boolean z) {
        if (z) {
            unregister();
        }
    }

    public void register() {
        LogUtil.m9i("BlockedNumberContentObserver.register", (String) null, new Object[0]);
        if (!PermissionsUtil.hasCallLogReadPermissions(this.context) || !PermissionsUtil.hasCallLogWritePermissions(this.context)) {
            LogUtil.m10w("BlockedNumberContentObserver.register", "no call log read/write permissions.", new Object[0]);
            return;
        }
        this.context.getContentResolver().registerContentObserver(CallLog.CONTENT_URI, true, this);
        this.handler.postDelayed(this.timeoutRunnable, 5000);
    }
}

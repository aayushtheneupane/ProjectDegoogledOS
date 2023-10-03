package com.android.dialer.main.impl.bottomnav;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.UiListener;
import java.util.concurrent.Callable;

public final class MissedCallCountObserver extends ContentObserver {
    private final Context appContext;
    private final BottomNavBar bottomNavBar;
    private final UiListener<Integer> uiListener;

    public MissedCallCountObserver(Context context, BottomNavBar bottomNavBar2, UiListener<Integer> uiListener2) {
        super((Handler) null);
        this.appContext = context;
        this.bottomNavBar = bottomNavBar2;
        this.uiListener = uiListener2;
    }

    static /* synthetic */ void lambda$onChange$2(Throwable th) {
        throw new RuntimeException(th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        if (r0 != null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Integer lambda$onChange$0$MissedCallCountObserver() throws java.lang.Exception {
        /*
            r6 = this;
            android.content.Context r6 = r6.appContext
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = android.provider.CallLog.Calls.CONTENT_URI
            java.lang.String r6 = "_id"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            r6 = 2
            java.lang.String[] r4 = new java.lang.String[r6]
            r6 = 0
            java.lang.String r3 = "0"
            r4[r6] = r3
            r3 = 3
            java.lang.String r3 = java.lang.Integer.toString(r3)
            r5 = 1
            r4[r5] = r3
            java.lang.String r3 = "(is_read = ? OR is_read IS NULL) AND type = ?"
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0028
            goto L_0x002c
        L_0x0028:
            int r6 = r0.getCount()     // Catch:{ all -> 0x0036 }
        L_0x002c:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            return r6
        L_0x0036:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r1 = move-exception
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ all -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r6.addSuppressed(r0)
        L_0x0043:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.main.impl.bottomnav.MissedCallCountObserver.lambda$onChange$0$MissedCallCountObserver():java.lang.Integer");
    }

    public /* synthetic */ void lambda$onChange$1$MissedCallCountObserver(Integer num) {
        this.bottomNavBar.setNotificationCount(1, num == null ? 0 : num.intValue());
    }

    public void onChange(boolean z) {
        this.uiListener.listen(this.appContext, DialerExecutorComponent.get(this.appContext).backgroundExecutor().submit(new Callable() {
            public final Object call() {
                return MissedCallCountObserver.this.lambda$onChange$0$MissedCallCountObserver();
            }
        }), new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                MissedCallCountObserver.this.lambda$onChange$1$MissedCallCountObserver((Integer) obj);
            }
        }, $$Lambda$MissedCallCountObserver$r6w374MbDCYDIZevAOMh7AbyepU.INSTANCE);
    }
}

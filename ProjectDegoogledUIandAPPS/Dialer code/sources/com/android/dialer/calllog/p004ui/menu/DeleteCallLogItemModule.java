package com.android.dialer.calllog.p004ui.menu;

import android.content.Context;
import android.provider.CallLog;
import com.android.dialer.CoalescedIds;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.database.Selection;
import com.android.dialer.historyitemactions.HistoryItemActionModule;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: com.android.dialer.calllog.ui.menu.DeleteCallLogItemModule */
final class DeleteCallLogItemModule implements HistoryItemActionModule {
    private final CoalescedIds coalescedIds;
    private final Context context;

    /* renamed from: com.android.dialer.calllog.ui.menu.DeleteCallLogItemModule$CallLogItemDeletionWorker */
    private static class CallLogItemDeletionWorker implements DialerExecutor.Worker<CoalescedIds, Void> {
        private final WeakReference<Context> contextWeakReference;

        CallLogItemDeletionWorker(Context context) {
            this.contextWeakReference = new WeakReference<>(context);
        }

        public Object doInBackground(Object obj) throws Throwable {
            CoalescedIds coalescedIds = (CoalescedIds) obj;
            Context context = (Context) this.contextWeakReference.get();
            if (context == null) {
                LogUtil.m8e("com.android.dialer.calllog.ui.menu.DeleteCallLogItemModule", "Unable to delete an call log item due to null context.", new Object[0]);
                return null;
            }
            Selection.Builder builder = Selection.builder();
            Selection.Column column = Selection.column("_id");
            Assert.checkArgument(coalescedIds.getCoalescedIdCount() > 0);
            ArrayList arrayList = new ArrayList(coalescedIds.getCoalescedIdCount());
            for (Long longValue : coalescedIds.getCoalescedIdList()) {
                arrayList.add(String.valueOf(longValue.longValue()));
            }
            builder.and(column.mo5865in(arrayList));
            Selection build = builder.build();
            int delete = context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, build.getSelection(), build.getSelectionArgs());
            if (delete == coalescedIds.getCoalescedIdCount()) {
                return null;
            }
            LogUtil.m8e("com.android.dialer.calllog.ui.menu.DeleteCallLogItemModule", "Deleting call log item is unsuccessful. %d of %d rows are deleted.", Integer.valueOf(delete), Integer.valueOf(coalescedIds.getCoalescedIdCount()));
            return null;
        }
    }

    DeleteCallLogItemModule(Context context2, CoalescedIds coalescedIds2) {
        this.context = context2;
        this.coalescedIds = coalescedIds2;
    }

    public int getDrawableId() {
        return R.drawable.quantum_ic_delete_vd_theme_24;
    }

    public int getStringId() {
        return R.string.delete;
    }

    public boolean onClick() {
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this.context).dialerExecutorFactory()).createNonUiTaskBuilder(new CallLogItemDeletionWorker(this.context)).build().executeSerial(this.coalescedIds);
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.USER_DELETED_CALL_LOG_ITEM);
        return true;
    }
}

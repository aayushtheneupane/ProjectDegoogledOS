package com.android.dialer.calllog.p004ui;

import android.content.Context;
import android.support.p000v4.content.CursorLoader;
import com.android.dialer.calllog.database.contract.AnnotatedCallLogContract;

/* renamed from: com.android.dialer.calllog.ui.AnnotatedCallLogCursorLoader */
final class AnnotatedCallLogCursorLoader extends CursorLoader {
    AnnotatedCallLogCursorLoader(Context context) {
        super(context, AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI, (String[]) null, "call_type != ?", new String[]{Integer.toString(4)}, "timestamp DESC");
    }
}

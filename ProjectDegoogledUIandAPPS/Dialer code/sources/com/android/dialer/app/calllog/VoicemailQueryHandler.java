package com.android.dialer.app.calllog;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;

public class VoicemailQueryHandler extends AsyncQueryHandler {
    private VoicemailQueryHandler(ContentResolver contentResolver) {
        super(contentResolver);
        Assert.isMainThread();
    }

    public static void markAllNewVoicemailsAsOld(Context context) {
        DialerExecutorModule.postOnUiThread(new Runnable(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                new VoicemailQueryHandler(this.f$0.getContentResolver()).markNewVoicemailsAsOld(this.f$0, (Uri) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void markNewVoicemailsAsOld(Context context, Uri uri) {
        String[] strArr;
        StringBuilder sb = new StringBuilder();
        sb.append("new");
        sb.append(" = 1 AND ");
        sb.append("type");
        sb.append(" = ?");
        if (uri != null) {
            sb.append(" AND ");
            sb.append("voicemail_uri");
            sb.append(" = ?");
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("new", "0");
        Uri uri2 = CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL;
        String sb2 = sb.toString();
        if (uri == null) {
            strArr = new String[]{Integer.toString(4)};
        } else {
            strArr = new String[]{Integer.toString(4), uri.toString()};
        }
        startUpdate(50, (Object) null, uri2, contentValues, sb2, strArr);
        VoicemailNotificationJobService.cancelJob(context);
    }

    public static void markSingleNewVoicemailAsOld(Context context, Uri uri) {
        if (uri == null) {
            LogUtil.m8e("VoicemailQueryHandler.markSingleNewVoicemailAsOld", "voicemail URI is null", new Object[0]);
        } else {
            DialerExecutorModule.postOnUiThread(new Runnable(context, uri) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ Uri f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    new VoicemailQueryHandler(this.f$0.getContentResolver()).markNewVoicemailsAsOld(this.f$0, this.f$1);
                }
            });
        }
    }
}

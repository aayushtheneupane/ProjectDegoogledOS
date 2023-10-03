package com.android.dialer.app.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import com.android.dialer.common.LogUtil;
import com.android.dialer.database.CallLogQueryHandler;
import com.android.dialer.voicemail.listui.error.OmtpVoicemailMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailStatusCorruptionHandler$Source;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;

public class CallLogReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        if ("android.intent.action.NEW_VOICEMAIL".equals(intent.getAction())) {
            new CallLogQueryHandler(context, context.getContentResolver(), new CallLogQueryHandler.Listener() {
                public boolean onCallsFetched(Cursor cursor) {
                    return false;
                }

                public void onMissedCallsUnreadCountFetched(Cursor cursor) {
                }

                public void onVoicemailStatusFetched(Cursor cursor) {
                    OmtpVoicemailMessageCreator.maybeFixVoicemailStatus(context, cursor, VoicemailStatusCorruptionHandler$Source.Notification);
                }

                public void onVoicemailUnreadCountFetched(Cursor cursor) {
                }
            }, -1).fetchVoicemailStatus();
            BroadcastReceiver.PendingResult goAsync = goAsync();
            Objects.requireNonNull(goAsync);
            VisualVoicemailUpdateTask.scheduleTask(context, new Runnable(goAsync) {
                private final /* synthetic */ BroadcastReceiver.PendingResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.finish();
                }
            });
        } else if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            BroadcastReceiver.PendingResult goAsync2 = goAsync();
            Objects.requireNonNull(goAsync2);
            VisualVoicemailUpdateTask.scheduleTask(context, new Runnable(goAsync2) {
                private final /* synthetic */ BroadcastReceiver.PendingResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.finish();
                }
            });
        } else {
            LogUtil.m10w("CallLogReceiver.onReceive", GeneratedOutlineSupport.outline6("could not handle: ", intent), new Object[0]);
        }
    }
}

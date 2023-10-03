package android.support.p000v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

/* renamed from: android.support.v4.app.NotificationCompat$Action */
public class NotificationCompat$Action {
    public PendingIntent actionIntent;
    public int icon;
    private boolean mAllowGeneratedReplies;
    final Bundle mExtras;
    private final RemoteInput[] mRemoteInputs;
    private final int mSemanticAction;
    boolean mShowsUserInterface;
    public CharSequence title;

    public boolean getAllowGeneratedReplies() {
        return this.mAllowGeneratedReplies;
    }

    public RemoteInput[] getRemoteInputs() {
        return this.mRemoteInputs;
    }

    public int getSemanticAction() {
        return this.mSemanticAction;
    }
}

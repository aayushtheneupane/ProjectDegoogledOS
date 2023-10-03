package android.support.p000v4.app;

import android.os.Bundle;

/* renamed from: android.support.v4.app.NotificationCompatJellybean */
class NotificationCompatJellybean {
    private static final Object sExtrasLock = new Object();

    static {
        new Object();
    }

    static Bundle getBundleForAction(NotificationCompat$Action notificationCompat$Action) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("icon", notificationCompat$Action.icon);
        bundle2.putCharSequence("title", notificationCompat$Action.title);
        bundle2.putParcelable("actionIntent", notificationCompat$Action.actionIntent);
        Bundle bundle3 = notificationCompat$Action.mExtras;
        if (bundle3 != null) {
            bundle = new Bundle(bundle3);
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action.getAllowGeneratedReplies());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", toBundleArray(notificationCompat$Action.getRemoteInputs()));
        bundle2.putBoolean("showsUserInterface", notificationCompat$Action.mShowsUserInterface);
        bundle2.putInt("semanticAction", notificationCompat$Action.getSemanticAction());
        return bundle2;
    }

    private static Bundle[] toBundleArray(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        if (remoteInputArr.length <= 0) {
            return bundleArr;
        }
        RemoteInput remoteInput = remoteInputArr[0];
        new Bundle();
        throw null;
    }
}

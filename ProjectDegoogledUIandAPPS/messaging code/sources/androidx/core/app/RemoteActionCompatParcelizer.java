package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcelable;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.C0613c;
import androidx.versionedparcelable.C0615e;

public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(C0613c cVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.mIcon = (IconCompat) cVar.mo5298a((C0615e) remoteActionCompat.mIcon, 1);
        remoteActionCompat.mTitle = cVar.mo5299a(remoteActionCompat.mTitle, 2);
        remoteActionCompat.mContentDescription = cVar.mo5299a(remoteActionCompat.mContentDescription, 3);
        remoteActionCompat.mActionIntent = (PendingIntent) cVar.mo5297a((Parcelable) remoteActionCompat.mActionIntent, 4);
        remoteActionCompat.mEnabled = cVar.mo5300a(remoteActionCompat.mEnabled, 5);
        remoteActionCompat.mShouldShowIcon = cVar.mo5300a(remoteActionCompat.mShouldShowIcon, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, C0613c cVar) {
        cVar.mo5311h(false, false);
        cVar.mo5304b((C0615e) remoteActionCompat.mIcon, 1);
        cVar.mo5305b(remoteActionCompat.mTitle, 2);
        cVar.mo5305b(remoteActionCompat.mContentDescription, 3);
        cVar.writeParcelable(remoteActionCompat.mActionIntent, 4);
        cVar.mo5306b(remoteActionCompat.mEnabled, 5);
        cVar.mo5306b(remoteActionCompat.mShouldShowIcon, 6);
    }
}

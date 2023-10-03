package androidx.core.app;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.os.Build;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.C0615e;

public final class RemoteActionCompat implements C0615e {
    public PendingIntent mActionIntent;
    public CharSequence mContentDescription;
    public boolean mEnabled;
    public IconCompat mIcon;
    public boolean mShouldShowIcon;
    public CharSequence mTitle;

    public RemoteActionCompat(IconCompat iconCompat, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
        if (iconCompat != null) {
            this.mIcon = iconCompat;
            if (charSequence != null) {
                this.mTitle = charSequence;
                if (charSequence2 != null) {
                    this.mContentDescription = charSequence2;
                    if (pendingIntent != null) {
                        this.mActionIntent = pendingIntent;
                        this.mEnabled = true;
                        this.mShouldShowIcon = true;
                        return;
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public static RemoteActionCompat createFromRemoteAction(RemoteAction remoteAction) {
        if (remoteAction != null) {
            RemoteActionCompat remoteActionCompat = new RemoteActionCompat(IconCompat.m256a(remoteAction.getIcon()), remoteAction.getTitle(), remoteAction.getContentDescription(), remoteAction.getActionIntent());
            remoteActionCompat.setEnabled(remoteAction.isEnabled());
            int i = Build.VERSION.SDK_INT;
            remoteActionCompat.setShouldShowIcon(remoteAction.shouldShowIcon());
            return remoteActionCompat;
        }
        throw new NullPointerException();
    }

    public PendingIntent getActionIntent() {
        return this.mActionIntent;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setShouldShowIcon(boolean z) {
        this.mShouldShowIcon = z;
    }

    public boolean shouldShowIcon() {
        return this.mShouldShowIcon;
    }

    public RemoteAction toRemoteAction() {
        RemoteAction remoteAction = new RemoteAction(this.mIcon.mo3445md(), this.mTitle, this.mContentDescription, this.mActionIntent);
        remoteAction.setEnabled(isEnabled());
        int i = Build.VERSION.SDK_INT;
        remoteAction.setShouldShowIcon(shouldShowIcon());
        return remoteAction;
    }

    public RemoteActionCompat() {
    }

    public RemoteActionCompat(RemoteActionCompat remoteActionCompat) {
        if (remoteActionCompat != null) {
            this.mIcon = remoteActionCompat.mIcon;
            this.mTitle = remoteActionCompat.mTitle;
            this.mContentDescription = remoteActionCompat.mContentDescription;
            this.mActionIntent = remoteActionCompat.mActionIntent;
            this.mEnabled = remoteActionCompat.mEnabled;
            this.mShouldShowIcon = remoteActionCompat.mShouldShowIcon;
            return;
        }
        throw new NullPointerException();
    }
}

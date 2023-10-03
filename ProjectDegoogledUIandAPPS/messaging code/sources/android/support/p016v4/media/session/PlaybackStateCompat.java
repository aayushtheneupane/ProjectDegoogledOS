package android.support.p016v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.session.PlaybackStateCompat */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0109s();

    /* renamed from: Fe */
    final long f110Fe;

    /* renamed from: Ge */
    List f111Ge;

    /* renamed from: He */
    final long f112He;
    final long mActions;
    final int mErrorCode;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    final long mUpdateTime;

    PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List list, long j5, Bundle bundle) {
        this.mState = i;
        this.mPosition = j;
        this.f110Fe = j2;
        this.mSpeed = f;
        this.mActions = j3;
        this.mErrorCode = i2;
        this.mErrorMessage = charSequence;
        this.mUpdateTime = j4;
        this.f111Ge = new ArrayList(list);
        this.f112He = j5;
        this.mExtras = bundle;
    }

    /* renamed from: e */
    public static PlaybackStateCompat m102e(Object obj) {
        ArrayList arrayList = null;
        if (obj == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        if (customActions != null) {
            arrayList = new ArrayList(customActions.size());
            for (PlaybackState.CustomAction d : customActions) {
                arrayList.add(CustomAction.m103d(d));
            }
        }
        ArrayList arrayList2 = arrayList;
        int i2 = Build.VERSION.SDK_INT;
        return new PlaybackStateCompat(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), 0, playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList2, playbackState.getActiveQueueItemId(), playbackState.getExtras());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {" + "state=" + this.mState + ", position=" + this.mPosition + ", buffered position=" + this.f110Fe + ", speed=" + this.mSpeed + ", updated=" + this.mUpdateTime + ", actions=" + this.mActions + ", error code=" + this.mErrorCode + ", error message=" + this.mErrorMessage + ", custom actions=" + this.f111Ge + ", active item id=" + this.f112He + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.f110Fe);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, parcel, i);
        parcel.writeTypedList(this.f111Ge);
        parcel.writeLong(this.f112He);
        parcel.writeBundle(this.mExtras);
        parcel.writeInt(this.mErrorCode);
    }

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$CustomAction */
    public final class CustomAction implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C0110t();
        private final String mAction;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.mAction = str;
            this.mName = charSequence;
            this.mIcon = i;
            this.mExtras = bundle;
        }

        /* renamed from: d */
        public static CustomAction m103d(Object obj) {
            if (obj == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            return new CustomAction(customAction.getAction(), customAction.getName(), customAction.getIcon(), customAction.getExtras());
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("Action:mName='");
            Pa.append(this.mName);
            Pa.append(", mIcon=");
            Pa.append(this.mIcon);
            Pa.append(", mExtras=");
            Pa.append(this.mExtras);
            return Pa.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, parcel, i);
            parcel.writeInt(this.mIcon);
            parcel.writeBundle(this.mExtras);
        }

        CustomAction(Parcel parcel) {
            this.mAction = parcel.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.mIcon = parcel.readInt();
            this.mExtras = parcel.readBundle(C0107q.class.getClassLoader());
        }
    }

    PlaybackStateCompat(Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.f110Fe = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f111Ge = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f112He = parcel.readLong();
        this.mExtras = parcel.readBundle(C0107q.class.getClassLoader());
        this.mErrorCode = parcel.readInt();
    }
}

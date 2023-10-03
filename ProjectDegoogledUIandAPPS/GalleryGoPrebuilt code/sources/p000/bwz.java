package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.photosgo.editor.PresetThumbnail;

/* renamed from: bwz */
/* compiled from: PG */
public final class bwz implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PresetThumbnail(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PresetThumbnail[i];
    }
}

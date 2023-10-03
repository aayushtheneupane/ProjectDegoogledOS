package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.C0613c;

public class IconCompatParcelizer {
    public static IconCompat read(C0613c cVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = cVar.readInt(iconCompat.mType, 1);
        iconCompat.mData = cVar.mo5307c(iconCompat.mData, 2);
        iconCompat.f319zu = cVar.mo5297a(iconCompat.f319zu, 3);
        iconCompat.f315Au = cVar.readInt(iconCompat.f315Au, 4);
        iconCompat.f316Bu = cVar.readInt(iconCompat.f316Bu, 5);
        iconCompat.mTintList = (ColorStateList) cVar.mo5297a((Parcelable) iconCompat.mTintList, 6);
        iconCompat.f317Cu = cVar.mo5308d(iconCompat.f317Cu, 7);
        iconCompat.mo3444ld();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, C0613c cVar) {
        cVar.mo5311h(true, true);
        iconCompat.mo3440E(false);
        int i = iconCompat.mType;
        if (-1 != i) {
            cVar.mo5314q(i, 1);
        }
        byte[] bArr = iconCompat.mData;
        if (bArr != null) {
            cVar.mo5309d(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f319zu;
        if (parcelable != null) {
            cVar.writeParcelable(parcelable, 3);
        }
        int i2 = iconCompat.f315Au;
        if (i2 != 0) {
            cVar.mo5314q(i2, 4);
        }
        int i3 = iconCompat.f316Bu;
        if (i3 != 0) {
            cVar.mo5314q(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.mTintList;
        if (colorStateList != null) {
            cVar.writeParcelable(colorStateList, 6);
        }
        String str = iconCompat.f317Cu;
        if (str != null) {
            cVar.mo5310e(str, 7);
        }
    }
}

package com.android.contacts.util;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import androidx.core.content.ContextCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.activities.PeopleActivity;

public class MaterialColorMapUtils {
    private final TypedArray sPrimaryColors;
    private final TypedArray sSecondaryColors;

    public MaterialColorMapUtils(Resources resources) {
        this.sPrimaryColors = resources.obtainTypedArray(R.array.letter_tile_colors);
        this.sSecondaryColors = resources.obtainTypedArray(R.array.letter_tile_colors_dark);
    }

    public static class MaterialPalette implements Parcelable {
        public static final Parcelable.Creator<MaterialPalette> CREATOR = new Parcelable.Creator<MaterialPalette>() {
            public MaterialPalette createFromParcel(Parcel parcel) {
                return new MaterialPalette(parcel);
            }

            public MaterialPalette[] newArray(int i) {
                return new MaterialPalette[i];
            }
        };
        public final int mPrimaryColor;
        public final int mSecondaryColor;

        public int describeContents() {
            return 0;
        }

        public MaterialPalette(int i, int i2) {
            this.mPrimaryColor = i;
            this.mSecondaryColor = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || MaterialPalette.class != obj.getClass()) {
                return false;
            }
            MaterialPalette materialPalette = (MaterialPalette) obj;
            return this.mPrimaryColor == materialPalette.mPrimaryColor && this.mSecondaryColor == materialPalette.mSecondaryColor;
        }

        public int hashCode() {
            return ((this.mPrimaryColor + 31) * 31) + this.mSecondaryColor;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mPrimaryColor);
            parcel.writeInt(this.mSecondaryColor);
        }

        private MaterialPalette(Parcel parcel) {
            this.mPrimaryColor = parcel.readInt();
            this.mSecondaryColor = parcel.readInt();
        }
    }

    public MaterialPalette calculatePrimaryAndSecondaryColor(int i) {
        Trace.beginSection("calculatePrimaryAndSecondaryColor");
        float hue = hue(i);
        int i2 = 0;
        float f = Float.MAX_VALUE;
        for (int i3 = 0; i3 < this.sPrimaryColors.length(); i3++) {
            float abs = Math.abs(hue(this.sPrimaryColors.getColor(i3, 0)) - hue);
            if (abs < f) {
                i2 = i3;
                f = abs;
            }
        }
        Trace.endSection();
        return new MaterialPalette(this.sPrimaryColors.getColor(i2, 0), this.sSecondaryColors.getColor(i2, 0));
    }

    public static MaterialPalette getDefaultPrimaryAndSecondaryColors(Resources resources) {
        return new MaterialPalette(resources.getColor(R.color.quickcontact_default_photo_tint_color), resources.getColor(R.color.quickcontact_default_photo_tint_color_dark));
    }

    public static float hue(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        int max = Math.max(i4, Math.max(i2, i3));
        int min = Math.min(i4, Math.min(i2, i3));
        if (max == min) {
            return ContactPhotoManager.OFFSET_DEFAULT;
        }
        float f = (float) (max - min);
        float f2 = ((float) (max - i2)) / f;
        float f3 = ((float) (max - i3)) / f;
        float f4 = ((float) (max - i4)) / f;
        float f5 = (i2 == max ? f4 - f3 : i3 == max ? (f2 + 2.0f) - f4 : (f3 + 4.0f) - f2) / 6.0f;
        return f5 < ContactPhotoManager.OFFSET_DEFAULT ? f5 + 1.0f : f5;
    }

    public static int getStatusBarColor(Activity activity) {
        if ((activity instanceof PeopleActivity) && ((PeopleActivity) activity).isGroupView()) {
            return ContextCompat.getColor(activity, R.color.group_primary_color);
        }
        return ContextCompat.getColor(activity, R.color.primary_color);
    }

    public static int getToolBarColor(Activity activity) {
        if ((activity instanceof PeopleActivity) && ((PeopleActivity) activity).isGroupView()) {
            return ContextCompat.getColor(activity, R.color.group_primary_color);
        }
        return ContextCompat.getColor(activity, R.color.primary_color);
    }
}

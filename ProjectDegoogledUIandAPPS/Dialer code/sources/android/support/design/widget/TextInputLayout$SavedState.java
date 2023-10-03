package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class TextInputLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<TextInputLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<TextInputLayout$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new TextInputLayout$SavedState(parcel, classLoader);
        }

        public Object[] newArray(int i) {
            return new TextInputLayout$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new TextInputLayout$SavedState(parcel, (ClassLoader) null);
        }
    };
    CharSequence error;
    boolean isPasswordToggledVisible;

    TextInputLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.isPasswordToggledVisible = parcel.readInt() != 1 ? false : true;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TextInputLayout.SavedState{");
        outline13.append(Integer.toHexString(System.identityHashCode(this)));
        outline13.append(" error=");
        return GeneratedOutlineSupport.outline11(outline13, this.error, "}");
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        TextUtils.writeToParcel(this.error, parcel, i);
        parcel.writeInt(this.isPasswordToggledVisible ? 1 : 0);
    }
}

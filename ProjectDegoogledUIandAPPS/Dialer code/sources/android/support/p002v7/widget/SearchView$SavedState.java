package android.support.p002v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v7.widget.SearchView$SavedState */
class SearchView$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SearchView$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SearchView$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SearchView$SavedState(parcel, classLoader);
        }

        public Object[] newArray(int i) {
            return new SearchView$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new SearchView$SavedState(parcel, (ClassLoader) null);
        }
    };
    boolean isIconified;

    public SearchView$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isIconified = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SearchView.SavedState{");
        outline13.append(Integer.toHexString(System.identityHashCode(this)));
        outline13.append(" isIconified=");
        outline13.append(this.isIconified);
        outline13.append("}");
        return outline13.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(Boolean.valueOf(this.isIconified));
    }
}

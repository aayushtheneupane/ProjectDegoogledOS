package androidx.preference;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: h */
    public final boolean mo1189h() {
        return false;
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, C0071co.m4652a(context, (int) R.attr.preferenceCategoryStyle, 16842892), (byte[]) null);
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        if (Build.VERSION.SDK_INT >= 28) {
            ady.f16382a.setAccessibilityHeading(true);
        } else {
            int i = Build.VERSION.SDK_INT;
        }
    }

    @Deprecated
    /* renamed from: a */
    public final void mo1173a(C0354mx mxVar) {
        C0353mw mwVar;
        if (Build.VERSION.SDK_INT < 28) {
            int i = Build.VERSION.SDK_INT;
            AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = mxVar.f15257a.getCollectionItemInfo();
            if (collectionItemInfo != null) {
                mwVar = new C0353mw(collectionItemInfo);
            } else {
                mwVar = null;
            }
            if (mwVar != null) {
                int i2 = Build.VERSION.SDK_INT;
                int rowIndex = ((AccessibilityNodeInfo.CollectionItemInfo) mwVar.f15256a).getRowIndex();
                int i3 = Build.VERSION.SDK_INT;
                int rowSpan = ((AccessibilityNodeInfo.CollectionItemInfo) mwVar.f15256a).getRowSpan();
                int i4 = Build.VERSION.SDK_INT;
                int columnIndex = ((AccessibilityNodeInfo.CollectionItemInfo) mwVar.f15256a).getColumnIndex();
                int i5 = Build.VERSION.SDK_INT;
                int columnSpan = ((AccessibilityNodeInfo.CollectionItemInfo) mwVar.f15256a).getColumnSpan();
                int i6 = Build.VERSION.SDK_INT;
                mxVar.mo9429b((Object) C0353mw.m14775a(rowIndex, rowSpan, columnIndex, columnSpan, true, ((AccessibilityNodeInfo.CollectionItemInfo) mwVar.f15256a).isSelected()));
            }
        }
    }

    /* renamed from: c */
    public final boolean mo1162c() {
        return !super.mo1189h();
    }
}

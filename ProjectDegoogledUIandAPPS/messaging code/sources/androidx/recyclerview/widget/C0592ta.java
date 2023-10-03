package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.ta */
class C0592ta {
    /* renamed from: a */
    static int m912a(C0582oa oaVar, C0536I i, View view, View view2, C0558ca caVar, boolean z, boolean z2) {
        int i2;
        if (caVar.getChildCount() == 0 || oaVar.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(caVar.getPosition(view), caVar.getPosition(view2));
        int max = Math.max(caVar.getPosition(view), caVar.getPosition(view2));
        if (z2) {
            i2 = Math.max(0, (oaVar.getItemCount() - max) - 1);
        } else {
            i2 = Math.max(0, min);
        }
        if (!z) {
            return i2;
        }
        return Math.round((((float) i2) * (((float) Math.abs(i.getDecoratedEnd(view2) - i.getDecoratedStart(view))) / ((float) (Math.abs(caVar.getPosition(view) - caVar.getPosition(view2)) + 1)))) + ((float) (i.getStartAfterPadding() - i.getDecoratedStart(view))));
    }

    /* renamed from: b */
    static int m913b(C0582oa oaVar, C0536I i, View view, View view2, C0558ca caVar, boolean z) {
        if (caVar.getChildCount() == 0 || oaVar.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return oaVar.getItemCount();
        }
        return (int) ((((float) (i.getDecoratedEnd(view2) - i.getDecoratedStart(view))) / ((float) (Math.abs(caVar.getPosition(view) - caVar.getPosition(view2)) + 1))) * ((float) oaVar.getItemCount()));
    }

    /* renamed from: a */
    static int m911a(C0582oa oaVar, C0536I i, View view, View view2, C0558ca caVar, boolean z) {
        if (caVar.getChildCount() == 0 || oaVar.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(caVar.getPosition(view) - caVar.getPosition(view2)) + 1;
        }
        return Math.min(i.getTotalSpace(), i.getDecoratedEnd(view2) - i.getDecoratedStart(view));
    }
}

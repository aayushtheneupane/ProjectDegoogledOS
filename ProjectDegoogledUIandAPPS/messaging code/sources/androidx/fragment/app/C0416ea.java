package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
/* renamed from: androidx.fragment.app.ea */
public abstract class C0416ea {
    /* renamed from: e */
    protected static boolean m401e(List list) {
        return list == null || list.isEmpty();
    }

    /* renamed from: a */
    public abstract Object mo4211a(Object obj, Object obj2, Object obj3);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo4233a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], view.getWidth() + iArr[0], view.getHeight() + iArr[1]);
    }

    /* renamed from: a */
    public abstract void mo4212a(ViewGroup viewGroup, Object obj);

    /* renamed from: a */
    public abstract void mo4213a(Object obj, Rect rect);

    /* renamed from: a */
    public abstract void mo4214a(Object obj, View view);

    /* renamed from: a */
    public abstract void mo4215a(Object obj, View view, ArrayList arrayList);

    /* renamed from: a */
    public abstract void mo4216a(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    /* renamed from: a */
    public abstract void mo4217a(Object obj, ArrayList arrayList);

    /* renamed from: a */
    public abstract void mo4218a(Object obj, ArrayList arrayList, ArrayList arrayList2);

    /* renamed from: b */
    public abstract Object mo4219b(Object obj, Object obj2, Object obj3);

    /* renamed from: b */
    public abstract void mo4220b(Object obj, View view);

    /* renamed from: b */
    public abstract void mo4221b(Object obj, View view, ArrayList arrayList);

    /* renamed from: b */
    public abstract void mo4222b(Object obj, ArrayList arrayList, ArrayList arrayList2);

    /* renamed from: i */
    public abstract boolean mo4223i(Object obj);

    /* renamed from: j */
    public abstract Object mo4224j(Object obj);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4234a(ArrayList arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                mo4234a(arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4235a(Map map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    mo4235a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* renamed from: a */
    protected static void m399a(List list, View view) {
        int size = list.size();
        if (!m400a(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!m400a(list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m400a(List list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }
}

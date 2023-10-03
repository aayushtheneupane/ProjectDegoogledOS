package p000;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: hg */
/* compiled from: PG */
public abstract class C0202hg {
    /* renamed from: a */
    public abstract Object mo280a(Object obj, Object obj2, Object obj3);

    /* renamed from: a */
    public abstract void mo281a(ViewGroup viewGroup, Object obj);

    /* renamed from: a */
    public abstract void mo282a(Object obj, Rect rect);

    /* renamed from: a */
    public abstract void mo283a(Object obj, View view);

    /* renamed from: a */
    public abstract void mo284a(Object obj, View view, ArrayList arrayList);

    /* renamed from: a */
    public abstract void mo285a(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    /* renamed from: a */
    public abstract void mo286a(Object obj, ArrayList arrayList);

    /* renamed from: a */
    public abstract void mo287a(Object obj, ArrayList arrayList, ArrayList arrayList2);

    /* renamed from: a */
    public void mo288a(Object obj, C0259jj jjVar, Runnable runnable) {
        throw null;
    }

    /* renamed from: a */
    public abstract boolean mo289a(Object obj);

    /* renamed from: b */
    public abstract Object mo290b(Object obj);

    /* renamed from: b */
    public abstract void mo291b(Object obj, View view);

    /* renamed from: b */
    public abstract void mo292b(Object obj, View view, ArrayList arrayList);

    /* renamed from: b */
    public abstract void mo293b(Object obj, ArrayList arrayList, ArrayList arrayList2);

    /* renamed from: c */
    public abstract Object mo294c(Object obj);

    /* renamed from: c */
    public abstract void mo295c(Object obj, View view);

    /* renamed from: a */
    protected static void m11411a(List list, View view) {
        int size = list.size();
        if (!m11413a(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!m11413a(list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7391a(ArrayList arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = Build.VERSION.SDK_INT;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                mo7391a(arrayList, viewGroup.getChildAt(i2));
            }
            return;
        }
        arrayList.add(view);
    }

    /* renamed from: a */
    private static boolean m11413a(List list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7392a(Map map, View view) {
        if (view.getVisibility() == 0) {
            String m = C0340mj.m14722m(view);
            if (m != null) {
                map.put(m, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    mo7392a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* renamed from: a */
    protected static final void m11410a(View view, Rect rect) {
        if (C0340mj.m14735z(view)) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset((float) view.getLeft(), (float) view.getTop());
            ViewParent parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
                view2.getMatrix().mapRect(rectF);
                rectF.offset((float) view2.getLeft(), (float) view2.getTop());
                parent = view2.getParent();
            }
            int[] iArr = new int[2];
            view.getRootView().getLocationOnScreen(iArr);
            rectF.offset((float) iArr[0], (float) iArr[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    /* renamed from: a */
    protected static boolean m11412a(List list) {
        return list == null || list.isEmpty();
    }
}

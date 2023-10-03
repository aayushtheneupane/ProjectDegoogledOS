package p000;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* renamed from: mi */
/* compiled from: PG */
final class C0339mi {

    /* renamed from: a */
    public static final ArrayList f15230a = new ArrayList();

    /* renamed from: b */
    public WeakHashMap f15231b = null;

    /* renamed from: c */
    public WeakReference f15232c = null;

    /* renamed from: d */
    private SparseArray f15233d = null;

    /* renamed from: a */
    static C0339mi m14670a(View view) {
        C0339mi miVar = (C0339mi) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (miVar != null) {
            return miVar;
        }
        C0339mi miVar2 = new C0339mi();
        view.setTag(R.id.tag_unhandled_key_event_manager, miVar2);
        return miVar2;
    }

    /* renamed from: a */
    public final View mo9394a(View view, KeyEvent keyEvent) {
        WeakHashMap weakHashMap = this.f15231b;
        if (weakHashMap == null || !weakHashMap.containsKey(view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View a = mo9394a(viewGroup.getChildAt(childCount), keyEvent);
                if (a != null) {
                    return a;
                }
            }
        }
        if (m14671b(view)) {
            return view;
        }
        return null;
    }

    /* renamed from: a */
    public final SparseArray mo9393a() {
        if (this.f15233d == null) {
            this.f15233d = new SparseArray();
        }
        return this.f15233d;
    }

    /* renamed from: b */
    public static final boolean m14671b(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            return false;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((C0338mh) arrayList.get(size)).mo9392a()) {
                return true;
            }
        }
        return false;
    }
}

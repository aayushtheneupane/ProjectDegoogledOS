package p000;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: afg */
/* compiled from: PG */
public final class afg extends C0202hg {
    /* renamed from: b */
    public final void mo291b(Object obj, View view) {
        if (obj != null) {
            ((afl) obj).mo319d(view);
        }
    }

    /* renamed from: a */
    public final void mo286a(Object obj, ArrayList arrayList) {
        afl afl = (afl) obj;
        if (afl != null) {
            int i = 0;
            if (afl instanceof afs) {
                afs afs = (afs) afl;
                int j = afs.mo332j();
                while (i < j) {
                    mo286a((Object) afs.mo330a(i), arrayList);
                    i++;
                }
            } else if (!m317a(afl) && m11412a((List) afl.f325d)) {
                int size = arrayList.size();
                while (i < size) {
                    afl.mo319d((View) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo281a(ViewGroup viewGroup, Object obj) {
        afl afl = (afl) obj;
        if (!afp.f345a.contains(viewGroup) && C0340mj.m14732w(viewGroup)) {
            afp.f345a.add(viewGroup);
            afl g = afl.clone();
            ArrayList arrayList = (ArrayList) afp.m380a().get(viewGroup);
            if (arrayList != null && arrayList.size() > 0) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((afl) arrayList.get(i)).mo313b((View) viewGroup);
                }
            }
            if (g != null) {
                g.mo304a(viewGroup, true);
            }
            if (((flw) viewGroup.getTag(R.id.transition_current_scene)) == null) {
                viewGroup.setTag(R.id.transition_current_scene, (Object) null);
                if (g != null && viewGroup != null) {
                    afo afo = new afo(g, viewGroup);
                    viewGroup.addOnAttachStateChangeListener(afo);
                    viewGroup.getViewTreeObserver().addOnPreDrawListener(afo);
                    return;
                }
                return;
            }
            throw null;
        }
    }

    /* renamed from: a */
    public final boolean mo289a(Object obj) {
        return obj instanceof afl;
    }

    /* renamed from: b */
    public final Object mo290b(Object obj) {
        if (obj != null) {
            return ((afl) obj).clone();
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m317a(afl afl) {
        return !m11412a((List) afl.f324c) || !m11412a((List) null) || !m11412a((List) null);
    }

    /* renamed from: a */
    public final Object mo280a(Object obj, Object obj2, Object obj3) {
        afs afs = new afs();
        if (obj != null) {
            afs.mo331a((afl) obj);
        }
        if (obj2 != null) {
            afs.mo331a((afl) obj2);
        }
        if (obj3 != null) {
            afs.mo331a((afl) obj3);
        }
        return afs;
    }

    /* renamed from: c */
    public final void mo295c(Object obj, View view) {
        ((afl) obj).mo321e(view);
    }

    /* renamed from: b */
    public final void mo293b(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        afl afl = (afl) obj;
        int i = 0;
        if (afl instanceof afs) {
            afs afs = (afs) afl;
            int j = afs.mo332j();
            while (i < j) {
                mo293b((Object) afs.mo330a(i), arrayList, arrayList2);
                i++;
            }
        } else if (!m317a(afl)) {
            ArrayList arrayList3 = afl.f325d;
            if (arrayList3.size() == arrayList.size() && arrayList3.containsAll(arrayList)) {
                int size = arrayList2 != null ? arrayList2.size() : 0;
                while (i < size) {
                    afl.mo319d((View) arrayList2.get(i));
                    i++;
                }
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    afl.mo321e((View) arrayList.get(size2));
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo292b(Object obj, View view, ArrayList arrayList) {
        ((afl) obj).mo302a((afk) new afc(view, arrayList));
    }

    /* renamed from: a */
    public final void mo285a(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((afl) obj).mo302a((afk) new afd(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    /* renamed from: a */
    public final void mo282a(Object obj, Rect rect) {
        if (obj != null) {
            ((afl) obj).mo306a(new fxk((char[]) null));
        }
    }

    /* renamed from: a */
    public final void mo283a(Object obj, View view) {
        if (view != null) {
            m11410a(view, new Rect());
            ((afl) obj).mo306a(new fxk((byte[]) null));
        }
    }

    /* renamed from: a */
    public final void mo288a(Object obj, C0259jj jjVar, Runnable runnable) {
        afl afl = (afl) obj;
        jjVar.mo9160a(new afe(afl));
        afl.mo302a((afk) new aff(runnable));
    }

    /* renamed from: a */
    public final void mo284a(Object obj, View view, ArrayList arrayList) {
        afs afs = (afs) obj;
        ArrayList arrayList2 = afs.f325d;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            m11411a((List) arrayList2, (View) arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        mo286a((Object) afs, arrayList);
    }

    /* renamed from: a */
    public final void mo287a(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        afs afs = (afs) obj;
        if (afs != null) {
            afs.f325d.clear();
            afs.f325d.addAll(arrayList2);
            mo293b((Object) afs, arrayList, arrayList2);
        }
    }

    /* renamed from: c */
    public final Object mo294c(Object obj) {
        if (obj == null) {
            return null;
        }
        afs afs = new afs();
        afs.mo331a((afl) obj);
        return afs;
    }
}

package p000;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: hc */
/* compiled from: PG */
final class C0198hc extends C0202hg {
    /* renamed from: b */
    public final void mo291b(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    /* renamed from: a */
    public final void mo286a(Object obj, ArrayList arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    mo286a((Object) transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
            } else if (!m11178a(transition) && m11412a((List) transition.getTargets())) {
                int size = arrayList.size();
                while (i < size) {
                    transition.addTarget((View) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo281a(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    /* renamed from: a */
    public final boolean mo289a(Object obj) {
        return obj instanceof Transition;
    }

    /* renamed from: b */
    public final Object mo290b(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m11178a(Transition transition) {
        return !m11412a((List) transition.getTargetIds()) || !m11412a((List) transition.getTargetNames()) || !m11412a((List) transition.getTargetTypes());
    }

    /* renamed from: a */
    public final Object mo280a(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.addTransition((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.addTransition((Transition) obj3);
        }
        return transitionSet;
    }

    /* renamed from: c */
    public final void mo295c(Object obj, View view) {
        ((Transition) obj).removeTarget(view);
    }

    /* renamed from: b */
    public final void mo293b(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                mo293b((Object) transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
        } else if (!m11178a(transition) && (targets = transition.getTargets()) != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            int size = arrayList2 != null ? arrayList2.size() : 0;
            while (i < size) {
                transition.addTarget((View) arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                transition.removeTarget((View) arrayList.get(size2));
            }
        }
    }

    /* renamed from: b */
    public final void mo292b(Object obj, View view, ArrayList arrayList) {
        ((Transition) obj).addListener(new C0193gy(view, arrayList));
    }

    /* renamed from: a */
    public final void mo285a(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((Transition) obj).addListener(new C0194gz(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    /* renamed from: a */
    public final void mo282a(Object obj, Rect rect) {
        if (obj != null) {
            ((Transition) obj).setEpicenterCallback(new C0197hb(rect));
        }
    }

    /* renamed from: a */
    public final void mo283a(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            m11410a(view, rect);
            ((Transition) obj).setEpicenterCallback(new C0192gx(rect));
        }
    }

    /* renamed from: a */
    public final void mo288a(Object obj, C0259jj jjVar, Runnable runnable) {
        ((Transition) obj).addListener(new C0196ha(runnable));
    }

    /* renamed from: a */
    public final void mo284a(Object obj, View view, ArrayList arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            m11411a(targets, (View) arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        mo286a((Object) transitionSet, arrayList);
    }

    /* renamed from: a */
    public final void mo287a(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            mo293b((Object) transitionSet, arrayList, arrayList2);
        }
    }

    /* renamed from: c */
    public final Object mo294c(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }
}

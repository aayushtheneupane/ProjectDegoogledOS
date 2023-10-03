package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.o */
public class C0581o extends C0594ua {

    /* renamed from: ot */
    private static TimeInterpolator f653ot;

    /* renamed from: kt */
    private ArrayList f654kt = new ArrayList();

    /* renamed from: lt */
    private ArrayList f655lt = new ArrayList();
    ArrayList mAddAnimations = new ArrayList();
    ArrayList mAdditionsList = new ArrayList();
    ArrayList mChangeAnimations = new ArrayList();
    ArrayList mChangesList = new ArrayList();
    ArrayList mMoveAnimations = new ArrayList();
    ArrayList mMovesList = new ArrayList();
    ArrayList mRemoveAnimations = new ArrayList();

    /* renamed from: mt */
    private ArrayList f656mt = new ArrayList();

    /* renamed from: nt */
    private ArrayList f657nt = new ArrayList();

    /* renamed from: H */
    private void m892H(C0586qa qaVar) {
        if (f653ot == null) {
            f653ot = new ValueAnimator().getInterpolator();
        }
        qaVar.itemView.animate().setInterpolator(f653ot);
        mo5179w(qaVar);
    }

    /* renamed from: a */
    public boolean mo5168a(C0586qa qaVar, int i, int i2, int i3, int i4) {
        View view = qaVar.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) qaVar.itemView.getTranslationY());
        m892H(qaVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            mo5252s(qaVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX((float) (-i5));
        }
        if (i6 != 0) {
            view.setTranslationY((float) (-i6));
        }
        this.f656mt.add(new C0579n(qaVar, translationX, translationY, i3, i4));
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo5171b(C0586qa qaVar, int i, int i2, int i3, int i4) {
        View view = qaVar.itemView;
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (i5 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i6 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animate = view.animate();
        this.mMoveAnimations.add(qaVar);
        animate.setDuration(getMoveDuration()).setListener(new C0571j(this, qaVar, i5, view, i6, animate)).start();
    }

    /* access modifiers changed from: package-private */
    public void cancelAll(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((C0586qa) list.get(size)).itemView.animate().cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimations() {
        int size = this.f656mt.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            C0579n nVar = (C0579n) this.f656mt.get(size);
            View view = nVar.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            mo5252s(nVar.holder);
            this.f656mt.remove(size);
        }
        int size2 = this.f654kt.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            mo5254u((C0586qa) this.f654kt.get(size2));
            this.f654kt.remove(size2);
        }
        int size3 = this.f655lt.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            C0586qa qaVar = (C0586qa) this.f655lt.get(size3);
            qaVar.itemView.setAlpha(1.0f);
            mo5250q(qaVar);
            this.f655lt.remove(size3);
        }
        int size4 = this.f657nt.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            C0577m mVar = (C0577m) this.f657nt.get(size4);
            C0586qa qaVar2 = mVar.oldHolder;
            if (qaVar2 != null) {
                m894a(mVar, qaVar2);
            }
            C0586qa qaVar3 = mVar.newHolder;
            if (qaVar3 != null) {
                m894a(mVar, qaVar3);
            }
        }
        this.f657nt.clear();
        if (isRunning()) {
            int size5 = this.mMovesList.size();
            while (true) {
                size5--;
                if (size5 < 0) {
                    break;
                }
                ArrayList arrayList = (ArrayList) this.mMovesList.get(size5);
                int size6 = arrayList.size();
                while (true) {
                    size6--;
                    if (size6 >= 0) {
                        C0579n nVar2 = (C0579n) arrayList.get(size6);
                        View view2 = nVar2.holder.itemView;
                        view2.setTranslationY(0.0f);
                        view2.setTranslationX(0.0f);
                        mo5252s(nVar2.holder);
                        arrayList.remove(size6);
                        if (arrayList.isEmpty()) {
                            this.mMovesList.remove(arrayList);
                        }
                    }
                }
            }
            int size7 = this.mAdditionsList.size();
            while (true) {
                size7--;
                if (size7 < 0) {
                    break;
                }
                ArrayList arrayList2 = (ArrayList) this.mAdditionsList.get(size7);
                int size8 = arrayList2.size();
                while (true) {
                    size8--;
                    if (size8 >= 0) {
                        C0586qa qaVar4 = (C0586qa) arrayList2.get(size8);
                        qaVar4.itemView.setAlpha(1.0f);
                        mo5250q(qaVar4);
                        arrayList2.remove(size8);
                        if (arrayList2.isEmpty()) {
                            this.mAdditionsList.remove(arrayList2);
                        }
                    }
                }
            }
            int size9 = this.mChangesList.size();
            while (true) {
                size9--;
                if (size9 >= 0) {
                    ArrayList arrayList3 = (ArrayList) this.mChangesList.get(size9);
                    int size10 = arrayList3.size();
                    while (true) {
                        size10--;
                        if (size10 >= 0) {
                            C0577m mVar2 = (C0577m) arrayList3.get(size10);
                            C0586qa qaVar5 = mVar2.oldHolder;
                            if (qaVar5 != null) {
                                m894a(mVar2, qaVar5);
                            }
                            C0586qa qaVar6 = mVar2.newHolder;
                            if (qaVar6 != null) {
                                m894a(mVar2, qaVar6);
                            }
                            if (arrayList3.isEmpty()) {
                                this.mChangesList.remove(arrayList3);
                            }
                        }
                    }
                } else {
                    cancelAll(this.mRemoveAnimations);
                    cancelAll(this.mMoveAnimations);
                    cancelAll(this.mAddAnimations);
                    cancelAll(this.mChangeAnimations);
                    dispatchAnimationsFinished();
                    return;
                }
            }
        }
    }

    public boolean isRunning() {
        return !this.f655lt.isEmpty() || !this.f657nt.isEmpty() || !this.f656mt.isEmpty() || !this.f654kt.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }

    /* renamed from: n */
    public boolean mo5176n(C0586qa qaVar) {
        m892H(qaVar);
        qaVar.itemView.setAlpha(0.0f);
        this.f655lt.add(qaVar);
        return true;
    }

    /* renamed from: o */
    public boolean mo5177o(C0586qa qaVar) {
        m892H(qaVar);
        this.f654kt.add(qaVar);
        return true;
    }

    public void runPendingAnimations() {
        boolean z = !this.f654kt.isEmpty();
        boolean z2 = !this.f656mt.isEmpty();
        boolean z3 = !this.f657nt.isEmpty();
        boolean z4 = !this.f655lt.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator it = this.f654kt.iterator();
            while (it.hasNext()) {
                C0586qa qaVar = (C0586qa) it.next();
                View view = qaVar.itemView;
                ViewPropertyAnimator animate = view.animate();
                this.mRemoveAnimations.add(qaVar);
                animate.setDuration(getRemoveDuration()).alpha(0.0f).setListener(new C0567h(this, qaVar, animate, view)).start();
            }
            this.f654kt.clear();
            if (z2) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f656mt);
                this.mMovesList.add(arrayList);
                this.f656mt.clear();
                C0561e eVar = new C0561e(this, arrayList);
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((C0579n) arrayList.get(0)).holder.itemView, eVar, getRemoveDuration());
                } else {
                    eVar.run();
                }
            }
            if (z3) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.f657nt);
                this.mChangesList.add(arrayList2);
                this.f657nt.clear();
                C0563f fVar = new C0563f(this, arrayList2);
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((C0577m) arrayList2.get(0)).oldHolder.itemView, fVar, getRemoveDuration());
                } else {
                    fVar.run();
                }
            }
            if (z4) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.f655lt);
                this.mAdditionsList.add(arrayList3);
                this.f655lt.clear();
                C0565g gVar = new C0565g(this, arrayList3);
                if (z || z2 || z3) {
                    long j = 0;
                    long removeDuration = z ? getRemoveDuration() : 0;
                    long moveDuration = z2 ? getMoveDuration() : 0;
                    if (z3) {
                        j = getChangeDuration();
                    }
                    ViewCompat.postOnAnimationDelayed(((C0586qa) arrayList3.get(0)).itemView, gVar, Math.max(moveDuration, j) + removeDuration);
                    return;
                }
                gVar.run();
            }
        }
    }

    /* renamed from: w */
    public void mo5179w(C0586qa qaVar) {
        View view = qaVar.itemView;
        view.animate().cancel();
        int size = this.f656mt.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((C0579n) this.f656mt.get(size)).holder == qaVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                mo5252s(qaVar);
                this.f656mt.remove(size);
            }
        }
        m893a((List) this.f657nt, qaVar);
        if (this.f654kt.remove(qaVar)) {
            view.setAlpha(1.0f);
            mo5254u(qaVar);
        }
        if (this.f655lt.remove(qaVar)) {
            view.setAlpha(1.0f);
            mo5250q(qaVar);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.mChangesList.get(size2);
            m893a((List) arrayList, qaVar);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((C0579n) arrayList2.get(size4)).holder == qaVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    mo5252s(qaVar);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size5);
            if (arrayList3.remove(qaVar)) {
                view.setAlpha(1.0f);
                mo5250q(qaVar);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(qaVar);
        this.mAddAnimations.remove(qaVar);
        this.mChangeAnimations.remove(qaVar);
        this.mMoveAnimations.remove(qaVar);
        dispatchFinishedWhenDone();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: x */
    public void mo5180x(C0586qa qaVar) {
        View view = qaVar.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.mAddAnimations.add(qaVar);
        animate.alpha(1.0f).setDuration(getAddDuration()).setListener(new C0569i(this, qaVar, view, animate)).start();
    }

    /* renamed from: a */
    public boolean mo5169a(C0586qa qaVar, C0586qa qaVar2, int i, int i2, int i3, int i4) {
        if (qaVar == qaVar2) {
            return mo5168a(qaVar, i, i2, i3, i4);
        }
        float translationX = qaVar.itemView.getTranslationX();
        float translationY = qaVar.itemView.getTranslationY();
        float alpha = qaVar.itemView.getAlpha();
        m892H(qaVar);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        qaVar.itemView.setTranslationX(translationX);
        qaVar.itemView.setTranslationY(translationY);
        qaVar.itemView.setAlpha(alpha);
        if (qaVar2 != null) {
            m892H(qaVar2);
            qaVar2.itemView.setTranslationX((float) (-i5));
            qaVar2.itemView.setTranslationY((float) (-i6));
            qaVar2.itemView.setAlpha(0.0f);
        }
        this.f657nt.add(new C0577m(qaVar, qaVar2, i, i2, i3, i4));
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5167a(C0577m mVar) {
        View view;
        C0586qa qaVar = mVar.oldHolder;
        View view2 = null;
        if (qaVar == null) {
            view = null;
        } else {
            view = qaVar.itemView;
        }
        C0586qa qaVar2 = mVar.newHolder;
        if (qaVar2 != null) {
            view2 = qaVar2.itemView;
        }
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(getChangeDuration());
            this.mChangeAnimations.add(mVar.oldHolder);
            duration.translationX((float) (mVar.toX - mVar.fromX));
            duration.translationY((float) (mVar.toY - mVar.fromY));
            duration.alpha(0.0f).setListener(new C0573k(this, mVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator animate = view2.animate();
            this.mChangeAnimations.add(mVar.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new C0575l(this, mVar, animate, view2)).start();
        }
    }

    /* renamed from: a */
    private void m893a(List list, C0586qa qaVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            C0577m mVar = (C0577m) list.get(size);
            if (m894a(mVar, qaVar) && mVar.oldHolder == null && mVar.newHolder == null) {
                list.remove(mVar);
            }
        }
    }

    /* renamed from: a */
    private boolean m894a(C0577m mVar, C0586qa qaVar) {
        boolean z = false;
        if (mVar.newHolder == qaVar) {
            mVar.newHolder = null;
        } else if (mVar.oldHolder != qaVar) {
            return false;
        } else {
            mVar.oldHolder = null;
            z = true;
        }
        qaVar.itemView.setAlpha(1.0f);
        qaVar.itemView.setTranslationX(0.0f);
        qaVar.itemView.setTranslationY(0.0f);
        mo5240b(qaVar, z);
        return true;
    }

    /* renamed from: a */
    public boolean mo5170a(C0586qa qaVar, List list) {
        if (list.isEmpty()) {
            if (!(!this.mSupportsChangeAnimations || qaVar.isInvalid())) {
                return false;
            }
        }
        return true;
    }
}

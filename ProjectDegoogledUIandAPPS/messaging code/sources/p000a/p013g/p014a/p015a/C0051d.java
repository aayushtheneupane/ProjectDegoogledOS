package p000a.p013g.p014a.p015a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import p000a.p005b.C0015b;

/* renamed from: a.g.a.a.d */
class C0051d extends Drawable.ConstantState {
    ArrayList mAnimators;
    int mChangingConfigurations;
    C0015b mTargetNameMap;
    C0064q mVectorDrawable;

    /* renamed from: vc */
    AnimatorSet f36vc;

    public C0051d(C0051d dVar, Drawable.Callback callback, Resources resources) {
        if (dVar != null) {
            this.mChangingConfigurations = dVar.mChangingConfigurations;
            C0064q qVar = dVar.mVectorDrawable;
            if (qVar != null) {
                Drawable.ConstantState constantState = qVar.getConstantState();
                if (resources != null) {
                    this.mVectorDrawable = (C0064q) constantState.newDrawable(resources);
                } else {
                    this.mVectorDrawable = (C0064q) constantState.newDrawable();
                }
                C0064q qVar2 = this.mVectorDrawable;
                qVar2.mutate();
                this.mVectorDrawable = qVar2;
                this.mVectorDrawable.setCallback(callback);
                this.mVectorDrawable.setBounds(dVar.mVectorDrawable.getBounds());
                this.mVectorDrawable.setAllowCaching(false);
            }
            ArrayList arrayList = dVar.mAnimators;
            if (arrayList != null) {
                int size = arrayList.size();
                this.mAnimators = new ArrayList(size);
                this.mTargetNameMap = new C0015b(size);
                for (int i = 0; i < size; i++) {
                    Animator animator = (Animator) dVar.mAnimators.get(i);
                    Animator clone = animator.clone();
                    String str = (String) dVar.mTargetNameMap.get(animator);
                    clone.setTarget(this.mVectorDrawable.getTargetByName(str));
                    this.mAnimators.add(clone);
                    this.mTargetNameMap.put(clone, str);
                }
                if (this.f36vc == null) {
                    this.f36vc = new AnimatorSet();
                }
                this.f36vc.playTogether(this.mAnimators);
            }
        }
    }

    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    public Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    public Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}

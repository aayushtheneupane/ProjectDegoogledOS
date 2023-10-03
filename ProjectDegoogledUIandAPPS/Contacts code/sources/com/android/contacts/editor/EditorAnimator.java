package com.android.contacts.editor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.util.SchedulingUtils;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class EditorAnimator {
    private static EditorAnimator sInstance = new EditorAnimator();
    /* access modifiers changed from: private */
    public AnimatorRunner mRunner = new AnimatorRunner();

    public static EditorAnimator getInstance() {
        return sInstance;
    }

    private EditorAnimator() {
    }

    public void removeEditorView(final View view) {
        this.mRunner.endOldAnimation();
        int height = view.getHeight();
        final List<View> viewsBelowOf = getViewsBelowOf(view);
        ArrayList newArrayList = Lists.newArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, ContactPhotoManager.OFFSET_DEFAULT});
        ofFloat.setDuration(200);
        newArrayList.add(ofFloat);
        translateViews(newArrayList, viewsBelowOf, ContactPhotoManager.OFFSET_DEFAULT, (float) (-height), 100, 200);
        this.mRunner.run(newArrayList, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                for (int i = 0; i < viewsBelowOf.size(); i++) {
                    ((View) viewsBelowOf.get(i)).setTranslationY(ContactPhotoManager.OFFSET_DEFAULT);
                }
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
            }
        });
    }

    public void slideAndFadeIn(final ViewGroup viewGroup, final int i) {
        this.mRunner.endOldAnimation();
        viewGroup.setVisibility(0);
        viewGroup.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
        SchedulingUtils.doAfterLayout(viewGroup, new Runnable() {
            public void run() {
                int height = viewGroup.getHeight() - i;
                ArrayList newArrayList = Lists.newArrayList();
                EditorAnimator.translateViews(newArrayList, EditorAnimator.getViewsBelowOf(viewGroup), (float) (-height), ContactPhotoManager.OFFSET_DEFAULT, 0, 200);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewGroup, View.ALPHA, new float[]{ContactPhotoManager.OFFSET_DEFAULT, 1.0f});
                ofFloat.setDuration(200);
                ofFloat.setStartDelay(200);
                newArrayList.add(ofFloat);
                EditorAnimator.this.mRunner.run(newArrayList);
            }
        });
    }

    public void showFieldFooter(final View view) {
        this.mRunner.endOldAnimation();
        if (view.getVisibility() != 0) {
            view.setVisibility(0);
            view.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
            SchedulingUtils.doAfterLayout(view, new Runnable() {
                public void run() {
                    int height = view.getHeight();
                    ArrayList newArrayList = Lists.newArrayList();
                    EditorAnimator.translateViews(newArrayList, EditorAnimator.getViewsBelowOf(view), (float) (-height), ContactPhotoManager.OFFSET_DEFAULT, 0, 200);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{ContactPhotoManager.OFFSET_DEFAULT, 1.0f});
                    ofFloat.setDuration(200);
                    ofFloat.setStartDelay(200);
                    newArrayList.add(ofFloat);
                    EditorAnimator.this.mRunner.run(newArrayList);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void translateViews(List<Animator> list, List<View> list2, float f, float f2, int i, int i2) {
        for (int i3 = 0; i3 < list2.size(); i3++) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(list2.get(i3), View.TRANSLATION_Y, new float[]{f, f2});
            ofFloat.setStartDelay((long) i);
            ofFloat.setDuration((long) i2);
            list.add(ofFloat);
        }
    }

    /* access modifiers changed from: private */
    public static List<View> getViewsBelowOf(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        ArrayList newArrayList = Lists.newArrayList();
        if (viewGroup != null) {
            getViewsBelowOfRecursive(newArrayList, viewGroup, viewGroup.indexOfChild(view) + 1, view);
        }
        return newArrayList;
    }

    private static void getViewsBelowOfRecursive(List<View> list, ViewGroup viewGroup, int i, View view) {
        while (i < viewGroup.getChildCount()) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getY() > view.getY() + ((float) (view.getHeight() / 2))) {
                list.add(childAt);
            }
            i++;
        }
        ViewParent parent = viewGroup.getParent();
        if (parent instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) parent;
            getViewsBelowOfRecursive(list, linearLayout, linearLayout.indexOfChild(viewGroup) + 1, view);
        }
    }

    static class AnimatorRunner extends AnimatorListenerAdapter {
        private Animator mLastAnimator;

        AnimatorRunner() {
        }

        public void onAnimationEnd(Animator animator) {
            this.mLastAnimator = null;
        }

        public void run(List<Animator> list) {
            run(list, (Animator.AnimatorListener) null);
        }

        public void run(List<Animator> list, Animator.AnimatorListener animatorListener) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(list);
            if (animatorListener != null) {
                animatorSet.addListener(animatorListener);
            }
            animatorSet.addListener(this);
            this.mLastAnimator = animatorSet;
            animatorSet.start();
        }

        public void endOldAnimation() {
            Animator animator = this.mLastAnimator;
            if (animator != null) {
                animator.end();
            }
        }
    }
}

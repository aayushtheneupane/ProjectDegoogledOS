package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.transition.AnimatorUtils;
import android.support.transition.Transition;
import android.view.View;
import android.view.ViewGroup;

public abstract class Visibility extends Transition {
    private static final String[] sTransitionProperties = {"android:visibility:visibility", "android:visibility:parent"};
    private int mMode = 3;

    private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener, AnimatorUtils.AnimatorPauseListenerCompat {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view, int i, boolean z) {
            this.mView = view;
            this.mFinalVisibility = i;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = z;
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean z) {
            ViewGroup viewGroup;
            if (this.mSuppressLayout && this.mLayoutSuppressed != z && (viewGroup = this.mParent) != null) {
                this.mLayoutSuppressed = z;
                AnimatorUtils.suppressLayout(viewGroup, z);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            hideViewWhenNotCanceled();
        }

        public void onAnimationPause(Animator animator) {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationResume(Animator animator) {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onTransitionEnd(Transition transition) {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition) {
            suppressLayout(false);
        }

        public void onTransitionResume(Transition transition) {
            suppressLayout(true);
        }

        public void onTransitionStart(Transition transition) {
        }
    }

    private static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        /* synthetic */ VisibilityInfo(C00781 r1) {
        }
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:visibility:screenLocation", iArr);
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo((C00781) null);
        visibilityInfo.mVisibilityChange = false;
        visibilityInfo.mFadeIn = false;
        if (transitionValues == null || !transitionValues.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.mStartVisibility = -1;
            visibilityInfo.mStartParent = null;
        } else {
            visibilityInfo.mStartVisibility = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.mStartParent = (ViewGroup) transitionValues.values.get("android:visibility:parent");
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.mEndVisibility = -1;
            visibilityInfo.mEndParent = null;
        } else {
            visibilityInfo.mEndVisibility = ((Integer) transitionValues2.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.mEndParent = (ViewGroup) transitionValues2.values.get("android:visibility:parent");
        }
        if (transitionValues == null || transitionValues2 == null) {
            if (transitionValues == null && visibilityInfo.mEndVisibility == 0) {
                visibilityInfo.mFadeIn = true;
                visibilityInfo.mVisibilityChange = true;
            } else if (transitionValues2 == null && visibilityInfo.mStartVisibility == 0) {
                visibilityInfo.mFadeIn = false;
                visibilityInfo.mVisibilityChange = true;
            }
        } else if (visibilityInfo.mStartVisibility == visibilityInfo.mEndVisibility && visibilityInfo.mStartParent == visibilityInfo.mEndParent) {
            return visibilityInfo;
        } else {
            int i = visibilityInfo.mStartVisibility;
            int i2 = visibilityInfo.mEndVisibility;
            if (i != i2) {
                if (i == 0) {
                    visibilityInfo.mFadeIn = false;
                    visibilityInfo.mVisibilityChange = true;
                } else if (i2 == 0) {
                    visibilityInfo.mFadeIn = true;
                    visibilityInfo.mVisibilityChange = true;
                }
            } else if (visibilityInfo.mEndParent == null) {
                visibilityInfo.mFadeIn = false;
                visibilityInfo.mVisibilityChange = true;
            } else if (visibilityInfo.mStartParent == null) {
                visibilityInfo.mFadeIn = true;
                visibilityInfo.mVisibilityChange = true;
            }
        }
        return visibilityInfo;
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ce A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator createAnimator(android.view.ViewGroup r10, android.support.transition.TransitionValues r11, android.support.transition.TransitionValues r12) {
        /*
            r9 = this;
            android.support.transition.Visibility$VisibilityInfo r0 = r9.getVisibilityChangeInfo(r11, r12)
            boolean r1 = r0.mVisibilityChange
            r2 = 0
            if (r1 == 0) goto L_0x0139
            android.view.ViewGroup r1 = r0.mStartParent
            if (r1 != 0) goto L_0x0011
            android.view.ViewGroup r1 = r0.mEndParent
            if (r1 == 0) goto L_0x0139
        L_0x0011:
            boolean r1 = r0.mFadeIn
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0045
            int r1 = r0.mStartVisibility
            int r0 = r0.mEndVisibility
            int r0 = r9.mMode
            r0 = r0 & r4
            if (r0 != r4) goto L_0x0044
            if (r12 != 0) goto L_0x0023
            goto L_0x0044
        L_0x0023:
            if (r11 != 0) goto L_0x003e
            android.view.View r0 = r12.view
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            android.support.transition.TransitionValues r1 = r9.getMatchedTransitionValues(r0, r3)
            android.support.transition.TransitionValues r0 = r9.getTransitionValues(r0, r3)
            android.support.transition.Visibility$VisibilityInfo r0 = r9.getVisibilityChangeInfo(r1, r0)
            boolean r0 = r0.mVisibilityChange
            if (r0 == 0) goto L_0x003e
            goto L_0x0044
        L_0x003e:
            android.view.View r0 = r12.view
            android.animation.Animator r2 = r9.onAppear(r10, r0, r11, r12)
        L_0x0044:
            return r2
        L_0x0045:
            int r1 = r0.mStartVisibility
            int r0 = r0.mEndVisibility
            int r1 = r9.mMode
            r5 = 2
            r1 = r1 & r5
            if (r1 == r5) goto L_0x0051
            goto L_0x0139
        L_0x0051:
            if (r11 == 0) goto L_0x0056
            android.view.View r1 = r11.view
            goto L_0x0057
        L_0x0056:
            r1 = r2
        L_0x0057:
            if (r12 == 0) goto L_0x005c
            android.view.View r6 = r12.view
            goto L_0x005d
        L_0x005c:
            r6 = r2
        L_0x005d:
            if (r6 == 0) goto L_0x007f
            android.view.ViewParent r7 = r6.getParent()
            if (r7 != 0) goto L_0x0066
            goto L_0x007f
        L_0x0066:
            r7 = 4
            if (r0 != r7) goto L_0x006a
            goto L_0x006c
        L_0x006a:
            if (r1 != r6) goto L_0x006f
        L_0x006c:
            r1 = r2
            goto L_0x00cc
        L_0x006f:
            boolean r6 = r9.mCanRemoveViews
            if (r6 == 0) goto L_0x0074
            goto L_0x00c8
        L_0x0074:
            android.view.ViewParent r6 = r1.getParent()
            android.view.View r6 = (android.view.View) r6
            android.view.View r1 = android.support.transition.TransitionUtils.copyViewImage(r10, r1, r6)
            goto L_0x00c8
        L_0x007f:
            if (r6 == 0) goto L_0x0083
            r1 = r6
            goto L_0x00c8
        L_0x0083:
            if (r1 == 0) goto L_0x00ca
            android.view.ViewParent r6 = r1.getParent()
            if (r6 != 0) goto L_0x008c
            goto L_0x00c8
        L_0x008c:
            android.view.ViewParent r6 = r1.getParent()
            boolean r6 = r6 instanceof android.view.View
            if (r6 == 0) goto L_0x00ca
            android.view.ViewParent r6 = r1.getParent()
            android.view.View r6 = (android.view.View) r6
            android.support.transition.TransitionValues r7 = r9.getTransitionValues(r6, r4)
            android.support.transition.TransitionValues r8 = r9.getMatchedTransitionValues(r6, r4)
            android.support.transition.Visibility$VisibilityInfo r7 = r9.getVisibilityChangeInfo(r7, r8)
            boolean r7 = r7.mVisibilityChange
            if (r7 != 0) goto L_0x00af
            android.view.View r1 = android.support.transition.TransitionUtils.copyViewImage(r10, r1, r6)
            goto L_0x00c8
        L_0x00af:
            android.view.ViewParent r7 = r6.getParent()
            if (r7 != 0) goto L_0x00c7
            int r6 = r6.getId()
            r7 = -1
            if (r6 == r7) goto L_0x00c7
            android.view.View r6 = r10.findViewById(r6)
            if (r6 == 0) goto L_0x00c7
            boolean r6 = r9.mCanRemoveViews
            if (r6 == 0) goto L_0x00c7
            goto L_0x00c8
        L_0x00c7:
            r1 = r2
        L_0x00c8:
            r6 = r2
            goto L_0x00cc
        L_0x00ca:
            r1 = r2
            r6 = r1
        L_0x00cc:
            if (r1 == 0) goto L_0x0116
            if (r11 == 0) goto L_0x0116
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.values
            java.lang.String r2 = "android:visibility:screenLocation"
            java.lang.Object r0 = r0.get(r2)
            int[] r0 = (int[]) r0
            r2 = r0[r3]
            r0 = r0[r4]
            int[] r5 = new int[r5]
            r10.getLocationOnScreen(r5)
            r3 = r5[r3]
            int r2 = r2 - r3
            int r3 = r1.getLeft()
            int r2 = r2 - r3
            r1.offsetLeftAndRight(r2)
            r2 = r5[r4]
            int r0 = r0 - r2
            int r2 = r1.getTop()
            int r0 = r0 - r2
            r1.offsetTopAndBottom(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            android.support.transition.ViewGroupOverlayApi18 r0 = new android.support.transition.ViewGroupOverlayApi18
            r0.<init>(r10)
            r0.add((android.view.View) r1)
            android.animation.Animator r2 = r9.onDisappear(r10, r1, r11, r12)
            if (r2 != 0) goto L_0x010d
            r0.remove((android.view.View) r1)
            goto L_0x0139
        L_0x010d:
            android.support.transition.Visibility$1 r10 = new android.support.transition.Visibility$1
            r10.<init>(r9, r0, r1)
            r2.addListener(r10)
            goto L_0x0139
        L_0x0116:
            if (r6 == 0) goto L_0x0139
            int r1 = r6.getVisibility()
            android.support.transition.ViewUtils.setTransitionVisibility(r6, r3)
            android.animation.Animator r2 = r9.onDisappear(r10, r6, r11, r12)
            if (r2 == 0) goto L_0x0136
            android.support.transition.Visibility$DisappearListener r10 = new android.support.transition.Visibility$DisappearListener
            r10.<init>(r6, r0, r4)
            r2.addListener(r10)
            int r11 = android.os.Build.VERSION.SDK_INT
            r2.addPauseListener(r10)
            r9.addListener(r10)
            goto L_0x0139
        L_0x0136:
            android.support.transition.ViewUtils.setTransitionVisibility(r6, r1)
        L_0x0139:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Visibility.createAnimator(android.view.ViewGroup, android.support.transition.TransitionValues, android.support.transition.TransitionValues):android.animation.Animator");
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.values.containsKey("android:visibility:visibility") != transitionValues.values.containsKey("android:visibility:visibility")) {
            return false;
        }
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (!visibilityChangeInfo.mVisibilityChange) {
            return false;
        }
        if (visibilityChangeInfo.mStartVisibility == 0 || visibilityChangeInfo.mEndVisibility == 0) {
            return true;
        }
        return false;
    }

    public abstract Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2);

    public abstract Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2);

    public void setMode(int i) {
        if ((i & -4) == 0) {
            this.mMode = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }
}

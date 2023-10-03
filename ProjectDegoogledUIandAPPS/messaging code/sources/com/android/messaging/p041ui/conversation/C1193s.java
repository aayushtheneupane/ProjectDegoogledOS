package com.android.messaging.p041ui.conversation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.C0564fa;
import androidx.recyclerview.widget.C0566ga;
import androidx.recyclerview.widget.C0586qa;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1485y;

/* renamed from: com.android.messaging.ui.conversation.s */
public class C1193s extends C0566ga implements View.OnLayoutChangeListener, C0564fa {

    /* renamed from: As */
    private final TextView f1875As;

    /* renamed from: Bs */
    private final int f1876Bs;

    /* renamed from: Cs */
    private final int f1877Cs;

    /* renamed from: Ds */
    private final int f1878Ds;

    /* renamed from: Es */
    private final int f1879Es;

    /* renamed from: Fs */
    private final int f1880Fs;

    /* renamed from: Gs */
    private final int f1881Gs;

    /* renamed from: Hs */
    private final boolean f1882Hs;
    /* access modifiers changed from: private */

    /* renamed from: Is */
    public boolean f1883Is = false;

    /* renamed from: Js */
    private AnimatorSet f1884Js;

    /* renamed from: Ks */
    private ObjectAnimator f1885Ks;

    /* renamed from: Ls */
    private final Runnable f1886Ls = new C1189q(this);
    private final Rect mContainer = new Rect();
    private final Context mContext;
    private boolean mDragging = false;
    private final Handler mHandler = new Handler();
    private final ViewGroupOverlay mOverlay;
    private final int mTouchSlop;
    private boolean mVisible = false;

    /* renamed from: xs */
    private final RecyclerView f1887xs;

    /* renamed from: ys */
    private final ImageView f1888ys;

    /* renamed from: zs */
    private final ImageView f1889zs;

    private C1193s(RecyclerView recyclerView, int i) {
        this.mContext = recyclerView.getContext();
        this.f1887xs = recyclerView;
        this.f1887xs.addOnLayoutChangeListener(this);
        this.f1887xs.mo4824a((C0566ga) this);
        this.f1887xs.mo4823a((C0564fa) this);
        this.f1887xs.getAdapter().mo4796a(new C1191r(this));
        this.f1882Hs = i == 0;
        Resources resources = this.mContext.getResources();
        this.f1876Bs = resources.getDimensionPixelSize(R.dimen.fastscroll_track_width);
        this.f1877Cs = resources.getDimensionPixelSize(R.dimen.fastscroll_thumb_height);
        this.f1878Ds = resources.getDimensionPixelSize(R.dimen.fastscroll_preview_height);
        this.f1879Es = resources.getDimensionPixelSize(R.dimen.fastscroll_preview_min_width);
        this.f1880Fs = resources.getDimensionPixelOffset(R.dimen.fastscroll_preview_margin_top);
        this.f1881Gs = resources.getDimensionPixelOffset(R.dimen.fastscroll_preview_margin_left_right);
        this.mTouchSlop = resources.getDimensionPixelOffset(R.dimen.fastscroll_touch_slop);
        LayoutInflater from = LayoutInflater.from(this.mContext);
        this.f1888ys = (ImageView) from.inflate(R.layout.fastscroll_track, (ViewGroup) null);
        this.f1889zs = (ImageView) from.inflate(R.layout.fastscroll_thumb, (ViewGroup) null);
        this.f1875As = (TextView) from.inflate(R.layout.fastscroll_preview, (ViewGroup) null);
        this.f1875As.setBackground(C1037D.get().mo6939V(this.f1882Hs));
        if (C1464na.m3758Yj()) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, C1037D.get().mo6940W(true));
            stateListDrawable.addState(StateSet.WILD_CARD, C1037D.get().mo6940W(false));
            this.f1889zs.setImageDrawable(stateListDrawable);
        } else {
            this.f1889zs.setImageDrawable(C1037D.get().mo6940W(false));
        }
        this.mOverlay = recyclerView.getOverlay();
        this.mOverlay.add(this.f1888ys);
        this.mOverlay.add(this.f1889zs);
        this.mOverlay.add(this.f1875As);
        m3031aa(false);
        this.f1875As.setAlpha(0.0f);
    }

    /* renamed from: Rn */
    private void m3026Rn() {
        if (this.f1883Is) {
            this.mHandler.removeCallbacks(this.f1886Ls);
        }
    }

    /* renamed from: Sn */
    private void m3027Sn() {
        this.mDragging = false;
        this.f1889zs.setPressed(false);
        this.f1885Ks = ObjectAnimator.ofFloat(this.f1875As, View.ALPHA, new float[]{0.0f});
        this.f1885Ks.setDuration(300);
        this.f1885Ks.start();
        m3026Rn();
        this.mHandler.postDelayed(this.f1886Ls, 1500);
        this.f1883Is = true;
    }

    /* access modifiers changed from: private */
    /* renamed from: Tn */
    public void m3028Tn() {
        float f;
        int i;
        int i2;
        C0586qa findViewHolderForAdapterPosition;
        if (this.mVisible) {
            int height = this.mContainer.height();
            int i3 = this.f1877Cs;
            int i4 = height - i3;
            int i5 = (i3 / 2) + this.mContainer.top;
            int computeVerticalScrollRange = this.f1887xs.computeVerticalScrollRange();
            int computeVerticalScrollExtent = this.f1887xs.computeVerticalScrollExtent();
            int computeVerticalScrollOffset = this.f1887xs.computeVerticalScrollOffset();
            if (computeVerticalScrollRange == 0 || computeVerticalScrollExtent == 0) {
                f = 1.0f;
            } else {
                int i6 = computeVerticalScrollRange - computeVerticalScrollExtent;
                f = ((float) Math.min(computeVerticalScrollOffset, i6)) / ((float) i6);
            }
            int i7 = i5 + ((int) (((float) i4) * f));
            this.f1889zs.measure(View.MeasureSpec.makeMeasureSpec(this.f1876Bs, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f1877Cs, 1073741824));
            int i8 = this.f1882Hs ? this.mContainer.right - this.f1876Bs : this.mContainer.left;
            int height2 = i7 - (this.f1889zs.getHeight() / 2);
            this.f1889zs.layout(i8, height2, this.f1882Hs ? this.mContainer.right : this.mContainer.left + this.f1876Bs, this.f1877Cs + height2);
            if (this.mDragging) {
                int findFirstVisibleItemPosition = ((LinearLayoutManager) this.f1887xs.getLayoutManager()).findFirstVisibleItemPosition();
                if (!(findFirstVisibleItemPosition == -1 || (findViewHolderForAdapterPosition = this.f1887xs.findViewHolderForAdapterPosition(findFirstVisibleItemPosition)) == null)) {
                    this.f1875As.setText(C1485y.m3836C(((ConversationMessageView) findViewHolderForAdapterPosition.itemView).getData().mo6558rg()));
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mContainer.width(), RtlSpacingHelper.UNDEFINED);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.f1878Ds, 1073741824);
                this.f1875As.measure(makeMeasureSpec, makeMeasureSpec2);
                int measuredWidth = this.f1875As.getMeasuredWidth();
                int i9 = this.f1879Es;
                if (measuredWidth < i9) {
                    this.f1875As.measure(View.MeasureSpec.makeMeasureSpec(i9, 1073741824), makeMeasureSpec2);
                }
                Rect rect = this.mContainer;
                int i10 = rect.top + this.f1880Fs;
                if (this.f1882Hs) {
                    i2 = (rect.right - this.f1876Bs) - this.f1881Gs;
                    i = i2 - this.f1875As.getMeasuredWidth();
                } else {
                    i = this.f1881Gs + rect.left + this.f1876Bs;
                    i2 = this.f1875As.getMeasuredWidth() + i;
                }
                int measuredHeight = i7 - this.f1875As.getMeasuredHeight();
                if (measuredHeight < i10) {
                    i7 = this.f1875As.getMeasuredHeight() + i10;
                } else {
                    i10 = measuredHeight;
                }
                this.f1875As.layout(i, i10, i2, i7);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: aa */
    public void m3031aa(boolean z) {
        int i = this.f1882Hs ? this.f1876Bs : -this.f1876Bs;
        if (z) {
            float f = (float) i;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f1888ys, View.TRANSLATION_X, new float[]{f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f1889zs, View.TRANSLATION_X, new float[]{f});
            this.f1884Js = new AnimatorSet();
            this.f1884Js.playTogether(new Animator[]{ofFloat, ofFloat2});
            this.f1884Js.setDuration(300);
            this.f1884Js.start();
        } else {
            float f2 = (float) i;
            this.f1888ys.setTranslationX(f2);
            this.f1889zs.setTranslationX(f2);
        }
        this.mVisible = false;
    }

    /* renamed from: d */
    public static C1193s m3033d(RecyclerView recyclerView, int i) {
        if (C1464na.m3756Wj()) {
            return new C1193s(recyclerView, i);
        }
        return null;
    }

    /* renamed from: c */
    public void mo5124c(RecyclerView recyclerView, int i) {
        if (i == 1) {
            if (!this.mVisible) {
                int computeVerticalScrollRange = this.f1887xs.computeVerticalScrollRange();
                int computeVerticalScrollExtent = this.f1887xs.computeVerticalScrollExtent();
                if ((computeVerticalScrollRange == 0 || computeVerticalScrollExtent == 0 || ((float) computeVerticalScrollRange) / ((float) computeVerticalScrollExtent) <= 7.0f) ? false : true) {
                    AnimatorSet animatorSet = this.f1884Js;
                    if (animatorSet != null && animatorSet.isRunning()) {
                        this.f1884Js.cancel();
                    }
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f1888ys, View.TRANSLATION_X, new float[]{0.0f});
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f1889zs, View.TRANSLATION_X, new float[]{0.0f});
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
                    animatorSet2.setDuration(150);
                    animatorSet2.start();
                    this.mVisible = true;
                    m3028Tn();
                }
            }
            m3026Rn();
        } else if (i == 0 && !this.mDragging) {
            m3026Rn();
            this.mHandler.postDelayed(this.f1886Ls, 1500);
            this.f1883Is = true;
        }
    }

    /* renamed from: f */
    public void mo5125f(RecyclerView recyclerView, int i, int i2) {
        m3028Tn();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (!this.mVisible) {
            m3031aa(false);
        }
        this.mContainer.set(i, this.f1887xs.getPaddingTop() + i2, i3, i4);
        this.f1888ys.measure(View.MeasureSpec.makeMeasureSpec(this.f1876Bs, 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max(0, this.mContainer.height()), 1073741824));
        int i9 = this.f1882Hs ? this.mContainer.right - this.f1876Bs : this.mContainer.left;
        Rect rect = this.mContainer;
        this.f1888ys.layout(i9, rect.top, this.f1882Hs ? rect.right : rect.left + this.f1876Bs, this.mContainer.bottom);
        m3028Tn();
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r6 != 3) goto L_0x0089;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo4665b(androidx.recyclerview.widget.RecyclerView r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r6 = r5.mVisible
            r0 = 0
            if (r6 != 0) goto L_0x0006
            return r0
        L_0x0006:
            int r6 = r7.getActionMasked()
            r1 = 1
            if (r6 == 0) goto L_0x0024
            if (r6 == r1) goto L_0x001c
            r7 = 2
            if (r6 == r7) goto L_0x0017
            r7 = 3
            if (r6 == r7) goto L_0x001c
            goto L_0x0089
        L_0x0017:
            boolean r6 = r5.mDragging
            if (r6 == 0) goto L_0x001c
            return r1
        L_0x001c:
            boolean r6 = r5.mDragging
            if (r6 == 0) goto L_0x0023
            r5.m3027Sn()
        L_0x0023:
            return r0
        L_0x0024:
            float r6 = r7.getX()
            float r7 = r7.getY()
            android.widget.ImageView r2 = r5.f1889zs
            int r2 = r2.getLeft()
            int r3 = r5.mTouchSlop
            int r2 = r2 - r3
            android.widget.ImageView r3 = r5.f1889zs
            int r3 = r3.getRight()
            int r4 = r5.mTouchSlop
            int r3 = r3 + r4
            float r2 = (float) r2
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0062
            float r2 = (float) r3
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0049
            goto L_0x0062
        L_0x0049:
            android.widget.ImageView r6 = r5.f1889zs
            int r6 = r6.getTop()
            float r6 = (float) r6
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0062
            android.widget.ImageView r6 = r5.f1889zs
            int r6 = r6.getBottom()
            float r6 = (float) r6
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0060
            goto L_0x0062
        L_0x0060:
            r6 = r1
            goto L_0x0063
        L_0x0062:
            r6 = r0
        L_0x0063:
            if (r6 == 0) goto L_0x0089
            r5.mDragging = r1
            android.widget.ImageView r6 = r5.f1889zs
            r6.setPressed(r1)
            r5.m3028Tn()
            android.animation.ObjectAnimator r6 = r5.f1885Ks
            if (r6 == 0) goto L_0x007e
            boolean r6 = r6.isRunning()
            if (r6 == 0) goto L_0x007e
            android.animation.ObjectAnimator r6 = r5.f1885Ks
            r6.cancel()
        L_0x007e:
            android.widget.TextView r6 = r5.f1875As
            r7 = 1065353216(0x3f800000, float:1.0)
            r6.setAlpha(r7)
            r5.m3026Rn()
            return r1
        L_0x0089:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversation.C1193s.mo4665b(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    /* renamed from: a */
    public void mo4663a(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mDragging) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float y = motionEvent.getY();
                    int height = this.mContainer.height();
                    int i = this.f1877Cs;
                    int i2 = height - i;
                    float min = Math.min(Math.max((y - ((float) ((i / 2) + this.mContainer.top))) / ((float) i2), 0.0f), 1.0f);
                    this.f1887xs.scrollToPosition((int) (((float) (this.f1887xs.getAdapter().getItemCount() - 1)) * min));
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            m3027Sn();
        }
    }
}

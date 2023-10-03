package com.android.messaging.p041ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import com.android.messaging.R;
import com.android.messaging.annotation.VisibleForAnimation;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.p041ui.conversation.ComposeMessageView;
import com.android.messaging.p041ui.p042a.C1079f;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.AttachmentPreview */
public class AttachmentPreview extends ScrollView implements C1063Q {
    /* access modifiers changed from: private */

    /* renamed from: Ha */
    public ComposeMessageView f1570Ha;
    /* access modifiers changed from: private */

    /* renamed from: df */
    public FrameLayout f1571df;

    /* renamed from: ef */
    private int f1572ef = -1;

    /* renamed from: ff */
    private Animator f1573ff;

    /* renamed from: gf */
    private boolean f1574gf;
    /* access modifiers changed from: private */

    /* renamed from: hf */
    public boolean f1575hf;

    /* renamed from: if */
    private C1079f f1576if;
    /* access modifiers changed from: private */
    public ImageButton mCloseButton;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Runnable mHideRunnable;

    public AttachmentPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Lm */
    private void m2465Lm() {
        Animator animator = this.f1573ff;
        if (animator != null) {
            animator.cancel();
            this.f1573ff = null;
        }
        this.f1572ef = -1;
    }

    /* renamed from: c */
    static /* synthetic */ void m2469c(AttachmentPreview attachmentPreview) {
        C1079f fVar = attachmentPreview.f1576if;
        if (fVar != null) {
            fVar.cancel();
            attachmentPreview.f1576if = null;
        }
    }

    /* renamed from: Gb */
    public void mo6867Gb() {
        if (getVisibility() != 8) {
            C1486ya.m3854a((View) this.mCloseButton, 8, (Runnable) null);
            m2465Lm();
            this.f1573ff = ObjectAnimator.ofInt(this, "animatedHeight", new int[]{getHeight(), 0});
            this.f1573ff.start();
            if (this.f1571df.getChildCount() > 0) {
                this.f1575hf = false;
                C1486ya.m3854a(this.f1571df.getChildCount() > 1 ? this.f1571df : this.f1571df.getChildAt(0), 4, (Runnable) new C1257f(this));
                return;
            }
            this.f1571df.removeAllViews();
            setVisibility(8);
        }
    }

    /* renamed from: Hb */
    public void mo6868Hb() {
        Runnable runnable = this.mHideRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            setVisibility(4);
            this.mHideRunnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mCloseButton = (ImageButton) findViewById(R.id.close_button);
        this.mCloseButton.setOnClickListener(new C1116c(this));
        this.f1571df = (FrameLayout) findViewById(R.id.attachment_view);
        addOnLayoutChangeListener(new C1255e(this));
        this.f1574gf = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f1572ef >= 0) {
            setMeasuredDimension(getMeasuredWidth(), this.f1572ef);
        }
    }

    @VisibleForAnimation
    public void setAnimatedHeight(int i) {
        if (this.f1572ef != i) {
            this.f1572ef = i;
            requestLayout();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0139  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo6871b(com.android.messaging.datamodel.data.C0889A r15) {
        /*
            r14 = this;
            boolean r0 = r14.f1574gf
            java.util.List r1 = r15.mo6195if()
            java.util.List r2 = r15.mo6197jf()
            android.animation.Animator r3 = r14.f1573ff
            r4 = 0
            if (r3 == 0) goto L_0x0014
            r3.cancel()
            r14.f1573ff = r4
        L_0x0014:
            r3 = -1
            r14.f1572ef = r3
            r5 = 0
            r14.f1574gf = r5
            int r6 = r1.size()
            int r7 = r2.size()
            int r7 = r7 + r6
            android.widget.ImageButton r6 = r14.mCloseButton
            android.content.res.Resources r8 = r14.getResources()
            r9 = 2131689474(0x7f0f0002, float:1.9007964E38)
            java.lang.String r8 = r8.getQuantityString(r9, r7)
            r6.setContentDescription(r8)
            if (r7 != 0) goto L_0x0052
            com.android.messaging.ui.g r0 = new com.android.messaging.ui.g
            r0.<init>(r14, r1, r2)
            r14.mHideRunnable = r0
            boolean r15 = r15.mo6204pf()
            if (r15 == 0) goto L_0x004c
            android.os.Handler r15 = r14.mHandler
            java.lang.Runnable r14 = r14.mHideRunnable
            r0 = 500(0x1f4, double:2.47E-321)
            r15.postDelayed(r14, r0)
            goto L_0x0051
        L_0x004c:
            java.lang.Runnable r14 = r14.mHideRunnable
            r14.run()
        L_0x0051:
            return r5
        L_0x0052:
            r15 = 1
            r14.f1575hf = r15
            int r6 = r14.getVisibility()
            if (r6 == 0) goto L_0x007c
            r14.setVisibility(r5)
            android.widget.FrameLayout r6 = r14.f1571df
            r6.setVisibility(r5)
            if (r0 != 0) goto L_0x007c
            android.widget.ImageButton r0 = r14.mCloseButton
            r6 = 4
            r0.setVisibility(r6)
            android.os.Handler r0 = com.android.messaging.util.C1480va.getMainThreadHandler()
            com.android.messaging.ui.h r6 = new com.android.messaging.ui.h
            r6.<init>(r14)
            int r8 = com.android.messaging.util.C1486ya.f2354YK
            int r8 = r8 + 300
            long r8 = (long) r8
            r0.postDelayed(r6, r8)
        L_0x007c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.addAll(r1)
            r0.addAll(r2)
            android.content.Context r1 = r14.getContext()
            android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r1)
            if (r7 <= r15) goto L_0x00f1
            android.widget.FrameLayout r1 = r14.f1571df
            int r1 = r1.getChildCount()
            if (r1 <= 0) goto L_0x00ca
            android.widget.FrameLayout r1 = r14.f1571df
            android.view.View r1 = r1.getChildAt(r5)
            boolean r2 = r1 instanceof com.android.messaging.p041ui.MultiAttachmentLayout
            if (r2 == 0) goto L_0x00b3
            android.widget.FrameLayout r2 = r14.f1571df
            int r2 = r2.getChildCount()
            com.android.messaging.util.C1424b.equals((int) r15, (int) r2)
            com.android.messaging.ui.MultiAttachmentLayout r1 = (com.android.messaging.p041ui.MultiAttachmentLayout) r1
            r1.mo7042a(r0, r4, r7)
            r2 = r4
            goto L_0x00cc
        L_0x00b3:
            android.graphics.Rect r2 = new android.graphics.Rect
            int r5 = r1.getLeft()
            int r6 = r1.getTop()
            int r8 = r1.getRight()
            int r1 = r1.getBottom()
            r2.<init>(r5, r6, r8, r1)
            r1 = r4
            goto L_0x00cc
        L_0x00ca:
            r1 = r4
            r2 = r1
        L_0x00cc:
            if (r1 != 0) goto L_0x015f
            android.content.Context r1 = r14.getContext()
            com.android.messaging.ui.MultiAttachmentLayout r5 = new com.android.messaging.ui.MultiAttachmentLayout
            r5.<init>(r1, r4)
            android.view.ViewGroup$LayoutParams r1 = new android.view.ViewGroup$LayoutParams
            r1.<init>(r3, r3)
            r5.setLayoutParams(r1)
            r5.mo7040a((com.android.messaging.p041ui.C1063Q) r14)
            r5.mo7042a(r0, r2, r7)
            android.widget.FrameLayout r0 = r14.f1571df
            r0.removeAllViews()
            android.widget.FrameLayout r14 = r14.f1571df
            r14.addView(r5)
            goto L_0x015f
        L_0x00f1:
            java.lang.Object r0 = r0.get(r5)
            com.android.messaging.datamodel.data.MessagePartData r0 = (com.android.messaging.datamodel.data.MessagePartData) r0
            android.widget.FrameLayout r1 = r14.f1571df
            int r1 = r1.getChildCount()
            if (r1 <= 0) goto L_0x0127
            android.widget.FrameLayout r1 = r14.f1571df
            android.view.View r1 = r1.getChildAt(r5)
            boolean r2 = r1 instanceof com.android.messaging.p041ui.MultiAttachmentLayout
            if (r2 == 0) goto L_0x0128
            boolean r2 = r0 instanceof com.android.messaging.datamodel.data.MediaPickerMessagePartData
            if (r2 == 0) goto L_0x0128
            com.android.messaging.ui.MultiAttachmentLayout r1 = (com.android.messaging.p041ui.MultiAttachmentLayout) r1
            android.view.View r1 = r1.mo7044e(r0)
            if (r1 == 0) goto L_0x0128
            android.graphics.Rect r1 = com.android.messaging.util.C1486ya.m3858h(r1)
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x0128
            if (r2 == 0) goto L_0x0128
            r2 = r0
            com.android.messaging.datamodel.data.MediaPickerMessagePartData r2 = (com.android.messaging.datamodel.data.MediaPickerMessagePartData) r2
            r2.mo6240c(r1)
        L_0x0127:
            r5 = r15
        L_0x0128:
            android.widget.FrameLayout r1 = r14.f1571df
            r1.removeAllViews()
            android.widget.FrameLayout r10 = r14.f1571df
            r11 = 1
            r12 = 1
            r9 = r0
            r13 = r14
            android.view.View r1 = com.android.messaging.p041ui.C1269l.m3184a(r8, r9, r10, r11, r12, r13)
            if (r1 == 0) goto L_0x015f
            android.widget.FrameLayout r2 = r14.f1571df
            r2.addView(r1)
            if (r5 == 0) goto L_0x015f
            boolean r2 = r0 instanceof com.android.messaging.datamodel.data.MediaPickerMessagePartData
            if (r2 == 0) goto L_0x015f
            com.android.messaging.datamodel.data.MediaPickerMessagePartData r0 = (com.android.messaging.datamodel.data.MediaPickerMessagePartData) r0
            android.graphics.Rect r0 = r0.mo6241kh()
            com.android.messaging.ui.a.f r2 = r14.f1576if
            if (r2 == 0) goto L_0x0153
            r2.cancel()
            r14.f1576if = r4
        L_0x0153:
            com.android.messaging.ui.a.f r2 = new com.android.messaging.ui.a.f
            r2.<init>(r0, r1)
            r14.f1576if = r2
            com.android.messaging.ui.a.f r14 = r14.f1576if
            r14.mo7127dc()
        L_0x015f:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.AttachmentPreview.mo6871b(com.android.messaging.datamodel.data.A):boolean");
    }

    /* renamed from: a */
    public void mo6869a(ComposeMessageView composeMessageView) {
        this.f1570Ha = composeMessageView;
    }

    /* renamed from: a */
    public boolean mo6870a(MessagePartData messagePartData, Rect rect, boolean z) {
        if (z) {
            this.f1570Ha.mo7296Rb();
            return true;
        } else if ((messagePartData instanceof PendingAttachmentData) || !messagePartData.mo6304fh()) {
            return false;
        } else {
            this.f1570Ha.mo7300a(messagePartData.getContentUri(), rect);
            return true;
        }
    }
}

package p000;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.p001v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.Button;
import com.google.android.apps.photosgo.R;

/* renamed from: oj */
/* compiled from: PG */
public final class C0394oj extends C0418pg implements DialogInterface {

    /* renamed from: a */
    public final C0392oh f15413a = new C0392oh(getContext(), this, getWindow());

    protected C0394oj(Context context, int i) {
        super(context, m14906a(context, i));
    }

    /* renamed from: a */
    public final Button mo9526a(int i) {
        C0392oh ohVar = this.f15413a;
        if (i == -3) {
            return ohVar.f15403s;
        }
        if (i == -2) {
            return ohVar.f15399o;
        }
        if (i != -1) {
            return null;
        }
        return ohVar.f15395k;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02d5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x02fb  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:147:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r15) {
        /*
            r14 = this;
            super.onCreate(r15)
            oh r15 = r14.f15413a
            int r0 = r15.f15377D
            if (r0 == 0) goto L_0x000c
            int r0 = r15.f15376C
            goto L_0x000e
        L_0x000c:
            int r0 = r15.f15376C
        L_0x000e:
            pg r1 = r15.f15386b
            r1.setContentView((int) r0)
            android.view.Window r0 = r15.f15387c
            r1 = 2131362148(0x7f0a0164, float:1.8344068E38)
            android.view.View r0 = r0.findViewById(r1)
            r1 = 2131362311(0x7f0a0207, float:1.83444E38)
            android.view.View r2 = r0.findViewById(r1)
            r3 = 2131361923(0x7f0a0083, float:1.8343612E38)
            android.view.View r4 = r0.findViewById(r3)
            r5 = 2131361888(0x7f0a0060, float:1.8343541E38)
            android.view.View r6 = r0.findViewById(r5)
            r7 = 2131361928(0x7f0a0088, float:1.8343622E38)
            android.view.View r0 = r0.findViewById(r7)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r7 = r15.f15392h
            r8 = 0
            if (r7 == 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r7 = r8
        L_0x0042:
            r9 = -1
            r10 = 8
            if (r7 != 0) goto L_0x0048
            goto L_0x004e
        L_0x0048:
            boolean r11 = p000.C0392oh.m14893a((android.view.View) r7)
            if (r11 != 0) goto L_0x005d
        L_0x004e:
            android.view.Window r11 = r15.f15387c
            r12 = 131072(0x20000, float:1.83671E-40)
            r11.setFlags(r12, r12)
            if (r7 == 0) goto L_0x0058
            goto L_0x005d
        L_0x0058:
            r0.setVisibility(r10)
            goto L_0x007d
        L_0x005d:
            android.view.Window r11 = r15.f15387c
            r12 = 2131361927(0x7f0a0087, float:1.834362E38)
            android.view.View r11 = r11.findViewById(r12)
            android.widget.FrameLayout r11 = (android.widget.FrameLayout) r11
            android.view.ViewGroup$LayoutParams r12 = new android.view.ViewGroup$LayoutParams
            r12.<init>(r9, r9)
            r11.addView(r7, r12)
            android.widget.ListView r7 = r15.f15391g
            if (r7 == 0) goto L_0x007d
            android.view.ViewGroup$LayoutParams r7 = r0.getLayoutParams()
            vz r7 = (p000.C0599vz) r7
            r11 = 0
            r7.f16179g = r11
        L_0x007d:
            android.view.View r1 = r0.findViewById(r1)
            android.view.View r3 = r0.findViewById(r3)
            android.view.View r5 = r0.findViewById(r5)
            android.view.ViewGroup r1 = p000.C0392oh.m14891a(r1, r2)
            android.view.ViewGroup r2 = p000.C0392oh.m14891a(r3, r4)
            android.view.ViewGroup r3 = p000.C0392oh.m14891a(r5, r6)
            android.view.Window r4 = r15.f15387c
            r5 = 2131362210(0x7f0a01a2, float:1.8344194E38)
            android.view.View r4 = r4.findViewById(r5)
            android.support.v4.widget.NestedScrollView r4 = (android.support.p001v4.widget.NestedScrollView) r4
            r15.f15404t = r4
            android.support.v4.widget.NestedScrollView r4 = r15.f15404t
            r5 = 0
            r4.setFocusable(r5)
            android.support.v4.widget.NestedScrollView r4 = r15.f15404t
            r4.setNestedScrollingEnabled(r5)
            r4 = 16908299(0x102000b, float:2.387726E-38)
            android.view.View r4 = r2.findViewById(r4)
            android.widget.TextView r4 = (android.widget.TextView) r4
            r15.f15409y = r4
            android.widget.TextView r4 = r15.f15409y
            if (r4 == 0) goto L_0x00f3
            java.lang.CharSequence r6 = r15.f15390f
            if (r6 != 0) goto L_0x00f0
            r4.setVisibility(r10)
            android.support.v4.widget.NestedScrollView r4 = r15.f15404t
            android.widget.TextView r6 = r15.f15409y
            r4.removeView(r6)
            android.widget.ListView r4 = r15.f15391g
            if (r4 == 0) goto L_0x00eb
            android.support.v4.widget.NestedScrollView r4 = r15.f15404t
            android.view.ViewParent r4 = r4.getParent()
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            android.support.v4.widget.NestedScrollView r6 = r15.f15404t
            int r6 = r4.indexOfChild(r6)
            r4.removeViewAt(r6)
            android.widget.ListView r7 = r15.f15391g
            android.view.ViewGroup$LayoutParams r11 = new android.view.ViewGroup$LayoutParams
            r11.<init>(r9, r9)
            r4.addView(r7, r6, r11)
            goto L_0x00f3
        L_0x00eb:
            r2.setVisibility(r10)
            goto L_0x00f3
        L_0x00f0:
            r4.setText(r6)
        L_0x00f3:
            r4 = 16908313(0x1020019, float:2.38773E-38)
            android.view.View r4 = r3.findViewById(r4)
            android.widget.Button r4 = (android.widget.Button) r4
            r15.f15395k = r4
            android.widget.Button r4 = r15.f15395k
            android.view.View$OnClickListener r6 = r15.f15384K
            r4.setOnClickListener(r6)
            java.lang.CharSequence r4 = r15.f15396l
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            r6 = 1
            if (r4 != 0) goto L_0x010f
            goto L_0x011b
        L_0x010f:
            android.graphics.drawable.Drawable r4 = r15.f15398n
            if (r4 != 0) goto L_0x011b
            android.widget.Button r4 = r15.f15395k
            r4.setVisibility(r10)
            r4 = 0
            goto L_0x0138
        L_0x011b:
            android.widget.Button r4 = r15.f15395k
            java.lang.CharSequence r7 = r15.f15396l
            r4.setText(r7)
            android.graphics.drawable.Drawable r4 = r15.f15398n
            if (r4 == 0) goto L_0x0132
            int r7 = r15.f15388d
            r4.setBounds(r5, r5, r7, r7)
            android.widget.Button r4 = r15.f15395k
            android.graphics.drawable.Drawable r7 = r15.f15398n
            r4.setCompoundDrawables(r7, r8, r8, r8)
        L_0x0132:
            android.widget.Button r4 = r15.f15395k
            r4.setVisibility(r5)
            r4 = 1
        L_0x0138:
            r7 = 16908314(0x102001a, float:2.3877302E-38)
            android.view.View r7 = r3.findViewById(r7)
            android.widget.Button r7 = (android.widget.Button) r7
            r15.f15399o = r7
            android.widget.Button r7 = r15.f15399o
            android.view.View$OnClickListener r11 = r15.f15384K
            r7.setOnClickListener(r11)
            java.lang.CharSequence r7 = r15.f15400p
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0153
            goto L_0x015d
        L_0x0153:
            android.graphics.drawable.Drawable r7 = r15.f15402r
            if (r7 != 0) goto L_0x015d
            android.widget.Button r7 = r15.f15399o
            r7.setVisibility(r10)
            goto L_0x017b
        L_0x015d:
            android.widget.Button r7 = r15.f15399o
            java.lang.CharSequence r11 = r15.f15400p
            r7.setText(r11)
            android.graphics.drawable.Drawable r7 = r15.f15402r
            if (r7 == 0) goto L_0x0174
            int r11 = r15.f15388d
            r7.setBounds(r5, r5, r11, r11)
            android.widget.Button r7 = r15.f15399o
            android.graphics.drawable.Drawable r11 = r15.f15402r
            r7.setCompoundDrawables(r11, r8, r8, r8)
        L_0x0174:
            android.widget.Button r7 = r15.f15399o
            r7.setVisibility(r5)
            r4 = r4 | 2
        L_0x017b:
            r7 = 16908315(0x102001b, float:2.3877305E-38)
            android.view.View r7 = r3.findViewById(r7)
            android.widget.Button r7 = (android.widget.Button) r7
            r15.f15403s = r7
            android.widget.Button r7 = r15.f15403s
            android.view.View$OnClickListener r11 = r15.f15384K
            r7.setOnClickListener(r11)
            boolean r7 = android.text.TextUtils.isEmpty(r8)
            if (r7 != 0) goto L_0x01b0
            android.widget.Button r7 = r15.f15403s
            r7.setText(r8)
            android.graphics.drawable.Drawable r7 = r15.f15398n
            if (r7 == 0) goto L_0x01a8
            int r11 = r15.f15388d
            r7.setBounds(r5, r5, r11, r11)
            android.widget.Button r7 = r15.f15395k
            android.graphics.drawable.Drawable r11 = r15.f15398n
            r7.setCompoundDrawables(r11, r8, r8, r8)
        L_0x01a8:
            android.widget.Button r7 = r15.f15403s
            r7.setVisibility(r5)
            r4 = r4 | 4
            goto L_0x01b5
        L_0x01b0:
            android.widget.Button r7 = r15.f15403s
            r7.setVisibility(r10)
        L_0x01b5:
            android.content.Context r7 = r15.f15385a
            android.util.TypedValue r11 = new android.util.TypedValue
            r11.<init>()
            android.content.res.Resources$Theme r7 = r7.getTheme()
            r12 = 2130968615(0x7f040027, float:1.7545889E38)
            r7.resolveAttribute(r12, r11, r6)
            int r7 = r11.data
            r11 = 2
            if (r7 == 0) goto L_0x01e4
            if (r4 != r6) goto L_0x01d3
            android.widget.Button r4 = r15.f15395k
            p000.C0392oh.m14892a((android.widget.Button) r4)
            goto L_0x01e9
        L_0x01d3:
            if (r4 != r11) goto L_0x01db
            android.widget.Button r4 = r15.f15399o
            p000.C0392oh.m14892a((android.widget.Button) r4)
            goto L_0x01e9
        L_0x01db:
            r7 = 4
            if (r4 != r7) goto L_0x01e4
            android.widget.Button r4 = r15.f15403s
            p000.C0392oh.m14892a((android.widget.Button) r4)
            goto L_0x01e9
        L_0x01e4:
            if (r4 != 0) goto L_0x01e9
            r3.setVisibility(r10)
        L_0x01e9:
            android.view.View r4 = r15.f15410z
            r7 = 2131362309(0x7f0a0205, float:1.8344395E38)
            if (r4 == 0) goto L_0x0206
            android.view.ViewGroup$LayoutParams r4 = new android.view.ViewGroup$LayoutParams
            r12 = -2
            r4.<init>(r9, r12)
            android.view.View r9 = r15.f15410z
            r1.addView(r9, r5, r4)
            android.view.Window r4 = r15.f15387c
            android.view.View r4 = r4.findViewById(r7)
            r4.setVisibility(r10)
            goto L_0x027d
        L_0x0206:
            android.view.Window r4 = r15.f15387c
            r9 = 16908294(0x1020006, float:2.3877246E-38)
            android.view.View r4 = r4.findViewById(r9)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            r15.f15407w = r4
            java.lang.CharSequence r4 = r15.f15389e
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            r4 = r4 ^ r6
            if (r4 != 0) goto L_0x021d
            goto L_0x026c
        L_0x021d:
            boolean r4 = r15.f15382I
            if (r4 == 0) goto L_0x026c
            android.view.Window r4 = r15.f15387c
            r7 = 2131361863(0x7f0a0047, float:1.834349E38)
            android.view.View r4 = r4.findViewById(r7)
            android.widget.TextView r4 = (android.widget.TextView) r4
            r15.f15408x = r4
            android.widget.TextView r4 = r15.f15408x
            java.lang.CharSequence r7 = r15.f15389e
            r4.setText(r7)
            int r4 = r15.f15405u
            if (r4 != 0) goto L_0x0266
            android.graphics.drawable.Drawable r4 = r15.f15406v
            if (r4 != 0) goto L_0x0260
            android.widget.TextView r4 = r15.f15408x
            android.widget.ImageView r7 = r15.f15407w
            int r7 = r7.getPaddingLeft()
            android.widget.ImageView r9 = r15.f15407w
            int r9 = r9.getPaddingTop()
            android.widget.ImageView r12 = r15.f15407w
            int r12 = r12.getPaddingRight()
            android.widget.ImageView r13 = r15.f15407w
            int r13 = r13.getPaddingBottom()
            r4.setPadding(r7, r9, r12, r13)
            android.widget.ImageView r4 = r15.f15407w
            r4.setVisibility(r10)
            goto L_0x027d
        L_0x0260:
            android.widget.ImageView r7 = r15.f15407w
            r7.setImageDrawable(r4)
            goto L_0x027d
        L_0x0266:
            android.widget.ImageView r7 = r15.f15407w
            r7.setImageResource(r4)
            goto L_0x027d
        L_0x026c:
            android.view.Window r4 = r15.f15387c
            android.view.View r4 = r4.findViewById(r7)
            r4.setVisibility(r10)
            android.widget.ImageView r4 = r15.f15407w
            r4.setVisibility(r10)
            r1.setVisibility(r10)
        L_0x027d:
            if (r0 == 0) goto L_0x0288
            int r0 = r0.getVisibility()
            if (r0 == r10) goto L_0x0287
            r0 = 1
            goto L_0x0289
        L_0x0287:
        L_0x0288:
            r0 = 0
        L_0x0289:
            if (r1 == 0) goto L_0x0294
            int r4 = r1.getVisibility()
            if (r4 == r10) goto L_0x0293
            r4 = 1
            goto L_0x0295
        L_0x0293:
        L_0x0294:
            r4 = 0
        L_0x0295:
            if (r3 == 0) goto L_0x02a0
            int r3 = r3.getVisibility()
            if (r3 == r10) goto L_0x029f
            r3 = 1
            goto L_0x02a1
        L_0x029f:
        L_0x02a0:
            r3 = 0
        L_0x02a1:
            if (r3 != 0) goto L_0x02b1
            if (r2 == 0) goto L_0x02b1
            r7 = 2131362289(0x7f0a01f1, float:1.8344354E38)
            android.view.View r7 = r2.findViewById(r7)
            if (r7 == 0) goto L_0x02b1
            r7.setVisibility(r5)
        L_0x02b1:
            if (r4 == 0) goto L_0x02d5
            android.support.v4.widget.NestedScrollView r7 = r15.f15404t
            if (r7 != 0) goto L_0x02b8
            goto L_0x02bc
        L_0x02b8:
            r7.setClipToPadding(r6)
        L_0x02bc:
            java.lang.CharSequence r7 = r15.f15390f
            if (r7 == 0) goto L_0x02c1
            goto L_0x02c6
        L_0x02c1:
            android.widget.ListView r7 = r15.f15391g
            if (r7 != 0) goto L_0x02c6
            goto L_0x02cd
        L_0x02c6:
            r7 = 2131362308(0x7f0a0204, float:1.8344393E38)
            android.view.View r8 = r1.findViewById(r7)
        L_0x02cd:
            if (r8 != 0) goto L_0x02d0
            goto L_0x02e3
        L_0x02d0:
            r8.setVisibility(r5)
            goto L_0x02e3
        L_0x02d5:
            if (r2 == 0) goto L_0x02e3
            r1 = 2131362290(0x7f0a01f2, float:1.8344356E38)
            android.view.View r1 = r2.findViewById(r1)
            if (r1 == 0) goto L_0x02e3
            r1.setVisibility(r5)
        L_0x02e3:
            android.widget.ListView r1 = r15.f15391g
            boolean r7 = r1 instanceof android.support.p002v7.app.AlertController$RecycleListView
            if (r7 == 0) goto L_0x0312
            if (r3 == 0) goto L_0x02ed
            if (r4 != 0) goto L_0x0312
        L_0x02ed:
            android.support.v7.app.AlertController$RecycleListView r1 = (android.support.p002v7.app.AlertController$RecycleListView) r1
            int r7 = r1.getPaddingLeft()
            if (r4 == 0) goto L_0x02fb
            int r8 = r1.getPaddingTop()
            goto L_0x02fe
        L_0x02fb:
            int r8 = r1.f856a
        L_0x02fe:
            int r9 = r1.getPaddingRight()
            if (r3 == 0) goto L_0x030b
            int r10 = r1.getPaddingBottom()
            goto L_0x030e
        L_0x030b:
            int r10 = r1.f857b
        L_0x030e:
            r1.setPadding(r7, r8, r9, r10)
        L_0x0312:
            if (r0 != 0) goto L_0x0344
            android.widget.ListView r0 = r15.f15391g
            if (r0 != 0) goto L_0x031a
            android.support.v4.widget.NestedScrollView r0 = r15.f15404t
        L_0x031a:
            if (r0 != 0) goto L_0x031d
            goto L_0x0344
        L_0x031d:
            if (r3 != 0) goto L_0x0320
            goto L_0x0322
        L_0x0320:
            r5 = 2
        L_0x0322:
            android.view.Window r1 = r15.f15387c
            r3 = 2131362209(0x7f0a01a1, float:1.8344192E38)
            android.view.View r1 = r1.findViewById(r3)
            android.view.Window r3 = r15.f15387c
            r7 = 2131362208(0x7f0a01a0, float:1.834419E38)
            android.view.View r3 = r3.findViewById(r7)
            int r7 = android.os.Build.VERSION.SDK_INT
            r4 = r4 | r5
            p000.C0340mj.m14715f(r0, r4)
            if (r1 == 0) goto L_0x033f
            r2.removeView(r1)
        L_0x033f:
            if (r3 == 0) goto L_0x0344
            r2.removeView(r3)
        L_0x0344:
            android.widget.ListView r0 = r15.f15391g
            if (r0 == 0) goto L_0x035a
            android.widget.ListAdapter r1 = r15.f15374A
            if (r1 != 0) goto L_0x034d
            goto L_0x035a
        L_0x034d:
            r0.setAdapter(r1)
            int r15 = r15.f15375B
            if (r15 < 0) goto L_0x035a
            r0.setItemChecked(r15, r6)
            r0.setSelection(r15)
        L_0x035a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0394oj.onCreate(android.os.Bundle):void");
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f15413a.f15404t;
        if (nestedScrollView == null || !nestedScrollView.mo708a(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f15413a.f15404t;
        if (nestedScrollView == null || !nestedScrollView.mo708a(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    /* renamed from: a */
    static int m14906a(Context context, int i) {
        if ((i >>> 24) > 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* renamed from: a */
    public final void mo9527a(Drawable drawable) {
        this.f15413a.mo9516a(drawable);
    }

    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f15413a.mo9517a(charSequence);
    }
}

package p000;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;

/* renamed from: oi */
/* compiled from: PG */
public class C0393oi {

    /* renamed from: a */
    public final C0389oe f15411a;

    /* renamed from: b */
    private final int f15412b;

    public C0393oi(Context context) {
        this(context, C0394oj.m14906a(context, 0));
    }

    public C0393oi(Context context, int i) {
        this.f15411a = new C0389oe(new ContextThemeWrapper(context, C0394oj.m14906a(context, i)));
        this.f15412b = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: ob} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: android.widget.ListAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: og} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: ob} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: ob} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: ob} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p000.C0394oj mo6550b() {
        /*
            r14 = this;
            oj r0 = new oj
            oe r1 = r14.f15411a
            android.content.Context r1 = r1.f15348a
            int r2 = r14.f15412b
            r0.<init>(r1, r2)
            oe r1 = r14.f15411a
            oh r2 = r0.f15413a
            android.view.View r3 = r1.f15353f
            r9 = 0
            r10 = 0
            if (r3 != 0) goto L_0x003e
            java.lang.CharSequence r3 = r1.f15352e
            if (r3 != 0) goto L_0x001a
            goto L_0x001d
        L_0x001a:
            r2.mo9517a((java.lang.CharSequence) r3)
        L_0x001d:
            android.graphics.drawable.Drawable r3 = r1.f15351d
            if (r3 != 0) goto L_0x0022
            goto L_0x0025
        L_0x0022:
            r2.mo9516a((android.graphics.drawable.Drawable) r3)
        L_0x0025:
            int r3 = r1.f15350c
            if (r3 == 0) goto L_0x0040
            r2.f15406v = r10
            r2.f15405u = r3
            android.widget.ImageView r3 = r2.f15407w
            if (r3 != 0) goto L_0x0032
            goto L_0x0040
        L_0x0032:
            r3.setVisibility(r9)
            android.widget.ImageView r3 = r2.f15407w
            int r4 = r2.f15405u
            r3.setImageResource(r4)
            goto L_0x0040
        L_0x003e:
            r2.f15410z = r3
        L_0x0040:
            java.lang.CharSequence r3 = r1.f15354g
            if (r3 == 0) goto L_0x004e
            r2.f15390f = r3
            android.widget.TextView r4 = r2.f15409y
            if (r4 != 0) goto L_0x004b
            goto L_0x004e
        L_0x004b:
            r4.setText(r3)
        L_0x004e:
            java.lang.CharSequence r3 = r1.f15355h
            if (r3 == 0) goto L_0x0053
            goto L_0x0057
        L_0x0053:
            android.graphics.drawable.Drawable r4 = r1.f15356i
            if (r4 == 0) goto L_0x005f
        L_0x0057:
            r4 = -1
            android.content.DialogInterface$OnClickListener r5 = r1.f15357j
            android.graphics.drawable.Drawable r6 = r1.f15356i
            r2.mo9515a(r4, r3, r5, r6)
        L_0x005f:
            java.lang.CharSequence r3 = r1.f15358k
            if (r3 == 0) goto L_0x0064
            goto L_0x0068
        L_0x0064:
            android.graphics.drawable.Drawable r4 = r1.f15359l
            if (r4 == 0) goto L_0x0070
        L_0x0068:
            r4 = -2
            android.content.DialogInterface$OnClickListener r5 = r1.f15360m
            android.graphics.drawable.Drawable r6 = r1.f15359l
            r2.mo9515a(r4, r3, r5, r6)
        L_0x0070:
            java.lang.CharSequence[] r3 = r1.f15362o
            r11 = 1
            if (r3 == 0) goto L_0x0076
            goto L_0x007a
        L_0x0076:
            android.widget.ListAdapter r3 = r1.f15363p
            if (r3 == 0) goto L_0x00e2
        L_0x007a:
            android.view.LayoutInflater r3 = r1.f15349b
            int r4 = r2.f15378E
            android.view.View r3 = r3.inflate(r4, r10)
            r12 = r3
            android.support.v7.app.AlertController$RecycleListView r12 = (android.support.p002v7.app.AlertController$RecycleListView) r12
            boolean r3 = r1.f15369v
            if (r3 == 0) goto L_0x0098
            ob r13 = new ob
            android.content.Context r5 = r1.f15348a
            int r6 = r2.f15379F
            java.lang.CharSequence[] r7 = r1.f15362o
            r3 = r13
            r4 = r1
            r8 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x00b1
        L_0x0098:
            boolean r3 = r1.f15370w
            if (r3 != 0) goto L_0x009f
            int r3 = r2.f15381H
            goto L_0x00a1
        L_0x009f:
            int r3 = r2.f15380G
        L_0x00a1:
            android.widget.ListAdapter r13 = r1.f15363p
            if (r13 != 0) goto L_0x00b0
            og r13 = new og
            android.content.Context r4 = r1.f15348a
            java.lang.CharSequence[] r5 = r1.f15362o
            r13.<init>(r4, r3, r5)
            goto L_0x00b1
        L_0x00b0:
        L_0x00b1:
            r2.f15374A = r13
            int r3 = r1.f15371x
            r2.f15375B = r3
            android.content.DialogInterface$OnClickListener r3 = r1.f15364q
            if (r3 == 0) goto L_0x00c4
            oc r3 = new oc
            r3.<init>(r1, r2)
            r12.setOnItemClickListener(r3)
            goto L_0x00d0
        L_0x00c4:
            android.content.DialogInterface$OnMultiChoiceClickListener r3 = r1.f15372y
            if (r3 == 0) goto L_0x00d0
            od r3 = new od
            r3.<init>(r1, r12, r2)
            r12.setOnItemClickListener(r3)
        L_0x00d0:
            boolean r3 = r1.f15370w
            if (r3 == 0) goto L_0x00d8
            r12.setChoiceMode(r11)
            goto L_0x00e0
        L_0x00d8:
            boolean r3 = r1.f15369v
            if (r3 == 0) goto L_0x00e0
            r3 = 2
            r12.setChoiceMode(r3)
        L_0x00e0:
            r2.f15391g = r12
        L_0x00e2:
            android.view.View r1 = r1.f15366s
            if (r1 == 0) goto L_0x00ec
            r2.f15392h = r1
            r2.f15393i = r9
            r2.f15394j = r9
        L_0x00ec:
            r0.setCancelable(r11)
            r0.setCanceledOnTouchOutside(r11)
            r0.setOnCancelListener(r10)
            r0.setOnDismissListener(r10)
            oe r1 = r14.f15411a
            android.content.DialogInterface$OnKeyListener r1 = r1.f15361n
            if (r1 == 0) goto L_0x0102
            r0.setOnKeyListener(r1)
        L_0x0102:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0393oi.mo6550b():oj");
    }

    /* renamed from: a */
    public final Context mo9518a() {
        return this.f15411a.f15348a;
    }

    /* renamed from: a */
    public final void mo9521a(Drawable drawable) {
        this.f15411a.f15351d = drawable;
    }

    /* renamed from: a */
    public void mo9519a(int i) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15354g = oeVar.f15348a.getText(i);
    }

    /* renamed from: a */
    public final void mo9523a(CharSequence charSequence) {
        this.f15411a.f15354g = charSequence;
    }

    /* renamed from: a */
    public void mo9520a(int i, DialogInterface.OnClickListener onClickListener) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15355h = oeVar.f15348a.getText(i);
        this.f15411a.f15357j = onClickListener;
    }

    /* renamed from: a */
    public final void mo9524a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15355h = charSequence;
        oeVar.f15357j = onClickListener;
    }

    /* renamed from: b */
    public final void mo9525b(CharSequence charSequence) {
        this.f15411a.f15352e = charSequence;
    }

    /* renamed from: a */
    public final void mo9522a(View view) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15366s = view;
        oeVar.f15365r = 0;
        oeVar.f15367t = false;
    }
}

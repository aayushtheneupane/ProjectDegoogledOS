package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.MenuInflater;

/* renamed from: qm */
/* compiled from: PG */
public final class C0451qm extends MenuInflater {

    /* renamed from: a */
    public static final Class[] f15656a;

    /* renamed from: e */
    private static final Class[] f15657e;

    /* renamed from: b */
    public final Object[] f15658b;

    /* renamed from: c */
    public final Context f15659c;

    /* renamed from: d */
    public Object f15660d;

    /* renamed from: f */
    private final Object[] f15661f;

    static {
        Class[] clsArr = {Context.class};
        f15656a = clsArr;
        f15657e = clsArr;
    }

    public C0451qm(Context context) {
        super(context);
        this.f15659c = context;
        Object[] objArr = {context};
        this.f15658b = objArr;
        this.f15661f = objArr;
    }

    /* renamed from: a */
    public final Object mo9725a(Object obj) {
        return ((obj instanceof Activity) || !(obj instanceof ContextWrapper)) ? obj : mo9725a(((ContextWrapper) obj).getBaseContext());
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void inflate(int r4, android.view.Menu r5) {
        /*
            r3 = this;
            java.lang.String r0 = "Error inflating menu XML"
            boolean r1 = r5 instanceof p000.C0254je
            if (r1 == 0) goto L_0x003c
            r1 = 0
            android.content.Context r2 = r3.f15659c     // Catch:{ XmlPullParserException -> 0x002b, IOException -> 0x0024, all -> 0x0022 }
            android.content.res.Resources r2 = r2.getResources()     // Catch:{ XmlPullParserException -> 0x002b, IOException -> 0x0024, all -> 0x0022 }
            android.content.res.XmlResourceParser r1 = r2.getLayout(r4)     // Catch:{ XmlPullParserException -> 0x002b, IOException -> 0x0024, all -> 0x0022 }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r1)     // Catch:{ XmlPullParserException -> 0x0020, IOException -> 0x001e }
            r3.m15161a(r1, r4, r5)     // Catch:{ XmlPullParserException -> 0x0020, IOException -> 0x001e }
            if (r1 == 0) goto L_0x001d
            r1.close()
        L_0x001d:
            return
        L_0x001e:
            r4 = move-exception
            goto L_0x0025
        L_0x0020:
            r4 = move-exception
            goto L_0x002c
        L_0x0022:
            r4 = move-exception
            goto L_0x0035
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            android.view.InflateException r5 = new android.view.InflateException     // Catch:{ all -> 0x0032 }
            r5.<init>(r0, r4)     // Catch:{ all -> 0x0032 }
            throw r5     // Catch:{ all -> 0x0032 }
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            android.view.InflateException r5 = new android.view.InflateException     // Catch:{ all -> 0x0032 }
            r5.<init>(r0, r4)     // Catch:{ all -> 0x0032 }
            throw r5     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r4 = move-exception
        L_0x0035:
            if (r1 != 0) goto L_0x0038
            goto L_0x003b
        L_0x0038:
            r1.close()
        L_0x003b:
            throw r4
        L_0x003c:
            super.inflate(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0451qm.inflate(int, android.view.Menu):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01fa  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m15161a(org.xmlpull.v1.XmlPullParser r17, android.util.AttributeSet r18, android.view.Menu r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            ql r2 = new ql
            r3 = r19
            r2.<init>(r0, r3)
            int r3 = r17.getEventType()
        L_0x000f:
            java.lang.String r4 = "menu"
            r5 = 2
            r6 = 1
            if (r3 == r5) goto L_0x001c
            int r3 = r17.next()
            if (r3 != r6) goto L_0x000f
            goto L_0x002a
        L_0x001c:
            java.lang.String r3 = r17.getName()
            boolean r7 = r3.equals(r4)
            if (r7 == 0) goto L_0x0252
            int r3 = r17.next()
        L_0x002a:
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x002f:
            if (r9 != 0) goto L_0x0251
            if (r3 == r6) goto L_0x0249
            java.lang.String r12 = "item"
            java.lang.String r13 = "group"
            r14 = 3
            if (r3 == r5) goto L_0x00b0
            if (r3 == r14) goto L_0x0042
        L_0x003c:
            r12 = r17
            r5 = 0
            r7 = 2
            goto L_0x0241
        L_0x0042:
            java.lang.String r3 = r17.getName()
            if (r10 != 0) goto L_0x0049
            goto L_0x0057
        L_0x0049:
            boolean r14 = r3.equals(r11)
            if (r14 == 0) goto L_0x0057
            r12 = r17
            r5 = 0
            r7 = 2
            r10 = 0
            r11 = 0
            goto L_0x0241
        L_0x0057:
            boolean r13 = r3.equals(r13)
            if (r13 == 0) goto L_0x0068
            r2.mo9722a()
            r12 = r17
            r5 = 0
            r7 = 2
            goto L_0x0241
        L_0x0068:
            boolean r12 = r3.equals(r12)
            if (r12 != 0) goto L_0x007c
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003c
            r12 = r17
            r5 = 0
            r7 = 2
            r9 = 1
            goto L_0x0241
        L_0x007c:
            boolean r3 = r2.f15637h
            if (r3 != 0) goto L_0x003c
            ln r3 = r2.f15624A
            if (r3 == 0) goto L_0x0095
            boolean r3 = r3.mo9369e()
            if (r3 != 0) goto L_0x008b
            goto L_0x0095
        L_0x008b:
            r2.mo9724b()
            r12 = r17
            r5 = 0
            r7 = 2
            goto L_0x0241
        L_0x0095:
            r2.f15637h = r6
            android.view.Menu r3 = r2.f15630a
            int r12 = r2.f15631b
            int r13 = r2.f15638i
            int r14 = r2.f15639j
            java.lang.CharSequence r15 = r2.f15640k
            android.view.MenuItem r3 = r3.add(r12, r13, r14, r15)
            r2.mo9723a((android.view.MenuItem) r3)
            r12 = r17
            r5 = 0
            r7 = 2
            goto L_0x0241
        L_0x00b0:
            if (r10 != 0) goto L_0x023d
            java.lang.String r3 = r17.getName()
            boolean r13 = r3.equals(r13)
            r15 = 5
            r7 = 4
            if (r13 != 0) goto L_0x0206
            boolean r12 = r3.equals(r12)
            if (r12 != 0) goto L_0x00e0
            boolean r7 = r3.equals(r4)
            if (r7 == 0) goto L_0x00d8
            android.view.SubMenu r3 = r2.mo9724b()
            r12 = r17
            r0.m15161a(r12, r1, r3)
            r5 = 0
            r7 = 2
            goto L_0x0241
        L_0x00d8:
            r12 = r17
            r11 = r3
            r5 = 0
            r7 = 2
            r10 = 1
            goto L_0x0241
        L_0x00e0:
            r12 = r17
            qm r3 = r2.f15629F
            android.content.Context r3 = r3.f15659c
            int[] r13 = p000.C0435px.f15589q
            zc r3 = p000.C0684zc.m16191a((android.content.Context) r3, (android.util.AttributeSet) r1, (int[]) r13)
            int r13 = r3.mo10734f(r5, r8)
            r2.f15638i = r13
            int r13 = r2.f15632c
            int r13 = r3.mo10722a((int) r15, (int) r13)
            r15 = -65536(0xffffffffffff0000, float:NaN)
            r13 = r13 & r15
            r15 = 6
            int r5 = r2.f15633d
            int r5 = r3.mo10722a((int) r15, (int) r5)
            char r5 = (char) r5
            r5 = r5 | r13
            r2.f15639j = r5
            r5 = 7
            java.lang.CharSequence r5 = r3.mo10729c(r5)
            r2.f15640k = r5
            r5 = 8
            java.lang.CharSequence r5 = r3.mo10729c(r5)
            r2.f15641l = r5
            int r5 = r3.mo10734f(r8, r8)
            r2.f15642m = r5
            r5 = 9
            java.lang.String r5 = r3.mo10731d(r5)
            char r5 = p000.C0450ql.m15156a((java.lang.String) r5)
            r2.f15643n = r5
            r5 = 16
            r13 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.mo10722a((int) r5, (int) r13)
            r2.f15644o = r5
            r5 = 10
            java.lang.String r5 = r3.mo10731d(r5)
            char r5 = p000.C0450ql.m15156a((java.lang.String) r5)
            r2.f15645p = r5
            r5 = 20
            int r5 = r3.mo10722a((int) r5, (int) r13)
            r2.f15646q = r5
            r5 = 11
            boolean r13 = r3.mo10735f(r5)
            if (r13 == 0) goto L_0x0154
            boolean r5 = r3.mo10725a((int) r5, (boolean) r8)
            r2.f15647r = r5
            goto L_0x0158
        L_0x0154:
            int r5 = r2.f15634e
            r2.f15647r = r5
        L_0x0158:
            boolean r5 = r3.mo10725a((int) r14, (boolean) r8)
            r2.f15648s = r5
            boolean r5 = r2.f15635f
            boolean r5 = r3.mo10725a((int) r7, (boolean) r5)
            r2.f15649t = r5
            boolean r5 = r2.f15636g
            boolean r5 = r3.mo10725a((int) r6, (boolean) r5)
            r2.f15650u = r5
            r5 = 21
            r7 = -1
            int r5 = r3.mo10722a((int) r5, (int) r7)
            r2.f15651v = r5
            r5 = 12
            java.lang.String r5 = r3.mo10731d(r5)
            r2.f15655z = r5
            r5 = 13
            int r5 = r3.mo10734f(r5, r8)
            r2.f15652w = r5
            r5 = 15
            java.lang.String r5 = r3.mo10731d(r5)
            r2.f15653x = r5
            r5 = 14
            java.lang.String r5 = r3.mo10731d(r5)
            r2.f15654y = r5
            java.lang.String r5 = r2.f15654y
            if (r5 == 0) goto L_0x01bc
            int r13 = r2.f15652w
            if (r13 == 0) goto L_0x01a1
            goto L_0x01b4
        L_0x01a1:
            java.lang.String r13 = r2.f15653x
            if (r13 != 0) goto L_0x01b4
            java.lang.Class[] r13 = f15657e
            qm r14 = r2.f15629F
            java.lang.Object[] r14 = r14.f15661f
            java.lang.Object r5 = r2.mo9721a(r5, r13, r14)
            ln r5 = (p000.C0317ln) r5
            r2.f15624A = r5
            goto L_0x01c0
        L_0x01b4:
            java.lang.String r5 = "SupportMenuInflater"
            java.lang.String r13 = "Ignoring attribute 'actionProviderClass'. Action view already specified."
            android.util.Log.w(r5, r13)
        L_0x01bc:
            r5 = 0
            r2.f15624A = r5
        L_0x01c0:
            r5 = 17
            java.lang.CharSequence r5 = r3.mo10729c(r5)
            r2.f15625B = r5
            r5 = 22
            java.lang.CharSequence r5 = r3.mo10729c(r5)
            r2.f15626C = r5
            r5 = 19
            boolean r13 = r3.mo10735f(r5)
            if (r13 == 0) goto L_0x01e5
            int r5 = r3.mo10722a((int) r5, (int) r7)
            android.graphics.PorterDuff$Mode r7 = r2.f15628E
            android.graphics.PorterDuff$Mode r5 = p000.C0579vf.m15603a(r5, r7)
            r2.f15628E = r5
            goto L_0x01e9
        L_0x01e5:
            r5 = 0
            r2.f15628E = r5
        L_0x01e9:
            r5 = 18
            boolean r7 = r3.mo10735f(r5)
            if (r7 == 0) goto L_0x01fa
            android.content.res.ColorStateList r5 = r3.mo10733e(r5)
            r2.f15627D = r5
            r5 = 0
            goto L_0x01fe
        L_0x01fa:
            r5 = 0
            r2.f15627D = r5
        L_0x01fe:
            r3.mo10724a()
            r2.f15637h = r8
            r7 = 2
            goto L_0x0241
        L_0x0206:
            r12 = r17
            r5 = 0
            qm r3 = r2.f15629F
            android.content.Context r3 = r3.f15659c
            int[] r13 = p000.C0435px.f15588p
            android.content.res.TypedArray r3 = r3.obtainStyledAttributes(r1, r13)
            int r13 = r3.getResourceId(r6, r8)
            r2.f15631b = r13
            int r13 = r3.getInt(r14, r8)
            r2.f15632c = r13
            int r7 = r3.getInt(r7, r8)
            r2.f15633d = r7
            int r7 = r3.getInt(r15, r8)
            r2.f15634e = r7
            r7 = 2
            boolean r13 = r3.getBoolean(r7, r6)
            r2.f15635f = r13
            boolean r13 = r3.getBoolean(r8, r6)
            r2.f15636g = r13
            r3.recycle()
            goto L_0x0241
        L_0x023d:
            r12 = r17
            r5 = 0
            r7 = 2
        L_0x0241:
            int r3 = r17.next()
            r5 = 2
            goto L_0x002f
        L_0x0249:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Unexpected end of document"
            r1.<init>(r2)
            throw r1
        L_0x0251:
            return
        L_0x0252:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Expecting menu, got "
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            goto L_0x026a
        L_0x0269:
            throw r1
        L_0x026a:
            goto L_0x0269
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0451qm.m15161a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }
}

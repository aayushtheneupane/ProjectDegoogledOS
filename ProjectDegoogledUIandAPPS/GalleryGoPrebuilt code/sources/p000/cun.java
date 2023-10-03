package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.Base64;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlPullParser;
import p003j$.util.function.Supplier;

/* renamed from: cun */
/* compiled from: PG */
public class cun {
    /* renamed from: a */
    public static int m5438a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 0 : 4;
        }
        return 3;
    }

    /* renamed from: b */
    public static /* synthetic */ String m5449b(int i) {
        switch (i) {
            case R.id.copy_to_folder /*2131361926*/:
                return "COPY_TO";
            case R.id.delete /*2131361934*/:
                return "DELETE";
            case R.id.move_to_folder /*2131362088*/:
                return "MOVE_TO";
            case R.id.picker_done /*2131362168*/:
                return "PICKER_DONE";
            case R.id.select_all /*2131362226*/:
                return "SELECT_ALL";
            case R.id.share /*2131362234*/:
                return "SHARE";
            default:
                return "null";
        }
    }

    /* renamed from: c */
    public static /* synthetic */ String m5450c(int i) {
        switch (i) {
            case 1:
                return "NONE";
            case RecyclerView.SCROLL_STATE_SETTLING:
                return "SIMPLE";
            case 3:
                return "CHOICE";
            case 4:
                return "PLURAL";
            case 5:
                return "SELECT";
            case 6:
                return "SELECTORDINAL";
            default:
                return "null";
        }
    }

    /* renamed from: d */
    public static boolean m5451d(int i) {
        return i == 4 || i == 6;
    }

    /* renamed from: a */
    public void mo518a(C0028az azVar) {
    }

    /* JADX WARNING: type inference failed for: r8v13, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList m5439a(android.content.res.Resources r17, org.xmlpull.v1.XmlPullParser r18, android.util.AttributeSet r19, android.content.res.Resources.Theme r20) {
        /*
            r0 = r19
            r1 = r20
            java.lang.String r2 = r18.getName()
            java.lang.String r3 = "selector"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0105
            int r2 = r18.getDepth()
            r3 = 1
            int r2 = r2 + r3
            r4 = 20
            int[][] r5 = new int[r4][]
            int[] r4 = new int[r4]
            r6 = 0
            r7 = 0
        L_0x001e:
            int r8 = r18.next()
            if (r8 == r3) goto L_0x00f5
            int r9 = r18.getDepth()
            if (r9 >= r2) goto L_0x002d
            r10 = 3
            if (r8 == r10) goto L_0x00f5
        L_0x002d:
            r10 = 2
            if (r8 != r10) goto L_0x00f0
            if (r9 > r2) goto L_0x00f0
            java.lang.String r8 = r18.getName()
            java.lang.String r9 = "item"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x00ed
            int[] r8 = p000.C0101dp.f6975a
            if (r1 == 0) goto L_0x0049
            android.content.res.TypedArray r8 = r1.obtainStyledAttributes(r0, r8, r6, r6)
            r9 = r17
            goto L_0x004f
        L_0x0049:
            r9 = r17
            android.content.res.TypedArray r8 = r9.obtainAttributes(r0, r8)
        L_0x004f:
            r11 = -65281(0xffffffffffff00ff, float:NaN)
            int r11 = r8.getColor(r6, r11)
            boolean r12 = r8.hasValue(r3)
            r13 = 1065353216(0x3f800000, float:1.0)
            if (r12 == 0) goto L_0x0063
            float r13 = r8.getFloat(r3, r13)
            goto L_0x0070
        L_0x0063:
            boolean r12 = r8.hasValue(r10)
            if (r12 != 0) goto L_0x006b
            goto L_0x0070
        L_0x006b:
            float r13 = r8.getFloat(r10, r13)
        L_0x0070:
            r8.recycle()
            int r8 = r19.getAttributeCount()
            int[] r10 = new int[r8]
            r12 = 0
            r14 = 0
        L_0x007b:
            if (r12 >= r8) goto L_0x00a2
            int r15 = r0.getAttributeNameResource(r12)
            r3 = 16843173(0x10101a5, float:2.3694738E-38)
            if (r15 != r3) goto L_0x0087
        L_0x0086:
            goto L_0x009e
        L_0x0087:
            r3 = 16843551(0x101031f, float:2.3695797E-38)
            if (r15 == r3) goto L_0x0086
            r3 = 2130968624(0x7f040030, float:1.7545907E38)
            if (r15 == r3) goto L_0x0086
            int r3 = r14 + 1
            boolean r16 = r0.getAttributeBooleanValue(r12, r6)
            if (r16 != 0) goto L_0x009a
            int r15 = -r15
        L_0x009a:
            r10[r14] = r15
            r14 = r3
        L_0x009e:
            int r12 = r12 + 1
            r3 = 1
            goto L_0x007b
        L_0x00a2:
            int[] r3 = android.util.StateSet.trimStateSet(r10, r14)
            r8 = 16777215(0xffffff, float:2.3509886E-38)
            r8 = r8 & r11
            int r10 = android.graphics.Color.alpha(r11)
            float r10 = (float) r10
            float r10 = r10 * r13
            int r10 = java.lang.Math.round(r10)
            int r10 = r10 << 24
            r8 = r8 | r10
            int r10 = r7 + 1
            int r11 = r4.length
            if (r10 > r11) goto L_0x00be
            goto L_0x00c9
        L_0x00be:
            int r11 = p000.C0071co.m4668b((int) r7)
            int[] r11 = new int[r11]
            java.lang.System.arraycopy(r4, r6, r11, r6, r7)
            r4 = r11
        L_0x00c9:
            r4[r7] = r8
            int r8 = r5.length
            if (r10 <= r8) goto L_0x00e4
            java.lang.Class r8 = r5.getClass()
            java.lang.Class r8 = r8.getComponentType()
            int r11 = p000.C0071co.m4668b((int) r7)
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r11)
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            java.lang.System.arraycopy(r5, r6, r8, r6, r7)
            r5 = r8
        L_0x00e4:
            r5[r7] = r3
            int[][] r5 = (int[][]) r5
            r7 = r10
            r3 = 1
            goto L_0x001e
        L_0x00ed:
            r9 = r17
            goto L_0x00f2
        L_0x00f0:
            r9 = r17
        L_0x00f2:
            r3 = 1
            goto L_0x001e
        L_0x00f5:
            int[] r0 = new int[r7]
            int[][] r1 = new int[r7][]
            java.lang.System.arraycopy(r4, r6, r0, r6, r7)
            java.lang.System.arraycopy(r5, r6, r1, r6, r7)
            android.content.res.ColorStateList r2 = new android.content.res.ColorStateList
            r2.<init>(r1, r0)
            return r2
        L_0x0105:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r18.getPositionDescription()
            r1.append(r3)
            java.lang.String r3 = ": invalid color state list tag "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            goto L_0x0124
        L_0x0123:
            throw r0
        L_0x0124:
            goto L_0x0123
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cun.m5439a(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    /* renamed from: a */
    public static void m5448a(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    /* renamed from: a */
    public static List m5447a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String decode : strArr) {
            arrayList.add(Base64.decode(decode, 0));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static cue m5444a(cue... cueArr) {
        return new ctu(cueArr);
    }

    /* renamed from: a */
    public static cue m5442a(Callable callable) {
        return new cua(callable);
    }

    /* renamed from: a */
    public static ieh m5445a(cue cue, ice ice, Executor executor) {
        return gte.m10771a(cue.mo3142a(), (icf) new ctp(ice), executor);
    }

    /* renamed from: a */
    public static cue m5443a(boolean z) {
        return m5442a((Callable) new cty(z));
    }

    /* renamed from: a */
    public static cue m5441a(Supplier supplier) {
        return new ctw(supplier);
    }

    /* renamed from: a */
    public static cue m5440a() {
        return m5443a(true);
    }

    /* renamed from: a */
    public static ieh m5446a(Iterable iterable, cue cue, Executor executor) {
        return cue.mo3143a(new cuk(iterable, cue, executor), executor);
    }
}

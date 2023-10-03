package androidx.core.content.p022a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.C0297R;
import androidx.core.provider.FontRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: androidx.core.content.a.a */
public final class C0308a {
    /* renamed from: a */
    public static boolean m244a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    /* renamed from: b */
    public static int m245b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        if (!m244a(xmlPullParser, str)) {
            return i2;
        }
        return typedArray.getInt(i, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList createFromXml(android.content.res.Resources r4, org.xmlpull.v1.XmlPullParser r5, android.content.res.Resources.Theme r6) {
        /*
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)
        L_0x0004:
            int r1 = r5.next()
            r2 = 2
            if (r1 == r2) goto L_0x000f
            r3 = 1
            if (r1 == r3) goto L_0x000f
            goto L_0x0004
        L_0x000f:
            if (r1 != r2) goto L_0x0016
            android.content.res.ColorStateList r4 = createFromXmlInner((android.content.res.Resources) r4, (org.xmlpull.v1.XmlPullParser) r5, (android.util.AttributeSet) r0, (android.content.res.Resources.Theme) r6)
            return r4
        L_0x0016:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r5 = "No start tag found"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.p022a.C0308a.createFromXml(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x011d, code lost:
        if (r12.size() <= 0) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x011f, code lost:
        r0 = new androidx.core.content.p022a.C0314g((java.util.List) r12, (java.util.List) r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0125, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0126, code lost:
        if (r0 == null) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0129, code lost:
        if (r13 == false) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x012b, code lost:
        r0 = new androidx.core.content.p022a.C0314g(r7, r4, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0131, code lost:
        r0 = new androidx.core.content.p022a.C0314g(r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0137, code lost:
        if (r5 == 1) goto L_0x016a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x013a, code lost:
        if (r5 == 2) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x013c, code lost:
        r13 = r0.mColors;
        r14 = r0.f313co;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0142, code lost:
        if (r6 == 1) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0144, code lost:
        if (r6 == 2) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0146, code lost:
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0149, code lost:
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x014c, code lost:
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x015b, code lost:
        return new android.graphics.LinearGradient(r22, r21, r20, r18, r13, r14, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0169, code lost:
        return new android.graphics.SweepGradient(r16, r17, r0.mColors, r0.f313co);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x016a, code lost:
        r3 = r16;
        r4 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0171, code lost:
        if (r19 <= 0.0f) goto L_0x0196;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0173, code lost:
        r2 = r0.mColors;
        r0 = r0.f313co;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x017a, code lost:
        if (r6 == 1) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x017d, code lost:
        if (r6 == 2) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x017f, code lost:
        r5 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0182, code lost:
        r5 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0185, code lost:
        r5 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0195, code lost:
        return new android.graphics.RadialGradient(r3, r4, r19, r2, r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x019d, code lost:
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* renamed from: createFromXmlInner  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Shader m4702createFromXmlInner(android.content.res.Resources r23, org.xmlpull.v1.XmlPullParser r24, android.util.AttributeSet r25, android.content.res.Resources.Theme r26) {
        /*
            r0 = r24
            r1 = r25
            r2 = r26
            java.lang.String r3 = r24.getName()
            java.lang.String r4 = "gradient"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x019e
            int[] r3 = androidx.core.C0297R.styleable.GradientColor
            r4 = r23
            android.content.res.TypedArray r3 = m241a(r4, r2, r1, r3)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_startX
            r6 = 0
            java.lang.String r7 = "startX"
            float r9 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_startY
            java.lang.String r7 = "startY"
            float r10 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_endX
            java.lang.String r7 = "endX"
            float r11 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_endY
            java.lang.String r7 = "endY"
            float r12 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_centerX
            java.lang.String r7 = "centerX"
            float r14 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_centerY
            java.lang.String r7 = "centerY"
            float r15 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r7, (int) r5, (float) r6)
            int r5 = androidx.core.C0297R.styleable.GradientColor_android_type
            r6 = 0
            java.lang.String r7 = "type"
            int r5 = m245b(r3, r0, r7, r5, r6)
            int r7 = androidx.core.C0297R.styleable.GradientColor_android_startColor
            java.lang.String r8 = "startColor"
            int r7 = m240a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r8, (int) r7, (int) r6)
            java.lang.String r8 = "centerColor"
            boolean r13 = m244a((org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r8)
            int r4 = androidx.core.C0297R.styleable.GradientColor_android_centerColor
            int r4 = m240a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r8, (int) r4, (int) r6)
            int r8 = androidx.core.C0297R.styleable.GradientColor_android_endColor
            r16 = r14
            java.lang.String r14 = "endColor"
            int r8 = m240a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r14, (int) r8, (int) r6)
            int r14 = androidx.core.C0297R.styleable.GradientColor_android_tileMode
            r17 = r15
            java.lang.String r15 = "tileMode"
            int r6 = m245b(r3, r0, r15, r14, r6)
            int r14 = androidx.core.C0297R.styleable.GradientColor_android_gradientRadius
            java.lang.String r15 = "gradientRadius"
            r18 = r12
            r12 = 0
            float r12 = m239a((android.content.res.TypedArray) r3, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r15, (int) r14, (float) r12)
            r3.recycle()
            int r3 = r24.getDepth()
            int r3 = r3 + 1
            java.util.ArrayList r14 = new java.util.ArrayList
            r15 = 20
            r14.<init>(r15)
            r19 = r12
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>(r15)
            r15 = r23
            r20 = r11
        L_0x00a2:
            int r11 = r24.next()
            r21 = r10
            r10 = 1
            if (r11 == r10) goto L_0x0117
            int r10 = r24.getDepth()
            r22 = r9
            if (r10 >= r3) goto L_0x00b6
            r9 = 3
            if (r11 == r9) goto L_0x0119
        L_0x00b6:
            r9 = 2
            if (r11 == r9) goto L_0x00ba
            goto L_0x0112
        L_0x00ba:
            if (r10 > r3) goto L_0x0110
            java.lang.String r9 = r24.getName()
            java.lang.String r10 = "item"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x00c9
            goto L_0x0112
        L_0x00c9:
            int[] r9 = androidx.core.C0297R.styleable.GradientColorItem
            android.content.res.TypedArray r9 = m241a(r15, r2, r1, r9)
            int r10 = androidx.core.C0297R.styleable.GradientColorItem_android_color
            boolean r10 = r9.hasValue(r10)
            int r11 = androidx.core.C0297R.styleable.GradientColorItem_android_offset
            boolean r11 = r9.hasValue(r11)
            if (r10 == 0) goto L_0x00ff
            if (r11 == 0) goto L_0x00ff
            int r10 = androidx.core.C0297R.styleable.GradientColorItem_android_color
            r11 = 0
            int r10 = r9.getColor(r10, r11)
            int r11 = androidx.core.C0297R.styleable.GradientColorItem_android_offset
            r15 = 0
            float r11 = r9.getFloat(r11, r15)
            r9.recycle()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            r12.add(r9)
            java.lang.Float r9 = java.lang.Float.valueOf(r11)
            r14.add(r9)
            goto L_0x0110
        L_0x00ff:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r0, (java.lang.StringBuilder) r2, (java.lang.String) r3)
            r1.<init>(r0)
            throw r1
        L_0x0110:
            r15 = r23
        L_0x0112:
            r10 = r21
            r9 = r22
            goto L_0x00a2
        L_0x0117:
            r22 = r9
        L_0x0119:
            int r0 = r12.size()
            if (r0 <= 0) goto L_0x0125
            androidx.core.content.a.g r0 = new androidx.core.content.a.g
            r0.<init>((java.util.List) r12, (java.util.List) r14)
            goto L_0x0126
        L_0x0125:
            r0 = 0
        L_0x0126:
            if (r0 == 0) goto L_0x0129
            goto L_0x0136
        L_0x0129:
            if (r13 == 0) goto L_0x0131
            androidx.core.content.a.g r0 = new androidx.core.content.a.g
            r0.<init>(r7, r4, r8)
            goto L_0x0136
        L_0x0131:
            androidx.core.content.a.g r0 = new androidx.core.content.a.g
            r0.<init>((int) r7, (int) r8)
        L_0x0136:
            r1 = 1
            if (r5 == r1) goto L_0x016a
            r2 = 2
            if (r5 == r2) goto L_0x015c
            android.graphics.LinearGradient r3 = new android.graphics.LinearGradient
            int[] r13 = r0.mColors
            float[] r14 = r0.f313co
            if (r6 == r1) goto L_0x014c
            if (r6 == r2) goto L_0x0149
            android.graphics.Shader$TileMode r0 = android.graphics.Shader.TileMode.CLAMP
            goto L_0x014e
        L_0x0149:
            android.graphics.Shader$TileMode r0 = android.graphics.Shader.TileMode.MIRROR
            goto L_0x014e
        L_0x014c:
            android.graphics.Shader$TileMode r0 = android.graphics.Shader.TileMode.REPEAT
        L_0x014e:
            r15 = r0
            r8 = r3
            r9 = r22
            r10 = r21
            r11 = r20
            r12 = r18
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            return r3
        L_0x015c:
            android.graphics.SweepGradient r1 = new android.graphics.SweepGradient
            int[] r2 = r0.mColors
            float[] r0 = r0.f313co
            r3 = r16
            r4 = r17
            r1.<init>(r3, r4, r2, r0)
            return r1
        L_0x016a:
            r3 = r16
            r4 = r17
            r1 = 0
            int r1 = (r19 > r1 ? 1 : (r19 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x0196
            android.graphics.RadialGradient r1 = new android.graphics.RadialGradient
            int[] r2 = r0.mColors
            float[] r0 = r0.f313co
            r5 = 1
            if (r6 == r5) goto L_0x0185
            r5 = 2
            if (r6 == r5) goto L_0x0182
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.CLAMP
            goto L_0x0187
        L_0x0182:
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.MIRROR
            goto L_0x0187
        L_0x0185:
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.REPEAT
        L_0x0187:
            r13 = r1
            r14 = r3
            r15 = r4
            r16 = r19
            r17 = r2
            r18 = r0
            r19 = r5
            r13.<init>(r14, r15, r16, r17, r18, r19)
            return r1
        L_0x0196:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "<gradient> tag requires 'gradientRadius' attribute with radial type"
            r0.<init>(r1)
            throw r0
        L_0x019e:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = r24.getPositionDescription()
            r2.append(r0)
            java.lang.String r0 = ": invalid gradient color tag "
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.p022a.C0308a.m4702createFromXmlInner(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.graphics.Shader");
    }

    public static C0310c parse(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        Resources resources2 = resources;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            xmlPullParser.require(2, (String) null, "font-family");
            if (xmlPullParser.getName().equals("font-family")) {
                TypedArray obtainAttributes = resources2.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0297R.styleable.FontFamily);
                String string = obtainAttributes.getString(C0297R.styleable.FontFamily_fontProviderAuthority);
                String string2 = obtainAttributes.getString(C0297R.styleable.FontFamily_fontProviderPackage);
                String string3 = obtainAttributes.getString(C0297R.styleable.FontFamily_fontProviderQuery);
                int resourceId = obtainAttributes.getResourceId(C0297R.styleable.FontFamily_fontProviderCerts, 0);
                int integer = obtainAttributes.getInteger(C0297R.styleable.FontFamily_fontProviderFetchStrategy, 1);
                int integer2 = obtainAttributes.getInteger(C0297R.styleable.FontFamily_fontProviderFetchTimeout, 500);
                obtainAttributes.recycle();
                if (string == null || string2 == null || string3 == null) {
                    ArrayList arrayList = new ArrayList();
                    while (xmlPullParser.next() != 3) {
                        if (xmlPullParser.getEventType() == 2) {
                            if (xmlPullParser.getName().equals("font")) {
                                TypedArray obtainAttributes2 = resources2.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0297R.styleable.FontFamilyFont);
                                int i = obtainAttributes2.getInt(obtainAttributes2.hasValue(C0297R.styleable.FontFamilyFont_fontWeight) ? C0297R.styleable.FontFamilyFont_fontWeight : C0297R.styleable.FontFamilyFont_android_fontWeight, 400);
                                boolean z = 1 == obtainAttributes2.getInt(obtainAttributes2.hasValue(C0297R.styleable.FontFamilyFont_fontStyle) ? C0297R.styleable.FontFamilyFont_fontStyle : C0297R.styleable.FontFamilyFont_android_fontStyle, 0);
                                int i2 = obtainAttributes2.hasValue(C0297R.styleable.FontFamilyFont_ttcIndex) ? C0297R.styleable.FontFamilyFont_ttcIndex : C0297R.styleable.FontFamilyFont_android_ttcIndex;
                                String string4 = obtainAttributes2.getString(obtainAttributes2.hasValue(C0297R.styleable.FontFamilyFont_fontVariationSettings) ? C0297R.styleable.FontFamilyFont_fontVariationSettings : C0297R.styleable.FontFamilyFont_android_fontVariationSettings);
                                int i3 = obtainAttributes2.getInt(i2, 0);
                                int i4 = obtainAttributes2.hasValue(C0297R.styleable.FontFamilyFont_font) ? C0297R.styleable.FontFamilyFont_font : C0297R.styleable.FontFamilyFont_android_font;
                                int resourceId2 = obtainAttributes2.getResourceId(i4, 0);
                                String string5 = obtainAttributes2.getString(i4);
                                obtainAttributes2.recycle();
                                while (xmlPullParser.next() != 3) {
                                    skip(xmlPullParser);
                                }
                                arrayList.add(new C0312e(string5, i, z, string4, i3, resourceId2));
                            } else {
                                skip(xmlPullParser);
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return null;
                    }
                    return new C0311d((C0312e[]) arrayList.toArray(new C0312e[arrayList.size()]));
                }
                while (xmlPullParser.next() != 3) {
                    skip(xmlPullParser);
                }
                return new C0313f(new FontRequest(string, string2, string3, m243a(resources2, resourceId)), integer, integer2);
            }
            skip(xmlPullParser);
            return null;
        }
        throw new XmlPullParserException("No start tag found");
    }

    private static void skip(XmlPullParser xmlPullParser) {
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
    public static float m239a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        if (!m244a(xmlPullParser, str)) {
            return f;
        }
        return typedArray.getFloat(i, f);
    }

    /* renamed from: b */
    private static List m246b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String decode : strArr) {
            arrayList.add(Base64.decode(decode, 0));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static int m240a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        if (!m244a(xmlPullParser, str)) {
            return i2;
        }
        return typedArray.getColor(i, i2);
    }

    /* renamed from: a */
    public static C0309b m242a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i, int i2) {
        if (m244a(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return C0309b.m247N(typedValue.data);
            }
            C0309b b = C0309b.m248b(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            if (b != null) {
                return b;
            }
        }
        return C0309b.m247N(i2);
    }

    /* renamed from: a */
    public static List m243a(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            int i2 = Build.VERSION.SDK_INT;
            if (obtainTypedArray.getType(0) == 1) {
                for (int i3 = 0; i3 < obtainTypedArray.length(); i3++) {
                    int resourceId = obtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        arrayList.add(m246b(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(m246b(resources.getStringArray(i)));
            }
            obtainTypedArray.recycle();
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    /* renamed from: a */
    public static TypedArray m241a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    /* JADX WARNING: type inference failed for: r8v16, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList createFromXmlInner(android.content.res.Resources r17, org.xmlpull.v1.XmlPullParser r18, android.util.AttributeSet r19, android.content.res.Resources.Theme r20) {
        /*
            r0 = r19
            r1 = r20
            java.lang.String r2 = r18.getName()
            java.lang.String r3 = "selector"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0104
            int r2 = r18.getDepth()
            r3 = 1
            int r2 = r2 + r3
            r4 = 20
            int[][] r4 = new int[r4][]
            int r5 = r4.length
            int[] r5 = new int[r5]
            r6 = 0
            r7 = r6
        L_0x001f:
            int r8 = r18.next()
            if (r8 == r3) goto L_0x00f4
            int r9 = r18.getDepth()
            if (r9 >= r2) goto L_0x002e
            r10 = 3
            if (r8 == r10) goto L_0x00f4
        L_0x002e:
            r10 = 2
            if (r8 != r10) goto L_0x00ef
            if (r9 > r2) goto L_0x00ef
            java.lang.String r8 = r18.getName()
            java.lang.String r9 = "item"
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x0041
            goto L_0x00ef
        L_0x0041:
            int[] r8 = androidx.core.C0297R.styleable.ColorStateListItem
            if (r1 != 0) goto L_0x004c
            r9 = r17
            android.content.res.TypedArray r8 = r9.obtainAttributes(r0, r8)
            goto L_0x0052
        L_0x004c:
            r9 = r17
            android.content.res.TypedArray r8 = r1.obtainStyledAttributes(r0, r8, r6, r6)
        L_0x0052:
            int r10 = androidx.core.C0297R.styleable.ColorStateListItem_android_color
            r11 = -65281(0xffffffffffff00ff, float:NaN)
            int r10 = r8.getColor(r10, r11)
            r11 = 1065353216(0x3f800000, float:1.0)
            int r12 = androidx.core.C0297R.styleable.ColorStateListItem_android_alpha
            boolean r12 = r8.hasValue(r12)
            if (r12 == 0) goto L_0x006c
            int r12 = androidx.core.C0297R.styleable.ColorStateListItem_android_alpha
            float r11 = r8.getFloat(r12, r11)
            goto L_0x007a
        L_0x006c:
            int r12 = androidx.core.C0297R.styleable.ColorStateListItem_alpha
            boolean r12 = r8.hasValue(r12)
            if (r12 == 0) goto L_0x007a
            int r12 = androidx.core.C0297R.styleable.ColorStateListItem_alpha
            float r11 = r8.getFloat(r12, r11)
        L_0x007a:
            r8.recycle()
            int r8 = r19.getAttributeCount()
            int[] r12 = new int[r8]
            r13 = r6
            r14 = r13
        L_0x0085:
            if (r13 >= r8) goto L_0x00aa
            int r15 = r0.getAttributeNameResource(r13)
            r3 = 16843173(0x10101a5, float:2.3694738E-38)
            if (r15 == r3) goto L_0x00a6
            r3 = 16843551(0x101031f, float:2.3695797E-38)
            if (r15 == r3) goto L_0x00a6
            int r3 = androidx.core.C0297R.attr.alpha
            if (r15 == r3) goto L_0x00a6
            int r3 = r14 + 1
            boolean r16 = r0.getAttributeBooleanValue(r13, r6)
            if (r16 == 0) goto L_0x00a2
            goto L_0x00a3
        L_0x00a2:
            int r15 = -r15
        L_0x00a3:
            r12[r14] = r15
            r14 = r3
        L_0x00a6:
            int r13 = r13 + 1
            r3 = 1
            goto L_0x0085
        L_0x00aa:
            int[] r3 = android.util.StateSet.trimStateSet(r12, r14)
            int r8 = android.graphics.Color.alpha(r10)
            float r8 = (float) r8
            float r8 = r8 * r11
            int r8 = java.lang.Math.round(r8)
            r11 = 16777215(0xffffff, float:2.3509886E-38)
            r10 = r10 & r11
            int r8 = r8 << 24
            r8 = r8 | r10
            int r10 = r7 + 1
            int r11 = r5.length
            if (r10 <= r11) goto L_0x00ce
            int r11 = androidx.core.content.p022a.C0315h.growSize(r7)
            int[] r11 = new int[r11]
            java.lang.System.arraycopy(r5, r6, r11, r6, r7)
            r5 = r11
        L_0x00ce:
            r5[r7] = r8
            int r8 = r4.length
            if (r10 <= r8) goto L_0x00e9
            java.lang.Class r8 = r4.getClass()
            java.lang.Class r8 = r8.getComponentType()
            int r11 = androidx.core.content.p022a.C0315h.growSize(r7)
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r11)
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            java.lang.System.arraycopy(r4, r6, r8, r6, r7)
            r4 = r8
        L_0x00e9:
            r4[r7] = r3
            int[][] r4 = (int[][]) r4
            r7 = r10
            goto L_0x00f1
        L_0x00ef:
            r9 = r17
        L_0x00f1:
            r3 = 1
            goto L_0x001f
        L_0x00f4:
            int[] r0 = new int[r7]
            int[][] r1 = new int[r7][]
            java.lang.System.arraycopy(r5, r6, r0, r6, r7)
            java.lang.System.arraycopy(r4, r6, r1, r6, r7)
            android.content.res.ColorStateList r2 = new android.content.res.ColorStateList
            r2.<init>(r1, r0)
            return r2
        L_0x0104:
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
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.p022a.C0308a.createFromXmlInner(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }
}

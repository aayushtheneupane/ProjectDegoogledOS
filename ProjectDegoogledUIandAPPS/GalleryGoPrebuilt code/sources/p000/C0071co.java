package p000;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.AttributeSet;
import android.util.TypedValue;
import java.io.File;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: co */
/* compiled from: PG */
public final class C0071co {
    /* renamed from: a */
    public static boolean m4665a(int i) {
        return i == 13 || i == 14;
    }

    /* renamed from: b */
    public static int m4668b(int i) {
        if (i <= 4) {
            return 8;
        }
        return i + i;
    }

    /* renamed from: a */
    public static void m4664a(Activity activity, Intent intent, int i) {
        int i2 = Build.VERSION.SDK_INT;
        activity.startActivityForResult(intent, i, (Bundle) null);
    }

    /* renamed from: c */
    public static Context m4672c(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.createDeviceProtectedStorageContext();
    }

    /* renamed from: b */
    public static int m4669b(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getColor(i);
    }

    /* renamed from: a */
    public static File m4662a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getDataDir();
    }

    /* renamed from: a */
    public static Drawable m4660a(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getDrawable(i);
    }

    /* renamed from: b */
    public static File[] m4671b(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalCacheDirs();
    }

    /* renamed from: d */
    public static File[] m4676d(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalFilesDirs((String) null);
    }

    /* renamed from: a */
    public static int m4653a(Context context, String str) {
        int myPid = Process.myPid();
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, myPid, myUid) != -1) {
            int i = Build.VERSION.SDK_INT;
            String permissionToOp = AppOpsManager.permissionToOp(str);
            if (permissionToOp == null) {
                return 0;
            }
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(myUid);
                if (packagesForUid != null && packagesForUid.length > 0) {
                    packageName = packagesForUid[0];
                }
            }
            int i2 = Build.VERSION.SDK_INT;
            if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(permissionToOp, packageName) == 0) {
                return 0;
            }
            return -2;
        }
        return -1;
    }

    /* renamed from: c */
    public static Shader.TileMode m4673c(int i) {
        if (i == 1) {
            return Shader.TileMode.REPEAT;
        }
        if (i != 2) {
            return Shader.TileMode.CLAMP;
        }
        return Shader.TileMode.MIRROR;
    }

    /* renamed from: a */
    public static Typeface m4658a(Context context, int i, TypedValue typedValue, int i2, C0237io ioVar, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        return m4659a(context, resources, typedValue, i, i2, ioVar, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        r3.require(2, r12, "font-family");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0065, code lost:
        if (r3.getName().equals("font-family") == false) goto L_0x01c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0067, code lost:
        r2 = r0.obtainAttributes(android.util.Xml.asAttributeSet(r3), p000.C0101dp.f6976b);
        r14 = r2.getString(0);
        r12 = r2.getString(4);
        r11 = r2.getString(5);
        r15 = r2.getResourceId(1, 0);
        r6 = r2.getInteger(2, 1);
        r7 = r2.getInteger(3, 500);
        r2.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0092, code lost:
        if (r14 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0096, code lost:
        if (r12 == null) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0098, code lost:
        if (r11 == null) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009e, code lost:
        if (r3.next() == 3) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a0, code lost:
        p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a4, code lost:
        if (r15 == 0) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a6, code lost:
        r2 = r0.obtainTypedArray(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ae, code lost:
        if (r2.length() == 0) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b0, code lost:
        r3 = new java.util.ArrayList();
        r13 = android.os.Build.VERSION.SDK_INT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bd, code lost:
        if (r2.getType(0) == 1) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bf, code lost:
        r3.add(p000.cun.m5447a(r0.getStringArray(r15)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cb, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d0, code lost:
        if (r1 >= r2.length()) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d2, code lost:
        r15 = r2.getResourceId(r1, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d7, code lost:
        if (r15 == 0) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d9, code lost:
        r3.add(p000.cun.m5447a(r0.getStringArray(r15)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e4, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e7, code lost:
        r3 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f4, code lost:
        r3 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f8, code lost:
        r2 = new p000.C0233ik(new p000.C0265jp(r14, r12, r11, r3), r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0106, code lost:
        r1 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010f, code lost:
        if (r3.next() == 3) goto L_0x01ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0116, code lost:
        if (r3.getEventType() != 2) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0122, code lost:
        if (r3.getName().equals("font") == false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0124, code lost:
        r2 = r0.obtainAttributes(android.util.Xml.asAttributeSet(r3), p000.C0101dp.f6977c);
        r7 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0134, code lost:
        if (r2.hasValue(8) != false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0136, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013a, code lost:
        r18 = r2.getInt(r7, 400);
        r7 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (r2.hasValue(6) != false) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0147, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0151, code lost:
        if (r2.getInt(r7, 0) != 1) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0153, code lost:
        r19 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0156, code lost:
        r19 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0159, code lost:
        r7 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015f, code lost:
        if (r2.hasValue(9) != false) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0161, code lost:
        r7 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0162, code lost:
        r11 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0167, code lost:
        if (r2.hasValue(7) != false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0169, code lost:
        r11 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x016d, code lost:
        r20 = r2.getString(r11);
        r21 = r2.getInt(r7, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x017b, code lost:
        if (r2.hasValue(5) != false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x017d, code lost:
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x017f, code lost:
        r11 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0181, code lost:
        r22 = r2.getResourceId(r11, 0);
        r17 = r2.getString(r11);
        r2.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0191, code lost:
        if (r3.next() != 3) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0193, code lost:
        r1.add(new p000.C0232ij(r17, r18, r19, r20, r21, r22));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x019f, code lost:
        p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a3, code lost:
        p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01b2, code lost:
        if (r1.isEmpty() != false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b4, code lost:
        r2 = new p000.C0231ii((p000.C0232ij[]) r1.toArray(new p000.C0232ij[r1.size()]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01c6, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c9, code lost:
        p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3);
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01ce, code lost:
        if (r2 != null) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01d0, code lost:
        android.util.Log.e("ResourcesCompat", "Failed to find font-family tag");
        r8.mo9035a(-3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01da, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01eb, code lost:
        return p000.C0241is.m14373a(r23, r2, r24, r26, r27, r28, r29);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface m4659a(android.content.Context r23, android.content.res.Resources r24, android.util.TypedValue r25, int r26, int r27, p000.C0237io r28, boolean r29) {
        /*
            r0 = r24
            r1 = r25
            r4 = r26
            r5 = r27
            r8 = r28
            java.lang.String r2 = "font-family"
            java.lang.String r9 = "ResourcesCompat"
            java.lang.CharSequence r3 = r1.string
            if (r3 == 0) goto L_0x0239
            java.lang.CharSequence r1 = r1.string
            java.lang.String r10 = r1.toString()
            java.lang.String r1 = "res/"
            boolean r1 = r10.startsWith(r1)
            r11 = -3
            r12 = 0
            if (r1 == 0) goto L_0x0233
            ku r1 = p000.C0241is.f14961b
            java.lang.String r3 = p000.C0241is.m14374a((android.content.res.Resources) r0, (int) r4, (int) r5)
            java.lang.Object r1 = r1.mo9237a((java.lang.Object) r3)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 != 0) goto L_0x022f
            java.lang.String r1 = r10.toLowerCase()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.String r3 = ".xml"
            boolean r1 = r1.endsWith(r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r1 != 0) goto L_0x004c
            r1 = r23
            android.graphics.Typeface r0 = p000.C0241is.m14371a(r1, r0, r4, r10, r5)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r0 != 0) goto L_0x0048
            r8.mo9035a((int) r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x004b
        L_0x0048:
            r8.mo9036b(r0)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x004b:
            return r0
        L_0x004c:
            r1 = r23
            android.content.res.XmlResourceParser r3 = r0.getXml(r4)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x0052:
            int r6 = r3.next()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r7 = 2
            r13 = 1
            if (r6 != r7) goto L_0x01ec
            r3.require(r7, r12, r2)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.String r6 = r3.getName()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            boolean r2 = r6.equals(r2)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 == 0) goto L_0x01c9
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int[] r6 = p000.C0101dp.f6976b     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            android.content.res.TypedArray r2 = r0.obtainAttributes(r2, r6)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r6 = 0
            java.lang.String r14 = r2.getString(r6)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r15 = 4
            java.lang.String r12 = r2.getString(r15)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r15 = 5
            java.lang.String r11 = r2.getString(r15)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int r15 = r2.getResourceId(r13, r6)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int r6 = r2.getInteger(r7, r13)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r7 = 500(0x1f4, float:7.0E-43)
            r13 = 3
            int r7 = r2.getInteger(r13, r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2.recycle()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r14 != 0) goto L_0x0096
            goto L_0x0106
        L_0x0096:
            if (r12 == 0) goto L_0x0106
            if (r11 == 0) goto L_0x0106
        L_0x009a:
            int r2 = r3.next()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 == r13) goto L_0x00a4
            p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x009a
        L_0x00a4:
            if (r15 == 0) goto L_0x00f4
            android.content.res.TypedArray r2 = r0.obtainTypedArray(r15)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int r3 = r2.length()     // Catch:{ all -> 0x00ef }
            if (r3 == 0) goto L_0x00e7
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00ef }
            r3.<init>()     // Catch:{ all -> 0x00ef }
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00ef }
            r13 = 0
            int r1 = r2.getType(r13)     // Catch:{ all -> 0x00ef }
            r13 = 1
            if (r1 == r13) goto L_0x00cb
            java.lang.String[] r1 = r0.getStringArray(r15)     // Catch:{ all -> 0x00ef }
            java.util.List r1 = p000.cun.m5447a((java.lang.String[]) r1)     // Catch:{ all -> 0x00ef }
            r3.add(r1)     // Catch:{ all -> 0x00ef }
            goto L_0x00eb
        L_0x00cb:
            r1 = 0
        L_0x00cc:
            int r13 = r2.length()     // Catch:{ all -> 0x00ef }
            if (r1 >= r13) goto L_0x00eb
            r13 = 0
            int r15 = r2.getResourceId(r1, r13)     // Catch:{ all -> 0x00ef }
            if (r15 == 0) goto L_0x00e4
            java.lang.String[] r13 = r0.getStringArray(r15)     // Catch:{ all -> 0x00ef }
            java.util.List r13 = p000.cun.m5447a((java.lang.String[]) r13)     // Catch:{ all -> 0x00ef }
            r3.add(r13)     // Catch:{ all -> 0x00ef }
        L_0x00e4:
            int r1 = r1 + 1
            goto L_0x00cc
        L_0x00e7:
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00ef }
        L_0x00eb:
            r2.recycle()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x00f8
        L_0x00ef:
            r0 = move-exception
            r2.recycle()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            throw r0     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x00f4:
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x00f8:
            ik r1 = new ik     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            jp r2 = new jp     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2.<init>(r14, r12, r11, r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r1.<init>(r2, r6, r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2 = r1
            goto L_0x01ce
        L_0x0106:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x010b:
            int r2 = r3.next()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 == r13) goto L_0x01ae
            int r2 = r3.getEventType()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r6 = 2
            if (r2 != r6) goto L_0x01aa
            java.lang.String r2 = r3.getName()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.String r7 = "font"
            boolean r2 = r2.equals(r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 == 0) goto L_0x01a3
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int[] r7 = p000.C0101dp.f6977c     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            android.content.res.TypedArray r2 = r0.obtainAttributes(r2, r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r7 = 8
            boolean r11 = r2.hasValue(r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r11 != 0) goto L_0x0138
            r7 = 1
            goto L_0x013a
        L_0x0138:
        L_0x013a:
            r11 = 400(0x190, float:5.6E-43)
            int r18 = r2.getInt(r7, r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r7 = 6
            boolean r11 = r2.hasValue(r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r11 != 0) goto L_0x0149
            r7 = 2
            goto L_0x014b
        L_0x0149:
        L_0x014b:
            r11 = 0
            int r7 = r2.getInt(r7, r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r11 = 1
            if (r7 != r11) goto L_0x0156
            r19 = 1
            goto L_0x0159
        L_0x0156:
            r19 = 0
        L_0x0159:
            r7 = 9
            boolean r11 = r2.hasValue(r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r11 != 0) goto L_0x0162
            r7 = 3
        L_0x0162:
            r11 = 7
            boolean r12 = r2.hasValue(r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r12 != 0) goto L_0x016b
            r11 = 4
            goto L_0x016d
        L_0x016b:
        L_0x016d:
            java.lang.String r20 = r2.getString(r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r11 = 0
            int r21 = r2.getInt(r7, r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r7 = 5
            boolean r11 = r2.hasValue(r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r11 != 0) goto L_0x017f
            r11 = 0
            goto L_0x0181
        L_0x017f:
            r11 = 5
        L_0x0181:
            r12 = 0
            int r22 = r2.getResourceId(r11, r12)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.String r17 = r2.getString(r11)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2.recycle()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x018d:
            int r2 = r3.next()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 != r13) goto L_0x019f
            ij r2 = new ij     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r16 = r2
            r16.<init>(r17, r18, r19, r20, r21, r22)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r1.add(r2)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x010b
        L_0x019f:
            p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x018d
        L_0x01a3:
            r7 = 5
            r12 = 0
            p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x010b
        L_0x01aa:
            r7 = 5
            r12 = 0
            goto L_0x010b
        L_0x01ae:
            boolean r2 = r1.isEmpty()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            if (r2 != 0) goto L_0x01c6
            ii r2 = new ii     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            int r3 = r1.size()     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            ij[] r3 = new p000.C0232ij[r3]     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.Object[] r1 = r1.toArray(r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            ij[] r1 = (p000.C0232ij[]) r1     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2.<init>(r1)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            goto L_0x01ce
        L_0x01c6:
            r2 = 0
            goto L_0x01ce
        L_0x01c9:
            p000.cun.m5448a((org.xmlpull.v1.XmlPullParser) r3)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r2 = 0
        L_0x01ce:
            if (r2 != 0) goto L_0x01db
            java.lang.String r0 = "Failed to find font-family tag"
            android.util.Log.e(r9, r0)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r1 = -3
            r8.mo9035a((int) r1)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            r1 = 0
            return r1
        L_0x01db:
            r1 = r23
            r3 = r24
            r4 = r26
            r5 = r27
            r6 = r28
            r7 = r29
            android.graphics.Typeface r0 = p000.C0241is.m14373a(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            return r0
        L_0x01ec:
            r1 = 1
            if (r6 == r1) goto L_0x01f5
            r1 = r23
            r11 = -3
            r12 = 0
            goto L_0x0052
        L_0x01f5:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            java.lang.String r1 = "No start tag found"
            r0.<init>(r1)     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
            throw r0     // Catch:{ XmlPullParserException -> 0x0213, IOException -> 0x01fd }
        L_0x01fd:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to read xml resource "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r9, r1, r0)
            goto L_0x0228
        L_0x0213:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to parse xml resource "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r9, r1, r0)
        L_0x0228:
            r1 = -3
            r8.mo9035a((int) r1)
            r2 = 0
            return r2
        L_0x022f:
            r8.mo9036b(r1)
            return r1
        L_0x0233:
            r2 = r12
            r1 = -3
            r8.mo9035a((int) r1)
            return r2
        L_0x0239:
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Resource \""
            r3.append(r5)
            java.lang.String r0 = r0.getResourceName(r4)
            r3.append(r0)
            java.lang.String r0 = "\" ("
            r3.append(r0)
            java.lang.String r0 = java.lang.Integer.toHexString(r26)
            r3.append(r0)
            java.lang.String r0 = ") is not a Font: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            goto L_0x0269
        L_0x0268:
            throw r2
        L_0x0269:
            goto L_0x0268
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0071co.m4659a(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, io, boolean):android.graphics.Typeface");
    }

    /* renamed from: a */
    public static int m4652a(Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId != 0 ? i : i2;
    }

    /* renamed from: a */
    public static boolean m4666a(TypedArray typedArray, int i, int i2, boolean z) {
        return typedArray.getBoolean(i, typedArray.getBoolean(i2, z));
    }

    /* renamed from: d */
    public static int m4675d(TypedArray typedArray, int i, int i2) {
        return typedArray.getInt(i, typedArray.getInt(i2, Integer.MAX_VALUE));
    }

    /* renamed from: a */
    private static int m4655a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (m4667a(xmlPullParser, str)) {
            return typedArray.getColor(i, 0);
        }
        return 0;
    }

    /* JADX WARNING: type inference failed for: r1v7, types: [android.graphics.Shader] */
    /* JADX WARNING: type inference failed for: r18v3, types: [android.graphics.RadialGradient] */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r13v4, types: [android.graphics.LinearGradient] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        r7 = r0.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        r10 = r7.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        if (r10 == 89650992) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        if (r10 == 1191572447) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        if (r7.equals("selector") == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0065, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        if (r7.equals("gradient") == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006d, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006f, code lost:
        r10 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0070, code lost:
        if (r10 == 0) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0072, code lost:
        if (r10 != 1) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r7 = r0.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        if (r7.equals("gradient") == false) goto L_0x0202;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        r7 = m4657a(r4, r1, r6, p000.C0101dp.f6978d);
        r14 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "startX", 8, 0.0f);
        r15 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "startY", 9, 0.0f);
        r16 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "endX", 10, 0.0f);
        r17 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "endY", 11, 0.0f);
        r10 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "centerX", 3, 0.0f);
        r2 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "centerY", 4, 0.0f);
        r13 = m4656a(r7, (org.xmlpull.v1.XmlPullParser) r0, "type", 2, r5);
        r8 = m4655a(r7, (org.xmlpull.v1.XmlPullParser) r0, "startColor", r5);
        r18 = m4667a((org.xmlpull.v1.XmlPullParser) r0, "centerColor");
        r3 = m4655a(r7, (org.xmlpull.v1.XmlPullParser) r0, "centerColor", 7);
        r11 = m4655a(r7, (org.xmlpull.v1.XmlPullParser) r0, "endColor", 1);
        r9 = m4656a(r7, (org.xmlpull.v1.XmlPullParser) r0, "tileMode", 6, r5);
        r20 = r2;
        r21 = m4651a(r7, (org.xmlpull.v1.XmlPullParser) r0, "gradientRadius", 5, 0.0f);
        r7.recycle();
        r2 = r0.getDepth() + 1;
        r5 = new java.util.ArrayList(20);
        r12 = new java.util.ArrayList(20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f4, code lost:
        r7 = r0.next();
        r22 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00fb, code lost:
        if (r7 == 1) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00fd, code lost:
        r10 = r0.getDepth();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0101, code lost:
        if (r10 >= r2) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0103, code lost:
        r23 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0106, code lost:
        if (r7 == 3) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0109, code lost:
        r23 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010d, code lost:
        if (r7 != 2) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x010f, code lost:
        if (r10 > r2) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011b, code lost:
        if (r0.getName().equals("item") == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011d, code lost:
        r7 = m4657a(r4, r1, r6, p000.C0101dp.f6979e);
        r15 = r7.hasValue(0);
        r24 = r7.hasValue(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x012d, code lost:
        if (r15 == false) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012f, code lost:
        if (r24 == false) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0131, code lost:
        r15 = r7.getColor(0, 0);
        r25 = r7.getFloat(1, 0.0f);
        r7.recycle();
        r12.add(java.lang.Integer.valueOf(r15));
        r5.add(java.lang.Float.valueOf(r25));
        r10 = r22;
        r15 = r23;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0170, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r0.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0171, code lost:
        r24 = r2;
        r10 = r22;
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0179, code lost:
        r24 = r2;
        r10 = r22;
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0181, code lost:
        r23 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0187, code lost:
        if (r12.size() <= 0) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0189, code lost:
        r0 = new p000.C0234il((java.util.List) r12, (java.util.List) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x018f, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0190, code lost:
        if (r0 == null) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0193, code lost:
        if (r18 != false) goto L_0x019b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0195, code lost:
        r0 = new p000.C0234il(r8, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x019b, code lost:
        r0 = new p000.C0234il(r8, r3, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01a2, code lost:
        if (r13 == 1) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a5, code lost:
        if (r13 == 2) goto L_0x01bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a7, code lost:
        r13 = new android.graphics.LinearGradient(r14, r23, r16, r17, r0.f14426a, r0.f14427b, m4673c(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01bc, code lost:
        r1 = new android.graphics.SweepGradient(r22, r20, r0.f14426a, r0.f14427b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01cb, code lost:
        r4 = r20;
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01d2, code lost:
        if (r21 <= 0.0f) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01d4, code lost:
        r18 = new android.graphics.RadialGradient(r3, r4, r21, r0.f14426a, r0.f14427b, m4673c(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01f4, code lost:
        r2 = new p000.C0229ig(r1, (android.content.res.ColorStateList) null, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01f7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01f8, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0201, code lost:
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x021f, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r0.getPositionDescription() + ": invalid gradient color tag " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x023d, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r0.getPositionDescription() + ": unsupported complex color tag " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x023e, code lost:
        r0 = p000.cun.m5439a(r4, r0, r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0248, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r2 = new p000.C0229ig((android.graphics.Shader) null, r0, r0.getDefaultColor());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x025d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x026e A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.C0229ig m4661a(android.content.res.TypedArray r26, org.xmlpull.v1.XmlPullParser r27, android.content.res.Resources.Theme r28, java.lang.String r29, int r30) {
        /*
            r0 = r26
            r1 = r28
            r2 = r30
            java.lang.String r3 = "centerColor"
            r4 = r27
            r5 = r29
            boolean r4 = m4667a((org.xmlpull.v1.XmlPullParser) r4, (java.lang.String) r5)
            r5 = 0
            if (r4 == 0) goto L_0x026f
            android.util.TypedValue r4 = new android.util.TypedValue
            r4.<init>()
            r0.getValue(r2, r4)
            int r6 = r4.type
            r7 = 28
            if (r6 < r7) goto L_0x002f
            int r6 = r4.type
            r7 = 31
            if (r6 <= r7) goto L_0x0028
            goto L_0x002f
        L_0x0028:
            int r0 = r4.data
            ig r0 = p000.C0229ig.m12951a((int) r0)
            return r0
        L_0x002f:
            android.content.res.Resources r4 = r26.getResources()
            int r0 = r0.getResourceId(r2, r5)
            android.content.res.XmlResourceParser r0 = r4.getXml(r0)     // Catch:{ Exception -> 0x025f }
            android.util.AttributeSet r6 = android.util.Xml.asAttributeSet(r0)     // Catch:{ Exception -> 0x025f }
        L_0x003f:
            int r7 = r0.next()     // Catch:{ Exception -> 0x025f }
            r8 = 2
            r9 = 1
            if (r7 != r8) goto L_0x024e
            java.lang.String r7 = r0.getName()     // Catch:{ Exception -> 0x025f }
            int r10 = r7.hashCode()
            r11 = 89650992(0x557f730, float:1.01546526E-35)
            java.lang.String r12 = "gradient"
            if (r10 == r11) goto L_0x0067
            r11 = 1191572447(0x4705f3df, float:34291.87)
            if (r10 == r11) goto L_0x005c
        L_0x005b:
            goto L_0x006f
        L_0x005c:
            java.lang.String r10 = "selector"
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L_0x005b
            r10 = 0
            goto L_0x0070
        L_0x0067:
            boolean r10 = r7.equals(r12)
            if (r10 == 0) goto L_0x005b
            r10 = 1
            goto L_0x0070
        L_0x006f:
            r10 = -1
        L_0x0070:
            if (r10 == 0) goto L_0x023e
            if (r10 != r9) goto L_0x0220
            java.lang.String r7 = r0.getName()     // Catch:{ Exception -> 0x025f }
            boolean r10 = r7.equals(r12)     // Catch:{ Exception -> 0x025f }
            if (r10 == 0) goto L_0x0202
            int[] r7 = p000.C0101dp.f6978d     // Catch:{ Exception -> 0x025f }
            android.content.res.TypedArray r7 = m4657a((android.content.res.Resources) r4, (android.content.res.Resources.Theme) r1, (android.util.AttributeSet) r6, (int[]) r7)     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "startX"
            r11 = 8
            r12 = 0
            float r14 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r10, (int) r11, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "startY"
            r11 = 9
            float r15 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r10, (int) r11, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "endX"
            r11 = 10
            float r16 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r10, (int) r11, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "endY"
            r11 = 11
            float r17 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r10, (int) r11, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "centerX"
            r11 = 3
            float r10 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r10, (int) r11, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r13 = "centerY"
            r2 = 4
            float r2 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r13, (int) r2, (float) r12)     // Catch:{ Exception -> 0x025f }
            java.lang.String r13 = "type"
            int r13 = m4656a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r13, (int) r8, (int) r5)     // Catch:{ Exception -> 0x025f }
            java.lang.String r8 = "startColor"
            int r8 = m4655a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r8, (int) r5)     // Catch:{ Exception -> 0x025f }
            boolean r18 = m4667a((org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r3)     // Catch:{ Exception -> 0x025f }
            r11 = 7
            int r3 = m4655a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r3, (int) r11)     // Catch:{ Exception -> 0x025f }
            java.lang.String r11 = "endColor"
            int r11 = m4655a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r11, (int) r9)     // Catch:{ Exception -> 0x025f }
            java.lang.String r9 = "tileMode"
            r12 = 6
            int r9 = m4656a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r9, (int) r12, (int) r5)     // Catch:{ Exception -> 0x025f }
            java.lang.String r12 = "gradientRadius"
            r5 = 5
            r20 = r2
            r2 = 0
            float r21 = m4651a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r0, (java.lang.String) r12, (int) r5, (float) r2)     // Catch:{ Exception -> 0x025f }
            r7.recycle()     // Catch:{ Exception -> 0x025f }
            int r2 = r0.getDepth()     // Catch:{ Exception -> 0x025f }
            r5 = 1
            int r2 = r2 + r5
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x025f }
            r7 = 20
            r5.<init>(r7)     // Catch:{ Exception -> 0x025f }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ Exception -> 0x025f }
            r12.<init>(r7)     // Catch:{ Exception -> 0x025f }
        L_0x00f4:
            int r7 = r0.next()     // Catch:{ Exception -> 0x025f }
            r22 = r10
            r10 = 1
            if (r7 == r10) goto L_0x0181
            int r10 = r0.getDepth()     // Catch:{ Exception -> 0x025f }
            if (r10 >= r2) goto L_0x0109
            r23 = r15
            r15 = 3
            if (r7 == r15) goto L_0x0183
            goto L_0x010c
        L_0x0109:
            r23 = r15
            r15 = 3
        L_0x010c:
            r15 = 2
            if (r7 != r15) goto L_0x0179
            if (r10 > r2) goto L_0x0179
            java.lang.String r7 = r0.getName()     // Catch:{ Exception -> 0x025f }
            java.lang.String r10 = "item"
            boolean r7 = r7.equals(r10)     // Catch:{ Exception -> 0x025f }
            if (r7 == 0) goto L_0x0171
            int[] r7 = p000.C0101dp.f6979e     // Catch:{ Exception -> 0x025f }
            android.content.res.TypedArray r7 = m4657a((android.content.res.Resources) r4, (android.content.res.Resources.Theme) r1, (android.util.AttributeSet) r6, (int[]) r7)     // Catch:{ Exception -> 0x025f }
            r10 = 0
            boolean r15 = r7.hasValue(r10)     // Catch:{ Exception -> 0x025f }
            r10 = 1
            boolean r24 = r7.hasValue(r10)     // Catch:{ Exception -> 0x025f }
            if (r15 == 0) goto L_0x0156
            if (r24 == 0) goto L_0x0156
            r10 = 0
            int r15 = r7.getColor(r10, r10)     // Catch:{ Exception -> 0x025f }
            r24 = r2
            r2 = 1
            r10 = 0
            float r25 = r7.getFloat(r2, r10)     // Catch:{ Exception -> 0x025f }
            r7.recycle()     // Catch:{ Exception -> 0x025f }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r15)     // Catch:{ Exception -> 0x025f }
            r12.add(r2)     // Catch:{ Exception -> 0x025f }
            java.lang.Float r2 = java.lang.Float.valueOf(r25)     // Catch:{ Exception -> 0x025f }
            r5.add(r2)     // Catch:{ Exception -> 0x025f }
            r10 = r22
            r15 = r23
            r2 = r24
            goto L_0x00f4
        L_0x0156:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x025f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r2.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r0.getPositionDescription()     // Catch:{ Exception -> 0x025f }
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x025f }
            r1.<init>(r0)     // Catch:{ Exception -> 0x025f }
            throw r1     // Catch:{ Exception -> 0x025f }
        L_0x0171:
            r24 = r2
            r10 = r22
            r15 = r23
            goto L_0x00f4
        L_0x0179:
            r24 = r2
            r10 = r22
            r15 = r23
            goto L_0x00f4
        L_0x0181:
            r23 = r15
        L_0x0183:
            int r0 = r12.size()     // Catch:{ Exception -> 0x025f }
            if (r0 <= 0) goto L_0x018f
            il r0 = new il     // Catch:{ Exception -> 0x025f }
            r0.<init>((java.util.List) r12, (java.util.List) r5)     // Catch:{ Exception -> 0x025f }
            goto L_0x0190
        L_0x018f:
            r0 = 0
        L_0x0190:
            if (r0 == 0) goto L_0x0193
        L_0x0192:
            goto L_0x01a1
        L_0x0193:
            if (r18 != 0) goto L_0x019b
            il r0 = new il     // Catch:{ Exception -> 0x025f }
            r0.<init>((int) r8, (int) r11)     // Catch:{ Exception -> 0x025f }
            goto L_0x0192
        L_0x019b:
            il r0 = new il     // Catch:{ Exception -> 0x025f }
            r0.<init>(r8, r3, r11)     // Catch:{ Exception -> 0x025f }
            goto L_0x0192
        L_0x01a1:
            r1 = 1
            if (r13 == r1) goto L_0x01cb
            r1 = 2
            if (r13 == r1) goto L_0x01bc
            android.graphics.LinearGradient r1 = new android.graphics.LinearGradient     // Catch:{ Exception -> 0x025f }
            int[] r2 = r0.f14426a     // Catch:{ Exception -> 0x025f }
            float[] r0 = r0.f14427b     // Catch:{ Exception -> 0x025f }
            android.graphics.Shader$TileMode r20 = m4673c((int) r9)     // Catch:{ Exception -> 0x025f }
            r13 = r1
            r15 = r23
            r18 = r2
            r19 = r0
            r13.<init>(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x025f }
            goto L_0x01ed
        L_0x01bc:
            android.graphics.SweepGradient r1 = new android.graphics.SweepGradient     // Catch:{ Exception -> 0x025f }
            int[] r2 = r0.f14426a     // Catch:{ Exception -> 0x025f }
            float[] r0 = r0.f14427b     // Catch:{ Exception -> 0x025f }
            r4 = r20
            r3 = r22
            r1.<init>(r3, r4, r2, r0)     // Catch:{ Exception -> 0x025f }
            goto L_0x01ed
        L_0x01cb:
            r4 = r20
            r3 = r22
            r1 = 0
            int r1 = (r21 > r1 ? 1 : (r21 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x01fa
            android.graphics.RadialGradient r1 = new android.graphics.RadialGradient     // Catch:{ Exception -> 0x025f }
            int[] r2 = r0.f14426a     // Catch:{ Exception -> 0x025f }
            float[] r0 = r0.f14427b     // Catch:{ Exception -> 0x025f }
            android.graphics.Shader$TileMode r24 = m4673c((int) r9)     // Catch:{ Exception -> 0x025f }
            r18 = r1
            r19 = r3
            r20 = r4
            r22 = r2
            r23 = r0
            r18.<init>(r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x025f }
        L_0x01ed:
            ig r0 = new ig     // Catch:{ Exception -> 0x025f }
            r2 = 0
            r3 = 0
            r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x01f7 }
            r2 = r0
            goto L_0x026a
        L_0x01f7:
            r0 = move-exception
            r5 = r2
            goto L_0x0261
        L_0x01fa:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x025f }
            java.lang.String r1 = "<gradient> tag requires 'gradientRadius' attribute with radial type"
            r0.<init>(r1)     // Catch:{ Exception -> 0x025f }
            throw r0     // Catch:{ Exception -> 0x025f }
        L_0x0202:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x025f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r2.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r0.getPositionDescription()     // Catch:{ Exception -> 0x025f }
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = ": invalid gradient color tag "
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            r2.append(r7)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x025f }
            r1.<init>(r0)     // Catch:{ Exception -> 0x025f }
            throw r1     // Catch:{ Exception -> 0x025f }
        L_0x0220:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x025f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r2.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r0.getPositionDescription()     // Catch:{ Exception -> 0x025f }
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = ": unsupported complex color tag "
            r2.append(r0)     // Catch:{ Exception -> 0x025f }
            r2.append(r7)     // Catch:{ Exception -> 0x025f }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x025f }
            r1.<init>(r0)     // Catch:{ Exception -> 0x025f }
            throw r1     // Catch:{ Exception -> 0x025f }
        L_0x023e:
            android.content.res.ColorStateList r0 = p000.cun.m5439a(r4, r0, r6, r1)     // Catch:{ Exception -> 0x025f }
            ig r1 = new ig     // Catch:{ Exception -> 0x025f }
            int r2 = r0.getDefaultColor()     // Catch:{ Exception -> 0x025f }
            r5 = 0
            r1.<init>(r5, r0, r2)     // Catch:{ Exception -> 0x025d }
            r2 = r1
            goto L_0x026a
        L_0x024e:
            r5 = 0
            r2 = 1
            if (r7 == r2) goto L_0x0255
            r5 = 0
            goto L_0x003f
        L_0x0255:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x025d }
            java.lang.String r1 = "No start tag found"
            r0.<init>(r1)     // Catch:{ Exception -> 0x025d }
            throw r0     // Catch:{ Exception -> 0x025d }
        L_0x025d:
            r0 = move-exception
            goto L_0x0261
        L_0x025f:
            r0 = move-exception
            r5 = 0
        L_0x0261:
            java.lang.String r1 = "ComplexColorCompat"
            java.lang.String r2 = "Failed to inflate ComplexColor."
            android.util.Log.e(r1, r2, r0)
            r2 = r5
        L_0x026a:
            if (r2 != 0) goto L_0x026e
            goto L_0x026f
        L_0x026e:
            return r2
        L_0x026f:
            r1 = 0
            ig r0 = p000.C0229ig.m12951a((int) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0071co.m4661a(android.content.res.TypedArray, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme, java.lang.String, int):ig");
    }

    /* renamed from: a */
    public static float m4651a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        return m4667a(xmlPullParser, str) ? typedArray.getFloat(i, f) : f;
    }

    /* renamed from: a */
    public static int m4656a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return m4667a(xmlPullParser, str) ? typedArray.getInt(i, i2) : i2;
    }

    /* renamed from: a */
    public static int m4654a(TypedArray typedArray, int i, int i2, int i3) {
        return typedArray.getResourceId(i, typedArray.getResourceId(i2, i3));
    }

    /* renamed from: a */
    public static String m4663a(TypedArray typedArray, int i, int i2) {
        String string = typedArray.getString(i);
        return string == null ? typedArray.getString(i2) : string;
    }

    /* renamed from: b */
    public static CharSequence m4670b(TypedArray typedArray, int i, int i2) {
        CharSequence text = typedArray.getText(i);
        return text == null ? typedArray.getText(i2) : text;
    }

    /* renamed from: c */
    public static CharSequence[] m4674c(TypedArray typedArray, int i, int i2) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        return textArray == null ? typedArray.getTextArray(i2) : textArray;
    }

    /* renamed from: a */
    public static boolean m4667a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    /* renamed from: a */
    public static TypedArray m4657a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme != null) {
            return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        }
        return resources.obtainAttributes(attributeSet, iArr);
    }
}

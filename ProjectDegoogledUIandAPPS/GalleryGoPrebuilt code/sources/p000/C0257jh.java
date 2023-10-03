package p000;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.apps.photosgo.R;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: jh */
/* compiled from: PG */
public final class C0257jh {
    public C0257jh() {
        new HashMap();
    }

    /* renamed from: a */
    public static int m14468a(int i, int i2, int i3) {
        return i >= i2 ? i > i3 ? i3 : i : i2;
    }

    /* renamed from: a */
    public static Object m14471a(Class cls, String str) {
        String str2;
        String name = cls.getPackage().getName();
        String canonicalName = cls.getCanonicalName();
        if (!name.isEmpty()) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        String str3 = canonicalName.replace('.', '_') + str;
        try {
            if (!name.isEmpty()) {
                str2 = name + "." + str3;
            } else {
                str2 = str3;
            }
            return Class.forName(str2).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find implementation for " + cls.getCanonicalName() + ". " + str3 + " does not exist");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot access the constructor" + cls.getCanonicalName());
        } catch (InstantiationException e3) {
            throw new RuntimeException("Failed to create an instance of " + cls.getCanonicalName());
        }
    }

    /* renamed from: a */
    public static C0154fo m14469a(Context context, C0156fq fqVar, C0147fh fhVar, boolean z) {
        int i;
        C0145ff ffVar = fhVar.f9576O;
        int i2 = ffVar != null ? ffVar.f9427e : 0;
        int D = fhVar.mo5623D();
        fhVar.mo5642b(0);
        View a = fqVar.mo5559a(fhVar.f9563B);
        if (!(a == null || a.getTag(R.id.visible_removing_fragment_view_tag) == null)) {
            a.setTag(R.id.visible_removing_fragment_view_tag, (Object) null);
        }
        ViewGroup viewGroup = fhVar.f9572K;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        if (D != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(D));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, D);
                    if (loadAnimation != null) {
                        return new C0154fo(loadAnimation);
                    }
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                }
            }
            try {
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, D);
                if (loadAnimator != null) {
                    return new C0154fo(loadAnimator);
                }
            } catch (RuntimeException e3) {
                if (!equals) {
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(context, D);
                    if (loadAnimation2 != null) {
                        return new C0154fo(loadAnimation2);
                    }
                } else {
                    throw e3;
                }
            }
        }
        if (i2 != 0) {
            if (i2 != 4097) {
                i = i2 != 4099 ? i2 != 8194 ? -1 : !z ? R.anim.fragment_close_exit : R.anim.fragment_close_enter : !z ? R.anim.fragment_fade_exit : R.anim.fragment_fade_enter;
            } else {
                i = !z ? R.anim.fragment_open_exit : R.anim.fragment_open_enter;
            }
            if (i >= 0) {
                return new C0154fo(AnimationUtils.loadAnimation(context, i));
            }
        }
        return null;
    }

    /* renamed from: a */
    private static void m14479a(ArrayList arrayList, char c, float[] fArr) {
        arrayList.add(new C0240ir(c, fArr));
    }

    /* renamed from: a */
    public static float[] m14483a(float[] fArr, int i) {
        if (i >= 0) {
            int min = Math.min(i, fArr.length);
            float[] fArr2 = new float[i];
            System.arraycopy(fArr, 0, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab A[Catch:{ NumberFormatException -> 0x00d5 }, LOOP:3: B:22:0x0073->B:40:0x00ab, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b2 A[Catch:{ NumberFormatException -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b3 A[Catch:{ NumberFormatException -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c7 A[Catch:{ NumberFormatException -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cb A[Catch:{ NumberFormatException -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00b0 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.C0240ir[] m14484a(java.lang.String r17) {
        /*
            r0 = r17
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 0
            r4 = 1
            r5 = 0
        L_0x000a:
            int r6 = r17.length()
            if (r4 >= r6) goto L_0x0105
        L_0x0010:
            int r6 = r17.length()
            r7 = 69
            r8 = 101(0x65, float:1.42E-43)
            if (r4 >= r6) goto L_0x0037
            char r6 = r0.charAt(r4)
            int r9 = r6 + -65
            int r10 = r6 + -90
            int r9 = r9 * r10
            if (r9 <= 0) goto L_0x002f
            int r9 = r6 + -97
            int r10 = r6 + -122
            int r9 = r9 * r10
            if (r9 <= 0) goto L_0x002f
            goto L_0x0034
        L_0x002f:
            if (r6 == r8) goto L_0x0034
            if (r6 == r7) goto L_0x0034
            goto L_0x0037
        L_0x0034:
            int r4 = r4 + 1
            goto L_0x0010
        L_0x0037:
            java.lang.String r5 = r0.substring(r5, r4)
            java.lang.String r5 = r5.trim()
            int r6 = r5.length()
            if (r6 > 0) goto L_0x0047
            goto L_0x00fd
        L_0x0047:
            char r6 = r5.charAt(r3)
            r9 = 122(0x7a, float:1.71E-43)
            if (r6 == r9) goto L_0x00f2
            char r6 = r5.charAt(r3)
            r9 = 90
            if (r6 != r9) goto L_0x0059
            goto L_0x00f2
        L_0x0059:
            int r6 = r5.length()     // Catch:{ NumberFormatException -> 0x00d5 }
            float[] r6 = new float[r6]     // Catch:{ NumberFormatException -> 0x00d5 }
            iq r9 = new iq     // Catch:{ NumberFormatException -> 0x00d5 }
            r9.<init>()     // Catch:{ NumberFormatException -> 0x00d5 }
            int r10 = r5.length()     // Catch:{ NumberFormatException -> 0x00d5 }
            r11 = 1
            r12 = 0
        L_0x006a:
            if (r11 >= r10) goto L_0x00ce
            r9.f14639a = r3     // Catch:{ NumberFormatException -> 0x00d5 }
            r13 = r11
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0073:
            int r3 = r5.length()     // Catch:{ NumberFormatException -> 0x00d5 }
            if (r13 >= r3) goto L_0x00b0
            char r3 = r5.charAt(r13)     // Catch:{ NumberFormatException -> 0x00d5 }
            r2 = 32
            if (r3 == r2) goto L_0x00a6
            if (r3 == r7) goto L_0x00a4
            if (r3 == r8) goto L_0x00a4
            switch(r3) {
                case 44: goto L_0x00a6;
                case 45: goto L_0x0097;
                case 46: goto L_0x008b;
                default: goto L_0x0088;
            }     // Catch:{ NumberFormatException -> 0x00d5 }
        L_0x0088:
        L_0x0089:
            r15 = 0
            goto L_0x00a9
        L_0x008b:
            if (r14 != 0) goto L_0x008f
            r14 = 1
            goto L_0x0089
        L_0x008f:
            r2 = 1
            r9.f14639a = r2     // Catch:{ NumberFormatException -> 0x00d5 }
            r15 = 0
            r16 = 1
            goto L_0x00a9
        L_0x0097:
            if (r13 == r11) goto L_0x0088
            if (r15 != 0) goto L_0x00a3
            r2 = 1
            r9.f14639a = r2     // Catch:{ NumberFormatException -> 0x00d5 }
            r15 = 0
            r16 = 1
            goto L_0x00a9
        L_0x00a3:
            goto L_0x0088
        L_0x00a4:
            r15 = 1
            goto L_0x00a9
        L_0x00a6:
            r15 = 0
            r16 = 1
        L_0x00a9:
            if (r16 != 0) goto L_0x00b0
            int r13 = r13 + 1
            r3 = 0
            goto L_0x0073
        L_0x00b0:
            if (r11 < r13) goto L_0x00b3
            goto L_0x00c3
        L_0x00b3:
            int r2 = r12 + 1
            java.lang.String r3 = r5.substring(r11, r13)     // Catch:{ NumberFormatException -> 0x00d5 }
            float r3 = java.lang.Float.parseFloat(r3)     // Catch:{ NumberFormatException -> 0x00d5 }
            r6[r12] = r3     // Catch:{ NumberFormatException -> 0x00d5 }
            r12 = r2
        L_0x00c3:
            boolean r2 = r9.f14639a     // Catch:{ NumberFormatException -> 0x00d5 }
            if (r2 != 0) goto L_0x00cb
            int r11 = r13 + 1
            r3 = 0
            goto L_0x006a
        L_0x00cb:
            r11 = r13
            r3 = 0
            goto L_0x006a
        L_0x00ce:
            float[] r2 = m14483a((float[]) r6, (int) r12)     // Catch:{ NumberFormatException -> 0x00d5 }
            r3 = r2
            r2 = 0
            goto L_0x00f5
        L_0x00d5:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "error in parsing \""
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = "\""
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x00f2:
            r2 = 0
            float[] r3 = new float[r2]
        L_0x00f5:
            char r5 = r5.charAt(r2)
            m14479a((java.util.ArrayList) r1, (char) r5, (float[]) r3)
        L_0x00fd:
            int r2 = r4 + 1
            r5 = r4
            r3 = 0
            r4 = r2
            goto L_0x000a
        L_0x0105:
            int r4 = r4 - r5
            r2 = 1
            if (r4 != r2) goto L_0x0119
            int r2 = r17.length()
            if (r5 >= r2) goto L_0x0119
            char r0 = r0.charAt(r5)
            r2 = 0
            float[] r2 = new float[r2]
            m14479a((java.util.ArrayList) r1, (char) r0, (float[]) r2)
        L_0x0119:
            int r0 = r1.size()
            ir[] r0 = new p000.C0240ir[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            ir[] r0 = (p000.C0240ir[]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0257jh.m14484a(java.lang.String):ir[]");
    }

    /* renamed from: a */
    public static C0240ir[] m14485a(C0240ir[] irVarArr) {
        if (irVarArr == null) {
            return null;
        }
        C0240ir[] irVarArr2 = new C0240ir[irVarArr.length];
        for (int i = 0; i < irVarArr.length; i++) {
            irVarArr2[i] = new C0240ir(irVarArr[i]);
        }
        return irVarArr2;
    }

    /* renamed from: a */
    public static void m14478a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m14481a(File file, Resources resources, int i) {
        InputStream inputStream;
        try {
            inputStream = resources.openRawResource(i);
            try {
                boolean a = m14482a(file, inputStream);
                m14478a((Closeable) inputStream);
                return a;
            } catch (Throwable th) {
                th = th;
                m14478a((Closeable) inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            m14478a((Closeable) inputStream);
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m14482a(File file, InputStream inputStream) {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        m14478a((Closeable) fileOutputStream2);
                        StrictMode.setThreadPolicy(allowThreadDiskWrites);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    m14478a((Closeable) fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    m14478a((Closeable) fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                m14478a((Closeable) fileOutputStream);
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            m14478a((Closeable) fileOutputStream);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th3) {
            th = th3;
            m14478a((Closeable) fileOutputStream);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    /* renamed from: a */
    public static File m14470a(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
            int i = 0;
            while (i < 100) {
                File file = new File(cacheDir, str + i);
                try {
                    if (file.createNewFile()) {
                        return file;
                    }
                    i++;
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m14480a(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }

    /* renamed from: a */
    public static void m14477a(Drawable drawable, boolean z) {
        int i = Build.VERSION.SDK_INT;
        drawable.setAutoMirrored(z);
    }

    /* renamed from: a */
    public static void m14472a(Drawable drawable, float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        drawable.setHotspot(f, f2);
    }

    /* renamed from: a */
    public static void m14474a(Drawable drawable, int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        drawable.setHotspotBounds(i, i2, i3, i4);
    }

    /* renamed from: b */
    public static void m14486b(Drawable drawable, int i) {
        int i2 = Build.VERSION.SDK_INT;
        drawable.setLayoutDirection(i);
    }

    /* renamed from: a */
    public static void m14473a(Drawable drawable, int i) {
        int i2 = Build.VERSION.SDK_INT;
        drawable.setTint(i);
    }

    /* renamed from: a */
    public static void m14475a(Drawable drawable, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintList(colorStateList);
    }

    /* renamed from: a */
    public static void m14476a(Drawable drawable, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintMode(mode);
    }
}

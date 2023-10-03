package p000;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ek */
/* compiled from: PG */
public final class C0123ek {

    /* renamed from: a */
    public static final Set f8461a = new HashSet();

    /* renamed from: b */
    public static final boolean f8462b;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0037, code lost:
        if (r3 > 0) goto L_0x003e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    static {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            f8461a = r0
            java.lang.String r0 = "java.vm.version"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x003d
            java.lang.String r3 = "(\\d+)\\.(\\d+)(\\.\\d+)?"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)
            java.util.regex.Matcher r3 = r3.matcher(r0)
            boolean r4 = r3.matches()
            if (r4 == 0) goto L_0x003d
            java.lang.String r4 = r3.group(r1)     // Catch:{ NumberFormatException -> 0x003a }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x003a }
            r5 = 2
            java.lang.String r3 = r3.group(r5)     // Catch:{ NumberFormatException -> 0x003a }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x003a }
            if (r4 <= r5) goto L_0x0035
        L_0x0034:
            goto L_0x003e
        L_0x0035:
            if (r4 != r5) goto L_0x003d
            if (r3 > 0) goto L_0x0034
            goto L_0x003d
        L_0x003a:
            r1 = move-exception
            r1 = 0
            goto L_0x003e
        L_0x003d:
            r1 = 0
        L_0x003e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "VM with version "
            r2.append(r3)
            r2.append(r0)
            if (r1 != 0) goto L_0x0050
            java.lang.String r0 = " does not have multidex support"
            goto L_0x0052
        L_0x0050:
            java.lang.String r0 = " has multidex support"
        L_0x0052:
            r2.append(r0)
            r2.toString()
            f8462b = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0123ek.<clinit>():void");
    }

    /* renamed from: a */
    public static Field m7653a(Object obj, String str) {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    /* renamed from: a */
    public static void m7654a(File file) {
        file.mkdir();
        if (!file.isDirectory()) {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". Parent file is null.");
            } else {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
            }
            throw new IOException("Failed to create directory " + file.getPath());
        }
    }
}

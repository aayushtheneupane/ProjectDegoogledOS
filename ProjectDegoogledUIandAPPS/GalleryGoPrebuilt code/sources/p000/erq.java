package p000;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* renamed from: erq */
/* compiled from: PG */
public final class erq {

    /* renamed from: a */
    public static Boolean f8874a;

    /* renamed from: b */
    public static eru f8875b;

    /* renamed from: c */
    public static int f8876c = -1;

    /* renamed from: d */
    public static final ThreadLocal f8877d = new ThreadLocal();

    /* renamed from: e */
    public static final ern f8878e = new erh();

    /* renamed from: f */
    public static final erp f8879f = new eri();

    /* renamed from: h */
    private static ers f8880h;

    /* renamed from: i */
    private static String f8881i;

    /* renamed from: g */
    public final Context f8882g;

    public erq(Context context) {
        this.f8882g = (Context) abj.m85a((Object) context);
    }

    /* renamed from: a */
    private static void m8064a(ClassLoader classLoader) {
        eru eru;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (!(queryLocalInterface instanceof eru)) {
                    eru = new ert(iBinder);
                } else {
                    eru = (eru) queryLocalInterface;
                }
            } else {
                eru = null;
            }
            f8875b = eru;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new erm("Failed to instantiate dynamite loader", e);
        }
    }

    /* renamed from: a */
    public static ers m8063a(Context context) {
        ers ers;
        synchronized (erq.class) {
            if (f8880h != null) {
                ers ers2 = f8880h;
                return ers2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    ers = !(queryLocalInterface instanceof ers) ? new err(iBinder) : (ers) queryLocalInterface;
                } else {
                    ers = null;
                }
                if (ers != null) {
                    f8880h = ers;
                    return ers;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() == 0 ? new String("Failed to load IDynamiteLoader from GmsCore: ") : "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf));
            }
        }
        return null;
    }

    /* renamed from: a */
    public static int m8061a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(str.length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".");
            sb.append("ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + str.length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException e) {
            StringBuilder sb3 = new StringBuilder(str.length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() == 0 ? new String("Failed to load module descriptor class: ") : "Failed to load module descriptor class: ".concat(valueOf2));
            return 0;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:47:0x00a0=Splitter:B:47:0x00a0, B:32:0x0079=Splitter:B:32:0x0079} */
    /* renamed from: a */
    public static int m8062a(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<erq> r0 = p000.erq.class
            monitor-enter(r0)     // Catch:{ all -> 0x0151 }
            java.lang.Boolean r1 = f8874a     // Catch:{ all -> 0x014e }
            if (r1 != 0) goto L_0x00d4
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            java.lang.String r2 = "sClassLoader"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            java.lang.Class r2 = r1.getDeclaringClass()     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            monitor-enter(r2)     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
            r3 = 0
            java.lang.Object r4 = r1.get(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x00a4 }
            if (r4 != 0) goto L_0x0092
            java.lang.String r4 = "com.google.android.gms"
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ all -> 0x00a4 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x00a4 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00a4 }
            if (r4 != 0) goto L_0x0087
            int r4 = m8065b(r8, r9, r10)     // Catch:{ erm -> 0x007c }
            java.lang.String r5 = f8881i     // Catch:{ erm -> 0x007c }
            if (r5 == 0) goto L_0x0079
            java.lang.String r5 = f8881i     // Catch:{ erm -> 0x007c }
            boolean r5 = r5.isEmpty()     // Catch:{ erm -> 0x007c }
            if (r5 == 0) goto L_0x004e
            goto L_0x0079
        L_0x004e:
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ erm -> 0x007c }
            r6 = 29
            if (r5 >= r6) goto L_0x0060
            erj r5 = new erj     // Catch:{ erm -> 0x007c }
            java.lang.String r6 = f8881i     // Catch:{ erm -> 0x007c }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ erm -> 0x007c }
            r5.<init>(r6, r7)     // Catch:{ erm -> 0x007c }
        L_0x005f:
            goto L_0x006c
        L_0x0060:
            dalvik.system.DelegateLastClassLoader r5 = new dalvik.system.DelegateLastClassLoader     // Catch:{ erm -> 0x007c }
            java.lang.String r6 = f8881i     // Catch:{ erm -> 0x007c }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ erm -> 0x007c }
            r5.<init>(r6, r7)     // Catch:{ erm -> 0x007c }
            goto L_0x005f
        L_0x006c:
            m8064a((java.lang.ClassLoader) r5)     // Catch:{ erm -> 0x007c }
            r1.set(r3, r5)     // Catch:{ erm -> 0x007c }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ erm -> 0x007c }
            f8874a = r5     // Catch:{ erm -> 0x007c }
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            monitor-exit(r0)     // Catch:{ all -> 0x014e }
            return r4
        L_0x0079:
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            monitor-exit(r0)     // Catch:{ all -> 0x014e }
            return r4
        L_0x007c:
            r4 = move-exception
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a4 }
            r1.set(r3, r4)     // Catch:{ all -> 0x00a4 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a4 }
            goto L_0x00a2
        L_0x0087:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a4 }
            r1.set(r3, r4)     // Catch:{ all -> 0x00a4 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a4 }
            goto L_0x00a2
        L_0x0092:
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a4 }
            if (r4 != r1) goto L_0x009b
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a4 }
            goto L_0x00a2
        L_0x009b:
            m8064a((java.lang.ClassLoader) r4)     // Catch:{ erm -> 0x009f }
            goto L_0x00a0
        L_0x009f:
            r1 = move-exception
        L_0x00a0:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00a4 }
        L_0x00a2:
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            goto L_0x00d2
        L_0x00a4:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            throw r1     // Catch:{ ClassNotFoundException -> 0x00ab, IllegalAccessException -> 0x00a9, NoSuchFieldException -> 0x00a7 }
        L_0x00a7:
            r1 = move-exception
            goto L_0x00ac
        L_0x00a9:
            r1 = move-exception
            goto L_0x00ac
        L_0x00ab:
            r1 = move-exception
        L_0x00ac:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x014e }
            int r3 = r3.length()     // Catch:{ all -> 0x014e }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x014e }
            r4.<init>(r3)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "Failed to load module via V2: "
            r4.append(r3)     // Catch:{ all -> 0x014e }
            r4.append(r1)     // Catch:{ all -> 0x014e }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x014e }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x014e }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x014e }
        L_0x00d2:
            f8874a = r1     // Catch:{ all -> 0x014e }
        L_0x00d4:
            monitor-exit(r0)     // Catch:{ all -> 0x014e }
            boolean r0 = r1.booleanValue()     // Catch:{ all -> 0x0151 }
            r1 = 0
            if (r0 == 0) goto L_0x0102
            int r8 = m8065b(r8, r9, r10)     // Catch:{ erm -> 0x00e1 }
            return r8
        L_0x00e1:
            r9 = move-exception
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0151 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0151 }
            int r2 = r9.length()     // Catch:{ all -> 0x0151 }
            if (r2 != 0) goto L_0x00fa
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x0151 }
            r9.<init>(r0)     // Catch:{ all -> 0x0151 }
            goto L_0x00fe
        L_0x00fa:
            java.lang.String r9 = r0.concat(r9)     // Catch:{ all -> 0x0151 }
        L_0x00fe:
            android.util.Log.w(r10, r9)     // Catch:{ all -> 0x0151 }
            return r1
        L_0x0102:
            ers r0 = m8063a((android.content.Context) r8)     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x014b
            int r2 = r0.mo5184b()     // Catch:{ RemoteException -> 0x0129 }
            r3 = 2
            if (r2 >= r3) goto L_0x011f
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r3 = "IDynamite loader version < 2, falling back to getModuleVersion2"
            android.util.Log.w(r2, r3)     // Catch:{ RemoteException -> 0x0129 }
            erf r2 = p000.erg.m8051a((java.lang.Object) r8)     // Catch:{ RemoteException -> 0x0129 }
            int r1 = r0.mo5182a((p000.erf) r2, (java.lang.String) r9, (boolean) r10)     // Catch:{ RemoteException -> 0x0129 }
            goto L_0x014c
        L_0x011f:
            erf r2 = p000.erg.m8051a((java.lang.Object) r8)     // Catch:{ RemoteException -> 0x0129 }
            int r1 = r0.mo5185b((p000.erf) r2, (java.lang.String) r9, (boolean) r10)     // Catch:{ RemoteException -> 0x0129 }
            goto L_0x014d
        L_0x0129:
            r9 = move-exception
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0151 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0151 }
            int r2 = r9.length()     // Catch:{ all -> 0x0151 }
            if (r2 != 0) goto L_0x0142
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x0151 }
            r9.<init>(r0)     // Catch:{ all -> 0x0151 }
            goto L_0x0146
        L_0x0142:
            java.lang.String r9 = r0.concat(r9)     // Catch:{ all -> 0x0151 }
        L_0x0146:
            android.util.Log.w(r10, r9)     // Catch:{ all -> 0x0151 }
            goto L_0x014d
        L_0x014b:
        L_0x014c:
        L_0x014d:
            return r1
        L_0x014e:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x014e }
            throw r9     // Catch:{ all -> 0x0151 }
        L_0x0151:
            r9 = move-exception
            p000.abj.m89a((android.content.Context) r8, (java.lang.Throwable) r9)
            goto L_0x0157
        L_0x0156:
            throw r9
        L_0x0157:
            goto L_0x0156
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.erq.m8062a(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00af  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m8065b(android.content.Context r7, java.lang.String r8, boolean r9) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            if (r9 != 0) goto L_0x000a
            java.lang.String r7 = "api"
            goto L_0x000d
        L_0x000a:
            java.lang.String r7 = "api_force_staging"
        L_0x000d:
            int r9 = r7.length()     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            int r9 = r9 + 42
            int r2 = r8.length()     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            int r9 = r9 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            java.lang.String r9 = "content://com.google.android.gms.chimera/"
            r2.append(r9)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r2.append(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            java.lang.String r7 = "/"
            r2.append(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r2.append(r8)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            java.lang.String r7 = r2.toString()     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            android.net.Uri r2 = android.net.Uri.parse(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            if (r7 == 0) goto L_0x0080
            boolean r8 = r7.moveToFirst()     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            if (r8 == 0) goto L_0x0080
            r8 = 0
            int r8 = r7.getInt(r8)     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            if (r8 <= 0) goto L_0x0079
            java.lang.Class<erq> r9 = p000.erq.class
            monitor-enter(r9)     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            r1 = 2
            java.lang.String r1 = r7.getString(r1)     // Catch:{ all -> 0x0076 }
            f8881i = r1     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = "loaderVersion"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ all -> 0x0076 }
            if (r1 < 0) goto L_0x0064
            int r1 = r7.getInt(r1)     // Catch:{ all -> 0x0076 }
            f8876c = r1     // Catch:{ all -> 0x0076 }
        L_0x0064:
            monitor-exit(r9)     // Catch:{ all -> 0x0076 }
            java.lang.ThreadLocal r9 = f8877d     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            java.lang.Object r9 = r9.get()     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            erk r9 = (p000.erk) r9     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            if (r9 == 0) goto L_0x0079
            android.database.Cursor r1 = r9.f8869a     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            if (r1 != 0) goto L_0x0079
            r9.f8869a = r7     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            goto L_0x007a
        L_0x0076:
            r8 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0076 }
            throw r8     // Catch:{ Exception -> 0x0093, all -> 0x008f }
        L_0x0079:
            r0 = r7
        L_0x007a:
            if (r0 == 0) goto L_0x007f
            r0.close()
        L_0x007f:
            return r8
        L_0x0080:
            java.lang.String r8 = "DynamiteModule"
            java.lang.String r9 = "Failed to retrieve remote module version."
            android.util.Log.w(r8, r9)     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            erm r8 = new erm     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            java.lang.String r9 = "Failed to connect to dynamite module ContentResolver."
            r8.<init>(r9)     // Catch:{ Exception -> 0x0093, all -> 0x008f }
            throw r8     // Catch:{ Exception -> 0x0093, all -> 0x008f }
        L_0x008f:
            r8 = move-exception
            r0 = r7
            goto L_0x00ad
        L_0x0093:
            r8 = move-exception
            r0 = r7
            goto L_0x009b
        L_0x0096:
            r7 = move-exception
            r8 = r7
            goto L_0x00ad
        L_0x0099:
            r7 = move-exception
            r8 = r7
        L_0x009b:
            boolean r7 = r8 instanceof p000.erm     // Catch:{ all -> 0x00a9 }
            if (r7 != 0) goto L_0x00a8
            erm r7 = new erm     // Catch:{ all -> 0x00a9 }
            java.lang.String r9 = "V2 version check failed"
            r7.<init>(r9, r8)     // Catch:{ all -> 0x00a9 }
            throw r7     // Catch:{ all -> 0x00a9 }
        L_0x00a8:
            throw r8     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            r7 = move-exception
            r8 = r7
        L_0x00ad:
            if (r0 == 0) goto L_0x00b2
            r0.close()
        L_0x00b2:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.erq.m8065b(android.content.Context, java.lang.String, boolean):int");
    }

    /* renamed from: b */
    public static erq m8066b(Context context, String str) {
        if (str.length() != 0) {
            "Selected local version of ".concat(str);
        } else {
            new String("Selected local version of ");
        }
        return new erq(context.getApplicationContext());
    }
}

package p000;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* renamed from: ilv */
/* compiled from: PG */
final class ilv {

    /* renamed from: a */
    public static final boolean f14462a;

    /* renamed from: b */
    public static final boolean f14463b;

    /* renamed from: c */
    public static final boolean f14464c = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    /* renamed from: d */
    private static final Logger f14465d = Logger.getLogger(ilv.class.getName());

    /* renamed from: e */
    private static final Unsafe f14466e = m14030a();

    /* renamed from: f */
    private static final Class f14467f = ihe.f14176a;

    /* renamed from: g */
    private static final boolean f14468g = m14043c(Long.TYPE);

    /* renamed from: h */
    private static final boolean f14469h = m14043c(Integer.TYPE);

    /* renamed from: i */
    private static final ilu f14470i;

    /* renamed from: j */
    private static final long f14471j = ((long) m14039b(byte[].class));

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0166 A[SYNTHETIC, Splitter:B:37:0x0166] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0314  */
    static {
        /*
            java.lang.String r0 = "copyMemory"
            java.lang.Class<double[]> r1 = double[].class
            java.lang.Class<float[]> r2 = float[].class
            java.lang.Class<long[]> r3 = long[].class
            java.lang.Class<int[]> r4 = int[].class
            java.lang.Class<boolean[]> r5 = boolean[].class
            java.lang.Class<ilv> r6 = p000.ilv.class
            java.lang.String r6 = r6.getName()
            java.util.logging.Logger r6 = java.util.logging.Logger.getLogger(r6)
            f14465d = r6
            sun.misc.Unsafe r6 = m14030a()
            f14466e = r6
            java.lang.Class r6 = p000.ihe.f14176a
            f14467f = r6
            java.lang.Class r6 = java.lang.Long.TYPE
            boolean r6 = m14043c(r6)
            f14468g = r6
            java.lang.Class r6 = java.lang.Integer.TYPE
            boolean r6 = m14043c(r6)
            f14469h = r6
            sun.misc.Unsafe r6 = f14466e
            r7 = 0
            if (r6 == 0) goto L_0x005e
            boolean r6 = p000.ihe.m13011a()
            if (r6 != 0) goto L_0x0045
            ilt r7 = new ilt
            sun.misc.Unsafe r6 = f14466e
            r7.<init>(r6)
            goto L_0x005f
        L_0x0045:
            boolean r6 = f14468g
            if (r6 == 0) goto L_0x0051
            ils r7 = new ils
            sun.misc.Unsafe r6 = f14466e
            r7.<init>(r6)
            goto L_0x005f
        L_0x0051:
            boolean r6 = f14469h
            if (r6 == 0) goto L_0x005d
            ilr r7 = new ilr
            sun.misc.Unsafe r6 = f14466e
            r7.<init>(r6)
            goto L_0x005f
        L_0x005d:
        L_0x005e:
        L_0x005f:
            f14470i = r7
            sun.misc.Unsafe r6 = f14466e
            java.lang.String r7 = "putLong"
            java.lang.String r8 = "putByte"
            java.lang.String r9 = "putInt"
            java.lang.String r10 = "getByte"
            java.lang.String r11 = "getInt"
            java.lang.String r12 = "objectFieldOffset"
            java.lang.String r13 = "com.google.protobuf.UnsafeUtil"
            java.lang.String r14 = "platform method missing - proto runtime falling back to safer methods: "
            java.lang.String r15 = "getLong"
            r16 = r1
            r1 = 1
            r17 = 0
            if (r6 == 0) goto L_0x015b
            java.lang.Class r6 = r6.getClass()     // Catch:{ all -> 0x012d }
            r18 = r2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class<java.lang.reflect.Field> r19 = java.lang.reflect.Field.class
            r2[r17] = r19     // Catch:{ all -> 0x012b }
            r6.getMethod(r12, r2)     // Catch:{ all -> 0x012b }
            r2 = 2
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x012b }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x012b }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x012b }
            r19 = 1
            r1[r19] = r2     // Catch:{ all -> 0x012b }
            r6.getMethod(r15, r1)     // Catch:{ all -> 0x012b }
            java.lang.reflect.Field r1 = m14041b()     // Catch:{ all -> 0x012b }
            if (r1 == 0) goto L_0x015d
            boolean r1 = p000.ihe.m13011a()     // Catch:{ all -> 0x012b }
            if (r1 != 0) goto L_0x0127
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x012b }
            r2[r17] = r1     // Catch:{ all -> 0x012b }
            r6.getMethod(r10, r2)     // Catch:{ all -> 0x012b }
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x012b }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x012b }
            r2[r17] = r1     // Catch:{ all -> 0x012b }
            java.lang.Class r1 = java.lang.Byte.TYPE     // Catch:{ all -> 0x012b }
            r20 = r3
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0125 }
            r6.getMethod(r8, r2)     // Catch:{ all -> 0x0125 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0125 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r1[r17] = r2     // Catch:{ all -> 0x0125 }
            r6.getMethod(r11, r1)     // Catch:{ all -> 0x0125 }
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r2[r17] = r1     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0125 }
            r6.getMethod(r9, r2)     // Catch:{ all -> 0x0125 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0125 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r1[r17] = r2     // Catch:{ all -> 0x0125 }
            r6.getMethod(r15, r1)     // Catch:{ all -> 0x0125 }
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r2[r17] = r1     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0125 }
            r6.getMethod(r7, r2)     // Catch:{ all -> 0x0125 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r2[r17] = r1     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0125 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0125 }
            r6.getMethod(r0, r2)     // Catch:{ all -> 0x0125 }
            r1 = 5
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ all -> 0x0125 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x0125 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0125 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r3 = 2
            r1[r3] = r2     // Catch:{ all -> 0x0125 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r3 = 3
            r1[r3] = r2     // Catch:{ all -> 0x0125 }
            r2 = 4
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x0125 }
            r1[r2] = r3     // Catch:{ all -> 0x0125 }
            r6.getMethod(r0, r1)     // Catch:{ all -> 0x0125 }
            goto L_0x0129
        L_0x0125:
            r0 = move-exception
            goto L_0x0132
        L_0x0127:
            r20 = r3
        L_0x0129:
            r0 = 1
            goto L_0x0160
        L_0x012b:
            r0 = move-exception
            goto L_0x0130
        L_0x012d:
            r0 = move-exception
            r18 = r2
        L_0x0130:
            r20 = r3
        L_0x0132:
            java.util.logging.Logger r1 = f14465d
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            int r3 = r3 + 71
            r6.<init>(r3)
            r6.append(r14)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            java.lang.String r3 = "supportsUnsafeByteBufferOperations"
            r1.logp(r2, r13, r3, r0)
            r0 = 0
            goto L_0x0160
        L_0x015b:
            r18 = r2
        L_0x015d:
            r20 = r3
            r0 = 0
        L_0x0160:
            f14462a = r0
            sun.misc.Unsafe r0 = f14466e
            if (r0 == 0) goto L_0x02c6
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x029a }
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.reflect.Field> r1 = java.lang.reflect.Field.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r12, r2)     // Catch:{ all -> 0x029a }
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Class> r1 = java.lang.Class.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.String r1 = "arrayBaseOffset"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x029a }
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Class> r1 = java.lang.Class.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.String r1 = "arrayIndexScale"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x029a }
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r11, r2)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ all -> 0x029a }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r9, r2)     // Catch:{ all -> 0x029a }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x029a }
            r0.getMethod(r15, r1)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r7, r2)     // Catch:{ all -> 0x029a }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x029a }
            java.lang.String r2 = "getObject"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.String r1 = "putObject"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x029a }
            boolean r1 = p000.ihe.m13011a()     // Catch:{ all -> 0x029a }
            if (r1 != 0) goto L_0x0296
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r10, r2)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Byte.TYPE     // Catch:{ all -> 0x029a }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            r0.getMethod(r8, r2)     // Catch:{ all -> 0x029a }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x029a }
            java.lang.String r2 = "getBoolean"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x029a }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.String r1 = "putBoolean"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x029a }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x029a }
            java.lang.String r2 = "getFloat"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r17] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ all -> 0x029a }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x029a }
            java.lang.String r1 = "putFloat"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x029a }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x029a }
            java.lang.String r2 = "getDouble"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x029a }
            r1 = 3
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ all -> 0x029a }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r17] = r2     // Catch:{ all -> 0x029a }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x029a }
            r19 = 1
            r1[r19] = r2     // Catch:{ all -> 0x0294 }
            java.lang.Class r2 = java.lang.Double.TYPE     // Catch:{ all -> 0x0294 }
            r3 = 2
            r1[r3] = r2     // Catch:{ all -> 0x0294 }
            java.lang.String r2 = "putDouble"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x0294 }
            goto L_0x0298
        L_0x0294:
            r0 = move-exception
            goto L_0x029d
        L_0x0296:
            r19 = 1
        L_0x0298:
            r0 = 1
            goto L_0x02c9
        L_0x029a:
            r0 = move-exception
            r19 = 1
        L_0x029d:
            java.util.logging.Logger r1 = f14465d
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            int r3 = r3 + 71
            r6.<init>(r3)
            r6.append(r14)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            java.lang.String r3 = "supportsUnsafeArrayOperations"
            r1.logp(r2, r13, r3, r0)
            r0 = 0
            goto L_0x02c9
        L_0x02c6:
            r19 = 1
            r0 = 0
        L_0x02c9:
            f14463b = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = m14039b(r0)
            long r0 = (long) r0
            f14471j = r0
            m14039b(r5)
            m14046d(r5)
            m14039b(r4)
            m14046d(r4)
            m14039b(r20)
            m14046d(r20)
            m14039b(r18)
            m14046d(r18)
            m14039b(r16)
            m14046d(r16)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            m14039b(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            m14046d(r0)
            java.lang.reflect.Field r0 = m14041b()
            if (r0 != 0) goto L_0x0303
            goto L_0x030a
        L_0x0303:
            ilu r1 = f14470i
            if (r1 == 0) goto L_0x030a
            r1.mo8983a((java.lang.reflect.Field) r0)
        L_0x030a:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0314
            r1 = 1
            goto L_0x0315
        L_0x0314:
            r1 = 0
        L_0x0315:
            f14464c = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ilv.<clinit>():void");
    }

    private ilv() {
    }

    /* renamed from: a */
    static Object m14028a(Class cls) {
        try {
            return f14466e.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    /* renamed from: b */
    private static int m14039b(Class cls) {
        if (f14463b) {
            return f14470i.mo8979a(cls);
        }
        return -1;
    }

    /* renamed from: d */
    private static void m14046d(Class cls) {
        if (f14463b) {
            f14470i.mo8984b(cls);
        }
    }

    /* renamed from: b */
    private static Field m14041b() {
        Field a;
        if (ihe.m13011a() && (a = m14029a(Buffer.class, "effectiveDirectAddress")) != null) {
            return a;
        }
        Field a2 = m14029a(Buffer.class, "address");
        if (a2 == null || a2.getType() != Long.TYPE) {
            return null;
        }
        return a2;
    }

    /* renamed from: c */
    private static boolean m14043c(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        if (ihe.m13011a()) {
            try {
                Class cls3 = f14467f;
                cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
                cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
                cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
                cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
                cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
                cls3.getMethod("peekByte", new Class[]{cls});
                cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
                cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
                return true;
            } catch (Throwable th) {
            }
        }
        return false;
    }

    /* renamed from: a */
    private static Field m14029a(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: c */
    static boolean m14044c(Object obj, long j) {
        return f14470i.mo8976b(obj, j);
    }

    /* renamed from: i */
    public static boolean m14051i(Object obj, long j) {
        return m14049g(obj, j) != 0;
    }

    /* renamed from: j */
    public static boolean m14052j(Object obj, long j) {
        return m14050h(obj, j) != 0;
    }

    /* renamed from: a */
    static byte m14026a(byte[] bArr, long j) {
        return f14470i.mo8971a(bArr, f14471j + j);
    }

    /* renamed from: g */
    public static byte m14049g(Object obj, long j) {
        return (byte) (m14027a(obj, -4 & j) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    /* renamed from: h */
    public static byte m14050h(Object obj, long j) {
        return (byte) (m14027a(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* renamed from: e */
    static double m14047e(Object obj, long j) {
        return f14470i.mo8978d(obj, j);
    }

    /* renamed from: d */
    static float m14045d(Object obj, long j) {
        return f14470i.mo8977c(obj, j);
    }

    /* renamed from: a */
    static int m14027a(Object obj, long j) {
        return f14470i.mo8985e(obj, j);
    }

    /* renamed from: b */
    static long m14040b(Object obj, long j) {
        return f14470i.mo8986f(obj, j);
    }

    /* renamed from: f */
    static Object m14048f(Object obj, long j) {
        return f14470i.mo8987g(obj, j);
    }

    /* renamed from: a */
    static Unsafe m14030a() {
        try {
            return (Unsafe) AccessController.doPrivileged(new ilq());
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    static void m14037a(Object obj, long j, boolean z) {
        f14470i.mo8975a(obj, j, z);
    }

    /* renamed from: a */
    static void m14038a(byte[] bArr, long j, byte b) {
        f14470i.mo8972a((Object) bArr, f14471j + j, b);
    }

    /* renamed from: a */
    public static void m14031a(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = ((((int) j) ^ -1) & 3) << 3;
        m14034a(obj, j2, ((b & 255) << i) | (m14027a(obj, j2) & ((255 << i) ^ -1)));
    }

    /* renamed from: b */
    public static void m14042b(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        m14034a(obj, j2, ((b & 255) << i) | (m14027a(obj, j2) & ((255 << i) ^ -1)));
    }

    /* renamed from: a */
    static void m14032a(Object obj, long j, double d) {
        f14470i.mo8973a(obj, j, d);
    }

    /* renamed from: a */
    static void m14033a(Object obj, long j, float f) {
        f14470i.mo8974a(obj, j, f);
    }

    /* renamed from: a */
    static void m14034a(Object obj, long j, int i) {
        f14470i.mo8980a(obj, j, i);
    }

    /* renamed from: a */
    static void m14035a(Object obj, long j, long j2) {
        f14470i.mo8981a(obj, j, j2);
    }

    /* renamed from: a */
    static void m14036a(Object obj, long j, Object obj2) {
        f14470i.mo8982a(obj, j, obj2);
    }
}

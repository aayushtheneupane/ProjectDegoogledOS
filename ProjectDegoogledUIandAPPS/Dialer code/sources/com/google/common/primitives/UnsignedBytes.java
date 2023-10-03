package com.google.common.primitives;

import java.util.Comparator;
import sun.misc.Unsafe;

public final class UnsignedBytes {

    static class LexicographicalComparatorHolder {
        static final String UNSAFE_COMPARATOR_NAME = (LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator");

        enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public int compare(Object obj, Object obj2) {
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                int min = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < min; i++) {
                    int compare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }
        }

        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = false;
            static final int BYTE_ARRAY_BASE_OFFSET = 0;
            static final Unsafe theUnsafe = null;

            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x006c, code lost:
                throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
             */
            /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
                r1 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.C08881());
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0024 */
            static {
                /*
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator r0 = new com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator
                    r1 = 0
                    java.lang.String r2 = "INSTANCE"
                    r0.<init>(r2, r1)
                    INSTANCE = r0
                    r0 = 1
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator[] r2 = new com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator[r0]
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator r3 = INSTANCE
                    r2[r1] = r3
                    $VALUES = r2
                    java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
                    java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
                    boolean r1 = r1.equals(r2)
                    BIG_ENDIAN = r1
                    sun.misc.Unsafe r1 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0024 }
                    goto L_0x002f
                L_0x0024:
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1 r1 = new com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1     // Catch:{ PrivilegedActionException -> 0x0060 }
                    r1.<init>()     // Catch:{ PrivilegedActionException -> 0x0060 }
                    java.lang.Object r1 = java.security.AccessController.doPrivileged(r1)     // Catch:{ PrivilegedActionException -> 0x0060 }
                    sun.misc.Unsafe r1 = (sun.misc.Unsafe) r1     // Catch:{ PrivilegedActionException -> 0x0060 }
                L_0x002f:
                    theUnsafe = r1
                    sun.misc.Unsafe r1 = theUnsafe
                    java.lang.Class<byte[]> r2 = byte[].class
                    int r1 = r1.arrayBaseOffset(r2)
                    BYTE_ARRAY_BASE_OFFSET = r1
                    java.lang.String r1 = "sun.arch.data.model"
                    java.lang.String r1 = java.lang.System.getProperty(r1)
                    java.lang.String r2 = "64"
                    boolean r1 = r2.equals(r1)
                    if (r1 == 0) goto L_0x005a
                    int r1 = BYTE_ARRAY_BASE_OFFSET
                    int r1 = r1 % 8
                    if (r1 != 0) goto L_0x005a
                    sun.misc.Unsafe r1 = theUnsafe
                    java.lang.Class<byte[]> r2 = byte[].class
                    int r1 = r1.arrayIndexScale(r2)
                    if (r1 != r0) goto L_0x005a
                    return
                L_0x005a:
                    java.lang.Error r0 = new java.lang.Error
                    r0.<init>()
                    throw r0
                L_0x0060:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.Throwable r0 = r0.getCause()
                    java.lang.String r2 = "Could not initialize intrinsics"
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.<clinit>():void");
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i = min & -8;
                int i2 = 0;
                while (i2 < i) {
                    long j = (long) i2;
                    long j2 = theUnsafe.getLong(bArr, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    long j3 = theUnsafe.getLong(bArr2, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    if (j2 == j3) {
                        i2 += 8;
                    } else if (BIG_ENDIAN) {
                        return UnsignedLongs.compare(j2, j3);
                    } else {
                        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & -8;
                        return ((int) ((j2 >>> numberOfTrailingZeros) & 255)) - ((int) (255 & (j3 >>> numberOfTrailingZeros)));
                    }
                }
                while (i2 < min) {
                    int compare = UnsignedBytes.compare(bArr[i2], bArr2[i2]);
                    if (compare != 0) {
                        return compare;
                    }
                    i2++;
                }
                return bArr.length - bArr2.length;
            }
        }

        static {
            try {
                Comparator comparator = (Comparator) Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                PureJavaComparator pureJavaComparator = PureJavaComparator.INSTANCE;
            }
        }

        LexicographicalComparatorHolder() {
        }
    }

    public static int compare(byte b, byte b2) {
        return (b & 255) - (b2 & 255);
    }

    static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }
}

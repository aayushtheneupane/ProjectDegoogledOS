package com.google.protobuf;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class Utf8 {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Utf8.class.getName());
    private static final Processor processor = (UnsafeProcessor.isAvailable() ? new UnsafeProcessor() : new SafeProcessor());

    static abstract class Processor {
        Processor() {
        }

        /* access modifiers changed from: package-private */
        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* access modifiers changed from: package-private */
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            int i5;
            char charAt;
            int length = charSequence.length();
            int i6 = i2 + i;
            int i7 = 0;
            while (i7 < length && (i5 = i7 + i) < i6 && (charAt = charSequence.charAt(i7)) < 128) {
                bArr[i5] = (byte) charAt;
                i7++;
            }
            if (i7 == length) {
                return i + length;
            }
            int i8 = i + i7;
            while (i7 < length) {
                char charAt2 = charSequence.charAt(i7);
                if (charAt2 < 128 && i8 < i6) {
                    i4 = i8 + 1;
                    bArr[i8] = (byte) charAt2;
                } else if (charAt2 < 2048 && i8 <= i6 - 2) {
                    int i9 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                    i8 = i9 + 1;
                    bArr[i9] = (byte) ((charAt2 & '?') | 128);
                    i7++;
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i8 <= i6 - 3) {
                    int i10 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 >>> 12) | 480);
                    int i11 = i10 + 1;
                    bArr[i10] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i4 = i11 + 1;
                    bArr[i11] = (byte) ((charAt2 & '?') | 128);
                } else if (i8 <= i6 - 4) {
                    int i12 = i7 + 1;
                    if (i12 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i12);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i13 = i8 + 1;
                            bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                            int i14 = i13 + 1;
                            bArr[i13] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i15 = i14 + 1;
                            bArr[i14] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i8 = i15 + 1;
                            bArr[i15] = (byte) ((codePoint & 63) | 128);
                            i7 = i12;
                            i7++;
                        } else {
                            i7 = i12;
                        }
                    }
                    throw new UnpairedSurrogateException(i7 - 1, length, (C09221) null);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i7 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i8);
                } else {
                    throw new UnpairedSurrogateException(i7, length, (C09221) null);
                }
                i8 = i4;
                i7++;
            }
            return i8;
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        /* synthetic */ UnpairedSurrogateException(int i, int i2, C09221 r4) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static final class UnsafeProcessor extends Processor {
        private static final int ARRAY_BASE_OFFSET;
        private static final boolean AVAILABLE;
        private static final long BUFFER_ADDRESS_OFFSET;
        private static final Unsafe UNSAFE;

        static {
            Unsafe unsafe;
            Unsafe unsafe2;
            Field field = null;
            try {
                unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                    public Object run() throws Exception {
                        Class<Unsafe> cls = Unsafe.class;
                        UnsafeProcessor.access$800(cls);
                        for (Field field : cls.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get((Object) null);
                            if (cls.isInstance(obj)) {
                                return cls.cast(obj);
                            }
                        }
                        return null;
                    }
                });
            } catch (Throwable unused) {
                unsafe = null;
            }
            String str = "available";
            Utf8.logger.log(Level.FINEST, "sun.misc.Unsafe: {}", unsafe != null ? str : "unavailable");
            UNSAFE = unsafe;
            Class<Buffer> cls = Buffer.class;
            boolean z = true;
            try {
                Field declaredField = cls.getDeclaredField("address");
                declaredField.setAccessible(true);
                field = declaredField;
            } catch (Throwable unused2) {
            }
            Logger access$700 = Utf8.logger;
            Level level = Level.FINEST;
            Object[] objArr = new Object[3];
            objArr[0] = cls.getName();
            objArr[1] = "address";
            if (field == null) {
                str = "unavailable";
            }
            objArr[2] = str;
            access$700.log(level, "{0}.{1}: {2}", objArr);
            BUFFER_ADDRESS_OFFSET = (field == null || (unsafe2 = UNSAFE) == null) ? -1 : unsafe2.objectFieldOffset(field);
            Unsafe unsafe3 = UNSAFE;
            ARRAY_BASE_OFFSET = unsafe3 == null ? -1 : unsafe3.arrayBaseOffset(byte[].class);
            if (BUFFER_ADDRESS_OFFSET == -1 || ARRAY_BASE_OFFSET % 8 != 0) {
                z = false;
            }
            AVAILABLE = z;
        }

        UnsafeProcessor() {
        }

        static /* synthetic */ void access$800(Class cls) throws NoSuchMethodException, SecurityException {
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
        }

        static boolean isAvailable() {
            return AVAILABLE;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x0036  */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x003a A[LOOP:1: B:12:0x003a->B:37:0x0110, LOOP_START, PHI: r2 r3 r4 r6 r9 r11 
          PHI: (r2v3 int) = (r2v2 int), (r2v5 int) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r3v2 char) = (r3v1 char), (r3v3 char) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r4v7 long) = (r4v4 long), (r4v8 long) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r6v3 long) = (r6v1 long), (r6v4 long) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r9v1 java.lang.String) = (r9v0 java.lang.String), (r9v2 java.lang.String) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x0034, B:37:0x0110] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int encodeUtf8(java.lang.CharSequence r18, byte[] r19, int r20, int r21) {
            /*
                r17 = this;
                r0 = r18
                r1 = r19
                r2 = r20
                r3 = r21
                int r4 = ARRAY_BASE_OFFSET
                int r4 = r4 + r2
                long r4 = (long) r4
                long r6 = (long) r3
                long r6 = r6 + r4
                int r8 = r18.length()
                java.lang.String r9 = " at index "
                java.lang.String r10 = "Failed writing "
                if (r8 > r3) goto L_0x015b
                int r11 = r1.length
                int r11 = r11 - r3
                if (r11 < r2) goto L_0x015b
                r2 = 0
            L_0x001d:
                r3 = 128(0x80, float:1.794E-43)
                r11 = 1
                if (r2 >= r8) goto L_0x0034
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x0034
                sun.misc.Unsafe r3 = UNSAFE
                long r11 = r11 + r4
                byte r13 = (byte) r13
                r3.putByte(r1, r4, r13)
                int r2 = r2 + 1
                r4 = r11
                goto L_0x001d
            L_0x0034:
                if (r2 != r8) goto L_0x003a
                int r0 = ARRAY_BASE_OFFSET
                goto L_0x0157
            L_0x003a:
                if (r2 >= r8) goto L_0x0155
                char r13 = r0.charAt(r2)
                if (r13 >= r3) goto L_0x0054
                int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r14 >= 0) goto L_0x0054
                sun.misc.Unsafe r14 = UNSAFE
                long r15 = r4 + r11
                byte r13 = (byte) r13
                r14.putByte(r1, r4, r13)
                r17 = r9
                r4 = r15
            L_0x0051:
                r15 = r6
                goto L_0x0110
            L_0x0054:
                r3 = 2048(0x800, float:2.87E-42)
                if (r13 >= r3) goto L_0x007d
                r14 = 2
                long r14 = r6 - r14
                int r3 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
                if (r3 > 0) goto L_0x007d
                sun.misc.Unsafe r3 = UNSAFE
                long r14 = r4 + r11
                r17 = r9
                int r9 = r13 >>> 6
                r9 = r9 | 960(0x3c0, float:1.345E-42)
                byte r9 = (byte) r9
                r3.putByte(r1, r4, r9)
                sun.misc.Unsafe r3 = UNSAFE
                long r4 = r14 + r11
                r9 = r13 & 63
                r9 = r9 | 128(0x80, float:1.794E-43)
                byte r9 = (byte) r9
                r3.putByte(r1, r14, r9)
                r3 = 128(0x80, float:1.794E-43)
                goto L_0x0051
            L_0x007d:
                r17 = r9
                r3 = 57343(0xdfff, float:8.0355E-41)
                r9 = 55296(0xd800, float:7.7486E-41)
                if (r13 < r9) goto L_0x0089
                if (r3 >= r13) goto L_0x00bc
            L_0x0089:
                r14 = 3
                long r14 = r6 - r14
                int r14 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
                if (r14 > 0) goto L_0x00bc
                sun.misc.Unsafe r3 = UNSAFE
                long r14 = r4 + r11
                int r9 = r13 >>> 12
                r9 = r9 | 480(0x1e0, float:6.73E-43)
                byte r9 = (byte) r9
                r3.putByte(r1, r4, r9)
                sun.misc.Unsafe r3 = UNSAFE
                long r11 = r11 + r14
                int r4 = r13 >>> 6
                r4 = r4 & 63
                r4 = r4 | 128(0x80, float:1.794E-43)
                byte r4 = (byte) r4
                r3.putByte(r1, r14, r4)
                sun.misc.Unsafe r3 = UNSAFE
                r4 = 1
                long r4 = r4 + r11
                r9 = r13 & 63
                r9 = r9 | 128(0x80, float:1.794E-43)
                byte r9 = (byte) r9
                r3.putByte(r1, r11, r9)
                r3 = 128(0x80, float:1.794E-43)
                r11 = 1
                goto L_0x0051
            L_0x00bc:
                r11 = 4
                long r11 = r6 - r11
                int r11 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                r12 = 0
                if (r11 > 0) goto L_0x0120
                int r3 = r2 + 1
                if (r3 == r8) goto L_0x0118
                char r2 = r0.charAt(r3)
                boolean r9 = java.lang.Character.isSurrogatePair(r13, r2)
                if (r9 == 0) goto L_0x0117
                int r2 = java.lang.Character.toCodePoint(r13, r2)
                sun.misc.Unsafe r9 = UNSAFE
                r11 = 1
                long r13 = r4 + r11
                int r15 = r2 >>> 18
                r15 = r15 | 240(0xf0, float:3.36E-43)
                byte r15 = (byte) r15
                r9.putByte(r1, r4, r15)
                sun.misc.Unsafe r4 = UNSAFE
                r15 = r6
                long r5 = r13 + r11
                int r7 = r2 >>> 12
                r7 = r7 & 63
                r9 = 128(0x80, float:1.794E-43)
                r7 = r7 | r9
                byte r7 = (byte) r7
                r4.putByte(r1, r13, r7)
                sun.misc.Unsafe r4 = UNSAFE
                long r13 = r5 + r11
                int r7 = r2 >>> 6
                r7 = r7 & 63
                r7 = r7 | r9
                byte r7 = (byte) r7
                r4.putByte(r1, r5, r7)
                sun.misc.Unsafe r4 = UNSAFE
                long r5 = r13 + r11
                r2 = r2 & 63
                r2 = r2 | r9
                byte r2 = (byte) r2
                r4.putByte(r1, r13, r2)
                r2 = r3
                r4 = r5
                r3 = r9
            L_0x0110:
                int r2 = r2 + 1
                r9 = r17
                r6 = r15
                goto L_0x003a
            L_0x0117:
                r2 = r3
            L_0x0118:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                int r2 = r2 + -1
                r0.<init>(r2, r8, r12)
                throw r0
            L_0x0120:
                if (r9 > r13) goto L_0x0138
                if (r13 > r3) goto L_0x0138
                int r1 = r2 + 1
                if (r1 == r8) goto L_0x0132
                char r0 = r0.charAt(r1)
                boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
                if (r0 != 0) goto L_0x0138
            L_0x0132:
                com.google.protobuf.Utf8$UnpairedSurrogateException r0 = new com.google.protobuf.Utf8$UnpairedSurrogateException
                r0.<init>(r2, r8, r12)
                throw r0
            L_0x0138:
                java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r10)
                r1.append(r13)
                r6 = r17
                r1.append(r6)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0155:
                int r0 = ARRAY_BASE_OFFSET
            L_0x0157:
                long r0 = (long) r0
                long r4 = r4 - r0
                int r0 = (int) r4
                return r0
            L_0x015b:
                r6 = r9
                java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r4 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r10)
                int r8 = r8 + -1
                char r0 = r0.charAt(r8)
                r4.append(r0)
                r4.append(r6)
                int r0 = r2 + r3
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.encodeUtf8(java.lang.CharSequence, byte[], int, int):int");
        }
    }

    private Utf8() {
    }

    static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new UnpairedSurrogateException(i2, length2, (C09221) null);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("UTF-8 length does not fit in int: ");
        outline13.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(outline13.toString());
    }
}

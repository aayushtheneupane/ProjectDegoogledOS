package p000;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: ahf */
/* compiled from: PG */
public final class ahf {

    /* renamed from: a */
    public static final ahf f487a = new ahe().mo468a();

    /* renamed from: c */
    private static final String f488c = iol.m14236b("Data");

    /* renamed from: b */
    public Map f489b;

    ahf() {
    }

    public ahf(ahf ahf) {
        this.f489b = new HashMap(ahf.f489b);
    }

    public ahf(Map map) {
        this.f489b = new HashMap(map);
    }

    /* renamed from: a */
    static Boolean[] m484a(boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    /* renamed from: b */
    static Byte[] m489b(byte[] bArr) {
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: a */
    static Double[] m485a(double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    /* renamed from: a */
    static Float[] m486a(float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    /* renamed from: a */
    static Integer[] m487a(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    /* renamed from: a */
    static Long[] m488a(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ahf ahf = (ahf) obj;
        Set<String> keySet = this.f489b.keySet();
        if (!keySet.equals(ahf.f489b.keySet())) {
            return false;
        }
        for (String str : keySet) {
            Object obj2 = this.f489b.get(str);
            Object obj3 = ahf.f489b.get(str);
            if (obj2 != null && obj3 != null) {
                if (!((!(obj2 instanceof Object[]) || !(obj3 instanceof Object[])) ? obj2.equals(obj3) : Arrays.deepEquals((Object[]) obj2, (Object[]) obj3))) {
                }
            } else if (obj2 == obj3) {
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0072 A[SYNTHETIC, Splitter:B:38:0x0072] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0062=Splitter:B:31:0x0062, B:14:0x0035=Splitter:B:14:0x0035} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.ahf m482a(byte[] r7) {
        /*
            java.lang.String r0 = "Error in Data#fromByteArray: "
            int r1 = r7.length
            r2 = 10240(0x2800, float:1.4349E-41)
            if (r1 > r2) goto L_0x0087
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r7)
            r7 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x004b, all -> 0x0049 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x004b, all -> 0x0049 }
            int r7 = r3.readInt()     // Catch:{ IOException -> 0x0047, ClassNotFoundException -> 0x0045, all -> 0x0040 }
        L_0x001b:
            if (r7 <= 0) goto L_0x002b
            java.lang.String r4 = r3.readUTF()     // Catch:{ IOException -> 0x0047, ClassNotFoundException -> 0x0045, all -> 0x0040 }
            java.lang.Object r5 = r3.readObject()     // Catch:{ IOException -> 0x0047, ClassNotFoundException -> 0x0045, all -> 0x0040 }
            r1.put(r4, r5)     // Catch:{ IOException -> 0x0047, ClassNotFoundException -> 0x0045, all -> 0x0040 }
            int r7 = r7 + -1
            goto L_0x001b
        L_0x002b:
            r3.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0035
        L_0x002f:
            r7 = move-exception
            java.lang.String r3 = f488c
            android.util.Log.e(r3, r0, r7)
        L_0x0035:
            r2.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0065
        L_0x0039:
            r7 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r7)
            goto L_0x0065
        L_0x0040:
            r7 = move-exception
            r1 = r7
            r7 = r3
            goto L_0x006f
        L_0x0045:
            r7 = move-exception
            goto L_0x0051
        L_0x0047:
            r7 = move-exception
            goto L_0x0051
        L_0x0049:
            r1 = move-exception
            goto L_0x006f
        L_0x004b:
            r3 = move-exception
            goto L_0x004e
        L_0x004d:
            r3 = move-exception
        L_0x004e:
            r6 = r3
            r3 = r7
            r7 = r6
        L_0x0051:
            java.lang.String r4 = f488c     // Catch:{ all -> 0x006b }
            android.util.Log.e(r4, r0, r7)     // Catch:{ all -> 0x006b }
            if (r3 == 0) goto L_0x0062
            r3.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0062
        L_0x005c:
            r7 = move-exception
            java.lang.String r3 = f488c
            android.util.Log.e(r3, r0, r7)
        L_0x0062:
            r2.close()     // Catch:{ IOException -> 0x0039 }
        L_0x0065:
            ahf r7 = new ahf
            r7.<init>((java.util.Map) r1)
            return r7
        L_0x006b:
            r7 = move-exception
            r1 = r7
            r7 = r3
        L_0x006f:
            if (r7 != 0) goto L_0x0072
            goto L_0x007c
        L_0x0072:
            r7.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007c
        L_0x0076:
            r7 = move-exception
            java.lang.String r3 = f488c
            android.util.Log.e(r3, r0, r7)
        L_0x007c:
            r2.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0086
        L_0x0080:
            r7 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r7)
        L_0x0086:
            throw r1
        L_0x0087:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240 bytes when serialized"
            r7.<init>(r0)
            goto L_0x0090
        L_0x008f:
            throw r7
        L_0x0090:
            goto L_0x008f
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ahf.m482a(byte[]):ahf");
    }

    /* renamed from: a */
    public final Map mo470a() {
        return Collections.unmodifiableMap(this.f489b);
    }

    public final int hashCode() {
        return this.f489b.hashCode() * 31;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007b A[SYNTHETIC, Splitter:B:31:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0096 A[SYNTHETIC, Splitter:B:42:0x0096] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m483a(p000.ahf r5) {
        /*
            java.lang.String r0 = "Error in Data#toByteArray: "
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r2 = 0
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x006f, all -> 0x006d }
            r3.<init>(r1)     // Catch:{ IOException -> 0x006f, all -> 0x006d }
            java.util.Map r2 = r5.f489b     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            int r2 = r2.size()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            r3.writeInt(r2)     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.util.Map r5 = r5.f489b     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.util.Set r5 = r5.entrySet()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
        L_0x0020:
            boolean r2 = r5.hasNext()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r5.next()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.lang.Object r4 = r2.getKey()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            r3.writeObject(r2)     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            goto L_0x0020
        L_0x003d:
            r3.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0047
        L_0x0041:
            r5 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r5)
        L_0x0047:
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0051
        L_0x004b:
            r5 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r5)
        L_0x0051:
            int r5 = r1.size()
            r0 = 10240(0x2800, float:1.4349E-41)
            if (r5 > r0) goto L_0x005e
            byte[] r5 = r1.toByteArray()
            return r5
        L_0x005e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240 bytes when serialized"
            r5.<init>(r0)
            throw r5
        L_0x0066:
            r5 = move-exception
            r2 = r3
            goto L_0x0093
        L_0x006a:
            r5 = move-exception
            r2 = r3
            goto L_0x0070
        L_0x006d:
            r5 = move-exception
            goto L_0x0093
        L_0x006f:
            r5 = move-exception
        L_0x0070:
            java.lang.String r3 = f488c     // Catch:{ all -> 0x0090 }
            android.util.Log.e(r3, r0, r5)     // Catch:{ all -> 0x0090 }
            byte[] r5 = r1.toByteArray()     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0085
            r2.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0085
        L_0x007f:
            r2 = move-exception
            java.lang.String r3 = f488c
            android.util.Log.e(r3, r0, r2)
        L_0x0085:
            r1.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008f
        L_0x0089:
            r1 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r1)
        L_0x008f:
            return r5
        L_0x0090:
            r5 = move-exception
        L_0x0093:
            if (r2 != 0) goto L_0x0096
            goto L_0x00a0
        L_0x0096:
            r2.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x00a0
        L_0x009a:
            r2 = move-exception
            java.lang.String r3 = f488c
            android.util.Log.e(r3, r0, r2)
        L_0x00a0:
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00aa
        L_0x00a4:
            r1 = move-exception
            java.lang.String r2 = f488c
            android.util.Log.e(r2, r0, r1)
        L_0x00aa:
            goto L_0x00ac
        L_0x00ab:
            throw r5
        L_0x00ac:
            goto L_0x00ab
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ahf.m483a(ahf):byte[]");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Data {");
        if (!this.f489b.isEmpty()) {
            for (String str : this.f489b.keySet()) {
                sb.append(str);
                sb.append(" : ");
                Object obj = this.f489b.get(str);
                if (obj instanceof Object[]) {
                    sb.append(Arrays.toString((Object[]) obj));
                } else {
                    sb.append(obj);
                }
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

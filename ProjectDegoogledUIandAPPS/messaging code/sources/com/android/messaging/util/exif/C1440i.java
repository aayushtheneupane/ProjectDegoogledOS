package com.android.messaging.util.exif;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.exif.i */
public class C1440i {
    private static final short TAG_JPEG_INTERCHANGE_FORMAT = ((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
    private static final short TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = ((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
    private static final short TAG_STRIP_BYTE_COUNTS = ((short) C1435d.TAG_STRIP_BYTE_COUNTS);
    private static final short TAG_STRIP_OFFSETS = ((short) C1435d.TAG_STRIP_OFFSETS);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    /* renamed from: pL */
    private static final short f2279pL = ((short) C1435d.f2257pL);

    /* renamed from: qL */
    private static final short f2280qL = ((short) C1435d.f2258qL);

    /* renamed from: wL */
    private static final short f2281wL = ((short) C1435d.f2264wL);

    /* renamed from: HL */
    private final C1432a f2282HL;

    /* renamed from: IL */
    private int f2283IL = 0;

    /* renamed from: KL */
    private int f2284KL = 0;

    /* renamed from: LL */
    private int f2285LL;

    /* renamed from: ML */
    private C1439h f2286ML;

    /* renamed from: NL */
    private C1442k f2287NL;

    /* renamed from: OL */
    private C1442k f2288OL;

    /* renamed from: PL */
    private boolean f2289PL;

    /* renamed from: QL */
    private boolean f2290QL = false;

    /* renamed from: RL */
    private int f2291RL;

    /* renamed from: SL */
    private byte[] f2292SL;

    /* renamed from: TL */
    private int f2293TL;

    /* renamed from: UL */
    private int f2294UL;

    /* renamed from: VL */
    private final TreeMap f2295VL = new TreeMap();
    private final C1435d mInterface;
    private final int mOptions;
    private C1442k mTag;

    private C1440i(InputStream inputStream, int i, C1435d dVar) {
        boolean z;
        if (inputStream != null) {
            this.mInterface = dVar;
            C1432a aVar = new C1432a(inputStream);
            if (aVar.readShort() == -40) {
                short readShort = aVar.readShort();
                while (true) {
                    if (readShort == -39 || C1444m.m3717e(readShort)) {
                        break;
                    }
                    int readUnsignedShort = aVar.readUnsignedShort();
                    if (readShort == -31 && readUnsignedShort >= 8) {
                        int readInt = aVar.readInt();
                        short readShort2 = aVar.readShort();
                        readUnsignedShort -= 6;
                        if (readInt == 1165519206 && readShort2 == 0) {
                            this.f2294UL = aVar.mo8065Il();
                            this.f2291RL = readUnsignedShort;
                            z = true;
                            break;
                        }
                    }
                    if (readUnsignedShort < 2) {
                        break;
                    }
                    long j = (long) (readUnsignedShort - 2);
                    if (j != aVar.skip(j)) {
                        break;
                    }
                    readShort = aVar.readShort();
                }
                Log.w("MessagingApp", "Invalid JPEG format.");
                z = false;
                this.f2290QL = z;
                this.f2282HL = new C1432a(inputStream);
                this.mOptions = i;
                if (this.f2290QL) {
                    short readShort3 = this.f2282HL.readShort();
                    if (18761 == readShort3) {
                        this.f2282HL.setByteOrder(ByteOrder.LITTLE_ENDIAN);
                    } else if (19789 == readShort3) {
                        this.f2282HL.setByteOrder(ByteOrder.BIG_ENDIAN);
                    } else {
                        throw new ExifInvalidFormatException("Invalid TIFF header");
                    }
                    if (this.f2282HL.readShort() == 42) {
                        long readUnsignedInt = this.f2282HL.readUnsignedInt();
                        if (readUnsignedInt <= 2147483647L) {
                            this.f2293TL = (int) readUnsignedInt;
                            this.f2285LL = 0;
                            if (m3675Kb(0) || m3678Oo()) {
                                m3681c(0, readUnsignedInt);
                                int i2 = this.f2293TL;
                                if (i2 > 8) {
                                    this.f2292SL = new byte[(i2 - 8)];
                                    read(this.f2292SL);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        throw new ExifInvalidFormatException("Invalid offset " + readUnsignedInt);
                    }
                    throw new ExifInvalidFormatException("Invalid TIFF header");
                }
                return;
            }
            throw new ExifInvalidFormatException("Invalid JPEG format");
        }
        throw new IOException("Null argument inputStream to ExifParser");
    }

    /* renamed from: Kb */
    private boolean m3675Kb(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return i == 4 && (this.mOptions & 8) != 0;
                    }
                    if ((this.mOptions & 16) != 0) {
                        return true;
                    }
                    return false;
                } else if ((this.mOptions & 4) != 0) {
                    return true;
                } else {
                    return false;
                }
            } else if ((this.mOptions & 2) != 0) {
                return true;
            } else {
                return false;
            }
        } else if ((this.mOptions & 1) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: Lb */
    private void m3676Lb(int i) {
        this.f2282HL.mo8064H((long) i);
        while (!this.f2295VL.isEmpty() && ((Integer) this.f2295VL.firstKey()).intValue() < i) {
            this.f2295VL.pollFirstEntry();
        }
    }

    /* renamed from: No */
    private boolean m3677No() {
        return (this.mOptions & 32) != 0;
    }

    /* renamed from: Oo */
    private boolean m3678Oo() {
        int i = this.f2285LL;
        if (i != 0) {
            if (i == 1) {
                return m3677No();
            }
            if (i != 2) {
                return false;
            }
            return m3675Kb(3);
        } else if (m3675Kb(2) || m3675Kb(4) || m3675Kb(3) || m3675Kb(1)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: Z */
    private boolean m3679Z(int i, int i2) {
        int i3 = this.mInterface.mo8103yk().get(i2);
        if (i3 == 0) {
            return false;
        }
        int[] Jk = C1443l.m3709Jk();
        int i4 = i3 >>> 24;
        for (int i5 = 0; i5 < Jk.length; i5++) {
            if (i == Jk[i5] && ((i4 >> i5) & 1) == 1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    protected static C1440i m3680a(InputStream inputStream, C1435d dVar) {
        return new C1440i(inputStream, 63, dVar);
    }

    /* renamed from: c */
    private void m3681c(int i, long j) {
        this.f2295VL.put(Integer.valueOf((int) j), new C1438g(i, m3675Kb(i)));
    }

    /* renamed from: e */
    private void m3682e(C1442k kVar) {
        if (kVar.getComponentCount() != 0) {
            short Gk = kVar.mo8123Gk();
            int Ek = kVar.mo8121Ek();
            if (Gk != f2279pL || !m3679Z(Ek, C1435d.f2257pL)) {
                if (Gk != f2280qL || !m3679Z(Ek, C1435d.f2258qL)) {
                    if (Gk != f2281wL || !m3679Z(Ek, C1435d.f2264wL)) {
                        if (Gk != TAG_JPEG_INTERCHANGE_FORMAT || !m3679Z(Ek, C1435d.TAG_JPEG_INTERCHANGE_FORMAT)) {
                            if (Gk != TAG_JPEG_INTERCHANGE_FORMAT_LENGTH || !m3679Z(Ek, C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH)) {
                                if (Gk != TAG_STRIP_OFFSETS || !m3679Z(Ek, C1435d.TAG_STRIP_OFFSETS)) {
                                    if (Gk == TAG_STRIP_BYTE_COUNTS && m3679Z(Ek, C1435d.TAG_STRIP_BYTE_COUNTS) && m3677No() && kVar.hasValue()) {
                                        this.f2287NL = kVar;
                                    }
                                } else if (!m3677No()) {
                                } else {
                                    if (kVar.hasValue()) {
                                        for (int i = 0; i < kVar.getComponentCount(); i++) {
                                            if (kVar.getDataType() == 3) {
                                                this.f2295VL.put(Integer.valueOf((int) kVar.mo8128Ya(i)), new C1439h(4, i));
                                            } else {
                                                this.f2295VL.put(Integer.valueOf((int) kVar.mo8128Ya(i)), new C1439h(4, i));
                                            }
                                        }
                                        return;
                                    }
                                    this.f2295VL.put(Integer.valueOf(kVar.getOffset()), new C1437f(kVar, false));
                                }
                            } else if (m3677No()) {
                                this.f2288OL = kVar;
                            }
                        } else if (m3677No()) {
                            this.f2295VL.put(Integer.valueOf((int) kVar.mo8128Ya(0)), new C1439h(3));
                        }
                    } else if (m3675Kb(3)) {
                        m3681c(3, kVar.mo8128Ya(0));
                    }
                } else if (m3675Kb(4)) {
                    m3681c(4, kVar.mo8128Ya(0));
                }
            } else if (m3675Kb(2) || m3675Kb(3)) {
                m3681c(2, kVar.mo8128Ya(0));
            }
        }
    }

    private C1442k readTag() {
        short readShort = this.f2282HL.readShort();
        short readShort2 = this.f2282HL.readShort();
        long readUnsignedInt = this.f2282HL.readUnsignedInt();
        if (readUnsignedInt > 2147483647L) {
            throw new ExifInvalidFormatException("Number of component is larger then Integer.MAX_VALUE");
        } else if (!C1442k.m3693b(readShort2)) {
            Log.w("MessagingApp", String.format("Tag %04x: Invalid data type %d", new Object[]{Short.valueOf(readShort), Short.valueOf(readShort2)}));
            this.f2282HL.skip(4);
            return null;
        } else {
            int i = (int) readUnsignedInt;
            C1442k kVar = new C1442k(readShort, readShort2, i, this.f2285LL, i != 0);
            int dataSize = kVar.getDataSize();
            if (dataSize > 4) {
                long readUnsignedInt2 = this.f2282HL.readUnsignedInt();
                if (readUnsignedInt2 <= 2147483647L) {
                    byte[] bArr = this.f2292SL;
                    if (bArr == null || readUnsignedInt2 >= ((long) this.f2293TL) || readShort2 != 7) {
                        kVar.setOffset((int) readUnsignedInt2);
                    } else {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, ((int) readUnsignedInt2) - 8, bArr2, 0, i);
                        kVar.setValue(bArr2);
                    }
                } else {
                    throw new ExifInvalidFormatException("offset is larger then Integer.MAX_VALUE");
                }
            } else {
                boolean Ik = kVar.mo8125Ik();
                kVar.mo8140na(false);
                mo8112c(kVar);
                kVar.mo8140na(Ik);
                this.f2282HL.skip((long) (4 - dataSize));
                kVar.setOffset(this.f2282HL.mo8065Il() - 4);
            }
            return kVar;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ak */
    public int mo8108Ak() {
        return this.f2285LL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Bk */
    public int mo8109Bk() {
        return this.f2286ML.f2278GL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ck */
    public int mo8110Ck() {
        C1442k kVar = this.f2287NL;
        if (kVar == null) {
            return 0;
        }
        return (int) kVar.mo8128Ya(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Dk */
    public long mo8111Dk() {
        return ((long) readLong()) & 4294967295L;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo8113d(C1442k kVar) {
        if (kVar.getOffset() >= this.f2282HL.mo8065Il()) {
            this.f2295VL.put(Integer.valueOf(kVar.getOffset()), new C1437f(kVar, true));
        }
    }

    /* access modifiers changed from: protected */
    public C1442k getTag() {
        return this.mTag;
    }

    /* access modifiers changed from: protected */
    public int next() {
        if (!this.f2290QL) {
            return 5;
        }
        int Il = this.f2282HL.mo8065Il();
        int i = (this.f2284KL * 12) + this.f2283IL + 2;
        if (Il < i) {
            this.mTag = readTag();
            C1442k kVar = this.mTag;
            if (kVar == null) {
                return next();
            }
            if (this.f2289PL) {
                m3682e(kVar);
            }
            return 1;
        }
        if (Il == i) {
            if (this.f2285LL == 0) {
                long Dk = mo8111Dk();
                if ((m3675Kb(1) || m3677No()) && Dk != 0) {
                    m3681c(1, Dk);
                }
            } else {
                int intValue = this.f2295VL.size() > 0 ? ((Integer) this.f2295VL.firstEntry().getKey()).intValue() - this.f2282HL.mo8065Il() : 4;
                if (intValue < 4) {
                    Log.w("MessagingApp", "Invalid size of link to next IFD: " + intValue);
                } else {
                    long Dk2 = mo8111Dk();
                    if (Dk2 != 0) {
                        Log.w("MessagingApp", "Invalid link to next IFD: " + Dk2);
                    }
                }
            }
        }
        while (this.f2295VL.size() != 0) {
            Map.Entry pollFirstEntry = this.f2295VL.pollFirstEntry();
            Object value = pollFirstEntry.getValue();
            try {
                m3676Lb(((Integer) pollFirstEntry.getKey()).intValue());
                if (value instanceof C1438g) {
                    C1438g gVar = (C1438g) value;
                    this.f2285LL = gVar.f2277FL;
                    this.f2284KL = this.f2282HL.readShort() & 65535;
                    this.f2283IL = ((Integer) pollFirstEntry.getKey()).intValue();
                    if ((this.f2284KL * 12) + this.f2283IL + 2 > this.f2291RL) {
                        StringBuilder Pa = C0632a.m1011Pa("Invalid size of IFD ");
                        Pa.append(this.f2285LL);
                        Log.w("MessagingApp", Pa.toString());
                        return 5;
                    }
                    this.f2289PL = m3678Oo();
                    if (gVar.f2276EL) {
                        return 0;
                    }
                    int i2 = (this.f2284KL * 12) + this.f2283IL + 2;
                    int Il2 = this.f2282HL.mo8065Il();
                    if (Il2 <= i2) {
                        if (this.f2289PL) {
                            while (Il2 < i2) {
                                this.mTag = readTag();
                                Il2 += 12;
                                C1442k kVar2 = this.mTag;
                                if (kVar2 != null) {
                                    m3682e(kVar2);
                                }
                            }
                        } else {
                            m3676Lb(i2);
                        }
                        long Dk3 = mo8111Dk();
                        if (this.f2285LL == 0 && ((m3675Kb(1) || m3677No()) && Dk3 > 0)) {
                            m3681c(1, Dk3);
                        }
                    }
                } else if (value instanceof C1439h) {
                    this.f2286ML = (C1439h) value;
                    return this.f2286ML.type;
                } else {
                    C1437f fVar = (C1437f) value;
                    this.mTag = fVar.tag;
                    if (this.mTag.getDataType() != 7) {
                        mo8112c(this.mTag);
                        m3682e(this.mTag);
                    }
                    if (fVar.f2275EL) {
                        return 2;
                    }
                }
            } catch (IOException unused) {
                StringBuilder Pa2 = C0632a.m1011Pa("Failed to skip to data at: ");
                Pa2.append(pollFirstEntry.getKey());
                Pa2.append(" for ");
                Pa2.append(value.getClass().getName());
                Pa2.append(", the file may be broken.");
                Log.w("MessagingApp", Pa2.toString());
            }
        }
        return 5;
    }

    /* access modifiers changed from: protected */
    public int read(byte[] bArr) {
        return this.f2282HL.read(bArr);
    }

    /* access modifiers changed from: protected */
    public int readLong() {
        return this.f2282HL.readInt();
    }

    /* access modifiers changed from: protected */
    /* renamed from: sk */
    public ByteOrder mo8118sk() {
        return this.f2282HL.mo8076sk();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zk */
    public int mo8119zk() {
        C1442k kVar = this.f2288OL;
        if (kVar == null) {
            return 0;
        }
        return (int) kVar.mo8128Ya(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo8112c(C1442k kVar) {
        short dataType = kVar.getDataType();
        if (dataType == 2 || dataType == 7 || dataType == 1) {
            int componentCount = kVar.getComponentCount();
            if (this.f2295VL.size() > 0 && ((Integer) this.f2295VL.firstEntry().getKey()).intValue() < this.f2282HL.mo8065Il() + componentCount) {
                Object value = this.f2295VL.firstEntry().getValue();
                if (value instanceof C1439h) {
                    StringBuilder Pa = C0632a.m1011Pa("Thumbnail overlaps value for tag: \n");
                    Pa.append(kVar.toString());
                    Log.w("MessagingApp", Pa.toString());
                    Map.Entry pollFirstEntry = this.f2295VL.pollFirstEntry();
                    StringBuilder Pa2 = C0632a.m1011Pa("Invalid thumbnail offset: ");
                    Pa2.append(pollFirstEntry.getKey());
                    Log.w("MessagingApp", Pa2.toString());
                } else {
                    if (value instanceof C1438g) {
                        StringBuilder Pa3 = C0632a.m1011Pa("Ifd ");
                        Pa3.append(((C1438g) value).f2277FL);
                        Pa3.append(" overlaps value for tag: \n");
                        Pa3.append(kVar.toString());
                        Log.w("MessagingApp", Pa3.toString());
                    } else if (value instanceof C1437f) {
                        StringBuilder Pa4 = C0632a.m1011Pa("Tag value for tag: \n");
                        Pa4.append(((C1437f) value).tag.toString());
                        Pa4.append(" overlaps value for tag: \n");
                        Pa4.append(kVar.toString());
                        Log.w("MessagingApp", Pa4.toString());
                    }
                    int intValue = ((Integer) this.f2295VL.firstEntry().getKey()).intValue() - this.f2282HL.mo8065Il();
                    StringBuilder Pa5 = C0632a.m1011Pa("Invalid size of tag: \n");
                    Pa5.append(kVar.toString());
                    Pa5.append(" setting count to: ");
                    Pa5.append(intValue);
                    Log.w("MessagingApp", Pa5.toString());
                    kVar.mo8126Wa(intValue);
                }
            }
        }
        int i = 0;
        switch (kVar.getDataType()) {
            case 1:
            case 7:
                byte[] bArr = new byte[kVar.getComponentCount()];
                this.f2282HL.read(bArr);
                kVar.setValue(bArr);
                return;
            case 2:
                int componentCount2 = kVar.getComponentCount();
                kVar.setValue(componentCount2 > 0 ? this.f2282HL.mo8066a(componentCount2, US_ASCII) : "");
                return;
            case 3:
                int[] iArr = new int[kVar.getComponentCount()];
                int length = iArr.length;
                while (i < length) {
                    iArr[i] = this.f2282HL.readShort() & 65535;
                    i++;
                }
                kVar.mo8132d(iArr);
                return;
            case 4:
                long[] jArr = new long[kVar.getComponentCount()];
                int length2 = jArr.length;
                while (i < length2) {
                    jArr[i] = mo8111Dk();
                    i++;
                }
                kVar.mo8130a(jArr);
                return;
            case 5:
                C1446o[] oVarArr = new C1446o[kVar.getComponentCount()];
                int length3 = oVarArr.length;
                while (i < length3) {
                    oVarArr[i] = new C1446o(mo8111Dk(), mo8111Dk());
                    i++;
                }
                kVar.mo8131a(oVarArr);
                return;
            case 9:
                int[] iArr2 = new int[kVar.getComponentCount()];
                int length4 = iArr2.length;
                while (i < length4) {
                    iArr2[i] = readLong();
                    i++;
                }
                kVar.mo8132d(iArr2);
                return;
            case 10:
                C1446o[] oVarArr2 = new C1446o[kVar.getComponentCount()];
                int length5 = oVarArr2.length;
                while (i < length5) {
                    oVarArr2[i] = new C1446o((long) readLong(), (long) readLong());
                    i++;
                }
                kVar.mo8131a(oVarArr2);
                return;
            default:
                return;
        }
    }
}

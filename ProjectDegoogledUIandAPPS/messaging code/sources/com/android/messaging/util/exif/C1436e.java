package com.android.messaging.util.exif;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.exif.e */
class C1436e extends FilterOutputStream {

    /* renamed from: Ew */
    private final ByteBuffer f2270Ew = ByteBuffer.allocate(4);

    /* renamed from: ZO */
    private C1433b f2271ZO;

    /* renamed from: _O */
    private int f2272_O;

    /* renamed from: aP */
    private int f2273aP;

    /* renamed from: bP */
    private final byte[] f2274bP = new byte[1];
    private final C1435d mInterface;
    private int mState = 0;

    protected C1436e(OutputStream outputStream, C1435d dVar) {
        super(new BufferedOutputStream(outputStream, 65536));
        this.mInterface = dVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8104a(C1433b bVar) {
        this.f2271ZO = bVar;
    }

    public void write(byte[] bArr, int i, int i2) {
        while (true) {
            if ((this.f2272_O > 0 || this.f2273aP > 0 || this.mState != 2) && i2 > 0) {
                int i3 = this.f2272_O;
                if (i3 > 0) {
                    if (i2 <= i3) {
                        i3 = i2;
                    }
                    i2 -= i3;
                    this.f2272_O -= i3;
                    i += i3;
                }
                int i4 = this.f2273aP;
                if (i4 > 0) {
                    if (i2 <= i4) {
                        i4 = i2;
                    }
                    this.out.write(bArr, i, i4);
                    i2 -= i4;
                    this.f2273aP -= i4;
                    i += i4;
                }
                if (i2 != 0) {
                    int i5 = this.mState;
                    if (i5 == 0) {
                        int position = 2 - this.f2270Ew.position();
                        if (i2 <= position) {
                            position = i2;
                        }
                        this.f2270Ew.put(bArr, i, position);
                        i += position;
                        i2 -= position;
                        if (this.f2270Ew.position() >= 2) {
                            this.f2270Ew.rewind();
                            if (this.f2270Ew.getShort() == -40) {
                                this.out.write(this.f2270Ew.array(), 0, 2);
                                this.mState = 1;
                                this.f2270Ew.rewind();
                                C1433b bVar = this.f2271ZO;
                                if (bVar == null) {
                                    continue;
                                } else {
                                    ArrayList arrayList = new ArrayList();
                                    if (bVar.mo8088rk() != null) {
                                        for (C1442k kVar : bVar.mo8088rk()) {
                                            if (kVar.getValue() == null && !C1435d.m3658a(kVar.mo8123Gk())) {
                                                bVar.mo8084b(kVar.mo8123Gk(), kVar.mo8121Ek());
                                                arrayList.add(kVar);
                                            }
                                        }
                                    }
                                    C1443l Qa = this.f2271ZO.mo8078Qa(0);
                                    if (Qa == null) {
                                        Qa = new C1443l(0);
                                        this.f2271ZO.mo8082a(Qa);
                                    }
                                    C1442k Sa = this.mInterface.mo8095Sa(C1435d.f2257pL);
                                    if (Sa != null) {
                                        Qa.mo8150b(Sa);
                                        C1443l Qa2 = this.f2271ZO.mo8078Qa(2);
                                        if (Qa2 == null) {
                                            Qa2 = new C1443l(2);
                                            this.f2271ZO.mo8082a(Qa2);
                                        }
                                        if (this.f2271ZO.mo8078Qa(4) != null) {
                                            C1442k Sa2 = this.mInterface.mo8095Sa(C1435d.f2258qL);
                                            if (Sa2 != null) {
                                                Qa.mo8150b(Sa2);
                                            } else {
                                                StringBuilder Pa = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa.append(C1435d.f2258qL);
                                                throw new IOException(Pa.toString());
                                            }
                                        }
                                        if (this.f2271ZO.mo8078Qa(3) != null) {
                                            C1442k Sa3 = this.mInterface.mo8095Sa(C1435d.f2264wL);
                                            if (Sa3 != null) {
                                                Qa2.mo8150b(Sa3);
                                            } else {
                                                StringBuilder Pa2 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa2.append(C1435d.f2264wL);
                                                throw new IOException(Pa2.toString());
                                            }
                                        }
                                        C1443l Qa3 = this.f2271ZO.mo8078Qa(1);
                                        if (this.f2271ZO.mo8092vk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            C1442k Sa4 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            if (Sa4 != null) {
                                                Qa3.mo8150b(Sa4);
                                                C1442k Sa5 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                if (Sa5 != null) {
                                                    Sa5.setValue(this.f2271ZO.mo8090tk().length);
                                                    Qa3.mo8150b(Sa5);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                                } else {
                                                    StringBuilder Pa3 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa3.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                    throw new IOException(Pa3.toString());
                                                }
                                            } else {
                                                StringBuilder Pa4 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa4.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                throw new IOException(Pa4.toString());
                                            }
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            int uk = this.f2271ZO.mo8091uk();
                                            C1442k Sa6 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_OFFSETS);
                                            if (Sa6 != null) {
                                                C1442k Sa7 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                if (Sa7 != null) {
                                                    long[] jArr = new long[uk];
                                                    for (int i6 = 0; i6 < this.f2271ZO.mo8091uk(); i6++) {
                                                        jArr[i6] = (long) this.f2271ZO.mo8079Ra(i6).length;
                                                    }
                                                    Sa7.mo8130a(jArr);
                                                    Qa3.mo8150b(Sa6);
                                                    Qa3.mo8150b(Sa7);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                } else {
                                                    StringBuilder Pa5 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa5.append(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                    throw new IOException(Pa5.toString());
                                                }
                                            } else {
                                                StringBuilder Pa6 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa6.append(C1435d.TAG_STRIP_OFFSETS);
                                                throw new IOException(Pa6.toString());
                                            }
                                        } else if (Qa3 != null) {
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                        }
                                        C1443l Qa4 = this.f2271ZO.mo8078Qa(0);
                                        int a = m3671a(Qa4, 8);
                                        Qa4.mo8151c((short) C1435d.f2257pL).setValue(a);
                                        C1443l Qa5 = this.f2271ZO.mo8078Qa(2);
                                        int a2 = m3671a(Qa5, a);
                                        C1443l Qa6 = this.f2271ZO.mo8078Qa(3);
                                        if (Qa6 != null) {
                                            Qa5.mo8151c((short) C1435d.f2264wL).setValue(a2);
                                            a2 = m3671a(Qa6, a2);
                                        }
                                        C1443l Qa7 = this.f2271ZO.mo8078Qa(4);
                                        if (Qa7 != null) {
                                            Qa4.mo8151c((short) C1435d.f2258qL).setValue(a2);
                                            a2 = m3671a(Qa7, a2);
                                        }
                                        C1443l Qa8 = this.f2271ZO.mo8078Qa(1);
                                        if (Qa8 != null) {
                                            Qa4.mo8149ab(a2);
                                            a2 = m3671a(Qa8, a2);
                                        }
                                        if (this.f2271ZO.mo8092vk()) {
                                            Qa8.mo8151c((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT).setValue(a2);
                                            a2 += this.f2271ZO.mo8090tk().length;
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            long[] jArr2 = new long[this.f2271ZO.mo8091uk()];
                                            int i7 = a2;
                                            for (int i8 = 0; i8 < this.f2271ZO.mo8091uk(); i8++) {
                                                jArr2[i8] = (long) i7;
                                                i7 += this.f2271ZO.mo8079Ra(i8).length;
                                            }
                                            Qa8.mo8151c((short) C1435d.TAG_STRIP_OFFSETS).mo8130a(jArr2);
                                            a2 = i7;
                                        }
                                        int i9 = a2 + 8;
                                        if (i9 <= 65535) {
                                            C1445n nVar = new C1445n(this.out);
                                            nVar.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            nVar.writeShort(-31);
                                            nVar.writeShort((short) i9);
                                            nVar.writeInt(1165519206);
                                            nVar.writeShort(0);
                                            if (this.f2271ZO.mo8089sk() == ByteOrder.BIG_ENDIAN) {
                                                nVar.writeShort(19789);
                                            } else {
                                                nVar.writeShort(18761);
                                            }
                                            nVar.setByteOrder(this.f2271ZO.mo8089sk());
                                            nVar.writeShort(42);
                                            nVar.writeInt(8);
                                            m3673a(this.f2271ZO.mo8078Qa(0), nVar);
                                            m3673a(this.f2271ZO.mo8078Qa(2), nVar);
                                            C1443l Qa9 = this.f2271ZO.mo8078Qa(3);
                                            if (Qa9 != null) {
                                                m3673a(Qa9, nVar);
                                            }
                                            C1443l Qa10 = this.f2271ZO.mo8078Qa(4);
                                            if (Qa10 != null) {
                                                m3673a(Qa10, nVar);
                                            }
                                            if (this.f2271ZO.mo8078Qa(1) != null) {
                                                m3673a(this.f2271ZO.mo8078Qa(1), nVar);
                                            }
                                            if (this.f2271ZO.mo8092vk()) {
                                                nVar.write(this.f2271ZO.mo8090tk());
                                            } else if (this.f2271ZO.mo8093wk()) {
                                                for (int i10 = 0; i10 < this.f2271ZO.mo8091uk(); i10++) {
                                                    nVar.write(this.f2271ZO.mo8079Ra(i10));
                                                }
                                            }
                                            Iterator it = arrayList.iterator();
                                            while (it.hasNext()) {
                                                this.f2271ZO.mo8080a((C1442k) it.next());
                                            }
                                        } else {
                                            throw new IOException("Exif header is too large (>64Kb)");
                                        }
                                    } else {
                                        StringBuilder Pa7 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                        Pa7.append(C1435d.f2257pL);
                                        throw new IOException(Pa7.toString());
                                    }
                                }
                            } else {
                                throw new IOException("Not a valid jpeg image, cannot write exif");
                            }
                        } else {
                            return;
                        }
                    } else if (i5 == 1) {
                        int position2 = 4 - this.f2270Ew.position();
                        if (i2 <= position2) {
                            position2 = i2;
                        }
                        this.f2270Ew.put(bArr, i, position2);
                        i += position2;
                        i2 -= position2;
                        if (this.f2270Ew.position() == 2 && this.f2270Ew.getShort() == -39) {
                            this.out.write(this.f2270Ew.array(), 0, 2);
                            this.f2270Ew.rewind();
                        }
                        if (this.f2270Ew.position() >= 4) {
                            this.f2270Ew.rewind();
                            short s = this.f2270Ew.getShort();
                            if (s == -31) {
                                this.f2272_O = (this.f2270Ew.getShort() & 65535) - 2;
                                this.mState = 2;
                            } else if (!C1444m.m3717e(s)) {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.f2273aP = (this.f2270Ew.getShort() & 65535) - 2;
                            } else {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.mState = 2;
                            }
                            this.f2270Ew.rewind();
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else {
                    return;
                }
            }
        }
        if (i2 > 0) {
            this.out.write(bArr, i, i2);
        }
    }

    /* renamed from: a */
    private int m3670a(int i, byte[] bArr, int i2, int i3) {
        int position = i - this.f2270Ew.position();
        if (i3 <= position) {
            position = i3;
        }
        this.f2270Ew.put(bArr, i2, position);
        return position;
    }

    /* renamed from: a */
    private void m3673a(C1443l lVar, C1445n nVar) {
        C1442k[] rk = lVar.mo8155rk();
        nVar.writeShort((short) rk.length);
        for (C1442k kVar : rk) {
            nVar.writeShort(kVar.mo8123Gk());
            nVar.writeShort(kVar.getDataType());
            nVar.writeInt(kVar.getComponentCount());
            if (kVar.getDataSize() > 4) {
                nVar.writeInt(kVar.getOffset());
            } else {
                m3672a(kVar, nVar);
                int dataSize = 4 - kVar.getDataSize();
                for (int i = 0; i < dataSize; i++) {
                    nVar.write(0);
                }
            }
        }
        nVar.writeInt(lVar.mo8147Kk());
        for (C1442k kVar2 : rk) {
            if (kVar2.getDataSize() > 4) {
                m3672a(kVar2, nVar);
            }
        }
    }

    /* renamed from: a */
    private int m3671a(C1443l lVar, int i) {
        int Lk = (lVar.mo8148Lk() * 12) + 2 + 4 + i;
        for (C1442k kVar : lVar.mo8155rk()) {
            if (kVar.getDataSize() > 4) {
                kVar.setOffset(Lk);
                Lk = kVar.getDataSize() + Lk;
            }
        }
        return Lk;
    }

    /* renamed from: a */
    static void m3672a(C1442k kVar, C1445n nVar) {
        int i = 0;
        switch (kVar.getDataType()) {
            case 1:
            case 7:
                byte[] bArr = new byte[kVar.getComponentCount()];
                kVar.mo8141q(bArr);
                nVar.write(bArr);
                return;
            case 2:
                byte[] Fk = kVar.mo8122Fk();
                if (Fk.length == kVar.getComponentCount()) {
                    Fk[Fk.length - 1] = 0;
                    nVar.write(Fk);
                    return;
                }
                nVar.write(Fk);
                nVar.write(0);
                return;
            case 3:
                int componentCount = kVar.getComponentCount();
                while (i < componentCount) {
                    nVar.writeShort((short) ((int) kVar.mo8128Ya(i)));
                    i++;
                }
                return;
            case 4:
            case 9:
                int componentCount2 = kVar.getComponentCount();
                while (i < componentCount2) {
                    nVar.writeInt((int) kVar.mo8128Ya(i));
                    i++;
                }
                return;
            case 5:
            case 10:
                int componentCount3 = kVar.getComponentCount();
                while (i < componentCount3) {
                    nVar.mo8156a(kVar.mo8127Xa(i));
                    i++;
                }
                return;
            default:
                return;
        }
    }

    public void write(int i) {
        byte[] bArr = this.f2274bP;
        bArr[0] = (byte) (i & 255);
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if ((this.f2272_O > 0 || this.f2273aP > 0 || this.mState != 2) && length > 0) {
                int i3 = this.f2272_O;
                if (i3 > 0) {
                    if (length <= i3) {
                        i3 = length;
                    }
                    length -= i3;
                    this.f2272_O -= i3;
                    i2 += i3;
                }
                int i4 = this.f2273aP;
                if (i4 > 0) {
                    if (length <= i4) {
                        i4 = length;
                    }
                    this.out.write(bArr, i2, i4);
                    length -= i4;
                    this.f2273aP -= i4;
                    i2 += i4;
                }
                if (length != 0) {
                    int i5 = this.mState;
                    if (i5 == 0) {
                        int a = m3670a(2, bArr, i2, length);
                        i2 += a;
                        length -= a;
                        if (this.f2270Ew.position() >= 2) {
                            this.f2270Ew.rewind();
                            if (this.f2270Ew.getShort() == -40) {
                                this.out.write(this.f2270Ew.array(), 0, 2);
                                this.mState = 1;
                                this.f2270Ew.rewind();
                                C1433b bVar = this.f2271ZO;
                                if (bVar == null) {
                                    continue;
                                } else {
                                    ArrayList arrayList = new ArrayList();
                                    if (bVar.mo8088rk() != null) {
                                        for (C1442k kVar : bVar.mo8088rk()) {
                                            if (kVar.getValue() == null && !C1435d.m3658a(kVar.mo8123Gk())) {
                                                bVar.mo8084b(kVar.mo8123Gk(), kVar.mo8121Ek());
                                                arrayList.add(kVar);
                                            }
                                        }
                                    }
                                    C1443l Qa = this.f2271ZO.mo8078Qa(0);
                                    if (Qa == null) {
                                        Qa = new C1443l(0);
                                        this.f2271ZO.mo8082a(Qa);
                                    }
                                    C1442k Sa = this.mInterface.mo8095Sa(C1435d.f2257pL);
                                    if (Sa != null) {
                                        Qa.mo8150b(Sa);
                                        C1443l Qa2 = this.f2271ZO.mo8078Qa(2);
                                        if (Qa2 == null) {
                                            Qa2 = new C1443l(2);
                                            this.f2271ZO.mo8082a(Qa2);
                                        }
                                        if (this.f2271ZO.mo8078Qa(4) != null) {
                                            C1442k Sa2 = this.mInterface.mo8095Sa(C1435d.f2258qL);
                                            if (Sa2 != null) {
                                                Qa.mo8150b(Sa2);
                                            } else {
                                                StringBuilder Pa = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa.append(C1435d.f2258qL);
                                                throw new IOException(Pa.toString());
                                            }
                                        }
                                        if (this.f2271ZO.mo8078Qa(3) != null) {
                                            C1442k Sa3 = this.mInterface.mo8095Sa(C1435d.f2264wL);
                                            if (Sa3 != null) {
                                                Qa2.mo8150b(Sa3);
                                            } else {
                                                StringBuilder Pa2 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa2.append(C1435d.f2264wL);
                                                throw new IOException(Pa2.toString());
                                            }
                                        }
                                        C1443l Qa3 = this.f2271ZO.mo8078Qa(1);
                                        if (this.f2271ZO.mo8092vk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            C1442k Sa4 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            if (Sa4 != null) {
                                                Qa3.mo8150b(Sa4);
                                                C1442k Sa5 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                if (Sa5 != null) {
                                                    Sa5.setValue(this.f2271ZO.mo8090tk().length);
                                                    Qa3.mo8150b(Sa5);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                                } else {
                                                    StringBuilder Pa3 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa3.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                    throw new IOException(Pa3.toString());
                                                }
                                            } else {
                                                StringBuilder Pa4 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa4.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                throw new IOException(Pa4.toString());
                                            }
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            int uk = this.f2271ZO.mo8091uk();
                                            C1442k Sa6 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_OFFSETS);
                                            if (Sa6 != null) {
                                                C1442k Sa7 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                if (Sa7 != null) {
                                                    long[] jArr = new long[uk];
                                                    for (int i6 = 0; i6 < this.f2271ZO.mo8091uk(); i6++) {
                                                        jArr[i6] = (long) this.f2271ZO.mo8079Ra(i6).length;
                                                    }
                                                    Sa7.mo8130a(jArr);
                                                    Qa3.mo8150b(Sa6);
                                                    Qa3.mo8150b(Sa7);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                } else {
                                                    StringBuilder Pa5 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa5.append(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                    throw new IOException(Pa5.toString());
                                                }
                                            } else {
                                                StringBuilder Pa6 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa6.append(C1435d.TAG_STRIP_OFFSETS);
                                                throw new IOException(Pa6.toString());
                                            }
                                        } else if (Qa3 != null) {
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                        }
                                        C1443l Qa4 = this.f2271ZO.mo8078Qa(0);
                                        int a2 = m3671a(Qa4, 8);
                                        Qa4.mo8151c((short) C1435d.f2257pL).setValue(a2);
                                        C1443l Qa5 = this.f2271ZO.mo8078Qa(2);
                                        int a3 = m3671a(Qa5, a2);
                                        C1443l Qa6 = this.f2271ZO.mo8078Qa(3);
                                        if (Qa6 != null) {
                                            Qa5.mo8151c((short) C1435d.f2264wL).setValue(a3);
                                            a3 = m3671a(Qa6, a3);
                                        }
                                        C1443l Qa7 = this.f2271ZO.mo8078Qa(4);
                                        if (Qa7 != null) {
                                            Qa4.mo8151c((short) C1435d.f2258qL).setValue(a3);
                                            a3 = m3671a(Qa7, a3);
                                        }
                                        C1443l Qa8 = this.f2271ZO.mo8078Qa(1);
                                        if (Qa8 != null) {
                                            Qa4.mo8149ab(a3);
                                            a3 = m3671a(Qa8, a3);
                                        }
                                        if (this.f2271ZO.mo8092vk()) {
                                            Qa8.mo8151c((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT).setValue(a3);
                                            a3 += this.f2271ZO.mo8090tk().length;
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            long[] jArr2 = new long[this.f2271ZO.mo8091uk()];
                                            int i7 = a3;
                                            for (int i8 = 0; i8 < this.f2271ZO.mo8091uk(); i8++) {
                                                jArr2[i8] = (long) i7;
                                                i7 += this.f2271ZO.mo8079Ra(i8).length;
                                            }
                                            Qa8.mo8151c((short) C1435d.TAG_STRIP_OFFSETS).mo8130a(jArr2);
                                            a3 = i7;
                                        }
                                        int i9 = a3 + 8;
                                        if (i9 <= 65535) {
                                            C1445n nVar = new C1445n(this.out);
                                            nVar.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            nVar.writeShort(-31);
                                            nVar.writeShort((short) i9);
                                            nVar.writeInt(1165519206);
                                            nVar.writeShort(0);
                                            if (this.f2271ZO.mo8089sk() == ByteOrder.BIG_ENDIAN) {
                                                nVar.writeShort(19789);
                                            } else {
                                                nVar.writeShort(18761);
                                            }
                                            nVar.setByteOrder(this.f2271ZO.mo8089sk());
                                            nVar.writeShort(42);
                                            nVar.writeInt(8);
                                            m3673a(this.f2271ZO.mo8078Qa(0), nVar);
                                            m3673a(this.f2271ZO.mo8078Qa(2), nVar);
                                            C1443l Qa9 = this.f2271ZO.mo8078Qa(3);
                                            if (Qa9 != null) {
                                                m3673a(Qa9, nVar);
                                            }
                                            C1443l Qa10 = this.f2271ZO.mo8078Qa(4);
                                            if (Qa10 != null) {
                                                m3673a(Qa10, nVar);
                                            }
                                            if (this.f2271ZO.mo8078Qa(1) != null) {
                                                m3673a(this.f2271ZO.mo8078Qa(1), nVar);
                                            }
                                            if (this.f2271ZO.mo8092vk()) {
                                                nVar.write(this.f2271ZO.mo8090tk());
                                            } else if (this.f2271ZO.mo8093wk()) {
                                                for (int i10 = 0; i10 < this.f2271ZO.mo8091uk(); i10++) {
                                                    nVar.write(this.f2271ZO.mo8079Ra(i10));
                                                }
                                            }
                                            Iterator it = arrayList.iterator();
                                            while (it.hasNext()) {
                                                this.f2271ZO.mo8080a((C1442k) it.next());
                                            }
                                        } else {
                                            throw new IOException("Exif header is too large (>64Kb)");
                                        }
                                    } else {
                                        StringBuilder Pa7 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                        Pa7.append(C1435d.f2257pL);
                                        throw new IOException(Pa7.toString());
                                    }
                                }
                            } else {
                                throw new IOException("Not a valid jpeg image, cannot write exif");
                            }
                        } else {
                            return;
                        }
                    } else if (i5 != 1) {
                        continue;
                    } else {
                        int a4 = m3670a(4, bArr, i2, length);
                        i2 += a4;
                        length -= a4;
                        if (this.f2270Ew.position() == 2 && this.f2270Ew.getShort() == -39) {
                            this.out.write(this.f2270Ew.array(), 0, 2);
                            this.f2270Ew.rewind();
                        }
                        if (this.f2270Ew.position() >= 4) {
                            this.f2270Ew.rewind();
                            short s = this.f2270Ew.getShort();
                            if (s == -31) {
                                this.f2272_O = (this.f2270Ew.getShort() & 65535) - 2;
                                this.mState = 2;
                            } else if (!C1444m.m3717e(s)) {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.f2273aP = (this.f2270Ew.getShort() & 65535) - 2;
                            } else {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.mState = 2;
                            }
                            this.f2270Ew.rewind();
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
        if (length > 0) {
            this.out.write(bArr, i2, length);
        }
    }

    public void write(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        while (true) {
            if ((this.f2272_O > 0 || this.f2273aP > 0 || this.mState != 2) && length > 0) {
                int i2 = this.f2272_O;
                if (i2 > 0) {
                    if (length <= i2) {
                        i2 = length;
                    }
                    length -= i2;
                    this.f2272_O -= i2;
                    i += i2;
                }
                int i3 = this.f2273aP;
                if (i3 > 0) {
                    if (length <= i3) {
                        i3 = length;
                    }
                    this.out.write(bArr, i, i3);
                    length -= i3;
                    this.f2273aP -= i3;
                    i += i3;
                }
                if (length != 0) {
                    int i4 = this.mState;
                    if (i4 == 0) {
                        int a = m3670a(2, bArr, i, length);
                        i += a;
                        length -= a;
                        if (this.f2270Ew.position() >= 2) {
                            this.f2270Ew.rewind();
                            if (this.f2270Ew.getShort() == -40) {
                                this.out.write(this.f2270Ew.array(), 0, 2);
                                this.mState = 1;
                                this.f2270Ew.rewind();
                                C1433b bVar = this.f2271ZO;
                                if (bVar == null) {
                                    continue;
                                } else {
                                    ArrayList arrayList = new ArrayList();
                                    if (bVar.mo8088rk() != null) {
                                        for (C1442k kVar : bVar.mo8088rk()) {
                                            if (kVar.getValue() == null && !C1435d.m3658a(kVar.mo8123Gk())) {
                                                bVar.mo8084b(kVar.mo8123Gk(), kVar.mo8121Ek());
                                                arrayList.add(kVar);
                                            }
                                        }
                                    }
                                    C1443l Qa = this.f2271ZO.mo8078Qa(0);
                                    if (Qa == null) {
                                        Qa = new C1443l(0);
                                        this.f2271ZO.mo8082a(Qa);
                                    }
                                    C1442k Sa = this.mInterface.mo8095Sa(C1435d.f2257pL);
                                    if (Sa != null) {
                                        Qa.mo8150b(Sa);
                                        C1443l Qa2 = this.f2271ZO.mo8078Qa(2);
                                        if (Qa2 == null) {
                                            Qa2 = new C1443l(2);
                                            this.f2271ZO.mo8082a(Qa2);
                                        }
                                        if (this.f2271ZO.mo8078Qa(4) != null) {
                                            C1442k Sa2 = this.mInterface.mo8095Sa(C1435d.f2258qL);
                                            if (Sa2 != null) {
                                                Qa.mo8150b(Sa2);
                                            } else {
                                                StringBuilder Pa = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa.append(C1435d.f2258qL);
                                                throw new IOException(Pa.toString());
                                            }
                                        }
                                        if (this.f2271ZO.mo8078Qa(3) != null) {
                                            C1442k Sa3 = this.mInterface.mo8095Sa(C1435d.f2264wL);
                                            if (Sa3 != null) {
                                                Qa2.mo8150b(Sa3);
                                            } else {
                                                StringBuilder Pa2 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa2.append(C1435d.f2264wL);
                                                throw new IOException(Pa2.toString());
                                            }
                                        }
                                        C1443l Qa3 = this.f2271ZO.mo8078Qa(1);
                                        if (this.f2271ZO.mo8092vk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            C1442k Sa4 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            if (Sa4 != null) {
                                                Qa3.mo8150b(Sa4);
                                                C1442k Sa5 = this.mInterface.mo8095Sa(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                if (Sa5 != null) {
                                                    Sa5.setValue(this.f2271ZO.mo8090tk().length);
                                                    Qa3.mo8150b(Sa5);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                                    Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                                } else {
                                                    StringBuilder Pa3 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa3.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                    throw new IOException(Pa3.toString());
                                                }
                                            } else {
                                                StringBuilder Pa4 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa4.append(C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                throw new IOException(Pa4.toString());
                                            }
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            if (Qa3 == null) {
                                                Qa3 = new C1443l(1);
                                                this.f2271ZO.mo8082a(Qa3);
                                            }
                                            int uk = this.f2271ZO.mo8091uk();
                                            C1442k Sa6 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_OFFSETS);
                                            if (Sa6 != null) {
                                                C1442k Sa7 = this.mInterface.mo8095Sa(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                if (Sa7 != null) {
                                                    long[] jArr = new long[uk];
                                                    for (int i5 = 0; i5 < this.f2271ZO.mo8091uk(); i5++) {
                                                        jArr[i5] = (long) this.f2271ZO.mo8079Ra(i5).length;
                                                    }
                                                    Sa7.mo8130a(jArr);
                                                    Qa3.mo8150b(Sa6);
                                                    Qa3.mo8150b(Sa7);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                                    Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                                } else {
                                                    StringBuilder Pa5 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                    Pa5.append(C1435d.TAG_STRIP_BYTE_COUNTS);
                                                    throw new IOException(Pa5.toString());
                                                }
                                            } else {
                                                StringBuilder Pa6 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                                Pa6.append(C1435d.TAG_STRIP_OFFSETS);
                                                throw new IOException(Pa6.toString());
                                            }
                                        } else if (Qa3 != null) {
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_OFFSETS);
                                            Qa3.mo8152d((short) C1435d.TAG_STRIP_BYTE_COUNTS);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT);
                                            Qa3.mo8152d((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                        }
                                        C1443l Qa4 = this.f2271ZO.mo8078Qa(0);
                                        int a2 = m3671a(Qa4, 8);
                                        Qa4.mo8151c((short) C1435d.f2257pL).setValue(a2);
                                        C1443l Qa5 = this.f2271ZO.mo8078Qa(2);
                                        int a3 = m3671a(Qa5, a2);
                                        C1443l Qa6 = this.f2271ZO.mo8078Qa(3);
                                        if (Qa6 != null) {
                                            Qa5.mo8151c((short) C1435d.f2264wL).setValue(a3);
                                            a3 = m3671a(Qa6, a3);
                                        }
                                        C1443l Qa7 = this.f2271ZO.mo8078Qa(4);
                                        if (Qa7 != null) {
                                            Qa4.mo8151c((short) C1435d.f2258qL).setValue(a3);
                                            a3 = m3671a(Qa7, a3);
                                        }
                                        C1443l Qa8 = this.f2271ZO.mo8078Qa(1);
                                        if (Qa8 != null) {
                                            Qa4.mo8149ab(a3);
                                            a3 = m3671a(Qa8, a3);
                                        }
                                        if (this.f2271ZO.mo8092vk()) {
                                            Qa8.mo8151c((short) C1435d.TAG_JPEG_INTERCHANGE_FORMAT).setValue(a3);
                                            a3 += this.f2271ZO.mo8090tk().length;
                                        } else if (this.f2271ZO.mo8093wk()) {
                                            long[] jArr2 = new long[this.f2271ZO.mo8091uk()];
                                            int i6 = a3;
                                            for (int i7 = 0; i7 < this.f2271ZO.mo8091uk(); i7++) {
                                                jArr2[i7] = (long) i6;
                                                i6 += this.f2271ZO.mo8079Ra(i7).length;
                                            }
                                            Qa8.mo8151c((short) C1435d.TAG_STRIP_OFFSETS).mo8130a(jArr2);
                                            a3 = i6;
                                        }
                                        int i8 = a3 + 8;
                                        if (i8 <= 65535) {
                                            C1445n nVar = new C1445n(this.out);
                                            nVar.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            nVar.writeShort(-31);
                                            nVar.writeShort((short) i8);
                                            nVar.writeInt(1165519206);
                                            nVar.writeShort(0);
                                            if (this.f2271ZO.mo8089sk() == ByteOrder.BIG_ENDIAN) {
                                                nVar.writeShort(19789);
                                            } else {
                                                nVar.writeShort(18761);
                                            }
                                            nVar.setByteOrder(this.f2271ZO.mo8089sk());
                                            nVar.writeShort(42);
                                            nVar.writeInt(8);
                                            m3673a(this.f2271ZO.mo8078Qa(0), nVar);
                                            m3673a(this.f2271ZO.mo8078Qa(2), nVar);
                                            C1443l Qa9 = this.f2271ZO.mo8078Qa(3);
                                            if (Qa9 != null) {
                                                m3673a(Qa9, nVar);
                                            }
                                            C1443l Qa10 = this.f2271ZO.mo8078Qa(4);
                                            if (Qa10 != null) {
                                                m3673a(Qa10, nVar);
                                            }
                                            if (this.f2271ZO.mo8078Qa(1) != null) {
                                                m3673a(this.f2271ZO.mo8078Qa(1), nVar);
                                            }
                                            if (this.f2271ZO.mo8092vk()) {
                                                nVar.write(this.f2271ZO.mo8090tk());
                                            } else if (this.f2271ZO.mo8093wk()) {
                                                for (int i9 = 0; i9 < this.f2271ZO.mo8091uk(); i9++) {
                                                    nVar.write(this.f2271ZO.mo8079Ra(i9));
                                                }
                                            }
                                            Iterator it = arrayList.iterator();
                                            while (it.hasNext()) {
                                                this.f2271ZO.mo8080a((C1442k) it.next());
                                            }
                                        } else {
                                            throw new IOException("Exif header is too large (>64Kb)");
                                        }
                                    } else {
                                        StringBuilder Pa7 = C0632a.m1011Pa("No definition for crucial exif tag: ");
                                        Pa7.append(C1435d.f2257pL);
                                        throw new IOException(Pa7.toString());
                                    }
                                }
                            } else {
                                throw new IOException("Not a valid jpeg image, cannot write exif");
                            }
                        } else {
                            return;
                        }
                    } else if (i4 != 1) {
                        continue;
                    } else {
                        int a4 = m3670a(4, bArr, i, length);
                        i += a4;
                        length -= a4;
                        if (this.f2270Ew.position() == 2 && this.f2270Ew.getShort() == -39) {
                            this.out.write(this.f2270Ew.array(), 0, 2);
                            this.f2270Ew.rewind();
                        }
                        if (this.f2270Ew.position() >= 4) {
                            this.f2270Ew.rewind();
                            short s = this.f2270Ew.getShort();
                            if (s == -31) {
                                this.f2272_O = (this.f2270Ew.getShort() & 65535) - 2;
                                this.mState = 2;
                            } else if (!C1444m.m3717e(s)) {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.f2273aP = (this.f2270Ew.getShort() & 65535) - 2;
                            } else {
                                this.out.write(this.f2270Ew.array(), 0, 4);
                                this.mState = 2;
                            }
                            this.f2270Ew.rewind();
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
        if (length > 0) {
            this.out.write(bArr, i, length);
        }
    }
}

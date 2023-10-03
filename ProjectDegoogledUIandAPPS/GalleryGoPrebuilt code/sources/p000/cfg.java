package p000;

import android.util.LongSparseArray;
import com.google.android.apps.photosgo.face.cluster.ClusterManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: cfg */
/* compiled from: PG */
final /* synthetic */ class cfg implements icf {

    /* renamed from: a */
    private final cfj f4257a;

    /* renamed from: b */
    private final hso f4258b;

    /* renamed from: c */
    private final hso f4259c;

    public cfg(cfj cfj, hso hso, hso hso2) {
        this.f4257a = cfj;
        this.f4258b = hso;
        this.f4259c = hso2;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        Throwable th;
        Optional optional;
        int i;
        HashMap hashMap;
        ije ije;
        String str;
        int i2;
        HashMap hashMap2;
        iir iir;
        igl igl;
        boolean z;
        cfj cfj = this.f4257a;
        hso hso = this.f4258b;
        hso hso2 = this.f4259c;
        igl igl2 = (igl) obj;
        HashMap hashMap3 = new HashMap();
        hvs i3 = hso2.listIterator();
        while (i3.hasNext()) {
            cia cia = (cia) i3.next();
            hashMap3.put(cia.mo3109c(), cia);
        }
        LongSparseArray longSparseArray = new LongSparseArray(hso.size());
        hvs i4 = hso.listIterator();
        while (i4.hasNext()) {
            cff cff = (cff) i4.next();
            longSparseArray.put(((Long) cff.mo3091a().get()).longValue(), cff);
        }
        iir g = igk.f14112e.mo8793g();
        iit iit = (iit) igh.f14094c.mo8793g();
        iit iit2 = (iit) igg.f14083i.mo8793g();
        if (iit2.f14319c) {
            iit2.mo8751b();
            iit2.f14319c = false;
        }
        igg igg = (igg) iit2.f14318b;
        int i5 = igg.f14086b | 4194304;
        igg.f14086b = i5;
        igg.f14090f = -0.5f;
        igg.f14086b = i5 | 8388608;
        igg.f14091g = 0.65f;
        int i6 = igg.f14085a | 8192;
        igg.f14085a = i6;
        igg.f14089e = 0.5f;
        igg.f14085a = i6 | 1024;
        igg.f14088d = 50.0f;
        iir g2 = igf.f14078d.mo8793g();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        igf igf = (igf) g2.f14318b;
        int i7 = igf.f14080a | 256;
        igf.f14080a = i7;
        igf.f14081b = true;
        igf.f14080a = i7 | 512;
        igf.f14082c = 0.7f;
        if (iit2.f14319c) {
            iit2.mo8751b();
            iit2.f14319c = false;
        }
        igg igg2 = (igg) iit2.f14318b;
        igf igf2 = (igf) g2.mo8770g();
        igf2.getClass();
        igg2.f14092h = igf2;
        igg2.f14087c |= 8192;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        igh igh = (igh) iit.f14318b;
        igg igg3 = (igg) iit2.mo8770g();
        igg3.getClass();
        igh.f14097b = igg3;
        igh.f14096a |= 4;
        igh igh2 = (igh) iit.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        igk igk = (igk) g.f14318b;
        igh2.getClass();
        igk.f14115b = igh2;
        igk.f14114a |= 1;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = hso.size();
        int i8 = 0;
        while (i8 < size) {
            cff cff2 = (cff) hso.get(i8);
            ife.m12845a(cff2.mo3091a().isPresent(), (Object) "Face has not been persisted.");
            ife.m12849a(cff2.mo3128n(), "Face %s has not been embedded.", cff2.mo3091a().get());
            long c = cff2.mo3093c();
            Long valueOf = Long.valueOf(c);
            Optional map = Optional.ofNullable((igw) linkedHashMap.get(valueOf)).map(cif.f4454a);
            iit iit3 = (iit) igw.f14160d.mo8793g();
            int i9 = size;
            if (iit3.f14319c) {
                iit3.mo8751b();
                iit3.f14319c = false;
            }
            igw igw = (igw) iit3.f14318b;
            cfj cfj2 = cfj;
            igw.f14162a |= 1;
            igw.f14163b = c;
            iit iit4 = (iit) map.orElse(iit3);
            byte[] g3 = cff2.mo3098g();
            int d = cff2.mo3094d();
            int e = cff2.mo3095e();
            gbv gbv = (gbv) iix.m13603a((iix) gbv.f10871o, cff2.mo3097f());
            HashMap hashMap4 = hashMap3;
            LongSparseArray longSparseArray2 = longSparseArray;
            long longValue = ((Long) cff2.mo3091a().get()).longValue();
            hso hso3 = hso2;
            String format = String.format("photo_%d_face_%d", new Object[]{valueOf, cff2.mo3091a().get()});
            iir g4 = imn.f14522n.mo8793g();
            int length = g3.length;
            hso hso4 = hso;
            if (length != 148) {
                byte[] bArr = new byte[148];
                igl = igl2;
                iir = g;
                z = false;
                System.arraycopy(g3, 0, bArr, 20, length);
                g3 = bArr;
            } else {
                igl = igl2;
                iir = g;
                z = false;
            }
            ihw a = ihw.m13162a(g3);
            if (g4.f14319c) {
                g4.mo8751b();
                g4.f14319c = z;
            }
            imn imn = (imn) g4.f14318b;
            a.getClass();
            imn.f14524a |= 536870912;
            imn.f14536m = a;
            iir g5 = iml.f14512d.mo8793g();
            iir g6 = imm.f14517d.mo8793g();
            gbs gbs = gbv.f10874b;
            if (gbs == null) {
                gbs = gbs.f10849f;
            }
            int i10 = (int) gbs.f10852b;
            if (g6.f14319c) {
                g6.mo8751b();
                g6.f14319c = false;
            }
            imm imm = (imm) g6.f14318b;
            imm.f14519a |= 1;
            imm.f14520b = i10;
            gbs gbs2 = gbv.f10874b;
            if (gbs2 == null) {
                gbs2 = gbs.f10849f;
            }
            int i11 = (int) gbs2.f10853c;
            if (g6.f14319c) {
                g6.mo8751b();
                g6.f14319c = false;
            }
            imm imm2 = (imm) g6.f14318b;
            imm2.f14519a |= 2;
            imm2.f14521c = i11;
            if (g5.f14319c) {
                g5.mo8751b();
                g5.f14319c = false;
            }
            iml iml = (iml) g5.f14318b;
            imm imm3 = (imm) g6.mo8770g();
            imm3.getClass();
            iml.f14515b = imm3;
            iml.f14514a |= 1;
            iir g7 = imm.f14517d.mo8793g();
            gbs gbs3 = gbv.f10874b;
            if (gbs3 == null) {
                gbs3 = gbs.f10849f;
            }
            int i12 = (int) gbs3.f10854d;
            if (g7.f14319c) {
                g7.mo8751b();
                g7.f14319c = false;
            }
            imm imm4 = (imm) g7.f14318b;
            imm4.f14519a |= 1;
            imm4.f14520b = i12;
            gbs gbs4 = gbv.f10874b;
            if (gbs4 == null) {
                gbs4 = gbs.f10849f;
            }
            int i13 = (int) gbs4.f10855e;
            if (g7.f14319c) {
                g7.mo8751b();
                g7.f14319c = false;
            }
            imm imm5 = (imm) g7.f14318b;
            imm5.f14519a |= 2;
            imm5.f14521c = i13;
            if (g5.f14319c) {
                g5.mo8751b();
                g5.f14319c = false;
            }
            iml iml2 = (iml) g5.f14318b;
            imm imm6 = (imm) g7.mo8770g();
            imm6.getClass();
            iml2.f14516c = imm6;
            iml2.f14514a |= 2;
            if (g4.f14319c) {
                g4.mo8751b();
                g4.f14319c = false;
            }
            imn imn2 = (imn) g4.f14318b;
            iml iml3 = (iml) g5.mo8770g();
            iml3.getClass();
            imn2.f14525b = iml3;
            imn2.f14524a |= 1;
            if (g4.f14319c) {
                g4.mo8751b();
                g4.f14319c = false;
            }
            imn imn3 = (imn) g4.f14318b;
            int i14 = imn3.f14524a | 8;
            imn3.f14524a = i14;
            imn3.f14526c = d;
            int i15 = i14 | 16;
            imn3.f14524a = i15;
            imn3.f14527d = e;
            float f = gbv.f10876d;
            int i16 = i15 | 512;
            imn3.f14524a = i16;
            imn3.f14531h = f;
            float f2 = gbv.f10878f;
            int i17 = i16 | 64;
            imn3.f14524a = i17;
            imn3.f14529f = f2;
            float f3 = gbv.f10879g;
            int i18 = i17 | 128;
            imn3.f14524a = i18;
            imn3.f14530g = f3;
            float f4 = gbv.f10877e;
            int i19 = i18 | 32;
            imn3.f14524a = i19;
            imn3.f14528e = f4;
            if ((gbv.f10873a & 65536) != 0) {
                float f5 = gbv.f10881i;
                i19 |= 2097152;
                imn3.f14524a = i19;
                imn3.f14532i = f5;
            }
            if ((gbv.f10873a & 131072) != 0) {
                float f6 = gbv.f10882j;
                i19 |= 4194304;
                imn3.f14524a = i19;
                imn3.f14533j = f6;
            }
            if ((gbv.f10873a & 262144) != 0) {
                float f7 = gbv.f10883k;
                i19 |= 8388608;
                imn3.f14524a = i19;
                imn3.f14534k = f7;
            }
            if ((gbv.f10873a & 4194304) != 0) {
                float f8 = gbv.f10884l;
                imn3.f14524a = i19 | 134217728;
                imn3.f14535l = f8;
            }
            iir g8 = igx.f14166e.mo8793g();
            if (g8.f14319c) {
                g8.mo8751b();
                g8.f14319c = false;
            }
            igx igx = (igx) g8.f14318b;
            int i20 = igx.f14168a | 1;
            igx.f14168a = i20;
            igx.f14169b = longValue;
            format.getClass();
            igx.f14168a = i20 | 2;
            igx.f14170c = format;
            imn imn4 = (imn) g4.mo8770g();
            imn4.getClass();
            igx.f14171d = imn4;
            igx.f14168a |= 4;
            igx igx2 = (igx) g8.mo8770g();
            if (iit4.f14319c) {
                iit4.mo8751b();
                iit4.f14319c = false;
            }
            igw igw2 = (igw) iit4.f14318b;
            igx2.getClass();
            if (!igw2.f14164c.mo8521a()) {
                igw2.f14164c = iix.m13608a(igw2.f14164c);
            }
            igw2.f14164c.add(igx2);
            linkedHashMap.put(valueOf, (igw) iit4.mo8770g());
            i8++;
            size = i9;
            cfj = cfj2;
            hashMap3 = hashMap4;
            longSparseArray = longSparseArray2;
            hso2 = hso3;
            hso = hso4;
            igl2 = igl;
            g = iir;
        }
        cfj cfj3 = cfj;
        hso hso5 = hso;
        hso hso6 = hso2;
        igl igl3 = igl2;
        HashMap hashMap5 = hashMap3;
        LongSparseArray longSparseArray3 = longSparseArray;
        iir iir2 = g;
        iir g9 = igv.f14156b.mo8793g();
        Collection values = linkedHashMap.values();
        if (g9.f14319c) {
            g9.mo8751b();
            g9.f14319c = false;
        }
        igv igv = (igv) g9.f14318b;
        if (!igv.f14158a.mo8521a()) {
            igv.f14158a = iix.m13608a(igv.f14158a);
        }
        igz.m12986a((Iterable) values, (List) igv.f14158a);
        igv igv2 = (igv) g9.mo8770g();
        iir iir3 = iir2;
        if (iir3.f14319c) {
            iir3.mo8751b();
            iir3.f14319c = false;
        }
        igk igk2 = (igk) iir3.f14318b;
        igv2.getClass();
        igk2.f14117d = igv2;
        int i21 = igk2.f14114a | 4;
        igk2.f14114a = i21;
        igl3.getClass();
        igk2.f14116c = igl3;
        igk2.f14114a = i21 | 2;
        igk igk3 = (igk) iir3.mo8770g();
        hlj a2 = hnb.m11765a("Native Cluster Faces");
        try {
            byte[] nativeClusterFaces = ClusterManager.nativeClusterFaces(igk3.mo8512ag());
            ikn ikn = (ikn) igl.f14119d.mo8790b(7);
            if (nativeClusterFaces == null) {
                optional = Optional.empty();
            } else {
                optional = Optional.m16285of((ikf) ikn.mo8519a(nativeClusterFaces));
            }
            if (a2 != null) {
                a2.close();
            }
            if (!optional.isPresent()) {
                cwn.m5510a("FaceClusterer: clusterFaces gave no results for %d new faces and %d existing persons.", Integer.valueOf(hso5.size()), Integer.valueOf(hso6.size()));
                hsj j = hso.m12048j();
                hvs i22 = hso5.listIterator();
                while (i22.hasNext()) {
                    cfe m = ((cff) i22.next()).mo3105m();
                    m.mo3122a(true);
                    j.mo7908c(m.mo3117a());
                }
                return ife.m12820a((Object) new cfi(j.mo7905a(), hso6));
            }
            igl igl4 = (igl) optional.get();
            hsj j2 = hso.m12048j();
            HashSet hashSet = new HashSet();
            LongSparseArray longSparseArray4 = new LongSparseArray();
            igj igj = igl4.f14122b;
            if (igj == null) {
                igj = igj.f14107d;
            }
            ije ije2 = igj.f14111c;
            int size2 = ije2.size();
            int i23 = 0;
            while (i23 < size2) {
                igi igi = (igi) ije2.get(i23);
                if ((igi.f14101a & 2) != 0) {
                    str = igi.f14103c;
                } else {
                    str = UUID.randomUUID().toString();
                }
                longSparseArray4.put((long) igi.f14102b, str);
                ije ije3 = igi.f14106f;
                int size3 = ije3.size();
                int i24 = 0;
                while (true) {
                    i2 = i23 + 1;
                    if (i24 >= size3) {
                        break;
                    }
                    LongSparseArray longSparseArray5 = longSparseArray3;
                    cff cff3 = (cff) longSparseArray5.get(((igm) ije3.get(i24)).f14128b);
                    if (cff3 == null) {
                        cwn.m5514b("FaceClusterer: No face found matching given regionId. Skipped.", new Object[0]);
                        hashMap2 = hashMap5;
                    } else {
                        hashSet.add((Long) cff3.mo3091a().get());
                        cfe m2 = cff3.mo3105m();
                        m2.mo3121a(str);
                        m2.mo3122a(true);
                        j2.mo7908c(m2.mo3117a());
                        hashMap2 = hashMap5;
                        if (!hashMap2.containsKey(str)) {
                            chz h = cia.m4343h();
                            h.mo3148a(str);
                            hashMap2.put(str, h.mo3146a());
                        }
                    }
                    i24++;
                    hashMap5 = hashMap2;
                    longSparseArray3 = longSparseArray5;
                }
                HashMap hashMap6 = hashMap5;
                LongSparseArray longSparseArray6 = longSparseArray3;
                i23 = i2;
            }
            HashMap hashMap7 = hashMap5;
            LongSparseArray longSparseArray7 = longSparseArray3;
            for (int i25 = 0; i25 < longSparseArray7.size(); i25++) {
                long keyAt = longSparseArray7.keyAt(i25);
                if (hashSet.add(Long.valueOf(keyAt))) {
                    cfe m3 = ((cff) longSparseArray7.get(keyAt)).mo3105m();
                    m3.mo3122a(true);
                    j2.mo7908c(m3.mo3117a());
                }
            }
            cie cie = cfj3.f4264a;
            HashSet hashSet2 = new HashSet();
            iir g10 = igo.f14138b.mo8793g();
            igo igo = igl4.f14123c;
            if (igo == null) {
                igo = igo.f14138b;
            }
            ije ije4 = igo.f14140a;
            int size4 = ije4.size();
            int i26 = 0;
            while (i26 < size4) {
                ign ign = (ign) ije4.get(i26);
                iit iit5 = (iit) ign.f14129g.mo8793g();
                String valueOf2 = String.valueOf(ign.f14133c);
                if (iit5.f14319c) {
                    iit5.mo8751b();
                    iit5.f14319c = false;
                }
                ign ign2 = (ign) iit5.f14318b;
                valueOf2.getClass();
                int i27 = ign2.f14131a | 1;
                ign2.f14131a = i27;
                ign2.f14132b = valueOf2;
                if ((ign.f14131a & 4) == 0) {
                    String str2 = (String) longSparseArray4.get((long) ign.f14135e, "");
                    if (str2.isEmpty()) {
                        cwn.m5510a("ClusterProtoFactory: Skipping person; no personClusterMediaKey matching given clusterId[%d].", Integer.valueOf(ign.f14135e));
                        ije = ije4;
                        i = size4;
                        hashMap = hashMap7;
                        i26++;
                        size4 = i;
                        ije4 = ije;
                        hashMap7 = hashMap;
                    } else {
                        if (iit5.f14319c) {
                            iit5.mo8751b();
                            iit5.f14319c = false;
                        }
                        ign ign3 = (ign) iit5.f14318b;
                        str2.getClass();
                        ign3.f14131a |= 4;
                        ign3.f14134d = str2;
                        hashSet2.add(str2);
                    }
                } else {
                    String str3 = ign.f14134d;
                    str3.getClass();
                    ign2.f14131a = i27 | 4;
                    ign2.f14134d = str3;
                    hashSet2.add(ign.f14134d);
                }
                ije ije5 = ign.f14136f;
                int size5 = ije5.size();
                int i28 = 0;
                while (i28 < size5) {
                    igr igr = (igr) ije5.get(i28);
                    iir iir4 = (iir) igr.mo8790b(5);
                    iir4.mo8503a((iix) igr);
                    ije ije6 = ije4;
                    int i29 = size4;
                    String valueOf3 = String.valueOf(igr.f14148c);
                    if (iir4.f14319c) {
                        iir4.mo8751b();
                        iir4.f14319c = false;
                    }
                    igr igr2 = (igr) iir4.f14318b;
                    igr igr3 = igr.f14144e;
                    valueOf3.getClass();
                    int i30 = igr2.f14146a | 1;
                    igr2.f14146a = i30;
                    igr2.f14147b = valueOf3;
                    igr2.f14146a = i30 & -3;
                    int i31 = size5;
                    HashMap hashMap8 = hashMap7;
                    igr2.f14148c = 0;
                    if (iit5.f14319c) {
                        iit5.mo8751b();
                        iit5.f14319c = false;
                    }
                    ign ign4 = (ign) iit5.f14318b;
                    igr igr4 = (igr) iir4.mo8770g();
                    igr4.getClass();
                    if (!ign4.f14136f.mo8521a()) {
                        ign4.f14136f = iix.m13608a(ign4.f14136f);
                    }
                    ign4.f14136f.add(igr4);
                    i28++;
                    size4 = i29;
                    size5 = i31;
                    ije4 = ije6;
                    hashMap7 = hashMap8;
                }
                ije = ije4;
                i = size4;
                hashMap = hashMap7;
                if (g10.f14319c) {
                    g10.mo8751b();
                    g10.f14319c = false;
                }
                igo igo2 = (igo) g10.f14318b;
                ign ign5 = (ign) iit5.mo8770g();
                ign5.getClass();
                if (!igo2.f14140a.mo8521a()) {
                    igo2.f14140a = iix.m13608a(igo2.f14140a);
                }
                igo2.f14140a.add(ign5);
                i26++;
                size4 = i;
                ije4 = ije;
                hashMap7 = hashMap;
            }
            HashMap hashMap9 = hashMap7;
            igo igo3 = igl4.f14123c;
            if (igo3 == null) {
                igo3 = igo.f14138b;
            }
            igo3.f14140a.size();
            igj igj2 = igl4.f14122b;
            if (igj2 == null) {
                igj2 = igj.f14107d;
            }
            igj2.f14111c.size();
            iir g11 = igj.f14107d.mo8793g();
            igj igj3 = igl4.f14122b;
            if (igj3 == null) {
                igj3 = igj.f14107d;
            }
            int a3 = ihg.m13013a(igj3.f14110b);
            if (a3 == 0) {
                a3 = 1;
            }
            if (g11.f14319c) {
                g11.mo8751b();
                g11.f14319c = false;
            }
            igj igj4 = (igj) g11.f14318b;
            igj4.f14110b = a3 - 1;
            igj4.f14109a |= 1;
            igj igj5 = igl4.f14122b;
            if (igj5 == null) {
                igj5 = igj.f14107d;
            }
            ije ije7 = igj5.f14111c;
            int size6 = ije7.size();
            for (int i32 = 0; i32 < size6; i32++) {
                igi igi2 = (igi) ije7.get(i32);
                String str4 = (String) longSparseArray4.get((long) igi2.f14102b);
                if (hashSet2.contains(str4)) {
                    iir g12 = igi.f14099g.mo8793g();
                    String str5 = (String) longSparseArray4.get((long) igi2.f14102b);
                    if (g12.f14319c) {
                        g12.mo8751b();
                        g12.f14319c = false;
                    }
                    igi igi3 = (igi) g12.f14318b;
                    str5.getClass();
                    igi3.f14101a |= 2;
                    igi3.f14103c = str5;
                    int b = ihg.m13044b(igi2.f14104d);
                    if (b == 0) {
                        b = 1;
                    }
                    if (g12.f14319c) {
                        g12.mo8751b();
                        g12.f14319c = false;
                    }
                    igi igi4 = (igi) g12.f14318b;
                    igi4.f14104d = b - 1;
                    int i33 = igi4.f14101a | 8;
                    igi4.f14101a = i33;
                    double d2 = igi2.f14105e;
                    igi4.f14101a = i33 | 64;
                    igi4.f14105e = d2;
                    if (g11.f14319c) {
                        g11.mo8751b();
                        g11.f14319c = false;
                    }
                    igj igj6 = (igj) g11.f14318b;
                    igi igi5 = (igi) g12.mo8770g();
                    igi5.getClass();
                    if (!igj6.f14111c.mo8521a()) {
                        igj6.f14111c = iix.m13608a(igj6.f14111c);
                    }
                    igj6.f14111c.add(igi5);
                } else {
                    new Object[1][0] = str4;
                }
            }
            iir g13 = cig.f4455d.mo8793g();
            if (g13.f14319c) {
                g13.mo8751b();
                g13.f14319c = false;
            }
            cig cig = (cig) g13.f14318b;
            igo igo4 = (igo) g10.mo8770g();
            igo4.getClass();
            cig.f4459c = igo4;
            cig.f4457a |= 2;
            if (g13.f14319c) {
                g13.mo8751b();
                g13.f14319c = false;
            }
            cig cig2 = (cig) g13.f14318b;
            igj igj7 = (igj) g11.mo8770g();
            igj7.getClass();
            cig2.f4458b = igj7;
            cig2.f4457a |= 1;
            return gte.m10770a(cie.f4453a.mo6360a(new cib((cig) g13.mo8770g()), idh.f13918a), (hpr) new cfh(j2, hashMap9), (Executor) idh.f13918a);
        } catch (Throwable th2) {
            ifn.m12935a(th, th2);
        }
        throw th;
    }
}

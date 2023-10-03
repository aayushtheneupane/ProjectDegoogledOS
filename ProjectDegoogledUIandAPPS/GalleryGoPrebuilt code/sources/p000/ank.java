package p000;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: ank */
/* compiled from: PG */
public final class ank {

    /* renamed from: a */
    public static final anl f1195a = new aog();

    /* renamed from: b */
    private static anm f1196b = null;

    /* renamed from: a */
    public static ani m1170a() {
        return new aoa();
    }

    /* renamed from: b */
    private static synchronized anm m1173b() {
        anm anm;
        synchronized (ank.class) {
            if (f1196b == null) {
                try {
                    f1196b = new anj();
                } catch (Throwable th) {
                    System.out.println(th);
                }
            }
            anm = f1196b;
        }
        return anm;
    }

    /* renamed from: a */
    public static ani m1171a(byte[] bArr) {
        return aob.m1234a((Object) bArr);
    }

    /* renamed from: a */
    public static byte[] m1172a(ani ani, aoq aoq) {
        int i;
        char c;
        if (ani instanceof aoa) {
            aoa aoa = (aoa) ani;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
            if (aoq.mo1359a(4096)) {
                aoa.f1242a.mo1329j();
            }
            aoh aoh = new aoh();
            try {
                aoh.f1267b = new anp(byteArrayOutputStream);
                aoh.f1268c = new OutputStreamWriter(aoh.f1267b, aoq.mo1392h());
                aoh.f1266a = aoa;
                aoh.f1269d = aoq;
                aoh.f1271f = aoq.f1283b;
                aoh.f1268c = new OutputStreamWriter(aoh.f1267b, aoq.mo1392h());
                if (aoh.f1269d.mo1390f() || aoh.f1269d.mo1391g()) {
                    aoh.f1270e = 2;
                }
                if (aoh.f1269d.mo1389e()) {
                    if (aoh.f1269d.mo1385a() || aoh.f1269d.mo1387c()) {
                        throw new ang("Inconsistent options for exact size serialize", 103);
                    } else if ((aoh.f1269d.f1283b & (aoh.f1270e - 1)) != 0) {
                        throw new ang("Exact size must be a multiple of the Unicode element", 103);
                    }
                } else if (aoh.f1269d.mo1386b()) {
                    if (!aoh.f1269d.mo1385a() && !aoh.f1269d.mo1387c()) {
                        aoh.f1271f = 0;
                    } else {
                        throw new ang("Inconsistent options for read-only packet", 103);
                    }
                } else if (!aoh.f1269d.mo1385a()) {
                    if (aoh.f1271f == 0) {
                        aoh.f1271f = aoh.f1270e << 11;
                    }
                    if (aoh.f1269d.mo1387c() && !aoh.f1266a.mo1264c("http://ns.adobe.com/xap/1.0/", "Thumbnails")) {
                        aoh.f1271f += aoh.f1270e * 10000;
                    }
                } else if (!aoh.f1269d.mo1387c()) {
                    aoh.f1271f = 0;
                } else {
                    throw new ang("Inconsistent options for non-packet serialize", 103);
                }
                if (!aoh.f1269d.mo1385a()) {
                    aoh.mo1337a(0);
                    aoh.mo1340a("<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>");
                    aoh.mo1342b();
                }
                aoh.mo1337a(0);
                aoh.mo1340a("<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"");
                aoh.mo1340a(m1173b().mo1268a());
                aoh.mo1340a("\">");
                aoh.mo1342b();
                aoh.mo1337a(1);
                aoh.mo1340a("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">");
                aoh.mo1342b();
                if (aoh.f1269d.mo1359a(64)) {
                    aoh.mo1337a(2);
                    aoh.mo1340a("<rdf:Description rdf:about=");
                    aoh.mo1336a();
                    HashSet hashSet = new HashSet();
                    hashSet.add("xml");
                    hashSet.add("rdf");
                    Iterator f = aoh.f1266a.f1242a.mo1325f();
                    while (f.hasNext()) {
                        aoh.mo1338a((aod) f.next(), (Set) hashSet);
                    }
                    Iterator f2 = aoh.f1266a.f1242a.mo1325f();
                    boolean z = true;
                    while (f2.hasNext()) {
                        z &= aoh.mo1341a((aod) f2.next(), 3);
                    }
                    if (!z) {
                        aoh.mo1343b(62);
                        aoh.mo1342b();
                        Iterator f3 = aoh.f1266a.f1242a.mo1325f();
                        while (f3.hasNext()) {
                            aoh.mo1344b((aod) f3.next(), 3);
                        }
                        aoh.mo1337a(2);
                        aoh.mo1340a("</rdf:Description>");
                        aoh.mo1342b();
                    } else {
                        aoh.mo1340a("/>");
                        aoh.mo1342b();
                    }
                } else if (aoh.f1266a.f1242a.mo1318c() <= 0) {
                    aoh.mo1337a(2);
                    aoh.mo1340a("<rdf:Description rdf:about=");
                    aoh.mo1336a();
                    aoh.mo1340a("/>");
                    aoh.mo1342b();
                } else {
                    Iterator f4 = aoh.f1266a.f1242a.mo1325f();
                    while (f4.hasNext()) {
                        aod aod = (aod) f4.next();
                        aoh.mo1337a(2);
                        aoh.mo1340a("<rdf:Description rdf:about=");
                        aoh.mo1336a();
                        HashSet hashSet2 = new HashSet();
                        hashSet2.add("xml");
                        hashSet2.add("rdf");
                        aoh.mo1338a(aod, (Set) hashSet2);
                        aoh.mo1343b(62);
                        aoh.mo1342b();
                        Iterator f5 = aod.mo1325f();
                        while (f5.hasNext()) {
                            aoh.mo1339a((aod) f5.next(), false, 3);
                        }
                        aoh.mo1337a(2);
                        aoh.mo1340a("</rdf:Description>");
                        aoh.mo1342b();
                    }
                }
                aoh.mo1337a(1);
                aoh.mo1340a("</rdf:RDF>");
                aoh.mo1342b();
                aoh.mo1337a(0);
                aoh.mo1340a("</x:xmpmeta>");
                aoh.mo1342b();
                String str = "";
                if (!aoh.f1269d.mo1385a()) {
                    String valueOf = String.valueOf("<?xpacket end=\"");
                    if (!aoh.f1269d.mo1386b()) {
                        c = 'w';
                    } else {
                        c = 'r';
                    }
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1);
                    sb.append(valueOf);
                    sb.append(c);
                    str = String.valueOf(sb.toString()).concat("\"?>");
                }
                aoh.f1268c.flush();
                int length = str.length();
                if (aoh.f1269d.mo1389e()) {
                    int i2 = aoh.f1267b.f1202a + (length * aoh.f1270e);
                    int i3 = aoh.f1271f;
                    if (i2 <= i3) {
                        aoh.f1271f = i3 - i2;
                    } else {
                        throw new ang("Can't fit into specified packet size", 107);
                    }
                }
                aoh.f1271f /= aoh.f1270e;
                int length2 = aoh.f1269d.f1284c.length();
                int i4 = aoh.f1271f;
                if (i4 >= length2) {
                    aoh.f1271f = i4 - length2;
                    while (true) {
                        i = aoh.f1271f;
                        int i5 = length2 + 100;
                        if (i < i5) {
                            break;
                        }
                        aoh.mo1345c(100);
                        aoh.mo1342b();
                        aoh.f1271f -= i5;
                    }
                    aoh.mo1345c(i);
                    aoh.mo1342b();
                } else {
                    aoh.mo1345c(i4);
                }
                aoh.mo1340a(str);
                aoh.f1268c.flush();
                aoh.f1267b.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new ang("Error writing to the OutputStream", 0);
            }
        } else {
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        }
    }
}

package p000;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* renamed from: aob */
/* compiled from: PG */
public final class aob {

    /* renamed from: a */
    private static final Object f1243a = new Object();

    /* renamed from: b */
    private static final DocumentBuilderFactory f1244b;

    static {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setIgnoringComments(true);
        try {
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        } catch (Exception e) {
        }
        f1244b = newInstance;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] m1236a(org.w3c.dom.Node r7, boolean r8, java.lang.Object[] r9) {
        /*
            org.w3c.dom.NodeList r7 = r7.getChildNodes()
            r0 = 0
            r1 = 0
        L_0x0006:
            int r2 = r7.getLength()
            if (r1 >= r2) goto L_0x0085
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 7
            if (r3 != r4) goto L_0x002b
            r3 = r2
            org.w3c.dom.ProcessingInstruction r3 = (org.w3c.dom.ProcessingInstruction) r3
            java.lang.String r5 = r3.getTarget()
            java.lang.String r6 = "xpacket"
            if (r5 == r6) goto L_0x0023
            goto L_0x002b
        L_0x0023:
            r2 = 2
            java.lang.String r3 = r3.getData()
            r9[r2] = r3
            goto L_0x0082
        L_0x002b:
            short r3 = r2.getNodeType()
            r5 = 3
            if (r3 == r5) goto L_0x0082
            short r3 = r2.getNodeType()
            if (r3 == r4) goto L_0x0082
            java.lang.String r3 = r2.getNamespaceURI()
            java.lang.String r4 = r2.getLocalName()
            java.lang.String r5 = "xmpmeta"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0050
            java.lang.String r5 = "xapmeta"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0059
        L_0x0050:
            java.lang.String r5 = "adobe:ns:meta/"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x007c
        L_0x0059:
            if (r8 != 0) goto L_0x0074
            java.lang.String r5 = "RDF"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0074
            java.lang.String r4 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x006c
            goto L_0x0074
        L_0x006c:
            r9[r0] = r2
            java.lang.Object r7 = f1243a
            r8 = 1
            r9[r8] = r7
            return r9
        L_0x0074:
            java.lang.Object[] r2 = m1236a(r2, r8, r9)
            if (r2 != 0) goto L_0x007b
            goto L_0x0082
        L_0x007b:
            return r2
        L_0x007c:
            java.lang.Object[] r7 = m1236a(r2, r0, r9)
            return r7
        L_0x0082:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0085:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aob.m1236a(org.w3c.dom.Node, boolean, java.lang.Object[]):java.lang.Object[]");
    }

    /* renamed from: a */
    public static ani m1234a(Object obj) {
        Document document;
        Object obj2 = obj;
        ckx.m4482a(obj);
        aoo aoo = new aoo();
        if (obj2 instanceof byte[]) {
            ano ano = new ano((byte[]) obj2);
            try {
                document = m1235a(new InputSource(ano.mo1274a()));
            } catch (ang e) {
                ang ang = e;
                int i = ang.f1194a;
                if (i == 201 || i == 204) {
                    if (aoo.mo1359a(16) && "UTF-8".equals(ano.mo1278b())) {
                        byte[] bArr = new byte[8];
                        ano ano2 = new ano((ano.f1200b << 2) / 3);
                        int i2 = 0;
                        char c = 0;
                        int i3 = 0;
                        int i4 = 0;
                        while (true) {
                            int i5 = ano.f1200b;
                            if (i2 >= i5) {
                                if (c == 11) {
                                    for (int i6 = 0; i6 < i4; i6++) {
                                        ano2.mo1276a(cjk.m4397a(bArr[i6]));
                                    }
                                }
                                ano = ano2;
                            } else if (i2 < i5) {
                                byte b = ano.f1199a[i2] & 255;
                                if (c != 11) {
                                    if (b < Byte.MAX_VALUE) {
                                        ano2.mo1275a(ano2.f1200b + 1);
                                        byte[] bArr2 = ano2.f1199a;
                                        int i7 = ano2.f1200b;
                                        ano2.f1200b = i7 + 1;
                                        bArr2[i7] = (byte) b;
                                    } else if (b < 192) {
                                        ano2.mo1276a(cjk.m4397a((byte) b));
                                    } else {
                                        int i8 = b;
                                        i3 = -1;
                                        while (i3 < 8 && (i8 & 128) == 128) {
                                            i3++;
                                            i8 += i8;
                                        }
                                        bArr[i4] = (byte) b;
                                        i4++;
                                        c = 11;
                                    }
                                } else if (i3 > 0 && (b & 192) == 128) {
                                    int i9 = i4 + 1;
                                    bArr[i4] = (byte) b;
                                    i3--;
                                    if (i3 == 0) {
                                        ano2.mo1277a(bArr, i9);
                                        c = 0;
                                        i4 = 0;
                                    } else {
                                        i4 = i9;
                                    }
                                } else {
                                    ano2.mo1276a(cjk.m4397a(bArr[0]));
                                    i2 -= i4;
                                    c = 0;
                                    i4 = 0;
                                }
                                i2++;
                            } else {
                                throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
                            }
                        }
                    }
                    if (aoo.mo1365a()) {
                        try {
                            document = m1235a(new InputSource(new anq(new InputStreamReader(ano.mo1274a(), ano.mo1278b()))));
                        } catch (UnsupportedEncodingException e2) {
                            throw new ang("Unsupported Encoding", 9, ang);
                        }
                    } else {
                        document = m1235a(new InputSource(ano.mo1274a()));
                    }
                } else {
                    throw ang;
                }
            }
        } else {
            String str = (String) obj2;
            try {
                document = m1235a(new InputSource(new StringReader(str)));
            } catch (ang e3) {
                ang ang2 = e3;
                if (ang2.f1194a != 201 || !aoo.mo1365a()) {
                    throw ang2;
                }
                document = m1235a(new InputSource(new anq(new StringReader(str))));
            }
        }
        Object[] a = m1236a(document, aoo.mo1359a(1), new Object[3]);
        if (a == null || a[1] != f1243a) {
            return new aoa();
        }
        Node node = (Node) a[0];
        aoa aoa = new aoa();
        if (node.hasAttributes()) {
            aod aod = aoa.f1242a;
            for (int i10 = 0; i10 < node.getChildNodes().getLength(); i10++) {
                Node item = node.getChildNodes().item(i10);
                if (!C0637xj.m15905a(item)) {
                    C0637xj.m15899a(aoa, aod, item, true);
                }
            }
            String str2 = (String) a[2];
            if (!aoo.mo1359a(32)) {
                return aoe.m1262a(aoa, aoo);
            }
            return aoa;
        }
        throw new ang("Invalid attributes of rdf:RDF element", 202);
    }

    /* renamed from: a */
    private static Document m1235a(InputSource inputSource) {
        try {
            DocumentBuilder newDocumentBuilder = f1244b.newDocumentBuilder();
            newDocumentBuilder.setErrorHandler((ErrorHandler) null);
            return newDocumentBuilder.parse(inputSource);
        } catch (SAXException e) {
            throw new ang("XML parsing failure", 201, e);
        } catch (ParserConfigurationException e2) {
            throw new ang("XML Parser not correctly configured", 0, e2);
        } catch (IOException e3) {
            throw new ang("Error reading the XML-file", 204, e3);
        }
    }
}

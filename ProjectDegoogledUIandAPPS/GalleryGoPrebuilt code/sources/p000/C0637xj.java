package p000;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EdgeEffect;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* renamed from: xj */
/* compiled from: PG */
public final class C0637xj {
    /* renamed from: a */
    public static int m15889a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 0 : 4;
        }
        return 3;
    }

    /* renamed from: a */
    public static final EdgeEffect m15892a(RecyclerView recyclerView) {
        return new EdgeEffect(recyclerView.getContext());
    }

    /* renamed from: a */
    public static void m15898a(View view, CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        view.setTooltipText(charSequence);
    }

    /* renamed from: a */
    private static aod m15893a(aoa aoa, aod aod, Node node, String str, boolean z) {
        String str2;
        anl anl = ank.f1195a;
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null) {
            if ("http://purl.org/dc/1.1/".equals(namespaceURI)) {
                namespaceURI = "http://purl.org/dc/elements/1.1/";
            }
            String a = anl.mo1270a(namespaceURI);
            if (a == null) {
                if (node.getPrefix() != null) {
                    str2 = node.getPrefix();
                } else {
                    str2 = "_dflt";
                }
                a = anl.mo1271a(namespaceURI, str2);
            }
            String valueOf = String.valueOf(a);
            String valueOf2 = String.valueOf(node.getLocalName());
            String str3 = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
            aop aop = new aop();
            boolean z2 = false;
            if (z) {
                aod = m15895a(aoa.f1242a, namespaceURI, "_dflt", true);
                aod.f1251f = false;
                if (anl.mo1273c(str3) != null) {
                    aoa.f1242a.f1252g = true;
                    aod.f1252g = true;
                    z2 = true;
                }
            }
            boolean equals = "rdf:li".equals(str3);
            boolean equals2 = "rdf:value".equals(str3);
            aod aod2 = new aod(str3, str, aop);
            aod2.f1253h = z2;
            if (!equals2) {
                aod.mo1313a(aod2);
            } else {
                aod.mo1312a(1, aod2);
                if (z || !aod.mo1328i().mo1374e()) {
                    throw new ang("Misplaced rdf:value element", 202);
                }
                aod.f1254i = true;
            }
            if (equals) {
                if (aod.mo1328i().mo1375f()) {
                    aod2.f1246a = "[]";
                } else {
                    throw new ang("Misplaced rdf:li element", 202);
                }
            }
            return aod2;
        }
        throw new ang("XML namespace required for all elements and attributes", 202);
    }

    /* renamed from: b */
    private static void m15910b(aod aod, String str, String str2) {
        if ("xml:lang".equals(str)) {
            str2 = ant.m1191a(str2);
        }
        aod.mo1319c(new aod(str, str2, (aop) null));
    }

    /* renamed from: c */
    private static void m15914c(aod aod) {
        aod a = aod.mo1309a(1);
        if (a.mo1328i().mo1372c()) {
            if (!aod.mo1328i().mo1372c()) {
                aod b = a.mo1314b(1);
                a.mo1323d(b);
                aod.mo1319c(b);
            } else {
                throw new ang("Redundant xml:lang for rdf:value element", 203);
            }
        }
        for (int i = 1; i <= a.mo1322d(); i++) {
            aod.mo1319c(a.mo1314b(i));
        }
        for (int i2 = 2; i2 <= aod.mo1318c(); i2++) {
            aod.mo1319c(aod.mo1309a(i2));
        }
        aod.f1254i = false;
        aod.mo1328i().mo1373d(false);
        aod.mo1328i().mo1366a(a.mo1328i());
        aod.f1247b = a.f1247b;
        aod.mo1316b();
        Iterator f = a.mo1325f();
        while (f.hasNext()) {
            aod.mo1313a((aod) f.next());
        }
    }

    /* renamed from: b */
    private static int m15906b(Node node) {
        String localName = node.getLocalName();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null && (("about".equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && "http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespaceURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        }
        if (!"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI)) {
            return 0;
        }
        if ("li".equals(localName)) {
            return 9;
        }
        if ("parseType".equals(localName)) {
            return 4;
        }
        if ("Description".equals(localName)) {
            return 8;
        }
        if ("about".equals(localName)) {
            return 3;
        }
        if ("resource".equals(localName)) {
            return 5;
        }
        if ("RDF".equals(localName)) {
            return 1;
        }
        if ("ID".equals(localName)) {
            return 2;
        }
        if ("nodeID".equals(localName)) {
            return 6;
        }
        if ("datatype".equals(localName)) {
            return 7;
        }
        if ("aboutEach".equals(localName)) {
            return 10;
        }
        if (!"aboutEachPrefix".equals(localName)) {
            return "bagID".equals(localName) ? 12 : 0;
        }
        return 11;
    }

    /* renamed from: a */
    public static boolean m15905a(Node node) {
        if (node.getNodeType() != 3) {
            return false;
        }
        String nodeValue = node.getNodeValue();
        for (int i = 0; i < nodeValue.length(); i++) {
            if (!Character.isWhitespace(nodeValue.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m15916d(p000.aoa r17, p000.aod r18, org.w3c.dom.Node r19, boolean r20) {
        /*
            r0 = r17
            boolean r1 = r19.hasChildNodes()
            if (r1 != 0) goto L_0x017a
            r1 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x000e:
            org.w3c.dom.NamedNodeMap r9 = r19.getAttributes()
            int r9 = r9.getLength()
            java.lang.String r10 = "Unrecognized attribute of empty property element"
            r11 = 6
            r12 = 5
            java.lang.String r13 = "xml:lang"
            r14 = 2
            java.lang.String r15 = "xmlns"
            r3 = 1
            if (r4 < r9) goto L_0x00dc
            java.lang.String r4 = ""
            r9 = r18
            r6 = r19
            r2 = r20
            aod r2 = m15893a(r0, r9, r6, r4, r2)
            if (r5 != 0) goto L_0x003f
            if (r7 != 0) goto L_0x003f
            if (r8 == 0) goto L_0x003c
            aop r4 = r2.mo1328i()
            r4.mo1373d(r3)
            goto L_0x0055
        L_0x003c:
        L_0x003d:
            r3 = 0
            goto L_0x0055
        L_0x003f:
            if (r1 != 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            java.lang.String r4 = r1.getNodeValue()
        L_0x0046:
            r2.f1247b = r4
            if (r5 != 0) goto L_0x0054
            aop r4 = r2.mo1328i()
            r4.mo1358a(r14, r3)
            r3 = 0
            goto L_0x0055
        L_0x0054:
            goto L_0x003d
        L_0x0055:
            r4 = 0
        L_0x0056:
            org.w3c.dom.NamedNodeMap r5 = r19.getAttributes()
            int r5 = r5.getLength()
            if (r4 >= r5) goto L_0x00db
            org.w3c.dom.NamedNodeMap r5 = r19.getAttributes()
            org.w3c.dom.Node r5 = r5.item(r4)
            if (r5 == r1) goto L_0x00d6
            java.lang.String r7 = r5.getPrefix()
            boolean r7 = r15.equals(r7)
            if (r7 == 0) goto L_0x0076
            r8 = 0
            goto L_0x00d7
        L_0x0076:
            java.lang.String r7 = r5.getPrefix()
            if (r7 != 0) goto L_0x0089
            java.lang.String r7 = r5.getNodeName()
            boolean r7 = r15.equals(r7)
            if (r7 != 0) goto L_0x0087
            goto L_0x0089
        L_0x0087:
            r8 = 0
            goto L_0x00d7
        L_0x0089:
            int r7 = m15906b((org.w3c.dom.Node) r5)
            if (r7 == 0) goto L_0x00ab
            if (r7 == r14) goto L_0x00a9
            if (r7 == r12) goto L_0x009e
            if (r7 != r11) goto L_0x0096
            goto L_0x00a9
        L_0x0096:
            ang r0 = new ang
            r1 = 202(0xca, float:2.83E-43)
            r0.<init>(r10, r1)
            throw r0
        L_0x009e:
            java.lang.String r5 = r5.getNodeValue()
            java.lang.String r7 = "rdf:resource"
            m15910b((p000.aod) r2, (java.lang.String) r7, (java.lang.String) r5)
            r8 = 0
            goto L_0x00d7
        L_0x00a9:
            r8 = 0
            goto L_0x00d7
        L_0x00ab:
            if (r3 == 0) goto L_0x00c9
            java.lang.String r7 = r5.getNodeName()
            boolean r7 = r13.equals(r7)
            if (r7 == 0) goto L_0x00c0
            java.lang.String r5 = r5.getNodeValue()
            m15910b((p000.aod) r2, (java.lang.String) r13, (java.lang.String) r5)
            r8 = 0
            goto L_0x00d7
        L_0x00c0:
            java.lang.String r7 = r5.getNodeValue()
            r8 = 0
            m15893a(r0, r2, r5, r7, r8)
            goto L_0x00d7
        L_0x00c9:
            r8 = 0
            java.lang.String r7 = r5.getNodeName()
            java.lang.String r5 = r5.getNodeValue()
            m15910b((p000.aod) r2, (java.lang.String) r7, (java.lang.String) r5)
            goto L_0x00d7
        L_0x00d6:
            r8 = 0
        L_0x00d7:
            int r4 = r4 + 1
            goto L_0x0056
        L_0x00db:
            return
        L_0x00dc:
            r9 = r18
            r2 = r20
            r16 = 0
            org.w3c.dom.NamedNodeMap r3 = r19.getAttributes()
            org.w3c.dom.Node r3 = r3.item(r4)
            java.lang.String r11 = r3.getPrefix()
            boolean r11 = r15.equals(r11)
            if (r11 == 0) goto L_0x00f6
        L_0x00f4:
            goto L_0x0175
        L_0x00f6:
            java.lang.String r11 = r3.getPrefix()
            if (r11 != 0) goto L_0x0106
            java.lang.String r11 = r3.getNodeName()
            boolean r11 = r15.equals(r11)
            if (r11 != 0) goto L_0x00f4
        L_0x0106:
            int r11 = m15906b((org.w3c.dom.Node) r3)
            java.lang.String r15 = "Empty property element can't have both rdf:value and rdf:resource"
            if (r11 == 0) goto L_0x0143
            if (r11 == r14) goto L_0x00f4
            java.lang.String r13 = "Empty property element can't have both rdf:resource and rdf:nodeID"
            if (r11 == r12) goto L_0x012b
            r12 = 6
            if (r11 != r12) goto L_0x0123
            if (r7 != 0) goto L_0x011b
            r6 = 1
            goto L_0x0175
        L_0x011b:
            ang r0 = new ang
            r1 = 202(0xca, float:2.83E-43)
            r0.<init>(r13, r1)
            throw r0
        L_0x0123:
            r1 = 202(0xca, float:2.83E-43)
            ang r0 = new ang
            r0.<init>(r10, r1)
            throw r0
        L_0x012b:
            if (r6 != 0) goto L_0x013b
            if (r5 != 0) goto L_0x0133
            r1 = r3
            r5 = 0
            r7 = 1
            goto L_0x0175
        L_0x0133:
            ang r0 = new ang
            r1 = 203(0xcb, float:2.84E-43)
            r0.<init>(r15, r1)
            throw r0
        L_0x013b:
            ang r0 = new ang
            r1 = 202(0xca, float:2.83E-43)
            r0.<init>(r13, r1)
            throw r0
        L_0x0143:
            java.lang.String r10 = r3.getLocalName()
            java.lang.String r11 = "value"
            boolean r10 = r11.equals(r10)
            if (r10 == 0) goto L_0x0169
            java.lang.String r10 = r3.getNamespaceURI()
            java.lang.String r11 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x015c
            goto L_0x0169
        L_0x015c:
            if (r7 != 0) goto L_0x0161
            r1 = r3
            r5 = 1
            goto L_0x0175
        L_0x0161:
            ang r0 = new ang
            r1 = 203(0xcb, float:2.84E-43)
            r0.<init>(r15, r1)
            throw r0
        L_0x0169:
            java.lang.String r3 = r3.getNodeName()
            boolean r3 = r13.equals(r3)
            if (r3 != 0) goto L_0x00f4
            r8 = 1
        L_0x0175:
            int r4 = r4 + 1
            goto L_0x000e
        L_0x017a:
            ang r0 = new ang
            java.lang.String r1 = "Nested content not allowed with rdf:resource or property attributes"
            r2 = 202(0xca, float:2.83E-43)
            r0.<init>(r1, r2)
            goto L_0x0185
        L_0x0184:
            throw r0
        L_0x0185:
            goto L_0x0184
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0637xj.m15916d(aoa, aod, org.w3c.dom.Node, boolean):void");
    }

    /* renamed from: c */
    private static void m15913c(aoa aoa, aod aod, Node node, boolean z) {
        aod a = m15893a(aoa, aod, node, (String) null, z);
        int i = 0;
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node item = node.getAttributes().item(i2);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String namespaceURI = item.getNamespaceURI();
                String localName = item.getLocalName();
                if ("xml:lang".equals(item.getNodeName())) {
                    m15910b(a, "xml:lang", item.getNodeValue());
                } else if (!"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI) || (!"ID".equals(localName) && !"datatype".equals(localName))) {
                    throw new ang("Invalid attribute for literal property element", 202);
                }
            }
        }
        String str = "";
        while (i < node.getChildNodes().getLength()) {
            Node item2 = node.getChildNodes().item(i);
            if (item2.getNodeType() == 3) {
                String valueOf = String.valueOf(str);
                String valueOf2 = String.valueOf(item2.getNodeValue());
                str = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
                i++;
            } else {
                throw new ang("Invalid child of literal property element", 202);
            }
        }
        a.f1247b = str;
    }

    /* renamed from: a */
    public static void m15899a(aoa aoa, aod aod, Node node, boolean z) {
        int b = m15906b(node);
        if (b == 8 || b == 0) {
            if (z && b == 0) {
                throw new ang("Top level typed node not allowed", 203);
            }
            char c = 0;
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node item = node.getAttributes().item(i);
                if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                    int b2 = m15906b(item);
                    if (b2 == 0) {
                        m15893a(aoa, aod, item, item.getNodeValue(), z);
                    } else if (b2 != 6 && b2 != 2 && b2 != 3) {
                        throw new ang("Invalid nodeElement attribute", 202);
                    } else if (c <= 0) {
                        if (z && b2 == 3) {
                            String str = aod.f1246a;
                            if (str == null || str.length() <= 0) {
                                aod.f1246a = item.getNodeValue();
                                c = 1;
                            } else if (!aod.f1246a.equals(item.getNodeValue())) {
                                throw new ang("Mismatched top level rdf:about values", 203);
                            }
                        }
                        c = 1;
                    } else {
                        throw new ang("Mutally exclusive about, ID, nodeID attributes", 202);
                    }
                }
            }
            m15908b(aoa, aod, node, z);
            return;
        }
        throw new ang("Node element must be rdf:Description or typed node", 202);
    }

    /* renamed from: b */
    private static void m15908b(aoa aoa, aod aod, Node node, boolean z) {
        boolean z2;
        int i;
        String localName;
        String namespaceURI;
        String nodeValue;
        aoa aoa2 = aoa;
        aod aod2 = aod;
        boolean z3 = z;
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node item = node.getChildNodes().item(i2);
            if (!m15905a(item)) {
                if (item.getNodeType() == 1) {
                    int b = m15906b(item);
                    if (b != 8 && b < 10) {
                        if (!(b > 0 && b <= 7)) {
                            NamedNodeMap attributes = item.getAttributes();
                            ArrayList arrayList = null;
                            for (int i3 = 0; i3 < attributes.getLength(); i3++) {
                                Node item2 = attributes.item(i3);
                                if ("xmlns".equals(item2.getPrefix()) || (item2.getPrefix() == null && "xmlns".equals(item2.getNodeName()))) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(item2.getNodeName());
                                }
                            }
                            if (arrayList != null) {
                                int size = arrayList.size();
                                for (int i4 = 0; i4 < size; i4++) {
                                    attributes.removeNamedItem((String) arrayList.get(i4));
                                }
                            }
                            if (attributes.getLength() > 3) {
                                m15916d(aoa2, aod2, item, z3);
                            } else {
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= attributes.getLength()) {
                                        if (item.hasChildNodes()) {
                                            int i6 = 0;
                                            while (true) {
                                                if (i6 >= item.getChildNodes().getLength()) {
                                                    m15913c(aoa2, aod2, item, z3);
                                                    break;
                                                } else if (item.getChildNodes().item(i6).getNodeType() == 3) {
                                                    i6++;
                                                } else if (!z3 || !"iX:changes".equals(item.getNodeName())) {
                                                    aod a = m15893a(aoa2, aod2, item, "", z3);
                                                    for (int i7 = 0; i7 < item.getAttributes().getLength(); i7++) {
                                                        Node item3 = item.getAttributes().item(i7);
                                                        if (!"xmlns".equals(item3.getPrefix()) && (item3.getPrefix() != null || !"xmlns".equals(item3.getNodeName()))) {
                                                            String localName2 = item3.getLocalName();
                                                            String namespaceURI2 = item3.getNamespaceURI();
                                                            if ("xml:lang".equals(item3.getNodeName())) {
                                                                m15910b(a, "xml:lang", item3.getNodeValue());
                                                            } else if (!"ID".equals(localName2) || !"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI2)) {
                                                                throw new ang("Invalid attribute for resource property element", 202);
                                                            }
                                                        }
                                                    }
                                                    int i8 = 0;
                                                    boolean z4 = false;
                                                    while (i8 < item.getChildNodes().getLength()) {
                                                        Node item4 = item.getChildNodes().item(i8);
                                                        if (m15905a(item4)) {
                                                            z2 = z4;
                                                        } else {
                                                            if (item4.getNodeType() == 1) {
                                                                if (!z4) {
                                                                    boolean equals = "http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(item4.getNamespaceURI());
                                                                    String localName3 = item4.getLocalName();
                                                                    if (equals && "Bag".equals(localName3)) {
                                                                        a.mo1328i().mo1381l();
                                                                    } else if (equals && "Seq".equals(localName3)) {
                                                                        aop i9 = a.mo1328i();
                                                                        i9.mo1381l();
                                                                        i9.mo1384o();
                                                                    } else if (!equals || !"Alt".equals(localName3)) {
                                                                        a.mo1328i().mo1373d(true);
                                                                        if (!equals) {
                                                                            if (!"Description".equals(localName3)) {
                                                                                String namespaceURI3 = item4.getNamespaceURI();
                                                                                if (namespaceURI3 != null) {
                                                                                    StringBuilder sb = new StringBuilder(namespaceURI3.length() + 1 + String.valueOf(localName3).length());
                                                                                    sb.append(namespaceURI3);
                                                                                    sb.append(':');
                                                                                    sb.append(localName3);
                                                                                    m15910b(a, "rdf:type", sb.toString());
                                                                                } else {
                                                                                    throw new ang("All XML elements must be in a namespace", 203);
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        aop i10 = a.mo1328i();
                                                                        i10.mo1381l();
                                                                        i10.mo1384o();
                                                                        i10.mo1383n();
                                                                    }
                                                                    m15899a(aoa2, a, item4, false);
                                                                    if (a.f1254i) {
                                                                        m15914c(a);
                                                                        z2 = true;
                                                                    } else {
                                                                        if (a.mo1328i().mo1377h() && a.mo1328i().mo1377h() && a.mo1324e()) {
                                                                            Iterator f = a.mo1325f();
                                                                            while (true) {
                                                                                if (f.hasNext()) {
                                                                                    if (((aod) f.next()).mo1328i().mo1372c()) {
                                                                                        a.mo1328i().mo1382m();
                                                                                        m15909b(a);
                                                                                        z2 = true;
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        z2 = true;
                                                                    }
                                                                } else {
                                                                    i = 202;
                                                                }
                                                            } else if (!z4) {
                                                                throw new ang("Children of resource property element must be XML elements", 202);
                                                            } else {
                                                                i = 202;
                                                            }
                                                            throw new ang("Invalid child of resource property element", i);
                                                        }
                                                        i8++;
                                                        z4 = z2;
                                                    }
                                                    if (!z4) {
                                                        throw new ang("Missing child of resource property element", 202);
                                                    }
                                                }
                                            }
                                        } else {
                                            m15916d(aoa2, aod2, item, z3);
                                        }
                                    } else {
                                        Node item5 = attributes.item(i5);
                                        localName = item5.getLocalName();
                                        namespaceURI = item5.getNamespaceURI();
                                        nodeValue = item5.getNodeValue();
                                        if ("xml:lang".equals(item5.getNodeName()) && (!"ID".equals(localName) || !"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI))) {
                                            i5++;
                                        }
                                    }
                                }
                                if ("datatype".equals(localName) && "http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI)) {
                                    m15913c(aoa2, aod2, item, z3);
                                } else if (!"parseType".equals(localName) || !"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI)) {
                                    m15916d(aoa2, aod2, item, z3);
                                } else if ("Literal".equals(nodeValue)) {
                                    throw new ang("ParseTypeLiteral property element not allowed", 203);
                                } else if ("Resource".equals(nodeValue)) {
                                    aod a2 = m15893a(aoa2, aod2, item, "", z3);
                                    a2.mo1328i().mo1373d(true);
                                    for (int i11 = 0; i11 < item.getAttributes().getLength(); i11++) {
                                        Node item6 = item.getAttributes().item(i11);
                                        if (!"xmlns".equals(item6.getPrefix()) && (item6.getPrefix() != null || !"xmlns".equals(item6.getNodeName()))) {
                                            String localName4 = item6.getLocalName();
                                            String namespaceURI4 = item6.getNamespaceURI();
                                            if ("xml:lang".equals(item6.getNodeName())) {
                                                m15910b(a2, "xml:lang", item6.getNodeValue());
                                            } else if (!"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespaceURI4) || (!"ID".equals(localName4) && !"parseType".equals(localName4))) {
                                                throw new ang("Invalid attribute for ParseTypeResource property element", 202);
                                            }
                                        }
                                    }
                                    m15908b(aoa2, a2, item, false);
                                    if (a2.f1254i) {
                                        m15914c(a2);
                                    }
                                } else if ("Collection".equals(nodeValue)) {
                                    throw new ang("ParseTypeCollection property element not allowed", 203);
                                } else {
                                    throw new ang("ParseTypeOther property element not allowed", 203);
                                }
                            }
                        }
                    }
                    throw new ang("Invalid property element name", 202);
                }
                throw new ang("Expected property element node not found", 202);
            }
        }
    }

    /* renamed from: a */
    public static void m15901a(aod aod, String str, String str2) {
        aod aod2 = new aod("[]", str2, (aop) null);
        aod aod3 = new aod("xml:lang", str, (aop) null);
        aod2.mo1319c(aod3);
        if (!"x-default".equals(aod3.f1247b)) {
            aod.mo1313a(aod2);
        } else {
            aod.mo1312a(1, aod2);
        }
    }

    /* renamed from: a */
    public static void m15900a(aod aod) {
        aod aod2 = aod.f1248c;
        if (aod.mo1328i().mo1370b()) {
            aod2.mo1323d(aod);
        } else {
            aod2.mo1317b(aod);
        }
        if (!aod2.mo1324e() && aod2.mo1328i().mo1379j()) {
            aod2.f1248c.mo1317b(aod2);
        }
    }

    /* renamed from: b */
    public static aod m15907b(aod aod, String str, boolean z) {
        if (!aod.mo1328i().mo1379j() && !aod.mo1328i().mo1374e()) {
            if (!aod.f1251f) {
                throw new ang("Named children only allowed for schemas and structs", 102);
            } else if (aod.mo1328i().mo1375f()) {
                throw new ang("Named children not allowed for arrays", 102);
            } else if (z) {
                aod.mo1328i().mo1373d(true);
            }
        }
        aod a = aod.mo1310a(str);
        if (a != null || !z) {
            return a;
        }
        aod aod2 = new aod(str, new aop());
        aod2.f1251f = true;
        aod.mo1313a(aod2);
        return aod2;
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01e3 A[Catch:{ NumberFormatException -> 0x00a2, ang -> 0x0215 }] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01e4 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.aod m15894a(p000.aod r17, p000.aoj r18, boolean r19, p000.aop r20) {
        /*
            r0 = r18
            r1 = r19
            java.lang.String r2 = "xml:lang"
            int r3 = r18.mo1346a()
            r4 = 102(0x66, float:1.43E-43)
            if (r3 == 0) goto L_0x021f
            r3 = 0
            aok r5 = r0.mo1347a((int) r3)
            java.lang.String r5 = r5.f1278a
            r6 = r17
            aod r5 = m15896a((p000.aod) r6, (java.lang.String) r5, (boolean) r1)
            r6 = 0
            if (r5 == 0) goto L_0x021c
            boolean r7 = r5.f1251f
            if (r7 == 0) goto L_0x0027
            r5.f1251f = r3
            r7 = r5
            goto L_0x0029
        L_0x0027:
            r7 = r6
        L_0x0029:
            r8 = 1
            r9 = 1
        L_0x002b:
            int r10 = r18.mo1346a()     // Catch:{ ang -> 0x0215 }
            if (r9 >= r10) goto L_0x0202
            aok r10 = r0.mo1347a((int) r9)     // Catch:{ ang -> 0x0215 }
            int r11 = r10.f1279b     // Catch:{ ang -> 0x0215 }
            r12 = -1
            if (r11 != r8) goto L_0x0042
            java.lang.String r10 = r10.f1278a     // Catch:{ ang -> 0x0215 }
            aod r5 = m15907b((p000.aod) r5, (java.lang.String) r10, (boolean) r1)     // Catch:{ ang -> 0x0215 }
            goto L_0x018c
        L_0x0042:
            r13 = 2
            if (r11 != r13) goto L_0x0065
            java.lang.String r10 = r10.f1278a     // Catch:{ ang -> 0x0215 }
            java.lang.String r10 = r10.substring(r8)     // Catch:{ ang -> 0x0215 }
            aod r11 = r5.mo1315b((java.lang.String) r10)     // Catch:{ ang -> 0x0215 }
            if (r11 != 0) goto L_0x0061
            if (r1 == 0) goto L_0x0061
            aod r11 = new aod     // Catch:{ ang -> 0x0215 }
            r11.<init>(r10, r6)     // Catch:{ ang -> 0x0215 }
            r11.f1251f = r8     // Catch:{ ang -> 0x0215 }
            r5.mo1319c((p000.aod) r11)     // Catch:{ ang -> 0x0215 }
            r5 = r11
            goto L_0x018c
        L_0x0061:
            r5 = r11
            goto L_0x018c
        L_0x0065:
            aop r13 = r5.mo1328i()     // Catch:{ ang -> 0x0215 }
            boolean r13 = r13.mo1375f()     // Catch:{ ang -> 0x0215 }
            if (r13 == 0) goto L_0x01fa
            r13 = 3
            java.lang.String r14 = "[]"
            if (r11 != r13) goto L_0x00ab
            java.lang.String r10 = r10.f1278a     // Catch:{ ang -> 0x0215 }
            int r11 = r10.length()     // Catch:{ NumberFormatException -> 0x00a2 }
            int r11 = r11 + r12
            java.lang.String r10 = r10.substring(r8, r11)     // Catch:{ NumberFormatException -> 0x00a2 }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x00a2 }
            if (r10 <= 0) goto L_0x009a
            if (r1 == 0) goto L_0x017c
            int r11 = r5.mo1318c()     // Catch:{ ang -> 0x0215 }
            int r11 = r11 + r8
            if (r10 != r11) goto L_0x017c
            aod r11 = new aod     // Catch:{ ang -> 0x0215 }
            r11.<init>(r14, r6)     // Catch:{ ang -> 0x0215 }
            r11.f1251f = r8     // Catch:{ ang -> 0x0215 }
            r5.mo1313a((p000.aod) r11)     // Catch:{ ang -> 0x0215 }
            goto L_0x017c
        L_0x009a:
            ang r0 = new ang     // Catch:{ NumberFormatException -> 0x00a2 }
            java.lang.String r1 = "Array index must be larger than zero"
            r0.<init>(r1, r4)     // Catch:{ NumberFormatException -> 0x00a2 }
            throw r0     // Catch:{ NumberFormatException -> 0x00a2 }
        L_0x00a2:
            r0 = move-exception
            ang r0 = new ang     // Catch:{ ang -> 0x0215 }
            java.lang.String r1 = "Array index not digits."
            r0.<init>(r1, r4)     // Catch:{ ang -> 0x0215 }
            throw r0     // Catch:{ ang -> 0x0215 }
        L_0x00ab:
            r13 = 4
            if (r11 != r13) goto L_0x00b4
            int r10 = r5.mo1318c()     // Catch:{ ang -> 0x0215 }
            goto L_0x017c
        L_0x00b4:
            r13 = 6
            if (r11 != r13) goto L_0x010d
            java.lang.String r10 = r10.f1278a     // Catch:{ ang -> 0x0215 }
            java.lang.String[] r10 = p000.ant.m1195b((java.lang.String) r10)     // Catch:{ ang -> 0x0215 }
            r11 = r10[r3]     // Catch:{ ang -> 0x0215 }
            r10 = r10[r8]     // Catch:{ ang -> 0x0215 }
            r13 = -1
            r14 = 1
        L_0x00c3:
            int r15 = r5.mo1318c()     // Catch:{ ang -> 0x0215 }
            if (r14 > r15) goto L_0x010a
            if (r13 >= 0) goto L_0x010a
            aod r15 = r5.mo1309a((int) r14)     // Catch:{ ang -> 0x0215 }
            aop r16 = r15.mo1328i()     // Catch:{ ang -> 0x0215 }
            boolean r16 = r16.mo1374e()     // Catch:{ ang -> 0x0215 }
            if (r16 == 0) goto L_0x0102
            r12 = 1
        L_0x00da:
            int r6 = r15.mo1318c()     // Catch:{ ang -> 0x0215 }
            if (r12 > r6) goto L_0x00fc
            aod r6 = r15.mo1309a((int) r12)     // Catch:{ ang -> 0x0215 }
            java.lang.String r8 = r6.f1246a     // Catch:{ ang -> 0x0215 }
            boolean r8 = r11.equals(r8)     // Catch:{ ang -> 0x0215 }
            if (r8 == 0) goto L_0x00f7
            java.lang.String r6 = r6.f1247b     // Catch:{ ang -> 0x0215 }
            boolean r6 = r10.equals(r6)     // Catch:{ ang -> 0x0215 }
            if (r6 != 0) goto L_0x00f5
            goto L_0x00f7
        L_0x00f5:
            r13 = r14
            goto L_0x00fc
        L_0x00f7:
            int r12 = r12 + 1
            r6 = 0
            r8 = 1
            goto L_0x00da
        L_0x00fc:
            int r14 = r14 + 1
            r6 = 0
            r8 = 1
            r12 = -1
            goto L_0x00c3
        L_0x0102:
            ang r0 = new ang     // Catch:{ ang -> 0x0215 }
            java.lang.String r1 = "Field selector must be used on array of struct"
            r0.<init>(r1, r4)     // Catch:{ ang -> 0x0215 }
            throw r0     // Catch:{ ang -> 0x0215 }
        L_0x010a:
            r10 = r13
            goto L_0x017c
        L_0x010d:
            r6 = 5
            if (r11 != r6) goto L_0x01f0
            java.lang.String r6 = r10.f1278a     // Catch:{ ang -> 0x0215 }
            java.lang.String[] r6 = p000.ant.m1195b((java.lang.String) r6)     // Catch:{ ang -> 0x0215 }
            r8 = r6[r3]     // Catch:{ ang -> 0x0215 }
            r11 = 1
            r6 = r6[r11]     // Catch:{ ang -> 0x0215 }
            int r10 = r10.f1281d     // Catch:{ ang -> 0x0215 }
            boolean r11 = r2.equals(r8)     // Catch:{ ang -> 0x0215 }
            if (r11 != 0) goto L_0x0155
            r10 = 1
        L_0x0124:
            int r11 = r5.mo1318c()     // Catch:{ ang -> 0x0215 }
            if (r10 >= r11) goto L_0x0152
            aod r11 = r5.mo1309a((int) r10)     // Catch:{ ang -> 0x0215 }
            java.util.Iterator r11 = r11.mo1327h()     // Catch:{ ang -> 0x0215 }
        L_0x0132:
            boolean r12 = r11.hasNext()     // Catch:{ ang -> 0x0215 }
            if (r12 == 0) goto L_0x014f
            java.lang.Object r12 = r11.next()     // Catch:{ ang -> 0x0215 }
            aod r12 = (p000.aod) r12     // Catch:{ ang -> 0x0215 }
            java.lang.String r13 = r12.f1246a     // Catch:{ ang -> 0x0215 }
            boolean r13 = r8.equals(r13)     // Catch:{ ang -> 0x0215 }
            if (r13 == 0) goto L_0x0132
            java.lang.String r12 = r12.f1247b     // Catch:{ ang -> 0x0215 }
            boolean r12 = r6.equals(r12)     // Catch:{ ang -> 0x0215 }
            if (r12 == 0) goto L_0x0132
            goto L_0x017c
        L_0x014f:
            int r10 = r10 + 1
            goto L_0x0124
        L_0x0152:
            r10 = -1
            goto L_0x017c
        L_0x0155:
            java.lang.String r6 = p000.ant.m1191a((java.lang.String) r6)     // Catch:{ ang -> 0x0215 }
            int r6 = m15890a((p000.aod) r5, (java.lang.String) r6)     // Catch:{ ang -> 0x0215 }
            if (r6 >= 0) goto L_0x017b
            r8 = r10 & 4096(0x1000, float:5.74E-42)
            if (r8 <= 0) goto L_0x017b
            aod r6 = new aod     // Catch:{ ang -> 0x0215 }
            r8 = 0
            r6.<init>(r14, r8)     // Catch:{ ang -> 0x0215 }
            aod r8 = new aod     // Catch:{ ang -> 0x0215 }
            java.lang.String r10 = "x-default"
            r11 = 0
            r8.<init>(r2, r10, r11)     // Catch:{ ang -> 0x0215 }
            r6.mo1319c((p000.aod) r8)     // Catch:{ ang -> 0x0215 }
            r8 = 1
            r5.mo1312a((int) r8, (p000.aod) r6)     // Catch:{ ang -> 0x0215 }
            r10 = 1
            goto L_0x017c
        L_0x017b:
            r10 = r6
        L_0x017c:
            if (r10 <= 0) goto L_0x018a
            int r6 = r5.mo1318c()     // Catch:{ ang -> 0x0215 }
            if (r10 <= r6) goto L_0x0185
            goto L_0x018b
        L_0x0185:
            aod r5 = r5.mo1309a((int) r10)     // Catch:{ ang -> 0x0215 }
            goto L_0x018c
        L_0x018a:
        L_0x018b:
            r5 = 0
        L_0x018c:
            if (r5 == 0) goto L_0x01e9
            boolean r6 = r5.f1251f     // Catch:{ ang -> 0x0215 }
            if (r6 != 0) goto L_0x0194
            r8 = 1
            goto L_0x01e4
        L_0x0194:
            r5.f1251f = r3     // Catch:{ ang -> 0x0215 }
            r6 = 1
            if (r9 != r6) goto L_0x01ba
            aok r8 = r0.mo1347a((int) r6)     // Catch:{ ang -> 0x0215 }
            boolean r8 = r8.f1280c     // Catch:{ ang -> 0x0215 }
            if (r8 == 0) goto L_0x01ba
            aok r8 = r0.mo1347a((int) r6)     // Catch:{ ang -> 0x0215 }
            int r6 = r8.f1281d     // Catch:{ ang -> 0x0215 }
            if (r6 != 0) goto L_0x01aa
            goto L_0x01ba
        L_0x01aa:
            aop r6 = r5.mo1328i()     // Catch:{ ang -> 0x0215 }
            r8 = 1
            aok r10 = r0.mo1347a((int) r8)     // Catch:{ ang -> 0x0215 }
            int r10 = r10.f1281d     // Catch:{ ang -> 0x0215 }
            r6.mo1358a(r10, r8)     // Catch:{ ang -> 0x0215 }
            r8 = 1
            goto L_0x01e1
        L_0x01ba:
            int r6 = r18.mo1346a()     // Catch:{ ang -> 0x0215 }
            r8 = -1
            int r6 = r6 + r8
            if (r9 >= r6) goto L_0x01e0
            aok r6 = r0.mo1347a((int) r9)     // Catch:{ ang -> 0x0215 }
            int r6 = r6.f1279b     // Catch:{ ang -> 0x0215 }
            r8 = 1
            if (r6 != r8) goto L_0x01e1
            aop r6 = r5.mo1328i()     // Catch:{ ang -> 0x0215 }
            boolean r6 = r6.mo1380k()     // Catch:{ ang -> 0x0215 }
            if (r6 != 0) goto L_0x01de
            aop r6 = r5.mo1328i()     // Catch:{ ang -> 0x0215 }
            r8 = 1
            r6.mo1373d(r8)     // Catch:{ ang -> 0x0215 }
            goto L_0x01e1
        L_0x01de:
            r8 = 1
            goto L_0x01e1
        L_0x01e0:
            r8 = 1
        L_0x01e1:
            if (r7 != 0) goto L_0x01e4
            r7 = r5
        L_0x01e4:
            int r9 = r9 + 1
            r6 = 0
            goto L_0x002b
        L_0x01e9:
            if (r1 == 0) goto L_0x01ee
            m15900a((p000.aod) r7)     // Catch:{ ang -> 0x0215 }
        L_0x01ee:
            r0 = 0
            return r0
        L_0x01f0:
            ang r0 = new ang     // Catch:{ ang -> 0x0215 }
            java.lang.String r1 = "Unknown array indexing step in FollowXPathStep"
            r2 = 9
            r0.<init>(r1, r2)     // Catch:{ ang -> 0x0215 }
            throw r0     // Catch:{ ang -> 0x0215 }
        L_0x01fa:
            ang r0 = new ang     // Catch:{ ang -> 0x0215 }
            java.lang.String r1 = "Indexing applied to non-array"
            r0.<init>(r1, r4)     // Catch:{ ang -> 0x0215 }
            throw r0     // Catch:{ ang -> 0x0215 }
        L_0x0202:
            if (r7 != 0) goto L_0x0205
            goto L_0x0214
        L_0x0205:
            aop r0 = r5.mo1328i()
            r1 = r20
            r0.mo1366a((p000.aop) r1)
            aop r0 = r5.mo1328i()
            r5.f1250e = r0
        L_0x0214:
            return r5
        L_0x0215:
            r0 = move-exception
            if (r7 == 0) goto L_0x021b
            m15900a((p000.aod) r7)
        L_0x021b:
            throw r0
        L_0x021c:
            r0 = 0
            return r0
        L_0x021f:
            ang r0 = new ang
            java.lang.String r1 = "Empty XMPPath"
            r0.<init>(r1, r4)
            goto L_0x0228
        L_0x0227:
            throw r0
        L_0x0228:
            goto L_0x0227
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0637xj.m15894a(aod, aoj, boolean, aop):aod");
    }

    /* renamed from: a */
    public static aod m15895a(aod aod, String str, String str2, boolean z) {
        aod a = aod.mo1310a(str);
        if (a == null && z) {
            aop aop = new aop();
            aop.mo1358a(RecyclerView.UNDEFINED_DURATION, true);
            a = new aod(str, aop);
            a.f1251f = true;
            String a2 = ank.f1195a.mo1270a(str);
            if (a2 == null) {
                if (str2 == null || str2.length() == 0) {
                    throw new ang("Unregistered schema namespace URI", 101);
                }
                a2 = ank.f1195a.mo1271a(str, str2);
            }
            a.f1247b = a2;
            aod.mo1313a(a);
        }
        return a;
    }

    /* renamed from: a */
    public static aod m15896a(aod aod, String str, boolean z) {
        return m15895a(aod, str, (String) null, z);
    }

    /* renamed from: a */
    public static int m15890a(aod aod, String str) {
        if (aod.mo1328i().mo1375f()) {
            for (int i = 1; i <= aod.mo1318c(); i++) {
                aod a = aod.mo1309a(i);
                if (a.mo1326g() && "xml:lang".equals(a.mo1314b(1).f1246a) && str.equals(a.mo1314b(1).f1247b)) {
                    return i;
                }
            }
            return -1;
        }
        throw new ang("Language item must be used on array", 102);
    }

    /* renamed from: b */
    public static void m15909b(aod aod) {
        if (aod.mo1328i().mo1378i()) {
            int i = 2;
            while (i <= aod.mo1318c()) {
                aod a = aod.mo1309a(i);
                if (!a.mo1326g() || !"x-default".equals(a.mo1314b(1).f1247b)) {
                    i++;
                } else {
                    try {
                        aod.mo1330k().remove(i - 1);
                        aod.mo1311a();
                        aod.mo1312a(1, a);
                    } catch (ang e) {
                    }
                    if (i == 2) {
                        aod.mo1309a(2).f1247b = a.f1247b;
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static aop m15897a(aop aop, Object obj) {
        if (aop == null) {
            aop = new aop();
        }
        if (aop.mo1378i()) {
            aop.mo1383n();
        }
        if (aop.mo1377h()) {
            aop.mo1384o();
        }
        if (aop.mo1376g()) {
            aop.mo1381l();
        }
        if (aop.mo1380k() && obj != null && obj.toString().length() > 0) {
            throw new ang("Structs and arrays can't have values", 103);
        }
        aop.mo1361c(aop.f1282a);
        return aop;
    }

    /* renamed from: a */
    public static boolean m15903a(PackageManager packageManager, String str, Set set) {
        String str2;
        if (set != null && !set.isEmpty()) {
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 0);
            if (resolveContentProvider != null) {
                str2 = resolveContentProvider.packageName;
            } else {
                str2 = null;
            }
            if (!TextUtils.isEmpty(str2)) {
                return m15911b(packageManager, str2, set);
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m15911b(PackageManager packageManager, String str, Set set) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (packageInfo.signatures != null && packageInfo.signatures.length == 1) {
                try {
                    MessageDigest instance = MessageDigest.getInstance("SHA1");
                    instance.update(packageInfo.signatures[0].toByteArray());
                    return set.contains(dhe.m6107a(instance.digest()));
                } catch (NoSuchAlgorithmException e) {
                    cwn.m5514b("TrustedPackageUtil: Unable to compute hash using %s; do not trust.", "SHA1");
                    return false;
                }
            } else {
                cwn.m5510a("TrustedPackageUtil: Too many (%d) signatures found for package (%s); do not trust.", Integer.valueOf(packageInfo.signatures.length), str);
                return false;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            cwn.m5510a("TrustedPackageUtil: Package[%s] not found; do not trust.", str);
            return false;
        }
    }

    /* renamed from: a */
    public static Intent m15891a(dik dik, Uri uri) {
        if ((dik.f6609a & 16) != 0 && dik.f6614f == bis.BADGE.ordinal()) {
            throw new UnsupportedOperationException("Badge special types don't support intents.");
        }
        Intent intent = new Intent();
        dii dii = dik.f6615g;
        if (dii == null) {
            dii = dii.f6597d;
        }
        String str = dii.f6601c;
        dii dii2 = dik.f6615g;
        if (dii2 == null) {
            dii2 = dii.f6597d;
        }
        intent.setComponent(new ComponentName(str, dii2.f6600b));
        intent.setData(uri);
        intent.setFlags(1);
        return intent;
    }

    /* renamed from: a */
    public static boolean m15904a(dik dik) {
        return (dik.f6609a & 16) != 0 && dik.f6614f == bis.INTERACT.ordinal();
    }

    /* renamed from: b */
    public static boolean m15912b(dik dik) {
        return (dik.f6609a & 16) != 0 && dik.f6614f == bis.LAUNCH.ordinal();
    }

    /* renamed from: c */
    public static boolean m15915c(dik dik) {
        return dik != null && !m15912b(dik);
    }

    /* renamed from: a */
    public static void m15902a(fwy fwy, dke dke) {
        ihg.m13037a((C0140fa) fwy, djy.class, (hol) new dkf(dke));
    }
}

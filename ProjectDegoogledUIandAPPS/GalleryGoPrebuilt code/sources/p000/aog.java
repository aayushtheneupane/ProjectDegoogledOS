package p000;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: aog */
/* compiled from: PG */
public final class aog implements anl {

    /* renamed from: a */
    private final Map f1261a = new HashMap();

    /* renamed from: b */
    private final Map f1262b = new HashMap();

    /* renamed from: c */
    private final Map f1263c = new HashMap();

    /* renamed from: d */
    private final Pattern f1264d = Pattern.compile("[/*?\\[\\]]");

    public aog() {
        try {
            mo1271a("http://www.w3.org/XML/1998/namespace", "xml");
            mo1271a("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf");
            mo1271a("http://purl.org/dc/elements/1.1/", "dc");
            mo1271a("http://iptc.org/std/Iptc4xmpCore/1.0/xmlns/", "Iptc4xmpCore");
            mo1271a("adobe:ns:meta/", "x");
            mo1271a("http://ns.adobe.com/iX/1.0/", "iX");
            mo1271a("http://ns.adobe.com/xap/1.0/", "xmp");
            mo1271a("http://ns.adobe.com/xap/1.0/rights/", "xmpRights");
            mo1271a("http://ns.adobe.com/xap/1.0/mm/", "xmpMM");
            mo1271a("http://ns.adobe.com/xap/1.0/bj/", "xmpBJ");
            mo1271a("http://ns.adobe.com/xmp/note/", "xmpNote");
            mo1271a("http://ns.adobe.com/pdf/1.3/", "pdf");
            mo1271a("http://ns.adobe.com/pdfx/1.3/", "pdfx");
            mo1271a("http://www.npes.org/pdfx/ns/id/", "pdfxid");
            mo1271a("http://www.aiim.org/pdfa/ns/schema#", "pdfaSchema");
            mo1271a("http://www.aiim.org/pdfa/ns/property#", "pdfaProperty");
            mo1271a("http://www.aiim.org/pdfa/ns/type#", "pdfaType");
            mo1271a("http://www.aiim.org/pdfa/ns/field#", "pdfaField");
            mo1271a("http://www.aiim.org/pdfa/ns/id/", "pdfaid");
            mo1271a("http://www.aiim.org/pdfa/ns/extension/", "pdfaExtension");
            mo1271a("http://ns.adobe.com/photoshop/1.0/", "photoshop");
            mo1271a("http://ns.adobe.com/album/1.0/", "album");
            mo1271a("http://ns.adobe.com/exif/1.0/", "exif");
            mo1271a("http://ns.adobe.com/exif/1.0/aux/", "aux");
            mo1271a("http://ns.adobe.com/tiff/1.0/", "tiff");
            mo1271a("http://ns.adobe.com/png/1.0/", "png");
            mo1271a("http://ns.adobe.com/jpeg/1.0/", "jpeg");
            mo1271a("http://ns.adobe.com/jp2k/1.0/", "jp2k");
            mo1271a("http://ns.adobe.com/camera-raw-settings/1.0/", "crs");
            mo1271a("http://ns.adobe.com/StockPhoto/1.0/", "bmsp");
            mo1271a("http://ns.adobe.com/creatorAtom/1.0/", "creatorAtom");
            mo1271a("http://ns.adobe.com/asf/1.0/", "asf");
            mo1271a("http://ns.adobe.com/xmp/wav/1.0/", "wav");
            mo1271a("http://ns.adobe.com/xmp/1.0/DynamicMedia/", "xmpDM");
            mo1271a("http://ns.adobe.com/xmp/transient/1.0/", "xmpx");
            mo1271a("http://ns.adobe.com/xap/1.0/t/", "xmpT");
            mo1271a("http://ns.adobe.com/xap/1.0/t/pg/", "xmpTPg");
            mo1271a("http://ns.adobe.com/xap/1.0/g/", "xmpG");
            mo1271a("http://ns.adobe.com/xap/1.0/g/img/", "xmpGImg");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/Font#", "stFNT");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/Dimensions#", "stDim");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/ResourceEvent#", "stEvt");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/ResourceRef#", "stRef");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/Version#", "stVer");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/Job#", "stJob");
            mo1271a("http://ns.adobe.com/xap/1.0/sType/ManifestItem#", "stMfs");
            mo1271a("http://ns.adobe.com/xmp/Identifier/qual/1.0/", "xmpidq");
            aol aol = new aol();
            aol.mo1358a(1536, true);
            aol aol2 = new aol();
            aol2.mo1358a(7680, true);
            m1270a("http://ns.adobe.com/xap/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aol);
            m1270a("http://ns.adobe.com/xap/1.0/", "Authors", "http://purl.org/dc/elements/1.1/", "creator", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/", "Format", "http://purl.org/dc/elements/1.1/", "format", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/", "Locale", "http://purl.org/dc/elements/1.1/", "language", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", (aol) null);
            m1270a("http://ns.adobe.com/xap/1.0/rights/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", (aol) null);
            m1270a("http://ns.adobe.com/pdf/1.3/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aol);
            m1270a("http://ns.adobe.com/pdf/1.3/", "BaseURL", "http://ns.adobe.com/xap/1.0/", "BaseURL", (aol) null);
            m1270a("http://ns.adobe.com/pdf/1.3/", "CreationDate", "http://ns.adobe.com/xap/1.0/", "CreateDate", (aol) null);
            m1270a("http://ns.adobe.com/pdf/1.3/", "Creator", "http://ns.adobe.com/xap/1.0/", "CreatorTool", (aol) null);
            m1270a("http://ns.adobe.com/pdf/1.3/", "ModDate", "http://ns.adobe.com/xap/1.0/", "ModifyDate", (aol) null);
            m1270a("http://ns.adobe.com/pdf/1.3/", "Subject", "http://purl.org/dc/elements/1.1/", "description", aol2);
            m1270a("http://ns.adobe.com/pdf/1.3/", "Title", "http://purl.org/dc/elements/1.1/", "title", aol2);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aol);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Caption", "http://purl.org/dc/elements/1.1/", "description", aol2);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", aol2);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", (aol) null);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Marked", "http://ns.adobe.com/xap/1.0/rights/", "Marked", (aol) null);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", aol2);
            m1270a("http://ns.adobe.com/photoshop/1.0/", "WebStatement", "http://ns.adobe.com/xap/1.0/rights/", "WebStatement", (aol) null);
            m1270a("http://ns.adobe.com/tiff/1.0/", "Artist", "http://purl.org/dc/elements/1.1/", "creator", aol);
            m1270a("http://ns.adobe.com/tiff/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", (aol) null);
            m1270a("http://ns.adobe.com/tiff/1.0/", "DateTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", (aol) null);
            m1270a("http://ns.adobe.com/tiff/1.0/", "ImageDescription", "http://purl.org/dc/elements/1.1/", "description", (aol) null);
            m1270a("http://ns.adobe.com/tiff/1.0/", "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", (aol) null);
            m1270a("http://ns.adobe.com/png/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aol);
            m1270a("http://ns.adobe.com/png/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", aol2);
            m1270a("http://ns.adobe.com/png/1.0/", "CreationTime", "http://ns.adobe.com/xap/1.0/", "CreateDate", (aol) null);
            m1270a("http://ns.adobe.com/png/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", aol2);
            m1270a("http://ns.adobe.com/png/1.0/", "ModificationTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", (aol) null);
            m1270a("http://ns.adobe.com/png/1.0/", "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", (aol) null);
            m1270a("http://ns.adobe.com/png/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", aol2);
        } catch (ang e) {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }

    /* renamed from: c */
    public final synchronized aor mo1273c(String str) {
        return (aor) this.f1263c.get(str);
    }

    /* renamed from: a */
    public final synchronized String mo1270a(String str) {
        return (String) this.f1261a.get(str);
    }

    /* renamed from: b */
    public final synchronized String mo1272b(String str) {
        if (str != null) {
            if (!str.endsWith(":")) {
                str = str.concat(":");
            }
        }
        return (String) this.f1262b.get(str);
    }

    /* renamed from: a */
    private final synchronized void m1270a(String str, String str2, String str3, String str4, aol aol) {
        aol aol2;
        ckx.m4485d(str);
        ckx.m4484c(str2);
        ckx.m4485d(str3);
        ckx.m4484c(str4);
        if (aol != null) {
            aol2 = new aol(C0637xj.m15897a(aol.mo1354c(), (Object) null).f1282a);
        } else {
            aol2 = new aol();
        }
        if (this.f1264d.matcher(str2).find() || this.f1264d.matcher(str4).find()) {
            throw new ang("Alias and actual property names must be simple", 102);
        }
        String a = mo1270a(str);
        String a2 = mo1270a(str3);
        if (a == null) {
            throw new ang("Alias namespace is not registered", 101);
        } else if (a2 != null) {
            String str5 = str2.length() == 0 ? new String(a) : a.concat(str2);
            if (!this.f1263c.containsKey(str5)) {
                if (!this.f1263c.containsKey(str4.length() == 0 ? new String(a2) : a2.concat(str4))) {
                    this.f1263c.put(str5, new aof(str3, a2, str4, aol2));
                } else {
                    throw new ang("Actual property is already an alias, use the base property", 4);
                }
            } else {
                throw new ang("Alias is already existing", 4);
            }
        } else {
            throw new ang("Actual namespace is not registered", 101);
        }
    }

    /* renamed from: a */
    public final synchronized String mo1271a(String str, String str2) {
        ckx.m4485d(str);
        if (str2 == null || str2.length() == 0) {
            throw new ang("Empty prefix", 4);
        }
        int i = 1;
        if (str2.charAt(str2.length() - 1) != ':') {
            StringBuilder sb = new StringBuilder(str2.length() + 1);
            sb.append(str2);
            sb.append(':');
            str2 = sb.toString();
        }
        if (ant.m1199e(str2.substring(0, str2.length() - 1))) {
            String str3 = (String) this.f1261a.get(str);
            String str4 = (String) this.f1262b.get(str2);
            if (str3 != null) {
                return str3;
            }
            if (str4 != null) {
                String str5 = str2;
                while (this.f1262b.containsKey(str5)) {
                    String substring = str2.substring(0, str2.length() - 1);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(substring).length() + 14);
                    sb2.append(substring);
                    sb2.append("_");
                    sb2.append(i);
                    sb2.append("_:");
                    str5 = sb2.toString();
                    i++;
                }
                str2 = str5;
            }
            this.f1262b.put(str2, str);
            this.f1261a.put(str, str2);
            return str2;
        }
        throw new ang("The prefix is a bad XML name", 201);
    }
}

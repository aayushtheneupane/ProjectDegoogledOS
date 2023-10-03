package com.android.messaging.sms;

import android.content.ContentValues;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1474sa;
import com.google.common.collect.C1633Xa;
import java.io.IOException;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.e */
class C1009e {

    /* renamed from: QD */
    private static final Map f1529QD = C1633Xa.newHashMap();

    /* renamed from: OD */
    private C1007c f1530OD;

    /* renamed from: PD */
    private C1008d f1531PD;
    private final XmlPullParser mInputParser;
    private final StringBuilder mLogStringBuilder = new StringBuilder();

    static {
        f1529QD.put("mcc", "mcc");
        f1529QD.put("mnc", "mnc");
        f1529QD.put("carrier", "name");
        f1529QD.put("apn", "apn");
        f1529QD.put("mmsc", "mmsc");
        f1529QD.put("mmsproxy", "mmsproxy");
        f1529QD.put("mmsport", "mmsport");
        f1529QD.put("type", "type");
        f1529QD.put("user", "user");
        f1529QD.put("password", "password");
        f1529QD.put("authtype", "authtype");
        f1529QD.put("mvno_match_data", "mvno_match_data");
        f1529QD.put("mvno_type", "mvno_type");
        f1529QD.put("protocol", "protocol");
        f1529QD.put("bearer", "bearer");
        f1529QD.put("server", "server");
        f1529QD.put("roaming_protocol", "roaming_protocol");
        f1529QD.put("proxy", "proxy");
        f1529QD.put("port", "port");
        f1529QD.put("carrier_enabled", "carrier_enabled");
    }

    private C1009e(XmlPullParser xmlPullParser) {
        this.mInputParser = xmlPullParser;
        this.f1530OD = null;
        this.f1531PD = null;
    }

    /* renamed from: a */
    public static C1009e m2351a(XmlPullParser xmlPullParser) {
        C1424b.m3594t(xmlPullParser);
        return new C1009e(xmlPullParser);
    }

    /* renamed from: c */
    private void m2353c(ContentValues contentValues) {
        Boolean bool;
        C1424b.m3594t(contentValues);
        contentValues.clear();
        for (int i = 0; i < this.mInputParser.getAttributeCount(); i++) {
            String str = (String) f1529QD.get(this.mInputParser.getAttributeName(i));
            if (str != null) {
                contentValues.put(str, this.mInputParser.getAttributeValue(i));
            }
        }
        contentValues.put("numeric", C1474sa.m3799j(contentValues.getAsString("mcc"), contentValues.getAsString("mnc")));
        String asString = contentValues.getAsString("authtype");
        if (asString != null) {
            contentValues.put("authtype", m2352a(asString, -1, "apn authtype"));
        }
        String asString2 = contentValues.getAsString("carrier_enabled");
        if (asString2 != null) {
            try {
                bool = Boolean.valueOf(Boolean.parseBoolean(asString2));
            } catch (Exception unused) {
                C1430e.m3622e("MessagingApp", "Invalid value " + asString2 + "for" + "apn carrierEnabled" + " @" + xmlParserDebugContext());
                bool = null;
            }
            contentValues.put("carrier_enabled", bool);
        }
        String asString3 = contentValues.getAsString("bearer");
        if (asString3 != null) {
            contentValues.put("bearer", m2352a(asString3, 0, "apn bearer"));
        }
        if (this.mInputParser.next() == 3) {
            C1007c cVar = this.f1530OD;
            if (cVar != null) {
                ((C1005a) cVar).f1526ND.insert("apn", (String) null, contentValues);
                return;
            }
            return;
        }
        StringBuilder Pa = C0632a.m1011Pa("Apn: expecting end tag @");
        Pa.append(xmlParserDebugContext());
        throw new XmlPullParserException(Pa.toString());
    }

    /* renamed from: lo */
    private void m2354lo() {
        String str;
        C1474sa.m3799j(this.mInputParser.getAttributeValue((String) null, "mcc"), this.mInputParser.getAttributeValue((String) null, "mnc"));
        while (true) {
            int next = this.mInputParser.next();
            if (next != 4) {
                if (next == 2) {
                    String attributeValue = this.mInputParser.getAttributeValue((String) null, "name");
                    String name = this.mInputParser.getName();
                    int next2 = this.mInputParser.next();
                    if (next2 == 4) {
                        str = this.mInputParser.getText();
                        next2 = this.mInputParser.next();
                    } else {
                        str = null;
                    }
                    if (next2 == 3) {
                        C1008d dVar = this.f1531PD;
                        if (dVar != null) {
                            C1014j.m2365a(((C1013i) dVar).val$values, name, attributeValue, str);
                        }
                    } else {
                        StringBuilder Pa = C0632a.m1011Pa("ApnsXmlProcessor: expecting end tag @");
                        Pa.append(xmlParserDebugContext());
                        throw new XmlPullParserException(Pa.toString());
                    }
                } else if (next != 3) {
                    StringBuilder Pa2 = C0632a.m1011Pa("MmsConfig: expecting start or end tag @");
                    Pa2.append(xmlParserDebugContext());
                    throw new XmlPullParserException(Pa2.toString());
                } else {
                    return;
                }
            }
        }
    }

    private String xmlParserDebugContext() {
        this.mLogStringBuilder.setLength(0);
        XmlPullParser xmlPullParser = this.mInputParser;
        if (xmlPullParser == null) {
            return "Unknown";
        }
        try {
            int eventType = xmlPullParser.getEventType();
            this.mLogStringBuilder.append(eventType != 0 ? eventType != 1 ? eventType != 2 ? eventType != 3 ? eventType != 4 ? Integer.toString(eventType) : "TEXT" : "END_TAG" : "START_TAG" : "END_DOCUMENT" : "START_DOCUMENT");
            if (eventType == 2 || eventType == 3 || eventType == 4) {
                StringBuilder sb = this.mLogStringBuilder;
                sb.append('<');
                sb.append(this.mInputParser.getName());
                for (int i = 0; i < this.mInputParser.getAttributeCount(); i++) {
                    StringBuilder sb2 = this.mLogStringBuilder;
                    sb2.append(' ');
                    sb2.append(this.mInputParser.getAttributeName(i));
                    sb2.append('=');
                    sb2.append(this.mInputParser.getAttributeValue(i));
                }
                this.mLogStringBuilder.append("/>");
            }
            return this.mLogStringBuilder.toString();
        } catch (XmlPullParserException e) {
            C0632a.m1020a("xmlParserDebugContext: ", (Object) e, "MessagingApp", (Throwable) e);
            return "Unknown";
        }
    }

    /* renamed from: gi */
    public void mo6817gi() {
        int next;
        do {
            try {
                next = this.mInputParser.next();
                if (next == 2) {
                    break;
                }
            } catch (IOException e) {
                C0632a.m1020a("ApnsXmlProcessor: I/O failure ", (Object) e, "MessagingApp", (Throwable) e);
                return;
            } catch (XmlPullParserException e2) {
                C0632a.m1020a("ApnsXmlProcessor: parsing failure ", (Object) e2, "MessagingApp", (Throwable) e2);
                return;
            }
        } while (next != 1);
        if (next == 2) {
            ContentValues contentValues = new ContentValues();
            String name = this.mInputParser.getName();
            if ("apns".equals(name)) {
                while (true) {
                    int next2 = this.mInputParser.next();
                    if (next2 == 2 || next2 == 1) {
                        if (next2 == 2) {
                            String name2 = this.mInputParser.getName();
                            if ("apn".equals(name2)) {
                                m2353c(contentValues);
                            } else if ("mms_config".equals(name2)) {
                                m2354lo();
                            }
                        } else {
                            return;
                        }
                    }
                }
            } else if ("mms_config".equals(name)) {
                m2354lo();
            }
        } else {
            throw new XmlPullParserException("ApnsXmlProcessor: expecting start tag @" + xmlParserDebugContext());
        }
    }

    /* renamed from: a */
    public C1009e mo6815a(C1007c cVar) {
        this.f1530OD = cVar;
        return this;
    }

    /* renamed from: a */
    public C1009e mo6816a(C1008d dVar) {
        this.f1531PD = dVar;
        return this;
    }

    /* renamed from: a */
    private Integer m2352a(String str, Integer num, String str2) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            C1430e.m3622e("MessagingApp", "Invalid value " + str + "for" + str2 + " @" + xmlParserDebugContext());
            return num;
        }
    }
}

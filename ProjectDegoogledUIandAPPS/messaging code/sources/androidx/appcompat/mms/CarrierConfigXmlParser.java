package androidx.appcompat.mms;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p026b.p027a.p030b.p031a.C0632a;

class CarrierConfigXmlParser extends MmsXmlResourceParser {
    private static final String TAG_MMS_CONFIG = "mms_config";
    private final KeyValueProcessor mKeyValueProcessor;

    interface KeyValueProcessor {
        void process(String str, String str2, String str3);
    }

    CarrierConfigXmlParser(XmlPullParser xmlPullParser, KeyValueProcessor keyValueProcessor) {
        super(xmlPullParser);
        this.mKeyValueProcessor = keyValueProcessor;
    }

    /* access modifiers changed from: protected */
    public String getRootTag() {
        return TAG_MMS_CONFIG;
    }

    /* access modifiers changed from: protected */
    public void parseRecord() {
        String str = null;
        String attributeValue = this.mInputParser.getAttributeValue((String) null, "name");
        String name = this.mInputParser.getName();
        int next = this.mInputParser.next();
        if (next == 4) {
            str = this.mInputParser.getText();
            next = this.mInputParser.next();
        }
        if (next == 3) {
            KeyValueProcessor keyValueProcessor = this.mKeyValueProcessor;
            if (keyValueProcessor != null) {
                keyValueProcessor.process(name, attributeValue, str);
                return;
            }
            return;
        }
        StringBuilder Pa = C0632a.m1011Pa("Expecting end tag @");
        Pa.append(xmlParserDebugContext());
        throw new XmlPullParserException(Pa.toString());
    }
}

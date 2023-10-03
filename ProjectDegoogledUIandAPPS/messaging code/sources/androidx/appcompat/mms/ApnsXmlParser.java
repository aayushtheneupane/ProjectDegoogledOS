package androidx.appcompat.mms;

import android.content.ContentValues;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p026b.p027a.p030b.p031a.C0632a;

class ApnsXmlParser extends MmsXmlResourceParser {
    private static final String TAG_APN = "apn";
    private static final String TAG_APNS = "apns";
    private final ApnProcessor mApnProcessor;
    private final ContentValues mValues = new ContentValues();

    interface ApnProcessor {
        void process(ContentValues contentValues);
    }

    ApnsXmlParser(XmlPullParser xmlPullParser, ApnProcessor apnProcessor) {
        super(xmlPullParser);
        this.mApnProcessor = apnProcessor;
    }

    /* access modifiers changed from: protected */
    public String getRootTag() {
        return TAG_APNS;
    }

    /* access modifiers changed from: protected */
    public void parseRecord() {
        if (TAG_APN.equals(this.mInputParser.getName())) {
            this.mValues.clear();
            for (int i = 0; i < this.mInputParser.getAttributeCount(); i++) {
                String attributeName = this.mInputParser.getAttributeName(i);
                if (attributeName != null) {
                    this.mValues.put(attributeName, this.mInputParser.getAttributeValue(i));
                }
            }
            ApnProcessor apnProcessor = this.mApnProcessor;
            if (apnProcessor != null) {
                apnProcessor.process(this.mValues);
            }
        }
        if (this.mInputParser.next() != 3) {
            StringBuilder Pa = C0632a.m1011Pa("Expecting end tag @");
            Pa.append(xmlParserDebugContext());
            throw new XmlPullParserException(Pa.toString());
        }
    }
}

package androidx.appcompat.mms;

import android.util.Log;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

abstract class MmsXmlResourceParser {
    protected final XmlPullParser mInputParser;
    private final StringBuilder mLogStringBuilder = new StringBuilder();

    protected MmsXmlResourceParser(XmlPullParser xmlPullParser) {
        this.mInputParser = xmlPullParser;
    }

    private static String xmlParserEventString(int i) {
        if (i == 0) {
            return "START_DOCUMENT";
        }
        if (i == 1) {
            return "END_DOCUMENT";
        }
        if (i == 2) {
            return "START_TAG";
        }
        if (i != 3) {
            return i != 4 ? Integer.toString(i) : "TEXT";
        }
        return "END_TAG";
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    protected int advanceToNextEvent(int r3) {
        /*
            r2 = this;
        L_0x0000:
            org.xmlpull.v1.XmlPullParser r0 = r2.mInputParser
            int r0 = r0.next()
            if (r0 == r3) goto L_0x000b
            r1 = 1
            if (r0 != r1) goto L_0x0000
        L_0x000b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsXmlResourceParser.advanceToNextEvent(int):int");
    }

    /* access modifiers changed from: protected */
    public abstract String getRootTag();

    /* access modifiers changed from: package-private */
    public void parse() {
        int next;
        try {
            if (advanceToNextEvent(2) != 2) {
                throw new XmlPullParserException("ApnsXmlProcessor: expecting start tag @" + xmlParserDebugContext());
            } else if (!getRootTag().equals(this.mInputParser.getName())) {
                Log.w("MmsLib", "Carrier config does not start with " + getRootTag());
            } else {
                while (true) {
                    next = this.mInputParser.next();
                    if (next != 4) {
                        if (next != 2) {
                            break;
                        }
                        parseRecord();
                    }
                }
                if (next != 3) {
                    throw new XmlPullParserException("Expecting start or end tag @" + xmlParserDebugContext());
                }
            }
        } catch (IOException e) {
            Log.w("MmsLib", "XmlResourceParser: I/O failure", e);
        } catch (XmlPullParserException e2) {
            Log.w("MmsLib", "XmlResourceParser: parsing failure", e2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void parseRecord();

    /* access modifiers changed from: protected */
    public String xmlParserDebugContext() {
        this.mLogStringBuilder.setLength(0);
        XmlPullParser xmlPullParser = this.mInputParser;
        if (xmlPullParser == null) {
            return "Unknown";
        }
        try {
            int eventType = xmlPullParser.getEventType();
            this.mLogStringBuilder.append(xmlParserEventString(eventType));
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
            Log.w("MmsLib", "XmlResourceParser exception", e);
            return "Unknown";
        }
    }
}

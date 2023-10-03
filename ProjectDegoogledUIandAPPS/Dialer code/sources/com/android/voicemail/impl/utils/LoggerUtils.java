package com.android.voicemail.impl.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.VoicemailContract;
import android.telecom.PhoneAccountHandle;
import android.util.ArrayMap;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Voicemail;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LoggerUtils {
    public static Uri insert(Context context, Voicemail voicemail) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", String.valueOf(voicemail.getTimestampMillis()));
        contentValues.put("number", voicemail.getNumber());
        contentValues.put("duration", String.valueOf(voicemail.getDuration()));
        contentValues.put("source_package", voicemail.getSourcePackage());
        contentValues.put("source_data", voicemail.getSourceData());
        contentValues.put("is_read", Integer.valueOf(voicemail.isRead() ? 1 : 0));
        contentValues.put("is_omtp_voicemail", 1);
        PhoneAccountHandle phoneAccount = voicemail.getPhoneAccount();
        if (phoneAccount != null) {
            contentValues.put("subscription_component_name", phoneAccount.getComponentName().flattenToString());
            contentValues.put("subscription_id", phoneAccount.getId());
        }
        if (voicemail.getTranscription() != null) {
            contentValues.put("transcription", voicemail.getTranscription());
        }
        return contentResolver.insert(VoicemailContract.Voicemails.buildSourceUri(context.getPackageName()), contentValues);
    }

    public static void logImpressionOnMainThread(Context context, DialerImpression$Type dialerImpression$Type) {
        DialerExecutorModule.postOnUiThread(new Runnable(context, dialerImpression$Type) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ DialerImpression$Type f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                ((LoggingBindingsStub) Logger.get(this.f$0)).logImpression(this.f$1);
            }
        });
    }

    public static final ArrayMap<String, ?> readThisArrayMapXml(XmlPullParser xmlPullParser, String str, String[] strArr, XmlUtils$ReadMapCallback xmlUtils$ReadMapCallback) throws XmlPullParserException, IOException {
        ArrayMap<String, ?> arrayMap = new ArrayMap<>();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayMap.put(strArr[0], readThisValueXml(xmlPullParser, strArr, xmlUtils$ReadMapCallback, true));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayMap;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Document ended before ", str, " end tag"));
    }

    public static final ArrayList readThisListXml(XmlPullParser xmlPullParser, String str, String[] strArr, XmlUtils$ReadMapCallback xmlUtils$ReadMapCallback, boolean z) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(readThisValueXml(xmlPullParser, strArr, xmlUtils$ReadMapCallback, z));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayList;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Document ended before ", str, " end tag"));
    }

    private static Object readThisValueXml(XmlPullParser xmlPullParser, String[] strArr, XmlUtils$ReadMapCallback xmlUtils$ReadMapCallback, boolean z) throws XmlPullParserException, IOException {
        int next;
        Object obj;
        Object obj2 = null;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "name");
        String name = xmlPullParser.getName();
        if (!name.equals("null")) {
            if (name.equals("string")) {
                String str = "";
                while (true) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 1) {
                        throw new XmlPullParserException("Unexpected end of document in <string>");
                    } else if (next2 == 3) {
                        if (xmlPullParser.getName().equals("string")) {
                            strArr[0] = attributeValue;
                            return str;
                        }
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected end tag in <string>: ");
                        outline13.append(xmlPullParser.getName());
                        throw new XmlPullParserException(outline13.toString());
                    } else if (next2 == 4) {
                        StringBuilder outline132 = GeneratedOutlineSupport.outline13(str);
                        outline132.append(xmlPullParser.getText());
                        str = outline132.toString();
                    } else if (next2 == 2) {
                        StringBuilder outline133 = GeneratedOutlineSupport.outline13("Unexpected start tag in <string>: ");
                        outline133.append(xmlPullParser.getName());
                        throw new XmlPullParserException(outline133.toString());
                    }
                }
            } else {
                try {
                    if (name.equals("int")) {
                        obj = Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "value")));
                    } else if (name.equals("long")) {
                        obj = Long.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
                    } else if (name.equals("float")) {
                        obj = Float.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
                    } else if (name.equals("double")) {
                        obj = Double.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
                    } else {
                        obj = name.equals("boolean") ? Boolean.valueOf(xmlPullParser.getAttributeValue((String) null, "value")) : null;
                    }
                    if (obj != null) {
                        obj2 = obj;
                    } else if (name.equals("string-array")) {
                        xmlPullParser.next();
                        ArrayList arrayList = new ArrayList();
                        int eventType = xmlPullParser.getEventType();
                        do {
                            if (eventType == 2) {
                                if (xmlPullParser.getName().equals("item")) {
                                    try {
                                        arrayList.add(xmlPullParser.getAttributeValue((String) null, "value"));
                                    } catch (NullPointerException unused) {
                                        throw new XmlPullParserException("Need value attribute in item");
                                    } catch (NumberFormatException unused2) {
                                        throw new XmlPullParserException("Not a number in value attribute in item");
                                    }
                                } else {
                                    StringBuilder outline134 = GeneratedOutlineSupport.outline13("Expected item tag at: ");
                                    outline134.append(xmlPullParser.getName());
                                    throw new XmlPullParserException(outline134.toString());
                                }
                            } else if (eventType == 3) {
                                if (xmlPullParser.getName().equals("string-array")) {
                                    String[] strArr2 = (String[]) arrayList.toArray(new String[0]);
                                    strArr[0] = attributeValue;
                                    return strArr2;
                                } else if (!xmlPullParser.getName().equals("item")) {
                                    throw new XmlPullParserException("Expected " + "string-array" + " end tag at: " + xmlPullParser.getName());
                                }
                            }
                            eventType = xmlPullParser.next();
                        } while (eventType != 1);
                        throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Document ended before ", "string-array", " end tag"));
                    } else if (name.equals("list")) {
                        xmlPullParser.next();
                        ArrayList readThisListXml = readThisListXml(xmlPullParser, "list", strArr, xmlUtils$ReadMapCallback, z);
                        strArr[0] = attributeValue;
                        return readThisListXml;
                    } else if (xmlUtils$ReadMapCallback != null) {
                        Object readThisUnknownObjectXml = xmlUtils$ReadMapCallback.readThisUnknownObjectXml(xmlPullParser, name);
                        strArr[0] = attributeValue;
                        return readThisUnknownObjectXml;
                    } else {
                        throw new XmlPullParserException(GeneratedOutlineSupport.outline8("Unknown tag: ", name));
                    }
                } catch (NullPointerException unused3) {
                    throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Need value attribute in <", name, ">"));
                } catch (NumberFormatException unused4) {
                    throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Not a number in value attribute in <", name, ">"));
                }
            }
        }
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                throw new XmlPullParserException(GeneratedOutlineSupport.outline9("Unexpected end of document in <", name, ">"));
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(name)) {
                    strArr[0] = attributeValue;
                    return obj2;
                }
                throw new XmlPullParserException("Unexpected end tag in <" + name + ">: " + xmlPullParser.getName());
            } else if (next == 4) {
                throw new XmlPullParserException("Unexpected text in <" + name + ">: " + xmlPullParser.getName());
            }
        } while (next != 2);
        throw new XmlPullParserException("Unexpected start tag in <" + name + ">: " + xmlPullParser.getName());
    }
}

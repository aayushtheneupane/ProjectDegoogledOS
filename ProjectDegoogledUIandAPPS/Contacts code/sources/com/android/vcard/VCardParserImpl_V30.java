package com.android.vcard;

import android.util.Log;
import com.android.vcard.exception.VCardException;
import java.io.IOException;
import java.util.Set;

class VCardParserImpl_V30 extends VCardParserImpl_V21 {
    private boolean mEmittedAgentWarning = false;
    private String mPreviousLine;

    /* access modifiers changed from: protected */
    public String getBase64(String str) throws IOException, VCardException {
        return str;
    }

    /* access modifiers changed from: protected */
    public int getVersion() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getVersionString() {
        return "3.0";
    }

    public VCardParserImpl_V30() {
    }

    public VCardParserImpl_V30(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public String peekLine() throws IOException {
        String str = this.mPreviousLine;
        if (str != null) {
            return str;
        }
        return this.mReader.peekLine();
    }

    /* access modifiers changed from: protected */
    public String getLine() throws IOException {
        String str = this.mPreviousLine;
        if (str == null) {
            return this.mReader.readLine();
        }
        this.mPreviousLine = null;
        return str;
    }

    /* access modifiers changed from: protected */
    public String getNonEmptyLine() throws IOException, VCardException {
        String readLine;
        String str = null;
        StringBuilder sb = null;
        while (true) {
            readLine = this.mReader.readLine();
            if (readLine == null) {
                break;
            } else if (readLine.length() != 0) {
                if (readLine.charAt(0) != ' ' && readLine.charAt(0) != 9) {
                    if (sb != null || this.mPreviousLine != null) {
                        break;
                    }
                    this.mPreviousLine = readLine;
                } else {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    String str2 = this.mPreviousLine;
                    if (str2 != null) {
                        sb.append(str2);
                        this.mPreviousLine = null;
                    }
                    sb.append(readLine.substring(1));
                }
            }
        }
        if (sb != null) {
            str = sb.toString();
        } else {
            String str3 = this.mPreviousLine;
            if (str3 != null) {
                str = str3;
            }
        }
        this.mPreviousLine = readLine;
        if (str != null) {
            return str;
        }
        throw new VCardException("Reached end of buffer.");
    }

    /* access modifiers changed from: protected */
    public boolean readBeginVCard(boolean z) throws IOException, VCardException {
        return super.readBeginVCard(z);
    }

    /* access modifiers changed from: protected */
    public void handleParams(VCardProperty vCardProperty, String str) throws VCardException {
        try {
            super.handleParams(vCardProperty, str);
        } catch (VCardException unused) {
            String[] split = str.split("=", 2);
            if (split.length == 2) {
                handleAnyParam(vCardProperty, split[0], split[1]);
                return;
            }
            throw new VCardException("Unknown params value: " + str);
        }
    }

    /* access modifiers changed from: protected */
    public void handleAnyParam(VCardProperty vCardProperty, String str, String str2) {
        splitAndPutParam(vCardProperty, str, str2);
    }

    /* access modifiers changed from: protected */
    public void handleParamWithoutName(VCardProperty vCardProperty, String str) {
        handleType(vCardProperty, str);
    }

    /* access modifiers changed from: protected */
    public void handleType(VCardProperty vCardProperty, String str) {
        splitAndPutParam(vCardProperty, "TYPE", str);
    }

    private void splitAndPutParam(VCardProperty vCardProperty, String str, String str2) {
        int length = str2.length();
        StringBuilder sb = null;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str2.charAt(i);
            if (charAt == '\"') {
                if (z) {
                    vCardProperty.addParameter(str, encodeParamValue(sb.toString()));
                    sb = null;
                    z = false;
                } else {
                    if (sb != null) {
                        if (sb.length() > 0) {
                            Log.w("vCard", "Unexpected Dquote inside property.");
                        } else {
                            vCardProperty.addParameter(str, encodeParamValue(sb.toString()));
                        }
                    }
                    z = true;
                }
            } else if (charAt != ',' || z) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(charAt);
            } else if (sb == null) {
                Log.w("vCard", "Comma is used before actual string comes. (" + str2 + ")");
            } else {
                vCardProperty.addParameter(str, encodeParamValue(sb.toString()));
                sb = null;
            }
        }
        if (z) {
            Log.d("vCard", "Dangling Dquote.");
        }
        if (sb == null) {
            return;
        }
        if (sb.length() == 0) {
            Log.w("vCard", "Unintended behavior. We must not see empty StringBuilder at the end of parameter value parsing.");
        } else {
            vCardProperty.addParameter(str, encodeParamValue(sb.toString()));
        }
    }

    /* access modifiers changed from: protected */
    public String encodeParamValue(String str) {
        return VCardUtils.convertStringCharset(str, "ISO-8859-1", "UTF-8");
    }

    /* access modifiers changed from: protected */
    public void handleAgent(VCardProperty vCardProperty) {
        if (!this.mEmittedAgentWarning) {
            Log.w("vCard", "AGENT in vCard 3.0 is not supported yet. Ignore it");
            this.mEmittedAgentWarning = true;
        }
    }

    /* access modifiers changed from: protected */
    public String maybeUnescapeText(String str) {
        return unescapeText(str);
    }

    public static String unescapeText(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\\' || i >= length - 1) {
                sb.append(charAt);
            } else {
                i++;
                char charAt2 = str.charAt(i);
                if (charAt2 == 'n' || charAt2 == 'N') {
                    sb.append("\n");
                } else {
                    sb.append(charAt2);
                }
            }
            i++;
        }
        return sb.toString();
    }

    public static String unescapeCharacter(char c) {
        return (c == 'n' || c == 'N') ? "\n" : String.valueOf(c);
    }

    /* access modifiers changed from: protected */
    public Set<String> getKnownPropertyNameSet() {
        return VCardParser_V30.sKnownPropertyNameSet;
    }
}

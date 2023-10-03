package com.android.vcard;

import android.util.Log;
import com.android.vcard.exception.VCardException;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.vcard.h */
class C1501h extends C1500g {

    /* renamed from: BM */
    private boolean f2382BM = false;

    /* renamed from: zM */
    private String f2383zM;

    public C1501h(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qa */
    public String mo8488Qa(String str) {
        return str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qk */
    public Set mo8489Qk() {
        return VCardParser_V30.sKnownPropertyNameSet;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ra */
    public String mo8490Ra(String str) {
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

    /* access modifiers changed from: protected */
    /* renamed from: Rk */
    public String mo8491Rk() {
        String readLine;
        String str = null;
        StringBuilder sb = null;
        while (true) {
            readLine = this.mReader.readLine();
            if (readLine == null) {
                break;
            } else if (readLine.length() != 0) {
                if (readLine.charAt(0) != ' ' && readLine.charAt(0) != 9) {
                    if (sb != null || this.f2383zM != null) {
                        break;
                    }
                    this.f2383zM = readLine;
                } else {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    String str2 = this.f2383zM;
                    if (str2 != null) {
                        sb.append(str2);
                        this.f2383zM = null;
                    }
                    sb.append(readLine.substring(1));
                }
            }
        }
        if (sb != null) {
            str = sb.toString();
        } else {
            String str3 = this.f2383zM;
            if (str3 != null) {
                str = str3;
            }
        }
        this.f2383zM = readLine;
        if (str != null) {
            return str;
        }
        throw new VCardException("Reached end of buffer.");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sa */
    public String mo8508Sa(String str) {
        return VCardUtils.convertStringCharset(str, VCardConfig.DEFAULT_INTERMEDIATE_CHARSET, "UTF-8");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sk */
    public String mo8492Sk() {
        return VCardConstants.VERSION_V30;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8497a(VCardProperty vCardProperty, String str, String str2) {
        m3926b(vCardProperty, str, str2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8499b(VCardProperty vCardProperty, String str) {
        try {
            super.mo8499b(vCardProperty, str);
        } catch (VCardException unused) {
            String[] split = str.split("=", 2);
            if (split.length == 2) {
                m3926b(vCardProperty, split[0], split[1]);
                return;
            }
            throw new VCardException(C0632a.m1025k("Unknown params value: ", str));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo8500c(VCardProperty vCardProperty, String str) {
        m3926b(vCardProperty, VCardConstants.PARAM_TYPE, str);
    }

    /* access modifiers changed from: protected */
    public String getLine() {
        String str = this.f2383zM;
        if (str == null) {
            return this.mReader.readLine();
        }
        this.f2383zM = null;
        return str;
    }

    /* access modifiers changed from: protected */
    public int getVersion() {
        return 1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: oa */
    public boolean mo8504oa(boolean z) {
        boolean z2;
        while (true) {
            String line = getLine();
            z2 = false;
            if (line == null) {
                break;
            } else if (line.trim().length() > 0) {
                String[] split = line.split(":", 2);
                if (split.length == 2 && split[0].trim().equalsIgnoreCase(VCardConstants.PROPERTY_BEGIN)) {
                    z2 = true;
                    if (split[1].trim().equalsIgnoreCase("VCARD")) {
                        break;
                    }
                }
                if (!z) {
                    throw new VCardException(C0632a.m1023d("Expected String \"BEGIN:VCARD\" did not come (Instead, \"", line, "\" came)"));
                } else if (!z) {
                    throw new VCardException("Reached where must not be reached.");
                }
            }
        }
        return z2;
    }

    /* access modifiers changed from: protected */
    public String peekLine() {
        String str = this.f2383zM;
        if (str != null) {
            return str;
        }
        return this.mReader.peekLine();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8496a(VCardProperty vCardProperty, String str) {
        m3926b(vCardProperty, VCardConstants.PARAM_TYPE, str);
    }

    public C1501h() {
        super(VCardConfig.VCARD_TYPE_DEFAULT);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8495a(VCardProperty vCardProperty) {
        if (!this.f2382BM) {
            Log.w("vCard", "AGENT in vCard 3.0 is not supported yet. Ignore it");
            this.f2382BM = true;
        }
    }

    /* renamed from: b */
    private void m3926b(VCardProperty vCardProperty, String str, String str2) {
        int length = str2.length();
        boolean z = false;
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char charAt = str2.charAt(i);
            if (charAt == '\"') {
                if (z) {
                    vCardProperty.addParameter(str, mo8508Sa(sb.toString()));
                    z = false;
                } else {
                    if (sb != null) {
                        if (sb.length() > 0) {
                            Log.w("vCard", "Unexpected Dquote inside property.");
                        } else {
                            vCardProperty.addParameter(str, mo8508Sa(sb.toString()));
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
                vCardProperty.addParameter(str, mo8508Sa(sb.toString()));
            }
            sb = null;
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
            vCardProperty.addParameter(str, mo8508Sa(sb.toString()));
        }
    }
}

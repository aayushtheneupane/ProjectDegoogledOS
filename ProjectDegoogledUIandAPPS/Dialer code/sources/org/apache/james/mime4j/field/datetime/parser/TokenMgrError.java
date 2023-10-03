package org.apache.james.mime4j.field.datetime.parser;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public class TokenMgrError extends Error {
    private static final long serialVersionUID = 1;
    int errorCode;

    public TokenMgrError() {
    }

    protected static final String addEscapes(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != 0) {
                if (charAt == '\"') {
                    stringBuffer.append("\\\"");
                } else if (charAt == '\'') {
                    stringBuffer.append("\\'");
                } else if (charAt == '\\') {
                    stringBuffer.append("\\\\");
                } else if (charAt == 12) {
                    stringBuffer.append("\\f");
                } else if (charAt != 13) {
                    switch (charAt) {
                        case 8:
                            stringBuffer.append("\\b");
                            break;
                        case 9:
                            stringBuffer.append("\\t");
                            break;
                        case 10:
                            stringBuffer.append("\\n");
                            break;
                        default:
                            char charAt2 = str.charAt(i);
                            if (charAt2 >= ' ' && charAt2 <= '~') {
                                stringBuffer.append(charAt2);
                                break;
                            } else {
                                StringBuilder outline13 = GeneratedOutlineSupport.outline13("0000");
                                outline13.append(Integer.toString(charAt2, 16));
                                String sb = outline13.toString();
                                StringBuilder outline132 = GeneratedOutlineSupport.outline13("\\u");
                                outline132.append(sb.substring(sb.length() - 4, sb.length()));
                                stringBuffer.append(outline132.toString());
                                break;
                            }
                    }
                } else {
                    stringBuffer.append("\\r");
                }
            }
        }
        return stringBuffer.toString();
    }

    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError(String str, int i) {
        super(str);
        this.errorCode = i;
    }
}

package org.apache.james.mime4j.field.datetime.parser;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ParseException extends org.apache.james.mime4j.dom.field.ParseException {
    private static final long serialVersionUID = 1;
    public Token currentToken;
    protected String eol = System.getProperty("line.separator", "\n");
    public int[][] expectedTokenSequences;
    protected boolean specialConstructor = false;
    public String[] tokenImage;

    public ParseException(Token token, int[][] iArr, String[] strArr) {
        super("");
        this.currentToken = token;
        this.expectedTokenSequences = iArr;
        this.tokenImage = strArr;
    }

    /* access modifiers changed from: protected */
    public String add_escapes(String str) {
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
        String str;
        int[][] iArr;
        if (!this.specialConstructor) {
            return super.getMessage();
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int i2 = 0;
        while (true) {
            int[][] iArr2 = this.expectedTokenSequences;
            if (i >= iArr2.length) {
                break;
            }
            if (i2 < iArr2[i].length) {
                i2 = iArr2[i].length;
            }
            int i3 = 0;
            while (true) {
                iArr = this.expectedTokenSequences;
                if (i3 >= iArr[i].length) {
                    break;
                }
                stringBuffer.append(this.tokenImage[iArr[i][i3]]);
                stringBuffer.append(" ");
                i3++;
            }
            if (iArr[i][iArr[i].length - 1] != 0) {
                stringBuffer.append("...");
            }
            stringBuffer.append(this.eol);
            stringBuffer.append("    ");
            i++;
        }
        String str2 = "Encountered \"";
        Token token = this.currentToken.next;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                break;
            }
            if (i4 != 0) {
                str2 = GeneratedOutlineSupport.outline8(str2, " ");
            }
            if (token.kind == 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13(str2);
                outline13.append(this.tokenImage[0]);
                str2 = outline13.toString();
                break;
            }
            StringBuilder outline132 = GeneratedOutlineSupport.outline13(str2);
            outline132.append(add_escapes(token.image));
            str2 = outline132.toString();
            token = token.next;
            i4++;
        }
        StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, "\" at line ");
        outline14.append(this.currentToken.next.beginLine);
        outline14.append(", column ");
        outline14.append(this.currentToken.next.beginColumn);
        StringBuilder outline142 = GeneratedOutlineSupport.outline14(outline14.toString(), ".");
        outline142.append(this.eol);
        String sb = outline142.toString();
        if (this.expectedTokenSequences.length == 1) {
            str = GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline14(sb, "Was expecting:"), this.eol, "    ");
        } else {
            str = GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline14(sb, "Was expecting one of:"), this.eol, "    ");
        }
        StringBuilder outline133 = GeneratedOutlineSupport.outline13(str);
        outline133.append(stringBuffer.toString());
        return outline133.toString();
    }

    public ParseException() {
        super("Cannot parse field");
    }

    public ParseException(String str) {
        super(str);
    }
}

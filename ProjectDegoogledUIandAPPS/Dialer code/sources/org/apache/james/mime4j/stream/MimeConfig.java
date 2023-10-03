package org.apache.james.mime4j.stream;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class MimeConfig implements Cloneable {
    private boolean countLineNumbers = false;
    private String headlessParsing = null;
    private boolean malformedHeaderStartsBody = false;
    private long maxContentLen = -1;
    private int maxHeaderCount = 1000;
    private int maxHeaderLen = 10000;
    private int maxLineLen = 1000;
    private boolean strictParsing = false;

    public Object clone() throws CloneNotSupportedException {
        try {
            return (MimeConfig) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public String getHeadlessParsing() {
        return this.headlessParsing;
    }

    public long getMaxContentLen() {
        return this.maxContentLen;
    }

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    public int getMaxHeaderLen() {
        return this.maxHeaderLen;
    }

    public int getMaxLineLen() {
        return this.maxLineLen;
    }

    public boolean isCountLineNumbers() {
        return this.countLineNumbers;
    }

    public boolean isMalformedHeaderStartsBody() {
        return this.malformedHeaderStartsBody;
    }

    public boolean isStrictParsing() {
        return this.strictParsing;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[strict parsing: ");
        outline13.append(this.strictParsing);
        outline13.append(", max line length: ");
        outline13.append(this.maxLineLen);
        outline13.append(", max header count: ");
        outline13.append(this.maxHeaderCount);
        outline13.append(", max content length: ");
        outline13.append(this.maxContentLen);
        outline13.append(", count line numbers: ");
        outline13.append(this.countLineNumbers);
        outline13.append("]");
        return outline13.toString();
    }
}

package org.apache.james.mime4j.stream;

import android.support.p002v7.appcompat.R$style;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.james.mime4j.util.MimeUtil;

public final class RawField implements Field {
    private final String body;
    private final int delimiterIdx;
    private final String name;
    private final ByteSequence raw;

    RawField(ByteSequence byteSequence, int i, String str, String str2) {
        if (str != null) {
            this.raw = byteSequence;
            this.delimiterIdx = i;
            this.name = str.trim();
            this.body = str2;
            return;
        }
        throw new IllegalArgumentException("Field may not be null");
    }

    public String getBody() {
        String str = this.body;
        if (str != null) {
            return str;
        }
        ByteSequence byteSequence = this.raw;
        if (byteSequence == null) {
            return null;
        }
        int length = byteSequence.length();
        int i = this.delimiterIdx + 1;
        int i2 = i + 1;
        if (length > i2 && CharsetUtil.isWhitespace((char) (this.raw.byteAt(i) & 255))) {
            i = i2;
        }
        return MimeUtil.unfold(R$style.decode(this.raw, i, length - i));
    }

    public int getDelimiterIdx() {
        return this.delimiterIdx;
    }

    public String getName() {
        return this.name;
    }

    public ByteSequence getRaw() {
        return this.raw;
    }

    public String toString() {
        ByteSequence byteSequence = this.raw;
        if (byteSequence == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(": ");
            String str = this.body;
            if (str != null) {
                sb.append(str);
            }
            return sb.toString();
        } else if (byteSequence == null) {
            return null;
        } else {
            return R$style.decode(byteSequence, 0, byteSequence.length());
        }
    }
}

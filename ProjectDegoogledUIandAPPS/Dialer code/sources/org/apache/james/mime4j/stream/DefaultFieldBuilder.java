package org.apache.james.mime4j.stream;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.BitSet;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.p012io.MaxHeaderLengthLimitException;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class DefaultFieldBuilder implements FieldBuilder {
    private static final BitSet FIELD_CHARS = new BitSet();
    private final ByteArrayBuffer buf = new ByteArrayBuffer(1024);
    private final int maxlen;

    static {
        for (int i = 33; i <= 57; i++) {
            FIELD_CHARS.set(i);
        }
        for (int i2 = 59; i2 <= 126; i2++) {
            FIELD_CHARS.set(i2);
        }
    }

    public DefaultFieldBuilder(int i) {
        this.maxlen = i;
    }

    public void append(ByteArrayBuffer byteArrayBuffer) throws MaxHeaderLengthLimitException {
        if (byteArrayBuffer != null) {
            int length = byteArrayBuffer.length();
            if (this.maxlen <= 0 || this.buf.length() + length < this.maxlen) {
                this.buf.append(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
                return;
            }
            throw new MaxHeaderLengthLimitException("Maximum header length limit exceeded");
        }
    }

    public RawField build() throws MimeException {
        int length = this.buf.length();
        if (length > 0) {
            if (this.buf.byteAt(length - 1) == 10) {
                length--;
            }
            if (this.buf.byteAt(length - 1) == 13) {
                length--;
            }
        }
        int i = 0;
        RawField parseField = RawFieldParser.DEFAULT.parseField(new ByteArrayBuffer(this.buf.buffer(), length, false));
        String name = parseField.getName();
        while (i < name.length()) {
            if (FIELD_CHARS.get(name.charAt(i))) {
                i++;
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("MIME field name contains illegal characters: ");
                outline13.append(parseField.getName());
                throw new MimeException(outline13.toString());
            }
        }
        return parseField;
    }

    public ByteArrayBuffer getRaw() {
        return this.buf;
    }

    public void reset() {
        this.buf.clear();
    }
}

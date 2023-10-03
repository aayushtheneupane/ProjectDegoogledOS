package com.android.voicemail.impl.mail.store.imap;

import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.FixedLengthInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ImapMemoryLiteral extends ImapString {
    private byte[] data;

    ImapMemoryLiteral(FixedLengthInputStream fixedLengthInputStream) throws IOException {
        int read;
        this.data = new byte[fixedLengthInputStream.getLength()];
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i < bArr.length && (read = fixedLengthInputStream.read(bArr, i, bArr.length - i)) >= 0) {
                i += read;
            }
        }
        if (i != this.data.length) {
            VvmLog.m46w("ImapMemoryLiteral", "length mismatch");
        }
    }

    public void destroy() {
        this.data = null;
        super.destroy();
    }

    public InputStream getAsStream() {
        return new ByteArrayInputStream(this.data);
    }

    public String getString() {
        try {
            return new String(this.data, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            VvmLog.m44e("ImapMemoryLiteral", "Unsupported encoding: ", e);
            return null;
        }
    }

    public String toString() {
        return String.format("{%d byte literal(memory)}", new Object[]{Integer.valueOf(this.data.length)});
    }
}

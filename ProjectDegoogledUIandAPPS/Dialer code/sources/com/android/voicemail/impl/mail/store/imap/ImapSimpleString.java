package com.android.voicemail.impl.mail.store.imap;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ImapSimpleString extends ImapString {
    private String string;

    ImapSimpleString(String str) {
        this.string = str == null ? "" : str;
    }

    public void destroy() {
        this.string = null;
        super.destroy();
    }

    public InputStream getAsStream() {
        try {
            return new ByteArrayInputStream(this.string.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            VvmLog.m44e("ImapSimpleString", "Unsupported encoding: ", e);
            return null;
        }
    }

    public String getString() {
        return this.string;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("\""), this.string, "\"");
    }
}

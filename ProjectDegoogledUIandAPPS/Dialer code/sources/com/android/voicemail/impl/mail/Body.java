package com.android.voicemail.impl.mail;

import java.io.IOException;
import java.io.OutputStream;

public interface Body {
    void writeTo(OutputStream outputStream) throws IOException, MessagingException;
}

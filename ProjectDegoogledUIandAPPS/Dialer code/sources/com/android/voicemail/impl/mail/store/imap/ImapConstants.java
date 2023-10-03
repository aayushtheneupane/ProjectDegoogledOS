package com.android.voicemail.impl.mail.store.imap;

import java.util.Locale;

public final class ImapConstants {
    public static final String FETCH_FIELD_BODY_PEEK_SANE = String.format(Locale.US, "BODY.PEEK[]<0.%d>", new Object[]{128000});
}

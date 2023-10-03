package com.android.voicemail.impl.mail.store.imap;

import com.android.voicemail.impl.VvmLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class ImapString extends ImapElement {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss Z", Locale.US);
    public static final ImapString EMPTY = new ImapString() {
        public void destroy() {
        }

        public InputStream getAsStream() {
            return new ByteArrayInputStream(ImapString.EMPTY_BYTES);
        }

        public String getString() {
            return "";
        }

        public String toString() {
            return "";
        }
    };
    /* access modifiers changed from: private */
    public static final byte[] EMPTY_BYTES = new byte[0];
    private boolean isInteger;
    private Date parsedDate;
    private int parsedInteger;

    public abstract InputStream getAsStream();

    public final Date getDateOrNull() {
        boolean z = true;
        if (this.parsedDate == null) {
            if (!isEmpty()) {
                try {
                    this.parsedDate = DATE_TIME_FORMAT.parse(getString());
                } catch (ParseException unused) {
                    VvmLog.m46w("ImapString", getString() + " can't be parsed as a date.");
                }
            }
            z = false;
        }
        if (!z) {
            return null;
        }
        return this.parsedDate;
    }

    public final int getNumber(int i) {
        boolean z = true;
        if (!this.isInteger) {
            try {
                this.parsedInteger = Integer.parseInt(getString());
                this.isInteger = true;
            } catch (NumberFormatException unused) {
                z = false;
            }
        }
        if (!z) {
            return i;
        }
        return this.parsedInteger;
    }

    public final int getNumberOrZero() {
        return getNumber(0);
    }

    public abstract String getString();

    /* renamed from: is */
    public final boolean mo9269is(String str) {
        if (str == null) {
            return false;
        }
        return getString().equalsIgnoreCase(str);
    }

    public final boolean isEmpty() {
        return getString().length() == 0;
    }

    public final boolean isList() {
        return false;
    }

    public final boolean isString() {
        return true;
    }

    public final boolean startsWith(String str) {
        if (str == null) {
            return false;
        }
        String string = getString();
        if (string.length() < str.length()) {
            return false;
        }
        return string.substring(0, str.length()).equalsIgnoreCase(str);
    }
}

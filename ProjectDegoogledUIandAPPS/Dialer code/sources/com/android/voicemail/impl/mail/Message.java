package com.android.voicemail.impl.mail;

import java.util.Date;
import java.util.HashSet;

public abstract class Message implements Part, Body {
    public static final Message[] EMPTY_ARRAY = new Message[0];
    private HashSet<String> flags = null;
    protected String uid;

    private HashSet<String> getFlagSet() {
        if (this.flags == null) {
            this.flags = new HashSet<>();
        }
        return this.flags;
    }

    private final void setFlagDirectlyForTest(String str, boolean z) throws MessagingException {
        if (z) {
            getFlagSet().add(str);
        } else {
            getFlagSet().remove(str);
        }
    }

    public abstract Long getDuration() throws MessagingException;

    public String[] getFlags() {
        return (String[]) getFlagSet().toArray(new String[0]);
    }

    public abstract Address[] getFrom() throws MessagingException;

    public abstract Date getSentDate() throws MessagingException;

    public String getUid() {
        return this.uid;
    }

    public void setFlag(String str, boolean z) throws MessagingException {
        setFlagDirectlyForTest(str, z);
    }

    public void setInternalDate(Date date) {
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String toString() {
        return getClass().getSimpleName() + ':' + this.uid;
    }
}

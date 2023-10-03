package com.android.voicemail.impl.mail.store.imap;

public abstract class ImapElement {
    public static final ImapElement NONE = new ImapElement() {
        public void destroy() {
        }

        public boolean isList() {
            return false;
        }

        public boolean isString() {
            return false;
        }

        public String toString() {
            return "[NO ELEMENT]";
        }
    };
    private boolean destroyed = false;

    /* access modifiers changed from: protected */
    public final void checkNotDestroyed() {
        if (this.destroyed) {
            throw new RuntimeException("Already destroyed");
        }
    }

    public void destroy() {
        this.destroyed = true;
    }

    /* access modifiers changed from: protected */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    public abstract boolean isList();

    public abstract boolean isString();
}

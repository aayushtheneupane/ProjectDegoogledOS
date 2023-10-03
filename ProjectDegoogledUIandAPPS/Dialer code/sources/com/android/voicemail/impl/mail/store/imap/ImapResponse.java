package com.android.voicemail.impl.mail.store.imap;

public class ImapResponse extends ImapList {
    private final boolean isContinuationRequest;
    private final String tag;

    ImapResponse(String str, boolean z) {
        this.tag = str;
        this.isContinuationRequest = z;
    }

    public ImapString getResponseCodeOrEmpty() {
        if (!isStatusResponse()) {
            return ImapString.EMPTY;
        }
        return getListOrEmpty(1).getStringOrEmpty(0);
    }

    public ImapString getStatusResponseTextOrEmpty() {
        if (!isStatusResponse()) {
            return ImapString.EMPTY;
        }
        int i = 1;
        if (getElementOrNone(1).isList()) {
            i = 2;
        }
        return getStringOrEmpty(i);
    }

    public boolean isContinuationRequest() {
        return this.isContinuationRequest;
    }

    public final boolean isDataResponse(int i, String str) {
        return !isTagged() && getStringOrEmpty(i).mo9269is(str);
    }

    public boolean isOk() {
        return mo9247is(0, "OK");
    }

    public boolean isStatusResponse() {
        String string = getStringOrEmpty(0).getString();
        if ("OK".equalsIgnoreCase(string) || "NO".equalsIgnoreCase(string) || "BAD".equalsIgnoreCase(string) || "PREAUTH".equalsIgnoreCase(string) || "BYE".equalsIgnoreCase(string)) {
            return true;
        }
        return false;
    }

    public boolean isTagged() {
        return this.tag != null;
    }

    public String toString() {
        String str = this.tag;
        if (this.isContinuationRequest) {
            str = "+";
        }
        return "#" + str + "# " + super.toString();
    }
}

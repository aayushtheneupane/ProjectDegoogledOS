package com.android.vcard.exception;

public class VCardNestedException extends VCardNotSupportedException {
    public VCardNestedException() {
    }

    public VCardNestedException(String str) {
        super(str);
    }
}

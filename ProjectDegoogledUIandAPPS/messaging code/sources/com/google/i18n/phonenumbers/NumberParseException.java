package com.google.i18n.phonenumbers;

import p026b.p027a.p030b.p031a.C0632a;

public class NumberParseException extends Exception {
    private ErrorType errorType;
    private String message;

    public enum ErrorType {
        INVALID_COUNTRY_CODE,
        NOT_A_NUMBER,
        TOO_SHORT_AFTER_IDD,
        TOO_SHORT_NSN,
        TOO_LONG
    }

    public NumberParseException(ErrorType errorType2, String str) {
        super(str);
        this.message = str;
        this.errorType = errorType2;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Error type: ");
        Pa.append(this.errorType);
        Pa.append(". ");
        Pa.append(this.message);
        return Pa.toString();
    }
}

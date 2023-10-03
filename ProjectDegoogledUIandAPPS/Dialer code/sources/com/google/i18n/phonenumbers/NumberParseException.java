package com.google.i18n.phonenumbers;

import com.android.tools.p006r8.GeneratedOutlineSupport;

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
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error type: ");
        outline13.append(this.errorType);
        outline13.append(". ");
        outline13.append(this.message);
        return outline13.toString();
    }
}

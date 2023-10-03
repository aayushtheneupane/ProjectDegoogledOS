package com.android.voicemail;

public interface PinChanger {

    public static class PinSpecification {
        public int maxLength;
        public int minLength;
    }

    int changePin(String str, String str2);

    PinSpecification getPinSpecification();

    String getScrambledPin();

    void setScrambledPin(String str);
}

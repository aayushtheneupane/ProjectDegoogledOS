package com.android.voicemail.impl.mail;

public interface Part extends Fetchable {
    void addHeader(String str, String str2) throws MessagingException;

    Body getBody() throws MessagingException;

    String getContentType() throws MessagingException;

    String[] getHeader(String str) throws MessagingException;

    String getMimeType() throws MessagingException;

    int getSize() throws MessagingException;

    void setBody(Body body) throws MessagingException;

    void setHeader(String str, String str2) throws MessagingException;
}

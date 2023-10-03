package com.android.voicemail.impl.mail;

import java.util.ArrayList;

public abstract class Multipart implements Body {
    protected ArrayList<BodyPart> parts = new ArrayList<>();

    public void addBodyPart(BodyPart bodyPart) throws MessagingException {
        this.parts.add(bodyPart);
    }

    public BodyPart getBodyPart(int i) throws MessagingException {
        return this.parts.get(i);
    }

    public int getCount() throws MessagingException {
        return this.parts.size();
    }

    public void setParent(Part part) throws MessagingException {
    }
}

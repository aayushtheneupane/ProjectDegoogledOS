package com.android.voicemail.impl.mail;

import java.util.ArrayList;

public class FetchProfile extends ArrayList<Fetchable> {

    public enum Item implements Fetchable {
        FLAGS,
        ENVELOPE,
        STRUCTURE,
        BODY_SANE,
        BODY
    }
}

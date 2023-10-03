package com.android.vcard;

import com.android.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class VCardParser_V21 extends VCardParser {
    static final Set<String> sAvailableEncoding = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"7BIT", "8BIT", "QUOTED-PRINTABLE", "BASE64", "B"})));
    static final Set<String> sKnownPropertyNameSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER"})));
    static final Set<String> sKnownTypeSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"DOM", "INTL", "POSTAL", "PARCEL", "HOME", "WORK", "PREF", "VOICE", "FAX", "MSG", "CELL", "PAGER", "BBS", "MODEM", "CAR", "ISDN", "VIDEO", "AOL", "APPLELINK", "ATTMAIL", "CIS", "EWORLD", "INTERNET", "IBMMAIL", "MCIMAIL", "POWERSHARE", "PRODIGY", "TLX", "X400", "GIF", "CGM", "WMF", "BMP", "MET", "PMB", "DIB", "PICT", "TIFF", "PDF", "PS", "JPEG", "QTIME", "MPEG", "MPEG2", "AVI", "WAVE", "AIFF", "PCM", "X509", "PGP"})));
    static final Set<String> sKnownValueSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"INLINE", "URL", "CONTENT-ID", "CID"})));
    private final VCardParserImpl_V21 mVCardParserImpl;

    public VCardParser_V21() {
        this.mVCardParserImpl = new VCardParserImpl_V21();
    }

    public VCardParser_V21(int i) {
        this.mVCardParserImpl = new VCardParserImpl_V21(i);
    }

    public void addInterpreter(VCardInterpreter vCardInterpreter) {
        this.mVCardParserImpl.addInterpreter(vCardInterpreter);
    }

    public void parse(InputStream inputStream) throws IOException, VCardException {
        this.mVCardParserImpl.parse(inputStream);
    }

    public void cancel() {
        this.mVCardParserImpl.cancel();
    }
}

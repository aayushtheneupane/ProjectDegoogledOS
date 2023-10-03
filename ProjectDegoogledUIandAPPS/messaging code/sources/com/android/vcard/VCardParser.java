package com.android.vcard;

import java.io.InputStream;

public abstract class VCardParser {
    public abstract void addInterpreter(VCardInterpreter vCardInterpreter);

    public abstract void cancel();

    public abstract void parse(InputStream inputStream);

    @Deprecated
    public void parse(InputStream inputStream, VCardInterpreter vCardInterpreter) {
        addInterpreter(vCardInterpreter);
        parse(inputStream);
    }

    public abstract void parseOne(InputStream inputStream);
}

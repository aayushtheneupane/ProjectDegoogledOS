package com.android.vcard;

import java.io.BufferedReader;
import java.io.Reader;

public final class VCardParserImpl_V21$CustomBufferedReader extends BufferedReader {
    private String mNextLine;
    private boolean mNextLineIsValid;
    private long mTime;

    public VCardParserImpl_V21$CustomBufferedReader(Reader reader) {
        super(reader);
    }

    public long getTotalmillisecond() {
        return this.mTime;
    }

    public String peekLine() {
        if (!this.mNextLineIsValid) {
            long currentTimeMillis = System.currentTimeMillis();
            String readLine = super.readLine();
            this.mTime = (System.currentTimeMillis() - currentTimeMillis) + this.mTime;
            this.mNextLine = readLine;
            this.mNextLineIsValid = true;
        }
        return this.mNextLine;
    }

    public String readLine() {
        if (this.mNextLineIsValid) {
            String str = this.mNextLine;
            this.mNextLine = null;
            this.mNextLineIsValid = false;
            return str;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String readLine = super.readLine();
        this.mTime = (System.currentTimeMillis() - currentTimeMillis) + this.mTime;
        return readLine;
    }
}

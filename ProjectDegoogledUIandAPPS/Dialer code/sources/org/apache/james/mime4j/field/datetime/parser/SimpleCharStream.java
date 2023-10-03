package org.apache.james.mime4j.field.datetime.parser;

import java.io.IOException;
import java.io.Reader;

public class SimpleCharStream {
    int available;
    protected int[] bufcolumn;
    protected char[] buffer;
    protected int[] bufline;
    public int bufpos = -1;
    int bufsize;
    protected int column = 0;
    protected int inBuf = 0;
    protected Reader inputStream;
    protected int line = 1;
    protected int maxNextCharInd = 0;
    protected boolean prevCharIsCR = false;
    protected boolean prevCharIsLF = false;
    protected int tabSize = 8;
    int tokenBegin;

    public SimpleCharStream(Reader reader, int i, int i2) {
        this.inputStream = reader;
        this.line = i;
        this.column = i2 - 1;
        this.bufsize = 4096;
        this.available = 4096;
        this.buffer = new char[4096];
        this.bufline = new int[4096];
        this.bufcolumn = new int[4096];
    }

    /* access modifiers changed from: protected */
    public void ExpandBuff(boolean z) {
        int i = this.bufsize;
        char[] cArr = new char[(i + 2048)];
        int[] iArr = new int[(i + 2048)];
        int[] iArr2 = new int[(i + 2048)];
        if (z) {
            try {
                System.arraycopy(this.buffer, this.tokenBegin, cArr, 0, i - this.tokenBegin);
                System.arraycopy(this.buffer, 0, cArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.buffer = cArr;
                System.arraycopy(this.bufline, this.tokenBegin, iArr, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufline, 0, iArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufline = iArr;
                System.arraycopy(this.bufcolumn, this.tokenBegin, iArr2, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufcolumn, 0, iArr2, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufcolumn = iArr2;
                int i2 = (this.bufsize - this.tokenBegin) + this.bufpos;
                this.bufpos = i2;
                this.maxNextCharInd = i2;
            } catch (Throwable th) {
                throw new Error(th.getMessage());
            }
        } else {
            System.arraycopy(this.buffer, this.tokenBegin, cArr, 0, i - this.tokenBegin);
            this.buffer = cArr;
            System.arraycopy(this.bufline, this.tokenBegin, iArr, 0, this.bufsize - this.tokenBegin);
            this.bufline = iArr;
            System.arraycopy(this.bufcolumn, this.tokenBegin, iArr2, 0, this.bufsize - this.tokenBegin);
            this.bufcolumn = iArr2;
            int i3 = this.bufpos - this.tokenBegin;
            this.bufpos = i3;
            this.maxNextCharInd = i3;
        }
        this.bufsize += 2048;
        this.available = this.bufsize;
        this.tokenBegin = 0;
    }

    public String GetImage() {
        int i = this.bufpos;
        int i2 = this.tokenBegin;
        if (i >= i2) {
            return new String(this.buffer, i2, (i - i2) + 1);
        }
        StringBuilder sb = new StringBuilder();
        char[] cArr = this.buffer;
        int i3 = this.tokenBegin;
        sb.append(new String(cArr, i3, this.bufsize - i3));
        sb.append(new String(this.buffer, 0, this.bufpos + 1));
        return sb.toString();
    }

    public char[] GetSuffix(int i) {
        char[] cArr = new char[i];
        int i2 = this.bufpos;
        if (i2 + 1 >= i) {
            System.arraycopy(this.buffer, (i2 - i) + 1, cArr, 0, i);
        } else {
            System.arraycopy(this.buffer, this.bufsize - ((i - i2) - 1), cArr, 0, (i - i2) - 1);
            char[] cArr2 = this.buffer;
            int i3 = this.bufpos;
            System.arraycopy(cArr2, 0, cArr, (i - i3) - 1, i3 + 1);
        }
        return cArr;
    }

    public void backup(int i) {
        this.inBuf += i;
        int i2 = this.bufpos - i;
        this.bufpos = i2;
        if (i2 < 0) {
            this.bufpos += this.bufsize;
        }
    }

    public char readChar() throws IOException {
        int i = this.inBuf;
        if (i > 0) {
            this.inBuf = i - 1;
            int i2 = this.bufpos + 1;
            this.bufpos = i2;
            if (i2 == this.bufsize) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        int i3 = this.bufpos + 1;
        this.bufpos = i3;
        int i4 = this.maxNextCharInd;
        if (i3 >= i4) {
            int i5 = this.available;
            if (i4 == i5) {
                int i6 = this.bufsize;
                if (i5 == i6) {
                    int i7 = this.tokenBegin;
                    if (i7 > 2048) {
                        this.maxNextCharInd = 0;
                        this.bufpos = 0;
                        this.available = i7;
                    } else if (i7 < 0) {
                        this.maxNextCharInd = 0;
                        this.bufpos = 0;
                    } else {
                        ExpandBuff(false);
                    }
                } else {
                    int i8 = this.tokenBegin;
                    if (i5 > i8) {
                        this.available = i6;
                    } else if (i8 - i5 < 2048) {
                        ExpandBuff(true);
                    } else {
                        this.available = i8;
                    }
                }
            }
            try {
                int read = this.inputStream.read(this.buffer, this.maxNextCharInd, this.available - this.maxNextCharInd);
                if (read != -1) {
                    this.maxNextCharInd += read;
                } else {
                    this.inputStream.close();
                    throw new IOException();
                }
            } catch (IOException e) {
                this.bufpos--;
                backup(0);
                if (this.tokenBegin == -1) {
                    this.tokenBegin = this.bufpos;
                }
                throw e;
            }
        }
        char c = this.buffer[this.bufpos];
        this.column++;
        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            int i9 = this.line;
            this.column = 1;
            this.line = i9 + 1;
        } else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == 10) {
                this.prevCharIsLF = true;
            } else {
                int i10 = this.line;
                this.column = 1;
                this.line = i10 + 1;
            }
        }
        if (c == 9) {
            this.column--;
            int i11 = this.column;
            int i12 = this.tabSize;
            this.column = (i12 - (i11 % i12)) + i11;
        } else if (c == 10) {
            this.prevCharIsLF = true;
        } else if (c == 13) {
            this.prevCharIsCR = true;
        }
        int[] iArr = this.bufline;
        int i13 = this.bufpos;
        iArr[i13] = this.line;
        this.bufcolumn[i13] = this.column;
        return c;
    }
}

package p000;

import java.io.PushbackReader;
import java.io.Reader;

/* renamed from: anq */
/* compiled from: PG */
public final class anq extends PushbackReader {

    /* renamed from: a */
    private int f1204a = 0;

    /* renamed from: b */
    private int f1205b = 0;

    /* renamed from: c */
    private int f1206c = 0;

    public anq(Reader reader) {
        super(reader, 8);
    }

    public final int read(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[8];
        int i3 = 1;
        int i4 = i;
        boolean z = true;
        int i5 = 0;
        int i6 = 0;
        while (z && i5 < i2) {
            if (super.read(cArr2, i6, i3) == i3) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                char c = cArr2[i6];
                int i7 = this.f1204a;
                if (i7 != 0) {
                    if (i7 != i3) {
                        if (i7 != 2) {
                            if (i7 != 3) {
                                if (i7 != 4) {
                                    this.f1204a = 0;
                                    i3 = 1;
                                    i7 = 0;
                                } else if (c >= '0' && c <= '9') {
                                    this.f1205b = (this.f1205b * 10) + Character.digit(c, 10);
                                    int i8 = this.f1206c + 1;
                                    this.f1206c = i8;
                                    if (i8 <= 5) {
                                        this.f1204a = 4;
                                        i3 = 1;
                                        i7 = 4;
                                    } else {
                                        this.f1204a = 5;
                                        i3 = 1;
                                        i7 = 5;
                                    }
                                } else if (c != ';' || !ant.m1193a((char) this.f1205b)) {
                                    this.f1204a = 5;
                                    i3 = 1;
                                    i7 = 5;
                                } else {
                                    this.f1204a = 0;
                                    c = (char) this.f1205b;
                                    i3 = 1;
                                    i7 = 0;
                                }
                            } else if ((c >= '0' && c <= '9') || ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                                this.f1205b = (this.f1205b << 4) + Character.digit(c, 16);
                                int i9 = this.f1206c + 1;
                                this.f1206c = i9;
                                if (i9 <= 4) {
                                    this.f1204a = 3;
                                    i3 = 1;
                                    i7 = 3;
                                } else {
                                    this.f1204a = 5;
                                    i3 = 1;
                                    i7 = 5;
                                }
                            } else if (c != ';' || !ant.m1193a((char) this.f1205b)) {
                                this.f1204a = 5;
                                i3 = 1;
                                i7 = 5;
                            } else {
                                this.f1204a = 0;
                                c = (char) this.f1205b;
                                i3 = 1;
                                i7 = 0;
                            }
                        } else if (c == 'x') {
                            this.f1205b = 0;
                            this.f1206c = 0;
                            this.f1204a = 3;
                            i3 = 1;
                            i7 = 3;
                        } else if (c >= '0' && c <= '9') {
                            this.f1205b = Character.digit(c, 10);
                            this.f1206c = 1;
                            this.f1204a = 4;
                            i3 = 1;
                            i7 = 4;
                        } else {
                            this.f1204a = 5;
                            i3 = 1;
                            i7 = 5;
                        }
                    } else if (c != '#') {
                        this.f1204a = 5;
                        i3 = 1;
                        i7 = 5;
                    } else {
                        this.f1204a = 2;
                        i3 = 1;
                        i7 = 2;
                    }
                } else if (c == '&') {
                    i3 = 1;
                    this.f1204a = 1;
                    i7 = 1;
                } else {
                    i3 = 1;
                }
                if (i7 == 0) {
                    if (ant.m1193a(c)) {
                        c = ' ';
                    }
                    cArr[i4] = c;
                    i5++;
                    i4++;
                    i6 = 0;
                } else if (i7 == 5) {
                    unread(cArr2, 0, i6 + 1);
                    i6 = 0;
                } else {
                    i6++;
                }
            } else if (i6 > 0) {
                unread(cArr2, 0, i6);
                this.f1204a = 5;
                z = true;
                i6 = 0;
            }
        }
        if (i5 > 0 || z) {
            return i5;
        }
        return -1;
    }
}

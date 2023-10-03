package org.apache.james.mime4j.field.datetime.parser;

import java.io.IOException;
import java.io.PrintStream;

public class DateTimeParserTokenManager implements DateTimeParserConstants {
    static int commentNest;
    static final long[] jjbitVec0 = {0, 0, -1, -1};
    public static final int[] jjnewLexState = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1};
    public static final String[] jjstrLiteralImages = {"", "\r", "\n", ",", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", ":", null, "UT", "GMT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT", null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    static final long[] jjtoSkip = {343597383680L};
    static final long[] jjtoSpecial = {68719476736L};
    static final long[] jjtoToken = {70437463654399L};
    protected char curChar;
    int curLexState = 0;
    private StringBuilder image = this.jjimage;
    protected SimpleCharStream input_stream;
    private final StringBuilder jjimage = new StringBuilder();
    private int jjimageLen;
    int jjmatchedKind;
    int jjmatchedPos;
    int jjnewStateCnt;
    int jjround;
    private final int[] jjrounds = new int[4];
    private final int[] jjstateSet = new int[8];

    static {
        new String[]{"DEFAULT", "INCOMMENT", "NESTED_COMMENT"};
        new long[1][0] = 69956427317248L;
    }

    public DateTimeParserTokenManager(SimpleCharStream simpleCharStream) {
        PrintStream printStream = System.out;
        this.input_stream = simpleCharStream;
    }

    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 4;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                this.jjrounds[i2] = Integer.MIN_VALUE;
                i = i2;
            } else {
                return;
            }
        }
    }

    private void jjCheckNAdd(int i) {
        int[] iArr = this.jjrounds;
        int i2 = iArr[i];
        int i3 = this.jjround;
        if (i2 != i3) {
            int[] iArr2 = this.jjstateSet;
            int i4 = this.jjnewStateCnt;
            this.jjnewStateCnt = i4 + 1;
            iArr2[i4] = i;
            iArr[i] = i3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0081, code lost:
        if (r5 > 24) goto L_0x004d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0087 A[LOOP:1: B:7:0x0029->B:34:0x0087, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00b6 A[EDGE_INSN: B:58:0x00b6->B:46:0x00b6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int jjMoveNfa_0(int r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = 4
            r0.jjnewStateCnt = r1
            int[] r2 = r0.jjstateSet
            r3 = 0
            r2[r3] = r19
            r2 = 1
            r4 = 2147483647(0x7fffffff, float:NaN)
            r7 = r20
            r6 = r3
            r5 = r4
            r3 = r2
        L_0x0013:
            int r8 = r0.jjround
            int r8 = r8 + r2
            r0.jjround = r8
            if (r8 != r4) goto L_0x001d
            r18.ReInitRounds()
        L_0x001d:
            char r8 = r0.curChar
            r9 = 64
            r10 = 1
            r12 = 0
            if (r8 >= r9) goto L_0x008d
            long r14 = r10 << r8
        L_0x0029:
            int[] r8 = r0.jjstateSet
            int r3 = r3 + -1
            r8 = r8[r3]
            r10 = 4294967808(0x100000200, double:2.121996044E-314)
            r16 = 287948901175001088(0x3ff000000000000, double:1.988135013128901E-289)
            r2 = 36
            r1 = 46
            r4 = 3
            r9 = 2
            if (r8 == 0) goto L_0x005a
            if (r8 == r9) goto L_0x004f
            if (r8 == r4) goto L_0x0043
            goto L_0x0084
        L_0x0043:
            long r8 = r14 & r16
            int r2 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x004a
            goto L_0x0084
        L_0x004a:
            r0.jjCheckNAdd(r4)
        L_0x004d:
            r5 = r1
            goto L_0x0084
        L_0x004f:
            long r10 = r10 & r14
            int r1 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x0055
            goto L_0x0084
        L_0x0055:
            r0.jjCheckNAdd(r9)
        L_0x0058:
            r5 = r2
            goto L_0x0084
        L_0x005a:
            long r16 = r14 & r16
            int r8 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r8 == 0) goto L_0x0068
            if (r5 <= r1) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r1 = r5
        L_0x0064:
            r0.jjCheckNAdd(r4)
            goto L_0x004d
        L_0x0068:
            long r10 = r10 & r14
            int r1 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r1 == 0) goto L_0x0075
            if (r5 <= r2) goto L_0x0070
            goto L_0x0071
        L_0x0070:
            r2 = r5
        L_0x0071:
            r0.jjCheckNAdd(r9)
            goto L_0x0058
        L_0x0075:
            r1 = 43980465111040(0x280000000000, double:2.17292368994844E-310)
            long r1 = r1 & r14
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 == 0) goto L_0x0084
            r1 = 24
            if (r5 <= r1) goto L_0x0084
            goto L_0x004d
        L_0x0084:
            if (r3 != r6) goto L_0x0087
            goto L_0x00b6
        L_0x0087:
            r1 = 4
            r2 = 1
            r4 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0029
        L_0x008d:
            r1 = 128(0x80, float:1.794E-43)
            if (r8 >= r1) goto L_0x00ae
            r1 = r8 & 63
            long r1 = r10 << r1
        L_0x0095:
            int[] r4 = r0.jjstateSet
            int r3 = r3 + -1
            r4 = r4[r3]
            if (r4 == 0) goto L_0x009e
            goto L_0x00ab
        L_0x009e:
            r8 = 576456345801194494(0x7fffbfe07fffbfe, double:3.7839149227159348E-270)
            long r8 = r8 & r1
            int r4 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x00ab
            r4 = 35
            r5 = r4
        L_0x00ab:
            if (r3 != r6) goto L_0x0095
            goto L_0x00b6
        L_0x00ae:
            int[] r1 = r0.jjstateSet
            int r3 = r3 + -1
            r1 = r1[r3]
            if (r3 != r6) goto L_0x00ae
        L_0x00b6:
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == r1) goto L_0x00c0
            r0.jjmatchedKind = r5
            r0.jjmatchedPos = r7
            r5 = r1
        L_0x00c0:
            int r7 = r7 + 1
            int r3 = r0.jjnewStateCnt
            r0.jjnewStateCnt = r6
            r2 = 4
            int r6 = 4 - r6
            if (r3 != r6) goto L_0x00cc
            return r7
        L_0x00cc:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r4 = r0.input_stream     // Catch:{ IOException -> 0x00d9 }
            char r4 = r4.readChar()     // Catch:{ IOException -> 0x00d9 }
            r0.curChar = r4     // Catch:{ IOException -> 0x00d9 }
            r4 = r1
            r1 = r2
            r2 = 1
            goto L_0x0013
        L_0x00d9:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.field.datetime.parser.DateTimeParserTokenManager.jjMoveNfa_0(int, int):int");
    }

    private int jjMoveStringLiteralDfa1_0(long j) {
        try {
            this.curChar = this.input_stream.readChar();
            char c = this.curChar;
            if (c == 'D') {
                return jjMoveStringLiteralDfa2_0(j, 22817013760L);
            }
            if (c == 'M') {
                return jjMoveStringLiteralDfa2_0(j, 67108864);
            }
            if (c == 'a') {
                return jjMoveStringLiteralDfa2_0(j, 43520);
            }
            if (c == 'c') {
                return jjMoveStringLiteralDfa2_0(j, 1048576);
            }
            if (c == 'e') {
                return jjMoveStringLiteralDfa2_0(j, 4722752);
            }
            if (c == 'h') {
                return jjMoveStringLiteralDfa2_0(j, 128);
            }
            if (c == 'r') {
                return jjMoveStringLiteralDfa2_0(j, 256);
            }
            if (c == 'u') {
                return jjMoveStringLiteralDfa2_0(j, 459808);
            }
            if (c == 'S') {
                return jjMoveStringLiteralDfa2_0(j, 11408506880L);
            }
            if (c != 'T') {
                if (c == 'o') {
                    return jjMoveStringLiteralDfa2_0(j, 2097168);
                }
                if (c == 'p') {
                    return jjMoveStringLiteralDfa2_0(j, 16384);
                }
            } else if ((33554432 & j) != 0) {
                return jjStopAtPos(1, 25);
            }
            return jjStartNfa_0(0, j);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(0, j);
            return 1;
        }
    }

    private int jjMoveStringLiteralDfa2_0(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return jjStartNfa_0(0, j);
        }
        try {
            this.curChar = this.input_stream.readChar();
            char c = this.curChar;
            if (c != 'T') {
                if (c != 'g') {
                    if (c != 'i') {
                        if (c != 'l') {
                            if (c != 'n') {
                                if (c != 'p') {
                                    if (c != 'r') {
                                        if (c != 'y') {
                                            switch (c) {
                                                case 'b':
                                                    if ((4096 & j3) != 0) {
                                                        return jjStopAtPos(2, 12);
                                                    }
                                                    break;
                                                case 'c':
                                                    if ((4194304 & j3) != 0) {
                                                        return jjStopAtPos(2, 22);
                                                    }
                                                    break;
                                                case 'd':
                                                    if ((64 & j3) != 0) {
                                                        return jjStopAtPos(2, 6);
                                                    }
                                                    break;
                                                case 'e':
                                                    if ((32 & j3) != 0) {
                                                        return jjStopAtPos(2, 5);
                                                    }
                                                    break;
                                                default:
                                                    switch (c) {
                                                        case 't':
                                                            if ((512 & j3) != 0) {
                                                                return jjStopAtPos(2, 9);
                                                            }
                                                            if ((1048576 & j3) != 0) {
                                                                return jjStopAtPos(2, 20);
                                                            }
                                                            break;
                                                        case 'u':
                                                            if ((128 & j3) != 0) {
                                                                return jjStopAtPos(2, 7);
                                                            }
                                                            break;
                                                        case 'v':
                                                            if ((2097152 & j3) != 0) {
                                                                return jjStopAtPos(2, 21);
                                                            }
                                                            break;
                                                    }
                                            }
                                        } else if ((32768 & j3) != 0) {
                                            return jjStopAtPos(2, 15);
                                        }
                                    } else if ((8192 & j3) != 0) {
                                        return jjStopAtPos(2, 13);
                                    } else {
                                        if ((16384 & j3) != 0) {
                                            return jjStopAtPos(2, 14);
                                        }
                                    }
                                } else if ((524288 & j3) != 0) {
                                    return jjStopAtPos(2, 19);
                                }
                            } else if ((16 & j3) != 0) {
                                return jjStopAtPos(2, 4);
                            } else {
                                if ((1024 & j3) != 0) {
                                    return jjStopAtPos(2, 10);
                                }
                                if ((2048 & j3) != 0) {
                                    return jjStopAtPos(2, 11);
                                }
                                if ((65536 & j3) != 0) {
                                    return jjStopAtPos(2, 16);
                                }
                            }
                        } else if ((131072 & j3) != 0) {
                            return jjStopAtPos(2, 17);
                        }
                    } else if ((256 & j3) != 0) {
                        return jjStopAtPos(2, 8);
                    }
                } else if ((262144 & j3) != 0) {
                    return jjStopAtPos(2, 18);
                }
            } else if ((67108864 & j3) != 0) {
                return jjStopAtPos(2, 26);
            } else {
                if ((134217728 & j3) != 0) {
                    return jjStopAtPos(2, 27);
                }
                if ((268435456 & j3) != 0) {
                    return jjStopAtPos(2, 28);
                }
                if ((536870912 & j3) != 0) {
                    return jjStopAtPos(2, 29);
                }
                if ((1073741824 & j3) != 0) {
                    return jjStopAtPos(2, 30);
                }
                if ((2147483648L & j3) != 0) {
                    return jjStopAtPos(2, 31);
                }
                if ((4294967296L & j3) != 0) {
                    return jjStopAtPos(2, 32);
                }
                if ((8589934592L & j3) != 0) {
                    return jjStopAtPos(2, 33);
                }
                if ((17179869184L & j3) != 0) {
                    return jjStopAtPos(2, 34);
                }
            }
            return jjStartNfa_0(1, j3);
        } catch (IOException unused) {
            jjStopStringLiteralDfa_0(1, j3);
            return 2;
        }
    }

    private final int jjStartNfa_0(int i, long j) {
        jjStopStringLiteralDfa_0(i, j);
        return jjMoveNfa_0(-1, i + 1);
    }

    private int jjStopAtPos(int i, int i2) {
        this.jjmatchedKind = i2;
        this.jjmatchedPos = i;
        return i + 1;
    }

    private final int jjStopStringLiteralDfa_0(int i, long j) {
        if (i != 0) {
            if (i == 1 && (j & 34334373872L) != 0 && this.jjmatchedPos == 0) {
                this.jjmatchedKind = 35;
                this.jjmatchedPos = 0;
            }
            return -1;
        }
        if ((j & 34334373872L) != 0) {
            this.jjmatchedKind = 35;
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:168:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x03d8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.james.mime4j.field.datetime.parser.Token getNextToken() {
        /*
            r22 = this;
            r0 = r22
            java.lang.String r1 = ""
            r2 = 0
            r3 = 0
            r4 = r2
        L_0x0007:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r5 = r0.input_stream     // Catch:{ IOException -> 0x047b }
            r6 = -1
            r5.tokenBegin = r6     // Catch:{ IOException -> 0x047b }
            char r7 = r5.readChar()     // Catch:{ IOException -> 0x047b }
            int r8 = r5.bufpos     // Catch:{ IOException -> 0x047b }
            r5.tokenBegin = r8     // Catch:{ IOException -> 0x047b }
            r0.curChar = r7     // Catch:{ IOException -> 0x047b }
            java.lang.StringBuilder r5 = r0.jjimage
            r0.image = r5
            java.lang.StringBuilder r5 = r0.image
            r5.setLength(r2)
            r0.jjimageLen = r2
        L_0x0021:
            int r5 = r0.curLexState
            r7 = 40
            r8 = 2
            r9 = 3
            r12 = 1
            r13 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == 0) goto L_0x01ee
            r15 = 64
            r14 = 41
            if (r5 == r12) goto L_0x0112
            if (r5 == r8) goto L_0x0037
            goto L_0x02c6
        L_0x0037:
            r0.jjmatchedKind = r13
            r0.jjmatchedPos = r2
            char r4 = r0.curChar
            if (r4 == r7) goto L_0x010a
            if (r4 == r14) goto L_0x0102
            r0.jjnewStateCnt = r9
            int[] r4 = r0.jjstateSet
            r4[r2] = r2
            r7 = r2
            r9 = r7
            r4 = r12
            r5 = r13
            r2 = 128(0x80, float:1.794E-43)
        L_0x004d:
            int r14 = r0.jjround
            int r14 = r14 + r12
            r0.jjround = r14
            if (r14 != r13) goto L_0x0057
            r22.ReInitRounds()
        L_0x0057:
            char r14 = r0.curChar
            r13 = 42
            r10 = 45
            if (r14 >= r15) goto L_0x0077
        L_0x005f:
            int[] r2 = r0.jjstateSet
            int r4 = r4 + r6
            r2 = r2[r4]
            if (r2 == 0) goto L_0x006d
            if (r2 == r12) goto L_0x0069
            goto L_0x0070
        L_0x0069:
            if (r5 <= r13) goto L_0x0070
            r5 = r13
            goto L_0x0070
        L_0x006d:
            if (r5 <= r10) goto L_0x0070
            r5 = r10
        L_0x0070:
            if (r4 != r7) goto L_0x005f
        L_0x0072:
            r2 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x00db
        L_0x0077:
            if (r14 >= r2) goto L_0x00a5
        L_0x0079:
            int[] r2 = r0.jjstateSet
            int r4 = r4 + r6
            r2 = r2[r4]
            if (r2 == 0) goto L_0x008d
            if (r2 == r12) goto L_0x0089
            if (r2 == r8) goto L_0x0085
            goto L_0x00a0
        L_0x0085:
            if (r5 <= r10) goto L_0x00a0
            r5 = r10
            goto L_0x00a0
        L_0x0089:
            if (r5 <= r13) goto L_0x00a0
            r5 = r13
            goto L_0x00a0
        L_0x008d:
            if (r5 <= r10) goto L_0x0090
            r5 = r10
        L_0x0090:
            char r2 = r0.curChar
            r8 = 92
            if (r2 != r8) goto L_0x00a0
            int[] r2 = r0.jjstateSet
            int r8 = r0.jjnewStateCnt
            int r11 = r8 + 1
            r0.jjnewStateCnt = r11
            r2[r8] = r12
        L_0x00a0:
            if (r4 != r7) goto L_0x00a3
            goto L_0x0072
        L_0x00a3:
            r8 = 2
            goto L_0x0079
        L_0x00a5:
            r2 = r14 & 255(0xff, float:3.57E-43)
            int r8 = r2 >> 6
            r2 = r14 & 63
            r18 = 1
            long r18 = r18 << r2
        L_0x00af:
            int[] r2 = r0.jjstateSet
            int r4 = r4 + r6
            r2 = r2[r4]
            if (r2 == 0) goto L_0x00c9
            if (r2 == r12) goto L_0x00b9
            goto L_0x00d8
        L_0x00b9:
            long[] r2 = jjbitVec0
            r20 = r2[r8]
            long r20 = r20 & r18
            r16 = 0
            int r2 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r2 == 0) goto L_0x00d8
            if (r5 <= r13) goto L_0x00d8
            r5 = r13
            goto L_0x00d8
        L_0x00c9:
            r16 = 0
            long[] r2 = jjbitVec0
            r20 = r2[r8]
            long r20 = r20 & r18
            int r2 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r2 == 0) goto L_0x00d8
            if (r5 <= r10) goto L_0x00d8
            r5 = r10
        L_0x00d8:
            if (r4 != r7) goto L_0x00af
            goto L_0x0072
        L_0x00db:
            if (r5 == r2) goto L_0x00e4
            r0.jjmatchedKind = r5
            r0.jjmatchedPos = r9
            r5 = 2147483647(0x7fffffff, float:NaN)
        L_0x00e4:
            int r9 = r9 + 1
            int r4 = r0.jjnewStateCnt
            r0.jjnewStateCnt = r7
            int r7 = 3 - r7
            if (r4 != r7) goto L_0x00ef
            goto L_0x00ff
        L_0x00ef:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r2 = r0.input_stream     // Catch:{ IOException -> 0x00ff }
            char r2 = r2.readChar()     // Catch:{ IOException -> 0x00ff }
            r0.curChar = r2     // Catch:{ IOException -> 0x00ff }
            r8 = 2
            r2 = 128(0x80, float:1.794E-43)
            r13 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x004d
        L_0x00ff:
            r2 = r9
            goto L_0x02c5
        L_0x0102:
            r4 = 44
            int r2 = r0.jjStopAtPos(r2, r4)
            goto L_0x02c5
        L_0x010a:
            r4 = 43
            int r2 = r0.jjStopAtPos(r2, r4)
            goto L_0x02c5
        L_0x0112:
            r4 = r13
            r0.jjmatchedKind = r4
            r0.jjmatchedPos = r2
            char r4 = r0.curChar
            if (r4 == r7) goto L_0x01e8
            if (r4 == r14) goto L_0x01df
            r4 = 3
            r0.jjnewStateCnt = r4
            int[] r4 = r0.jjstateSet
            r4[r2] = r2
            r2 = 0
            r4 = 0
            r5 = r2
            r7 = r4
            r2 = r12
            r4 = 2147483647(0x7fffffff, float:NaN)
        L_0x012c:
            int r8 = r0.jjround
            int r8 = r8 + r12
            r0.jjround = r8
            r9 = 2147483647(0x7fffffff, float:NaN)
            if (r8 != r9) goto L_0x0139
            r22.ReInitRounds()
        L_0x0139:
            char r8 = r0.curChar
            r9 = 39
            if (r8 >= r15) goto L_0x0157
        L_0x013f:
            int[] r8 = r0.jjstateSet
            int r2 = r2 + r6
            r8 = r8[r2]
            if (r8 == 0) goto L_0x014d
            if (r8 == r12) goto L_0x0149
            goto L_0x0150
        L_0x0149:
            if (r4 <= r9) goto L_0x0150
            r4 = r9
            goto L_0x0150
        L_0x014d:
            if (r4 <= r14) goto L_0x0150
            r4 = r14
        L_0x0150:
            if (r2 != r5) goto L_0x013f
        L_0x0152:
            r2 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x01bc
        L_0x0157:
            r10 = 128(0x80, float:1.794E-43)
            if (r8 >= r10) goto L_0x0186
        L_0x015b:
            int[] r8 = r0.jjstateSet
            int r2 = r2 + r6
            r8 = r8[r2]
            if (r8 == 0) goto L_0x0170
            if (r8 == r12) goto L_0x016c
            r10 = 2
            if (r8 == r10) goto L_0x0168
            goto L_0x0183
        L_0x0168:
            if (r4 <= r14) goto L_0x0183
            r4 = r14
            goto L_0x0183
        L_0x016c:
            if (r4 <= r9) goto L_0x0183
            r4 = r9
            goto L_0x0183
        L_0x0170:
            if (r4 <= r14) goto L_0x0173
            r4 = r14
        L_0x0173:
            char r8 = r0.curChar
            r10 = 92
            if (r8 != r10) goto L_0x0183
            int[] r8 = r0.jjstateSet
            int r10 = r0.jjnewStateCnt
            int r11 = r10 + 1
            r0.jjnewStateCnt = r11
            r8[r10] = r12
        L_0x0183:
            if (r2 != r5) goto L_0x015b
            goto L_0x0152
        L_0x0186:
            r10 = r8 & 255(0xff, float:3.57E-43)
            int r10 = r10 >> 6
            r8 = r8 & 63
            r18 = 1
            long r18 = r18 << r8
        L_0x0190:
            int[] r8 = r0.jjstateSet
            int r2 = r2 + r6
            r8 = r8[r2]
            if (r8 == 0) goto L_0x01aa
            if (r8 == r12) goto L_0x019a
            goto L_0x01b9
        L_0x019a:
            long[] r8 = jjbitVec0
            r20 = r8[r10]
            long r20 = r20 & r18
            r16 = 0
            int r8 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x01b9
            if (r4 <= r9) goto L_0x01b9
            r4 = r9
            goto L_0x01b9
        L_0x01aa:
            r16 = 0
            long[] r8 = jjbitVec0
            r20 = r8[r10]
            long r20 = r20 & r18
            int r8 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x01b9
            if (r4 <= r14) goto L_0x01b9
            r4 = r14
        L_0x01b9:
            if (r2 != r5) goto L_0x0190
            goto L_0x0152
        L_0x01bc:
            if (r4 == r2) goto L_0x01c5
            r0.jjmatchedKind = r4
            r0.jjmatchedPos = r7
            r4 = 2147483647(0x7fffffff, float:NaN)
        L_0x01c5:
            int r7 = r7 + 1
            int r2 = r0.jjnewStateCnt
            r0.jjnewStateCnt = r5
            int r5 = 3 - r5
            if (r2 != r5) goto L_0x01d0
            goto L_0x01dc
        L_0x01d0:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r8 = r0.input_stream     // Catch:{ IOException -> 0x01dc }
            char r8 = r8.readChar()     // Catch:{ IOException -> 0x01dc }
            r0.curChar = r8     // Catch:{ IOException -> 0x01dc }
            r15 = 64
            goto L_0x012c
        L_0x01dc:
            r2 = r7
            goto L_0x02c5
        L_0x01df:
            r2 = 38
            r4 = 0
            int r2 = r0.jjStopAtPos(r4, r2)
            goto L_0x02c5
        L_0x01e8:
            int r2 = r0.jjStopAtPos(r2, r7)
            goto L_0x02c5
        L_0x01ee:
            r4 = r13
            r0.jjmatchedKind = r4
            r0.jjmatchedPos = r2
            char r2 = r0.curChar
            r4 = 10
            if (r2 == r4) goto L_0x02bf
            r4 = 13
            if (r2 == r4) goto L_0x02b9
            if (r2 == r7) goto L_0x02b1
            r4 = 44
            if (r2 == r4) goto L_0x02aa
            r4 = 58
            if (r2 == r4) goto L_0x02a2
            r4 = 65
            if (r2 == r4) goto L_0x029a
            r4 = 74
            if (r2 == r4) goto L_0x0292
            r4 = 87
            if (r2 == r4) goto L_0x028b
            switch(r2) {
                case 67: goto L_0x0246;
                case 68: goto L_0x023d;
                case 69: goto L_0x0234;
                case 70: goto L_0x022c;
                case 71: goto L_0x0223;
                default: goto L_0x0216;
            }
        L_0x0216:
            switch(r2) {
                case 77: goto L_0x026a;
                case 78: goto L_0x0262;
                case 79: goto L_0x025a;
                case 80: goto L_0x024f;
                default: goto L_0x0219;
            }
        L_0x0219:
            switch(r2) {
                case 83: goto L_0x0283;
                case 84: goto L_0x027c;
                case 85: goto L_0x0274;
                default: goto L_0x021c;
            }
        L_0x021c:
            r2 = 0
            int r2 = r0.jjMoveNfa_0(r2, r2)
            goto L_0x02c5
        L_0x0223:
            r4 = 67108864(0x4000000, double:3.31561842E-316)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x022c:
            r4 = 4352(0x1100, double:2.15E-320)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0234:
            r4 = 402653184(0x18000000, double:1.989371054E-315)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x023d:
            r4 = 4194304(0x400000, double:2.0722615E-317)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0246:
            r4 = 1610612736(0x60000000, double:7.957484216E-315)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x024f:
            r4 = 25769803776(0x600000000, double:1.2731974746E-313)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x025a:
            r4 = 1048576(0x100000, double:5.180654E-318)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0262:
            r4 = 2097152(0x200000, double:1.0361308E-317)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x026a:
            r4 = 6442491920(0x18000a010, double:3.1830139313E-314)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0274:
            r4 = 33554432(0x2000000, double:1.6578092E-316)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x027c:
            r4 = 160(0xa0, double:7.9E-322)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0283:
            r4 = 525824(0x80600, double:2.597916E-318)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x028b:
            r4 = 64
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x0292:
            r4 = 198656(0x30800, double:9.8149E-319)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x029a:
            r4 = 278528(0x44000, double:1.37611E-318)
            int r2 = r0.jjMoveStringLiteralDfa1_0(r4)
            goto L_0x02c5
        L_0x02a2:
            r2 = 23
            r4 = 0
            int r2 = r0.jjStopAtPos(r4, r2)
            goto L_0x02c5
        L_0x02aa:
            r2 = 3
            r4 = 0
            int r2 = r0.jjStopAtPos(r4, r2)
            goto L_0x02c5
        L_0x02b1:
            r2 = 0
            r4 = 37
            int r2 = r0.jjStopAtPos(r2, r4)
            goto L_0x02c5
        L_0x02b9:
            r2 = 0
            int r2 = r0.jjStopAtPos(r2, r12)
            goto L_0x02c5
        L_0x02bf:
            r2 = 2
            r4 = 0
            int r2 = r0.jjStopAtPos(r4, r2)
        L_0x02c5:
            r4 = r2
        L_0x02c6:
            int r2 = r0.jjmatchedKind
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r2 == r5) goto L_0x03d8
            int r2 = r0.jjmatchedPos
            int r5 = r2 + 1
            if (r5 >= r4) goto L_0x02db
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r5 = r0.input_stream
            int r2 = r4 - r2
            int r2 = r2 - r12
            r5.backup(r2)
        L_0x02db:
            long[] r2 = jjtoToken
            int r5 = r0.jjmatchedKind
            int r7 = r5 >> 6
            r8 = r2[r7]
            r2 = r5 & 63
            r10 = 1
            long r10 = r10 << r2
            long r8 = r8 & r10
            r13 = 0
            int r2 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r2 == 0) goto L_0x0302
            org.apache.james.mime4j.field.datetime.parser.Token r1 = r22.jjFillToken()
            r1.specialToken = r3
            int[] r2 = jjnewLexState
            int r3 = r0.jjmatchedKind
            r4 = r2[r3]
            if (r4 == r6) goto L_0x0301
            r2 = r2[r3]
            r0.curLexState = r2
        L_0x0301:
            return r1
        L_0x0302:
            long[] r2 = jjtoSkip
            r8 = r2[r7]
            long r8 = r8 & r10
            r13 = 0
            int r2 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r2 == 0) goto L_0x0331
            long[] r2 = jjtoSpecial
            r7 = r2[r7]
            long r7 = r7 & r10
            int r2 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r2 == 0) goto L_0x0322
            org.apache.james.mime4j.field.datetime.parser.Token r2 = r22.jjFillToken()
            if (r3 != 0) goto L_0x031d
            goto L_0x0321
        L_0x031d:
            r2.specialToken = r3
            r3.next = r2
        L_0x0321:
            r3 = r2
        L_0x0322:
            int[] r2 = jjnewLexState
            int r5 = r0.jjmatchedKind
            r7 = r2[r5]
            if (r7 == r6) goto L_0x032e
            r2 = r2[r5]
            r0.curLexState = r2
        L_0x032e:
            r2 = 0
            goto L_0x0007
        L_0x0331:
            int r2 = r0.jjimageLen
            int r4 = r0.jjmatchedPos
            int r4 = r4 + r12
            int r4 = r4 + r2
            r0.jjimageLen = r4
            switch(r5) {
                case 39: goto L_0x039f;
                case 40: goto L_0x038c;
                case 41: goto L_0x033c;
                case 42: goto L_0x0370;
                case 43: goto L_0x035a;
                case 44: goto L_0x033e;
                default: goto L_0x033c;
            }
        L_0x033c:
            goto L_0x03ba
        L_0x033e:
            java.lang.StringBuilder r2 = r0.image
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r4 = r0.input_stream
            int r5 = r0.jjimageLen
            char[] r4 = r4.GetSuffix(r5)
            r2.append(r4)
            r2 = 0
            r0.jjimageLen = r2
            int r2 = commentNest
            int r2 = r2 - r12
            commentNest = r2
            int r2 = commentNest
            if (r2 != 0) goto L_0x03ba
            r0.curLexState = r12
            goto L_0x03ba
        L_0x035a:
            java.lang.StringBuilder r2 = r0.image
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r4 = r0.input_stream
            int r5 = r0.jjimageLen
            char[] r4 = r4.GetSuffix(r5)
            r2.append(r4)
            r2 = 0
            r0.jjimageLen = r2
            int r2 = commentNest
            int r2 = r2 + r12
            commentNest = r2
            goto L_0x03ba
        L_0x0370:
            r2 = 0
            java.lang.StringBuilder r4 = r0.image
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r5 = r0.input_stream
            int r7 = r0.jjimageLen
            char[] r5 = r5.GetSuffix(r7)
            r4.append(r5)
            r0.jjimageLen = r2
            java.lang.StringBuilder r2 = r0.image
            int r4 = r2.length()
            int r4 = r4 + -2
            r2.deleteCharAt(r4)
            goto L_0x03ba
        L_0x038c:
            java.lang.StringBuilder r2 = r0.image
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r4 = r0.input_stream
            int r5 = r0.jjimageLen
            char[] r4 = r4.GetSuffix(r5)
            r2.append(r4)
            r2 = 0
            r0.jjimageLen = r2
            commentNest = r12
            goto L_0x03ba
        L_0x039f:
            r2 = 0
            java.lang.StringBuilder r4 = r0.image
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r5 = r0.input_stream
            int r7 = r0.jjimageLen
            char[] r5 = r5.GetSuffix(r7)
            r4.append(r5)
            r0.jjimageLen = r2
            java.lang.StringBuilder r2 = r0.image
            int r4 = r2.length()
            int r4 = r4 + -2
            r2.deleteCharAt(r4)
        L_0x03ba:
            int[] r2 = jjnewLexState
            int r4 = r0.jjmatchedKind
            r5 = r2[r4]
            if (r5 == r6) goto L_0x03c6
            r2 = r2[r4]
            r0.curLexState = r2
        L_0x03c6:
            r2 = 2147483647(0x7fffffff, float:NaN)
            r0.jjmatchedKind = r2
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r2 = r0.input_stream     // Catch:{ IOException -> 0x03d7 }
            char r2 = r2.readChar()     // Catch:{ IOException -> 0x03d7 }
            r0.curChar = r2     // Catch:{ IOException -> 0x03d7 }
            r2 = 0
            r4 = 0
            goto L_0x0021
        L_0x03d7:
            r4 = 0
        L_0x03d8:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r2 = r0.input_stream
            int[] r3 = r2.bufline
            int r5 = r2.bufpos
            r3 = r3[r5]
            int[] r6 = r2.bufcolumn
            r5 = r6[r5]
            r2.readChar()     // Catch:{ IOException -> 0x03ef }
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r2 = r0.input_stream     // Catch:{ IOException -> 0x03ef }
            r2.backup(r12)     // Catch:{ IOException -> 0x03ef }
            r2 = 0
            r6 = 0
            goto L_0x040c
        L_0x03ef:
            if (r4 > r12) goto L_0x03f3
            r2 = r1
            goto L_0x03f9
        L_0x03f3:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r2 = r0.input_stream
            java.lang.String r2 = r2.GetImage()
        L_0x03f9:
            char r6 = r0.curChar
            r7 = 10
            if (r6 == r7) goto L_0x0407
            r7 = 13
            if (r6 != r7) goto L_0x0404
            goto L_0x0407
        L_0x0404:
            int r5 = r5 + 1
            goto L_0x040a
        L_0x0407:
            int r3 = r3 + 1
            r5 = 0
        L_0x040a:
            r6 = r2
            r2 = r12
        L_0x040c:
            if (r2 != 0) goto L_0x041d
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r6 = r0.input_stream
            r6.backup(r12)
            if (r4 > r12) goto L_0x0416
            goto L_0x041e
        L_0x0416:
            org.apache.james.mime4j.field.datetime.parser.SimpleCharStream r1 = r0.input_stream
            java.lang.String r1 = r1.GetImage()
            goto L_0x041e
        L_0x041d:
            r1 = r6
        L_0x041e:
            org.apache.james.mime4j.field.datetime.parser.TokenMgrError r4 = new org.apache.james.mime4j.field.datetime.parser.TokenMgrError
            char r0 = r0.curChar
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Lexical error at line "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r3 = ", column "
            r6.append(r3)
            r6.append(r5)
            java.lang.String r3 = ".  Encountered: "
            r6.append(r3)
            java.lang.String r3 = "\""
            if (r2 == 0) goto L_0x0443
            java.lang.String r0 = "<EOF> "
            goto L_0x0466
        L_0x0443:
            java.lang.StringBuilder r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r5 = java.lang.String.valueOf(r0)
            java.lang.String r5 = org.apache.james.mime4j.field.datetime.parser.TokenMgrError.addEscapes(r5)
            r2.append(r5)
            r2.append(r3)
            java.lang.String r5 = " ("
            r2.append(r5)
            r2.append(r0)
            java.lang.String r0 = "), "
            r2.append(r0)
            java.lang.String r0 = r2.toString()
        L_0x0466:
            r6.append(r0)
            java.lang.String r0 = "after : \""
            r6.append(r0)
            java.lang.String r0 = org.apache.james.mime4j.field.datetime.parser.TokenMgrError.addEscapes(r1)
            java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r6, r0, r3)
            r1 = 0
            r4.<init>(r0, r1)
            throw r4
        L_0x047b:
            r0.jjmatchedKind = r2
            org.apache.james.mime4j.field.datetime.parser.Token r0 = r22.jjFillToken()
            r0.specialToken = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.field.datetime.parser.DateTimeParserTokenManager.getNextToken():org.apache.james.mime4j.field.datetime.parser.Token");
    }

    /* access modifiers changed from: protected */
    public Token jjFillToken() {
        String str = jjstrLiteralImages[this.jjmatchedKind];
        if (str == null) {
            str = this.input_stream.GetImage();
        }
        SimpleCharStream simpleCharStream = this.input_stream;
        int[] iArr = simpleCharStream.bufline;
        int i = simpleCharStream.tokenBegin;
        int i2 = iArr[i];
        int[] iArr2 = simpleCharStream.bufcolumn;
        int i3 = iArr2[i];
        int i4 = simpleCharStream.bufpos;
        int i5 = iArr[i4];
        int i6 = iArr2[i4];
        Token token = new Token(this.jjmatchedKind, str);
        token.beginLine = i2;
        token.endLine = i5;
        token.beginColumn = i3;
        token.endColumn = i6;
        return token;
    }
}

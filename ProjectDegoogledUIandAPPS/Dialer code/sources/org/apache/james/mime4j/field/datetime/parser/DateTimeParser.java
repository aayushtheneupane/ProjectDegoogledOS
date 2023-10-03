package org.apache.james.mime4j.field.datetime.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DateTimeParser implements DateTimeParserConstants {
    private static int[] jj_la1_0 = {2, 2032, 2032, 8386560, 8388608, -16777216, -33554432};
    private static int[] jj_la1_1 = {0, 0, 0, 0, 0, 15, 15};
    private List<int[]> jj_expentries = new ArrayList();
    private int[] jj_expentry;
    private int jj_gen;
    SimpleCharStream jj_input_stream;
    private int jj_kind = -1;
    private final int[] jj_la1 = new int[7];
    public Token jj_nt;
    private int jj_ntk;
    public Token token;
    public DateTimeParserTokenManager token_source;

    private static class Date {
        private int day;
        private int month;
        private String year;

        public Date(String str, int i, int i2) {
            this.year = str;
            this.month = i;
            this.day = i2;
        }

        public int getDay() {
            return this.day;
        }

        public int getMonth() {
            return this.month;
        }

        public String getYear() {
            return this.year;
        }
    }

    private static class Time {
        private int hour;
        private int minute;
        private int second;
        private int zone;

        public Time(int i, int i2, int i3, int i4) {
            this.hour = i;
            this.minute = i2;
            this.second = i3;
            this.zone = i4;
        }

        public int getHour() {
            return this.hour;
        }

        public int getMinute() {
            return this.minute;
        }

        public int getSecond() {
            return this.second;
        }

        public int getZone() {
            return this.zone;
        }
    }

    public DateTimeParser(Reader reader) {
        this.jj_input_stream = new SimpleCharStream(reader, 1, 1);
        this.token_source = new DateTimeParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 7; i++) {
            this.jj_la1[i] = -1;
        }
    }

    private Token jj_consume_token(int i) throws ParseException {
        Token token2 = this.token;
        Token token3 = token2.next;
        if (token3 != null) {
            this.token = token3;
        } else {
            Token nextToken = this.token_source.getNextToken();
            token2.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        Token token4 = this.token;
        if (token4.kind == i) {
            this.jj_gen++;
            return token4;
        }
        this.token = token2;
        this.jj_kind = i;
        this.jj_expentries.clear();
        boolean[] zArr = new boolean[49];
        int i2 = this.jj_kind;
        if (i2 >= 0) {
            zArr[i2] = true;
            this.jj_kind = -1;
        }
        for (int i3 = 0; i3 < 7; i3++) {
            if (this.jj_la1[i3] == this.jj_gen) {
                for (int i4 = 0; i4 < 32; i4++) {
                    int i5 = 1 << i4;
                    if ((jj_la1_0[i3] & i5) != 0) {
                        zArr[i4] = true;
                    }
                    if ((jj_la1_1[i3] & i5) != 0) {
                        zArr[i4 + 32] = true;
                    }
                }
            }
        }
        for (int i6 = 0; i6 < 49; i6++) {
            if (zArr[i6]) {
                this.jj_expentry = new int[1];
                int[] iArr = this.jj_expentry;
                iArr[0] = i6;
                this.jj_expentries.add(iArr);
            }
        }
        int[][] iArr2 = new int[this.jj_expentries.size()][];
        for (int i7 = 0; i7 < this.jj_expentries.size(); i7++) {
            iArr2[i7] = this.jj_expentries.get(i7);
        }
        throw new ParseException(this.token, iArr2, DateTimeParserConstants.tokenImage);
    }

    private int jj_ntk() {
        Token token2 = this.token;
        Token token3 = token2.next;
        this.jj_nt = token3;
        if (token3 == null) {
            Token nextToken = this.token_source.getNextToken();
            token2.next = nextToken;
            int i = nextToken.kind;
            this.jj_ntk = i;
            return i;
        }
        int i2 = this.jj_nt.kind;
        this.jj_ntk = i2;
        return i2;
    }

    private static int parseDigits(Token token2) {
        return Integer.parseInt(token2.image, 10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016e, code lost:
        r1 = -7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x017b, code lost:
        r1 = -6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0189, code lost:
        r1 = -5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0196, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0197, code lost:
        r1 = r1 * 100;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.apache.james.mime4j.dom.datetime.DateTime parseAll() throws org.apache.james.mime4j.field.datetime.parser.ParseException {
        /*
            r21 = this;
            r0 = r21
            int r1 = r0.jj_ntk
            r2 = -1
            if (r1 != r2) goto L_0x000b
            int r1 = r21.jj_ntk()
        L_0x000b:
            r3 = 3
            r4 = 10
            r5 = 9
            r6 = 8
            r7 = 7
            r8 = 6
            r9 = 5
            r10 = 4
            r11 = 1
            r12 = 2
            switch(r1) {
                case 4: goto L_0x0022;
                case 5: goto L_0x0022;
                case 6: goto L_0x0022;
                case 7: goto L_0x0022;
                case 8: goto L_0x0022;
                case 9: goto L_0x0022;
                case 10: goto L_0x0022;
                default: goto L_0x001b;
            }
        L_0x001b:
            int[] r1 = r0.jj_la1
            int r13 = r0.jj_gen
            r1[r11] = r13
            goto L_0x005e
        L_0x0022:
            int r1 = r0.jj_ntk
            if (r1 != r2) goto L_0x002a
            int r1 = r21.jj_ntk()
        L_0x002a:
            switch(r1) {
                case 4: goto L_0x0054;
                case 5: goto L_0x0050;
                case 6: goto L_0x004c;
                case 7: goto L_0x0048;
                case 8: goto L_0x0044;
                case 9: goto L_0x0040;
                case 10: goto L_0x003c;
                default: goto L_0x002d;
            }
        L_0x002d:
            int[] r1 = r0.jj_la1
            int r3 = r0.jj_gen
            r1[r12] = r3
            r0.jj_consume_token(r2)
            org.apache.james.mime4j.field.datetime.parser.ParseException r0 = new org.apache.james.mime4j.field.datetime.parser.ParseException
            r0.<init>()
            throw r0
        L_0x003c:
            r0.jj_consume_token(r4)
            goto L_0x0057
        L_0x0040:
            r0.jj_consume_token(r5)
            goto L_0x0057
        L_0x0044:
            r0.jj_consume_token(r6)
            goto L_0x0057
        L_0x0048:
            r0.jj_consume_token(r7)
            goto L_0x0057
        L_0x004c:
            r0.jj_consume_token(r8)
            goto L_0x0057
        L_0x0050:
            r0.jj_consume_token(r9)
            goto L_0x0057
        L_0x0054:
            r0.jj_consume_token(r10)
        L_0x0057:
            org.apache.james.mime4j.field.datetime.parser.Token r1 = r0.token
            java.lang.String r1 = r1.image
            r0.jj_consume_token(r3)
        L_0x005e:
            r1 = 46
            org.apache.james.mime4j.field.datetime.parser.Token r13 = r0.jj_consume_token(r1)
            int r13 = parseDigits(r13)
            int r14 = r0.jj_ntk
            if (r14 != r2) goto L_0x0070
            int r14 = r21.jj_ntk()
        L_0x0070:
            r15 = 12
            r4 = 11
            switch(r14) {
                case 11: goto L_0x00d1;
                case 12: goto L_0x00cc;
                case 13: goto L_0x00c5;
                case 14: goto L_0x00be;
                case 15: goto L_0x00b7;
                case 16: goto L_0x00b0;
                case 17: goto L_0x00a9;
                case 18: goto L_0x00a2;
                case 19: goto L_0x009b;
                case 20: goto L_0x0093;
                case 21: goto L_0x008c;
                case 22: goto L_0x0086;
                default: goto L_0x0077;
            }
        L_0x0077:
            int[] r1 = r0.jj_la1
            int r4 = r0.jj_gen
            r1[r3] = r4
            r0.jj_consume_token(r2)
            org.apache.james.mime4j.field.datetime.parser.ParseException r0 = new org.apache.james.mime4j.field.datetime.parser.ParseException
            r0.<init>()
            throw r0
        L_0x0086:
            r3 = 22
            r0.jj_consume_token(r3)
            goto L_0x00d5
        L_0x008c:
            r3 = 21
            r0.jj_consume_token(r3)
            r15 = r4
            goto L_0x00d5
        L_0x0093:
            r3 = 20
            r0.jj_consume_token(r3)
            r15 = 10
            goto L_0x00d5
        L_0x009b:
            r3 = 19
            r0.jj_consume_token(r3)
            r15 = r5
            goto L_0x00d5
        L_0x00a2:
            r3 = 18
            r0.jj_consume_token(r3)
            r15 = r6
            goto L_0x00d5
        L_0x00a9:
            r3 = 17
            r0.jj_consume_token(r3)
            r15 = r7
            goto L_0x00d5
        L_0x00b0:
            r3 = 16
            r0.jj_consume_token(r3)
            r15 = r8
            goto L_0x00d5
        L_0x00b7:
            r3 = 15
            r0.jj_consume_token(r3)
            r15 = r9
            goto L_0x00d5
        L_0x00be:
            r3 = 14
            r0.jj_consume_token(r3)
            r15 = r10
            goto L_0x00d5
        L_0x00c5:
            r4 = 13
            r0.jj_consume_token(r4)
            r15 = r3
            goto L_0x00d5
        L_0x00cc:
            r0.jj_consume_token(r15)
            r15 = r12
            goto L_0x00d5
        L_0x00d1:
            r0.jj_consume_token(r4)
            r15 = r11
        L_0x00d5:
            org.apache.james.mime4j.field.datetime.parser.Token r3 = r0.jj_consume_token(r1)
            java.lang.String r3 = r3.image
            org.apache.james.mime4j.field.datetime.parser.DateTimeParser$Date r4 = new org.apache.james.mime4j.field.datetime.parser.DateTimeParser$Date
            r4.<init>(r3, r15, r13)
            org.apache.james.mime4j.field.datetime.parser.Token r3 = r0.jj_consume_token(r1)
            int r3 = parseDigits(r3)
            r5 = 23
            r0.jj_consume_token(r5)
            org.apache.james.mime4j.field.datetime.parser.Token r6 = r0.jj_consume_token(r1)
            int r6 = parseDigits(r6)
            int r7 = r0.jj_ntk
            if (r7 != r2) goto L_0x00fd
            int r7 = r21.jj_ntk()
        L_0x00fd:
            r12 = 0
            if (r7 == r5) goto L_0x0108
            int[] r5 = r0.jj_la1
            int r7 = r0.jj_gen
            r5[r10] = r7
            r5 = r12
            goto L_0x0113
        L_0x0108:
            r0.jj_consume_token(r5)
            org.apache.james.mime4j.field.datetime.parser.Token r5 = r0.jj_consume_token(r1)
            int r5 = parseDigits(r5)
        L_0x0113:
            int r7 = r0.jj_ntk
            if (r7 != r2) goto L_0x011b
            int r7 = r21.jj_ntk()
        L_0x011b:
            switch(r7) {
                case 24: goto L_0x019a;
                case 25: goto L_0x012d;
                case 26: goto L_0x012d;
                case 27: goto L_0x012d;
                case 28: goto L_0x012d;
                case 29: goto L_0x012d;
                case 30: goto L_0x012d;
                case 31: goto L_0x012d;
                case 32: goto L_0x012d;
                case 33: goto L_0x012d;
                case 34: goto L_0x012d;
                case 35: goto L_0x012d;
                default: goto L_0x011e;
            }
        L_0x011e:
            int[] r1 = r0.jj_la1
            int r3 = r0.jj_gen
            r1[r9] = r3
            r0.jj_consume_token(r2)
            org.apache.james.mime4j.field.datetime.parser.ParseException r0 = new org.apache.james.mime4j.field.datetime.parser.ParseException
            r0.<init>()
            throw r0
        L_0x012d:
            int r1 = r0.jj_ntk
            if (r1 != r2) goto L_0x0135
            int r1 = r21.jj_ntk()
        L_0x0135:
            r7 = -7
            r9 = -6
            r10 = -5
            switch(r1) {
                case 25: goto L_0x0191;
                case 26: goto L_0x018b;
                case 27: goto L_0x0184;
                case 28: goto L_0x017d;
                case 29: goto L_0x0176;
                case 30: goto L_0x0170;
                case 31: goto L_0x0169;
                case 32: goto L_0x0163;
                case 33: goto L_0x015c;
                case 34: goto L_0x0156;
                case 35: goto L_0x014a;
                default: goto L_0x013b;
            }
        L_0x013b:
            int[] r1 = r0.jj_la1
            int r3 = r0.jj_gen
            r1[r8] = r3
            r0.jj_consume_token(r2)
            org.apache.james.mime4j.field.datetime.parser.ParseException r0 = new org.apache.james.mime4j.field.datetime.parser.ParseException
            r0.<init>()
            throw r0
        L_0x014a:
            r1 = 35
            org.apache.james.mime4j.field.datetime.parser.Token r1 = r0.jj_consume_token(r1)
            java.lang.String r1 = r1.image
            r1.charAt(r12)
            goto L_0x0196
        L_0x0156:
            r1 = 34
            r0.jj_consume_token(r1)
            goto L_0x016e
        L_0x015c:
            r1 = 33
            r0.jj_consume_token(r1)
            r1 = -8
            goto L_0x0197
        L_0x0163:
            r1 = 32
            r0.jj_consume_token(r1)
            goto L_0x017b
        L_0x0169:
            r1 = 31
            r0.jj_consume_token(r1)
        L_0x016e:
            r1 = r7
            goto L_0x0197
        L_0x0170:
            r1 = 30
            r0.jj_consume_token(r1)
            goto L_0x0189
        L_0x0176:
            r1 = 29
            r0.jj_consume_token(r1)
        L_0x017b:
            r1 = r9
            goto L_0x0197
        L_0x017d:
            r1 = 28
            r0.jj_consume_token(r1)
            r1 = -4
            goto L_0x0197
        L_0x0184:
            r1 = 27
            r0.jj_consume_token(r1)
        L_0x0189:
            r1 = r10
            goto L_0x0197
        L_0x018b:
            r1 = 26
            r0.jj_consume_token(r1)
            goto L_0x0196
        L_0x0191:
            r1 = 25
            r0.jj_consume_token(r1)
        L_0x0196:
            r1 = r12
        L_0x0197:
            int r1 = r1 * 100
            goto L_0x01b5
        L_0x019a:
            r7 = 24
            org.apache.james.mime4j.field.datetime.parser.Token r7 = r0.jj_consume_token(r7)
            org.apache.james.mime4j.field.datetime.parser.Token r1 = r0.jj_consume_token(r1)
            int r1 = parseDigits(r1)
            java.lang.String r7 = r7.image
            java.lang.String r8 = "-"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x01b3
            goto L_0x01b4
        L_0x01b3:
            r2 = r11
        L_0x01b4:
            int r1 = r1 * r2
        L_0x01b5:
            org.apache.james.mime4j.field.datetime.parser.DateTimeParser$Time r2 = new org.apache.james.mime4j.field.datetime.parser.DateTimeParser$Time
            r2.<init>(r3, r6, r5, r1)
            org.apache.james.mime4j.dom.datetime.DateTime r1 = new org.apache.james.mime4j.dom.datetime.DateTime
            java.lang.String r14 = r4.getYear()
            int r15 = r4.getMonth()
            int r16 = r4.getDay()
            int r17 = r2.getHour()
            int r18 = r2.getMinute()
            int r19 = r2.getSecond()
            int r20 = r2.getZone()
            r13 = r1
            r13.<init>(r14, r15, r16, r17, r18, r19, r20)
            r0.jj_consume_token(r12)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.field.datetime.parser.DateTimeParser.parseAll():org.apache.james.mime4j.dom.datetime.DateTime");
    }
}

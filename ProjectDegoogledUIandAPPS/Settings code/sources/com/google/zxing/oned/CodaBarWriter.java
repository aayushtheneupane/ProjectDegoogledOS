package com.google.zxing.oned;

import java.util.Arrays;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};

    public boolean[] encode(String str) {
        int i;
        if (str.length() >= 2) {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean z = CodaBarReader.arrayContains(START_END_CHARS, upperCase) && CodaBarReader.arrayContains(START_END_CHARS, upperCase2);
            boolean z2 = CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase) && CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase2);
            if (z || z2) {
                char[] cArr = {'/', ':', '+', '.'};
                int i2 = 20;
                for (int i3 = 1; i3 < str.length() - 1; i3++) {
                    if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                        i2 += 9;
                    } else if (CodaBarReader.arrayContains(cArr, str.charAt(i3))) {
                        i2 += 10;
                    } else {
                        throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i3) + '\'');
                    }
                }
                boolean[] zArr = new boolean[(i2 + (str.length() - 1))];
                int i4 = 0;
                for (int i5 = 0; i5 < str.length(); i5++) {
                    char upperCase3 = Character.toUpperCase(str.charAt(i5));
                    if (i5 == str.length() - 1) {
                        if (upperCase3 == '*') {
                            upperCase3 = 'C';
                        } else if (upperCase3 == 'E') {
                            upperCase3 = 'D';
                        } else if (upperCase3 == 'N') {
                            upperCase3 = 'B';
                        } else if (upperCase3 == 'T') {
                            upperCase3 = 'A';
                        }
                    }
                    int i6 = 0;
                    while (true) {
                        char[] cArr2 = CodaBarReader.ALPHABET;
                        if (i6 >= cArr2.length) {
                            i = 0;
                            break;
                        } else if (upperCase3 == cArr2[i6]) {
                            i = CodaBarReader.CHARACTER_ENCODINGS[i6];
                            break;
                        } else {
                            i6++;
                        }
                    }
                    int i7 = 0;
                    boolean z3 = true;
                    int i8 = i4;
                    int i9 = 0;
                    while (i9 < 7) {
                        zArr[i8] = z3;
                        i8++;
                        if (((i >> (6 - i9)) & 1) == 0 || i7 == 1) {
                            z3 = !z3;
                            i9++;
                            i7 = 0;
                        } else {
                            i7++;
                        }
                    }
                    if (i5 < str.length() - 1) {
                        zArr[i8] = false;
                        i8++;
                    }
                    i4 = i8;
                }
                return zArr;
            }
            throw new IllegalArgumentException("Codabar should start/end with " + Arrays.toString(START_END_CHARS) + ", or start/end with " + Arrays.toString(ALT_START_END_CHARS));
        }
        throw new IllegalArgumentException("Codabar should start/end with start/stop symbols");
    }
}

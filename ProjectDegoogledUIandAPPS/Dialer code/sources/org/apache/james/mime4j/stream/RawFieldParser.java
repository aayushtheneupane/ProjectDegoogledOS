package org.apache.james.mime4j.stream;

import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;

public class RawFieldParser {
    static final BitSet COLON = INIT_BITSET(58);
    public static final RawFieldParser DEFAULT = new RawFieldParser();
    static final BitSet EQUAL_OR_SEMICOLON = INIT_BITSET(61, 59);
    static final BitSet SEMICOLON = INIT_BITSET(59);

    public static BitSet INIT_BITSET(int... iArr) {
        BitSet bitSet = new BitSet(iArr.length);
        for (int i : iArr) {
            bitSet.set(i);
        }
        return bitSet;
    }

    public void copyContent(ByteSequence byteSequence, ParserCursor parserCursor, BitSet bitSet, StringBuilder sb) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        for (int pos2 = parserCursor.getPos(); pos2 < upperBound; pos2++) {
            char byteAt = (char) (byteSequence.byteAt(pos2) & 255);
            if ((bitSet != null && bitSet.get(byteAt)) || CharsetUtil.isWhitespace(byteAt) || byteAt == '(') {
                break;
            }
            pos++;
            sb.append(byteAt);
        }
        parserCursor.updatePos(pos);
    }

    public RawField parseField(ByteSequence byteSequence) throws MimeException {
        if (byteSequence == null) {
            return null;
        }
        ParserCursor parserCursor = new ParserCursor(0, byteSequence.length());
        String parseToken = parseToken(byteSequence, parserCursor, COLON);
        if (!parserCursor.atEnd()) {
            return new RawField(byteSequence, parserCursor.getPos(), parseToken, (String) null);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid MIME field: no name/value separator found: ");
        outline13.append(byteSequence.toString());
        throw new MimeException(outline13.toString());
    }

    public RawBody parseRawBody(RawField rawField) {
        NameValuePair nameValuePair;
        ByteSequence raw = rawField.getRaw();
        int delimiterIdx = rawField.getDelimiterIdx() + 1;
        String str = null;
        if (raw == null) {
            String body = rawField.getBody();
            if (body == null) {
                return new RawBody("", (List<NameValuePair>) null);
            }
            raw = R$style.encode(body);
            delimiterIdx = 0;
        }
        ParserCursor parserCursor = new ParserCursor(delimiterIdx, raw.length());
        String parseToken = parseToken(raw, parserCursor, SEMICOLON);
        if (parserCursor.atEnd()) {
            return new RawBody(parseToken, new ArrayList());
        }
        parserCursor.updatePos(parserCursor.getPos() + 1);
        ArrayList arrayList = new ArrayList();
        skipWhiteSpace(raw, parserCursor);
        while (!parserCursor.atEnd()) {
            String parseToken2 = parseToken(raw, parserCursor, EQUAL_OR_SEMICOLON);
            if (parserCursor.atEnd()) {
                nameValuePair = new NameValuePair(parseToken2, str);
            } else {
                byte byteAt = raw.byteAt(parserCursor.getPos());
                parserCursor.updatePos(parserCursor.getPos() + 1);
                if (byteAt == 59) {
                    nameValuePair = new NameValuePair(parseToken2, str);
                } else {
                    BitSet bitSet = SEMICOLON;
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        boolean z = false;
                        while (!parserCursor.atEnd()) {
                            char byteAt2 = (char) (raw.byteAt(parserCursor.getPos()) & 255);
                            if (bitSet != null && bitSet.get(byteAt2)) {
                                break;
                            } else if (CharsetUtil.isWhitespace(byteAt2)) {
                                skipWhiteSpace(raw, parserCursor);
                                z = true;
                            } else if (byteAt2 == '(') {
                                skipComment(raw, parserCursor);
                            } else if (byteAt2 == '\"') {
                                if (sb.length() > 0 && z) {
                                    sb.append(' ');
                                }
                                if (!parserCursor.atEnd()) {
                                    int pos = parserCursor.getPos();
                                    int pos2 = parserCursor.getPos();
                                    int upperBound = parserCursor.getUpperBound();
                                    if (((char) (raw.byteAt(pos) & 255)) == '\"') {
                                        int i = pos2 + 1;
                                        int i2 = pos + 1;
                                        boolean z2 = false;
                                        while (true) {
                                            if (i >= upperBound) {
                                                break;
                                            }
                                            char byteAt3 = (char) (raw.byteAt(i) & 255);
                                            if (z2) {
                                                if (!(byteAt3 == '\"' || byteAt3 == '\\')) {
                                                    sb.append('\\');
                                                }
                                                sb.append(byteAt3);
                                                z2 = false;
                                            } else if (byteAt3 == '\"') {
                                                i2++;
                                                break;
                                            } else if (byteAt3 == '\\') {
                                                z2 = true;
                                            } else if (!(byteAt3 == 13 || byteAt3 == 10)) {
                                                sb.append(byteAt3);
                                            }
                                            i++;
                                            i2++;
                                        }
                                        parserCursor.updatePos(i2);
                                    }
                                }
                            } else {
                                if (sb.length() > 0 && z) {
                                    sb.append(' ');
                                }
                                copyContent(raw, parserCursor, bitSet, sb);
                            }
                        }
                        break;
                    }
                    String sb2 = sb.toString();
                    if (!parserCursor.atEnd()) {
                        parserCursor.updatePos(parserCursor.getPos() + 1);
                    }
                    nameValuePair = new NameValuePair(parseToken2, sb2);
                }
            }
            arrayList.add(nameValuePair);
            str = null;
        }
        return new RawBody(parseToken, arrayList);
    }

    public String parseToken(ByteSequence byteSequence, ParserCursor parserCursor, BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        loop0:
        while (true) {
            boolean z = false;
            while (!parserCursor.atEnd()) {
                char byteAt = (char) (byteSequence.byteAt(parserCursor.getPos()) & 255);
                if (bitSet != null && bitSet.get(byteAt)) {
                    break loop0;
                } else if (CharsetUtil.isWhitespace(byteAt)) {
                    skipWhiteSpace(byteSequence, parserCursor);
                    z = true;
                } else if (byteAt == '(') {
                    skipComment(byteSequence, parserCursor);
                } else {
                    if (sb.length() > 0 && z) {
                        sb.append(' ');
                    }
                    copyContent(byteSequence, parserCursor, bitSet, sb);
                }
            }
            break loop0;
        }
        return sb.toString();
    }

    public void skipComment(ByteSequence byteSequence, ParserCursor parserCursor) {
        if (!parserCursor.atEnd()) {
            int pos = parserCursor.getPos();
            int pos2 = parserCursor.getPos();
            int upperBound = parserCursor.getUpperBound();
            if (((char) (byteSequence.byteAt(pos) & 255)) == '(') {
                int i = pos2 + 1;
                int i2 = pos + 1;
                int i3 = 1;
                boolean z = false;
                while (true) {
                    if (i >= upperBound) {
                        break;
                    }
                    char byteAt = (char) (byteSequence.byteAt(i) & 255);
                    if (z) {
                        z = false;
                    } else if (byteAt == '\\') {
                        z = true;
                    } else if (byteAt == '(') {
                        i3++;
                    } else if (byteAt == ')') {
                        i3--;
                    }
                    if (i3 <= 0) {
                        i2++;
                        break;
                    } else {
                        i++;
                        i2++;
                    }
                }
                parserCursor.updatePos(i2);
            }
        }
    }

    public void skipWhiteSpace(ByteSequence byteSequence, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int pos2 = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos2 < upperBound && CharsetUtil.isWhitespace((char) (byteSequence.byteAt(pos2) & 255))) {
            pos++;
            pos2++;
        }
        parserCursor.updatePos(pos);
    }
}

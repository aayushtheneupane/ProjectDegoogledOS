package p000;

import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.util.List;
import java.util.Map;

/* renamed from: cr */
/* compiled from: PG */
final class C0076cr {

    /* renamed from: a */
    public final Appendable f5449a;

    /* renamed from: b */
    public int f5450b;

    /* renamed from: c */
    public List f5451c = null;

    public C0076cr(StringBuffer stringBuffer) {
        this.f5449a = stringBuffer;
        this.f5450b = stringBuffer.length();
    }

    public C0076cr(StringBuilder sb) {
        this.f5449a = sb;
        this.f5450b = sb.length();
    }

    /* renamed from: a */
    public final void mo3771a(CharSequence charSequence) {
        try {
            this.f5449a.append(charSequence);
            this.f5450b += charSequence.length();
        } catch (IOException e) {
            throw new C0100do(e);
        }
    }

    /* renamed from: a */
    public final void mo3772a(Format format, Object obj) {
        if (this.f5451c != null) {
            AttributedCharacterIterator formatToCharacterIterator = format.formatToCharacterIterator(obj);
            int i = this.f5450b;
            Appendable appendable = this.f5449a;
            try {
                int beginIndex = formatToCharacterIterator.getBeginIndex();
                int endIndex = formatToCharacterIterator.getEndIndex();
                int i2 = endIndex - beginIndex;
                if (beginIndex < endIndex) {
                    appendable.append(formatToCharacterIterator.first());
                    while (true) {
                        beginIndex++;
                        if (beginIndex >= endIndex) {
                            break;
                        }
                        appendable.append(formatToCharacterIterator.next());
                    }
                }
                this.f5450b = i2 + i;
                formatToCharacterIterator.first();
                int index = formatToCharacterIterator.getIndex();
                int endIndex2 = formatToCharacterIterator.getEndIndex();
                int i3 = i - index;
                while (index < endIndex2) {
                    Map<AttributedCharacterIterator.Attribute, Object> attributes = formatToCharacterIterator.getAttributes();
                    int runLimit = formatToCharacterIterator.getRunLimit();
                    if (attributes.size() != 0) {
                        for (Map.Entry next : attributes.entrySet()) {
                            this.f5451c.add(new C0077cs((AttributedCharacterIterator.Attribute) next.getKey(), next.getValue(), i3 + index, i3 + runLimit));
                        }
                    }
                    formatToCharacterIterator.setIndex(runLimit);
                    index = runLimit;
                }
            } catch (IOException e) {
                throw new C0100do(e);
            }
        } else {
            mo3771a(format.format(obj));
        }
    }

    /* renamed from: a */
    public final void mo3773a(Format format, Object obj, String str) {
        if (this.f5451c == null && str != null) {
            mo3771a(str);
        } else {
            mo3772a(format, obj);
        }
    }
}

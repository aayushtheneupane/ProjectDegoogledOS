package okio;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class Buffer implements BufferedSource, BufferedSink, Cloneable {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    Segment head;
    long size;

    public Object clone() throws CloneNotSupportedException {
        Buffer buffer = new Buffer();
        if (this.size != 0) {
            buffer.head = new Segment(this.head);
            Segment segment = buffer.head;
            segment.prev = segment;
            segment.next = segment;
            Segment segment2 = this.head;
            while (true) {
                segment2 = segment2.next;
                if (segment2 == this.head) {
                    break;
                }
                buffer.head.prev.push(new Segment(segment2));
            }
            buffer.size = this.size;
        }
        return buffer;
    }

    public void close() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j = this.size;
        if (j != buffer.size) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment segment = this.head;
        Segment segment2 = buffer.head;
        int i = segment.pos;
        int i2 = segment2.pos;
        while (j2 < this.size) {
            long min = (long) Math.min(segment.limit - i, segment2.limit - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (((long) i5) < min) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (segment.data[i4] != segment2.data[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == segment.limit) {
                segment = segment.next;
                i = segment.pos;
            } else {
                i = i4;
            }
            if (i3 == segment2.limit) {
                segment2 = segment2.next;
                i2 = segment2.pos;
            } else {
                i2 = i3;
            }
            j2 += min;
        }
        return true;
    }

    public void flush() {
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i;
    }

    public String toString() {
        ByteString byteString;
        long j = this.size;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                byteString = ByteString.EMPTY;
            } else {
                byteString = new SegmentedByteString(this, i);
            }
            return byteString.toString();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("size > Integer.MAX_VALUE: ");
        outline13.append(this.size);
        throw new IllegalArgumentException(outline13.toString());
    }
}

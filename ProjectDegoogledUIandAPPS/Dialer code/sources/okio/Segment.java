package okio;

final class Segment {
    final byte[] data;
    int limit;
    Segment next;
    boolean owner;
    int pos;
    Segment prev;
    boolean shared;

    Segment(Segment segment) {
        byte[] bArr = segment.data;
        int i = segment.pos;
        int i2 = segment.limit;
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.owner = false;
        this.shared = true;
        segment.shared = true;
    }

    public Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }
}

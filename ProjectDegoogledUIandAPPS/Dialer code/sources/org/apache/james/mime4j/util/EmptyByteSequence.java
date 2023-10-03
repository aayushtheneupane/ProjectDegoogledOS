package org.apache.james.mime4j.util;

final class EmptyByteSequence implements ByteSequence {
    EmptyByteSequence() {
    }

    public byte byteAt(int i) {
        throw new IndexOutOfBoundsException();
    }

    public int length() {
        return 0;
    }
}

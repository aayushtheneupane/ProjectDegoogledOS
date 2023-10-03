package org.apache.james.mime4j.util;

public interface ByteSequence {
    static {
        new EmptyByteSequence();
    }

    byte byteAt(int i);

    int length();
}

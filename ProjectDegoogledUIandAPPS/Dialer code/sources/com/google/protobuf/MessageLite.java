package com.google.protobuf;

import java.io.IOException;

public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
    }

    int getSerializedSize();

    void writeTo(CodedOutputStream codedOutputStream) throws IOException;
}

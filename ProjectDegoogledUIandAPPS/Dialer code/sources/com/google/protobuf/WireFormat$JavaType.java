package com.google.protobuf;

public enum WireFormat$JavaType {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(ByteString.EMPTY),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object defaultDefault;

    private WireFormat$JavaType(Object obj) {
        this.defaultDefault = obj;
    }
}

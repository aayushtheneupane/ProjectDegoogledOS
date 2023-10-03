package com.google.protobuf;

public enum WireFormat$FieldType {
    DOUBLE(WireFormat$JavaType.DOUBLE, 1),
    FLOAT(WireFormat$JavaType.FLOAT, 5),
    INT64(WireFormat$JavaType.LONG, 0),
    UINT64(WireFormat$JavaType.LONG, 0),
    INT32(WireFormat$JavaType.INT, 0),
    FIXED64(WireFormat$JavaType.LONG, 1),
    FIXED32(WireFormat$JavaType.INT, 5),
    BOOL(WireFormat$JavaType.BOOLEAN, 0),
    STRING(WireFormat$JavaType.STRING, 2) {
    },
    GROUP(WireFormat$JavaType.MESSAGE, 3) {
    },
    MESSAGE(WireFormat$JavaType.MESSAGE, 2) {
    },
    BYTES(WireFormat$JavaType.BYTE_STRING, 2) {
    },
    UINT32(WireFormat$JavaType.INT, 0),
    ENUM(WireFormat$JavaType.ENUM, 0),
    SFIXED32(WireFormat$JavaType.INT, 5),
    SFIXED64(WireFormat$JavaType.LONG, 1),
    SINT32(WireFormat$JavaType.INT, 0),
    SINT64(WireFormat$JavaType.LONG, 0);
    
    private final WireFormat$JavaType javaType;
    private final int wireType;

    private WireFormat$FieldType(WireFormat$JavaType wireFormat$JavaType, int i) {
        this.javaType = wireFormat$JavaType;
        this.wireType = i;
    }

    public WireFormat$JavaType getJavaType() {
        return this.javaType;
    }
}

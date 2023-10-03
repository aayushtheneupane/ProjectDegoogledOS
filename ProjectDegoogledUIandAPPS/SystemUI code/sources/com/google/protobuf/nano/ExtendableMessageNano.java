package com.google.protobuf.nano;

import com.google.protobuf.nano.ExtendableMessageNano;
import java.io.IOException;

public abstract class ExtendableMessageNano<M extends ExtendableMessageNano<M>> extends MessageNano {
    protected FieldArray unknownFieldData;

    /* access modifiers changed from: protected */
    public int computeSerializedSize() {
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray == null) {
            return 0;
        }
        fieldArray.size();
        throw null;
    }

    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray != null) {
            fieldArray.size();
            throw null;
        }
    }

    public M clone() throws CloneNotSupportedException {
        M m = (ExtendableMessageNano) super.clone();
        InternalNano.cloneUnknownFieldData(this, m);
        return m;
    }
}

package org.apache.james.mime4j.stream;

import java.io.InputStream;

public class RawEntity implements EntityStateMachine {
    private EntityState state = EntityState.T_RAW_ENTITY;
    private final InputStream stream;

    RawEntity(InputStream inputStream) {
        this.stream = inputStream;
    }

    public EntityStateMachine advance() {
        this.state = EntityState.T_END_OF_STREAM;
        return null;
    }

    public BodyDescriptor getBodyDescriptor() {
        return null;
    }

    public InputStream getContentStream() {
        return this.stream;
    }

    public InputStream getDecodedContentStream() throws IllegalStateException {
        throw new IllegalStateException("Raw entity does not support stream decoding");
    }

    public Field getField() throws IllegalStateException {
        return null;
    }

    public EntityState getState() {
        return this.state;
    }

    public void setRecursionMode(RecursionMode recursionMode) {
    }
}

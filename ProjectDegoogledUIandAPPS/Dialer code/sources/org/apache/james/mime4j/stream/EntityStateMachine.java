package org.apache.james.mime4j.stream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;

public interface EntityStateMachine {
    EntityStateMachine advance() throws IOException, MimeException;

    BodyDescriptor getBodyDescriptor() throws IllegalStateException;

    InputStream getContentStream() throws IllegalStateException;

    InputStream getDecodedContentStream() throws IllegalStateException;

    Field getField() throws IllegalStateException;

    EntityState getState();

    void setRecursionMode(RecursionMode recursionMode);
}

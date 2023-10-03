package org.apache.james.mime4j.stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.p012io.LineNumberInputStream;
import org.apache.james.mime4j.util.ByteSequence;

public class MimeTokenStream {
    private final BodyDescriptorBuilder bodyDescBuilder;
    private final MimeConfig config;
    private EntityStateMachine currentStateMachine;
    private final LinkedList<EntityStateMachine> entities = new LinkedList<>();
    private final FieldBuilder fieldBuilder;
    private final DecodeMonitor monitor;
    private RecursionMode recursionMode = RecursionMode.M_RECURSE;
    private MimeEntity rootentity;
    private EntityState state = EntityState.T_END_OF_STREAM;

    public MimeTokenStream(MimeConfig mimeConfig, DecodeMonitor decodeMonitor, BodyDescriptorBuilder bodyDescriptorBuilder) {
        this.config = mimeConfig == null ? new MimeConfig() : mimeConfig;
        this.fieldBuilder = new DefaultFieldBuilder(this.config.getMaxHeaderLen());
        this.monitor = decodeMonitor == null ? this.config.isStrictParsing() ? DecodeMonitor.STRICT : DecodeMonitor.SILENT : decodeMonitor;
        this.bodyDescBuilder = bodyDescriptorBuilder == null ? new FallbackBodyDescriptorBuilder((String) null, (DecodeMonitor) null) : bodyDescriptorBuilder;
    }

    private void doParse(InputStream inputStream, EntityState entityState) {
        LineNumberInputStream lineNumberInputStream;
        LineNumberInputStream lineNumberInputStream2;
        if (this.config.isCountLineNumbers()) {
            lineNumberInputStream2 = new LineNumberInputStream(inputStream);
            lineNumberInputStream = lineNumberInputStream2;
        } else {
            lineNumberInputStream = inputStream;
            lineNumberInputStream2 = null;
        }
        this.rootentity = new MimeEntity(lineNumberInputStream2, lineNumberInputStream, this.config, entityState, EntityState.T_END_MESSAGE, this.monitor, this.fieldBuilder, this.bodyDescBuilder);
        this.rootentity.setRecursionMode(this.recursionMode);
        this.currentStateMachine = this.rootentity;
        this.entities.clear();
        this.entities.add(this.currentStateMachine);
        this.state = this.currentStateMachine.getState();
    }

    public BodyDescriptor getBodyDescriptor() {
        return this.currentStateMachine.getBodyDescriptor();
    }

    public MimeConfig getConfig() {
        return this.config;
    }

    public InputStream getDecodedInputStream() {
        return this.currentStateMachine.getDecodedContentStream();
    }

    public Field getField() {
        return this.currentStateMachine.getField();
    }

    public InputStream getInputStream() {
        return this.currentStateMachine.getContentStream();
    }

    public EntityState getState() {
        return this.state;
    }

    public EntityState next() throws IOException, MimeException {
        if (this.state == EntityState.T_END_OF_STREAM || this.currentStateMachine == null) {
            throw new IllegalStateException("No more tokens are available.");
        }
        while (true) {
            EntityStateMachine entityStateMachine = this.currentStateMachine;
            if (entityStateMachine != null) {
                EntityStateMachine advance = entityStateMachine.advance();
                if (advance != null) {
                    this.entities.add(advance);
                    this.currentStateMachine = advance;
                }
                this.state = this.currentStateMachine.getState();
                EntityState entityState = this.state;
                if (entityState != EntityState.T_END_OF_STREAM) {
                    return entityState;
                }
                this.entities.removeLast();
                if (this.entities.isEmpty()) {
                    this.currentStateMachine = null;
                } else {
                    this.currentStateMachine = this.entities.getLast();
                    this.currentStateMachine.setRecursionMode(this.recursionMode);
                }
            } else {
                this.state = EntityState.T_END_OF_STREAM;
                return this.state;
            }
        }
    }

    public void parse(InputStream inputStream) {
        doParse(inputStream, EntityState.T_START_MESSAGE);
    }

    public Field parseHeadless(InputStream inputStream, String str) {
        if (str != null) {
            try {
                RawField rawField = new RawField((ByteSequence) null, -1, "Content-Type", str);
                ((FallbackBodyDescriptorBuilder) this.bodyDescBuilder).addField(rawField);
                doParse(inputStream, EntityState.T_END_HEADER);
                try {
                    next();
                    return rawField;
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                } catch (MimeException e2) {
                    throw new IllegalStateException(e2);
                }
            } catch (MimeException e3) {
                throw new IllegalArgumentException(e3.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Content type may not be null");
        }
    }
}

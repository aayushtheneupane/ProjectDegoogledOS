package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.util.ByteSequence;

public abstract class AbstractField implements ParsedField {
    protected final Field rawField;

    protected AbstractField(Field field, DecodeMonitor decodeMonitor) {
        this.rawField = field;
        if (decodeMonitor == null) {
            DecodeMonitor decodeMonitor2 = DecodeMonitor.SILENT;
        }
    }

    public String getBody() {
        return this.rawField.getBody();
    }

    public String getName() {
        return this.rawField.getName();
    }

    public ByteSequence getRaw() {
        return this.rawField.getRaw();
    }

    public String toString() {
        return this.rawField.toString();
    }
}

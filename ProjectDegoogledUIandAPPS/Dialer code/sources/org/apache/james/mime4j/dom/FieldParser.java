package org.apache.james.mime4j.dom;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public interface FieldParser<T extends ParsedField> {
    T parse(Field field, DecodeMonitor decodeMonitor);
}

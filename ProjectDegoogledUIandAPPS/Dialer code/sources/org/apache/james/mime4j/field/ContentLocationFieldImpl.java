package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLocationField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentLocationFieldImpl extends AbstractField implements ContentLocationField {
    public static final FieldParser<ContentLocationField> PARSER = new FieldParser<ContentLocationField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentLocationFieldImpl(field, decodeMonitor);
        }
    };

    ContentLocationFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

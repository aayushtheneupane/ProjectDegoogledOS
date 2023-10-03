package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentIdField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentIdFieldImpl extends AbstractField implements ContentIdField {
    public static final FieldParser<ContentIdField> PARSER = new FieldParser<ContentIdField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentIdFieldImpl(field, decodeMonitor);
        }
    };

    ContentIdFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

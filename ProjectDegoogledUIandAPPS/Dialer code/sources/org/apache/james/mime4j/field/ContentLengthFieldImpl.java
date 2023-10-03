package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLengthField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentLengthFieldImpl extends AbstractField implements ContentLengthField {
    public static final FieldParser<ContentLengthField> PARSER = new FieldParser<ContentLengthField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentLengthFieldImpl(field, decodeMonitor);
        }
    };

    ContentLengthFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentTransferEncodingFieldImpl extends AbstractField implements ContentTransferEncodingField {
    public static final FieldParser<ContentTransferEncodingField> PARSER = new FieldParser<ContentTransferEncodingField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentTransferEncodingFieldImpl(field, decodeMonitor);
        }
    };

    ContentTransferEncodingFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

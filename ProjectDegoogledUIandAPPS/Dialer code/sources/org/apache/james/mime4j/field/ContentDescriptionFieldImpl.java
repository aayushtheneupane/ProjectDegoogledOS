package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentDescriptionField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentDescriptionFieldImpl extends AbstractField implements ContentDescriptionField {
    public static final FieldParser<ContentDescriptionField> PARSER = new FieldParser<ContentDescriptionField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentDescriptionFieldImpl(field, decodeMonitor);
        }
    };

    ContentDescriptionFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

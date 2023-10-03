package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentMD5Field;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentMD5FieldImpl extends AbstractField implements ContentMD5Field {
    public static final FieldParser<ContentMD5Field> PARSER = new FieldParser<ContentMD5Field>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentMD5FieldImpl(field, decodeMonitor);
        }
    };

    ContentMD5FieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

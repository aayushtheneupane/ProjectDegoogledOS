package org.apache.james.mime4j.field;

import java.util.HashMap;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentTypeFieldImpl extends AbstractField implements ContentTypeField {
    public static final FieldParser<ContentTypeField> PARSER = new FieldParser<ContentTypeField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentTypeFieldImpl(field, decodeMonitor);
        }
    };

    ContentTypeFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
        new HashMap();
    }
}

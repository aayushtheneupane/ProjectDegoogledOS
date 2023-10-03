package org.apache.james.mime4j.field;

import java.util.HashMap;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentDispositionFieldImpl extends AbstractField implements ContentDispositionField {
    public static final FieldParser<ContentDispositionField> PARSER = new FieldParser<ContentDispositionField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentDispositionFieldImpl(field, decodeMonitor);
        }
    };

    ContentDispositionFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
        new HashMap();
    }
}

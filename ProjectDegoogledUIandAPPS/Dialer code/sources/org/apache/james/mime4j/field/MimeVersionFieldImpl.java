package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.MimeVersionField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class MimeVersionFieldImpl extends AbstractField implements MimeVersionField {
    public static final FieldParser<MimeVersionField> PARSER = new FieldParser<MimeVersionField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new MimeVersionFieldImpl(field, decodeMonitor);
        }
    };

    MimeVersionFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLanguageField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class ContentLanguageFieldImpl extends AbstractField implements ContentLanguageField {
    public static final FieldParser<ContentLanguageField> PARSER = new FieldParser<ContentLanguageField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new ContentLanguageFieldImpl(field, decodeMonitor);
        }
    };

    ContentLanguageFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

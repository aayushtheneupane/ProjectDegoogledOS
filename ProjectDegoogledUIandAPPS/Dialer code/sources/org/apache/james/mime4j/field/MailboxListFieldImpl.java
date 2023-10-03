package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class MailboxListFieldImpl extends AbstractField implements MailboxListField {
    public static final FieldParser<MailboxListField> PARSER = new FieldParser<MailboxListField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new MailboxListFieldImpl(field, decodeMonitor);
        }
    };

    MailboxListFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

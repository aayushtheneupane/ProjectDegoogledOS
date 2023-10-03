package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class MailboxFieldImpl extends AbstractField implements MailboxField {
    public static final FieldParser<MailboxField> PARSER = new FieldParser<MailboxField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new MailboxFieldImpl(field, decodeMonitor);
        }
    };

    MailboxFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

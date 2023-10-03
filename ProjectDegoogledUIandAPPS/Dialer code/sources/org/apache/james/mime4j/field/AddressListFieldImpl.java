package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class AddressListFieldImpl extends AbstractField implements AddressListField {
    public static final FieldParser<AddressListField> PARSER = new FieldParser<AddressListField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new AddressListFieldImpl(field, decodeMonitor);
        }
    };

    AddressListFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }
}

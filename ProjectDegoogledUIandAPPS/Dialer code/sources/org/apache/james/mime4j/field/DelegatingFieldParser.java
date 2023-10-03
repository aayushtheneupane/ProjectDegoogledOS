package org.apache.james.mime4j.field;

import java.util.HashMap;
import java.util.Map;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class DelegatingFieldParser implements FieldParser<ParsedField> {
    private final FieldParser<? extends ParsedField> defaultParser;
    private final Map<String, FieldParser<? extends ParsedField>> parsers = new HashMap();

    public DelegatingFieldParser(FieldParser<? extends ParsedField> fieldParser) {
        this.defaultParser = fieldParser;
    }

    public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
        FieldParser fieldParser = this.parsers.get(field.getName().toLowerCase());
        if (fieldParser == null) {
            fieldParser = this.defaultParser;
        }
        return fieldParser.parse(field, decodeMonitor);
    }

    public void setFieldParser(String str, FieldParser<? extends ParsedField> fieldParser) {
        this.parsers.put(str.toLowerCase(), fieldParser);
    }
}

package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Date;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.field.datetime.parser.DateTimeParser;
import org.apache.james.mime4j.field.datetime.parser.ParseException;
import org.apache.james.mime4j.field.datetime.parser.TokenMgrError;
import org.apache.james.mime4j.stream.Field;

public class DateTimeFieldImpl extends AbstractField implements DateTimeField {
    public static final FieldParser<DateTimeField> PARSER = new FieldParser<DateTimeField>() {
        public ParsedField parse(Field field, DecodeMonitor decodeMonitor) {
            return new DateTimeFieldImpl(field, decodeMonitor);
        }
    };
    private Date date;
    private boolean parsed = false;

    DateTimeFieldImpl(Field field, DecodeMonitor decodeMonitor) {
        super(field, decodeMonitor);
    }

    public Date getDate() {
        if (!this.parsed) {
            try {
                this.date = new DateTimeParser(new StringReader(this.rawField.getBody())).parseAll().getDate();
            } catch (ParseException unused) {
            } catch (TokenMgrError e) {
                new ParseException(e.getMessage());
            }
            this.parsed = true;
        }
        return this.date;
    }
}

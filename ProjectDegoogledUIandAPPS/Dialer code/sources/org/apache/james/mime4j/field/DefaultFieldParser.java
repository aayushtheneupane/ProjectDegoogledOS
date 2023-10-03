package org.apache.james.mime4j.field;

import android.support.p002v7.appcompat.R$style;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.RawFieldParser;

public class DefaultFieldParser extends DelegatingFieldParser {
    private static final FieldParser<ParsedField> PARSER = new DefaultFieldParser();

    public DefaultFieldParser() {
        super(UnstructuredFieldImpl.PARSER);
        setFieldParser("Content-Type", ContentTypeFieldImpl.PARSER);
        setFieldParser("Content-Length", ContentLengthFieldImpl.PARSER);
        setFieldParser("Content-Transfer-Encoding", ContentTransferEncodingFieldImpl.PARSER);
        setFieldParser("Content-Disposition", ContentDispositionFieldImpl.PARSER);
        setFieldParser("Content-ID", ContentIdFieldImpl.PARSER);
        setFieldParser("Content-MD5", ContentMD5FieldImpl.PARSER);
        setFieldParser("Content-Description", ContentDescriptionFieldImpl.PARSER);
        setFieldParser("Content-Language", ContentLanguageFieldImpl.PARSER);
        setFieldParser("Content-Location", ContentLocationFieldImpl.PARSER);
        setFieldParser("MIME-Version", MimeVersionFieldImpl.PARSER);
        FieldParser<DateTimeField> fieldParser = DateTimeFieldImpl.PARSER;
        setFieldParser("Date", fieldParser);
        setFieldParser("Resent-Date", fieldParser);
        FieldParser<MailboxListField> fieldParser2 = MailboxListFieldImpl.PARSER;
        setFieldParser("From", fieldParser2);
        setFieldParser("Resent-From", fieldParser2);
        FieldParser<MailboxField> fieldParser3 = MailboxFieldImpl.PARSER;
        setFieldParser("Sender", fieldParser3);
        setFieldParser("Resent-Sender", fieldParser3);
        FieldParser<AddressListField> fieldParser4 = AddressListFieldImpl.PARSER;
        setFieldParser("To", fieldParser4);
        setFieldParser("Resent-To", fieldParser4);
        setFieldParser("Cc", fieldParser4);
        setFieldParser("Resent-Cc", fieldParser4);
        setFieldParser("Bcc", fieldParser4);
        setFieldParser("Resent-Bcc", fieldParser4);
        setFieldParser("Reply-To", fieldParser4);
    }

    public static ParsedField parse(String str) throws MimeException {
        DecodeMonitor decodeMonitor = DecodeMonitor.SILENT;
        return PARSER.parse(RawFieldParser.DEFAULT.parseField(R$style.encode(str)), decodeMonitor);
    }
}

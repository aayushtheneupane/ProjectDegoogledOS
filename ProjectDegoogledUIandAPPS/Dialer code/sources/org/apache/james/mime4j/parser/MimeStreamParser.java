package org.apache.james.mime4j.parser;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.BodyDescriptorBuilder;
import org.apache.james.mime4j.stream.EntityState;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.MimeConfig;
import org.apache.james.mime4j.stream.MimeTokenStream;

public class MimeStreamParser {
    private boolean contentDecoding;
    private ContentHandler handler = null;
    private final MimeTokenStream mimeTokenStream;

    public MimeStreamParser() {
        MimeTokenStream mimeTokenStream2 = new MimeTokenStream(new MimeConfig(), (DecodeMonitor) null, (BodyDescriptorBuilder) null);
        this.mimeTokenStream = mimeTokenStream2;
        this.contentDecoding = false;
    }

    public void parse(InputStream inputStream) throws MimeException, IOException {
        InputStream inputStream2;
        MimeConfig config = this.mimeTokenStream.getConfig();
        if (config.getHeadlessParsing() != null) {
            Field parseHeadless = this.mimeTokenStream.parseHeadless(inputStream, config.getHeadlessParsing());
            this.handler.startMessage();
            this.handler.startHeader();
            this.handler.field(parseHeadless);
            this.handler.endHeader();
        } else {
            this.mimeTokenStream.parse(inputStream);
        }
        while (true) {
            EntityState state = this.mimeTokenStream.getState();
            switch (state.ordinal()) {
                case 0:
                    this.handler.startMessage();
                    break;
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                    this.handler.endMessage();
                    break;
                case 2:
                    this.handler.raw(this.mimeTokenStream.getInputStream());
                    throw null;
                case 3:
                    this.handler.startHeader();
                    break;
                case 4:
                    this.handler.field(this.mimeTokenStream.getField());
                    break;
                case 5:
                    this.handler.endHeader();
                    break;
                case 6:
                    this.handler.startMultipart(this.mimeTokenStream.getBodyDescriptor());
                    break;
                case 7:
                    this.handler.endMultipart();
                    break;
                case 8:
                    this.handler.preamble(this.mimeTokenStream.getInputStream());
                    break;
                case 9:
                    this.handler.epilogue(this.mimeTokenStream.getInputStream());
                    break;
                case 10:
                    this.handler.startBodyPart();
                    break;
                case 11:
                    this.handler.endBodyPart();
                    break;
                case 12:
                    BodyDescriptor bodyDescriptor = this.mimeTokenStream.getBodyDescriptor();
                    if (this.contentDecoding) {
                        inputStream2 = this.mimeTokenStream.getDecodedInputStream();
                    } else {
                        inputStream2 = this.mimeTokenStream.getInputStream();
                    }
                    this.handler.body(bodyDescriptor, inputStream2);
                    break;
                case 13:
                    return;
                default:
                    throw new IllegalStateException(GeneratedOutlineSupport.outline6("Invalid state: ", state));
            }
            this.mimeTokenStream.next();
        }
    }

    public void setContentHandler(ContentHandler contentHandler) {
        this.handler = contentHandler;
    }
}

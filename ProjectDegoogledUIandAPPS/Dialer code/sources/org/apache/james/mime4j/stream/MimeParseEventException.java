package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.MimeException;

public class MimeParseEventException extends MimeException {
    private static final long serialVersionUID = 4632991604246852302L;
    private final Event event;

    public MimeParseEventException(Event event2) {
        super(event2.toString());
        this.event = event2;
    }
}

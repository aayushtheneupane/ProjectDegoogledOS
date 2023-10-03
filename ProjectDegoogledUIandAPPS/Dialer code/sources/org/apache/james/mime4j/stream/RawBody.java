package org.apache.james.mime4j.stream;

import java.util.ArrayList;
import java.util.List;

public final class RawBody {
    private final List<NameValuePair> params;
    private final String value;

    RawBody(String str, List<NameValuePair> list) {
        if (str != null) {
            this.value = str;
            this.params = list == null ? new ArrayList<>() : list;
            return;
        }
        throw new IllegalArgumentException("Field value not be null");
    }

    public List<NameValuePair> getParams() {
        return new ArrayList(this.params);
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        sb.append("; ");
        for (NameValuePair append : this.params) {
            sb.append("; ");
            sb.append(append);
        }
        return sb.toString();
    }
}

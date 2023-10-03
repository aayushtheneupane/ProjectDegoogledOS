package com.android.voicemail.impl.mail.internet;

import com.android.voicemail.impl.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class MimeHeader {
    private static final String[] WRITE_OMIT_FIELDS = {"X-Android-Attachment-StoreData"};
    protected final ArrayList<Field> fields = new ArrayList<>();

    private static class Field {
        final String name;
        final String value;

        public Field(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String toString() {
            return this.name + "=" + this.value;
        }
    }

    public String getFirstHeader(String str) throws MessagingException {
        String[] header = getHeader(str);
        if (header == null) {
            return null;
        }
        return header[0];
    }

    public String[] getHeader(String str) throws MessagingException {
        ArrayList arrayList = new ArrayList();
        Iterator<Field> it = this.fields.iterator();
        while (it.hasNext()) {
            Field next = it.next();
            if (next.name.equalsIgnoreCase(str)) {
                arrayList.add(next.value);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void setHeader(String str, String str2) throws MessagingException {
        if (str != null && str2 != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<Field> it = this.fields.iterator();
            while (it.hasNext()) {
                Field next = it.next();
                if (next.name.equalsIgnoreCase(str)) {
                    arrayList.add(next);
                }
            }
            this.fields.removeAll(arrayList);
            this.fields.add(new Field(str, str2));
        }
    }

    public String toString() {
        ArrayList<Field> arrayList = this.fields;
        if (arrayList == null) {
            return null;
        }
        return arrayList.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 1024);
        Iterator<Field> it = this.fields.iterator();
        while (it.hasNext()) {
            Field next = it.next();
            String[] strArr = WRITE_OMIT_FIELDS;
            String str = next.name;
            int length = strArr.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (strArr[i].equals(str)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                z = true;
            }
            if (!z) {
                bufferedWriter.write(next.name + ": " + next.value + "\r\n");
            }
        }
        bufferedWriter.flush();
    }
}

package org.apache.james.mime4j.codec;

public class DecodeMonitor {
    public static final DecodeMonitor SILENT = new DecodeMonitor();
    public static final DecodeMonitor STRICT = new DecodeMonitor() {
        public boolean isListening() {
            return true;
        }

        public boolean warn(String str, String str2) {
            return true;
        }
    };

    public boolean isListening() {
        return false;
    }

    public boolean warn(String str, String str2) {
        return false;
    }
}

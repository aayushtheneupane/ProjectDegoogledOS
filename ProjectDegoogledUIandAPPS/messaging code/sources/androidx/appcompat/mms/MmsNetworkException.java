package androidx.appcompat.mms;

class MmsNetworkException extends Exception {
    public MmsNetworkException() {
    }

    public MmsNetworkException(String str) {
        super(str);
    }

    public MmsNetworkException(Throwable th) {
        super(th);
    }

    public MmsNetworkException(String str, Throwable th) {
        super(str, th);
    }
}

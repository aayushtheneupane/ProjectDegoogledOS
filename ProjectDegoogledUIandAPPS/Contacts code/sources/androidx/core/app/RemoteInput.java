package androidx.core.app;

public final class RemoteInput {
    public String getResultKey() {
        throw null;
    }

    static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        if (remoteInputArr.length <= 0) {
            return remoteInputArr2;
        }
        fromCompat(remoteInputArr[0]);
        throw null;
    }

    static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        remoteInput.getResultKey();
        throw null;
    }
}

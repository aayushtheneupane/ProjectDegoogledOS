package p000;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.InputStream;

/* renamed from: arz */
/* compiled from: PG */
public final class arz extends aru {

    /* renamed from: a */
    private static final UriMatcher f1504a;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f1504a = uriMatcher;
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        f1504a.addURI("com.android.contacts", "contacts/lookup/*", 1);
        f1504a.addURI("com.android.contacts", "contacts/#/photo", 2);
        f1504a.addURI("com.android.contacts", "contacts/#", 3);
        f1504a.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        f1504a.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    public arz(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1512a(Object obj) {
        ((InputStream) obj).close();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object mo1511a(android.net.Uri r3, android.content.ContentResolver r4) {
        /*
            r2 = this;
            android.content.UriMatcher r0 = f1504a
            int r0 = r0.match(r3)
            r1 = 1
            if (r0 == r1) goto L_0x0019
            r1 = 3
            if (r0 == r1) goto L_0x0014
            r1 = 5
            if (r0 == r1) goto L_0x0019
            java.io.InputStream r4 = r4.openInputStream(r3)
            goto L_0x0023
        L_0x0014:
            java.io.InputStream r4 = m1542a((android.content.ContentResolver) r4, (android.net.Uri) r3)
            goto L_0x0023
        L_0x0019:
            android.net.Uri r0 = android.provider.ContactsContract.Contacts.lookupContact(r4, r3)
            if (r0 == 0) goto L_0x004b
            java.io.InputStream r4 = m1542a((android.content.ContentResolver) r4, (android.net.Uri) r0)
        L_0x0023:
            if (r4 == 0) goto L_0x0026
            return r4
        L_0x0026:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r0 = r0.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r0 = r0 + 24
            r1.<init>(r0)
            java.lang.String r0 = "InputStream is null for "
            r1.append(r0)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r4.<init>(r3)
            throw r4
        L_0x004b:
            java.io.FileNotFoundException r3 = new java.io.FileNotFoundException
            java.lang.String r4 = "Contact cannot be found"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.arz.mo1511a(android.net.Uri, android.content.ContentResolver):java.lang.Object");
    }

    /* renamed from: a */
    private static final InputStream m1542a(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }
}

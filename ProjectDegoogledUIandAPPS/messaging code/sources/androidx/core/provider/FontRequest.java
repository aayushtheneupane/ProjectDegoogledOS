package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

public final class FontRequest {
    private final List mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(String str, String str2, String str3, List list) {
        if (str != null) {
            this.mProviderAuthority = str;
            if (str2 != null) {
                this.mProviderPackage = str2;
                if (str3 != null) {
                    this.mQuery = str3;
                    if (list != null) {
                        this.mCertificates = list;
                        this.mCertificatesArray = 0;
                        this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
                        return;
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public List getCertificates() {
        return this.mCertificates;
    }

    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder Pa = C0632a.m1011Pa("FontRequest {mProviderAuthority: ");
        Pa.append(this.mProviderAuthority);
        Pa.append(", mProviderPackage: ");
        Pa.append(this.mProviderPackage);
        Pa.append(", mQuery: ");
        Pa.append(this.mQuery);
        Pa.append(", mCertificates:");
        sb.append(Pa.toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            sb.append(" [");
            List list = (List) this.mCertificates.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.mCertificatesArray);
        return sb.toString();
    }

    public FontRequest(String str, String str2, String str3, int i) {
        if (str != null) {
            this.mProviderAuthority = str;
            if (str2 != null) {
                this.mProviderPackage = str2;
                if (str3 != null) {
                    this.mQuery = str3;
                    this.mCertificates = null;
                    Preconditions.checkArgument(i != 0);
                    this.mCertificatesArray = i;
                    this.mIdentifier = this.mProviderAuthority + "-" + this.mProviderPackage + "-" + this.mQuery;
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }
}

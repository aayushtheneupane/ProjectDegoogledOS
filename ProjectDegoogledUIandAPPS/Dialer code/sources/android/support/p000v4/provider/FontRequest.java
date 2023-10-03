package android.support.p000v4.provider;

import android.util.Base64;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

/* renamed from: android.support.v4.provider.FontRequest */
public final class FontRequest {
    private final List<List<byte[]>> mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(String str, String str2, String str3, List<List<byte[]>> list) {
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

    public List<List<byte[]>> getCertificates() {
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
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FontRequest {mProviderAuthority: ");
        outline13.append(this.mProviderAuthority);
        outline13.append(", mProviderPackage: ");
        outline13.append(this.mProviderPackage);
        outline13.append(", mQuery: ");
        outline13.append(this.mQuery);
        outline13.append(", mCertificates:");
        sb.append(outline13.toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            sb.append(" [");
            List list = this.mCertificates.get(i);
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
}

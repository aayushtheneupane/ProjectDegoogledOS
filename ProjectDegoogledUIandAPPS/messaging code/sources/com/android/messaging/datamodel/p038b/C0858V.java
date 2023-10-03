package com.android.messaging.datamodel.p038b;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1478ua;
import com.android.vcard.VCardEntry;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.b.V */
public class C0858V {

    /* renamed from: aD */
    private final List f1111aD;
    /* access modifiers changed from: private */
    public final Uri mAvatarUri;

    /* renamed from: yv */
    private final String f1112yv;

    public C0858V(C0869i iVar, Uri uri) {
        String str;
        String str2;
        String str3;
        Resources resources = C0967f.get().getApplicationContext().getResources();
        ArrayList arrayList = new ArrayList();
        if (iVar.getPhoneList() != null) {
            for (VCardEntry.PhoneData phoneData : iVar.getPhoneList()) {
                Intent intent = new Intent("android.intent.action.DIAL");
                StringBuilder Pa = C0632a.m1011Pa("tel:");
                Pa.append(phoneData.getNumber());
                intent.setData(Uri.parse(Pa.toString()));
                arrayList.add(new C0857U(phoneData.getNumber(), ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, phoneData.getType(), phoneData.getLabel()).toString(), intent));
            }
        }
        if (iVar.getEmailList() != null) {
            for (VCardEntry.EmailData emailData : iVar.getEmailList()) {
                Intent intent2 = new Intent("android.intent.action.SENDTO");
                intent2.setData(Uri.parse("mailto:"));
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{emailData.getAddress()});
                arrayList.add(new C0857U(emailData.getAddress(), ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, emailData.getType(), emailData.getLabel()).toString(), intent2));
            }
        }
        if (iVar.getPostalList() != null) {
            for (VCardEntry.PostalData postalData : iVar.getPostalList()) {
                try {
                    str3 = resources.getStringArray(17235972)[postalData.getType() - 1];
                } catch (Resources.NotFoundException unused) {
                    str3 = resources.getStringArray(17235972)[2];
                } catch (Exception e) {
                    C1430e.m3622e("MessagingApp", "createContactItem postal Exception:" + e);
                    str3 = resources.getStringArray(17235972)[2];
                }
                Intent intent3 = new Intent("android.intent.action.VIEW");
                StringBuilder sb = new StringBuilder();
                String pobox = postalData.getPobox();
                if (!TextUtils.isEmpty(pobox)) {
                    sb.append(pobox);
                    sb.append(" ");
                }
                String extendedAddress = postalData.getExtendedAddress();
                if (!TextUtils.isEmpty(extendedAddress)) {
                    sb.append(extendedAddress);
                    sb.append(" ");
                }
                String street = postalData.getStreet();
                if (!TextUtils.isEmpty(street)) {
                    sb.append(street);
                    sb.append(" ");
                }
                String localty = postalData.getLocalty();
                if (!TextUtils.isEmpty(localty)) {
                    sb.append(localty);
                    sb.append(" ");
                }
                String region = postalData.getRegion();
                if (!TextUtils.isEmpty(region)) {
                    sb.append(region);
                    sb.append(" ");
                }
                String postalCode = postalData.getPostalCode();
                if (!TextUtils.isEmpty(postalCode)) {
                    sb.append(postalCode);
                    sb.append(" ");
                }
                String country = postalData.getCountry();
                if (!TextUtils.isEmpty(country)) {
                    sb.append(country);
                }
                String sb2 = sb.toString();
                try {
                    intent3.setData(Uri.parse("geo:0,0?q=" + URLEncoder.encode(sb2, "UTF-8")));
                } catch (UnsupportedEncodingException unused2) {
                    intent3 = null;
                }
                arrayList.add(new C0857U(sb2, str3, intent3));
            }
        }
        if (iVar.getImList() != null) {
            for (VCardEntry.ImData imData : iVar.getImList()) {
                try {
                    str2 = resources.getString(ContactsContract.CommonDataKinds.Im.getProtocolLabelResource(imData.getProtocol()));
                } catch (Resources.NotFoundException unused3) {
                    str2 = null;
                }
                arrayList.add(new C0857U(imData.getAddress(), str2, (Intent) null));
            }
        }
        if (iVar.getOrganizationList() != null) {
            for (VCardEntry.OrganizationData organizationData : iVar.getOrganizationList()) {
                try {
                    str = resources.getString(ContactsContract.CommonDataKinds.Organization.getTypeLabelResource(organizationData.getType()));
                } catch (Resources.NotFoundException unused4) {
                    str = resources.getStringArray(17235970)[1];
                } catch (Exception e2) {
                    C1430e.m3622e("MessagingApp", "createContactItem org Exception:" + e2);
                    str = resources.getStringArray(17235970)[1];
                }
                arrayList.add(new C0857U(organizationData.getOrganizationName(), str, (Intent) null));
            }
        }
        if (iVar.getWebsiteList() != null) {
            for (VCardEntry.WebsiteData websiteData : iVar.getWebsiteList()) {
                if (websiteData != null && TextUtils.isGraphic(websiteData.getWebsite())) {
                    String website = websiteData.getWebsite();
                    if (!website.startsWith("http://") && !website.startsWith("https://")) {
                        website = C0632a.m1025k("http://", website);
                    }
                    arrayList.add(new C0857U(websiteData.getWebsite(), (String) null, new Intent("android.intent.action.VIEW", Uri.parse(website))));
                }
            }
        }
        if (iVar.getBirthday() != null) {
            String birthday = iVar.getBirthday();
            if (TextUtils.isGraphic(birthday)) {
                arrayList.add(new C0857U(birthday, resources.getString(R.string.vcard_detail_birthday_label), (Intent) null));
            }
        }
        if (iVar.getNotes() != null) {
            for (VCardEntry.NoteData noteData : iVar.getNotes()) {
                new C0015b();
                if (TextUtils.isGraphic(noteData.getNote())) {
                    arrayList.add(new C0857U(noteData.getNote(), resources.getString(R.string.vcard_detail_notes_label), (Intent) null));
                }
            }
        }
        this.f1111aD = arrayList;
        String displayName = iVar.getDisplayName();
        if (displayName == null) {
            iVar.consolidateFields();
            displayName = iVar.getDisplayName();
        }
        this.f1112yv = displayName;
        this.mAvatarUri = uri;
    }

    /* renamed from: Xh */
    public C0909V mo6135Xh() {
        return new C0855S(this);
    }

    /* renamed from: Yh */
    public List mo6136Yh() {
        return this.f1111aD;
    }

    /* access modifiers changed from: package-private */
    public void close() {
        if (MediaScratchFileProvider.m1258d(this.mAvatarUri)) {
            C1478ua.m3823a((Runnable) new C0854Q(this));
        }
    }

    public String getDisplayName() {
        return this.f1112yv;
    }

    /* renamed from: rf */
    public Uri mo6139rf() {
        return this.mAvatarUri;
    }
}

package com.android.contacts.common.model.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.android.contacts.common.model.account.AccountType;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public class ExternalAccountType extends BaseAccountType {
    private static final String[] METADATA_CONTACTS_NAMES = {"android.provider.ALTERNATE_CONTACTS_STRUCTURE", "android.provider.CONTACTS_STRUCTURE"};
    private String mAccountTypeIconAttribute;
    private String mAccountTypeLabelAttribute;
    private List<String> mExtensionPackageNames;
    private boolean mHasContactsMetadata;
    private boolean mHasEditSchema;
    private String mInviteActionLabelAttribute;
    private String mInviteContactActivity;
    private String mViewContactNotifyService;
    private String mViewGroupLabelAttribute;

    public ExternalAccountType(Context context, String str, boolean z) {
        this.resourcePackageName = str;
        this.syncAdapterPackageName = str;
        XmlResourceParser loadContactsXml = loadContactsXml(context, str);
        boolean z2 = true;
        if (loadContactsXml != null) {
            try {
                inflate(context, loadContactsXml);
            } catch (AccountType.DefinitionException e) {
                e = e;
            }
        }
        try {
            if (this.mHasEditSchema) {
                checkKindExists("vnd.android.cursor.item/name");
                checkKindExists("#displayName");
                checkKindExists("#phoneticName");
                checkKindExists("vnd.android.cursor.item/photo");
            } else {
                addDataKindStructuredName(context);
                addDataKindDisplayName(context);
                addDataKindPhoneticName(context);
                addDataKindPhoto(context);
            }
            if (loadContactsXml != null) {
                loadContactsXml.close();
            }
            this.mExtensionPackageNames = new ArrayList();
            resolveExternalResId(context, this.mInviteActionLabelAttribute, this.syncAdapterPackageName, "inviteContactActionLabel");
            resolveExternalResId(context, this.mViewGroupLabelAttribute, this.syncAdapterPackageName, "viewGroupActionLabel");
            resolveExternalResId(context, this.mAccountTypeLabelAttribute, this.syncAdapterPackageName, "accountTypeLabel");
            resolveExternalResId(context, this.mAccountTypeIconAttribute, this.syncAdapterPackageName, "accountTypeIcon");
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e2) {
            e = e2;
            z2 = false;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Problem reading XML");
                if (z2 && loadContactsXml != null) {
                    sb.append(" in line ");
                    sb.append(loadContactsXml.getLineNumber());
                }
                sb.append(" for external package ");
                sb.append(str);
                LogUtil.m7e("ExternalAccountType", sb.toString(), (Throwable) e);
            } finally {
                if (loadContactsXml != null) {
                    loadContactsXml.close();
                }
            }
        }
    }

    private void checkKindExists(String str) throws AccountType.DefinitionException {
        if (getKindForMimetype(str) == null) {
            throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline8(str, " must be supported"));
        }
    }

    public static XmlResourceParser loadContactsXml(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent("android.content.SyncAdapter").setPackage(str), 132);
        if (queryIntentServices == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null) {
                for (String str2 : METADATA_CONTACTS_NAMES) {
                    XmlResourceParser loadXmlMetaData = serviceInfo.loadXmlMetaData(packageManager, str2);
                    if (loadXmlMetaData != null) {
                        String.format("Metadata loaded from: %s, %s, %s", new Object[]{serviceInfo.packageName, serviceInfo.name, str2});
                        return loadXmlMetaData;
                    }
                }
                continue;
            }
        }
        return null;
    }

    static int resolveExternalResId(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.charAt(0) != '@') {
            LogUtil.m8e("ExternalAccountType", GeneratedOutlineSupport.outline8(str3, " must be a resource name beginnig with '@'"), new Object[0]);
            return -1;
        }
        try {
            int identifier = context.getPackageManager().getResourcesForApplication(str2).getIdentifier(str.substring(1), (String) null, str2);
            if (identifier != 0) {
                return identifier;
            }
            LogUtil.m8e("ExternalAccountType", "Unable to load " + str + " from package " + str2, new Object[0]);
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.m8e("ExternalAccountType", GeneratedOutlineSupport.outline8("Unable to load package ", str2), new Object[0]);
            return -1;
        }
    }

    public boolean areContactsWritable() {
        return this.mHasEditSchema;
    }

    public List<String> getExtensionPackageNames() {
        return this.mExtensionPackageNames;
    }

    public String getInviteContactActivityClassName() {
        return this.mInviteContactActivity;
    }

    public String getViewContactNotifyServiceClassName() {
        return this.mViewContactNotifyService;
    }

    public boolean hasContactsMetadata() {
        return this.mHasContactsMetadata;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0171 A[Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0013 A[Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inflate(android.content.Context r12, org.xmlpull.v1.XmlPullParser r13) throws com.android.contacts.common.model.account.AccountType.DefinitionException {
        /*
            r11 = this;
            java.lang.String r0 = "Problem reading XML"
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r13)
        L_0x0006:
            int r2 = r13.next()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r3 = 2
            r4 = 1
            if (r2 == r3) goto L_0x0011
            if (r2 == r4) goto L_0x0011
            goto L_0x0006
        L_0x0011:
            if (r2 != r3) goto L_0x0171
            java.lang.String r2 = r13.getName()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r5 = "ContactsAccountType"
            boolean r5 = r5.equals(r2)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r5 != 0) goto L_0x003f
            java.lang.String r5 = "ContactsSource"
            boolean r5 = r5.equals(r2)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r5 == 0) goto L_0x0028
            goto L_0x003f
        L_0x0028:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r12.<init>()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r13 = "Top level element must be ContactsAccountType, not "
            r12.append(r13)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r12.append(r2)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r12 = r12.toString()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r11.<init>(r12)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            throw r11     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x003f:
            r11.mHasContactsMetadata = r4     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            int r2 = r13.getAttributeCount()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r5 = 0
            r6 = r5
        L_0x0047:
            if (r6 >= r2) goto L_0x0105
            java.lang.String r7 = r13.getAttributeName(r6)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r8 = r13.getAttributeValue(r6)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.<init>()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.append(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r10 = "="
            r9.append(r10)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.append(r8)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.toString()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r9 = "editContactActivity"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x006e
            goto L_0x0101
        L_0x006e:
            java.lang.String r9 = "createContactActivity"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x0078
            goto L_0x0101
        L_0x0078:
            java.lang.String r9 = "inviteContactActivity"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x0084
            r11.mInviteContactActivity = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x0084:
            java.lang.String r9 = "inviteContactActionLabel"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x0090
            r11.mInviteActionLabelAttribute = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x0090:
            java.lang.String r9 = "viewContactNotifyService"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x009b
            r11.mViewContactNotifyService = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x009b:
            java.lang.String r9 = "viewGroupActivity"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00a4
            goto L_0x0101
        L_0x00a4:
            java.lang.String r9 = "viewGroupActionLabel"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00af
            r11.mViewGroupLabelAttribute = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00af:
            java.lang.String r9 = "dataSet"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00ba
            r11.dataSet = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00ba:
            java.lang.String r9 = "extensionPackageNames"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00c8
            java.util.List<java.lang.String> r7 = r11.mExtensionPackageNames     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r7.add(r8)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00c8:
            java.lang.String r9 = "accountType"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00d3
            r11.accountType = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00d3:
            java.lang.String r9 = "accountTypeLabel"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00de
            r11.mAccountTypeLabelAttribute = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00de:
            java.lang.String r9 = "accountTypeIcon"
            boolean r9 = r9.equals(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r9 == 0) goto L_0x00e9
            r11.mAccountTypeIconAttribute = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0101
        L_0x00e9:
            java.lang.String r8 = "ExternalAccountType"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.<init>()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r10 = "Unsupported attribute "
            r9.append(r10)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9.append(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r7 = r9.toString()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r8, (java.lang.String) r7, (java.lang.Object[]) r9)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x0101:
            int r6 = r6 + 1
            goto L_0x0047
        L_0x0105:
            int r2 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x0109:
            int r5 = r13.next()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r6 = 3
            if (r5 != r6) goto L_0x0116
            int r7 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r7 <= r2) goto L_0x0170
        L_0x0116:
            if (r5 == r4) goto L_0x0170
            if (r5 != r3) goto L_0x0109
            int r5 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            int r7 = r2 + 1
            if (r5 == r7) goto L_0x0123
            goto L_0x0109
        L_0x0123:
            java.lang.String r5 = r13.getName()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r7 = "EditSchema"
            boolean r7 = r7.equals(r5)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r7 == 0) goto L_0x0135
            r11.mHasEditSchema = r4     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r11.parseEditSchema(r12, r13, r1)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0109
        L_0x0135:
            java.lang.String r7 = "ContactsDataKind"
            boolean r5 = r7.equals(r5)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r5 == 0) goto L_0x0109
            int[] r5 = com.android.dialer.contacts.resources.R$styleable.ContactsDataKind     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            android.content.res.TypedArray r5 = r12.obtainStyledAttributes(r1, r5)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            com.android.contacts.common.model.dataitem.DataKind r7 = new com.android.contacts.common.model.dataitem.DataKind     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r8 = r5.getString(r4)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r7.mimeType = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r8 = r5.getString(r3)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r9 = -1
            if (r8 == 0) goto L_0x015c
            com.android.contacts.common.model.account.BaseAccountType$SimpleInflater r10 = new com.android.contacts.common.model.account.BaseAccountType$SimpleInflater     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r10.<init>(r9, r8)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r7.actionHeader = r10     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x015c:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            if (r6 == 0) goto L_0x0169
            com.android.contacts.common.model.account.BaseAccountType$SimpleInflater r8 = new com.android.contacts.common.model.account.BaseAccountType$SimpleInflater     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r8.<init>(r9, r6)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r7.actionBody = r8     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x0169:
            r5.recycle()     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            r11.addKind(r7)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            goto L_0x0109
        L_0x0170:
            return
        L_0x0171:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            java.lang.String r12 = "No start tag found"
            r11.<init>(r12)     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
            throw r11     // Catch:{ XmlPullParserException -> 0x0180, IOException -> 0x0179 }
        L_0x0179:
            r11 = move-exception
            com.android.contacts.common.model.account.AccountType$DefinitionException r12 = new com.android.contacts.common.model.account.AccountType$DefinitionException
            r12.<init>(r0, r11)
            throw r12
        L_0x0180:
            r11 = move-exception
            com.android.contacts.common.model.account.AccountType$DefinitionException r12 = new com.android.contacts.common.model.account.AccountType$DefinitionException
            r12.<init>(r0, r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.common.model.account.ExternalAccountType.inflate(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public boolean isEmbedded() {
        return false;
    }
}

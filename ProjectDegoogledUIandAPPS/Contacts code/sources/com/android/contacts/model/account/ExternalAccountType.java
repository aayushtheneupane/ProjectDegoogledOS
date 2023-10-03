package com.android.contacts.model.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contactsbind.FeedbackHelper;
import java.util.ArrayList;
import java.util.List;

public class ExternalAccountType extends BaseAccountType {
    private static final String ATTR_ACCOUNT_ICON = "accountTypeIcon";
    private static final String ATTR_ACCOUNT_LABEL = "accountTypeLabel";
    private static final String ATTR_ACCOUNT_TYPE = "accountType";
    private static final String ATTR_DATA_SET = "dataSet";
    private static final String ATTR_EXTENSION_PACKAGE_NAMES = "extensionPackageNames";
    private static final String ATTR_INVITE_CONTACT_ACTION_LABEL = "inviteContactActionLabel";
    private static final String ATTR_INVITE_CONTACT_ACTIVITY = "inviteContactActivity";
    private static final String ATTR_VIEW_CONTACT_NOTIFY_SERVICE = "viewContactNotifyService";
    private static final String ATTR_VIEW_GROUP_ACTION_LABEL = "viewGroupActionLabel";
    private static final String ATTR_VIEW_GROUP_ACTIVITY = "viewGroupActivity";
    private static final String[] METADATA_CONTACTS_NAMES = {"android.provider.ALTERNATE_CONTACTS_STRUCTURE", "android.provider.CONTACTS_STRUCTURE"};
    private static final String SYNC_META_DATA = "android.content.SyncAdapter";
    private static final String TAG = "ExternalAccountType";
    private static final String TAG_CONTACTS_ACCOUNT_TYPE = "ContactsAccountType";
    private static final String TAG_CONTACTS_DATA_KIND = "ContactsDataKind";
    private static final String TAG_CONTACTS_SOURCE_LEGACY = "ContactsSource";
    private static final String TAG_EDIT_SCHEMA = "EditSchema";
    private String mAccountTypeIconAttribute;
    private String mAccountTypeLabelAttribute;
    private List<String> mExtensionPackageNames;
    private boolean mGroupMembershipEditable;
    private boolean mHasContactsMetadata;
    private boolean mHasEditSchema;
    private String mInviteActionLabelAttribute;
    private int mInviteActionLabelResId;
    private String mInviteContactActivity;
    private final boolean mIsExtension;
    private String mViewContactNotifyService;
    private String mViewGroupActivity;
    private String mViewGroupLabelAttribute;
    private int mViewGroupLabelResId;

    public boolean isEmbedded() {
        return false;
    }

    public ExternalAccountType(Context context, String str, boolean z) {
        this(context, str, z, (XmlResourceParser) null);
    }

    ExternalAccountType(Context context, String str, boolean z, XmlResourceParser xmlResourceParser) {
        this.mIsExtension = z;
        this.resourcePackageName = str;
        this.syncAdapterPackageName = str;
        XmlResourceParser loadContactsXml = xmlResourceParser == null ? loadContactsXml(context, str) : xmlResourceParser;
        boolean z2 = false;
        if (loadContactsXml != null) {
            try {
                inflate(context, loadContactsXml);
            } catch (AccountType.DefinitionException e) {
                e = e;
                z2 = true;
            }
        }
        try {
            if (this.mHasEditSchema) {
                checkKindExists("vnd.android.cursor.item/name");
                checkKindExists(DataKind.PSEUDO_MIME_TYPE_NAME);
                checkKindExists(DataKind.PSEUDO_MIME_TYPE_PHONETIC_NAME);
                checkKindExists("vnd.android.cursor.item/photo");
            } else {
                addDataKindStructuredName(context);
                addDataKindName(context);
                addDataKindPhoneticName(context);
                addDataKindPhoto(context);
            }
            if (loadContactsXml != null) {
                loadContactsXml.close();
            }
            this.mExtensionPackageNames = new ArrayList();
            this.mInviteActionLabelResId = resolveExternalResId(context, this.mInviteActionLabelAttribute, this.syncAdapterPackageName, ATTR_INVITE_CONTACT_ACTION_LABEL);
            this.mViewGroupLabelResId = resolveExternalResId(context, this.mViewGroupLabelAttribute, this.syncAdapterPackageName, ATTR_VIEW_GROUP_ACTION_LABEL);
            this.titleRes = resolveExternalResId(context, this.mAccountTypeLabelAttribute, this.syncAdapterPackageName, ATTR_ACCOUNT_LABEL);
            this.iconRes = resolveExternalResId(context, this.mAccountTypeIconAttribute, this.syncAdapterPackageName, ATTR_ACCOUNT_ICON);
            DataKind kindForMimetype = getKindForMimetype("vnd.android.cursor.item/group_membership");
            if (kindForMimetype != null && kindForMimetype.editable) {
                z2 = true;
            }
            this.mGroupMembershipEditable = z2;
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e2) {
            e = e2;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Problem reading XML");
                if (z2 && loadContactsXml != null) {
                    sb.append(" in line ");
                    sb.append(loadContactsXml.getLineNumber());
                }
                sb.append(" for external package ");
                sb.append(str);
                if (xmlResourceParser == null) {
                    FeedbackHelper.sendFeedback(context, TAG, "Failed to build external account type", e);
                }
            } finally {
                if (loadContactsXml != null) {
                    loadContactsXml.close();
                }
            }
        }
    }

    public static XmlResourceParser loadContactsXml(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent(SYNC_META_DATA).setPackage(str), 132);
        if (queryIntentServices == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null) {
                for (String str2 : METADATA_CONTACTS_NAMES) {
                    XmlResourceParser loadXmlMetaData = serviceInfo.loadXmlMetaData(packageManager, str2);
                    if (loadXmlMetaData != null) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, String.format("Metadata loaded from: %s, %s, %s", new Object[]{serviceInfo.packageName, serviceInfo.name, str2}));
                        }
                        return loadXmlMetaData;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static boolean hasContactsXml(Context context, String str) {
        return loadContactsXml(context, str) != null;
    }

    private void checkKindExists(String str) throws AccountType.DefinitionException {
        if (getKindForMimetype(str) == null) {
            throw new AccountType.DefinitionException(str + " must be supported");
        }
    }

    public boolean isExtension() {
        return this.mIsExtension;
    }

    public boolean areContactsWritable() {
        return this.mHasEditSchema;
    }

    public boolean hasContactsMetadata() {
        return this.mHasContactsMetadata;
    }

    public String getInviteContactActivityClassName() {
        return this.mInviteContactActivity;
    }

    /* access modifiers changed from: protected */
    public int getInviteContactActionResId() {
        return this.mInviteActionLabelResId;
    }

    public String getViewContactNotifyServiceClassName() {
        return this.mViewContactNotifyService;
    }

    public String getViewGroupActivity() {
        return this.mViewGroupActivity;
    }

    /* access modifiers changed from: protected */
    public int getViewGroupLabelResId() {
        return this.mViewGroupLabelResId;
    }

    public List<String> getExtensionPackageNames() {
        return this.mExtensionPackageNames;
    }

    public boolean isGroupMembershipEditable() {
        return this.mGroupMembershipEditable;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016d A[Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015 A[Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inflate(android.content.Context r12, org.xmlpull.v1.XmlPullParser r13) throws com.android.contacts.model.account.AccountType.DefinitionException {
        /*
            r11 = this;
            java.lang.String r0 = "Problem reading XML"
            java.lang.String r1 = "ExternalAccountType"
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r13)
        L_0x0008:
            int r3 = r13.next()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r4 = 2
            r5 = 1
            if (r3 == r4) goto L_0x0013
            if (r3 == r5) goto L_0x0013
            goto L_0x0008
        L_0x0013:
            if (r3 != r4) goto L_0x016d
            java.lang.String r3 = r13.getName()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r6 = "ContactsAccountType"
            boolean r6 = r6.equals(r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r6 != 0) goto L_0x0041
            java.lang.String r6 = "ContactsSource"
            boolean r6 = r6.equals(r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r6 == 0) goto L_0x002a
            goto L_0x0041
        L_0x002a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r13.<init>()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r1 = "Top level element must be ContactsAccountType, not "
            r13.append(r1)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r13.append(r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r13 = r13.toString()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r12.<init>(r13)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            throw r12     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0041:
            r11.mHasContactsMetadata = r5     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            int r3 = r13.getAttributeCount()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r6 = 0
        L_0x0048:
            r7 = 3
            if (r6 >= r3) goto L_0x0103
            java.lang.String r8 = r13.getAttributeName(r6)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r9 = r13.getAttributeValue(r6)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            boolean r7 = android.util.Log.isLoggable(r1, r7)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x0070
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.append(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r10 = "="
            r7.append(r10)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.append(r9)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r7 = r7.toString()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            android.util.Log.d(r1, r7)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0070:
            java.lang.String r7 = "inviteContactActivity"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x007c
            r11.mInviteContactActivity = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x007c:
            java.lang.String r7 = "inviteContactActionLabel"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x0088
            r11.mInviteActionLabelAttribute = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x0088:
            java.lang.String r7 = "viewContactNotifyService"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x0094
            r11.mViewContactNotifyService = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x0094:
            java.lang.String r7 = "viewGroupActivity"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x009f
            r11.mViewGroupActivity = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x009f:
            java.lang.String r7 = "viewGroupActionLabel"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00aa
            r11.mViewGroupLabelAttribute = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00aa:
            java.lang.String r7 = "dataSet"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00b5
            r11.dataSet = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00b5:
            java.lang.String r7 = "extensionPackageNames"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00c3
            java.util.List<java.lang.String> r7 = r11.mExtensionPackageNames     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.add(r9)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00c3:
            java.lang.String r7 = "accountType"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00ce
            r11.accountType = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00ce:
            java.lang.String r7 = "accountTypeLabel"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00d9
            r11.mAccountTypeLabelAttribute = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00d9:
            java.lang.String r7 = "accountTypeIcon"
            boolean r7 = r7.equals(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00e4
            r11.mAccountTypeIconAttribute = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x00ff
        L_0x00e4:
            r7 = 5
            boolean r7 = android.util.Log.isLoggable(r1, r7)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r7 == 0) goto L_0x00ff
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r9 = "Unsupported attribute "
            r7.append(r9)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r7.append(r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r7 = r7.toString()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            android.util.Log.w(r1, r7)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x00ff:
            int r6 = r6 + 1
            goto L_0x0048
        L_0x0103:
            int r1 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0107:
            int r3 = r13.next()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r3 != r7) goto L_0x0113
            int r6 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r6 <= r1) goto L_0x016c
        L_0x0113:
            if (r3 == r5) goto L_0x016c
            if (r3 != r4) goto L_0x0107
            int r3 = r13.getDepth()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            int r6 = r1 + 1
            if (r3 == r6) goto L_0x0120
            goto L_0x0107
        L_0x0120:
            java.lang.String r3 = r13.getName()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r6 = "EditSchema"
            boolean r6 = r6.equals(r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r6 == 0) goto L_0x0132
            r11.mHasEditSchema = r5     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r11.parseEditSchema(r12, r13, r2)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x0107
        L_0x0132:
            java.lang.String r6 = "ContactsDataKind"
            boolean r3 = r6.equals(r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r3 == 0) goto L_0x0107
            int[] r3 = com.android.contacts.R$styleable.ContactsDataKind     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            android.content.res.TypedArray r3 = r12.obtainStyledAttributes(r2, r3)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            com.android.contacts.model.dataitem.DataKind r6 = new com.android.contacts.model.dataitem.DataKind     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r8 = r3.getString(r5)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r6.mimeType = r8     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r8 = r3.getString(r4)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r8 == 0) goto L_0x0158
            com.android.contacts.model.account.BaseAccountType$SimpleInflater r9 = new com.android.contacts.model.account.BaseAccountType$SimpleInflater     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r9.<init>((java.lang.String) r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r6.actionHeader = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0158:
            java.lang.String r8 = r3.getString(r7)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            if (r8 == 0) goto L_0x0165
            com.android.contacts.model.account.BaseAccountType$SimpleInflater r9 = new com.android.contacts.model.account.BaseAccountType$SimpleInflater     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r9.<init>((java.lang.String) r8)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r6.actionBody = r9     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0165:
            r3.recycle()     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            r11.addKind(r6)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            goto L_0x0107
        L_0x016c:
            return
        L_0x016d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            java.lang.String r13 = "No start tag found"
            r12.<init>(r13)     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
            throw r12     // Catch:{ XmlPullParserException -> 0x017c, IOException -> 0x0175 }
        L_0x0175:
            r12 = move-exception
            com.android.contacts.model.account.AccountType$DefinitionException r13 = new com.android.contacts.model.account.AccountType$DefinitionException
            r13.<init>(r0, r12)
            throw r13
        L_0x017c:
            r12 = move-exception
            com.android.contacts.model.account.AccountType$DefinitionException r13 = new com.android.contacts.model.account.AccountType$DefinitionException
            r13.<init>(r0, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.model.account.ExternalAccountType.inflate(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    static int resolveExternalResId(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.charAt(0) != '@') {
            if (Log.isLoggable(TAG, 5) && !isFromTestApp(str2)) {
                Log.w(TAG, str3 + " must be a resource name beginnig with '@'");
            }
            return -1;
        }
        try {
            int identifier = context.getPackageManager().getResourcesForApplication(str2).getIdentifier(str.substring(1), (String) null, str2);
            if (identifier != 0) {
                return identifier;
            }
            if (Log.isLoggable(TAG, 5) && !isFromTestApp(str2)) {
                Log.w(TAG, "Unable to load " + str + " from package " + str2);
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            if (Log.isLoggable(TAG, 5) && !isFromTestApp(str2)) {
                Log.w(TAG, "Unable to load package " + str2);
            }
            return -1;
        }
    }

    static boolean isFromTestApp(String str) {
        return TextUtils.equals(str, "com.google.android.contacts.tests");
    }
}

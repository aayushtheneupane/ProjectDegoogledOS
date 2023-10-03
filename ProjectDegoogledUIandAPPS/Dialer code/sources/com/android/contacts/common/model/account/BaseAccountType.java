package com.android.contacts.common.model.account;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import android.util.ArrayMap;
import android.util.AttributeSet;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.dataitem.DataKind;
import com.android.contacts.common.util.CommonDateUtils;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class BaseAccountType extends AccountType {
    public static final AccountType.StringInflater ORGANIZATION_BODY_INFLATER = new AccountType.StringInflater() {
        public CharSequence inflateUsing(Context context, ContentValues contentValues) {
            String str = null;
            String asString = contentValues.containsKey("data1") ? contentValues.getAsString("data1") : null;
            if (contentValues.containsKey("data4")) {
                str = contentValues.getAsString("data4");
            }
            if (asString == null || str == null) {
                return asString == null ? str : asString;
            }
            return asString + ": " + str;
        }
    };

    public static abstract class CommonInflater implements AccountType.StringInflater {
        /* access modifiers changed from: protected */
        public String getLabelColumn() {
            return "data3";
        }

        /* access modifiers changed from: protected */
        public String getTypeColumn() {
            return "data2";
        }

        /* access modifiers changed from: protected */
        public abstract int getTypeLabelResource(Integer num);

        public CharSequence inflateUsing(Context context, ContentValues contentValues) {
            Integer asInteger = contentValues.getAsInteger(getTypeColumn());
            String asString = contentValues.getAsString(getLabelColumn());
            Resources resources = context.getResources();
            int typeLabelResource = getTypeLabelResource(asInteger);
            if (asInteger == null) {
                return resources.getText(typeLabelResource);
            }
            if (!isCustom(asInteger)) {
                return resources.getText(typeLabelResource);
            }
            Object[] objArr = new Object[1];
            if (asString == null) {
                asString = "";
            }
            objArr[0] = asString;
            return resources.getString(typeLabelResource, objArr);
        }

        /* access modifiers changed from: protected */
        public boolean isCustom(Integer num) {
            return num.intValue() == 0;
        }

        public String toString() {
            return getClass().getSimpleName();
        }
    }

    public static class EmailActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            if (num == null) {
                return R.string.email;
            }
            int intValue = num.intValue();
            if (intValue == 1) {
                return R.string.email_home;
            }
            if (intValue == 2) {
                return R.string.email_work;
            }
            if (intValue != 3) {
                return intValue != 4 ? R.string.email_custom : R.string.email_mobile;
            }
            return R.string.email_other;
        }
    }

    private static class EmailKindBuilder extends KindBuilder {
        /* synthetic */ EmailKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            if ("home".equals(str)) {
                return BaseAccountType.buildEmailType(1);
            }
            if ("work".equals(str)) {
                return BaseAccountType.buildEmailType(2);
            }
            if ("other".equals(str)) {
                return BaseAccountType.buildEmailType(3);
            }
            if ("mobile".equals(str)) {
                return BaseAccountType.buildEmailType(4);
            }
            if (!"custom".equals(str)) {
                return null;
            }
            AccountType.EditType buildEmailType = BaseAccountType.buildEmailType(0);
            buildEmailType.secondary = true;
            buildEmailType.customColumn = "data3";
            return buildEmailType;
        }

        public String getTagName() {
            return "email";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/email_v2", "data2", R.string.emailLabelsGroup, 15, new EmailActionInflater(), new SimpleInflater(-1, "data1"));
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.emailLabelsGroup, 33));
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public static class EventActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            return ContactsContract.CommonDataKinds.Event.getTypeResource(num);
        }
    }

    private static class EventKindBuilder extends KindBuilder {
        /* synthetic */ EventKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            boolean access$1700 = attributeSet.getAttributeBooleanValue((String) null, "yearOptional", false);
            if ("birthday".equals(str)) {
                AccountType.EditType buildEventType = BaseAccountType.buildEventType(3, access$1700);
                buildEventType.specificMax = 1;
                return buildEventType;
            } else if ("anniversary".equals(str)) {
                return BaseAccountType.buildEventType(1, access$1700);
            } else {
                if ("other".equals(str)) {
                    return BaseAccountType.buildEventType(2, access$1700);
                }
                if (!"custom".equals(str)) {
                    return null;
                }
                AccountType.EditType buildEventType2 = BaseAccountType.buildEventType(0, access$1700);
                buildEventType2.secondary = true;
                buildEventType2.customColumn = "data3";
                return buildEventType2;
            }
        }

        public String getTagName() {
            return "event";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/contact_event", "data2", R.string.eventLabelsGroup, 120, new EventActionInflater(), new SimpleInflater(-1, "data1"));
            GeneratedOutlineSupport.outline17("data1", R.string.eventLabelsGroup, 1, newDataKind.fieldList);
            if (attributeSet.getAttributeBooleanValue((String) null, "dateWithTime", false)) {
                newDataKind.dateFormatWithoutYear = CommonDateUtils.NO_YEAR_DATE_AND_TIME_FORMAT;
                newDataKind.dateFormatWithYear = CommonDateUtils.DATE_AND_TIME_FORMAT;
            } else {
                newDataKind.dateFormatWithoutYear = CommonDateUtils.NO_YEAR_DATE_FORMAT;
                newDataKind.dateFormatWithYear = CommonDateUtils.FULL_DATE_FORMAT;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class GroupMembershipKindBuilder extends KindBuilder {
        /* synthetic */ GroupMembershipKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "group_membership";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/group_membership", (String) null, R.string.groupsLabel, 150, (AccountType.StringInflater) null, (AccountType.StringInflater) null);
            GeneratedOutlineSupport.outline17("data1", -1, -1, newDataKind.fieldList);
            newDataKind.maxLinesForDisplay = 10;
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public static class ImActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public String getLabelColumn() {
            return "data6";
        }

        /* access modifiers changed from: protected */
        public String getTypeColumn() {
            return "data5";
        }

        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            if (num == null) {
                return R.string.chat;
            }
            switch (num.intValue()) {
                case 0:
                    return R.string.chat_aim;
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                    return R.string.chat_msn;
                case 2:
                    return R.string.chat_yahoo;
                case 3:
                    return R.string.chat_skype;
                case 4:
                    return R.string.chat_qq;
                case 5:
                    return R.string.chat_gtalk;
                case 6:
                    return R.string.chat_icq;
                case 7:
                    return R.string.chat_jabber;
                default:
                    return R.string.chat;
            }
        }
    }

    private static class ImKindBuilder extends KindBuilder {
        /* synthetic */ ImKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            if ("aim".equals(str)) {
                return BaseAccountType.buildImType(0);
            }
            if ("msn".equals(str)) {
                return BaseAccountType.buildImType(1);
            }
            if ("yahoo".equals(str)) {
                return BaseAccountType.buildImType(2);
            }
            if ("skype".equals(str)) {
                return BaseAccountType.buildImType(3);
            }
            if ("qq".equals(str)) {
                return BaseAccountType.buildImType(4);
            }
            if ("google_talk".equals(str)) {
                return BaseAccountType.buildImType(5);
            }
            if ("icq".equals(str)) {
                return BaseAccountType.buildImType(6);
            }
            if ("jabber".equals(str)) {
                return BaseAccountType.buildImType(7);
            }
            if (!"custom".equals(str)) {
                return null;
            }
            AccountType.EditType buildImType = BaseAccountType.buildImType(-1);
            buildImType.secondary = true;
            buildImType.customColumn = "data6";
            return buildImType;
        }

        public String getTagName() {
            return "im";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/im", "data5", R.string.imLabelsGroup, 140, new ImActionInflater(), new SimpleInflater(-1, "data1"));
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.imLabelsGroup, 33));
            newDataKind.defaultValues = new ContentValues();
            newDataKind.defaultValues.put("data2", 3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static abstract class KindBuilder {
        private KindBuilder() {
        }

        private void parseTypes(XmlPullParser xmlPullParser, AttributeSet attributeSet, DataKind dataKind, boolean z) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if (next == 1) {
                    return;
                }
                if (next != 3 || xmlPullParser.getDepth() > depth) {
                    int depth2 = xmlPullParser.getDepth();
                    if (next == 2 && depth2 == depth + 1) {
                        String name = xmlPullParser.getName();
                        if (!"Type".equals(name)) {
                            throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline8("Unknown tag: ", name));
                        } else if (z) {
                            List<AccountType.EditType> list = dataKind.typeList;
                            String access$1400 = attributeSet.getAttributeValue((String) null, "type");
                            AccountType.EditType buildEditTypeForTypeTag = buildEditTypeForTypeTag(attributeSet, access$1400);
                            if (buildEditTypeForTypeTag != null) {
                                buildEditTypeForTypeTag.specificMax = attributeSet.getAttributeIntValue((String) null, "maxOccurs", -1);
                                list.add(buildEditTypeForTypeTag);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Undefined type '");
                                sb.append(access$1400);
                                sb.append("' for data kind '");
                                throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline12(sb, dataKind.mimeType, "'"));
                            }
                        } else {
                            throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("Kind "), dataKind.mimeType, " can't have types"));
                        }
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            return null;
        }

        public abstract String getTagName();

        /* access modifiers changed from: protected */
        public final DataKind newDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, boolean z, String str, String str2, int i, int i2, AccountType.StringInflater stringInflater, AccountType.StringInflater stringInflater2) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            "Adding DataKind: " + str;
            DataKind dataKind = new DataKind(str, i, i2, true);
            dataKind.typeColumn = str2;
            dataKind.actionHeader = stringInflater;
            dataKind.actionBody = stringInflater2;
            dataKind.fieldList = new ArrayList();
            if (!z) {
                dataKind.typeOverallMax = attributeSet.getAttributeIntValue((String) null, "maxOccurs", -1);
                if (dataKind.typeColumn != null) {
                    dataKind.typeList = new ArrayList();
                    parseTypes(xmlPullParser, attributeSet, dataKind, true);
                    if (dataKind.typeList.size() == 0) {
                        throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("Kind "), dataKind.mimeType, " must have at least one type"));
                    }
                } else {
                    parseTypes(xmlPullParser, attributeSet, dataKind, false);
                }
            }
            return dataKind;
        }

        public abstract List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException;

        /* access modifiers changed from: protected */
        public final void throwIfList(DataKind dataKind) throws AccountType.DefinitionException {
            if (dataKind.typeOverallMax != 1) {
                throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("Kind "), dataKind.mimeType, " must have 'overallMax=\"1\"'"));
            }
        }

        /* synthetic */ KindBuilder(C02671 r1) {
        }
    }

    private static class KindParser {
        public static final KindParser INSTANCE = new KindParser();
        private final Map<String, KindBuilder> mBuilders = new ArrayMap();

        private KindParser() {
            NameKindBuilder nameKindBuilder = new NameKindBuilder((C02671) null);
            this.mBuilders.put(nameKindBuilder.getTagName(), nameKindBuilder);
            NicknameKindBuilder nicknameKindBuilder = new NicknameKindBuilder((C02671) null);
            this.mBuilders.put(nicknameKindBuilder.getTagName(), nicknameKindBuilder);
            PhoneKindBuilder phoneKindBuilder = new PhoneKindBuilder((C02671) null);
            this.mBuilders.put(phoneKindBuilder.getTagName(), phoneKindBuilder);
            EmailKindBuilder emailKindBuilder = new EmailKindBuilder((C02671) null);
            this.mBuilders.put(emailKindBuilder.getTagName(), emailKindBuilder);
            StructuredPostalKindBuilder structuredPostalKindBuilder = new StructuredPostalKindBuilder((C02671) null);
            this.mBuilders.put(structuredPostalKindBuilder.getTagName(), structuredPostalKindBuilder);
            ImKindBuilder imKindBuilder = new ImKindBuilder((C02671) null);
            this.mBuilders.put(imKindBuilder.getTagName(), imKindBuilder);
            OrganizationKindBuilder organizationKindBuilder = new OrganizationKindBuilder((C02671) null);
            this.mBuilders.put(organizationKindBuilder.getTagName(), organizationKindBuilder);
            PhotoKindBuilder photoKindBuilder = new PhotoKindBuilder((C02671) null);
            this.mBuilders.put(photoKindBuilder.getTagName(), photoKindBuilder);
            NoteKindBuilder noteKindBuilder = new NoteKindBuilder((C02671) null);
            this.mBuilders.put(noteKindBuilder.getTagName(), noteKindBuilder);
            WebsiteKindBuilder websiteKindBuilder = new WebsiteKindBuilder((C02671) null);
            this.mBuilders.put(websiteKindBuilder.getTagName(), websiteKindBuilder);
            SipAddressKindBuilder sipAddressKindBuilder = new SipAddressKindBuilder((C02671) null);
            this.mBuilders.put(sipAddressKindBuilder.getTagName(), sipAddressKindBuilder);
            GroupMembershipKindBuilder groupMembershipKindBuilder = new GroupMembershipKindBuilder((C02671) null);
            this.mBuilders.put(groupMembershipKindBuilder.getTagName(), groupMembershipKindBuilder);
            EventKindBuilder eventKindBuilder = new EventKindBuilder((C02671) null);
            this.mBuilders.put(eventKindBuilder.getTagName(), eventKindBuilder);
            RelationshipKindBuilder relationshipKindBuilder = new RelationshipKindBuilder((C02671) null);
            this.mBuilders.put(relationshipKindBuilder.getTagName(), relationshipKindBuilder);
        }

        public List<DataKind> parseDataKindTag(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            String access$1400 = attributeSet.getAttributeValue((String) null, "kind");
            KindBuilder kindBuilder = this.mBuilders.get(access$1400);
            if (kindBuilder != null) {
                return kindBuilder.parseDataKind(context, xmlPullParser, attributeSet);
            }
            throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline9("Undefined data kind '", access$1400, "'"));
        }
    }

    private static class NameKindBuilder extends KindBuilder {
        /* synthetic */ NameKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        private static void checkAttributeTrue(boolean z, String str) throws AccountType.DefinitionException {
            if (!z) {
                throw new AccountType.DefinitionException(GeneratedOutlineSupport.outline8(str, " must be true"));
            }
        }

        public String getTagName() {
            return "name";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            AttributeSet attributeSet2 = attributeSet;
            boolean z = context.getResources().getBoolean(R.bool.config_editor_field_order_primary);
            boolean access$1700 = attributeSet2.getAttributeBooleanValue((String) null, "supportsDisplayName", false);
            boolean attributeBooleanValue = attributeSet2.getAttributeBooleanValue((String) null, "supportsPrefix", false);
            boolean attributeBooleanValue2 = attributeSet2.getAttributeBooleanValue((String) null, "supportsMiddleName", false);
            boolean attributeBooleanValue3 = attributeSet2.getAttributeBooleanValue((String) null, "supportsSuffix", false);
            boolean attributeBooleanValue4 = attributeSet2.getAttributeBooleanValue((String) null, "supportsPhoneticFamilyName", false);
            boolean attributeBooleanValue5 = attributeSet2.getAttributeBooleanValue((String) null, "supportsPhoneticMiddleName", false);
            boolean attributeBooleanValue6 = attributeSet2.getAttributeBooleanValue((String) null, "supportsPhoneticGivenName", false);
            checkAttributeTrue(access$1700, "supportsDisplayName");
            checkAttributeTrue(attributeBooleanValue, "supportsPrefix");
            checkAttributeTrue(attributeBooleanValue2, "supportsMiddleName");
            checkAttributeTrue(attributeBooleanValue3, "supportsSuffix");
            checkAttributeTrue(attributeBooleanValue4, "supportsPhoneticFamilyName");
            checkAttributeTrue(attributeBooleanValue5, "supportsPhoneticMiddleName");
            checkAttributeTrue(attributeBooleanValue6, "supportsPhoneticGivenName");
            ArrayList arrayList = new ArrayList();
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/name", (String) null, R.string.nameLabelsGroup, -1, new SimpleInflater(R.string.nameLabelsGroup, (String) null), new SimpleInflater(-1, "data1"));
            throwIfList(newDataKind);
            arrayList.add(newDataKind);
            GeneratedOutlineSupport.outline17("data1", R.string.full_name, 8289, newDataKind.fieldList);
            List<AccountType.EditField> list = newDataKind.fieldList;
            AccountType.EditField editField = new AccountType.EditField("data4", R.string.name_prefix, 8289);
            editField.longForm = true;
            list.add(editField);
            List<AccountType.EditField> list2 = newDataKind.fieldList;
            AccountType.EditField editField2 = new AccountType.EditField("data3", R.string.name_family, 8289);
            editField2.longForm = true;
            list2.add(editField2);
            List<AccountType.EditField> list3 = newDataKind.fieldList;
            AccountType.EditField editField3 = new AccountType.EditField("data5", R.string.name_middle, 8289);
            editField3.longForm = true;
            list3.add(editField3);
            List<AccountType.EditField> list4 = newDataKind.fieldList;
            AccountType.EditField editField4 = new AccountType.EditField("data2", R.string.name_given, 8289);
            editField4.longForm = true;
            list4.add(editField4);
            List<AccountType.EditField> list5 = newDataKind.fieldList;
            AccountType.EditField editField5 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
            editField5.longForm = true;
            list5.add(editField5);
            GeneratedOutlineSupport.outline17("data9", R.string.name_phonetic_family, 193, newDataKind.fieldList);
            GeneratedOutlineSupport.outline17("data8", R.string.name_phonetic_middle, 193, newDataKind.fieldList);
            newDataKind.fieldList.add(new AccountType.EditField("data7", R.string.name_phonetic_given, 193));
            SimpleInflater simpleInflater = new SimpleInflater(R.string.nameLabelsGroup, (String) null);
            SimpleInflater simpleInflater2 = simpleInflater;
            String str = "data2";
            String str2 = "data5";
            String str3 = "data3";
            String str4 = "data4";
            DataKind newDataKind2 = newDataKind(context, xmlPullParser, attributeSet, true, "#displayName", (String) null, R.string.nameLabelsGroup, -1, simpleInflater2, new SimpleInflater(-1, "data1"));
            newDataKind2.typeOverallMax = 1;
            arrayList.add(newDataKind2);
            List<AccountType.EditField> list6 = newDataKind2.fieldList;
            AccountType.EditField editField6 = new AccountType.EditField("data1", R.string.full_name, 8289);
            editField6.shortForm = true;
            list6.add(editField6);
            if (!z) {
                List<AccountType.EditField> list7 = newDataKind2.fieldList;
                AccountType.EditField editField7 = new AccountType.EditField(str4, R.string.name_prefix, 8289);
                editField7.longForm = true;
                list7.add(editField7);
                List<AccountType.EditField> list8 = newDataKind2.fieldList;
                AccountType.EditField editField8 = new AccountType.EditField(str3, R.string.name_family, 8289);
                editField8.longForm = true;
                list8.add(editField8);
                List<AccountType.EditField> list9 = newDataKind2.fieldList;
                AccountType.EditField editField9 = new AccountType.EditField(str2, R.string.name_middle, 8289);
                editField9.longForm = true;
                list9.add(editField9);
                List<AccountType.EditField> list10 = newDataKind2.fieldList;
                AccountType.EditField editField10 = new AccountType.EditField(str, R.string.name_given, 8289);
                editField10.longForm = true;
                list10.add(editField10);
                List<AccountType.EditField> list11 = newDataKind2.fieldList;
                AccountType.EditField editField11 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
                editField11.longForm = true;
                list11.add(editField11);
            } else {
                List<AccountType.EditField> list12 = newDataKind2.fieldList;
                AccountType.EditField editField12 = new AccountType.EditField(str4, R.string.name_prefix, 8289);
                editField12.longForm = true;
                list12.add(editField12);
                List<AccountType.EditField> list13 = newDataKind2.fieldList;
                AccountType.EditField editField13 = new AccountType.EditField(str, R.string.name_given, 8289);
                editField13.longForm = true;
                list13.add(editField13);
                List<AccountType.EditField> list14 = newDataKind2.fieldList;
                AccountType.EditField editField14 = new AccountType.EditField(str2, R.string.name_middle, 8289);
                editField14.longForm = true;
                list14.add(editField14);
                List<AccountType.EditField> list15 = newDataKind2.fieldList;
                AccountType.EditField editField15 = new AccountType.EditField(str3, R.string.name_family, 8289);
                editField15.longForm = true;
                list15.add(editField15);
                List<AccountType.EditField> list16 = newDataKind2.fieldList;
                AccountType.EditField editField16 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
                editField16.longForm = true;
                list16.add(editField16);
            }
            DataKind newDataKind3 = newDataKind(context, xmlPullParser, attributeSet, true, "#phoneticName", (String) null, R.string.name_phonetic, -1, new SimpleInflater(R.string.nameLabelsGroup, (String) null), new SimpleInflater(-1, "data1"));
            newDataKind3.typeOverallMax = 1;
            arrayList.add(newDataKind3);
            List<AccountType.EditField> list17 = newDataKind3.fieldList;
            AccountType.EditField editField17 = new AccountType.EditField("#phoneticName", R.string.name_phonetic, 193);
            editField17.shortForm = true;
            list17.add(editField17);
            List<AccountType.EditField> list18 = newDataKind3.fieldList;
            AccountType.EditField editField18 = new AccountType.EditField("data9", R.string.name_phonetic_family, 193);
            editField18.longForm = true;
            list18.add(editField18);
            List<AccountType.EditField> list19 = newDataKind3.fieldList;
            AccountType.EditField editField19 = new AccountType.EditField("data8", R.string.name_phonetic_middle, 193);
            editField19.longForm = true;
            list19.add(editField19);
            List<AccountType.EditField> list20 = newDataKind3.fieldList;
            AccountType.EditField editField20 = new AccountType.EditField("data7", R.string.name_phonetic_given, 193);
            editField20.longForm = true;
            list20.add(editField20);
            return arrayList;
        }
    }

    private static class NicknameKindBuilder extends KindBuilder {
        /* synthetic */ NicknameKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "nickname";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/nickname", (String) null, R.string.nicknameLabelsGroup, 111, new SimpleInflater(R.string.nicknameLabelsGroup, (String) null), new SimpleInflater(-1, "data1"));
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.nicknameLabelsGroup, 8289));
            newDataKind.defaultValues = new ContentValues();
            newDataKind.defaultValues.put("data2", 1);
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class NoteKindBuilder extends KindBuilder {
        /* synthetic */ NoteKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "note";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/note", (String) null, R.string.label_notes, 130, new SimpleInflater(R.string.label_notes, (String) null), new SimpleInflater(-1, "data1"));
            GeneratedOutlineSupport.outline17("data1", R.string.label_notes, 147457, newDataKind.fieldList);
            newDataKind.maxLinesForDisplay = 100;
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class OrganizationKindBuilder extends KindBuilder {
        /* synthetic */ OrganizationKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "organization";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/organization", (String) null, R.string.organizationLabelsGroup, 125, new SimpleInflater(R.string.organizationLabelsGroup, (String) null), BaseAccountType.ORGANIZATION_BODY_INFLATER);
            GeneratedOutlineSupport.outline17("data1", R.string.ghostData_company, 8193, newDataKind.fieldList);
            GeneratedOutlineSupport.outline17("data4", R.string.ghostData_title, 8193, newDataKind.fieldList);
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public static class PhoneActionAltInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            return R$style.getSmsLabelResourceId(num);
        }

        /* access modifiers changed from: protected */
        public boolean isCustom(Integer num) {
            return R$style.isCustomPhoneType(num);
        }
    }

    public static class PhoneActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            return R$style.getPhoneLabelResourceId(num);
        }

        /* access modifiers changed from: protected */
        public boolean isCustom(Integer num) {
            return R$style.isCustomPhoneType(num);
        }
    }

    private static class PhoneKindBuilder extends KindBuilder {
        /* synthetic */ PhoneKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        protected static AccountType.EditType build(int i, boolean z) {
            AccountType.EditType editType = new AccountType.EditType(i, ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(i));
            editType.secondary = z;
            return editType;
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            if ("home".equals(str)) {
                return build(1, false);
            }
            if ("mobile".equals(str)) {
                return build(2, false);
            }
            if ("work".equals(str)) {
                return build(3, false);
            }
            if ("fax_work".equals(str)) {
                return build(4, true);
            }
            if ("fax_home".equals(str)) {
                return build(5, true);
            }
            if ("pager".equals(str)) {
                return build(6, true);
            }
            if ("other".equals(str)) {
                return build(7, false);
            }
            if ("callback".equals(str)) {
                return build(8, true);
            }
            if ("car".equals(str)) {
                return build(9, true);
            }
            if ("company_main".equals(str)) {
                return build(10, true);
            }
            if ("isdn".equals(str)) {
                return build(11, true);
            }
            if ("main".equals(str)) {
                return build(12, true);
            }
            if ("other_fax".equals(str)) {
                return build(13, true);
            }
            if ("radio".equals(str)) {
                return build(14, true);
            }
            if ("telex".equals(str)) {
                return build(15, true);
            }
            if ("tty_tdd".equals(str)) {
                return build(16, true);
            }
            if ("work_mobile".equals(str)) {
                return build(17, true);
            }
            if ("work_pager".equals(str)) {
                return build(18, true);
            }
            if ("assistant".equals(str)) {
                return build(19, true);
            }
            if ("mms".equals(str)) {
                return build(20, true);
            }
            if (!"custom".equals(str)) {
                return null;
            }
            AccountType.EditType build = build(0, true);
            build.customColumn = "data3";
            return build;
        }

        public String getTagName() {
            return "phone";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/phone_v2", "data2", R.string.phoneLabelsGroup, 10, new PhoneActionInflater(), new SimpleInflater(-1, "data1"));
            newDataKind.iconAltRes = R.drawable.quantum_ic_message_vd_theme_24;
            newDataKind.iconAltDescriptionRes = R.string.sms;
            newDataKind.actionAltHeader = new PhoneActionAltInflater();
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.phoneLabelsGroup, 3));
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class PhotoKindBuilder extends KindBuilder {
        /* synthetic */ PhotoKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "photo";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/photo", (String) null, -1, -1, (AccountType.StringInflater) null, (AccountType.StringInflater) null);
            GeneratedOutlineSupport.outline17("data15", -1, -1, newDataKind.fieldList);
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public static class PostalActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            if (num == null) {
                return R.string.map_other;
            }
            int intValue = num.intValue();
            if (intValue == 1) {
                return R.string.map_home;
            }
            if (intValue != 2) {
                return intValue != 3 ? R.string.map_custom : R.string.map_other;
            }
            return R.string.map_work;
        }
    }

    public static class RelationActionInflater extends CommonInflater {
        /* access modifiers changed from: protected */
        public int getTypeLabelResource(Integer num) {
            return ContactsContract.CommonDataKinds.Relation.getTypeLabelResource(num == null ? 0 : num.intValue());
        }
    }

    private static class RelationshipKindBuilder extends KindBuilder {
        /* synthetic */ RelationshipKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            if ("assistant".equals(str)) {
                return BaseAccountType.buildRelationType(1);
            }
            if ("brother".equals(str)) {
                return BaseAccountType.buildRelationType(2);
            }
            if ("child".equals(str)) {
                return BaseAccountType.buildRelationType(3);
            }
            if ("domestic_partner".equals(str)) {
                return BaseAccountType.buildRelationType(4);
            }
            if ("father".equals(str)) {
                return BaseAccountType.buildRelationType(5);
            }
            if ("friend".equals(str)) {
                return BaseAccountType.buildRelationType(6);
            }
            if ("manager".equals(str)) {
                return BaseAccountType.buildRelationType(7);
            }
            if ("mother".equals(str)) {
                return BaseAccountType.buildRelationType(8);
            }
            if ("parent".equals(str)) {
                return BaseAccountType.buildRelationType(9);
            }
            if ("partner".equals(str)) {
                return BaseAccountType.buildRelationType(10);
            }
            if ("referred_by".equals(str)) {
                return BaseAccountType.buildRelationType(11);
            }
            if ("relative".equals(str)) {
                return BaseAccountType.buildRelationType(12);
            }
            if ("sister".equals(str)) {
                return BaseAccountType.buildRelationType(13);
            }
            if ("spouse".equals(str)) {
                return BaseAccountType.buildRelationType(14);
            }
            if (!"custom".equals(str)) {
                return null;
            }
            AccountType.EditType buildRelationType = BaseAccountType.buildRelationType(0);
            buildRelationType.secondary = true;
            buildRelationType.customColumn = "data3";
            return buildRelationType;
        }

        public String getTagName() {
            return "relationship";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/relation", "data2", R.string.relationLabelsGroup, 999, new RelationActionInflater(), new SimpleInflater(-1, "data1"));
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.relationLabelsGroup, 8289));
            newDataKind.defaultValues = new ContentValues();
            newDataKind.defaultValues.put("data2", 14);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public static class SimpleInflater implements AccountType.StringInflater {
        private final String mColumnName;
        private final int mStringRes;

        public SimpleInflater(int i, String str) {
            this.mStringRes = i;
            this.mColumnName = str;
        }

        public CharSequence inflateUsing(Context context, ContentValues contentValues) {
            boolean containsKey = contentValues.containsKey(this.mColumnName);
            boolean z = this.mStringRes > 0;
            CharSequence text = z ? context.getText(this.mStringRes) : null;
            String asString = containsKey ? contentValues.getAsString(this.mColumnName) : null;
            if (z && containsKey) {
                return String.format(text.toString(), new Object[]{asString});
            } else if (z) {
                return text;
            } else {
                if (containsKey) {
                    return asString;
                }
                return null;
            }
        }

        public String toString() {
            return SimpleInflater.class.getSimpleName() + " mStringRes=" + this.mStringRes + " mColumnName" + this.mColumnName;
        }
    }

    private static class SipAddressKindBuilder extends KindBuilder {
        /* synthetic */ SipAddressKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "sip_address";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/sip_address", (String) null, R.string.label_sip_address, 145, new SimpleInflater(R.string.label_sip_address, (String) null), new SimpleInflater(-1, "data1"));
            GeneratedOutlineSupport.outline17("data1", R.string.label_sip_address, 33, newDataKind.fieldList);
            throwIfList(newDataKind);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class StructuredPostalKindBuilder extends KindBuilder {
        /* synthetic */ StructuredPostalKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        /* access modifiers changed from: protected */
        public AccountType.EditType buildEditTypeForTypeTag(AttributeSet attributeSet, String str) {
            if ("home".equals(str)) {
                return BaseAccountType.buildPostalType(1);
            }
            if ("work".equals(str)) {
                return BaseAccountType.buildPostalType(2);
            }
            if ("other".equals(str)) {
                return BaseAccountType.buildPostalType(3);
            }
            if (!"custom".equals(str)) {
                return null;
            }
            AccountType.EditType buildPostalType = BaseAccountType.buildPostalType(0);
            buildPostalType.secondary = true;
            buildPostalType.customColumn = "data3";
            return buildPostalType;
        }

        public String getTagName() {
            return "postal";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/postal-address_v2", "data2", R.string.postalLabelsGroup, 25, new PostalActionInflater(), new SimpleInflater(-1, "data1"));
            if (!attributeSet.getAttributeBooleanValue((String) null, "needsStructured", false)) {
                newDataKind.maxLinesForDisplay = 10;
                GeneratedOutlineSupport.outline17("data1", R.string.postal_address, 139377, newDataKind.fieldList);
            } else if (Locale.JAPANESE.getLanguage().equals(Locale.getDefault().getLanguage())) {
                List<AccountType.EditField> list = newDataKind.fieldList;
                AccountType.EditField editField = new AccountType.EditField("data10", R.string.postal_country, 139377);
                editField.optional = true;
                list.add(editField);
                GeneratedOutlineSupport.outline17("data9", R.string.postal_postcode, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data8", R.string.postal_region, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data7", R.string.postal_city, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data4", R.string.postal_street, 139377, newDataKind.fieldList);
            } else {
                GeneratedOutlineSupport.outline17("data4", R.string.postal_street, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data7", R.string.postal_city, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data8", R.string.postal_region, 139377, newDataKind.fieldList);
                GeneratedOutlineSupport.outline17("data9", R.string.postal_postcode, 139377, newDataKind.fieldList);
                List<AccountType.EditField> list2 = newDataKind.fieldList;
                AccountType.EditField editField2 = new AccountType.EditField("data10", R.string.postal_country, 139377);
                editField2.optional = true;
                list2.add(editField2);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    private static class WebsiteKindBuilder extends KindBuilder {
        /* synthetic */ WebsiteKindBuilder(C02671 r1) {
            super((C02671) null);
        }

        public String getTagName() {
            return "website";
        }

        public List<DataKind> parseDataKind(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws AccountType.DefinitionException, XmlPullParserException, IOException {
            DataKind newDataKind = newDataKind(context, xmlPullParser, attributeSet, false, "vnd.android.cursor.item/website", (String) null, R.string.websiteLabelsGroup, 160, new SimpleInflater(R.string.websiteLabelsGroup, (String) null), new SimpleInflater(-1, "data1"));
            newDataKind.fieldList.add(new AccountType.EditField("data1", R.string.websiteLabelsGroup, 17));
            newDataKind.defaultValues = new ContentValues();
            newDataKind.defaultValues.put("data2", 7);
            ArrayList arrayList = new ArrayList();
            arrayList.add(newDataKind);
            return arrayList;
        }
    }

    public BaseAccountType() {
        this.accountType = null;
        this.dataSet = null;
    }

    protected static AccountType.EditType buildEmailType(int i) {
        return new AccountType.EditType(i, ContactsContract.CommonDataKinds.Email.getTypeLabelResource(i));
    }

    protected static AccountType.EditType buildEventType(int i, boolean z) {
        AccountType.EventEditType eventEditType = new AccountType.EventEditType(i, ContactsContract.CommonDataKinds.Event.getTypeResource(Integer.valueOf(i)));
        eventEditType.setYearOptional(z);
        return eventEditType;
    }

    protected static AccountType.EditType buildImType(int i) {
        return new AccountType.EditType(i, ContactsContract.CommonDataKinds.Im.getProtocolLabelResource(i));
    }

    protected static AccountType.EditType buildPhoneType(int i) {
        return new AccountType.EditType(i, ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(i));
    }

    protected static AccountType.EditType buildPostalType(int i) {
        return new AccountType.EditType(i, ContactsContract.CommonDataKinds.StructuredPostal.getTypeLabelResource(i));
    }

    protected static AccountType.EditType buildRelationType(int i) {
        return new AccountType.EditType(i, ContactsContract.CommonDataKinds.Relation.getTypeLabelResource(i));
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindDisplayName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("#displayName", R.string.nameLabelsGroup, -1, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.nameLabelsGroup, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        List<AccountType.EditField> list = outline2.fieldList;
        AccountType.EditField editField = new AccountType.EditField("data1", R.string.full_name, 8289);
        editField.shortForm = true;
        list.add(editField);
        if (!context.getResources().getBoolean(R.bool.config_editor_field_order_primary)) {
            List<AccountType.EditField> list2 = outline2.fieldList;
            AccountType.EditField editField2 = new AccountType.EditField("data4", R.string.name_prefix, 8289);
            editField2.longForm = true;
            list2.add(editField2);
            List<AccountType.EditField> list3 = outline2.fieldList;
            AccountType.EditField editField3 = new AccountType.EditField("data3", R.string.name_family, 8289);
            editField3.longForm = true;
            list3.add(editField3);
            List<AccountType.EditField> list4 = outline2.fieldList;
            AccountType.EditField editField4 = new AccountType.EditField("data5", R.string.name_middle, 8289);
            editField4.longForm = true;
            list4.add(editField4);
            List<AccountType.EditField> list5 = outline2.fieldList;
            AccountType.EditField editField5 = new AccountType.EditField("data2", R.string.name_given, 8289);
            editField5.longForm = true;
            list5.add(editField5);
            List<AccountType.EditField> list6 = outline2.fieldList;
            AccountType.EditField editField6 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
            editField6.longForm = true;
            list6.add(editField6);
        } else {
            List<AccountType.EditField> list7 = outline2.fieldList;
            AccountType.EditField editField7 = new AccountType.EditField("data4", R.string.name_prefix, 8289);
            editField7.longForm = true;
            list7.add(editField7);
            List<AccountType.EditField> list8 = outline2.fieldList;
            AccountType.EditField editField8 = new AccountType.EditField("data2", R.string.name_given, 8289);
            editField8.longForm = true;
            list8.add(editField8);
            List<AccountType.EditField> list9 = outline2.fieldList;
            AccountType.EditField editField9 = new AccountType.EditField("data5", R.string.name_middle, 8289);
            editField9.longForm = true;
            list9.add(editField9);
            List<AccountType.EditField> list10 = outline2.fieldList;
            AccountType.EditField editField10 = new AccountType.EditField("data3", R.string.name_family, 8289);
            editField10.longForm = true;
            list10.add(editField10);
            List<AccountType.EditField> list11 = outline2.fieldList;
            AccountType.EditField editField11 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
            editField11.longForm = true;
            list11.add(editField11);
        }
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEmail(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/email_v2", R.string.emailLabelsGroup, 15, true, this);
        outline2.actionHeader = new EmailActionInflater();
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(buildEmailType(1));
        outline2.typeList.add(buildEmailType(2));
        outline2.typeList.add(buildEmailType(3));
        outline2.typeList.add(buildEmailType(4));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildEmailType = buildEmailType(0);
        buildEmailType.secondary = true;
        buildEmailType.customColumn = "data3";
        list.add(buildEmailType);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.emailLabelsGroup, 33, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindGroupMembership(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/group_membership", R.string.groupsLabel, 150, true, this);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", -1, -1, outline2.fieldList);
        outline2.maxLinesForDisplay = 10;
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindIm(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/im", R.string.imLabelsGroup, 140, true, this);
        outline2.actionHeader = new ImActionInflater();
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 3);
        outline2.typeColumn = "data5";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(buildImType(0));
        outline2.typeList.add(buildImType(1));
        outline2.typeList.add(buildImType(2));
        outline2.typeList.add(buildImType(3));
        outline2.typeList.add(buildImType(4));
        outline2.typeList.add(buildImType(5));
        outline2.typeList.add(buildImType(6));
        outline2.typeList.add(buildImType(7));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildImType = buildImType(-1);
        buildImType.secondary = true;
        buildImType.customColumn = "data6";
        list.add(buildImType);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.imLabelsGroup, 33, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNickname(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/nickname", R.string.nicknameLabelsGroup, 111, true, this);
        outline2.typeOverallMax = 1;
        outline2.actionHeader = new SimpleInflater(R.string.nicknameLabelsGroup, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 1);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.nicknameLabelsGroup, 8289, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNote(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/note", R.string.label_notes, 130, true, this);
        outline2.typeOverallMax = 1;
        outline2.actionHeader = new SimpleInflater(R.string.label_notes, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.label_notes, 147457, outline2.fieldList);
        outline2.maxLinesForDisplay = 100;
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindOrganization(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/organization", R.string.organizationLabelsGroup, 125, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.organizationLabelsGroup, (String) null);
        outline2.actionBody = ORGANIZATION_BODY_INFLATER;
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.ghostData_company, 8193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data4", R.string.ghostData_title, 8193, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhone(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/phone_v2", R.string.phoneLabelsGroup, 10, true, this);
        outline2.iconAltRes = R.drawable.quantum_ic_message_vd_theme_24;
        outline2.iconAltDescriptionRes = R.string.sms;
        outline2.actionHeader = new PhoneActionInflater();
        outline2.actionAltHeader = new PhoneActionAltInflater();
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(buildPhoneType(2));
        outline2.typeList.add(buildPhoneType(1));
        outline2.typeList.add(buildPhoneType(3));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildPhoneType = buildPhoneType(4);
        buildPhoneType.secondary = true;
        list.add(buildPhoneType);
        List<AccountType.EditType> list2 = outline2.typeList;
        AccountType.EditType buildPhoneType2 = buildPhoneType(5);
        buildPhoneType2.secondary = true;
        list2.add(buildPhoneType2);
        List<AccountType.EditType> list3 = outline2.typeList;
        AccountType.EditType buildPhoneType3 = buildPhoneType(6);
        buildPhoneType3.secondary = true;
        list3.add(buildPhoneType3);
        outline2.typeList.add(buildPhoneType(7));
        List<AccountType.EditType> list4 = outline2.typeList;
        AccountType.EditType buildPhoneType4 = buildPhoneType(0);
        buildPhoneType4.secondary = true;
        buildPhoneType4.customColumn = "data3";
        list4.add(buildPhoneType4);
        List<AccountType.EditType> list5 = outline2.typeList;
        AccountType.EditType buildPhoneType5 = buildPhoneType(8);
        buildPhoneType5.secondary = true;
        list5.add(buildPhoneType5);
        List<AccountType.EditType> list6 = outline2.typeList;
        AccountType.EditType buildPhoneType6 = buildPhoneType(9);
        buildPhoneType6.secondary = true;
        list6.add(buildPhoneType6);
        List<AccountType.EditType> list7 = outline2.typeList;
        AccountType.EditType buildPhoneType7 = buildPhoneType(10);
        buildPhoneType7.secondary = true;
        list7.add(buildPhoneType7);
        List<AccountType.EditType> list8 = outline2.typeList;
        AccountType.EditType buildPhoneType8 = buildPhoneType(11);
        buildPhoneType8.secondary = true;
        list8.add(buildPhoneType8);
        List<AccountType.EditType> list9 = outline2.typeList;
        AccountType.EditType buildPhoneType9 = buildPhoneType(12);
        buildPhoneType9.secondary = true;
        list9.add(buildPhoneType9);
        List<AccountType.EditType> list10 = outline2.typeList;
        AccountType.EditType buildPhoneType10 = buildPhoneType(13);
        buildPhoneType10.secondary = true;
        list10.add(buildPhoneType10);
        List<AccountType.EditType> list11 = outline2.typeList;
        AccountType.EditType buildPhoneType11 = buildPhoneType(14);
        buildPhoneType11.secondary = true;
        list11.add(buildPhoneType11);
        List<AccountType.EditType> list12 = outline2.typeList;
        AccountType.EditType buildPhoneType12 = buildPhoneType(15);
        buildPhoneType12.secondary = true;
        list12.add(buildPhoneType12);
        List<AccountType.EditType> list13 = outline2.typeList;
        AccountType.EditType buildPhoneType13 = buildPhoneType(16);
        buildPhoneType13.secondary = true;
        list13.add(buildPhoneType13);
        List<AccountType.EditType> list14 = outline2.typeList;
        AccountType.EditType buildPhoneType14 = buildPhoneType(17);
        buildPhoneType14.secondary = true;
        list14.add(buildPhoneType14);
        List<AccountType.EditType> list15 = outline2.typeList;
        AccountType.EditType buildPhoneType15 = buildPhoneType(18);
        buildPhoneType15.secondary = true;
        list15.add(buildPhoneType15);
        List<AccountType.EditType> list16 = outline2.typeList;
        AccountType.EditType buildPhoneType16 = buildPhoneType(19);
        buildPhoneType16.secondary = true;
        list16.add(buildPhoneType16);
        List<AccountType.EditType> list17 = outline2.typeList;
        AccountType.EditType buildPhoneType17 = buildPhoneType(20);
        buildPhoneType17.secondary = true;
        list17.add(buildPhoneType17);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.phoneLabelsGroup, 3, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoneticName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("#phoneticName", R.string.name_phonetic, -1, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.nameLabelsGroup, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        List<AccountType.EditField> list = outline2.fieldList;
        AccountType.EditField editField = new AccountType.EditField("#phoneticName", R.string.name_phonetic, 193);
        editField.shortForm = true;
        list.add(editField);
        List<AccountType.EditField> list2 = outline2.fieldList;
        AccountType.EditField editField2 = new AccountType.EditField("data9", R.string.name_phonetic_family, 193);
        editField2.longForm = true;
        list2.add(editField2);
        List<AccountType.EditField> list3 = outline2.fieldList;
        AccountType.EditField editField3 = new AccountType.EditField("data8", R.string.name_phonetic_middle, 193);
        editField3.longForm = true;
        list3.add(editField3);
        List<AccountType.EditField> list4 = outline2.fieldList;
        AccountType.EditField editField4 = new AccountType.EditField("data7", R.string.name_phonetic_given, 193);
        editField4.longForm = true;
        list4.add(editField4);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoto(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/photo", -1, -1, true, this);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data15", -1, -1, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindSipAddress(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/sip_address", R.string.label_sip_address, 145, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.label_sip_address, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.label_sip_address, 33, outline2.fieldList);
        outline2.typeOverallMax = 1;
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/name", R.string.nameLabelsGroup, -1, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.nameLabelsGroup, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.full_name, 8289, outline2.fieldList);
        List<AccountType.EditField> list = outline2.fieldList;
        AccountType.EditField editField = new AccountType.EditField("data4", R.string.name_prefix, 8289);
        editField.longForm = true;
        list.add(editField);
        List<AccountType.EditField> list2 = outline2.fieldList;
        AccountType.EditField editField2 = new AccountType.EditField("data3", R.string.name_family, 8289);
        editField2.longForm = true;
        list2.add(editField2);
        List<AccountType.EditField> list3 = outline2.fieldList;
        AccountType.EditField editField3 = new AccountType.EditField("data5", R.string.name_middle, 8289);
        editField3.longForm = true;
        list3.add(editField3);
        List<AccountType.EditField> list4 = outline2.fieldList;
        AccountType.EditField editField4 = new AccountType.EditField("data2", R.string.name_given, 8289);
        editField4.longForm = true;
        list4.add(editField4);
        List<AccountType.EditField> list5 = outline2.fieldList;
        AccountType.EditField editField5 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
        editField5.longForm = true;
        list5.add(editField5);
        GeneratedOutlineSupport.outline17("data9", R.string.name_phonetic_family, 193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data8", R.string.name_phonetic_middle, 193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data7", R.string.name_phonetic_given, 193, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredPostal(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/postal-address_v2", R.string.postalLabelsGroup, 25, true, this);
        outline2.actionHeader = new PostalActionInflater();
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(buildPostalType(1));
        outline2.typeList.add(buildPostalType(2));
        outline2.typeList.add(buildPostalType(3));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildPostalType = buildPostalType(0);
        buildPostalType.secondary = true;
        buildPostalType.customColumn = "data3";
        list.add(buildPostalType);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.postal_address, 139377, outline2.fieldList);
        outline2.maxLinesForDisplay = 10;
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindWebsite(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/website", R.string.websiteLabelsGroup, 160, true, this);
        outline2.actionHeader = new SimpleInflater(R.string.websiteLabelsGroup, (String) null);
        outline2.actionBody = new SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 7);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.websiteLabelsGroup, 17, outline2.fieldList);
        return outline2;
    }

    public boolean isGroupMembershipEditable() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void parseEditSchema(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException, AccountType.DefinitionException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next != 3 || xmlPullParser.getDepth() > depth) {
                int depth2 = xmlPullParser.getDepth();
                if (next == 2 && depth2 == depth + 1) {
                    String name = xmlPullParser.getName();
                    if ("DataKind".equals(name)) {
                        for (DataKind addKind : KindParser.INSTANCE.parseDataKindTag(context, xmlPullParser, attributeSet)) {
                            addKind(addKind);
                        }
                    } else {
                        LogUtil.m9i("BaseAccountType.parseEditSchema", GeneratedOutlineSupport.outline8("Skipping unknown tag ", name), new Object[0]);
                    }
                }
            } else {
                return;
            }
        }
    }
}

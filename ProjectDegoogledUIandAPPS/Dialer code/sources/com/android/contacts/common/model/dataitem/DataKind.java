package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;
import com.android.contacts.common.model.account.AccountType;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.Collections2;
import java.text.SimpleDateFormat;
import java.util.List;

public final class DataKind {
    public AccountType.StringInflater actionAltHeader;
    public AccountType.StringInflater actionBody;
    public AccountType.StringInflater actionHeader;
    public SimpleDateFormat dateFormatWithYear;
    public SimpleDateFormat dateFormatWithoutYear;
    public ContentValues defaultValues;
    public boolean editable;
    public List<AccountType.EditField> fieldList;
    public int iconAltDescriptionRes;
    public int iconAltRes;
    public int maxLinesForDisplay;
    public String mimeType;
    public String resourcePackageName;
    public int titleRes;
    public String typeColumn;
    public List<AccountType.EditType> typeList;
    public int typeOverallMax;
    public int weight;

    public DataKind() {
        this.maxLinesForDisplay = 1;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        StringBuilder outline14 = GeneratedOutlineSupport.outline14("DataKind:", " resPackageName=");
        outline14.append(this.resourcePackageName);
        outline14.append(" mimeType=");
        outline14.append(this.mimeType);
        outline14.append(" titleRes=");
        outline14.append(this.titleRes);
        outline14.append(" iconAltRes=");
        outline14.append(this.iconAltRes);
        outline14.append(" iconAltDescriptionRes=");
        outline14.append(this.iconAltDescriptionRes);
        outline14.append(" weight=");
        outline14.append(this.weight);
        outline14.append(" editable=");
        outline14.append(this.editable);
        outline14.append(" actionHeader=");
        outline14.append(this.actionHeader);
        outline14.append(" actionAltHeader=");
        outline14.append(this.actionAltHeader);
        outline14.append(" actionBody=");
        outline14.append(this.actionBody);
        outline14.append(" typeColumn=");
        outline14.append(this.typeColumn);
        outline14.append(" typeOverallMax=");
        outline14.append(this.typeOverallMax);
        outline14.append(" typeList=");
        List<AccountType.EditType> list = this.typeList;
        String str4 = "(null)";
        if (list == null) {
            str = str4;
        } else {
            str = Collections2.toString(list.iterator());
        }
        outline14.append(str);
        outline14.append(" fieldList=");
        List<AccountType.EditField> list2 = this.fieldList;
        if (list2 == null) {
            str2 = str4;
        } else {
            str2 = Collections2.toString(list2.iterator());
        }
        outline14.append(str2);
        outline14.append(" defaultValues=");
        outline14.append(this.defaultValues);
        outline14.append(" dateFormatWithoutYear=");
        SimpleDateFormat simpleDateFormat = this.dateFormatWithoutYear;
        if (simpleDateFormat == null) {
            str3 = str4;
        } else {
            str3 = simpleDateFormat.toPattern();
        }
        outline14.append(str3);
        outline14.append(" dateFormatWithYear=");
        SimpleDateFormat simpleDateFormat2 = this.dateFormatWithYear;
        if (simpleDateFormat2 != null) {
            str4 = simpleDateFormat2.toPattern();
        }
        outline14.append(str4);
        return outline14.toString();
    }

    public DataKind(String str, int i, int i2, boolean z) {
        this.mimeType = str;
        this.titleRes = i;
        this.weight = i2;
        this.editable = z;
        this.typeOverallMax = -1;
        this.maxLinesForDisplay = 1;
    }
}

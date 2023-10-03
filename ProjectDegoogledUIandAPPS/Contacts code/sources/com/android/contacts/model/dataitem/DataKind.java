package com.android.contacts.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import com.android.contacts.model.account.AccountType;
import com.google.common.collect.Iterators;
import java.text.SimpleDateFormat;
import java.util.List;

public final class DataKind {
    public static final String PSEUDO_MIME_TYPE_NAME = "#name";
    public static final String PSEUDO_MIME_TYPE_PHONETIC_NAME = "#phoneticName";
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

    public DataKind(String str, int i, int i2, boolean z) {
        this.mimeType = str;
        this.titleRes = i;
        this.weight = i2;
        this.editable = z;
        this.typeOverallMax = -1;
        this.maxLinesForDisplay = 1;
    }

    public String getKindString(Context context) {
        int i = this.titleRes;
        return (i == -1 || i == 0) ? "" : context.getString(i);
    }

    public String toString() {
        return "DataKind:" + " resPackageName=" + this.resourcePackageName + " mimeType=" + this.mimeType + " titleRes=" + this.titleRes + " iconAltRes=" + this.iconAltRes + " iconAltDescriptionRes=" + this.iconAltDescriptionRes + " weight=" + this.weight + " editable=" + this.editable + " actionHeader=" + this.actionHeader + " actionAltHeader=" + this.actionAltHeader + " actionBody=" + this.actionBody + " typeColumn=" + this.typeColumn + " typeOverallMax=" + this.typeOverallMax + " typeList=" + toString((Iterable<?>) this.typeList) + " fieldList=" + toString((Iterable<?>) this.fieldList) + " defaultValues=" + this.defaultValues + " dateFormatWithoutYear=" + toString(this.dateFormatWithoutYear) + " dateFormatWithYear=" + toString(this.dateFormatWithYear);
    }

    public static String toString(SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat == null ? "(null)" : simpleDateFormat.toPattern();
    }

    public static String toString(Iterable<?> iterable) {
        return iterable == null ? "(null)" : Iterators.toString(iterable.iterator());
    }
}

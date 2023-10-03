package com.android.dialer.common.database;

import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Selection {
    private final String selection;
    /* access modifiers changed from: private */
    public final String[] selectionArgs;

    public static class Column {
        private final String column;

        /* synthetic */ Column(String str, C04581 r2) {
            Assert.isNotNull(str);
            this.column = str;
        }

        /* renamed from: in */
        public Selection mo5865in(Collection<String> collection) {
            return new Builder(this.column + " IN (" + TextUtils.join(",", Collections.nCopies(collection.size(), "?")) + ")", collection, (C04581) null).build();
        }

        /* renamed from: is */
        public Selection mo5867is(String str, Object obj) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.column);
            sb.append(" ");
            Assert.isNotNull(str);
            return Selection.fromString(GeneratedOutlineSupport.outline12(sb, str, " ?"), obj.toString());
        }

        /* renamed from: is */
        public Selection mo5866is(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.column);
            sb.append(" ");
            Assert.isNotNull(str);
            sb.append(str);
            return Selection.fromString(sb.toString(), new String[0]);
        }
    }

    /* synthetic */ Selection(String str, String[] strArr, C04581 r3) {
        this.selection = str;
        this.selectionArgs = strArr;
    }

    static /* synthetic */ String access$400(String str) {
        if (str.isEmpty()) {
            return "";
        }
        if (!str.startsWith("(")) {
            return GeneratedOutlineSupport.outline9("(", str, ")");
        }
        boolean z = true;
        int i = 1;
        for (int i2 = 1; i2 < str.length() - 1; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '(') {
                i++;
            } else if (charAt == ')' && i - 1 == 0) {
                return GeneratedOutlineSupport.outline9("(", str, ")");
            }
        }
        if (i != 1) {
            z = false;
        }
        Assert.checkArgument(z);
        return str;
    }

    public static Builder builder() {
        return new Builder((C04581) null);
    }

    public static Column column(String str) {
        return new Column(str, (C04581) null);
    }

    public static Selection fromString(String str, String... strArr) {
        return new Builder(str, strArr == null ? Collections.emptyList() : Arrays.asList(strArr), (C04581) null).build();
    }

    public Builder buildUpon() {
        return new Builder(this, (C04581) null);
    }

    public String getSelection() {
        return this.selection;
    }

    public String[] getSelectionArgs() {
        return this.selectionArgs;
    }

    public boolean isEmpty() {
        return this.selection.isEmpty();
    }

    public static final class Builder {
        private final StringBuilder selection = new StringBuilder();
        private final List<String> selectionArgs = new ArrayList();

        /* synthetic */ Builder(C04581 r1) {
        }

        public Builder and(Selection selection2) {
            if (selection2.isEmpty()) {
                return this;
            }
            if (this.selection.length() > 0) {
                this.selection.append(" AND ");
            }
            this.selection.append(selection2.getSelection());
            Collections.addAll(this.selectionArgs, selection2.getSelectionArgs());
            return this;
        }

        public Selection build() {
            if (this.selection.length() == 0) {
                return new Selection("", new String[0], (C04581) null);
            }
            String access$400 = Selection.access$400(this.selection.toString());
            List<String> list = this.selectionArgs;
            return new Selection(access$400, (String[]) list.toArray(new String[list.size()]), (C04581) null);
        }

        /* renamed from: or */
        public Builder mo5864or(Selection selection2) {
            if (selection2.isEmpty()) {
                return this;
            }
            if (this.selection.length() > 0) {
                this.selection.append(" OR ");
            }
            this.selection.append(selection2.getSelection());
            Collections.addAll(this.selectionArgs, selection2.getSelectionArgs());
            return this;
        }

        /* synthetic */ Builder(String str, Collection collection, C04581 r7) {
            int i;
            if (str != null) {
                boolean z = false;
                int i2 = 0;
                for (int i3 = 0; i3 < str.length(); i3++) {
                    if (str.charAt(i3) == '?') {
                        i2++;
                    }
                }
                if (collection == null) {
                    i = 0;
                } else {
                    i = collection.size();
                }
                Assert.checkArgument(i2 == i ? true : z);
                this.selection.append(Selection.access$400(str));
                if (collection != null) {
                    this.selectionArgs.addAll(collection);
                }
            }
        }

        /* synthetic */ Builder(Selection selection2, C04581 r3) {
            this.selection.append(selection2.getSelection());
            Collections.addAll(this.selectionArgs, selection2.selectionArgs);
        }
    }
}

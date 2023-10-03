package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.res.Configuration;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.FontSizeUtils;
import java.text.DecimalFormat;

/* renamed from: com.android.systemui.qs.tiles.DataUsageDetailView */
public class DataUsageDetailView extends LinearLayout {
    private final DecimalFormat FORMAT = new DecimalFormat("#.##");

    public DataUsageDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FontSizeUtils.updateFontSize(this, 16908310, C1775R$dimen.qs_data_usage_text_size);
        FontSizeUtils.updateFontSize(this, C1777R$id.usage_text, C1775R$dimen.qs_data_usage_usage_text_size);
        FontSizeUtils.updateFontSize(this, C1777R$id.usage_carrier_text, C1775R$dimen.qs_data_usage_text_size);
        FontSizeUtils.updateFontSize(this, C1777R$id.usage_info_top_text, C1775R$dimen.qs_data_usage_text_size);
        FontSizeUtils.updateFontSize(this, C1777R$id.usage_period_text, C1775R$dimen.qs_data_usage_text_size);
        FontSizeUtils.updateFontSize(this, C1777R$id.usage_info_bottom_text, C1775R$dimen.qs_data_usage_text_size);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bind(com.android.settingslib.net.DataUsageController.DataUsageInfo r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            android.content.Context r2 = r0.mContext
            android.content.res.Resources r2 = r2.getResources()
            long r3 = r1.usageLevel
            long r5 = r1.warningLevel
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r6 = 0
            r7 = 0
            r9 = 1
            r10 = 0
            if (r5 < 0) goto L_0x0072
            long r11 = r1.limitLevel
            int r5 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x001e
            goto L_0x0072
        L_0x001e:
            int r5 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r5 > 0) goto L_0x0044
            int r5 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_remaining_data
            long r11 = r11 - r3
            int r13 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_used
            java.lang.Object[] r14 = new java.lang.Object[r9]
            java.lang.CharSequence r3 = r0.formatDataUsage(r3)
            r14[r10] = r3
            java.lang.String r3 = r2.getString(r13, r14)
            int r4 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_limit
            java.lang.Object[] r13 = new java.lang.Object[r9]
            long r14 = r1.limitLevel
            java.lang.CharSequence r14 = r0.formatDataUsage(r14)
            r13[r10] = r14
            java.lang.String r2 = r2.getString(r4, r13)
            goto L_0x0087
        L_0x0044:
            int r5 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_over_limit
            long r11 = r3 - r11
            int r6 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_used
            java.lang.Object[] r13 = new java.lang.Object[r9]
            java.lang.CharSequence r3 = r0.formatDataUsage(r3)
            r13[r10] = r3
            java.lang.String r3 = r2.getString(r6, r13)
            int r4 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_limit
            java.lang.Object[] r6 = new java.lang.Object[r9]
            long r13 = r1.limitLevel
            java.lang.CharSequence r13 = r0.formatDataUsage(r13)
            r6[r10] = r13
            java.lang.String r6 = r2.getString(r4, r6)
            android.content.Context r2 = r0.mContext
            android.content.res.ColorStateList r2 = com.android.settingslib.Utils.getColorError(r2)
            r18 = r6
            r6 = r2
            r2 = r18
            goto L_0x0087
        L_0x0072:
            int r5 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_usage
            long r11 = r1.usageLevel
            int r3 = com.android.systemui.C1784R$string.quick_settings_cellular_detail_data_warning
            java.lang.Object[] r4 = new java.lang.Object[r9]
            long r13 = r1.warningLevel
            java.lang.CharSequence r13 = r0.formatDataUsage(r13)
            r4[r10] = r13
            java.lang.String r3 = r2.getString(r3, r4)
            r2 = r6
        L_0x0087:
            if (r6 != 0) goto L_0x008f
            android.content.Context r4 = r0.mContext
            android.content.res.ColorStateList r6 = com.android.settingslib.Utils.getColorAccent(r4)
        L_0x008f:
            r4 = 16908310(0x1020016, float:2.387729E-38)
            android.view.View r4 = r0.findViewById(r4)
            android.widget.TextView r4 = (android.widget.TextView) r4
            r4.setText(r5)
            int r4 = com.android.systemui.C1777R$id.usage_text
            android.view.View r4 = r0.findViewById(r4)
            android.widget.TextView r4 = (android.widget.TextView) r4
            java.lang.CharSequence r5 = r0.formatDataUsage(r11)
            r4.setText(r5)
            r4.setTextColor(r6)
            int r4 = com.android.systemui.C1777R$id.usage_graph
            android.view.View r4 = r0.findViewById(r4)
            com.android.systemui.qs.DataUsageGraph r4 = (com.android.systemui.p006qs.DataUsageGraph) r4
            long r12 = r1.limitLevel
            long r14 = r1.warningLevel
            long r5 = r1.usageLevel
            r11 = r4
            r16 = r5
            r11.setLevels(r12, r14, r16)
            int r5 = com.android.systemui.C1777R$id.usage_carrier_text
            android.view.View r5 = r0.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            java.lang.String r6 = r1.carrier
            r5.setText(r6)
            int r5 = com.android.systemui.C1777R$id.usage_period_text
            android.view.View r5 = r0.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            java.lang.String r6 = r1.period
            r5.setText(r6)
            int r5 = com.android.systemui.C1777R$id.usage_info_top_text
            android.view.View r5 = r0.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            r6 = 8
            if (r3 == 0) goto L_0x00e9
            r11 = r10
            goto L_0x00ea
        L_0x00e9:
            r11 = r6
        L_0x00ea:
            r5.setVisibility(r11)
            r5.setText(r3)
            int r3 = com.android.systemui.C1777R$id.usage_info_bottom_text
            android.view.View r0 = r0.findViewById(r3)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r2 == 0) goto L_0x00fc
            r3 = r10
            goto L_0x00fd
        L_0x00fc:
            r3 = r6
        L_0x00fd:
            r0.setVisibility(r3)
            r0.setText(r2)
            long r2 = r1.warningLevel
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0111
            long r0 = r1.limitLevel
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0110
            goto L_0x0111
        L_0x0110:
            r9 = r10
        L_0x0111:
            if (r9 == 0) goto L_0x0114
            goto L_0x0115
        L_0x0114:
            r10 = r6
        L_0x0115:
            r4.setVisibility(r10)
            if (r9 != 0) goto L_0x011d
            r5.setVisibility(r6)
        L_0x011d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.p006qs.tiles.DataUsageDetailView.bind(com.android.settingslib.net.DataUsageController$DataUsageInfo):void");
    }

    private CharSequence formatDataUsage(long j) {
        Formatter.BytesResult formatBytes = Formatter.formatBytes(this.mContext.getResources(), j, 8);
        return BidiFormatter.getInstance().unicodeWrap(this.mContext.getString(17040051, new Object[]{formatBytes.value, formatBytes.units}));
    }
}

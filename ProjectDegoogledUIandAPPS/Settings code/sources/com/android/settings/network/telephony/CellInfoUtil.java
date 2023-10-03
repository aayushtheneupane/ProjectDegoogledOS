package com.android.settings.network.telephony;

import android.telephony.CellIdentity;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.OperatorInfo;
import java.util.List;
import java.util.stream.Collectors;

public final class CellInfoUtil {
    public static CellInfo wrapCellInfoWithCellIdentity(CellIdentity cellIdentity) {
        if (cellIdentity instanceof CellIdentityLte) {
            CellInfoLte cellInfoLte = new CellInfoLte();
            cellInfoLte.setCellIdentity((CellIdentityLte) cellIdentity);
            return cellInfoLte;
        } else if (cellIdentity instanceof CellIdentityCdma) {
            CellInfoCdma cellInfoCdma = new CellInfoCdma();
            cellInfoCdma.setCellIdentity((CellIdentityCdma) cellIdentity);
            return cellInfoCdma;
        } else if (cellIdentity instanceof CellIdentityWcdma) {
            CellInfoWcdma cellInfoWcdma = new CellInfoWcdma();
            cellInfoWcdma.setCellIdentity((CellIdentityWcdma) cellIdentity);
            return cellInfoWcdma;
        } else if (cellIdentity instanceof CellIdentityGsm) {
            CellInfoGsm cellInfoGsm = new CellInfoGsm();
            cellInfoGsm.setCellIdentity((CellIdentityGsm) cellIdentity);
            return cellInfoGsm;
        } else {
            Log.e("NetworkSelectSetting", "Invalid CellInfo type");
            return null;
        }
    }

    public static String getNetworkTitle(CellInfo cellInfo) {
        OperatorInfo operatorInfoFromCellInfo = getOperatorInfoFromCellInfo(cellInfo);
        if (!TextUtils.isEmpty(operatorInfoFromCellInfo.getOperatorAlphaLong())) {
            return operatorInfoFromCellInfo.getOperatorAlphaLong();
        }
        if (!TextUtils.isEmpty(operatorInfoFromCellInfo.getOperatorAlphaShort())) {
            return operatorInfoFromCellInfo.getOperatorAlphaShort();
        }
        return BidiFormatter.getInstance().unicodeWrap(operatorInfoFromCellInfo.getOperatorNumeric(), TextDirectionHeuristics.LTR);
    }

    public static OperatorInfo getOperatorInfoFromCellInfo(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoLte) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            return new OperatorInfo((String) cellInfoLte.getCellIdentity().getOperatorAlphaLong(), (String) cellInfoLte.getCellIdentity().getOperatorAlphaShort(), cellInfoLte.getCellIdentity().getMobileNetworkOperator());
        } else if (cellInfo instanceof CellInfoWcdma) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            return new OperatorInfo((String) cellInfoWcdma.getCellIdentity().getOperatorAlphaLong(), (String) cellInfoWcdma.getCellIdentity().getOperatorAlphaShort(), cellInfoWcdma.getCellIdentity().getMobileNetworkOperator());
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            return new OperatorInfo((String) cellInfoGsm.getCellIdentity().getOperatorAlphaLong(), (String) cellInfoGsm.getCellIdentity().getOperatorAlphaShort(), cellInfoGsm.getCellIdentity().getMobileNetworkOperator());
        } else if (cellInfo instanceof CellInfoCdma) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            return new OperatorInfo((String) cellInfoCdma.getCellIdentity().getOperatorAlphaLong(), (String) cellInfoCdma.getCellIdentity().getOperatorAlphaShort(), "");
        } else {
            Log.e("NetworkSelectSetting", "Invalid CellInfo type");
            return new OperatorInfo("", "", "");
        }
    }

    public static CellInfo convertOperatorInfoToCellInfo(OperatorInfo operatorInfo) {
        String str;
        String str2;
        String operatorNumeric = operatorInfo.getOperatorNumeric();
        if (operatorNumeric == null || !operatorNumeric.matches("^[0-9]{5,6}$")) {
            str2 = null;
            str = null;
        } else {
            String substring = operatorNumeric.substring(0, 3);
            str = operatorNumeric.substring(3);
            str2 = substring;
        }
        CellIdentityGsm cellIdentityGsm = new CellIdentityGsm(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, str2, str, operatorInfo.getOperatorAlphaLong(), operatorInfo.getOperatorAlphaShort());
        CellInfoGsm cellInfoGsm = new CellInfoGsm();
        cellInfoGsm.setCellIdentity(cellIdentityGsm);
        return cellInfoGsm;
    }

    public static boolean isForbidden(CellInfo cellInfo, List<String> list) {
        return list != null && list.contains(getOperatorInfoFromCellInfo(cellInfo).getOperatorNumeric());
    }

    public static String cellInfoListToString(List<CellInfo> list) {
        return (String) list.stream().map($$Lambda$CellInfoUtil$Qzf0JKtnhKRUHWJfKmx3LHFmuG0.INSTANCE).collect(Collectors.joining(", "));
    }

    public static String cellInfoToString(CellInfo cellInfo) {
        String simpleName = cellInfo.getClass().getSimpleName();
        CellIdentity cellIdentity = cellInfo.getCellIdentity();
        return String.format("{CellType = %s, isRegistered = %b, mcc = %s, mnc = %s, alphaL = %s, alphaS = %s}", new Object[]{simpleName, Boolean.valueOf(cellInfo.isRegistered()), cellIdentity.getMccString(), cellIdentity.getMncString(), cellIdentity.getOperatorAlphaLong(), cellIdentity.getOperatorAlphaShort()});
    }
}

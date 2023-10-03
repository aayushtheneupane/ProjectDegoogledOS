package com.android.dialer.logging;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.Internal;

public enum UiAction$Type implements Internal.EnumLite {
    UNKNOWN(0),
    CHANGE_TAB_TO_FAVORITE(1),
    CHANGE_TAB_TO_CALL_LOG(2),
    CHANGE_TAB_TO_CONTACTS(3),
    CHANGE_TAB_TO_VOICEMAIL(4),
    PRESS_ANDROID_BACK_BUTTON(5),
    TEXT_CHANGE_WITH_INPUT(6),
    SCROLL(7),
    CLICK_CALL_LOG_ITEM(100),
    OPEN_CALL_DETAIL(101),
    CLOSE_CALL_DETAIL_WITH_CANCEL_BUTTON(102),
    COPY_NUMBER_IN_CALL_DETAIL(103),
    EDIT_NUMBER_BEFORE_CALL_IN_CALL_DETAIL(104),
    OPEN_DIALPAD(200),
    CLOSE_DIALPAD(201),
    PRESS_CALL_BUTTON_WITHOUT_CALLING(202),
    OPEN_SEARCH(300),
    HIDE_KEYBOARD_IN_SEARCH(301),
    CLOSE_SEARCH_WITH_HIDE_BUTTON(302),
    OPEN_CALL_HISTORY(400),
    CLOSE_CALL_HISTORY_WITH_CANCEL_BUTTON(401);
    
    private final int value;

    private UiAction$Type(int i) {
        this.value = i;
    }

    public static UiAction$Type forNumber(int i) {
        if (i == 400) {
            return OPEN_CALL_HISTORY;
        }
        if (i == 401) {
            return CLOSE_CALL_HISTORY_WITH_CANCEL_BUTTON;
        }
        switch (i) {
            case 0:
                return UNKNOWN;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return CHANGE_TAB_TO_FAVORITE;
            case 2:
                return CHANGE_TAB_TO_CALL_LOG;
            case 3:
                return CHANGE_TAB_TO_CONTACTS;
            case 4:
                return CHANGE_TAB_TO_VOICEMAIL;
            case 5:
                return PRESS_ANDROID_BACK_BUTTON;
            case 6:
                return TEXT_CHANGE_WITH_INPUT;
            case 7:
                return SCROLL;
            default:
                switch (i) {
                    case 100:
                        return CLICK_CALL_LOG_ITEM;
                    case 101:
                        return OPEN_CALL_DETAIL;
                    case 102:
                        return CLOSE_CALL_DETAIL_WITH_CANCEL_BUTTON;
                    case 103:
                        return COPY_NUMBER_IN_CALL_DETAIL;
                    case 104:
                        return EDIT_NUMBER_BEFORE_CALL_IN_CALL_DETAIL;
                    default:
                        switch (i) {
                            case 200:
                                return OPEN_DIALPAD;
                            case 201:
                                return CLOSE_DIALPAD;
                            case 202:
                                return PRESS_CALL_BUTTON_WITHOUT_CALLING;
                            default:
                                switch (i) {
                                    case 300:
                                        return OPEN_SEARCH;
                                    case 301:
                                        return HIDE_KEYBOARD_IN_SEARCH;
                                    case 302:
                                        return CLOSE_SEARCH_WITH_HIDE_BUTTON;
                                    default:
                                        return null;
                                }
                        }
                }
        }
    }

    public final int getNumber() {
        return this.value;
    }
}

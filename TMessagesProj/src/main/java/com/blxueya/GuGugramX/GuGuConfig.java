package com.blxueya.GuGugramX;

public class GuGuConfig {

    public static boolean ForceAllowCopy = KTGuGuConfig.INSTANCE.getForceCopy().Bool();
    public static boolean AlwaysSaveChatOffset = KTGuGuConfig.INSTANCE.getAlwaysSaveChatOffset().Bool();

    public static final int DOUBLE_TAP_ACTION_NONE = 0;
    public static final int DOUBLE_TAP_ACTION_REACTION = 1;
    //public static final int DOUBLE_TAP_ACTION_TRANSLATE = 2;
    public static final int DOUBLE_TAP_ACTION_REPLY = 2;
    public static final int DOUBLE_TAP_ACTION_SAVE = 3;
    public static final int DOUBLE_TAP_ACTION_REPEAT = 4;
    public static final int DOUBLE_TAP_ACTION_REPEATASCOPY = 5;
    public static final int DOUBLE_TAP_ACTION_EDIT = 6;

    public static int doubleTapAction = DOUBLE_TAP_ACTION_REACTION;

}

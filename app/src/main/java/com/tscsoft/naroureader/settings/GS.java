package com.tscsoft.naroureader.settings;

public class GS {

    public static GS gs() {
        return new GS();
    }

    public void setViewerMode(ViewerSetting.ViewMode viewMode) { }

    public ViewerSetting.ViewMode getViewerMode() { return null; }

    public boolean needMinIndexUpdate() { return true; }
}

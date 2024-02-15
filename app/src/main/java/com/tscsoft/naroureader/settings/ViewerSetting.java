package com.tscsoft.naroureader.settings;


/* loaded from: classes2.dex */
public class ViewerSetting {

    /* loaded from: classes2.dex */
    public enum ViewMode {
        HorizontalScroll(0),
        VerticalScroll(1),
        VerticalPaging(2),
        HorizontalPaging(3),
        HorizontalSpread(4),
        VerticalSpread(5),
        HorizontalSpreadV(6),
        VerticalSpreadH(7);

        private final int id;

        ViewMode(int i) {
            this.id = i;
        }

    }
}
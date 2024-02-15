package com.tscsoft.naroureader.beans;

import com.tscsoft.naroureader.services.ServiceItem;
public abstract class ListBean {
    public abstract boolean isShort();
    public abstract int getAllNo();
    public abstract String getUrl();
    public abstract ServiceItem.WorkMode getWorkMode();
    public abstract int getPrevAllNo();
}

package com.tscsoft.naroureader.beans;

import com.tscsoft.naroureader.services.ServiceItem;
public abstract class ListBean {

    public abstract int getReadStartNo();
    public abstract void setReadStartNo(int i);
    public abstract boolean isShort();
    public abstract int getAllNo();
    public abstract String getUrl();
    public abstract ServiceItem.WorkMode getWorkMode();
    public abstract int getPrevAllNo();

    public abstract int getUpdateStartNo();
    public abstract void setUpdateStartNo(int i);

    public abstract String getNcode();
}

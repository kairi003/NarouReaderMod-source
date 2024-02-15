package com.tscsoft.naroureader.services;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ServiceItem implements Serializable, Cloneable {
    public enum WorkMode {
        FullCheck,
        Import,
        ForceUpdate,
        NotFoundApi,
        UpdateRanking,
        DeleteCache,
        DownloadCache,
        MakeCacheDownload,
        MakeCacheDelete
    }
}
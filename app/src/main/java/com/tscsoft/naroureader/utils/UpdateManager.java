package com.tscsoft.naroureader.utils;

import com.tscsoft.naroureader.beans.ListBean;
import com.tscsoft.naroureader.beans.NovelBean;
import com.tscsoft.naroureader.NovelHtmlObject;

public class UpdateManager {
    private NovelBean.Accessor mEpisodeAccessor;

    private boolean loadEpisode(String str, int i, NovelBean novelBean, NovelBean.Accessor accessor) {
        return false;
    }

    private void modEpisodeFetcherChapter(ListBean listBean, NovelHtmlObject.EpisodeFetcher episodeFetcher) {
        int i = listBean.getUpdateStartNo() - 1;
        if (i < 1) return;
        NovelBean novelBean = new NovelBean();
        loadEpisode(listBean.getNcode(), i, novelBean, this.mEpisodeAccessor);
        int lastChapter = novelBean.getChapter();
        episodeFetcher.setChapter(lastChapter);
    }
}

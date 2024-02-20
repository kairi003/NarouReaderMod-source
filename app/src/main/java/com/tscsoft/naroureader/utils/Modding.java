package com.tscsoft.naroureader.utils;

import android.text.TextUtils;
import android.util.Log;

import com.tscsoft.naroureader.beans.ListBean;
import com.tscsoft.naroureader.http.HttpGet;
import com.tscsoft.naroureader.presenters.ViewerActivityPresenter;
import com.tscsoft.naroureader.services.ServiceItem;
import com.tscsoft.naroureader.settings.GS;
import com.tscsoft.naroureader.settings.ViewerSetting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class Modding {
    public static String patchNovelHtml(String baseHtml, HttpGet httpGet, ListBean listBean) throws IOException, InterruptedException {
        Log.d("NarouModding", "patchNovelHtml()");
        Log.d("NarouModding", "listBean: " + listBean);
        Log.d("NarouModding", "listBean.url: " + listBean.getUrl());
        Log.d("NarouModding", "listBean.workMode: " + listBean.getWorkMode());
        Log.d("NarouModding", "listBean.prevAllNo: " + listBean.getPrevAllNo());
        if (listBean.isShort() || listBean.getAllNo() <= 100) return baseHtml;
        Document baseDoc = Jsoup.parse(baseHtml);
        Element baseIndexBox = baseDoc.selectFirst(".index_box");
        if (baseIndexBox == null) return baseHtml;

        int pageNo = 1 + Math.max(listBean.getPrevAllNo() - 1, 0) / 100;
        boolean needMinIndexUpdate = GS.gs().needMinIndexUpdate();
        Log.d("NarouModding", "pageNo: " + pageNo);
        Log.d("NarouModding", "needMinIndexUpdate: " + needMinIndexUpdate);
        if (needMinIndexUpdate && listBean.getWorkMode() == ServiceItem.WorkMode.FullCheck && pageNo > 1) {
            URL url = new URL(httpGet.getActualUrl(), "?p=" + pageNo);
            Log.d("NarouModding", "Load start from: " + url);
            String html = httpGet.get(url.toExternalForm());
            baseDoc = Jsoup.parse(html);
        }

        Element nextPager = baseDoc.selectFirst("a.novelview_pager-next[href]");
        URL url = httpGet.getActualUrl();

        while (nextPager != null) {
            url = new URL(url, nextPager.attr("href"));
            Log.d("NarouModding", "Fetch: " + url);
            Log.d("NarouModding", "Reset: " + httpGet);
            String html;
            try {
                html = httpGet.get(url.toExternalForm());
            } catch (Exception e) {
                Log.e("NarouModding", e.toString());
                throw e;
            }
            Log.d("NarouModding", "Get HTML");
            if (!httpGet.isSuccessful() || TextUtils.isEmpty(html))
                break;
            Document doc = Jsoup.parse(html);
            Element indexBox = doc.selectFirst(".index_box");
            if (indexBox == null)
                break;
            baseIndexBox.append(indexBox.html());
            nextPager = doc.selectFirst("a.novelview_pager-next[href]");
        }
        return baseDoc.outerHtml();
    }

    public static void switchViewerMode(ViewerActivityPresenter presenter) {
        GS gs = GS.gs();
        switch (gs.getViewerMode()) {
            case HorizontalScroll:
                gs.setViewerMode(ViewerSetting.ViewMode.VerticalPaging);
                break;
            case VerticalPaging:
                gs.setViewerMode(ViewerSetting.ViewMode.HorizontalScroll);
                break;
        }
        presenter.onViewerPreferenceReload();
    }

    public static String getLastIndexPageHtml(String html, HttpGet httpGet) throws IOException {
        if (TextUtils.isEmpty(html)) return html;
        Document doc = Jsoup.parse(html);
        Element lastPager = doc.selectFirst("a.novelview_pager-last[href]");
        if (lastPager == null) return html;
        URL url = new URL(httpGet.getActualUrl(), lastPager.attr("href"));
        Log.d("NarouModding", "Fetch: " + url);
        String lastHtml = httpGet.get(url.toExternalForm());
        return lastHtml;
    }
}

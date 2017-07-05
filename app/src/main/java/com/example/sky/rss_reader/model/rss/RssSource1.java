package com.example.sky.rss_reader.model.rss;

import android.content.res.Resources;
import com.example.sky.rss_reader.R;
import com.example.sky.rss_reader.application.MyApplication;
import com.example.sky.rss_reader.model.news.NewsItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// RSS源：36氪
public class RssSource1 extends BaseRssSource {
	public RssSource1() {
		Resources resources = MyApplication.getContext().getResources();
		mUrl = resources.getString(R.string.rss_source_1_url);
		mName = resources.getString(R.string.rss_source_1_name);
	}

	@Override
	public void pickItemRaws() {
		String regItem = "<item>[\\S\\s]*?</item>";
		Pattern p = Pattern.compile(regItem);
		Matcher m = p.matcher(mRaw);
		while (m.find()) {
			mItemRaws.add(m.group());
		}
	}

	@Override
	public void generateItems() {
		for (String itemRaw : mItemRaws) {
			// Guid
			String regGuid = "<guid>([\\S\\s]*?)</guid>";
			Pattern pGuid = Pattern.compile(regGuid);
			Matcher mGuid = pGuid.matcher(itemRaw);
			String guid = "";
			while (mGuid.find()) {
				guid = mGuid.group(1);
			}

			// Title
			String regTitle = "<title><!\\[CDATA\\[([\\S\\s]*?)\\]\\]>[\\S\\s]*?</title>";
			Pattern pTitle = Pattern.compile(regTitle);
			Matcher mTitle = pTitle.matcher(itemRaw);
			String title = "";
			while (mTitle.find()) {
				title = mTitle.group(1);
			}

			// Author
			String regAuthor = "<author>(.*?)</author>";
			Pattern pAuthor = Pattern.compile(regAuthor);
			Matcher mAuthor = pAuthor.matcher(itemRaw);
			String author = "";
			while (mAuthor.find()) {
				author = mAuthor.group(1);
			}

			// PublishTime
			String regPublishTime = "<pubDate><!\\[CDATA\\[(...), (..) (...) (....) (..):(..):(..) \\+0800\\]\\]>[\\S\\s]*?</pubDate>";
			Pattern pPublishTime = Pattern.compile(regPublishTime);
			Matcher mPublishTime = pPublishTime.matcher(itemRaw);
			String dayInWeek = "";
			String dayInMonth = "";
			String month = "";
			String year = "";
			String hour = "";
			String minute = "";
			String second = "";
			while (mPublishTime.find()) {
				dayInWeek = mPublishTime.group(1);
				dayInMonth = mPublishTime.group(2);
				month = mPublishTime.group(3);
				year = mPublishTime.group(4);
				hour = mPublishTime.group(5);
				minute = mPublishTime.group(6);
				second = mPublishTime.group(7);
			}
			Map<String, String> monthMap = new HashMap<>();
			monthMap.put("Jan", "01");
			monthMap.put("Feb", "02");
			monthMap.put("Mar", "03");
			monthMap.put("Apr", "04");
			monthMap.put("May", "05");
			monthMap.put("Jun", "06");
			monthMap.put("Jul", "07");
			monthMap.put("Aug", "08");
			monthMap.put("Sep", "09");
			monthMap.put("Oct", "10");
			monthMap.put("Nov", "11");
			monthMap.put("Dec", "12");
			String publishTime = monthMap.get(month) + "月" + dayInMonth + "日  " + hour + ":" + minute + ":" + second;

			// HtmlBody
			String regTemp = "<description>([\\S\\s]*?)</description>";
			Pattern pTemp = Pattern.compile(regTemp);
			Matcher mTemp = pTemp.matcher(itemRaw);
			String temp = "";
			while (mTemp.find()) {
				temp = mTemp.group(1);
			}

			String regHtmlBody = "<!\\[CDATA\\[([\\S\\s]*?)\\]\\]>";
			Pattern pHtmlBody = Pattern.compile(regHtmlBody);
			Matcher mHtmlBody = pHtmlBody.matcher(temp);
			String htmlBody = "";
			while (mHtmlBody.find()) {
				htmlBody += mHtmlBody.group(1);
			}

			// Summary
			String regSummary = ">([\\S\\s]*?)<";
			Pattern pSummary = Pattern.compile(regSummary);
			Matcher mSummary = pSummary.matcher(htmlBody);
			String summary = "";
			while (mSummary.find()) {
				summary += mSummary.group(1);
				if (summary.length() >= 100) break;
			}
			summary = summary.replaceAll("^\\s.*?", "");
			summary = summary.replaceAll("\n", " ");
			summary = summary.replaceAll("&[a-zA-Z]*?;", " ");

			htmlBody = "<html><body>\n" + "<h1>" + title + "</h1>" + htmlBody + "</body></html>\n";

			// ImageUrls
			String regImageUrl = "<img src=\"([\\S\\s]*?)\"[\\S\\s]*?/>";
			Pattern pImageUrl = Pattern.compile(regImageUrl);
			Matcher mImageUrl = pImageUrl.matcher(htmlBody);
			ArrayList<String> imageUrls = new ArrayList<>();
			while (mImageUrl.find()) {
				imageUrls.add(mImageUrl.group(1));
			}

			// NewsItem
			NewsItem newsItem = new NewsItem();
			newsItem.setRssSourceName(mName);
			newsItem.setGuid(guid);
			newsItem.setTitle(title);
			newsItem.setAuthor(author);
			newsItem.setPublishTime(publishTime);
			newsItem.setHtmlBody(htmlBody);
			newsItem.setSummary(summary);
			newsItem.setImageUrls(imageUrls);
			mItemsList.add(newsItem);
		}
	}
}


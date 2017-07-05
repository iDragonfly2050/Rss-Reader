package com.example.sky.rssreader.model.news;

import com.example.sky.rssreader.model.manager.AppManager;
import com.example.sky.rssreader.model.manager.FontsManager;

public class Style {
	public static String generateStyle() {
		String style = "<style>\n" +
				"    img {\n" +
				"        display: inline;\n" +
				"        height: auto;\n" +
				"        max-width: 100%;\n" +
				"    }\n" +
				"\n";

		for (String fontFileName : FontsManager.getInstance().getFonts()) {
			String baseName = fontFileName.substring(0, fontFileName.lastIndexOf('.'));
			style += "    @font-face {\n" +
					"        font-family: " + baseName + ";\n" +
					"        src: url(\"file:///android_asset/fonts/" + fontFileName + "\")\n" +
					"    }\n";
		}

		style += "\n" +
				"    body {\n" +
				"        background-color: #ECEFF1" +
				"    }\n" +
				"\n" +
				"    p {\n" +
				"        font-family: '" + AppManager.getInstance().getSettingsManager().getFontName() + "', 'Helvetica Neue', 'Helvetica', 'Hiragino Sans GB', 'PingHei', 'PingFang SC', 'STHeitiSC-Light', 'Microsoft YaHei', 'Lantinghei SC', 'Arial', sans-serif;\n" +
				"        font-size: " + AppManager.getInstance().getSettingsManager().getFontSize() + "em;\n" +
				"        line-height: " + AppManager.getInstance().getSettingsManager().getLineHeight() + "em;\n" +
				"    }\n" +
				"\n" +
				"    h1 {\n" +
				"        font-size: " + AppManager.getInstance().getSettingsManager().getTitleFontSize() + "em;\n" +
				"        margin-left: 0.5em;\n" +
				"        margin-right: 0.5em;\n" +
				"    }\n" +
				"\n" +
				"    h1, h2, h3, h4, h5, h6 {\n" +
				"        font-family: '" + AppManager.getInstance().getSettingsManager().getTitleFontName() + "', 'Helvetica Neue', 'Helvetica', 'Hiragino Sans GB', 'PingHei', 'PingFang SC', 'STHeitiSC-Light', 'Microsoft YaHei', 'Lantinghei SC', 'Arial', sans-serif;\n" +
				"    }\n" +
				"\n" +
				"    p, h1, h2, h3, h4, h5, h6 {\n" +
				"        margin-left: 0.5em;\n" +
				"        margin-right: 0.5em;\n" +
				"    }\n" +
				"\n" +
				"    hr {\n" +
				"        margin-left: 0.5em;\n" +
				"        margin-right: 0.5em;\n" +
				"        line-height: 1px;\n" +
				"        color: lightgray;\n" +
				"        opacity: 0.3;\n" +
				"    }\n" +
				"\n" +
				"    blockquote {\n" +
				"        margin: 1em 1em;\n" +
				"        padding: .5em 1em;\n" +
				"        border-left: 5px solid #455A64;\n" +
				"        background-color: #B0BEC5;\n" +
				"    }\n" +
				"\n" +
				"    blockquote p {\n" +
				"        margin: 0;\n" +
				"    }\n" +
				"\n" +
				"</style>\n";
		return style;
	}
}

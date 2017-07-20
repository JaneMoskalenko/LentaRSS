package com.innopolis.lentanews.parser;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.innopolis.lentanews.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class LoadTask extends AsyncTask<Void, List<News>, List<News>> {
    private static final String LOG_TAG = LoadTask.class.getSimpleName();

    private static final String ns = null;
    private static URL lentaRssUrl;
    private Context mContext;
    private ParserCallback mParserCallback;
    private List<News> mNewsList = new ArrayList<>();

    static {
        try {
            lentaRssUrl = new URL("https://lenta.ru/rss/top7");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public LoadTask(ParserCallback parserCallback) {
        mParserCallback = parserCallback;
    }

    @Override
    protected List<News> doInBackground(Void... voids) {

        String tmp = "";
        String title = null, description, link, pubDate, enclosure, category;

        try {
            XmlPullParser xpp = prepareXpp();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                title = null;
                description = null;
                link = null;
                pubDate = null;
                enclosure = null;
                category = null;

                // начало тэга
             //   if ((xpp.getEventType() == XmlPullParser.START_TAG) && (xpp.getName().equals("item"))) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item"))
                        Log.d(LOG_TAG, "START TAG: name = " + xpp.getName());

                       /* Log.d(LOG_TAG, "START_TAG: name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + ", attrCount = "
                                + xpp.getAttributeCount());*/
                    tmp = "";
                    for (int i = 0; i < xpp.getAttributeCount(); i++) {
                        tmp = tmp + xpp.getAttributeName(i) + " = "
                                + xpp.getAttributeValue(i) + ", ";
                    }
                       /* if (!TextUtils.isEmpty(tmp))
                            Log.d(LOG_TAG, "Attributes: " + tmp);
*/
                    switch (xpp.getName()) {
                        case "title":
                            title = readStringTag(xpp, "title");
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + title);
                            break;
                        case "link":
                            link = readStringTag(xpp, "link");
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + link);
                            break;
                        case "description":
                            description = readStringTag(xpp, "description");
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + description);
                            break;
                        case "pubDate":
                            pubDate = readStringTag(xpp, "pubDate");
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + pubDate);
                            break;
                        case "enclosure":
                            enclosure = getAttributeValueByName("url", xpp);
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + enclosure);
                            break;
                        case "category":
                            category = readStringTag(xpp, "category");
                            Log.d(LOG_TAG, "TAG: name = " + xpp.getName() + " " + category);
                            break;

                        default:
                            break;
                    }
                }
                //окончание тега
                /*if ((xpp.getEventType() == XmlPullParser.END_TAG) && (xpp.getName().equals("item"))) {
                    Log.d(LOG_TAG, "END TAG: name = " + xpp.getName());
                    mNewsList.add(new News(title, link, description, pubDate, enclosure, category));
                }*/
                if (title != null)
                    mNewsList.add(new News(title, link, description, pubDate, enclosure, category));
                // следующий элемент
                xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return mNewsList;
    }

    private String getAttributeValueByName(String tag, XmlPullParser parser) {
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            if (parser.getAttributeName(i).equals(tag)) {
                return parser.getAttributeValue(i);
            }
        }
        return null;
    }

    private String readStringTag(XmlPullParser parser, String tagName) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tagName);
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tagName);
        return s;
    }

    // For the tags extract their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        super.onPostExecute(newses);
        Log.d(LOG_TAG, "SIZE of news: " + String.valueOf(mNewsList.size()));
        mParserCallback.onGetNewsList(mNewsList);
    }

    private XmlPullParser prepareXpp() throws XmlPullParserException, IOException {
        // получаем фабрику
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // включаем поддержку namespace (по умолчанию выключена)
        factory.setNamespaceAware(true);
        // создаем парсер
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(getInputStream(lentaRssUrl), "UTF_8");
        return xpp;
    }

    @Nullable
    private InputStream getInputStream(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }
}

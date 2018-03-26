package com.example.ryan.rcobb200_s1630846;


//-Project Imports-//
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//-Road Works Feed-//
public class RoadWorksFeed extends AsyncTask {
    //-Private Variables-//
    private URL link;//URL Storage for XML Feed
    private ArrayList<String> items = new ArrayList();//String Storage for Returned data
    private boolean isItem = false;//Is Item inside Check Boolean

    //-AsyncTask-//
    @Override
    protected Object doInBackground(Object[] objects) {
        //-Main Try/Catch-//
        try{
            //Declare Target for Planned Road Works
            link = new URL("http://trafficscotland.org/rss/feeds/plannedroadworks.aspx");

            //-Caller for XML Parsers-//
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//New Instance of Factory
            factory.setNamespaceAware(false);//Space Aware
            XmlPullParser xpp = factory.newPullParser();//Declare XPP for Usage
            //-Declare Input Stream-//
            xpp.setInput(getXMLStream(link), "UTF_8");
            //-Event Type Declare to target <>-//
            int eType = xpp.getEventType();

            //-Main Loop Block-//
            while (eType != XmlPullParser.END_DOCUMENT) {
                if (eType == XmlPullParser.START_TAG) {
                    //-Target ITEM-//
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        isItem = true;
                        //-Target TITLE-//
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (isItem)
                            items.add(xpp.nextText()); //extract the headline
                        //-Target DESCRIPTION-//
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (isItem)
                            items.add(xpp.nextText()); //extract the link of article
                        //-Target GEORSS-//
                    }else if (xpp.getName().equalsIgnoreCase("georss:point")) {
                        if (isItem)
                            items.add(xpp.nextText()); //extract the link of article
                        //-Target PubDate-//
                    }else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                        if (isItem)
                            items.add(xpp.nextText()); //extract the link of article
                    }
                    //-Target End Tag-//
                } else if (eType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    //-Set IsItem to FALSE-//
                    isItem = false;
                }
                //-Next ITEM in XML Parser-//
                eType = xpp.next();
            }
        } catch(MalformedURLException e){
            //Print Error
            System.out.println("RoadWorksFeed.AsyncTask Error(MalformedURLException): " + e);
        } catch(XmlPullParserException e){
            //Print Error
            System.out.println("RoadWorksFeed.AsyncTask Error(XmlPullParserException): " + e);
        } catch(IOException e){
            //Print Error
            System.out.println("RoadWorksFeed.AsyncTask Error(IOException): " + e);
        } catch(Exception e){
            //Print Error
            System.out.println("RoadWorksFeed.AsyncTask Error(Exception): " + e);
        }
        //-Return ArrayList-//
        return items;
    }

    //-Get XML InputStream from URL-//
    public InputStream getXMLStream(URL link) {
        //-Main Try/Catch Control-//
        try {
            //Return XML Stream
            return link.openConnection().getInputStream();
        } catch (IOException e) {
            //Print Error
            System.out.println("RoadWorksFeed.etXMLStream Error(Exception): " + e);
            return null;
        }
    }

    //--Getter--//
    public ArrayList<String> getRoadWorksData(){return items;}
}

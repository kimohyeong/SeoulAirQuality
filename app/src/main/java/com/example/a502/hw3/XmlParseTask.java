package com.example.a502.hw3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by 502 on 2018-11-15.
 */

public class XmlParseTask extends AsyncTask<String, Void, Document> {

    Document document = null;
    TextView dateTxt;
    TextView alertTxt;
    TextView pollTxt;
    TextView levelTxt;
    TextView actionTxt;


    XmlParseTask(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5)
    {
        dateTxt=t1;
        alertTxt=t2;
        pollTxt=t3;
        levelTxt=t4;
        actionTxt=t5;
    }
    @Override
    protected Document doInBackground(String... urls) {
        URL url;
        try {
            url = new URL(urls[0]);
            DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder  = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(new InputSource(url.openStream()));
            document.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    protected void onPostExecute(Document doc) {

        NodeList nodeList = doc.getElementsByTagName("row");

        for(int i = 0; i< nodeList.getLength(); i++){

            Node node = nodeList.item(i);
            Element element = (Element) node;

            String str=element.getElementsByTagName("APPLC_DT").item(0).getTextContent();

            dateTxt.setText(str.substring(0,4)+"년 "+str.substring(4,6)+"월 "+str.substring(6,8)+"일 "+str.substring(8,10)+"시 "+str.substring(10,12)+"분"  );

            if(element.getElementsByTagName("FA_ON").item(0).getTextContent().equals("f"))
                alertTxt.setText("예보");
            else
                alertTxt.setText("경보");
            pollTxt.setText(element.getElementsByTagName("POLLUTANT").item(0).getTextContent());
            levelTxt.setText(element.getElementsByTagName("CAISTEP").item(0).getTextContent());
            actionTxt.setText(element.getElementsByTagName("ALARM_CNDT").item(0).getTextContent());

        }

        super.onPostExecute(doc);
    }



}

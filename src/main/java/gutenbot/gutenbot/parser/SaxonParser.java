package gutenbot.gutenbot.parser;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformer;

public class SaxonParser implements Parser {

	Logger logger = Logger.getLogger(SaxonParser.class);

	@Override
	public Article parse(SyndEntry articleUrl) {
		System.out.println("start");
		try {

			System.out.println("link "+articleUrl.getLink());
			URL url1 = new URL(articleUrl.getLink());
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 " + "(Windows; U; Windows NT 5.1; en-US; rv:1.8.1.1) " + "Gecko/20061204 Firefox/2.0.0.1");
			// build a JDOM tree from a SAX stream provided by tagsoup
			SAXBuilder builder = new SAXBuilder("org.ccil.cowan.tagsoup.Parser");
			System.out.println("caricamento html dal sito");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			Document doc;
			System.out.println("creazione del documento");
			doc = builder.build(reader);

			XMLOutputter outputter = new XMLOutputter();
//			try {
//				outputter.output(doc, System.out);
//			} catch (IOException e) {
//				System.err.println(e);
//			}

			System.out.println("carico il transformer");
			XSLTransformer transformer = new XSLTransformer("resources/test.xsl");
            System.out.println("doc pulito");
            outputter.output(doc, System.out);

            System.out.println("trasformo");
            Document out = transformer.transform(doc);
            outputter.output(out, System.out);

            XSLTransformer transformer2 = new XSLTransformer("resources/test2.xsl");
            System.out.println("trasformo2");
            Document out2 = transformer2.transform(out);
            outputter.output(out2, System.out);



		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Article addEnclosure(Article withoutEnclosure, String enclosure) {
		// TODO Auto-generated method stub
		return null;
	}

}

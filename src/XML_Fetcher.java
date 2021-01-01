import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class XML_Fetcher
{
    public static void main(String args[])
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler()
            {
                boolean groupId = false;
                boolean artifactId = false;
                boolean version = false;
                boolean description = false;

                //parser starts parsing a specific element inside the document
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                {
                    // System.out.println("Start Element :" + qName);

                    if(qName.equalsIgnoreCase("groupId"))
                    {
                        groupId=true;
                    }
                    if (qName.equalsIgnoreCase("artifactId"))
                    {
                        artifactId = true;
                    }
                    if (qName.equalsIgnoreCase("version"))
                    {
                        version = true;
                    }
                    if (qName.equalsIgnoreCase("description"))
                    {
                        description = true;
                    }

                }
                //parser ends parsing the specific element inside the document
                public void endElement(String uri, String localName, String qName) throws SAXException
                {
                    //System.out.println("End Element:" + qName);
                }
                //reads the text value of the currently parsed element
                public void characters(char ch[], int start, int length) throws SAXException
                {
                    if (groupId)
                    {
                        System.out.println("GroupID : " + new String(ch, start, length));
                        groupId = false;
                    }
                    if (artifactId)
                    {
                        System.out.println("Artifact ID : " + new String(ch, start, length));
                        artifactId = false;
                    }
                    if (version)
                    {
                        System.out.println("Version : " + new String(ch, start, length));
                        version = false;
                    }
                    if (description)
                    {
                        System.out.println("Description : " + new String(ch, start, length));
                        description = false;
                        System.out.println();
                    }

                }
            };
            saxParser.parse("src/archetype-catalog.xml", handler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


//  https://mavensync.zkoss.org/maven2/archetype-catalog.xml
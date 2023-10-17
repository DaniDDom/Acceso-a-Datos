import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Esta clase representa la clase principal.
 *
 * @author Dani Dom
 */
public class Main {

    /**
     * Método principal
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse("contactos.xml", new MiGestorDeContactosXML());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
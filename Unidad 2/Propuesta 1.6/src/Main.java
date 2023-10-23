import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

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
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File("contactos.xml");
            MiGestorDeContactosXML handler = new MiGestorDeContactosXML();
            saxParser.parse(file, handler);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

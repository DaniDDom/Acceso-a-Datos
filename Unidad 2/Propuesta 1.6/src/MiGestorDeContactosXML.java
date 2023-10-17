import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Esta clase representa un gestor de contactos
 *
 * @author Dani Dom
 */
public class MiGestorDeContactosXML extends DefaultHandler {

    /**
     * Enumeramos los distintos tipos de teléfono
     */
    enum TiposTelefono {NINGUNO, CASA, TRABAJO, MOVIL}

    ;

    /**
     * Contenido de la etiqueta
     */
    private String contenido;

    /**
     * Los datos del contacto.
     */
    private String datosContacto;

    /**
     * Teléfono de contacto
     */
    private String telefono;

    /**
     * Tipo de teléfono del que tenemos la información.
     */
    private TiposTelefono tipoTelefonoGuardado;

    private boolean analizandoTelefonos;


    // Etiqueta de apertura encontrada
    public void startElement(String uri, String nombreLocal, String qName, Attributes atributos) throws SAXException {
        if (qName.equals("telefonos")) {
            analizandoTelefonos = true;
            tipoTelefonoGuardado = TiposTelefono.NINGUNO;
            telefono = "";
        }
    }

    // Contenido de la etiqueta, normalmente CDATA
    public void characters(char ch[], int inicio, int longitud) throws SAXException {
        contenido = new String(ch, inicio, longitud);
    }

    //Etiqueta de cierre
    public void endElement(String uri, String nombreLocal, String qName) throws SAXException {
        if (!qName.isBlank()) {
            if (qName.equals("nombre"))
                datosContacto = contenido;
            else if (qName.equals("apellido"))
                datosContacto = " " + contenido;
            else if (analizandoTelefonos && qName.equals("casa") &&
                    tipoTelefonoGuardado == TiposTelefono.NINGUNO) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.CASA;
            } else if (analizandoTelefonos && qName.equals("trabajo") &&
                    tipoTelefonoGuardado == TiposTelefono.CASA || tipoTelefonoGuardado == TiposTelefono.NINGUNO) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.TRABAJO;
            } else if (analizandoTelefonos && qName.equals("movil")) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.MOVIL;
            } else if (qName.equals("telefonos")) {
                if (!telefono.isBlank()) {
                    datosContacto += "- Teléfono: " + telefono;
                    switch (tipoTelefonoGuardado) {
                        case CASA -> datosContacto += "(Casa)";
                        case TRABAJO -> datosContacto += "(Trabajo)";
                        case MOVIL -> datosContacto += " (Móvil) ";
                    }
                    analizandoTelefonos = false;
                }
            } else if (qName.equals("contacto")) {
                System.out.println(datosContacto);
            }
        }
    }
}

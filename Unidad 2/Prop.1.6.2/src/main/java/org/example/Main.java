package org.example;



import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;


public class Main {
    public static void convertirContactoAXML(Contacto contacto, String archivoXML) {
        try {
            File file = new File(archivoXML);
            JAXBContext jaxbContext = JAXBContext.newInstance(Contacto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(contacto, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Contacto contacto = new Contacto("Nombre", "Apellidos", "Telefdono","ejemplo@example.com","descripcion");

        convertirContactoAXML(contacto, "contacto.xml");
    }


}
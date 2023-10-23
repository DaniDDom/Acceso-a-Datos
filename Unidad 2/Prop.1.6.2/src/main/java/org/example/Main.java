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
  // create JAXB context and instantiate marshaller
          var context = JAXBContext.newInstance(BookStore.class);
          var m = context.createMarshaller();
          m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

          // Write to System.out
          m.marshal(bookstore, System.out);

          // Write to File
          m.marshal(bookstore, new File(BOOKSTORE_XML));
      }

      private static void JavaReadJaxbEx () throws JAXBException, FileNotFoundException {
          BookStore bookStore = null;
          JAXBContext context = JAXBContext.newInstance(BookStore.class);

              bookStore = (BookStore) context.createUnmarshaller().unmarshal(new FileReader("src/main/resources/bookstore.xml"));


    public static void main(String[] args) {
        Contacto contacto = new Contacto("Nombre", "Apellidos", "Telefdono","ejemplo@example.com","descripcion");

        convertirContactoAXML(contacto, "contacto.xml");
    }


}
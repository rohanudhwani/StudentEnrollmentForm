package CMS;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class Main extends GUI{

    public static String viewXML(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();

            Document document = builder.parse(new File("Contacts.xml"));


            document.getDocumentElement().normalize();

            Element Contacts = document.getDocumentElement();

            NodeList contactList = document.getElementsByTagName("Contact");

//        try {
//            FileWriter myWriter = new FileWriter("Contacts.txt");
//            myWriter.write(a.displayName()+a.displayNumber());
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        }
//        catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
            viewContacts+="<html>";
            for(int i=0; i< contactList.getLength(); i++) {
                Node contact = contactList.item(i);
                if (contact.getNodeType() == Node.ELEMENT_NODE) {
                    Element contactElement = (Element) contact;
                    viewContacts+="Contact Name: " + contactElement.getAttribute("Name")+"<br>";

                    NodeList contactDetails = contact.getChildNodes();
                    Node number = contactDetails.item(1);
                    if (number.getNodeType() == Node.ELEMENT_NODE) {
                        Element numberElement = (Element) number;
                        viewContacts+="Contact Number: " + numberElement.getAttribute("value")+"<br>";
                    }

                }
            }
            viewContacts+="</html>";
            return viewContacts;

        }


        catch (ParserConfigurationException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        catch (SAXException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveXML(String nameFieldText, String numberFieldText){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();

            Document document = builder.parse(new File("Contacts.xml"));


            document.getDocumentElement().normalize();

            Element Contacts = document.getDocumentElement();

            NodeList contactList = document.getElementsByTagName("Contact");


            Element contact = document.createElement("Contact");
            contact.setAttribute("Name", nameFieldText);


            Element number = document.createElement("Number");
            number.setAttribute("value", numberFieldText);


            contact.appendChild(number);

            Contacts.appendChild(contact);


            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult("Contacts.xml");
            transformer.transform(source, result);

        }


        catch (ParserConfigurationException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        catch (SAXException e){
            throw new RuntimeException(e);
        }
        catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        catch (TransformerException e){
            throw new RuntimeException(e);
        }
    }

    public static String searchContacts(String contactName) {
        String allContacts = viewXML();
        String contactNumber="";
        if (allContacts.contains(contactName)) {
            contactNumber = allContacts.substring((allContacts.indexOf(contactName)+contactName.length()+8+9+3), ((allContacts.indexOf(contactName)+contactName.length()+8)+19+3));
        }
        else {
            contactNumber = "Contact not found";
        }

        return contactNumber;
    }


    public static void deleteContacts(String contactName, String contactNumber){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();

            Document document = builder.parse(new File("Contacts.xml"));


            document.getDocumentElement().normalize();

            Element Contacts = document.getDocumentElement();

            NodeList contactList = document.getElementsByTagName("Contact");

            int i;
            forloop:
            for(i=0; i< contactList.getLength(); i++) {
                Node contact = contactList.item(i);
                if (contact.getNodeType() == Node.ELEMENT_NODE) {
                    Element contactElement = (Element) contact;

                    if(contactElement.getAttribute("Name").equals(contactName)){
                        break forloop;
                    }

                }
            }
            Node contact = contactList.item(i);
            document.getDocumentElement().removeChild(contact);


            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult("Contacts.xml");
            transformer.transform(source, result);

        }


        catch (ParserConfigurationException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        catch (SAXException e){
            throw new RuntimeException(e);
        }
        catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        catch (TransformerException e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String args[]){



        GUI gui = new GUI();


    }


}
//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.

package com.pav2py.excel.utlilty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.XMLConstants;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.UnmarshalException;
//import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The XmlBindingUtility Class is responsible for Marshalling,Unmarshalling XML
 * files and also Validating the XML against its schemas.
 *
 * @author aak3cob
 */
public class XmlBindingUtility {

    /**
     * The method is responsible to un-marshal the XML File passed to it. It
     * returns Null if any exception occurs.
     *
     * @param <T> Generics has been used in the method.
     * @param T Object of the XML Model
     * @param unmarshallXmlFile Full path and name of the XML file to be parsed.
     * @return Object of the XML Model after unmarshalling.
     *
     * @author aak3cob
//     */
//    public static <T> T unmarshallXml(Class<T> T, String unmarshallXmlFile) {
//
//        File tempXmlFile = new File(unmarshallXmlFile);
//        T xmlModelObject;
//
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(T);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            xmlModelObject = (T) unmarshaller.unmarshal(tempXmlFile);
//
//        } catch (JAXBException exception) {
//            MasterLogger.getInstance().logToFile(exception);
//            xmlModelObject = null;
//        }
//        return xmlModelObject;
//    }
//
//    public static <T> T unmarshallXml(Class<T> T, InputStream unmarshallXmlFile) throws SAXParseException {
//
//        T xmlModelObject;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(T);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            //unmarshallXmlFile.reset();
//            try (Reader reader = new InputStreamReader(unmarshallXmlFile, "ISO-8859-1")) {
//                xmlModelObject = (T) unmarshaller.unmarshal(reader);
//            }
//        } catch (JAXBException | IOException exception) {
//            //Handled internally and the exception is not logged  
//            MasterLogger.getInstance().logToFile(exception);
//            if (exception instanceof UnmarshalException) {
//                Throwable throwable = exception.getCause();
//                if (null != throwable && (throwable instanceof SAXParseException)) {
//                    SAXParseException sAXParseException = (SAXParseException) throwable;
//                    throw sAXParseException;
//                }
//            }
//            xmlModelObject = null;
//        }
//
//        return xmlModelObject;
//    }
//
//    /**
//     * The method is responsible to marshal the XML object into an Xml File
//     * passed to it. It returns false if any exception occurs.
//     *
//     * @param <T> Generics has been used in the method.
//     * @param xmlObject Object of the Xml to be Marshalled.
//     * @param marshallXmlFile Absolute name of the XML file to be written.
//     * @return True if it is successfully Marshalled.
//     */
//    public static <T> boolean marshallXml(T xmlObject, String marshallXmlFile) {
//        File tempXmlFile = new File(marshallXmlFile);
//        boolean marshallXml = true;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(xmlObject.getClass());
//            Marshaller marshaller = jaxbContext.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            //marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3schools.com");
//            marshaller.marshal(xmlObject, tempXmlFile);
//        } catch (JAXBException exception) {
//            MasterLogger.getInstance().logToFile(exception);
//            marshallXml = false;
//        }
//        return marshallXml;
//    }
//
//    /**
//     * The method is responsible to validate the XML File against the schema.
//     *
//     * @param validateXmlFile Full path and name of the XML file
//     * @param schemaFile Full path and name of the XML schema.
//     * @return Returns true if the validation succeeds.
//     *
//     * @author aak3cob
//     */
//    public static boolean validateXmlAgainstSchema(String validateXmlFile, String schemaFile) {
//
//        boolean validXml = true;
//        try {
//            //Adding Schema Factory
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = schemaFactory.newSchema(new File(schemaFile));
//
//            //Validation
//            Validator validator = schema.newValidator();
//
//            validator.validate(new StreamSource(new File(validateXmlFile)));
//        } catch (SAXException | IOException exception) {
//            MasterLogger.getInstance().logToFile(exception);
//            //Handled internally and the exception is not logged
//            validXml = false;
//        }
//
//        return validXml;
//    }
//
//    public static boolean validateXmlAgainstSchema(InputStream validateXmlStream, String schemaFile) {
//
//        boolean validXml = true;
//        try {
//
//            //Adding Schema Factory
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = schemaFactory.newSchema(new File(schemaFile));
//            //Validation
//            Validator validator = schema.newValidator();
//            validateXmlStream.reset();
//            validator.validate(new StreamSource(validateXmlStream));
//
//        } catch (SAXException | IOException | NullPointerException exception) {
//            MasterLogger.getInstance().logToFile(exception);
//            //Handled internally and the exception is not logged
//            validXml = false;
//        }
//        return validXml;
//    }
//
//    public static InputStream correctCfitabFile(String xmlFile) throws FileNotFoundException, IOException {
//        InputStream inputStream, returnStream;
//        inputStream = new FileInputStream(xmlFile);
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
//            String readLine;
//            while ((readLine = bufferedReader.readLine()) != null) {
//                stringBuilder.append(readLine.replaceAll("&", "&amp;"));
//                stringBuilder.append("\n");
//            }
//        }
//        String fileContents = stringBuilder.toString().trim();
//        if (!fileContents.startsWith("</root>")) {  //Handling empty files
//            if (!fileContents.startsWith("<root>")) {
//                fileContents = "<root>" + fileContents;
//            }
//            if (!fileContents.endsWith("</root>")) {
//                fileContents = fileContents + "</root>";
//            }
//        }
//        returnStream = IOUtils.toInputStream(fileContents);
//        inputStream.close();
//        return returnStream;
//    }
//
//    public static InputStream replaceEscapeCharactersXml(String xmlFile) throws FileNotFoundException, IOException {
//        InputStream inputStream, returnStream;
//        inputStream = new FileInputStream(xmlFile);
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
//            String readLine;
//            while ((readLine = bufferedReader.readLine()) != null) {
//                stringBuilder.append(readLine.replaceAll("&", "&amp;"));
//                stringBuilder.append("\n");
//            }
//        }
//        returnStream = IOUtils.toInputStream(stringBuilder.toString());
//        inputStream.close();
//
//        return returnStream;
//    }

    
}

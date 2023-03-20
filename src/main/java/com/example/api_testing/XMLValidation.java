package com.example.api_testing;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidation {
    public static void main(String[] args) {
        System.out.println("GET_employees.xml validates against GET_employees.xsd? "+validateXMLSchema("output\\GET_employees.xsd", "output\\GET_employees.xml"));
        System.out.println("POST_employees.xml validates against POST_employees.xsd? "+validateXMLSchema("output\\POST_employees.xsd", "output\\POST_employees.xml"));
        System.out.println("PUT_employees.xml validates against PUT_employees.xsd? "+validateXMLSchema("output\\PUT_employees.xsd", "output\\PUT_employees.xml"));

    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

}

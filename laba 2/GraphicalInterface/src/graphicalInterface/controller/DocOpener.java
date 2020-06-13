package graphicalInterface.controller;

import graphicalInterface.model.FullName;
import graphicalInterface.model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocOpener {
    private static FullName fullName;
    private static List<Student> studentList;
    private static String group;
    private static String course;
    private static String totalWorks;
    private static String finishedWorks;
    private static String computerLanguage;

    public static List<Student> openDoc(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        XMLHandler handler;

        studentList = new ArrayList<>();
        handler = new XMLHandler();
        parser.parse(file, handler);

        return studentList;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String URI, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("snp")) {
                fullName = new FullName(attributes.getValue("surname"), attributes.getValue("name"), attributes.getValue("patronymic"));
            }

            if (qName.equals("group")) {
                group = attributes.getValue("groupNumber");
            }

            if (qName.equals("course")) {
                course = attributes.getValue("courseNumber");
            }

            if (qName.equals("totalWorks")) {

                totalWorks = attributes.getValue("totalWorksNumber");
            }

            if (qName.equals("finishedWorks")) {
                finishedWorks = attributes.getValue("finishedWorksCounter");
            }

            if (qName.equals("computerLanguage")) {
                computerLanguage = attributes.getValue("title");
            }
        }

        @Override
        public void endElement(String URI, String localName, String qName) throws SAXException {
            if(qName.equals("student")) {
                studentList.add(new Student(fullName, group, Integer.parseInt(course), Integer.parseInt(totalWorks), Integer.parseInt(finishedWorks), computerLanguage));
            }
        }
    }

}

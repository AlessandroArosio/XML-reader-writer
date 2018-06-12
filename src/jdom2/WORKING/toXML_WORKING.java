package jdom2.WORKING;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class toXML_WORKING {

    public static void main(String[] args) {
        //writeXML();
        //readXML();
        //appendToXML();
        modifyValue();
    }

    private static void writeXML() {
        try {
            Document doc = new Document();

            Element theRoot = new Element("Persons");
            doc.setRootElement(theRoot);

            Element person = new Element("person");
            Element name = new Element("name");

            name.addContent(new Text("Alessandro"));

            person.addContent(name);

            theRoot.addContent(person);

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

            xmlOutputter.output(doc, new FileOutputStream(new File("myxml5.xml")));

            System.out.println("Wrote to file");

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readXML(){
        SAXBuilder builder = new SAXBuilder();

        try {
            Document readDoc = builder.build(new File("myxml4.xml"));

            System.out.println("Root: " + readDoc.getRootElement());

            System.out.println("Show: " +
                    readDoc.getRootElement().getChild("show").getChildText("name"));

            System.out.println("Show ID: " +
                    readDoc.getRootElement().getChild("show").getAttributeValue("show_id"));

            Element root = readDoc.getRootElement();

            for (Element curEl : root.getChildren("show")){
                System.out.println("Show name: " +
                        curEl.getChildText("name"));

                System.out.println("Show ID: " +
                        curEl.getChild("name").getAttributeValue("show_id"));
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendToXML(){
        File file = new File("myxml5.xml");
        SAXBuilder builder = new SAXBuilder();
        try{
            Document document = builder.build(file);
            Element root = document.getRootElement();
            Element person = new Element("person");
            Element name = new Element("name");

            name.addContent(new Text("Bob"));

            person.addContent(name);
            person.addContent(new Element("age").setText("30"));
//            System.out.println("the content is: " + person.getChildText("name"));
            root.addContent(person);
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

            xmlOutputter.output(document, new FileOutputStream(new File("myxml5.xml"), false));

            System.out.println("Wrote to file");

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    private static void modifyValue(){
        File file = new File("myxml5.xml");
        SAXBuilder builder = new SAXBuilder();
        try{
            Document document = builder.build(file);
            Element root = document.getRootElement();

            Element name = root.getChild("name");


            // Changes all the occurrences of Fabio under <name></name> to Mike.
            for (Element el : root.getChildren()){
                System.out.println(el.toString());
                if ("Fabio".equals(el.getChildText("name"))){
                    el.getChild("name").setText("Mike");
                    System.out.println("Name found and changed");
                }
            }

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

            xmlOutputter.output(document, new FileOutputStream(new File("myxml5.xml"), false));

            System.out.println("Wrote to file");

        } catch (IOException e){
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}


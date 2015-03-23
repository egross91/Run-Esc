package org.escaperun.game.serialization;

import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SaveManager {

    public static void save(String filename, Saveable saveable) throws Exception {
        File file = new File(filename);
        if (!file.exists()) file.createNewFile();

        Document xmlDom = new DocumentImpl();
        Element root = xmlDom.createElement("Root");
        xmlDom.appendChild(root);
        saveable.save(xmlDom, root);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        DOMSource source = new DOMSource(xmlDom);
        StreamResult result = new StreamResult(file);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public static <T extends Saveable> T load(String filename, T saveable) throws Exception {
        File file = new File(filename);
        if (!file.exists()) return null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document dom = builder.parse(file);
        dom.getDocumentElement().normalize();

        return (T) saveable.load(dom.getDocumentElement());
    }
}

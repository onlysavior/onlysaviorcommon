package onlysaviorcommon.util;

import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-7
 * Time: 下午7:35
 * To change this template use File | Settings | File Templates.
 */
public class DomUtil {
    public static org.dom4j.Element convertElement(Element element) {
        Document doc;

        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new IllegalArgumentException("Failed to create dom4j document", e);
        }

        Element clonedElement = (Element)doc.importNode(element, true);
        doc.adoptNode(clonedElement);

        DOMReader reader = new DOMReader();
        org.dom4j.Document dom4jDoc = reader.read(doc);

        return dom4jDoc.getRootElement();
    }

    public static void convertElement(Element element, ContentHandler contentHandler) throws SAXException {
        SAXWriter writer = new SAXWriter(contentHandler);

        if (contentHandler instanceof ErrorHandler) {
            writer.setErrorHandler((ErrorHandler) contentHandler);
        }

        if (contentHandler instanceof LexicalHandler) {
            writer.setLexicalHandler((LexicalHandler) contentHandler);
        }

        writer.write(convertElement(element));
    }

    public static interface ElementSelector {
        boolean accept(Element element);
    }

    public static ElementSelector sameNs(Element element) {
        String namespaceURI = Assert.assertNotNull(element,"element").getNamespaceURI();
        return or(ns(namespaceURI), ns(null));
    }

    public static ElementSelector or(final ElementSelector... selectors) {
        return new ElementSelector() {
            @Override
            public boolean accept(Element element) {
                for (ElementSelector s : selectors) {
                    if(s.accept(element)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static ElementSelector and(final ElementSelector... selectors) {
        return new ElementSelector() {
            @Override
            public boolean accept(Element element) {
                for(ElementSelector s : selectors) {
                    if(!s.accept(element)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static ElementSelector any() {
        return new ElementSelector() {
            @Override
            public boolean accept(Element element) {
                return true;
            }
        };
    }

    public static ElementSelector none() {
        return new ElementSelector() {
            @Override
            public boolean accept(Element element) {
                return false;
            }
        };
    }

    public static ElementSelector name(String name) {
        final String nameAfterTrim = Assert.assertNotNull(StringUtil.trimToNull(name), "elementName");

        return new ElementSelector() {
            @Override
            public boolean accept(Element element) {
                String toCompare = element.getNamespaceURI() == null ?
                        element.getNodeName() : element.getLocalName();
                return StringUtil.isEquals(toCompare, nameAfterTrim);
            }
        };
    }

    public static ElementSelector ns(String nsUri) {
        final String trimmedNsUri = StringUtil.trimToNull(nsUri);

        return new ElementSelector() {
            public boolean accept(Element element) {
                return StringUtil.isEquals(element.getNamespaceURI(), trimmedNsUri);
            }

            @Override
            public String toString() {
                return "ns[" + (trimmedNsUri == null ? "no namespace" : trimmedNsUri) + "]";
            }
        };
    }
}

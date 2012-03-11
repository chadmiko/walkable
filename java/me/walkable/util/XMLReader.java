/**
 * 
 */
package me.walkable.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Christopher Butera
 *
 */
public class XMLReader {

	private Element eElement;

	public XMLReader(Element eElement){
		this.eElement = eElement;
	}

	public String getTagValue(String sTag) {
		NodeList nlList = eElement.getElementsByTagName(sTag);
		if (nlList != null && nlList.item(0) != null && nlList.item(0).hasChildNodes()){
			nlList = nlList.item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);
			if (nValue != null){
				return nValue.getNodeValue();
			}
		}
		return null;

	}

	public String getTagValueNS(String namespace, String sTag) {
		NodeList nlList = eElement.getElementsByTagNameNS(namespace, sTag);
		if (nlList != null && nlList.item(0) != null && nlList.item(0).hasChildNodes()){
			nlList = nlList.item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);
			if (nValue != null){
				return nValue.getNodeValue();
			}
		}
		return null;

	}

	
	public String getAttributeValue(String sTag, String attribute) {
		Element child = (Element) eElement.getElementsByTagName(sTag).item(0);
		if (child != null){
			return child.getAttribute(attribute);
		}
		else{
			return null;
		}
	}

}

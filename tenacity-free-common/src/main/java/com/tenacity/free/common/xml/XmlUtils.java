package com.tenacity.free.common.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tenacity.free.common.io.IOUtils;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: tenacity-free-common
 * @PackageName: com.tenacity.free.common.xml
 * @ClassName: XmlUtils.java
 * @author: free.zhang
 * @Date: 2018年1月21日 上午10:25:30
 * @Description: xml工具类
 */
public class XmlUtils {

	private final XPath path;
	private final Document doc;

	private XmlUtils(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = getDocumentBuilderFactory();
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.parse(inputSource);
		path = getXPathFactory().newXPath();
	}

	private static XmlUtils create(InputSource inputSource) {
		try {
			return new XmlUtils(inputSource);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static XmlUtils of(InputStream is) {
		InputSource inputSource = new InputSource(is);
		return create(inputSource);
	}

	public static XmlUtils of(String xmlStr) {
		StringReader sr = new StringReader(xmlStr.trim());
		InputSource inputSource = new InputSource(sr);
		XmlUtils xmlHelper = create(inputSource);
		IOUtils.closeQuietly(sr);
		return xmlHelper;
	}

	private Object evalXPath(String expression, Object item, QName returnType) {
		item = null == item ? doc : item;
		try {
			return path.evaluate(expression, item, returnType);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param expression
	 *            路径
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:31:42
	 * @Description: 获取String
	 * @return: String
	 */
	public String getString(String expression) {
		return (String) evalXPath(expression, null, XPathConstants.STRING);
	}

	/**
	 * @param expression
	 *            路径
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:31:58
	 * @Description: 获取Boolean
	 * @return: Boolean
	 */
	public Boolean getBoolean(String expression) {
		return (Boolean) evalXPath(expression, null, XPathConstants.BOOLEAN);
	}

	/**
	 * @param expression
	 *            路径
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:32:11
	 * @Description: 获取Number
	 * @return: Number
	 */
	public Number getNumber(String expression) {
		return (Number) evalXPath(expression, null, XPathConstants.NUMBER);
	}

	/**
	 * @param expression
	 *            路径
	 * @return
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:32:26
	 * @Description: 获取某个节点
	 */
	public Node getNode(String expression) {
		return (Node) evalXPath(expression, null, XPathConstants.NODE);
	}

	/**
	 * @param expression
	 *            路径
	 * @return NodeList
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:32:39
	 * @Description:获取子节点
	 */
	public NodeList getNodeList(String expression) {
		return (NodeList) evalXPath(expression, null, XPathConstants.NODESET);
	}

	/**
	 * @param node
	 *            节点
	 * @param expression
	 *            相对于node的路径
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:32:50
	 * @Description: 获取String
	 * @return: String
	 */
	public String getString(Object node, String expression) {
		return (String) evalXPath(expression, node, XPathConstants.STRING);
	}

	/**
	 * @param node
	 *            节点
	 * @param expression
	 *            相对于node的路径
	 * @return String
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:05
	 * @Description:获取
	 */
	public Boolean getBoolean(Object node, String expression) {
		return (Boolean) evalXPath(expression, node, XPathConstants.BOOLEAN);
	}

	/**
	 * @param node
	 *            节点
	 * @param expression
	 *            相对于node的路径
	 * @return {Number}
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:15
	 * @Description: 获取
	 */
	public Number getNumber(Object node, String expression) {
		return (Number) evalXPath(expression, node, XPathConstants.NUMBER);
	}

	/**
	 * @param node
	 *            节点
	 * @param expression
	 *            路径
	 * @return {Node}
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:26
	 * @Description: 获取某个节点
	 */
	public Node getNode(Object node, String expression) {
		return (Node) evalXPath(expression, node, XPathConstants.NODE);
	}

	/**
	 * @param node
	 *            节点
	 * @param expression
	 *            相对于node的路径
	 * @return NodeList
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:36
	 * @Description: 获取子节点
	 */
	public NodeList getNodeList(Object node, String expression) {
		return (NodeList) evalXPath(expression, node, XPathConstants.NODESET);
	}

	/**
	 * @return map集合
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:45
	 * @Description: 针对没有嵌套节点的简单处理
	 */
	public Map<String, String> toMap() {
		Element root = doc.getDocumentElement();
		Map<String, String> params = new HashMap<String, String>();

		// 将节点封装成map形式
		NodeList list = root.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node instanceof Element) {
				params.put(node.getNodeName(), node.getTextContent());
			}
		}
		return params;
	}

	private static DocumentBuilderFactory getDocumentBuilderFactory() {
		return XmlHelperHolder.documentBuilderFactory;
	}

	private static XPathFactory getXPathFactory() {
		return XmlHelperHolder.xPathFactory;
	}

	/**
	 * @ProjectName: tenacity-free-common
	 * @PackageName: com.tenacity.free.common.xml
	 * @ClassName: XmlUtils.java
	 * @author: free.zhang
	 * @Date: 2018年1月21日 上午10:33:58
	 * @Description: 内部类单例
	 */
	private static class XmlHelperHolder {
		private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		private static XPathFactory xPathFactory = XPathFactory.newInstance();
	}
}

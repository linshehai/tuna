package com.hy.tuna.xml;

import com.hy.tuna.utils.ReflectUtil;
import com.hy.tuna.utils.StringUtils;
import com.hy.tuna.xml.elements.*;
import com.hy.tuna.xml.elements.config.Config;
import com.hy.tuna.xml.elements.config.DataSourceNode;
import com.hy.tuna.xml.elements.config.ResourceNode;
import com.hy.tuna.xml.elements.mapper.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TunaXmlParser extends DefaultHandler {

    private Stack<Node> stack;

    private static Map<String,Class<? extends Node>> NODE_MAPS;

    static {
        NODE_MAPS = new HashMap<>();
        NODE_MAPS.put("mapper", MapperNode.class);
        NODE_MAPS.put("select", SelectStatement.class);
        NODE_MAPS.put("delete", DeleteStatement.class);
        NODE_MAPS.put("update", UpdateStatement.class);
        NODE_MAPS.put("insert", InsertStatement.class);
        NODE_MAPS.put("sql", SqlElement.class);
        NODE_MAPS.put("if", IfNode.class);
        NODE_MAPS.put("when", WhenNode.class);
        NODE_MAPS.put("otherwise", OtherwiseNode.class);
        NODE_MAPS.put("choose", ChooseNode.class);
        NODE_MAPS.put("where", WhereNode.class);
        NODE_MAPS.put("config", Config.class);
        NODE_MAPS.put("resource", ResourceNode.class);
        NODE_MAPS.put("resultMap", ResultMapNode.class);
        NODE_MAPS.put("result", ResultMappingNode.class);
        NODE_MAPS.put("dataSource", DataSourceNode.class);
    }

    public TunaXmlParser(){
        stack = new Stack<>();
    }

    public void parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = parserFactory.newSAXParser();
        saxParser.parse(inputStream,this);
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Class<? extends Node> nodeClass =  NODE_MAPS.get(qName);
        if(nodeClass!=null) {
            try {
                Node node = nodeClass.newInstance();
                Class superClazz = nodeClass.getSuperclass();
                while (superClazz!=null){
                    Field[] fields = superClazz.getDeclaredFields();
                    for(Field field:fields){
                        String name = field.getName();
                        if(field.getType().isPrimitive()||field.getType().isAssignableFrom(String.class)){
                            String value = attributes.getValue(name);
                            if(!StringUtils.isEmpty(value))
                                ReflectUtil.setValue(field,node,value);
                        }

                    }
                    superClazz = superClazz.getSuperclass();
                }
                Field[] fields = nodeClass.getDeclaredFields();
                for(Field field:fields){
                    field.setAccessible(true);
                    String name = field.getName();
                    String value = attributes.getValue(name);
                    if(!StringUtils.isEmpty(value)){
                        ReflectUtil.setValue(field,node,value);
                    }

                }
                stack.push(node);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Class<? extends Node> nodeClass =  NODE_MAPS.get(qName);
        if(nodeClass!=null&&stack.size()>1) {
            Node node = stack.pop();
            Node parent = stack.peek();
            node.setNamespace(parent.getNamespace());
            parent.addNode(node);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String line = new String(ch,start,length).trim();
        if(StringUtils.hasText(line)){
            if(stack.size()>0) {
                Node currentNode = stack.peek();
                currentNode.addNode(new TextElement(line));
            }
        }

    }

    public Node getRoot(){
        return stack.pop();
    }
}

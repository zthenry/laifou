package com.cyou.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * Jackson相关工具类
 */
public class JacksonUtil {

  private static ObjectMapper jsonMapper;
  private static XmlMapper xmlMapper;

  public static synchronized ObjectMapper getJsonMapper() {
    if(jsonMapper == null) {
      jsonMapper = new ObjectMapper();
      AnnotationIntrospector primary = new JaxbAnnotationIntrospector(jsonMapper.getTypeFactory());
      AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
      AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(primary, secondary);
      jsonMapper.setAnnotationIntrospector(pair);
      jsonMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    }
    return jsonMapper;
  }

  public static synchronized XmlMapper getXmlMapper() {
    if(xmlMapper == null) {
      xmlMapper = new XmlMapper();
      AnnotationIntrospector primary = new JaxbAnnotationIntrospector(jsonMapper.getTypeFactory());
      AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
      AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(primary, secondary);
      xmlMapper.setAnnotationIntrospector(pair);
    }
    return xmlMapper;
  }
}

/*
 * #%L
 * restdoc-doclet
 * %%
 * Copyright (C) 2012 IG Group
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.iggroup.oss.restdoclet.doclet.type.builder;

import static com.iggroup.oss.restdoclet.doclet.util.AnnotationUtils.elementValue;
import static com.iggroup.oss.restdoclet.doclet.util.AnnotationUtils.isAnnotated;
import static com.iggroup.oss.restdoclet.doclet.util.AnnotationUtils.parseValueAnnotation;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iggroup.oss.restdoclet.doclet.type.Controller;
import com.iggroup.oss.restdoclet.doclet.type.Method;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ClassDoc;

/**
 * This class populates a Controller class from JavaDoc types.
 */
public class ControllerBuilder extends BaseTypeBuilder {

   /**
    * Logger
    */
   private static final Logger LOG = Logger.getLogger(ControllerBuilder.class);

   /**
    * Populate a Controller
    * 
    * @param controller the controller to populate
    * @param type controller type
    * @param javadoc controller javadoc
    * @param methods controller methods
    * @return populated controller
    */
   public Controller build(Controller controller, final String type,
                           final String javadoc,
                           final Collection<Method> methods) {
      super.build(controller, type, type, javadoc);
      controller.setMethods(methods);

      return controller;
   }


   /**
    * Populate a controller
    * 
    * @param controller the controller to populate
    * @param classDoc class javadoc
    * @return populated controller
    */
   public Controller build(Controller controller, final ClassDoc classDoc) {
      super.build(controller, classDoc);
      initMethods(controller, classDoc);

      return controller;
   }

   /**
    * Initialises the methods in this controller annotated with
    * <code>@RequestMapping</code> in the source class.
    * 
    * @param classDoc the controller's Java documentation object.
    */
   private void initMethods(Controller controller, final ClassDoc classDoc) {

      LOG.info(controller.getName());

      // Controller-level $RequestMapping annotation?
      String baseUri = "";
      AnnotationValue urlAnnotation =
         elementValue(classDoc, RequestMapping.class, "value");
      if (urlAnnotation != null) {
         String[] methodUris = parseValueAnnotation(urlAnnotation);
         // Assume just one...
         baseUri = methodUris[0];
      }

      // Initialise methods
      ArrayList<Method> methods = new ArrayList<Method>();
      for (int i = 0; classDoc.methods(false) != null
         && i < classDoc.methods(false).length; i++) {
         if (isAnnotated(classDoc.methods(false)[i], RequestMapping.class)) {
            methods.add(new MethodBuilder().build(new Method(),
               classDoc.methods(false)[i], baseUri));
         }
      }

      controller.setMethods(methods);

      if (methods.size() == 0) {
         LOG.warn("No methods found with @RequestMapping tag");
      }
   }

}

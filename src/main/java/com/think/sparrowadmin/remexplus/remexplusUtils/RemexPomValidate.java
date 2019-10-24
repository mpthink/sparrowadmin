/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.remexplusUtils;

import org.dom4j.util.XMLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.InputStream;

/**
 * @author map6
 */
public class RemexPomValidate {

    private static final Logger LOG = LoggerFactory.getLogger(RemexPomValidate.class);
    private static final String remex_submit_v1 = "remexplus/remex-submit_v1_10.xsd";
    private static final String remex_submit_v2 = "remexplus/remex-submit_v1_20.xsd";

    public static int validateRemexPomWithFilePath(String xmlFilePath) {
        Source source = new StreamSource(xmlFilePath);
        return validateRemexPom(source);
    }

    public static int validateRemexPomWithInputStream(InputStream inputStream) {
        Source source = new StreamSource(inputStream);
        return  validateRemexPom(source);
    }

    public static int validateRemexPom(Source source) {

        try {
            Resource resourceV1 = new ClassPathResource(remex_submit_v1);
            Resource resourceV2 = new ClassPathResource(remex_submit_v2);
            File v1File = resourceV1.getFile();
            File v2File = resourceV2.getFile();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schemav1 = schemaFactory.newSchema(v1File);
            Schema schemav2 = schemaFactory.newSchema(v2File);
            Validator validator1 = schemav1.newValidator();
            Validator validator2 = schemav2.newValidator();
            XMLErrorHandler errorHandler1 = new XMLErrorHandler();
            XMLErrorHandler errorHandler2 = new XMLErrorHandler();
            validator1.setErrorHandler(errorHandler1);
            validator2.setErrorHandler(errorHandler2);

            validator1.validate(source);
            validator2.validate(source);
            if(errorHandler1.getErrors().hasContent() && errorHandler2.getErrors().hasContent()){
                LOG.error("file format is wrong");
                return 0;
            }else if(!errorHandler1.getErrors().hasContent()){
                LOG.info("file format is match with remex-submit_v1_10.xsd!");
                return 1;
            }else if(!errorHandler2.getErrors().hasContent()){
                LOG.info("file format is match with remex-submit_v1_20.xsd!");
                return 2;
            }else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("exception happened, return 0...");
            return 0;
        }
    }

    /**
     * @args command line arguments
     */
    public static void main(String[] args) {
        String xmlFilePath = "C:\\Users\\map6\\Desktop\\remex-submitter\\remex-submit-cache-751.xml";
        //String xmlFilePath = "C:\\Users\\map6\\Desktop\\remex-submitter\\test.xml";
        String fileContent = FileHandleUtils.readFileByChars(new File(xmlFilePath));
        if(fileContent.contains("v1_10")){
            System.out.println("submit/config/v1_10");
        }

        int i = validateRemexPomWithFilePath(xmlFilePath);
        System.out.println(i);


    }


}

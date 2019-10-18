/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.remexplusUtils;

import com.dell.compellent.test.remex.message.v4_00.framework.TestPayload;
import com.dell.compellent.test.remex.message.v4_00.global.*;
import com.dell.compellent.test.remex.message.v4_00.remoteexecution.JobSubmitRequest;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.think.sparrowadmin.common.config.Config;
import com.think.sparrowadmin.remexplus.config.v1_10.JobType;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.*;

/**
 * @author map6
 */
public class BuildJobSubmitRequest {

    private static final Logger LOG = LoggerFactory.getLogger(BuildJobSubmitRequest.class);
    private static final String pomTemplatePath = "remexplus/pomTemplate.txt";


    public static JobSubmitRequest buildJobLaunchRequest(RpTask task, JobType job, String poolId){
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setTimestamp(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        header.setUserName(task.getOwnerId());
        jobSubmitRequest.setRequestHeader(header);
        ResourceSpecificationType resourceSpecification =  new ResourceSpecificationType();
        //TODO
        RigSpecificationType rigSpec = new RigSpecificationType();
        rigSpec.setGenerated(false); //TODO double check this value when select pool
        resourceSpecification.setRigSpecification(rigSpec);
        PoolSpecificationType poolSpec = new PoolSpecificationType();
        poolSpec.setPoolId(poolId);
        poolSpec.setName(poolId);//TODO check name
        poolSpec.setAllRigs(false);
        resourceSpecification.setPoolSpecification(poolSpec);
        ExecutorSpecificationType executorSpec = new ExecutorSpecificationType();
        executorSpec.setMemoryHostMB(2048);
        resourceSpecification.setExecutorSpecification(executorSpec);
        jobSubmitRequest.setResourceSpecification(resourceSpecification);
        JobSpecificationType jobSpecification = new JobSpecificationType();
        jobSpecification.setOwnerUserName(task.getOwnerId());
        jobSpecification.setJobType(JobTypeEnum.COMPELLENT_TEST_FRAMEWORK_JAVA_8);
        jobSpecification.setContinueOnFailure(false);
        jobSpecification.setJobPriority(0);//TODO
        jobSpecification.setResubmit(false);
        jobSpecification.setFinalizeReservation(false);
        jobSpecification.setExecuteImmediate(false);
        TestPayload testPayload = buildTestPayLoad(task, job.getClazz(), poolId);
        jobSpecification.setPayload(convertToXML(testPayload).getBytes());
        jobSubmitRequest.setJobSpecification(jobSpecification);

        return jobSubmitRequest;
    }

    public static List<HttpMessageConverter<?>> buildConverts(){
        List< HttpMessageConverter<?>> converters = new ArrayList< HttpMessageConverter<?>>();
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.dell.compellent.test.remex.message.v4_00.executor:com.dell.compellent.test.remex.message.v4_00.job:com.dell.compellent.test.remex.message.v4_00.executormanager:com.dell.compellent.test.remex.message.v4_00.resourcemanager:com.dell.compellent.test.remex.message.v4_00.framework:com.dell.compellent.test.remex.message.v4_00.remoteexecution");
        marshallingHttpMessageConverter.setMarshaller(marshaller);
        marshallingHttpMessageConverter.setUnmarshaller(marshaller);
        marshallingHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_XML));
        converters.add(marshallingHttpMessageConverter);
        return converters;

    }

    public static Object buildRemexSubmitObject(String remexPomPath) throws JAXBException {
        String remexPomAbsolutePath = Config.UPLOAD_FOLDER + remexPomPath.replace("/upload/","");
        String fileContent = FileHandleUtils.readFileByChars(new File(remexPomAbsolutePath));
        JAXBContext jaxbContext = null;
        Object object;
        if(fileContent.contains("remex/submit/config/v1_20")){
            jaxbContext = JAXBContext.newInstance(com.think.sparrowadmin.remexplus.config.v1_20.RemexSubmit.class);
        }else if(fileContent.contains("remex/submit/config/v1_10")){
            jaxbContext = JAXBContext.newInstance(com.think.sparrowadmin.remexplus.config.v1_10.RemexSubmit.class);
        }else{
            LOG.error("Not supported remex pom file format!!");
            return null;
        }
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        object = unmarshaller.unmarshal(new File(remexPomAbsolutePath));
        return object;
    }

    private static TestPayload buildTestPayLoad(RpTask task, String mainClass, String poolName){
        TestPayload testPayload = new TestPayload();
        String pomFileContent = buildPomFileContents(task.getSubmitPom());
        String testConfig = buildTestConfig(mainClass, poolName, task.getOwnerEmail());
        testPayload.setMainClass(mainClass);
        testPayload.setPomFileContents(pomFileContent.getBytes());
        testPayload.setTestConfig(testConfig.getBytes());
        return testPayload;
    }


    private static String buildPomFileContents(String pomSubmitFilePath){
        String pomTemplateContent = FileHandleUtils.readFileFromClassPath(pomTemplatePath);
        String pomFileAbsolutePath = Config.UPLOAD_FOLDER + pomSubmitFilePath.replace("/upload/","");
        String taskSubmitFileContent = FileHandleUtils.readFileByChars(new File(pomFileAbsolutePath));
        int begin = taskSubmitFileContent.indexOf("<dependencies>");
        int end = taskSubmitFileContent.indexOf("</dependencies>");
        String dependencies = FileHandleUtils.replaceSpecialStr(taskSubmitFileContent.substring(begin + 14, end));
        pomTemplateContent.replace("<replacement>",dependencies);
        return pomTemplateContent;
    }

    private static String buildTestConfig(String mainClass, String poolName, String email){
        String templateConfig = "#<classname>[1] <-> <classnamewithpackage>\n" +
                "#<date>\n" +
                "com.compellent.systemtest.libs.tasks.CompellentTask\\ <->\\ _taskconfiguration_emailContacts=<email>,\n" +
                "com.compellent.systemtest.libs.tasks.CompellentDisplayableTask\\ <->\\ <poolName>";
        String dateString = new Date().toString();
        String[] strings = mainClass.split("\\.");
        String className = strings[strings.length-1];
        templateConfig.replace("<classname>", className);
        templateConfig.replace("<classnamewithpackage>", mainClass);
        templateConfig.replace("<date>", dateString);
        templateConfig.replace("<poolName>", poolName);
        templateConfig.replace("<email>", email);
        return templateConfig;
    }

    private static String convertToXML(Object object){
        JAXBContext context = null;
        String result = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(object,writer);
            result =writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }


}

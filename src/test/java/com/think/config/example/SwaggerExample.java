package com.think.config.example;

public class SwaggerExample {
//    @Value("${swagger2.basePackage:com.spingcloud.serviceconsumer.controller}")
//    private String swagger2BasePackage;
//    @Value("${swagger2.title:系统API文档}")
//    private String swagger2Title;
//    @Value("${swagger2.api.version:2.0}")
//    private String apiVersion;
//
//
//    @Bean
//    public Docket createRestApi() {
//
//        //添加query参数start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
////        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("query").required(true).build();
//        pars.add(tokenPar.build());
//        //添加query参数end
//
//
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select().apis(RequestHandlerSelectors.basePackage(swagger2BasePackage))
//                .paths(PathSelectors.any())
//                .build()
////                .globalOperationParameters(pars)
//                //添加验证
////                .securitySchemes(securitySchemes())
////                .securityContexts(securityContexts())
//                //添加验证
//                ;
//
//
//
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(swagger2Title)
//                .version(apiVersion)
//                .build();
//    }
//
//
//
//    private List<ApiKey> securitySchemes() {
//        List<ApiKey> apiKeyList= new ArrayList();
//        apiKeyList.add(new ApiKey("token", "令牌", "header"));
//        return apiKeyList;
//    }
//
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts=new ArrayList<>();
//        securityContexts.add(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                        .build());
//        return securityContexts;
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences=new ArrayList<>();
//        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
//        return securityReferences;
//    }
}

# SpringBoot 结合 国际化
###此项目中解决了在给页面返回字符乱码的问题
```java
class I18nConfig(){
//-------------------------解决RestController返回乱码问题 start-----------------
    /**
     *  解决字符串返回乱码问题，指定UTF-8返回字符集
     **/
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        Charset utf8 = Charset.forName("utf8");
        return new StringHttpMessageConverter(utf8);
    }

    /**
     *  解决DTO对象返回，反序列化问题
     **/
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new  ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     *  配置Jackson
     **/
    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //解决中文乱码
        converters.add(responseBodyConverter());
        //返回JSON序列化问题
        converters.add(messageConverter());
    }
    //-------------------------解决RestController返回乱码问题 end-----------------
}
```
以上的Bean注入IoC容器中即可解决问题。  
###SpringBoot的国际化使用
1.下面Bean的配置，设置了请求的URL中要带有关键字，决定调取的是哪种语言的值
```java
class I18nConfig(){
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：locale
        //lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        //这里自定义请求参数为 "lang"
        lci.setParamName("lang");
        return lci;
    }
}
```
2.国际化文本在/resources/i18n/ResourceBundle'messages'/messages*.properties
###请求示例
ip:端口/？lang=zh_CN 得到即是中文  
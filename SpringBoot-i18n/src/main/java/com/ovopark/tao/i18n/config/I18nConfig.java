package com.ovopark.tao.i18n.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * @author Xiu_Er
 * @Date 2020-10-04 下午1:11
 */
@Configuration
public class I18nConfig extends WebMvcConfigurationSupport {

    /**
     * session区域解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //这里通过设置China.US可以进行中英文转化
        //resolver.setDefaultLocale(Locale.US);
        resolver.setDefaultLocale(Locale.CHINA);

        return resolver;
    }


    /**
     * cookie区域解析器
     * @return
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver slr = new CookieLocaleResolver();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.CHINA);
//        slr.setCookieMaxAge(3600);//设置cookie有效期.
//        return slr;
//    }

//    @Bean
//    public LocaleResolver localeResolver() {
//        FixedLocaleResolver slr = new FixedLocaleResolver ();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }

    /**
     * 采用默认的LocaleChangeInterceptor作为拦截器来指定切换国际化语言的参数名
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：locale
        //lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

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

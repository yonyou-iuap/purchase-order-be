package com.yonyou;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * 说明: SwaggerAPI加载配置类，需要扫描的package可通过Docket.apis()方法管理
 * @date 2019-3-7 10:31:19
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yonyou"))//扫描的单包路径
//                .apis(basePackage("com.yonyou,com.yonyou.iuap.zet"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口 ")
                .description("SINGLETABLE-POPUPEDIT REST API")
                .version("0.0.1-SNAPSHOT")
                .build();
    }

    /**
     * 标准不支持多路径扫描，
     * 这里实现新的扫描包路径规则,支持多路径扫描匹配以逗号“,”隔开
     *
     * @param basePackage
     * @return
     */
    private static Predicate<RequestHandler> basePackage(final String basePackage) {
    return new Predicate<RequestHandler>() {
        @Override
        public boolean apply(RequestHandler input) {
        Function<Class<?>, Boolean> functions = new Function<Class<?>, Boolean>() {
        @Override
        public Boolean apply(Class<?> input) {
        for (String strPackage : basePackage.split(",")) {
        if (input.getPackage().getName().startsWith(strPackage)) {
        return true;
        }
        }
        return false;
        }
        };
        return Optional.fromNullable(input.declaringClass()).transform(functions).or(true);
        }
        };
        }

        }

// ${watermark}

${javaSpecs.wrapMultiComment(copyright)}

package ${organType}.${organName}.${projectName}.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

<#if javaSpecs.isConf(extOpts, "swagger.ui", "bootstrapSwagger2")>
//import io.swagger.annotations.Api;
<#elseif javaSpecs.isConf(extOpts, "swagger.ui", "officialOas")>
import io.swagger.annotations.ApiOperation;
</#if>
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@link SwaggerAutoConfiguration}
 *
 * @author ${author}
 * @version ${version}
 * @Date ${now}
 * @since ${since}
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

    @Bean
    public Docket springfoxSwaggerDocket() {
<#if javaSpecs.isConf(extOpts, "swagger.ui", "bootstrapSwagger2")>
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("${organType}.${organName}.${projectName}"))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
                .paths(PathSelectors.any()).build();
<#elseif javaSpecs.isConf(extOpts, "swagger.ui", "officialOas")>
        return new Docket(DocumentationType.OAS_30).apiInfo(createApiInfo()).select().apis(withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build();
</#if>
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title("${projectName?uncap_first} API文档").description("${projectDescription}")
                .license("").contact(new Contact("${author}", "#", "${author}"))
                .version("${version}").build();
    }

}
package com.mrxu.cloud.common.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/7
 */
public class Swagger2Config {
    protected ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MRxu.Cloud提供API接口")
                .description("MRxu.Cloud提供API接口")
                .termsOfServiceUrl("http://localhost:10000/")
                .contact("徐徐荔荔")
                .version("1.0")
                .build();
    }
}

package com.mrxu.cloud.common.util;

import com.mrxu.cloud.common.enums.ClientOSEnum;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/21
 */
public class RequestHeaderUtils {

    /**
     * 从header中获取平台信息
     *  ios android holo windows
     * @param request
     * @return
     */
    public static String findPlatformByHeader(HttpServletRequest request){
        String cliInfo = request.getHeader("CLI_INFO");
        if(StringUtils.isEmpty(cliInfo)){
            return null;
        }
        String[] cli_info = cliInfo.split("&");
        String support = cli_info[1].toLowerCase();
        if(!ClientOSEnum.contains(support)){  // 校验设备支持参数是否符合
            return null;
        }
        return support;
    }

}

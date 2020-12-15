package com.detabes.jwtweb.scan;

import com.detabes.jwtweb.bean.InterceptorBean;
import com.detabes.jwtweb.config.WebApiConfig;
import com.detabes.jwtweb.holder.ApplicationContextHolder;
import com.detabes.jwtweb.interceptor.WebApiInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author tn
 * @ClassName EnableScan
 * @description 自动扫描
 * @date 2020-09-27 10:17
 */
@ConditionalOnWebApplication
@Import({WebApiConfig.class, ApplicationContextHolder.class, WebApiInterceptor.class, InterceptorBean.class})
@ComponentScan("com.databstech.utils.jwt.web.**")
public class EnableAutoScanConfiguration {
}

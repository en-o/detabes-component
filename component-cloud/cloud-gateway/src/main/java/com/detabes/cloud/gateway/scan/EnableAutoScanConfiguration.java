package com.detabes.cloud.gateway.scan;

import com.detabes.cloud.gateway.config.ServerConfig;
import com.detabes.cloud.gateway.exception.core.ExceptionHandlerConfiguration;
import com.detabes.cloud.gateway.factory.DocNameRoutePredicateFactory;
import com.detabes.cloud.gateway.sawagger.config.SwaggerResourceConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * @author tn
 * @ClassName EnableScan
 * @description 自动扫描
 * @date 2020-09-27 10:17
 */
@ConditionalOnWebApplication
@Import({SwaggerResourceConfig.class,
        DocNameRoutePredicateFactory.class,
        ServerConfig.class})
@ComponentScan( basePackages = "com.databstech.doc.gateway.**",
        excludeFilters= {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = ExceptionHandlerConfiguration.class)}
                )
public class EnableAutoScanConfiguration {
}

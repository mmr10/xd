package eu.els.sie.xmlfirst.batch.config;

import eu.els.sie.xmlfirst.ecm.core.domain.config.EcmCoreProperties;
import eu.els.sie.xmlfirst.ecm.core.domain.config.SpringProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by mmarzougui on 13/02/2017.
 */
@Configuration
@ComponentScan(basePackages = { "eu.els.sie.xmlfirst.ecm.core", "eu.els.sie.proxy.referential" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE)})
@EnableConfigurationProperties({ SpringProperties.class, EcmCoreProperties.class })
@EnableMongoRepositories(basePackages = "eu.els.sie.xmlfirst.ecm.core.domain.repository.mongo")
@EntityScan(basePackages = { "eu.els.sie.xmlfirst.ecm.core.domain.models",
		"eu.els.sie.proxy.referential.shared.model" })
public class EcmServicesConfiguration {


}

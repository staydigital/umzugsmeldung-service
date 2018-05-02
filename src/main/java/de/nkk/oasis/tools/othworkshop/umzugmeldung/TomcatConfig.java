package de.nkk.oasis.tools.othworkshop.umzugmeldung;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatConfig implements EmbeddedServletContainerCustomizer {

	@Override public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(10024);
	}
}

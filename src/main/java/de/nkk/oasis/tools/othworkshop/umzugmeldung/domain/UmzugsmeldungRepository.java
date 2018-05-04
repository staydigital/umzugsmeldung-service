package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.ApplicationContextProvider;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.process.UmzugMeldenOTHDefinitions;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "umzugsmeldung", path = "umzugsmeldung") public interface UmzugsmeldungRepository
		extends JpaRepository<Umzugsmeldung, Long> {



	default Umzugsmeldung save(Umzugsmeldung var1) {
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		UmzugMeldenOTHDefinitions umzugMeldenOTHDefinitions = ctx.getBean(UmzugMeldenOTHDefinitions.class);
		Umzugsmeldung umzugsmeldung = this.saveAndFlush(var1);
		umzugMeldenOTHDefinitions.startUmzugMeldenProcess(umzugsmeldung);

		return this.getOne(umzugsmeldung.getId());
	}

}

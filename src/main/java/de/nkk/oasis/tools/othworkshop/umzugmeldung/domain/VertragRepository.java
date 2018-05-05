package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.ApplicationContextProvider;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.process.UmzugMeldenOTHDefinitions;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vertrag", path="vertrag")
public interface VertragRepository extends JpaRepository<Vertrag, Long> {

	default Vertrag save(Vertrag var1) {
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		UmzugMeldenOTHDefinitions umzugMeldenOTHDefinitions = ctx.getBean(UmzugMeldenOTHDefinitions.class);
		umzugMeldenOTHDefinitions.completeTask(var1);
		var1.setActivityId(null);
		Vertrag vertrag = this.saveAndFlush(var1);
		return this.getOne(vertrag.getId());
	}
}

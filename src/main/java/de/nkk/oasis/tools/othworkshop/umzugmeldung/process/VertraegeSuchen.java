package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VN;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VNRepository;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class VertraegeSuchen implements JavaDelegate {
	private static final Logger LOG = LoggerFactory.getLogger(VertraegeSuchen.class);

	@Autowired
	private VNRepository vnRepository;

	@Override public void execute(DelegateExecution delegateExecution) throws Exception {
		VN vn = vnRepository.getOne(UmzugMeldenOTHDefinitions.getVnId(delegateExecution));

		List<Long> vertraegeIds = new ArrayList<>();
		for (Vertrag vertrag : vn.getVertraege()) {
			vertraegeIds.add(vertrag.getId());
		}

		UmzugMeldenOTHDefinitions.setVertraegeIds(delegateExecution, vertraegeIds);
		LOG.info("In VertraegeSuchen");

	}
}

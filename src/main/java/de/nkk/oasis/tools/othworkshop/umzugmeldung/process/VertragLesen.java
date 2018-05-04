package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VertragRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class VertragLesen implements JavaDelegate {

	@Autowired
	private VertragRepository vertragRepository;

	@Autowired
	private VertragSparte vertragSparte;

	@Override public void execute(DelegateExecution delegateExecution) throws Exception {
		Long vertragId = VertragHandlingOTHDefinitions.getVertragId(delegateExecution);
		Vertrag vertrag = vertragRepository.getOne(vertragId);
		VertragHandlingOTHDefinitions.setSparte(delegateExecution, vertragSparte.getSparte(vertrag));
	}
}

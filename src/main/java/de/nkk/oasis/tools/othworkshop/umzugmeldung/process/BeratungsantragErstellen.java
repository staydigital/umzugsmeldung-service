package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VertragRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class BeratungsantragErstellen implements JavaDelegate {

	@Autowired
	private VertragRepository vertragRepository;

	@Override public void execute(DelegateExecution delegateExecution) throws Exception {

		Vertrag vertrag = vertragRepository.getOne(VertragHandlingOTHDefinitions.getVertragId(delegateExecution));

		vertrag.setActivityId(delegateExecution.getActivityInstanceId());
		vertragRepository.saveAndFlush(vertrag);

	}
}

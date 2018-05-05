package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Beratungsprotokoll;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.BeratungsprotokollRepository;
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

	@Autowired
	private BeratungsprotokollRepository beratungsprotokollRepository;

	@Autowired
	private VertragSparte vertragSparte;

	@Override public void execute(DelegateExecution delegateExecution) throws Exception {

		Vertrag vertrag = vertragRepository.getOne(VertragHandlingOTHDefinitions.getVertragId(delegateExecution));

		Beratungsprotokoll beratungsprotokoll = new Beratungsprotokoll();
		beratungsprotokoll.setVertragId(vertrag.getId());
		beratungsprotokoll.setVsnr(vertrag.getVsnr());
		beratungsprotokoll.setSparte(vertragSparte.getSparte(vertrag));

		vertrag.setActivityId(delegateExecution.getProcessInstanceId());
		vertragRepository.saveAndFlush(vertrag);

		beratungsprotokollRepository.saveAndFlush(beratungsprotokoll);
	}
}

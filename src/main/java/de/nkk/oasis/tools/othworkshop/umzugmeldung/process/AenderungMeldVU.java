package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.AdresseRepository;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VN;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VNRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class AenderungMeldVU implements JavaDelegate {

	@Autowired
	private VNRepository vnRepository;

	@Autowired
	private AdresseRepository adresseRepository;

	@Override public void execute(DelegateExecution delegateExecution) throws Exception {
		VN vn = vnRepository.getOne(VertragHandlingOTHDefinitions.getVnId(delegateExecution));
		Adresse adresse = adresseRepository.getOne(VertragHandlingOTHDefinitions.getAdresseId(delegateExecution));
		vn.setAdresse(adresse);
		vnRepository.saveAndFlush(vn);
	}
}

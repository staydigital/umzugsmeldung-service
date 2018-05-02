package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "adressen", path="adresse")
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}

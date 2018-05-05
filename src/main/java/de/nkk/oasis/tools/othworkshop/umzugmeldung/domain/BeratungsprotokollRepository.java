package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "beratungsprotokoll", path = "beratungsprotokoll")
public interface BeratungsprotokollRepository extends JpaRepository<Beratungsprotokoll, Long> {

    long deleteByVertragId(Long vertragId);
}

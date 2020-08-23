package pl.sda.JobOfferApplication.jobOffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.JobOfferApplication.user.entity.UserEntity;


@Repository
public interface JobOfferRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByOfferTitle(String offerTitle);
}
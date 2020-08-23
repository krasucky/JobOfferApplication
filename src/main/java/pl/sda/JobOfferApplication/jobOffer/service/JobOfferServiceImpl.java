package pl.sda.JobOfferApplication.jobOffer.service;

import org.springframework.stereotype.Service;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private JobOfferRepository jobOfferRepository;


    @Override
    public void createJobOffer(JobOfferInput jobOfferInput) throws JobOfferException {

    }

    @Override
    public List<UserOutput> getAllJobOffer() {
        return jobOfferRepository.findAll()
                .stream()
                .map(JobOfferEntity::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutput getJobOfferById(Long id) throws JobOfferException {
        return getJobOfferEntity(id).toOutput();
    }

    private JobOfferEntity getJobOfferEntity(Long id) throws JobOfferException {
        final Optional<JobOfferEntity> optionalJobOfferEntity = jobOfferRepository.findById(id);
        if (!optionalJobOfferEntity.isPresent()) {
            throw new JobOfferException(NO_JOB_OFFER_FOUND_OR_GIVEN_ID);
        }
        return optionalJobOfferEntity.get();
    }

    @Override
    public void deleteJobOfferById(Long id) throws JobOfferException {
        getJobOfferById(id);
        jobOfferRepository.delete(getJobOfferEntity(id));
    }
    private void validateJobOfferInput(JobOfferInput jobOfferInput) throws JobOfferException {
        if (jobOfferRepository.existsByOfferTitle(jobOfferRepository.getJobOfferTitle())) {
            throw new JobOfferException(THERE_IS_ALREADY_AN_JOB_OFFER_WITH_THIS_OFFER_TITLE);
        }
}

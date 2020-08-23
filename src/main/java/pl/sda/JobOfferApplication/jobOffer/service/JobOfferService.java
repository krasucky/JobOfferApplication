package pl.sda.JobOfferApplication.jobOffer.service;

import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;

public interface JobOfferService {
    void createJobOffer(JobOfferInput jobOfferInput) throws JobOfferException;
    List<UserOutput> getAllJobOffer();
    UserOutput getJobOfferById(Long id) throws JobOfferException;
    void deleteJobOfferById(Long id) throws JobOfferException;
}

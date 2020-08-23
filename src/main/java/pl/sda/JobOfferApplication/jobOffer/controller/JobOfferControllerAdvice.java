package pl.sda.JobOfferApplication.jobOffer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobOfferControllerAdvice {

    @ExceptionHandler(JobOfferException.class)
    public ResponseEntity<String> handJobOfferException(JobOfferException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

//package pl.sda.JobOfferApplication.jobOffer.entity;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.User;
//import pl.sda.JobOfferApplication.jobOffer.model.Category;
//import pl.sda.JobOfferApplication.jobOffer.model.JobOfferOutput;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "JOBOFFERS")
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@Getter
//public class JobOfferEntity {
//
//    private Category jobOfferCategory;
//    private LocalDate startDate = LocalDate.now();
//    public LocalDate endDate = startDate.plusDays(14);
//    private User user;
//
//    public JobOfferEntity(Category jobOfferCategory, LocalDate startDate, LocalDate endDate, User user) {
//        this.jobOfferCategory = jobOfferCategory;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.user = user;
//    }
//
//    public JobOfferOutput toOutput() {
//        return new JobOfferOutput(jobOfferCategory, startDate, endDate, user);
//    }
//}

package dev.tojoos.helpnow.bootstrap;
import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.services.FundraiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final FundraiseService fundraiseService;

    public DataLoader(FundraiseService fundraiseService) {
        this.fundraiseService = fundraiseService;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
        log.debug("Loading fake data");
    }

    private void loadData() {
        List<Fundraise> fundraises = new ArrayList<>();

        Fundraise fundraise1 = Fundraise.builder() //add imageURL
                .name("School supplies for underprivileged children in Warsaw")
                .description("This fundraiser aims to provide school supplies for underprivileged children in " +
                        "the local community. The supplies will be distributed to schools in the area to help " +
                        "children in need. We need your help to reach our goal of 5000 PLN. Your donation will " +
                        "make a big difference in the lives of these children.")
                .requiredAmount(5000L)
                .raisedAmount(4500L)
                .startingDate(LocalDate.of(2022, Month.OCTOBER, 1))
                .endingDate(LocalDate.of(2022, Month.NOVEMBER, 1))
                .build();

        Fundraise fundraise2 = Fundraise.builder()
                .name("Medical equipment for injured immigrants - Bielsko Biała")
                .description("This fundraiser aims to purchase medical equipment for injured immigrants in need. " +
                        "The hospitals are located in remote areas and have limited access to the necessary " +
                        "equipment to provide adequate care. We need your help to reach our goal of 15000 PLN. " +
                        "Your donation will help improve the quality of medical help.")
                .requiredAmount(15000L)
                .raisedAmount(8000L)
                .startingDate(LocalDate.of(2022, Month.DECEMBER, 1))
                .endingDate(LocalDate.of(2023, Month.JANUARY, 1))
                .build();

        Fundraise fundraise3 = Fundraise.builder()
                .name("Food and shelter for newcomers in Rzeszów")
                .description("This fundraiser aims to provide food and shelter for homeless newcomers in the " +
                        "local area. The funds raised will be used to support organizations that provide " +
                        "these services to those in need. We need your help to reach our goal of 50000 PLN. " +
                        "Your donation will make a direct impact on the lives of homeless immigrants in your community.")
                .requiredAmount(50000L)
                .raisedAmount(45000L)
                .startingDate(LocalDate.of(2023, Month.MARCH, 1))
                .endingDate(LocalDate.of(2023, Month.MAY, 1))
                .build();

        Fundraise fundraise4 = Fundraise.builder()
                .name("Scholarships for low-income students (5-15 years old)")
                .description("This fundraiser aims to provide scholarships for low-income students to help " +
                        "them afford higher education. The scholarships will be awarded to students who " +
                        "have demonstrated academic excellence and financial need. We need your help to " +
                        "reach our goal of 30000 PLN. Your donation will help bright, deserving students " +
                        "achieve their educational goals.")
                .requiredAmount(30000L)
                .raisedAmount(5000L)
                .startingDate(LocalDate.of(2023, Month.JULY,1 ))
                .endingDate(LocalDate.of(2023, Month.AUGUST, 1))
                .build();

        Fundraise fundraise5 = Fundraise.builder()
                .name("Clean water and sanitation for communities in need")
                .description("This fundraiser aims to provide clean water and sanitation for communities " +
                        "in need. The funds raised will be used to support projects that provide clean " +
                        "water and sanitation facilities to communities that lack access to these basic " +
                        "necessities. We need your help to reach our goal of 7000 PLN. Your donation will " +
                        "help improve the health and quality of life for people in these communities.")
                .requiredAmount(7000L)
                .raisedAmount(100L)
                .startingDate(LocalDate.of(2023, Month.SEPTEMBER, 1))
                .endingDate(LocalDate.of(2023, Month.OCTOBER, 1))
                .build();

        fundraises.add(fundraise1);
        fundraises.add(fundraise2);
        fundraises.add(fundraise3);
        fundraises.add(fundraise4);
        fundraises.add(fundraise5);

        fundraises.forEach(fundraiseService::add);
    }
}
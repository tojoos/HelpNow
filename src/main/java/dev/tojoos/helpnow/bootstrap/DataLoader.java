package dev.tojoos.helpnow.bootstrap;
import dev.tojoos.helpnow.model.Announcement;
import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.model.Organization;
import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final FundraiseService fundraiseService;
    private final OrganizationService organizationService;
    private final StatisticsService statisticsService;
    private final AnnouncementService announcementService;
    private final UserService userService;

    public DataLoader(FundraiseService fundraiseService, OrganizationService organizationService, StatisticsService statisticsService, AnnouncementService announcementService, UserService userService) {
        this.fundraiseService = fundraiseService;
        this.organizationService = organizationService;
        this.statisticsService = statisticsService;
        this.announcementService = announcementService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
        loadStatistics();
        log.debug("Loading fake data");
    }

    private void loadStatistics() {
        statisticsService.recalculateStatistics();
    }

    private void loadData() {
        List<Organization> organizations = new ArrayList<>();

        Organization organization1 = Organization.builder()
                .name("Polish Aid: East&Beyond")
                .imageUrl("https://images.unsplash.com/photo-1488521787991-ed7bbaae773c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80")
                .description("Polish Aid is a non-profit organization that provides assistance to immigrants" +
                        " in Poland. We offer a range of services, including language classes, cultural" +
                        " orientation programs, and job placement assistance. Our goal is to help immigrants" +
                        " integrate into Polish society and thrive in their new home.")
                .createdFundraises(new ArrayList<>())
                .build();

        Organization organization2 = Organization.builder()
                .name("Helping Hands")
                .imageUrl("https://images.unsplash.com/photo-1469571486292-0ba58a3f068b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80")
                .description("Helping Hands is a charity that focuses on providing support for children in need." +
                        " We offer educational programs, extracurricular activities, and mentorship opportunities" +
                        " to help children from disadvantaged backgrounds succeed. Our dedicated team of" +
                        " volunteers and staff are committed to making a difference in the lives of young" +
                        " people in Poland.")
                .createdFundraises(new ArrayList<>())
                .build();

        Organization organization3 = Organization.builder()
                .name("Safe Haven")
                .imageUrl("https://images.unsplash.com/photo-1485286162995-aa63d31c06cb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80")
                .description("Safe Haven is a charity that works to provide job training and employment" +
                        " opportunities for young adults in eastern Poland and neighboring Ukraine. We believe" +
                        " that everyone deserves the chance to pursue their dreams, even in the midst of war." +
                        " Our programs include career counseling, job placement services, and entrepreneurship" +
                        " training for young people affected by the conflict in Ukraine.")
                .createdFundraises(new ArrayList<>())
                .build();

        List<Fundraise> fundraises = new ArrayList<>();

        Fundraise fundraise1 = Fundraise.builder() //add imageURL
                .name("School supplies for underprivileged children in Warsaw")
                .description("This fundraiser aims to provide school supplies for underprivileged children in " +
                        "the local community. The supplies will be distributed to schools in the area to help " +
                        "children in need. We need your help to reach our goal of 5000 PLN. Your donation will " +
                        "make a big difference in the lives of these children.")
                .requiredAmount(5000L)
                .raisedAmount(5000L)
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
                .raisedAmount(28000L)
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

        List<Announcement> announcements = new ArrayList<>();

        Announcement announcement1 = Announcement.builder()
                .title("Need Help Navigating Life in Poland")
                .description("Hi, my name is Maria and I am a recent immigrant to Poland. I am struggling " +
                        "to get settled and find my footing in this new country. I am looking for someone " +
                        "who can help me navigate the local bureaucracy, find housing and a job, and just" +
                        " generally adjust to life in Poland. If you have experience helping immigrants in " +
                        "Poland and are willing to lend a hand, please get in touch. Thank you!")
                .creationDateTime(LocalDateTime.of(2022,11,29,16,22,12))
                .status("open")
                .build();

        Announcement announcement2 = Announcement.builder()
                .title("Seeking Support for My Small Business in Poland")
                .description("Hi, I am Ahmed and I recently moved to Poland with my family. I have always dreamed of starting my own small business, and I am now in the process of getting everything set up. However, I am finding it difficult to navigate the local business regulations and procedures. I am looking for someone who can help me with the paperwork and provide guidance on how to succeed as a small business owner in Poland. If you have experience in this area and are willing to help, please get in touch. Thank you!")
                .creationDateTime(LocalDateTime.of(2022,12,2,8,10,39))
                .status("open")
                .build();

        Announcement announcement3 = Announcement.builder()
                .title("Need Help Finding a Job in Poland")
                .description("Hi, my name is Alejandra and I am an immigrant to Poland from Mexico. I am highly skilled and educated, but I am having trouble finding a job in my field. I am fluent in both Spanish and English, and I am willing to learn Polish as well. I am looking for someone who can help me with my job search and provide advice on how to succeed in the Polish job market. If you have experience in this area and are willing to help, please get in touch. Thank you!")
                .creationDateTime(LocalDateTime.of(2022,12,2,12,48,49))
                .status("open")
                .build();

        Announcement announcement4 = Announcement.builder()
                .title("Need Help Enrolling My Children in School in Poland")
                .description("Hi, my name is Yara and I am an immigrant to Poland from Lebanon. I have two children who need to enroll in school, but I am having trouble understanding the local education system and the enrollment process. I am looking for someone who can help me navigate the bureaucracy and get my children enrolled in school. If you have experience in this area and are willing to help, please get in touch. Thank you!")
                .creationDateTime(LocalDateTime.of(2022,12,12,19,11,28))
                .status("open")
                .build();

        Announcement announcement5 = Announcement.builder()
                .title("Looking for a Mentor to Help Me Adjust to Life in Poland")
                .description("Hi, my name is Sita and I am an immigrant to Poland from India. I am struggling to adjust to life in a new country and I am feeling very lonely and isolated. I am looking for someone who can be a mentor and a friend, and help me navigate the local culture and customs. If you have experience living in Poland and are willing to be a mentor, please get in touch. Thank you!")
                .creationDateTime(LocalDateTime.of(2022,12,15,7,54,19))
                .status("closed")
                .build();

        List<User> users = new ArrayList<>();

        User user1 = User.builder()
                .name("Maria")
                .lastName("Rodriguez")
                .email("maria.rodriguez@gmail.com")
                .phone("774-211-894")
                .createdAnnouncements(new ArrayList<>())
                .build();

        User user2 = User.builder()
                .name("Ahmed")
                .lastName("Alibura")
                .email("ahmed.ali@gmail.com")
                .phone("734-669-122")
                .createdAnnouncements(new ArrayList<>())
                .build();

        User user3 = User.builder()
                .name("Alejandra")
                .lastName("Gonzalez")
                .email("alejandra.gonzalez@gmail.com")
                .phone("890-129-700")
                .createdAnnouncements(new ArrayList<>())
                .build();

        User user4 = User.builder()
                .name("Yara")
                .lastName("Hussein")
                .email("yara.hussein@gmail.com")
                .phone("330-226-789")
                .createdAnnouncements(new ArrayList<>())
                .build();

        User user5 = User.builder()
                .name("Sita")
                .lastName("Patel")
                .email("sita.patel@gmail.com")
                .phone("789-127-223")
                .createdAnnouncements(new ArrayList<>())
                .build();

        user1.addCreatedAnnouncement(announcement1);
        user2.addCreatedAnnouncement(announcement2);
        user3.addCreatedAnnouncement(announcement3);
        user4.addCreatedAnnouncement(announcement4);
        user5.addCreatedAnnouncement(announcement5);

        // causing issues with infinite loop jackson
        organization1.addCreatedFundraise(fundraise1);
        organization1.addCreatedFundraise(fundraise3);
        organization2.addCreatedFundraise(fundraise2);
        organization3.addCreatedFundraise(fundraise4);
        organization3.addCreatedFundraise(fundraise5);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        announcements.add(announcement1);
        announcements.add(announcement2);
        announcements.add(announcement3);
        announcements.add(announcement4);
        announcements.add(announcement5);

        organizations.add(organization1);
        organizations.add(organization2);
        organizations.add(organization3);

        fundraises.add(fundraise1);
        fundraises.add(fundraise2);
        fundraises.add(fundraise3);
        fundraises.add(fundraise4);
        fundraises.add(fundraise5);

        fundraises.forEach(fundraiseService::add);
        organizations.forEach(organizationService::add);
        users.forEach(userService::add);
        announcements.forEach(announcementService::add);
    }
}
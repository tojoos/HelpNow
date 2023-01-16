package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Mail;
import dev.tojoos.helpnow.util.EmailUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final EmailUtil emailUtil;

    MailController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @PostMapping("/contact")
    public ResponseEntity<Mail> sendConfirmationEmail(@RequestBody Mail incomingMail) {
        UUID uuid = UUID.randomUUID();
        // send confirmation mail to customer
        emailUtil.send(incomingMail.getRecipientEmailAddress(), "HelpNow Support, mail #" + uuid,
                "Dear " + incomingMail.getRecipientName() + ",\n\nThank you for contacting HelpNow " +
                        "Customer Support. We are currently processing your case #" + uuid +
                        ". We will contact you as soon as possible.\n\n\n HelpNow");

        // forward mail to server's mailbox
        emailUtil.send( "Customer service mail #" + uuid + " - " + incomingMail.getSubject(),
                "Content of the correspondence:\n\n" + incomingMail.getContent() +
                "\n\n\nContact email: " + incomingMail.getRecipientEmailAddress());
        return new ResponseEntity<>(incomingMail, HttpStatus.OK);
    }
}

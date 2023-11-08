import { Component, OnInit } from '@angular/core';
import { MailService } from '../services/mail.service';
import { NgForm } from '@angular/forms';
import { Mail } from '../model/mail';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  public modalMessage: string = '';
  public modalTitle: string = '';

  constructor(private mailService: MailService){}

  ngOnInit(): void {
  }

  public submitEmail(submitForm: NgForm): void {
    document.getElementById('submit-mail-form')!.click();
    this.mailService.sendContactEmail(submitForm.value).subscribe({
      next: (response: Mail) => {
        console.log(response);
        submitForm.reset();
        this.showModalWithMessage('Confirmation', 'Your message had been successfully delivered. Our customer service will contact you soon.')
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        submitForm.reset();
        this.showModalWithMessage('Error occured', 'Message couldn\'t be sent. Application returned following error: ' + error.message)
      }
    });
  }

  private showModalWithMessage(title: string, message: string) {
    this.modalTitle = title;
    this.modalMessage = message;
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#confirmationMessage');
    container!.appendChild(button);
    button.click();
  }
}

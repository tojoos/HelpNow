import { Component, OnInit } from '@angular/core';
import { Announcement } from '../model/announcement';
import { AnnouncementService } from '../services/announcement.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormControl, NgForm } from '@angular/forms';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {
  public announcements: Announcement[] = [];
  public latestAnnouncement!: Announcement | undefined;
  public currentUser!: User;

  public modalMessage: string = '';
  public modalTitle: string = '';

  constructor(private announcementService: AnnouncementService,
    private userService: UserService) {
  }

  author: User = {
    username: 'foo',
    password: 'bar',
    name: "John",
    lastName: 'Walberg',
    imageUrl: '',
    phone: '782126821',
    email: 'exmaple@gmail.com',
    id: 10,
    roles: [],
    createdAnnouncements: [],
  } 

  ngOnInit() {
    this.getAnnouncements();
        
    var username = '';
    var localUsername = localStorage.getItem('username')!
    var sessionUsername = sessionStorage.getItem('username')!

    if (localUsername) {
      username = localUsername
    } 
    if (sessionUsername) {
      username = sessionUsername
    }

    this.getUserByUsername(username)
  }

  public getAnnouncements(): void {
    this.announcementService.getAnnouncements().subscribe({
      next: (response: Announcement[]) => {
        this.announcements = response;
        this.announcements.sort((f1, f2) => new Date(f2.creationDateTime).getTime() - new Date(f1.creationDateTime).getTime());
        this.announcements.sort((f1, f2) => f2.status === 'closed' ? -1 : 1)
        this.latestAnnouncement = this.announcements[0]
        this.announcements.splice(0,1)
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        this.showModalWithMessage('Error', error.message)
      }
    });
  }

  public onAddAnnouncement(addForm: NgForm): void {
    document.getElementById('add-announcement-form')!.click();

    addForm.value.author = this.currentUser
    console.log(addForm.value)

    this.announcementService.addAnnouncement(addForm.value).subscribe({
      next: (response: Announcement) => {
        console.log(response);
        this.getAnnouncements();
        addForm.reset();
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        if (error.status === 403) {
          this.showModalWithMessage('User not logged in', 'Please login to create an announcement')
        }
        addForm.reset();
      }
    });
  }

  public getUserByUsername(username: string): void {
    this.userService.getByUsername(username).subscribe({
      next: (response: User) => {
        console.log(response);
        this.currentUser = response
        }
    });
  }

  public onOpenModal(mode: string, announcement?: Announcement): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addAnnouncementModal');
    container!.appendChild(button);
    button.click();
  }

  private showModalWithMessage(title: string, message: string) {
    this.modalTitle = title;
    this.modalMessage = message;
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#alertModal');
    container!.appendChild(button);
    button.click();
  }
}

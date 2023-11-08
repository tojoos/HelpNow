import {Component, OnInit} from '@angular/core';
import { Announcement } from './model/announcement';
import { HttpErrorResponse } from '@angular/common/http';
import { AnnouncementService } from './services/announcement.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public latestAnnouncements: Announcement[] = [];
  public username!: string;

  constructor(private announcementService: AnnouncementService, private router: Router) {
  }

  ngOnInit() {
    this.getAnnouncements();
  }

  public isUserLoggedIn(): boolean {
    var localUsername = localStorage.getItem('username')!
    var sessionUsername = sessionStorage.getItem('username')!
    if (localUsername) {
      this.username = localUsername
    } 
    if (sessionUsername) {
      this.username = sessionUsername
    }

    var localAccessToken = localStorage.getItem('access_token')
    var sessionAccessToken = sessionStorage.getItem('access_token')

    if (localAccessToken != null || sessionAccessToken != null) {
      return true
    }
    return false
  }

  public logUserOut(): void {
    localStorage.removeItem('username');
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');

    sessionStorage.removeItem('username');
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('refresh_token');

    this.router.navigate(['/login']);
    window.location.reload();
  }

  public getAnnouncements(): void {
    this.announcementService.getAnnouncements().subscribe({
      next: (response: Announcement[]) => {
        var announcement: Announcement[]
        announcement = response;
        announcement.sort((f1, f2) => new Date(f2.creationDateTime).getTime() - new Date(f1.creationDateTime).getTime());
        announcement.sort((f1, f2) => f2.status === 'closed' ? -1 : 1)
        this.latestAnnouncements = announcement.slice(0,2)
        },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }
}

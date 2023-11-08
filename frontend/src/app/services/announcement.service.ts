import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Announcement } from "../model/announcement";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) {}

  public getAnnouncements(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(`${this.apiServerUrl}/announcement/list`);
  }

  public addAnnouncement(announcement: Announcement): Observable<Announcement> {
    return this.http.post<Announcement>(`${this.apiServerUrl}/announcement/add`, announcement);
  }

  public updateAnnouncement(announcement: Announcement): Observable<Announcement> {
    return this.http.put<Announcement>(`${this.apiServerUrl}/announcement/update`, announcement);
  }

  public deleteAnnouncement(announcementId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/announcement/${announcementId}/delete`);
  }
}
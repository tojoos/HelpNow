import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Mail } from "../model/mail";

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) {}

  public sendContactEmail(mail: Mail): Observable<Mail> {
    return this.http.post<Mail>(`${this.apiServerUrl}/mail/contact`, mail);
  }
}
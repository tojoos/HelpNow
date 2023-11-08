import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Statistics } from "../model/statistics";

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) {}

  public getStatistics(): Observable<Statistics> {
    return this.http.get<Statistics>(`${this.apiServerUrl}/statistics/getStats`);
  }

  public updateStatistics(statistics: Statistics): Observable<Statistics> {
    return this.http.put<Statistics>(`${this.apiServerUrl}/statistics/update`, statistics);
  }
}
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Fundraise } from "../model/fundraise";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FundraisesService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) {}

  public getFundraises(): Observable<Fundraise[]> {
    return this.http.get<Fundraise[]>(`${this.apiServerUrl}/fundraise/list`);
  }

  public addFundraise(fundraise: Fundraise): Observable<Fundraise> {
    return this.http.post<Fundraise>(`${this.apiServerUrl}/fundraise/add`, fundraise);
  }

  public updateFundraise(fundraise: Fundraise): Observable<Fundraise> {
    return this.http.put<Fundraise>(`${this.apiServerUrl}/fundraise/update`, fundraise);
  }

  public deleteFundraise(fundraiseId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/fundraise/${fundraiseId}/delete`);
  }
}
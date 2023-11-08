import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Organization } from "../model/organization";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) {}

  public getOrganizations(): Observable<Organization[]> {
    return this.http.get<Organization[]>(`${this.apiServerUrl}/organization/list`);
  }

  public addOrganization(organization: Organization): Observable<Organization> {
    return this.http.post<Organization>(`${this.apiServerUrl}/organization/add`, organization);
  }

  public updateOrganization(organization: Organization): Observable<Organization> {
    return this.http.put<Organization>(`${this.apiServerUrl}/organization/update`, organization);
  }

  public deleteOrganization(organizationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/organization/${organizationId}/delete`);
  }
}
import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse, HttpClient
} from '@angular/common/http';
import {catchError, Observable, switchMap, throwError} from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
  private apiServerUrl = environment.apiServerUrl;
  public accessToken = '';
  public refreshToken = '';
  refresh = false;

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.endsWith('/user/token/refresh')) {
      console.log("Not intercepting request because - refresh token request.")
      return next.handle(request);
    }

    var localAccessToken = localStorage.getItem('access_token')!
    var sessionAccessToken = sessionStorage.getItem('access_token')!
    if (localAccessToken) {
      this.accessToken = localAccessToken
      this.refreshToken = localStorage.getItem('refresh_token')!
    } 
    if (sessionAccessToken) {
      this.accessToken = sessionAccessToken
      this.refreshToken = sessionStorage.getItem('refresh_token')!
    }

    const req = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.accessToken}`
      }
    });

    return next.handle(req).pipe(catchError((err: HttpErrorResponse) => {
      if (err.status === 403 && !this.refresh && this.refreshToken != '') {
        this.refresh = true;
        return this.http.get(`${this.apiServerUrl}/user/token/refresh`, {headers: {Authorization: `Bearer ${this.refreshToken}`}}).pipe(
          switchMap((res: any) => {
            console.log("Returned new access and refresh token using user/token/refresh endpoint.")
            if (localAccessToken) {
              localStorage.setItem('access_token',  res.access_token);
              localStorage.setItem('refresh_token', res.refresh_token);  
            } 
            if (sessionAccessToken) {
              sessionStorage.setItem('access_token',  res.access_token);
              sessionStorage.setItem('refresh_token', res.refresh_token);
            }

            this.accessToken = res.access_token;
            this.refreshToken = res.refresh_token;
            return next.handle(request.clone({
              setHeaders: {
                Authorization: `Bearer ${this.accessToken}`
              }
            }));
          })
        );
      }
      this.refresh = false;
      return throwError(() => err);
    }));
  }
}

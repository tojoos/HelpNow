import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private apiServerUrl = environment.apiServerUrl;
  form!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: '',
      password: ''
    });
  }

  login() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    const payload = {
      username: this.form.get('username')?.value,
      password: this.form.get('password')?.value
    };

    this.http.post<any>(`${this.apiServerUrl}/login`, payload, {headers: httpOptions.headers}).subscribe({
      next: (response) => {
        var checkbox = document.getElementById("loginCheckbox") as HTMLInputElement

        if (checkbox.checked) {
          localStorage.setItem('username', payload.username);
          localStorage.setItem('access_token',  response.access_token);
          localStorage.setItem('refresh_token', response.refresh_token);
        } else {
          sessionStorage.setItem('username', payload.username);
          sessionStorage.setItem('access_token',  response.access_token);
          sessionStorage.setItem('refresh_token', response.refresh_token);
        }

        this.router.navigate(['/']);
        },
      error: (error: HttpErrorResponse) => {
        if (error.status === 403) {
          alert('Failed to login, please make sure that you are using valid credentials.');
        } else {
          alert(error)
        }
      }
    });
  }
}
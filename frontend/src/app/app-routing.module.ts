import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {EmployeesComponent} from "./employees/employees.component";
import {FundraisesComponent} from "./fundraises/fundraises.component";
import {OrganizationsComponent} from "./organizations/organizations.component";
import {ContactComponent} from "./contact/contact.component";
import {HomeComponent} from "./home/home.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {AnnouncementsComponent} from './announcements/announcements.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: 'home', title: 'HelpNow', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'fundraises', title: 'HelpNow | Fundraises', component: FundraisesComponent},
  {path: 'employees', title: 'HelpNow | Crew', component: EmployeesComponent},
  {path: 'announcements', title: 'HelpNow | Announcements', component: AnnouncementsComponent},
  {path: 'organizations', title: 'HelpNow | Organizations', component: OrganizationsComponent},
  {path: 'contact', title: 'HelpNow | Contact', component: ContactComponent},
  {path: 'login', title: 'HelpNow | Login', component: LoginComponent},
  {path: 'register', title: 'HelpNow | Register', component: RegisterComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

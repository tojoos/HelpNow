import {Component, OnInit} from '@angular/core';
import {Employee} from '../model/employee';
import {EmployeeService} from "../services/employee.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  public employees: Employee[] = [];
  public editEmployee!: Employee | undefined;
  public deleteEmployee!: Employee | undefined;

  public modalMessage: string = '';
  public modalTitle: string = '';

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next: (response: Employee[]) => {
        this.employees = response;
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.message);
          if (error.status === 403) {
            this.showModalWithMessage('Access denied', 'No sufficient access permissions. To manage employee login as administrator.')
          } else {
            console.log(error.message);
            this.showModalWithMessage('Error occured', error.message);
          }
      }
    });
  }

  public onAddEmployee(addForm: NgForm): void {
    document.getElementById('add-employee-form')!.click();
    this.employeeService.addEmployee(addForm.value).subscribe({
      next: (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        if (error.status === 403) {
          this.showModalWithMessage('Access denied', 'No sufficient access permissions. To add new employee, please log in as administrator.')
        } else {
          console.log(error.message);
          this.showModalWithMessage('Error occured', error.message);
        }
        addForm.reset();
      }
    });
  }

  public onUpdateEmployee(employee: Employee): void {
    document.getElementById('edit-employee-form')!.click();
    this.employeeService.updateEmployee(employee).subscribe({
      next: (response: Employee) => {
        console.log(response);
        this.getEmployees();
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        this.showModalWithMessage('Error occured', error.message);
      }
    });
  }

  public onDeleteEmployee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe({
      next: (response: void) => {
        console.log(response);
        this.getEmployees();
        },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
        this.showModalWithMessage('Error occured', error.message);
      }
    });
  }

  public onOpenModal(mode: string, employee?: Employee): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    } else if (mode === 'edit') {
      this.editEmployee = employee!;
      button.setAttribute('data-target', '#editEmployeeModal');
    } else if (mode === 'delete') {
      this.deleteEmployee = employee!;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container!.appendChild(button);
    button.click();
  }

  public searchEmployees(key: string): void {
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (!key) {
      this.getEmployees();
    }
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

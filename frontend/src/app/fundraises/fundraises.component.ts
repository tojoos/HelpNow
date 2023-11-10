import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Fundraise } from '../model/fundraise';
import { Statistics } from '../model/statistics';
import { FundraisesService } from '../services/fundraises.service';
import { StatisticsService } from '../services/statistics.service';
import { FormControl, FormGroup, NgForm } from '@angular/forms';

@Component({
  selector: 'app-fundraises',
  templateUrl: './fundraises.component.html',
  styleUrls: ['./fundraises.component.css', './fundraises.component.scss']
})
export class FundraisesComponent implements OnInit {
  public fundraises: Fundraise[] = [];
  public edittedFundraise!: Fundraise;
  public statistics: Statistics | any;
  public modalTitle: String = '';
  public modalMessage: String = '';

  formGroup = new FormGroup({
    amount: new FormControl(),
  });
  
  constructor(private fundraiseService: FundraisesService, private statisticsService: StatisticsService) { }

  ngOnInit() {
    this.getFundraises();
    this.getStatistics();
  }

  async incrementServiceViews() {
    this.statistics.serviceVisits = this.statistics.serviceVisits + 1;
    this.updateStatistics(this.statistics);
  }

  public getStatistics(): void {
    this.statisticsService.getStatistics().subscribe({
      next: (response: Statistics) => {
        this.statistics = response as Statistics;
      },
      error: (error: HttpErrorResponse) => {
        this.showModalWithMessage('Error occured', error.message);
      }
    });

    const interval = setInterval(async () => {
      if (typeof this.statistics !== 'undefined') {
        clearInterval(interval);
        await this.incrementServiceViews();
      }
    }, 500);
  }

  public updateStatistics(statistics: Statistics): void {
    this.statisticsService.updateStatistics(statistics).subscribe({
      next: (response: Statistics) => {
        console.log(response);
        },
      error: (error: HttpErrorResponse) => {
        this.showModalWithMessage('Error occured', error.message);
      },
    });
  }

  public getFundraises(): void {
    this.fundraiseService.getFundraises().subscribe({
      next: (response: Fundraise[]) => {
        this.fundraises = response;
        this.fundraises.sort((f1, f2) => new Date(f1.startingDate).getTime() - new Date(f2.startingDate).getTime());
        this.fundraises.sort((f1, f2) => this.isFundraiseFinished(f2) ? -1 : 1)
      },
      error: (error: HttpErrorResponse) => {
        this.showModalWithMessage('Error occured', error.message);
      }
    });
  }

  public isFundraiseFinished(fundraise: Fundraise): boolean {
    var toEndTime = new Date(fundraise.endingDate).getTime() - new Date().getTime();
    return String(toEndTime) < String(0)
  }

  public calculateTillFundraise(fundraise: Fundraise): string {
    var toEndTime = new Date(fundraise.endingDate).getTime() - new Date().getTime();
    var toStartTime = new Date(fundraise.startingDate).getTime() - new Date().getTime();
    if (String(toStartTime) < String(0)) {
      if (String(toEndTime) < String(0)) {
        return 'Ended ' + Math.abs(Math.floor(toEndTime / (1000 * 60 * 60 * 24))) + ' days ago.';
      } else {
        return 'Ending in ' + Math.abs(Math.floor(toEndTime / (1000 * 60 * 60 * 24))) + ' days.';
      }
    } else {
      return 'Starting in ' + Math.abs(Math.floor(toStartTime / (1000 * 60 * 60 * 24))) + ' days.';
    }
  }

  public onHelpButton(helpForm: FormGroup): void {
    document.getElementById('donate-button')!.click();
    var editedFundraiseCopy = { ...this.edittedFundraise };
    const newAmount = Number(this.edittedFundraise.raisedAmount) + Number(helpForm.value.amount);

    editedFundraiseCopy.raisedAmount = newAmount;

    this.fundraiseService.updateFundraise(editedFundraiseCopy).subscribe({
      next: (response: Fundraise) => {
        this.edittedFundraise.raisedAmount = newAmount;
        console.log(response);
        this.getFundraises();
        helpForm.reset();
        },
      error: (error: HttpErrorResponse) => {
        if (error.status === 403) {
          console.log('Couldn\'t create an announcement. Make sure you are logged in.')
          this.showModalWithMessage('Please log in', 'Couldn\'t create an announcement. Make sure you are logged in.');
        } else {
          console.log(error.message);
          this.showModalWithMessage('Error occured', error.message);
        }
        this.getFundraises();
        helpForm.reset();
      }
    });
  }

  public onHelpModal(fundraise?: Fundraise): void {
    this.edittedFundraise = fundraise!;
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addAnnouncementModal');
    container!.appendChild(button);
    button.click();
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
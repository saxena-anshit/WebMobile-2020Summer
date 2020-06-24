import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  setInterval = setInterval;

  ngOnInit() {
    // run the function updateCountdown every 1 seconds
    this.updateCountdown();
  }

  /* An empty array that is responsible
       to add a division */
  public items = [];

  /* A two-way binding performed which
     pushes text on division */
  public newLI;


  /* When input is empty, it will
     not create a new division */
  public addToList() {
    if (this.newLI == '') {
    }
    else {
      this.items.push(this.newLI);
      this.newLI = '';
    }
  }

  /* This function takes to input the
     task, that has to be deleted*/

  public deleteTask(index) {
    this.items.splice(index, 1);
  }

  public startDate: Date = new Date();
  public endDate: Date = new Date("2020-11-03");
  public second: number;
  public seconds: number;
  public minute: number;
  public minutes: number;
  public hour: number;
  public hours: number;
  public days: number;

  public updateCountdown() {
      this.second = Math.floor((this.endDate.getTime() - this.startDate.getTime()) /1000);
      this.seconds = this.second % 60;
      this.minute = Math.floor((this.endDate.getTime() - this.startDate.getTime()) /1000/60);
      this.minutes = this.minute %60;
      this.hour = Math.floor((this.endDate.getTime() - this.startDate.getTime()) /1000/60/60);
      this.hours = this.hour %24;
      this.days = Math.floor((this.endDate.getTime() - this.startDate.getTime()) /1000/60/60/24);
}
}

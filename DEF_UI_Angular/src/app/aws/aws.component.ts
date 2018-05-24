import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-aws',
  templateUrl: './aws.component.html',
  styleUrls: ['./aws.component.css']
})
export class AwsComponent implements OnInit {
  launchInstance = false;
  showDiv(){
    this.launchInstance = true;
  }
  hideDiv(){
    this.launchInstance = false;
  }
  constructor() { }

  ngOnInit() {
  }

}

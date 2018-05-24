import { Component, OnInit, Input } from '@angular/core';
import { DataserviceService } from '../dataservice.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

  @Input('parentData') incomingData: string;

  successdata :any;
   extractData: string;
   datavalid :any;
   message:string;


  constructor(private data: DataserviceService) { }

  ngOnInit() {
	this.newMessage();
  }

   newMessage() {

   this.datavalid = this.data.myData();
  }

  

}

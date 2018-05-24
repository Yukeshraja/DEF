import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
@Injectable()
export class DataserviceService {

  DataSuccess:any;
 
  constructor() { }
  

  setData(DataSuccess){

  this.DataSuccess = DataSuccess;
  }

  myData() {
    return this.DataSuccess;
  }

}

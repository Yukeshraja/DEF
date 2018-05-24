import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Router }    from '@angular/router';
import { environment } from '../../environments/environment';
import 'rxjs/add/operator/catch';
import { ToasterService, Toast } from 'angular2-toaster';
import { DataserviceService } from '../dataservice.service';

@Component({
  selector: 'app-password-management',
  templateUrl: './password-management.component.html',
  styleUrls: ['./password-management.component.css']
})

export class PasswordManagementComponent implements OnInit {

  
  PasswordManagement = {
    "name": "Alti",
    "authType": "authtype",
    "userName": "",
    "password": "",
    "sshKey": "",
    "secretText": "secretkey",
    "hostName":""
  };

  validData :any;
  changePassword = false;
  credentialsList = [];
  cerdentialsData : string;
  deleteItem : string;

  editPMObject = {
    "credentialName": "",
    "userName" : "",
    "password" : "",
    "sshKey" : "",
    "secretKey" : "",
    "type": "",
  };
  verifyPassword = {
    "oldPassword" : "",
    "newPassword" : ""
  };

  constructor(private http: Http, private router: Router, private toasterService: ToasterService, private data: DataserviceService) {
   }

  ngOnInit() {
    this.http.get(environment.apiservices+ '/demo/getAllCredentials').map(
      (response) => response.json()
    )
   .subscribe(
     (data) => {
      // Read the result field from the JSON response.
      this.cerdentialsData = data.result;
     // alert(JSON.stringify(this.cerdentialsData));
    }
   )
  }

  save(data: any) {
  	//alert(environment.apiservices)
    //var headers = new Headers();
    //headers.append('Content-Type', 'application/json');

   /// alert(123);

   if(true){

   let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let url = environment.apiservices+ '/demo/createCredential';

    //alert(url cfe);

  this.http.request('https://address-book-demo.herokuapp.com/api/contacts')
    .map(res => res.json())
    .subscribe(
      data => console.log(data),
      err => console.log(err),
      () => console.log('yay')
    );
  
    alert(JSON.stringify(data))
  this.http.post(url, data, options)
    .map(res => res.json())
      .subscribe(
        data => console.log(data),
        err => console.log(err),
        () => console.log('yay')
      );


   }else{


   }

	alert(1)



	/*    
		this.http
			  .post(environment.apiservices+ '/demo/createCredential',
			  data, {
			    headers: headers
			  })
			  .subscribe(data => {
			  	alert(data)
			    this.router.navigate(['Success']);
			  }, error => {
			    //  console.log(JSON.stringify(error.json()));
			    console.log("error");
			  });
		// console.log(data);
	*/
	}

	private extractData(res: Response) {
	let body = res.json();
        //return body.data || {};
        alert(body.data || {})
        //body.data || {};
    }
    private handleErrorObservable (error: Response | any) {
    alert(error.message || error);
	console.error(error.message || error);
	return Observable.throw(error.message || error);
    }   	

    editRow(editObject){
      this.editPMObject = editObject;
      console.log(this.editPMObject);
    }

    editPassword(){
      this.changePassword = true;
    }

    verifyChangePassword(){
      if(this.verifyPassword.oldPassword == this.editPMObject.password){
        this.editPMObject.password = this.verifyPassword.newPassword;
        this.changePassword = false;
        this.toasterService.pop('success',"Successfully Password Changed");
        this.verifyPassword = {
          "oldPassword" : "",
          "newPassword" : ""
        };
      }
      else{
        this.changePassword = true;
        this.toasterService.pop('failure',"Password doesn't match");
      }
    }

    cancelChangePassword(){
     this.verifyPassword = {
        "oldPassword" : "",
        "newPassword" : ""
      };
      
      this.changePassword = false;
    }

    confirmDelete(deleteItemName){
      this.deleteItem = deleteItemName;
    }

      
    updateCredentials(data : any, credName){
      console.log(data);
      this.validData = data;
      this.data.setData(this.validData);
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');

      this.http
      .put(environment.apiservices + '/demo/updateCredential/'+ credName,
      data, {
        headers: headers
      })
      .subscribe(data => {
        this.toasterService.pop('success',"Successfully Updated");
        window.location.reload();     
      }, error => {
        this.toasterService.pop('failure',"Failure in Updation");        
      });
    }

    deleteCredential(credName){
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      this.http
      .delete(environment.apiservices + '/demo/deleteCredential/'+ credName, {
        headers: headers
      })
      .subscribe(data => {
        this.toasterService.pop('success',"Successfully Deleted");
        window.location.reload();        
      }, error => {
        this.toasterService.pop('failure',"Failure in Deletion");        
      });
    }
}

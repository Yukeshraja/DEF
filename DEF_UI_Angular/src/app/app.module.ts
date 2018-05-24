import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ToasterModule } from 'angular2-toaster';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {RouterModule , Routes} from '@angular/router';
import { ContinuousDeliveryComponent } from './continuous-delivery/continuous-delivery.component';
import { ScmComponent } from './scm/scm.component';
import { ProjectComponent } from './project/project.component';
import { NewProjectComponent } from './new-project/new-project.component';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';
import { ConfigComponent } from './config/config.component';
import { LoginComponent } from './login/login.component';
import { PasswordManagementComponent } from './password-management/password-management.component';
import { SuccessComponent } from './success/success.component';
import { FailureComponent } from './failure/failure.component';
import { ConfigHomeComponent } from './config-home/config-home.component';
import { AwsComponent } from './aws/aws.component';
import { GetJobComponent } from './get-job/get-job.component';
import { UpdateJobsComponent } from './update-jobs/update-jobs.component';
import { DataserviceService } from './dataservice.service';
import { SecretTextMaskPipe } from './mask-text/text-mask.pipe'
import { PasswordMaskPipe } from './mask-text/mask-password.pipe'

const appRoutes: Routes = [
{
  path: '',
  redirectTo: 'CiCd',
  pathMatch: 'full'
},
{
  path: 'home',
  component: HomeComponent
},
{
  path: 'CiCd',
  component: ContinuousDeliveryComponent
},
{
  path: 'project',
  component: ProjectComponent
},
{
  path: 'login',
  component: LoginComponent
},
{
  path: 'scm',
  component: ScmComponent
},
{
  path: 'NewProject',
  component: NewProjectComponent
},
{
  path: 'config',
  component: ConfigComponent
},
{
  path: 'passwordManagement',
  component: PasswordManagementComponent
},
{
  path: 'Success',
  component: SuccessComponent
},
{
  path: 'Failure',
  component: FailureComponent
},
{
  path: 'chome',
  component: ConfigHomeComponent
},
{
  path: 'aws',
  component: AwsComponent
},
{
  path: 'getJob',
  component: GetJobComponent
},
{
  path: 'update',
  component: UpdateJobsComponent
}
];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContinuousDeliveryComponent,
    ScmComponent,
    ProjectComponent,
    NewProjectComponent,
    ConfigComponent,
    LoginComponent,
    PasswordManagementComponent,
    SuccessComponent,
    FailureComponent,
    ConfigHomeComponent,
    AwsComponent,
    GetJobComponent,
    UpdateJobsComponent,
    SecretTextMaskPipe,
    PasswordMaskPipe
  ],
  imports: [
    BrowserModule,
    AngularMultiSelectModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes,
    { enableTracing: true }
  ),
    BrowserAnimationsModule,
    ToasterModule
  ],
  providers: [DataserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }

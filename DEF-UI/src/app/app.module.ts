import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {RouterModule} from '@angular/router';
import { ContinuousDeliveryComponent } from './continuous-delivery/continuous-delivery.component';
import { ScmComponent } from './scm/scm.component';
import { ProjectComponent } from './project/project.component';
import { NewProjectComponent } from './new-project/new-project.component';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';
import { ConfigComponent } from './config/config.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContinuousDeliveryComponent,
    ScmComponent,
    ProjectComponent,
    NewProjectComponent,
    ConfigComponent
  ],
  imports: [
    BrowserModule,
    AngularMultiSelectModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        redirectTo: 'home',
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
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

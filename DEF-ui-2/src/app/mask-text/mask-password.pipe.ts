import { Pipe, PipeTransform } from '@angular/core';

  @Pipe({
    name: 'passwordMaskPipe'
  })
  
export class PasswordMaskPipe implements PipeTransform {
  transform(passwordToMask: string): string {
    if(passwordToMask!=''){
      return passwordToMask.replace(/./g, '*');
    }
    else{
      return '';
    }
  }
}
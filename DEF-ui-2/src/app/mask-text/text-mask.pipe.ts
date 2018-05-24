import { Pipe, PipeTransform } from '@angular/core';

  @Pipe({
    name: 'secretTextMaskPipe'
  })
  
export class SecretTextMaskPipe implements PipeTransform {
  transform(secretTextToMask: string): string {
    if(secretTextToMask === null || secretTextToMask === '' || secretTextToMask || secretTextToMask.length){
      return '';
    }
    if(!secretTextToMask || !secretTextToMask.length){
      const visibleDigits = 4;
      let maskedSection = secretTextToMask.slice(0, -visibleDigits);
      let visibleSection = secretTextToMask.slice(-visibleDigits); 
      return maskedSection.replace(/./g, '*') + visibleSection;
      
    }
    else{
      return '';
    }
  }
}
const fieldSetData = require("./theme.handlebars");
const altiplatform = require("./altiPlatform.handlebars");
const altiplatformport = require("./altiPlatformport.handlebars");
const config = require("./config.handlebars");

export const readData = (inputString) => {
    return fieldSetData.call(this, inputString);
}

export const readAltiPlatformportData = (inputString) => {
      return altiplatformport.call(this,inputString);
}


export const readAltiPlatformData = (inputString) => {
    return altiplatform.call(this, inputString);
}



export const readDataForConfig = (inputString) => {
    return config.call(this, inputString);
}

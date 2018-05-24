import {
  cyan500, cyan700, lime900, lime500,
  pinkA200, blue700, blue500, blue300, 
  grey100, grey300, grey400, grey500,
  white, darkBlack, fullBlack,indigo500
} from 'material-ui/styles/colors';
import {fade} from 'material-ui/utils/colorManipulator';

export default {
  fontFamily: 'Roboto, sans-serif',
  appBar : {
      height : 80
  },
  palette: {
    primary1Color: lime900,
    primary2Color: cyan700,
    primary3Color: grey400,
    accent1Color: pinkA200,
    accent2Color: grey100,
    accent3Color: grey500,
    textColor: darkBlack,
    alternateTextColor: lime500,
    canvasColor: lime500,
    borderColor: grey300,
    disabledColor: fade(darkBlack, 0.3),
    pickerHeaderColor: cyan500,
    clockCircleColor: fade(darkBlack, 0.07),
    shadowColor: fullBlack,
  },
};
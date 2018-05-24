import React from "react";
import Collapser from '../components/collapser';
import ListExampleNested from '../components/list';
import Col from '../materialize/Col';
import Breadcrumb from '../materialize/Breadcrumb';
import MenuItem from '../materialize/MenuItem';
import injectTapEventPlugin from "react-tap-event-plugin";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import AppBar from "material-ui/AppBar";
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import Footer from '../components/footer';
import "../static/css/all.css";

import {
  cyan500, cyan700,
  pinkA200, pinkA500, pinkA700,
  pink200, pink500, pink700,
  grey100, grey300, grey400, grey500,
  yellow600, yellow400, yellow200, 
  white, darkBlack, fullBlack,
  blue500
} from 'material-ui/styles/colors';



const muiTheme = getMuiTheme({
  palette: {
    textColor: cyan500,
    primary1Color: yellow600,
    primary2Color: yellow400,
    primary3Color: yellow200    
  },
  appBar: {
    height: 70
  }
});


export const Layout3 = props => {
  return (
    <div>
	   		 <div className="bodyStyle">	     
	   		 		<MuiThemeProvider muiTheme={muiTheme}>
				    	<AppBar title="Platforms" iconClassNameLeft="logo" className="logo"/>
				    </MuiThemeProvider>
		     
		      		
            <div>
		          <ListExampleNested serviceUrl='http://192.168.94.119:2222/componentrepo/api/userData' />
            </div>
          </div>
			     <Footer />

    </div>
  );
};



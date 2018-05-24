import React, { Component, PropTypes } from "react";
import Handlebars from "handlebars";
import ThemeConfig from './themeConfig.json';
import { readData } from "./theme";

class ThemeGeneratorNew extends React.Component  {

	constructor(props) {
		super(props);
    	this.handleClick = this.handleClick.bind(this);
	}

	handleClick() {
		//alert("123") 
		//alert(JSON.stringify(this.props))
  		const { Project: { path } } = this.props;
  		const { fileUploadAPI } = this.props;

		//alert(JSON.stringify(this.props.theme));
		//alert("Hello-->"+readData(this.props.theme));
		//alert(ThemeTemplate);
		
  		
  		//const template  = Handlebars.compile( ThemeTemplate );
		//alert({ path }.path + "/template/src/themeConfig.js");
		//alert(JQuery("#header").html());


		fetch(`${ fileUploadAPI }`, {
		  method: 'POST',
		  headers: {
		    'Accept': 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify({
                "filepath" : `${path}` + "/template/src/themeConfig.js",
                "data" : readData(this.props.theme)
			})
		})
	}

	render () {
		return <div className="container">
					<button onClick={this.handleClick}>
	                    ThemeGenerator
	                  </button>
	           </div>;
	}
	
}


export default ThemeGeneratorNew;
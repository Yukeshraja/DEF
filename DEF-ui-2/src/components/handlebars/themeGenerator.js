import React, { Component, PropTypes } from "react";
import Handlebars from "handlebars";
import ThemeConfig from './themeConfig.json';


class ThemeGenerator extends React.Component  {


	constructor(props) {
		super(props);
		this.state = { options: [] , open: true , isToggleOn: 'ON', 
			template  :  Handlebars.compile( ThemeConfig.THEME_CONFIG.join('\n') )};
		
		// This binding is necessary to make `this` work in the callback
    	this.handleClick = this.handleClick.bind(this);
	}


	componentDidMount() {

	}



	handleClick() {
		const { Project: { path } } = this.props;
		const { fileUploadAPI } = this.props;

		//alert(`${ path }` + "/template/src/themeConfig.js")
		fetch(`${fileUploadAPI}`, {
		  method: 'POST',
		  headers: {
		    'Accept': 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify({
                "filepath" : `${ path }` + "/template/src/themeConfig.js",
                "data" : this.state.template( ThemeConfig.COLOR )
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


export default ThemeGenerator;
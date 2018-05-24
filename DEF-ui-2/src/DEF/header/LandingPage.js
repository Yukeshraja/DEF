import React, { Component, PropTypes } from "react";
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import Paper from 'material-ui/Paper';

class LandingPage extends React.Component  {

	constructor(props) {
		super(props);
    	this.handleClick = this.handleClick.bind(this);
	}

	handleClick() {
	}
	

	render () {

		const style = {
		  card : {width : "20%", height : "40%", display: 'inline-block', margin : '2%'},
		  button : {margin: 12},
		  paper : {
			    marginLeft: 'auto',
			    marginRight: 'auto',
			    width: '70%', 
		  	  verticalAlign: 'middle',
			  margin: 20,
			  textAlign: 'center',
			  display: 'inline-block',
		  },
		  CardMedia : {width : "20%", height : "20%" ,
					verticalAlign: 'middle'},
			centerPlaced : {textAlign : "center"}
		};

		return (
<div className="outer" >
  <div className="middle">
    <div className="inner" style={style.centerPlaced}>


	    			<Paper style={style.paper} zDepth={3} >
					    <CardTitle title="Continuous Delivery for every team"/>
					    <CardText>
					      DEF 2.0 puts Continuous Delivery in reach of any team without sacrificing the power and sophistication of Jenkins and Concourse.
					    </CardText>
					</Paper>
<div>
				<Card  style={style.card}>
				    <CardMedia  style={style.CardMedia}>
				      <img src="assets/images/landing/code.png" alt="" />
				    </CardMedia>
				    <CardTitle title="Code" subtitle="Any SCM control tool" />
				    <CardText>
				      Run the pipeline from any SCM source control tool.
				    </CardText>
				</Card>	

				  <Card  style={style.card}>
				    <CardMedia  style={style.CardMedia}>
				      <img src="assets/images/landing/Git.png" alt="" />
				    </CardMedia>
				    <CardTitle title="Github & Git" subtitle="Github & Git" />
				    <CardText>
				      Run the pipeline for every feature branch and pull request.
				    </CardText>
				  </Card>	

				  <Card  style={style.card}>
					    <CardMedia  style={style.CardMedia}>
					      <img src="assets/images/landing/visualization.png" alt="" />
					    </CardMedia>
					    <CardTitle title="Pipeline" subtitle="Pipeline visualization tool" />
					    <CardText>
					    	Run the pipeline for every feature branch and pull request.
					    </CardText>
				  </Card>	
</div>
<div>

				  <Card  style={style.card}>
					    <CardMedia  style={style.CardMedia}>
					      <img src="assets/images/landing/services.png" alt="" />
					    </CardMedia>
					    <CardTitle title="External Services" subtitle="External Services tool" />
					    <CardText>
					    	Integrate with dozens of external services such as JIRA, Slack, Asana and more.
					    </CardText>
				  </Card>	

				  <Card  style={style.card}>
					    <CardMedia  style={style.CardMedia}>
					      <img src="assets/images/landing/monitoring.png" alt="" />
					    </CardMedia>
					    <CardTitle title="Monitoring" subtitle="Monitoring tool" />
					    <CardText>
					    	Monitoring is easy with the tool, all the services and failures can be configured.
					    </CardText>
				  </Card>	

				  <Card  style={style.card}>
					    <CardMedia  style={style.CardMedia}>
					      <img src="assets/images/landing/deploytocloud.png" alt="" />
					    </CardMedia>
					    <CardTitle title="Deploy to Cloud" subtitle="Deploy to Cloud tool" />
					    <CardText>
					    	With the modern pipeline all the app can be deployed to the cloud with ease.
					    </CardText>
				  </Card>	

</div>
					<RaisedButton label="Get Started" style={style.button} />

    </div>
  </div>
</div>
	           );
	}
	
}


export default LandingPage;
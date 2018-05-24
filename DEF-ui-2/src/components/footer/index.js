import React from 'react';
import PropTypes from 'prop-types';
import './footer.css';
import { appendClassName } from '../../utils';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';


const Footer = (props) => {

		 return(
        	

        	<div className="footer">

						<Card style={{backgroundColor: 'grey', color: 'white'}}>
						    <CardText >
			         
							    <div>
							    	<span className="align-left">Altimetrik</span>
							    	<span className="align-right">www.altimetrik.com</span>
							    </div>
							    <br />
							    <div>
								 	<span className="grey-text text-lighten-4 align-left">Digital transformation company</span>
									<span className="copyright align-right">
						                {props.copyrightMessage}
						            </span>
								</div> 

						    </CardText>
						</Card>
			     </div>            
        )
}

Footer.defaultProps = {
        copyrightMessage : 'Copyright (c) 2017-2018 Altimetrik, Pvt Ltd'
}

Footer.propTypes = {
    copyrightMessage: PropTypes.string.isRequired
}
export default Footer;

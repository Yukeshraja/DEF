import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar';
import IconButton from 'material-ui/IconButton';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import FlatButton from 'material-ui/FlatButton';
import Toggle from 'material-ui/Toggle';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import NavigationClose from 'material-ui/svg-icons/navigation/close';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
//import { Link } from 'react-router-dom';
//import { Route, Link } from "react-router-dom";
import DevLog from './index';
import UserForm from './index';
// import { BrowserRouter as Router, Route } from 'react-router-dom';
 import { Link, Route ,Router } from "react-router-dom";

class AppContext extends React.Component {
 // static muiName = 'FlatButton';

  render() {
    return (
      <h1>User Data</h1>
    );
  }
}


class AppBarHeader extends Component {
  state = {
    logged: true,
  };

  handleChange = (event, logged) => {
    this.setState({logged: logged, open: false});
  };

  constructor(props) {
    super(props);
      this.onRequestChange = this.onRequestChange.bind(this);
  }

  onRequestChange(open , reason) {
    this.setState({open: true});
  }

  render() {

   
    return (
        <div>
                <AppBar title="Developer Enablement Framework" iconClassNameLeft="logo" className="logoNew">

                    <div>
                      <IconMenu onClick={this.onRequestChange}
                        iconButtonElement={<IconButton><MoreVertIcon /></IconButton>}
                        anchorOrigin={{horizontal: 'left', vertical: 'top'}}
                        targetOrigin={{horizontal: 'left', vertical: 'top'}}
                      >
                        <MenuItem primaryText="Refresh" selected="true" containerElement={<Link to="/view/landing" />}/>
                        <MenuItem primaryText="Settings" />
                        <MenuItem primaryText="Admin" containerElement={<Link to="/view/admin" />}/>
                        <MenuItem primaryText="Get Jobs" containerElement={<Link to="/view/getjobs" />}/>
                        <MenuItem primaryText="Credential Management" containerElement={<Link to="/view/credmgmt" />}/>
                        <MenuItem primaryText="Developer" containerElement={<Link to="/view/developer" />}/>
                      </IconMenu>
                    </div>

                </AppBar>

        </div>        
    );
  }
}

export default AppBarHeader;
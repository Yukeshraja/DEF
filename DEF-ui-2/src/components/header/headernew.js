import React, { Component, PropTypes } from "react";
import AppBar from "material-ui/AppBar";
import Paper from "material-ui/Paper";
import SvgIcon from "material-ui/SvgIcon";
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import ConfigDetails from '../config/configdetails.js';
import { Link, Route } from "react-router-dom";

class HeaderNew extends React.Component  {

  constructor(props) {
    super(props);
  }

  renderRoutes(expand) {
    const { MenuContext: { data: { menuItems } } } = this.props;
    const routes = () => {
      return (
          <Route
          path="/view/config"
          render={() => {
            return (
              <ConfigDetails {...this.props}/>
            );
          }}
        />
      );
    }
  }

  render () {
    const { Header : { appTitle }} = this.props;

    return ( 
                <div>
                          <AppBar title={appTitle} iconClassNameLeft="logo" className="logoNew">

                          <div>
                            <IconMenu
                              iconButtonElement={<IconButton><MoreVertIcon /></IconButton>}
                              anchorOrigin={{horizontal: 'left', vertical: 'top'}}
                              targetOrigin={{horizontal: 'left', vertical: 'top'}}
                            >
                              <MenuItem primaryText="Refresh" />
                              <MenuItem primaryText="Send feedback" />
                              <MenuItem primaryText="Settings" containerElement={<Link to="/view/config" />}/>
                              <MenuItem primaryText="Help" />
                              <MenuItem primaryText="Sign out" />
                               <MenuItem primaryText="Settings12" containerElement={<Link to="/home" />}/>
                            </IconMenu>
                          </div>
                          </AppBar>

                    {this.renderRoutes()}

                </div>                        
          )

         

  }
  
}


export default HeaderNew;          
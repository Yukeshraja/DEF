import React, { Component, PropTypes } from "react";
import Table from '../components/table';
import Field from "../components/field";
import ThemeGeneratorNew from "../components/handlebars/ThemeGeneratorNew";
import DropDown, { DropDownWithData } from "../components/dropdown";
import RemitterUI from "../codegenerated/remitter_kb";
import RaisedButton from 'material-ui/RaisedButton';
import Beneficiary from "../codegenerated/beneficiary";
import ThemeConfig from '../components/handlebars/themeConfig.json';
import Handlebars from "handlebars";
import ThemeTemplate from '../components/handlebars/theme.handlebars';
import ConfigUi from '../components/handlebars/config.handlebars';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import { Card, CardTitle } from "material-ui/Card";
import {List, ListItem} from 'material-ui/List';

import MaterialTable from "../components/table/materialtable";
import Footer from "../components/footer";
import ActionGrade from 'material-ui/svg-icons/action/grade';
import ContentInbox from 'material-ui/svg-icons/content/inbox';
import ContentDrafts from 'material-ui/svg-icons/content/drafts';
import ContentSend from 'material-ui/svg-icons/content/send';
import Subheader from 'material-ui/Subheader';
import Toggle from 'material-ui/Toggle';
import AppBar from 'material-ui/AppBar';
import Divider from 'material-ui/Divider';
import TextField from 'material-ui/TextField';
import ConfigDetails from '../components/config/configdetails.js';
import { Link, Route } from "react-router-dom";
import HeaderNew from "../components/header/headernew.js";

const paddingLeft = (s, c, n) => {
   return (s !== null || s !== '' ? 
      s.length > n ? s : c.repeat(n - s.length) + s
      : 
      c.repeat(n));
   
};


class Layout2 extends Component {
  constructor(props) {
    super(props);
    this.state = { options: [] , open: true };
  }	

  componentDidMount() {
  }

 
  render() { 
			

          return (

          <div>

                  <div>
                    <HeaderNew {...this.props}/>
                  </div>
                     <Beneficiary />

          				  <ThemeGeneratorNew  {...this.props}/>
                    
                    <Footer />

            </div>
          );
  }
};

export default Layout2;

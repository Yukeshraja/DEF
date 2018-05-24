import React, { Component, PropTypes } from "react";
import { Header  } from './layout1';
import ThemeGeneratorNew from "../components/handlebars/ThemeGeneratorNew";
import DropDown, { DropDownWithData } from "../components/dropdown";
import RemitterUI from "../codegenerated/remitter_kb";
import RaisedButton from 'material-ui/RaisedButton';
import Beneficiary from "../codegenerated/beneficiary";
import ThemeConfig from '../components/handlebars/themeConfig.json';
import Handlebars from "handlebars";
import ThemeTemplate from '../components/handlebars/theme.handlebars';
import ConfigUi from '../components/handlebars/config.handlebars';
import MaterialTextField from '../components/textfield/index.js';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import { Card, CardTitle } from "material-ui/Card";
import {List, ListItem} from 'material-ui/List';

import ActionGrade from 'material-ui/svg-icons/action/grade';
import ContentInbox from 'material-ui/svg-icons/content/inbox';
import ContentDrafts from 'material-ui/svg-icons/content/drafts';
import ContentSend from 'material-ui/svg-icons/content/send';
import Subheader from 'material-ui/Subheader';
import Toggle from 'material-ui/Toggle';
import AppBar from 'material-ui/AppBar';
import Divider from 'material-ui/Divider';
import TextField from 'material-ui/TextField';
import SvgIcon from "material-ui/SvgIcon";
import Footer from "../components/footer"
import * as actions from "../actions";
import { connect } from 'react-redux';
import { compose } from "redux";
import { readDataForConfig } from "../components/handlebars/theme";
import ConfigDetails from '../components/config/configdetails.js';

const isEmpty = (obj) => {

  // null and undefined are "empty"
  if (obj == null) return true;
  if (obj.length > 0)    return false;
  if (obj.length === 0)  return true;

  if (typeof obj !== "object") return true;

  return false;
};


class Layout15 extends Component {
  constructor(props) {
    super(props);
    this.state = { options: [] , open: true };
  } 

  componentDidMount() {
  }

  handleClick() {
    const { apiUrl1, reduxFormData } = this.props;
    var apiName = 'beneficiary';
    var apiUrl =`http://192.168.91.51:8001/api/${apiName}`;
    //alert(apiUrl)
    //alert(JSON.stringify(reduxFormData))
    //alert(Object.keys(reduxFormData))

    const { Project: { path } } = this.props;
    const { fileUploadAPI } = this.props;
    const { MenuContext: { data: { menuItems } } , Header : { appTitle }} = this.props;

    //alert(JSON.stringify(this.props))
    const { styles , layout , theme } = this.props;
    //alert(layout)

    var newObject = {}
    var data = JSON.parse(JSON.stringify(reduxFormData));
    //alert(data.layout)
    //alert(isEmpty(data.layout))
    if (isEmpty(data.layout)){
      //alert('Layout Not choosen')
      newObject["layout"] = layout;
    } else {
      //alert(data.layout)
      newObject["layout"] = data.layout;
    }

    if (isEmpty(data.url)){
      //alert('menuItems Not added')
      newObject["menuItems"] = JSON.stringify(menuItems); 

    } else {
      var newMenuItem = {}
      if (!isEmpty(data.anchorText) && 
          !isEmpty(data.link)) {
              //alert(data.link)      
              //alert(menuItems.length)

              newMenuItem["link"] = data.link;
              newMenuItem["url"] = data.url;
              newMenuItem["anchorText"] = data.anchorText;
              newMenuItem["icon"] = data.icon;
              menuItems[menuItems.length]=newMenuItem

              //alert(JSON.stringify(newMenuItem))
              //alert(menuItems.length)
              //alert(JSON.stringify(menuItems))

              newObject["menuItems"] = JSON.stringify(menuItems);
          }
      
    }

    //alert(JSON.stringify(newObject))
    //alert({ path }.path)
    //alert(`${ path }`)
    //alert(`${fileUploadAPI}`)
    //alert(`${ path }` + "/template/src/templates/config.json")
    fetch(`${fileUploadAPI}`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
                "filepath" : `${ path }` + "/template/src/templates/config.json",
                "data" : readDataForConfig(newObject)
      })
    })                                  
  }

  

  handleNewMI() {

    this.setState({ options: [{
            "link": "",
            "anchorText": "",
            "icon": "",
            "url": "",
            "topic" : "New Menu"
          }] });

    //alert('this.state' + this.state.options)       
  }

  render() { 
          const { MenuContext: { data: { menuItems } } , Header : { appTitle }} = this.props;
          //alert('Rendering.. ' + JSON.stringify(this.state.options))     
          return (
              <div>
                  
                  <ConfigDetails {...this.props}/>
              </div>
      );
  }
};


const mapStateToProps = (state, ownProps) => {
  return {
    reduxFormData: state.form.get("ConfigUiData", {})
  };
};

export default connect(mapStateToProps, actions)(Layout15);


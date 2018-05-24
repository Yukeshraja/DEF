import React, { Component, PropTypes } from "react";
import ThemeGeneratorNew from "../handlebars/ThemeGeneratorNew";
import DropDown, { DropDownWithData } from "../dropdown";
import RemitterUI from "../../codegenerated/remitter_kb";
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from	'material-ui/FlatButton';
import Beneficiary from "../../codegenerated/beneficiary";
import ThemeConfig from '../../components/handlebars/themeConfig.json';
import Handlebars from "handlebars";
import ThemeTemplate from '../handlebars/theme.handlebars';
import ConfigUi from '../handlebars/config.handlebars';
import MaterialTextField from '../textfield/index.js';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import { Card, CardTitle } from "material-ui/Card";
import {List, ListItem} from 'material-ui/List';
import {Tabs, Tab} from 'material-ui/Tabs';
import { get, convertJSON, handleResponseError, post } from "../../utils";
//import {FileUpload} from 'material-ui/FileUpload';
//import Upload from 'material-ui-upload/Upload';
//import Upload from 'material-ui-upload/Upload';

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
import Footer from "../footer"
import * as actions from "../../actions";
import { connect } from 'react-redux';
import { compose } from "redux";
import { readDataForConfig } from "../handlebars/theme";


const AngularIcon = props => (
  <SvgIcon {...props}>
    <path d="M2 13.5h14V12H2v1.5zm0-4h14V8H2v1.5zM2 4v1.5h14V4H2z"/>
  </SvgIcon>
);

const paddingLeft = (s, c, n) => {
   return (s !== null || s !== '' ? 
      s.length > n ? s : c.repeat(n - s.length) + s
      : 
      c.repeat(n));
   
};

  const stylestab = {
  headline: {
    fontSize: "24",
    paddingTop: "60px",
    marginBottom: '12',
    fontWeight: "100px"
  }
};

const exampleImageInput = {
    cursor: 'pointer',
    position: 'absolute',
    top: 0,
    bottom: 0,
    right: 0,
    left: 0,
    width: '100%',
    opacity: 0,
  };

const isEmpty = (obj) => {

    // null and undefined are "empty"
    if (obj == null) return true;

    // Assume if it has a length property with a non-zero value
    // that that property is correct.
    if (obj.length > 0)    return false;
    if (obj.length === 0)  return true;

    // If it isn't an object at this point
    // it is empty, but it can't be anything *but* empty
    // Is it empty?  Depends on your application.
    if (typeof obj !== "object") return true;

    return false;
};

                  


class ConfigDetails extends Component {
  constructor(props) {
    super(props);
	this.state = { options: [] , open: true };
	
	this.handleClick = this.handleClick.bind(this);
	this.handleNewMI = this.handleNewMI.bind(this);
	this.handleChange = this.handleChange.bind(this);
	this.holdFileData = this.holdFileData.bind(this);
	this.openFileDialog = this.openFileDialog.bind(this); 
	this.handleChangeFile = this.handleChangeFile.bind(this);
  this.handleFileUpload = this.handleFileUpload.bind(this);
  this.uploadFile = this.uploadFile.bind(this);
  } 

  componentDidMount() {
  }

  
openFileDialog(){
  /* var fileUploadDom = React.findDOMNode(this.refs.fileUpload);
  fileUploadDom.click(); */
};

uploadFile(e) {
        var fd = new FormData();
       // alert("value is..."+e);
        //fd.append('file', this.refs.file.getDOMNode().files[0]);


          var reader = new FileReader();
          var file = e.target.files[0];

           e.preventDefault();

    

        //alert("data is....."+fd);

        post({
            url: 'http://localhost:51218/api/Values/UploadFile',
            data: fd,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function(data){
                alert(data);
            } 
        });
        e.preventDefault()
    }


sendData(url, data) {
  var formData  = new FormData();

  for(var name in data) {
    formData.append(name, data[name]);
  }

  fetch(url, {
    method: 'POST',
    body: formData
  }).then(function (response) {
     
  });
}


handleChangeFile(e){
	console.log("value is..."+e.target.value);
	//this.setState{ path: e.target.value}
	//this.state.path = e.target.value;
}

handleFileUpload({ file }) {
  const files = file;
  alert("file data is..."+files);
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
    //alert(`${ path }` + "/template/src/templates/config.json")
    fetch(`${ fileUploadAPI }` , {
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

    ////alert('this.state' + this.state.options)       
  }

    handleChange = (value) => {
      this.setState({
        value: value,
      });
      };
	  
	  holdFileData = (value)=> {
		  alert("holding File Data");
	  }

  render() { 

    //alert("user is..."+user);
         
          const { MenuContext: { data: { menuItems } } , Header : { appTitle }} = this.props;
          ////alert('Rendering.. ' + JSON.stringify(this.state.options))     
          return (
      
      

        <div>
        <AppBar title="Alti123" iconClassNameLeft="logo" className="logoNew" />
        <Divider />
      
              <Tabs   value={this.state.value}  onChange={this.handleChange}>
			  
				
                    <Tab label="UI Config" value="a">

                    <div  style={{ marginLeft: "auto", marginRight: "auto", width: "70%"}}>
                    
                        <Card>
                              <CardTitle title="App Config" style={{ textTransform : 'capitalize', textAlign : "center"}} />
                        </Card>
                  </div>


                   <div style={{ marginLeft: "auto", marginRight: "auto", width: "70%"}}>


                      <DropDown path={ ["ConfigUiData"] } key="layout" 
                      name="layout" options={ [
                      {valueText:'layout7',labelText:'Layout7'},
                      {valueText:'layout8',labelText:'Layout8'},
                      {valueText:'layout9',labelText:'Layout9'},
                      {valueText:'layout12',labelText:'Layout12'},
                      {valueText:'layout15',labelText:'Layout15'}] }
 />

      

                      <div style={{ marginTop: "5px", width : "100%" }}>
                        <Card>
                              <CardTitle title="MenuItem" style={{ textTransform : 'capitalize', textAlign : "center"}} />
                        </Card>
                      </div>

        <div style={{ marginLeft: "auto", marginRight: "auto", width: "70%"}}>



                      <List>


                                {this.props.MenuContext.data.menuItems.map( (item,index) =>
                                      <div>


                                            <ListItem 
                                                style={{fontSize: 14}}
                                                primaryText={item.anchorText}
                                                leftIcon={<AngularIcon />}
                                                initiallyOpen={<Toggle/>}
                                                primaryTogglesNestedList={true}
                                                nestedItems={[
                                                      <div>
                                                        <TextField name="link" value={item.link} 
                                                        hintText="Enter the menu Items link" style={{ width: "50%"}}/>
                                                        <br />
                                                        <TextField name="anchorText" value={item.anchorText}  
                                                        hintText="Enter the menu Items anchorText" style={{ width: "50%"}}/>
                                                        <br />
                                                        <TextField name="icon" value={item.icon}  
                                                        hintText="Enter the menu Items icon" style={{ width: "50%"}}/>
                                                        <br />
                                                        <TextField name="url" value={item.url}  
                                                        hintText="Enter the menu Items url" style={{ width: "50%"}}/>

                                                      </div>
                                                ]}
                                              />

                                              <Divider />
                                              
                                    </div>          
                                )}

                                

                                {this.state.options.map( (item,index) =>
                                      <div  style={{ marginLeft: "auto", marginRight: "auto", width: "70%"}}>


                                            <ListItem 
                                                style={{fontSize: 14}}
                                                primaryText={item.topic}
                                                leftIcon={<AngularIcon />}
                                                initiallyOpen={<Toggle/>}
                                                primaryTogglesNestedList={true}
                                                nestedItems={[
                                                      <div>
                                                        <MaterialTextField name="link" value={item.link} path={ ["ConfigUiData"] }
                                                        hintText="Enter the menu Items link" style={{ width: "50%"}}/>
                                                        <br />
                                                        <MaterialTextField name="anchorText" value={item.anchorText}  path={ ["ConfigUiData"] }
                                                        hintText="Enter the menu Items anchorText" style={{ width: "50%"}}/>
                                                        <br />
                                                        <MaterialTextField name="icon" value={item.icon}  path={ ["ConfigUiData"] }
                                                        hintText="Enter the menu Items icon" style={{ width: "50%"}}/>
                                                        <br />
                                                        <MaterialTextField name="url" value={item.url}  path={ ["ConfigUiData"] }
                                                        hintText="Enter the menu Items url" style={{ width: "50%"}}/>

                                                      </div>
                                                ]}
                                              />

                                              <Divider />
                                              
                                    </div>          
                                )}                                
                      </List>


      </div>
       <div style = {{leftPadding:"140%"}}>
              <RaisedButton label="AddNew" onClick={this.handleNewMI}/>
              <RaisedButton label="Submit" primary={true}  onClick={this.handleClick}/>
          </div>
       
                    </div>

                    </Tab>
                    

                    <Tab label="Swagger Config" value="b">

                    <div  style={{ marginLeft: "auto", marginRight: "auto", width: "70%"}}>
                      <Card>
                          <CardTitle title="Swagger Config" style={{ textTransform : 'capitalize', textAlign : "center"}} />
                      </Card>
							
						<RaisedButton
						label="Choose File"
						labelPosition="before"
						style={{margin: '12px',borderRadius: '25px'}}
						containerElement="label"
						
            value="Upload" 
            onClick={this.uploadFile}
						>
						 <input type="file" name="file"/>
						</RaisedButton>
					<DropDown path={ ["ConfigUiData"] } key="layout" 
						  name="Select API" options={ [
						  {valueText:'layout7',labelText:'API ONE '},
						  {valueText:'layout8',labelText:'API TWO'},
						  {valueText:'layout9',labelText:'API THREE'},
						  {valueText:'layout12',labelText:'API FOUR'},
						  {valueText:'layout15',labelText:'API FIVE'}] }
					/>
							
							
                    </div>

                    </Tab>
              </Tabs>
        

        <Footer/>
        </div>
          );
  }
};


const mapStateToProps = (state, ownProps) => {
  return {
    reduxFormData: state.form.get("ConfigUiData", {})
  };
};

export default connect(mapStateToProps, actions)(ConfigDetails);


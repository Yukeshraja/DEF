import React , { Component } from 'react';
import MaterialCheckbox, {
  MaterialCheckboxWithData
} from "../components/checkbox/materialcheckbox";
import DropDown, { DropDownWithData } from "../components/dropdown";
import MaterialRadio, { RadioWithData } from "../components/radio/materialradiobutton";
import MaterialTextField from '../components/textfield';
import { Card, CardTitle } from "material-ui/Card";
import RaisedButton from 'material-ui/RaisedButton';
import { connect } from 'react-redux'
import * as actions from "../actions";
import { compose } from "redux";
import { fetchData } from "../components/HOC/fetchData";
import {
  post,
  handleResponseError,
  convertJSON,
  Loader,
  capitalize
} from "../utils";

const style = {
  margin: 12,
};


                 
              
class beneficiary extends React.Component  {

  constructor(props) {
    super(props);
      this.handleClick = this.handleClick.bind(this);
  }


  handleClick() {
    const { apiUrl1, reduxFormData } = this.props;
    var apiName = 'beneficiary';
    var apiUrl =`http://192.168.91.51:8001/api/${apiName}`;
    alert(apiUrl)
    alert(reduxFormData)
    
    post(apiUrl, reduxFormData)
      .then(handleResponseError)
      .then(convertJSON)
      .then(response => {
        this.setState({
          isSubmitting: false,
          errorSubmitting: false,
          submitResponse: response
        });
        this.props.fetchData(
          "tableData",
          `http://192.168.91.51:8001/api/${apiName}`
        );
      })
      .catch(error => {
        console.error(error);
        this.setState({ isSubmitting: false, errorSubmitting: true });
     });
    }

    render() {
        return (
              <div>
                   <Card>
                          <CardTitle key="title" title="beneficiary" style={{  textTransform : 'capitalize', textAlign : "left", background: "grey"}} />
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="address" style={ ["[object Object]"] } name="address" floatingLabelText="ADDRESS1234" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="flag" style={ ["[object Object]"] } name="flag" floatingLabelText="FLAG" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="beneficiaryMobileNumber" style={ {"style":""} } name="beneficiaryMobileNumber" floatingLabelText="BENEFICIARY MOBILE NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="city" style={ {"style":""} } name="city" floatingLabelText="CITY" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="remitterId" style={ ["[object Object]"] } name="remitterId" floatingLabelText="REMITTER ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="mmid" style={ {"style":""} } name="mmid" floatingLabelText="MMID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="branch" style={ {"style":""} } name="branch" floatingLabelText="BRANCH" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="ifscCode" style={ {"style":""} } name="ifscCode" floatingLabelText="IFSC CODE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="sessionToken" style={ {"style":""} } name="sessionToken" floatingLabelText="SESSION TOKEN" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="beneficiaryName" style={ {"style":""} } name="beneficiaryName" floatingLabelText="BENEFICIARY NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="bank" style={ {"style":""} } name="bank" floatingLabelText="BANK" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="bcAgent" style={ {"style":""} } name="bcAgent" floatingLabelText="BC AGENT" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="relationshipId" style={ {"style":""} } name="relationshipId" floatingLabelText="RELATIONSHIP ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="beneficiaryEmailId" style={ {"style":""} } name="beneficiaryEmailId" floatingLabelText="BENEFICIARY EMAIL ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="id" style={ {"style":""} } name="id" floatingLabelText="ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="accountNumber" style={ ["[object Object]"] } name="accountNumber" floatingLabelText="ACCOUNT NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="state" style={ {"style":""} } name="state" floatingLabelText="STATE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="clientName" style={ {"style":""} } name="clientName" floatingLabelText="CLIENT NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["beneficiary"] } key="status" style={ {"style":""} } name="status" floatingLabelText="STATUS" style={{ width : '100%'}}/>
                                    </div>
                              <div>
                                  <RaisedButton label="Submit" primary={true} style={style} onClick={this.handleClick}/>
                              </div>
                      </Card>
                
            </div>  

        )

    }
}

const mapStateToProps = (state, ownProps) => {
  return {
    reduxFormData: state.form.get("beneficiary", {})
  };
};

export default connect(mapStateToProps, actions)(beneficiary);
  

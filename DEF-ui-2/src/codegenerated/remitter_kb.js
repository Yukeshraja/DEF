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

class Remitter extends React.Component  {

  constructor(props) {
    super(props);
      this.handleClick = this.handleClick.bind(this);
  }


  handleClick() {
    const { apiUrl1, reduxFormData } = this.props;
    var apiName = 'remitter';
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
                          <CardTitle key="title" title="REMITTER" style={{ textTransform : 'upppercase'}} />
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="pincode" name="pincode" floatingLabelText="PINCODE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterAddress2" name="remitterAddress2" floatingLabelText="REMITTER ADDRESS2" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterAddress1" name="remitterAddress1" floatingLabelText="REMITTER ADDRESS1" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofIssuePlace" name="idProofIssuePlace" floatingLabelText="ID PROOF ISSUE PLACE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lRemitterAddress" name="lRemitterAddress" floatingLabelText="L REMITTER ADDRESS" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProof" name="idProof" floatingLabelText="ID PROOF" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px', marginLeft : '10px'}}>
                                      <DropDownWithData path={ ["remitter"] } key="lCityName" name="lCityName" url="http://192.168.91.51:8001/api/statelist?text=labelText&value=valueText"/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterName" name="remitterName" floatingLabelText="REMITTER NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterMobileNumber" name="remitterMobileNumber" floatingLabelText="REMITTER MOBILE NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lStateName" name="lStateName" floatingLabelText="L STATE NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="sessionToken" name="sessionToken" floatingLabelText="SESSION TOKEN" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="alternateNumber" name="alternateNumber" floatingLabelText="ALTERNATE NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="cityName" name="cityName" floatingLabelText="CITY NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="stateName" name="stateName" floatingLabelText="STATE NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="bcAgent" name="bcAgent" floatingLabelText="BC AGENT" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="id" name="id" floatingLabelText="ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofIssueDate" name="idProofIssueDate" floatingLabelText="ID PROOF ISSUE DATE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="addressProof" name="addressProof" floatingLabelText="ADDRESS PROOF" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="clientName" name="clientName" floatingLabelText="CLIENT NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofNumber" name="idProofNumber" floatingLabelText="ID PROOF NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lPincode" name="lPincode" floatingLabelText="L PINCODE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofExpiryDate" name="idProofExpiryDate" floatingLabelText="ID PROOF EXPIRY DATE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="status" name="status" floatingLabelText="STATUS" style={{ width : '100%'}}/>
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
    reduxFormData: state.form.get("remitter", {})
  };
};

export default connect(mapStateToProps, actions)(Remitter);

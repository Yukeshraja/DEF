import React , { Component } from 'react';
import MaterialCheckbox, {
  MaterialCheckboxWithData
} from "../components/checkbox/materialcheckbox";
import DropDown, { DropDownWithData } from "../components/dropdown";
import MaterialRadio, { RadioWithData } from "../components/radio/materialradiobutton";
import MaterialTextField from '../components/textfield';
import { Card, CardTitle } from "material-ui/Card";

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

const FieldSet = (props) => {
        return (

                 
              


class capitalize(remitter) extends React.Component  {

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
                                      <MaterialTextField path={ ["remitter"] } key="pincode" style={ {"style":""} } name="pincode" floatingLabelText="PINCODE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterAddress2" style={ {"style":""} } name="remitterAddress2" floatingLabelText="REMITTER ADDRESS2" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterAddress1" style={ {"style":""} } name="remitterAddress1" floatingLabelText="REMITTER ADDRESS1" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofIssuePlace" style={ {"style":""} } name="idProofIssuePlace" floatingLabelText="ID PROOF ISSUE PLACE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lRemitterAddress" style={ {"style":""} } name="lRemitterAddress" floatingLabelText="L REMITTER ADDRESS" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProof" style={ {"style":""} } name="idProof" floatingLabelText="ID PROOF" style={{ width : '100%'}}/>
                                    </div>
                                             <div style={{ padding : '10px', marginLeft : '10px'}}>
                                                <DropDownWithData path={ ["remitter"] } key="lCityName" style={ {"style":""} } name="lCityName" url="http://192.168.94.119:5555/api/statelist?text=labelText&value=valueText"/>
                                             </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterName" style={ {"style":""} } name="remitterName" floatingLabelText="REMITTER NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="remitterMobileNumber" style={ {"style":""} } name="remitterMobileNumber" floatingLabelText="REMITTER MOBILE NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lStateName" style={ {"style":""} } name="lStateName" floatingLabelText="L STATE NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="sessionToken" style={ {"style":""} } name="sessionToken" floatingLabelText="SESSION TOKEN" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="alternateNumber" style={ {"style":""} } name="alternateNumber" floatingLabelText="ALTERNATE NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="cityName" style={ {"style":""} } name="cityName" floatingLabelText="CITY NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="stateName" style={ {"style":""} } name="stateName" floatingLabelText="STATE NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="bcAgent" style={ {"style":""} } name="bcAgent" floatingLabelText="BC AGENT" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="id" style={ {"style":""} } name="id" floatingLabelText="ID" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofIssueDate" style={ {"style":""} } name="idProofIssueDate" floatingLabelText="ID PROOF ISSUE DATE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="addressProof" style={ {"style":""} } name="addressProof" floatingLabelText="ADDRESS PROOF" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="clientName" style={ {"style":""} } name="clientName" floatingLabelText="CLIENT NAME" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofNumber" style={ {"style":""} } name="idProofNumber" floatingLabelText="ID PROOF NUMBER" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="lPincode" style={ {"style":""} } name="lPincode" floatingLabelText="L PINCODE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="idProofExpiryDate" style={ {"style":""} } name="idProofExpiryDate" floatingLabelText="ID PROOF EXPIRY DATE" style={{ width : '100%'}}/>
                                    </div>
                                    <div style={{ padding : '10px'}}>
                                      <MaterialTextField path={ ["remitter"] } key="status" style={ {"style":""} } name="status" floatingLabelText="STATUS" style={{ width : '100%'}}/>
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

export default connect(mapStateToProps, actions)(capitalize(remitter));
        )
}



export default FieldSet;

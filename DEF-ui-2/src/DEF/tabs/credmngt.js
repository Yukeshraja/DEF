import React from 'react';
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
    TableFooter
} from 'material-ui/Table';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import TextField from '../../components/textfield';
import RaisedButton from 'material-ui/RaisedButton';
import { Toolbar, ToolbarGroup } from 'material-ui/Toolbar';
import { connect } from 'react-redux'
//import { compose } from "redux";
//import { Field, reduxForm, formValueSelector } from 'redux-form';
import * as actions from "../../actions";
import $ from 'jquery';
import Snackbar from 'material-ui/Snackbar';

class FetchCredentials extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            fixedHeader: true,
            fixedFooter: true,
            selectable: false,
            deselectOnClickaway: true,
            showCheckboxes: false,
            stripedRows: true,
            showRowHover: true,
            height: '300px',
            openEditModal: false,
            openDeleteModal: false,
            openCreateModal: false,
            result: [],
            typeValue: '',
            createObject: {
                credentialName: '',
                type: '',
                userName: '',
                password: '',
                sshKey: '',
                secretKey: ''
            },
            editObject: {},
            deleteObject: {},
            upFlag: false,
            upsFlag: false,
            upssFlag: false,
            showSnackBarSuccess : false,
            showSnackBarFailure : false
        };
  
        this.createNewCredentials = this.createNewCredentials.bind(this);
        this.updateCredentials = this.updateCredentials.bind(this);
        this.deleteCredentials = this.deleteCredentials.bind(this);
        this.setInitialValues = this.setInitialValues.bind(this);
        this.dropdownChange = this.dropdownChange.bind(this);
        this.formRequestObject = this.formRequestObject.bind(this);
        this.handleOpenCreateModal = this.handleOpenCreateModal.bind(this);
        this.handleOpenEditModal = this.handleOpenEditModal.bind(this);
        this.handleOpenDeleteModal = this.handleOpenDeleteModal.bind(this);        
    }

    componentDidMount() {
		
		const creds = 'getAllCredentials'
        var url = process.env.REACT_APP_API_URLS+'/'+creds;
        fetch(url, {
            method: 'GET',
            mode: 'cors'
        }).then(response => response.json())
            .then(response => {
                const { result } = response;
                this.setState({ result });
            });
    }

    handleTouchTapSuccess = () => {
        this.setState({
            showSnackBarSuccess: true,
        });
      };

      handleTouchTapFailure = () => {
        this.setState({
            showSnackBarFailure: true,
        });
      };

      handleRequestClose = () => {
        this.setState({
            showSnackBarSuccess: false,            
            showSnackBarFailure: false
        });
      };

    dropdownChange = (event, index, value) => {
        this.state.typeValue = value;
        this.setInitialValues(value);
    };

    setInitialValues(typeValue) {
        if (typeValue === "up") {
            this.setState({ upFlag: true, upsFlag: false, upssFlag: false });
        } else if (typeValue === "ups") {
            this.setState({ upsFlag: true, upFlag: false, upssFlag: false });
        } else if (typeValue === "upss") {
            this.setState({ upssFlag: true, upsFlag: false, upFlag: false });
        }
    }

    formRequestObject(reduxFormData, setObject) {
        let reqObject = setObject;
        
        Object.entries(reduxFormData._root.entries).forEach(([key, value]) => {
            if (value[0] === "credentialName") {
                reqObject.credentialName = value[1];
            } if (value[0] === "sshKey") {
                reqObject.sshKey = value[1];
            } if (value[0] === "secretKey") {
                reqObject.secretKey = value[1];
            } if (value[0] === "userName") {
                reqObject.userName = value[1];
            } if (value[0] === "password") {
                reqObject.password = value[1];
            }
        });
        return reqObject;
    };

    handleOpenCreateModal = () => {             
        this.setState({ openCreateModal: true });
    };
   
    createNewCredentials() {
       // var url = "http://localhost:8035/demo/createCredential";
        const  reduxFormCreateData  = this.props.reduxFormCreateData;
        var reqObject = {};
        reqObject = this.formRequestObject(reduxFormCreateData, this.state.createObject);
        reqObject.type = this.state.typeValue;
        //this.state.createObject = reqObject;
        
       //alert(JSON.stringify(reqObject));

      // console.log(JSON.stringify(this.state.createObject));
		const createcred = 'createCredential'
        var url = process.env.REACT_APP_API_URLS+'/'+createcred;
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(
                reqObject
            )
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                this.componentDidMount();
                if(response.status == 'SUCCESS'){
                    this.handleTouchTapSuccess();
                }else{
                    this.handleTouchTapFailure();                
                }
                //window.location.reload();                
            }).catch(error => {
                console.error(error);
                this.handleTouchTapFailure();                                
            });
        this.handleClose();
    }

    handleOpenEditModal = (item) => {
        //alert(JSON.stringify(item));
        this.state.editObject = item;
        this.state.typeValue = item.type;
        this.setState({ openEditModal: true });
        //alert(JSON.stringify(this.state.editObject));     
        //alert(JSON.stringify(this.state.typeValue));   
        this.setInitialValues(item.type);
    };
   
    updateCredentials() {
        //var url = "http://localhost:8035/demo/updateCredential/" + this.state.editObject.credentialName;
        var reduxFormEditData  = this.props.reduxFormEditData;
        var reqObject = {};

        if($.isEmptyObject(reduxFormEditData)){
            reqObject = this.state.editObject;
        }else{
            reqObject = this.formRequestObject(reduxFormEditData, this.state.editObject);
            reqObject.credentialName = this.state.editObject.credentialName;
            reqObject.type = this.state.typeValue;            
        }

        //this.state.editObject = reqObject;
        //this.setState({ editObject: reqObject });

        //alert(JSON.stringify(reqObject));
		const credName = 'updateCredential'
        var url = process.env.REACT_APP_API_URLS+'/'+credName+'/'+  this.state.editObject.credentialName;

        fetch(url, {
            method: 'PUT',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(
                reqObject
            )
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                if(response.status == 'SUCCESS'){
                    this.handleTouchTapSuccess();
                }else{
                    this.handleTouchTapFailure();                
                }
                //window.location.reload();
            }).catch(error => {
                console.error(error);
                this.handleTouchTapFailure();                                
            });
        this.handleClose();
    }

    handleOpenDeleteModal = (item) => {
        this.state.deleteObject = item;
        this.setState({ openDeleteModal: true });
    };

    deleteCredentials() {
       // var url = "http://localhost:8035/demo/deleteCredential/" + this.state.deleteObject.credentialName;
		
		const deleteName = 'deleteCredential'
        var url = process.env.REACT_APP_API_URLS+'/'+deleteName+'/'+  this.state.deleteObject.credentialName;
        fetch(url, {
            method: 'DELETE',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                this.componentDidMount();
                if(response.status == 'SUCCESS'){
                    this.handleTouchTapSuccess();
                }else{
                    this.handleTouchTapFailure();                
                }            
               // window.location.reload();                
            }).catch(error => {
                console.error(error);
                this.handleTouchTapFailure();                                
            });
        this.handleClose();
    };

    handleClose = () => {
        this.state.createObject = {};
        this.state.editObject = {};
        this.state.value = '';
        this.setState({ openEditModal: false, openDeleteModal: false, openCreateModal: false});
    };

    render() {
        const style = {
            customWidth: { width: 200 },
            centerPlaced : {verticalAlign: 'top', height: 'auto', paddingTop: '.5em'},
            centerText : {textAlign: 'center', verticalAlign: 'top', height: 'auto', paddingTop: '1.5em'}
        };

        const actionsCreate = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                ref="CreateButton"
                label="Save"
                primary={true}
                onClick={this.createNewCredentials}
            />
        ];
        const actionsEdit = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                ref="EditButton"
                label="Save"
                primary={true}
                onClick={this.updateCredentials}
            />
        ];
        const actionsDelete = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                label="Discard"
                primary={true}
                onClick={this.deleteCredentials}
            />
        ];

        return (
            <div>
                 <div>
                    <Snackbar
                        open={this.state.showSnackBarSuccess}
                        message="SUCCESS!!"
                        autoHideDuration={2000}
                        onRequestClose={this.handleRequestClose}
                    />
                    <Snackbar
                        open={this.state.showSnackBarFailure}
                        message="FAILURE!!"
                        autoHideDuration={2000}
                        onRequestClose={this.handleRequestClose}
                    />
                </div>
                <Toolbar>
                    <ToolbarGroup>
                        <RaisedButton label="Add System" primary={true} onClick={this.handleOpenCreateModal} />
                    </ToolbarGroup>
                </Toolbar>
                <Dialog
                    title="Create"
                    actions={actionsCreate}
                    modal={true}
                    open={this.state.openCreateModal}
                    autoScrollBodyContent={true}>
                    <div>
                        <TextField
                            id="credentialId"
                            formName="credentialName"
                            floatingLabelText="Credential ID"
                            path={["newCredentials"]} key="credentialName" name="credentialName"
                        /><br />
                        <SelectField
                            id="type"
                            floatingLabelText="Select Type"
                            style={style.customWidth}
                            value={this.state.typeValue}
                            onChange={this.dropdownChange}
                            name="type"
                            autoWidth={true}>
                            <MenuItem value="up" primaryText="Hostname with Username & Password" />
                            <MenuItem value="ups" primaryText="Hostname with SSH Key" />
                            <MenuItem value="upss" primaryText="Secret Text" />
                        </SelectField>
                        <br />
                        {this.state.upFlag ? <div>
                            <TextField
                                id="username"
                                formName="userName"
                                floatingLabelText="UserName"
                                path={["newCredentials"]} key="userName" name="userName"
                            /><br />
                            <TextField
                                id="password"
                                formName="password"
                                hintText="Password"
                                floatingLabelText="Password"
                                type="password"
                                path={["newCredentials"]} key="password" name="password"
                            /><br />
                        </div> : this.state.upsFlag ? <div>
                            <TextField
                                hintText="Enter SSH Key"
                                floatingLabelText="SSH Key"
                                formName="sshKey"
                                multiLine={true}
                                rows={4}
                                path={["newCredentials"]} key="sshKey" name="sshKey"
                            /><br />
                        </div> : this.state.upssFlag ? <div>
                            <TextField
                                id="secretText"
                                formName="secretKey"
                                hintText="Secret Text"
                                floatingLabelText="Secret Text"
                                path={["newCredentials"]} key="secretKey" name="secretKey"
                            /><br />
                        </div> : null}
                    </div>
                </Dialog>

                <Table style={style.centerPlaced}
                    fixedHeader={this.state.fixedHeader}
                    fixedFooter={this.state.fixedFooter}
                    selectable={this.state.selectable}>
                    <TableHeader
                        displaySelectAll={this.state.showCheckboxes}
                        adjustForCheckbox={this.state.showCheckboxes}>
                        <TableRow>
                            <TableHeaderColumn style={style.centerText}>Credential ID</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>SSH key</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>Username</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>Password</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>Secret Text</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>Edit</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText}>Delete</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody
                        displayRowCheckbox={this.state.showCheckboxes}
                        deselectOnClickaway={this.state.deselectOnClickaway}
                        showRowHover={this.state.showRowHover}
                        stripedRows={this.state.stripedRows}>credentialName
                        {this.state.result.map((item, key) => (
                            <TableRow key={key}>
                                <TableRowColumn style={style.centerText}>{item.credentialName}</TableRowColumn>
                                <TableRowColumn style={style.centerText}>{item.sshKey}</TableRowColumn>
                                <TableRowColumn style={style.centerText}>{item.userName}</TableRowColumn>
                                <TableRowColumn style={style.centerText}>{item.password}</TableRowColumn>
                                <TableRowColumn style={style.centerText}>{item.secretKey}</TableRowColumn>
                                <TableRowColumn style={style.centerPlaced}><RaisedButton label="Edit" onClick={() => {this.handleOpenEditModal(item)}}/></TableRowColumn>
                                <TableRowColumn style={style.centerPlaced}><RaisedButton label="Delete" onClick={() => {this.handleOpenDeleteModal(item)}} /></TableRowColumn>
                                <Dialog
                                    title="Update Credentials"
                                    actions={actionsEdit}
                                    modal={true}
                                    open={this.state.openEditModal}
                                    autoScrollBodyContent={true}
                                >
                                    <div>
                                        <TextField
                                            id="credentialIdEdit"
                                            value={this.state.editObject.credentialName}
                                            disabled={true}
                                            formName="credentialName"
                                            floatingLabelText="Credential ID"
                                            path={["editCredentials"]} key="credentialName" name="credentialName"
                                        /><br />
                                        <SelectField
                                            id="typeEdit"
                                            floatingLabelText="Select Type"
                                            value={this.state.typeValue}
                                            onChange={this.dropdownChange}
                                            //formName="type"
                                            name="type"
                                            style={style.customWidth}
                                           // path={["editCredentials"]} key="type" 
                                            autoWidth={true}>
                                            <MenuItem value="up" primaryText="Hostname with Username & Password" />
                                            <MenuItem value="ups" primaryText="Hostname with SSH Key" />
                                            <MenuItem value="upss" primaryText="Secret Text" />
                                        </SelectField>
                                        <br />
                                        {this.state.upFlag ? <div>
                                            <TextField
                                                id="usernameEdit"
                                                value={this.state.editObject.userName}
                                                formName="userName"
                                                floatingLabelText="UserName"
                                                path={["editCredentials"]} key="userName" name="userName"
                                            /><br />
                                            <TextField
                                                id="passwordEdit"
                                                hintText="Password"
                                                value={this.state.editObject.password}
                                                formName="password"
                                                floatingLabelText="Password"
                                                type="password"
                                                path={["editCredentials"]} key="password" name="password"
                                            /><br />
                                        </div> : this.state.upsFlag ? <div>
                                            <TextField
                                                id="sshKeyEdit"
                                                hintText="Enter SSH Key"
                                                floatingLabelText="SSH Key"
                                                formName="sshKey"
                                                value={this.state.editObject.sshKey}
                                                path={["editCredentials"]} key="sshKey" name="sshKey"
                                                multiLine={true}
                                                rows={4}
                                            /><br />
                                        </div> : this.state.upssFlag ? <div>
                                            <TextField
                                                id="secretTextEdit"
                                                value={this.state.editObject.secretKey}
                                                path={["editCredentials"]} key="secretKey" name="secretKey"
                                                formName="secretKey"
                                                hintText="Secret Text"
                                                floatingLabelText="Secret Text"
                                            /><br />
                                        </div> : null}
                                    </div>
                                </Dialog>
                                <Dialog
                                    actions={actionsDelete}
                                    modal={false}
                                    open={this.state.openDeleteModal}
                                    onRequestClose={this.handleClose}
                                >
                                    Are you sure you want to permanently delete {this.state.deleteObject.credentialName} ?
                                    </Dialog>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter
                        adjustForCheckbox={this.state.showCheckboxes}
                    ></TableFooter>
                </Table>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        reduxFormCreateData: state.form.get("newCredentials", {}),
        reduxFormEditData : state.form.get("editCredentials", {})
    };
};

export default connect(mapStateToProps, actions)(FetchCredentials);
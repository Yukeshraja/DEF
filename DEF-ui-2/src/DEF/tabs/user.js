import React from 'react';
import DropDownMenu from 'material-ui/DropDownMenu';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';

export default class UserForm extends React.Component {

state = {
  userName:'',
  projectName:'',
  selectValue:''
}

handleChange = (event, index, value) => this.setState({selectValue: value});

render(){
return(<div>
      <Card>
    
    <CardTitle title="User Form" />
    <CardText>
    <strong>Enter the UserName and ProjectName</strong>
    </CardText>
    
   <SelectField
          floatingLabelText="Select Type"
          value={this.state.selectValue}
          onChange={this.handleChange}
          style={{marginTop: 0,marginLeft:10, width:'100%',maxWidth:700,margin:'auto'}}
        >
          <MenuItem value={1} primaryText="LDAP" />
          <MenuItem value={2} primaryText="eloud" />
        </SelectField>
   <br/>
    <TextField value={this.state.userName}   onChange={(e, newValue) => this.setState({ userName: newValue})}   hintText="Enter UserName" style={{marginTop: 0, marginLeft: 10, width:'100%',maxWidth:700,margin:'auto'}} />
    <TextField  value={this.state.projectName}   onChange={(e, newValue) => this.setState({ projectName: newValue})}    hintText="Enter Project Name" style={{marginTop: 0, marginLeft: 10, width:'100%',maxWidth:700,margin:'auto'}} />
    <CardActions>
      <FlatButton label="update" />
      <FlatButton label="cancel" />
    </CardActions>
  </Card>
  </div>);
  }
}

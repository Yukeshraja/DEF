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
import RaisedButton from 'material-ui/RaisedButton';
import Snackbar from 'material-ui/Snackbar';

export default class FetchDemo extends React.Component {
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
        result: [],
        showSnackBarSuccess : false,
        showSnackBarFailure : false        
      };  
      
      this.startJob = this.startJob.bind(this);
      this.stopJob = this.stopJob.bind(this);
      this.viewLogs = this.viewLogs.bind(this);
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

    startJob = (item) => {
        
        // alert(JSON.stringify(item))
        var url = process.env.REACT_APP_API_URLS+'/startJob/'+item.Name;
        // alert(url)
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            },
            body: item.Name
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                if(response.status == 'SUCCESS'){
                    this.handleTouchTapSuccess();
                }else{
                    this.handleTouchTapFailure();                
                }
                // alert(response.status)           
            }).catch(error => {
                this.handleTouchTapFailure();                                
                console.error(error);
            });
    }

    stopJob = (item) => {
        // alert(JSON.stringify(item))
        var url = process.env.REACT_APP_API_URLS+'/stopJob/'+item.Name;
        // alert(url)        
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            },
            body: item.Name
        }).then(response => response.json())
            .then(response => {
                console.log(response);  
                if(response.status == 'SUCCESS'){
                    this.handleTouchTapSuccess();
                }else{
                    this.handleTouchTapFailure();                
                }                // alert(response.status)              
            }).catch(error => {
                this.handleTouchTapFailure();                                
                console.error(error);
            });
    }

    viewLogs = (item) => {
        // alert(JSON.stringify(item))
        var url = process.env.REACT_APP_API_URLS+'/jobStatus/'+item.Name;
        // alert(url)   
        fetch(url, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(response => {
                console.log(response);   
                alert(response.returnValue)             
            }).catch(error => {
                console.error(error);
            });
    }

    componentDidMount(){
//        var url = "http://localhost:8900/demo/getAllJob";
        //var url = "https://1-platform.altimetrik.com/demo/getAllJob";
		const getJobs = 'getAllJob'
        var url = process.env.REACT_APP_API_URLS+'/'+getJobs;
        fetch(url, {
                method: 'GET',
                mode : 'cors'
        }).then(response => response.json())
                .then(response => {
        
                console.log(response);  
                const {result} = response;
                console.log(result);
                this.setState({result});
                });
        }

    render() {   
         const style = {
             centerPlaced : {textAlign : "center"}
         };
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
                <Table
                    height={this.state.height}
                    fixedHeader={this.state.fixedHeader}
                    fixedFooter={this.state.fixedFooter}
                    selectable={this.state.selectable}>
                    <TableHeader
                     displaySelectAll={this.state.showCheckboxes}
                     adjustForCheckbox={this.state.showCheckboxes}>
                        <TableRow>
                            <TableHeaderColumn>Name</TableHeaderColumn>
                            <TableHeaderColumn>SCM Link</TableHeaderColumn>
                            <TableHeaderColumn>Action</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody 
                        displayRowCheckbox={this.state.showCheckboxes}
                        deselectOnClickaway={this.state.deselectOnClickaway}
                        showRowHover={this.state.showRowHover}
                        stripedRows={this.state.stripedRows}>
                        {this.state.result.map((item, key)=>(
                            <TableRow key = {key}>
                                <TableRowColumn>{item.Name}</TableRowColumn>
                                <TableRowColumn>{item.SCM.Link}</TableRowColumn>
                                <TableRowColumn><button className="">Edit</button></TableRowColumn>
                                <TableRowColumn><RaisedButton buttonStyle={style.centerPlaced} onClick={() => this.startJob(item)} label="Start Job"/></TableRowColumn>
                                <TableRowColumn><RaisedButton onClick={() => this.stopJob(item)} label="Stop Job"/></TableRowColumn>
                                <TableRowColumn><RaisedButton onClick={() => this.viewLogs(item)} label="View Logs"/></TableRowColumn>
                                {/* <TableRowColumn> <IconButton> <FontIcon className="fa fa-pencil-square-o" aria-hidden="true"
                                  style={iconStyles}/></IconButton></TableRowColumn> */}
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
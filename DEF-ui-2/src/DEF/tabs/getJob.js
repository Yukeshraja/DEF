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
import FlatButton from 'material-ui/FlatButton';
import Snackbar from 'material-ui/Snackbar';
import Dialog from 'material-ui/Dialog';
import MenuItem from 'material-ui/MenuItem';
import { Router, Route, Link, IndexRoute, hashHistory, browserHistory } from 'react-router';
import MyComp from './MyComp';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';

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
            showSnackBarSuccess: false,
            showSnackBarFailure: false,
            showViewLogs: false,
            viewLogsValue : {}
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

    handleOpenViewLogs = (response) => {
        this.setState({ viewLogsValue : response, showViewLogs: true });
    };

    handleCloseViewLogs = () => {
        this.setState({ showViewLogs: false });
    };

    startJob = (item) => {

        // alert(JSON.stringify(item))
        var url = process.env.REACT_APP_API_URLS + '/startJob/' + item.Name;
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
                if (response.status == 'SUCCESS') {
                    this.handleTouchTapSuccess();
                } else {
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
        var url = process.env.REACT_APP_API_URLS + '/stopJob/' + item.Name;
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
                if (response.status == 'SUCCESS') {
                    this.handleTouchTapSuccess();
                } else {
                    this.handleTouchTapFailure();
                }                // alert(response.status)              
            }).catch(error => {
                this.handleTouchTapFailure();
                console.error(error);
            });
    }

    // ask for `router` from context
    contextTypes: {
        router: React.PropTypes.object
    }


    editJob = (item) =>  {
        this.props.history.push('/view/developer')
    }

    viewLogs = (item) => {
        // alert(JSON.stringify(item))
        var url = process.env.REACT_APP_API_URLS + '/jobStatus/' + item.Name;
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
                if(response.status === 'SUCCESS'){
                    this.handleOpenViewLogs(response.returnValue);                    
                }else if(response.status === 'FAILURE'){
                    this.handleTouchTapFailure();
                }
            }).catch(error => {
                console.error(error);
            });
    }

    componentDidMount() {
        //        var url = "http://localhost:8900/demo/getAllJob";
        //var url = "https://1-platform.altimetrik.com/demo/getAllJob";
        const getJobs = 'getAllJob'
        var url = process.env.REACT_APP_API_URLS + '/' + getJobs;
        fetch(url, {
            method: 'GET',
            mode: 'cors'
        }).then(response => response.json())
            .then(response => {

                console.log(response);
                const { result } = response;
                // alert(JSON.stringify(result));
                console.log(result);
                this.setState({ result });
            });
    }



    render() {



        const style = {
            centerPlaced: { verticalAlign: 'top', height: 'auto', paddingTop: '.5em' },
            centerText: { textAlign: 'center', verticalAlign: 'top', height: 'auto', paddingTop: '1.5em' }
        };

        const actions = [
            <FlatButton
              label="Close"
              primary={true}
              onClick={this.handleCloseViewLogs}
            />,
            ];

        var Text = require('react-format-text');
            
        return (
            <div>
                
                    <Dialog
                        actions={actions}
                        modal={false}
                        open={this.state.showViewLogs}
                        onRequestClose={this.handleClose}
                        autoScrollBodyContent={true}
                    >
                        <Text>
                            {this.state.viewLogsValue.consoleOutputText}
                            {/*JSON.stringify(this.state.viewLogsValue.consoleOutputText, null, 2)*/}
                        </Text>
                    </Dialog>
                
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
                <Table style={style.centerPlaced}
                    fixedHeader={this.state.fixedHeader}
                    fixedFooter={this.state.fixedFooter}
                    selectable={this.state.selectable}>
                    <TableHeader
                        displaySelectAll={this.state.showCheckboxes}
                        adjustForCheckbox={this.state.showCheckboxes}>
                        <TableRow>
                            <TableHeaderColumn style={style.centerText} width={5}>Name</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText} width={100}>SCM Link</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText} width={70}>Status</TableHeaderColumn>
                            <TableHeaderColumn style={style.centerText} width={20} colSpan="4">Action</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody
                        displayRowCheckbox={this.state.showCheckboxes}
                        deselectOnClickaway={this.state.deselectOnClickaway}
                        showRowHover={this.state.showRowHover}
                        stripedRows={this.state.stripedRows}>
                        {this.state.result.map((item, key) => (
                            <TableRow key={key}>
                                <TableRowColumn width={150} style={style.centerText}
                                        title={item.Name}>{item.Name}</TableRowColumn>
                                <TableRowColumn style={style.centerText} title={item.SCM != null ? item.SCM.Link : ''}>
                                    {item.SCM != null ? item.SCM.Link : ''}

                                </TableRowColumn>
                                <TableRowColumn width={150} style={style.centerText}
                                         title={item.status}>{item.status}</TableRowColumn>
                                <TableRowColumn style={style.centerPlaced} width={70} title="Edit Job">

                                    <MyComp jobName={item.Name}/>

                                </TableRowColumn>
                                <TableRowColumn style={style.centerPlaced} width={70} title="Start Job">

                                    <IconButton onClick={() => this.startJob(item)} label="Start Job">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M8.59 16.34l4.58-4.59-4.58-4.59L10 5.75l6 6-6 6z"/></svg>                               
                                    </IconButton>  
                                </TableRowColumn>
                                <TableRowColumn style={style.centerPlaced} width={70} title="Stop Job">

                                    <IconButton onClick={() => this.stopJob(item)} label="Stop Job">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M6 6h12v12H6z"/></svg>                              
                                    </IconButton>  
                                </TableRowColumn>
                                <TableRowColumn style={style.centerText} width={120} title="View Job">
                                    <u>
                                        <a onClick={() => this.viewLogs(item)} label="View Logs" >View Logs</a>
                                    </u>    
                                </TableRowColumn>
                                
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



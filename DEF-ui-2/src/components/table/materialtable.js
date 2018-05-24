import React from "react";
import { connect } from "react-redux";
import muiThemeable from 'material-ui/styles/muiThemeable';
import path from "path";
import * as actions from "../../actions";
import Dialog from "material-ui/Dialog";
import Dimensions from "react-dimensions";
import RaisedButton from "material-ui/RaisedButton";
import Snackbar from "material-ui/Snackbar";
import { fetchCompdata } from "../HOC/fetchData";
import { FieldGroup } from "../field/fieldset";
import { compose } from "redux";

import FloatingActionButton from "material-ui/FloatingActionButton";
import ContentAdd from "material-ui/svg-icons/content/add";

import { Table, Column, Cell } from "fixed-data-table";
import "fixed-data-table/dist/fixed-data-table.min.css";
import "./custom_table.css";
import R from "ramda";
import { Card, CardTitle } from "material-ui/Card";

class TableUI extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      open: false,
      currentIndex: 0,
      newForm: false,
      snackBar: false
    };
    this.getFieldForm = this.getFieldForm.bind(this);
    this.addForm = this.addForm.bind(this);
    this.handleSnackBar = this.handleSnackBar.bind(this);
    this.handleRowClick = this.handleRowClick.bind(this);
    this.handleClose = this.handleClose.bind(this);
  }

  handleSnackBar(payload, event, index) {
    this.setState({ snackBar: payload });
  }
  handleRowClick(event, index) {
    this.setState({ open: true, currentIndex: index });
  }

  handleClose() {
    this.setState({ open: false, newForm: false });
  }

  getFieldForm() {
    const { url , tableData, fieldSetURL } = this.props;
    //alert(this.props.fileUploadAPI);
    const { currentIndex, newForm } = this.state;
    const apiName = path.basename(url);
    if (newForm) {
      return (
        <FieldGroup
          apiUrl={url}
          newForm={true}
          url={fieldSetURL}
          fileUploadAPI={this.props.fileUploadAPI}
          data={{api : apiName, swagger : 'oneplatform'}}
          showButton={true}
          recurse={true}
        />
      );
    }
    return (
      <FieldGroup
        defaultData={tableData[currentIndex]}
        newForm={false}
        url={fieldSetURL}
        fileUploadAPI={this.props.fileUploadAPI}
        data={{api : apiName, swagger : 'oneplatform'}}
        apiUrl={url}
        showButton={true}
        recurse={true}
      />
    );
  }

  addForm() {
    this.setState({ open: true, newForm: true });
  }

  getWidth(customWidth, fixedWidth) {
    return customWidth < fixedWidth ? customWidth : fixedWidth;
  }

  render() {
    const { tableData = [] , url , containerWidth , muiTheme : { palette :  { primary1Color, alternateTextColor } }} = this.props;
    const apiName = path.basename(url);
    const modalActions = [
      <RaisedButton
        label="Close"
        primary={true}
        onTouchTap={this.handleClose}
      />
    ];

    const columnCount = R.compose(
      R.length,
      R.filter(keyName => !R.is(Object, tableData[0][keyName])),
      R.keys
    )(tableData[0]);
    console.log("containerWidth",containerWidth);
    return (
      <div> 
        <div style={{ marginTop: "5px" }}>
            <Card>
                  <CardTitle title={apiName} style={{ textTransform : 'capitalize', textAlign : "left"}} />
            </Card>
        </div>
        <div style={{ marginTop: "10px" }}>


          <Table
            rowsCount={tableData.length}
            rowHeight={50}
            headerHeight={40}
            onRowMouseEnter={this.handleSnackBar.bind(this, true)}
            onRowClick={this.handleRowClick}
            rowClassNameGetter={() => "table_row"}
            width={Math.min(containerWidth,columnCount*200)}
            maxHeight={500}
            style={{ marginTop: "10px" }}
          >
            {R.compose(
              R.map(keyName => (
                <Column
                  header={
                    <Cell style={{ backgroundColor : primary1Color, color : alternateTextColor}}>{keyName.toUpperCase()}</Cell>
                  }
                  width={200}
                  key={keyName}
                  cell={props => (
                    <Cell {...props}>
                      {tableData[props.rowIndex][keyName]}
                    </Cell>
                  )}
                />
              )),
              R.filter(keyName => !R.is(Object, tableData[0][keyName])),
              R.keys
            )(tableData[0])}
          </Table>
          <FloatingActionButton
            style={{ position: "fixed", bottom: "10%", right: "2%" }}
            onClick={this.addForm}
          >
            <ContentAdd />
          </FloatingActionButton>
          <Snackbar
            style={{ position: "fixed", bottom: "10%" }}
            open={this.state.snackBar}
            message="Delete this Row"
            action="Delete"
            autoHideDuration={4000}
            onRequestClose={() => this.setState({ snackBar: false })}
          />
          <Dialog
            open={this.state.open}
            modal={true}
            actions={modalActions}
            contentStyle={{
              height: "600px",
              marginBottom: "100px",
              width: "100%",
              maxWidth: "none"
            }}
            onRequestClose={this.handleClose}
            title={apiName.toUpperCase()}
            autoDetectWindowHeight={true}
            autoScrollBodyContent={true}
          >
            {this.getFieldForm()}
          </Dialog>
        </div>
      </div>
    );
  }
}
// const getColumns = R.compose(
//   R.map(columnData => {
//     return <TableRowColumn key={columnData}>{columnData}</TableRowColumn>;
//   }),
//   R.values
// );
// const getRows = R.map(rowData => {
//   console.log("rowData", R.values(rowData));
//   return <TableRow key={rowData.id}>{getColumns(rowData)}</TableRow>;
// });
//
// const getHeader = R.compose(
//   R.map((headerData, index) => (
//     <TableHeaderColumn key={headerData}>
//       {headerData.toUpperCase()}
//     </TableHeaderColumn>
//   )),
//   R.keys
// );
// return (
//   <Table style={{width: '200%'}}>
//     <TableHeader>
//       <TableRow>
//         {getHeader(tableData[0])}
//       </TableRow>
//     </TableHeader>
//     <TableBody>
//       {getRows(tableData)}
//     </TableBody>
//   </Table>
// );

TableUI.defaultProps = {
  width: 1000
};

const mapStateToProps = (state, ownProps) => {
  return {
    isFetching: state.api[ownProps.keyName].isFetching,
    error: state.api[ownProps.keyName].error.message,
    response: state.api[ownProps.keyName].responseData
  };
};

// export default connect(mapStateToProps,actions)(TableUI);

export default compose(
  connect(mapStateToProps, actions),
  fetchCompdata("GET", {}),
  muiThemeable(),
  Dimensions()
)(TableUI);

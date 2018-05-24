import React from "react";
import Table from "../components/table";
import * as actions from "../actions";
import { connect } from "react-redux";

class Show extends React.Component {
  componentDidMount() {
    this.props.fetchData("table", "http://localhost:3004/posts");
  }
  render() {
    const { table: { isFetching, responseData, error } } = this.props.api;
    if (isFetching) {
      return <div> Loading..</div>;
    } else if (error) {
      return <div style={{ color: "red" }}>Error fetching</div>;
    } else {
      return <Table rowData={responseData} />;
    }
  }
}

const mapStateToProps = state => ({
  api: state.api
});

export default connect(mapStateToProps, actions)(Show);

import React from "react";

const Table = props => {
  const { tableData = [] } = props;
  return (
    <table className="table_custom">
      <Header headers={Object.keys(tableData[0] || {})} />
      <tbody>
        {tableData.map((item, index) => <Row columnData={item} key={index} />)}
      </tbody>
    </table>
  );
};

export const Header = props => (
  <thead>
    <tr>
      {props.headers.map((item, index) => <th style={{padding: '10px'}} key={index}>{item}</th>)}
    </tr>
  </thead>
);

export const Row = props => {
  return (
    <tr>
      {Object.keys(props.columnData).map((item, index) => (
        <td style={{padding: '10px'}} key={index}>{props.columnData[item]}</td>
      ))}
    </tr>
  );
};

export default Table;

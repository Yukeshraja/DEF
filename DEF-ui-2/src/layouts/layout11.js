import React from 'react';
import Layout from "../components/layout";
import MaterialTable from "../components/table/materialtable";
import { Route } from "react-router-dom";

class Layout11 extends React.Component {
  renderRoutes(expand) {
    const { MenuContext: { data: { menuItems } } } = this.props;
    const routes = menuItems.map(({ url, link }, index) => {
      return (
        <Route
          path={link}
          key={index}
          render={({ match: { params } }) => {
            return (
              <MaterialTable
                url={url}
                keyName="tableData"
                fieldSetURL={this.props.fieldSetURL}
                width={expand ? 1072 : 1288}
              />
            );
          }}
        />
      );
    });

    return routes.concat(
      <Route
        path="/"
        key={routes.length}
        render={() => {
          return (
            <MaterialTable
              url={menuItems[0].url}
              keyName="tableData"
              fieldSetURL={this.props.fieldSetURL}
              width={expand ? 1072 : 1288}
            />
          );
        }}
        exact={true}
      />
    );
  }
  render() {
    const {
      MenuContext: { data: { menuItems }, menuType = "icon" }
    } = this.props;
    const { Header: { appTitle } } = this.props;
    return (
      <Layout menuItems={menuItems} menuType={menuType} appTitle={appTitle} >
          {this.renderRoutes(false)}
      </Layout>
    );
  }
}
export default Layout11;

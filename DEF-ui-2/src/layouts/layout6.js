import React from "react";
import { Route } from "react-router-dom";
import Header from "../components/header";
import Footer from "../components/footer";
import Menu from "../components/menu";
import View from "../views";
import { FieldUI } from "../components/field";
import { fetchData } from "../components/HOC/fetchData";
import { FieldGroup } from "../components/field/fieldset";

import altilogo from "../static/img/alti.png";

export const Layout6 = props => {
  const { MenuContext } = props;
  const { data } = MenuContext;
  const FieldGroupAPI = fetchData(
    "http://192.168.94.119:2233/api/genUIFieldset",
    {
      api: "transactioninfo",
      filepath: "/home/platform/Documents/Workspace/Api-Generation/src/main/resources/swagger.json"
    },
    "post"
  )(FieldGroup);
  return (
    <div>
      <Header
        customstyles={{ width: "100%", margin: "0px" }}
        appName="Altimetrik"
        imgUrl={altilogo}
      />
      <div>
        <Menu
          customstyles={{
            minHeight: "527px",
            display: "inline-block",
            float: "none",
            height: "100%",
            verticalAlign: "top"
          }}
          {...data}
        />
        <View customstyles={{ display: "inline-block", float: "none" }}>
          <Route path="/view/:templateName" component={FieldUI} exact={true} />
          <Route path="/view/fieldset/:templateName" component={FieldGroup} />
        </View>
      </div>
      <Footer
        customstyles={{ position: "fixed", bottom: "0px", width: "100%" }}
      />
    </div>
  );
};

import React from "react";
import { Route } from 'react-router-dom';
import  Header  from "../components/header";
import  Footer  from "../components/footer";
import  Menu  from "../components/menu";
import  View from "../views";
import Field from '../components/field';
import FieldSet from '../components/field/fieldset';

import altilogo from '../static/img/alti.png';

export const Layout4 = props => {
  const { MenuContext } = props;
  const { data } = MenuContext;
  return (
    <div>
      <Header customstyles={{width: '100%', margin: '0px'}} appName="Altimetrik" imgUrl={altilogo}/>
      <Menu  customstyles={{minHeight: '527px'}} {...data} />
      <View>
        <Route path="/" component={Field} exact={true}/>
        <Route path="/view/:templateName" component={Field} exact={true}/>
        <Route path="/view/fieldset/:templateName" component={FieldSet} />
      </View>
      <Footer customstyles={{position: 'fixed', bottom :'0px',width:'100%'}}/>
    </div>
  );
};

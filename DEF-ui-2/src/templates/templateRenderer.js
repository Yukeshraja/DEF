import React , { Component } from 'react';
// this is the config..entry
import config from './config.json';
import { Layout1 } from '../layouts/layout1';
import Layout2 from '../layouts/layout2';
import { Layout3 } from '../layouts/layout3';
import { Layout4 } from '../layouts/layout4';
import { Layout6 } from '../layouts/layout6';
import Layout7  from '../layouts/layout7';
import Layout8  from '../layouts/layout8';
import Layout9  from '../layouts/layout9';
import Layout10 from '../layouts/layout10';
import Layout11  from '../layouts/layout11';
import Layout12  from '../layouts/layout12';
import Layout14  from '../layouts/layout14';
import Layout15  from '../layouts/layout15';

class TemplateRender extends Component {
          // Based on the config get the layout
          getLayout(layout, config){
                  switch(layout){
                        case "layout1":
                              return <Layout1 {...config} />
                        case "layout2":
                              return <Layout2 {...config} />
                        case "layout3":
                              return <Layout3 {...config} />
                        case "layout4":
                              return <Layout4 {...config} />
                        case "layout6":
                              return <Layout6 {...config} />
                        case "layout7":
                              return <Layout7 {...config} />
                        case "layout8":
                              return <Layout8 {...config} />
                        case "layout9":
                              return <Layout9 {...config} />
                        case "layout10":
                              return <Layout10 {...config} />
                        case "layout11":
                              return <Layout11 {...config} />
                        case "layout12":
                              return <Layout12 {...config} />
                        case "layout14":
                              return <Layout14 {...config} />
                        case "layout15":
                              return <Layout15 {...config} />                                  
                        default :
                              return <Layout1 {...config} />
                  }
          }
          render(){
                  const { GlobalConfigutation , LayoutConfig } = config;
                  const { styles , layout , theme } = GlobalConfigutation;
                  alert("layout  is..."+layout);
                  return(
                    <div className={styles.style} style={theme}>
                        {this.getLayout(layout,Object.assign({},GlobalConfigutation,LayoutConfig))}
                    </div>
                  )
          }
}

export default TemplateRender;

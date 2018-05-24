import React, { Component, PropTypes } from "react";
import {List, ListItem} from 'material-ui/List';
import componentData from '../collapser/components.json';
import ActionGrade from 'material-ui/svg-icons/action/grade';
import ContentInbox from 'material-ui/svg-icons/content/inbox';
import ContentDrafts from 'material-ui/svg-icons/content/drafts';
import ContentSend from 'material-ui/svg-icons/content/send';
import Subheader from 'material-ui/Subheader';
import Toggle from 'material-ui/Toggle';
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import darkBaseTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import lightBaseTheme from 'material-ui/styles/baseThemes/lightBaseTheme'; 
import AppBar from 'material-ui/AppBar';
import Divider from 'material-ui/Divider';


import {
  cyan500, cyan700,
  pinkA200, pinkA500, pinkA700,
  pink200, pink500, pink700,
  grey100, grey300, grey400, grey500,
  yellow600, yellow400, yellow200, 
  white, darkBlack, fullBlack,
  blue500
} from 'material-ui/styles/colors';

const muiTheme = getMuiTheme({
  ripple: {
    color: blue500,

  },
  palette: {
    textColor: pinkA200,
    primary1Color: yellow200,
    primary2Color: yellow400,  
  },
  fontFamily: 'Roboto, sans-serif',
  fontSize: '8',
});


const paddingLeft = (s, c, n) => {
   return (s !== null || s !== '' ? 
      s.length > n ? s : c.repeat(n - s.length) + s
      : 
      c.repeat(n));
   
};

class ListExampleNested extends Component {


  constructor(props) {
    super(props);
    this.state = { options: [] , open: true };
  }


  componentDidMount() {
    const { serviceUrl } = this.props;
    this.setState({ isFetching: true });

        var component = this;

        fetch(serviceUrl, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
         .then( (response) => {
            return response.json()
         })
         .then( (json) => {
            component.setState({
               options: json
            })
            console.log('parsed json', json)
         })
         .catch( (ex) => {
            console.log('parsing failed', ex)
         })
         console.log(this.state.options)
         //alert(this.state.options)
  }

  state = {
    open: false,
  };

  handleToggle = () => {
    this.setState({
      open: !this.state.open,
    });
  };

  handleNestedListToggle = (item) => {
    this.setState({
      open: item.state.open,
    });
  };

  render() {
    return (
           <div>
                <MuiThemeProvider muiTheme={muiTheme}>
                      <List>


                                {this.state.options.map( (item,index) =>
                                      <div>


                                            <ListItem 
                                                style={{fontSize: 14}}
                                                primaryText={paddingLeft(item.name,' ', 20) +' - '+ paddingLeft(item.desc,' ', 50) }
                                                leftIcon={<ContentInbox />}
                                                initiallyOpen={<Toggle/>}
                                                primaryTogglesNestedList={true}
                                                nestedItems={[
                                                      <div>
                                                        <span>
                                                              <pre>
                                                                <div className="inlineText">{JSON.stringify({item}, null, 5)}</div>
                                                              </pre>
                                                        </span>
                                                      </div>
                                                ]}
                                              />

                                              <Divider />
                                              
                                    </div>          
                                )}
                      </List>
     
                 </MuiThemeProvider>

               </div>
      );
  }
}


ListExampleNested.propTypes = {
  serviceUrl: PropTypes.string.isRequired
};

export default ListExampleNested;

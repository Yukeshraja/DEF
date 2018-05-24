import React, { Component, PropTypes } from "react";
import componentData from './components.json';
import Accordion from '../accordion';
import { handleResponseError, get, convertJSON } from "../../utils";
import Collapsible from '../../materialize/Collapsible';
import CollapsibleItem from '../../materialize/CollapsibleItem';
import Breadcrumb from '../../materialize/Breadcrumb';
import MenuItem from '../../materialize/MenuItem';

class Collapser extends Component {
  constructor(props) {
    super(props);
    this.state = { options: [], error: "", isFetching: false };
  }


  componentDidMount() {
    const { serviceUrl } = this.props;
    this.setState({ isFetching: true });

        var url = 'http://192.168.94.119:2222/componentrepo/api/userData'

        var component = this;

        fetch(url, {
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
         if (this.state.options == '' )
            this.state.options = componentData;
  }

  render() {
    return (
    <div  className="cardMoveTop">
      <div  className="vAlignCenter">
        <Breadcrumb>
          <MenuItem  className="col s12 m6 offset-m3"><br />Components</MenuItem>
        </Breadcrumb>
      </div>

      <table className="table_custom" width="100%">

        <tbody>
           <Collapsible>
              {this.state.options.map( (item,index) =>


                          <CollapsibleItem className="textEllipsis"
                            header={item.name +' - '+ item.desc }
                            icon="filter_drama"
                            scroll="auto">
                            <span>
                                  <pre>
                                    <div className="inlineText">{JSON.stringify({item}, null, 5)}</div>
                                  </pre>
                            </span>
                          </CollapsibleItem>




              )}

          </Collapsible>
        </tbody>
      </table>
    </div>
    )
  }

}

Collapser.propTypes = {
  serviceUrl: PropTypes.string.isRequired
};

export default Collapser;

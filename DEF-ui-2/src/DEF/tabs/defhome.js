import React from 'react';

export default class DefHome extends React.Component {
    constructor(props) {
      super(props);
  
      this.state = {
        fixedHeader: true,
      };   
    }

    render() {      
        return (
            <div>
                <h1>home context</h1>
            </div>
        )
      }
}
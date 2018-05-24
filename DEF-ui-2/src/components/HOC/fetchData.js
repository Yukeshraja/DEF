import React, { Component } from "react";
import { isEmpty, isNil } from "ramda";
import {
  getDisplayName,
  get,
  post,
  handleResponseError,
  convertJSON,
  Loader,
  makeCancelable
} from "../../utils";
export const fetchCompdata = (type, data) => {
  return BaseComponent => {
    class FetchComponent extends Component {
      constructor(props) {
        super(props);
        this.fetchAPI = this.fetchAPI.bind(this);
      }
      fetchAPI(url, keyName) {
        if (type === "GET") {
          this.props.fetchData(keyName, url);
        } else {
          this.props.postData(keyName, url, data);
        }
      }
      componentDidMount() {
        const { keyName, url } = this.props;
        this.fetchAPI(url, keyName);
      }
      componentWillReceiveProps(nextProps) {
        if (this.props.url !== nextProps.url)
          this.fetchAPI(nextProps.url, nextProps.keyName);
      }
      render() {
        const { isFetching, response = {}, keyName, error , ...baseProps} = this.props;
        if (!isFetching && !error && (isEmpty(response) || isNil(response))) {
          return (
            <h1 style={{ textAlign: "center" }}>Api Response is Empty..</h1>
          );
        } else if (isFetching) {
          return <Loader message="Fetching data.." />;
        } else if (error) {
          return <div style={{ color: "red" }}>Error in Fetching..</div>;
        } else {
          return <BaseComponent {...baseProps} {...{ [keyName]: response }} />;
        }
      }
    }
    FetchComponent.displayName = `FetchData(${getDisplayName(BaseComponent)})`;
    // console.log(getDisplayName(BaseComponent));
    return FetchComponent;
  };
};

export const fetchData = (type, keyName) => {
  return BaseComponent => {
    class FetchComponent extends Component {
      constructor(props) {
        super(props);
        this.state = { isFetching: false, error: false, response: {} };
        this.getPromise = null;
        this.postPromise = null;
      }

      componentDidMount() {
        const { url , data } = this.props;
        this.setState({ isFetching: true });
        if (type === "GET") {
          this.getPromise = makeCancelable(get(url));
          this.getPromise
            .promise
            .then(handleResponseError)
            .then(convertJSON)
            .then(responseData => {
              this.setState({
                response: { [keyName]: responseData },
                isFetching: false
              });
            })
            .catch(e => {
              console.error(e.message,e.stack);
              this.setState({ error: true, isFetching: false });
            });
        } else {

          this.postPromise = makeCancelable(post(url, data));
          this.postPromise
            .promise
            .then(handleResponseError)
            .then(convertJSON)
            .then(responseData => {
              // console.log(responseData, "responseData");
              this.setState({
                response: { [keyName]: responseData },
                isFetching: false
              });
            })
            .catch(e => {
              // Avoiding, Unwanted set state on unmounte component
              if(e.isCanceled) return;
              console.error(e.message,e.stack);
              this.setState({ error: true, isFetching: false });
            });
        }
      }
      componentWillUnmount(){
            /// Avoid race conditions
                if(type === 'GET') this.getPromise.cancel();
                else if(type === 'POST') this.postPromise.cancel();
                else return;
      }
      render() {
        const { isFetching, response = {}, error } = this.state;
        const {  data , ...baseProps} = this.props;
        if (
          !isFetching &&
          !error &&
          (isEmpty(response[keyName]) || isNil(response[keyName]))
        ) {
          return (
            <h1 style={{ textAlign: "center" }}> No response to display!</h1>
          );
        } else if (isFetching) {
          return <Loader message="Fetching data.." />;
        } else if (error) {
          return <div style={{ color: "red" }}>Error in Fetching..</div>;
        } else {
          console.log(baseProps,"baseProps");
          return <BaseComponent {...baseProps} {...response} />;
        }
      }
    }
    FetchComponent.defaultProps = {
                data :  {}
    };
    FetchComponent.displayName = `FetchData(${getDisplayName(BaseComponent)})`;
    return FetchComponent;
  };
};

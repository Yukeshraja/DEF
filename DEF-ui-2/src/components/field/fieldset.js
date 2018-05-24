import React, { Component } from "react";
import Field, { FieldUI } from "./index";
import { appendClassName, camelCase } from "../../utils";
import { Card, CardTitle } from "material-ui/Card";
import { connect } from "react-redux";
import * as actions from "../../actions";
import RaisedButton from "material-ui/RaisedButton";
import R from "ramda";
import {
  post,
  handleResponseError,
  convertJSON,
  Loader,
  capitalize
} from "../../utils";
import { compose } from "redux";
import { fetchData } from "../HOC/fetchData";
import { fromJS } from "immutable";
import fieldSetTemplate from "../../codegenerated/fieldSet.handlebars";
import { flattenData } from "../../utils";


const FieldSet = props => {
  const { match = { params: {} } } = props;
  const { templateName = "fieldset" } = match.params;

  const renderField = fieldData => {
    return Object.keys(fieldData).reduce((acc, current, index) => {
      switch (current) {
        case "Title":
          return acc.concat([<h1>{fieldData[current]}</h1>]);
        case "Field":
          if (Array.isArray(fieldData[current]))
            return acc.concat(<Field fieldData={fieldData[current]} />);
          else return acc.concat([<Field fieldData={[fieldData[current]]} />]);
        case "FieldSet":
          return acc.concat(renderField(fieldData[current]));
        default:
          return acc;
      }
    }, []);
  };
  try {
    const selectedData = require(`./${templateName}.json`);
    return (
      <div className={appendClassName("fieldset", props.className)}>
        {renderField(selectedData)}
      </div>
    );
  } catch (e) {
    return (
      <div style={{ color: "red" }}>
        Check file name
      </div>
    );
  }
};

export const getClassName = type => {
  const columnTest = /column(\d+)/;
  if (type === "flowlayout" || type === "panel") {
    return type;
  } else if (columnTest.test(type)) {
    const columnCount = columnTest.exec(type);
    return columnCount[1];
  } else {
    return "flowlayout";
  }
};

const validateField = (re, value) => re.test(value);

const genFieldSet = (fieldSetData = {}, path) => {
  return Object.keys(fieldSetData).reduce((acc, curr, index) => {
    if (curr === "fieldSet") {
      if (Array.isArray(fieldSetData[curr])) {
        if (fieldSetData[curr].length === 0)
          return acc.concat(
            genFieldSet(fieldSetData[curr][0], path.concat("fieldSet[0]"))
          );
        else
          return acc
            .concat([
              {
                compName: capitalize(fieldSetData[curr][0].title),
                fieldComp: `<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.${path
                  .concat("fieldSet[0]")
                  .join(".")}}} recurse={false} />`
              }
            ])
            .concat(
              genFieldSet(fieldSetData[curr][0], path.concat("fieldSet[0]"))
            );
      } else
        return acc
          .concat([
            {
              compName: capitalize(fieldSetData[curr].title),
              fieldComp: `<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.${path
                .concat("fieldSet")
                .join(".")}}} recurse={false} />`
            }
          ])
          .concat(genFieldSet(fieldSetData[curr], path.concat("fieldSet")));
    } else return acc;
  }, []);
};

// const getValue = R.reduce((acc, curr) => {
//   return acc[curr];
// });

export class FieldGroupUI extends Component {
  constructor(props) {
    super(props);
    this.renderField = this.renderField.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.handleCodeGen = this.handleCodeGen.bind(this);
    this.flattenFormData = this.flattenFormData.bind(this);
    this.state = {
      isSubmitting: false,
      errorSubmitting: false,
      submitResponse: {},
      formData: fromJS({})
    };
  }

  flattenFormData(formData) {
    return formData.map(eachValue => {
      return !eachValue.has("value")
        ? this.flattenFormData(eachValue)
        : eachValue.get("value", "");
    });
  }
  handleClick() {
    const { apiUrl, reduxFormData } = this.props;
    const apiName = R.compose(R.last, R.split("/"))(apiUrl);
    this.setState({ isSubmitting: true, submitResponse: {} });
    post(apiUrl, reduxFormData)
      .then(handleResponseError)
      .then(convertJSON)
      .then(response => {
        this.setState({
          isSubmitting: false,
          errorSubmitting: false,
          submitResponse: response
        });
        this.props.fetchData(
          "tableData",
          apiUrl
        );
      })
      .catch(error => {
        console.error(error);
        this.setState({ isSubmitting: false, errorSubmitting: true });
      });
  }

  handleCodeGen() {
    const { apiUrl, fieldSetData, url , fileUploadAPI} = this.props;
    const templateData = flattenData(fieldSetData, []);
    //alert(JSON.stringify(templateData))
    const apiName = R.compose(R.last, R.split("/"))(apiUrl);
    const genCode = fieldSetTemplate({ fieldSetData : templateData });
    alert(genCode)
    post(fileUploadAPI, {

      filepath: `D:/uimodeling626/uimodeling/template/src/codegenerated/${apiName}.js`,
     // filepath: `/app/P1/phabricator/uimodel/uimodeling/template/src/codegenerated/${apiName}.js`,
      data: genCode
    })
      .then(convertJSON)
      .then(data => {
        console.log(data);
      })
      .catch(e => {
        console.error(e.stack);
      });
  }
  componentDidMount() {
    const {
      defaultData = {},
      fieldSetData: { fieldSet: { title } },
    } = this.props;
    if (R.isEmpty(defaultData)) this.props.clearForm(title);
    else this.props.setInitialValues(title, defaultData);
  }
  renderField(fieldData, className, defaultData = fromJS({}), path) {
    return Object.keys(fieldData).reduce((acc, current, index) => {
      switch (current) {
        case "title":
          return acc.concat([
            <CardTitle
              key="title"
              title={fieldData[current]}
              style={{ textTransform: "uppercase" }}
            />
          ]);
        case "field":
          if (Array.isArray(fieldData[current]))
            return acc.concat([
              <div
                style={!isNaN(className) ? { columnCount: className } : {}}
                key={fieldData[current][0].name}
              >
                {fieldData[current].map((item, index) => {
                  if (R.test(/.*foreign.*/, item.description)) return null;
                  return (
                    <FieldUI
                      key={index}
                      formName={fieldData.title}
                      response={Object.assign(item)}
                      path={path}
                      className={!isNaN(className) ? "flowlayout" : className}
                    />
                  );
                })}
              </div>
            ]);
          else
            return acc.concat([
              <FieldUI
                key={index}
                response={fieldData[current]}
                path={path}
                className={className}
              />
            ]);
        case "fieldSet":
          if (!this.props.recurse) return acc;
          const subFieldData = fieldData[current];
          if (Array.isArray(subFieldData))
            return acc.concat(
              subFieldData.map((item, fieldIndex) =>
                this.renderField(
                  item,
                  subFieldData.type,
                  defaultData[camelCase(item.title)],
                  path.concat(camelCase(item.title))
                )
              )
            );
          else
            return acc.concat(
              this.renderField(
                subFieldData,
                subFieldData.type,
                defaultData[camelCase(subFieldData.title)],
                path.concat(camelCase(subFieldData.title))
              )
            );
        default:
          return acc;
      }
    }, []);
  }
  render() {
    const {
      fieldSetData: { fieldSet = {} },
      label = "Submit",
      showButton = false,
      path = [],
      defaultData
    } = this.props;
    const { isSubmitting, errorSubmitting, submitResponse } = this.state;
    const { type, title } = fieldSet;
    const className = getClassName(type);
    if (isSubmitting) {
      return <Loader message="Submitting Form..." />;
    } else if (errorSubmitting) {
      return (
        <div style={{ color: "red" }}>
          Error submitting check network connection..
        </div>
      );
    } else if (
      !isSubmitting &&
      !errorSubmitting &&
      !R.isEmpty(submitResponse)
    ) {
      return (
        <div style={{ color: "green", textAlign: "center" }}>
          <h1>Successfully Submitted!</h1>
        </div>
      );
    } else {
      return (
        <Card style={{ marginTop: "10px" }}>
          {this.renderField(
            fieldSet,
            className,
            defaultData,
            R.isEmpty(path) ? [title] : path
          )}
          {showButton
            ? <RaisedButton
                primary={true}
                label={label}
                onClick={this.handleClick}
                style={{
                  width: "40%",
                  display: "inline-block",
                  marginRight: "10px"
                }}
              />
            : null}
          <RaisedButton
            primary={true}
            label="Generate Code"
            style={{ width: "40%", display: "inline-block" }}
            onClick={this.handleCodeGen}
          />
        </Card>
      );
    }
  }
}

const mapStateToProps = (state, ownProps) => {
  const { fieldSetData: { fieldSet: { title } } } = ownProps;

  return {
    reduxFormData: state.form.get(title)
  };
};

export const FieldGroup = compose(
  fetchData("POST", "fieldSetData"),
  connect(mapStateToProps, actions)
)(FieldGroupUI);

export default FieldSet;

import "whatwg-fetch";
import React from "react";
import R from 'ramda';
import loader from "../static/img/ring.gif";

export const handleResponseError = response => {
  if (!response.ok) {
    throw new Error(response.statusText);
  }
  return response;
};

export const capitalize = str => str.charAt(0).toUpperCase() + str.slice(1);

const upperCase=(inputString) => {
        if(!inputString) return '';
        return inputString.toUpperCase();
}

const lowerCase=(inputString) => {
        if(!inputString) return '';
        return inputString.toLowerCase();
}


export const flattenData = (fieldSetData, fieldPath = []) => {
  return Object.keys(fieldSetData).reduce((acc, curr, index) => {
    const currentValue = fieldSetData[curr];
    if (curr === "fieldSet") {
      if (R.isEmpty(currentValue)) return acc;
      else if (Array.isArray(currentValue)) {
        return acc
          .concat(
            R.map(
              item =>
                R.merge(R.omit([curr], item), {
                  path: fieldPath.concat(item.title)
                }),
              currentValue
            )
          )
          .concat(
            R.flatten(
              currentValue.map(item =>
                flattenData(item, fieldPath.concat(item.title))
              )
            )
          );
      } else {
        return acc
          .concat(
            R.merge(R.omit([curr], currentValue), {
              path: fieldPath.concat(currentValue.title)
            })
          )
          .concat(
            flattenData(currentValue, fieldPath.concat(currentValue.title))
          );
      }
    } else return acc;
  }, []);
};


export const updateLast = R.curry((updateWith,inputList) => {
            const lastItem = R.last(inputList);
            const lastIndex = R.length(inputList)-1;
            return R.update(lastIndex,updateWith(lastItem),inputList);
})


export const camelCase = str => str.replace(/_\w/gi, v => v.slice(-1).toUpperCase());


export const makeCancelable = (promise) => {
  let hasCanceled_ = false;

  const wrappedPromise = new Promise((resolve, reject) => {
    promise
      .then((val) =>
        hasCanceled_ ? reject({isCanceled: true}) : resolve(val)
      )
      .catch((error) =>
        hasCanceled_ ? reject({isCanceled: true}) : reject(error)
      );
  });

  return {
    promise: wrappedPromise,
    cancel() {
      hasCanceled_ = true;
    },
  };
};

export const convertJSON = response => {
  return response.json();
};
export const post = (url, data) => {
  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });
};

export const get = url => {
  return fetch(url, {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  });
};

export const classNames = cssMap => {
  return Object.keys(cssMap).reduce(
    (acc, current) => {
      return cssMap[current] ? `${acc} ${current}` : acc;
    },
    ""
  );
};

export const Loader = props => {
  return (
    <div>
      <h1 style={{textTransform : 'uppercase',padding : '10px',fontSize : '30px'}}>{props.message}</h1>
      <img src={loader} alt="Loading.." />
    </div>
  );
};

export const appendClassName = (currentclassName, newClassname) => {
  if (newClassname) return `${currentclassName} ${newClassname}`;
  else return currentclassName;
};

export const getDisplayName = WrappedComponent => {
  return WrappedComponent.displayName || WrappedComponent.name || "Component";
};

export const wrapComponentName = (WrappedComponent,wrapText) => {
        return `${wrapText}(${getDisplayName(WrappedComponent)})`;
}

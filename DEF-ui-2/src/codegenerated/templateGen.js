const handlebars = require("handlebars");
const fieldSetData = require("./remitter.json");
const fs = require("fs");
const R = require("ramda");
const capitalize = str => str.charAt(0).toUpperCase() + str.slice(1);

const flattenData = (fieldSetData, fieldPath = []) => {
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

const genFieldSet = (fieldSetData = {}, path, fieldPath) => {
  console.log(fieldPath, "fieldPath", path, fieldSetData);
  return Object.keys(fieldSetData).reduce((acc, curr, index) => {
    if (curr === "fieldSet") {
      if (Array.isArray(fieldSetData[curr])) {
        if (fieldSetData[curr].length === 0)
          return acc.concat(
            genFieldSet(
              fieldSetData[curr][0],
              path.concat("fieldSet[0]"),
              fieldPath
            )
          );
        else
          return acc
            .concat([
              {
                compName: capitalize(fieldSetData[curr][0].title),
                fieldComp: `<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.${path
                  .concat("fieldSet[0]")
                  .join(
                    "."
                  )}}} path={[${fieldPath
                  .concat(`'${fieldSetData[curr][0].title}'`)
                  .join(",")}]} recurse={false}/>`
              }
            ])
            .concat(
              genFieldSet(
                fieldSetData[curr][0],
                path.concat("fieldSet[0]"),
                fieldPath.concat(`'${fieldSetData[curr][0].title}'`)
              )
            );
      } else
        return acc
          .concat([
            {
              compName: capitalize(fieldSetData[curr].title),
              fieldComp: `<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.${path
                .concat("fieldSet")
                .join(
                  "."
                )}}} recurse={false} path={[${fieldPath
                .concat(`'${fieldSetData[curr].title}'`)
                .join(",")}]} />`
            }
          ])
          .concat(
            genFieldSet(
              fieldSetData[curr],
              path.concat("fieldSet"),
              fieldPath.concat(`'${fieldSetData[curr].title}'`)
            )
          );
    } else return acc;
  }, []);
};


const upperCase=(inputString) => {
        if(!inputString) return '';
        return inputString.toUpperCase();
}

const lowerCase=(inputString) => {
        if(!inputString) return '';
        return inputString.toLowerCase();
}

fs.readFile("./fieldSet.handlebars", "utf8", (err, data) => {
  if (err){
        console.error(err);
        return; 
  }
  handlebars.registerHelper('upperCase',upperCase);
  handlebars.registerHelper('lowerCase',lowerCase);
  handlebars.registerHelper('if_eq', function(a, b, opts) {
    if(a === b) // Or === depending on your needs
        return opts.fn(this);
    else
        return opts.inverse(this);
});
  handlebars.registerHelper('capitalize', function(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  });
handlebars.registerHelper('json', (obj) => JSON.stringify(obj));
  const template = handlebars.compile(data);
  const templateData = flattenData(fieldSetData, []);
  console.log(templateData,JSON.stringify(templateData));
  const renderString = template({ fieldSetData : templateData });
  fs.writeFile("./generatedCode.js", renderString, err => {
    if (err) {
      console.error(err);
      return;
    } else console.log("Done");
  });
});

import { shallow } from "enzyme";
import React from "react";
import Radio from "./index";

let RadioButton = null;
let RadioButtonWithProps = null;

describe("Radio", () => {
  beforeEach(() => {
    RadioButton = shallow(<Radio />);
    RadioButtonWithProps = shallow(
      <Radio
        name="test"
        values={[
          { value: "test1", labelText: "Test1" },
          { value: "test2", labelText: "Test2" }
        ]}
      />
    );
  });
  it("has no input type radio buttons in case of default options", () => {
    expect(RadioButton.find('input[type="radio"]').length).toEqual(1);
  });
  it("has current number of input radio buttons based on the options", () => {
    expect(RadioButtonWithProps.find('input[type="radio"]').length).toEqual(2);
  });
  it("gets checked when clicked on the radio button", () => {
    RadioButtonWithProps.find('input[type="radio"]')
      .first()
      .simulate("change", { target: { name: "test", value: "test1" } });
    const expectedCheck = [true, false];
    RadioButtonWithProps.find('input[type="radio"]').forEach((item, index) => {
      expect(item.prop("checked")).toEqual(expectedCheck[index]);
    });
    RadioButtonWithProps.find('input[type="radio"]')
      .last()
      .simulate("change", { target: { name: "test", value: "test2" } });
    const expectedCheckNext = [false, true];
    RadioButtonWithProps.find('input[type="radio"]').forEach((item, index) => {
      expect(item.prop("checked")).toEqual(expectedCheckNext[index]);
    });
  });
  it("has correct state value set when clicked", () => {
    RadioButtonWithProps.find('input[type="radio"]')
      .last()
      .simulate("change", { target: { name: "test", value: "test2" } });
    expect(RadioButtonWithProps.state("test")).toEqual("test2");
    RadioButtonWithProps.find('input[type="radio"]')
      .first()
      .simulate("change", { target: { name: "test", value: "test1" } });
    expect(RadioButtonWithProps.state("test")).toEqual("test1");
  });

  it("has proper label text provided", () => {
    expect(RadioButtonWithProps.text()).toContain("Test1");
    expect(RadioButtonWithProps.text()).toContain("Test2");
  });
  it("calls the changeCallback with the current state object", () => {
    const callback = jest.fn();
    RadioButtonWithProps.setProps({ changeCallback: callback });
    RadioButtonWithProps.find('input[type="radio"]')
      .last()
      .simulate("change", { target: { name: "test", value: "test2" } });
    expect(callback).toBeCalled();
    expect(callback).toBeCalledWith({ test : 'test2'});
  });
});

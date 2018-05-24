import { shallow } from "enzyme";
import React from "react";
import Checkbox from "./index";

let CheckboxShallow = null;
let CheckboxShallowWithProps = null;

describe("Checkbox", () => {
  beforeEach(() => {
    CheckboxShallow = shallow(<Checkbox />);
    CheckboxShallowWithProps = shallow(
      <Checkbox
        options={[
          { name: "test", labelText: "Test" },
          { name: "test1", labelText: "Test1" }
        ]}
      />
    );
  });
  it("has a input type checkbox with defaultProps", () => {
    expect(
      CheckboxShallow.find('input[type="checkbox"]').length
    ).toBeGreaterThan(0);
  });
  it("has a correct number of checkboxes given the props", () => {
    expect(
      CheckboxShallowWithProps.find('input[type="checkbox"]').length
    ).toEqual(2);
  });

  it("gets checked when its clicked", () => {
    const Checkboxes = CheckboxShallowWithProps.find('input[type="checkbox"]');
    const first = Checkboxes.first();
    first.simulate("change", { target: { name: "test", checked: true } });
    console.log(first.props());
    // const expectedBool = [true, false];
    expect(CheckboxShallowWithProps.state("test")).toBeTruthy();
  });
});

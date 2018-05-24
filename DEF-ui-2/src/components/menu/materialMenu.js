import React, { PropTypes, Component } from "react";
import { List, ListItem } from "material-ui/List";
import { Link } from "react-router-dom";
import ContentInbox from "material-ui/svg-icons/content/inbox";
import ActionGrade from "material-ui/svg-icons/action/grade";
import ContentSend from "material-ui/svg-icons/content/send";
import ContentAdd from "material-ui/svg-icons/content/add";
import ContentArchive from "material-ui/svg-icons/content/archive";
import ImageEdit from "material-ui/svg-icons/image/edit";
import ImageBook from "material-ui/svg-icons/action/book";
import ImageCached from "material-ui/svg-icons/action/cached";
import ImageEject from "material-ui/svg-icons/action/eject";
import R from "ramda";


const IconMap = {
  ContentInbox: <ContentInbox />,
  ActionGrade: <ActionGrade />,
  ContentSend: <ContentSend />,
  ContentAdd: <ContentAdd />,
  ContentArchive: <ContentArchive />,
  ImageEdit: <ImageEdit />,
  ImageBook: <ImageBook />,
  ImageCached: <ImageCached />,
  ImageEject: <ImageEject />
};



class Menu extends Component {
  constructor(props) {
    super(props);
    this.state = { expand: false };
    this.handleToggle = this.handleToggle.bind(this);
  }
  handleToggle() {
    this.setState({ expand: !this.state.expand });
  }
  render() {
    const expandedStyle = R.merge(
      { width: "4%", overflow: "hidden" },
      this.props.style
    );
    const unexpandedStyle = R.merge(
      { width: "20%", overflow: "hidden" },
      this.props.style
    );
    const { menuType } = this.props;
    if (menuType === "plain") {
      return (
        <List style={this.props.style}>
          {this.props.menuItems.map(({ anchorText, link, icon }, index) => {
            return (
              <Link
                key={index}
                to={link}
                style={{ textDecoration: "none", display: "block" }}
              >
                <ListItem primaryText={anchorText} />
              </Link>
            );
          })}
        </List>
      );
    } else if (menuType === "icon") {
      return (
        <List style={this.props.style}>
          {this.props.menuItems.map(({ anchorText, link, icon }, index) => {
            return (
              <Link
                key={index}
                to={link}
                style={{ textDecoration: "none", display: "block" }}
              >
                <ListItem primaryText={anchorText} leftIcon={IconMap[icon]} />
              </Link>
            );
          })}
        </List>
      );
    } else if (menuType === "menucollapser") {
      return (
          <List style={this.props.style}>
            {this.props.menuItems.map(({ anchorText, link, icon }, index) => {
              return (
                <Link
                  key={index}
                  to={link}
                  style={{
                    textDecoration: "none",
                    display: "block",
                    marginTop: "10px"
                  }}
                >
                  <ListItem primaryText={anchorText} leftIcon={IconMap[icon]} />
                </Link>
              );
            })}
          </List>
      );
    } else {
      return (
        <List style={this.props.style}>
          {this.props.menuItems.map(({ anchorText, link, icon }, index) => {
            return (
              <Link
                key={index}
                to={link}
                style={{ textDecoration: "none", display: "block" }}
              >
                <ListItem primaryText={anchorText} />
              </Link>
            );
          })}
        </List>
      );
    }
  }
}

Menu.propTypes = {
  menuItems: PropTypes.arrayOf(
    PropTypes.shape({
      anchorText: PropTypes.string.isRequired,
      link: PropTypes.string.isRequired,
      icon: PropTypes.string
    })
  ),
  menuType: PropTypes.string
};

Menu.defaultProps = {
  menuItems: [],
  menuType: "icon"
};

export default Menu;

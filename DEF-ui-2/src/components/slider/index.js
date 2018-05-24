import React, { Component, PropTypes } from "react";
import "./slider.css";
import CSSTransitionGroup from "react-transition-group/CSSTransitionGroup";
import { Motion, spring } from "react-motion";
import { classNames } from "../../utils";
import test1 from "../../static/img/test.jpg";
import test2 from "../../static/img/test1.jpg";
import test3 from "../../static/img/test2.jpg";

const rotate = (arr, clock) => {
  if (clock) {
    const first = arr.shift();
    return arr.concat([first]);
  } else {
    const last = arr.unshift();
    return [last].concat(arr);
  }
};

class Slider extends Component {
  constructor(props) {
    super(props);
    this.state = { currentIndex: 0, currentImages: [], offsetWidth: 0 };
    this.selectImage = this.selectImage.bind(this);
    this.nextImage = this.nextImage.bind(this);
    this.prevImage = this.prevImage.bind(this);
    this.nextStep = this.nextStep.bind(this);
    this.prevStep = this.prevStep.bind(this);
  }
  selectImage(index) {
    console.log(index, "index");
    this.setState({ currentIndex: index });
  }
  nextImage() {
    console.log("next");
    this.setState((state, props) => {
      const total = props.images.length;
      return { currentIndex: (state.currentIndex + 1) % total };
    });
  }
  componentWillReceiveProps(nextProps) {
    if (this.state.currentImages.length === 0 && !nextProps.images.length !== 0)
      this.setState({ currentImages: nextProps.images });
  }
  nextStep() {
    this.setState((state, props) => {
      return {
        currentImages: rotate(state.currentImages, true),
        offsetWidth: state.offsetWidth - parseInt(props.slideWidth, 10)
      };
    });
  }

  prevStep() {
    this.setState((state, props) => {
      return {
        currentImages: rotate(state.currentImages, false),
        offsetWidth: state.offsetWidth + parseInt(props.slideWidth, 10)
      };
    });
  }
  prevImage() {
    this.setState((state, props) => {
      const total = props.images.length;
      if (state.currentIndex === 0) {
        return { currentIndex: total - 1 };
      } else {
        return { currentIndex: state.currentIndex - 1 };
      }
    });
  }
  render() {
    const { images } = this.props;
    const { currentImages,offsetWidth } = this.state;
    console.log(currentImages,"currentImages");
    const currentImage = images[this.state.currentIndex];
    const sliderWidth = parseInt(this.props.slideWidth, 10) * images.length;
    const currentPostion = -1 *
      parseInt(this.props.slideWidth, 10) *
      this.state.currentIndex;
    return (
      <div className="slider">
        <span className="left-arrow" onClick={this.prevImage} />
        <div
          className="slide-frame"
          style={{
            width: this.props.slideWidth,
            overflow: "hidden",
            margin: "0 auto"
          }}
        >
          <Motion
            defaultStyle={{ x: 0 }}
            style={{ x: spring(currentPostion) }}
            key="slide"
          >
            {({ x }) => (
              <div
                className="slider-display"
                style={{
                  width: sliderWidth,
                  position: "relative",
                  transform: `translate3d(${x}px,0,0)`
                }}
              >
                {images.map((image, index) => (
                  <ImageComponent
                    key={index}
                    currentImage={image}
                    slideWidth={this.props.slideWidth}
                  />
                ))}
                {/* <ImageComponent currentImage={currentImage} key="slide" /> */}
              </div>
            )}
          </Motion>
          <Indicators
            {...this.props}
            selectImage={this.selectImage}
            {...this.state}
          />
        </div>
        <span className="right-arrow" onClick={this.nextImage} />
      </div>
    );
  }
}

class ImageComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { new: false };
  }
  componentWillReceiveProps(nextProps) {
    if (this.props.currentImage.image !== nextProps.currentImage.image) {
      this.setState({ new: false });
      setTimeout(() => this.setState({ new: true }), 1000);
    }
  }
  render() {
    return (
      <a
        href={this.props.currentImage.link}
        className={classNames({ "slide-animation": false })}
        style={{ width: this.props.slideWidth }}
        key="slider"
      >
        <img src={this.props.currentImage.image} alt="Slider" />
      </a>
    );
  }
}

const Indicators = props => (
  <ol className="slider-indicators">
    {props.images.map((item, index) => (
      <li
        key={index}
        className={props.currentIndex === index ? "active" : ""}
        onClick={() => props.selectImage(index)}
      />
    ))}
  </ol>
);

Slider.propTypes = {
  images: PropTypes.arrayOf(
    PropTypes.shape({
      image: PropTypes.string.isRequired,
      link: PropTypes.string
    })
  ),
  slideWidth: PropTypes.string
};

Slider.defaultProps = {
  images: [
    { image: test1, link: "#" },
    { image: test2, link: "#" },
    { image: test3, link: "#" }
  ],
  slideWidth: "600px"
};

export default Slider;

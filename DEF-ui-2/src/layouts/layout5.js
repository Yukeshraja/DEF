import React from "react";

export const Layout5 = props => {
  return (
    <div class="col l4 m6 s12 gallery-item gallery-expand gallery-filter bigbang">
      <div class="gallery-curve-wrapper">
        <a class="gallery-cover gray">
          <img class="responsive-img" src="img/bigbang1.png" alt="placeholder"/>
        </a>
        <div class="gallery-header">
          <span>Gallery Link</span>
        </div>
        <div class="gallery-body">
          <div class="title-wrapper">
            <h3>Grapefruit</h3>
            <span class="price">$14.99</span>
          </div>
        </div>
        <div class="gallery-action">
          <a class="btn-floating btn-large waves-effect waves-light">
            <i class="material-icons">favorite
          </i></a>
        </div>
      </div>
    </div>
  );
};


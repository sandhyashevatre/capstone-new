import React from 'react';

import ImageSlider from "./ImageSlider";

export default function Home() {
  const images = [
    "/images/IS1.jpg",
    "/images/IS2.png",
    "/images/IS3.png",
    "/images/IS4.png",
    "/images/IS5.png",
    "/images/IS6.png",
  ];

  return (
    <div>
      <h1>Home Page</h1>
      <div className="imageslider">
          <ImageSlider images={images} glideDuration={5000} />
      </div>
    </div>
  );
}

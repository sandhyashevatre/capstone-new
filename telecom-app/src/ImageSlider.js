import React, { useState, useEffect } from "react";
import { Carousel } from "react-bootstrap";
const ImageSlider = ({ images, glideDuration = 3000 }) => {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex, e) => {
    setIndex(selectedIndex);
  };

  useEffect(() => {
    const timer = setInterval(() => {
      setIndex((index + 1) % images.length);
    }, glideDuration);

    return () => {
      clearInterval(timer);
    };
  }, [index, images.length, glideDuration]);

  return (
    <Carousel activeIndex={index} onSelect={handleSelect}>
      {images.map((image, i) => (
        <Carousel.Item key={i}>
          <img
            className="d-block w-100"
            src={image}
            alt={`Slide ${i}`}
          />
        </Carousel.Item>
      ))}
    </Carousel>
  );
};

export default ImageSlider;

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

  const sliderStyle = {
    width: "calc(100% - 100px)", // 100px padding on both sides
    height: "150px",
    margin: "0 auto",
  };
  return (
    <Carousel activeIndex={index} onSelect={handleSelect} style={sliderStyle}>
      {images.map((image, i) => (
        <Carousel.Item key={i}>
          <img
            className="d-block w-100"
            src={image}
            alt={`Slide ${i}`}
            style={{ height: "100%", objectFit: "cover" }}
          />
        </Carousel.Item>
      ))}
    </Carousel>
  );
};

export default ImageSlider;

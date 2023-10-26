import React, { useState, useEffect } from "react";
import { Carousel } from "react-bootstrap";

const ImageSlider = ({ images, glideDuration = 1000 }) => {
  const [index, setIndex] = useState(0);

  //const images = ["/images/perks1.png"];

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
    <>
    <div className="perks">
      <img src={images} alt="perks1"/>
      </div>
    <Carousel activeIndex={index} onSelect={handleSelect} >
      {images.map((image, i) => (
        <Carousel.Item key={i}>
          <img
            className="d-block "
            src={image}
            alt={`Slide ${i}`}
            style={{ width:"50%", height: "10%", objectFit: "cover", margin: "0 auto" }}
          />
        </Carousel.Item>
      ))}
    </Carousel>
    </>
  );
};
export default ImageSlider;

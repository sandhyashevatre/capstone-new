import React from "react";
import { render } from "@testing-library/react";
import ImageSlider from "./ImageSlider";

describe("ImageSlider Component (Unit Tests)", () => {
  it("renders the ImageSlider component with provided images", () => {
    const images = ["/image1.jpg", "/image2.jpg"];

    const { container } = render(
      <ImageSlider images={images} glideDuration={1000} />
    );

    const imageSlider = container.querySelector(".perks");
    expect(imageSlider).toBeInTheDocument();

    const carousel = container.querySelector(".carousel");
    expect(carousel).toBeInTheDocument();

    const imageElements = container.querySelectorAll(".carousel-item img");
    expect(imageElements.length).toBe(images.length);
  });
});

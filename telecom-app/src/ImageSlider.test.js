import React from 'react';
import { render } from '@testing-library/react';
import ImageSlider from './ImageSlider';

describe('ImageSlider Component (Unit Tests)', () => {
  it('renders the ImageSlider component with provided images', () => {
    const images = ['/image1.jpg', '/image2.jpg']; // Replace with actual image paths

    const { container } = render(<ImageSlider images={images} glideDuration={1000} />);

    const imageSlider = container.querySelector('.perks'); // Check for the container element
    expect(imageSlider).toBeInTheDocument();

    const carousel = container.querySelector('.carousel'); // Check for the carousel element
    expect(carousel).toBeInTheDocument();

    const imageElements = container.querySelectorAll('.carousel-item img'); // Check for image elements
    expect(imageElements.length).toBe(images.length);
  });
  
  // You can add more specific tests based on your component's structure and content.
});

import React from 'react';
import { render, screen } from '@testing-library/react';
import Home from './Home';

describe('Home Component (Unit Tests)', () => {
  it('renders the Home page with a title', () => {
    render(<Home />);

    const pageTitle = screen.getByText('Home Page');
    expect(pageTitle).toBeInTheDocument();
  });

  it('renders the ImageSlider component', () => {
    render(<Home />);

    const imageSlider = screen.getByTestId('image-slider'); 
    expect(imageSlider).toBeInTheDocument();
  });

  it('displays a set of images in the ImageSlider', () => {
    render(<Home />);

    const imageSlider = screen.getByTestId('image-slider');
    const images = imageSlider.querySelectorAll('img');
    expect(images.length).toBeGreaterThan(0);
  });

});

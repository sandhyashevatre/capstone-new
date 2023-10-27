import React from 'react';
import { render, screen } from '@testing-library/react';
import Contact from './Contact';

describe('Contact Component (Unit Tests)', () => {
  it('renders the About section with expected content', () => {
    render(<Contact />);

    const aboutTitle = screen.getByText('About Wireless Inventory Number Management');
    expect(aboutTitle).toBeInTheDocument();

    const missionSection = screen.getByText('Our Mission');
    expect(missionSection).toBeInTheDocument();

    const reservationSection = screen.getByText('What We Offer Number Reservation:');
    expect(reservationSection).toBeInTheDocument();

    const revolutionSection = screen.getByText('Join the 5G Revolution with Us');
    expect(revolutionSection).toBeInTheDocument();
  });

  it('renders specific content within the About section', () => {
    render(<Contact />);

    const aboutContent = screen.getByText('Wireless Inventory Number Management is here to empower');
    expect(aboutContent).toBeInTheDocument();
  });

  // Add more specific tests for other content as needed.
});

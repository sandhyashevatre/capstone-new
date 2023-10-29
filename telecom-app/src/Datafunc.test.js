import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom'; 

import Data from './Data';

describe('Data Component Integration Test', () => {
  it('renders Data component with sample SIM data', () => {

    const mockLocalStorage = {
      getItem: jest.fn(() => 'your-auth-token'), 
    };
    Object.defineProperty(window, 'localStorage', { value: mockLocalStorage });

    render(
      <BrowserRouter>
        <Data connection={{ type: 'prepaid' }} />
      </BrowserRouter>
    );

    const dataComponent = screen.getByTestId('data-component'); 
    expect(dataComponent).toBeInTheDocument();

  });
});

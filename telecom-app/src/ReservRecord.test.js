import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import ReserRecord from './ReserRecord';

test('renders the ReserRecords component', () => {
  render(<ReserRecords />);
  const headerElement = screen.getByText('Reserved Record');
  expect(headerElement).toBeInTheDocument();
});

test('displays SIM records fetched from the API', async () => {
  // Mock the fetch call to return sample data
  global.fetch = jest.fn().mockResolvedValue({
    ok: true,
    json: () =>
      Promise.resolve([
        {
          id: 1,
          msisdn: '1234567890',
          customerName: 'John Doe',
          reservationDateTime: '2023-10-26T15:00:00.000Z',
        },
        // Add more sample data as needed
      ]),
  });

  render(<ReserRecords />);
  const msisdnElement = await screen.findByText('1234567890');
  const customerNameElement = await screen.findByText('John Doe');
  expect(msisdnElement).toBeInTheDocument();
  expect(customerNameElement).toBeInTheDocument();
});

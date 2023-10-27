import React from 'react';
import { render, screen } from '@testing-library/react';
import InactiveRecord from './InactiveRecord';

test('renders Inactive Sims title', () => {
  render(<InactiveRecord />);
  const titleElement = screen.getByText('Inactive Sims');
  expect(titleElement).toBeInTheDocument();
});

test('fetches and displays inactive SIM records', async () => {
  // You can use a library like `msw` to mock API responses
  // Mock the fetchSims function to return sample data
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: async () => [
      { id: 1, msisdn: '12345', reservationDateTime: '2023-10-26T12:00:00Z', connectionType: 'Prepaid' },
      { id: 2, msisdn: '67890', reservationDateTime: '2023-10-27T14:00:00Z', connectionType: 'Postpaid' },
    ],
    ok: true,
  });

  render(<InactiveRecord />);
  const tableRows = await screen.findAllByRole('row');

  // Ensure that the data is displayed correctly
  expect(tableRows).toHaveLength(3); // 1 header row + 2 data rows
  expect(screen.getByText('12345')).toBeInTheDocument();
  expect(screen.getByText('67890')).toBeInTheDocument();
  expect(screen.getByText('2023-10-26')).toBeInTheDocument();
  expect(screen.getByText('2023-10-27')).toBeInTheDocument();
  expect(screen.getByText('Prepaid')).toBeInTheDocument();
  expect(screen.getByText('Postpaid')).toBeInTheDocument();
});

test('handles API fetch error', async () => {
  // Mock the fetchSims function to throw an error
  jest.spyOn(global, 'fetch').mockRejectedValue(new Error('API Error'));

  render(<InactiveRecord />);
  const errorMessage = await screen.findByText('Failed to fetch data');

  expect(errorMessage).toBeInTheDocument();
});

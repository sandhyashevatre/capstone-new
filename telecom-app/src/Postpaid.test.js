import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import Postpaid from './Postpaid';

test('renders the Postpaid title', () => {
  render(<Postpaid />);
  const titleElement = screen.getByText('Postpaid');
  expect(titleElement).toBeInTheDocument();
});

test('updates form fields when input values change', () => {
  render(<Postpaid />);
  const customerInput = screen.getByLabelText('Customer Name');
  const phoneNumberInput = screen.getByLabelText('Phone Number');
  const airtelRadio = screen.getByLabelText('Airtel');

  fireEvent.change(customerInput, { target: { value: 'John Doe' } });
  fireEvent.change(phoneNumberInput, { target: { value: '1234567890' } });
  fireEvent.click(airtelRadio);

  expect(customerInput).toHaveValue('John Doe');
  expect(phoneNumberInput).toHaveValue('1234567890');
  expect(airtelRadio).toBeChecked();
});

test('submits the form when the "Submit" button is clicked', async () => {
  render(<Postpaid />);
  const customerInput = screen.getByLabelText('Customer Name');
  const phoneNumberInput = screen.getByLabelText('Phone Number');
  const airtelRadio = screen.getByLabelText('Airtel');
  const submitButton = screen.getByText('Submit');

  fireEvent.change(customerInput, { target: { value: 'John Doe' } });
  fireEvent.change(phoneNumberInput, { target: { value: '1234567890' } });
  fireEvent.click(airtelRadio);
  fireEvent.click(submitButton);

  // Mock the API call and assert the response or behavior
  // For simplicity, you can use a testing library like `msw` to mock the fetch call
  await waitFor(() => expect(screen.getByText('Sim has been registered successfully')).toBeInTheDocument());
});

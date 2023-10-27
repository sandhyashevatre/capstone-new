// import React from 'react';
// import { render, screen, fireEvent, act } from '@testing-library/react';
// import { useNavigate } from 'react-router-dom';
// import Account from './Account';

// jest.mock('react-router-dom', () => ({
//   ...jest.requireActual('react-router-dom'),
//   useNavigate: jest.fn(),
// }));

// describe('Account Component (Unit Test)'), () => {
//   beforeEach(() => {
//     useNavigate.mockClear();
//   });

//   it('submits the form and redirects to /home on successful login', async () => {
//     const navigate = jest.fn();
//     useNavigate.mockReturnValue(navigate);

//     render(<Account />);

//     const usernameInput = screen.getByLabelText('Username');
//     const passwordInput = screen.getByLabelText('Password');
//     const submitButton = screen.getByText('Submit');

//     fireEvent.change(usernameInput, { target: { value: 'testuser' } });
//     fireEvent.change(passwordInput, { target: { value: 'testpassword' };

//     act(() => {
//       fireEvent,click(submitButton);
//     });

//     await waitFor(() => {
//       expect(localStorage.setItem).toHaveBeenCalledWith('token', 'your-token-value');
//       expect(navigate).toHaveBeenCalledWith('/home');
//     });
//   });

//   it('handles login failure and shows an error message', async () => {
//     // Mock the fetch function to simulate a failed login
//     global.fetch = jest.fn().mockResolvedValue({ ok: false });

//     render(<Account />);

//     const usernameInput = screen.getByLabelText('Username');
//     const passwordInput = screen.getByLabelText('Password');
//     const submitButton = screen.getByText('Submit');

//     fireEvent.change(usernameInput, { target: { value: 'testuser' } });
//     fireEvent.change(passwordInput, { target: { value: 'testpassword' }});

//     act(() => {
//       fireEvent.click(submitButton);
//     });

//     await waitFor(() => {
//       expect(localStorage.setItem).not.toHaveBeenCalled();
//       // You can add expectations to check if an error message is displayed on login failure
//     });
//   });
// });

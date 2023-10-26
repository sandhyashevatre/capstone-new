import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('renders the Home link', () => {
  render(<App />);
  const homeLink = screen.getByText("Home");
  expect(homeLink).toBeInTheDocument();
});

test('renders the About link', () => {
  render(<App />);
  const aboutLink = screen.getByText("About");
  expect(aboutLink).toBeInTheDocument();
});

test('renders the Prepaid link', () => {
  render(<App />);
  const prepaidLink = screen.getAllByText("Prepaid");
  expect(prepaidLink.length).toBeGreaterThan(0);
});

test('renders the Postpaid link', () => {
  render(<App />);
  const postpaidLink = screen.getAllByText("Postpaid");
  expect(postpaidLink.length).toBeGreaterThan(0);
});

test('renders the Reservation link', () => {
  render(<App />);
  const reservationLink = screen.getByText("Reservation");
  expect(reservationLink).toBeInTheDocument();
});

test('renders the Records link', () => {
  render(<App />);
  const recordsLink = screen.getByText("Records");
  expect(recordsLink).toBeInTheDocument();
});

test('renders the Account link', () => {
  render(<App />);
  const accountLink = screen.getByText('Account');
  expect(accountLink).toBeInTheDocument();
});

test('renders the social media links in the footer', () => {
  render(<App />);
  const linkedinLink = screen.getByText("LinkedIn");
  expect(linkedinLink).toBeInTheDocument();
  const githubLink = screen.getByText("GitHub");
  expect(githubLink).toBeInTheDocument();
  const instagramLink = screen.getByText("Instagram");
  expect(instagramLink).toBeInTheDocument();
});

test('navigates to the Home page when the Home link is clicked', () => {
  render(<App />);
  const homeLink = screen.getByText("Home");
  fireEvent.click(homeLink);
  const homePageHeader = screen.getByText("Home Page"); // Replace with actual content on your Home page
  expect(homePageHeader).toBeInTheDocument();
});



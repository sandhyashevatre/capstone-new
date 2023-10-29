import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import Reservation from "./Reservation";

test("renders the Reservation component", () => {
  render(<Reservation connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer Name");
  expect(customerInput).toBeInTheDocument();
});

test("updates form fields when input values change", () => {
  render(<Reservation connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer Name");
  const phoneNumberInput = screen.getByLabelText("Mobile Number");
  const airtelRadio = screen.getByLabelText("Airtel");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);

  expect(customerInput).toHaveValue("John Doe");
  expect(phoneNumberInput).toHaveValue("1234567890");
  expect(airtelRadio).toBeChecked();
});

test('submits the form when the "Submit" button is clicked', async () => {
  render(<Reservation connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer Name");
  const phoneNumberInput = screen.getByLabelText("Mobile Number");
  const airtelRadio = screen.getByLabelText("Airtel");
  const submitButton = screen.getByText("Submit");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);
  fireEvent.click(submitButton);

  await waitFor(() =>
    expect(screen.getByText("Sim Reserved successfully")).toBeInTheDocument()
  );
});

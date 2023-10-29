import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import ReplaceSim from "./ReplaceSim";

test("renders the ReplaceSim component", () => {
  render(<ReplaceSim connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer");
  expect(customerInput).toBeInTheDocument();
});

test("updates form fields when input values change", () => {
  render(<ReplaceSim connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer");
  const phoneNumberInput = screen.getByLabelText("Phone Number");
  const airtelRadio = screen.getByLabelText("Airtel");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);

  expect(customerInput).toHaveValue("John Doe");
  expect(phoneNumberInput).toHaveValue("1234567890");
  expect(airtelRadio).toBeChecked();
});

test('submits the form when the "Submit" button is clicked', async () => {
  render(<ReplaceSim connection={{ type: "prepaid" }} />);
  const customerInput = screen.getByLabelText("Customer");
  const phoneNumberInput = screen.getByLabelText("Phone Number");
  const airtelRadio = screen.getByLabelText("Airtel");
  const submitButton = screen.getByText("Submit");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);
  fireEvent.click(submitButton);

  await waitFor(() =>
    expect(
      screen.getByText("Sim has been registered successfully")
    ).toBeInTheDocument()
  );
});

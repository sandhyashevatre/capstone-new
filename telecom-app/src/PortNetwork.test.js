import { render, screen, fireEvent } from "@testing-library/react";
import PortNetwork from "./PortNetwork";

test("renders PortNetwork component with form fields", () => {
  render(<PortNetwork connection={{ type: "prepaid" }} />);

  const customerInput = screen.getByLabelText("Customer Name");
  const phoneNumberInput = screen.getByLabelText("Mobile Number");
  const airtelRadio = screen.getByLabelText("Airtel");
  const jioRadio = screen.getByLabelText("Jio");
  const viRadio = screen.getByLabelText("Vodafone Idea");
  const aircelRadio = screen.getByLabelText("Aircel");
  const submitButton = screen.getByText("Submit");

  expect(customerInput).toBeInTheDocument();
  expect(phoneNumberInput).toBeInTheDocument();
  expect(airtelRadio).toBeInTheDocument();
  expect(jioRadio).toBeInTheDocument();
  expect(viRadio).toBeInTheDocument();
  expect(aircelRadio).toBeInTheDocument();
  expect(submitButton).toBeInTheDocument();
});

test("updates form fields when input values change", () => {
  render(<PortNetwork connection={{ type: "prepaid" }} />);

  const customerInput = screen.getByLabelText("Customer Name");
  const phoneNumberInput = screen.getByLabelText("Mobile Number");
  const airtelRadio = screen.getByLabelText("Airtel");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);

  expect(customerInput.value).toBe("John Doe");
  expect(phoneNumberInput.value).toBe("1234567890");
  expect(airtelRadio.checked).toBe(true);
});

test('submits the form when the "Submit" button is clicked', () => {
  render(<PortNetwork connection={{ type: "prepaid" }} />);

  const customerInput = screen.getByLabelText("Customer Name");
  const phoneNumberInput = screen.getByLabelText("Mobile Number");
  const airtelRadio = screen.getByLabelText("Airtel");
  const submitButton = screen.getByText("Submit");

  fireEvent.change(customerInput, { target: { value: "John Doe" } });
  fireEvent.change(phoneNumberInput, { target: { value: "1234567890" } });
  fireEvent.click(airtelRadio);
  fireEvent.click(submitButton);
});

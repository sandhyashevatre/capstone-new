import { render, screen, waitFor } from '@testing-library/react';
import Data from './Data';

describe('Data Component', () => {
  it('renders a table with SIM data', async () => {
    // Mock the fetch function
    global.fetch = jest.fn().mockResolvedValue({
      ok: true,
      json: async () => [
        {
          id: 1,
          msisdn: '1234567890',
          iccid: 'ICCID123',
          imei: 'IMEI123',
          reservationDateTime: '2023-10-26',
          activated: true,
        },
      ],
    });

    render(<Data connection={{ type: 'prepaid' }} />);

    // Wait for the data to load and the table to render
    await waitFor(() => {
      const table = screen.getByRole('table');
      const rows = screen.getAllByRole('row');
      const cells = screen.getAllByRole('cell');

      expect(table).toBeInTheDocument();
      expect(rows.length).toBe(2); // Including header row
      expect(cells.length).toBe(6); // 6 cells in a single row
    });
  });
});

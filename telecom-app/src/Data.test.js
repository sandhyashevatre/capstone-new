import { render, screen, waitFor } from '@testing-library/react';
import Data from './Data';

describe('Data Component', () => {
  it('renders a table with SIM data', async () => {

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

  
    await waitFor(() => {
      const table = screen.getByRole('table');
      const rows = screen.getAllByRole('row');
      const cells = screen.getAllByRole('cell');

      expect(table).toBeInTheDocument();
      expect(rows.length).toBe(2);
      expect(cells.length).toBe(6); 
    });
  });
});

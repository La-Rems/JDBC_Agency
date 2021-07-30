package agency;

import agency.services.AgencyService;

import java.sql.SQLException;

public class AgencyApplication {
    public static void main(String[] args) throws SQLException {
        AgencyService agencyService = new AgencyService();
        agencyService.run();
    }
}

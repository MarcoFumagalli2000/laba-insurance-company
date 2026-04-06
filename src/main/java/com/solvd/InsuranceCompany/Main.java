package com.solvd.InsuranceCompany;

import com.solvd.InsuranceCompany.Exceptions.IncompleteRecord;
import com.solvd.InsuranceCompany.Exceptions.InvalidValue;
import com.solvd.InsuranceCompany.Items.InsurancedItem;
import com.solvd.InsuranceCompany.Items.OtherObjects;
import com.solvd.InsuranceCompany.Items.Vehicle;
import com.solvd.InsuranceCompany.People.Client;
import com.solvd.InsuranceCompany.People.Employee;
import com.solvd.InsuranceCompany.Tools.ObjectCalculator;
import com.solvd.InsuranceCompany.Tools.VehicleCalculator;
import com.solvd.InsuranceCompany.Tracking.ClientRequests;
import com.solvd.InsuranceCompany.Tracking.ObjectRecord;
import com.solvd.InsuranceCompany.Tracking.VehicleRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {

	public static final Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		Map<String, Client> clientMap = new HashMap<>();
		Map<String, Employee>employeeMap = new HashMap<>();
		List<InsurancedItem>itemList = new ArrayList<>();
		TreeSet<Double> sortedPayouts = new TreeSet<>();
		Queue<ClientRequests> requestDone = new LinkedList<>();

		try {
			Client john = new Client("John", "Jones", "40302123", "42901321", "jjsomedude@gmail.com");
			Vehicle yaris = new Vehicle(john, 20000, "Toyota", "Yaris", "2018", "AA0022", "a900jE");
			OtherObjects necklace = new OtherObjects(john, 170, "Necklace", "Gold", "Leiva", 1);
			Employee marco = new Employee(
				"Marco",
				"Fumagalli",
				"42900800",
				"0022119922",
				"marcofumagalli@gmail.com",
				"Assistant",
				"9 to 5"
			);

			clientMap.put(john.getIdNumber(), john);
			employeeMap.put(marco.getIdNumber(), marco);
			itemList.add(yaris);
			itemList.add(necklace);

			ClientRequests request1 = new ClientRequests("Rear ended by drunk driver", 400, 1, true, john);
			ClientRequests request2 = new ClientRequests("Stolen necklace", 100, 2, true, john);
			requestDone.add(request1);
			requestDone.add(request2);

			VehicleRecords records1 = new VehicleRecords(
				"Rear ended by drunk driver",
				"Driver name: Tom Thoms",
				request1,
				3
			);
			records1.recordNewCrash();
			ObjectRecord records2 = new ObjectRecord("Ripped from neck", "By relative", request2, false, 5);
			records2.recordNewBrake();

			VehicleCalculator vCalc = new VehicleCalculator(john, 400, yaris, yaris, records1);
			double clientPayout = vCalc.calculatePayout();

			ObjectCalculator oCalc = new ObjectCalculator(john, 40, necklace, records2);
			double clientPayout2 = oCalc.calculatePayout();

			sortedPayouts.add(clientPayout);
			sortedPayouts.add(clientPayout2);

			LOGGER.info("----Berliner insurances----");
			LOGGER.info("Client: {}", clientMap.get("40302123"));

			logAssetsInfo(itemList);

			LOGGER.info("Request 1 payout : {}", clientPayout);
			LOGGER.info("Request 2 payout: {}", clientPayout2);
			LOGGER.info("Highest payout received: {}", sortedPayouts.last());
			LOGGER.info("Number of completed requests: {}", requestDone.size());
			LOGGER.info("Employee assigned to your case: {} | ID: {}",
					employeeMap.get(marco.getIdNumber()).getName(),
					employeeMap.get(marco.getIdNumber()).getIdNumber());
		} catch (InvalidValue | IncompleteRecord e) {
			LOGGER.error("ERROR: {}", e.getMessage());
		} catch (Exception e) {
			LOGGER.error("UNEXPECTED ERROR: {}", e.getMessage());
		}
	}

	public static void logAssetsInfo(List<? extends InsurancedItem> items) {
		for (InsurancedItem item : items) {
			LOGGER.info("Insured Asset: {} | Value: ${}", item, item.getValue());
		}
	}
}

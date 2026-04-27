package com.solvd.InsuranceCompany;

import com.solvd.InsuranceCompany.annotations.Rule;
import com.solvd.InsuranceCompany.enums.EmployeeRole;
import com.solvd.InsuranceCompany.enums.ObjectType;
import com.solvd.InsuranceCompany.enums.RequestStatus;
import com.solvd.InsuranceCompany.enums.VehicleType;
import com.solvd.InsuranceCompany.exceptions.IncompleteRecord;
import com.solvd.InsuranceCompany.exceptions.InvalidValue;
import com.solvd.InsuranceCompany.interfaces.IInsurancedItem;
import com.solvd.InsuranceCompany.items.OtherObjects;
import com.solvd.InsuranceCompany.items.Vehicle;
import com.solvd.InsuranceCompany.people.Client;
import com.solvd.InsuranceCompany.people.Employee;
import com.solvd.InsuranceCompany.tools.NameThread;
import com.solvd.InsuranceCompany.tools.ObjectCalculator;
import com.solvd.InsuranceCompany.tools.VehicleCalculator;
import com.solvd.InsuranceCompany.tools.WordCounter;
import com.solvd.InsuranceCompany.tracking.ClientRequests;
import com.solvd.InsuranceCompany.tracking.ObjectRecord;
import com.solvd.InsuranceCompany.tracking.PoolTest;
import com.solvd.InsuranceCompany.tracking.VehicleRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

	public static final Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		Map<String, Client> clientMap = new HashMap<>();
		Map<String, Employee>employeeMap = new HashMap<>();
		List<IInsurancedItem>itemList = new ArrayList<>();
		TreeSet<Double> sortedPayouts = new TreeSet<>();
		Queue<ClientRequests> requestDone = new LinkedList<>();

		try {
			Client John = new Client("John", "Jones", "40302123", "42901321", "jjsomedude@gmail.com");
			Vehicle Yaris = new Vehicle(VehicleType.CAR,John, 20000, "Toyota", "Yaris", "2018", "AA0022", "a900jE");
			Vehicle Tornado = new Vehicle(VehicleType.MOTORCYCLE,John,10000,"Honda","Tornado","2010","JPG198","0001999288");
			OtherObjects necklace = new OtherObjects(John, 170, "Necklace", ObjectType.JEWELRY, "Leiva", 1);
			OtherObjects cellphone = new OtherObjects(John,800,"Cellphone", ObjectType.ELECTRONICS, "Samsung", 1);
			OtherObjects chair = new OtherObjects(John,50,"Chair", ObjectType.FURNITURE, "DeKit", 4);
			Employee Marco = new Employee(
				"Marco",
				"Fumagalli",
				"42900800",
				"0022119922",
				"marcofumagalli@gmail.com",
				EmployeeRole.ASSISTANT,"9 to 5");
			Employee Nicolas = new Employee("Nicolas","Diez","29423817","019283910","nico10@gmail.com",EmployeeRole.MANAGER,"9 to 5");
			Employee Gonzalo = new Employee("Gonzalo","Aranda","47082143","2611159287","garanda@gmail.com",EmployeeRole.ACCOUNTANT,"9 to 5");

			clientMap.put(John.idNumber(), John);
			employeeMap.put(Marco.idNumber(), Marco);
			employeeMap.put(Nicolas.idNumber(), Nicolas);
			employeeMap.put(Gonzalo.idNumber(), Gonzalo);
			itemList.add(Yaris);
			itemList.add(Tornado);
			itemList.add(necklace);
			itemList.add(cellphone);
			itemList.add(chair);

			ClientRequests request1 = new ClientRequests("Rear ended by drunk driver", 400, 1, RequestStatus.APPROVED, John);
			ClientRequests request2 = new ClientRequests("Broken necklace", 100, 2, RequestStatus.APPROVED, John);
			ClientRequests request3 = new ClientRequests("Merge accident",800, 4, RequestStatus.APPROVED, John);
			ClientRequests request4 = new ClientRequests("Stolen cellphone", 500, 3, RequestStatus.PENDING, John);
			ClientRequests request5 = new ClientRequests("Broken Chair",20, 4, RequestStatus.REJECTED, John);
			requestDone.add(request1);
			requestDone.add(request2);
			requestDone.add(request3);
			requestDone.add(request5);

			VehicleRecords records1 = new VehicleRecords(
				"Rear ended by drunk driver",
				"Driver name: Tom Thoms",
				request1,
				3
			);
			records1.recordNewCrash();
			ObjectRecord records2 = new ObjectRecord("Ripped from neck", "By relative", request2,  5);
			records2.recordNewBrake();
			VehicleRecords records3 = new VehicleRecords("Merge accident","By old driver", request3,2);
			records3.recordNewCrash();
			ObjectRecord records4 = new ObjectRecord("Stolen cellphone","By thief", request4, 6, true);
			records4.recordNewBrake();
			ObjectRecord record5 = new ObjectRecord("Broken Chair","By child", request5, 7);
			record5.recordNewBrake();

			VehicleCalculator vCalc1 = new VehicleCalculator(John, 400, Yaris, Yaris, records1);
			double clientPayout = vCalc1.calculatePayout();
			ObjectCalculator oCalc = new ObjectCalculator(John, 40, necklace, records2);
			double clientPayout2 = oCalc.calculatePayout();
			VehicleCalculator vCalc2 = new VehicleCalculator(John,500,Tornado, Tornado,records3);
			double clientPayout3 = vCalc2.calculatePayout();
			ObjectCalculator oCalc3 = new ObjectCalculator(John,20,chair,record5);
			double clientPayout5 = oCalc3.calculatePayout();


			sortedPayouts.add(clientPayout);
			sortedPayouts.add(clientPayout2);
			sortedPayouts.add(clientPayout3);
			sortedPayouts.add(clientPayout5);

			runThreadDemo();
			runConnectionPoolDemo();
			LOGGER.info("----Berliner insurances----");
			LOGGER.info("Client: {}", clientMap.get("40302123"));

			logAssetsInfo(itemList);
			LOGGER.info("---------------------------");
			LOGGER.info("Request 1 payout : {}", clientPayout);
			LOGGER.info(getApprovalMessage(request1));
			LOGGER.info("Request 2 payout: {}", clientPayout2);
			LOGGER.info(getApprovalMessage(request2));
			LOGGER.info("Request 3 payout: {}", clientPayout3);
			LOGGER.info(getApprovalMessage(request3));
			LOGGER.info("Request 4 payout: pending");
			LOGGER.info(getApprovalMessage(request4));
			LOGGER.info("Request 5 payout: {}", clientPayout5);
			LOGGER.info(getApprovalMessage(request5));
			LOGGER.info("Highest payout received: {}", sortedPayouts.last());
			LOGGER.info("Number of completed requests: {}", requestDone.size());
			LOGGER.info("---------------------------");
			LOGGER.info("Assistant assigned to your case: {} | ID: {}",
					employeeMap.get(Marco.idNumber()).name(),
					employeeMap.get(Marco.idNumber()).idNumber());
			LOGGER.info("Accountant assigned to your case: {} | ID: {}",
					employeeMap.get(Gonzalo.idNumber()).name(),
					employeeMap.get(Gonzalo.idNumber()).idNumber());
			LOGGER.info("Manager: {} | ID: {}",
					employeeMap.get(Nicolas.idNumber()).name(),
					employeeMap.get(Nicolas.idNumber()).idNumber());
			LOGGER.info("------------------------------------------------");
			LOGGER.info("Is request 4 reported as stolen? : {}", records4.hasTheftReport());
			logRule(request1, "Request details");
			logRule(vCalc1, "Vehicle payout details");
			LOGGER.info("Starting Word Count Analysis...");
			WordCounter.runAnalysis("src/main/resources/insurance.txt", "src/main/resources/output.txt", "insurance");
			LOGGER.info("File analysis completed successfully.");
		} catch (InvalidValue | IncompleteRecord e) {
			LOGGER.error("ERROR: {}", e.getMessage());
		} catch (Exception e) {
			LOGGER.error("UNEXPECTED ERROR: {}", e.getMessage());
		}
	}

	public static void logAssetsInfo(List<? extends IInsurancedItem> items) {
		items.forEach(item ->
				LOGGER.info("Insured Asset: {} | Value: ${}", item, item.value())
		);
	}

	public static String getApprovalMessage(ClientRequests request) {
		if (request.hasValidStatus() || request.getStatus() == RequestStatus.PENDING) {
			return "This request is not yet approved";
		}
		if (request.getStatus() == RequestStatus.APPROVED) {
			return "This request is approved";
		}
		return "This request has been rejected";
	}

	public static void logRule(Object target, String title) {
		LOGGER.info("{}:", title);

		String fieldName = "";
		String methodName = "";
		int methodParams = 0;

		if (target instanceof ClientRequests request) {
			fieldName = "status";
			methodName = "getStatusMessage";
			methodParams = 1;
			LOGGER.info("Request #{}:", request.getRequestNumber());
		} else if (target instanceof ObjectRecord) {
			fieldName = "isStolen";
			methodName = "hasTheftReport";
		} else if (target instanceof VehicleCalculator || target instanceof ObjectCalculator) {
			fieldName = "repairPrice";
			methodName = "calculatePayout";
		}

		for (Field field : target.getClass().getDeclaredFields()) {
			if (!field.getName().equals(fieldName)) {
				continue;
			}

			boolean restoreAccess = field.canAccess(target);
			try {
				field.setAccessible(true);
				Object value = field.get(target);
				if ("status".equals(field.getName())) {
					LOGGER.info("Current status: {}", value);
				} else if ("isStolen".equals(field.getName())) {
					LOGGER.info("Theft reported: {}", value);
				} else if ("repairPrice".equals(field.getName())) {
					LOGGER.info("Repair estimate: ${}", value);
				}
			} catch (IllegalAccessException | InaccessibleObjectException | SecurityException e) {
				LOGGER.warn("Could not read {}.", field.getName());
			} finally {
				try {
					field.setAccessible(restoreAccess);
				} catch (InaccessibleObjectException | SecurityException ignored) {
				}
			}
		}

		for (Method method : target.getClass().getDeclaredMethods()) {
			if (!method.getName().equals(methodName) || method.getParameterCount() != methodParams) {
				continue;
			}

			if ("calculatePayout".equals(method.getName())) {
				boolean restoreAccess = method.canAccess(target);
				try {
					method.setAccessible(true);
					Object value = method.invoke(target);
					LOGGER.info("Final payout: ${}", value);
				} catch (ReflectiveOperationException | InaccessibleObjectException | SecurityException e) {
					LOGGER.warn("Could not calculate payout.");
				} finally {
					try {
						method.setAccessible(restoreAccess);
					} catch (InaccessibleObjectException | SecurityException ignored) {
					}
				}
			}

			Rule rule = method.getAnnotation(Rule.class);
			if (rule != null) {
				LOGGER.info("Rule: {}", rule.value().isBlank() ? rule.category() : rule.value());
			}
		}
	}
	public static void runThreadDemo () {
		LOGGER.info("Starting thread demo...");

		NameThread thread1 = new NameThread("Extended Thread");

		Thread thread2 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " - iteration " + i);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " interrupted");
				}
			}
		}, "Runnable Thread");

		thread1.start();
		thread2.start();
	}
	public static void runConnectionPoolDemo () {
		LOGGER.info("Running Connection Pool demos...");
		PoolTest.runTest();
		PoolTest.runSecondTest();
	}
}

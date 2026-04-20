package com.solvd.InsuranceCompany.tools;

import com.solvd.InsuranceCompany.annotations.BusinessRule;
import com.solvd.InsuranceCompany.tracking.ClientRequests;
import com.solvd.InsuranceCompany.tracking.ObjectRecord;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ClaimReflectionSummary {

	private ClaimReflectionSummary() {
	}

	public static List<String> summarizeRequest(ClientRequests request) {
		List<String> summary = new ArrayList<>();
		summary.add("Request #" + request.getRequestNumber() + " status: " + readAnnotatedField(request, "status"));
		summary.add("Request rule: " + readAnnotatedMethodRule(request, "updateRequestValue", 2));
		return summary;
	}

	public static List<String> summarizeObjectClaim(ObjectRecord record) {
		List<String> summary = new ArrayList<>();
		summary.add("Theft flag: " + readAnnotatedField(record, "isStolen"));
		summary.add("Theft rule: " + readAnnotatedMethodRule(record, "hasTheftReport", 0));
		return summary;
	}

	public static List<String> summarizePayout(String label, Object calculator) {
		List<String> summary = new ArrayList<>();
		summary.add(label + " payout base: $" + readAnnotatedField(calculator, "repairPrice"));
		summary.add(label + " rule: " + readAnnotatedMethodRule(calculator, "calculatePayout", 0));
		return summary;
	}

	private static String readAnnotatedField(Object target, String fieldName) {
		for (Field field : target.getClass().getDeclaredFields()) {
			if (!field.getName().equals(fieldName)) {
				continue;
			}

			BusinessRule rule = field.getAnnotation(BusinessRule.class);
			if (rule == null) {
				return "<missing @BusinessRule>";
			}

			return readFieldValue(field, target);
		}
		return "<field not found>";
	}

	private static String readAnnotatedMethodRule(Object target, String methodName, int parameterCount) {
		for (Method method : target.getClass().getDeclaredMethods()) {
			if (!method.getName().equals(methodName) || method.getParameterCount() != parameterCount) {
				continue;
			}

			BusinessRule rule = method.getAnnotation(BusinessRule.class);
			if (rule == null) {
				return "<missing @BusinessRule>";
			}

			return rule.value().isBlank() ? rule.category() : rule.value();
		}
		return "<method not found>";
	}

	private static String readFieldValue(Field field, Object target) {
		boolean restoreAccess = field.canAccess(target);
		try {
			field.setAccessible(true);
			return String.valueOf(field.get(target));
		} catch (IllegalAccessException | InaccessibleObjectException | SecurityException e) {
			return "<unavailable>";
		} finally {
			try {
				field.setAccessible(restoreAccess);
			} catch (InaccessibleObjectException | SecurityException ignored) {
			}
		}
	}
}

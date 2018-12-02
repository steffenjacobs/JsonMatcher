package me.steffenjacobs.jsonmatcher.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import me.steffenjacobs.jsonmatcher.domain.MappingDTO;

/** @author Steffen Jacobs */
public class MessageApplicationService {

	/***
	 * applies the model to a given source object and performs e.g. unit
	 * transformation
	 */
	public String applyModelToJson(String source, Set<MappingDTO<?, ?>> model) {

		final Map<String, MappingDTO<?, ?>> modelBySourceKey = new HashMap<>();
		for (MappingDTO<?, ?> dto : model) {
			modelBySourceKey.put(dto.getKeySource(), dto);
		}

		final JSONObject json = new JSONObject(source);

		final StringBuilder sb = new StringBuilder();
		sb.append("{");
		int count = 0;
		for (String key : json.keySet()) {
			@SuppressWarnings("rawtypes")
			MappingDTO m = modelBySourceKey.get(key);
			Object value = json.get(key);
			@SuppressWarnings("unchecked")
			Object result = m.getValueTransformation().apply(value);
			sb.append("\"");
			sb.append(m.getKeyTarget());
			sb.append("\": ");
			if (result instanceof String) {
				sb.append("\"");
				sb.append(result);
				sb.append("\"");
			} else {
				sb.append(result);
			}
			count++;
			if (count < json.length()) {
				sb.append(", ");
			}

		}
		sb.append("}");
		return sb.toString();
	}
}

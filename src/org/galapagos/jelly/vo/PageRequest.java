package org.galapagos.jelly.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
	private int start;
	private int perCount; // end 계산 가능

	public int getEnd() {
		return start + perCount - 1;
	}
}

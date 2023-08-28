package org.galapagos.jelly.command;

import org.galapagos.common.cli.input;
import org.galapagos.common.cli.command.Command;
import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelMySQLDaoImpl;
import org.galapagos.jelly.vo.TravelVO;

public class TravelDetailCommand implements Command {
	// TravelDao dao = TravelOracleDaoImpl.getInstance();
	TravelDao dao = TravelMySQLDaoImpl.getInstance();

	@Override
	public void execute() {
		// No: 104 --입력 시
		int no = input.readInt("No: ");

		TravelVO travel = dao.findById(no);
		if (travel == null) {
			System.out.println("잘못된 번호입니다.");
			return;
		}
		// 권역: xxxx
		// 관광지명: xxxxx
		// 주소: xxxx
		// 전화번호: xxxx
		// xxxxxxxxxxxx ....
		System.out.println("권역: " + travel.getRegion());
		System.out.println("관광지명: " + travel.getTitle());
		System.out.println("주소: " + travel.getAddress());
		System.out.println("전화번호: " + travel.getPhone());
		System.out.println(travel.getDescription().replace(".", ".\n")); // 줄나눔 방법
	}

}

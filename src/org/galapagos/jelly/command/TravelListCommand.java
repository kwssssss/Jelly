package org.galapagos.jelly.command;

import java.util.List;

import org.galapagos.common.cli.input;
import org.galapagos.common.cli.command.Command;
import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelMySQLDaoImpl;
import org.galapagos.jelly.utils.TravelUtil;
import org.galapagos.jelly.vo.PageRequest;
import org.galapagos.jelly.vo.TravelVO;

public class TravelListCommand implements Command {
	final static int COUNT_PER_PAGE = 10; // 상수 선언 방법 double은 뭔지알지
	// TravelDao dao = TravelOracleDaoImpl.getInstance(); //ORACLE
	TravelDao dao = TravelMySQLDaoImpl.getInstance();

	@Override
	public void execute() {
		while (true) {
			// 페이지 선택 [1~12]: 3
			int totalCount = dao.getTotalCount();
			int totalPage = (int) Math.ceil(totalCount / (double) COUNT_PER_PAGE); // 웹서비스할때 항상 하게 되는 작업, 기억해두자. 10.0
			String title = String.format("페이지 선택 [1~%d]: ", totalPage);
			int page = input.readInt(title);
			if (page == 0) {
				return;
			}

			// page 1: 1
			// page 2: 11

			// 오라클
			// int start = (page - 1) * COUNT_PER_PAGE + 1;
			// int end = start + COUNT_PER_PAGE - 1;

			// MySQL
			int start = (page - 1) * COUNT_PER_PAGE;

			PageRequest pageRequest = new PageRequest(start, COUNT_PER_PAGE);
			List<TravelVO> list = dao.getPage(pageRequest);

			TravelUtil.printTravelList(list);

			// 총 xxx 건 (현재 페이지 번호 / 총 페이지 수)
			System.out.printf("총 %d건 (페이지: %d/%d\n", totalCount, page, totalPage);
		}
	}
}

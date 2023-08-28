package org.galapagos.jelly;

import org.galapagos.common.cli.ui.Application;
import org.galapagos.common.cli.ui.Menu;
import org.galapagos.common.cli.ui.MenuItem;
import org.galapagos.jelly.command.TravelDetailCommand;
import org.galapagos.jelly.command.TravelListCommand;
import org.galapagos.jelly.command.TravelRegionCommand;
import org.galapagos.jelly.command.TravelSearchCommand;

public class TravelApp extends Application {

	public static void main(String[] args) {

		Application app = new TravelApp();
		app.run();

	}

	@Override
	public void createMenu(Menu menu) {
		super.createMenu(menu);

		menu.add(new MenuItem("목록", new TravelListCommand()));
		menu.add(new MenuItem("검색", new TravelSearchCommand()));
		menu.add(new MenuItem("상세보기", new TravelDetailCommand()));
		menu.add(new MenuItem("권역별보기", new TravelRegionCommand()));

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		super.cleanup();
	}
}
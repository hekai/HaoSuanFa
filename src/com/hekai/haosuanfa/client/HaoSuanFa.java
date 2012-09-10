package com.hekai.haosuanfa.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.hekai.haosuanfa.shared.Record;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HaoSuanFa implements EntryPoint {
private ListDataProvider<Record> dataProvider=new ListDataProvider<Record>();
	
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		HorizontalPanel loginPanel = new HorizontalPanel();
		loginPanel.setStyleName("loginPanel");
		rootPanel.add(loginPanel, 0, 0);
		loginPanel.setSize("100%", "30px");
		
		HorizontalPanel userInfoPanel = new HorizontalPanel();
		loginPanel.add(userInfoPanel);
		userInfoPanel.setSize("100%", "30px");
		
		Anchor userInfo = new Anchor("User Info");
		userInfo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		userInfo.setStyleName("loginInfo");
		userInfoPanel.add(userInfo);
		
		HorizontalPanel loginoutPanel = new HorizontalPanel();
		loginoutPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		loginoutPanel.setSize("100%", "30px");
		loginPanel.add(loginoutPanel);
		
		Anchor loginout = new Anchor("Login/Logout");
		loginout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		loginout.setStyleName("loginLink");
		loginoutPanel.add(loginout);
		
		VerticalPanel tablePanel = new VerticalPanel();
		tablePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(tablePanel, 0, 51);
		tablePanel.setSize("100%", "");
		
		CellTable<Record> cellTable = new CellTable<Record>(){
			@Override
		    protected void onBrowserEvent2(Event event) {
		        // Get the event target.
		        EventTarget eventTarget = event.getEventTarget();
		        if (!Element.is(eventTarget)) {
		            return;
		        }

		        // Ignore Mouseover events. Better performance in IE6 and FF3.
		        switch (DOM.eventGetType(event)) {
		            case Event.ONMOUSEOVER:
		            case Event.ONMOUSEOUT:
		                return;
		            default:
		                super.onBrowserEvent2(event);
		        }

		    }
		};
		tablePanel.add(cellTable);
		cellTable.setWidth("80%");
		
		List<Record> records=getRecords();
		
		TextColumn<Record> nameColumn=new TextColumn<Record>() {
			@Override
			public String getValue(Record object) {
				return object.getName();
			}
		};
		nameColumn.setCellStyleNames("tableNameCol");
		cellTable.addColumn(nameColumn,"Name");
		
		TextColumn<Record> titleColumn=new TextColumn<Record>() {
			@Override
			public String getValue(Record object) {
				return object.getTitle();
			}
		};
		cellTable.addColumn(titleColumn,"Title");
		
		if(records.size()>0){
			int num=records.get(0).getScores().length;
			for(int i=0;i<num;i++){
				final int j=i;
				TextColumn<Record> scoreColumn = new TextColumn<Record>() {
					@Override
					public String getValue(Record object) {
						return String.valueOf(object.getScores()[j]);
					}
				};
				cellTable.addColumn(scoreColumn,"user"+j);
			}
		}
		
		dataProvider.setList(records);
		dataProvider.addDataDisplay(cellTable);
		
		SimplePager simplePager = new SimplePager();
		simplePager.setDisplay(cellTable);
		tablePanel.add(simplePager);
	}
	
	private List<Record> getRecords(){
		LinkedList<Record> records=new LinkedList<Record>();
		Random random=new Random();
		int NUM=6;
		for(int i=0;i<NUM;i++){
			String name = "user"+i;
			String title="random"+random.nextInt(1000);
			int[] scores=new int[NUM];
			for(int j=0;j<NUM;j++){
				if(i==j)
					scores[j]=-1;
				else
					scores[j]=random.nextInt(10);
			}
			records.add(new Record(name, title, scores));
		}
		return records;
	}
}

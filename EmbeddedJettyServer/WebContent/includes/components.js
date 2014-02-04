/**
 * This is a file to contain all the fields and functions for the prototypes and
 * ui components to be used to drag and dropped to make the web page's content
 */

// A list of widgets that weren't added;
var widgetErrorArray = new Array();
// A complete list of widgets objects
var widgetsArray = new Array();
// A list of the widgetTypes objects
var widgetsTypesArray = new Array();
// A list of the types of widgets
var types = new Array("Buttons", "Panels");

function addWidgetsToWidgetDrawer() {
	createWidgetList();
	var listItem = $("<li class=\"list-group-item list-group-item-widget wc-draggable\">");
	for (x in widgetsTypesArray) {
		var array = widgetsTypesArray[x].widgets;
		for (y in array) {
			var newWidget = array[y];
			listItemClone = listItem.clone();
			newWidget.object.appendTo(listItemClone);
			newWidget.object.data("widget", newWidget);
			addWidgetTypeToDrawer(newWidget.type, listItemClone);
		}
	}
	makeWidgetComponentDraggable('.wc-draggable');
	makeWidgetComponentDraggable('.wc-draggableChild');
	$("#accordion").accordion({
		heightStyle : "content",
		collapsible : true
	});
}

function addWidgetTypeToDrawer(type, listItem) {
	var accordionObject = $(document.getElementById("accordion"));
	var list = document.getElementById("list-" + type);
	if (list == null) {
		list = $("<ul id=\"list-" + type + "\" class=\"list-group\"></ul>");
		$("<h3>" + type + " </h3>").appendTo($(accordionObject));
		list.appendTo(accordionObject);
	}
	listItem.appendTo(list);

}

function createWidgetList(reportError) {
	addWidget(new widget(
			0,
			"Button",
			"Buttons",
			false,
			$("<button id=\"protoButton\" type=\"button\" class=\"btn btn-default wc-draggableChild\">Button</button>"),
			null));
	addWidget(new widget(
			1,
			"Default Panel",
			"Panels",
			true,
			$("<div	id=\"protoDefaultPanel\"  class=\"panel panel-default\"> <div class=\"panel-body\">Basic panel example</div>"),
			".panel-body"));
	addWidget(new widget(
			3,
			"Panel With Heading",
			"Panels",
			true,
			$("<div class=\"panel panel-primary\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Panel primary</h3></div><div class=\"panel-body\">Panel content</div></div>"),
			".panel-body"));
	addWidget(new widget(
			4,
			"Panel With Heading",
			"Panels",
			true,
			$("<div class=\"panel panel-success\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Panel success</h3></div><div class=\"panel-body\">Panel content</div></div>"),
			".panel-body"));
	addWidget(new widget(
			5,
			"Panel With Heading",
			"Panels",
			true,
			$("<div class=\"panel panel-default\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Panel title</h3></div><div class=\"panel-body\">Panel content</div></div>"),
			".panel-body"));
	addWidget(new widget(
			6,
			"Panel With Heading",
			"Panels",
			true,
			$("<div class=\"panel panel-default\"><div class=\"panel-heading\"><h3 class=\"panel-title\">Panel title</h3></div><div class=\"panel-body\">Panel content</div></div>"),
			".panel-body"));
}

/**
 * Widget Constructor These widgets are going to be in the widget drawer and
 * used to drag and drop to make the web page's content
 * 
 * @param id
 *            the id of the widget
 * @param name
 *            the name of the widget
 * @param type
 *            is the type of widget
 * @param object
 *            the dom object
 * @param droppableTarget
 *            is the target that other widgets will be dropped into
 */
function widget(id, name, type, iscontainer, object, droppableTarget) {
	this.id = id;
	this.name = name;
	this.type = type;
	this.iscontainer = iscontainer;
	this.object = object;
	this.droppableTarget = droppableTarget;
}

/**
 * Adds a widget to the components
 * 
 * @param object
 *            the widget that is added to the components
 * @returns {Boolean} true if the object was added, false if not.
 */
function addWidget(object, reportError) {
	if (object.id != null && widgetsArray[object.id] == null) {
		if (widgetsTypesArray[object.type] == null) {
			widgetsTypesArray[object.type] = new widgetType(object.type);
		}
		widgetsTypesArray[object.type].widgets[object.id] = object;
		widgetsArray[object.id] = object;
		return true;
	}
	if (reportError) {
		widgetErrorArray[widgetErrorArray.length] = object;
	}
	return false;
}

function widgetType(name) {
	this.name = name;
	this.widgets = new Array();
}

function makeWidgetComponentDraggable(object, widget) {
	$(object).css('cursor', 'pointer');
	$(object).draggable({
		helper : "clone",
		revert : "invalid",
		cursor : "move",
		scroll : false,
		zIndex : 100,
		start : function dragging() {
			if (object == '.wc-draggableChild') {
				currentDraggedObject = $(object).parent();
			} else
				currentDraggedObject = this;
		}

	});
	if (object == '.wc-draggableChild') {
		$(object).draggable('option', 'helper', $(object).parent());
		$(object).draggable('option', 'cancel', false);
	}
}

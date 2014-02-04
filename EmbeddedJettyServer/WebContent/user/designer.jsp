<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Designer</title>
<script type="text/javascript" src="/includes/jquery-2.0.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" href="http://jqueryui.com//resources/demos/style.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<!-- MY CSS -->
<link rel="stylesheet" type="text/css" href="/css/webdesigner.css">

<script type="text/javascript" src="/includes/webdesigner.js"></script>
<script type="text/javascript" src="/includes/components.js"></script>
<script type="text/javascript">
	//the object that is currently being dragged and dropped
	var currentDraggedObject;
	//the item that has been selected
	var selectedWidget;
	var lastDroppedObject;
	var lastContainerUsedObject;
	var mainContentObject = document.getElementById('mainContent');
	var potentialDroppableTarget;
	$(function() {
		$('#mainContent')
				.droppable(
						{
							accept : ".wc-draggable, .wc-draggableChild",
							activeClass : "ui-state-hover",
							hoverClass : "ui-state-active",
							drop : function(event, ui) {
								lastContainerUsedObject = this;
								if ($(currentDraggedObject).hasClass(
										"wc-draggable")) {
									lastDroppedObject = getChildClone(currentDraggedObject);
								} else if ($(currentDraggedObject).hasClass(
										"wd-ui-component")) {
									lastDroppedObject = currentDraggedObject;
								}
								var widget = $(currentDraggedObject.children[0])
										.data('widget');
								lastDroppedObject.data("widget", widget);
								lastDroppedObject.appendTo(this);
								makeUIComponentDraggable(lastDroppedObject);
								lastDroppedObject.addClass('uic-draggable');
								makeUIComponentDroppable(lastDroppedObject);
								lastDroppedObject.addClass('wd-ui-component');
								$(".wd-ui-component")
										.mouseenter(function() {
											console.log("mouse enter");
											potentialDroppableTarget = this;
											console.log(this);
										})
										.mouseleave(function() {
											console.log("mouse leave");
											console.log(this);
										})
										.click(
												function() {
													if ($(this).attr('id') != 'mainContent') {
														if (selectedWidget != null) {
															$(selectedWidget)
																	.resizable(
																			'destroy');
															$(selectedWidget).contentEditable = "false";
														}
														selectedWidget = this;
														$(this).resizable();
														$(this)
																.attr(
																		'contentEditable',
																		true);
													}
												})
										.dblclick(
												function() {

													console.log("double click");
													console
															.log($(selectedWidget));
													console.log($(
															selectedWidget)
															.data('widget'));
												});
							}
						});
	});
	function getChildClone(parent) {
		return $($($(currentDraggedObject).clone()).children()[0]);
	}
</script>

</head>


<body onload="addWidgetsToWidgetDrawer()">
	<div id="wrapper">
		<jsp:include page="/includes/nav-bar-main.jsp"></jsp:include>
		<%
			// This is a scriptlet.  Notice that the "date"
			// variable we declare here is available in the
			// embedded expression later on.
			System.out.println("Evaluating date now");
			java.util.Date date = new java.util.Date();
		%>
		Hello! The time is now
		<%=date%>
		<div class="row" style="min-height: 900px; background-color: gray;">
			<div id="widgetDrawer" class="col-xs-1 col-sm-1 col-md-2 col-lg-2" style="overflow-x: scroll; overflow-y: hidden; min-height: 900px;">
				<div id="accordion"></div>
			</div>
			<div class=".col-xs-11 col-sm-11 col-md-10 col-lg-10" style="min-height: 500px; background-color: black; padding: 5px;">
				<div id="mainContent" class="droppable ui-widget-header wd-ui-component" style="min-height: 500px; background-color: white;">
					<p>Content goes here</p>

				</div>

			</div>
			<jsp:include page="/includes/footer.jsp"></jsp:include></div>
	</div>
</body>
</html>

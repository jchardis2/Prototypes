<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Designer</title>
<script type="text/javascript"
	src="/WebDesigner/includes/jquery-2.0.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet"
	href="http://jqueryui.com//resources/demos/style.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<!-- MY CSS -->
<link rel="stylesheet" type="text/css"
	href="/WebDesigner/css/webdesigner.css">

<script type="text/javascript"
	src="/WebDesigner/includes/webdesigner.js"></script>
<script>
	var selectedObject;
	var lastDroppedObject;
	var lastContainerUsedObject;
	var mainContentObject = document.getElementById('mainContent');
	$(function() {
		makeWidgetComponentDraggable('.wc-draggable');
		makeWidgetComponentDraggable('.wc-draggableChild');
		$('#mainContent').droppable({
			accept : ".wc-draggable, .wc-draggableChild",
			activeClass : "ui-state-hover",
			hoverClass : "ui-state-active",
			drop : function(event, ui) {
				lastContainerUsedObject = this;
				lastDroppedObject = getChildClone(selectedObject);
				lastDroppedObject.appendTo(this);
				makeUIComponentDraggable(lastDroppedObject);
				lastDroppedObject.addClass('uic-draggable');
				makeUIComponentDroppable(lastDroppedObject);
			}
		});
	});
	function getChildClone(parent) {
		return $($($(selectedObject).clone()).children()[0]);
	}
</script>

</head>


<body>
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
			<div id="col-widgets" class="col-xs-1 col-sm-1 col-md-2 col-lg-2">
				<ul id="widgetDrawer" class="list-group">
					<li
						class="list-group-item list-group-item-widget wc-draggable ui-widget-content"><button
							type="button" class="btn btn-default wc-draggableChild">Example
							Button</button></li>
					<li class="list-group-item list-group-item-widget wc-draggable"><div
							class="panel panel-default">
							<div class="panel-body">Basic panel example</div>
						</div></li>
					<li class="list-group-item list-group-item-widget wc-draggable">Cras
						justo odio</li>
					<li class="list-group-item list-group-item-widget wc-draggable">Cras
						justo odio</li>
					<li class="list-group-item list-group-item-widget wc-draggable">Cras
						justo odio</li>
				</ul>
			</div>
			<div class=".col-xs-11 col-sm-11 col-md-10 col-lg-10"
				style="min-height: 500px; background-color: black; padding: 5px;">
				<div id="mainContent" class="droppable ui-widget-header "
					style="min-height: 500px; background-color: white;">
					<p>Content goes here</p>

				</div>

			</div>
			<jsp:include page="/includes/footer.jsp"></jsp:include></div>
	</div>
</body>
</html>

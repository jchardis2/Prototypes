// the object that is currently being dragged and dropped
var currentDraggedObject;
// the item that has been selected
var selectedWidget;
var lastDroppedObject;
var lastContainerUsedObject;
var mainContentObject = document.getElementById('mainContent');
var potentialDroppableTarget;
$(function() {
	$('#mainContent').droppable({
		accept : ".wc-draggable, .wc-draggableChild",
		activeClass : "ui-state-hover",
		hoverClass : "ui-state-active",
		drop : function(event, ui) {
			lastContainerUsedObject = this;
			if ($(currentDraggedObject).hasClass("wc-draggable")) {
				lastDroppedObject = getChildClone(currentDraggedObject);
			} else if ($(currentDraggedObject).hasClass("wd-ui-component")) {
				lastDroppedObject = currentDraggedObject;
			}
			var widget = $(currentDraggedObject.children[0]).data('widget');
			lastDroppedObject.data("widget", widget);
			lastDroppedObject.appendTo(this);
			makeUIComponentDraggable(lastDroppedObject);
			lastDroppedObject.addClass('uic-draggable');
			makeUIComponentDroppable(lastDroppedObject);
			lastDroppedObject.addClass('wd-ui-component');
			$(".wd-ui-component").mouseenter(function() {
				console.log("mouse enter");
				potentialDroppableTarget = this;
				console.log(this);
			}).mouseleave(function() {
				console.log("mouse leave");
				console.log(this);
			}).click(function() {
				if ($(this).attr('id') != 'mainContent') {
					if (selectedWidget != null) {
						$(selectedWidget).resizable('destroy');
						$(selectedWidget).contentEditable = "false";
					}
					selectedWidget = this;
					$(this).resizable();
					$(this).attr('contentEditable', true);
				}
			}).dblclick(function() {

				console.log("double click");
				console.log($(selectedWidget));
				console.log($(selectedWidget).data('widget'));
			})
		}
	});
	$('#mainContentContainer').resizable();
});
function getChildClone(parent) {
	return $($($(currentDraggedObject).clone()).children()[0]);
}

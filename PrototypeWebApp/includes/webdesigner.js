designer = new designer();
function makeDroppable(droppableObject) {
	$(droppableObject).droppable({

	});
}

function makeUIComponentDraggable(object) {
	$(object).css('cursor', 'pointer');
	$(object).draggable({
		containment : "parent, #mainContent",
		helper : "original",
		cursor : "move",
		scroll : true,
		zIndex : 100,
		cancel : false,
		start : function dragging() {
			currentDraggedObject = this;
		}

	});
}

function makeUIComponentDroppable(object) {
	$(object).droppable(
			{
				accept : ".wc-draggable, .wc-draggableChild,.uic-draggable",
				activeClass : "ui-state-hover",
				hoverClass : "ui-state-active",
				greedy : true,
				drop : function(event, ui) {
					console.log("potentialDroppableTarget: ");
					console.log(potentialDroppableTarget);
					lastContainerUsedObject = this;
					var widget = $(lastContainerUsedObject).data("widget");
					if (widget.iscontainer) {
						if ($(currentDraggedObject).hasClass('uic-draggable')) {
							$(currentDraggedObject).appendTo(
									$($(this).find(widget.droppableTarget)));
						} else {
							lastDroppedObject = getChildClone(currentDraggedObject);
							lastDroppedObject.appendTo($($(this).find(
									widget.droppableTarget)));
							makeUIComponentDraggable(lastDroppedObject);
							lastDroppedObject.addClass('uic-draggable');
							lastDroppedObject.addClass('wd-ui-component');
							$(".wd-ui-component").mouseenter(function() {
								potentialDroppableTarget = this;
								console.log("mouse enter");
								console.log(this);
							}).mouseleave(function() {
								console.log("mouse leave");
								console.log(this);
							});
						}
					}
				}
			});
}

function get(key) {
	return map[k];
}

function designer() {
	droppableMap = new Object();

	/**
	 * Accepts a droppableObject
	 */
	function setDroppable(droppableObject) {
		$(droppableObject).droppable({});
	}

	function getDroppableMap() {
		return droppableMap;
	}
}

function droppableObject() {

}
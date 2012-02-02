function showDateIndicator(data) {
	showIndicatorRegion(data, "dateIndicator");
}

function showIndicatorRegion(data, regionId) {
  if (data.status == "begin") {
	  document.getElementById(regionId).style.display = "inline";
  } else if (data.status == "success") {
	  document.getElementById(regionId).style.display = "none";
  }
}